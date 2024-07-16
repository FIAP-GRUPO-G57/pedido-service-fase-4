package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.payment;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentOrderDto {



    private Long reference;
    private BigDecimal valor;
    private Long id;
    private String status;
    private String qrcode;
    private Long paymentId;

    
}
