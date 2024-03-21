package com.postechlances.producao.domain.service.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemPedidoCreateResponseDTO;

import java.util.List;

public interface IItemListaPedidoCrudService {
  public ItemPedidoCreateResponseDTO create(ItemPedidoCreateRequestDTO request);

  public List<ItemPedidoCreateResponseDTO> list();

  public ItemPedidoCreateResponseDTO advanceStatus(String idPedido) throws JsonProcessingException;
}
