<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String rootPath=request.getContextPath(); %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
		<c:forEach items="${pageFinder.data }" var="pic" varStatus="status">
			 		<div class='icon-box'> <input type='radio' name="pics" onchange="setImgUrl('${pic.picOriginalUrl}');"/><em><img src='${pic.picOriginalUrl}' alt='' width='100%' height='100%'/></em></div>
		</c:forEach>
<div class="pages" style="position: absolute; bottom: 0; left: 50%; transform: translate(-50%,0);">
	<ul class="pagination"></ul>
</div>
<script type="text/javascript">
  $(document).ready(function(){
	  var type='${itemOneId}';
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
		    	 queryPublicPic(pageNo);  
	    	 }
	   });
  });
</script>