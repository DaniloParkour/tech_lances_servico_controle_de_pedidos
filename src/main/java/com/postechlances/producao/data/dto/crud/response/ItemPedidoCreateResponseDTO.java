package com.postechlances.producao.data.dto.crud.response;

import com.postechlances.producao.domain.enums.StatusPedido;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class ItemListaPedidoCreateResponseDTO {
  private Long id;
  //private String id_pedido;
  //private String id_cliente;
  private String identifier_pedido;
  private String identifier_cliente;
  private Timestamp recebimento;
  private Timestamp fechamento;
  private Timestamp pagamento;
  private StatusPedido status;
  private List<String> itens;
  private List<String> preparo;
}
