<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 
  <% String path = request.getContextPath(); 
  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
 <head>
 <base a href="<%=basePath%>">
    <link href="<%=basePath%>">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>分页</title>
 <script src="<%=path%>/js/jquery.min.js"></script>
 </head>
 <body>
     <div class="page_nav" style="text-align:center" >
         <c:choose>
             <c:when test="${page.totalPages <= 10&&page.totalPages>0}"><!-- 如果总页数小于10，则全部显示 -->
                <c:set var="begin" value="0"></c:set>
                <c:set var="end" value="${page.totalPages }"></c:set>
             </c:when>
             <c:when test="${page.totalPages==0}"><!-- 如果总页数小于10，则全部显示 -->
                <c:set var="begin" value="0"></c:set>
                <c:set var="end" value="1"></c:set>
             </c:when>
            
             
             
            <%--  <c:when test="${0<=page.totalPages<1}"><!-- 如果总页数小于10，则全部显示 -->
                 <c:set var="begin" value="0"></c:set>
                <c:set var="end" value="2"></c:set>
             </c:when> --%>
             
             <c:when test="${page.number <= 5 }"><!-- 如果当前页数小于5，则显示1-10页 -->
                 <c:set var="begin" value="0"></c:set>
                 <c:set var="end" value="9"></c:set>
             </c:when>
             <c:otherwise><!-- 否则，显示前5页和后5页，保证当前页在中间 -->
                <c:set var="begin" value="${page.number-5 }"></c:set>
                <c:set var="end" value="${page.number+5 }"></c:set>
                <c:if test="${end > page.totalPages }"><!-- 如果end值大于总的记录数，则显示最后10页 -->
                    <c:set var="end" value="${page.totalPages}"></c:set>
                     <c:set var="begin" value="${end-10 }"></c:set>
                 </c:if>
             </c:otherwise>
        </c:choose>
        <c:choose>
             <c:when test="${page.number != 0 }"><!-- 如果当前页为1，则不显示首页和上一页 -->
                <a href="product-list?start=0">首页</a> 
                <%--  <a href="?start=${page.number-1}">[上一页]</a> --%>
                <a href="product-list?start=${page.number-1 }">上一页</a>
             </c:when>
         </c:choose>
    
         <!-- 遍历页码 -->
        <c:forEach begin="${begin }" end="${end-1}" var="index">
             <c:choose>
                 <c:when test="${page.number == index }"><!-- 如果为当前页，则特殊显示 -->
                     <a style="height:24px; margin:0 3px; border:none; background:rgb(90,152,222);">${index+1}</a>
                </c:when>
                 <c:otherwise><!-- 否则，普通显示 -->
                     <a href="product-list?start=${index}">${index+1}</a>
                   
                </c:otherwise>
             </c:choose>
        </c:forEach> 
 
         <c:choose>
             <c:when test="${page.number!= page.totalPages-1}"><!-- 如果当前页为总的记录数，则不显示末页和下一页 -->
                 <a href="product-list?start=${page.number+1 }">下一页</a> 
                <a href="product-list?start=${page.totalPages-1}">末页</a>
            </c:when>
         </c:choose> 
        共${page.totalPages }页，${page.totalElements }条记录 到第<input
             value="${page.number+1}" name="pn" id="pn_input" style="width:55px;" type="number"/>页 <input
             id="pn_btn" type="button" value="确定">
        <script type="text/javascript">
             //为按钮绑定一个单击响应函数
            $("#pn_btn").click(function() {
                 //获取到要跳转的页码
               var number = $("#pn_input").val()-1;
                 //通过修改window.location属性跳转到另一个页面
                 window.location = "product-list?start=" + number;
            });
         </script>
     </div>
 </body>
 </html>