<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师管理</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/resource.css"/>
<link href="<%=rootPath%>/stylesheets/user.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" />
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.css" />
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <style type="text/css">
    	.classes .mainbackground .c-main .add-classes p.h{
    		margin-bottom: 15px;
    	}
    </style>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>

<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/resource/teacher/headPicInit.js"></script>
<style>
	.hide{display:none}
	.show{display:block}
</style>
</head>
<body>
<script type="text/javascript">
$(function(){
	$('.upload')
    .on('click','a.btn',function(){
        $(this).next('input[type="file"]').click();
    });
	
	$(".pic").on("change","#target", function() {
		var theImage = new Image();
		console.log($(this).attr("src"));
		theImage.src = $(this).attr("src");
		 if (theImage.complete) {
			 	sourceHeight = theImage.height;
				sourceWidth = theImage.width;
				$.init(sourceWidth, sourceHeight);
			    } else {
			    	theImage.onload = function () {
			        	sourceHeight = theImage.height;
					sourceWidth = theImage.width;
					$.init(sourceWidth, sourceHeight);
			        };
			    }; 
		
	});
});

</script>
	<!-- 二级导航开始 -->
	<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
	<!-- 二级导航结束 -->
	
	<!--  内容开始 -->
		<div class="u-wrap resource">
		    <div class="mainbackground">
		        <div class="heading">
		            <h2 class="h5">老师</h2>
		            <span class="line"></span>
		        </div>
		        <div class="mark-more">
		        <form method="post" id="teacherManageForm">
		        <%-- <c:if test="${empty teacher.id}"> --%>
		        <div class="main-content" style="margin-bottom: -100px;">
                <div class="m-title">
                    <h2 class="h6">账户信息</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">登陆账号<em>*</em></span>
                            <span class="c-content">
                                <input type="text" name="userName" id="userName">
                            </span>
                        </p>
                    </li>
                    <li>
                    	<div style="width: 130px;margin-left: 80px;">
			                <img id='choiceHead' width='150' height='85'/>
			                <a href="javascript:;" class="btn btn-sm btn-default addPic" style="display: block;text-align: center;width:115px;">选择头像</a>
			            </div>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">密码<em>*</em></span>
                            <span class="c-content">
                                <input type="password" name="pwd" id="pwd">
                            </span>
                        </p>
                    </li>
                    <li></li>
                    <li>
                        <p class='c'>
                            <span class="c-title">确认密码<em>*</em></span>
                            <span class="c-content">
                                <input type="password" name="pwdNext" id="pwdNext">
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
            <input type="hidden" name="teaOrAdu" value="tea"/>
		        <%-- </c:if> --%>
		        
		        	
		            <div class="main-content">
		                <div class="m-title">
		                    <h2 class="h6">基本信息</h2>
		                    <span class="more">
		                        <a href="javascript:;" class="e"></a>
		                    </span>
		                </div>
		                <input type="hidden" value="${teacher.id }" name="id" id="teacherId"/>
		                <ul class="list-infos clear">
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">姓名<em>*</em></span>
		                            <span class="c-content"><input type="text" id="teacherName" name="name" value="" ></span>
		                        </p>
		                    </li>
		                    
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">是否是名师<em>*</em></span>
		                            <span class="c-content">
		                                <input type="radio" name="isDistinguished" value="1" checked> 是
		                                <input type="radio" name="isDistinguished" value="0" > 否
		                            </span>
		                        </p>
		                    </li>
							<li>
								<p class='c'>
									<span class="c-title">教师级别<em>*</em></span>
									<span class="c-content">
										<select name="teacherLevel">
											<option value="GRADE_HIGH" >小学高级</option>
											<option value="MIDDLE_GRADE_HIGH" >中小学高级</option>
											<option value="MIDDLE_ONE">中学一级</option>
											<option value="MIDDLE_TWO" >中学二级</option>
											<option value="MIDDLE_HIGH">中学高级</option>
										</select>
									</span>
								</p>
							</li>

							<li>
								<p class='c'>
									<span class="c-title">所在区域<em>*</em></span>
		                            <span class="c-content">
		                                <select name="teacherArea">
											<option value="alone" >直属直管</option>
											<option value="qing_yang" >青羊区</option>
											<option value="jin_niu">金牛区</option>
											<option value="jin_jiang" >锦江区</option>
											<option value="wu_hou">武侯区</option>
											<option value="gao_xin">高新区</option>
											<option value="tian_fu_xin">天府新区</option>
											<option value="cheng_hua">成华区</option>
											<option value="long_quan_yi">龙泉驿区</option>
											<option value="shuang_liu">双流区</option>
										</select>
		                            </span>
								</p>
							</li>
							<li>
		                        <p class='c'>
		                            <span class="c-title">出生日期</span>
		                            <span class="c-content">
		                               <!--  <input type="text" value="1997-04-09"> -->
		                                <input type="text" readonly="readonly" name="birthday"  value="" id="datetimepicker">
		                            </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">性别</span>
		                            <span class="c-content">
		                                <input type="radio" name="sex" value="MALE" checked> 男
		                                <input type="radio" name="sex" value="FEMALE" }> 女
		                            </span>
		                        </p>
		                    </li>           
		                   
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">现居住址</span>
		                            <span class="c-content">
		                                <input type="text" name="address" value="">
		                            </span>
		                        </p>
		                    </li>
		                     <li>
		                        <p class='c'>
		                            <span class="c-title">最高学历</span>
		                            <span class="c-content">
		                                <select name="educationCode">
		                                	<option value="UNDER_JUNIOR" >大专以下</option>
		                                	<option value="JUNIOR" >大专</option>
		                                	<option value="BECHELOR">本科</option>
		                                	<option value="POSTGRADUATE" >研究生</option>
		                                	<option value="DOCTOR">博士生及以上</option>
		                                </select>
		                            </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">身份证</span>
					                 <span class="c-content">
					                     <input type="text" name="idNumber" value="" />
					                 </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">开户行</span>
					                 <span class="c-content">
					                     <input type="text" name="bankName" value="" />
					                 </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">开户名</span>
					                 <span class="c-content">
					                     <input type="text" name="bankAccountName" value="" />
					                 </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">银行卡号</span>
					                 <span class="c-content">
					                     <input type="text" name="bankAccountNum" value="" />
					                 </span>
		                        </p>
		                    </li>
							<li>
								<p class="c">
									<span class="c-title">学校名称<em>*</em></span>
										<span class="c-content" style="width: 60%">
											<input name="schoolName" id="schoolName" type="text" style="width:350px">
										</span>
								</p>
							</li>
							<li>
								<p class="c">
									<span class="c-title">学校简称<em>*</em></span>
										<span class="c-content">
											<input name="schoolShortName" id="schoolShortName" type="text" maxlength="6">
										</span>
								</p>
							</li>
		                   <li style="width:100%;">
		                        <p class='c' style="width:100%;">
		                            <span class="c-title" style="width:15%;">简介</span>
					                 <span class="c-content" style="width:80%;">
					                     <textarea name="resume" id="resume" rows="8" cols="50" style="width:80%;">${teacher.resume}</textarea>
					                 </span>
		                        </p>
		                    </li>
							<li style="width:100%;margin-top:115px;">
								<p class='c' style="width:100%;">
									<span class="c-title" style="width:15%;">摘要</span>
									<span class="c-content" style="width:80%;">
					                     <textarea name="remark" id="remark" rows="8" cols="50" style="width:80%;">${teacher.remark}</textarea>
									</span>
								</p>
							</li>
		                    
		                </ul>
		            </div>
		            
		            <div class="main-content" style="margin-top:80px;margin-bottom: -120px;">
		                <div class="m-title">
		                    <h2 class="h6">联系信息</h2>
		                </div>
		                <ul class="list-infos clear">
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">手机号<em>*</em></span>
		                            <span class="c-content">
		                                <input type="text" name="mobile" value="" id="mobile">
		                            </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">家庭电话</span>
		                            <span class="c-content">
		                                <input type="text" name="homePhone" value="">
		                            </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">办公电话</span>
		                            <span class="c-content">
		                                <input type="text" name="workPhone" value="">
		                            </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">紧急联系人</span>
		                            <span class="c-content">
		                                <input type="text" name="emergencyContactName" value="">
		                            </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">紧急人电话</span>
		                            <span class="c-content">
		                                <input type="text" name="emergencyContactPhone" value="">
		                            </span>
		                        </p>
		                    </li>
		                    <li>
		                        <p class='c'>
		                            <span class="c-title">邮箱</span>
		                            <span class="c-content">
		                                <input type="text" name="email" value="">
		                            </span>
		                        </p>
		                    </li>
		                </ul>
		            </div>
		            <input type="hidden" name="headpicUrl" id="headpicUrl"/>
		            
		            
		            <div class="main-content">
		                <div class="m-title">
		                    <h2 class="h6">授课信息</h2>
		                </div>
		                <ul class="list-infos clear">       
		                    <li class="long">
		                        <p class='c'>
		                            <span class="c-title">学科</span>
		                            <span class="c-content itemOneClass">
		                                <c:forEach items="${firstItems }" var="item" varStatus="index">
		                                	<c:choose>
											       <c:when test="${index.index == 0}">
											                <a href="javascript:;" itemOneId="${item.id }" onclick="chooseOneItem(this, ${item.id})" class="btn btn-mini btn-success itemOne">${item.itemName }</a>
											                <input type="hidden" value="${item.id}" name="itemOneId" id="itemOneId"/>
											       </c:when>
											       <c:otherwise>
											               <a href="javascript:;" itemOneId="${item.id }" onclick="chooseOneItem(this, ${item.id})"  class="btn btn-mini btn-default itemOne">${item.itemName }</a>
											       </c:otherwise>
											</c:choose>
		                                </c:forEach>
		                            </span>
		                        </p>
                                <c:forEach var="secondMap" items="${secondItemMap }" varStatus="index">
                                	<c:choose>
									       <c:when test="${index.index == 0}">
									              <p class='c secondItem show' item-one-id=${secondMap.key }>
									       </c:when>
									       <c:otherwise>
									              <p class='c secondItem hide' item-one-id=${secondMap.key }>
									       </c:otherwise>
									</c:choose>
                                		<span class="c-title">学科小类</span>
	                                	<span class="c-content itemTwoClass">
										    <c:forEach var="second" items="${secondMap.value }" varStatus="dex">
											    <c:choose>
												       <c:when test="${dex.index == 0}">
											                <a href="javascript:;" itemTwoId="${second.id }" class="btn btn-mini itemTwo btn-success">${second.itemName }</a>
												       </c:when>
												       <c:otherwise>
											                <a href="javascript:;" itemTwoId="${second.id }" class="btn btn-mini itemTwo btn-default">${second.itemName }</a>
												       </c:otherwise>
												</c:choose>
										    </c:forEach>
									    </span>
								    </p>
								</c:forEach>
								<input type="hidden" value="" name="itemSecondId" id="itemSecondId"/>
		                    </li>
		                </ul>
		                <!-- <div class="box-config">
		                    <div class="tabs">
		                        <a href="javascript:;" teachMethod="TEACH_METHOD_LIVE" class="btn btn-mini btn-success">直播</a>
		                        <a href="javascript:;" teachMethod="TEACH_METHOD_FACE" class="btn btn-mini btn-default">面授</a>
		                    </div>
		                    <div class="sel-tabs">
			                    <div class="box-select">
			                        <span class="curr">
			                            <i class="iconfont">&#xe61e;</i>
			                            <i class="iconfont">&#xe61f;</i>
			                        </span>
			                        ajax加载一级学科小类对应的模块
			                        <div class="left">
			                            
			                        </div>
			                        <div class="right">
			                            <ul>
			                            </ul>
			                        </div>
			                    </div>
		                    </div>
		                    
		                </div> -->
		            </div>
		            </form>
		            <!--ceshi后台git提交  -->
		            <div class="m-bo text-center" >
		                <a href="javascript:;" class="btn btn-sm btn-primary">保存</a>
		                <a href="<%=rootPath%>/sysConfigTeacher/toTeacherIndex" class="btn btn-sm btn-default">取消</a>
		            </div>
		        </div>
		    </div>
		</div>
	<!--  内容结束 -->
	<!-- 添加 -->
	<div class="wrap edit-content user-main nospace add-subs-layer add-head" style="margin-left: -43%;margin-top: -140px;height: 400px;">
    <div class="edit-main">
        <div class="edit-picture clear">
            <div class="picture-left">
                <div class="upload">
                    <a href="javascript:;" class="btn btn-sm btn-default">上传图片</a>
                    <input type="file"  class="file-input" accept=".jpg,.png,.gif" name="imgData" id="imgData" onchange="savePic()" value="重新选择文件">
                </div>
		        
                <div class="upload-div pic" >
					<img id="target" src="" style="height: 300px; width: 300px;" />
                </div>
            </div>
            <div class="attributes none">
		        	<input type="hidden" id="x" name="x" value="0"/>
		            <input type="hidden" id="y" name="y" value="0"/>
		            <input type="hidden" id="w" name="w" value="0"/>
		            <input type="hidden" id="h" name="h" value="0"/>
		            <input type="hidden" id="x2" name="x2" value="0"/>
		            <input type="hidden" id="y2" name="y2" value="0"/>
		    </div>
            <div class="picture-right">
                <h2 class="h5">效果预览</h2>
                <p>您上传的头像会自动生成三种尺寸，请注意头像是否清晰。</p>
                <div class="view-big p1">
                    <p class="pic-view preview-container"><img id="img1" src="" alt=""></p>
                    <p class="content-text">大尺寸 150*150像素</p>
                </div>
                <div class="view-normal p2">
                    <p class="pic-view preview-container"><img id="img2" src="" alt=""></p>
                    <p class="content-text">中尺寸 110*110像素</p>
                </div>
                <div class="view-sm p3">
                    <p class="pic-view preview-container"><img id="img3" src="" alt=""></p>
                    <p class="content-text">小尺寸 40*40像素</p>
                </div>
            </div>
        </div>
        </div>
        <div class="btns">
            <p>
                <a href="javascript:saveHeadPic();" class="btn btn-sm btn-default">完成</a>
                <a href="javascript:;" class="btn btn-sm btn-default hideBG">取消</a>
            </p>
        </div>
    </div>
</div>
<input type="hidden" id="imgUrl" value="${imgUrl}"/>
<input type="hidden" value="${teaStatus}" id="teaStatus"/>

<div class="add-subs-layer-bg"></div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->
	<script type="text/javascript" src="<%=rootPath%>/javascripts/resource/teacher/teacherManage.js"></script>
	<script>
		$(function(){
			$selectSubMenu('resource_teacher');
		});
	</script>	
</body>
</html>