package com.postechlances.producao.domain.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.domain.messaging.dtos.PedidoParaProducaoConsumerDTO;
import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TechFoodConsumer {

  @Autowired
  IItemListaPedidoCrudService itemListaPedidoCrudService;

  @KafkaListener(topics = "pedido_para_producao", groupId = "tech_group_1")
  public void receiveMessage(String message) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    PedidoParaProducaoConsumerDTO consumedData = objectMapper.readValue(message, PedidoParaProducaoConsumerDTO.class);
    ItemPedidoCreateRequestDTO newItemPedidoCreate = new ItemPedidoCreateRequestDTO();
    newItemPedidoCreate.setId(consumedData.pedidoId());
    itemListaPedidoCrudService.create(newItemPedidoCreate);
  }

}
