package springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.pojo.Product;

public interface ProductDAO extends  JpaRepository<Product,Integer>{

}
