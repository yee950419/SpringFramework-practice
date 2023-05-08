package com.jpabook.jpashop.api;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    /**
     * 엔티티 노출로 인해 api 스팩 바뀜, N+1 문제 발생
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();

            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all;
    }

    /**
     * N+1 문제 발생
     */
    @GetMapping("/api/v2/orders")
    public OrderResult ordersV2() {
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        List<OrderDto> collect = orders.stream().map(o -> new OrderDto(o)).collect(Collectors.toList());
        return new OrderResult(collect);
    }

    /**
     * fetch join으로 N+1 문제 해결, distinct로 중복 컬럼 제거
     * 페이징 불가능, 패치 조인 2개 이상 사용 불가능 -> 데이터 부정합 위험
     */
    @GetMapping("/api/v3/orders")
    public OrderResult ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> collect = orders.stream().map(OrderDto::new).collect(Collectors.toList());
        return new OrderResult(collect);
    }

    /**
     * N:1 관계는 DB row 갯수를 증가시키지 않으므로 fetch join하고,
     * 나머지 1:N 관계들은 default_batch_fetch_size 설정으로 미리 가져오는 방식 (paging 시 가장 많이 사용하는 방식)
     * 개별 size를 설정하고 싶을 때는 엔티티에 @BatchSize로 size 설정
     */
    @GetMapping("/api/v3.1/orders")
    public OrderResult ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                     @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberAndDelivery(offset, limit);
        List<OrderDto> collect = orders.stream().map(OrderDto::new).collect(Collectors.toList());
        return new OrderResult(collect);
    }

    @Data
    @AllArgsConstructor
    static class OrderResult<T> {
        private T orderData;

    }

    @Data
    static class OrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream().map(o -> new OrderItemDto(o)).collect(Collectors.toList());
        }
    }

    @Data
    static class OrderItemDto{

        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {

            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
