<%@ page pageEncoding="UTF-8"%>
<script type="text/javascript">
  		window.onload = function(){
  			goForward();
  		}
  		function goForward(){
  			obj = document.getElementById("time");
  			count = parseInt(obj.innerHTML);
  			count--;
  			if(count == 0){
  				window.location = "<%=request.getParameter("url")%>";
  			}else{
  				obj.innerHTML = count;
  				setTimeout(goForward,1000);
  			}
  		}
  	</script>
  	<h1><%=request.getParameter("msg")%></h1>
  	<h1><span id="time">3</span>后自动跳转</h1>
  	<h1><a href="<%=request.getParameter("url")%>">点击此处进行跳转</a></h1>		
