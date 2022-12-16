package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.BoardComment;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.repository.BoardCommentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class BoardCommentService {
    @Autowired
    private BoardCommentRepository bcRepo;

    // 게시글 댓글 작성
    public String create(BoardComment boardComment, Member member) {
        log.info("create()");
        String msg = null;

        try {
            boardComment.setMemberNum(member);
            bcRepo.save(boardComment);

            log.info("bComNum : " + boardComment.getBComNum());
            log.info("memberNum : " + boardComment.getMemberNum());
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
    public String update(BoardComment boardComment, Member member, Long bComNum) {
        log.info("update()");
        String msg = null;

        BoardComment bcData = bcRepo.findById(bComNum).get();
        long mNum = (bcData.getMemberNum()).getMemberNum();

        try {
            if (member.getMemberNum() == mNum) {
                bcData.setContent(boardComment.getContent());
                bcRepo.save(bcData);
                msg = "댓글 수정을 완료했습니다.";
            } else {
                msg = "작성자만 수정할 수 있습니다.";
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "댓글 수정을 실패했습니다.";
        }

        return msg;
    }

    // 게시글 댓글 삭제
    public String delete(Member member, Long bComNum) {
        log.info("delete()");
        String msg = null;

        BoardComment bcData = bcRepo.findById(bComNum).get();
        long mNum = (bcData.getMemberNum()).getMemberNum();

        try {
            if (member.getMemberNum() == mNum) {
                bcRepo.deleteById(bComNum);
                msg = "댓글 삭제를 완료했습니다.";
            }  else {
                msg = "작성자만 삭제할 수 있습니다.";
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "댓글 삭제를 실패했습니다.";
        }

        return msg;
    }

    // 게시글 댓글 리스트 출력
    public Iterable<BoardComment> getList() {
        log.info("getList()");
        Iterable<BoardComment> bcList = bcRepo.findAll();

        return bcList;
    }

}
