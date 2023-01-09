package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.entity.Payment;
import com.jsframe.wadizit.service.PaymentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Log
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService pServ;

    @PostMapping
    public void save(String oNum, String oName, Date date, HttpSession session) {
        log.info("save()");
        Member member = (Member) session.getAttribute("mem");
        pServ.save(oNum, oName, date, member);
    }
}
