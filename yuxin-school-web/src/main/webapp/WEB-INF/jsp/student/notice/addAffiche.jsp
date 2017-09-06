<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css"/> 



<title>通知内容</title>
  

    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/notice/createNotice.js"></script>
   <script type="text/javascript">
      function addAffiche(){
    	  var content = $('#content').val();
    	  if($.trim(content) ==""){
    		  alert("公告内容不能为空");
    		  return;
    	  }else if(content.length > 119){
    		  alert("公告内容大于119个字符");
    		  return;
    	  }
    	  $('#addAffiche').submit();
      }
    
      function goBackAffiche(){
    	  window.location = "<%=rootPath%>/student/goBackAffiche";
      }
   </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp" />
          <form id="addAffiche" action="<%=rootPath %>/student/addAffiche" method="post">
          	<div class="tips-info">
          		 <div class="heading">
		            <h2 class="h5"> <c:if test="${empty msg}">新增公告通知</c:if>  <c:if test="${not empty msg}">通知结果</c:if></h2>
		           <c:if test="${not empty msg}">
		              <label style="display: inline-block;margin-right: 20px;">发行时间 : <fmt:formatDate value="${msg.sendTime }" pattern="yyyy-MM-dd HH:mm:ss"/></label> 
		              <label style="display: inline-block;margin-right: 20px;">发布人 : ${msg.senderName }</label> 
		              <label style="display: inline-block;margin-right: 20px;">通知状态 : 已执行</label>
		           </c:if>
		            <span class="line"></span>
		        </div>     
	            <textarea id="content" name="content" maxlength="119" class="tips-area"  <c:if test="${not empty msg.content}"> readonly="readonly" </c:if> >${msg.content}</textarea>
	            <div class="tips-btn">
	            <c:if test="${empty msg.content}">
	            	<input type="button" class="btn btn-primary " onclick="addAffiche()" value="发布公告"> 
          	    </c:if>
          	    <input type="button" value="返回" class="btn btn-sm" onclick="goBackAffiche()">
          	    
	            </div>  
          	</div>
            
            </form>
           
          
    
	


</body>
</html>