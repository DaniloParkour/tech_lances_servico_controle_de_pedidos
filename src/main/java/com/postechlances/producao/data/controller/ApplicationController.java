package com.postechlances.producao.data.controller;

import com.postechlances.producao.data.dto.crud.request.ItemListaPedidoListRequestDTO;
import com.postechlances.producao.data.dto.crud.response.ItemListaPedidoListResponseDTO;
import com.postechlances.producao.infra.mapper.response.ResponseModel;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthcheck")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "/itemlistapedido", description = "Operações CRUD e avançar STATUS para os pedidos")
public class ApplicationController {

  @GetMapping
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("OK");
  }

}
