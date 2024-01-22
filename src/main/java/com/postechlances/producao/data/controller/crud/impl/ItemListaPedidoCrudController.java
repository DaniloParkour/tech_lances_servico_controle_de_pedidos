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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemlistapedido")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemListaPedidoCrudController implements IItemListaPedidoCrudController {

  @Autowired
  private IItemListaPedidoCrudService service;

  @Override
  @PostMapping
  public ResponseModel<ItemListaPedidoCreateResponseDTO> create(@RequestBody ItemListaPedidoCreateRequestDTO request) {
    ResponseModel<ItemListaPedidoCreateResponseDTO> response = new ResponseModel<ItemListaPedidoCreateResponseDTO>();
    ItemListaPedidoCreateResponseDTO createdItem = service.create(request);
    response.setData(createdItem);
    return response;
  }

  @Override
  @GetMapping
  public ResponseModel<ItemListaPedidoListResponseDTO> list(@ModelAttribute ItemListaPedidoListRequestDTO request) {
    ResponseModel<ItemListaPedidoListResponseDTO> response = new ResponseModel<ItemListaPedidoListResponseDTO>();
    List<ItemListaPedidoListResponseDTO> listOfItems = service.list(request);
    response.setList(listOfItems);
    return response;
  }

  @Override
  @GetMapping("/{id}")
  public ResponseModel<ItemListaPedidoListResponseDTO> detail(@PathVariable Long id) {
    ResponseModel<ItemListaPedidoListResponseDTO> response = new ResponseModel<ItemListaPedidoListResponseDTO>();
    ItemListaPedidoListResponseDTO item = service.detail(id);
    response.setData(item);
    return response;
  }

  @Override
  @PutMapping
  public ResponseModel<ItemListaPedidoUpdateResponseDTO> update(@RequestBody ItemListaPedidoUpdateRequestDTO request) {
    ResponseModel<ItemListaPedidoUpdateResponseDTO> response = new ResponseModel<ItemListaPedidoUpdateResponseDTO>();
    ItemListaPedidoUpdateResponseDTO updatedItem = service.update(request);
    response.setData(updatedItem);
    return response;
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseModel<ItemListaPedidoDeleteResponseDTO> delete(@PathVariable Long id) {
    ResponseModel<ItemListaPedidoDeleteResponseDTO> response = new ResponseModel<ItemListaPedidoDeleteResponseDTO>();
    ItemListaPedidoDeleteResponseDTO deletedItem = service.delete(id);
    response.setData(deletedItem);
    return response;
  }
}
