package edu.example.coffeeproject.dto.order;

import java.util.List;

public class OrderRequestDTO {

    private String email;

    private String address;

    private int postcode;

    private List<orderItemRequestDTO> orderItems;
}
