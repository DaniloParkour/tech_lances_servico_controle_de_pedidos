package com.postechlances.producao.domain.service.crud;

import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoDeleteRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoListRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoUpdateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoCreateResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoDeleteResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoListResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoUpdateResponseDTO;
import com.postechlances.producao.infra.mapper.response.ResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IItemListaPedidoCrudService {
  public ItemListaPedidoCreateResponseDTO create(ItemListaPedidoCreateRequestDTO request);

  public List<ItemListaPedidoListResponseDTO> list(ItemListaPedidoListRequestDTO request);

  public ItemListaPedidoListResponseDTO detail(Long id);

  public ItemListaPedidoUpdateResponseDTO update(ItemListaPedidoUpdateRequestDTO request);

  public ItemListaPedidoDeleteResponseDTO delete(Long id);

  public ItemListaPedidoUpdateResponseDTO advanceStatus(Long idPedido);
}
