package springboot.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@RequestMapping("/uploadPage")
     public String uploadPage(){
		return "uploadPage";
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(HttpServletRequest req,@RequestParam("file")MultipartFile file,Model m){
	
		try{
			String fileName=System.currentTimeMillis()+file.getOriginalFilename();
			String destFileName=req.getServletContext().getRealPath("")+"uploaded"+File.separator+fileName;
			System.out.println(fileName);
			System.out.println(destFileName+"12321312");
			File destFile=new File(destFileName);
			destFile.getParentFile().mkdirs();
			file.transferTo(destFile); 
			String ext=FilenameUtils.getExtension(file.getOriginalFilename());
			m.addAttribute("fileName",fileName);
			m.addAttribute("destFileName",destFileName);
			System.out.println(ext);
			System.out.println(destFile.getParent());
		}catch(FileNotFoundException e){
			e.printStackTrace();
			return "上传失败,"+e.getMessage();
			
			
		}catch(IOException e){
			e.printStackTrace();
			return "上传失败,"+e.getMessage();
			
		}
		
		return "showImg";
		
	}
	
}
