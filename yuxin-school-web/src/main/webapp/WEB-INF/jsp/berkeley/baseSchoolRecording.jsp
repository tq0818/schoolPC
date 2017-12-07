<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>基地校录播课</title>
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
<div class="u-wrap admin overflow baseSchoolBg">

    <div class="heading baseSchoolHead">
        <h2 class="h5">基地校</h2>
        <a href="<%=rootPath%>/baseSchool/baseSchoolRecording" class="btn btn-primary recordingBtn">录播课</a>
        <a href="<%=rootPath%>/baseSchool/baseSchoolLive" class="btn btn-default liveBtn">直播课</a>
        <span class="line"></span>
    </div>
    <div style="margin-top: 10px;">
        <ul class="baseSchoolLiveLabel">
            <li>
                <label>区域：</label>
                <select>
                    <option value="">请选择区域</option>
                    <option value="">天府新区</option>
                    <option value="">天府新区</option>
                    <option value="">天府新区</option>
                </select>
            </li>
            <li>
                <label>学校：</label>
                <select>
                    <option value="">请选择学校</option>
                    <option value="">1</option>
                    <option value="">1</option>
                    <option value="">1</option>
                </select>
            </li>
            <li>
                <label>学段：</label>
                <select>
                    <option value="">请选择学段</option>
                    <option value="">2</option>
                    <option value="">2</option>
                    <option value="">2</option>
                </select>
            </li>
            <li>
                <label>入学年份：</label>
                <select>
                    <option value="">请选择入学年份</option>
                    <option value="">4</option>
                    <option value="">4</option>
                    <option value="">4</option>
                </select>
            </li>
            <li>
                <label>班级：</label>
                <select>
                    <option value="">请选择入学班级</option>
                    <option value="">4</option>
                    <option value="">4</option>
                    <option value="">4</option>
                </select>
            </li>
        </ul>
        <span class="chooseData">日期 :</span>
        <span>
                <input type="text" name="startTime" class="date-picker from" id="startTime"/>
                <em>到</em>
                <input type="text" name="endTime" class="date-picker to" id="endTime"/>
            </span>
        <input type="text" placeholder="学员手机号/用户名查询">
        <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
        <span><a href="javascript:;" class="btn btn-primary checkOutData">导出数据</a></span>
    </div>
    <div class="user-list">
        <table class="table table-center" id="tableList">
            <tr data-buy="true">
                <th width="10%">课程名称</th>
                <th width="10%">用户名</th>
                <th width="10%">姓名</th>
                <th width="10%">学校</th>
                <th width="10%">学段</th>
                <th width="10%">班级</th>
                <th width="15%">总播放时长<img src="/images/upDown.png" alt=""></th>
                <th width="15%">播完率<img src="/images/upDown.png" alt=""></th>
                <th width="15%">观看次数<img src="/images/upDown.png" alt=""></th>
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
                <td>1</td>
            </tr>
        </table>
        <div class="pages pagination">

        </div>
    </div>

</div>
<input type="hidden" value="5" id="pageSize">

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->

<%--调用日期插件--%>
<script type="text/javascript">
    $("#startTime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
    $("#endTime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
</script>
<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('baseSchoolLive');
    });
</script>
</body>
</html>