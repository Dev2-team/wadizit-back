package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Board;

import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.repository.BoardRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
@Log
public class BoardService {

    @Autowired
    private BoardRepository bRepo;

    public String create(Board board) {
        log.info("create()");
        String msg = null;

        log.info(""+board.getBoardNum());
        log.info(board.getContent());
        log.info(board.getTitle());

        try{
            bRepo.save(board);
            msg = "게시물 등록 성공";
        } catch (Exception e){
            log.info(e.getMessage());
            msg = "게시물 등록 실패";
        }

        return msg;

    }

    public Board read(Long boardNum) {
        log.info("read()");

        Board boa2 = bRepo.findById(boardNum).get();
        log.info("출력 : "+ boa2.getContent());
        return boa2;

    }

    public String delete(Long boardNum) {
        log.info("deleteBoard()");
        String msg = null;

        try{
            bRepo.deleteById(boardNum);
            msg = "삭제 성공";
        } catch (Exception e){
            msg = "삭제 실패";
        }
        return msg;

    }

    public Iterable<Board> getList(Board board) {
        log.info("getList()");
        Iterable<Board> bList = bRepo.findAll();

        return bList;
    }

    public String update(Board board, Long boardNum, Long memberNum) {
        log.info("update()");
        String msg = null;


        Board board3 = bRepo.findById(boardNum).get();

        //로그인한 사람의 memberNum
        String memberInfo = String.valueOf(memberNum);
        log.info(memberInfo);
        log.info(""+board3.getMemberNum().getMemberNum());
        if(memberNum.equals(board3.getMemberNum().getMemberNum())){

            board3.setTitle(board.getTitle());
            board3.setContent(board.getContent());

            try{
                bRepo.save(board3);
                msg = "수정 성공";
            }catch (Exception e){
                msg = "수정 실패";
            }

        } else {
            msg = "작성자만 수정 가능합니다.";
        }

        return msg;
    }
}
