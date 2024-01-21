package com.postechlances.producao.data.controller.crud;

import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoDeleteRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoListRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoUpdateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoCreateResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoDeleteResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoListResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoUpdateResponseDTO;
import com.postechlances.producao.infra.mapper.response.ResponseModel;

public interface IItemListaPedidoCrudController {
  public ResponseModel<ItemListaPedidoCreateResponseDTO> create(ItemListaPedidoCreateRequestDTO request);

  public ResponseModel<ItemListaPedidoListResponseDTO> list(ItemListaPedidoListRequestDTO request);

  public ResponseModel<ItemListaPedidoUpdateResponseDTO> update(ItemListaPedidoUpdateRequestDTO request);

  public ResponseModel<ItemListaPedidoDeleteResponseDTO> delete(ItemListaPedidoDeleteRequestDTO request);
}
