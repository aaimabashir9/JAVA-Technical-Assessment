package com.assessment.payment.repository;

import com.assessment.payment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Aaima Bashir on 1/25/2022
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
