package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.entity.Reward;
import org.springframework.data.repository.CrudRepository;

public interface FundingRewardRepository extends CrudRepository<Reward, Long> {
    Iterable<Reward> findByFundingNum(Funding fundingData);
}
