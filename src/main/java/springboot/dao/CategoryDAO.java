package springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.pojo.Category;

public interface CategoryDAO extends JpaRepository <Category,Integer> {
          public List<Category>findByName(String name);
          public List<Category>findByNameLikeAndIdGreaterThanOrderByNameAsc(
        		  String name,int id);
     

}


