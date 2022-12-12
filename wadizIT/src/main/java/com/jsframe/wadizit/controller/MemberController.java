package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Member;
import com.jsframe.wadizit.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
public class MemberController {
    @Autowired
    private MemberService mServ;

    @PostMapping("join")
    @ResponseBody
    public boolean join(Member member) {
        boolean result = mServ.join(member);
        return result;
    }

    @GetMapping("checkId")
    @ResponseBody
    public int checkId (@RequestParam String id) {
        return mServ.checkId(id);
    }
}
