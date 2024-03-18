package com.assessment.payment.service;

import com.assessment.payment.constant.PaymentStatus;
import com.assessment.payment.entity.Balance;
import com.assessment.payment.entity.Fee;
import com.assessment.payment.entity.Payment;
import com.assessment.payment.entity.Wallet;
import com.assessment.payment.exception.PaymentException;
import com.assessment.payment.repository.BalanceRepository;
import com.assessment.payment.repository.FeeRepository;
import com.assessment.payment.repository.PaymentRepository;
import com.assessment.payment.repository.WalletRepository;
import com.assessment.payment.request.PaymentRequest;
import com.assessment.payment.response.PaymentResponse;
import com.assessment.payment.validator.PaymentValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/** Created by Aaima Bashir on 1/26/2022 */
@Service
@Transactional
public class PaymentService {

  @Autowired private PaymentRepository paymentRepository;

  @Autowired private FeeRepository feeRepository;

  @Autowired private WalletRepository walletRepository;

  @Autowired private BalanceRepository balanceRepository;

  @Autowired private PaymentValidator paymentValidator;

  public ResponseEntity<Payment> topUpWallet(final PaymentRequest paymentRequest) {

    List<String> errors = paymentValidator.validatePaymentRequest(paymentRequest);
    if (!errors.isEmpty()) {
      throw new PaymentException(errors.get(0), HttpStatus.BAD_REQUEST);
    }

    Fee fee = fetchFee(paymentRequest);
    Optional<Wallet> wallet = walletRepository.findById(paymentRequest.getCustomer().getWalletId());
    Balance balance = updateBalance(fee, wallet.get());
    Payment successPayment = savePayment(paymentRequest, fee);

    PaymentResponse paymentResponse = new PaymentResponse();
    BeanUtils.copyProperties(successPayment, paymentResponse);
    paymentResponse.setBalance(balance);

    return new ResponseEntity(paymentResponse, HttpStatus.OK);
  }

  @Transactional
  public Balance updateBalance(Fee fee, Wallet wallet) {

    Balance balance = balanceRepository.findById(wallet.getId()).get();

    Double netWalletChange = balance.getTotalAmount() - fee.getAmount();
    balance.setTotalAmount(balance.getTotalAmount() + netWalletChange);
    return balanceRepository.save(balance);
  }

  private Payment savePayment(PaymentRequest paymentRequest, Fee fee) {
    Payment payment = new Payment();
    BeanUtils.copyProperties(paymentRequest, payment);
    payment.setFee(fee);
    payment.setStatus(PaymentStatus.SUCCESS.toString());
    return paymentRepository.save(payment);
  }

  private Fee fetchFee(PaymentRequest paymentRequest) {
    Fee fee;
    Optional<Fee> feeRecord =
        feeRepository.findFirstByAmountAndCurrency(
            paymentRequest.getFee().getAmount(), paymentRequest.getCurrency());
    if (feeRecord.isPresent()) {
      fee = feeRecord.get();
    } else {
      fee =
          Fee.builder()
              .amount(paymentRequest.getFee().getAmount())
              .currency(paymentRequest.getCurrency())
              .build();
      fee = feeRepository.save(fee);
    }
    return fee;
  }
}
