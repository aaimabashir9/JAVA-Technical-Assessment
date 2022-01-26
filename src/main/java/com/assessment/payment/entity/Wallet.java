package com.assessment.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Aaima Bashir on 1/24/2022
 */

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
  @Id
  @Column(name = "id", columnDefinition = "serial")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull @OneToOne private Balance balance;

  @Column(name = "currency")
  @NotNull(message = "currency cannot be null")
  private String currency;
}
