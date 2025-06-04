package com.assessment.payment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Aaima Bashir on 1/24/2022
 */

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RegisterReflectionForBinding({UUID[].class})
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "status")
  @NotBlank
  private String status;

  @Column(name = "created", insertable = false, updatable = false,
      columnDefinition = "timestamp default current_timestamp")
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
