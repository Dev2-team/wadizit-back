package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findMemberById(String id) ;

    int countMemberById(String id);
}
