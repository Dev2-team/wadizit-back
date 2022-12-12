package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Board;

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

    public Board getBoard(Long boardNum) {
        log.info("getBoard()");

        Board boa2 = bRepo.findById(boardNum).get();
        log.info("출력 : "+ boa2.getContent());
        return boa2;

    }

    public String deleteBoard(Long boardNum) {
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
}
