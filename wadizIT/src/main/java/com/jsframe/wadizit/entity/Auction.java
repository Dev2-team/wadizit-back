package com.jsframe.wadizit.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;

@Data
@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long auctionNum;

    // 경매 제목
    @Column(nullable = false, length = 100)
    private String title;

    // 경매 상태(0: 진행, 1: 종료)
    @Column(nullable = false)
    @ColumnDefault("-1")
    private long status;

    // 시작가
    @Column(nullable = false)
    private long startPrice;

    // 현재가
    @Column(nullable = false)
    private long currentPrice;
}
