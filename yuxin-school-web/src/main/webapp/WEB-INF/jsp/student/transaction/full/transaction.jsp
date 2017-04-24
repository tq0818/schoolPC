<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>异动</title>
    <%@include file="/decorators/import.jsp"%>
        <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
        <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('student_manage');
    		//加载报名信息
    		var stuId=$("#stuId").val();
    		$.ajax({
    			url:"<%=request.getContextPath()%>/student/full/selectEntryMessage",
    			type:"post",
    			data:{
    				"stuId":stuId
    			},
    			success:function(html){
    				//$.loading("玩命加载中...");
    				$(".m-b-m").html(html);
    				//$.loadover();
    			}
    			
    		})
    		//初始化日期框
			$(".date-picker").datetimepicker({
				format: "yyyy-mm-dd",
 				minView:2,
 				autoclose: true,
 				language: "zh-CN"
			});
    	})
    	function edit(){
    		$("#oldMessage").hide();
    		$("#editMessage").show();
    		$("#contractMsg").hide();
    	}
    	function save(){
    		var form=$("#studentMessage").serialize();
    		$.ajax({
    			url:"<%=request.getContextPath()%>/student/update2",
    			type:"post",
    			data:form,
    			success:function(html){
    				$("#allMessage").html(html);
    			}
    		})
    	}
    	function search(){
    		$(".tips").hide();	
    		var mobile = $("#mb").val();
    		$.ajax({
    			url:"<%=request.getContextPath()%>/student/searchTransaction",
    			type:"post",
    			data:{
    				"mobile":mobile
    			},
    			success:function(data){
    				if("yes"==data){
    					$.ajax({
    						url:"<%=request.getContextPath()%>/student/loadStudent",
    						type:"post",
    		    			data:{
    		    				"mobile":mobile
    		    			},
    		    			success:function(html){
    		    				$("#allMessage").html(html);
    		    			}
    					})
    					$.ajax({
			    			url:"<%=request.getContextPath()%>/student/full/selectEntryMessage",
			    			type:"post",
			    			data:{
			    				"mobile":mobile
			    			},
			    			success:function(html){
			    				$(".m-b-m").html(html);
			    			}
    			
    				  })
    					
    				}else{
    					$(".tips").show();	
    				}
    			}
    		})
    	}
    </script>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
