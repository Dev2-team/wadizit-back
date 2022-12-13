package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Auction;
import com.jsframe.wadizit.entity.Board;
import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Log
@RestController
public class MemberController {
    @Autowired
    private MemberService mServ;

    // 회원가입 (create)
    @PostMapping("join")
    public boolean join(@RequestBody Member member) {
        log.info("join()");
        boolean result = mServ.join(member);
        return result;
    }

    // 로그인
    @PostMapping("login")
    public boolean login(@RequestBody Member member, HttpSession session) {
        session.setAttribute("mem", member);
        boolean result = mServ.login(member, session);
        return result;
    }

    // 로그아웃
    @GetMapping("logout")
    public boolean logout(HttpSession session) {
        session.removeAttribute("mem");
        return true;
    }

    // 회원조회 (read)
    @GetMapping("getMember")
    public Member getMember(Long MemberNum){
        log.info("getMember()");
        return mServ.getMember(MemberNum);
    }

    // 회원정보 수정 (update)
    @PutMapping ("updateMember")
    public boolean updateMember(@RequestBody Member member, HttpSession session){
        log.info("updateMember()");
        Member mb = (Member)session.getAttribute("mem");
        boolean result = mServ.updateMember(member, mb);
        return result;
    }


    // 회원탈퇴 (delete)
    @DeleteMapping ("deleteMember")
    public boolean deleteMember(Long MemberNum){
        log.info("deleteMember()");
        boolean result = mServ.deleteMember(MemberNum);
        return result;
    }

    @GetMapping("checkId")
    public int checkId (@RequestParam String id) {
        return mServ.checkId(id);
    }
}
