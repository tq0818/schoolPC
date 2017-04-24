<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String rootPath=request.getContextPath(); %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
 <table class="table table-center" id="tContent">
      <tr>
	     <th>课程</th>
         <th>考期</th>
         <th>报名总费用</th>
         <th>已结费用</th>
         <th>已结比例</th>
         <th>结费状态</th>
         <th>操作</th>
      </tr>
      <c:forEach items="${pageFinder.data }" var="remoteVo">
	     <tr>
             <td>${remoteVo.classTypeName }</td>
             <td>${remoteVo.examTermName }</td>
             <td>${remoteVo.totalFee }</td>
             <td>${remoteVo.hasPayFee }</td>
             <td>${remoteVo.payoffPercent }</td>
             <td class="sts">
             	<c:if test="${empty remoteVo.payoffStatus }">
             		未结费
             	</c:if>
             	<c:if test="${remoteVo.payoffStatus==1 }">
             		已结费
             	</c:if>
             	<c:if test="${remoteVo.payoffStatus==0 }">
             		部分结费
             	</c:if>
             </td>
             <td>
             	 <c:if test="${remoteVo.payoffStatus!=1 }">
             		<a href="<%=rootPath %>/fee/queryRemoteById/${remoteVo.payId}" class="btn btn-mini btn-success">结费</a>
             	 </c:if>
                 <a href="javascript:Form.queryDetailList(${remoteVo.id });" class="btn btn-mini btn-success checkStudent operate_btn btn-sel-stu">详情</a>
             </td>
         </tr>
	</c:forEach>
	 <c:if test="${empty pageFinder.data}">
			<tr style="background-color: #f6f6f6;">
				<th colspan="8">暂无数据</th>
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
		    	 Form.queryStuLongList(pageNo);
	    	 }
	   });
	 	 /*点击已招学员-弹出已招学员列表*/
		$(".checkStudent").click(function(){
			$(".checkStudentTc").showTc();				
		})
  });
</script>