package com.jsframe.wadizit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Donate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long donateNum;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private long payAmount;

    @ManyToOne
    @JoinColumn(name = "rewardNum")
    private Reward rewardNum;

    @ManyToOne
    @JoinColumn(name = "memberNum")
    private Member memberNum;
}
