package com.postechlances.producao.data.controller;

import com.postechlances.producao.domain.messaging.TechFoodProducer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthcheck")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "/itemlistapedido", description = "Operações CRUD e avançar STATUS para os pedidos")
public class ApplicationController {

  @Autowired
  TechFoodProducer techFoodProducer;

  @GetMapping
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("OK");
  }

}
