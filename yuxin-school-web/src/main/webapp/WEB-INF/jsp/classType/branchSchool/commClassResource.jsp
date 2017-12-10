<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-课程资料</title> 
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css?v=1.0"/>
     <style type="text/css">
		.btn-upload{float: right;margin-top:-15px}
		.h5{width:100px}
		.up-input{position:relative;width:166px;height:32px;z-index:1}
		.upfile{position:relative;display:inline-block}
		.upfile .btn-up{position:absolute;top:0;left:0;}
		.tt tr td{
			border: 1px solid #ddd;
		}
		.tt th{
		     border: 1px solid #ddd;
			 padding: 8px;
		}
	</style>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/operate.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript">
     var rootPath='<%=rootPath %>';
    </script>
</head>
<body>
<div class="q-box">
<input type="hidden" value="${ct.companyId }" id="companyId">
<input type="hidden" value="${ct.id }" id="class"/>
<input type="hidden" value="${oneId }" id="oneId"/>
<input type="hidden" value="${twoId }" id="twoId"/>

<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonTitle.jsp"></jsp:include>
<div class="u-wrap company overflow points-use-class">
	<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonClass.jsp"></jsp:include>
    <div class="right-side">
     	<div class="u-wrap operate" style="width: 97%;margin:0 auto;">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">课程资料</h2>
            <span class="line"></span>
        </div>
        <div class="select-option clear" style="display: none;">
            <ul>
                <li>
                    <span class="s-title">学科</span>
                    <span class="s-list" id="one">
                        <c:forEach var="o" items="${oneItem }">
                        		<a class="btn btn-sm btn-default oneItem" ids="${o.id }" href="javascript:;" data-id="${o.id }">${o.itemName }</a>
                        </c:forEach>
                    </span>
                </li>
                <li id="two">
                    <span class="s-title">学科小类</span>
                    <span class="s-list">
                        
                    </span>
                </li>
                <li id="term">
                    <span class="s-title">课程</span>
                    <span class="s-list">
                    	<select id="class" class="js-example-basic-single" style="width: 180px;"></select>
                    </span>
                </li>
                <li>
                    <span class="s-title">资料类型</span>
                    <span class="s-list">
                    	<a class="btn btn-sm btn-success resource" href="javascript:;" data-type="">全部</a>
                    	<c:forEach var="r" items="${rtList }">
                    		<a class="btn btn-sm btn-default resource" href="javascript:;" data-type="${r.resourceType }">${r.resourceName }</a>
                    	</c:forEach>
                    </span>
                </li>
            </ul>
        </div>
        	<div class="u-wrap operate">
		    <div class="mainbackground">
		        <div class="operate_list">
		            <div class="op-list">
		            </div>
		            <div class="pages">
		                <ul class="pagination">
							
						</ul>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
</div>
		

    </div>
</div>
<!-- 结束 -->
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
$(document).ready(function(){
	selClassResource(1);
})

function selClassResource(page){
	 $.ajax({
		 url : rootPath + "/branchSchool/selResource",
		 type: "post",
		 data:{"classTypeId":$("#class").val(),"page":page,"pageSize":10},
		 dataType:"html",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		 success:function(data){
			 $(".op-list").html(data);
		 },
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	 });
}
</script>
</div>
</body>
</html>