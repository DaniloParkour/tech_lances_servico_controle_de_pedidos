package com.postechlances.producao.data.dto.crud.request;

import com.postechlances.producao.domain.enums.StatusPedido;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ItemListaPedidoCreateRequestDTO {
  private String id;
  private StatusPedido status;
}
