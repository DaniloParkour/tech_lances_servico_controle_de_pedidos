package com.postechlances.producao.data.dto.crud.response;

import com.postechlances.producao.domain.enums.StatusPedido;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

public class ItemPedidoCreateResponseDTOTest {

  //@Test
  void deveObterOsDadosDoDTOItemPedidoCreateResponse() {
    ItemPedidoCreateResponseDTO item = createOneItemPedidoCreateResponseDTO();

    //assertThat(item).isNotNull();
    //verify(repository, times(1)).save(any(ItemListaPedido.class));
    //assertThat(saved.getId()).isEqualTo(item.getId());
    //assertThat(item.getStatus()).isEqualTo(StatusPedido.FINALIZADO);
  }

  private ItemPedidoCreateResponseDTO createOneItemPedidoCreateResponseDTO() {
    ItemPedidoCreateResponseDTO retorno = new ItemPedidoCreateResponseDTO();
    retorno.setId("123456");
    retorno.setCreated_at(new Date());
    retorno.setStatus(StatusPedido.SOLICITADO);
    return retorno;
  }

}
