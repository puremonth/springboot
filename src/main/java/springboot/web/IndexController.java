package springboot.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import springboot.dao.CategoryDAO;
import springboot.dao.ProductALLDAO;
import springboot.dao.ProductDAO;
import springboot.pojo.Category;
import springboot.pojo.Product;

@Controller


public class IndexController {
	@Autowired 
	ProductDAO productDAO;
	@Autowired
	ProductALLDAO productalldao;
	
	@RequestMapping ("/index")
	public String index(){
		
		return "index";
		
		
	}
	
	 @RequestMapping(value="/product-list")
	      public String listproduct(Model m,@RequestParam(value="start",defaultValue="0") int start,@RequestParam(value="size",defaultValue="10")int size)throws Exception{
		       start=start<0?0:start;
		       Sort sort=new Sort(Sort.Direction.DESC,"id");
		       Pageable pageable=new PageRequest(start,size,sort);
		       Page<Product>page=productDAO.findAll(pageable);
		       m.addAttribute("page",page);
		       System.out.println("列表");
		       System.out.println(page.getTotalElements());
		       System.out.println(page.getTotalPages());
		       System.out.println(page.getTotalPages()==0);
		      
		       
		       return "product-list";
		     
	
		 
	 }
	 @GetMapping("/product-list/{id}")
     public String editproduct(Model m,@RequestParam(value="start",defaultValue="0") int start,@RequestParam(value="size",defaultValue="10")int size
    		 ,@PathVariable("id")int id)throws Exception{
		 
		
		   Product p=productDAO.getOne(id);
	       m.addAttribute("p",p);
	       
	       return "product-edit";
	       
	 
}
	 @RequestMapping("/product-add")
     public String addproduct( )throws Exception{
		  
	       
	       return "product-add";
	       
	 
}
	 
