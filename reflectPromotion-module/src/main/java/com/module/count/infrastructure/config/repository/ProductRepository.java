package com.module.count.infrastructure.config.repository;

import com.module.count.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
