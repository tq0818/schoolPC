<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>远程结费</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<input type="hidden" id="totalMoney" value="${remoteFee.totalFee }"/>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">远程结费</h2>
<!--             <div class="search"> -->
<!--                 <input type="text" class="input-ctrl"> -->
<!--                 <input type="button" class="btn btn-sm" value="搜索"> -->
<!--             </div> -->
            <span class="line"></span>
        </div>
		<form id="myForm" method="post">
		<input type="hidden" name="id" value="${remoteFee.id }"/>
		<input type="hidden" name="classTypeId" value="${remoteFee.classTypeId }"/>
		<input type="hidden" name="examTermId" value="${remoteFee.examTermId }"/>
        <div class="long-edit">
            <p class="c">
                <span class="c-title">课程</span>
                <span class="input"><input type="text" name="classTypeName" value="${remoteFee.classTypeName }" class="readonly" readonly/></span>
            </p>
            <p class="c">
                <span class="c-title">考期</span>
                <span class="input"><input type="text" name="examTermName" value="${remoteFee.examTermName }"  class="readonly" readonly/></span>
            </p>
            <p class="c">
                <span class="c-title">已结费</span>
                <span class="input"><input type="text" name="hasPayFee" id="hasFee" value="${remoteFee.hasPayFee }" class="readonly" readonly/></span>
            </p>
            <p class="c">
                <span class="c-title">本次结费</span>
                <span class="input"><input type="text" name="payoffFee" value="" id="feeCen" class="prices"/></span>
            </p>
            <p class="c">
                <span class="c-title">已结比例</span>
                <span class="input"><input type="text" name="payoffPercent" value="" id="feePec"/></span>
            </p>
            <p class="c">
                <span class="c-title">是否完成结费</span>
                <span class="input">
                    <label><input type="radio" name="payoffStatus" value="1">完成</label>
                    <label><input type="radio" name="payoffStatus" checked="checked" value="0">未完成</label>
                </span>
            </p>
            <p class="c">
                <span class="c-title">&nbsp;</span>
                <span class="input">
                    <a href="javascript:;" id="saveMsg" class="btn btn-sm btn-primary">保存</a>
                    <a href="javascript:history.go(-1);" class="btn btn-sm btn-default">取消</a>
                </span>
            </p>
        </div>
        </form>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		$selectSubMenu('netschool_remote');
		$(".footer").addClass("footer-fixed"); 
		$(".prices").bind("keyup",function(event){
			//先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d.]/g,""));
	        //必须保证第一个为数字而不是. 
	        $(this).val($(this).val().replace(/^\./g,"0."));
	        //保证只有出现一个.而没有多个. 
	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	        //保证.只出现一次，而不能出现两次以上
	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
			
		})
		$(".prices").bind("blur",function(event){
			//先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d.]/g,""));
			 var value=$(this).val();
 	        var is=false;
 	        for (var i = 0; i < value.length; i++) {
 	            var  item =  value.charAt(i);
 	           	if("."==item){
 	           		is=true;
 	           	}
 	        }
 	        if(value!=null||value!=""){
 	        	if(!is){
 	        		$(this).val($(this).val()+".00");	
 	        	}
 	        } 
	        //必须保证第一个为数字而不是. 
	        $(this).val($(this).val().replace(/^\./g,"0."));
	        //保证只有出现一个.而没有多个. 
	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	        //保证.只出现一次，而不能出现两次以上
	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
			
		})
		$("#saveMsg").on('click',function(){
			var total=$("#totalMoney").val();
			var hasFee=$("#hasFee").val();
			var feeCen=$("#feeCen").val();
			var feePec=$("#feePec").val();
			if(!feeCen){
				alert("请完善结费金额");
				return;
			}
			if(!feePec){
				alert("请完善结费比例");
				return;
			}
			if((hasFee*1+feeCen*1)>total){
				alert("请核对交费金额是否等于应结费用金额!");
				return;
			}
			$("#myForm").attr("action",rootPath+"/fee/addRemoteFee").submit();
		})
	});
</script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>