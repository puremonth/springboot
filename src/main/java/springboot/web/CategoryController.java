package springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.dao.CategoryDAO;
import springboot.mapper.CategoryMapper;
import springboot.pojo.Category;
@Controller
public class CategoryController {

	
	@Autowired 
	CategoryDAO categoryDAO;
	/*
	 @GetMapping("/categories")

	public String listCategory(Model m,@RequestParam(value="start",defaultValue="0")
	   int start,@RequestParam(value="size",defaultValue="5")int size)throws Exception{
	  List<Category>cs=categoryDAO.findAll();
	    
		List<Category>cs=categorymapper.findAll();
		m.addAttribute("cs",cs);
	   return "listCategory";
		start=start<0?0:start;
		Sort sort=new Sort(Sort.DEFAULT_DIRECTION.DESC,"id");
		Pageable pageable=new PageRequest(start,size,sort);
		Page<Category>page=categoryDAO.findAll(pageable);
		m.addAttribute("page",page);
		return "listCategory";
		
		
		
	}
	*/
	 
	 @GetMapping("/categories")
	    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
	        start = start<0?0:start;
	        Sort sort = new Sort(Sort.Direction.DESC, "id");
	        Pageable pageable = new PageRequest(start, size, sort);
	        Page<Category> page =categoryDAO.findAll(pageable);
	        m.addAttribute("page", page);
	        return "listCategory";   
	    }
	 @RequestMapping("/hello1")
	    public String hello() throws Exception {
	      
	        return "hello1";   
	    }
	 
	/*@PostMapping("/categories")
	public String addCategory(Category c){
		categoryDAO.save(c);
		return "redirect:categories";
		
		
	}*/
/*	
	@DeleteMapping("/categories/{id}")
	public String deleteCategory(Category c) throws Exception{
		categoryDAO.delete(c);
		return "redirect:/categories";
		
		
	}
	
		
	@PutMapping("/categories/{id}")
	public String updateCategory(Category c)throws Exception{
		categoryDAO.save(c);
		return "redirect:/categories";
		
		
	}
	
	
	@GetMapping("/categories/{id}")
	public String editCategory(@PathVariable("id") int id,Model m){
		Category c=categoryDAO.getOne(id);
		m.addAttribute("c",c);
		return "editCategory";
		
		
	}
	
	
	@GetMapping("/categories/{id}")
	public Category getCategory(@PathVariable("id")int id){
		
		Category c=categoryDAO.getOne(id);
		System.out.println(c);
		return c;
	}*/
	
	 @GetMapping("/categories/{id}")
	    public Category getCategory(@PathVariable("id") int id) throws Exception {
	        Category c= categoryDAO.getOne(id);
	        System.out.println(c);
	        return c;
	    }
	 
	/*@PutMapping("/categories")
	
	public void addCategory(@RequestBody Category category)throws Exception{
		
		System.out.println("springboot接受到浏览器以json格式提交的数据:"+category);
	}*/
	
}
