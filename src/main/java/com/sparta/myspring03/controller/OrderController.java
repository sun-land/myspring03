package com.sparta.myspring03.controller;

import com.sparta.myspring03.requestDto.OrderRequestDto;
import com.sparta.myspring03.responseDto.OrderResponseDto;
import com.sparta.myspring03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문하기
    @PostMapping("/order/request")
    public OrderResponseDto saveOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.saveOrder(orderRequestDto);
    }

    // 주문 조회
    @GetMapping("/orders")
    public List<OrderResponseDto> getAllOrders() {
    List<OrderResponseDto> orderResponseDtos = orderService.getAllOrders();
    return orderResponseDtos;
    }

}

