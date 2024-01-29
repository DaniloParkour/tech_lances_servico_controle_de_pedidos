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

  @Test
  void deveAtualizarUmPedido() {
    //ARRANJE
    Long id = new Random().nextLong();
    ItemListaPedido item = criarItemPedido();
    item.setId(id);

    // Mocka para quando pesquisar pelo ID retornar o item definido acima
    when(repository.findById(any(Long.class))).thenReturn(Optional.of(item));
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
      assertThat(saved.getIdentifier_pedido()).isEqualTo(item.getIdentifier_pedido());
      assertThat(saved.getIdentifier_cliente()).isEqualTo(item.getIdentifier_cliente());
      assertThat(item.getStatus()).isEqualTo(StatusPedido.FINALIZADO);
      assertThat(saved.getRecebimento()).isEqualTo(item.getRecebimento());
      assertThat(saved.getPreparo()).isEqualTo(item.getPreparo());
      assertThat(saved.getFechamento()).isEqualTo(item.getFechamento());
      assertThat(saved.getItens()).isEqualTo(item.getItens());
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
    Long id = new Random().nextLong();
    ItemListaPedido item = criarItemPedido();
    item.setId(id);

    // Mocka para quando pesquisar pelo ID retornar o item definido acima
    when(repository.findById(any(Long.class))).thenReturn(Optional.of(item));

    //ACT
    var optionalItem = repository.findById(id);

    //ASSET
    verify(repository, times(1)).findById(id);
    assertThat(optionalItem).isPresent().containsSame(item);

    optionalItem.ifPresent(savedItem -> {
      assertThat(savedItem.getId()).isEqualTo(item.getId());
      assertThat(savedItem.getIdentifier_pedido()).isEqualTo(item.getIdentifier_pedido());
      assertThat(savedItem.getIdentifier_cliente()).isEqualTo(item.getIdentifier_cliente());
      assertThat(savedItem.getStatus()).isEqualTo(item.getStatus());
      assertThat(savedItem.getRecebimento()).isEqualTo(item.getRecebimento());
      assertThat(savedItem.getPreparo()).isEqualTo(item.getPreparo());
      assertThat(savedItem.getFechamento()).isEqualTo(item.getFechamento());
      assertThat(savedItem.getItens()).isEqualTo(item.getItens());
    });
  }

  @Test
  void deveDeletarUmPedido() {
    // Arrange
    Long id = new Random().nextLong();
    doNothing().when(repository).deleteById(id);
    // Act
    repository.deleteById(id);
    // Assert
    verify(repository, times(1)).deleteById(id);
  }

}
