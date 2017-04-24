<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
    <style type="text/css">
.zanwu{
	text-align: center;
    color: #666;
    font-size: 14px;
    position: absolute;
    left: 50%;
    top: 50%;
    z-index: 3;
    width: 100%;
    -webkit-transform: translate(-50%,-50%);
	}
</style>
<c:if test="${empty scrList }">
	<div class="zanwu">
		暂无信息
	</div>
</c:if>
<c:if test="${!empty scrList }">
<c:forEach var="r" items="${scrList }">
		<div class="sc-infos clear">
		    <div class="sc-infos-left">
		        <p class="c">
		            <span class="sc-c-title">所属校区</span>
		            <span class="sc-c-content">${scc.campusName }</span>
		        </p>
		        <p class="c">
		            <span class="sc-c-title">教室名称</span>
		            <span class="sc-c-content">${r.roomName }</span>
		        </p>
		        <p class="c">
		            <span class="sc-c-title">租用时段</span>
		            <span class="sc-c-content">
		            	<c:if test="${r.rentScope == 'CLASSROOM_WEEKEND' }">周末班</c:if>
		            	<c:if test="${r.rentScope == 'CLASSROOM_WORKDAY' }">工作日</c:if>
		            	<c:if test="${r.rentScope == 'CLASSROOM_UNLIMITED' }">不限</c:if>
		            </span>
		        </p>
		        <p class="c">
		            <span class="sc-c-title">属性</span>
		            <span class="sc-c-content">
		            	<c:if test="${r.roomAttrCode == 'CLASSROOM_FIXED' }">固定教室</c:if>
		            	<c:if test="${r.roomAttrCode == 'CLASSROOM_TEMP' }">临租教室</c:if>
		            </span>
		        </p>
		        <p class="c">
		            <span class="sc-c-title">类别</span>
		            <span class="sc-c-content">
		            	<c:if test="${r.roomTypeCode == 'CLASSROOM_GENERAL' }">普通教室</c:if>
		            	<c:if test="${r.roomTypeCode == 'CLASSROOM_MACHINE' }">机房</c:if>
		            </span>
		        </p>
		        <p class="c">
		            <span class="sc-c-title">容量</span>
		            <span class="sc-c-content">
		            	<c:if test="${r.seatNumCode == '1_TO_49' }">1-49人</c:if>
		            	<c:if test="${r.seatNumCode == '50_TO_99' }">50-99人</c:if>
		            	<c:if test="${r.seatNumCode == '100_TO_199' }">100-199人</c:if>
		            	<c:if test="${r.seatNumCode == '200_TO_299' }">200-299人</c:if>
		            	<c:if test="${r.seatNumCode == 'OVER_300' }">300人以上</c:if>
		            </span>
		        </p>
		        <p class="c">
		            <span class="sc-c-title">教室级别</span>
		            <span class="sc-c-content">
		            	<c:if test="${r.roomLevelCode == 'CLASSROOM_ONE_STAR' }">1星</c:if>
		            	<c:if test="${r.roomLevelCode == 'CLASSROOM_TWO_STAR' }">2星</c:if>
		            	<c:if test="${r.roomLevelCode == 'CLASSROOM_THREE_STAR' }">3星</c:if>
		            </span>
		        </p>
		        <p class="c">
		            <span class="sc-c-title">教室状态</span>
		            <span class="sc-c-content">
		            	<c:if test="${r.status == 1 }"><span>已启用</span></c:if>
		            	<c:if test="${r.status == 0 }"><span style="color:red">已停用</span></c:if>
		            </span>
		        </p>
		        <p class="f">
		            <span class="sc-c-title">地址</span>
		            <span class="sc-c-content">${r.address }</span>
		        </p>
		        <p class="f">
		            <span class="sc-c-title">公交线路</span>
		            <span class="sc-c-content">${r.busLine }</span>
		        </p>
		        <p class="f">
		            <span class="sc-c-title">备注</span>
		            <span class="sc-c-content">${r.remark }</span>
		        </p>
		    </div>
		    <div class="sc-infos-right">
		        <a href="javascript:;" class="btn btn-sm btn-block btn-default btn-status" data-id="${r.id }" data-status="${r.status }">
		        	<c:if test="${r.status == 1 }">停用</c:if>
		        	<c:if test="${r.status == 0 }">启用</c:if>
		        </a>
		        <a href="javascript:;" class="btn btn-sm btn-block btn-default btn-edit" data-id="${r.id }">编辑</a>
		    </div>
		</div>
    </c:forEach>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/resource/classroom/selClassroomAjax.js"></script>
</c:if>
