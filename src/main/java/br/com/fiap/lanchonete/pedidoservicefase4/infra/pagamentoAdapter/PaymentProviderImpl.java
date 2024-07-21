package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.PaymentProvider;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.order.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.payment.PaymentOrderDto;

@Component
public class PaymentProviderImpl implements PaymentProvider {

    private final RestTemplate restTemplate;

    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    public PaymentProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Pedido createOrderAndSetQrData(Pedido pedido, ArrayList<Item> itens) {
        // TODO Auto-generated method stub

        /*Create PaymentOrderDto using Pedido data */

        PaymentOrderDto paymentOrderDto = PaymentOrderDto.builder()
                .reference(pedido.getId())
                .valor(pedido.getPreco())
                .id(pedido.getId())
                .status("CREATED")
                .qrcode("string")
                .paymentId(0L)
                .build();


        /*Call the payment service to create the order and set the qrData using RestTemplate */

        PaymentOrderDto paymentOrderDtoResponse = restTemplate.postForObject(paymentServiceUrl + "/pagamentos", paymentOrderDto, PaymentOrderDto.class);

     /* 
        PaymentOrderDto paymentOrderDtoResponse = webClient.post()
                .uri("/pagamentos")
                .bodyValue(paymentOrderDto)
                .retrieve()
                .bodyToMono(PaymentOrderDto.class)
                .block();

                */


        /*Set the qrData in the Pedido object and externalReference from paymentId */


        pedido.setQrData(paymentOrderDtoResponse.getQrcode());
        pedido.setExternalReference(paymentOrderDtoResponse.getReference());
        pedido.setPaymentId(paymentOrderDtoResponse.getId());




        return pedido;








        
    }

    @Override
    public OrdemPedido getOrderFromPayment(Pedido pedido) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderFromPayment'");
    }

    @Override
    public OrdemPedido getMerchantOrder(Pedido pedido) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMerchantOrder'");
    }

    @Override
    public String getSku() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSku'");
    }

    
}
