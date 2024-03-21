package com.postechlances.producao.data.dto.crud.request;

import com.postechlances.producao.domain.enums.StatusPedido;
import lombok.Data;

@Data
public class ItemPedidoCreateRequestDTO {
  private String id;
  private StatusPedido status;
}
