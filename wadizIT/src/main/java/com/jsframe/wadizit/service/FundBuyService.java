package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.*;
import com.jsframe.wadizit.repository.FundBuyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Log
public class FundBuyService {
    @Autowired
    private FundBuyRepository fbRepo;

    public String buyFunding(FundBuy fundBuy, HttpSession session) {
        log.info("buyFunding()");
        String msg = null;
        try {
            //Member member = (Member) session.getAttribute("mem");
            //fundBuy.setMemberNum(member);
            fbRepo.save(fundBuy);
            msg = "펀딩 후원 성공";
        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "펀딩 후원 실패";
        }

        return msg;
    }

    public String updateFundBuy(FundBuy fundBuy, HttpSession session) {
        log.info("updateFundBuy()");
        String msg = null;
        try {
            //Member member1 = (Member) session.getAttribute("mem");
            //fundBuy.setMemberNum(member1);
            log.info("" + fundBuy.getFundingBuyNum());

            fbRepo.save(fundBuy);
            msg = "후원 취소 성공";
        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "후원 취소 실패";
        }

        return msg;
    }


    public FundBuy readFundBuy(Long fundBuy) {
        log.info("readFundBuy()");
        FundBuy readfb = fbRepo.findById(fundBuy).get();

        return readfb;
    }

    public String deleteFundBuy(Long fundBuy) {
        log.info("deleteFundBuy()");
        fbRepo.deleteById(fundBuy);
        String msg = "후원 삭제 완료";
        return msg;
    }
}
