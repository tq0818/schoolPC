<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>优惠码</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/footer.css"/>
   <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/coupon-select.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/manage-screen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/coupon.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
	<style type="text/css">
		.none{
			display: none;
		}
		.coupon-set select{
			width: 104px;
		}
	</style>
<script type="text/javascript" src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
	 <script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>
	 <script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
	 <script type="text/javascript" src="<%=rootPath %>/javascripts/coupon/alert-function.js"></script>
	 <script type="text/javascript" src="<%=rootPath %>/javascripts/coupon/coupon-out.js"></script> 
	 <script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentlistChoose.js"></script> 
	 <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	 <script type="text/javascript" src="<%=rootPath%>/javascripts/coupon/setCoupons.js"></script>
	 <script type="text/javascript" src="<%=rootPath%>/javascripts/coupon/coupon-create.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/DateUtils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/coupon/coupons.js"></script>
	
</head>
<body>
<input type="hidden" value="${couponsPatch.commodityType }" id="id_commodity_type"/>
<input type="hidden" value="${couponsPatch.rangeItemPackageCategory }" id="id_rangeItemPackageCategory"/>
<input type="hidden" value="${couponsPatch.issueWay }" id="add_type"/>
<input type="hidden" value="${couponsPatch.useWay }" id="useTypeIN"/>
<input type="hidden" value="${couponsPatch.useRange }" id="use_Rang_IN"/>
<input type="hidden" value="${couponsPatch.noticMsgContents }" id="noticContents"/>
<input type="hidden" value="${couponsPatch.memberList }" id="member_list_types"/>
<input type="hidden" value="${couponsPatch.userList }" id="user_list_types"/>
<input type="hidden" value="${couponsPatch.rangeItemOne }" id="id_rangeItemOne"/>
<input type="hidden" value="${couponsPatch.rangeItemSecond }" id="id_rangeItemSecond"/>
<input type="hidden" value="${couponsPatch.rangeItemCourse }" id="id_rangeItemCourse"/>
<input type="hidden" value="${couponsPatch.forCrowd }" id="id_forwardObject"/>
<input type="hidden" value="${couponsPatch.noticWay }" id="id_noticeWay"/>
<input type="hidden" value="${codes }" id="id_codes"/>
<input type="hidden" value="${classPackageName }" id="id_classPackageName"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap clear company manage-screen">
  <jsp:include page="/WEB-INF/jsp/menu/menu_coupon.jsp"></jsp:include>
    <div class="screen-right">
        <div class="screen-right-cont">
            <div class="screen-right-title">
                <h3> <i> 创建优惠码</i></h3>
            </div>
            <input type="hidden" value="${couponsPatch.id }" id="coupsPatchId"/>
            <div class="coupon-title-tab coupon_types">
                <span class="tab-itm active" ids="0" mark="coupon-online">线上发放</span>
                <c:if test="${empty couponsPatch.useRange }">
                <span class="tab-itm" ids="1" mark="coupon-downline">线下发放</span>
                </c:if>
            </div>
            <div class="coupon-out-type coupon-online">
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠名称
                    </div>
                    <div class="fl">
                        <input type="text" value="${couponsPatch.name }" id="onLine_couponseName" class="w210" maxlength="10" placeholder="最多只能输入10个汉字"/>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        优惠说明
                    </div>
                    <div class="fl">
                        <input type="text" id="onLine_desciprtion" value="${couponsPatch.description }" class="w310" maxlength="20" placeholder="最多只能输入20个汉字"/>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont"></i>
                        产品类型
                    </div>
                    <div class="fl coupon-type product-type" >
                    	<c:if test="${empty couponsPatch.commodityType || couponsPatch.commodityType!=1 }">
	                    <span><input type="radio" name="product-type" value="0" data-type="0"  checked="checked" >课程</span>
	                    </c:if>
	                    <c:if test="${empty couponsPatch.useRange || (!empty couponsPatch.useRange && couponsPatch.commodityType==1)}">
	                    <c:if test="${classPackageService==1 }">
	                   		<span><input type="radio" name="product-type" value="1" data-type="1">课程包</span>
	                    </c:if>
	                    </c:if>
               		</div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠方式
                    </div>
                    <div class="fl coupon-type" id="onLine_conTypeslist">
                        <span><input type="radio" name="type" value="0" mark="type-a"/>抵现</span>
                        <span><input type="radio" name="type" value="1" mark="type-b"/>满减</span>
                        <span><input type="radio" name="type" value="2" mark="type-c"/>打折</span>
                        <div class="coupon-type-cont">
                            <p class="type-a">面额 <input type="text" class="prices" value="${couponsPatch.insCashNum }" id="onLine_insCashNum" maxlength="9"/>元<span id="onLine_insCashNum_msg">当课程原价或优惠后的价格低于抵现金额时,抵现金额不能用.</span></p>

                            <p class="type-b">满 <input type="text" class="prices" value="${couponsPatch.overNum }" id="onLine_overNum" maxlength="9"/>减 <input type="text" value="${couponsPatch.overCutNum }" class="prices" maxlength="9" id="onLine_overCutNum"/><span>"减"的金额必须大于0</span></p>

                            <p class="type-c">折扣 <input type="text" value="${couponsPatch.discountNum }" class="print_prices" id="onLine_discountNum"/>折</p>
                        </div>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠对象
                    </div>
                    <div class="fl p-relative">
                        <div class="user-chose-wrap" id="">
                            <div class="user-choses-selected" id="onLine_choseObject" value="${couponsPatch.forCrowd }">
                            <c:choose>
                            	<c:when test="${!empty couponsPatch.forCrowd }">
                            		<c:if test="${couponsPatch.forCrowd=='COUPON_OBJECT_ALL' }">所有注册用户</c:if>
                            		<c:if test="${couponsPatch.forCrowd=='COUPON_OBJECT_STUDENT' }">所有购买过课程的学员</c:if>
                            		<c:if test="${couponsPatch.forCrowd=='COUPON_OBJECT_MEMBER' }">会员</c:if>
                            		<c:if test="${couponsPatch.forCrowd=='COUPON_OBJECT_SOMEONE' }">指定用户</c:if>
                            	</c:when>
                            	<c:otherwise>
                            		请选择
                            	</c:otherwise>
                            </c:choose>
                            </div>
                            <div class="user-chose-position">
                                <div ids="COUPON_OBJECT_ALL">所有注册用户</div>
                                <div ids="COUPON_OBJECT_STUDENT">所有购买过课程的学员</div>
                                <c:if test="${showMember=='show' }">
                                	 <div ids="COUPON_OBJECT_MEMBER" mark="member">会员</div>
                                </c:if>
                                <div ids="COUPON_OBJECT_SOMEONE" mark="point-user">指定用户</div>
                            </div>
                            <div class="select-sanjiao">
                                <b></b>
                            </div>
                        </div>
