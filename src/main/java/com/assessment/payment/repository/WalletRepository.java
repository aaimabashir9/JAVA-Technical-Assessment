package com.assessment.payment.repository;

import java.util.Optional;

import com.assessment.payment.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Aaima Bashir on 1/25/2022
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Optional<Wallet> findFirstByOrderByIdDesc();
}
