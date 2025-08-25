package com.hansung.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems=new ArrayList<>();

    private String orderNumber;

    private int amount;

    // 주문 시간
    private LocalDateTime orderDate;

    @PrePersist
    public void prePersist(){
        this.orderDate = LocalDateTime.now();
    }

}
