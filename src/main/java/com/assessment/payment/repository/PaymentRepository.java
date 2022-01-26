package com.assessment.payment.repository;

import com.assessment.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Aaima Bashir on 1/25/2022
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {}
