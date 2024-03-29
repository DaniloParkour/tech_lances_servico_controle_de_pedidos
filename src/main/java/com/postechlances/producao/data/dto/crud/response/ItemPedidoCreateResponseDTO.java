package com.postechlances.producao.data.dto.crud.response;

import com.postechlances.producao.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;

@Generated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoCreateResponseDTO {
  private String id;
  private StatusPedido status;
  private Date created_at;
}
