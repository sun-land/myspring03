package com.sparta.myspring03.controller;

import com.sparta.myspring03.model.Ordering;
import com.sparta.myspring03.requestDto.OrderRequestDto;
import com.sparta.myspring03.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    // 주문 저장하기 API
    @PostMapping("/order/request")
    public Ordering saveOrder(@RequestBody OrderRequestDto requestDto) {
        return orderService.saveOrder(requestDto);
    }

    // 주문 조회하기 API
    @GetMapping("/orders")
    public List<Ordering> getAllOrders() {
        return orderService.getAllOrders();
    }
}
