<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>服务管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css"/>
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
        <div class="heading">
            <h2 class="h5">服务设置</h2>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <table class="table table-center" id="tableList">
                <tr data-buy="true">
                    <th width="10%">序号</th>
                    <th width="10%">服务模块</th>
                    <th width="10%">服务名称</th>
                    <th width="10%">状态</th>
                    <th width="20%">操作</th>
                </tr>

                <c:forEach items="${sysConfigDicts.data}" var="dict" varStatus="status">
                    <tr data-buy="true">
                        <th width="10%">${(sysConfigDicts.pageNo-1)*sysConfigDicts.pageSize+status.index+1 }</th>
                        <th width="10%">${dict.itemValue}</th>
                        <th width="10%">${dict.dictName}</th>
                        <th width="10%">
                            <c:choose>
                                 <c:when test="${dict.delFlag==1 }">
                                     已开通
                                </c:when>
                            <c:otherwise>
                                     未开通
                            </c:otherwise>
                            </c:choose>
                        </th>
                        <th width="10%">
	                        <c:choose>
	                            <c:when test="${dict.delFlag==1 }">
	                                <a class="btn btn-link btn-mini serviceCloseBtn" onclick="closeBtn(${dict.companyId},'${dict.groupCode}',${dict.delFlag})" href="##">关闭<a/>
	                            </c:when>
	                            <c:otherwise>
	                                <a class="btn btn-link btn-mini" onclick="closeBtn(${dict.companyId},'${dict.groupCode}',${dict.delFlag})" href="##">开通</a>
	                            </c:otherwise>
	                        </c:choose>
                        </th>
                    </tr>
                </c:forEach>
            </table>
            <div class="pages pagination"></div>
        </div>
    </div>
</div>
<form id="myForm" method ="POST"  action ="<%=rootPath %>/serviceManager/getServiceManager/${companyId}" >
<input type="hidden" name="page" id="page" >
<input type="hidden" value="10" id="pageSize">
<input type="hidden" name="companyId" value="${companyId}" id="companyId">
</form>


<%--<div class="pages">
    <ul class="pagination"></ul>
</div>--%>

<script type="text/javascript">
    $(document).ready(function(){
        $(".pagination").pagination('${sysConfigDicts.rowCount}', {
            next_text : "下一页",
            prev_text : "上一页",
            current_page :'${sysConfigDicts.pageNo-1}',
            link_to : "javascript:void(0)",
            num_display_entries : 8,
            items_per_page : '${sysConfigDicts.pageSize}',
            num_edge_entries : 1,
            callback:function(page,jq){
            	var pageNo = page + 1;
            	$("#page").val(pageNo);
            	document.getElementById("myForm").submit();
            }
        });
    });
</script>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
function closeBtn(companyId,itemCode,delFlag) {

    $.ajax({
        type : 'post',
        url : rootPath+'/serviceManager/updateDelFlag',
        data : {
            'companyId' : companyId,
            'itemCode' : itemCode,
            'delFlag' : delFlag
        },
        dataType: 'json',
        success : function(data){
        	if(data.msg=="success"){
                window.location.href = rootPath+"/serviceManager/getServiceManager/"+companyId;
        	}else{
        		alert("操作失败");
        		return;
        	}
        	
        }
    });
}

</script>
<script>
    //    左侧active切换
    $selectSubMenus('getServiceManager');
</script>
</body>
</html>