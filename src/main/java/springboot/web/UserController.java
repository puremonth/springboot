package springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserController {
	
	@RequestMapping(value="/denglu")
	public ModelAndView  denglu(ModelAndView view){
		
		view.setViewName("/index");
		return view;

		
	}
	
			
	
		
		
	

}
