package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TokenRepository extends CrudRepository<Token, Long> {
    List<Token> findAll();

    Token findByFundingNum(Funding fundingNum);
}
