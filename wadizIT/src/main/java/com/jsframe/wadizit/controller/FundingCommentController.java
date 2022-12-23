package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.FundingComment;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.service.FundingCommentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Log
@RestController
@RequestMapping("/funding/comment")
public class FundingCommentController {
    @Autowired
    private FundingCommentService fcServ;

    //펀딩 댓글 작성
    @PostMapping("")
    public String create(@RequestBody FundingComment fCom, HttpSession session, Long fundingNum) {
        log.info("create()");
        Member member = (Member) session.getAttribute("mem");
        String msg = fcServ.create(fCom, member, fundingNum);
        return msg;
    }

    //펀딩 댓글 읽기
    @GetMapping("")
    public FundingComment read(Long fComNum) {
        log.info("read()");
        FundingComment fCom2 = fcServ.read(fComNum);
        return fCom2;
    }

    //펀딩 댓글 수정
    @PutMapping("")
    public String update(@RequestBody FundingComment fCom, Long fComNum, HttpSession session) {
        log.info("update()");
        Member member = (Member) session.getAttribute("mem");
        String msg = fcServ.update(fCom, fComNum, member);
        return msg;
    }

    //펀딩 댓글 삭제
    @DeleteMapping("")
    public String delete(Long fComNum, HttpSession session) {
        log.info("delete()");
        Member member = (Member) session.getAttribute("mem");
        String msg = fcServ.delete(fComNum, member);
        return msg;
    }

    //펀딩 댓글 리스트
    @GetMapping("/list")
    public Iterable<FundingComment> getList() {
        log.info("getList()");
        Iterable<FundingComment> fComList = fcServ.getList();
        return fComList;
    }
}
