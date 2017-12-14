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
                		<c:when test="${m.publishStatus eq 'CLASS_UNPUBLISHED'}">未上架</c:when>
                		<c:when test="${m.publishStatus eq 'CLASS_ON_SALE'}">招生中</c:when>
                		<c:when test="${m.publishStatus eq 'CLASS_STOP_SALE'}">已下架</c:when>
                	</c:choose>
                </td>
                <td>
                	<fmt:formatDate value="${m.createTime }" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                    <c:choose>
                		<c:when test="${m.publishStatus eq 'CLASS_UNPUBLISHED' or m.publishStatus eq 'CLASS_STOP_SALE' }"> 
                			<a  class="saleOnOrStop" data_id="${m.id }" data_type='0' onclick="saleOnOrStop(this);"  href="javascript:void(0);">上架</a>|
                		</c:when>
                		<c:when test="${m.publishStatus eq 'CLASS_ON_SALE'}"> 
                			<a  class="saleOnOrStop" data_id="${m.id }" data_type='1'  onclick="saleOnOrStop(this);" href="javascript:void(0);">下架</a>|
                		</c:when>
                	</c:choose>
                    <a class="studentDetail" href="<%=rootPath %>/otherSchool/classBaseInfo/${m.id }/live">课程详情</a>|
                    <a class="more" href="javascript:void(0);"> 管理  </a>
               		<ul class="none box openCourseManage" style="">
                     <li class="subentry" mark="/otherSchool/editClassBaseInfo/${m.id }/live"><a class=""  href="javascript:void(0);">基本信息</a></li>
                     <li class="subentry" mark="/otherSchool/editliveOrface/${m.id }/live"><a class=""  href="javascript:void(0);">排课表</a></li>
                     <li class="subentry" mark="/otherSchool/editCourseDetail/${m.id }/live"><a class=""  href="javascript:void(0);">课程详情</a></li>
                     <li class="subentry" mark="/otherSchool/studentList/${m.id }/live"><a class=""  href="javascript:void(0);">学员管理</a></li>
                     <li class="subentry" mark="/otherSchool/classesResource/${m.id }/live"><a class=""  href="javascript:void(0);">课程资料</a></li>
                     <li class="subentry" mark="/otherSchool/companyLiveStaticDetailList/${m.id }/live"><a class=""  href="javascript:void(0);">直播上课统计</a></li>
                 </ul>
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
		$(".openCourseManage").on('click','li.subentry',function(){
			var mark=$(this).attr("mark");
			window.location.href=rootPath+mark;
		});
	});
	
</script>