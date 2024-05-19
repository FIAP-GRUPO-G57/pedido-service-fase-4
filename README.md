# pedido-service-fase-4
Serviço Pedido da Fase 4 do Tech Challenge

```mermaid
graph TB
subgraph Pedido
PedidoDB[(Database SQL<br>Pedido)]
PedidoAPI["Pedido API"]
RegPedido["Registrar Pedido"]
ListPedido["Listar Pedidos"]
RetPedido["Retornar Info Pedido"]

        PedidoAPI --> RegPedido
        PedidoAPI --> ListPedido
        PedidoAPI --> RetPedido
        RegPedido --> PedidoDB
        ListPedido --> PedidoDB
        RetPedido --> PedidoDB
    end

    subgraph Pagamento
        PagamentoDB[(Database NoSQL<br>Pagamento)]
        PagamentoAPI["Pagamento API"]
        RegPag["Registrar Pagamento"]
        RetPag["Receber Retorno<br>do Mercado Pago"]
        AtualizaStatusPag["Atualizar Status Pedido"]
        
        PagamentoAPI --> RegPag
        PagamentoAPI --> RetPag
        PagamentoAPI --> AtualizaStatusPag
        RegPag --> PagamentoDB
        RetPag --> PagamentoDB
        AtualizaStatusPag --> PedidoAPI
    end

    subgraph Produção
        ProducaoDB[(Database SQL<br>Produção)]
        ProducaoAPI["Produção API"]
        AcompanhaFila["Acompanhar Fila de Pedidos"]
        AtualizaStatusProd["Atualizar Status do Pedido"]
        
        ProducaoAPI --> AcompanhaFila
        ProducaoAPI --> AtualizaStatusProd
        AcompanhaFila --> ProducaoDB
        AtualizaStatusProd --> ProducaoDB
        AtualizaStatusProd --> PedidoAPI
    end

    PedidoAPI -.-> PagamentoAPI
    PedidoAPI -.-> ProducaoAPI
    PagamentoAPI -.-> PedidoAPI
    ProducaoAPI -.-> PedidoAPI

    classDef database fill:#f9f,stroke:#333,stroke-width:2px;
    classDef api fill:#ccf,stroke:#333,stroke-width:2px;
    classDef process fill:#cfc,stroke:#333,stroke-width:1px;
    
    class PedidoDB,PagamentoDB,ProducaoDB database;
    class PedidoAPI,PagamentoAPI,ProducaoAPI api;
    class RegPedido,ListPedido,RetPedido,RegPag,RetPag,AtualizaStatusPag,AcompanhaFila,AtualizaStatusProd process;
```