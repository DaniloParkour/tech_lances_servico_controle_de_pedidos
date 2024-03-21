package com.postechlances.producao.data.dto.crud.response;

import com.postechlances.producao.domain.enums.StatusPedido;
import lombok.Data;
import java.util.Date;

@Data
public class ItemPedidoCreateResponseDTO {
  private String id;
  private StatusPedido status;
  private Date created_at;
}
