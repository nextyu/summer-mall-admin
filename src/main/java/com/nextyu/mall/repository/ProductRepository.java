package com.nextyu.mall.repository;

import com.nextyu.mall.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * created on 2017-07-11 15:08
 *
 * @author nextyu
 */
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
}
