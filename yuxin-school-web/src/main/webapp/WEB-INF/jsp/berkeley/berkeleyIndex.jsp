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
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
<div class="u-wrap admin overflow berkeleyIndex">
    <div>
        <div class="heading">
            <h2 class="h5">分校列表</h2>
            <span class="line"></span>
        </div>
        <div style="margin-top: 10px;">
            <input type="text" id="companyName" name="companyName" placeholder="分校名称"/>
            <select name="eduArea" id="eduArea">
                <option value="">请选择区域</option>
                <c:forEach items="${areas}" var="area" >
                <option value="${area.itemCode}"  ${eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
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
                    <th width="10%" class="btn-sort time" >
                   		创建时间<input type="hidden" id="time" value="1"/>
                    </th>
                    <th width="10%" class="btn-sort penNum">
                   		注册学生人数<input type="hidden" id="penNum" value="4"/>
                    </th>
                    <th width="10%" class="btn-sort lessNum">
                    	课程数<input type="hidden" id="lessNum" value="8"/>
                    </th>
                    <th width="10%" class="btn-sort clasNum">
                    	班级数<input type="hidden" id="clasNum" value="6"/>
                    </th>
                    <th width="15%">操作</th>
                </tr>
               
                <c:forEach items="${companyList.data}" var="cp" varStatus="i">
                <tr>
                	<td>${i.index+1}</td>
                    <td>${cp.eduAreaSchool}111</td>
                    <td>${cp.companyName}</td>
                    <td>${cp.eduArea}</td>
                    <td>${cp.eduArea}</td>
                    <td>${cp.registStudentCounts}</td>
                    <td>${cp.classTypeCounts}</td>
                    <td>${cp.classCounts}</td>
                    <td class="slink">
                        <a class="showSignUp" mobile="" uname="sdsdsd" href="javascript:void(0);" onclick="detail(${cp.companyId})">详情</a>|
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
                 </c:forEach>
            </table>
           	<div class="pages pagination"></div>
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
                    <label style="margin-right: 10px;">分校机构代码<i style="color: red;" class="iconfont ico"></i></label>
                    <input type="text" name="branchCode" id="branchCode">
                    <input type="hidden" name="isArea" id="isArea">
                    <button class="btn btn-primary" id="searchBranchSchool" style="margin-left: 5px;">搜索</button>
                </li>
                <li>
                    <label style="margin-right: 60px;">分校:</label>
                    <label id="branchSchool" name="branchSchool"></label>
                </li>
                <li>
                    <label style="margin-right: 35px;">所属区域:</label>
                    <label id="eara" name="eara"></label>
                </li>
                <li>
                    <label style="margin-right: 35px;">学校性质<i style="color: red;" class="iconfont ico"></i></label>
                    <select id="schoolProperties" style="width: 164px;">
                    	<option value="">无</option>
                        <c:forEach items="${schoolPros}" var="schoolPro" >
		                    <option value="${schoolPro.itemCode}" data-id="${schoolPro.id}"}>${schoolPro.itemValue}</option>
		                </c:forEach>
                    </select>
                </li>
                <li>
                    <label style="margin-right: 47px;">联系人<i style="color: red;" class="iconfont ico"></i></label>
                    <input type="text" name="linkPerson" id="linkPerson">
                </li>
                <li>
                    <label style="margin-right: 35px;">联系方式<i style="color: red;" class="iconfont ico" ></i></label>
                    <input type="text" name="linkPhone" id="linkPhone" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" maxlength="11" >
                </li>
                <li>
                    <label>分校域名<i style="color: red;" class="iconfont ico"></i></label><br/>
                    <span style="margin-left: 55px;">http://</span>
                    <input type="text" name="domain" id="domain">
                    <span>.cdds365.com</span>
                </li>
                <li>
                    <label>分校后台域名<i style="color: red;" class="iconfont ico"></i></label><br/>
                    <span style="margin-left: 55px;">http://</span>
                    <input type="text" name="domainManage" id="domainManage">
                    <span>.manage.cdds.com</span>
                </li>
                <li>
                    <label>收费配置:</label>
                    <p>
                        <label style="margin-left: 40px;">学校私有课程收费比例<i style="color: red;" class="iconfont ico"></i></label><br/>
                        <input style="margin-left: 85px;" type="text" id="privateCost" name="privateCost" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/">%
                    </p>
                    <p>
                        <label style="margin-left: 40px;">学校开放课程收费比例<i style="color: red;" class="iconfont ico"></i></label><br/>
                        <input style="margin-left: 85px;" type="text" id="publicCost" name="publicCost" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/">%
                    </p>
                </li>
            </ul>
            <ul style="float:right;">
                <li style="margin-bottom: 10px">
                    <label>资源分配</label>
                    <p style="margin-left: 30px;margin-bottom: 8px;">
                        流量<i style="color: red;" class="iconfont ico"></i>
                        <input type="text" id="flowSize" name="flowSize" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/">
                        GB
                    </p>
                    <p style="margin-left: 30px;">
                        空间<i style="color: red;" class="iconfont ico"></i>
                        <input type="text" id="spaceSize" name="spaceSize" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/"">
                        GB
                    </p>
                </li>
                <li style="margin-bottom: 10px">
                    <label for="">cc账号:</label><br/>
                    <i style="color: red;" class="iconfont ico"></i><input style="margin-left: 55px;margin-bottom: 5px;" type="text" placeholder="账号" name="ccUserName" id="ccUserName"/><br/>
                    <i style="color: red;" class="iconfont ico"></i><input style="margin-left: 55px;" type="password" placeholder="密码" name="ccPwd" id="ccPwd"/>
                </li>
                <li style="margin-bottom: 10px">
                    <label for="">展视互动账号:</label><br/>
                    <i style="color: red;" class="iconfont ico"></i><input style="margin-left: 55px;margin-bottom: 5px;" type="text"  placeholder="账号" name="zsUserName" id="zsUserName"/><br/>
                    <i style="color: red;" class="iconfont ico"></i><input style="margin-left: 55px;" type="password" placeholder="密码" name="zsPwd" id="zsPwd"/>
                </li>
                <li>
                    <label>分校简介<i style="color: red;" class="iconfont ico"></i></label><br/>
                    <textarea style="margin-left: 55px;" cols="50" rows="10" id="schoolSummary" name="schoolSummary" placeholder="最多输入200个字符"></textarea>
                </li>
            </ul>

        <div class="submitSchool">
            <button  type="button" onclick="addBerkeley(0)" class="btn btn-success ">确定</button>
            <button  type="button" class="btn btn-danger submitSchoolCancel">取消</button>
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
    <script type="text/javascript" src="<%=rootPath%>/javascripts/branchschool/berkeley.js"></script>
<!--  ajax加载中div结束 -->
<script>
//    点击更多显示菜单
    $('table').on('mouseenter','.more',function(){
        $(this).siblings('ul').show();
    });
    $('table').on('mouseleave','.more',function(){
        $(this).siblings('ul').hide();
    });
    $('table').on('mouseenter','.box',function(){
        $(this).show();
    });
    $('table').on('mouseleave','.box',function(){
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
