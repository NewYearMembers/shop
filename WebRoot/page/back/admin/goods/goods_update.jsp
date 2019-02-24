<%@ page language="java" import="java.util.*,cn.hsl.factory.*,java.sql.*,cn.hsl.service.*,cn.hsl.vo.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String nophoto = basePath + "upload/goods/";
String url = basePath + "page/back/admin/goods/goods_update_do.jsp";
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
  	int gid = Integer.parseInt(request.getParameter("gid"));
  	IGoodsService igs = Factory.getServiceInstance("service.goods");
  	Map<String,Object> all = igs.editPre(gid);
  	Set<Integer> t = (Set<Integer>)all.get("titles");
  	Item i = (Item)all.get("items");
  	Goods g = (Goods)all.get("goods");
  	List<Title> lt = (List<Title>)all.get("allTitles");
    List<Item> li = (List<Item>)all.get("allItems");
  %>
  	<!-- 如果此时想禁用某一个表单控件，那么就在其后面加上一个disabled属性 -->
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<form action="<%=url%>" method="post" class="form-horizontal" enctype="multipart/form-data">
					<fieldset><!-- 此标签为表单的边框标签 -->
						<legend><label>修改商品</label></legend><!-- 此标签表示表单中的提示信息 -->
						<div class="form-group"><!-- 表示为一个表单项 -->
							<label class="col-md-2 control-label">商品名:</label>
							<div class="col-md-5">
								<input type="text" name="title" id="title" value="<%=g.getTitle()%>" class="col-md-5 form-control">
							</div>
						</div>
						<div class="form-group"><!-- 表示为一个表单项 -->
							<label class="col-md-2 control-label">价格:</label>
							<div class="col-md-5">
								<input type="text" name="price" id="price" value="<%=g.getPrice()%>" class="form-control">
							</div>
						</div>
						<div class="form-group"><!-- 表示为一个表单项 -->
							<label class="col-md-2 control-label" for="province">类别:</label>
							<div class="col-md-5">
								<select id="iid" name="iid" class="form-control">
								<%
									Iterator<Item> its = li.iterator();
									while(its.hasNext()){
										Item ivo  = its.next();
								%>
									<option value="<%=ivo.getIid()%>" <%=ivo.getIid().equals(g.getIid()) ? "selected" : ""%>><%=ivo.getTitle()%></option>
								<%
									}
								%>
								</select>
							</div>
						</div>
						<div class="form-group"><!-- 表示为一个表单项 -->
							<label class="col-md-2 control-label" >文件:</label><!-- for属性用于绑定表单组件,control-label样式用于定义此显示内容的位置居中 -->
							<img src="<%=nophoto%><%=g.getPhoto()%>" style="width:20%">
							<div class="col-md-5"><!-- 由于input不能直接加栅格所以加个div -->
								<input type="file" name="file" id="file"class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="skill">标签:</label>
							<div class="col-md-6">
						<%
							Iterator<Title> it = lt.iterator();
							while(it.hasNext()){
								Title vo = it.next();
						%>
							<div class="col-md-5">
								<div class="checkbox-inline">
									<label><input type="checkbox" name="tid" id="tid" value="<%=vo.getTid()%>" <%=t.contains(vo.getTid())?"checked":""%>><%=vo.getTitle()%></label>
								</div>
							</div>
						<% 
							}
						%>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<input type="hidden" name="gid" value="<%=g.getGid()%>">
								<input type="hidden" name="oldPhoto" value="<%=g.getPhoto()%>">
								<input type="submit" value="登录" class="btn btn-primary">
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
