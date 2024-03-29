package com.postechlances.producao.domain.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.domain.messaging.dtos.PedidoParaProducaoConsumerDTO;
import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class TechFoodConsumerTest {

  @Mock
  private IItemListaPedidoCrudService itemListaPedidoCrudService;

  @InjectMocks
  private TechFoodConsumer techFoodConsumer;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(techFoodConsumer).alwaysDo(print()).build();
  }

  @Test
  void testReceiveMessage() throws JsonProcessingException {
    String message = "{\"pedidoId\": 123}";

    ObjectMapper objectMapper = new ObjectMapper();
    PedidoParaProducaoConsumerDTO pedidoParaProducaoConsumerDTO = new PedidoParaProducaoConsumerDTO(true, "123");

    techFoodConsumer.receiveMessage(message);

    verify(itemListaPedidoCrudService, times(1)).create(any(ItemPedidoCreateRequestDTO.class));
    verify(itemListaPedidoCrudService).create(argThat(argument -> argument.getId().equals(pedidoParaProducaoConsumerDTO.pedidoId())));
  }

}
