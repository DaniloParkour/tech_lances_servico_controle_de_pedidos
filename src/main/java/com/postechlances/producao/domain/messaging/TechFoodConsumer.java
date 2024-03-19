package com.postechlances.producao.domain.messaging;

import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TechFoodConsumer {

  @Autowired
  IItemListaPedidoCrudService itemListaPedidoCrudService;

  @KafkaListener(topics = "pedido_para_producao", groupId = "tech_group_1")
  public void receiveMessage(String message) {
    System.out.println("CONSUMER: ID lanche para avançar status = " + message);
  }

}
