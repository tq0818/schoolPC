<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>分校课程查询</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/system/order.js"></script>--%>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/berkeley.js"></script>--%>
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
<div class="u-wrap admin overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_berkeleyLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="heading">
            <h2 class="h5">分校课程查询</h2>
            <span class="line"></span>
        </div>
        <div style="margin-top: 10px;">
            <input type="text" id="sfzh" name="identityId" placeholder="课程名称/科目/老师"/>
            <select name="eduArea" id="eduArea">
                <option value="">请选择课程类型</option>
                <option value="1">1</option>
                <option value="1">2</option>
            </select>
            <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
        </div>
        <div class="user-list">
            <table class="table table-center" id="tableList">
                <tr data-buy="true">
                    <th width="3%">序号</th>
                    <th width="10%">课程名称</th>
                    <th width="10%">学科</th>
                    <th width="10%">授课老师</th>
                    <th width="10%">课程类型</th>
                    <th width="10%">
                        报名人数
                        <i class="icon iconfont unsort sorting">&#xe612;</i>
                    </th>
                    <th width="10%">
                        购买人数
                        <i class="icon iconfont unsort sorting">&#xe612;</i>
                    </th>
                    <th width="10%">课程详情</th>
                </tr>
                <%--<c:choose>--%>
                <%--<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">--%>
                <%--<tr><td colspan="15">暂无数据</td></tr>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                <%--<tr><td colspan="14">暂无数据</td></tr>--%>
                <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <tr>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td><a href="##">点击查看</a></td>
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
                <label>学校机构代码:</label>
                <input type="text">
                <button class="btn btn-primary">搜索</button>
            </li>
            <li>
                <label>学校:</label>
                <input type="text">
            </li>
            <li>
                <label>所属区域:</label>
                <select >
                    <option value="1">锦江</option>
                    <option value="1">锦江</option>
                    <option value="1">锦江</option>
                </select>
            </li>
            <li>
                <label>学校性质:</label>
                <select >
                    <option value="1">锦江</option>
                    <option value="1">锦江</option>
                    <option value="1">锦江</option>
                </select>
            </li>
            <li>
                <label>联系人:</label>
                <input type="text">
            </li>
            <li>
                <label>联系方式:</label>
                <input type="text">
            </li>
            <li>
                <label>分校域名:</label>
                www.
                <input type="text">
                .cdds365.com
            </li>
            <li>
                <label>分校后台域名:</label>
                www.
                <input type="text">
                .manage.cdds.com
            </li>
            <li>
                <label>收费配置:</label>
                <p>
                    <label>学校私有课程收费比例:</label>
                    <input type="text">
                </p>
                <p>
                    <label>学校开放课程收费比例：</label>
                    <input type="text">
                </p>
            </li>
            <li>
                <label>资源分配</label>
                <p>
                    流量
                    <input type="text">
                    GB
                </p>
                <p>
                    空间
                    <input type="text">
                    GB
                </p>
                <p>
                    短信
                    <input type="text">
                    条
                </p>
            </li>
            <li>
                <label for="">cc账号:</label>
                <input type="text"><br/>
                <input type="password">
            </li>
            <li>
                <label for="">展视互动账号:</label>
                <input type="text">
                <input type="password">
            </li>
            <li>
                <label>学校简介：</label>
                <textarea name="" id="" cols="30" rows="10"></textarea>
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

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
    $("#startTime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
    $("#endTime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
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
    //    左侧active切换
    $selectSubMenus('classQueryGetClassList');
</script>

</body>
</html>