<!--                         <div name="" class="coupon-user" style="display: none"> -->
<!--                             <option value="COUPON_OBJECT_ALL">所有注册用户</option> -->
<!--                             <option value="COUPON_OBJECT_STUDENT">所有购买过课程的学员</option> -->
<!--                             <option value="COUPON_OBJECT_MEMBER">会员</option> -->
<!--                             <option value="COUPON_OBJECT_SOMEONE" >指定用户</option> -->
<!--                         </div> -->
						<c:choose>
                            	<c:when test="${couponsPatch.forCrowd=='COUPON_OBJECT_MEMBER' }">
                            	   <span style="display: none;" class="give-out-nume">发放<input id="tiketsNums" type="text" disabled="disabled" value="${couponsPatch.totalNum }" style="border-radius: 0;width: 80px;margin: 0 10px"/>个</span>
									<div class="member-p member_lists_chose1" style="display: block;">
                            
                        				</div>
                            	</c:when>
                            	<c:otherwise>
                            	<span class="give-out-nume">发放<input id="tiketsNums" type="text" disabled="disabled" value="${couponsPatch.totalNum }" style="border-radius: 0;width: 80px;margin: 0 10px"/>个</span>
                            		<div class="member-p member_lists_chose1">
                            
                        				</div>	
                            	</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠码前缀
                    </div>
                    <div class="fl">
                        <input type="text" value="${couponsPatch.promoCodePrefix }" class="w210 tikeckts_fornt" id="onLine_promoCodePrefix" maxlength="4"/>

                        <p class="l-note">只能是英文字母A-Z，限4个字母，不区分大小写，不可与其他优惠码前缀重复</p>
                    </div>
                </div>
