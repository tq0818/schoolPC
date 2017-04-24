<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>报考材料</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	 $selectMenu("course_class_type");
    	var mobile=$("#mobile").val();
		var id=$("#pid").val();
		var stuId=$("#stuId").val();
		var schoolId=$("#schoolId").val();
		var classTypeId=$("#classTypeId").val();
		$.ajax({
			url:"<%=rootPath%>/studentAgentMaterial/classTypeMessage",
			type:"post",
			data:{
				"mobile":mobile,
				"id":id,
				"classTypeId":classTypeId,
				"stuId":stuId,
				"schoolId":schoolId
			},
			success:function(data){
				$("#classTypeMessage").html(data);
			}
		})
    });
    
//     function toSearch(){
// 		$(".tips").fadeOut();
// 		var stuId=$("#stuId").val();
// 		var mobile=$("#phone").val();
// 		$.ajax({
<%-- 			url:"<%=rootPath%>/student/search", --%>
// 			type:"post",
// 			data:{
// 				"mobile":mobile,
// 			},
// 			success:function(data){
// 				if(data!=null&&data!=""&&data!=-1){
// 					$("#baseMessage").html('');
// 					$("#classTypeMessage").html('');
// 					$("#matersList").html('');
// 					$.ajax({
<%-- 		    			url:"<%=rootPath%>/studentAgentMaterial/baseMessage", --%>
// 		    			type:"post",
// 		    			data:{
// 		    				"mobile":mobile
// 		    			},
// 		    			success:function(data){
// 		    				$("#baseMessage").html(data);
// 		    			}
// 		    		})
// 		    		$.ajax({
<%-- 		    					url:"<%=rootPath%>/studentAgentMaterial/classTypeMessage", --%>
// 		    					type:"post",
// 		    					data:{
// 		    						"mobile":mobile,
// 		    					},
// 		    					success:function(data){
// 		    						$("#classTypeMessage").html(data);
// 		    					}
// 		    		})
// 		    		$.ajax({
<%-- 		    			url:"<%=rootPath%>/studentAgentMaterial/stuMaterialLists", --%>
//     					type:"post",
//     					data:{
//     						"mobile":mobile,
//     						"paymaterId":data,
//     					},
//     					success:function(data){
//     						$("#matersList").html(data);
//     					}		
// 		    		})
// 				}else{
// 					$(".tips").fadeIn();
// 				}
// 			}
// 		})
		
// 	}
    </script>
    
</head>
<body>
<input type="hidden" id="mobile" value="${mobile }"/>
<input type="hidden" id="studentId" value="${student.id }"/>
<input type="hidden" id="classTypeId" value="${payMaster.commodityId }"/>
<input type="hidden" id="paymaterId" value="${payMaster.id }"/>
<input type="hidden" id="examTermName" name="examTermName" value="${payMaster.examTermName }"/>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">报考材料</h2>
           <!--  
           <div class="search">
           		 <i class="tips">没有查到相关信息!</i>
                <input type="text" id="phone" name="mobile" class="input-ctrl">
                <input type="button" class="btn btn-sm" onclick="toSearch();" value="搜索">
            </div>-->
            <span class="line"></span>
        </div>
        <div class="mark-more" id="baseMessage">
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">基本信息</h2>
                    <span class="more">
                        <a href="javascript:;" class="m">更多</a>
                    </span>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">姓名</span>
                            <span class="c-content">${student.name }</span>
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
                            <span class="c-content"><fmt:formatDate
							value="${student.birthday }" timeStyle="yyyy-MM-dd" /></span>
                        </p>
                    </li>
                    <li id="more-tel">
                        <p class='c'>
                            <span class="c-title">手机号</span>
                            <span class="c-content"><em>${student.mobile }</em></span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">年龄</span>
                            <span class="c-content">${student.age }</span>
                        </p>
                    </li>
                    <li class="none">
                        <p class='c'>
                            <span class="c-title">户口所在地</span>
                            <span class="c-content">${student.hkAddress }</span>
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
                            <span class="c-content">${student.identityId }</span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="main-content none">
                <div class="m-title">
                    <h2 class="h6">联系信息</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">手机号</span>
                            <span class="c-content"><em>${student.mobile }</em></span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">家庭电话</span>
                            <span class="c-content">${student.homePhone }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">办公电话</span>
                            <span class="c-content">${student.officePhone==null?'无':student.officePhone }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急电话</span>
                            <span class="c-content">${student.emergencyPhone }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急人电话</span>
                            <span class="c-content">${student.emergencyContact }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">邮箱</span>
                            <span class="c-content">${student.email }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">QQ号</span>
                            <span class="c-content">${student.qq }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">微信</span>
                            <span class="c-content">${student.weixinId }</span>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        <div class="mark-more" id="classTypeMessage">
            
        </div>
   <div id="matersList">
       <div class="mark-more">
          <div class="main-content">
            <div class="m-title">
                <h2 class="h6">代报考</h2>
            </div>
            <div class="m-diy space">
                <p class="m-tools">
                   <c:if test="${payMaster.isAgent==1 }">
                  		 <span>是否代报考<input class="iconfont" disabled="disabled" type="checkbox" checked="checked" name="dbk" id="dbk"/></span>
                  </c:if>
                   <c:if test="${payMaster.isAgent!=1 }">
                         <span>是否代报考<input class="iconfont" type="checkbox" name="dbk" id="dbk"/></span>
                  </c:if>
                  <c:if test="${payMaster.isAgentMaterialComplete==1 }">
                  	     <span>资料是否齐全 <input type="checkbox" checked="checked" name="zlqq" id="zlqq"/></span>
                  </c:if>
                   <c:if test="${payMaster.isAgentMaterialComplete!=1 }">
                          <span>资料是否齐全 <input type="checkbox" name="zlqq" id="zlqq"/></span>
                  </c:if>
                    <!-- <i class="iconfont">&#xe609;</i> -->
                </p>
                <div class="m-content">
                    <div class="m-c-top clear">
                        <p class="m-c-title">
                          	  资料交接
                        </p>
                        <div class="m-c-c">
                            <p class="m-c-c-t" id="material">
                                <span class="m-c-c-tt">已交资料</span>
                                <span class="m-c-c-c">
                                <c:forEach items="${stuMaterialList }" var="materialList">
                                	 <em class="icon-btn" style="cursor: pointer;">${materialList.materialName }<i ids="${materialList.id }" txt="${materialList.materialName }">${materialList.materialNumYet }</i></em>
                                </c:forEach>
                               <c:if test="${empty stuMaterialList}">
                               		<em class="icon-btn" style="cursor: pointer;">一寸<i txt="一寸">0</i></em> 
                                    <em class="icon-btn" style="cursor: pointer;">二寸<i txt="二寸">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">身份证<i txt="身份证">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">学历证<i txt="学历证">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">学位证<i txt="学位证">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">证明材料<i txt="证明材料">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">其他<i txt="其他">0</i></em>
                               </c:if>
                                </span>
                            </p>
