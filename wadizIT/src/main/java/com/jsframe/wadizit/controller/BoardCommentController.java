package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.BoardComment;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.service.BoardCommentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Log
@RequestMapping("/board/comment")
public class BoardCommentController {
    @Autowired
    private BoardCommentService bcServ;

    @PostMapping("")
    public String create(@RequestBody BoardComment boardComment, HttpSession session) {
        log.info("create()");
        Member member = (Member) session.getAttribute("mem");
        String msg = bcServ.create(boardComment, member);
        return msg;
    }

    @GetMapping("")
    public BoardComment read(Long bComNum) {
        log.info("read()");
        return bcServ.read(bComNum);
    }

    @PutMapping("")
    public String update(@RequestBody BoardComment boardComment, HttpSession session, Long bComNum) {
        log.info("update()");
        Member member = (Member) session.getAttribute("mem");
        String msg = bcServ.update(boardComment, member, bComNum);
        return msg;
    }

    @DeleteMapping("")
    public String delete(HttpSession session, Long bComNum) {
        log.info("delete()");
        Member member = (Member) session.getAttribute("mem");
        String msg = bcServ.delete(member, bComNum);
        return msg;
    }

    @GetMapping("list")
    public Iterable<BoardComment> getList() {
        log.info("getList()");
        return bcServ.getList();
    }
}