<!--                 <div class="coupon-block clear"> -->
<!--                     <div class="t-label fl"> -->
<!--                         <i class="iconfont">&#xe605;</i> -->
<!--                         优惠位数 -->
<!--                     </div> -->
<!--                     <div class="fl"> -->
<%--                         <input type="text" value="${couponsPatch.promoCodeLength }" class="w210" id="onLine_promoCodeLength"/> --%>

<!--                         <p class="l-note">不包含前缀，长度请填写在5-15位之间</p> -->
<!--                     </div> -->
<!--                 </div> -->
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        使用期限
                    </div>
                    <div class="fl">
                        <input type="text" readonly="readonly" value="<fmt:formatDate value="${couponsPatch.timeLimitFrom }" pattern="yyyy-MM-dd" />"  name="startTime" id="onLine_startTime" class="date-picker from">
                        <em>——</em>
                        <input type="text" readonly="readonly" value="<fmt:formatDate value="${couponsPatch.timeLimitTo }" pattern="yyyy-MM-dd" />" name="endTime" id="onLine_endTime" class="date-picker to">

                        <p class="l-note">开始时间不早于当天，结束时间不能早于开始时间</p>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠范围
                    </div>
                    <div class="fl coupon-name comm_useRanges" id="onLine_userRanges">
                        <p><input type="radio" checked="checked" value="0" name="coupon-name"/>全部课程</p>

                        <p class="item_list_choose">
                            <input class="class-choose"  type="radio" name="coupon-name" value="1"/>指定范围
                            <span class="coupon-set none">
                                 <select class="itemOne">
                                     
                                 </select>
                            </span>
                           <span class="coupon-set none">
                                <select class="itemTwo">
                                   
                                </select>
                            </span>
                        </p>
                        <p class="item_course_choose"><input class="class-choose"  type="radio" name="coupon-name" value="2"/>指定课程
                            <span class="coupon-set none">
                                 <select class="itemOne">
                                 
                                 </select>
                            </span>
                           <span class="coupon-set none">
                                <select class="itemTwo">
                                
                                </select>
                            </span>
                            <span class="coupon-set none">
                                <select class="classes_course">

                                </select>
                            </span>
                        </p>
                    </div>
                </div>
                <div class="coupon-block send_message_type clear">
                    <div class="t-label fl">
                        发放通知
                    </div>
                    <div class="fl coupon-notice">
                        <p>
                        <c:choose>
                        	<c:when test="${couponsPatch.noticWay==1 ||couponsPatch.noticWay==2 }">
                        		<input type="checkbox" name="coupon-notice" checked="checked" id="sms_info" value="1"/>
                        	</c:when>
                        	<c:otherwise>
                        		<input type="checkbox" name="coupon-notice" id="sms_info" value="1"/>
                        	</c:otherwise>
                        </c:choose>短信</p>

                        <p class="message_showMsg" style="display: none;">发送内容</p>

                        <div class="msg-content message_showMsg" style="display: none;">
                        	<c:choose>
                        		<c:when test="${!empty couponsPatch.noticSmsContents }">
                        			<textarea id="message_Info" maxlength="140">${couponsPatch.noticSmsContents }</textarea>
                        		</c:when>
                        		<c:otherwise>
                        			<textarea id="message_Info" maxlength="140">亲爱的【username】学员，您好，为了感谢您一直以来对我们学校的支持，现特赠送您一张优惠券，已发放到您的个人中心，请注意查收！</textarea>
                        		</c:otherwise>
                        	</c:choose>
                        </div>
                        <p class="l-note message-info message_showMsg" style="display: none;"><i>您已输入 <em id="writeNum">0</em>个字符，单条短信
                            <em>70</em> 个字符，最多可输入<em>140</em> 个字符</i></p>

                        <p><c:choose>
                        	<c:when test="${couponsPatch.noticWay==0 ||couponsPatch.noticWay==2 }">
                        		<input type="checkbox" checked="checked" name="coupon-notice" id="website_info"/>
                        	</c:when>
                        	<c:otherwise>
                        		<input type="checkbox" name="coupon-notice" id="website_info"/>
                        	</c:otherwise>
                        </c:choose>站内信</p>

                        <div id="show_zhanneixin" style="display: none;">
                            <textarea name="editor01" id="editor01" class="message-edit"></textarea>
                            <div>【username】,【couponinfo】,【coursename】,【startdate】,【enddate】是固定内容，不能修改。</div>
                        </div>
                    </div>
                </div>
                <div class="bnts-wrap">
                    <button class="manage-btn manage-btn-cancel cancle_ing">取消</button>
                    <button class="manage-btn manage-btn-success mr20 save_info" mark="save">保存</button>
                    <button class="manage-btn manage-btn-success btn-give-out save_info" mark="saveAndSend">保存并发放</button>
                </div>
            </div>
            
            <!-- 第一部分完 -->
            <div class="coupon-out-type coupon-downline">
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠名称
                    </div>
                    <div class="fl">
                        <input type="text" value="${couponsPatch.name }" id="offLine_couponseName" class="w210" placeholder="最多只能输入10个汉字" maxlength="10"/>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        优惠说明
                    </div>
                    <div class="fl">
                        <input type="text" value="${couponsPatch.description }" id="offLine_description" class="w310" placeholder="最多只能输入20个汉字" maxlength="20"/>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont"></i>
                        产品类型
                    </div>
                    <div class="fl coupon-type product-type1" >
	                    <span><input type="radio" name="product-type1" value="0" checked="checked" data-type="0">课程</span>
	                    <c:if test="${classPackageService==1 }">
	                   		<span><input type="radio" name="product-type1" value="1" data-type="1">课程包</span>
	                    </c:if>
               		</div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠方式
                    </div>
                    <div class="fl coupon-type" id="offLine_conTypeslist">
                        <span><input type="radio" name="type1" mark="type-a" value="0"/>抵现</span>
                        <span><input type="radio" name="type1" mark="type-b" value="1"/>满减</span>
                        <span><input type="radio" name="type1" mark="type-c" value="2"/>打折</span>

                        <div class="coupon-type-cont">
                            <p class="type-a">面额 <input type="text" value="${couponsPatch.insCashNum }" id="offLine_insCashNum" class="prices"/>元<span id="offLine_insCashNum_msg">当课程原价或优惠后的价格低于抵现金额时,抵现金额不能用.</span></p>

                            <p class="type-b">满 <input type="text" value="${couponsPatch.overNum }" id="offLine_overNum" class="prices"/>减 <input type="text" value="${couponsPatch.overCutNum }" id="offLine_overCutNum" class="prices"/><span>"减"的金额必须大于0</span></p>

                            <p class="type-c">折扣 <input type="text" value="${couponsPatch.discountNum }" class="print_prices" id="offLine_discountNum"/>折</p>
                        </div>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        发放数量
                    </div>
                    <div class="fl">
                        <span>发放<input type="text" value="${couponsPatch.totalNum }" id="offLine_tiketsNums" style="border-radius: 0;width: 80px;margin: 0 10px"/>个</span>

                        <p class="l-note">一次最多只能生成1000个优惠码</p>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠码前缀
                    </div>
                    <div class="fl">
                        <input type="text" value="${couponsPatch.promoCodePrefix }" id="offLine_promoCodePrefix" class="w210" maxlength="4"/>

                        <p class="l-note">只能是英文字母A-Z，限4个字母，不区分大小写，不可与其他优惠码前缀重复</p>
                    </div>
                </div>
