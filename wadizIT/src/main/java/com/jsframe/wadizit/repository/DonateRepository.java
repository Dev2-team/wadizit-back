package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Donate;
import com.jsframe.wadizit.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DonateRepository extends CrudRepository<Donate, Long> {
    List<Donate> findAllByMemberNum(Member member);


}
