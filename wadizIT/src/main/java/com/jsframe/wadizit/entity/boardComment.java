package com.jsframe.wadizit.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "boardcomment")
@Data
public class boardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bComNum;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp date;

}
