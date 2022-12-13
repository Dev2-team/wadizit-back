package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Member;
import
        com.jsframe.wadizit.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Log
public class MemberService {
    @Autowired
    private MemberRepository mRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean join(Member member){
        log.info("join()");
        boolean result = false;

        // 비밀번호 암호화 처리
        String ePwd = encoder.encode(member.getPwd());
        member.setPwd(ePwd); // 암호화된 비밀번호로 변경

        try{
            mRepo.save(member);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean login(Member member, HttpSession session) {
        log.info("login()");
        boolean result = false;

        Member m = mRepo.findMemberById(member.getId());
        if (m != null) {//입력한 회원의 아이디가 있음
            String ePwd = m.getPwd();
            if (encoder.matches(member.getPwd(), ePwd)) {
                member = mRepo.findMemberById(member.getId());
                // 세션에 로그인 성공 정보 저장
                session.setAttribute("mem", member);
                result = true;
            } else {// 비밀번호가 맞지 않는 경우
                result = false;
            }
        } else {//잘못된 아이디 입력
            result = false;
        }
        return result;
    }

    public int checkId(String id){
        log.info("checkId");
        return mRepo.countMemberById(id);
    }
}
