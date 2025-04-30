package com.order.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.order.management.entity.Product;
import com.order.management.enums.ProductStatus;

public interface ProductInfoRepository extends CrudRepository<Product, Integer> {

	Optional<Product> findByName(String name);
	List<Product> findByProductStatus(ProductStatus productStatus);
}
