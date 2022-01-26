package com.assessment.payment.repository;

import com.assessment.payment.entity.Balance;
import com.assessment.payment.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;
/**
 * Created by Aaima Bashir on 1/25/2022
 */
@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Balance> findById(Integer balanceId);

  Optional<Balance> findFirstByOrderByIdDesc();
}
