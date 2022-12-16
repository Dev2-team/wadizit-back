package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.*;
import com.jsframe.wadizit.repository.DonateRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Log
public class DonateService {
    @Autowired
    private DonateRepository fbRepo;

    public String createDonate(Donate donate, HttpSession session) {
        log.info("buyFunding()");
        String msg = null;
        try {
            //Member member = (Member) session.getAttribute("mem");
            //fundBuy.setMemberNum(member);
            fbRepo.save(donate);
            msg = "펀딩 후원 성공";
        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "펀딩 후원 실패";
        }

        return msg;
    }

    public String updateDonate(Donate donate, HttpSession session) {
        String msg = null;
        try {
            //Member member1 = (Member) session.getAttribute("mem");
            //fundBuy.setMemberNum(member1);
            log.info("" + donate.getDonateNum());

            fbRepo.save(donate);
            msg = "후원 취소 성공";
        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "후원 취소 실패";
        }

        return msg;
    }


    public Donate readDonate(Long donateNum) {
        Donate readfb = fbRepo.findById(donateNum).get();

        return readfb;
    }

    public String deleteDonate(Long donateNum) {
        fbRepo.deleteById(donateNum);
        String msg = "후원 삭제 완료";
        return msg;
    }
}