<!--                 <div class="coupon-block clear"> -->
<!--                     <div class="t-label fl"> -->
<!--                         <i class="iconfont">&#xe605;</i> -->
<!--                         优惠位数 -->
<!--                     </div> -->
<!--                     <div class="fl"> -->
<%--                         <input type="text" value="${couponsPatch.promoCodeLength }" id="offLine_promoCodeLength" class="w210"/> --%>

<!--                         <p class="l-note">不包含前缀，长度请填写在5-15位之间</p> -->
<!--                     </div> -->
<!--                 </div> -->
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        使用期限
                    </div>
                    <div class="fl">
                        <input type="text" readonly="readonly" value="<fmt:formatDate value="${couponsPatch.timeLimitFrom }" pattern="yyyy-MM-dd" />" name="startTime" id="offLine_startTime" class="date-picker from">
                        <em>——</em>
                        <input type="text" readonly="readonly" value="<fmt:formatDate value="${couponsPatch.timeLimitTo }" pattern="yyyy-MM-dd" />" name="endTime" id="offLine_endTime" class="date-picker to">

                        <p class="l-note">开始时间不早于当天，结束时间不能早于开始时间</p>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont">&#xe605;</i>
                        优惠范围
                    </div>
                    <div class="fl coupon-name comm_useRanges" id="offLine_userRanges">
                        <p><input type="radio" checked="checked" value="0" name="coupon-name1"/>全部课程</p>

                        <p class="item_list_choose2">
                            <input class="class-choose" type="radio" name="coupon-name1" value="1"/>指定范围
                            <span class="coupon-set none">
                                 <select class="itemOne">
                                     
                                 </select>
                            </span>
                           <span class="coupon-set none">
                                <select class="itemTwo">
                                   
                                </select>
                            </span>
                        </p>
                        <p class="item_course_choose2"><input class="class-choose"  type="radio" name="coupon-name1" value="2"/>指定课程
                            <span class="coupon-set none">
                                 <select class="itemOne">
                                 
                                 </select>
                            </span>
                           <span class="coupon-set none">
                                <select class="itemTwo">
                                
                                </select>
                            </span>
                            <span class="coupon-set none">
                                <select class="classes_course">

                                </select>
                            </span>
                        </p>
                    </div>
                </div>
                <div class="bnts-wrap">
                    <button class="manage-btn manage-btn-cancel cancle_ing">取消</button>
                    <button class="manage-btn manage-btn-success mr20 save_info2" mark="save">保存</button>
                    <button class="manage-btn manage-btn-success btn-lead-out save_info2" mark="saveAndSend">保存并导出<span class="manage-button-mask"></span></button>
                </div>
            </div>
            <!-- 第二部分完 -->
        </div>
    </div>
