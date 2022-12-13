package com.jsframe.wadizit.service;


import com.jsframe.wadizit.entity.FundingComment;
import com.jsframe.wadizit.repository.FundingCommentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@Log
public class FundingCommentService {

    @Autowired
    private FundingCommentRepository fcRepo;


    public String create(FundingComment fCom) {
        log.info("create()");
        String msg = null;

        try{
            fcRepo.save(fCom);
            msg = "댓글 입력 성공";
        } catch (Exception e){
            msg = "댓글 입력 실패";
        }

        return msg;
    }
}
