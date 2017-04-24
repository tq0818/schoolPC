<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<div class="m-list clear">
	<ul class="clear" id="ulListss">
	  <c:forEach items="${pageFinder.data }" var="item">
		  <li>
		  		<c:if test="${item.publishStatus == 1}">
					<i class="tips">已发布</i>
				</c:if>
				<c:if test="${item.publishStatus == 0}">
					<i class="tips">未发布</i>
				</c:if>
				<c:if test="${item.publishStatus == -1}">
					<i class="tips">已下架</i>
				</c:if>
		   		
			    <div class="infos-pic">
    			    <c:if test="${empty item.cover}">
                        <img alt="" src="<%=rootPath %>/images/overview_demo.jpg" onclick="location.href='<%=rootPath%>/liveOpenCourse/toAddOpenClass/${item.id}/0'">
    			    </c:if>
    			    <c:if test="${!empty item.cover}">
                       <img alt="" src="http://${imgUrl}/${item.cover}" onclick="location.href='<%=rootPath%>/liveOpenCourse/toAddOpenClass/${item.id}/0'">
    			    </c:if>
			    </div>
			    <div class="infos-title">
			        <h2 class="h5" style="width: 100%;line-height: 1.4;"><a href="<%=rootPath%>/liveOpenCourse/toAddOpenClass/${item.id}/0">课程名称：${item.openCourseName}</a></h2>
			        <h2 class="h5" style="width: 100%;line-height: 1.4;"><a href="javascript:void(0)">讲师：${item.teacherName}</a></h2>
			        <h2 class="h5" style="width: 100%;line-height: 1.4;">
				        <a href="javascript:void(0)">时间：
				            <fmt:formatDate value="${item.startOpenData}"/>&nbsp;&nbsp;&nbsp;${item.startTime}-${item.endTime}
				        </a>
			        </h2>
			    </div>
			    <div class="btns" style="padding:0px;">
			    <a href="javascript:void(0)" class="btn btn-sm btn-default del" couId="${item.id}">删除</a>
			    <c:if test="${item.publishStatus == 1}">
				<a href="javascript:;" class="btn btn-sm btn-default on" couId="${item.id}">下架</a>
				</c:if>
				<c:if test="${item.publishStatus == -1}">
				<a href="javascript:;" class="btn btn-sm btn-default on" couId="${item.id}" time="${item.startOpenData }">上架</a>
				</c:if>
				<c:if test="${item.publishStatus == 0}">
				<a href="javascript:;" class="btn btn-sm btn-default on" couId="${item.id}" time="${item.startOpenData }">发布</a>
				</c:if>
			    <a href="<%=rootPath%>/liveOpenCourse/toAddOpenClass/${item.id}/0" class="btn btn-sm btn-primary">编辑</a>
			    <a href="javascript:;" class="btn btn-sm btn-primary fz" couId="${item.id}" 
			    	fzTime="<fmt:formatDate value="${item.startOpenData}" pattern="yyyy-MM-dd"/> ${item.startTime}"
			    	feTime="<fmt:formatDate value="${item.endOpenData}" pattern="yyyy-MM-dd"/> ${item.endTime}" 
			    	data-barrage="${item.barrage }" data-modetype="${item.modetype }" data-ls="${item.liveServiceProvider }">复制</a>
			    </div>
			</li>
		</c:forEach>
	</ul>
</div>
<div class="pages">
	<ul class="pagination"></ul>
 </div>
 <input type="hidden" id="itemOneId" name="itemOneId" value="${itemOneId }"/>
 <input type="hidden" id="searchName" value="${searchName }"/>
 <script type="text/javascript">
 function resizeLayout(){
	 var w=$(".upload-layer").width();
	 var h=$(".upload-layer").height();
	 var ww=$(window).width();
	 var hh=$(window).height();
	 var left =(ww-w)/2;
	 var top =(hh-h)/2;
	 $(".upload-layer").css({"left":left+"px","top":top+"px"});
	 
 }
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
		    	var oneId = $("#oneId").val();
		    	var twoId = $("#twoId").val();
		    	var publishStatus = $("#publishStatus").val();
		    	var time = $("#choiceTime").val();
		    	Forms.ajaxInfo(pageNo, oneId, twoId, publishStatus,time);
	    	 }
	   });
  });
</script>