<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>页尾设置</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/minitip.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/system.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage-screen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/footer.css" />
	 <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/footSet.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/sidebar/sidebar.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/utils.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company overflow">
		<jsp:include page="/WEB-INF/jsp/menu/menu_microschool.jsp"></jsp:include>
		 <div class="right-side wxRight-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">页尾配置</span>
                    <c:choose>
                    	<c:when test="${functionStatus == 1 }">
                    	<span class="foot-open">
                    		<em class="iconfont normal open">&#xe603;</em>
                   			<span class="i open">已启用</span>
              			</span>
                    	</c:when>
                    	<c:otherwise>
                    	<span class="foot-open">
                    		<em class="iconfont normal close">&#xe604;</em>
                   			<span class="i close">已禁用</span>
                  		</span>
                    	</c:otherwise>
                    </c:choose>
                    
                </div>
            </div>
            <div class="footerSetBox">
                <h4>说明：开启后可配置微官网的页尾内容，填写内容后将在微网校首页的页尾进行展示。</h4>
                <c:choose>
                    	<c:when test="${functionStatus == 1 }">
                <form action=""  class="footerTabel">
                		</c:when>
                    	<c:otherwise>
           	 	<form action=""  class="footerTabel none">
               			</c:otherwise>
               </c:choose>
                    <div class="tableList clear">
                        <span>联系电话：</span>
                   		<input id="contactNumber" type="text" value="${footInfo.contactNumber }"/>
                        <input class="savebutton" type="button" value="保存">
                    </div>
                </form>
       		 </div>
      		</div>
    	</div>
	</div>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript">
		$selectSubMenus('microSchool_footerConfig');
	</script>
	<script type="text/javascript">
		$(document).on('click','.savebutton',function(){
			$.ajax({
				url: rootPath+"/microSchool/saveMobile",
				data:{contactNumber:$("#contactNumber").val()},
				type:"post",
				dataType:"json",
				success:function(jsonData){
					if(jsonData.result == "success"){
						$.msg("保存成功");
					}
				}
			})
		}).on("click", '.foot-open', function(){
			if($(this).find("em").hasClass("open")){
				$(this).html('<em class="iconfont normal close">&#xe604;</em> <span class="i close">已禁用</span>')
				$(".footerTabel").addClass('none');
			}else{
				$(this).html('<em class="iconfont normal open">&#xe603;</em> <span class="i open">已启用</span>')
				$(".footerTabel").removeClass('none');
			}		
			
			$.ajax({
				url: rootPath + '/microSchool/saveFootFuncFlag',
				data:{'flag':$(this).find("em").hasClass("open")?1:0},
				type:"post",
				dataType:"json",
				success:function(jsonData){
					if(jsonData.result == "success"){
						$.msg('更改成功');
					}
				}
			})
		})
	</script>
</body>
</html>