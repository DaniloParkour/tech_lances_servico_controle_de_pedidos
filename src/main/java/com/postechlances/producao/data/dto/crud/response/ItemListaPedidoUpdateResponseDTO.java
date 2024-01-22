package com.postechlances.producao.data.dto.crud.response;

import java.util.Date;
import java.util.List;

public class ItemListaPedidoUpdateResponseDTO {
  private Long id;
  private String id_pedido;
  private String id_cliente;
  private Date recebimento;
  private Date fechamento;
  private Date pagamento;
  private String status;
  private List<String> itens;
  private List<String> preparo;
}
