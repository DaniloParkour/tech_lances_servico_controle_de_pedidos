package com.postechlances.producao.data.dto.crud.response;

import com.postechlances.producao.domain.enums.StatusPedido;
import lombok.Data;
import lombok.Generated;

import java.util.Date;

@Generated
@Data
public class ItemPedidoCreateResponseDTO {
  private String id;
  private StatusPedido status;
  private Date created_at;
}
