<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="mldn.css">
<%--
	代码的引用过程
	<div id="splitBarDiv">
	<jsp:include page="/page/split_page_plugin_bars.jsp">
		<jsp:param name="currentPage" value="<%=currentPage%>"/>
		<jsp:param name="lineSize" value="<%=lineSize%>"/>
		<jsp:param name="keyWord" value="<%=keyWord%>"/>
		<jsp:param name="column" value="<%=column%>"/>
		<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
		<jsp:param name="url" value="<%=url%>"/>
		<jsp:param name="paramName" value=参数名称/>
		<jsp:param name="paramValue" value=参数内容/>
	</jsp:include>
</div>


<div id="splitBarDiv" style="float:right">
			<jsp:include page="/pages/split_page_plugin_bars.jsp">
				<jsp:param name="currentPage" value="${currentPage}"/>
				<jsp:param name="lineSize" value="${lineSize}"/>
				<jsp:param name="keyWord" value="${keyWord}"/>
				<jsp:param name="column" value="${column}"/>
				<jsp:param name="allRecorders" value="${allRecorders}"/>
				<jsp:param name="url" value="${url}"/>
			</jsp:include>
		</div>
--%>	
<%	//设置编码
	request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<%	//变量设置
	String url = null;
	int currentPage = 1;
	int lineSize = 1;
	String column = null;
	String keyWord = null;
	int allRecorders = 0;
	int pageSize = 0;
	int seed = 3; //种子树，用于判断，如果你的页码已经超过种子树了，就该显示...或者当前页码加种子树还小于总页数，那么也要显示...
%>

<%	//	接收传递参数
	try{
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}catch(Exception e){}
	try{
		allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
	}catch(Exception e){}
	
	try{
		lineSize = Integer.parseInt(request.getParameter("lineSize"));
	}catch(Exception e){}
	column = request.getParameter("column");
	keyWord = request.getParameter("keyWord");
	url = request.getParameter("url");
%>

<%	//数据处理
	if(currentPage <= 0){
		currentPage = 1;
	}
	if(allRecorders > 0){	//有记录
		pageSize = (allRecorders + (lineSize-1))/lineSize;
	}else{	//没有记录
		pageSize = 1;
	}
%>

<!--事件处理-->
<script type="text/javascript">
	function goSplit(vcp){	//根据外部传递cp进行操作
		try{
			var elekw = document.getElementById("kw").value;
			var elecol = document.getElementById("colsel").value
			window.location =  "<%=url%>?cp=" + vcp + "&ls=" + <%=lineSize%> + "&kw=" + elekw + "&col=" + elecol;
		}catch(Exception){
			window.location =  "<%=url%>?cp=" + vcp + "&ls=" + <%=lineSize%>;
		}
	}
</script> 

<ul class="pagination">
	<!--第一页-->
<%
if(pageSize > seed * 3){
%>
	<li class="<%=currentPage==1?"active":""%>"><a onclick="goSplit(1)">1</a></li>
<%	
	if(currentPage > seed * 2){	//如果当前页大于种子树(6页)
%>
		<li><span>...</span></li>	<!--就显示...-->
<%
		int startPage = currentPage - seed;	//用于显示当前页...的前三页
		int endPage = currentPage + seed;	//用于显示当前页...的后三页
		for(int i = startPage;i <= endPage;i++){
			if(i < pageSize){	//并且为了防止其无限增加后三页，加入当前页不能超过总页的判断
%>
				<li class="<%=currentPage==i?"active":""%>"><a onclick="goSplit(<%=i%>)"><%=i%></a></li>
<%
			}
		}
		if((currentPage + seed * 2) < pageSize){	//如果当前页加上两倍种子树仍旧没有总页数大
%>
		<li><span>...</span></li>	<!--就显示...-->
<%
		}
	}else{	//当前页还没有超过种子树
		for(int x = 2;x <= currentPage + seed;x++){
%>
			<li class="<%=currentPage==x?"active":""%>"><a onclick="goSplit(<%=x%>)"><%=x%></a></li>
<%		
		}
		if((currentPage + seed * 2) < pageSize){	//如果当前页加上两倍种子树仍旧没有总页数大
%>
			<li><span>...</span></li>	<!--就显示...-->
<%
		}
	}
%>
	<!--最后一页-->
	<li class="<%=currentPage==pageSize?"active":""%>"><a onclick="goSplit(<%=pageSize%>)"><%=pageSize%></a></li>
<%
}else{
	for(int i = 1;i <= pageSize;i++){
%>
		<li class="<%=currentPage==i?"active":""%>"><a onclick="goSplit(<%=i%>)"><%=i%></a></li>
<%
	}
}
%>
</ul>