package com.postechlances.producao.domain.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TechFoodProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public TechFoodProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String message) {
    kafkaTemplate.send("pedido_pronto", message);
  }

  public void auxSendAdvanceStatus(String message) {
    kafkaTemplate.send("pedido_para_producao", message);
  }

}
