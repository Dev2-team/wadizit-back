package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Board;
import com.jsframe.wadizit.entity.BoardFile;
import com.jsframe.wadizit.repository.BoardFileRepository;
import com.jsframe.wadizit.repository.BoardRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Log
@Service
@Transactional
public class BoardFileService {
    @Autowired
    private BoardRepository bRefo;
    @Autowired
    private BoardFileRepository bfRefo;

    public String upload(List<MultipartFile> files,
                         HttpSession session, long boardNum)
            throws Exception {
        log.info("upload()");

        // 파일 저장 위치 지정
        String realPath = session.getServletContext().getRealPath("/");
        realPath += "upload/";

        File folder = new File(realPath);
        if (!folder.isDirectory()) { // 폴더가 없을 경우 폴더 생성
            folder.mkdir();
        }

        for (MultipartFile mf : files) {
            String originName = mf.getOriginalFilename();

            if (originName.equals("")) { // 업로드할 파일이 없을 경우 정지
                return "업로드할 파일이 없습니다.";
            }

            BoardFile boardFile = new BoardFile();

            boardFile.setBoardNum(bRefo.findById(boardNum).get());
            boardFile.setOriginName(originName);

            String sysName = System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            boardFile.setSysName(sysName);

            File file = new File(realPath + sysName);

            mf.transferTo(file); // upload 폴더에 파일 저장

            bfRefo.save(boardFile);
        }

        return "파일 업로드를 성공했습니다.";
    }

    public BoardFile read(Long boardFileNum) {
        log.info("read()");
        BoardFile boardFile = bfRefo.findById(boardFileNum).get();
        log.info("파일 정보 : " + boardFile);
        return boardFile;
    }

//    public String update(List<MultipartFile> files,
//                         HttpSession session, long boardNum) {
//        log.info("update()");
//        String msg = null;
//
//        try {
//            Board bNum = bRefo.findById(boardNum).get();
//            long updateFileNum = bfRefo.findByBoardFileNum(bNum);
//
//            msg = "파일 수정 성공";
//        } catch (Exception e) {
//            msg = "파일 수정 실패";
//        }
//
//        return msg;
//    }

    public String delete(HttpSession session, long boardFileNum) {
        log.info("delete()");
        String msg = null;

        String realPath = session.getServletContext().getRealPath("/");
        realPath += "upload";

        try {
            BoardFile boardFile = new BoardFile();
            String delPath = realPath + boardFile.getSysName();

            File file = new File(delPath);
            file.delete();

            bfRefo.deleteById(boardFileNum);

            msg = "파일 삭제 완료";
        } catch (Exception e) {
            msg = "파일 삭제 실패";
        }

        return msg;
    }

    public Iterable<BoardFile> getList(long boardNum) {
        log.info("getList()");

        Board bNum = bRefo.findById(boardNum).get();
        Iterable<BoardFile> bfList = bfRefo.findAllByBoardNum(bNum);
        return bfList;
    }

    public ResponseEntity<Resource> download(long boardFileNum, HttpSession session)
            throws IOException {
        log.info("download()");

        BoardFile bFile = bfRefo.findById(boardFileNum).get();

        String realPath = session.getServletContext().getRealPath("/");
        realPath += "upload/" + bFile.getSysName();

        InputStreamResource fResource = new InputStreamResource(new FileInputStream(realPath));

        String fileName = URLEncoder.encode(bFile.getOriginName(), "UTF-8");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + fileName)
                .body(fResource);
    }
}
