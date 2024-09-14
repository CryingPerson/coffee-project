package edu.example.coffeeproject.dto.order;

import edu.example.coffeeproject.entity.Order;
import edu.example.coffeeproject.entity.OrderItem;
import edu.example.coffeeproject.entity.OrderStatus;
import edu.example.coffeeproject.entity.Product;
import edu.example.coffeeproject.exception.OrderException;
import edu.example.coffeeproject.repository.ProductRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
@Log4j2
@Data
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;
    private String email;
    private String address;
    private String postcode;
    private List<OrderItemDTO> orderItems;
    private OrderStatus orderStatus;


    public OrderDTO(Order order) {
        this.orderId = order.getOrderId();
        this.email = order.getEmail();
        this.address = order.getAddress();
        this.postcode = order.getPostcode();
        this.orderStatus = order.getOrderStatus();
        this.orderItems = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            this.orderItems.add(new OrderItemDTO(orderItem));
        }
    }

    public Order toEntity(ProductRepository productRepository) {
        Order order = Order.builder()
                .email(email)
                .address(address)
                .postcode(postcode)
                .orderStatus(orderStatus)
                .build();
        List<OrderItem> orderItemEntitys = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : orderItems) {
            Product product = productRepository.findById(orderItemDTO.getProductId())
                    .orElseThrow(OrderException.NOT_FOUND_ORDER::get);
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .order(order)
                    .category(orderItemDTO.getCategory())
                    .price(orderItemDTO.getPrice())
                    .quantity(orderItemDTO.getQuantity())
                    .build();
            orderItemEntitys.add(orderItem);
        }
        order.setOrderItems(orderItemEntitys);


        return order;


    }
}
