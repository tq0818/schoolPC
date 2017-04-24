<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>自定义学科</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <style type="text/css">
    	.pay-box-parent {
		    padding: 0px 50px 0px;
		    overflow: hidden;
		}
		.open{
    		color:#0099ff;
    	}
    	.font_big{
    		font-size:22px;
    	}
    	.txt_width{
    		padding:50px;
    	}
    	p.c {
		    margin-bottom: 15px;
		    clear: both;
		}
		.showColumn {
			background:transparent;
			border:0;
		}		
    </style>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
<jsp:include page="/WEB-INF/jsp/menu/menu_systemconfig.jsp"></jsp:include>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">自定义学科</span>
             
                </div>
            </div>
            
            <div class="pay-box-parent">
           		<div style="margin-top:15px;">
	           		<span style="font-size: 14px;color:#666;">启用自定义学科</span>
	           		<span style="margin-left: 36px;font-size: 14px;" id="subjectStatusOpen"><i class="iconfont normal open" >&#xe603;</i>
                    <span id="" class="">已启用</span></span>
                   	<span style="margin-left: 36px;font-size: 14px;" id="subjectStatusClose"><i class="iconfont normal close" >&#xe604;</i>
                    <span id="" class="">已禁用</span></span>
	            </div>
	            <div style="margin: 10px 0px 10px 0px;">
	           	   <span style="font-size: 12px;color:#999999;">说明: 开启后，可对学科及学科小类改名，并在网校中显示。 </span>
	            </div>
	            <hr/>
	            <p class="c study">
	            	<span style="width: 100px;color:#666;padding-left:2em;">学科:</span>
	            	<span>
		            	<input type="text" disabled="true" class="showColumn" id="showColumn1" name="cfs.column1" style="" value="${cfs.column1}"/>
		            	<input type="text" id="column1" name="cfs.column1" style="display: none;" maxlength="6" value="${cfs.column1}"/>
		            	<a href="javascript:subjectEdit(1);" id="subjectEdit1" mark="chapter"  class="btn btn-primary savebtn_list savechapterBtn" style="padding: 5px 14px;">修改</a>
		            	<a href="javascript:subjectSave(1);" id="subjectSave1" mark="chapter"  class="btn btn-primary savebtn_list savechapterBtn" style="display: none;padding: 5px 14px;">保存</a>
		            	<a href="javascript:subjectEsc(1);" id="subjectEsc1" class="btn btn-default cancle-chapterandlec" style="display: none;padding: 5px 14px;">取消</a>
	            	</span>
	            </p>
	            <p class="c study">
	            	<span style="width: 100px;color:#666;">学科小类:</span>
	            	<span>
		            	<input type="text" disabled="true" class="showColumn" id="showColumn2" name="cfs.column2" style="" value="${cfs.column2}"/>
		            	<input type="text" id="column2" name="cfs.column2" style="display: none;" maxlength="6" value="${cfs.column2}"/>
		            	<a href="javascript:subjectEdit(2);" id="subjectEdit2" mark="lecture"  class="btn btn-primary savebtn_list savelectureBtn" style="padding: 5px 14px;" >修改</a>
		            	<a href="javascript:subjectSave(2);" id="subjectSave2" mark="lecture"  class="btn btn-primary savebtn_list savelectureBtn" style="display: none;padding: 5px 14px;" >保存</a>
		            	<a href="javascript:subjectEsc(2);" id="subjectEsc2" class="btn btn-default cancle-chapterandlec" style="display: none;padding: 5px 14px;">取消</a>
	            	</span>
	            </p>
	       </div> 
           <div class="clear"></div>
        </div>
    </div>
</div>
	<input type="hidden" id="subjectStatus" value="${cfs.status }"/>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('org_service');
    		$selectSubMenus('company_subject_customname');
    		if($("#subjectStatus").val()==0){
    			$(".study").hide();
    			$("#subjectStatusOpen").hide();
    		}else if($("#subjectStatus").val()==1){
    			$(".study").show();
    			$("#subjectStatusClose").hide();
    		}
    		$("#subjectStatusOpen").click(function(){
    			$.ajax({
    			    url:"<%=rootPath %>/company/editSubjectStatus",    
    			    dataType:"text",   
    			    async:true,
    			    data:{"status":$("#subjectStatus").val()},    
    			    type:"post",   
    			    success:function(data){
    			    	if( data=="true"){
	    			    	$("#subjectStatusOpen").hide();
	    	    			$("#subjectStatusClose").show();
	    	    			$("#subjectStatus").val(0);
	    	    			$(".study").hide();
	    	    			$.msg("状态修改成功！");
    			    	}else{
    			    		$.msg("状态修改失败！");
    			    	}
    			    },
    			    error:function(){
    			    	$.msg("状态修改失败！");
    			    }
    			});
    			
    		});
    		$("#subjectStatusClose").click(function(){
    			$.ajax({
    			    url:"<%=rootPath %>/company/editSubjectStatus",    
    			    dataType:"text",   
    			    async:true,
    			    data:{"status":$("#subjectStatus").val()},    
    			    type:"post",   
    			    success:function(data){
    			    	if( data=="true"){
    			    		$("#subjectStatusClose").hide();
    		    			$("#subjectStatusOpen").show();
    		    			$("#subjectStatus").val(1);
    		    			$(".study").show();
    		    			$.msg("状态修改成功！");
    			    	}else{
    			    		$.msg("状态修改失败！");
    			    	}
    			    },
    			    error:function(){
    			    	$.msg("状态修改失败！");
    			    }
    			});
    		});
    	
    	});
    	function subjectEdit(type){
   			$("#subjectEdit"+type).hide();
   			$("#subjectSave"+type).show();
   			$("#subjectEsc"+type).show();
   			$("#showColumn"+type).hide();
   			$("#column"+type).show();
    	}
    	function subjectSave(type){
    		$.ajax({
			    url:"<%=rootPath %>/company/editSubjectName",    
			    dataType:"text",   
			    async:true,
			    data:{"value":$("#column"+type).val(),"type":type},    
			    type:"post",   
			    success:function(data){
			    	if(data=="true"){
				    	$("#showColumn"+type).val($("#column"+type).val());
				    	$.msg("保存成功！");
			    	}else{
			    		$.msg("保存失败！");
			    	}
			    },
			    error:function(){
			    	$.msg("保存失败！");
			    }
			});
   			$("#subjectEdit"+type).show();
   			$("#subjectSave"+type).hide();
   			$("#subjectEsc"+type).hide();
   			$("#showColumn"+type).show();
   			$("#column"+type).hide();
    	}
    	function subjectEsc(type){
   			$("#subjectEdit"+type).show();
   			$("#subjectSave"+type).hide();
   			$("#subjectEsc"+type).hide();
   			$("#showColumn"+type).show();
   			$("#column"+type).hide();
    	}
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/system/systemCompanyIco.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
   	<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
</body>

</html>