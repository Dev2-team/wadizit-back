package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Auction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuctionRepository extends CrudRepository<Auction, Long> {
    List<Auction> findByStartPrice(long price);
}
