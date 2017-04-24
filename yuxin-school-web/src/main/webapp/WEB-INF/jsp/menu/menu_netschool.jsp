<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp"%>
<script>
$(function(){
	$selectMenu('netschool_head');
	  $.ajax({
		  url: rootPath+"/checkPermition",
		  type: "post",
		  dataType: "text",
		  success: function(data){
			  if(data){
				  setTimeout(function(){
						var ehint = new EnjoyHint({
							onStart:function(){
								status = 1;
							},
							onEnd:function(){
								status = 0;
							}
						});
						var config=new Array();
						$("[data-school-enjoyhint]").each(function(){
							var data={};
							data.selector=$(this);
							data.event_type=$(this).data("event_type");
							data.description=$(this).data("description");
							data.next_url=$(this).data("next_url");
							config.push(data);
						})
						ehint.setScript(config);
						ehint.runScript();
					},500)
			  }
		  }
	  })

})
</script>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe600;</i>网校设置</a>
        <ul class="nav nav-left navspace" >
             <shiro:hasPermission name="netschool_pagehead">  
            <li code="netschool_pagehead"><a href="<%=request.getContextPath() %>/sysPageHeadFoot/showHead">页头导航</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="netschool_foot">  
            <li code="netschool_foot"><a href="<%=request.getContextPath() %>/sysPageHeadFoot/toConfigFooter" >页尾配置</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="netschool_body">  
            <li code="netschool_body"><a href="<%=request.getContextPath() %>/sysBody/show" >首页模板</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="netschool_item">  
            <li code="netschool_item"><a href="<%=request.getContextPath() %>/sysCyclePic/showPic">首页轮播图</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="open_class_banner">  
            <li code="open_class_banner"><a href="<%=request.getContextPath() %>/liveOpenCourse/showBannerPic">公开课轮播图</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="open_class_option">  
            <li code="open_class_option"><a href="<%=request.getContextPath() %>/sysBody/openClassOption">公开课模板配置</a></li>
            </shiro:hasPermission>

        </ul>
    </div>
</div>
<!-- 二级导航结束 -->