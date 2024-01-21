package com.postechlances.producao.domain.service.crud.impl;

import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoDeleteRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoListRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoUpdateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoCreateResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoDeleteResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoListResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoUpdateResponseDTO;
import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import com.postechlances.producao.infra.mapper.IGenericMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemListaPedidoCrudService implements IItemListaPedidoCrudService {

  private Logger logger = LogManager.getLogger(getClass());

  @Autowired
  private IGenericMapper mapper;

  @Override
  public ItemListaPedidoCreateResponseDTO create(ItemListaPedidoCreateRequestDTO request) {
    return null;
  }

  @Override
  public List<ItemListaPedidoListResponseDTO> list(ItemListaPedidoListRequestDTO request) {
    return null;
  }

  @Override
  public ItemListaPedidoUpdateResponseDTO update(ItemListaPedidoUpdateRequestDTO request) {
    return null;
  }

  @Override
  public ItemListaPedidoDeleteResponseDTO delete(ItemListaPedidoDeleteRequestDTO request) {
    return null;
  }
}
