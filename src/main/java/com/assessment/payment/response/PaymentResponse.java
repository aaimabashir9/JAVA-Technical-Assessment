package com.assessment.payment.response;

import com.assessment.payment.entity.Balance;
import com.assessment.payment.entity.Customer;
import com.assessment.payment.entity.Fee;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
/**
 * Created by Aaima Bashir on 1/24/2022
 */

@Data
public class PaymentResponse {

  private UUID id;
  private Date created;
  private String status;
  private Double amount;
  private String currency;
  private String chargeId;
  private Customer customer;
  private Fee fee;
  private Balance balance;
}
