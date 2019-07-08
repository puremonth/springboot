package springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.pojo.Product;

public interface  ProductALLDAO extends JpaRepository<Product,Integer> {

}
