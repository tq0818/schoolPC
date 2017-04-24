<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>竞赛成绩</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/newresource.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/utils.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/achievement.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/competitionScore.css"/>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
</head>
<body>
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_tiku.jsp"></jsp:include>
	<!-- 成绩列表 -->
	<div class="u-wrap resource">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">竞赛成绩</h2>
                <div class="search searchAdmin">
                    <a href="javascript:;" id="downloadTemplete" class="btn btn-mini btn-cl btn-primary downloadTemplete">模板下载</a>
                    <a href="javascript:;" id="importScore" class="btn btn-mini btn-cl btn-primary importScore">导入成绩</a>
                    <a href="javascript:;" id="addScore" class="btn btn-mini btn-ach btn-primary addScore">
                        <em class="iconfont">&#xe606;</em> 添加成绩
                    </a>
                </div>
                <span class="line"></span>
            </div>
            <div class="r-list L-r-list">
                <div class="tables r-list L-r-list-table" id="teacherContent" style="min-height: 350px;position: relative;">
                    <table class="table table-hover table-center table-list L-table">
                          <tr>
                              <th width="10%">姓名</th>
                              <th width="10%">考号</th>
                              <th width="15%">证件号</th>
                              <th width="11%">参赛年级组</th>
                              <th width="10%">分数</th>
                              <th width="10%">奖项</th>
                              <th width="18%">成绩分析链接</th>
                              <th width="16%">操作</th>
                          </tr>
                          <tr><td colspan="8">暂无数据</td></tr>
                    </table>
                </div>
            </div>
            <div class="pages L-pages">
                <ul class="pagination">
                </ul>
            </div>
        </div>
    </div>
	<!-- 添加成绩 -->
	<div class="popupwin-box clear showAdd" style="display: none; min-height: 500px;z-index: 999">
	    <div class="popupwin popuwins " style="width: 400px; height: auto; display: block;" data-pupwin="modal">
            <div class="popupwin-title">
              <span>添加成绩</span>
              <i class="close iconfont hideShow" data-type=".showAdd"></i>
            </div>
            <div class="maindiv form-horizontal div_add" >
               <div><div class="divName"><front style="color:red;" >*</front>姓名：</div><input type="text" id="add_name" value="" maxlength="16"/> </div>
               <div><div class="divName"><front style="color:red;" >*</front>考号：</div><input type="text" id="add_code" value="" maxlength="32"/> </div>
               <div><div class="divName"><front style="color:red;" >*</front>身份证（护照）号：</div><input type="text" id="add_idCode" value="" maxlength="32"/> </div>
               <div><div class="divName"><front style="color:red;" >*</front>参赛年级组：</div><input type="text" id="add_grade" value="" maxlength="16"/> </div>
               <div><div class="divName"><front style="color:red;" >*</front>分数：</div><input type="text" id="add_score" value="" maxlength="16"/> </div>
               <div><div class="divName">奖项：</div><input type="text" id="add_awards" value="" maxlength="64"/> </div>
               <div><div class="divName">成绩分析链接：</div><input type="text" id="add_link" value="" maxlength="500"/> </div>
            </div>
            <div style="margin-top: 10px;">
          	  <input type="button"  class="btn btn-sm hideShow" data-type=".showAdd"  value="取消" style="margin-left: 105px;margin-bottom: 20px;" >
          	  <input type="button"  class="btn btn-sm btn-primary addOKScore" value="添加" style="margin-left: 47px;margin-bottom: 20px;" >
            </div>
	    </div>
	</div>
	<!-- 修改成绩 -->
	<div class="popupwin-box clear showEdit" style="display: none; min-height: 500px;z-index: 999">
	    <div class="popupwin popuwins " style="width: 400px; height: auto; display: block;" data-pupwin="modal">
            <div class="popupwin-title">
              <span>修改成绩</span>
              <i class="close iconfont hideShow" data-type=".showEdit"></i>
            </div>
            <div class="maindiv form-horizontal div_edit" >
               <div><div class="divName"><front style="color:red;" >*</front>姓名：</div><input type="text" id="edit_name"  value="" maxlength="16"/> </div>
               <div><div class="divName"><front style="color:red;" >*</front>考号：</div><input type="text" id="edit_code" value="" maxlength="32"/> </div>
               <div><div class="divName"><front style="color:red;" >*</front>身份证（护照）号：</div><input type="text" id="edit_idCode" value="" maxlength="32"/> </div>
               <div><div class="divName"><front style="color:red;" >*</front>参赛年级组：</div><input type="text" id="edit_grade" value="" maxlength="16"/> </div>
               <div><div class="divName"><front style="color:red;" >*</front>分数：</div><input type="text" id="edit_score" value="" maxlength="16"/> </div>
               <div><div class="divName">奖项：</div><input type="text" id="edit_awards" value="" maxlength="64"/> </div>
               <div><div class="divName">成绩分析链接：</div><input type="text" id="edit_link" value="" maxlength="500"/> </div>
            </div>
            <div style="margin-top: 10px;">
          	  <input type="button"  class="btn btn-sm hideShow" data-type=".showEdit" value="取消" style="margin-left: 105px;margin-bottom: 20px;" >
          	  <input type="button"  class="btn btn-sm btn-primary editOKScore" data-id="" value="修改" style="margin-left: 47px;margin-bottom: 20px;" >
            </div>
	    </div>
	</div>
	<!-- 导入成绩 -->
	<div class="popupwin-box clear showImport" style="display: none; min-height: 260px;z-index: 999">
	    <div class="popupwin popuwins " style="width: 550px; height: auto; display: block;" data-pupwin="modal">
            <div class="popupwin-title">
              <span>导入成绩</span>
              <i class="close iconfont hideShow" data-type=".showImport"></i>
            </div>
            <div class="maindiv form-horizontal" >
               <div>
               	   <div class="importDivName">上传：</div>
	               <input type="button"  class="btn btn-sm btn-primary selectFile" value="选择文件" >
	               <input type="text"  class="scoreFile" value="" title="" disabled="disabled" style="height: 15px;border: 0px;width: 280px;">
	               <input type="file" id="scoreFile" name="scoreFile" class="btn btn-sm btn-primary realselectFile" style="width: 200px;display: none;" value="选择文件" >
	           </div>
               <div style="width: 320px;"><div class="importDivName">状态：</div><div class="importStatus">待导入</div></div>
               <div style="width: 485px;display: none;" class="errorReason"><div class="importDivName">原因：</div><div class="importReason" style="margin-left: 45px;"></div></div>
             
            </div>
            <div style="margin-top: 10px;">
          	  <input type="button"  class="btn btn-sm hideShow" data-type=".showImport" value="取消" style="margin-left: 175px;margin-bottom: 20px;" >
          	  <input type="button"  class="btn btn-sm btn-primary importStart" value="开始导入" style="margin-left: 47px;margin-bottom: 20px;" >
          	  <input type="button"  class="btn btn-sm btn-primary importFinished" value="导入完成" style="margin-left: 47px;margin-bottom: 20px;display: none;" >
            </div>
	    </div>
	</div>
	<input type="hidden" id="selectCounts" value="10">
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading import" style="display:none">
		<p><i></i>数据导入中,请稍后...</p>
	</div>
	<div class="loading lp-units-loading insert" style="display:none">
		<p><i></i>数据加载中,请稍后...</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/system/competitionScore.js"></script>
</body>
</html>