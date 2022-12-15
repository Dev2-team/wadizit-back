package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Board;
import com.jsframe.wadizit.entity.BoardFile;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.util.List;

@Service
@Log
public class BoardFileService {
    public void upload(List<MultipartFile> files,
                       HttpSession session, Board board)
            throws Exception {
        log.info("upload()");

        // 파일 저장 위치 지정
        String filePath = session.getServletContext().getRealPath("/");

        File folder = new File(filePath);
        if (!folder.isDirectory()) { // 폴더가 없을 경우 폴더 생성
            folder.mkdir();
        }

        for (MultipartFile mf : files) {
            String originName = mf.getOriginalFilename();

            if (originName.equals("")) { // 업로드할 파일이 없을 경우 정지
                return;
            }

            BoardFile boardFile = new BoardFile();

            boardFile.setBoardNum(board);
            boardFile.setOriginName(originName);

            String sysName = System.currentTimeMillis()
                    + originName.substring(originName.lastIndexOf("."));
            boardFile.setSysName(sysName);

            File file = new File(filePath + sysName);

        }
    }
}
