package com.hansung.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(
        name="review",
        uniqueConstraints = @UniqueConstraint(columnNames = {"item_id","user_id"})
)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate createdAt;

    private int rating;

    private String title;

    private String content;

    @PrePersist
    protected void onCreate(){
        this.createdAt= LocalDate.now();
    }
}
