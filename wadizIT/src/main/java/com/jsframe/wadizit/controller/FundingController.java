package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.service.FundingService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class FundingController {

    @Autowired
    private FundingService Serv;

    //펀딩 생성
    @PostMapping("funding/write")
    public String create(@RequestBody Funding funding){
        log.info("create()");

        String msg = Serv.create(funding);
        return msg;
    }

    //펀딩 게시글 읽기
    @GetMapping("funding/read")
    public Funding getFunding(Long fundingNum){
        log.info("getFunding()");
        return Serv.getFunding(fundingNum);
    }

    //펀딩 게시글 삭제
    @DeleteMapping("funding/delete")
    public String deleteFunding(Long fundingNum){
        log.info("deleteFunding()");
        return Serv.deleteFunding(fundingNum);
    }

    //펀딩 게시글 리스트
    @GetMapping("funding/list")
    public Iterable<Funding> getList(Funding funding){
        log.info("getList()");
        return Serv.getList(funding);
    }

    //펀딩 게시글 수정


}
