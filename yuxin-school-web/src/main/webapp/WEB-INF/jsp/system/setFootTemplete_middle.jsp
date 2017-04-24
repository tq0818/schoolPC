<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>页尾配置</title>
    <script type="text/javascript">
    	var rp='<%=rootPath %>';
    	var theme = "${companyInfo.themes }";
    </script>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/pagehead.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/pageafter.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/themesdiy/pageafter.css"/>
	<c:choose>
		<c:when test="${'footer-blue' == companyInfo.themes  }">
    		<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/footer/footer-blue.css"  id="footer-theme"/> 
		</c:when>
		<c:when test="${'footer-cyan' == companyInfo.themes  }">
    		<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/footer/footer-cyan.css"  id="footer-theme"/> 
		</c:when>
		<c:when test="${'footer-gray' == companyInfo.themes  }">
    		<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/footer/footer-gray.css"  id="footer-theme"/> 
		</c:when>
		<c:when test="${'footer-green' == companyInfo.themes  }">
    		<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/footer/footer-green.css"  id="footer-theme"/> 
		</c:when>
		<c:when test="${'footer-orange' == companyInfo.themes  }">
    		<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/footer/footer-orange.css"  id="footer-theme"/> 
		</c:when>
		<c:when test="${'footer-red' == companyInfo.themes  }">
    		<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/footer/footer-red.css"  id="footer-theme"/> 
		</c:when>
		<c:when test="${'footer-white' == companyInfo.themes  }">
    		<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/footer/footer-white.css"  id="footer-theme"/> 
		</c:when>
		<c:otherwise>
			<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/footer.css" id="footer-theme"/>
		</c:otherwise>
	</c:choose>
     <style type="text/css">
    	input.up {
		    position: absolute;
		    top: 0;
		    left: -4px;
		    width: 96px;
		    height: 32px;
		    opacity: 0;
		    filter: alpha(opacity = 0);
		    z-index: 1;
		}
   		label{
		    display: inline-block;
		}
		.ui-state-highlight{
    		height: 30px;
    		border: dashed 1px #ced7fd;
    		background: #adbdfc; 
    	}
    </style>
    
