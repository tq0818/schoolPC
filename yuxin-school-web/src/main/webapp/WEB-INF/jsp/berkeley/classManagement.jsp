<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>课程管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/classes.css"/>
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
<div class="u-wrap admin overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_berkeleyLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="heading">
            <h2 class="h5">分校直播课程管理</h2>
            <span class="line"></span>
        </div>
        <div style="margin-top: 10px;">
            <input type="text" id="sfzh" name="identityId" placeholder="课程名称/科目"/>
            <select name="" id="">
                <option value="">请选择直播课状态</option>
                <option value="">未开始</option>
            </select>
            <select name="" id="">
                <option value="">请选择权限状态</option>
                <option value="">已开通</option>
                <option value="">已关闭</option>
            </select>

            <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
            <button class="btn btn-primary addCourse">添加课程</button>
        </div>
        <div class="user-list">
            <table class="table table-center" id="tableList">
                <tr data-buy="true">
                    <th width="5%">序号</th>
                    <th width="10%">课程名称</th>
                    <th width="10%">学科</th>
                    <th width="10%">所属学校</th>
                    <th width="10%">直播课状态</th>
                    <th width="10%">权限</th>
                    <th width="10%">操作</th>
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
                    <td>行程问题</td>
                    <td>物理</td>
                    <td>芳草小学</td>
                    <td>进行中</td>
                    <td>已开通</td>
                    <td class="slink">
                        <a class="" href="javascript:void(0);">课程详情</a>|
                        <a class="classSchedule"  href="javascript:void(0);">课程表</a>|
                        <a class="changeStatus" href="javascript:void(0);">关闭</a>
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
                <label>学校机构代码:</label>
                <input type="text">
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

<%--课程表弹窗--%>
<div class="popupContainerCourse">
    <h5>课程表</h5>
    <div class="popupCourseContent">
            <ul class="sortable base-sort item-panel courseliList ui-sortable">
                <li class="item item-chapter" id="father131" ids="131" sort="1" modulenoid="47">
                    <div class="ball"></div><div class="line"></div>
                    <div class="content">
                        <div class="tt">
                            <span class="h3">1单元</span>
                            <span class="sta">已结课</span><span>共42课时</span></div>
                        <div class="tt none">
                            <span class="h3"><input type="text" value="1单元" class="addcou"></span>
                            <a href="javascript:void(0);" class="btn btn-default savecon">保存</a>
                        </div>
                    </div>
                </li>
            </ul>
    </div>
    <button class="btn btn-primary closeCourse">关闭</button>
</div>
<script>
    $('.classSchedule').click(function(){
        $('.popupContainerCourse').show();
        $('.popupOpacity').show();
    });
    $('.closeCourse').click(function(){
        $('.popupContainerCourse').hide();
        $('.popupOpacity').hide();
    });
    $('.changeStatus').click(function(){
        if($(this).html()=='关闭'){
            $(this).html('开通');
        }else{
            $(this).html('关闭');
        }
    });
</script>

<%--添加课程弹窗--%>
<div class="popupAddCourse">
    <h5>添加课程</h5>
    <button class="btn btn-primary closePopupAddCourse">关闭</button>
    <div>
        <div>
            <label for="">课程名称:</label>
            <input type="text">
        </div>
        <div>
            <label for="">课程所属学校:</label>
            <input type="text">
        </div>
        <button class="btn btn-primary">搜索</button>
    </div>
    <div class="user-list">
            <table class="table table-center">
                <tbody><tr data-buy="true">
                    <th width="5%">序号</th>
                    <th width="10%">课程名称</th>
                    <th width="10%">课程</th>
                    <th width="10%">所属学校</th>
                    <th width="10%">课程详情</th>
                    <th width="10%">操作</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td>1</td>
                    <td><a href="##">点击查看</a></td>
                    <td><button class="btn btn-primary">添加</button></td>
                </tr>
                </tbody></table>
            <div class="pages pagination">

            </div>
        </div>
</div>
<script>
    $('.addCourse').click(function(){
        $('.popupAddCourse').show();
        $('.popupOpacity').show();
    });
    $('.closePopupAddCourse').click(function(){
        $('.popupAddCourse').hide();
        $('.popupOpacity').hide();
    });
</script>

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
    //    左侧active切换
    $selectSubMenus('classManagerGetClassList');
</script>
</body>
</html>