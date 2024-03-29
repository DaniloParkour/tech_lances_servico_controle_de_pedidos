package com.postechlances.producao.data.controller.crud.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemPedidoCreateResponseDTO;
import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.messaging.TechFoodProducer;
import com.postechlances.producao.domain.model.ItemListaPedido;
import com.postechlances.producao.domain.repository.ItemListaPedidoRepository;
import com.postechlances.producao.domain.service.crud.impl.ItemListaPedidoCrudService;
import com.postechlances.producao.infra.mapper.IGenericMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemListaPedidoCrudControllerTest_BKP {

  private MockMvc mockMvc;

  @InjectMocks
  private ItemListaPedidoCrudService service;

  @Mock
  private IGenericMapper mapper;

  @Mock
  private ItemListaPedidoRepository repository;

  @Mock
  private TechFoodProducer techFoodProducer;

  @Autowired
  private ObjectMapper objectMapper;

  AutoCloseable mock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    mock = MockitoAnnotations.openMocks(this);

    //service = new ItemListaPedidoCrudService(
    //  new GenericMapper(new ModelMapper()),
    //  repository,
    //  techFoodProducer
    //);

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
  void deveCriarNovoPedido() throws Exception {
    ItemPedidoCreateRequestDTO requestDTO = new ItemPedidoCreateRequestDTO();
    requestDTO.setId("123asd456qwe");
    requestDTO.setStatus(StatusPedido.SOLICITADO);

    when(service.create(requestDTO))
      .thenReturn(new ItemPedidoCreateResponseDTO("123asd456qwe", StatusPedido.SOLICITADO, new Date()));

    String requestJson = objectMapper.writeValueAsString(requestDTO);

    mockMvc.perform(post("/itemlistapedido")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
      .andExpect(status().isOk());

  }





  //@Test
  void deveCriarPedido() throws Exception {

    var pedido = criarItemPedido();

    ItemPedidoCreateResponseDTO retorno = new ItemPedidoCreateResponseDTO();
    retorno.setId("1122wwssaa");
    retorno.setStatus(StatusPedido.SOLICITADO);
    retorno.setCreated_at(new Date());

    ItemPedidoCreateRequestDTO request = new ItemPedidoCreateRequestDTO();
    request.setId("1122wwssaa");
    request.setStatus(StatusPedido.SOLICITADO);

    when(service.create(request))
      .thenAnswer(i -> {
        ItemPedidoCreateRequestDTO requestDto = i.getArgument(0);
        ItemPedidoCreateResponseDTO responseDto = new ItemPedidoCreateResponseDTO();
        responseDto.setId(requestDto.getId());
        responseDto.setStatus(requestDto.getStatus());
        responseDto.setCreated_at(new Date());
        return responseDto;
      });

    // save
    ItemListaPedido novoItem = new ItemListaPedido();
    novoItem.setId("1122wwssaa");
    novoItem.setStatus(StatusPedido.SOLICITADO);
    novoItem.setCreated_at(new Date());

    when(repository.save(novoItem)).thenReturn(novoItem);

    String jsonPedido = asJsonString(pedido);

    System.out.println(".......................................................................................");
    System.out.println(jsonPedido);
    jsonPedido = "{\"id\":\"ac12poc203pd\",\"status\":\"SOLICITADO\"}";
    System.out.println(".......................................................................................");

    //ACT
    mockMvc.perform(
      post("/itemlistapedido")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPedido)
    ).andExpect(status().isCreated());

    //verify(service, times(1)).create(any(ItemPedidoCreateRequestDTO.class));
  }

  //@Test
  void deveListarPedidos() {

    List<ItemPedidoCreateResponseDTO> listaRetorno = new ArrayList<ItemPedidoCreateResponseDTO>();

    ItemPedidoCreateResponseDTO item = new ItemPedidoCreateResponseDTO();
    item.setId("12as335q3d");
    item.setStatus(StatusPedido.SOLICITADO);
    item.setCreated_at(new Date());
    listaRetorno.add(item);

    ItemPedidoCreateResponseDTO item2 = new ItemPedidoCreateResponseDTO();
    item2.setId("12qq23ert");
    item2.setStatus(StatusPedido.EM_PRODUCAO);
    item2.setCreated_at(new Date());
    listaRetorno.add(item2);

    when(service.list()).thenReturn(listaRetorno);

    //verify(service, times(1)).create(any(ItemPedidoCreateRequestDTO.class));

    verify(service, times(1)).list();

    //for (ItemPedidoCreateResponseDTO itemPedidoCreateResponseDTO : verify(service, times(1)).list()) {

    //}

    //verify(service, times(1));
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
