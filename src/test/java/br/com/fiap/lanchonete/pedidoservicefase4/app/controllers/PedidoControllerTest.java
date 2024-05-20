package br.com.fiap.lanchonete.pedidoservicefase4.app.controllers;


import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.ItemDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.PedidoDto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class PedidoControllerTest {


    private PedidoController pedidoController;

    @Mock

    private  GetPedidoByIdUsecase getPedidoByIdUsecase;

    @Mock
    private  FindPedidoByStatusUsecase findPedidoByStatusUsecase;

    @Mock

    private  CreatePedidoUsecase createPedidoUsecase;

    @Mock
    private  UpdatePedidoUsecase updatePedidoUsecase;

    @Mock
    private  AddItemPedidoUsecase addItemPedidoUsecase;

    @Mock

    private  DeleteItemPedidoUsecase deleteItemPedidoUsecase;

    @Mock

    private  CheckoutPedidoUsecase checkoutPedidoUsecase;

    @Mock

    private  ConfirmPedidoUsecase confirmPedidoUsecase;

    @Mock

    private  PayPedidoUsecase payPedidoUsecase;


    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        pedidoController = new PedidoController(getPedidoByIdUsecase, findPedidoByStatusUsecase, createPedidoUsecase, updatePedidoUsecase, addItemPedidoUsecase, deleteItemPedidoUsecase, checkoutPedidoUsecase, confirmPedidoUsecase, payPedidoUsecase, modelMapper);
    }

    @Test
    public void get_HappyPath() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setId(1L);

        when(getPedidoByIdUsecase.get(anyLong())).thenReturn(pedido);
       // when(modelMapper.map(any(Pedido.class), any())).thenReturn(pedidoDto);

        ResponseEntity<PedidoDto> result = pedidoController.get(1L);

        assertEquals(pedidoDto.getId(), result.getBody().getId());
    }

    @Test
    public void put_HappyPath() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);


        List<Item> itens = List.of(new Item());
        pedido.setItens(itens);

        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setId(1L);

        List<ItemDto> itensDto = List.of(new ItemDto());
        pedidoDto.setItens(itensDto);

        when(updatePedidoUsecase.update(anyLong(), any(Pedido.class))).thenReturn(pedido);
      //  when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);

        ResponseEntity<PedidoDto> result = pedidoController.put(1L, pedidoDto);

        assertEquals(pedidoDto.getId(), result.getBody().getId());
    }
}
