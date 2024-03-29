package com.postechlances.producao.domain.messaging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class TechFoodProducerTest {

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @InjectMocks
  private TechFoodProducer techFoodProducer;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(techFoodProducer).alwaysDo(print()).build();
  }

  @Test
  public void testSendMessage() {
    String message = "Test message";
    techFoodProducer.sendMessage(message);
    CompletableFuture<SendResult<String, String>> pedidoPronto = verify(kafkaTemplate).send("pedido_pronto", message);
  }

  @Test
  public void testAuxSendAdvanceStatus() {
    String message = "Test message";
    techFoodProducer.auxSendAdvanceStatus(message);
    verify(kafkaTemplate).send("pedido_para_producao", message);
  }

}