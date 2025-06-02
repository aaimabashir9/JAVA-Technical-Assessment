package com.assessment.payment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * Created by Aaima Bashir on 1/24/2022
 */

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fee {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;

  @Column(name = "amount")
  @DecimalMin(value = "0.0", inclusive = false, message = "Amount should be greater than 0.0")
  @NotNull(message = "amount cannot be null")
  private Double amount;

  @Column(name = "currency")
  @NotNull(message = "currency cannot be null")
  private String currency;

  @Column(name = "service")
  @JsonIgnore
  private String service;
}
