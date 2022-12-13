package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Board;
import com.jsframe.wadizit.service.BoardService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;


@RestController
@Log
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService Serv;

    //게시글 생성
    @PostMapping("")
    public String create(@RequestBody Board board){
        log.info("create()");

        String msg = Serv.create(board);
        return msg;

    }

    //게시글 읽기
    @GetMapping("")
    public Board read(Long boardNum){
        log.info("read()");
        return Serv.read(boardNum);
    }

    //게시글 수정
    @PutMapping("")
    public String update(@RequestBody Board board, Long boardNum){
        log.info("update()");
        return Serv.update(board, boardNum);
    }

    //게시글 삭제
    @DeleteMapping("")
    public String delete(Long boardNum){
        log.info("delete()");
        return Serv.delete(boardNum);
    }

    //게시글 리스트
    @GetMapping("/list")
    public Iterable<Board> getList(Board board){
        log.info("getList()");
        return Serv.getList(board);
    }




}
