package com.jpabook.jpashop.api;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import com.jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import com.jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * XToOne 관계(ManyToOne, OneToOne)
 * <p>
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());

        //Lazy 강제 초기화
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public OrderResult orderV2() {
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        List<SimpleOrderDto> collect = orders.stream().map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());
        return new OrderResult(collect);
    }

    //V4보다 query 성능은 조금 덜어지나, 다른 곳에서 재사용하기 좋음
    @GetMapping("/api/v3/simple-orders")
    public OrderResult orderV3() {
        List<Order> orders = orderRepository.findAllWithMemberAndDelivery();
        List<SimpleOrderDto> collect = orders.stream().map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());
        return new OrderResult(collect);
    }

    //V3보다 query 성능은 좋으나, 다른 곳에서 재사용하기 힘듦, 코드 복잡성 증가
    @GetMapping("/api/v4/simple-orders")
    public OrderResult orderV4() {
        List<OrderSimpleQueryDto> orders = orderSimpleQueryRepository.findOrderDtos();
        return new OrderResult(orders);
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }

    @Data
    @AllArgsConstructor
    static class OrderResult<T>{
        private T orderData;
    }
}
