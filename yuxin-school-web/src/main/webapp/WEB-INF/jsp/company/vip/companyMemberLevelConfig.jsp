<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath=request.getContextPath(); %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>会员等级配置</title>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/vip.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/custom-page.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
     <%@include file="/WEB-INF/jsp/menu/menu_member.jsp" %>
    <div class="right-side becomeMemberData" data-becomemember=${companyMemberConfig.becomeMember}>
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">编辑</span>
                </div>
            </div>
            <div class="block-box-s padd-box memberLevelIdDiv" data-id="${memberLevelAndDetail.id}">
                <div class="left-title">会员等级名称</div>
                <div class="right-cont">
                    <div class="right_cont_line">
                        <input type="text" name="name" id="name" value="${memberLevelAndDetail.name}"/>
                    </div>
                </div>
            </div>
            <div class="block-box-s padd-box">
                <div class="left-title">晋级方式</div>
                <div class="right-cont">
                    <div class="right_cont_line" style="line-height: 36px" id="open_way">
                    	<c:choose>
                    			<c:when test="${memberLevelAndDetail.openWay==0}">
	                    			<input type="radio" name="level" class="levelf" value="0" checked="checked"/><span>允许前台购买或累计消费晋升会员</span>
	                        		<input type="radio" name="level" class="levelf" value="1" style="margin-left: 20px"/><span>只允许机构后台开通</span>
                    			</c:when>
                    			<c:when test="${memberLevelAndDetail.openWay==1}">
                    				<input type="radio" name="level" class="levelf" value="0"/><span>允许前台购买或累计消费晋升会员</span>
	                        		<input type="radio" name="level" class="levelf" value="1" style="margin-left: 20px" checked="checked"/><span>只允许机构后台开通</span>
                    			</c:when>
                    			<c:otherwise>
                    				<input type="radio" name="level" class="levelf" value="0"/><span>允许前台购买或累计消费晋升会员</span>
                        			<input type="radio" name="level" class="levelf"value="1" style="margin-left: 20px"/><span>只允许机构后台开通</span>
                    			</c:otherwise>
                    	</c:choose>
                    </div>
                </div>
            </div>
            <div class="block-box-s padd-box">
                <div class="left-title">会员权限</div>
                <div class="right-cont">
                    <div class="right_cont_line" style="line-height: 36px" id="quanxian">
                    
                    	<c:choose>
                    		<c:when test="${memberLevelAndDetail.discount==0}">
                    			<input type="radio" name="authorities" class="radioItem" value="0" checked="checked"/>会员课程免费
                       			 <input type="radio" name="authorities" class="radioItem" value="1" style="margin-left: 20px"/>会员课程打折
                    		</c:when>
                    		<c:when test="${memberLevelAndDetail.discount>0}">
                    			<input type="radio" name="authorities" class="radioItem" value="0" />会员课程免费
                        		<input type="radio" name="authorities" class="radioItem" value="1" style="margin-left: 20px" checked="checked"/>会员课程打折
                    		</c:when>
							<c:otherwise>
								<input type="radio" name="authorities" class="radioItem" value="0"/>会员课程免费
                        		<input type="radio" name="authorities" class="radioItem" value="1" style="margin-left: 20px"/>会员课程打折
							</c:otherwise>
                    	</c:choose>
                    </div>
                    <c:choose>
                    	<c:when test="${memberLevelAndDetail.discount>-1 }">
                    		 <span id="discountspan"><input type="text" style="margin-bottom: 3px;" id="discount" value="${memberLevelAndDetail.discount}"/>&nbsp;折</span>
                    	</c:when>
                    	<c:otherwise> 
                    		<span id="discountspan"><input type="text" style="margin-bottom: 3px;" id="discount" />&nbsp;折</span>
                    	</c:otherwise>
                    </c:choose>
                </div>
            </div>
            
            <div class="block-box-s padd-box becomeMemberBuy" <c:if test="${memberLevelAndDetail.openWay==1}">style="padding-top: 10px;padding-bottom: 0;display:none;"</c:if>>
                <div class="left-title" >成为会员</div>
                <div class="right-cont addButtonParent">
                <c:if test="${companyMemberConfig.becomeMember==0}">
                 <!-- 如果会员配置里面设置的是购买型-->
                    <div class="right_cont_line addSelect">
                        购买
                        <b class="add-btn"><i class="iconfont">&#xe652;</i></b>
                   </div>
                        <c:forEach items="${memberLevelAndDetail.list}"  var="levelDetail">
                        <div class="right_cont_line addInput" data-id="${levelDetail.id}">
							  <select>    
								  <option value="1" <c:if test="${levelDetail.length==1}">selected="selected"</c:if>>1个月</option>
								  <option value="2" <c:if test="${levelDetail.length==2}">selected="selected"</c:if>>2个月</option>
								  <option value="3" <c:if test="${levelDetail.length==3}">selected="selected"</c:if>>3个月</option>
								  <option value="4" <c:if test="${levelDetail.length==4}">selected="selected"</c:if>>4个月</option>
								  <option value="5" <c:if test="${levelDetail.length==5}">selected="selected"</c:if>>5个月</option>
								  <option value="6" <c:if test="${levelDetail.length==6}">selected="selected"</c:if>>6个月</option>
								  <option value="7" <c:if test="${levelDetail.length==7}">selected="selected"</c:if>>7个月</option>
								  <option value="8" <c:if test="${levelDetail.length==8}">selected="selected"</c:if>>8个月</option>
								  <option value="9" <c:if test="${levelDetail.length==9}">selected="selected"</c:if>>9个月</option>
								  <option value="10" <c:if test="${levelDetail.length==10}">selected="selected"</c:if>>10个月</option>
								  <option value="11"<c:if test="${levelDetail.length==11}">selected="selected"</c:if>>11个月</option>
								  <option value="12"<c:if test="${levelDetail.length==12}">selected="selected"</c:if>>12个月</option>
								  <option value="24"<c:if test="${levelDetail.length==24}">selected="selected"</c:if>>24个月</option>
								  <option value="36"<c:if test="${levelDetail.length==36}">selected="selected"</c:if>>36个月</option>
								  <option value="-1"<c:if test="${levelDetail.length==-1}">selected="selected"</c:if>>终身</option>  
							  </select>
							  <em class="salename">售卖价</em>
							 	<input type="text" class="priceInput" value="${levelDetail.price}"/>元
								 <span class="bay-way-delete" onclick="deleteSelect(this,${memberLevelAndDetail.id},${levelDetail.id})">删除</span>
							  </div>
						  </c:forEach>
						  </c:if>
						    <!-- 如果会员配置里面设置的是累积型 -->
					 <c:if test="${companyMemberConfig.becomeMember==1}">
	                    <div class="right_cont_line" style="height: 22px;line-height: 22px;margin-top: 5px;">
	                        累计消费
	                    </div>
	                    <div class="right_cont_line" id="consumptionInputContent" >
	                        <em class="right_cont_notes">说明：开启后可在创建课程、课程包时针对个别课程临时调整会员所享受的优惠</em>
	                    </div>
                    </c:if>
                </div>
            </div>
            <!-- 如果会员配置里面设置的是累积型 -->
            <c:if test="${companyMemberConfig.becomeMember==1}">
            <div class="block-box-s padd-box" id="consumptionInput" <c:if test="${memberLevelAndDetail.openWay==1}">style="display:none;"</c:if>>
                <div class="left-title"></div>
                <div class="right-cont">
                    <em>需要累计消费</em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="margin-bottom: 3px" id="consumption" value="${memberLevelAndDetail.consumption}"/>&nbsp;元
                </div>
            </div>
            </c:if>
            <div class="add-layer-bg none" id="stopDiv" style="display: none;"></div>
            <div class="block-box-s padd-box">
                <div class="left-title">设置图标</div>
                <div class="right-cont">
                    <span class="set-pic">设置图标
                    </span>
					<img src="${memberLevelAndDetail.ico }" class='icoUrl' width="30px" height="30px" style="margin-left:10px;margin-right:10px;"/>
					<div class="trans">更换</div>
                </div>
            </div>
            <div class="block-box padd-box">
                <div class="left-title">会员简介</div>
                <div class="right-cont">
                    <div class="limitnumber_textarea" style="width: 550px;">
                        <textarea id="description">${memberLevelAndDetail.description}</textarea>
                        <span><em class="words">1</em>/60</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="save-cancel">
        <c:choose>
        	<c:when test="${memberLevelAndDetail.id==null}">
	        	<span class="save saveData">保存</span>
	            <span class="cancel">取消</span>
        	</c:when>
        	<c:otherwise>
        		<span class="save saveData">更新</span>
	            <span class="cancel">取消</span>
        	</c:otherwise>
        </c:choose>
        </div>
    </div>
</div>
<div class="upload-layer none" id="chooseDiv" style="width: 930px;">
    <div class="upload-title">
        <h2 class="h5">选择会员等级图标</h2>
        <i class="iconfont close">&#xe610;</i>
    </div>
    <div class="types">
        <a href="javascript:queryConditionPics('');" marks="publicPic" class="btn btn-default btn-success">系统默认图标</a>
        <a href="javascript:queryConditionPics('');" marks="privatePic" class="btn btn-default ">自定义图标</a>
    </div>
    <div id="tlist" style="height: 430px;position: relative;">
	   
    </div>
</div>
<div class="add-layer-bg none" id="stopDiv"></div>
<script type="text/javascript">
$(function(){
	$selectSubMenu('org_service');
	$chooseMenus('setMemberLevel');
});
</script>
<script>
var  rootPath='<%=rootPath%>';
</script>
<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script src="<%=rootPath%>/javascripts/company/vip/companyMemberLevelConfig.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
</body>
</html>


