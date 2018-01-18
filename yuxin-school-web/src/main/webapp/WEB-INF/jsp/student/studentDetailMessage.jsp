<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
	
    <title>学员详情</title>
    <%@include file="/decorators/import.jsp"%>
    
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<%=rootPath %>/images/favicon.ico" type="image/x-icon" /> 
	<!-- 日期控件样式 -->
	<!-- 日期控件样式结束 -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/miniJs/css/minitip.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/enjoyhint/jquery.enjoyhint.css" />
	<script type="text/javascript">var rootPath='<%=rootPath%>'</script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/constants.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/miniJs/js/miniTip.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/enjoyhint/enjoyhint.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/jquery.scrollTo-2.1.0/jquery.scrollTo.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	
    
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$selectMenu('student_head');
    		//加载报名信息
    		var stuId=$("#stuId").val();
    		var count = 0;
    		$(".loading").show();
			$(".loading-bg").show();
    		$.ajax({
    			url:"<%=request.getContextPath()%>/student/selectEntryMessage3",
    			type:"post",
    			data:{
    				"stuId":stuId
    			},
    			success:function(jsonData){
    				$.each(jsonData,function (i, spm) {
    					var itemOneNameNew = spm.itemOneName;
    					if(spm.itemOneName == null){
    						var itemOneNameNew = '无'
    					}
    					var itemSecondNameNew = spm.itemSecondName;
    					if(spm.itemSecondName == null){
    						var itemSecondNameNew = '无'
    					}
    				html = '<div class="m-b-p clear" > '+
			    		   '     <div class="m-b-p-left" style="height:115px;width: 100%;position:static;"> '+
			    	       '     <p class="h c"> '+
			    	       '         <span class="p-title">学科</span> '+
			    	       '         <span class="p-content">'+itemOneNameNew+'</span> '+
			    	       '     </p> '+
			    	       '     <p class="h c"> '+
			    	       '         <span class="p-title">学费</span> '+
			    	       '         <span class="p-content">'+$.formatMoney(spm.totalAmount)+'</span> '+
			    	       '     </p> '+
			    	       /* '     <p class="h c"> '+
			    	       '         <span class="p-title">学科小类</span> '+
			    	       '         <span class="p-content">'+itemSecondNameNew+'</span> '+
			    	       '     </p> '+  */
			    	       '     <p class="h c"> '+
			    	       '         <span class="p-title">已缴</span> '+
			    	       '         <span class="p-content">'+$.formatMoney(spm.hasPay)+'</span> '+
			    	       '     </p> '+
			    	       '     <p class="h c"> '+
			    	       '         <span class="p-title">课程</span> '+
			    	       '         <span class="p-content">'+spm.classTypeName+'</span> '+
			    	       '     </p> '+((spm.totalAmount-spm.hasPay + spm.usedFee>=0)?
			    		   '          <p class="h c important"> '+
			    		   '             <span class="p-title">欠缴</span> '+
			    		   '             <span class="p-content">'+$.formatMoney(spm.unPay)+'</span> '+
			    		   '         </p> ':"")+
			    	       '     <p class="h c"> '+
			    	       '         <span class="p-title">报名时间</span> '+
			    	       '         <span class="p-content">'+spm.applyTime+'</span> '+
			    	       '     </p> '+
			    	       '     <p class="h c"> '+
			    	       '         <span class="p-title">来源</span> '+
			    	       '         <span class="p-content">'+spm.applyChannelCode+'</span> '+
			    	       '     </p> '+
			    	       '       <p class="h c"> '+
			    	       '         <span class="p-title">状态</span> '+
			    	       '         <span class="p-content">'+spm.payStatusCode+'</span> '+
			    	       '     </p> '+
			    	       ' </div> '+
			    	   	   '</div> ';
    					$(".m-b-m .kc").append(html);
    				});
    			},
    			complete:function(XMLHttpRequest,textStatus){
    				count++;
    				if(count === 2){
	 					$(".loading").hide();
	 					$(".loading-bg").hide();
	 				}
			    }
    			
    		})
    		$.ajax({
    			url:"<%=request.getContextPath()%>/student/selectClassPackageEntryMessage",
    			type:"post",
    			data:{
    				"stuId":stuId
    			},
    			success:function(jsonData){
    			 $.each(jsonData,function (i, spm) {
    				html = '<div class="m-b-p clear" >'+
    			       ' 	<div class="m-b-p-left" style="height:139px;width: 100%;position:static;"> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">分类</span> '+
    			       ' 	         <span class="p-content">'+(spm.oneCategory?spm.oneCategory:"")+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">学费</span> '+
    			       ' 	        <span class="p-content">'+$.formatMoney(spm.trainingFee)+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">二级分类</span> '+
    			       ' 	        <span class="p-content">'+(spm.twoCategory?spm.twoCategory:"")+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">已缴</span> '+
    			       ' 	        <span class="p-content">'+$.formatMoney(spm.hasPay)+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">三级分类</span> '+
    			       ' 	        <span class="p-content">'+(spm.threeCategory?spm.threeCategory:"")+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">来源</span> '+
    			       ' 	        <span class="p-content">'+spm.applyChannelCode+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">课程包名称</span> '+
    			       ' 	        <span class="p-content">'+spm.classTypeName+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">状态</span> '+
    			       ' 	        <span class="p-content">'+(spm.payStatusCode?spm.payStatusCode:"")+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">阶段名称</span> '+
    			       ' 	        <span class="p-content">'+(spm.classPackageStageId?spm.classPackageStageTitle:"全部")+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	    </p> '+
    			       ' 	    <p class="h c"> '+
    			       ' 	        <span class="p-title">报名时间</span> '+
    			       ' 	        <span class="p-content">'+spm.applyTime+'</span> '+
    			       ' 	    </p> '+
    			       ' 	    </div> '+
    			       ' 	 </div> ';
    				$(".m-b-m .kcb").append(html);
    			 });
    				 
	 				
    			},
    			complete:function(XMLHttpRequest,textStatus){
    				count++;
    				if(count === 2){
	 					$(".loading").hide();
	 					$(".loading-bg").hide();
	 				}
			    }
    			
    		})
    		 getExpandField(".mark-more","r",function(){
 				getExpandData(".mark-more","student","stu_id",stuId);
 			});
    	})
    		 //查询扩展字段
	var getExpandField=function(ele,rw,func){
			$(ele).find(".otherInfo").remove();
			$.ajax({
				url: rootPath+"/longitudinalTableColDefine/getData",
				dataType:"json",
				success:function(jsonData){
					var valueble;
					if(jsonData){
						$(ele).find(".main-content:last").after('<div class="main-content otherInfo"><div class="m-title"><h2 class="h6">其他信息</h2></div><ul id="expandFields" class="list-infos clear"></ul></div>');
					}
					$.each(jsonData,function(i,data){
						if(data.allowModify){
						var dom='<li><p class="c">';
						if(rw=="r" && data.orgAllowRead ){
							dom+='<span class="c-title">'+(data.colComment?data.colComment:"")+'</span> <span class="c-content">'+
							'<span id="'+data.colName+'">'+(data.colValue?data.colValue:"")+'</span></span>';
						}else if(rw=="w" && data.orgAllowWrite){
							if(data.tldType=="text"){
								dom+='<span class="c-title">'+data.colComment+(data.colAllowNull==0?'<i class="iconfont ico"></i>':'')+'</span> <span class="c-content">'+
								'<input type="text" id="'+data.colName+'" name="'+data.colName+'" style="'+(data.styleCss?data.styleCss:'')+'" class="'+(data.styleClass?data.styleClass:'')+'" value="'+(data.defaultValue?data.defaultValue:'')+'" >'+
								'</span>'+
								'</p></li>';
							}else if(data.tldType=="select"){
								dom+='<span class="c-title">'+data.colComment+(data.colAllowNull==0?'<i class="iconfont ico"></i>':'')+'</span> <span class="c-content">'+
								'<select style="'+(data.styleCss?data.styleCss:'')+'" id="'+data.colName+'" name="'+data.colName+'" value="'+(data.defaultValue?data.defaultValue:'')+'">';
								$.each(data.itemsData,function(j,item){
									dom+='<option value="'+item[data.itemsValue]+'">'+item[data.itemsName]+'</option>';
								})
								dom+='</span>'+
								'</p></li>';
							}
						}
						}else{
							dom='<input type="hidden" id="'+data.colName+'" name='+data.colName+' value=""/>'
						}
						$(ele).find('#expandFields').append(dom);
					})
					if(func){func()}
				}
			})
		}
	//查询扩展数据
	var getExpandData=function(ele,table,colName,colValue){
		var data={};
		data.tableName=table;
		data.colName=colName;
		data.colValue=colValue;
		$.ajax({
			url: rootPath+"/longitudinalTableData/getData",
			data:data,
			dataType:"json",
			success:function(jsonData){
				$.each(jsonData,function(i,data){
					if($(ele).find("#"+data.colName).is('input') || $(ele).find("#"+data.colName).is('select')){
						$(ele).find("#"+data.colName).val(data.colValue);
					}else{
						$(ele).find("#"+data.colName).html(data.colValue);
					}
					
				})
			}
		})
	}
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
			    			url:"<%=request.getContextPath()%>/student/selectEntryMessage",
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
<!--<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include> -->
<input id="stuId" type="hidden" value="${student.id }"/>
<div class="u-wrap student">
    <div class="mainbackground">
        <!-- 
        <div class="heading">
            <h2 class="h5">异动</h2>
            <div class="search">
            	 <i class="tips">没有查到相关信息!</i>
                <input type="text" id="mb" class="input-ctrl">
                <input type="button" class="btn btn-sm" value="搜索" onclick="search();">
            </div>
            <span class="line"></span>
        </div> -->
        <div id="allMessage">
        <div class="mark-more">
            <div class="main-content" id="oldMessage">
                <div class="m-title">
                    <h2 class="h6">基本信息</h2>
                    <!-- 
                    <span class="more">
                        <a href="javascript:;" class="m">更多</a>
                        <a href="javascript:;" class="edit" onclick="edit();">编辑</a>
                    </span>
                    -->
                </div>
                <ul class="list-infos clear">
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
                    <li>
                        <p class='c'>
                            <span class="c-title">用户名</span>
                            <span class="c-content cc">${student.username}</span>
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
							<span class="c-title">个人身份</span>
							<span class="c-content">
								<c:choose>
									<c:when test="${student.teacherFlag==1}">
										教师
									</c:when>
									<c:otherwise>
										${student.eduIdentity==0?'学生':''}
										${student.eduIdentity==1?'普通用户':''}
									</c:otherwise>
								</c:choose>
							</span>
						</p>
					</li>
                    <li style="${student.eduIdentity!=0?'display:none;':''}">
                        <p class='c'>
                            <span class="c-title">所在区域/学校</span>
                            <span class="c-content">${student.eduArea} ${student.eduSchool}</span>
                        </p>
                    </li>
                    <li style="${student.eduIdentity!=0?'display:none;':''}">
                        <p class='c'>
                            <span class="c-title">所在班级</span>
                            <span class="c-content cc">${student.eduStep}${student.eduYear}级${student.eduClass}班</span>
                        </p>
                    </li>
                    <c:if test="${sgOpen==1 }">
	                   <%-- <c:if test="${!empty G1Name }"> --%> 
	                    <li>
	                        <p class='c'>
	                            <span class="c-title">一级分组</span>
	                            <span class="c-content cc">${G1Name}</span>
	                        </p>
	                    </li>
	                   <%-- </c:if> --%>
                   
	                   <%-- <c:if test="${!empty G2Name }"> --%> 
	                    <li>
	                        <p class='c'>
	                            <span class="c-title">二级分组</span>
	                            <span class="c-content cc">${G2Name}</span>
	                        </p>
	                    </li>
	                   <%-- </c:if> --%> 
	                 </c:if>
                </ul>
            </div>
            <div class="main-content " id="contractMsg">
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
                                <input type="text" name="birthday" value="<fmt:formatDate value='${student.birthday }' timeStyle='yyyy-MM-dd'/>">
                            </span>
                        </p>
                    </li>                    
                    <li>
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
                                <input type="text" name="mobile" value="${student.mobile }">
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
               <div class="kc"></div>
               <div class="kcb"></div>
            </div>
        </div>
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