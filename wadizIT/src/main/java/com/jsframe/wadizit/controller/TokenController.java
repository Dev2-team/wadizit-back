package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Token;
import com.jsframe.wadizit.repository.TokenRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenRepository tokenRepo;
    private HttpStatus respStatus;

    @PostMapping("")
    public ResponseEntity create(@RequestBody Token token) {
        token.setParValue(100);
        Token ret = tokenRepo.save(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }

    @GetMapping("")
    public ResponseEntity read(long tokenNum) {
        Optional<Token> ret = tokenRepo.findById(tokenNum);
        if (ret.isEmpty()) respStatus = HttpStatus.NO_CONTENT;
        else respStatus = HttpStatus.OK;
        return ResponseEntity.status(respStatus).body(ret);
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody Token token) {
        Token ret = tokenRepo.save(token);
        return ResponseEntity.status(HttpStatus.OK).body(ret);
    }

    @DeleteMapping("")
    public ResponseEntity delete(long tokenNum) {
        tokenRepo.deleteById(tokenNum);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/list")
    public ResponseEntity getList() {
        List<Token> tokenList = tokenRepo.findAll();
        if (tokenList.size() == 0) respStatus = HttpStatus.NO_CONTENT;
        else respStatus = HttpStatus.OK;
        return ResponseEntity.status(respStatus).body(tokenList);
    }
}
