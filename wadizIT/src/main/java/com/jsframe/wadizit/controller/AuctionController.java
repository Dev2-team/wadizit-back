package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Auction;
import com.jsframe.wadizit.repository.AuctionRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RestController
public class AuctionController {
    @Autowired
    private AuctionRepository auctionRepo;

    @GetMapping("/auction/list")
    public String getAuctionList() {
        Auction a = new Auction();
        a.setStatus(1);
        a.setAuctionNum(111);
        a.setCurrentPrice(1000);
        a.setStartPrice(2000);
        a.setTitle("a");
        auctionRepo.save(a);
        List<Auction> list = auctionRepo.findByStartPrice(a.getStartPrice());
        for (int i=0; i<list.size(); i++) {
            log.info("aaa: " + list.get(i));
        }
        log.info("bb: " + auctionRepo.findById(1L).get());
        return "aa";
    }
}
