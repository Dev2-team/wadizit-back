package com.jsframe.wadizit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "fundbuy")
@Data
public class fundBuy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fBuyNum;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private long payAmount;
}
