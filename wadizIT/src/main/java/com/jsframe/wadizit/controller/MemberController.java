package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Log
@RestController
public class MemberController {
    @Autowired
    private MemberService mServ;

    @PostMapping("join")
    public boolean join(@RequestBody Member member) {
        log.info("join()");
        boolean result = mServ.join(member);
        return result;
    }

    @PostMapping("login")
    public boolean login(@RequestBody Member member, HttpSession session) {
        session.setAttribute("mem", member);
        boolean result = mServ.login(member, session);
        return result;
    }

    @GetMapping("logout")
    public boolean logout(HttpSession session) {
        session.removeAttribute("mem");
        return true;
    }

    @GetMapping("checkId")
    public int checkId (@RequestParam String id) {
        return mServ.checkId(id);
    }
}
