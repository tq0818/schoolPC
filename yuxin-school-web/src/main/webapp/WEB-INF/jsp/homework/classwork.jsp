<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
    <%@include file="/decorators/import.jsp" %>
	<title>课后作业</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/utils.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/classWork.css">
	<style type="text/css">
		.className .select2-arrow{
			margin-right:0;
		}
		.select2-container .select2-default {
			height:30px;
		}
		#s2id_selClass{
			vertical-align: top;
			text-align: center;
		}
		.mainbackground{
			min-height:500px;
		}
	</style>
    
    <script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/plupload.full.min.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/moxie.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.2.1/jquery.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.js"></script>
	
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>


</head>
<body class="q-box" >
<%@include file="/WEB-INF/jsp/menu/menu_teach.jsp"%>
<div class="u-wrap operate">
    <div class="operate_live_heading" style="display:none">
    </div>
    <div class="mainbackground">
        <div class="heading" data-company-id="${companyId}">
            <h2 class="h5">课后作业</h2>
            <span class="line"></span>
        </div>
        <div class="select-option clear">
            <ul>
                <li class="classSelect">
                    <label for="" class="className">
                        <span>课程</span>
                        <select name="" id="selClass" class="selClass">
                            <!-- <option value=""></option> -->
                        </select>
                    </label>
                    <label for="" class="className">
                        <span>状态</span>
                        <select name="" id="" class="selAll">
                            <option value="">全部</option>
                            <option value="0">未留作业</option>
                            <option value="2">作业已全部批阅</option>
                            <option value="3">作业未全部批阅</option>
                        </select>
                    </label>
                </li>
            </ul>
        </div>
        <div >
            <ul class="classList-Con">
                
            </ul>
            <div class="nolesson" style="width:100%;font-size:14px;text-align:center;line-height:20px;margin-top:150px;color:#666;display:none;">此课程下不包含您教学的课次</div>
            <div class="pages L-pages">
		        <ul class="pagination classPages"></ul>
		    </div>
        </div>
    </div>
</div>
<!--课后作业-->
<!--
<div class="alertDialog">
    <h3 class="diaLogTil clear">
        <div class="fl">
            作业形式 :
        </div>
        <div class="fl rightCon">
            <span>
                <input type="radio" name="test" id="test">
                <label for="test">试卷</label>
            </span>
            <span>
                <input type="radio" name="test" id="Enclosure" checked="checked">
                <label for="Enclosure">附件</label>
            </span>
        </div>
    </h3>
    <div class="diaLogTil clear mTop20">
        <div class="fl">
            作业描述 :
        </div>
        <div class="fl rightCon">
            <textarea name="" class="userTextCon">必填</textarea>
        </div>
    </div>
    <div class="diaLogTil clear mTop20">
        <div class="fl workEnc">
            作业附件 :
        </div>
        <div class="fl rightCon uploadWork">
            <button class="TestName hide">选择试卷</button>
            <p class="show">
                <span>
                    <i class="iconfont iYun">&#xe6e1;</i>
                    <a href="javascript:;" class="fileBut">
                        上传附件
                        <input type="file" name="" id="">
                    </a>
                </span>
                <span class="colorRed hide">上传中</span>
                <span class="colorGre hide">上传完成</span>
            </p>
        </div>
    </div>
    <div class="diaLogTil clear mTop20">
        <div class="fl workEnc">
            附件名称 :
        </div>
        <div class="fl rightCon">
            <span class="EncName">附件名称</span>
            <span class="EncClose">删除</span>
        </div>
    </div>
</div>
-->
<!--选择试卷-->
<div class="add-layer w900 classEnc hide" style="z-index: 1001;">
    <h3 class="add-layer-tit ">选择试卷</h3>
    <i class="iconfont close q-close"></i>
    <div class="layer-content q-layer-content">
        <div class="term">
            <span class="term-title q-term-title">题库</span>
            <span class="term-title">
                <select name="" id="choose_tiku" style="" class="q-select">
                <option value=""></option></select>
            </span>
            <span class="term-title q-term-title">科目</span>
            <span class="term-title">
                <select name="" id="choose_item" class="q-select">
                </select>
            </span>
            <span class="term-title q-term-title">试卷名称</span>
            <span class="term-title">
                <input type="text" id="choose_paper" class="q-select">
            </span>
            <span class="btn btn-sm btn-default q-btn-primary q-btn-primary2" id="search_paper">
                搜索
            </span>
        </div>

        <div class="term-list q-term-list">
            <table class="table table-center table-head">
                <colgroup>
                    <col width="25%">
                    <col width="25%">
                    <col width="25%">
                    <col width="25%">
                </colgroup>
                <tr>
                    <th>试卷名称</th>
                    <th>所属题库</th>
                    <th>所属科目</th>
                    <th>试卷类型</th>
                </tr>
            </table>
            <div class="table-list">
                <table class="table table-center table-tbody">
                    <colgroup>
                        <col width="25%">
                        <col width="25%">
                        <col width="25%">
                        <col width="25%">
                    </colgroup>
					<tbody></tbody>
                </table>

            </div>
        </div>
    </div>
    <div class="pages L-pages">
        <ul class="pagination tikupaper"></ul>
    </div>
</div>
<!--背影遮罩-->
<div class="add-classes-bg hide" style="_display: block; width: 100%; height:100%; background: rgba(0,0,0,.2); position: fixed;left:0; top:0;"></div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->

<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/homework/homework.js"></script>
</body>
</html>