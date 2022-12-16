package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.entity.Donate;
import com.jsframe.wadizit.service.DonateService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Log
@RestController
@RequestMapping("/donate")
public class DonateController {
    @Autowired
    private DonateService Serv;

    @PostMapping("")
    public String create(@RequestBody Donate donate, HttpSession session) {
        return Serv.createDonate(donate, session);
    }

    @PutMapping("")
    public String update(@RequestBody Donate donate, HttpSession session) {
        return Serv.updateDonate(donate, session);
    }

    @GetMapping("")
    public Donate read(Long donateNum) {
        return Serv.readDonate(donateNum);
    }

    @DeleteMapping("")
    public String delete(Long donateNum) {
        return Serv.deleteDonate(donateNum);
    }
}
