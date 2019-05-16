<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 
 <div align="center">
 
</div>
 
 <div style="width:500px;margin:20px auto;text-align:center">
 
 <table align='center' border='1` cellspacing='0'>
 <tr>
 <td>id</td>
 <td>name</td>
 <td>编辑</td>
 <td>删除</td>
 </tr>
 
 </tr>
 <c:forEach items="${page.content}" var="c" varStatus="st">

 <tr>
 <td>${c.id}</td>
 <td>${c.name}</td>
 <td><a href="editCategory?id=${c.id}">编辑</a></td>
 <td><a href="deleteCategory?id=${c.id}">删除</a></td>
 </tr>
 </c:forEach>
 </table>  
 <br>
 <div>
 <a href="?start=0">[首页]</a>
 <a href="?start=${page.number-1}">[上一页]</a>
 <a href="?start=${page.number+1}">[下一页]</a>
 <a href="?start=${page.totalPages-1}">[末页]</a>
 </div>
 <br>
 
 <form action="addCategory"  method="post">
 name:<input name="name"> <br>
 <button type="submit">提交</button>
 </form>
 </div>