package com.postechlances.producao.data.dto.crud.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ItemListaPedidoUpdateRequestDTO {
  private String id_pedido;
  private String id_cliente;
  //private Date recebimento;
  //private Date fechamento;
  //private Date pagamento;
  //private String status;
  private List<String> itens;
  private List<String> preparo;
}
