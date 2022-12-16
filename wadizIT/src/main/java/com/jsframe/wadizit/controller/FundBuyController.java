package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.FundBuy;
import com.jsframe.wadizit.service.FundBuyService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Log
@RestController
@RequestMapping("/funding/donate")
public class FundBuyController {
    @Autowired
    private FundBuyService Serv;

    @PostMapping("")
    public String fundBuy(@RequestBody FundBuy fundBuy, HttpSession session) {
        log.info("fundBuy()");
        return Serv.buyFunding(fundBuy, session);
    }

    @PutMapping("")
    public String updateFundBuy(@RequestBody FundBuy fundBuy, HttpSession session) {
        log.info("updateFundBuy(): " + fundBuy.getFundingBuyNum());
        return Serv.updateFundBuy(fundBuy, session);
    }

    @GetMapping("")
    public FundBuy readFundBuy(Long fundBuy) {
        log.info("readFundBuy()");
        return Serv.readFundBuy(fundBuy);
    }

    @DeleteMapping("")
    public String deleteFundBuy(Long fundBuy) {
        log.info("deleteFundBuy()");
        return Serv.deleteFundBuy(fundBuy);
    }
}
