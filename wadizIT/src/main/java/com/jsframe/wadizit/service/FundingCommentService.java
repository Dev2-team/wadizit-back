package com.jsframe.wadizit.service;


import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.entity.FundingComment;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.repository.FundingCommentRepository;
import com.jsframe.wadizit.repository.FundingRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class FundingCommentService {
    @Autowired
    private FundingRepository fRepo;

    @Autowired
    private FundingCommentRepository fcRepo;

    public String create(FundingComment fCom, Member member, Long fundingNum) {
        log.info("create()");
        String msg = null;

        Funding funding = fRepo.findById(fundingNum).get();
        fCom.setFundingNum(funding);
        fCom.setMemberNum(member);

        try {
            fcRepo.save(fCom);
            msg = "댓글 입력 성공";
        } catch (Exception e) {
            msg = "댓글 입력 실패";
        }

        return msg;
    }

    public FundingComment read(Long fComNum) {
        log.info("read()");

        FundingComment fCom = fcRepo.findById(fComNum).get();
        return fCom;
    }

    public Iterable<FundingComment> getList() {
        log.info("getList()");
        Iterable<FundingComment> fComList = fcRepo.findAll();
        return fComList;
    }

    public String delete(Long fComNum, Member member) {
        log.info("delete()");
        String msg = null;

        //로그인한 사람 정보
        long loginPerson = member.getMemberNum();
        //댓글 작성자 정보(memberNum)
        FundingComment fCom = fcRepo.findById(fComNum).get();
        long writer = (fCom.getMemberNum()).getMemberNum();

        if (loginPerson == writer) {
            try {
                fcRepo.deleteById(fComNum);
                msg = "댓글 삭제 성공";
            } catch (Exception e) {
                msg = "댓글 삭제 실패";
            }

        } else {
            msg = "댓글 작성자만 삭제 가능합니다.";
        }
        return msg;
    }

    public String update(FundingComment fCom, Long fComNum, Member member) {
        log.info("update()");
        String msg = null;

        //로그인한 사람 정보
        long loginPerson = member.getMemberNum();
        //댓글 작성자
        FundingComment fComData = fcRepo.findById(fComNum).get();
        long writer = (fComData.getMemberNum()).getMemberNum();

        if (loginPerson == writer) {
            try {
                fComData.setContent(fCom.getContent());
                log.info("fComData.setContent(fCom.getContent())");
                fcRepo.save(fComData);
                msg = "댓글 수정 성공";

            } catch (Exception e) {
                msg = "댓글 수정 실패";

            }
        } else {
            msg = "댓글 작성자만 수정 가능합니다.";
        }
        return msg;
    }
}
