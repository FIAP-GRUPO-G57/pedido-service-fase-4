package br.com.fiap.lanchonete.pedidoservicefase4.infra.produtoAdapter.dto;

import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.ProdutoDto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.ProdutoProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProdutoAdapter implements ProdutoProvider {

    private final RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;


    public ProdutoAdapter(RestTemplate restTemplate) {


        this.restTemplate = restTemplate;
    }



    @Override
    public Produto get(Long id) {

        //Realizar chamada para o serviço de produtos para buscar o produto pelo id
        //Retornar o produto encontrado
        //Utilizar ProdutoDTO para mapear o retorno do serviço de produtos
        //utilizar o método fromDTO para converter o ProdutoDTO para Produto
        //Caso não encontre o produto, retornar null
        //Utilizar Webclient para realizar a chamada para o serviço de produtos

        ProdutoDto produtoDto = restTemplate.getForObject(productServiceUrl + "/produtos/{id}", ProdutoDto.class, id);


        if (produtoDto == null) {
            return null;
        }



        return fromDTO(produtoDto);
    }

    public Produto fromDTO(ProdutoDto produtoDto) {
        return Produto.builder()
                .id(produtoDto.getId().longValue())
                .nome(produtoDto.getNome())
                .descricao(produtoDto.getDescricao())
                .preco(produtoDto.getPreco())
                .categoria(produtoDto.getCategoria())
                .build();
    }
}
