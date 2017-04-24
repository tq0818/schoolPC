<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>订单</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
  	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
  	<script type="text/javascript">
  		function toSwitch(){
  			$("#switchForm")[0].submit();
  		}
  		$(document).ready(function(){
  			$selectSubMenu('student_online_order');
  		})
  	</script>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
<form action="<%=rootPath %>/studentPayMaster/toArrangeCourse" id="switchForm" method="post">
<input type="hidden" name="id" value="${payMaster.id }"/>
</form>
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
                    <a href="javascript:void(0);"  class="btn btn-success">详情</a>
                    <a href="javascript:void(0);" onclick="toSwitch();" class="btn btn-default">课程安排</a>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="c-title">学科小类</span>
                    <span class="c-content">${payMaster.itemSecondName }</span>
                </td>
                <td>
                    <span class="c-title">订单金额</span>
                    <span class="c-content">${payMaster.totalAmount }</span>
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
            <h2 class="h5">订单详情</h2>
            <div class="user-infos">
                <span>${student.name }</span> <span>${student.mobile }</span> <span>${payMaster.classTypeName }</span>
            </div>
            <span class="line"></span>
        </div>
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">基本信息</h2>
            </div>
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">姓名</span>
                        <span class="c-content">${student.name }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">性别</span>
                        <span class="c-content">${wx:dictCode2Name(student.sex )}</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">出生日期</span>
                        <span class="c-content">
                        <fmt:formatDate value="${student.birthday }" timeStyle="yyyy-MM-dd"/>
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">年龄</span>
                        <span class="c-content">${student.age }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">户口所在地</span>
                        <span class="c-content">${student.hkAddress }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">最高学历</span>
                        <span class="c-content">${wx:dictCode2Name(student.educationCode)}</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">证件类型</span>
                        <span class="c-content">${wx:dictCode2Name(student.identityTypeCode)}</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">证件号码</span>
                        <span class="c-content">${student.identityId }</span>
                    </p>
                </li>
            </ul>
        </div>
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">联系信息</h2>
            </div>
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">手机号</span>
                        <span class="c-content"><em>${student.mobile }</em></span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">家庭电话</span>
                        <span class="c-content">${student.homePhone }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">办公电话</span>
                        <span class="c-content">${student.officePhone }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">紧急联系人</span>
                        <span class="c-content">${student.emergencyContact }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">紧急人电话</span>
                        <span class="c-content">${student.emergencyPhone }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">邮箱</span>
                        <span class="c-content">${student.email }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">QQ号</span>
                        <span class="c-content">${student.qq }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">微信</span>
                        <span class="c-content">${student.weixinId }</span>
                    </p>
                </li>
            </ul>
        </div>
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">订单信息</h2>
            </div>
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">订单编号</span>
                        <span class="c-content">
                            ${payOrder.orderNum }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">订单金额</span>
                        <span class="c-content">
                            ${payOrder.payPrice }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">订单时间</span>
                        <span class="c-content">
                        <fmt:formatDate value="${payOrder.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"  />
                            
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">学科</span>
                        <span class="c-content">
                            ${payMaster.itemOneName }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">学科小类</span>
                        <span class="c-content">
                            ${payMaster.itemSecondName }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">学习课程</span>
                        <span class="c-content">
                            ${payMaster.classTypeName }
                        </span>
                    </p>
                </li>
                <%-- <li>
                    <p class='c'>
                        <span class="c-title">总 课 时</span>
                        <span class="c-content">${payMaster.classTypeHour }课时</span>
                    </p>
                </li> --%>
                <li>
                    <p class='c'>
                        <span class="c-title">支付状态</span>
                        <span class="c-content">${wx:dictCode2Name(payOrder.payStatus) }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">支付时间</span>
                        <span class="c-content">
                        <fmt:formatDate value="${payOrder.payTime }" pattern="yyyy-MM-dd HH:mm:ss"  />
                        </span>
                    </p>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>