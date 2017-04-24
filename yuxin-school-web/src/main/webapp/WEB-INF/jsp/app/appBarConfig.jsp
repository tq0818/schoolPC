<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>导航设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/plugins/swiper/swiper.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/app/app.css"/>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_appconfig.jsp"></jsp:include>
    
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">导航设置</span>
                </div>
            </div>
            <div class="cont-box">
                <p class="explain"><em>说明：可对APP首页的导航进行配置</em></p>

                <h3>预览：</h3>
<!--展示区  -->
                <div class="clear nav-block">
                    <div class="nav-cont fl">
                        <header>
                            <em>显示机构名称</em>
                            <span>北京校区
                                <i class="iconfont">&#xe623;</i>
                            </span>
                        </header>
                        <div class="banner"></div>
                        <div class="swiper-container">
                        <ul class="nav-show swiper-wrapper show-head-ul">
                        	<c:if test="${flag}"><!-- 开启头部导航才会显示 -->
	                        	<c:forEach items="${headCaps}" var="cap" varStatus="status">
	                               	<li num="${status.index}" class="choose-show swiper-slide">
										<div class="choose-show">
											<c:if test="${cap.pageCode==5}"><img src="<%=rootPath%>/images/app/wenda@2x.png" alt=""/></c:if>
											<c:if test="${cap.pageCode==6}"><img src="<%=rootPath%>/images/app/gongkike@2x.png" alt=""/></c:if>
											<c:if test="${cap.pageCode==3}"><img src="<%=rootPath%>/images/app/tiku@2x.png" alt=""/></c:if>
											<c:if test="${cap.pageCode==2}"><img src="<%=rootPath%>/images/app/kechen@2x.png" alt=""/></c:if>
											<c:if test="${cap.pageCode==4}"><img src="<%=rootPath%>/images/app/my@2x.png" alt=""/></c:if>
							                <p>${cap.name}</p>
						                </div>
					                </li>
				                </c:forEach>
			                </c:if>
                        </ul>
                        
                        </div>
                        <div class="recom-class">
                            <div>
                                <h2>推荐课程</h2>
                                <ul>
                                    <li class="clear">
                                        <div class="img-wrap">
                                            <img src="<%=rootPath %>/images/app/1.jpg" alt=""/>

                                            <div class="class-style hunhe"></div>
                                        </div>
                                        <div class="word-wrap">
                                            <h5>课程名称</h5>
                                            <div class="class-bottom">
                                                <span>
                                                    <i class="iconfont">&#xe6a1;</i>
                                                    <em>4900</em>
                                                </span>
                                                <span>
                                                    <i class="iconfont">&#xe6a1;</i>
                                                    <em>43</em>
                                                </span>
                                                <span class="price">免费</span>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="clear">
                                        <div class="img-wrap">
                                            <img src="<%=rootPath %>/images/app/1.jpg" alt=""/>

                                            <div class="class-style mianshou"></div>
                                        </div>
                                        <div class="word-wrap">
                                            <h5>课程名称</h5>

                                            <div class="class-bottom">
                                                <span>
                                                    <i class="iconfont">&#xe6a1;</i>
                                                    <em>4900</em>
                                                </span>
                                                <span>
                                                    <i class="iconfont">&#xe6a1;</i>
                                                    <em>43</em>
                                                </span>
                                                <span class="price">免费</span>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <footer>
                            <ul>
                            	<c:if test="${!empty footCaps}">
                                    <c:forEach items="${footCaps}" var="cap" varStatus="status">
	                                	<li num="${status.index}">
											<div class="choose-show">
												<c:if test="${cap.pageCode==5}"><img src="<%=rootPath%>/images/app/wenda@2x.png" alt=""/></c:if><!--底部导航没有问答导航图标 -->
												<c:if test="${cap.pageCode==6}"><img src="<%=rootPath%>/images/app/gongkaike_grey@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==3}"><img src="<%=rootPath%>/images/app/topic@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==2}"><img src="<%=rootPath%>/images/app/course@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==1}"><img src="<%=rootPath%>/images/app/home@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==4}"><img src="<%=rootPath%>/images/app/me@2x.png" alt=""/></c:if>
								                <p>${cap.name}</p>
							                </div>
						                </li>
					                </c:forEach>
                                    </c:if>
                                
                            </ul>
                        </footer>
                    </div>
                    
                    
                    <!--配置区域  -->
                    <div class="nav-set-cont fl">
                        <div class="nav-set-itm head-nav-set">
                            <div class="label-title">首页导航</div>
                            <c:if test="${flag}">
	                            <div class="nav-static open" >
	                                <i class="iconfont">&#xe603;</i>
	                                <em>启用</em>
	                            </div>
                            </c:if>
                            <c:if test="${!flag}">
	                            <div class="nav-static l-close" >
	                                <i class="iconfont">&#xe604;</i>
	                                <em>禁用</em>
	                            </div>
                            </c:if>
                            <div class="nav-choose clear">
                                <ul class="clear fl">
                                	<c:forEach items="${headCaps}" var="cap" varStatus="status">
	                                	<li num="${status.index}" data-id="${cap.id}" data-type="${cap.pageType}" data-code="${cap.pageCode}" class="ui-state-default headli " >
											<div class="choose-show">
												<c:if test="${cap.pageCode==5}"><img src="<%=rootPath%>/images/app/wenda@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==6}"><img src="<%=rootPath%>/images/app/gongkike@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==3}"><img src="<%=rootPath%>/images/app/tiku@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==2}"><img src="<%=rootPath%>/images/app/kechen@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==4}"><img src="<%=rootPath%>/images/app/my@2x.png" alt=""/></c:if>
								                <p>${cap.name}</p>
							                </div>
							                <div class="choose-mask">
								                <button class="eidtBtn">编辑</button>
								                <i class="iconfont mask-close-btn">&#xe610;</i>
							                </div>
							                <div class="delet-alert">
								                <p><i class="iconfont">&#xe653;</i>删除后该导航将不在APP首页显示<br/>是否删除</p>
								                <div class="btns-wrap">
									                <button class="btn-yes">是</button>
									                <button class="btn-no">否</button>
								                </div>
							                </div>
						                </li>
					                </c:forEach>
                                    <li>
                                        <div class="nav-add-icon fl">
                                            <i class="iconfont">&#xe629;</i>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="nav-choose-alert">
                                <div>
                                    <span>选择功能模块</span>
                                    <select name="" id="navhead">
                                        <option value="2">课程</option>
                                        <option value="3">题库</option>
                                        <option value="6">公开课</option>
                                        <!-- <option value="5">问答</option> -->
                                        <option value="4">我的</option>
                                    </select>
                                </div>
                                <div>
                                    <span>自定义名称</span>
                                    <input type="text"  id="head_custom_name"/>
                                </div>
                                <div class="btns-wrap">
                                    <button class="nav-success-btn" data-type="head">保存</button>
                                    <button class="nav-cancel-btn">取消</button>
                                </div>
                            </div>
                        </div>
                        <div class="nav-set-itm foot-nav-set">
                            <div class="label-title">底部菜单</div>
                            <div class="nav-choose clear">
                                <ul class="clear fl">
                                	<c:if test="${!empty footCaps}">
                                    <c:forEach items="${footCaps}" var="cap" varStatus="status">
	                                	<li num="${status.index}"  data-id="${cap.id}" data-type="${cap.pageType}" data-code="${cap.pageCode}" class="ui-state-default footli">
											<div class="choose-show">
												<c:if test="${cap.pageCode==5}"><img src="<%=rootPath%>/images/app/wenda@2x.png" alt=""/></c:if><!--底部导航没有问答导航图标 -->
												<c:if test="${cap.pageCode==6}"><img src="<%=rootPath%>/images/app/gongkaike_grey@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==3}"><img src="<%=rootPath%>/images/app/topic@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==2}"><img src="<%=rootPath%>/images/app/course@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==1}"><img src="<%=rootPath%>/images/app/home@2x.png" alt=""/></c:if>
												<c:if test="${cap.pageCode==4}"><img src="<%=rootPath%>/images/app/me@2x.png" alt=""/></c:if>
								                <p>${cap.name}</p>
							                </div>
							                <div class="choose-mask">
								                <button class="eidtBtn">编辑</button>
								                <c:if test="${cap.pageCode != 4}"><i class="iconfont mask-close-btn">&#xe610;</i></c:if>
							                </div>
							                <div class="delet-alert">
								                <p><i class="iconfont">&#xe653;</i>删除后该导航将不在APP首页显示<br/>是否删除</p>
								                <div class="btns-wrap">
									                <button class="btn-yes">是</button>
									                <button class="btn-no">否</button>
								                </div>
							                </div>
						                </li>
					                </c:forEach>
                                    </c:if>
                                    <c:if test="${empty footCaps}">
                                    	<li num="2" data-id="" data-type="foot" data-code="1" class="ui-state-default footli">
											<div class="choose-show">
												<img src="<%=rootPath%>/images/app/home@2x.png" alt=""/>
								                <p>首页</p>
							                </div>
							                <div class="choose-mask">
								                <button>编辑</button>
								                <i class="iconfont mask-close-btn">&#xe610;</i>
							                </div>
							                <div class="delet-alert">
								                <p><i class="iconfont">&#xe653;</i>删除后该导航将不在APP首页显示<br/>是否删除</p>
								                <div class="btns-wrap">
									                <button class="btn-yes">是</button>
									                <button class="btn-no">否</button>
								                </div>
							                </div>
						                </li>
						                <li num="1" data-id="" data-type="foot" data-code="2" class="ui-state-default footli">
											<div class="choose-show">
												<img src="<%=rootPath%>/images/app/course@2x.png" alt=""/>
								                <p>课程</p>
							                </div>
							                <div class="choose-mask">
								                <button>编辑</button>
								                <i class="iconfont mask-close-btn">&#xe610;</i>
							                </div>
							                <div class="delet-alert">
								                <p><i class="iconfont">&#xe653;</i>删除后该导航将不在APP首页显示<br/>是否删除</p>
								                <div class="btns-wrap">
									                <button class="btn-yes">是</button>
									                <button class="btn-no">否</button>
								                </div>
							                </div>
						                </li>
						                <li class="ui-state-default" data-code="4">
	                                        <div class="choose-show">
	                                            <img src="<%=rootPath%>/images/app/me@2x.png" alt=""/>
	                                            <p>我的</p>
	                                        </div>
	                                    </li>
						                
                                    </c:if>
                                    <li>
                                        <div class="nav-add-icon fl">
                                            <i class="iconfont">&#xe629;</i>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="nav-choose-alert">
                                <div>
                                    <span>选择功能模块</span>
                                    <select name="" id="navfoot">
                                        <option value="1">首页</option>
                                        <option value="2">课程</option>
                                        <option value="3">题库</option>
                                        <option value="4">我的</option>
                                        <option value="6">公开课</option>
                                    </select>
                                </div>
                                <div>
                                    <span>自定义名称</span>
                                    <input type="text" value="首页" id="foot_custom_name"/>
                                </div>
                                <div class="btns-wrap">
                                    <button class="nav-success-btn" data-type="foot">保存</button>
                                    <button class="nav-cancel-btn">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
<div class="mask-bg"></div>    
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenus('app_content');
    	});
    </script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
    <script src="<%=rootPath%>/plugins/swiper/swiper.min.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/sys-focus.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/app/app-nav.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/app/appBanner.js"></script>
</body>
</html>