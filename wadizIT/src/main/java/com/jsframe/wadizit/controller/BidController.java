package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Bid;
import com.jsframe.wadizit.repository.BidRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log
@RestController()
@RequestMapping("/bid")
public class BidController {
    @Autowired
    private BidRepository bidRepo;
    private HttpStatus respStatus;

    @PostMapping("")
    public ResponseEntity create(@RequestBody Bid bid) {
        Bid ret = bidRepo.save(bid);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }

    @GetMapping("")
    public ResponseEntity read(long bidNum) {
        Optional<Bid> ret = bidRepo.findById(bidNum);
        if (ret.isEmpty()) respStatus = HttpStatus.NO_CONTENT;
        else respStatus = HttpStatus.OK;
        return ResponseEntity.status(respStatus).body(ret);
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody Bid bid) {
        Bid ret = bidRepo.save(bid);
        return ResponseEntity.status(HttpStatus.OK).body(ret);
    }

    @DeleteMapping("")
    public ResponseEntity delete(long bidNum) {
        bidRepo.deleteById(bidNum);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/list")
    public ResponseEntity getList() {
        List<Bid> bidList = bidRepo.findAll();
        if (bidList.size() == 0) respStatus = HttpStatus.NO_CONTENT;
        else respStatus = HttpStatus.OK;
        return ResponseEntity.status(respStatus).body(bidList);
    }
}
