package com.jsframe.wadizit.controller;

import com.jsframe.wadizit.service.FundingCommentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/funding/comment")
public class FundingCommentController {

    @Autowired
    private FundingCommentService fcServ;


}
