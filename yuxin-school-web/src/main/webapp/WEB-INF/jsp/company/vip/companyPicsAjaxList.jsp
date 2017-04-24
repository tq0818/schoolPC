<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String rootPath=request.getContextPath(); %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<div class="pic-list">
	<ul id="ulList" style="width: 104%;">
		<li class='add' style="width:52px;height:50px;margin-left: 27px;"><i class='iconfont' style="top:12px;font-size:1.8rem">&#xe61c;</i>
		<input type="file" name="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" id="imgData" onchange="savePic()" style="position: absolute; display: inline-block;width: 50px;height: 50px;background-color: transparent;top: 0;left: 0;border: 0;cursor: pointer;opacity: 0;"/>
		</li>
		<c:forEach items="${pageFinder.data }" var="pic" varStatus="status">
		 		<li style="width:80px;height:50px;">
					<div class='icon-box'> <input type='radio' name="pics" onchange="setImgUrl('${pic.picOriginalUrl}');"/><em><img src='${pic.picOriginalUrl}' alt='' width='100%' height='100%'/></em></div>
		 		</li>	
		</c:forEach>
	</ul>
	<ul><div><font>提示：上传自定义图标时，请选择32*32像素的png图片<font></div></ul>
</div>
<div class="pages" style="position: absolute; bottom: 0; left: 50%; transform: translate(-50%,0);">
	<ul class="pagination"></ul>
</div>
<script type="text/javascript">
  $(document).ready(function(){
	  //点击添加图片弹出添加图片的框
// 		$(".add-cover").on("click",  function() {
// 			$(".select-icon").fadeOut(20);
// 			$(".add-layer-bg").fadeIn(100);
// 			$(".upload-layer").fadeIn(100);
// 		});
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
		    	 queryPrivatePic(pageNo);  
	    	 }
	   });
  });
  
</script>