</head>
<body>
<input type="hidden" value="${set.content }" id="header-theme-color"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_systemconfig.jsp"></jsp:include>
     <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">预览效果</span>
                </div>
            </div>
            <div  id="footerContents">
                <div class="set-system">
                    <div class="pageafter-box">
                        <div class="c-footer c-footer-3">
                            <div class="footer-content clear content-3" >
                                <div class="f-3-left record3 recordt">
                                    <div>
                                        <p id="company_showPic">
                                            <img src="<%=rootPath %>/images/logo1.png" alt="" width="150" height="50"/>
                                        </p>
                                        <p id="show_companyOveriew">24小时服务热线：</p>
                                        <p id="copyrightList" class=" record3-top"></p>
                                    </div>
                                </div>
                                <div class="f-3-middle recordt">
                                    <div id="foot_showContents" style="margin-top:0;">
                                       
                                    </div>
                                </div>
                                <div class="f-3-right recordt">
                                    <div class="clear">
                                        <div>
                                            <p style="font-size: 12px;margin-top: 40px;color: #999;">在这里关注我们的动向</p>
                                            <p style="margin-top: 10px;position:relative;" class="img_hover1 qimg_hover">
												<span style="display: inline-block; cursor: pointer;" id="tengxuns">    
													<i class="iconfont">&#xe690;</i>
												</span>
												<span id="wbUrl" style="display: inline-block;"><i class="iconfont">&#xe692;</i></span>
												<span id="txUrl" style="display: inline-block;" class="weixin-logo">
														<i class="iconfont">&#xe691;</i>
												</span>
												<em class="pop-ewm pop-ewm1" id="show_wxPic"><img src="http://image.yunduoketang.com/company/7232/20160513/def8febd-a373-40c9-8ce6-a83b24014610.jpg" alt="" width="85" height="85"></em>
											</p>
                                        </div>
                                        <div class="erwm">
                                            <img src="<%=rootPath %>/images/ewm.jpg" style="display: none;" id="show_wxPic" alt="" width="85" height="85"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
             <div>
                <div class="title-box marg-t-10">
                    <div class="tit-font ">
                        <span class="t">主题配色</span>
                    </div>
                </div>
                <div class="set-system">
                    <div class="theme-box">
                       <span class="them-text">选择主题：</span>
                       	<c:choose>
							<c:when test="${'header-blue' == set.content}">
								<span class="theme-choice footer-white " id="footer-white">
		                           <i class=""></i>
		                       	</span>
		                       	<span class="theme-choice footer-gray" id="footer-gray">
		                           <i class=""></i>
		                       	</span>
		                       	<span class="theme-choice footer-blue" id="footer-blue">
		                           <i class=""></i>
		                        </span>
							</c:when>
							<c:when test="${set.content == 'header-cyan' }">
								<span class="theme-choice footer-white " id="footer-white">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-gray" id="footer-gray">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-ceya " id="footer-ceya">
                           			<i class=""></i>
                       			</span>
							</c:when>
							<c:when test="${'header-gray' == set.content}">
								<span class="theme-choice footer-white " id="footer-white">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-gray" id="footer-gray">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-default" id="footer-default">
                           			<i class=""></i>
                       			</span>
							</c:when>
							<c:when test="${'header-green' == set.content}">
								<span class="theme-choice footer-white " id="footer-white">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-gray" id="footer-gray">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-green" id="footer-green">
                           			<i class=""></i>
                       			</span>
							</c:when>
							<c:when test="${'header-orange' == set.content}">
								<span class="theme-choice footer-white " id="footer-white">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-gray" id="footer-gray">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-orange" id="footer-orange">
                           			<i class=""></i>
                       			</span>
							</c:when>
							<c:when test="${'header-red' == set.content}">
								<span class="theme-choice footer-white " id="footer-white">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-gray" id="footer-gray">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-red" id="footer-red">
                           			<i class=""></i>
                       			</span>
							</c:when>
							<c:when test="${'header-white' == set.content}">
								<span class="theme-choice footer-white " id="footer-white">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-gray" id="footer-gray">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-default" id="footer-default">
                           			<i class=""></i>
                       			</span>
							</c:when>
							<c:otherwise>
								<span class="theme-choice footer-default" id="footer-default">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-gray" id="footer-gray">
		                           <i class=""></i>
		                        </span>
		                        <span class="theme-choice footer-white " id="footer-white">
		                           <i class=""></i>
		                        </span>
							</c:otherwise>
						</c:choose>
                    </div>
                </div>				
            </div>
           
            <div>
                <div class="title-box marg-t-10">
                    <div class="tit-font">
                        <span class="t">底部公司信息配置</span>
                    </div>
                </div>
                <div class="set-system">
                    <div class="config-box">
                        <div class="clear">
                            <div class="line-left">公司信息配置：</div>
                            <div class="line-right" id="chooseLists" style="line-height: 70px">
                                 <c:choose>
					            	<c:when test="${companyInfo.logoType=='picture' }">
					            		<input type="radio" checked="checked" name="com" value="logo"/>LOGO &nbsp;&nbsp; <input class="iconfont" type="radio" name="com" value="comname"/>公司名称
					            	</c:when>
					            	<c:otherwise>
					            		<input type="radio" name="com" value="logo"/>LOGO &nbsp;&nbsp; <input class="iconfont" type="radio" checked="checked" name="com" value="comname"/>公司名称
					            	</c:otherwise>
					            </c:choose>
                            </div>
                        </div>
                        <div class="clear">
                            <div class="line-left"></div>
                            
                           <div id="oneDiv" class="line-right" style="line-height: 70px">
                                <div class="brand-view" style="float: left">
                                    <p>
                                        <img id="imgUrl" src="${companyInfo.logoPic }" alt="" width="150px" height="50px"/>
                                    </p>
                                </div>
                                <div class="brand-btn" style="float: left">
                                    <p>*说明：建议上传 <span>透明底色的PNG</span> 图片。图像尺寸为<span>150px * 50px</span>点击上传</p>
                                    <div>点击上传</div>
                                    <input type="file" style="cursor: pointer;" accept="image/*" id="imgData" name="imgData" onchange="changCompanyIco('logo')">
                                </div>
                            </div>
                            
                             <div id="twoDiv" class="line-right" style="display: none;">
				            	<div style="float: left;">
				            		<div> 
				            			<span id="spanName">${companyInfo.logoText }</span>
				            			<input id="upName" type="text" style="display: none;" maxlength="8" value="${companyInfo.logoText }"/>
				            		</div>
				            		<div style="margin-top: 10px;">
				            			<input type="button" onclick="changen()" id="btnupdate" value="修改" class="btn btn-sm btn-default">
				            			<input type="button" style="display: none;" id="btnsave" onclick="changCompanyName()" value="保存" class="btn btn-primary">
				            		</div>
				            	</div>
				            	<div class="upfile-text" style="margin-left: 25px;">
				                    <p style="color: #666;"><i class="iconfont orange">&#xe605;</i>说明：填写公司名称，或者是你想显示在网校页尾LOGO位置的文字，长度要求：2-8字 </p>
				                </div>
				            </div>
                            
                        </div>
                        <div class="clear">
                            <div class="line-left">自定义内容：</div>
                            <div class="line-right" style="line-height: 70px">
                                <div class="right-input">
                                    <form action="">
                                    	<c:choose>
                                    		<c:when test="${!empty companyInfo }">
                                    			 <label for="">${companyInfo.overview }</label>
                                    		</c:when>
                                    		<c:otherwise>
                                    			 <label for="">24小时服务热线：400-965-8366</label>
                                    		</c:otherwise>
                                    	</c:choose>
                                        <input type="text" id="companyOverview" value=""/>
                                    </form>
                                </div>
                                <div class="right-btn-box">
                                    <div class="right-btn modify-btn" onclick="updateCompanyInfo(this);" mark="overview">修改</div>
                                    <div class="right-btn cancel-btn">取消</div>
                                </div>
                            </div>
                        </div>
                        
                         <div class="clear">
                            <div class="line-left">备案号：</div>
                            <div class="line-right" style="line-height: 70px">
                               <div class="right-input records-box" style="width: 208px;">
                                    <form action="">
                                    <c:choose>
                                    	<c:when test="${companyLevel=='yes' }">
                                    		<c:choose>
		                                   		<c:when test="${!empty companyInfo }">
		                                   		Copyright@
		                                   			<label class="show_company" id="copyright_1">${companyInfo.copyright }</label>
		                                   			<input class="hide_company" type="text" style="width: 95px;display: none;"  value="${companyInfo.copyright }" id="copyright"/>
		                                   		</c:when>
		                                   		<c:otherwise>
		                                   		Copyright@<label class="show_company" id="copyright_1">2014-2017.</label>
		                                   			 <input class="hide_company" type="text" style="width: 95px;display: none;"  placeholder="2014-2017." id="copyright"/>

		                                   		</c:otherwise>
		                                   	</c:choose>
                                    	</c:when>
                                    	<c:otherwise><label for="">Copyright@2014-2017.</label></c:otherwise>
                                    </c:choose>
                                    </form>
                                </div>
                                <div style="line-height: 70px">
                                  <c:choose>
                                    	<c:when test="${companyLevel=='yes' }">
                                    		 <c:choose>
		                                   		<c:when test="${!empty companyInfo }">
		                                   			 <label class="show_company" id="companyName_1">${companyInfo.companyName }</label>
		                                   			 <input class="hide_company" style="display: none;" type="text" value="${companyInfo.companyName }" id="companyName"/>
		                                   		</c:when>
		                                   		<c:otherwise>
		                                   		     <label class="show_company" id="companyName_1"></label>
		                                   			 <input class="hide_company" style="display: none;" type="text" placeholder="" id="companyName"/>
		                                   		</c:otherwise>
		                                    </c:choose>
                                    	</c:when>
                                    	<c:otherwise><label for=""></label></c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="right-input" style="width: 70px;margin-left: 10px;">
                                    <form action="">
                                        <label for="">版权所有</label>
                                    </form>
                                </div>
                                <div style="line-height: 70px">
                                   	 <c:choose>
                                    	<c:when test="${companyLevel=='yes' }">
                                    		 <c:choose>
		                                   		<c:when test="${!empty companyInfo }">
		                                   			<label class="show_company" id="icpNos_1">${companyInfo.icpNo }</label>
		                                   			 <input class="hide_company" style="display: none" type="text" value="${companyInfo.icpNo }" id="icpNos"/>
		                                   		</c:when>
		                                   		<c:otherwise>
		                                   			 <label class="show_company" id="icpNos_1">京ICP备15011506号</label>
		                                   			 <input class="hide_company" style="display: none" type="text" placeholder="京ICP备15011506号" id="icpNos"/>
		                                   		</c:otherwise>
		                                    </c:choose>
                                    	</c:when>
                                    	<c:otherwise><label for="">京ICP备15011506号</label></c:otherwise>
                                    </c:choose>
                                    <a class="btn btn-mini btn-primary updateCompanyInfo" mark="update" style="cursor: pointer;margin-left: 10px;">修改</a>
                                </div>
                            </div>
                        </div>
      
                    </div>
                </div>
            </div>
            
            <!-- 公安备案  -->
            <div>
                <div class="title-box marg-t-10">
                    <div class="tit-font">
                        <span class="t">备案信息配置</span>
                    </div>
                </div>
                <div class="set-system">
                    <div class="config-box" style="padding: 0 10px 20px 30px;">
                        <div class="clear">
                            <div style="position:relative;padding: 5px 0 5px 20px;">
                            	<span style="">备案图标：</span>
                            	<img  style="width:24px;height:24px;" src="${companyInfo.icoPath}" id="security_ico" data-path="${companyInfo.securityIco}">
                            	<input type="file" accept="image/*" id="secico" name="secico" style="opacity:0;width:52px;display: inline-block;position:absolute;top:7px;left:130px;cursor: pointer;filter:alpha(opacity = 0);" />
                            	<a class="btn btn-mini btn-primary" style="cursor: pointer;margin-left:10px;">上传</a><a class="btn btn-mini btn-primary del-img" style="cursor: pointer;margin-left:10px;">删除</a>
                            </div>
                            <div style="padding: 5px 0 5px 20px;">
                            	<span>公安备号：</span>
                                <input type="text" value="${companyInfo.securityRegno}" style="width:200px;height:20px;" placeholder="公安备号" id="security_regno"/>
                            </div>
                            <%-- <div style="padding: 5px 0 5px 20px;">
                            	<span>备案链接：</span>
                                <input type="text" value="${companyInfo.securityLink}" style="width:500px;height:20px;" placeholder="备案链接 " id="security_link"/>
                            </div> --%>
                            <div style="padding: 5px 0 5px 125px;">
                            	<a class="btn btn-mini btn-primary" style="cursor: pointer;" id="saveh">保存</a>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
            
            <div>
                <div class="title-box marg-t-10">
                    <div class="tit-font">
                        <span class="t">底部链接</span>
                    </div>
                </div>
                <div class="set-system" id="navbarconfigs">
                   
                </div>
