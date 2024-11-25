package com.example.studyDataAccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id",nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    private int cost;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt=new Date();

}
