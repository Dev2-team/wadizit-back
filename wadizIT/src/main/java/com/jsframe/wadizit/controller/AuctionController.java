package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Auction;
import com.jsframe.wadizit.repository.AuctionRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log
@RestController()
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    private AuctionRepository auctionRepo;
    private HttpStatus respStatus;

    @PostMapping("")
    public ResponseEntity create(@RequestBody Auction auction) {
        Auction ret = auctionRepo.save(auction);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }

    @GetMapping("")
    public ResponseEntity read(long auctionNum) {
        Optional<Auction> ret = auctionRepo.findById(auctionNum);
        if (ret.isEmpty()) respStatus = HttpStatus.NO_CONTENT;
        else respStatus = HttpStatus.OK;
        return ResponseEntity.status(respStatus).body(ret);
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody Auction auction) {
        Auction ret = auctionRepo.save(auction);
        return ResponseEntity.status(HttpStatus.OK).body(ret);
    }

    @DeleteMapping("")
    public ResponseEntity delete(long auctionNum) {
        auctionRepo.deleteById(auctionNum);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/list")
    public ResponseEntity getList() {
        List<Auction> auctionList = auctionRepo.findAll();
        if (auctionList.size() == 0) respStatus = HttpStatus.NO_CONTENT;
        else respStatus = HttpStatus.OK;
        return ResponseEntity.status(respStatus).body(auctionList);
    }
}