<!--                 <div class="new-bottom-btn"> -->
<!-- 				    <span>此模板最多可添加8个导航链接</span> -->
<!-- 				</div> -->
            </div>
           <div>
                <div class="title-box marg-t-10">
                    <div class="tit-font">
                        <span class="t">营销链接</span>
                    </div>
                </div>
                <div class="set-system">
                    <div class="section other-link">
                        <div class="block l-menu">
                            <div class="title-wrap ">
                                <div class="row clear">
                                    <em>新浪微博</em>
                                    <em class="tit-font" id="xlFlag" mark="sina_flag">
                                    	<em class="iconfont normal close">&#xe604;</em>&nbsp;&nbsp;<span id="" class="i close">已禁用</span>
                                    </em>
                                </div>
                                <div class="field fix-height">
                                    <div class="content">
                                        <p>
                                            <span class="name">链接地址：</span>
                                            <input type="text" value="${companyInfo.sinaWeibo }" disabled="disabled" id="sinaNos" class="title-content" placeholder="示例:http://yunduoketang.com/course">
                                            <s class="l-btn upload savebtn_mark" mark="sina_mark">编辑</s>
                                            <s class="l-btn upload savebtn_mark" style="display: none;" mark="sina_mark" onclick="updateMarkingInfo(this);">保存</s>
                                            <s class="r-btn marking_cancle" mark="sina_mark" value="${companyInfo.sinaWeibo }">取消</s>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="section other-link">
                        <div class="block l-menu">
                            <div class="title-wrap ">
                                <div class="row clear">
                                    <em>腾讯营销</em>
                                    <em class="tit-font" id="tecent_marking" mark="weibo_flag">
                                    	<em class="iconfont normal close">&#xe604;</em>&nbsp;&nbsp;<span id="" class="i close">已禁用</span>
                                    </em>
                                    <!--<em class="iconfont up-btn">&#xe655;</em>-->
                                </div>
                                <div class="field fix-height">
                                    <div class="content">
                                        <p>
                                            <span class="name">链接地址：</span>
                                            <input type="text" value="${companyInfo.tencentWeibo }" disabled="disabled" id="tecentMarking" class="title-content" placeholder="示例:http://yunduoketang.com/course">
                                            <s class="l-btn upload savebtn_mark" mark="qq_mark">编辑</s>
                                            <s class="l-btn upload savebtn_mark" style="display: none;" mark="qq_mark" onclick="updateMarkingInfo(this);">保存</s>
                                            <s class="r-btn marking_cancle"  mark="qq_mark" value="${companyInfo.tencentWeibo }">取消</s>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="section other-link button-link">
                        <div class="block l-menu">
                            <div class="title-wrap ">
                                <div class="row clear">
                                    <em>微信</em>
                                    <em class="tit-font" id="wxFlag" mark="wx_flag">
                                    	<em class="iconfont normal close">&#xe604;</em>&nbsp;&nbsp;<span id="" class="i close">已禁用</span>
                                    </em>
                                </div>
                                <div class="field fix-height">
                                    <div class="content">
                                        <p>
                                            <span class="name">图片：</span>
                                            <input type="text" id="wxPic_marking" value="${companyInfo.tencentWechat }" class="title-content" placeholder="/assets/img/default/weixin.png">
                                            <em class="reletive">
                                            <c:choose>
                                            	<c:when test="${empty companyInfo.tencentWechat }">
                                            		<s class="l-btn upload">上传</s>
                                            	</c:when>
                                            	<c:otherwise>
                                            		<s class="l-btn upload">更换</s>
                                            	</c:otherwise>
                                            </c:choose>
	                                            <input type="file" style="width: 80px;" accept="image/*" id="imgDatas" name="imgData" onchange="changCompanyIco('wx')">
                                        	</em>
