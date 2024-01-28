# Microserviço de Produção

**URL base localhost:** http://localhost:8080

### Criar Pedido
**POST:** http://localhost:8080/itemlistapedido
* **Body**
```json
{
    "identifier_pedido": "1",
    "identifier_cliente": "10",
    "itens": ["Cheese Burguer", "Batata G c/ Bacon", "Sorvete de manga com cobertura de menta", "Coca zero 1,5L"]
}
```
* **Response**
```json
{
    "data": {
        "id": 4,
        "identifier_pedido": "1",
        "identifier_cliente": "10",
        "recebimento": "2024-01-28T18:10:51.091+00:00",
        "fechamento": null,
        "pagamento": null,
        "status": "SOLICITADO",
        "itens": [
            "Cheese Burguer",
            "Batata G c/ Bacon",
            "Sorvete de manga com cobertura de menta",
            "Coca zero 1,5L"
        ],
        "preparo": null
    }
}
```

### Listar Pedidos  
**GET:** http://localhost:8080/itemlistapedido
* **Response**
```json
{
    "list": [
        {
            "id": 2,
            "identifier_pedido": "90",
            "identifier_cliente": "9",
            "recebimento": "2024-01-28T14:55:29.646+00:00",
            "fechamento": "2024-01-28T14:57:09.447+00:00",
            "pagamento": "2024-01-28T14:57:55.689+00:00",
            "status": "FINALIZADO",
            "itens": null,
            "preparo": null
        },
        {
            "id": 4,
            "identifier_pedido": "1",
            "identifier_cliente": "10",
            "recebimento": "2024-01-28T18:10:51.091+00:00",
            "fechamento": null,
            "pagamento": null,
            "status": "SOLICITADO",
            "itens": [
                "Cheese Burguer",
                "Batata G c/ Bacon",
                "Sorvete de manga com cobertura de menta",
                "Coca zero 1,5L"
            ],
            "preparo": null
        }
    ]
}
```

### Detalhar Pedido
**GET:** http://localhost:8080/itemlistapedido/{id}
* **Response**
```json
{
    "data": {
        "id": 2,
        "identifier_pedido": "90",
        "identifier_cliente": "9",
        "recebimento": "2024-01-28T14:55:29.646+00:00",
        "fechamento": "2024-01-28T14:57:09.447+00:00",
        "pagamento": "2024-01-28T14:57:55.689+00:00",
        "status": "FINALIZADO",
        "itens": null,
        "preparo": null
    }
}
```

### Atualizar Pedido
**PUT:** http://localhost:8080/itemlistapedido
* **Body**
```json
{
    "id": 10,
    "itens": ["4x Cheese Burguer", "2x Batata G c/ Bacon", "Sorvete de manga com cobertura de menta", "Coca zero 1,5L", "Eno Guaraná"]
}
```
* **Response**
```json
{
    "data": {
        "id": 4,
        "identifier_pedido": null,
        "identifier_cliente": null,
        "recebimento": null,
        "fechamento": null,
        "pagamento": null,
        "status": null,
        "itens": [
            "4x Cheese Burguer",
            "2x Batata G c/ Bacon",
            "Sorvete de manga com cobertura de menta",
            "Coca zero 1,5L",
            "Eno Guaraná"
        ],
        "preparo": null
    }
}
```

### Avançar Status
**GET:** http://localhost:8080/itemlistapedido/advancestatus/{id}
* **Fluxo:** EM_PRODUCAO => PRONTO => FINALIZADO
```
EM_PRODUCAO: Status inicial, quando é cadastrado um pedido já pago
PRONTO: Quando o pedido está pronto mas o cliente ainda não pegou
FINALIZADO: Cliente pegou o produto no balcão
```
* ***Response***
```json
{
    "status": "SUCCESS",
    "message": "Status alterado para FINALIZADO",
    "data": {
        "id": 2,
        "identifier_pedido": "90",
        "identifier_cliente": "9",
        "recebimento": "2024-01-28T14:55:29.646+00:00",
        "fechamento": "2024-01-28T14:57:09.447+00:00",
        "pagamento": "2024-01-28T14:57:55.689+00:00",
        "status": "FINALIZADO",
        "itens": null,
        "preparo": null
    }
}
```

### Deletar Pedido
**DELETE:** http://localhost:8080/itemlistapedido/{id}
*  ***Response***
```json
{
    "data": {
        "id": 1,
        "identifier_pedido": "9",
        "identifier_cliente": "99",
        "recebimento": "2024-01-28T14:55:02.071+00:00",
        "fechamento": null,
        "pagamento": null,
        "status": "SOLICITADO",
        "itens": null,
        "preparo": null
    }
}
```
