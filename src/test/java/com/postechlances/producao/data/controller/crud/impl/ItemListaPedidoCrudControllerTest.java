package com.postechlances.producao.data.controller.crud.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.messaging.TechFoodProducer;
import com.postechlances.producao.domain.model.ItemListaPedido;
import com.postechlances.producao.domain.repository.ItemListaPedidoRepository;
import com.postechlances.producao.domain.service.crud.impl.ItemListaPedidoCrudService;
import com.postechlances.producao.infra.mapper.impl.GenericMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemListaPedidoCrudControllerTest {

  private MockMvc mockMvc;

  @Mock
  private ItemListaPedidoCrudService service;

  @Mock
  private ItemListaPedidoRepository repository;

  @Mock
  private TechFoodProducer techFoodProducer;

  AutoCloseable mock;

  @BeforeEach
  void setup() {
    mock = MockitoAnnotations.openMocks(this);

    service = new ItemListaPedidoCrudService(
      new GenericMapper(new ModelMapper()),
      repository,
      techFoodProducer
    );

    ItemListaPedidoCrudController controller = new ItemListaPedidoCrudController(service);

    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

  }

  @AfterEach
  void tearDown() throws Exception {
    mock.close();
  }

  public static String asJsonString(final Object obj) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(obj);
  }

  //@Test
  void deveCriarPedido() throws Exception {

    var pedido = criarItemPedido();
    when(service.create(any(ItemPedidoCreateRequestDTO.class)))
      .thenAnswer(i -> i.getArgument(0));

    //ACT
    mockMvc.perform(
      post("/ola")
        .content(asJsonString(pedido))
    ).andExpect(status().isCreated());

    verify(service, times(1)).create(any(ItemPedidoCreateRequestDTO.class));
  }

  void deveListarPedidos() {
    fail("Teste não implementado!");
  }

  void deveAvancarStatusDoPedido() {
    fail("Teste não implementado!");
  }

  private ItemListaPedido criarItemPedido() {
    var itemLista = new ItemListaPedido();
    itemLista.setId("ac12poc203pd");
    itemLista.setStatus(StatusPedido.SOLICITADO);
    itemLista.setCreated_at(new Date());
    return itemLista;
  }

}
