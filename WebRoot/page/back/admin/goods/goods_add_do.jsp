<%@ page language="java" import="java.util.*,cn.hsl.factory.*,java.sql.*,cn.hsl.service.*,cn.hsl.vo.*,com.jspsmart.*,com.jspsmart.upload.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String url = basePath + "page/back/admin/goods/goods_add.jsp";
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
  	request.setCharacterEncoding("UTF-8");
  	response.setCharacterEncoding("UTF-8");
  	String msg = "商品信息增加失败";
  	SmartUpload smart = new SmartUpload();
  	smart.initialize(config, request, response);
  	smart.upload();
  	Goods vo = null;
  	String fileName = null;
  	if(smart.getFiles().getSize() > 0){
  		fileName = UUID.randomUUID() + "." +  smart.getFiles().getFile(0).getFileExt();
  	}
  	Request req = smart.getRequest();
  	vo = new Goods();
  	vo.setPhoto(fileName);
	vo.setIid(Integer.parseInt(req.getParameter("iid")));
	vo.setTitle(req.getParameter("title"));
	vo.setPrice(Double.parseDouble(req.getParameter("price")));
	Set<Integer> tids = new HashSet<>();
	String [] all = req.getParameterValues("tid");
	for(int i = 0;i < all.length;i++){
		tids.add(Integer.parseInt(all[i]));
	}
  	IGoodsService igs = Factory.getServiceInstance("service.goods");
  	if(igs.insert(vo, tids)){
  		msg = "增加成功";
  		String filePath = getServletContext().getRealPath("/upload/goods/" + fileName);
  		smart.getFiles().getFile(0).saveAs(filePath);
  	}
	%>
		<jsp:forward page="forward.jsp">
			<jsp:param value="<%=msg%>" name="msg"/>
			<jsp:param value="<%=url%>" name="url"/>
		</jsp:forward>
	<%
  %>
  </body>
</html>
