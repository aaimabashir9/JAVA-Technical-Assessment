package com.assessment.payment.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaSeeder {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @EventListener
  @Async
  public void sendExampleMessage(ContextRefreshedEvent event) {
      kafkaTemplate.send("payment", "Example message from KafkaSeeder");
      log.info("Example message sent to Kafka topic 'payment'");
  }
}
