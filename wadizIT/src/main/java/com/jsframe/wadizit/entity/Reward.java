package com.jsframe.wadizit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reward")
@Data
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rewardNum;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String item;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean delivery;
}
