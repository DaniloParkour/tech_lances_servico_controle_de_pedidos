package com.postechlances.producao.data.controller.crud.impl;


import com.postechlances.producao.data.controller.crud.IItemListaPedidoCrudController;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoDeleteRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoListRequestDTO;
import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoUpdateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoCreateResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoDeleteResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoListResponseDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoUpdateResponseDTO;
import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import com.postechlances.producao.infra.mapper.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class ItemListaPedidoCrudController implements IItemListaPedidoCrudController {

  @Autowired
  private IItemListaPedidoCrudService service;

  @Override
  @PostMapping
  public ResponseModel<ItemListaPedidoCreateResponseDTO> create(ItemListaPedidoCreateRequestDTO request) {
    ResponseModel<ItemListaPedidoCreateResponseDTO> response = new ResponseModel<ItemListaPedidoCreateResponseDTO>();
    ItemListaPedidoCreateResponseDTO createdItem = service.create(request);
    response.setData(createdItem);
    return response;
  }

  @Override
  @GetMapping
  public ResponseModel<ItemListaPedidoListResponseDTO> list(ItemListaPedidoListRequestDTO request) {
    ResponseModel<ItemListaPedidoListResponseDTO> response = new ResponseModel<ItemListaPedidoListResponseDTO>();
    List<ItemListaPedidoListResponseDTO> listOfItems = service.list(request);
    response.setList(listOfItems);
    return response;
  }

  @Override
  @PutMapping
  public ResponseModel<ItemListaPedidoUpdateResponseDTO> update(ItemListaPedidoUpdateRequestDTO request) {
    ResponseModel<ItemListaPedidoUpdateResponseDTO> response = new ResponseModel<ItemListaPedidoUpdateResponseDTO>();
    ItemListaPedidoUpdateResponseDTO updatedItem = service.update(request);
    response.setData(updatedItem);
    return response;
  }

  @Override
  @DeleteMapping
  public ResponseModel<ItemListaPedidoDeleteResponseDTO> delete(ItemListaPedidoDeleteRequestDTO request) {
    ResponseModel<ItemListaPedidoDeleteResponseDTO> response = new ResponseModel<ItemListaPedidoDeleteResponseDTO>();
    ItemListaPedidoDeleteResponseDTO deletedItem = service.delete(request);
    response.setData(deletedItem);
    return response;
  }
}
