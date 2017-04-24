<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
 <div class="mainbackground nopadding">
<div class="m-search clear">
            <span class="fl">
                <a href="javascript:void(0);" id="TEACH_METHOD_LIVE" fType="moduleType" fValue="TEACH_METHOD_LIVE" class="bt btn btn-mini btn-default ">直播</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_FACE" fType="moduleType" fValue="TEACH_METHOD_FACE" class="bt btn btn-mini btn-default">面授</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_VIDEO" fType="moduleType" fValue="TEACH_METHOD_VIDEO" class="bt btn btn-mini btn-default">录播</a>
  <!--            <a href="javascript:void(0);" id="TEACH_METHOD_REMOTE" fType="moduleType" fValue="TEACH_METHOD_REMOTE" class="bt btn btn-mini btn-default">远程</a>  -->   
            </span>
            <span class="fr">
                <a href="javascript:void(0);" onclick="add();" class="btn btn-mini btn-default"><i class="iconfont">&#xe61c;</i> 新增课程单元</a>
            </span>
</div>
<input type="hidden" value="${method }" id="teachMethod"/>
 <div class="teacher-list">
            <ul>
               <c:forEach items="${pageFinder.data }" var="remote">
                <li>
                    <table class="table">
                    <col width="25%">
                        <col width="25%">
                        <col width="30%">
                        <col width="20%">
                        
                        <tr>
                            <td>
                                <span class="t-title">学科</span>
                                <span class="t-content">${remote.itemOneName }</span>
                            </td>
                            <td>
                                <span class="t-title">学科小类</span>
                                <span class="t-content">${remote.itemSecondName }</span>
                            </td>
                            <td>
                                <span class="t-title">名称</span>
                                <span class="t-content">${remote.name }</span>
                            </td>
                            <td rowspan="2" colspan="2" class="text-center gray">
                                <a href="javascript:void(0);" onclick="edit(${remote.id});" class="btn btn-big btn-default">编辑</a>
                                <a href="javascript:void(0);" onclick="detail(${remote.id});" class="btn btn-big btn-default">详情</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="t-title">授课方式</span>
                                <span class="t-content">远程教育</span>
                            </td>
                             <td>
                                <span class="t-title">专业</span>
                                <span class="t-content">${remote.major }</span>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="pages" id="page"></div>
</div>
<form action="<%=rootPath%>/classModule/toAddModule2" method="post" id="addForm">
<input type="hidden" name="teachMethod" id="method"/>
<input type="hidden" name="itemOneId" id="itemOne"/>
<input type="hidden" name="itemTwoId" id="itemTwo"/>
</form>
<form action="<%=rootPath%>/classModule/remoteDetail" method="post" id="detailForm">
<input type="hidden" name="id" id="id" />
</form>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/teach/remote.js"></script>
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