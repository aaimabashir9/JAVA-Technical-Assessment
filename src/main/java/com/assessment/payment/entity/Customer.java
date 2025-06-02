package com.assessment.payment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Created by Aaima Bashir on 1/24/2022
 */

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "wallet_id")
  @JsonProperty("wallet_id")
  @NotNull
  private Integer walletId;

  @Column(name = "name")
  @JsonIgnore
  private String name;
}
