<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath=request.getContextPath(); %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>

<table class="table table-center">
        <col width="15%">
        <col width="10%">
        <col width="5%">
        <col width="10%">
        <col width="20%">
        <col width="10%">
        <col width="30%">
        <tr>
            <th>用户名</th>
            <th>姓名</th>
            <th>性别</th>
            <th>手机号</th>
            <th>角色</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
	<c:forEach items="${pageFinder.data }" var="user">
		<tr class="deletetr${user.userId }">
		    <td>${user.username }</td>
		    <td>${user.realName }</td>
			<td><c:forEach items="${dictList}" var="dict"><c:if test="${dict.itemCode== user.sex}">${dict.itemValue}</c:if></c:forEach></td>
		    <td>${user.mobile }</td>
		    <td  class="rNameSta"> 
		      <c:forEach items="${user.arr }" var="role" varStatus="status">
		      	<c:if test="${role.roleUid==1 }">
		      		<input type="hidden" value="1"/>
		      	</c:if>
		      	<c:choose>
		      		<c:when test="${status.last }">
		      			 ${role.roleName }
		      		</c:when>
		      		<c:otherwise>
		      			 ${role.roleName },
		      		</c:otherwise>
		      	</c:choose>
		      </c:forEach>
		    </td>
		    <td class="status" id="status${user.userId }">${user.status==1?'启用':'禁用' }</td>
		    <td class="btnsList">
		        <a href="javascript:Form.editUser('update',${user.userId })" class="btn btn-mini btn-primary">编辑</a>
	        	<c:if test="${userId != user.userId }">
	        		<a href="javascript:Form.changUserStatus(${user.userId })" id="com${user.userId }" marks="${user.status }" class="btn btn-mini btn-primary">${user.status==1?'禁用':'启用' }</a>
	        	</c:if>
		        <c:if test="${peoplemark=='admin' }">
		        	<a href="javascript:Form.deleteUser(${user.userId })" class="btn btn-mini btn-primary">删除</a>
		        </c:if>
		    </td>
		</tr>
	</c:forEach>
	<c:if test="${empty pageFinder.data }">
		<tr>
			<td colspan="7">暂无数据</td>
		</tr>
	</c:if>
</table>
 <div class="pages">
	<ul class="pagination"></ul>
 </div>
<script type="text/javascript">
  $(document).ready(function(){
	  $(".pagination").pagination('${pageFinder.rowCount}', {
	    	 next_text : "下一页",
	    	 prev_text : "上一页",
	    	 current_page :'${pageFinder.pageNo-1}',
	    	 link_to : "javascript:void(0)",
	    	 num_display_entries : 8,
	    	 items_per_page : '${pageFinder.pageSize}',
	    	 num_edge_entries : 1,
	    	 callback:function(page,jq){
		    	 var pageNo = page + 1;
		    	 Form.queryUserRolesList(pageNo,"");
	    	 }
	   });
  });
</script>