package com.hansung.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name="cartitem",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "item_id"})
)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="item_id",nullable = false)
    private Item item;

    @Column(nullable = false)
    private int quantity;
}
