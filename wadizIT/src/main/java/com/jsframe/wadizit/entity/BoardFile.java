package com.jsframe.wadizit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardFileNum;

    @Column(nullable = false, length = 20)
    private String originName;

    @Column(nullable = false, length = 20)
    private String sysName;

    @ManyToOne
    @JoinColumn(name = "board_num")
    private Board boardNum;
}
