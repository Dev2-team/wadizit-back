package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FundingRepository extends CrudRepository<Funding, Long> {
    List<Funding> findAllByMemberNum(Member member);
}
