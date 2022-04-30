package com.midas.outflearn.controller.order;

import com.midas.outflearn.controller.ApiResponse;
import com.midas.outflearn.service.order.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
