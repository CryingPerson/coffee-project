package edu.example.coffeeproject.controller;

import edu.example.coffeeproject.dto.order.PageRequestDTO;
import edu.example.coffeeproject.dto.product.ProductDTO;
import edu.example.coffeeproject.entity.Product;
import edu.example.coffeeproject.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> Register(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.add(productDTO));
    }

    @GetMapping("{pid}")
    public ResponseEntity<ProductDTO> getProducts(@PathVariable("pid") Long pid) {
        return ResponseEntity.ok(productService.read(pid));
    }

    @PutMapping("{pid}")
    public ResponseEntity<ProductDTO> Modify(@PathVariable("pid") Long pid, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.update(productDTO));
    }

    @DeleteMapping("{pid}")
    public void delete(@PathVariable("pid") Long pid) {
        productService.remove(pid);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(page).size(size).build();

        Page<Product> productPage = productService.readAll(pageRequestDTO);
        return ResponseEntity.ok(productPage);
    }
}
