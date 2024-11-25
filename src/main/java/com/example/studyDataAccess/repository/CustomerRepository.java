package com.example.studyDataAccess.repository;

import com.example.studyDataAccess.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByUsernameAndPassword(String username, String password);

    //사용자가 입력한 나이보다 더 큰 나이 회원을 출력
    public List<Customer> findByAgeGreaterThanEqual(int age);
 }
