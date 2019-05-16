package springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.dao.CategoryDAO;
import springboot.mapper.CategoryMapper;
import springboot.pojo.Category;
@Controller
public class CategoryController {

	
	@Autowired 
	CategoryDAO categoryDAO;
	@Autowired
	CategoryMapper categorymapper;
	@RequestMapping("/listCategory")
	public String listCategory(Model m,@RequestParam(value="start",defaultValue="0")
	   int start,@RequestParam(value="size",defaultValue="5")int size)throws Exception{
	/*  List<Category>cs=categoryDAO.findAll();*/
	    /*
		List<Category>cs=categorymapper.findAll();
		m.addAttribute("cs",cs);
	   return "listCategory";*/
		start=start<0?0:start;
		Sort sort=new Sort(Sort.DEFAULT_DIRECTION.DESC,"id");
		Pageable pageable=new PageRequest(start,size,sort);
		Page<Category>page=categoryDAO.findAll(pageable);
		m.addAttribute("page",page);
		return "listCategory";
		
		
		
	}
	
	@RequestMapping("/addCategory")
	public String addCategory(Category c){
		categoryDAO.save(c);
		return "redirect:listCategory";
		
		
	}
	
	@RequestMapping("/deleteCategory")
	public String deleteCategory(Category c){
		categoryDAO.delete(c);
		return "redirect:listCategory";
		
		
	}@RequestMapping("/updateCategory")
	public String updateCategory(Category c){
		categoryDAO.save(c);
		return "redirect:listCategory";
		
		
	}@RequestMapping("/editCategory")
	public String editCategory(int id,Model m){
		Category c=categoryDAO.getOne(id);
		m.addAttribute("c",c);
		return "editCategory";
		
		
	}
	
	
}
