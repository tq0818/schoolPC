<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${pageFinder.data}" var="vo">
	<tr>
        <td>
        	<div class="operate_vedio_item clear L-chap pview" rid="${vo.id}" rtype="${vo.resourceType}" style="cursor: pointer;">
                <c:if test="${vo.resourceType == 'video'}">
                    <p class="L-leftL">
                        <i class="iconfont L-icon">&#xe627;</i>
                    </p>
                </c:if>
                <c:if test="${vo.resourceType == 'swf'}">
                    <p class="L-leftL">
                        <i class="iconfont L-icon">&#xe6cb;</i>
                    </p>
                </c:if>
                <c:if test="${vo.resourceType == 'mp3'}">
                    <p class="L-leftL">
                        <i class="iconfont L-icon">&#xe6cc;</i>
                    </p>
                </c:if>
                <c:if test="${vo.resourceType == 'ppt' or vo.resourceType == 'pptx'}">
                    <p class="L-leftL">
                        <i class="iconfont L-icon">&#xe6cd;</i>
                    </p>
                </c:if>
                <c:if test="${vo.resourceType == 'doc' or vo.resourceType == 'docx' or vo.resourceType == 'xls' or vo.resourceType == 'xlsx' or vo.resourceType == 'pdf'}">
                    <p class="L-leftL">
                        <i class="iconfont L-icon">&#xe6ce;</i>
                    </p>
                </c:if>
                <p  class="L-LF">
                    <h2 alt="${vo.videoName}">${vo.sortName}</h2>
                    <p>
                        <span>${vo.creatorName}</span>
                        <span>上传</span>
                    </p>
                </p>
            </div>
        </td>
        <td>
        	<p><span class="sp_infor" name="itemId" itemOneId = "${vo.itemOneId}" itemSecondId = "${vo.itemSecondId}">${vo.itemName}</span></p>
        </td>
        <td>
             <c:if test="${vo.resourceType == 'video'}">
               <p><span class="sp_infor"><fmt:formatDate value="${vo.createTime}"  type="both" /></span></p>
            </c:if>
            <c:if test="${vo.resourceType != 'video'}">
        	   <p><span class="sp_infor"><fmt:formatDate value="${vo.createTime}"  pattern="yyyy-MM-dd"/></span></p>
            </c:if>
        </td>
        <td>
            <p><span class="sp_infor" videoTag = "${vo.videoTag}">${vo.videoTag}</span></p>
        </td>
        <td>
            <p><span class="sp_infor">${vo.resourceType}</span></p>
        </td>
        <td>
        	<p>
        		<c:if test="${vo.resourceType == 'video' }">
                <c:choose>
                    <c:when test="${vo.videoStatus == '0'}">
                        <span class="sp_infor">正常</span>
                    </c:when>
                    <c:when test="${vo.videoStatus == 'VIDEO_PROCESS_DELETE'}">
                        <span class="sp_infor" style="color: red;">
                        <c:forEach items="${dictList }"var ="dict"><c:if test="${dict.itemCode==vo.videoStatus }">${dict.itemValue }</c:if></c:forEach>
<%--                         	${wx:dictCode2Name(vo.videoStatus)} --%>
                    	</span>
                    </c:when>
                    <c:when test="${vo.videoStatus != 'VIDEO_PROCESS_DELETE'}">
                        <span class="sp_infor">
                        	 <c:forEach items="${dictList }"var ="dict"><c:if test="${dict.itemCode==vo.videoStatus }">${dict.itemValue }</c:if></c:forEach>
<%--                         	${wx:dictCode2Name(vo.videoStatus)} --%>
                    	</span>
                    </c:when>
                </c:choose>
        		</c:if>
        		<c:if test="${vo.resourceType != 'video' }">
        		<c:choose>
        			<c:when test="${vo.resourceType == 'swf' || vo.resourceType == 'mp3'
        				|| vo.resourceType == 'pdf'}">
        				<c:if test="${vo.videoStatus == '0'}">正常</c:if>
        			</c:when>
        			<c:otherwise>
        			<c:if test="${vo.videoStatus == '0'}">
        				<c:if test="${vo.convertType == 0 }">等待处理</c:if>
        				<c:if test="${vo.convertType == 1 }">正在处理</c:if>
        				<c:if test="${empty vo.convertType ||
        					 vo.convertType == 2 }">正常</c:if>
        				<c:if test="${vo.convertType == 3 || 
        					vo.convertType == 4}">处理异常</c:if>
        			</c:if>
        			</c:otherwise>
        		</c:choose>
        		</c:if>
        	</p>
        </td>
        <td style="white-space: nowrap;">
        	<p><span class="sp_infor" style="white-space: nowrap;">
                <c:choose>
        			<c:when test="${vo.storageType == 'VIDEO_STORAGE_TYPE_CC' }">
        				视频
        			</c:when>
                    <c:when test="${vo.storageType == 'VIDEO_STORAGE_TYPE_LETV' }">
                                                                        视频
                    </c:when>
                    <c:when test="${vo.storageType == 'VIDEO_STORAGE_TYPE_QN' }">
                                                                        视频
                    </c:when>
                    <c:when test="${vo.storageType == 'VIDEO_STORAGE_TYPE_YK' }">
                                                                         优酷
                    </c:when>
                    <c:when test="${vo.storageType == 'VIDEO_STORAGE_TYPE_TD' }">
                                                                         土豆
                    </c:when>
                    <c:when test="${vo.storageType == 'VIDEO_STORAGE_TYPE_WY' }">
                                                                         网易公开课
                    </c:when>
                    <c:when test="${vo.storageType == 'VIDEO_STORAGE_TYPE_SCORM' }">
                        scorm课件
                    </c:when>
        			<c:otherwise>
        				资源
        			</c:otherwise>
        		</c:choose>
        	</span></p>
        </td>
        <td rtype="${vo.resourceType}">
        	<a class="btn btn-mini btn-default edit" href="javascript:;" videoName="${vo.videoName}" videoId="${vo.id}">编辑</a>
            <c:if test="${vo.resourceType == 'video'}">
                <c:if test="${vo.videoStatus == 'VIDEO_PROCESS_DELETE'}">
                    <a class="btn btn-mini btn-default disable" href="javascript:;" videoId="${vo.id}">启用</a>
                </c:if>
                <c:if test="${vo.videoStatus != 'VIDEO_PROCESS_DELETE'}">
                    <a class="btn btn-mini btn-default disable" href="javascript:;" videoId="${vo.id}">禁用</a>
                </c:if>
                <input type="hidden" value="${vo.videoStatus}"/>
            </c:if>
            <a class="btn btn-mini btn-default delete" href="javascript:;" videoId="${vo.id}" ccid="${vo.videoCcId}" stype="${vo.storageType}">删除</a>
        </td>
    </tr>
</c:forEach>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".pagination").html("");
		$(".pagination").pagination('${pageFinder.rowCount}', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : '${pageFinder.pageNo-1}',
			link_to : "javascript:void(0)",
			num_display_entries : 8,
			items_per_page : '${pageFinder.pageSize}',
			num_edge_entries : 1,
			callback : function(page, jq) {
				var oneId = $("#itemOneId").val();
				var secId = $(".secItem.active").attr("itemid");
				var vStatus = $("#vStatus").val();
				var ccName = $(".ccName").val();
				var ccTag = $(".ccTag").val();
				var oldStartTime = $("#start").val();
				var oldOverTime = $("#end").val();
				var sortBy = $("#sortBy").val();
				loadVideoInfo(oneId,secId,oldStartTime,oldOverTime,vStatus,ccTag,ccName,sortBy);
			}
		});
	});
</script>