package com.assessment.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class Payment {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "status")
  @NotBlank
  private String status;

  @Column(
      name = "created",
      insertable = false,
      updatable = false,
      columnDefinition = "timestamp default current_timestamp")
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date created;

  @Column(name = "amount")
  @DecimalMin(value = "0.0", inclusive = false, message = "amount should be greater than 0.0")
  private Double amount;

  @Column(name = "currency", length = 3)
  @NotBlank(message = "currency code cannot be null")
  private String currency;

  @Column(name = "charge_id")
  @NotBlank(message = "charge_id cannot be null")
  @JsonProperty("charge_id")
  private String chargeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "fee_id")
  private Fee fee;

  @PrePersist
  protected void onCreate() {
    created = new Date();
  }
}
