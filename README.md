# Microserviço de Produção

### URL base localhos: http://localhost:8080

### Criar Pedido
**POST:** http://localhost:8080/itemlistapedido
**Body:**
```json
{
    "identifier_pedido": "1",
    "identifier_cliente": "10",
    "itens": ["Cheese Burguer", "Batata G c/ Bacon", "Sorvete de manga com cobertura de menta", "Coca zero 1,5L"]
}
```

### Listar Pedidos
**GET:** http://localhost:8080/itemlistapedido

### Detalhar Pedido
**GET:** http://localhost:8080/itemlistapedido/{id}

### Atualizar Pedido
**PUT:** http://localhost:8080/itemlistapedido
**Body:**
```json
{
    "id": 10,
    "itens": ["4x Cheese Burguer", "2x Batata G c/ Bacon", "Sorvete de manga com cobertura de menta", "Coca zero 1,5L", "Eno Guaraná"]
}
```

### Avançar Status
**GET:** http://localhost:8080/itemlistapedido/advancestatus/{id}
* **Fluxo:** EM_PRODUCAO => PRONTO => FINALIZADO
* EM_PRODUCAO: Status inicial, quando é cadastrado um pedido já pago
* PRONTO: Quando o pedido está pronto mas o cliente ainda não pegou
* FINALIZADO: Cliente pegou o produto no balcão

### Deletar Pedido
**DELETE:** http://localhost:8080/itemlistapedido/{id}
