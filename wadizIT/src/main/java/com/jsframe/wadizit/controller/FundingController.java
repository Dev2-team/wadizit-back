package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.service.FundingService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Log
@RequestMapping("/funding")
public class FundingController {
    @Autowired
    private FundingService Serv;

    //펀딩 생성
    @PostMapping("")
    public String create(@RequestBody Funding funding, HttpSession session){
        log.info("create()");
        Member member = (Member) session.getAttribute("mem");
        String msg = Serv.create(funding, member);
        return msg;
    }

    //펀딩 게시글 읽기
    @GetMapping("")
    public Funding getFunding(Long fundingNum){
        log.info("getFunding()");
        return Serv.getFunding(fundingNum);
    }

    //펀딩 게시글 삭제
    @DeleteMapping("")
    public String deleteFunding(Long fundingNum){
        log.info("deleteFunding()");
        return Serv.deleteFunding(fundingNum);
    }

    //펀딩 게시글 리스트
    @GetMapping("/list")
    public Iterable<Funding> getList(Funding funding){
        log.info("getList()");
        return Serv.getList(funding);
    }

    //펀딩 게시글 수정
    @PutMapping("")
    public String update(@RequestBody Funding funding, Long fundingNum, HttpSession session){
        log.info("update()");
        Member member = (Member) session.getAttribute("mem");
        log.info(""+member);
        return Serv.update(funding, fundingNum, member);
    }

    //펀딩 생성 내역 리스트(로그인한 유저)
    @GetMapping("/plist")
    public List<Funding> getMyList(Funding funding, HttpSession session){

        return Serv.getMyList(funding, session);
    }


}
