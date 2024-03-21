package com.postechlances.producao.domain.service.crud.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemPedidoCreateResponseDTO;
import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.messaging.TechFoodProducer;
import com.postechlances.producao.domain.model.ItemListaPedido;
import com.postechlances.producao.domain.repository.ItemListaPedidoRepository;
import com.postechlances.producao.infra.mapper.impl.GenericMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ItemListaPedidoCrudServiceTest {

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
  }

  @AfterEach
  void tearDown() throws Exception {
    mock.close();
  }

  @Test
  void devePermitirRegistrarItem() {

    //ARRANJE
    var item = criarItemPedido();
    when(repository.save(any(ItemListaPedido.class))).thenAnswer(i -> i.getArgument(0));

    //ACT
    ItemPedidoCreateRequestDTO itemSaveDTO = new ItemPedidoCreateRequestDTO();
    itemSaveDTO.setId(item.getId());

    var savedItem = service.create(itemSaveDTO);

    //ASSERT
    assertThat(savedItem).isInstanceOf(ItemPedidoCreateResponseDTO.class).isNotNull();
    assertThat(savedItem.getId()).isEqualTo(item.getId());
    assertThat(savedItem.getStatus()).isEqualTo(item.getStatus());

  }

  @Test
  void devePermitirListarItens() {
    // Arrange
    var item1 = criarItemPedido();
    item1.setId("ab" + new Random().nextInt() + "cdf");
    var item2 = criarItemPedido();
    item2.setId("ab" + new Random().nextInt() + "cdf");

    var itens = Arrays.asList(
      item1,
      item2
    );

    when(repository.findAll()).thenReturn(itens);

    // Act
    var resultado = service.list();

    // Assert
    verify(repository, times(1)).findAll();
    assertThat(resultado)
      .hasSize(2);
      //.containsExactlyInAnyOrder(item1, item2);
    assertThat(resultado.get(0).getId()).isEqualTo(item1.getId());
    assertThat(resultado.get(0).getStatus()).isEqualTo(item1.getStatus().toString());

    assertThat(resultado.get(1).getId()).isEqualTo(item2.getId());
    assertThat(resultado.get(1).getStatus()).isEqualTo(item2.getStatus().toString());
  }

  @Test
  void devePermitirAvancarStatusDeUmItem() throws JsonProcessingException {

    //ARRANJE
    String id = "ab" + new Random().nextInt() + "cdf";
    ItemListaPedido item = criarItemPedido();
    item.setId(id);

    when(repository.findById(any(String.class))).thenReturn(Optional.of(item));
    when(repository.save(any(ItemListaPedido.class))).thenReturn(item);

    //ACT
    ItemPedidoCreateResponseDTO advancedItem = service.advanceStatus(id);

    if(advancedItem != null) {
      assertThat(advancedItem.getId()).isEqualTo(item.getId());
      assertThat(advancedItem.getStatus()).isEqualTo(StatusPedido.EM_PRODUCAO.toString());
    }

  }

  private ItemListaPedido criarItemPedido() {
    var itemLista = new ItemListaPedido();
    itemLista.setStatus(StatusPedido.SOLICITADO);
    itemLista.setCreated_at(new Date());
    return itemLista;
  }

}
