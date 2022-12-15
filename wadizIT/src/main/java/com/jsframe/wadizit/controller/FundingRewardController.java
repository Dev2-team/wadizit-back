package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.entity.Reward;
import com.jsframe.wadizit.service.FundingRewardService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/funding/reward")
public class FundingRewardController {

    @Autowired
    private FundingRewardService frServ;

    //리워드 생성
    @PostMapping("")
    public String create(@RequestBody Reward reward){
        log.info("create()");
        String msg = frServ.create(reward);
        return msg;
    }

    //리워드 읽기
    @GetMapping("")
    public Iterable<Reward> read(@RequestBody Reward fundingNum){
        log.info("read()");
        Iterable<Reward> rewardList = frServ.read(fundingNum);
        return rewardList;

    }

    //리워드 정보 수정
    @PutMapping("")
    public String update(@RequestBody Reward reward, Long rewardNum){
        log.info("update()");
        String msg = frServ.update(reward, rewardNum);
        return msg;
    }

    //리워드 삭제
    @DeleteMapping("")
    public String delete(Long rewardNum){
        log.info("delete()");
        String msg = frServ.delete(rewardNum);
        return msg;

    }

}
