package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Board;
import com.jsframe.wadizit.entity.BoardComment;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.repository.BoardCommentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log
public class BoardCommentService {
    @Autowired
    private BoardCommentRepository bcRepo;

    // 게시글 댓글 작성
    public String create(BoardComment boardComment) {
        log.info("create()");
        String msg = null;

        try {
            bcRepo.save(boardComment);
            log.info("bComNum : " + boardComment.getBComNum());
            log.info("content : " + boardComment.getContent());
            log.info("date : " + boardComment.getDate());

            msg = "댓글 등록을 완료했습니다.";
        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "댓글 등록을 실패했습니다.";
        }

        return msg;
    }

    // 게시글 댓글 상세 출력 => 피료없음
    public BoardComment read(Long bComNum) {
        log.info("read()");

        BoardComment boardComment = bcRepo.findById(bComNum).get();
        log.info("댓글 : " + boardComment.getContent());

        return boardComment;
    }

    // 게시글 댓글 수정
    public String update(Long bComNum, BoardComment boardComment) {
        log.info("update()");
        String msg = null;

        BoardComment bComment = bcRepo.findById(bComNum).get();
        bComment.setContent(boardComment.getContent());

        try {
            bcRepo.save(bComment);
            msg = "댓글 수정을 완료했습니다.";

        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "댓글 수정을 실패했습니다.";
        }

        return msg;
    }

    // 게시글 댓글 삭제
    public String delete(Long bComNum) {
        log.info("delete()");
        String msg = null;

        try {
            bcRepo.deleteById(bComNum);
            msg = "댓글 삭제를 완료했습니다.";
        } catch (Exception e) {
            msg = "댓글 삭제를 실패했습니다.";
        }

        return msg;
    }

    // 게시글 댓글 리스트 출력
    public Iterable<BoardComment> getList(BoardComment boardComment) {
        log.info("getList()");
        Iterable<BoardComment> bcList = bcRepo.findAll();

        return bcList;
    }

}
