package springboot.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.Application;
import springboot.dao.CategoryDAO;
import springboot.pojo.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)

public class TestJPA {
@Autowired CategoryDAO dao;
@Test
public void test(){
	List<Category>cs=dao.findAll();
	for(Category c:cs){
		System.out.println("c.getName():"+c.getName());
	}
	
}

@Test
public void test2(){
	System.out.println("查询名称category 1的分类:");
	List<Category>cs=dao.findByName("category 1");
	for(Category c:cs){
		System.out.println("c.getName():"+c.getName());
	}
	System.out.println("123");
}
	
	@Test
	public void test3(){
		System.out.println("根据名称模糊查询,id大于5,并且名称正排序查询");
		List<Category>cs=dao.findByNameLikeAndIdGreaterThanOrderByNameAsc("%3%",5);
		for(Category c:cs){
			
			System.out.println(c.getName());
		}
		System.out.println();
	}
	
}
