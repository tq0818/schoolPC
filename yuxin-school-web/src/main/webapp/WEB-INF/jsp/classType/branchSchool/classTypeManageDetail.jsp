<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:choose>
	<c:when  test="${not empty msgPage.data }">
		<c:forEach items="${msgPage.data }" var="m" varStatus="status">
			<tr>
                <td><input type="checkbox" class="signUpMany" value="${m.id }"></td>
                <td>${m.name }</td>
                <td>${m.schoolName }</td>
                <td>${m.itemOneName }</td>
                <td>${m.itemSecondName }</td>
                <td>${m.itemThirdName }</td>
                <td>
                	<c:choose>
                		<c:when test="${m.cddsStatus eq '1'}">已上架</c:when>
                		<c:otherwise>已下架</c:otherwise>
                	</c:choose>
                </td>
                <td>
                	<fmt:formatDate value="${m.createTime }" pattern="yyyy-MM-dd"/>
                </td>
                <td  class="slink">
                    <c:choose>
                		<c:when test="${m.cddsStatus eq '1'}"> 
                			<a class="saleOnOrStop" data_id="${m.id }" data_type='1' onclick="saleOnOrStop(this)";  href="javascript:void(0);">下架</a>|
                		</c:when>
                		<c:otherwise>
                			 <a class="saleOnOrStop" data_id="${m.id }" data_type='0' onclick="saleOnOrStop(this)";  href="javascript:void(0);">上架</a>|
                		</c:otherwise>
                	</c:choose>
                	<c:choose>
                    	<c:when test="${m.liveFlag eq '1' }">
                    		<a class="studentDetail" href="<%=rootPath %>/branchSchool/classBaseInfo/${m.id }/live">课程详情</a>|
                    	</c:when>
                    	<c:when test="${m.videoFlag eq '1' }">
                    		<a class="studentDetail" href="<%=rootPath %>/branchSchool/classBaseInfo/${m.id }/video">课程详情</a>|
                    	</c:when>
                    	<c:otherwise>
                    		<a class="studentDetail" href="<%=rootPath %>/branchSchool/classBaseInfo/${m.id }/other">课程详情</a>|
                    	</c:otherwise>
                    	</c:choose>
                    <a class="more" href="javascript:void(0);"> 管理  </a>
                    <c:choose>
                    	<c:when test="${m.liveFlag eq '1' }">
                    		<ul class="none box openCourseManage" style="width: 120px;">
		                        <li mark="/branchSchool/editClassBaseInfo/${m.id }/live"><a class=""  href="javascript:void(0);">基本信息</a></li>
		                        <li mark="/branchSchool/editliveOrface/${m.id }/live"><a class=""  href="javascript:void(0);">排课表</a></li>
		                        <li mark="/branchSchool/editCourseDetail/${m.id }/live"><a class=""  href="javascript:void(0);">课程详情</a></li>
		                        <li mark="/branchSchool/studentList/${m.id }/live"><a class=""  href="javascript:void(0);">学员管理</a></li>
		                        <li mark="/branchSchool/classesResource/${m.id }/live"><a class=""  href="javascript:void(0);">课程资料</a></li>
		                        <li mark="/branchSchool/companyLiveStaticDetailList/${m.id }/live"><a class=""  href="javascript:void(0);">直播上课统计</a></li>
		                    </ul>
                    	</c:when>
                    	<c:when test="${m.videoFlag eq '1' }">
                    		<ul class="none box openCourseManage" style="width: 120px;">
		                        <li mark="/branchSchool/editClassBaseInfo/${m.id }/video"><a class=""  href="javascript:void(0);">基本信息</a></li>
		                        <li mark="/branchSchool/editClassTypeVideo/${m.id }/video"><a class=""  href="javascript:void(0);">视频课</a></li>
		                        <li mark="/branchSchool/editCourseDetail/${m.id }/video"><a class=""  href="javascript:void(0);">课程详情</a></li>
		                        <li mark="/branchSchool/studentList/${m.id }/video"><a class=""  href="javascript:void(0);">学员管理</a></li>
		                        <li mark="/branchSchool/classesResource/${m.id }/video"><a class=""  href="javascript:void(0);">课程资料</a></li>
		                        <li mark="/branchSchool/videoDetail/${m.id }/video"><a class=""  href="javascript:void(0);">点播详情统计</a></li>
		                    </ul>
                    	</c:when>
                    </c:choose>
                </td>
            </tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
         	<td colspan="9">暂无课程数据</td>
        </tr>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	$(function(){
		$('.checkboxAll').prop('checked',false);
		$("#rowCount").val("${msgPage.rowCount }");
		$("#pageNo").val("${msgPage.pageNo }");
		$("#pageSize").val("${msgPage.pageSize }");
		$(".pagination").html("");
		$(".pagination").pagination($("#rowCount").val(), {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ($("#pageNo").val() - 1),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : $("#pageSize").val(),
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				classTypeDetail(pageNo);
			}
		});
		
		//点击菜单列表
		$(".openCourseManage").on('click','li',function(){
			var mark=$(this).attr("mark");
			window.location.href=rootPath+mark;
		});
	});
	
</script>