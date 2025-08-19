package com.hansung.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=true)
    private String name;

    @Column(nullable = false)
    private int price;

    private String thumbnailImage;

    private String detailImage;

    private String description;
}