<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath=request.getContextPath(); %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>设置会员等级</title>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/vip.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
  <%@include file="/WEB-INF/jsp/menu/menu_member.jsp" %>
  	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
    <div class="right-side">
        <div class="vip-box">
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">会员基本设置</span>
                    <em class="add-vip-btn"><i class="iconfont">&#xe652;</i>添加会员等级</em>
                </div>
            </div>
            <p class="prompt-font">说明：添加并设置会员等级，设置成功后可在创建课程时添加会员优惠，或在<span class="blue-font memberClass">运营-会员课程</span>中进行优惠设置。<em class="red-font" style="display:block;">注：会员升级顺序为从上到下，可拖拽改变位置。</em></p>
            <div class="move-box">
            	<c:forEach items="${list}" var="companyMemberLevelConfigVo">
            	 <div class="vip-info" data-id="${companyMemberLevelConfigVo.id}" style="cursor:move;">
                    <div class="info-title">
                        <span class="iconfont">&#xe630;</span>
                        <em>${companyMemberLevelConfigVo.name}</em>
                        <span class="edit_enable"><a href="<%=rootPath%>/companyMemberLevelConfig/toUpdatePage/${companyMemberLevelConfigVo.id}">编辑</a></span>
                        <span class="edit_enable" style="margin-right:10px"><a href="javascript:void(0);" onclick="deleteCompanyMemberConfigAndDetail(${companyMemberLevelConfigVo.id},this);">删除</a></span>
                    </div>
                    
                    <div class="info-cont">
                    <c:choose>
                    <c:when test="${companyMemberLevelConfigVo.openWay==0}">
                    	<c:choose>
                    		<c:when test="${companyMemberConfig.becomeMember==0}">
                    				<c:forEach items="${companyMemberLevelConfigVo.list}" var="companyMemberLevelConfigDetail">
                    				<div class="info-cont-line">
                    				<span>会员有效期 &nbsp;&nbsp;&nbsp;${companyMemberLevelConfigDetail.name}</span>
		                            <span>会员特权：${companyMemberLevelConfigVo.discount}折</span>
		                            <span>会员价格：${companyMemberLevelConfigDetail.price}</span>
		                             <span  style="width:304px;">晋升方式：
                          		 <c:choose>
                          		 	<c:when test="${companyMemberLevelConfigVo.openWay==0}">
                          		 		允许前台购买或累计消费晋升会员
                          		 	</c:when>
                          		 	<c:otherwise>
                          		 		只允许机构后台开通
                          		 	</c:otherwise>
                          		 	</c:choose>
                          		 	</span>
                          		 	 <c:if test="${companyMemberLevelConfigDetail.status==1}">
		                          		 <em class="edit_enable"><a  href="javascript:void(0);" onclick="changeStatus(${companyMemberLevelConfigDetail.id},this)">禁用</a></em>
		                          	 </c:if>
		                          	     <c:if test="${companyMemberLevelConfigDetail.status==0}">
		                          		 <em class="edit_enable"><a  href="javascript:void(0);" onclick="changeStatus(${companyMemberLevelConfigDetail.id},this)">启用</a></em>
		                          	 </c:if>
		                          	 <em class="edit_enable" style="margin-right:10px"><a  href="javascript:void(0);" onclick="deleteMemberLevelDetail(${companyMemberLevelConfigDetail.id},this)">删除</a></em>
		                        </div>
		                    </c:forEach>
                    		</c:when>
                    		<c:otherwise>
                    			  <div class="info-cont-line">
		                            <span>会员特权：${companyMemberLevelConfigVo.discount}折</span>
		                            <span>会员价格：${companyMemberLevelConfigVo.consumption}</span>
		                          	<span style="width:304px;">晋升方式：
												 <c:choose>
		                          		 	<c:when test="${companyMemberLevelConfigVo.openWay==0}">
		                          		 	允许前台购买或累计消费晋升会员 
		                          		 	</c:when>
		                          		 	<c:otherwise>
		                          		 	只允许机构后台开通
		                          		 	</c:otherwise>
		                          		 </c:choose>
									</span>
		                        </div>
                    		</c:otherwise>
                    	</c:choose>
                    </c:when>
                    <c:otherwise>
                    		 <div class="info-cont-line">
                         	 <span  style="width:304px;">晋升方式：
                          		 <c:choose>
		                          		 	<c:when test="${companyMemberLevelConfigVo.openWay==0}">
		                          		 		允许前台购买或累计消费晋升会员
		                          		 	</c:when>
		                          		 	<c:otherwise>
		                          		 		只允许机构后台开通
		                          		 	</c:otherwise>
		                          		 </c:choose>
		                          		 </span>
		                    	</div>
                    </c:otherwise>
                    </c:choose>
                    </div>
                </div>
            	</c:forEach>
            </div>
        </div>
    </div>
</div>
<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script src="<%=rootPath%>/javascripts/company/vip/companyMemberLevelConfig.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript">
$(function(){
	$selectSubMenu('org_service');
	$chooseMenus('setMemberLevel');
});
</script>
<script>
		$(function() {
			$(".move-box" ).sortable({
	            update:function(event,ui){
					var sortMap = [];
					$(".move-box").find(".vip-info").each(function(i){
						var oneItem={};
						oneItem.id=$(this).data("id");
						oneItem.sequence=$(this).index()+1;
						sortMap.push(oneItem);
					});
					 $.ajax({ 
				 		  type: "post", 
				 		  url : rootPath+"/companyMemberLevelConfig/orderMemberLevelSequence", 
				 		  data: "list="+JSON.stringify(sortMap),
						  beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				          },
				 		  success: function(data){
								$.msg("修改成功");
						  },
				 		  error : function(){
				 			  $.msg("操作失败");
				 		  },
				 		  complete:function(XMLHttpRequest,textStatus){
							  $(".loading").hide();
					          $(".loading-bg").hide();
					      }
				 	 });
				},
				delay: 100,
	      }).disableSelection();
			$('body').on('focus','input',function(){
				
			});
		});
	</script>
</body>
</html>