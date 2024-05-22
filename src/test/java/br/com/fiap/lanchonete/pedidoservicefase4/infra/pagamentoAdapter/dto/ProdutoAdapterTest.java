package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto;

import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.ProdutoDto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.CategoriaEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.produtoAdapter.dto.ProdutoAdapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProdutoAdapterTest {

    @InjectMocks
    private ProdutoAdapter produtoAdapter;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnProdutoWhenFromDtoIsCalledWithValidProdutoDto() {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setId(1L);
        produtoDto.setNome("Produto Teste");
        produtoDto.setDescricao("Descricao Teste");
        produtoDto.setPreco(BigDecimal.valueOf(10.0));
        produtoDto.setCategoria(CategoriaEnum.BEBIDA);

        Produto expectedProduto = Produto.builder()
                .id(produtoDto.getId().longValue())
                .nome(produtoDto.getNome())
                .descricao(produtoDto.getDescricao())
                .preco(produtoDto.getPreco())
                .categoria(produtoDto.getCategoria())
                .build();

        Produto actualProduto = produtoAdapter.fromDTO(produtoDto);

        assertEquals(expectedProduto, actualProduto);
    }


}

