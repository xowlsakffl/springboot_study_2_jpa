package com.example.studyDataAccess.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String customerName;
    private int age;
    private String rating;
    private String occupation;

    @Column(columnDefinition = "int default 0")
    private int reserves; // 적립금

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//fetch = FetchType.LAZY 는 회원정보가져올때 리뷰정보를 가져오지않음
    private List<Review> reviews;// List<Review> reviews = cus.getReviews()

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;
}
