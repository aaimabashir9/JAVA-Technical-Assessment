package com.assessment.payment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Balance {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;

  @Column(name = "total_amount")
  @NotNull(message = "totalAmount can not be null")
  private Double totalAmount;
}