<!--                                         	<s class="l-btn upload savebtn_mark" mark="wx_mark" onclick="updateMarkingInfo(this);">保存</s> -->
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<input type="hidden" value="${configId }" id="configId"/>
<input type="hidden" value="sys_middle" id="showPageType"/>

<!-- 选择链接框 -->
<div class="link-choice-box chooseUrl" data-pupwin="modal">
    <div>
        <div class="link-tab-btn">
            <p class="active original" mark="course">课程链接</p>
            <p class="custom" mark="link">自定义链接</p>
        </div>
        <div class="links-box">
            <div class="top-box">
                <div class="top-box-padd1">
                    <div class="link-line clear itemOne">
                        <p>学科</p>
                        <p class="cons_content">
                            
                        </p>
                    </div>
                    <div class="link-line clear itemTwo">
                        <p>学科小类</p>
                        <p class="cons_content">
                           
                        </p>
                    </div>
                    <div class="link-line clear classes">
                        <p>课程</p>
                        <p class="cons_content">
                           
                        </p>
                    </div>
                </div>
                
                <!-- 选择自定义链接 -->
                 <div class="top-box-padd2">
                    <table id="linkList_custom">
                        <tr>
                            <td class="col-1 col-tit">标题</td>
                            <td class="col-2">自定义域名</td>
                            <td class="col-3">发布时间</td>
                            <td class="col-4">发布人</td>
                        </tr>
                    </table>
                </div>
                
            </div>
            <div class="bottom-btn-box">
                <div class="pop-save" data-pupwin-btn="success">保存</div>
                <div class="pop-cancel" data-pupwin-btn="cancle">取消</div>
            </div>
        </div>
    </div>
</div>
 <script type="text/javascript">
   	$(function(){
   		$selectSubMenus('system_foot');
   	});
 </script>
  <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="<%=rootPath%>/javascripts/popupwinChang.js"></script>
  <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/javascripts/system/templeteCommon.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/javascripts/system/footTemplete.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/javascripts/system/img-hover.js"></script>
</body>
</html>