package com.postechlances.producao.domain.service.crud.impl;

import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoDeleteRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoListRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoUpdateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoCreateResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoDeleteResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoListResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoUpdateResponseDTO;
import com.postechlances.producao.domain.model.ItemListaPedido;
import com.postechlances.producao.domain.repository.ItemListaPedidoRepository;
import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import com.postechlances.producao.infra.mapper.IGenericMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemListaPedidoCrudService implements IItemListaPedidoCrudService {

  private Logger logger = LogManager.getLogger(getClass());

  @Autowired
  private IGenericMapper mapper;

  @Autowired
  private ItemListaPedidoRepository repository;

  @Override
  public ItemListaPedidoCreateResponseDTO create(ItemListaPedidoCreateRequestDTO request) {
    ItemListaPedido itemToCreate = mapper.toObject(request, ItemListaPedido.class);
    itemToCreate.setRecebimento(new Date());
    ItemListaPedido createdItem = repository.save(itemToCreate);
    return mapper.toObject(createdItem, ItemListaPedidoCreateResponseDTO.class);
  }

  @Override
  public List<ItemListaPedidoListResponseDTO> list(ItemListaPedidoListRequestDTO request) {
    List<ItemListaPedido> itemsList = repository.findAll();
    return mapper.toList(itemsList, ItemListaPedidoListResponseDTO.class);
  }

  @Override
  public ItemListaPedidoListResponseDTO detail(Long id) {
    Optional<ItemListaPedido> item = repository.findById(id);
    if(item.isPresent())
      return mapper.toObject(item, ItemListaPedidoListResponseDTO.class);
    else
      return null;
  }

  @Override
  public ItemListaPedidoUpdateResponseDTO update(ItemListaPedidoUpdateRequestDTO request) {
    return null;
  }

  @Override
  public ItemListaPedidoDeleteResponseDTO delete(Long id) {
    Optional<ItemListaPedido> item = repository.findById(id);
    if(item.isPresent()) {
      repository.delete(item.get());
      return mapper.toObject(item, ItemListaPedidoDeleteResponseDTO.class);
    } else
      return null;
  }
}
