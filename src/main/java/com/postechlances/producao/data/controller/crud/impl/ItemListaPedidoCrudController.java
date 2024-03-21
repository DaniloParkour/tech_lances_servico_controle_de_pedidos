package com.postechlances.producao.data.controller.crud.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.postechlances.producao.data.controller.crud.IItemListaPedidoCrudController;
import com.postechlances.producao.data.dto.crud.request.ItemPedidoCreateRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemPedidoCreateResponseDTO;
import com.postechlances.producao.domain.service.crud.IItemListaPedidoCrudService;
import com.postechlances.producao.infra.mapper.response.ResponseModel;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemlistapedido")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "/itemlistapedido", description = "Operações CRUD e avançar STATUS para os pedidos")
@AllArgsConstructor
public class ItemListaPedidoCrudController implements IItemListaPedidoCrudController {

  @Autowired
  private IItemListaPedidoCrudService service;

  @Override
  @PostMapping
  public ResponseModel<ItemPedidoCreateResponseDTO> create(@RequestBody ItemPedidoCreateRequestDTO request) {
    ResponseModel<ItemPedidoCreateResponseDTO> response = new ResponseModel<ItemPedidoCreateResponseDTO>();
    ItemPedidoCreateResponseDTO createdItem = service.create(request);
    response.setData(createdItem);
    return response;
  }

  @Override
  @GetMapping
  public ResponseModel<ItemPedidoCreateResponseDTO> list() {
    ResponseModel<ItemPedidoCreateResponseDTO> response = new ResponseModel<ItemPedidoCreateResponseDTO>();
    List<ItemPedidoCreateResponseDTO> listOfItems = service.list();
    response.setList(listOfItems);
    return response;
  }

  @Override
  @GetMapping("/advancestatus/{idPedido}")
  public ResponseModel<ItemPedidoCreateResponseDTO> advanceStatus(@PathVariable String idPedido) {
    ResponseModel<ItemPedidoCreateResponseDTO> response = new ResponseModel<ItemPedidoCreateResponseDTO>();
    try {
      response.setData(service.advanceStatus(idPedido));
    } catch (JsonProcessingException e) {
      System.out.println("Erro JSON Parse!");
    }
    if(response.getData() != null) {
      response.setStatus("SUCCESS");
      response.setMessage("Status alterado para " + response.getData().getStatus());
      return response;
    }
    return null;
  }
}
