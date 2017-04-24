<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String rootPath=request.getContextPath(); %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
 <table class="table table-center" id="tableList">
    <tr>
        <th>#</th>
        <th>姓名</th>
        <th>用户名</th>
        <th>手机号</th>
        <th>学科</th>
        <th>学科小类</th>
        <th>课程</th>
        <th>是否代报</th>
        <th>是否报考完成</th>
        <th>资料齐全</th>
        <th>操作人</th>
    </tr>            
<c:forEach items="${pageFinder.data }" var="stu">
	<tr>
  		<td><input type="checkbox" value="${stu.pid }"></td>
        <td>${stu.name }</td>
        <td>${stu.username }</td>
        <td>${stu.mobile }</td>
        <td>${stu.itemOneName }</td>
        <td>${stu.itemSecondName }</td>
        <td>${stu.classTypeName }</td>
        <td>${stu.isAgent==1?'是':'否' }</td>
        <td id="completeStatus${stu.pid }">${stu.isAgentComplete==1?'是':'否' }</td>
        <td>${stu.isAgentMaterialComplete==1?'是':'否' }</td>
        <td>${stu.updator }</td>               
     </tr>                      
</c:forEach>
 <c:if test="${empty pageFinder.data}">
			<tr style="background-color: #f6f6f6;">
				<th colspan="11">暂无数据</th>
			</tr>
	</c:if>
</table>
 <div class="pages">
	<ul class="pagination"></ul>
 </div>
 <form id="searchForm" method="post">
 <input type="hidden" id="itemOneId" name="itemOneId" value="${search.itemOneId }"/>
 <input type="hidden" id="itemSecondId" name="itemSecondId" value="${search.itemSecondId }"/>
 <input type="hidden" id="examId" name="examTermId" value="${search.examTermId }"/>
 <input type="hidden" id="isComplete" name="isAgentMaterialComplete" value="${search.isAgentMaterialComplete }"/>
 <input type="hidden" id="mobile" name="mobile" value="${search.mobile }"/>
 </form>
 <input type="hidden" id="stuMobiles" value="${stuMobiles }"/>
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
		    	 if($("#stuMobiles").val()){
		    		 Form.queryStuListByMobile(pageNo);
		    	 }else{
		    		 Form.queryStudentList(pageNo,'${itemOneId}');
		    	 }
	    	 }
	   });
  });
</script>