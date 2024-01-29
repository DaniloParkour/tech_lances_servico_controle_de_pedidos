package com.postechlances.producao.domain.service.crud.impl;

import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoCreateResponseDTO;
import com.postechlances.producao.domain.enums.StatusPedido;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

class ItemListaPedidoCrudServiceTest {

  private ItemListaPedidoCrudService service;

  @Mock
  private ItemListaPedidoRepository repository;

  AutoCloseable mock;

  @BeforeEach
  void setup() {
    mock = MockitoAnnotations.openMocks(this);
    service = new ItemListaPedidoCrudService(new GenericMapper(new ModelMapper()), repository);
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
    ItemListaPedidoCreateRequestDTO itemSaveDTO = new ItemListaPedidoCreateRequestDTO();
    itemSaveDTO.setIdentifier_pedido(item.getIdentifier_pedido());
    itemSaveDTO.setIdentifier_cliente(item.getIdentifier_cliente());
    itemSaveDTO.setItens(item.getItens());
    itemSaveDTO.setPreparo(item.getPreparo());

    var savedItem = service.create(itemSaveDTO);

    //ASSERT
    assertThat(savedItem).isInstanceOf(ItemListaPedidoCreateResponseDTO.class).isNotNull();
    assertThat(savedItem.getIdentifier_cliente()).isEqualTo(item.getIdentifier_cliente());
    assertThat(savedItem.getIdentifier_pedido()).isEqualTo(item.getIdentifier_pedido());
    assertThat(savedItem.getItens()).isEqualTo(item.getItens());
    assertThat(savedItem.getStatus()).isEqualTo(item.getStatus());

  }

  @Test
  void devePermitirListarItens() {
    fail("Teste ainda não implementado!");
  }

  @Test
  void devePermitirAlterarItem() {
    fail("Teste ainda não implementado!");
  }

  @Test
  void devePermitirDetalhar() {
    fail("Teste ainda não implementado!");
  }

  @Test
  void devePermitirDeletarItem() {
    fail("Teste ainda não implementado!");
  }

  @Test
  void devePermitirAvancarStatusDeUmItem() {
    fail("Teste ainda não implementado!");
  }

  private ItemListaPedido criarItemPedido() {
    var itemLista = new ItemListaPedido();
    itemLista.setIdentifier_pedido("1");
    itemLista.setIdentifier_cliente("10");
    itemLista.setRecebimento(new Timestamp(new Date().getTime()));
    itemLista.setStatus(StatusPedido.SOLICITADO);
    List<String> items = new ArrayList<String>();
    items.add("Cheese Burguer");
    items.add("Batata G c/ Bacon");
    items.add("Sorvete de manga com cobertura de menta");
    items.add("Coca Zero 1,5L");
    items.add("Eno Guaraná");
    itemLista.setItens(items);
    return itemLista;
  }

}
