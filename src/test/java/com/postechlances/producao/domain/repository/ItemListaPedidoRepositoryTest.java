package com.postechlances.producao.domain.repository;

import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.model.ItemListaPedido;
import com.postechlances.producao.domain.repository.ItemListaPedidoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    when(repository.save(any(ItemListaPedido.class))).thenReturn(itemLista);

    //ACT
    var savedItem = repository.save(itemLista);

    //ASSERT
    assertThat(savedItem).isNotNull().isEqualTo(itemLista);
    verify(repository, times(1)).save(any(ItemListaPedido.class));

  }

  @Test
  void deveAtualizarUmPedido() {
    fail("Teste não implementado!");
  }

  @Test
  void deveListarOsPedidos() {
    fail("Teste não implementado!");
  }

  @Test
  void deveDetalharUmPedido() {
    fail("Teste não implementado!");
  }

  @Test
  void deveDeletarUmPedido() {
    fail("Teste não implementado!");
  }

  @Test
  void deveAvancarStatusDoPedido  () {
    fail("Teste não implementado!");
  }

}
