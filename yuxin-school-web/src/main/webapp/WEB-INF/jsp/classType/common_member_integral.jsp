<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/stylesheets/splitscreen.css"/>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/stylesheets/custom-page.css"/>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/stylesheets/vip.css"/>
 <style type="text/css">
 	
 </style>
 <p class="c integral_settings none">
    <span class="c-title">积分购买</span>
    <span class="c-content">
       <c:choose>
       		<c:when test="${classType.integralFlag==1 }">
       			<input type="radio" value="0" name="integralFlag"><em class="marg">不使用积分</em>
        		<input type="radio" value="1" name="integralFlag" checked="checked"><em class="marg">使用积分（最大兑换比例为<bl class="jf_bl"></bl>%）</em>
       		</c:when>
       		<c:otherwise>
       			<input type="radio" value="0" checked="checked" name="integralFlag"><em class="marg">不使用积分</em>
        		<input type="radio" value="1" name="integralFlag"><em class="marg">使用积分（最大兑换比例为<bl class="jf_bl"></bl>%）</em>
       		</c:otherwise>
       </c:choose>
    </span>
</p>
 <p class="c none member_settings">
<%--     <span class="c-title">会员课程</span>
     <span class="c-content">
     	<c:choose>
       		<c:when test="${classType.memberFlag==1 }">
       			 <input type="radio" value="0" name="memberFlag"/> 不加入
         		 <input type="radio" value="1" name="memberFlag" checked="checked" style="margin-left: 30px"/> 加入会员课程
       		</c:when>
       		<c:otherwise>
       			 <input type="radio" value="0" name="memberFlag" checked="checked"/> 不加入
         		 <input type="radio" value="1" name="memberFlag" style="margin-left: 30px"/> 加入会员课程
       		</c:otherwise>
       </c:choose>
     </span>--%>
 </p>
 <c:choose>
	<c:when test="${classType.memberFlag==1 }">
		 <p class="c member_setting_bl">
		     <span class="c-title"></span>
		     <span class="c-content" id="level_member_list">
		
		     </span>
		 </p>
	</c:when>
	<c:otherwise>
		 <p class="c member_setting_bl none">
		     <span class="c-title"></span>
		     <span class="c-content" id="level_member_list">
		
		     </span>
		 </p>
	</c:otherwise>
 </c:choose>      		
 <div class="add-page-pop">
    <div class="add-page-board">
        <p class="page-pop-title"><span id="mem_title_Name">会员价格</span><em class="iconfont">&#xe610;</em></p>
        <div style="padding: 10px 50px;">
            <div style="line-height: 22px;">
                <input type="radio" checked="checked" name="openWay" value="1" style="position: relative; top: 2px;margin-right: 3px;"/>打折优惠
            </div>
            <div class="pric-box">
                <p>原价：</p>
                <p><input class="pric" type="text" value="${classType.realPrice }"/>元</p>
            </div>
            <div class="pric-box">
                <p>折扣：</p>
                <p><input class="pric-text print_prices printYesColor" id="course_discount" type="text" value=""/>折</p>
            </div>
            <div class="pric-box">
                <p>减价：</p>
                <p><input class="pric-text prices printYesColor" id="chaPrice" type="text" value="7"/>元</p>
            </div>
            <div class="pric-box">
                <p>减价后：</p>
                <p><input class="pric-text prices printYesColor" id="youhuiPrice" type="text"/>元</p>
            </div>
            <div class="clear"></div>
            <div style="line-height: 22px;margin: 20px 0 30px">
                <input type="radio" name="openWay" value="0" style="position: relative; top: 2px;margin-right: 3px;"/>免费观看
            </div>
            <p class="c text-center">
                <a href="javascript:void(0);" class="btn btn-primary save_member_set">保存</a>
                <a href="javascript:void(0);" class="btn btn-default cancle_qx">取消</a>
            </p>
        </div>
    </div>
</div>
<p class="c buyNumSetting none">
    <span class="c-title">课程购买人数限制</span>
    <span class="c-content"><input name="buyNumMax" type="text" value="${classType.buyNumMax }">&nbsp;&nbsp;
     <i class="iconfont ask" style="cursor: pointer;color:gray;margin-left:10px;" title="请填写实际限制的购买人数，不包含购买基数">&#xe60f;</i>
    </span>
</p>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/class/integral_member.js"></script>