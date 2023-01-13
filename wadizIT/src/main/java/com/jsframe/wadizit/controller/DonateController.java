package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Donate;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.service.DonateService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log
@RestController
@RequestMapping("/donate")
public class DonateController {
    @Autowired
    private DonateService Serv;

    @PostMapping("")
    public String create(@RequestBody Donate donate, long fundingNum, HttpSession session) {
        log.info("ddd" + donate);
        Member member = (Member) session.getAttribute("mem");
        return Serv.createDonate(donate, fundingNum, member);
    }

    @PutMapping("")
    public String update(@RequestBody Donate donate, HttpSession session) {
        return Serv.updateDonate(donate, session);
    }

    @GetMapping("")
    public Donate read(Long donateNum) {
        return Serv.readDonate(donateNum);
    }

    @DeleteMapping("")
    public String delete(Long donateNum) {
        return Serv.deleteDonate(donateNum);
    }

    @GetMapping("/dlist")
    public List<Donate> getMyList(Donate donate, HttpSession session){
        return Serv.getMyList(donate, session);
    }

    //펀딩 후원자 리스트 출력
    @GetMapping("/getFundingPerson")
    public List<Integer> getFundingPerson(long fundingNum){
        log.info("getFundingPerson()");
        return Serv.getFundingPerson(fundingNum);
    }
}
