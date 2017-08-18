<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!doctype html>
<html lang="zh-cn">
<head>
  <meta charset="UTF-8">
  <title>新增专题</title>
  <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/vip.css">
  <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
  <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/teacher.css"/>
  <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/special/special-news-manage.css">
  <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/javascripts/special/special.js"></script>
  <script type="text/javascript">
        var subjectId = "${special.subjectId}";//表示更新操作
        var teacherIds = "${special.teacherIds}";
        var commodityIds = "${special.commodityIds}";
        function findTeacher(){
        	var subjectId = $('#subjectSelect').val();
        	$.ajax({
				url: "<%=rootPath%>/commodity/getTeachersBySubject",
				data:{"itemOneId":subjectId},
				async:false,
				success: function(jsonData){
					$('#teachers').empty();
					$.each(jsonData.teacherList,function(i,data){
						var li = '<li data-id="'+data.id+'">'+data.name+'</li>';
						$('#teachers').append(li);
					});
				}
			});
        }
    
        function findCourse(teacherIds){
        	var subjectId = $('#subjectSelect').val();
        	$.ajax({
				url: "<%=rootPath%>/commodity/getCourseByTeacher",
				data:{"teacherIds":teacherIds},
				async:false,
				success: function(jsonData){
				    var url = jsonData.url;
					$.each(jsonData.commodityList,function(i,data){
		                    var li = '<li commondityId = "'+data.id+'"  teacherId ="'+data.teacherId+'">';
							li = li + '<div class="parcel-box">';
							li = li + '<div class="picture" style="background-image:url('+url+data.coverUrl+')" onclick="openClassDetail()"></div>';
						    li = li + ' <input type="checkbox" class="input-checkbox" name="course">';
							li = li + '<span class="people fr"><i class="icon iconfont">&#xe6e7;</i><em>1</em></span>';
	                        li = li + '<div>' ;
							li = li + '<div class="stageMove">';
							li = li + '<h5 class="fl title">'+data.name+'</h5>'
							if(data.realPrice > 0){
							   li = li +  '<span class="rmb free fr">'+data.realPrice+'</span>';
							}else{
							   li = li + '<span class="rmb free fr">免费</span>';
							}
	                        li = li + '</div>';
							li = li + '<div class="stage-school-teacher">';
                        if(data.schoolShortName != null){
                            li = li + '<span class="school-name fl">'+data.schoolShortName+'</span>';
                        }else{
                            li = li + '<span class="school-name fl"></span>';
                        }
							li = li + ' <span class="teacher-name fr">'+data.teacherName+'</span>';
	                        li = li + '</div>';
	                        li = li + '</div>';
	                        li = li + '</div>';
	                        li = li + '</li>';
                      
                           $('#courseList').append(li);
					});
				}
			});
        }
        
        function clearCourse(cancelItem){
        	var lis = $("#courseList").children("li");
        	lis.each(function(i,v){
        		var courseTeacherId = $(v).attr("teacherId");
        		cancelItem.each(function(j,w){
        			if(courseTeacherId == $(w).attr("data-id")){
        				$(v).remove();
        			}
        		});
               
            });
        }
        
        function formSubmit(){
        	var teacherlis = $(".choose-item").children("li");
        	var commoditylis = $("#courseList").children("li");
        	var teacherIds = "";
        	var commodityIds = "";
        	teacherlis.each(function(i,v){
        		var id = $(v).attr("data-id");
        		teacherIds = teacherIds + id +","
               
            });
        	if($.trim(teacherIds) !=""){
             	teacherIds = teacherIds.substring(0,teacherIds.length-1);
            }
        	commoditylis.each(function(i,v){
        		if($(v).find("input[type='checkbox']:checked").length > 0){
        			var id = $(v).attr("commondityId");
            		commodityIds = commodityIds + id +","
        		}
        		
               
            });
        	if($.trim(commodityIds) !=""){
        		commodityIds = commodityIds.substring(0,commodityIds.length-1);
            }
        	
        	if($('#title').val().length <=0){
        		$.msg("专题标题不能为空");
        		return;
        	}
        	if($('#label').val().length <=0){
        		$.msg("专题标签不能为空");
        		return;
        	}
        	if($('#descript').val().length <=0){
        		$.msg("专题介绍不能为空");
        		return;
        	}
        	if($('#detailTitle').val().length <=0){
        		$.msg("专题详情标题不能为空");
        		return;
        	}
        	if($('#detailText').val().length <=0){
        		$.msg("专题正文不能为空");
        		return;
        	}
        	if(commodityIds.length <=0){
        		$.msg("课程不能为空");
        		return;
        	}
        	if(teacherIds.length <=0){
        		$.msg("老师不能为空");
        		return;
        	}
        	$('#commodityIds').attr("value",commodityIds);
        	$('#teacherIds').attr("value",teacherIds);
            $('#specialForm').submit();
        }
        
        function test(){
        	var commoditylis = $("#courseList").children("li");
        	var commodityIds = "";
        	commoditylis.each(function(i,v){
        		if($(v).find("input[type='checkbox']:checked").length > 0){
        			var id = $(v).attr("commondityId");
            		commodityIds = commodityIds + id +","
        		}
               
            });
        	alert(commodityIds);
        }
        
        function PreviewImage(fileObj,imgPreviewId,divPreviewId){
            var allowExtention=".jpg,.bmp,.gif,.png";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
            var extention=fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();            
            var browserVersion= window.navigator.userAgent.toUpperCase();
            if(allowExtention.indexOf(extention)>-1){ 
                if(fileObj.files){//HTML5实现预览，兼容chrome、火狐7+等
                    if(window.FileReader){
                        var reader = new FileReader(); 
                        reader.onload = function(e){
                            document.getElementById(imgPreviewId).setAttribute("src",e.target.result);
                        }  
                        reader.readAsDataURL(fileObj.files[0]);
                    }else if(browserVersion.indexOf("SAFARI")>-1){
                        alert("不支持Safari6.0以下浏览器的图片预览!");
                    }
                }else if (browserVersion.indexOf("MSIE")>-1){
                    if(browserVersion.indexOf("MSIE 6")>-1){//ie6
                        document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);
                    }else{//ie[7-9]
                        fileObj.select();
                        if(browserVersion.indexOf("MSIE 9")>-1)
                            fileObj.blur();//不加上document.selection.createRange().text在ie9会拒绝访问
                        var newPreview =document.getElementById(divPreviewId+"New");
                        if(newPreview==null){
                            newPreview =document.createElement("div");
                            newPreview.setAttribute("id",divPreviewId+"New");
                            newPreview.style.width = document.getElementById(imgPreviewId).width+"px";
                            newPreview.style.height = document.getElementById(imgPreviewId).height+"px";
                            newPreview.style.border="solid 1px #d2e2e2";
                        }
                        newPreview.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";                            
                        var tempDivPreview=document.getElementById(divPreviewId);
                        tempDivPreview.parentNode.insertBefore(newPreview,tempDivPreview);
                        tempDivPreview.style.display="none";                    
                    }
                }else if(browserVersion.indexOf("FIREFOX")>-1){//firefox
                    var firefoxVersion= parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
                    if(firefoxVersion<7){//firefox7以下版本
                        document.getElementById(imgPreviewId).setAttribute("src",fileObj.files[0].getAsDataURL());
                    }else{//firefox7.0+                    
                        document.getElementById(imgPreviewId).setAttribute("src",window.URL.createObjectURL(fileObj.files[0]));
                    }
                }else{
                    document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);
                }         
            }else{
                alert("仅支持"+allowExtention+"为后缀名的文件!");
                fileObj.value="";//清空选中文件
                if(browserVersion.indexOf("MSIE")>-1){                        
                    fileObj.select();
                    document.selection.clear();
                }                
                fileObj.outerHTML=fileObj.outerHTML;
            }
        }
        
        
        $(document).ready(function(){
            $selectSubMenu('special_topic');
               findTeacher();
        	   if(teacherIds.length > 0){
        		   findCourse(teacherIds);
        		   var lis = $("#teachers").children("li");
        		   var teacherIdArray = teacherIds.split(",");
        		   lis.each(function(i,v){
               		var teacherId = $(v).attr("data-id");
               	        for(i = 0; i < teacherIdArray.length;i++){
               	        	if(teacherIdArray[i] == teacherId ){
               	        		$(".choose-item").append($(v));
               	        	}
               	        }
                      
                   });
        	   }
        	  if(commodityIds.length > 0){
        		  var commodityIdArray = commodityIds.split(",");
        		  var commoditylis = $("#courseList").children("li");
              	  commoditylis.each(function(i,v){
              		for(i = 0; i < commodityIdArray.length;i++){
              			console.log($(v).attr("commondityId"))
              			if($(v).attr("commondityId")==commodityIdArray[i]){
              				$(v).find("input[type='checkbox']:first").attr("checked","true");
              			}
              		}  
              		                     
                  });
        	  } 
        });
        
       
        
  </script>
  
