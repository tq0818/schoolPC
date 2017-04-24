<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp" %>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
    <script type="text/javascript" >
    var rootPath='<%=rootPath%>';
    </script>
    <link rel="icon" href="<%=rootPath%>/images/favicon.ico" type="image/x-icon" />
    <title>证书</title>
    <!-- 日期控件样式 -->
    <!-- 日期控件样式结束 -->
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/system.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/coupon.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/conpon-alert.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage-screen.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fonts/iconfont.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/tiku/tiku_fatstyle.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/tiku/tiku_manage.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/tiku/tiku.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/sidebar/sidebar.css" />
	<link rel="stylesheet" href="<%=rootPath%>/stylesheets/resource.css"/>
	<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manager.css" />
	<link rel="stylesheet" href="<%=rootPath%>/stylesheets/newresource.css" /> 
    <style type="text/css">
        .coupon-use-detail table th a{height: 100%;display: inline-block}
        .coupon-use-detail table th a:link,.coupon-use-detail table th a:visited{color: #474747; }
        .coupon-use-detail table th a:hover,.coupon-use-detail table th a:active{ color:#474747; }
        .coupon-use-detail .sort-btn .chang_one{color:dodgerblue;}
    </style>
    <style>
    .iconfontH{color: red;}
    .tipsH{color: red;}
    .iii{
        position: absolute;
        top: 23px;
        right: -8px;
        width: 0;
        height: 0;
        border: 5px solid transparent;
        border-top-color: #ddd;
    }
    .cc{
        height: 62px;
        padding: 0 1%;
        line-height: 1.5;
    }
    .alert {
        padding: 15px;
        border: 1px solid transparent;
        margin:0 auto;
    }

    .alert-warning {
        color: #c09853;
        background-color: #fcf8e3;
        border-color: #fbeed5
    }
    .alert-dismissable .cloze {
        position: relative;
        right: 50px;
        color: inherit;
        font-size:1.6rem;
        cursor:pointer;
    }
    .cloze {
        float: right;
        font-size: 21px;
        font-weight: bold;
        line-height: 1;
        color: #000;
        text-shadow: 0 1px 0 #fff;
        opacity: .2;
        filter: alpha(opacity=20);
        display: inline-block;
        margin-top: 0px;
        margin-right: 0px;
        width: 9px;
        height: 9px;
        background-repeat: no-repeat !important;
    }

    .noalert {
        float: right;
        font-size: 12px;
        font-weight: bold;
        line-height: 1;
        color: #000;
        text-shadow: 0 1px 0 #fff;
        opacity: .2;
        filter: alpha(opacity=20);
        display: inline-block;
        margin: 1px -15px 0 15px;
        width: 80px;
        height: 9px;
        background-repeat: no-repeat !important;
        cursor:pointer;
    }

    .cloze:hover,.cloze:focus {
        color: #000;
        text-decoration: none;
        cursor: pointer;
        opacity: .5;
        filter: alpha(opacity=50)
    }
    button.cloze {
        padding: 0;
        cursor: pointer;
        background: transparent;
        border: 0;
        -webkit-appearance: none
    }
</style>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<!---------- 页面主体内容 开始 ------------>
<div class="u-wrap company overflow coupon-page">
    <div class="screen-left L-screen-left">
        <div class="screen-left-title">
            <i class="iconfont"></i>
            <span>证书</span>
        </div>
        <ul class="screen-left-list" id="screen-list">
            <li class="active">
                <span class="couponsManage">证书管理</span>
            </li>
            <li>
                <span class="couponsManage">证书查看</span>
            </li>
        </ul>
    </div>
    <!--证书管理开始-->
    <div class="right-side L-right-side showIn">
        <div class="u-wrap classes set-system">
            <div class="screen-right-cont set-system">
                <div class="screen-right-title">
                    <h3>
                        <i> 证书管理</i>
                    </h3>
                    <div class="set-new manage-button couponCreate"> <i class="iconfont iAdd">&#xe652;</i> 添加证书</div>
                </div>
                <input type="hidden" class="hiddenPageNo"/>
                <!--新加证书管理内容开始-->
                <div class="state clear L-state">
                    <div class="state-left">
                        <span>证书状态</span>
                        <button class="btn-all">全部</button>
                        <button class="btn-act">启用</button>
                        <button class="btn-pre">禁用</button>
                    </div>
                    <div class="state-right">
                        <input type="text" placeholder="证书名称">
                        <i class="iconfont stateSearch">&#xe6dd;</i>
                    </div>
                    <ul class="state-list">
                        
                    </ul>
                </div>
                    <div class="pages pagination"></div>
                <!--新加证书管理内容结束-->
            </div>
        </div>
    </div>
    <!--证书管理结束-->
    <!--证书查看开始-->
    <div class="right-side L-right-side">
        <div class="u-wrap classes set-system">
            <div class="screen-right-cont set-system">
                <div class="screen-right-title">
                    <h3>
                        <i>证书查看</i>
                    </h3>
                </div>
                <form  method="post" id="viewCertificateForm" class="reCoursesForm">
                    <select name="cerId" class="reCourses">
                        <option value="">证书名称</option>
                    </select>
                    <select  name="status" id="viewCertificateStatus">
                        <option value="">证书状态</option>
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                    <select  name="releaseStatus" id="viewReleaseStatus">
                        <option value="">发放状态</option>
                        <option value="1">已发放</option>
                        <option value="0">未发放</option>
                    </select>
                    <input type="text" name="stuName" placeholder="学员姓名" class="stuName" id="stuName1">
                    <input type="text" name="mobile" placeholder="手机号" class="phoNumber" id="mobile1">
                    <input type="text" name="username" placeholder="用户名" class="stuName userName" id="userName1">
                    <span style="margin-left:15px;margin-right: 5px;" class="reTime">发放日期 </span>
                    <span><input type="text" name="startTime" class="date-picker from1"><em  class="reTime"> 到 </em><input type="text" name="endTime" class="date-picker to1"></span>
                    <span><a href="javascript:;" class="btn" id="viewCertificateSearch">搜索</a></span>
                    </form>
                	<div class="user-list" style="margin-top: 20px;">
                    <div class="clear userTitle">
                        <h4>
                        </h4>
                        <div class="batch">
                            <a href="javascript:;" id="exportExcel">导出记录</a>
                            <p>已发放数量:<span id="releaseCount"></span></p>
                        </div>
                    </div>
                    <table class="table table-center userTable" id="viewCertificate">
                        <tbody>
                        <tr>
                            <th width="22%">证书名称</th>
                            <th width="12%">证书状态</th>
                            <th width="12%">学员姓名</th>
                            <th width="16%">手机号</th>
                            <th width="13%">用户名</th>
                            <th width="11%">发放状态</th>
                            <th width="14%">发放日期</th>
                        </tr>
                        
                        </tbody>
                    </table>
                    <div class="pages pagination2"></div>
                    <input type="hidden" class="hiddenViewCertificatePage">
                
            </div>
        </div>
    </div>
   </div> 
    <!--证书查看结束-->
    <!--证书发放开始-->
    <div class="right-side L-right-side" >
        <div class="u-wrap classes set-system">
            <div class="screen-right-cont set-system">
                <div class="screen-right-title">
                    <h3>
                        <i> 发放</i>
                    </h3>
                </div>
                <form action="" id="" class="reCoursesForm">
                        <select  name="" class="reCourses" id="relatedCourses">
                            <option value="">关联的课程名称</option>
                        </select>
                        <select id="studyStatus" >
                            <option value="">学习进度</option>
                            <option value="1">未学完</option>
                            <option value="0">已学完</option>
                        </select>
                        <select id="releaseStatus" >
                            <option value="">发放状态</option>
                            <option value="1">未发放</option>
                            <option value="0">已发放</option>
                        </select>
                        <input type="text" name="stuname" placeholder="学员姓名" class="stuName" id="stuName">
                        <input type="text" name="mobile" placeholder="手机号" class="phoNumber" id="mobile">
                        <input type="text" name="username" placeholder="用户名" class="stuName userName"  id="username">
                        <span style="margin-left:15px;margin-right: 5px;" class="reTime">发放日期 </span>
                        <span><input type="text" name="startTime" class="date-picker from"><em  class="reTime"> 到 </em><input type="text" name="endTime" class="date-picker to"></span>
                        <span><a href="javascript:;" class="btn" id="releaseSearch">搜索</a></span>
                        <input type="hidden" value="" class="hiddenReleaseSearch"/>
                        </form>
                <div class="user-list" style="margin-top: 20px;">
                    <div class="clear userTitle">
                        <h4>
                            证书名称：<span id="certificateName"></span><input type="hidden" class="releasePageHiddenCerId">
                        </h4>
                        <div>
                            <a href="javascript:;" id="batchRelease">批量发放</a>
                        </div>
                    </div>
                    <table class="table table-center userTable" id="userTable">
                        <tbody>
                            <tr>
                                <th width="3%"><input type="checkbox" class="checkboxAll"></th>
                                <th width="21%">课程名称</th>
                                <th width="11%">学员姓名</th>
                                <th width="17%">手机号</th>
                                <th width="11%">用户名</th>
                                <th width="8%">学习进度</th>
                                <th width="8%">发放状态</th>
                                <th width="11%">发放日期</th>
                                <th width="10%">操作</th>
                            </tr>
                        </tbody>
                    </table>
                    <div class="pages pagination1">
                </div>
            </div>
        </div>
    </div>
    <!-- <div class="right-side L-right-side">
        <div class="u-wrap classes set-system">
            <div class="screen-right-cont set-system">
                <div class="screen-right-title">
                    <h3>
                        <i> 发放</i>
                    </h3>
                    <div class="set-new manage-button couponCreate"> <i class="iconfont iAdd">&#xe652;</i> 添加证书</div>
                </div>
                <form method="" id="" class="reCoursesForm">
                    <div>
                    <select id="" name="" class="reCourses">
                        <option value="">关联的课程名称</option>
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                    <select id="" name="raLearn">
                        <option value="">考试情况</option>
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                    <select id="" name="raLearn">
                        <option value="">发放状态</option>
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                    <input type="text" name="stuname" placeholder="学员姓名" class="stuName">
                    <input type="text" name="mobile" placeholder="手机号" class="phoNumber">
                    <input type="text" name="username" placeholder="用户名" class="stuName userName">
                    <span style="margin-left:15px;margin-right: 5px;" class="reTime">发放日期 </span>
                    <span><input type="text" name="" class="date-picker"><em  class="reTime"> 到 </em><input type="text" name="" class="date-picker"></span>
                    <span><a href="javascript:;" class="btn">搜索</a></span>
                    </div>
                </form>
                <div class="user-list" style="margin-top: 20px;">
                    <div class="clear userTitle">
                        <h4>
                            证书名称：<sapn>ASQ Qulity Tools-Run Char</sapn>
                        </h4>
                        <div>
                            <a href="javascript:;">批量发放</a>
                        </div>
                    </div>
                    <table class="table table-center userTable">
                        <tbody>
                        <tr>
                            <th width="3%"><input type="checkbox"></th>
                            <th width="21%">课程名称</th>
                            <th width="11%">学员姓名</th>
                            <th width="17%">手机号</th>
                            <th width="11%">用户名</th>
                            <th width="8%">学习进度</th>
                            <th width="8%">发放状态</th>
                            <th width="11%">发放日期</th>
                            <th width="10%">操作</th>
                        </tr>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>质量工具-Run Char</td>
                            <td>贝利</td>
                            <td>13681326628</td>
                            <td>berly</td>
                            <td>已学完</td>
                            <td>已发放</td>
                            <td>2016-09-10</td>
                            <td><a href="javascript:;">查看</a></td>
                        </tr>
                        
                        </tbody>
                    </table>
                    
                    <div class="pages pagination"></div>
                </div>
            </div>
        </div>
    </div> -->
    <!--证书发放结束-->
</div>
<!--查看证书弹出提示开始-->
<div class="layer-tips L-allowAdmissionsTc certificate-alert ">
    <div class="layer-tips-title">
        查看详情 <i class="close iconfont">&#xe610;</i>
    </div>
    <div class="alertCertificate">
    <p class="CertificateCon cerbanner"></p>
    </div>
</div>
<!--查看证书弹出提示结束-->

<!--证书发放提示开始-->
<div class="layer-tips L-allowAdmissionsTc certificate" >
    <div class="layer-tips-title">
        提示 <i class="close iconfont">&#xe610;</i>
    </div>
    <p class="title-prompt">您确定要向该学员发放证书吗？</p>
    <div class="layer-tips-btns " id="L-edior">
        <a href="javascript:;" class="btn btn-mini btn-default " id="cancelRelease">取消</a>
        <a href="javascript:;" class="btn btn-mini btn-default btn-info" id="confrimRelease">确定</a>
    </div>
</div>
<!--证书发放提示结束-->

<!--证书批量发放提示开始-->
<div class="layer-tips L-allowAdmissionsTc certificate">
    <div class="layer-tips-title">
        提示 <i class="close iconfont">&#xe610;</i>
    </div>
    <p class="title-prompt">您确定要批量发放证书吗？</p>
    <div class="layer-tips-btns " id="L-edior">
        <a href="javascript:;" class="btn btn-mini btn-default " id="cancelBatchRelaseCer">取消</a>
        <a href="javascript:;" class="btn btn-mini btn-default btn-info" id="batchRelaseCer">确定</a>
    </div>
</div>
<!--证书批量发放提示结束-->

<!--删除提示开始-->
<div class="layer-tips L-allowAdmissionsTc" id="deleteCer">
    <div class="layer-tips-title">
        提示 <i class="close iconfont">&#xe610;</i>
    </div>
    <p class="title-prompt">确定要删除该证书吗？</p>
    <div class="layer-tips-btns " id="L-edior">
        <a href="javascript:;" class="btn btn-mini btn-default" id="cancelRelease1">取消</a>
        <a href="javascript:;" class="btn btn-mini btn-default btn-info" id="confirmCancel">确定</a>
    </div>
</div>
<!--删除提示结束-->
<!--无法删除提示开始-->
<div class="layer-tips L-allowAdmissionsTc" id="batchdelalert">
    <div class="layer-tips-title">
        提示 <i class="close iconfont">&#xe610;</i>
    </div>
    <p class="title-prompt">您要删除的证书已经有发放记录，无法删除</p>
    <div class="layer-tips-btns " id="L-edior">
        <a href="javascript:;" class="btn btn-mini btn-default" id="cancelBatchDel">取消</a>
    </div>
</div>
<!--无法删除提示结束-->
<!--添加开始代下拉-->
<div class="layer-tips L-allowAdmissionsTc" id="L-allowAdmissionsTc" >
    <div class="layer-tips-title">
        添加证书 <i class="close iconfont">&#xe610;</i>
    </div>
 	<input type="hidden" class="addhiddenCerId"/>
    <ul class="L-tips-content">
        <li><span><i class="iSnowflake">*</i>证书名称</span><input type="text" class="stateName"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id='errormsg' style='color: red;'></span></li>
        <li>
            <span><i class="iSnowflake">*</i>证书颁发条件</span>
            <label for="Finish"> <input type="radio" id="Finish" name="class" class="radio" checked="checked"/> 学完课程</label>
            <!-- <label for="adopt"> <input type="radio" id="adopt" name="class" class="radio" /> 考试通过</label> -->
        </li>
        <li class="L-last-tips clear">
            <span><i class="iSnowflake">*</i>绑定课程/考试</span>
            <div class="optgroup">
            <ul class="ckeckOption"></ul>
            <button class="abind" id="abind"><i class="iconfont iAdd">&#xe6db;</i> 添加绑定</button>
            </div>
        </li>
    </ul>
    <div class="layer-tips-btns " id="L-edior">
        <a href="javascript:;" class="btn btn-mini btn-default " id="cancelConfig">取消</a>
        <a href="javascript:;" class="btn btn-mini btn-default btn-info" id="saveConfig">保存</a>
    </div>
</div>

<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<div class="layer-tips-bg"></div>
<script src="<%=rootPath%>/javascripts/certificate/certificate-manage.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<%-- <script type="text/javascript" src="<%=rootPath%>/javascripts/common/common.js"></script> --%>
<script type="text/javascript" src="<%=rootPath%>/javascripts/certificate/certificatelist.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/certificate/certificate.js"></script>
</body>
</html>