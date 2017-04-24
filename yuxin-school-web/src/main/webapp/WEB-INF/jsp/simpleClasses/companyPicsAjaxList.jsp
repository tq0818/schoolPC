<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String rootPath=request.getContextPath(); %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>

<div class="pic-list">
	<ul id="ulList">
		<li class='add'><i class='iconfont'>&#xe61c;</i></li>
		<c:forEach items="${pageFinder.data }" var="pic" varStatus="status">
		 		<li>
			 		<div class="mark none">
			 			<a href="javascript:chooseOnePic('${pic.picOriginalUrl}','${pic.realPath}')" class='btn btn-mini btn-warning'>使用此封面</a>
			 		</div>
			 		<img src="${pic.picOriginalUrl}">
		 		</li>	
		</c:forEach>
	</ul>
</div>
<div class="pages">
	<ul class="pagination pagenations"></ul>
</div>
<script type="text/javascript">
  $(document).ready(function(){
	  var type='${itemOneId}';
	  $("ul.pagenations").pagination('${pageFinder.rowCount}', {
	    	 next_text : "下一页",
	    	 prev_text : "上一页",
	    	 current_page :'${pageFinder.pageNo-1}',
	    	 link_to : "javascript:void(0)",
	    	 num_display_entries : 8,
	    	 items_per_page : '${pageFinder.pageSize}',
	    	 num_edge_entries : 1,
	    	 callback:function(page,jq){
		    	 var pageNo = page + 1;
		    	 queryPicByItemOneId(pageNo,type);  
	    	 }
	   });
  });
</script>