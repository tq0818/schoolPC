<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>订单</title>
     <%@include file="/decorators/import.jsp"%>
  	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
  	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
  	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
  	<script type="text/javascript" src="<%=rootPath %>/javascripts/student/arrangeCourse.js"></script>
  	<script type="text/javascript">
		function toSwitch(){
			//alert(123);
			$("#switchForm")[0].submit();
		}
	</script>
    
</head>
<body>
<form action="<%=rootPath %>/studentPayMaster/detail" id="switchForm" method="post">
<input type="hidden" name="id" value="${payMaster.id }"/>
</form>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
<input type="hidden" id="itemOne" value="${payMaster.itemOneId }"/>
<input type="hidden" id="payMasterId" value="${payMaster.id }"/>
<input type="hidden" id="classType" value="${payMaster.classTypeId }"/>
<input type="hidden" id="student" value="${student.id }"/>
<input type="hidden" id="itemSecond" value="${payMaster.itemSecondId  }"/>
<input type="hidden" id="campusId" value=""/>
<div class="u-wrap student">
    <div class="mainbackground nopadding">
        <table class="table userinfos">
            <col width="25%">
            <col width="25%">
            <col width="25%">
            <col width="25%">
            <tr>
                <td>
                    <span class="c-title">学科</span>
                    <span class="c-content">${payMaster.itemOneName }</span>
                </td>
                <td>
                    <span class="c-title">课程</span>
                    <span class="c-content">${payMaster.classTypeName }</span>
                </td>
                <td>
                    <span class="c-title">报名时间</span>
                    <span class="c-content">
                    <fmt:formatDate value="${payMaster.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" />
                    </span>
                </td>
                <td rowspan="2">
                   <a href="javascript:void(0);" onclick="toSwitch();" class="btn btn-default">详情</a>
                    <a href="javascript:void(0);"  class="btn  btn-success">课程安排</a>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="c-title">学科小类</span>
                    <span class="c-content">${payMaster.itemSecondName }</span>
                </td>
                <td>
                    <span class="c-title">订单金额</span>
                    <span class="c-content">
                    <fmt:formatNumber value="${payMaster.totalAmount }" type="currency"></fmt:formatNumber>
                    </span>
                </td>
                <td>
                    <span class="c-title">来源</span>
                    <span class="c-content">${wx:dictCode2Name(payMaster.applyChannelCode)}</span>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">课程安排</h2>
            <div class="user-infos">
                <span>${student.name }</span> <span>${student.mobile }</span> <span>${payMaster.classTypeName }</span>
            </div>
            <span class="line"></span>
        </div>
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">订单信息</h2>
            </div>
            <ul class="list-infos clear">
                                <li>
                    <p class='c'>
                        <span class="c-title">学习课程</span>
                        <span class="c-content">
                            ${payMaster.classTypeName }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
							<span class="c-title">代报考</span> <span class="c-content">
								<input type="checkbox" 
								<c:if test="${payMaster.isAgent==1 }">checked</c:if>
								 value="1" disabled="disabled"> 是否代报考 
								 <input type="checkbox"
								<c:if test="${payMaster.isAgentMaterialComplete==1 }">checked</c:if>
								 value="2" disabled="disabled"> 资料是否齐全
							</span>
						</p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">订单总价</span>
                        <span class="c-content">
                            ${payMaster.totalAmount }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">实缴费用</span>
                        <span class="c-content">${hasPay }</span>
                    </p>
                </li>
            </ul>
        </div>
        <div class="main-content classInfo">
            <div class="m-title">
                <h2 class="h6">课程</h2>
            </div>
            <ul class="list-infos clear">
                <li class="long clear none">
                    <p class='c'>
                        <span class="c-title small">优先上课校区</span>
                        <span class="c-content schools">
                        </span>
                    </p>
                </li>
                <li class="long clear">
                    <p class="c">
                        <span class="c-title small">学员计划考期</span>
                        <span class="c-content">
                            <select name="" id="" class="term">
                                <option value="">请选择</option>
                            </select>
                        </span>
                    </p>
                </li>
            </ul>
            <div class="c-list modules">
                
            </div>
            <div class="m-content">
                <p class="c text-center">
                    <span class="c-title">&nbsp;</span>
                    <span class="c-content">
                        <a href="javascript:;" onclick="history.go(-1);"  class="btn btn-sm btn-default">取消</a>
                        <a href="javascript:;" onclick="save();" class="btn btn-sm btn-primary">保存</a>
                    </span>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>