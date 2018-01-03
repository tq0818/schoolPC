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
        <form action="" id="myForm">
            <div class="classSetting">
                <h5>小学</h5>
                <ul>
	                 <c:forEach items="${list}" var="ps" >
	                 	<c:if test="${ps.eduStep eq  'STEP_01'}">
		                    <li>
		                    	${ps.eduYear}级<input type="text" name="01_${ps.eduYear}" value="${ps.classCount}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"   disabled="disabled">个班
		                   	</li>
	                   	</c:if>
	                 </c:forEach>
                </ul>
            </div>
            <div class="classSetting">
                <h5>初中</h5>
                <ul>
                      <c:forEach items="${list}" var="ps" >
	                 	<c:if test="${ps.eduStep eq  'STEP_02'}">
		                    <li>
		                    	${ps.eduYear}级<input type="text" name="02_${ps.eduYear}" value="${ps.classCount}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"  disabled="disabled">个班
		                   	</li>
	                   	</c:if>
	                 </c:forEach>
                </ul>
            </div>
            <div class="classSetting">
                <h5>高中</h5>
                <ul>
                   <c:forEach items="${list}" var="ps" >
	                 	<c:if test="${ps.eduStep eq  'STEP_03'}">
		                    <li>
		                    	${ps.eduYear}级<input type="text" name="03_${ps.eduYear}" value="${ps.classCount}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"  disabled="disabled">个班
		                   	</li>
	                   	</c:if>
	                 </c:forEach>
                </ul>
            </div>
            </form>
        </div>
        <div class="classSettingBtn">
            <button class="btn btn-warning editClassNum">编辑</button>
            <button class="btn btn-success saveClassNum" id="save" style="display: none">保存</button>
        </div>
    </div>
</div>



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
    	var currentBtn = document.getElementById("save");
        $('.classSetting').addClass('classSettingEdit');
        $('.classSetting').find('input').attr('disabled',false);
        currentBtn.style.display = "inline-block";
    });
   	function ObjData(key,value){
   		this.Key=key;
   		this.Value=value;
	}
//    点击保存成功，不做跳转限制
        var onOff = 0 ;
//    点击保存，进入只读模式
    $('.saveClassNum').click(function(){
        onOff = 1;
    	var list=document.getElementById("myForm").getElementsByTagName("input");
    	var array=[];
    	for(var i=0;i<list.length && list[i];i++) { //判断不是空的 input,进行表单提交 
   	      if(list[i].value!="" || list[i].value!=null) {
         	var key=list[i].name;
           	var value=list[i].value;
           	if(list[i].value==''){
           		var value=0;
           	}
          	var s=new ObjData(key,value); //创建键值对象
           	array.push(s); //把对象放入对象数组中
   	      	}
    	}
    	var postData =  JSON.stringify(array); 
    	$.ajax({
    		url : rootPath + "/administrativeClassManager/editClass",
			type : "post",
		 	datatype : "json",
			data : {postData:postData},
			success : function(data) {
				if(data.result == 'success'){
					alert("保存成功！");
					window.location.href = rootPath+"/administrativeClassManager/administrativeClass";
				}else{
					alert("保存失败！");
					return ;
				}
			}
    	});
        $('.classSetting').removeClass('classSettingEdit');
        $('.classSetting').find('input').attr('disabled',true);
    });
//    误操作，跳转到其他链接，弹窗提醒
        $('input').focus(function(){
                window.onbeforeunload = function (e) {
                    e = e || window.event;
                    if (!onOff) {
                        // IE 和 Firefox
                        if (e) {
                            e.returnValue = "对不起，页面数据已做修改，尚未保存，确定要刷新或离开本页面？";
                        }
                        // Safari浏览器
                        return "对不起，页面数据已做修改，尚未保存，确定要刷新或离开本页面？";
                    }
                }
        });
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