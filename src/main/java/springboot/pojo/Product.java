package springboot.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

 
@Entity

public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
    
	@Column(name="ordertime")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date ordertime;
	@Column(name="supplier")
	public String supplier;
	@Column(name="suppliernum1")
    public String suppliernum1;
	@Column(name="suppliernum2")
    public String suppliernum2;
	@Column(name="productname")
    public String productname;
	@Column(name="productbrand")
    public String productbrand;
	@Column(name="parameter")
	public String parameter;
	@Column(name="unit")
	public String unit;
	@Column(name="ordertype")
	public String ordertype;
	@Column(name="unitprice")
	public String unitprice;
	@Column(name="unitpriceouttax")
	public String unitpriceouttax;
	@Column(name="guaranteeperiod")
	public String guaranteeperiod;
	@Column(name="contractno")
	public String contractno;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getSuppliernum1() {
		return suppliernum1;
	}
	public void setSuppliernum1(String suppliernum1) {
		this.suppliernum1 = suppliernum1;
	}
	public String getSuppliernum2() {
		return suppliernum2;
	}
	public void setSuppliernum2(String suppliernum2) {
		this.suppliernum2 = suppliernum2;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductbrand() {
		return productbrand;
	}
	public void setProductbrand(String productbrand) {
		this.productbrand = productbrand;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getUnitpriceouttax() {
		return unitpriceouttax;
	}
	public void setUnitpriceouttax(String unitpriceouttax) {
		this.unitpriceouttax = unitpriceouttax;
	}
	public String getGuaranteeperiod() {
		return guaranteeperiod;
	}
	public void setGuaranteeperiod(String guaranteeperiod) {
		this.guaranteeperiod = guaranteeperiod;
	}
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	
	
}