</div>

<div class="add-alert-content point-user">
    <div class="s_box">
        <div class="top">
            <h3>指定用户</h3>
            <i class="s_right iconfont">&#xe610;</i>
        </div>
        <div class="s_cont">
        <form method="post" id="searchForm">
            <div class="inputCont">
                <select name="status" class="registStatus" id="before1" style="width: 113px;">
                    <option value="" selected="selected">前台账号状态</option>
                    <option value="1">启用</option>
                    <option value="0">禁用</option>
                </select>
                <select name="registType" class="registMethods" id="before2" style="width: 112px;">
                    <option value="" selected="selected">前台登录账号</option>
                    <option value="1">已开通</option>
                    <option value="0">未开通</option>
                </select>
                <select name="payStatus" class="payStatus" id="before3" style="width: 88px;">
                    <option value="" selected="selected">报名状态</option>
                    <option value="1">已报名</option>
                    <option value="0">未报名</option>
                </select>
                <input type="text" placeholder="手机号" id="stuMobile" maxlength="11" style="width: 80px; margin-left: 10px;" />
                <input type="text" placeholder="用户名" id="username" style="width: 80px;" />
                <input type="text" placeholder="姓名" id="stuName" style="width: 80px;" />
                <input type="text" placeholder="证件号码" id="sfzh" style="width: 100px;" />
                <a href="JavaScript:void(0);" class="searchContents">搜索</a>
                <b>
                    <input type="checkbox" id="only" class="is_member" value="1"/>
                    <label for="only">只显示会员</label>
                </b>
            </div>
            </form>
            <div class="tabCont">
                <table class="table table-center student-list">
                    <colgroup>
                        <col width="5%">
                        <col width="10%">
                        <col width="10%">
                        <col width="10%">
                        <col width="19%">
                        <col width="10%">
                        <col width="10%">
                        <col width="10%">
                        <col width="8%">
                        <col width="8%">
                    </colgroup>
                    <tbody>
                    <tr class="tr1">
                        <td>
                            <input type="checkbox" class="checkboxAll"/>
                        </td>
                        <td>手机号</td>
                        <td>用户名</td>
                        <td>姓名</td>
                        <td>证件号码</td>
                        <td>创建时间</td>
                        <td>前台账号状态</td>
                        <td>前台登录账号</td>
                        <td>报名状态</td>
                        <td>会员等级</td>
                    </tr>
                    <tr><td colspan="9">暂无数据</td></tr>
                    </tbody>
                </table>
                <div class="pages pagination">
				</div>
                <div class="toggle" id="toggle">
                    <span href="JavaScript:void(0)"><i class="iconfont">&#xe623;</i></span>
                </div>
            </div>
            <div class="choice" id="choice">
                <p>您共选择了<span id="choseStuNum">0</span>位用户,向他们发放优惠</p>
                <div class="group" id="group">
                    
                </div>
            </div>
            <div class="conserve">
                <button class="con">保存</button>
                <button class="delet">取消</button>
            </div>
        </div>
    </div>
