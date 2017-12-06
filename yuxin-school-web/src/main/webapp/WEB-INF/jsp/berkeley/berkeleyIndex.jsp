<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>分校首页</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
    <style type="text/css">
        .head-div {
            position: relative;
            margin-top: 15px;
            padding: 3px 8px;
        }

        .font-size {
            font-size: 14px;
            margin-left: 10px;
            margin-right: 11px;
        }
    </style>
    <%--tob--%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
<div class="u-wrap admin overflow">
    <div>
        <div class="heading">
            <h2 class="h5">分校列表</h2>
            <span class="line"></span>
        </div>
        <div style="margin-top: 10px;">
            <input type="text" id="schoolName" name="schoolName" placeholder="分校名称"/>
            <select name="eduArea" id="eduArea">
                <option value="">请选择区域</option>
                <c:forEach items="${areas}" var="area" >
                    <option value="${area.itemCode}" data-id="${area.id}" ${eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
                </c:forEach>
            </select>
            <span>创建时间</span>
            <span><input type="text" name="startTime" class="date-picker from" id="startTime"/><em>到</em><input type="text" name="endTime" class="date-picker to" id="endTime"/></span>
            <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
            <button class="btn btn-primary createBranch">创建分校</button>
        </div>
        <div class="user-list">
            <table class="table table-center" id="tableList">
                <tr data-buy="true">
                    <th width="3%">编号</th>
                    <th width="10%">组织机构代码</th>
                    <th width="10%">分校名称</th>
                    <th width="10%">所属区域</th>
                    <th width="10%">创建时间<img src="/images/upDown.png" alt=""></th>
                    <th width="10%">注册学生人数<img src="/images/upDown.png" alt=""></th>
                    <th width="10%">课程数<img src="/images/upDown.png" alt=""></th>
                    <th width="10%">班级数<img src="/images/upDown.png" alt=""></th>
                    <th width="15%">操作</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td class="slink">
                        <a class="showSignUp" mobile="" uname="sdsdsd" href="javascript:void(0);">详情</a>|
                        <a class="studentDetail" mobile="" uname="sdsdsd" href="javascript:void(0);">查看官网</a>|
                        <a class="more" href="javascript:void(0);">
                            更多
                        </a>
                        <ul class="none box" style="display: none;">
                            <li><a class=""  href="javascript:void(0);">订单查询</a></li>
                            <li><a class=""  href="javascript:void(0);">权限管理</a></li>
                            <li><a class=""  href="javascript:void(0);">课程管理</a></li>
                            <li><a class=""  href="javascript:void(0);">服务管理</a></li>
                            <li><a class=""  href="javascript:void(0);">老师管理</a></li>
                            <li><a class=""  href="javascript:void(0);">分校课程</a></li>
                            <li><a class=""  href="javascript:void(0);">计算资源</a></li>
                        </ul>
                    </td>
                </tr>
            </table>
            <div class="pages pagination">
            </div>
        </div>
    </div>
</div>
<input type="hidden" value="5" id="pageSize">

<%--弹窗begin--%>
<div class="popupContainer">
    <h3>添加分校</h3>
    <div class="addSchool">
            <ul>
                <li>
                    <label>分校机构代码:</label>
                    <input type="text" name="branchCode" id="branchCode">
                    <button class="btn btn-primary" id="searchBranchSchool">搜索</button>
                </li>
                <li>
                    <label>分校:</label>
                    <label id="branchSchool" name="branchSchool"></label>
                </li>
                <li>
                    <label>所属区域:</label>
                    <label id="eduArea" name="eduArea"></label>
                </li>
                <li>
                    <label>学校性质:</label>
                    <select id="schoolProperties">
                        <c:forEach items="${schoolPros}" var="schoolPro" >
		                    <option value="${schoolPro.itemCode}" data-id="${schoolPro.id}"}>${schoolPro.itemValue}</option>
		                </c:forEach>
                    </select>
                </li>
                <li>
                    <label>联系人:</label>
                    <input type="text" name="linkPerson" id="linkPerson">
                </li>
                <li>
                    <label>联系方式:</label>
                    <input type="text" name="linkPhone" id="linkPhone">
                </li>
                <li>
                    <label>分校域名:</label>
                    www.
                    <input type="text" name="domain" id="domain">
                    .cdds365.com
                </li>
                <li>
                    <label>分校后台域名:</label>
                    www.
                    <input type="text" name="domainManage" id="domainManage">
                    .manage.cdds.com
                </li>
                <li>
                    <label>收费配置:</label>
                    <p>
                        <label>学校私有课程收费比例:</label>
                        <input type="text" id="privateCost" name="privateCost">
                    </p>
                    <p>
                        <label>学校开放课程收费比例：</label>
                        <input type="text" id="publicCost" name="publicCost">
                    </p>
                </li>
                <li>
                    <label>资源分配</label>
                    <p>
                        流量
                        <input type="text" id="flowSize" name="flowSize">
                        GB
                    </p>
                    <p>
                        空间
                        <input type="text" id="spaceSize" name="spaceSize">
                        GB
                    </p>
                </li>
                <li>
                    <label for="">cc账号:</label>
                    <input type="text" name="ccUserName" id="ccUserName"/><br/>
                    <input type="password" name="ccPwd" id="ccPwd"/>
                </li>
                <li>
                    <label for="">展视互动账号:</label>
                    <input type="text" name="zsUserName" id="zsUserName"/>
                    <input type="password" name="zsPwd" id="zsPwd"/>
                </li>
                <li>
                    <label>学校简介：</label>
                    <textarea cols="30" rows="10" id="schoolSummary" name="schoolSummary"></textarea>
                </li>
                <li class="submitSchool">
                    <button class="btn btn-success submitSchoolSure">确定</button>
                    <button class="btn btn-danger submitSchoolCancel">取消</button>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="popupOpacity"></div>
<%--弹窗end --%>
<input type="hidden" id="selectCounts" value="10">
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/branchschool/berkeley.js"></script>--%>
<!--  ajax加载中div结束 -->
<script>
//    点击更多显示菜单
    $('.more').mouseenter(function(){
        $(this).siblings('ul').show();
    });
    $('.more').mouseleave(function(){
        $(this).siblings('ul').hide();
    });
    $('.box').mouseenter(function(){
        $(this).show();
    });
    $('.box').mouseleave(function(){
        $(this).hide();
    });

</script>

<script>
//    点击更多显示菜单
    $('.more').mouseenter(function(){
        $(this).siblings('ul').show();
    });
    $('.more').mouseleave(function(){
        $(this).siblings('ul').hide();
    });
    $('.box').mouseenter(function(){
        $(this).show();
    });
    $('.box').mouseleave(function(){
        $(this).hide();
    });

</script>
<script>
//        二级菜单加active
    $selectSubMenu('course_class_type');
</script>
</body>
</html>
