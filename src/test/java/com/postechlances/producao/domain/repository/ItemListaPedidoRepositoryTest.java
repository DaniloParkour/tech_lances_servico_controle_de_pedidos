package com.postechlances.producao.domain.repository;

import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.model.ItemListaPedido;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class ItemListaPedidoRepositoryTest {

  @Mock
  private ItemListaPedidoRepository repository;

  AutoCloseable openMocks;

  @BeforeEach
  void setup() {
    //Iniciar o mock quando o teste for iniciar
    openMocks = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    //Limpar da memória quando cada teste terminar
    openMocks.close();
  }

  @Test
  void deveRegistrarUmNovoPedido() {

    //ARRANJE
    var itemLista = criarItemPedido();

    // Mockando o banco, quando enviado um save para o repository passando como parâmetro um objeto da classe
    // ItemListaPedido.class, retornar o objeto itemLista
    when(repository.save(any(ItemListaPedido.class))).thenReturn(itemLista);

    //ACT
    var savedItem = repository.save(itemLista);

    //ASSERT
    assertThat(savedItem).isNotNull().isEqualTo(itemLista);
    verify(repository, times(1)).save(any(ItemListaPedido.class));

  }

  private ItemListaPedido criarItemPedido() {
    var itemLista = new ItemListaPedido();
    itemLista.setId("av123po40dc");
    itemLista.setStatus(StatusPedido.SOLICITADO);
    itemLista.setCreated_at(new Date());
    return itemLista;
  }

  @Test
  void deveAtualizarUmPedido() {
    //ARRANJE
    String id = "ab" + new Random().nextInt() + "cdf" ;
    ItemListaPedido item = criarItemPedido();
    item.setId(id);

    // Mocka para quando pesquisar pelo ID retornar o item definido acima
    when(repository.findById(any(String.class))).thenReturn(Optional.of(item));
    when(repository.save(any(ItemListaPedido.class))).thenReturn(item);

    //ACT
    var optionalItem = repository.findById(id);
    optionalItem.ifPresent(itemListaPedido -> itemListaPedido.setStatus(StatusPedido.FINALIZADO));
    var savedItem = optionalItem.map(itemListaPedido -> repository.save(itemListaPedido)).orElse(null);

    //ASSET
    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).save(optionalItem.get());
    assertThat(optionalItem).isPresent().containsSame(item);

    optionalItem.ifPresent(saved -> {
      assertThat(saved.getId()).isEqualTo(item.getId());
      assertThat(item.getStatus()).isEqualTo(StatusPedido.FINALIZADO);
    });
  }

  @Test
  void deveListarOsPedidos() {
    // Arrange
    var item1 = criarItemPedido();
    var item2 = criarItemPedido();
    var itens = Arrays.asList(item1, item2);

    when(repository.findAll()).thenReturn(itens);

    // Act
    var resultado = repository.findAll();

    // Assert
    verify(repository, times(1)).findAll();
    assertThat(resultado)
      .hasSize(2)
      .containsExactlyInAnyOrder(item1, item2);
  }

  @Test
  void deveDetalharUmPedido() {
    //ARRANJE
    String id = "ab" + new Random().nextInt() + "cdf" ;
    ItemListaPedido item = criarItemPedido();
    item.setId(id);

    // Mocka para quando pesquisar pelo ID retornar o item definido acima
    when(repository.findById(any(String.class))).thenReturn(Optional.of(item));

    //ACT
    var optionalItem = repository.findById(id);

    //ASSET
    verify(repository, times(1)).findById(id);
    assertThat(optionalItem).isPresent().containsSame(item);

    optionalItem.ifPresent(savedItem -> {
      assertThat(savedItem.getId()).isEqualTo(item.getId());
      assertThat(savedItem.getStatus()).isEqualTo(item.getStatus());
    });
  }

  @Test
  void deveDeletarUmPedido() {
    // Arrange
    String id = "ab" + new Random().nextInt() + "cdf" ;
    doNothing().when(repository).deleteById(id);
    // Act
    repository.deleteById(id);
    // Assert
    verify(repository, times(1)).deleteById(id);
  }

}