<input id="stuId" type="hidden" value="${student.id }"/>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">异动</h2>
            <div class="search">
            	 <i class="tips">没有查到相关信息!</i>
                <input type="text" id="mb" class="input-ctrl">
                <input type="button" class="btn btn-sm" value="搜索" onclick="search();">
            </div>
            <span class="line"></span>
        </div>
        <div id="allMessage">
        <div class="mark-more">
            <div class="main-content" id="oldMessage">
                <div class="m-title">
                    <h2 class="h6">基本信息</h2>
                    <span class="more">
                        <a href="javascript:;" class="m">更多</a>
                        <a href="javascript:;" class="edit" onclick="edit();">编辑</a>
                    </span>
                </div>
                <ul class="list-infos clear">
                   <li>
                        <p class='c'>
                            <span class="c-title">用户名</span>
                            <span class="c-content cc">${student.username}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">姓名</span>
                            <span class="c-content cc">${student.name }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">性别</span>
                            <span class="c-content">${wx:dictCode2Name(student.sex)}</span>
                        </p>
                    </li>
                    <li class="none">
                        <p class='c'>
                            <span class="c-title">出生日期</span>
                            <span class="c-content cc"><fmt:formatDate value="${student.birthday}" timeStyle="yyyy-MM-dd"/></span>
                        </p>
                    </li>
                    <li id="more-tel">
                        <p class='c'>
                            <span class="c-title">手机号</span>
                            <span class="c-content cc"><em id="mobile">${student.mobile}</em></span>
                        </p>
                    </li>
                    <li style="display: none;">
                        <p class='c'>
                            <span class="c-title">年龄</span>
                            <span class="c-content cc">${student.age}</span>
                        </p>
                    </li>
                    <li class="none">
                        <p class='c'>
                            <span class="c-title">户口所在地</span>
                            <span class="c-content cc">${student.hkAddress}</span>
                        </p>
                    </li>
                    <li class="none">
                        <p class='c'>
                            <span class="c-title">最高学历</span>
                            <span class="c-content">${wx:dictCode2Name(student.educationCode)}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">证件类型</span>
                            <span class="c-content">${wx:dictCode2Name(student.identityTypeCode)}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">证件号码</span>
                            <span class="c-content cc">${student.identityId}</span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="main-content none" id="contractMsg">
                <div class="m-title">
                    <h2 class="h6">联系信息</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">手机号</span>
                            <span class="c-content"><em>${student.mobile}</em></span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">家庭电话</span>
                            <span class="c-content">${student.homePhone}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">办公电话</span>
                            <span class="c-content">${student.officePhone}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急联系人</span>
                            <span class="c-content">${student.emergencyContact}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急人电话</span>
                            <span class="c-content">${student.emergencyPhone}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">邮箱</span>
                            <span class="c-content">${student.email}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">QQ号</span>
                            <span class="c-content">${student.qq}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">微信</span>
                            <span class="c-content">${student.weixinId}</span>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        <form action="" method="post" id="studentMessage">
    	<input type="hidden" name="id" value="${student.id }"/>
        <div class="mark-more none"  id="editMessage">
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">基本信息</h2>
                    <span class="more">
                        <a href="javascript:;" class="e" onclick="save();">保存</a>
                    </span>
                </div>
                
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">用户名</span>
                            <span class="c-content">
                                <input type="text" name="username" value="${student.username }" disabled="disabled">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">姓名</span>
                            <span class="c-content"><input type="text" name="name" value="${student.name }"></span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">性别</span>
                            <span class="c-content">
                                <input type="radio" name="sex" value="MALE" 
                                <c:if test="${student.sex=='MALE'}">checked</c:if>
                                > 男
                                <input type="radio" name="sex" value="FEMALE" 
                                 <c:if test="${student.sex=='FEMALE'}">checked</c:if>
                                > 女
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">出生日期</span>
                            <span class="c-content">
                                <input type="text" class="datetime-picker date-picker" name="birthday" value="<fmt:formatDate value='${student.birthday }' timeStyle='yyyy-MM-dd'/>">
                            </span>
                        </p>
                    </li>                    
                    <li style="display: none;">
                        <p class='c'>
                            <span class="c-title">年龄</span>
                            <span class="c-content">
                                <input type="text" name="age" value="${student.age }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">户口所在地</span>
                            <span class="c-content">
                                <input type="text" name="hkAddress" value="${student.hkAddress }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">最高学历</span>
                            <span class="c-content">
                            	<select name="educationCode">
                            		<option value="UNDER_JUNIOR" 
                            		<c:if test="${student.educationCode== 'UNDER_JUNIOR'}">selected="selected"</c:if>
                            		>大专以下</option>
                            		<option value="JUNIOR"
                            		<c:if test="${student.educationCode== 'JUNIOR'}">selected="selected"</c:if>
                            		>大专</option>
                            		<option value="BECHELOR"
                            		<c:if test="${student.educationCode== 'BECHELOR'}">selected="selected"</c:if>
                            		>本科</option>
                            		<option value="POSTGRADUATE"
                            		<c:if test="${student.educationCode== 'POSTGRADUATE'}">selected="selected"</c:if>
                            		>研究生</option>
                            		<option value="DOCTOR"
                            		<c:if test="${student.educationCode== 'DOCTOR'}">selected="selected"</c:if>
                            		>博士生及以上</option>
                            	</select>
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">证件类型</span>
                            <span class="c-content">
                            
                                <select name="identityTypeCode" id="">
                                    <option value="ID_IDCARD"
                                    <c:if test="${student.identityTypeCode=='ID_IDCARD' }">selected</c:if>
                                    >身份证</option>
                                    <option value="ID_PASSPORT"
                                    <c:if test="${student.identityTypeCode=='ID_PASSPORT' }">selected</c:if>
                                    >护照</option>
                                    <option value="ID_HMP"
                                    <c:if test="${student.identityTypeCode=='ID_HMP' }">selected</c:if>
                                    >港澳通行证</option>
                                    <option value="ID_TCP"
                                    <c:if test="${student.identityTypeCode=='ID_TCP' }">selected</c:if>
                                    >台胞证</option>
                                    <option value="ID_OFFICERS"
                                    <c:if test="${student.identityTypeCode=='ID_OFFICERS' }">selected</c:if>
                                    >军官证</option>
                                    <option value="ID_SERGEANTS"
                                    <c:if test="${student.identityTypeCode=='ID_SERGEANTS' }">selected</c:if>
                                    >士官证</option>
                                    <option value="ID_UNCONFM_ID"
                                    <c:if test="${student.identityTypeCode=='ID_UNCONFM_ID' }">selected</c:if>
                                    >其他</option>
                                </select>
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">证件号码</span>
                            <span class="c-content">
                                <input type="text" name="identityId" value="${student.identityId }">
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">联系信息</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">手机号</span>
                            <span class="c-content">
                                <input type="text" name="mobile" value="${student.mobile }" disabled="disabled">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">家庭电话</span>
                            <span class="c-content">
                                <input type="text" name="homePhone" value="${student.homePhone }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">办公电话</span>
                            <span class="c-content">
                                <input type="text" name="officePhone" value="${student.officePhone }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急联系人</span>
                            <span class="c-content">
                                <input type="text" name="emergencyContact" value="${student.emergencyContact }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急人电话</span>
                            <span class="c-content">
                                <input type="text" name="emergencyPhone" value="${student.emergencyPhone }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">邮箱</span>
                            <span class="c-content">
                                <input type="text" name="email" value="${student.email }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">QQ号</span>
                            <span class="c-content">
                                <input type="text" name="qq" value="${student.qq }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">微信</span>
                            <span class="c-content">
                                <input type="text" name="weixinId" value="${student.weixinId }">
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        </form>
        </div>
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">报名信息</h2>
            </div>
            <div class="m-b-m">
               
            </div>
        </div>
    </div>
</div>
</body>
</html>