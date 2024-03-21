package com.postechlances.producao.data.dto.crud.request;

import com.postechlances.producao.domain.enums.StatusPedido;
import lombok.Data;
import lombok.Generated;

@Generated
@Data
public class ItemPedidoCreateRequestDTO {
  private String id;
  private StatusPedido status;
}
