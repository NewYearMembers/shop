<%@ page language="java" import="java.util.*,cn.hsl.factory.*,java.sql.*,cn.hsl.service.*,cn.hsl.vo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String url = basePath + "page/back/admin/goods/goods_add_do.jsp";
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
  	IGoodsService goods = Factory.getServiceInstance("service.goods");
      	Map<String,Object> all = goods.addGoodsPre();
      	List<Title> t = (List<Title>)all.get("allTitles");
      	List<Item> i = (List<Item>)all.get("allItems");
  %>
  	<!-- 如果此时想禁用某一个表单控件，那么就在其后面加上一个disabled属性 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form action="<%=url%>" method="post" class="form-horizontal" enctype="multipart/form-data">
					<fieldset><!-- 此标签为表单的边框标签 -->
						<legend><label>增加商品</label></legend>
						<div class="form-group"><!-- 表示为一个表单项 -->
							<label class="col-md-2 control-label">商品名:</label>
							<div class="col-md-5">
								<input type="text" name="title" id="title" placeholder="请输入商品名" class="col-md-5 form-control">
							</div>
						</div>
						<div class="form-group"><!-- 表示为一个表单项 -->
							<label class="col-md-2 control-label">价格:</label>
							<div class="col-md-5">
								<input type="text" name="price" id="price" placeholder="请输入价格" class="form-control">
							</div>
						</div>
						<div class="form-group"><!-- 表示为一个表单项 -->
							<label class="col-md-2 control-label">分类:</label>
							<div class="col-md-5"><!-- 由于input不能直接加栅格所以加个div -->
								<select id="iid" name="iid" class="form-control">
								<%
									Iterator<Item> its = i.iterator();
									while(its.hasNext()){
										Item ivo  = its.next();
								%>
									<option value="<%=ivo.getIid()%>"><%=ivo.getTitle()%></option>
								<%
									}
								%>
								</select>
							</div>
						</div>
						<div class="form-group"><!-- 表示为一个表单项 -->
							<label class="col-md-2 control-label">文件:</label>
							<div class="col-md-5"><!-- 由于input不能直接加栅格所以加个div -->
								<input type="file" name="photo" id="photo" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">标签:</label>
						<%
							Iterator<Title> it = t.iterator();
											while(it.hasNext()){
												Title vo  = it.next();
						%>
							<div class="col-md-5">
								<div class="checkbox-inline">
									<label><input type="checkbox" name="tid" id="tid" value="<%=vo.getTid()%>"><%=vo.getTitle()%></label>
								</div>
							</div>
						<% 
							}
						%>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<input type="submit" value="增加" class="btn btn-primary">
								<input type="reset" value="重置" class="btn btn-default">
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
  </body>
</html>
