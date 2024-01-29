package com.postechlances.producao.domain.service.crud.impl;

import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoListRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoUpdateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoCreateResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoUpdateResponseDTO;
import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.model.ItemListaPedido;
import com.postechlances.producao.domain.repository.ItemListaPedidoRepository;
import com.postechlances.producao.infra.mapper.IGenericMapper;
import com.postechlances.producao.infra.mapper.impl.GenericMapper;
import org.apache.logging.log4j.LogManager;
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
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class ItemListaPedidoCrudServiceTest {

  private ItemListaPedidoCrudService service;

  @Mock
  private ItemListaPedidoRepository repository;

  AutoCloseable mock;

  @BeforeEach
  void setup() {
    mock = MockitoAnnotations.openMocks(this);
    service = new ItemListaPedidoCrudService(
      new GenericMapper(new ModelMapper()),
      repository
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
    assertThat(savedItem.getStatus()).isEqualTo(item.getStatus().toString());

  }

  @Test
  void devePermitirListarItens() {
    // Arrange
    var item1 = criarItemPedido();
    item1.setId(new Random().nextLong());
    var item2 = criarItemPedido();
    item2.setId(new Random().nextLong());

    var itens = Arrays.asList(
      item1,
      item2
    );

    when(repository.findAll()).thenReturn(itens);

    // Act
    var resultado = service.list(new ItemListaPedidoListRequestDTO());

    // Assert
    verify(repository, times(1)).findAll();
    assertThat(resultado)
      .hasSize(2);
      //.containsExactlyInAnyOrder(item1, item2);
    assertThat(resultado.get(0).getId()).isEqualTo(item1.getId());
    assertThat(resultado.get(0).getItens()).isEqualTo(item1.getItens());
    assertThat(resultado.get(0).getStatus()).isEqualTo(item1.getStatus().toString());
    assertThat(resultado.get(0).getIdentifier_pedido()).isEqualTo(item1.getIdentifier_pedido());
    assertThat(resultado.get(0).getIdentifier_cliente()).isEqualTo(item1.getIdentifier_cliente());

    assertThat(resultado.get(1).getId()).isEqualTo(item2.getId());
    assertThat(resultado.get(1).getItens()).isEqualTo(item2.getItens());
    assertThat(resultado.get(1).getStatus()).isEqualTo(item2.getStatus().toString());
    assertThat(resultado.get(1).getIdentifier_pedido()).isEqualTo(item2.getIdentifier_pedido());
    assertThat(resultado.get(1).getIdentifier_cliente()).isEqualTo(item2.getIdentifier_cliente());
  }

  @Test
  void devePermitirAlterarItem() {
    //ARRANJE
    Long id = new Random().nextLong();
    ItemListaPedido item = criarItemPedido();
    item.setId(id);

    // Mocka para quando pesquisar pelo ID retornar o item definido acima
    when(repository.findById(any(Long.class))).thenReturn(Optional.of(item));
    when(repository.save(any(ItemListaPedido.class))).thenReturn(item);

    //ACT
    var findedItem = service.detail(id);
    ItemListaPedidoUpdateResponseDTO savedItem = null;
    if(findedItem != null) {
      findedItem.setStatus(StatusPedido.FINALIZADO.toString());
      ItemListaPedidoUpdateRequestDTO itemToUpdate = new ItemListaPedidoUpdateRequestDTO();
      itemToUpdate.setId(findedItem.getId());

      itemToUpdate.setIdentifier_pedido(findedItem.getIdentifier_pedido());
      itemToUpdate.setIdentifier_cliente(findedItem.getIdentifier_cliente());
      itemToUpdate.setRecebimento(findedItem.getRecebimento());
      itemToUpdate.setFechamento(findedItem.getFechamento());
      itemToUpdate.setPagamento(findedItem.getPagamento());
      itemToUpdate.setStatus(findedItem.getStatus());
      itemToUpdate.setItens(findedItem.getItens());
      itemToUpdate.setPreparo(findedItem.getPreparo());
      savedItem = service.update(itemToUpdate);
    }

    if(savedItem != null) {
      //ASSET
      //verify(repository, times(1)).findById(id);
      //verify(repository, times(1)).save(findedItem);
      //assertThat(optionalItem).isPresent().containsSame(item);

      assertThat(savedItem.getId()).isEqualTo(item.getId());
      assertThat(savedItem.getIdentifier_pedido()).isEqualTo(item.getIdentifier_pedido());
      assertThat(savedItem.getIdentifier_cliente()).isEqualTo(item.getIdentifier_cliente());
      //assertThat(item.getStatus()).isEqualTo(StatusPedido.FINALIZADO);
      assertThat(savedItem.getRecebimento()).isEqualTo(item.getRecebimento());
      assertThat(savedItem.getPreparo()).isEqualTo(item.getPreparo());
      assertThat(savedItem.getFechamento()).isEqualTo(item.getFechamento());
      assertThat(savedItem.getItens()).isEqualTo(item.getItens());
    }

  }

  @Test
  void devePermitirDetalhar() {
    // Arrange
    Long id = new Random().nextLong();
    var item = criarItemPedido();
    item.setId(id);

    when(repository.findById(any(Long.class)))
      .thenReturn(Optional.of(item));

    // Act
    var optionalItem = repository.findById(id);

    // Assert
    verify(repository, times(1)).findById(id);
    assertThat(optionalItem)
      .isPresent()
      .containsSame(item);
    optionalItem.ifPresent(detailedItem -> {
      assertThat(detailedItem.getId()).isEqualTo(item.getId());
      assertThat(detailedItem.getItens()).isEqualTo(item.getItens());
      assertThat(detailedItem.getIdentifier_pedido()).isEqualTo(item.getIdentifier_pedido());
      assertThat(detailedItem.getIdentifier_cliente()).isEqualTo(item.getIdentifier_cliente());
    });
  }

  @Test
  void devePermitirDeletarItem() {
    // Arrange
    Long id = new Random().nextLong();
    doNothing().when(repository).deleteById(id);
    // Act
    repository.deleteById(id);
    // Assert
    verify(repository, times(1)).deleteById(id);
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
