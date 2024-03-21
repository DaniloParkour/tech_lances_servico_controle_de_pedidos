package com.postechlances.producao.data.controller.crud;

import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemPedidoCreateResponseDTO;
import com.postechlances.producao.infra.mapper.response.ResponseModel;
import org.springframework.web.bind.annotation.PathVariable;

public interface IItemListaPedidoCrudController {
  public ResponseModel<ItemPedidoCreateResponseDTO> create(ItemPedidoCreateRequestDTO request);

  public ResponseModel<ItemPedidoCreateResponseDTO> list();

  ResponseModel<ItemPedidoCreateResponseDTO> advanceStatus(@PathVariable String idPedido);
}
