<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>排课管理</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
<script type="text/javascript" src="<%=rootPath %>/javascripts/operate.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/class/classes/showTc.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class/classes/classes.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%-- 控制  分页 查询 条数 --%>
<input type="hidden" value="5" id="pageSize"/>

<div class="u-wrap operate">
    <div class="mainbackground">
        <div class="select-option clear">
            <ul>
                <li>
                    <span class="s-title">学科</span>
                    <span class="s-list" id="one">
                        <a class="btn btn-sm btn-default oneItem active" data-id="" href="javascript:;" >全部</a>
                        <c:forEach var="o" items="${oneItem }">
                        	<a class="btn btn-sm btn-default oneItem" href="javascript:;" data-id="${o.id }">${o.itemName }</a>
                        </c:forEach>
                    </span>
                </li>
                <li id="two">
                    <span class="s-title">学科小类</span>
                    <span class="s-list">
                        
                    </span>
                </li>
                <li id="term">
                    <span class="s-title">考期</span>
                    <span class="s-list">
                    </span>
                </li>
                <li>
                    <span class="s-title">授课类型</span>
                    <span class="s-list">
                    	<a class="btn btn-sm btn-default classType active" data-type="" href="javascript:;">全部</a>
                        <a class="btn btn-sm btn-default classType" data-type="TEACH_METHOD_FACE" href="javascript:;">面授</a>
                        <a class="btn btn-sm btn-default classType" data-type="TEACH_METHOD_LIVE" href="javascript:;">直播</a>
                    </span>
                </li>
                <li id="campus">
                    <span class="s-title">校区</span>
                    <span class="s-list">
                    </span>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="u-wrap operate">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">排课</h2>
            <div class="search">
                <input type="text" class="input-ctrl" placeholder="班号" id="selNo"/>
                <input type="button" value="搜索" class="btn btn-sm btn-sel-no"  />
            </div>
            <span class="line"></span>
        </div>
        
        <div class="operate_list">
            <div class="select-option clear">
                <ul>
                    <li>
                        <span class="s-list">
                            <a class="btn btn-mini btn-success status" href="javascript:;" data-type="MODULE_NO_CREATED">未上架</a>
                            <a class="btn btn-mini btn-default status" href="javascript:;" data-type="MODULE_NO_ON_SALE">招生中</a>
                            <a class="btn btn-mini btn-default status" href="javascript:;" data-type="MODULE_NO_OFFLINE">招生结束</a>
                            <a class="btn btn-mini btn-default status" href="javascript:;" data-type="MODULE_NO_FINISH">已结课</a>
                        </span>
                        <span class="fr">
			                <a href="javascript:void(0);" onclick="addClassModuleNo()" class="btn btn-mini btn-primary"><em class="iconfont">&#xe606;</em> 新增班号</a>
			            </span>
                    </li>
                </ul> 
            </div>
            <div class="op-list">
            </div>
            <div class="pages">
                <ul class="pagination">
					
				</ul>
            </div>
        </div>
    </div>
</div>
<!-- 弹层信息 -->
<div class="layer-tips allowAdmissionsTc">
    <div class="layer-tips-title">提示信息 <i class="close iconfont">&#xe610;</i></div>
    <div class="layer-tips-content">
        <p></p>
        <p></p>
        <p></p>
    </div>
    <div class="layer-tips-btns">
    </div>
</div>
<!-- 弹层信息--已招学员-->
<div class="checkStudentTc">
<div class="check_student_hd">已报名学员<i class="close iconfont">&#xe610;</i></div>
    <div class="check_student_bd">
    </div>
    <div class="pages">
	    <ul class="paginations">
		</ul>
    </div>
</div>
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
</body>
</html>