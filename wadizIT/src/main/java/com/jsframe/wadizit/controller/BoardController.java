package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Board;
import com.jsframe.wadizit.service.BoardService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;


@RestController
@Log
public class BoardController {

    @Autowired
    private BoardService Serv;

    //게시글 생성
    @PostMapping("board/write")
    public String create(@RequestBody Board board){
        log.info("create()");

        String msg = Serv.create(board);
        return msg;

    }

    //게시글 읽기
    @GetMapping("board/read")
    public Board getBoard(Long boardNum){
        log.info("getBoard()");
        return Serv.getBoard(boardNum);
    }

    //게시글 삭제
    @GetMapping("board/delete")
    public String deleteBoard(Long boardNum){
        log.info("deleteBoard()");
        return Serv.deleteBoard(boardNum);
    }

    //게시글 리스트
    @GetMapping("board/list")
    public Iterable<Board> getList(Board board){
        log.info("getList()");
        return Serv.getList(board);
    }

}
