package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.*;
import com.jsframe.wadizit.repository.DonateRepository;
import com.jsframe.wadizit.repository.FundingRepository;
import com.jsframe.wadizit.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log
public class DonateService {
    @Autowired
    private DonateRepository fbRepo;

    @Autowired
    private FundingRepository fRepo;

    @Autowired
    private MemberRepository mRepo;

    public String createDonate(Donate donate, long fundingNum, Member member) {
        log.info("buyFunding()");
        String msg = null;
        try {
            Funding funding = fRepo.findById(fundingNum).get();
            donate.setMemberNum(member);
            donate.setFundingNum(funding);
            fbRepo.save(donate);

            //결제 포인트만큼 내 보유 포인트 감소
            Member mData = mRepo.findById(member.getMemberNum()).get();
            //보유 포인트 - 결제 포인트
            int remainPoint =  mData.getPoint() - Long.valueOf(donate.getPayAmount()).intValue();
            member.setPoint(remainPoint);
            mRepo.save(member);

            //펀딩 모금 금액 추가
            long gatherAmount = funding.getCurrentAmount() + donate.getPayAmount();
            funding.setCurrentAmount(gatherAmount);
            fRepo.save(funding);

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
            Member member1 = (Member) session.getAttribute("mem");
            donate.setMemberNum(member1);
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

    public List<Donate> getMyList(Donate donate, HttpSession session) {
        Member member = (Member) session.getAttribute("mem");
        donate.setMemberNum(member);
        List<Donate> myList = fbRepo.findAllByMemberNum(member);
        return myList;
    }

    public List<Integer> getFundingPerson(long fundingNum) {
        log.info("getFundingPerson()");
        List<Integer> dData = null;

        try{
           dData  = fbRepo.findDistinctByFundingNumWithNativeQuery(fundingNum);

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return dData;
    }
}
