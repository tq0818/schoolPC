<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>分校开放课程管理</title>
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
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap admin overflow baseSchoolBg">

    <div class="heading baseSchoolHead">
        <h2 class="h5">课程列表</h2>
        <a href="##" class="btn btn-primary batches">批量下架</a>
        <a href="##"  class="btn btn-primary batchesShelves">批量上架</a>
        <span class="line"></span>
    </div>
    <div style="margin-top: 10px;">
        <ul class="baseSchoolLiveLabel">
            <li><input type="text" placeholder="请输入课程名称"></li>
            <li>
                <select>
                    <option value="">请选择区域</option>
                    <option value="">天府新区</option>
                    <option value="">天府新区</option>
                    <option value="">天府新区</option>
                </select>
            </li>
            <li>
                <select>
                    <option value="">请选择学校</option>
                    <option value="">1</option>
                    <option value="">1</option>
                    <option value="">1</option>
                </select>
            </li>
            <li>
                <select>
                    <option value="">分类</option>
                    <option value="">2</option>
                    <option value="">2</option>
                    <option value="">2</option>
                </select>
            </li>
            <li>
                <select>
                    <option value="">学段</option>
                    <option value="">4</option>
                    <option value="">4</option>
                    <option value="">4</option>
                </select>
            </li>
            <li>
                <select>
                    <option value="">学科</option>
                    <option value="">4</option>
                    <option value="">4</option>
                    <option value="">4</option>
                </select>
            </li>
            <li>
                <select>
                    <option value="">上下架状态</option>
                    <option value="">4</option>
                    <option value="">4</option>
                    <option value="">4</option>
                </select>
            </li>
        </ul>
        <span class="chooseData">添加时间 :</span>
        <span>
                <input type="text" name="startTime" class="date-picker from" id="startTime"/>
                <em>到</em>
                <input type="text" name="endTime" class="date-picker to" id="endTime"/>
        </span>
        <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
        <span><a href="javascript:;" class="btn btn-primary checkOutData">导出数据</a></span>
    </div>
    <div class="user-list">
        <table class="table table-center" id="tableList">
            <tr data-buy="true">
                <th width="5%"><input type="checkbox" class="checkboxAll"></th>
                <th width="10%">课程名称</th>
                <th width="10%">所属学校</th>
                <th width="5%">分类</th>
                <th width="10%">学段</th>
                <th width="10%">学科</th>
                <th width="10%">上下架状态</th>
                <th width="15%">添加时间</th>
                <th width="15%">操作</th>
            </tr>
            <tr>
                <td><input type="checkbox" class="signUpMany"></td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>
                    <a class="showSignUp" mobile="" uname="sdsdsd" href="javascript:void(0);">上架</a>|
                    <a class="studentDetail" mobile="" uname="sdsdsd" href="javascript:void(0);">课程详情</a>|
                    <a class="more" href="javascript:void(0);">
                        管理
                    </a>
                    <ul class="none box openCourseManage" style="">
                        <li><a class=""  href="javascript:void(0);">基本信息</a></li>
                        <li><a class=""  href="javascript:void(0);">排课表</a></li>
                        <li><a class=""  href="javascript:void(0);">课程详情</a></li>
                        <li><a class=""  href="javascript:void(0);">学员管理</a></li>
                        <li><a class=""  href="javascript:void(0);">课程资料</a></li>
                        <li><a class=""  href="javascript:void(0);">直播上课统计</a></li>
                    </ul>
                </td>
            </tr>
        </table>
        <div class="pages">
            <ul class="paginations">

            </ul>
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

<script>
//    分页
    $(".paginations").pagination('', {
        next_text : "下一页",
        prev_text : "上一页",
        current_page : '',
        link_to : "javascript:void(0)",
        num_display_entries : 8,
        items_per_page : '',
        num_edge_entries : 1,
        callback : function(page, jq) {
            var pageNo = page + 1;
//            selOneAns(pageNo);
        }
    });
</script>
<%--调用日期插件--%>
<script type="text/javascript">
    //    $(function() {
    //        $selectSubMenu('financial');
    //        $selectSubMenus('operate_fee_confirm');
    //    });
    $("#startTime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
    $("#endTime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
</script>
<script>
//    批量上架
    $('.batchesShelves').click(function(){
        $.confirm("是否批量上架？",function(s){
            if(s==true){
                alert('批量上架成功！');
            }
        });
    });
//    批量下架
    $('.batches').click(function(){
        $.confirm("是否批量下架？",function(s){
            if(s==true){
                alert('批量下架成功！');
            }
        });
    });

</script>
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
    console.log(rootPath);

</script>
<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('course_class_type');
    });
</script>
</body>
</html>