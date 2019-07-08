 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>建材列表</title>
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body class="pos-r">
<!-- <div class="pos-a" style="width:200px;left:0;top:0; bottom:0; height:100%; border-right:1px solid #e5e5e5; background-color:#f5f5f5; overflow:auto;">
	<ul id="treeDemo" class="ztree"></ul>
</div> -->
<div style="">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 产品列表 
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav> 
	<div class="page-container">
		<div class="text-c"> 日期范围：
			<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
			-
			<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
			<input type="text" name="" id="" placeholder=" 产品名称" style="width:250px" class="input-text">
			<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜产品</button>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="product_del(this,'product-del')"  class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
		 <a class="btn btn-primary radius" onclick="product_add('添加产品','product-add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加产品</a>
		 <a class="btn btn-primary radius"  href="product-export">导出产品</a>
		
		<!--  <a href="product-import" enctype="multipart/form-data" method="post">导入产品</a> -->
	<form   enctype="multipart/form-data" method="post" action="product-import" style="display:inline-block"><span class="btn
	btn-primary radius">导入产品</span>
		<a class="btn btn-primary radius" >
		<input type="file" name="file"/>
          <input type="submit" value="上传"/>
         </a>
         </form>
         
         <span>&nbsp &nbsp &nbsp &nbsp 
         
		 </span> <span style="margin-top:5px;" class="r">共有数据：<strong>${page.totalElements}</strong> 条</span> </div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width=""><input name="" type="checkbox" value=""></th>
						<th width="">Id</th>
						<th width="">采购日期</th>
						<th width="">供应商</th>
						<th width="">产品名称</th>
						<th width="">品牌/规格/型号</th>
						<th width="">参数</th>
	                    <th width="">单位</th>
	                    <th width="">含税单价</th>
	                    <th width="">未税单价</th>
	                    <th width="">质保期</th>
	                    <th width="">订单类型</th>
	                    <th width="">合同编号</th>
	                    <th width="">供应商联系方式1</th>
	                    <th width="">供应商联系方式2</th>
	                    <th width="">操作</th>
	
					</tr>
				</thead>
				<tbody>
				
				   <c:forEach items="${page.content}" var="c" varStatus="st">
				 
					<tr class="text-c va-m">
						<td><input name="checkid" type="checkbox" value="${c.id}" ></td>
						<td>${c.id}</td>
						<td><fmt:formatDate value="${c.ordertime}" pattern="yyyy年MM月dd日"/></td>
						<td>${c.supplier}</td>
						<td>${c.productname}</td>
						<td>${c.productbrand }</td>
						<td>${c.parameter}</td>
						<td>${c.unit}</td>
						<td>${c.unitprice}</td>
						<td>${c.unitpriceouttax}</td>
						<td>${c.guaranteeperiod}</td>
						<td>${c.ordertype}</td>
						<td>${c.contractno}</td>
						<td>${c.suppliernum1}</td>
						<td>${c.suppliernum2}</td>
						<td class="td-manage">
						 <a style="text-decoration:none" class="ml-5" onClick="product_edit('产品编辑','product-list/${c.id}','${c.id}')" method="get"
						 href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
						  
						 <a style="text-decoration:none"  onClick="product_del(this,'product-del')" href="javascript:;" title="删除"  >
						 <i class="Hui-iconfont">&#xe6e2;</i></a></td>								
                          
						<!-- <td class="td-manage"><a style="text-decoration:none" onClick="product_stop(this,'10001')" href="javascript:;" title="下架">
						<i class="Hui-iconfont">&#xe6de;</i></a> <a style="text-decoration:none" class="ml-5" onClick="product_edit('产品编辑','product-add.html','10001')"
						 href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
						 <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td> -->
					</tr>
					</c:forEach>
					
				</tbody>
			</table>
			 <div style="margin-top:66px">
				<jsp:include page="page.jsp"/>
				</div>  
<%-- <div>
<div style="margin-top:36px">
 <a href="?start=0">[首页]</a>
 <a href="?start=${page.number-1}">[上一页]</a>
 <a href="?start=${page.number+1}">[下一页]</a>
 <a href="?start=${page.totalPages-1}">[末页]</a>
 </div>
 </div>  --%>
			
		</div>
	</div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
 <script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">


var setting = {
	view: {
		dblClickExpand: false,
		showLine: false,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				//demoIframe.attr("src",treeNode.file + ".html");
				return true;
			}
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"一级分类", open:true},
	{ id:11, pId:1, name:"二级分类"},
	{ id:111, pId:11, name:"三级分类"},
	{ id:112, pId:11, name:"三级分类"},
	{ id:113, pId:11, name:"三级分类"},
	{ id:114, pId:11, name:"三级分类"},
	{ id:115, pId:11, name:"三级分类"},
	{ id:12, pId:1, name:"二级分类 1-2"},
	{ id:121, pId:12, name:"三级分类 1-2-1"},
	{ id:122, pId:12, name:"三级分类 1-2-2"},
];
		
		
		
$(document).ready(function(){
	var t = $("#treeDemo");
	t = $.fn.zTree.init(t, setting, zNodes);
	//demoIframe = $("#testIframe");
	//demoIframe.on("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	//zTree.selectNode(zTree.getNodeByParam("id",'11'));
});

$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  {"orderable":false,"aTargets":[0,7]}// 制定列不参与排序
	]
});
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-查看*/
function product_show(title,url,id){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-审核*/
function product_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过'], 
		shade: false
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="product_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
}
/*产品-下架*/
function product_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
}

/*产品-发布*/
function product_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="product_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布!',{icon: 6,time:1000});
	});
}

/*产品-申请上线*/
function product_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

/*产品-编辑*/
function product_edit(title,url,id){




	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
	
/*  $.ajax({
                    type:"post",
                    url:url,
                    data: $('form'),//表单数据
                    success:function(d){
                   
                     
                         var index = parent.layer.getFrameIndex(window.name);
                         parent.layer.close(index)
                            layer.msg('保存成功！');//保存成功提示
                           
                       
                        if(d=="error"){
                            layer.msg('保存异常!');
                           
                        }
                        layer.closeAll('iframe');//关闭弹窗
                    }
                });
               */
	
	
	
}





/*产品-删除*/
function product_del(obj,id){

var ids='';
$('input:checkbox').each(function(){
if(this.checked==true){
    ids+=this.value+',';
 
}


});

	layer.confirm('确认要删除吗？',function(index){
	

             
             
                  
		$.ajax({
		
			type: 'POST',
			url:id,
		   // dataType:'json', 
			data:{ids:ids},
			success: function(data){
			 // alert(ids);
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
				   window.location.reload();
			//parent.layer.closeAll('iframe');
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
</script>
</body>
</html>