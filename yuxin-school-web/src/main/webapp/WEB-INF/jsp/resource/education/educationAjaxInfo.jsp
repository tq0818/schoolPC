<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
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
				var pageNo = page + 1;
				var teaType = $("#teaType").val();
				loadInfo(teaType, null, null, pageNo);
			}
		});

	});
</script>
<style type="text/css">
.zanwu {
    text-align: center;
    color: #666;
    font-size: 14px;
    position: absolute;
    left: 50%;
    top: 50%;
    z-index: 3;
   width: 100%;
    -webkit-transform: translate(-50%,-50%);
transform: translate(-50%,-50%);
	-moz-transform: translate(-50%,-50%);
-o-transform: translate(-50%,-50%);
}
</style>
<c:if test="${empty pageFinder.data }">
	<div class="zanwu">
		暂无信息
	</div>
</c:if>
<c:if test="${!empty pageFinder.data }">
	<c:forEach items="${pageFinder.data}" var="tc">
		<li class="teacher clear">
			<div class="r-list-title">
				<span class="r-name">${tc.name}</span>
				<div class="r-more">
					<a href="javascript:;" class="moreInfo">更多</a>
				</div>
			</div>
			<div class="r-list-content clear">
				<p class="c">
					<span class="c-title">姓名</span> <span class="c-content"> <input
						type="text" class="readonly" value="${tc.name}" readonly>
					</span>
				</p>
				<p class="c">
					<span class="c-title">手机号</span> <span class="c-content"> <input
						type="text" class="readonly" value="${tc.mobile}" readonly>
					</span>
				</p>
				<p class="c">
					<span class="c-title">类型</span> <span class="c-content"> <input
						type="text" class="readonly"
						value="<c:forEach items="${dictList}" var="dict"><c:if test="${dict.itemCode== tc.teacherType}">${dict.itemValue }</c:if></c:forEach>"
<%-- 						${wx:dictCode2Name(tc.teacherType)}"  --%>
						readonly>
					</span>
				</p>
			</div>
		</li>
		<li class="teacher clear detail">
			<div class="r-list-title">
				<span class="r-name">${tc.name}</span>
				<div class="r-more">
					<a href="javascript:;" class="pack">收起</a>
				</div>
			</div>
			<div class="r-list-content clear">
				<div class="baseInfo">
					<div class="r-list-content-title">
						<span class="r-list-content-title-txt">基本信息</span> <a
							href="<%=rootPath%>/sysConfigTeacher/addEducation/${tc.id}/${tc.teacherType}"
							class="btn btn-link btn-mini editBaseInfo">编辑</a> <a
							href="javascript:void(0)" style="margin-right: 20px;"
							class="btn btn-link btn-mini delTeacher" teacherId="${tc.id}">删除</a>
					</div>

					<p class="c">
						<span class="c-title">姓名</span> <span class="c-content"> <input
							type="text" class="readonly" value="${tc.name}" readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">性别</span> <span class="c-content"> <input
							type="text" class="readonly" value="<c:forEach items="${dictList}" var="dict"><c:if test="${dict.itemCode== tc.sex}">${dict.itemValue }</c:if></c:forEach>"
<%-- 							${wx:dictCode2Name(tc.sex)}" --%>
							readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">出生日期</span> <span class="c-content">

							<input type="text" class="readonly"
							value="<fmt:formatDate value="${tc.birthday}"/>" readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">家庭住址</span> <span class="c-content">
							<input type="text" class="readonly" value="${tc.homeAddress}"
							readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">现居住址</span> <span class="c-content">
							<input type="text" class="readonly" value="${tc.address}"
							readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">最高学历</span> <span class="c-content">
							<input type="text" class="readonly"
							value="<c:forEach items="${dictList}" var="dict"><c:if test="${dict.itemCode== tc.educationCode}">${dict.itemValue }</c:if></c:forEach>"
<%-- 							${wx:dictCode2Name(tc.educationCode)}"  --%>
							readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">身份证号码</span> <span class="c-content">
							<input type="text" class="readonly" value="${tc.idNumber}"
							readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">个人简介</span> <span class="c-content">
							<textarea rows="3" cols="30" class="readonly" readonly="readonly">${tc.resume}</textarea>
						</span>
					</p>
				</div>
			</div>

			<div class="r-list-content clear con">
				<div class="contactInfo">
					<div class="r-list-content-title">
						<span class="r-list-content-title-txt">联系信息</span>
					</div>
					<p class="c">
						<span class="c-title">手机号</span> <span class="c-content"> <input
							type="text" class="readonly" value="${tc.mobile}" readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">家庭电话</span> <span class="c-content">
							<input type="text" class="readonly" value="${tc.homePhone}"
							readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">办公电话</span> <span class="c-content">
							<input type="text" class="readonly" value="${tc.workPhone}"
							readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">紧急联系人</span> <span class="c-content">
							<input type="text" class="readonly"
							value="${tc.emergencyContactName}" readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">联系人电话</span> <span class="c-content">
							<input type="text" class="readonly"
							value="${tc.emergencyContactPhone}" readonly>
						</span>
					</p>
					<p class="c">
						<span class="c-title">邮箱</span> <span class="c-content"> <input
							type="text" class="readonly" value="${tc.email}" readonly>
						</span>
					</p>

				</div>
			</div>
		</li>
	</c:forEach>
</c:if>
