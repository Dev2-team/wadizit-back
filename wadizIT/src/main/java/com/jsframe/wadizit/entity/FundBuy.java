package com.jsframe.wadizit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FundBuy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fBuyNum;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private long payAmount;


}
