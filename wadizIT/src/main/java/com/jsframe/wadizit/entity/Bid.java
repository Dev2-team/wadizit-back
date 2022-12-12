package com.jsframe.wadizit.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bidNum;

    // 경매 번호
    @ManyToOne
    @JoinColumn(name = "auctionNum")
    private Auction auctionNum;

    // 회원 번호
    @ManyToOne
    @JoinColumn(name = "memberNum")
    private Member memberNum;

    // 상태
    @Column(nullable = false)
    @ColumnDefault("-1")
    private long status;

    // 입찰가
    @Column(nullable = false)
    @ColumnDefault("0")
    private long bidPrice;
}
