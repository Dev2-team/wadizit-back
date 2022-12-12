package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Bid;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidRepository extends CrudRepository<Bid, Long> {
    List<Bid> findAll();
}
