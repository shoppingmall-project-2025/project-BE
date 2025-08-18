package com.hansung.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

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
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Min(0)
    @Max(5)
    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @PrePersist
    protected void onCreate(){
        this.createdAt= LocalDate.now();
    }
}
