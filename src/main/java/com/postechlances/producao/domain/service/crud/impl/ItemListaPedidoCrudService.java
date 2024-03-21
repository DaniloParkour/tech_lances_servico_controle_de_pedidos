package com.postechlances.producao.domain.service.crud.impl;

import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemPedidoCreateResponseDTO;
import com.postechlances.producao.domain.enums.StatusPedido;
import com.postechlances.producao.domain.messaging.TechFoodProducer;
import com.postechlances.producao.domain.model.ItemListaPedido;
import com.postechlances.producao.domain.repository.ItemListaPedidoRepository;
import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import com.postechlances.producao.infra.mapper.IGenericMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemListaPedidoCrudService implements IItemListaPedidoCrudService {

  private final IGenericMapper mapper;

  private final ItemListaPedidoRepository repository;

  private final TechFoodProducer techFoodProducer;

  @Override
  public ItemPedidoCreateResponseDTO create(ItemPedidoCreateRequestDTO request) {
    ItemListaPedido itemToCreate = mapper.toObject(request, ItemListaPedido.class);
    itemToCreate.setCreated_at(new Date());
    itemToCreate.setStatus(StatusPedido.SOLICITADO);
    ItemListaPedido createdItem = repository.save(itemToCreate);
    return mapper.toObject(createdItem, ItemPedidoCreateResponseDTO.class);
  }

  @Override
  public List<ItemPedidoCreateResponseDTO> list() {
    List<ItemListaPedido> itemsList = repository.findAll();
    return mapper.toList(itemsList, ItemPedidoCreateResponseDTO.class);
  }

  @Override
  public ItemPedidoCreateResponseDTO advanceStatus(String idPedido) {
    Optional<ItemListaPedido> item = repository.findById(idPedido);
    if(item.isPresent()) {
      if(item.get().getStatus().equals(StatusPedido.SOLICITADO)) {
        item.get().setStatus(StatusPedido.EM_PRODUCAO);
      } else if(item.get().getStatus().equals(StatusPedido.EM_PRODUCAO)) {
        item.get().setStatus(StatusPedido.PRONTO);
        techFoodProducer.sendMessage("{ \"id\": " + item.get().getId() + ", \"status\": " + item.get().getStatus() + " }");

      }
      else return null;

      ItemListaPedido updatedItem = repository.save(mapper.toObject(item.get(), ItemListaPedido.class));
      return mapper.toObject(updatedItem, ItemPedidoCreateResponseDTO.class);
    } else
      return null;
  }

}
