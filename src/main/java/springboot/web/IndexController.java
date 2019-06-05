package springboot.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import springboot.dao.CategoryDAO;
import springboot.dao.ProductDAO;
import springboot.pojo.Category;
import springboot.pojo.Product;

@Controller


public class IndexController {
	@Autowired 
	ProductDAO productDAO;
	
	
	@RequestMapping ("/index")
	public String index(){
		
		return "index";
		
		
	}
	
	 @GetMapping("/product-list")
	      public String listproduct(Model m,@RequestParam(value="start",defaultValue="0") int start,@RequestParam(value="size",defaultValue="10")int size)throws Exception{
		       start=start<0?0:start;
		       Sort sort=new Sort(Sort.Direction.DESC,"id");
		       Pageable pageable=new PageRequest(start,size,sort);
		       Page<Product>page=productDAO.findAll(pageable);
		       m.addAttribute("page",page);
		       System.out.println("123123");
		       return "product-list";
		       
		 
	 }
	 @GetMapping("/product-list/{id}")
     public String editproduct(Model m,@RequestParam(value="start",defaultValue="0") int start,@RequestParam(value="size",defaultValue="10")int size
    		 ,@PathVariable("id")int id)throws Exception{
		   Product p=productDAO.getOne(id);
	       m.addAttribute("p",p);
	       System.out.println("jkhjkhkjh");
	       return "product-edit";
	       
	 
}
	 @PutMapping("/product-list/{id}")
     public String updateproduct(Product p)throws Exception{
		   productDAO.save(p);
		   System.out.println("shibushi");
	       return "product-list";
	       
	 
}
	 @PostMapping("/product-del/{id}")
     public String delproduct(Product p)throws Exception{
		 productDAO.delete(p);
		 return "product-list";
		 
	 }
	 
	/* @PostMapping("/categories")
	    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
	        start = start<0?0:start;
	        Sort sort = new Sort(Sort.Direction.DESC, "id");
	        Pageable pageable = new PageRequest(start, size, sort);
	        Page<Category> page =productDAO.findAll(pageable);
	        m.addAttribute("page", page);
	        return "listCategory";   
	    }*/
	
    @PostMapping("/product-list")
	public String addProduct(Product p,HttpServletRequest request,@RequestParam(value="start",defaultValue="0") int start,@RequestParam(value="size",defaultValue="10")int size,Model m) throws ParseException{
	
	
	

    	productDAO.save(p);
    
    	System.out.println("jhkjhkj");
    	   
		   return "product-list";
    	

	}
	
  
  
	

}
