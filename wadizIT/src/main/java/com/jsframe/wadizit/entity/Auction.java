package com.jsframe.wadizit.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;

@Data
@Entity
@Table(name = "auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long auctionNum;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    @ColumnDefault("-1")
    private long status;

    @Column(nullable = false)
    private long startPrice;

    @Column(nullable = false)
    private long currentPrice;
}
