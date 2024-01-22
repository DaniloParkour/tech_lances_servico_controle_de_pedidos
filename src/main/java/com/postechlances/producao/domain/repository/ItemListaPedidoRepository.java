package com.postechlances.producao.domain.repository;

import com.postechlances.producao.domain.model.ItemListaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemListaPedidoRepository extends JpaRepository<ItemListaPedido, Long> {

}
