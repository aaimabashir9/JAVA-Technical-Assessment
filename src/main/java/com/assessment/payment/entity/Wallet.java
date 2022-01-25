package com.assessment.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Data
@NoArgsConstructor
public class Wallet {
  @Id
  @Column(name = "id", columnDefinition = "serial")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @OneToOne
  private Balance balance;

  @Column(name = "currency")
  @NotNull(message = "currency cannot be null")
  private String currency;
}
