package com.postechlances.producao.domain.model;

import com.postechlances.producao.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemListaPedido {
  @Id
  private String id;
  @Enumerated(EnumType.STRING)
  private StatusPedido status;
  private Date created_at;
}
