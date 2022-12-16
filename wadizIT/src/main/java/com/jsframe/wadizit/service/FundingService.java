package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.repository.FundingRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class FundingService {

    @Autowired
    private FundingRepository fRepo;

    //펀딩 게시글 생성
    public String create(Funding funding) {
        log.info("create()");
        String msg = null;

        log.info("" + funding.getFundingNum());
        //log.info(funding.getContent());
        log.info(funding.getTitle());

        try {
            fRepo.save(funding);
            msg = "펀딩 등록 성공";
        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "펀딩 등록 실패";
        }

        return msg;
    }

    //펀딩 게시글 읽기
    public Funding getFunding(Long fundingNum) {
        log.info("getFunding()");

        Funding fund = fRepo.findById(fundingNum).get();
        log.info("출력 : " + fund.getFundingNum());
        return fund;
    }

    //펀딩 게시글 삭제
    public String deleteFunding(Long fundingNum) {
        log.info("deleteFunding()");
        String msg = null;

        try {
            fRepo.deleteById(fundingNum);
            msg = "삭제 성공";
        } catch (Exception e) {
            msg = "삭제 실패";
        }
        return msg;
    }

    //펀딩 게시글 리스트
    public Iterable<Funding> getList(Funding funding) {
        log.info("getList()");
        Iterable<Funding> fList = fRepo.findAll();

        return fList;
    }

    //펀딩 게시글 수정
    public String update(Funding funding, Long fundingNum) {
        log.info("update()");
        String msg = null;

        Funding funding3 = fRepo.findById(fundingNum).get();
        funding3.setTitle(funding.getTitle());

        try {
            fRepo.save(funding3);
            msg = "수정 성공";
        } catch (Exception e) {
            msg = "수정 실패";
        }
        return msg;
    }
}
