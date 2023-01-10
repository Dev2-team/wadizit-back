package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.entity.Payment;
import com.jsframe.wadizit.repository.MemberRepository;
import com.jsframe.wadizit.service.PaymentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Log
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private MemberRepository mRepo;

    @Autowired
    private PaymentService pServ;

    @GetMapping
    public void save(@RequestParam String oNum, @RequestParam String date, @RequestParam String id) {
        log.info("save()");
        Member mem = mRepo.findMemberById(id);
        log.info("mem : " + mem);
        pServ.save(oNum, date, mem);
    }
}
