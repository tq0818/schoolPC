<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/wx.tld" prefix="wx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<%@include file="/decorators/import.jsp" %>
<title>会员设置</title>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/vip.css" />
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company overflow">
  <%@include file="/WEB-INF/jsp/menu/menu_member.jsp" %>
		<div class="right-side">
			<div>
				<div class="title-box">
					<div class="tit-font">
						<span class="t">会员基本设置</span>
					</div>
				</div>
				<div class="block-box padd-box memberPage"  data-id="${companyMemberConfig.id}">
					<div class="left-title">会员专区</div>
					<div class="right-cont">
						<div class="tit-font">
						<c:if test="${companyMemberConfig.memberPage==0}">
							<em class="iconfont normal close   memberareaswitch">&#xe604;</em> <span
								class="i close">已禁用</span>
						</c:if>
						<c:if test="${companyMemberConfig.memberPage==1}">
							<em class="iconfont normal open   memberareaswitch">&#xe603;</em> <span
								class="i open">已启用</span>
						</c:if>
						</div>
						<div class="show-pic-area picarea" <c:if test="${companyMemberConfig.memberPage==0}">style="display:none;"</c:if>>
							<img src="${companyMemberConfig.memberPageBanner}" width="468px" height="180px" class="imgBanner"/>
							<div class="trans">更换</div>
						</div>
						<div class="right_cont_line pic" <c:if test="${companyMemberConfig.memberPage==0}">style="display:none;"</c:if>>
							<span class="upload_btn">上传专区宣传图</span>
							<input type="file" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" id="imgDatas" name="imgDatas" onchange="javascript:uploadFile();" style="opacity:0;width:68px;display: inline-block;position: absolute;left: 0;top:240px;margin-left: 15px;cursor: pointer;filter:alpha(opacity = 0);">
							<em class="right_cont_notes">请上传png, gif, jpg格式的图片文件。图片建议大小1920*340</em>
						</div>
					</div>
				</div>
				<div class="block-box padd-box">
                <div class="left-title becomember" data-becomemeber="${companyMemberConfig.becomeMember}">成为会员方式</div>
                <div class="right-cont">
                    <div class="right_cont_line" style="line-height: 36px">
                        <input type="radio" name="upgradeMethod" id="buy" class="upgradeMethod" value="0" <c:if test="${companyMemberConfig.becomeMember==0}">checked="checked"</c:if>>通过购买成为会员
                    </div>
                    <div class="">
                        <em class="right_cont_notes">说明：学员通过使用购买的方式成为会员</em>
                    </div>
                    <div class="right_cont_line" style="line-height: 36px">
                        <input type="radio" name="upgradeMethod" value="1" id="consumption" class="upgradeMethod" <c:if test="${companyMemberConfig.becomeMember==1}">checked="checked"</c:if>>通过累计消费成为会员
                    </div>
                    <div class="">
                        <em class="right_cont_notes">说明：学员在网校消费到一定额度时自动成为会员 <em class="red-notes" style="margin-left: 20px;">注：累积消费晋升的会员有效期默认为终身</em></em>
                    </div>
                </div>
            </div>
				<div class="block-box padd-box  memberreminddiv"<c:if test="${companyMemberConfig.becomeMember==1}">style="display:none;"</c:if>>
					<div class="left-title">会员到期提醒</div>
					<div class="right-cont">
						<div class="tit-font">
						<c:if test="${companyMemberConfig.remind==1}">
								<em class="iconfont normal open warning-btn remindSwitch"></em> 
								<span class="i open">已启用</span>
						</c:if>
						<c:if test="${companyMemberConfig.remind==0}">
									<em class="iconfont normal close warning-btn remindSwitch">&#xe604;</em> <span
								class="i close">已禁用</span>
						</c:if>	
						</div>
						<div class="right_cont_line  comment">
							<em class="right_cont_notes">说明：开启该功能后，在学员会员有效期即将到期时，系统会根据机构编辑内容给会员发送站内信</em>
						</div>
						<div class="right_cont_line beforeday"<c:if test="${companyMemberConfig.remind==0}">style="display:none;"</c:if> >
							<em class="text-em">会员过期日前</em> <span class="middle-btn date-btn"><input type="text" class="expireDay" value="${companyMemberConfig.remindBeforeDay }" style="width:20px;border:0px"></span>
							<em class="text-em">天发送通知提醒</em>
						</div>
						<div class="limitnumber_textarea remindContent" <c:if test="${companyMemberConfig.remind==0}">style="display:none;"</c:if>>
										<textarea class="remind_content"
										placeholder="亲爱的【username】，您好，您购买的会员将在【2016-04-06】日到期。为方便您的使用请及时续费。">${companyMemberConfig.remindContent}</textarea>
								<span><em class="words">0</em>/120</span>
							
						</div>
						<div class="right_cont_line tips" <c:if test="${companyMemberConfig.remind==0}">style="display:none;"</c:if>>
							<em class="right_cont_notes">【username】是固定内容，不能修改。</em>
						</div>
					</div>
				</div>
				<div class="block-box padd-box">
					<div class="left-title">会员课程个性化折扣</div>
					<div class="right-cont">
						<div class="tit-font">
						<c:choose>
							<c:when test="${companyMemberConfig.classDiscount==1}">
								<em class="iconfont normal open warning-btn classDiscountSwitch">&#xe603;</em> <span
								class="i open">已启用</span>
							</c:when>
							<c:when test="${companyMemberConfig.classDiscount==0}">
								<em class="iconfont normal close warning-btn classDiscountSwitch">&#xe604;</em> <span
								class="i close">已禁用</span>
							</c:when>
						</c:choose>
							
						</div>
						
						<div class="right_cont_line discounttxt">
							<em class="right_cont_notes">说明：开启后可在创建课程、课程包时针对个别课程临时调整会员所享受的优惠</em>
						</div>
					</div>
				</div>
				<div class="block-box padd-box" style="padding-left: 60px;">
					<div class="left-title" style="width:154px">只允许会员购买会员课程</div>
					<div class="right-cont">
						<div class="tit-font">
						<c:choose>
							<c:when test="${companyMemberConfig.buyFlag==0}">
								<em class="iconfont normal open warning-btn buyFlag">&#xe603;</em> <span
								class="i open">已启用</span>
							</c:when>
							<c:otherwise>
								<em class="iconfont normal close warning-btn buyFlag">&#xe604;</em> <span
								class="i close">已禁用</span>
							</c:otherwise>
						</c:choose>
							
						</div>
						
						<div class="right_cont_line discounttxt">
							<em class="right_cont_notes">说明：开启后所有的会员课程只允许会员购买，非会员用户不可购买</em>
						</div>
					</div>
				</div>
				<div class="block-box padd-box classDiscountRadio"<c:if test="${companyMemberConfig.becomeMember==1}">style="display:none;"</c:if> >
					<div class="left-title">会员升级设置</div>
					<div class="right-cont">
						<div class="right_cont_line" style="line-height: 36px" id="upgrade_type">
							<c:choose>
								<c:when test="${companyMemberConfig.upgradeType==0}">
									<input type="radio" name="sale" value="0" checked="checked"/>按原价购买 
									<input type="radio" name="sale" value="1" style="margin-left: 20px" />补差价购买
								</c:when>
								<c:when test="${companyMemberConfig.upgradeType==1}">
									<input type="radio" name="sale" value="0" />按原价购买 
									<input type="radio" name="sale" value="1" style="margin-left: 20px" checked="checked"/>补差价购买
								</c:when>
								
							</c:choose>
							
						</div>
						<div class="">
							<em class="right_cont_notes">说明：当学员是终身制会员，且购买更高等级的终身会员时。系统会根据机构的选择进行计算</em><br />
							<em class="red-notes">注：当会员存在有效期时，升级会员时，系统会按天计算差价（差价小于0时
								则不计算差价）</em>
						</div>
					</div>
				</div>
				<!-- 积分购买 -->
				<div class="block-box padd-box integralBuyDiv"<c:if test="${companyMemberConfig.becomeMember==1}">style="display:none;"</c:if>>
                <div class="left-title">积分购买</div>
                <div class="right-cont">
                    <div class="tit-font">
                    <c:choose>
						 <c:when test="${companyMemberConfig.buyWithIntegral==1}">
	                        <em class="iconfont normal open warning-btn buyWithIntegralSwitch"></em>
	                        <span class="i open">已启用</span>
                        </c:when>
                        <c:when test="${companyMemberConfig.buyWithIntegral==0}">
								<em class="iconfont normal close warning-btn buyWithIntegralSwitch">&#xe604;</em> <span
								class="i close">已禁用</span>
							</c:when>
					</c:choose>
                    </div>
                    <div class="right_cont_line">
                        <em class="right_cont_notes">说明：开启后允许学员使用积分购买会员</em>
                    </div>
                </div>
            </div>
				
				
				
				<c:choose>
					<c:when test="">
							<div class="save-cancel">
								<span class="save">保存</span> <span class="cancel">返回</span>
							</div>
					</c:when>
					<c:otherwise>
							<div class="save-cancel">
								<span class="save">更新</span> <span class="cancel">返回</span>
							</div>	
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(function(){
	$chooseMenus('setMember');
	$selectSubMenu('org_service');
});
</script>
	<script>
var  rootPath='<%=rootPath%>';
	</script>
	<script
		src="<%=rootPath%>/javascripts/company/vip/companyMemberConfig.js"></script>
	<script type="text/javascript"
		src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
</body>
</html>