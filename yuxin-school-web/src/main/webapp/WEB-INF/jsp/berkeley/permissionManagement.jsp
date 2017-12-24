<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>权限管理</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/footerPosition.js"></script>
    <script type="text/javascript">
        $(function(){
            $selectSubMenu('sys_user_auth');
        });
    </script>
    
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
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
        .u-wrap .mainbackground.nopadding{padding: 0;}
        .u-wrap{width: 100%;margin: 0;}
    </style>
    <%--tob--%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
<div class="u-wrap admin overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_berkeleyLeft.jsp"></jsp:include>
    <div class="right-side">
        <input type="hidden" id="peopleMark" value="${peoplemark }"/>
        <form id="myForm" method="post">
            <input type="hidden" name="type" id="type"/>
            <input type="hidden" name="userId" id="uIds"/>
            <input type="hidden" name="schoolId" id="schoolIds"/>
            <input type="hidden" name="companyId" id="companyId" value="${companyId}"/>
        </form>

        <div class="u-wrap set-system">
            <div class="mainbackground nopadding">
                <div class="heading">
                    <h2 class="h5">用户权限</h2>
                    <span class="line"></span>
                    <span class="rb">

				<%--<a href="javascript:Form.syncOrgUser('sync');" class="btn btn-mini btn-primary" style="height:14px">同步机构账号</a>--%>
            </span>
                </div>
                <div class="clear" style="width: 100%;">
                    <%--<p id="schoolListP" class="fl">--%>
                        
                        <%--<c:if test="${empty schoolList }">--%>
                            <%--<a href="javascript:;" class="btn btn-sm btn-default btn-success" mark="${school1.id }">${school1.schoolName }</a>--%>
                        <%--</c:if>--%>
                    <%--</p>--%>
                    <div class="fr" style="float: left;">
                        <form>
                            <p>
		        		<span style="border:1px solid #d7d7d7;border-radius:2px;padding: 5px;margin-right:5px;background-color: #fff;font-size: 1.2rem;vertical-align: middle;">
		        			<input style="border:none;" placeholder="用户名/姓名/手机号" id="condition"/>
		        		</span>
                                <select name="roleUid" id="roleUid" style="height: 29px;">
                                    <option value="">选择角色</option>
                                    <c:forEach items="${roleList }" var="roleList" varStatus="status">
                           			 	<option value="${roleList.roleUid}">${roleList.roleName }</option>
                        			</c:forEach>
                                </select>
                                <select name="status" id="status" style="height: 29px;">
                                    <option value="">选择状态</option>
                                    <option value="1">已启用</option>
                                    <option value="0">禁用</option>
                                </select>
                                <a class="btn btn-sm btn-primary" id="search_condition" style="cursor: pointer;vertical-align: middle;margin-left: 10px;">搜索</a>
                            </p>
                        </form>
                    </div>
                        <a href="javascript:Form.editUser('save');" class="btn btn-mini btn-primary" style="float: right;"><em class="iconfont">&#xe606;</em>添加用户</a>
                </div>
                <div class="user-list">

                </div>
            </div>
        </div>

       <%-- <script type="text/javascript" src="<%=rootPath %>/javascripts/branchschool/systemAuth.js"></script> --%>
        <script type="text/javascript" src="<%=rootPath %>/javascripts/branchschool/permissionManagement.js"></script>
    </div>
</div>
<input type="hidden" value="5" id="pageSize">
<input type="hidden" value="${companyId}" id="companyId">


<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>

<div class="pages pagination"></div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->

<script type="text/javascript">
    $(document).ready(function(){
        $(".pagination").pagination('${pageFinder.rowCount}', {
            next_text : "下一页",
            prev_text : "上一页",
            current_page :'${pageFinder.pageNo-1}',
            link_to : "javascript:void(0)",
            num_display_entries : 8,
            items_per_page : '${pageFinder.pageSize}',
            num_edge_entries : 1,
            callback:function(page,jq){
                var pageNo = page + 1;
                Form.queryUserRolesList(pageNo,"");
            }
        });
    });
</script>
<script>
    //    左侧active切换
    $selectSubMenus('permissionManagement');
</script>
</body>
</html>