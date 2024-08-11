package com.assessment.payment.repository;

import com.assessment.payment.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.Optional;
/**
 * Created by Aaima Bashir on 1/25/2022
 */
@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @NonNull Optional<Balance> findById(@NonNull Integer balanceId);

  Optional<Balance> findFirstByOrderByIdDesc();
}
