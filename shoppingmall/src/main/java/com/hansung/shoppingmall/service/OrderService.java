package com.hansung.shoppingmall.service;

import com.hansung.shoppingmall.dto.OrderItemResponseDto;
import com.hansung.shoppingmall.dto.OrderRequestDto;
import com.hansung.shoppingmall.dto.OrderResponseDto;
import com.hansung.shoppingmall.entity.*;
import com.hansung.shoppingmall.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public OrderResponseDto createOrderDirectly(Long userId, Long itemId, OrderRequestDto requestDto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("no item"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAmount(item.getPrice()* requestDto.getQuantity());
        orderRepository.save(order); //이때 시간 자동으로 기록

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setQuantity(requestDto.getQuantity());
        orderItem.setLineTotal(order.getAmount());
        orderItemRepository.save(orderItem);

        List<OrderItemResponseDto> itemDto=List.of(
                new OrderItemResponseDto(orderItem)
        );

        OrderResponseDto orderResponseDto = new OrderResponseDto(
                order.getId(), order.getOrderNumber(), order.getAmount(), order.getOrderDate(), itemDto);
        return orderResponseDto;
    }

    @Transactional
    public OrderResponseDto orderFromCart(Long userId, List<Long> cartItemIds){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no user"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        int totalAmount=0;
        List<OrderItemResponseDto> orderItemDtos=new ArrayList<>();

        for(Long cartItemId:cartItemIds){
            CartItem cartItem = cartItemRepository.findById(cartItemId)
                    .orElseThrow(() -> new NoSuchElementException("no item"));

            Item item = cartItem.getItem();
            int quantity=cartItem.getQuantity();
            int price=item.getPrice();
            int lineTotal=quantity*price;
            totalAmount+=lineTotal;

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
            orderItem.setQuantity(quantity);
            orderItem.setLineTotal(lineTotal);
            orderItemRepository.save(orderItem);

            orderItemDtos.add(new OrderItemResponseDto(orderItem));
            cartItemRepository.deleteById(cartItemId);
        }
        order.setAmount(totalAmount);

        OrderResponseDto orderResponseDto = new OrderResponseDto(order.getId(), order.getOrderNumber(), totalAmount, order.getOrderDate(), orderItemDtos);
        return orderResponseDto;
    }

    public List<OrderResponseDto> getUserOrders(Long userId){
        List<Order> orders = orderRepository.findByUser_Id(userId);
        return orders.stream()
                .map(OrderResponseDto::new)
                .toList();
    }

    public OrderResponseDto getOrderById(Long userId, Long orderId){
        Order order = orderRepository.findByIdAndUser_Id(orderId,userId)
                .orElseThrow(() -> new NoSuchElementException("no order"));
        return new OrderResponseDto(order);
    }

    public void  cancelOrder(Long userId,Long orderId){
        orderRepository.deleteById(orderId);
    }
}
