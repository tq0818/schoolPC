<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>行政班设置</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<div class="u-wrap admin overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_administrativeLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="heading">
            <h2 class="h5">行政班设置</h2>
            <span class="line"></span>
        </div>
        <div>
            <div class="classSetting">
                <h5>小学</h5>
                <ul>
                    <li>
                        小2012级<input type="text" value="23" disabled="disabled">个班
                    </li>
                    <li>
                        小2012级<input type="text" value="23" disabled="disabled">个班
                    </li>
                    <li>
                        小2012级<input type="text" value="23" disabled="disabled">个班
                    </li>
                    <li>
                        小2012级<input type="text" value="23" disabled="disabled">个班
                    </li>
                    <li>
                        小2012级<input type="text" value="23" disabled="disabled">个班
                    </li>
                    <li>
                        小2012级<input type="text" value="23" disabled="disabled">个班
                    </li>
                </ul>
            </div>
            <div class="classSetting">
                <h5>初中</h5>
                <ul>
                    <li>
                        初2015级<input type="text" value="5" disabled="disabled">个班
                    </li>
                    <li>
                        初2015级<input type="text" value="5" disabled="disabled">个班
                    </li>
                    <li>
                        初2015级<input type="text" value="5" disabled="disabled">个班
                    </li>
                </ul>
            </div>
            <div class="classSetting">
                <h5>高中</h5>
                <ul>
                    <li>
                        高2015级<input type="text" value="66" disabled="disabled">个班
                    </li>
                    <li>
                        高2015级<input type="text" value="66" disabled="disabled">个班
                    </li>
                    <li>
                        高2015级<input type="text" value="66" disabled="disabled">个班
                    </li>
                </ul>
            </div>
        </div>
        <div class="classSettingBtn">
            <button class="btn btn-warning editClassNum">编辑</button>
            <button class="btn btn-success saveClassNum">保存</button>
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
<script type="text/javascript">
    $(function() {
        $selectSubMenu('financial');
        $selectSubMenus('operate_fee_confirm');
    });
</script>

<script>
//    点击编辑,进入编辑状态
    $('.editClassNum').click(function(){
        $('.classSetting').addClass('classSettingEdit');
        $('.classSetting').find('input').attr('disabled',false);
    });
//    点击保存，进入只读模式
    $('.saveClassNum').click(function(){
        alert("保存成功！");
        $('.classSetting').removeClass('classSettingEdit');
        $('.classSetting').find('input').attr('disabled',true);
    });
//    误操作，跳转到其他链接，弹窗提醒
    function confirmPopup() {
       $.confirm("您修改的数据未保存，确定退出吗？",function(s){
            if(s==true){
             console.log("退出");
            }else {
                console.log("不退出");
            }
        });
    }
</script>

<script>
//    二级菜单加active
    $(function(){
        $selectSubMenu('org_service');
    });
</script>

<script>
    //    左侧active切换
    $selectSubMenus('administrativeClass');
</script>
</body>
</html>