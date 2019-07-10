package springboot.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springboot.pojo.User;
import springboot.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	@Resource
private	UserService userService;
	
	@RequestMapping(value="/denglu")
	public ModelAndView  denglu(ModelAndView view,Model model,@RequestParam String name,@RequestParam String password){
		
		if(name.equals("")||password.equals("")){
			
		
		model.addAttribute("-1");
	
		}else{
			
			User user=userService.selectByName(name);
			
			view.setViewName("index");
			
			return view;
		}
		
		
		view.setViewName("login.html");
		return view;
		
	
		
		
		
	}
	
			
	
		
		
	

}
