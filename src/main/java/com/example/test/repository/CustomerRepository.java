package com.example.test.repository;

import com.example.test.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByEmail(String email);

}
