package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Member;
import
        com.jsframe.wadizit.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public int checkId(String id){
        log.info("checkId");

        return mRepo.countMemberById(id);
    }
}
