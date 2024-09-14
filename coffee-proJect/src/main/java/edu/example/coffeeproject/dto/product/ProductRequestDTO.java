package edu.example.coffeeproject.dto.product;

import edu.example.coffeeproject.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDTO {
    private String productName;

    private Category category;

    private int price;

    private String description;
}
