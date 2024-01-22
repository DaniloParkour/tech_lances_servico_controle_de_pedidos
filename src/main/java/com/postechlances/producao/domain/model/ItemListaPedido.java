package com.postechlances.producao.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class ItemListaPedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String identifier_pedido;
  private String identifier_cliente;
  private Date recebimento;
  private Date fechamento;
  private Date pagamento;
  private String status;
  private List<String> itens;
  private List<String> preparo;
  //private Cliente cliente;
}