	 @PutMapping("/product-listupdate/{id}")
     public String updateproduct(Product p,@RequestParam(value="start",required=false)Integer number)throws Exception{
		   productDAO.save(p);
		    System.out.println("更新");
		    System.out.println(number);
	       return "redirect:product-list";
	       
	 
}
	 @PostMapping("/product-del")
     public String delproduct(Product p,HttpServletRequest request)throws Exception{
	/*	 List<Product> p1=productDAO.findAllById(ids);
		 productDAO.deleteInBatch(p);
		 return "product-list";*/
		String ids=request.getParameter("ids");
		String[]idsStr=ids.split(",");
		List idsStr1=removeNullStringArray(idsStr);
		for(int i=0;i<idsStr1.size();i++){
			
			productDAO.delete(Integer.parseInt((String) idsStr1.get(i)));
			
		}
		 
		
		 return "redirect:product-list";
		 
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
	
	
    @RequestMapping("/product-listsave")
	public String addProduct(Product p,HttpServletRequest request,@RequestParam(value="start",defaultValue="0") int start,@RequestParam(value="size",defaultValue="10")int size,Model m) throws ParseException{
	
	
	

    	productDAO.save(p);
    
    	System.out.println("保存");
    
		   return "redirect:product-list";
    	

	}
	
  
 @PostMapping("/product-import")
 
public String  product_import(MultipartFile file){
	 
	 
	 

	 if(file==null){
		 System.out.println("file不能为空");
		 
		 
	 }
	 List<Product>products=new ArrayList<Product>();
	 try{
		 HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
		 int sheets = workbook.getNumberOfSheets();
		 for (int i = 0; i < sheets; i++) {
             HSSFSheet sheet = workbook.getSheetAt(i);
             //获取多少行
             int rows = sheet.getPhysicalNumberOfRows();
             Product product = null;
             //遍历每一行，注意：第 0 行为标题
             for (int j = 1; j < rows; j++) {
                 product = new Product();
                  //获得第 j 行
                 HSSFRow row = sheet.getRow(j);
                 
                 
                 //row.createCell(0).setCellValue(product.getId());
        	     /*row.createCell(1).setCellValue(product.getProductname());
        		 row.createCell(2).setCellValue(product.getContractno());
        		 row.createCell(3).setCellValue(product.getGuaranteeperiod());
        		 row.createCell(4).setCellValue(product.getSuppliernum1());
        		 row.createCell(5).setCellValue(product.getSuppliernum2());
        		 row.createCell(6).setCellValue(product.getProductbrand());
        		 row.createCell(7).setCellValue(product.getParameter());
        		 row.createCell(8).setCellValue(product.getUnit());
        		 row.createCell(9).setCellValue(product.getOrdertype());
        		 row.createCell(10).setCellValue(product.getUnitprice());
        		 row.createCell(11).setCellValue(product.getUnitpriceouttax());
        		 row.createCell(12).setCellValue(product.getGuaranteeperiod());
        		 row.createCell(13).setCellValue(product.getContractno());*/
        		 
                 
                 
              // product.setId((int) row.getCell(0).getNumericCellValue());
               
                 if(null!=row.getCell(1).getDateCellValue()){
                	 
                 product.setOrdertime(row.getCell(1).getDateCellValue());
           }
            
                 if(null!=row.getCell(2).getStringCellValue()){
                	
                 product.setSupplier(row.getCell(2).getStringCellValue());
                 }
                 
              
                 if(null!=row.getCell(3).getStringCellValue()){
                 product.setProductname(row.getCell(3).getStringCellValue());
                 }
                 
               
                 if(null!=row.getCell(4).getStringCellValue()){
                 product.setProductbrand(row.getCell(4).getStringCellValue());
                 }
               
                 if(null!=row.getCell(5)){
                 product.setParameter(row.getCell(5).getStringCellValue());
                 }
           
                 if(null!=row.getCell(6).getStringCellValue()){
                 row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                 product.setUnit(row.getCell(6).getStringCellValue());
                 }
                
                
                 if(null!=row.getCell(7)){ 
                 row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                 product.setUnitprice(row.getCell(7).getStringCellValue());
                 }
                
                 if(null!=row.getCell(8)){
                 row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                 product.setUnitpriceouttax(row.getCell(8).getStringCellValue());
                 }
               
                 if(null!=row.getCell(9).getStringCellValue()){
                 product.setGuaranteeperiod(row.getCell(9).getStringCellValue());
                 }
              
                 if(null!=row.getCell(10).getStringCellValue()){
                 product.setOrdertype(row.getCell(10).getStringCellValue());
                 }
              
                 if(null!=row.getCell(11)){
                 row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                 product.setContractno(row.getCell(11).getStringCellValue());
                 }
                 
                 if(null!=row.getCell(12)){
                 row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                 product.setSuppliernum1(row.getCell(12).getStringCellValue());
                 }
               
                 if(null!=row.getCell(13)){
                 row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                 product.setSuppliernum2(row.getCell(13).getStringCellValue());
                 }
                 
             
                 productDAO.save(product);
               
                // products.add(product);
             
             }
             
         }

     } catch (IOException e) {
         System.out.println(e.getMessage()+e);
         System.out.println(e.getMessage());
     }
    return "redirect:product-list";
 }

 

@RequestMapping("/product-export")
 
public void product_export(HttpServletResponse response) throws IOException{
	 HSSFWorkbook workbook=new HSSFWorkbook();
	 HSSFSheet    sheet=workbook.createSheet("产品资料");
	 setTitle(workbook,sheet);
	 List<Product>products=productalldao.findAll();
	 int rowNum=1;
	 for (Product product:products){
		 HSSFRow row=sheet.createRow(rowNum);
		 row.createCell(0).setCellValue(product.getId());
		 if(product.getOrdertime()!=null){
			 row.createCell(1).setCellValue(product.getOrdertime());
			 }
		 row.createCell(2).setCellValue(product.getSupplier());
		 row.createCell(3).setCellValue(product.getProductname());
		 row.createCell(4).setCellValue(product.getProductbrand());
		 row.createCell(5).setCellValue(product.getParameter());
		 row.createCell(6).setCellValue(product.getUnit());
		 row.createCell(7).setCellValue(product.getUnitprice());
		 row.createCell(8).setCellValue(product.getUnitpriceouttax());
		 row.createCell(9).setCellValue(product.getGuaranteeperiod());
		 row.createCell(10).setCellValue(product.getOrdertype());
		 row.createCell(11).setCellValue(product.getContractno());
		 row.createCell(12).setCellValue(product.getSuppliernum1());
		 row.createCell(13).setCellValue(product.getSuppliernum2());
		 
		 
		 
	     rowNum++;
	 }
	 
	 String fileName="product-name.xlsx";
	response.reset();
	response.addHeader("Content-Disposition", "attachment;filename="+fileName);
	OutputStream os=new BufferedOutputStream(response.getOutputStream());
	response.setContentType("application/vnd.ms-excel;charset=gb2312");
	workbook.write(os);
	os.flush();
	os.close();
	 
	 
	 
 }
  
 private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet){
     HSSFRow row = sheet.createRow(0);
     //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
     sheet.setColumnWidth(0, 10*256);
     sheet.setColumnWidth(1, 20*256);
     sheet.setColumnWidth(2, 20*256);
     sheet.setColumnWidth(3, 20*256);
     sheet.setColumnWidth(4,20*256);
     sheet.setColumnWidth(5, 20*256);
     sheet.setColumnWidth(6, 20*256);
     sheet.setColumnWidth(7, 20*256);
     sheet.setColumnWidth(8, 20*256);
     sheet.setColumnWidth(9, 20*256);
     sheet.setColumnWidth(10, 20*256);
     sheet.setColumnWidth(11, 20*256);
     sheet.setColumnWidth(12, 20*256);
     sheet.setColumnWidth(13, 20*256);
    
     //设置为居中加粗
     HSSFCellStyle style = workbook.createCellStyle();
     HSSFFont font = workbook.createFont();
     font.setBold(true);
     style.setFont(font);

     HSSFCell cell;
     cell = row.createCell(0);
     cell.setCellValue("Id");
     cell.setCellStyle(style);

     cell = row.createCell(1);
     cell.setCellValue("采购日期");
     cell.setCellStyle(style);

     cell = row.createCell(2);
     cell.setCellValue("供应商");
     cell.setCellStyle(style);

     cell = row.createCell(3);
     cell.setCellValue("产品名称");
     cell.setCellStyle(style);
     
     
     cell = row.createCell(4);
     cell.setCellValue("品牌/规格/型号");
     cell.setCellStyle(style);
     cell = row.createCell(5);
     cell.setCellValue("参数");
     cell.setCellStyle(style);
     cell = row.createCell(6);
     cell.setCellValue("单位");
     cell.setCellStyle(style);
     cell = row.createCell(7);
     cell.setCellValue("含税单价");
     cell.setCellStyle(style);
     cell = row.createCell(8);
     cell.setCellValue("未税单价");
     cell.setCellStyle(style);
     cell = row.createCell(9);
     cell.setCellValue("质保期");
     cell.setCellStyle(style);
     cell = row.createCell(10);
     cell.setCellValue("订单类型");
     cell.setCellStyle(style);
     cell = row.createCell(11);
     cell.setCellValue("合同编号");
     cell.setCellStyle(style);
     cell = row.createCell(12);
     cell.setCellValue("供应联系方式1");
     cell.setCellStyle(style);
     cell = row.createCell(13);
     cell.setCellValue("供应联系方式2");
     cell.setCellStyle(style);
    
 }

 
    public List removeNullStringArray(String[] arrayString) {
    	List<String> list1 = new ArrayList<String>();
    	
    	for (int i=0 ;i<arrayString.length; i++) {
       if(arrayString[i]!=null && arrayString[i].length()!=0){
    		list1.add(arrayString[i]);
    	}
    		}
    	
		 return list1;
		 }
    
  
    
}
