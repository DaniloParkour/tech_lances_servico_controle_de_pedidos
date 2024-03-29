package com.postechlances.producao.data.controller.crud.impl;

import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemPedidoCreateResponseDTO;
import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.service.crud.impl.ItemListaPedidoCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemListaPedidoCrudControllerTest {

  @InjectMocks
  ItemListaPedidoCrudController controller;

  @Mock
  ItemListaPedidoCrudService service;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
  }

  @Test
  public void deveCriarPedido() throws Exception {

    ItemPedidoCreateRequestDTO deveCriarPedidoRequest = new ItemPedidoCreateRequestDTO();
    deveCriarPedidoRequest.setId("teste");
    deveCriarPedidoRequest.setStatus(StatusPedido.SOLICITADO);

    ItemPedidoCreateResponseDTO deveCriarPedidoResponse = new ItemPedidoCreateResponseDTO();
    deveCriarPedidoResponse.setId("teste");
    deveCriarPedidoResponse.setStatus(StatusPedido.SOLICITADO);
    deveCriarPedidoResponse.setCreated_at(new Date());

    when(service.create(any(ItemPedidoCreateRequestDTO.class))).thenReturn(deveCriarPedidoResponse);

    mockMvc.perform(
      post("/itemlistapedido")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\":\"teste\",\"status\":\"SOLICITADO\"}")
    ).andExpect(status().isOk());

    //verify(service, times(1)).create(any(ItemPedidoCreateRequestDTO.class));
    verify(service).create(deveCriarPedidoRequest);
    verifyNoMoreInteractions(service);

  }

  @Test
  void deveListarPedidos() throws Exception {

    List<ItemPedidoCreateResponseDTO> listaRetorno = new ArrayList<ItemPedidoCreateResponseDTO>();

    ItemPedidoCreateResponseDTO item = new ItemPedidoCreateResponseDTO();
    item.setId("teste1");
    item.setStatus(StatusPedido.SOLICITADO);
    item.setCreated_at(new Date());
    listaRetorno.add(item);

    ItemPedidoCreateResponseDTO item2 = new ItemPedidoCreateResponseDTO();
    item2.setId("teste2");
    item2.setStatus(StatusPedido.EM_PRODUCAO);
    item2.setCreated_at(new Date());
    listaRetorno.add(item2);

    when(service.list()).thenReturn(listaRetorno);

    mockMvc.perform(get("/itemlistapedido")).andExpect(status().isOk());

    verify(service, times(1)).list();

  }

  @Test
  public void deveAcavarOStatusDoPedido() throws Exception {
    ItemPedidoCreateResponseDTO respostaAvanvarPedido = new ItemPedidoCreateResponseDTO();
    respostaAvanvarPedido.setId("teste");
    respostaAvanvarPedido.setStatus(StatusPedido.EM_PRODUCAO);
    respostaAvanvarPedido.setCreated_at(new Date());

    when(service.advanceStatus(any(String.class))).thenReturn(respostaAvanvarPedido);

    mockMvc.perform(get("/itemlistapedido/advancestatus/{idPedido}", "teste")).andExpect(status().isOk());

  }

}
