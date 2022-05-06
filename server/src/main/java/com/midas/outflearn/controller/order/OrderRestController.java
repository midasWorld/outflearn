package com.midas.outflearn.controller.order;

import com.midas.outflearn.controller.ApiResponse;
import com.midas.outflearn.dto.order.OrderQueryDto;
import com.midas.outflearn.service.order.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.midas.outflearn.controller.ApiResponse.OK;

@RestController
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/orders")
    public ApiResponse<OrderDto> order(@RequestBody OrderRequest request) {
        return OK(
            new OrderDto(
                orderService.create(request.newOrder())
            )
        );
    }

    @GetMapping("/api/v1/orders")
    public ApiResponse<List<OrderQueryDto>> orders() {
        return OK(
            orderService.findAllOrderQueryDto()
        );
    }

    @GetMapping("/api/v1/orders/{orderId}")
    public ApiResponse<OrderQueryDto> order(@PathVariable Long orderId) {
        return OK(
            orderService.findOrderQueryDtoById(orderId)
        );
    }
}