</head>
<body>
 <jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="Y_wrap">
 <div class="Y_background">
     <div class="Y_head Y_clear">
         <h2 class="h5 fl">新增专题</h2>
         <span class="line"></span>
     </div>
       <form id="specialForm" action="<%=rootPath%>/commodity/addOrUpdateSpecial" method="post" enctype="multipart/form-data">

            <div class="form-conten">
                <div class="form-tit">专题卡片</div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>专题标题</label>
                    <input type="text" class="input-text fl" id="title" name="title" maxlength="8" value="${special.title }">

                </div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>专题标签</label>
                    <input type="text" class="input-text fl" id="label" name="label" maxlength="20" value="${special.label }">
                </div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>专题封面</label>
                    <div id="divPreviewCoverPic" class="img-box fl">
                        <img id="imgCoverPic" src="${baseUrl}${special.coverPicUrl}" alt="" class="upload-img">
                        <input onchange="PreviewImage(this,'imgCoverPic','divPreviewCoverPic')" type="file" class="file-btn" id="uploadImg" name="coverPic">
                        <label class="btn btn-default btn-upload" for="uploadImg">点击上传</label>
                    </div>
                </div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>专题介绍</label>
                    <textarea class="class-textarea fl" id="descript" name="descript" maxlength="100">${special.descript}</textarea>
                </div>
            </div>

            <div class="form-conten">
                <div class="form-tit">专题详情</div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>标题</label>
                    <input type="text" class="input-text fl" id="detailTitle" name="detailTitle" maxlength="30" value="${special.detailTitle }">
                </div>
                 <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>专题封面</label>
                    <div id="divPreviewDetailCoverPic" class="img-box fl">
                        <img id="imgDetailCoverPic" src="${baseUrl}${special.detailCoverPicUrl}" alt="" class="upload-img">
                        <input onchange="PreviewImage(this,'imgDetailCoverPic','divPreviewDetailCoverPic')"  type="file" class="file-btn" id="uploadDetailImg" name="detailCoverPic">
                        <label class="btn btn-default btn-upload" for="uploadDetailImg">点击上传</label>
                    </div>
                </div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>正文</label>
                    <textarea class="class-textarea fl" id="detailText" name="detailText" maxlength="500">${special.detailText }</textarea>
                </div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>学科</label>
                    <select id="subjectSelect" name="subjectSelect" class="select-box" onchange="findTeacher()">
                        <c:forEach items="${subjectList}" var="subject">
                        <option   <c:if test="${special.subjectId == subject.id}"> selected="selected"</c:if>  value="${subject.id}">${subject.itemName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>老师</label>
                    <div class="choose-teacher fl">
                        <ul class="teacher-list fl" id="teachers">


                        </ul>
                        <div class="item-btn fl">
                            <i class="btn-next">》</i>
                            <i class="btn-prev">《</i>
                        </div>
                        <ul class="choose-item fl">
                        </ul>
                    </div>
                    <input type="hidden" id="teacherIds" name="teacherIds">
                </div>
                <div class="row">
                    <label for="" class="label-text fl"><em class="class-requied">*</em>课程</label>
                    <div class="newindex-list">
                        <ul id="courseList" class="clear">


                        </ul>
                    </div>
                     <input type="hidden" id="commodityIds" name="commodityIds">
                     <input type="hidden" id="specialId" name="specialId" value="${special.id}">
                </div>
            </div>
       </form>
       <div class="text-center">
                <a href ="javascript:void(0)" class="btn btn-primary " onclick="formSubmit()">保存</a>
                <a href ="javascript:void(0)" class="btn btn-default" onclick="test()">取消</a>
             </div>

        </div>
   
    </div>

</body>
</html>