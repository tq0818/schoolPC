<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${page.data!=null&&fn:length(page.data)>0}">
        <c:forEach var="item" items="${page.data}">
            <div class="study" data-id="${item.id}">
                <div class="study-up">
                    <div class="study-img">
                        <c:if test="${item.userPic!=null&&item.userPic!=''}">
                            <img src="${imgHost}${item.userPic}" style="width: 100%;height: 100%;"/>
                        </c:if>
                    </div>
                    <div class="study-author">${item.userName}</div>
                </div>
                <div class="study-down">
                        ${item.des}
                </div>
                <div class="study-edit">
                    <div class="center">
                        <div class="left">删除</div>
                        <div class="right">编辑</div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="empty" style="height: 30px;width: 100%;color: #666;font-size: 14px;text-align: center;margin-top: 30px;">暂时没有学员心声</div>
    </c:otherwise>
</c:choose>
<script type="text/javascript">
    $(function(){
    	var pz = '${page.pageSize}',
    		pc = '${page.rowCount}';
    	$(".pagination").pagination(pc, {
	    	 next_text : "下一页",
	    	 prev_text : "上一页",
	    	 current_page : '${page.pageNo-1}',
	    	 link_to : "javascript:void(0)",
	    	 num_display_entries : 8,
	    	 items_per_page : '${page.pageSize}',
	    	 num_edge_entries : 1,
	    	 callback:function(page,jq){
		    	 var pageNo = page + 1;
		    	 loadPage(pageNo,pz);
	    	 }
	   });
        
    });
</script>