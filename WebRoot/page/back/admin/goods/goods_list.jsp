<%@ page language="java" import="java.util.*,cn.hsl.factory.*,java.sql.*,cn.hsl.service.*,cn.hsl.vo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String listurl = basePath + "page/back/admin/goods/goods_list.jsp";
String updateurl = basePath + "page/back/admin/goods/goods_update.jsp";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'goods_add.jsp' starting page</title>
     <script type="text/javascript" src="bootstrap-all/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="bootstrap-all/css/bootstrap.min.css">
  </head>
  <body>
  <% 
  	IGoodsService igs = Factory.getServiceInstance("service.goods");
  	Map<String,Object> all = igs.list();
  	List<Goods> g = (List<Goods>) all.get("allGoods");
  	Map<Integer,String> map = (Map<Integer,String>)all.get("allItems");
  %>
  	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<legend><label>商品</label></legend><!-- 此标签表示表单中的提示信息 -->
				<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
					<tr class="active">
						<td>gid</td>
						<td>title</td>
						<td>price</td>
						<td>age</td>
						<td>操作</td>
					</tr>
					<% 
						Iterator<Goods> it = g.iterator();
						while(it.hasNext()){
							Goods vo = it.next();
					%>
					<tr class="success">
						<td><%=vo.getGid()%></td>
						<td><%=vo.getTitle()%></td>
						<td><%=vo.getPrice()%></td>
						<td><%=map.get(vo.getIid())%></td>
						<td><a href="<%=updateurl%>?gid=<%=vo.getGid()%>" class="btn btn-danger">修改</a></td>
					</tr>
					<% 
						}
					%>
				</table>		
			</div>
		</div>
	</div>
  </body>
</html>
