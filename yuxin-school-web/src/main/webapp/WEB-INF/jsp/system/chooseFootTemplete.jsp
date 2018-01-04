<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>页尾配置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/pagehead.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/pageafter.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    
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
                    <span class="t">页尾配置</span>
                </div>
            </div>
            <c:forEach items="${templetes }" var="foot_tps" varStatus="stat">
				<c:if test="${foot_tps.name eq '模板二'}">
					<div class="temp-box">
						<div class="temp-title">
							<%--<div class="temp-name">${foot_tps.name }</div>--%>
								<div class="temp-name">模板一</div>
							<div class="btn setting-btn" ids="${foot_tps.id }">设置</div>
						</div>
						<div class="temp-pic">
							<img src="<%=rootPath %>/${foot_tps.priviewPic}" alt="" width="100%"/>
							<c:if test="${!empty templete}">
								<c:choose>
									<c:when test="${foot_tps.id == templete.templeteId}">
										<div class="black-bg" style="display: block;" ids="${foot_tps.id }">
											<c:choose>
												<c:when test="${foot_tps.id==1 }">
													<div class="iconfont" style="width: 140px;height: 140px;font-size: 60px">&#xe6de;</div>
												</c:when>
												<c:when test="${foot_tps.id==2 }">
													<div class="iconfont" style="font-size: 50px;">&#xe6de;</div>
												</c:when>
												<c:when test="${foot_tps.id==3 }">
													<div class="iconfont" style="font-size: 40px">&#xe6de;</div>
												</c:when>
												<c:otherwise>
													<div class="iconfont" style="font-size: 30px;">&#xe6de;</div>
												</c:otherwise>
											</c:choose>
										</div>
									</c:when>
									<c:otherwise>
										<div class="black-bg" tId="${templete.templeteId }" ids="${foot_tps.id }">
											<c:choose>
												<c:when test="${foot_tps.id==1 }">
													<div class="iconfont" style="width: 140px;height: 140px;font-size: 60px">&#xe6de;</div>
												</c:when>
												<c:when test="${foot_tps.id==2 }">
													<div class="iconfont" style="font-size: 50px;">&#xe6de;</div>
												</c:when>
												<c:when test="${foot_tps.id==3 }">
													<div class="iconfont" style="font-size: 40px">&#xe6de;</div>
												</c:when>
												<c:otherwise>
													<div class="iconfont" style="font-size: 30px;">&#xe6de;</div>
												</c:otherwise>
											</c:choose>
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${empty templete}">
								<c:choose>
									<c:when test="${stat.last}">
										<div class="black-bg" style="display: block;" ids="${foot_tps.id }">
											<c:choose>
												<c:when test="${foot_tps.id==1 }">
													<div class="iconfont" style="width: 140px;height: 140px;font-size: 60px">&#xe6de;</div>
												</c:when>
												<c:when test="${foot_tps.id==2 }">
													<div class="iconfont" style="font-size: 50px;">&#xe6de;</div>
												</c:when>
												<c:when test="${foot_tps.id==3 }">
													<div class="iconfont" style="font-size: 40px">&#xe6de;</div>
												</c:when>
												<c:otherwise>
													<div class="iconfont" style="font-size: 30px;">&#xe6de;</div>
												</c:otherwise>
											</c:choose>
										</div>
									</c:when>
									<c:otherwise>
										<div class="black-bg" ids="${foot_tps.id }">
											<c:choose>
												<c:when test="${foot_tps.id==1 }">
													<div class="iconfont" style="width: 140px;height: 140px;font-size: 60px">&#xe6de;</div>
												</c:when>
												<c:when test="${foot_tps.id==2 }">
													<div class="iconfont" style="font-size: 50px;">&#xe6de;</div>
												</c:when>
												<c:when test="${foot_tps.id==3 }">
													<div class="iconfont" style="font-size: 40px">&#xe6de;</div>
												</c:when>
												<c:otherwise>
													<div class="iconfont" style="font-size: 30px;">&#xe6de;</div>
												</c:otherwise>
											</c:choose>
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
						</div>
					</div>
				</c:if>

            </c:forEach>
        </div>
    </div>
</div>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenus('system_foot');
    		$(".temp-pic").on("click",function(){
    			$(this).parent(".temp-box").siblings().find(".black-bg").hide();
 	            $(this).find(".black-bg").show();
    			var ids=$(this).find(".black-bg").attr("ids");
	  			var data={};
	  			var temps=new Array();
	  			$(".temp-box").find(".black-bg").each(function(i){
	  				var id=$(this).attr("ids");
	  				var status=0;
	  				if(id==ids){
	  					status=1;
	  				}
	  				temps.push({templeteId:id,status:status});
	  			});
	  			data.temps=JSON.stringify(temps);
	  			$.ajax({
	  				type : "post",
	  				url : rootPath+"/companyHeadFootConfig/chooseTemplate",
	  				data : data,
	  				success: function(jsonData){
	  					
	  				}
	  			});
    		});
    		//点击设置模板
    		$(".setting-btn").on('click',function(){
    			var id=$(this).attr("ids");
    			window.location.href=rootPath+"/companyHeadFootConfig/setTemplate/"+id;
    		});
    	});
 </script>
</body>
</html>