<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String rootPath=request.getContextPath(); %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>

<div class="user-list">
        <table class="table table-center">
            <col width="40%">
            <col width="10%">
            <col width="10%">
            <col width="15%">
            <col width="10%">
            <col width="15%">
            <tr>
                <th>标题</th>
                <th>类型</th>
                <th>状态</th>
                <th>创建时间</th>
                <th>创建人</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageFinder.data }" var="news">
	            <tr id="tr${news.id }">
	                <td>${news.newsTitle }</td>
	                <td>${news.newsTypeName}</td>
	                <td id="td${news.id }">${news.newsStatus==1 ? '已发布' : '未发布' }</td>
	                <td><fmt:formatDate value="${news.publishTime }" pattern="yyyy-MM-dd" /></td>
	                <td>
	                	<c:if test="${empty news.createName}">${news.userName }</c:if>
	                	<c:if test="${!empty news.createName}">${news.createName }</c:if>
	                </td>
	                <td>
	                <a href="javascript:;" onclick="deleteNew(${news.id })" class="btn btn-mini btn-primary">删除</a>
		            <a href="javascript:editNews('2',${news.id });" class="btn btn-mini btn-primary">修改</a>
			        <a href="javascript:;" id="com${news.id }" marks="${news.newsStatus }" onclick="changNewsStatus(${news.id})" class="btn btn-mini btn-primary">${news.newsStatus==1?'下线':'发布'}</a>
	                </td>
	            </tr>
	         </c:forEach>
	         <c:if test="${empty pageFinder.data }">
	         	<tr>
	         		<td colspan="6">暂无数据</td>
	         	</tr>
	         </c:if>
        </table>
    </div>
<div class="pages">
	<c:if test="${pageFinder.pageCount>1}">
	         <ul class="pagination"></ul>
	</c:if>
	
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
		    	 queryPageByKeys(pageNo,'','');
	    	 }
	   });
  });
</script>