</div>

<div class="add-alert-content lead-out-code">
    <div class="s_box">
        <div class="top">
            <h3>导出优惠码</h3>
            <i class="s_right iconfont" onclick="javascript:window.location.href='<%=rootPath %>/companyCouponsConfig/showCouponsList'">&#xe610;</i>
        </div>
        <div class="cont">
            <div class="tabCont">
                <table class="table table-center coupons_libs_List">
                    <colgroup>
                        <col width="20%">
                        <col width="30%">
                        <col width="40%">
                    </colgroup>
                    <tbody>
                    <tr class="tr1">
                        <td>优惠码</td>
                        <td>优惠范围</td>
                        <td>使用期限</td>
                    </tr>
                    <tr><td colspan="3">暂无数据</td></tr>
                    </tbody>
                </table>
                <div class="pages pagination_libs"></div>
<!--                 <p class="q_page"> -->
<!--                     <input type="text"> -->
<!--                     <span><i>/</i>100</span> -->
<!--                     <a class="prev" href="javascript:void(0);"><i class="iconfont">&#xe6c0;</i>上一页</a> -->
<!--                     <a class="next" href="javascript:void(0);">下一页<i class="iconfont">&#xe6bf;</i></a> -->
<!--                 </p> -->
            </div>
        </div>
        <div class="right" style="">
                <span>说明：点击导出直接从浏览器下载</span>
                <a href="javascript:;" class="manage-button export_coupons_data">导出Excel文件 <span class="manage-button-mask"></span></a>
        </div>
    </div>
</div>
<div class="layer-tips-bg"></div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading lp-units-loading send" style="display:none">
	<p><i></i>发送中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
</body>
</html>