    package com.hansung.shoppingmall.entity;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Entity
    @Getter
    @Setter
    public class OrderItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "order_id",nullable = false)
        private Order order;

        @ManyToOne
        @JoinColumn(name = "item_id",nullable = false)
        private Item item;

        @Column(nullable = false)
        private int quantity;

        @Column(nullable = false)
        private int lineTotal;
    }
