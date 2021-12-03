package com.msi.services;

import com.msi.models.Product;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Optional<Product> getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);
}
