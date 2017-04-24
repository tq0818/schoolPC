<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String rootPath=request.getContextPath(); %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>

<div class="m-list clear">
	<ul class="clear" id="ulListss">
	<li class='add-class'> <a href='javascript:;' class="checkStudent operate_btn btn-sel-stu"><i class='iconfont icons'>&#xe61c;</i></a></li>
	  <c:forEach items="${pageFinder.data }" var="cousepackage" varStatus="status">
		   <li id="commodityLi${cousepackage.id }" onmouseover="Form.showSave(${cousepackage.id})" onmouseout="Form.closeSave(${cousepackage.id})">
		   	  <div class="bg">
		   		<c:choose>
		   			<c:when test="${cousepackage.publishStatus=='CLASS_STOP_SALE' }">
		   				<i class="tips" style="background-color: rgba(231,31,26,0.8);color: white;">
		   				<c:choose>
		   					<c:when test="${cousepackage.publishStatus=='CLASS_UNPUBLISHED'}">未发布</c:when>
		   					<c:when test="${cousepackage.publishStatus=='CLASS_ON_SALE'}">在售</c:when>
		   					<c:otherwise>停售</c:otherwise>
		   				</c:choose>
		   				</i>
		   			</c:when>
		   			<c:otherwise>
		   				<i class="tips"><c:choose>
		   					<c:when test="${cousepackage.publishStatus=='CLASS_UNPUBLISHED'}">未发布</c:when>
		   					<c:when test="${cousepackage.publishStatus=='CLASS_ON_SALE'}">在售</c:when>
		   					<c:otherwise>停售</c:otherwise>
		   				</c:choose></i>
		   			</c:otherwise>
		   		</c:choose>
			     <i class="add-com" id="com${cousepackage.id}" marks="${cousepackage.recommendFlag}" style="display: none;" onclick="Form.collectShop(${cousepackage.id})">${cousepackage.recommendFlag==1?'取消推荐':'加入推荐' }</i>
			    <div class="infos-pic">
			      <a href="javascript:Form.editClassType(${cousepackage.id });">
			    	<c:if test="${cousepackage.cover!=null }">
			    		<img src="${commodityPicUrl }${cousepackage.cover}" alt="">
			    	</c:if>
			    	<c:if test="${empty cousepackage.cover}">
			    		<img alt="" src="<%=rootPath %>/images/overview_demo.jpg">
			    	</c:if>
			      </a>
			    </div>
			    <div class="infos-title">
			        <h2 class="h5">
				          <c:choose>
				          	<c:when test="${fn:length(cousepackage.name)>15}">
				          		${fn:substring(cousepackage.name, 0, 15)}...
				          	</c:when>
				          	<c:otherwise>
				          		 ${cousepackage.name }
				          	</c:otherwise>
				          </c:choose>
			        </h2>
			        <div class="type" style="top:5px;">
			        	共<span class="classNums" ids="${cousepackage.id }">0</span>课程
			        </div>
			        <p class="descript" title="${cousepackage.description }">
			        	<c:if test="${fn:length(cousepackage.description)>15}">
			        		${fn:substring(cousepackage.description, 0, 15)}......
			        	</c:if>
			        	<c:if test="${fn:length(cousepackage.description)<=15}">
			        		${cousepackage.description }
			        	</c:if>
			        	<c:if test="${empty cousepackage.description }">
			        		&nbsp;&nbsp;
			        	</c:if>
			        </p>
			    </div>
			    <div class="infos-tips clear">
			        <p><span class="price">售价: ${cousepackage.realPrice }</span><span style="float:right; color: gray;font-size: 10px;">${cousepackage.buyNum }人学习</span></p>
			    </div>
			    <div class="btns">
			      <a href="javascript:Form.deleteClassPackage(${cousepackage.id });" class="btn btn-sm btn-default">删除</a>
			    	<c:if test="${cousepackage.publishStatus=='CLASS_ON_SALE'}">
			    	    <a href="javascript:Form.stopOnsale(${cousepackage.id });" class="btn btn-sm btn-default">下架</a>
			    	    <a href="javascript:Form.editClassType(${cousepackage.id });" class="btn btn-sm btn-primary">管理</a>
			    	</c:if>
			    	<c:if test="${cousepackage.publishStatus=='CLASS_STOP_SALE'}">
			    	    <a href="javascript:Form.classTypeOnsale(${cousepackage.id });" class="btn btn-sm btn-default">上架</a>
			    		<a href="javascript:Form.editClassType(${cousepackage.id });" class="btn btn-sm btn-primary">管理</a>
			    	</c:if>
			    	<c:if test="${cousepackage.publishStatus=='CLASS_UNPUBLISHED'}">
			    	    <a href="javascript:Form.classTypeOnsale(${cousepackage.id });" class="btn btn-sm btn-default">上架</a>
			    		<a href="javascript:Form.editClassType(${cousepackage.id });" class="btn btn-sm btn-primary">管理</a>
			    	</c:if>
			    </div>
			  </div>
			  <div class="bg-layer"></div>
			</li>
		</c:forEach>
	</ul>
</div>
<div class="pages">
	<ul class="pagination"></ul>
 </div>
 <input type="hidden" id="searchName" value="${searchName }"/>
 <input type="hidden" id="coursePackageCode" value="${packageCode }"/>
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
		    	 if($("#searchName").val()){
		    		 Form.queryCommodityByName(pageNo); 
		    	 }else{
		    		 Form.queryAllCommdityByItem(pageNo);
		    	 }
	    	 }
	   });
	  $("#ulListss").find(".classNums").each(function(){
		  var $this=$(this);
		  var id=$(this).attr("ids");
		  $.ajax({
				url : rootPath+"/classPackage/getCourseNum",
				type : "post",
				data : {"id":id},
				dataType : "json",
				success : function(result) {
					$this.text(result);
				}
			});
	  });
	 //点击添加课程包
	 $(".add-class").on('click',function(){
		 var code;
		 $("#firstTypeList").find("a.btn").each(function(){
			 var firstCode=$(this).attr("code");
			 if(firstCode){
				code=firstCode;
			}
		});
		if(code && code!=""){
			 window.location.href=rootPath+"/classPackage/manageBaseInfo/add"; 
		}else{
			$.msg("请先添加课程包分类");
		}
		
	 });
  });
</script>