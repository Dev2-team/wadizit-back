package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.BoardComment;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.service.BoardCommentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class BoardCommentController {
    @Autowired
    private BoardCommentService bcServ;

    @PostMapping("board/comment/write")
    public String create(@RequestBody BoardComment boardComment) {
        log.info("create()");
        String msg = bcServ.create(boardComment);
        return msg;
    }

    @GetMapping("board/comment/read")
    public BoardComment read(Long bComNum) {
        log.info("read()");
        return bcServ.read(bComNum);
    }

    @PutMapping("board/comment/update")
    public String update(Long bComNum, @RequestBody BoardComment boardComment) {
        log.info("update()");
        String msg = bcServ.update(bComNum, boardComment);
        return msg;
    }

    @DeleteMapping("board/comment/delete")
    public String delete(Long bComNum) {
        log.info("delete()");
        String msg = bcServ.delete(bComNum);
        return msg;
    }

    @GetMapping("board/comment/list")
    public Iterable<BoardComment> getList(BoardComment boardComment) {
        log.info("getList()");
        return bcServ.getList(boardComment);
    }
}