<!--                             <p class="m-c-c-t" id="unmaterial"> -->
<!--                                 <span class="m-c-c-tt">未交资料</span> -->
<!--                                 <span class="m-c-c-c"> -->
                        
<%--                                 <c:forEach items="${stuMaterialList }" var="materialList"> --%>
<%--                                 	 <em class="icon-btn" style="cursor: pointer;">${materialList.materialName }<i ids="${materialList.id }" txt="${materialList.materialName }">${materialList.materialNumNot }</i></em> --%>
<%--                                 </c:forEach> --%>
<%--                                  <c:if test="${empty stuMaterialList}"> --%>
<!--                                		<em class="icon-btn" style="cursor: pointer;">一寸<i txt="一寸">0</i></em>  -->
<!--                                     <em class="icon-btn" style="cursor: pointer;">二寸<i txt="二寸">0</i></em> -->
<!--                                     <em class="icon-btn" style="cursor: pointer;">身份证<i txt="身份证">0</i></em> -->
<!--                                     <em class="icon-btn" style="cursor: pointer;">学历证<i txt="学历证">0</i></em> -->
<!--                                     <em class="icon-btn" style="cursor: pointer;">学位证<i txt="学位证">0</i></em> -->
<!--                                     <em class="icon-btn" style="cursor: pointer;">证明材料<i txt="证明材料">0</i></em> -->
<!--                                     <em class="icon-btn" style="cursor: pointer;">其他<i txt="其他">0</i></em> -->
<%--                                 </c:if> --%>
<!--                                 </span> -->
<!--                             </p> -->
                        </div>
                    </div>
                    <div class="m-c-bottom clear">
                        <p class="m-c-title">
                         	   考务备注
                        </p>
                        <p class="m-c-c">
                            <textarea id="matrailMark" name="" id="" placeholder="输入你的备注信息">${payMaster.agentRemark }</textarea>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${payMaster.isAgent!=1 }">
         <div class="mark-more">
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">应交费用</h2>
                </div>
                 <ul class="list-infos clear">
                 	<li style="width: 80%;">
	                 <span class="c-title">代报考费用</span>
	                 <input type="text" id="materialFee" value="${payMaster.examAgentFee }"/>
	                 <span class="c-title">缴费方式<input type="radio" checked="checked" name="payFeeMethod" value="1"/>现金<input type="radio" name="payFeeMethod" value="0"/>POS<input type="radio" name="payFeeMethod" value="2"/>转账</span>
	                </li>
	             </ul>
             </div>
        </div>
       </c:if>
        <div>
        	<ul>
        		<li>
        			  <div class="m-content">
			            <p class="c text-center">
			                <span class="c-title">&nbsp;</span>
			                <span class="c-content">
			                    <a href="javascript:saveStuMaterial();" class="btn btn-sm btn-primary">提交</a>
			               
			                </span>
			            </p>
			        </div>
        		</li>
        	</ul>
        </div>
        
    </div>
</div>
</div>
</div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentMaterial.js"></script>
</body>
 
</html>