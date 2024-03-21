package com.postechlances.producao.data.dto.crud.response;

import com.postechlances.producao.domain.enums.StatusPedido;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class ItemPedidoCreateResponseDTO {
  private String id;
  private StatusPedido status;
  private Date created_at;
}
