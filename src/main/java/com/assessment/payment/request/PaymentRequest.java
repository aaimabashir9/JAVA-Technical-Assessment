package com.assessment.payment.request;

import com.assessment.payment.entity.Customer;
import com.assessment.payment.entity.Fee;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * Created by Aaima Bashir on 1/24/2022
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

  @DecimalMin(value = "0.0", inclusive = false, message = "Amount should be greater than 0.0")
  private Double amount;

  @NotBlank(message = "currency cannot be null")
  @Length(min = 3, max = 3, message = "currency code length should be 3")
  private String currency;

  @JsonProperty("charge_id")
  @NotBlank(message = "charge_id cannot be null")
  private String chargeId;

  @NotNull(message = "customer cannot be null")
  private Customer customer;

  private Fee fee;
}
