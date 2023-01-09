package com.jsframe.wadizit.dto;

import com.jsframe.wadizit.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class TokenInitRes {
    private Member member;
    private TokenSimple token;
    private long availableToken;
    private List<TokenOrderSimple> tokenOrderList;
    private List<TokenOrderSimple> myOrderList;
    private List<TokenTransactionSimple> tokenTransactionList;

    private int retCode;
    private String errorMsg;

    public TokenInitRes() {
        this.retCode = 200;
    }
}
