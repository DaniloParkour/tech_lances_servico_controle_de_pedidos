package com.postechlances.producao.domain.service.crud.impl;

import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoDeleteRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoListRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoUpdateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoCreateResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoDeleteResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoListResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoUpdateResponseDTO;
import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.model.ItemListaPedido;
import com.postechlances.producao.domain.repository.ItemListaPedidoRepository;
import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import com.postechlances.producao.infra.mapper.IGenericMapper;
import com.postechlances.producao.infra.mapper.response.ResponseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
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
    itemToCreate.setRecebimento(new Timestamp(new Date().getTime()));
    itemToCreate.setStatus(StatusPedido.SOLICITADO);
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
    Optional<ItemListaPedido> item = repository.findById(request.getId());
    if(item.isPresent()) {
      ItemListaPedido updatedItem = repository.save(mapper.toObject(request, ItemListaPedido.class));
      return mapper.toObject(updatedItem, ItemListaPedidoUpdateResponseDTO.class);
    } else
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

  @Override
  public ItemListaPedidoUpdateResponseDTO advanceStatus(Long idPedido) {
    Optional<ItemListaPedido> item = repository.findById(idPedido);
    if(item.isPresent()) {
      if(item.get().getStatus().equals(StatusPedido.SOLICITADO)) {
        item.get().setStatus(StatusPedido.EM_PRODUCAO);
      } else if(item.get().getStatus().equals(StatusPedido.EM_PRODUCAO)) {
        item.get().setStatus(StatusPedido.PRONTO);
        item.get().setFechamento(new Timestamp(new Date().getTime()));
      } else if(item.get().getStatus().equals(StatusPedido.PRONTO)) {
        item.get().setStatus(StatusPedido.FINALIZADO);
        item.get().setPagamento(new Timestamp(new Date().getTime()));
      } else return  null;

      ItemListaPedido updatedItem = repository.save(mapper.toObject(item.get(), ItemListaPedido.class));
      return mapper.toObject(updatedItem, ItemListaPedidoUpdateResponseDTO.class);
    } else
      return null;
  }
}
