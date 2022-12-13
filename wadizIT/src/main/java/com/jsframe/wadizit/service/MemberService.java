package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Member;
import
        com.jsframe.wadizit.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Log
public class MemberService {
    @Autowired
    private MemberRepository mRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 회원가입 (create)
    public boolean join(Member member){
        log.info("join()");
        boolean result = false;

        String ePwd = encoder.encode(member.getPwd());// 비밀번호 암호화 처리
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

    // 로그인
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

    // 회원조회 (read)
    public Member getMember(Long MemberNum) {
        log.info("getMember()");

        Member member = mRepo.findById(MemberNum).get();
        log.info("출력 : "+ member.getMemberNum());

        return member;
    }

    // 회원정보 수정 (update)
    public boolean updateMember(Member member, Member mb){
        log.info("updateMember()");
        boolean result = false;

        try{
            String ePwd = encoder.encode(member.getPwd());
            mb.setPwd(ePwd);

            mb.setNickname(member.getNickname());
            mb.setName(member.getName());
            mb.setPhone(member.getPhone());
            mb.setEmail(member.getEmail());

            mRepo.save(mb);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    // 회원탈퇴 (delete)
    public boolean deleteMember(Long MemberNum) {
        log.info("deleteMember()");
        boolean result = false;

        try{
            mRepo.deleteById(MemberNum);
            result = true;
        } catch (Exception e){
            result = false;
        }
        return result;
    }

    // countMemberById가 0이 아니면 중복
    public int checkId(String id){
        log.info("checkId");
        return mRepo.countMemberById(id);
    }
}
