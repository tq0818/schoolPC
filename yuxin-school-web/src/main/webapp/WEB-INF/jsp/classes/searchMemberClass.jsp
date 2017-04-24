<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>会员课程</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/custom-page.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/class-manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/vip.css"/>
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap company overflow">
    <div class="left-side">
        <!--<div class="left-side-title">返回</div>-->
        <div class="left-side-title">
            <em>会员管理</em>
<!--             <span class="iconfont return-pic">&#xe650;</span> -->
        </div>
        <ul>
            <li class="subentry active member_ClassType">会员课程</li>
         <!--   <li class="subentry">兑换码</li> -->
        </ul>
    </div>
    <input type="hidden" id="memberId" value="${cmlc.id }"/>
    <input type="hidden" id="memberLevel" value="${cmlc.name }"/>
    <input type="hidden" id="discount" value="${cmlc.discount }"/>
    <input type="hidden" id="discountType" value="${cmlc.discountType }"/>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">${cmlc.name }</span>
                    <span class="r"><em class="iconfont">&#xe652;</em>添加课程</span>
                </div>
            </div>
            <div class="select-content">
                <div class="memberClassList">
                    
                 </div>
                  <div class="page-btn-box" style="width: 100%;">
			        	<div class="pages pagination1">
			                
						</div>
			       </div>
            </div>
            <div class="page-btn-box"></div>
        </div>
    </div>
</div>
<div class="pop-fixed choice-class-pop" style="">
    <div class="select-board cum-detail">
        <div class="board-title">
            <span>选择课程</span>
            <em class="iconfont pop-close-btn">&#xe610;</em>
        </div>
        <div class="select-content">
            <div>
                <div class="selects">
                    <select name="xk" id="itemOne1" onchange="queryItemSecond()" style="width: 90px;">
                    </select>
                    <select name="km" id="itemTwo1">
                    </select>
                    <input type="text" placeholder="课程名称" class="input-name" id="searchName" style="width: 100px" value="" />
                    <span id="searchButton">查询</span>
                </div>
                <div class="list">
                </div>
                  <div class="page-btn-box" style="width: 100%;">
			        	<div class="pages pagination">
			                
						</div>
			       </div>
            </div>
        </div>
        <div class="page-btn-box"></div>
        <div class="choice-class-box bottomButtom">
            <span class="yes-btn">确认</span>
            <span class="cancel-btn">取消</span>
        </div>
    </div>
</div>
<div class="add-page-pop">
    <div class="add-page-board">
        <p class="page-pop-title"><span>${cmlc.name }</span><em class="iconfont">&#xe610;</em></p>
        <div style="padding: 10px 50px;">
            <div style="line-height: 22px;">
                <input type="radio"  name="openWay" id="usediscount"  style="position: relative; top: 2px;margin-right: 3px;" value="1"/>打折优惠
            </div>
            <div class="pric-box">
                <p>原价：</p>
                <p><input class="pric"  id="pric" type="text" value=""/>元</p>
            </div>
            <div class="pric-box">
                <p>折扣：</p>
                <p><input class="pric-text print_prices printYesColor" id="course_discount" type="text" value=""/>折</p>
            </div>
            <div class="pric-box">
                <p>减价：</p>
                <p><input class="pric-text prices printYesColor print_chaPrice"  id="chaPrice" type="text" value=""/>元</p>
            </div>
            <div class="pric-box">
                <p>减价后：</p>
                <p><input class="pric-text prices printYesColor print_youhuiPrice"  id="youhuiPrice" type="text"/>元</p>
            </div>
            <div class="clear"></div>
            <div style="line-height: 22px;margin: 20px 0 30px">
                <input type="radio" name="openWay" id="freelook" style="position: relative; top: 2px;margin-right: 3px;" value="0"/>免费观看
            </div>
            <p class="c text-center">
                <a href="javascript:void(0);" class="btn btn-primary save_member_set">保存<input type="hidden" id="discount_classTypeId" value="0"/></a>
                <a href="javascript:void(0);" class="btn btn-default cancle_qx">取消</a>
            </p>
        </div>
    </div>
</div>
<input type="hidden" id="page" value="1">
<input type="hidden" id="page1" value="1">
<input type="hidden" id="dclick" value="0"/>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>

<script src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery-ui.js"></script>
<script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script src="<%=rootPath %>/javascripts/splitmarket.js"></script>
<script src="<%=rootPath %>/javascripts/classedit.js"></script>
<script src="<%=rootPath %>/javascripts/class-manage.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class/cousePackage/packageManage.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>

<script type="text/javascript" src="<%=rootPath %>/javascripts/class/searchMemberClass.js"></script>
</body>
</html>


