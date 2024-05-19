package br.com.fiap.lanchonete.pedidoservicefase4.app.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.PedidoDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.PedidoReduceDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.mappers.ItemMapper;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;


import java.util.Collections;
import java.util.List;



class PedidoControllerTest {

    @Mock
    private GetPedidoByIdUsecase getPedidoByIdUsecase;

    @Mock
    private FindPedidoByStatusUsecase findPedidoByStatusUsecase;

    @Mock
    private CreatePedidoUsecase createPedidoUsecase;

    @Mock
    private UpdatePedidoUsecase updatePedidoUsecase;

    @Mock
    private AddItemPedidoUsecase addItemPedidoUsecase;

    @Mock
    private DeleteItemPedidoUsecase deleteItemPedidoUsecase;

    @Mock
    private CheckoutPedidoUsecase checkoutPedidoUsecase;

    @Mock
    private ConfirmPedidoUsecase confirmPedidoUsecase;

    @Mock
    private PayPedidoUsecase payPedidoUsecase;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPedidoById_HappyPath() {
        Pedido pedido = new Pedido();
        when(getPedidoByIdUsecase.get(anyLong())).thenReturn(pedido);
        when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(new PedidoDto());

        ResponseEntity<PedidoDto> response = pedidoController.get(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(getPedidoByIdUsecase, times(1)).get(anyLong());
    }

    @Test
    void getPedidoById_NotFound() {
        when(getPedidoByIdUsecase.get(anyLong())).thenReturn(null);

        ResponseEntity<PedidoDto> response = pedidoController.get(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(getPedidoByIdUsecase, times(1)).get(anyLong());
    }

    @Test
    void searchPedidos_HappyPath() {
        List<Pedido> pedidos = Collections.singletonList(new Pedido());
        when(findPedidoByStatusUsecase.findByStatus(anyList())).thenReturn(pedidos);
        when(modelMapper.map(any(Pedido.class), eq(PedidoDto.class))).thenReturn(new PedidoDto());

        ResponseEntity<List<PedidoReduceDto>> response = pedidoController.search(null);

        assertEquals(200, response.getStatusCodeValue());
        verify(findPedidoByStatusUsecase, times(1)).findByStatus(anyList());
    }

    @Test
    void searchPedidos_EmptyList() {
        when(findPedidoByStatusUsecase.findByStatus(anyList())).thenReturn(Collections.emptyList());

        ResponseEntity<List<PedidoReduceDto>> response = pedidoController.search(null);

        assertEquals(200, response.getStatusCodeValue());
        verify(findPedidoByStatusUsecase, times(1)).findByStatus(anyList());
    }

    // Add more tests for other methods and edge cases
}