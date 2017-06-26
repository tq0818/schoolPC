<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/decorators/import.jsp"%>
<style>
	.hide{display:none}
	.show{display:block}
	.c-introduction{
		margin-left: 100px;
	    display: block;
	    margin-top: -27px;
	    width: 180%;
	}
	.resource .mainbackground .r-list ul li.teacher .r-list-content p {
		height: auto;
	}
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
				var itemId = $("#itemId").val();
				queryPageByKeys(itemId,pageNo);
			}
		});

	});
</script>
<c:if test="${empty pageFinder.data }">
<div class="zanwu">
暂无信息
</div>
</c:if>
<c:if test="${!empty pageFinder.data }">
<ul>
<c:forEach items="${pageFinder.data }" var="item">
	<li class="teacher clear">
        <div class="r-list-title">
            <span class="r-name">${item.name }</span>
            <div class="r-more"><a href="javascript:;" onclick="showOtherInfo(this,${item.id})">更多</a></div>
        </div>
        <div class="r-list-content clear show">
            <p class="c">
                <span class="c-title">姓名</span>
                <span class="c-content">
                    <input type="text" class="readonly" value="${item.name }" readonly>
                </span>
            </p>
            <p class="c" style="width:30%">
                <span class="c-title">手机号</span>
                <span class="c-content">
                    <input type="text" class="readonly" value="${item.mobile }" readonly>
                </span>
                
            </p>
            <c:if test="${item.isDistinguished==1 }">
	            <p class="r sort">
	            	<span class="c-title">排序:</span>
	                <span class="c-content">
	                    <input type="text" id="${item.id }_sortId" value="${item.sortId>0?item.sortId:'' }" class="sortinput">
	                </span>
	                <input type="button" class="sortSave" data-id="${item.id }" data-sort="${item.sortId>0?item.sortId:'' }" value="保存">
	            </p>
            </c:if>
        </div>
        
        <div class="r-list-content clear hide">
             <div class="r-list-content-title">
                 <span class="r-list-content-title-txt">基本信息</span>
                 <a href="<%=request.getContextPath()%>/sysConfigTeacher/manage/${item.id}" class="btn btn-link btn-mini">编辑</a>
                 <a href="javascript:void(0)" style="margin-right: 20px;" class="btn btn-link btn-mini delTeacher" teacherId="${item.id}">删除</a>
             </div>
             <p class="c">
                 <span class="c-title">姓名</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.name }" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">性别</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.sex != 'MALE' ? '女' : '男' }" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">出生日期</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="<fmt:formatDate value="${item.birthday }" pattern="yyyy-MM-dd" />" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">是否是名师</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.isDistinguished == 1 ? '是' : '否' }" readonly>
                 </span>
             </p>

        <p class='c'>
            <span class="c-title">教师级别</span>
									<span class="c-content">
                                         <c:choose>
                                             <c:when test="${item.teacherLevel=='GRADE_HIGH'}"><c:set var="teacherLevel" value="小学高级"></c:set></c:when>
                                             <c:when test="${item.teacherLevel=='MIDDLE_GRADE_HIGH'}"><c:set var="teacherLevel" value="中小学高级"></c:set></c:when>
                                             <c:when test="${item.teacherLevel=='MIDDLE_ONE'}"><c:set var="teacherLevel" value="中学一级"></c:set></c:when>
                                             <c:when test="${item.teacherLevel=='MIDDLE_TWO'}"><c:set var="teacherLevel" value="中学二级"></c:set></c:when>
                                             <c:when test="${item.teacherLevel=='MIDDLE_HIGH'}"><c:set var="teacherLevel" value="中学高级"></c:set></c:when>
                                         </c:choose>
                                        <input type="text" class="readonly" value="${teacherLevel}" readonly>

									</span>
        </p>
        <p class='c'>
            <span class="c-title">所在区域</span>
            <span class="c-content">
                <c:choose>
                    <c:when test="${item.teacherArea=='alone'}"><c:set var="teacherArea" value="直属直管"></c:set></c:when>
                    <c:when test="${item.teacherArea=='qing_yang'}"><c:set var="teacherArea" value="青羊区"></c:set></c:when>
                    <c:when test="${item.teacherArea=='jin_niu'}"><c:set var="teacherArea" value="金牛区"></c:set></c:when>
                    <c:when test="${item.teacherArea=='jin_jiang'}"><c:set var="teacherArea" value="锦江区"></c:set></c:when>
                    <c:when test="${item.teacherArea=='wu_hou'}"><c:set var="teacherArea" value="武侯区"></c:set></c:when>
                    <c:when test="${item.teacherArea=='gao_xin'}"><c:set var="teacherArea" value="高新区"></c:set></c:when>
                    <c:when test="${item.teacherArea=='tian_fu_xin'}"><c:set var="teacherArea" value="天府新区"></c:set></c:when>
                    <c:when test="${item.teacherArea=='cheng_hua'}"><c:set var="teacherArea" value="成华区"></c:set></c:when>
                    <c:when test="${item.teacherArea=='long_quan_yi'}"><c:set var="teacherArea" value="龙泉驿区"></c:set></c:when>
                    <c:when test="${item.teacherArea=='shuang_liu'}"><c:set var="teacherArea" value="双流区"></c:set></c:when>
                </c:choose>
                <input type="text" class="readonly" value="${teacherArea}" readonly>
            </span>
        </p>


             <p class="c">
                 <span class="c-title">现居地址</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.address }" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">最高学历</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="<c:choose><c:when test="${item.educationCode eq 'UNDER_JUNIOR'}">大专以下</c:when><c:when test="${item.educationCode eq 'JUNIOR'}">大专</c:when><c:when test="${item.educationCode eq 'BECHELOR'}">本科</c:when><c:when test="${item.educationCode eq 'POSTGRADUATE'}">研究生</c:when><c:when test="${item.educationCode eq 'DOCTOR'}">博士生及以上</c:when><c:otherwise>大专以下</c:otherwise></c:choose>" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">身份证</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.idNumber}" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">开户行</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.bankName}" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">开户名</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.bankAccountName}" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">银行卡号</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.bankAccountNum}" readonly>
                 </span>
             </p>
            <p class="c">
                <span class="c-title">学校名称</span>
                <span class="c-content">
                    <input type="text" class="readonly" value="${item.schoolName}" readonly>
                </span>
            </p>
             <p class="c c-p">
                 <span class="c-title">简介</span>
                 <span class="c-content c-introduction" style="width: 480px;word-wrap:break-word">
                     ${item.resume}
                 </span>
             </p>
         </div>
         <div class="r-list-content clear hide">
             <div class="r-list-content-title">
                 <span class="r-list-content-title-txt">联系信息</span>
             </div>
             <p class="c">
                 <span class="c-title">手机号</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.mobile }" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">家庭电话</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.homePhone }" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">办公电话</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.workPhone }" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">紧急联系人</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.emergencyContactName }" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">联系人电话</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.emergencyContactPhone }" readonly>
                 </span>
             </p>
             <p class="c">
                 <span class="c-title">邮箱</span>
                 <span class="c-content">
                     <input type="text" class="readonly" value="${item.email }" readonly>
                 </span>
             </p>
         </div>
    </li>
</c:forEach>
</ul>
</c:if>