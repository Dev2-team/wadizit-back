package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.repository.FundingRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Handler;

@Service
@Log
public class FundingService {

    @Autowired
    private FundingRepository fRepo;

    //펀딩 게시글 생성
    public String create(Funding funding, Member member) {
        log.info("create()");
        String msg = null;

        log.info(""+funding.getFundingNum());
        //log.info(funding.getContent());
        log.info(funding.getTitle());
        funding.setMemberNum(member);

        try{
            fRepo.save(funding);
            msg = "펀딩 등록 성공";
        } catch (Exception e){
            log.info(e.getMessage());
            msg = "펀딩 등록 실패";
        }

        return msg;

    }

    //펀딩 게시글 읽기
    public Funding getFunding(Long fundingNum) {
        log.info("getFunding()");

        Funding fund = fRepo.findById(fundingNum).get();
        log.info("출력 : "+ fund.getFundingNum());
        return fund;

    }

    //펀딩 게시글 삭제
    public String deleteFunding(Long fundingNum) {
        log.info("deleteFunding()");
        String msg = null;

        try{
            fRepo.deleteById(fundingNum);
            msg = "삭제 성공";
        } catch (Exception e){
            msg = "삭제 실패";
        }
        return msg;

    }

    //펀딩 게시글 리스트(ALL)
    public Iterable<Funding> getList(Funding funding) {
        log.info("getList()");
        Iterable<Funding> fList = fRepo.findAll();

        return fList;
    }

    //펀딩 게시글 수정
    public String update(Funding funding, Long fundingNum, Member member) {
        log.info("update()");
        String msg = null;

        //로그인한 사람의 memberNum
        long loginPerson = member.getMemberNum();
        //펀드 작성자의 memberNum
        Funding fund3 = fRepo.findById(fundingNum).get();
        long fundWriter = (fund3.getMemberNum()).getMemberNum();

        log.info(""+fund3.getMemberNum().getMemberNum());
        Funding funding3 = fRepo.findById(fundingNum).get();
        funding3.setTitle(funding.getTitle());

        if ( loginPerson == fundWriter) {

            fund3.setTitle(funding.getTitle());
            fund3.setCategory(funding.getCategory());
            fund3.setStartDate(funding.getStartDate());
            fund3.setEndDate(funding.getEndDate());


            try {
                fRepo.save(funding3);
                msg = "수정 성공";
            } catch (Exception e) {
                msg = "수정 실패";
            }
        }
        else{
            msg = "작성자만 수정 가능합니다";
        }
        return msg;

    }

    //펀딩 생성 내역 리스트(로그인한 유저)
    public List<Funding> getMyList(Funding funding, HttpSession session) {
        Member member = (Member) session.getAttribute("mem");
        funding.setMemberNum(member);
        List<Funding> myList = fRepo.findAllByMemberNum(member);
        return myList;
    }

    public String statusUpdate(Funding funding, Long fundingNum) {
        log.info("statusUpdate()");
        String msg = null;

        Funding fData = fRepo.findById(fundingNum).get();

        try {
            fData.setStatus(funding.getStatus());
            fRepo.save(fData);
            msg = "상태 수정 성공";
        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "상태 수정 실패";
        }

        return msg;
    }
}
