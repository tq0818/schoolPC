<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
 <div class="mainbackground nopadding">
<div class="m-search clear">
            <span class="fl">
                <a href="javascript:void(0);" id="TEACH_METHOD_LIVE" fType="moduleType" fValue="TEACH_METHOD_LIVE" class="bt btn btn-mini btn-default ">直播</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_FACE" fType="moduleType" fValue="TEACH_METHOD_FACE" class="bt btn btn-mini btn-default">面授</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_VIDEO" fType="moduleType" fValue="TEACH_METHOD_VIDEO" class="bt btn btn-mini btn-default">录播</a>
<!--             <a href="javascript:void(0);" id="TEACH_METHOD_REMOTE" fType="moduleType" fValue="TEACH_METHOD_REMOTE" class="bt btn btn-mini btn-default">远程</a> -->    
            </span>
            <span class="fr">
                <a href="javascript:void(0);" onclick="add();" class="btn btn-mini btn-default"><i class="iconfont">&#xe61c;</i> 新增课程单元</a>
            </span>
</div>
<input type="hidden" value="${method }" id="teachMethod"/>
 <div class="teacher-list">
<ul>
	<c:forEach items="${pageFinder.data }" var="module">
		<li>
			<table class="table">
			<col width="20%">
            <col width="20%">
            <col width="20%">
            <col width="20%">
            <col width="10%">
            <col width="10%">
				<tr>
					<td rowspan="2"><span class="b-title">${module.name }
					<c:if test="${module.delFlag==1 }">
					<div class="tips" style="color:red">(已删除)</div>
					</c:if>
					</span>
					</td>
					<td><span class="t-title">学科</span> <span class="t-content">${module.itemOneName }</span>
					</td>
					<td><span class="t-title">课程单元类型</span> <span class="t-content">${wx:dictCode2Name(module.moduleType )}</span>
					</td>
					<td><span class="t-title">课时</span> <span class="t-content">${module.totalClassHour }课时</span>
					</td>
					<c:if test="${module.delFlag!=1 }">
					<td class="text-center gray">
					<a href="javascript:;" onclick="delet(${module.id });"  class="btn btn-mini btn-default">删除</a>
					
					</td>
					<td rowspan="2" class="text-center gray"><a href="<%=rootPath %>/classModuleLesson/classes"
						class="btn btn-big btn-default arrange"  value="${module.id}">排课</a></td>
					</c:if>
				</tr>
				<tr>																			
					<td><span class="t-title">学科小类</span> <span class="t-content">${module.itemSecondName }</span>
					</td>
					<td><span class="t-title">授课方式</span> <span class="t-content">${wx:dictCode2Name(module.teachMethod )}</span>
					</td>
					<td><span class="t-title">班号数</span> <span class="t-content">${module.moduleNoNum }</span>
					</td>
					<c:if test="${module.delFlag!=1 }">
					<td class="text-center gray">
					<a href="javascript:void(0);" value="${module.id}" onclick="detail(${module.id})"
						class="btn btn-mini btn-default">详情</a></td>
					</c:if>
					<c:if test="${module.delFlag==1 }">
					<td rowspan="2" colspan="1" class="text-center gray">
					<a href="javascript:void(0);" value="${module.id}" onclick="detail(${module.id})"
						class="btn btn-big btn-default">详情</a></td>
					</c:if>
				</tr>
			</table>
		</li>
	</c:forEach>
</ul>

</div>

</div>
<div class="pages" id="page"></div>
<form action="<%=rootPath%>/classModule/toAddModule2" method="post" id="addForm">
<input type="hidden" name="teachMethod" id="method"/>
<input type="hidden" name="itemOneId" id="itemOne"/>
<input type="hidden" name="itemTwoId" id="itemTwo"/>
<input type="hidden" name="id" id="id1"/>
</form>
<form action="<%=rootPath%>/classModule/detail" method="post" id="detailForm">
	<input type="hidden" name="id" id="id"/>
</form>

<script type="text/javascript" src="<%=rootPath %>/javascripts/class/teach/live.js"></script>
<script>
$("#page").pagination('${pageFinder.rowCount}', {
	next_text : "下一页",
	prev_text : "上一页",
	link_to : "javascript:void(0)",
	current_page : '${pageFinder.pageNo}' - 1,
	num_display_entries : 8,
	items_per_page : '${pageFinder.pageSize}',
	num_edge_entries : 1,
	callback:function(page,jq){
		var pageNo = page + 1;
		search(pageNo);
	}
});

</script>