package com.postechlances.producao.data.dto.crud.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ItemListaPedidoListResponseDTO {
  private Long id;
  private String identifier_pedido;
  private String identifier_cliente;
  private Date recebimento;
  private Date fechamento;
  private Date pagamento;
  private String status;
  private List<String> itens;
  private List<String> preparo;
}
