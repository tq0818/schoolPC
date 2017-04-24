$(document).ready(function(){
	$("body").on("click",':text',function(){
		$(this).focus();
	})
	//$(".footer").addClass("footer-fixed");
	queryDataTitle();
	queryOneHeadTitleDatas();
	//默认logo为图片显示
	//updateLogoType("picture");
	$("#chooseLists").find("input[type=radio]").each(function(i){
		if($(this).is(":checked")){
			var name=$(this).attr("value");
			if(name=="logo"){
				updateLogoType("picture");
				$("#companyIcoUrl").addClass("img").removeClass("text");
				var url=$("#imgUrl").attr("src");
				$("#companyIcoUrl").css("background-image","url("+url+")");
				$("#companyIcoUrl").text('');
				$("#oneDiv").css("display","block");
				$("#twoDiv").css("display","none");
			}else{
				updateLogoType("word");
				$("#companyIcoUrl").addClass("text").removeClass("img");
				$("#companyIcoUrl").css("background-image",'');
				$("#oneDiv").css("display","none");
				$("#twoDiv").css("display","block");
				//alert($("#upName").val());
				$("#companyIcoUrl").text($("#upName").val());
			}	
		}
	});
	$(".nameCss").click(function(){
		$("#childMenu").css("display","block");
	});
	//$("#companyIcoUrl").text('');
	$("#chooseLists").on('click','input[type=radio]',function(){
		var sta=$(this).is(":checked");
		if(sta){
			var name=$(this).attr("value");
			if(name=="logo"){
				updateLogoType("picture");
				$("#companyIcoUrl").addClass("img").removeClass("text");
				var url=$("#imgUrl").attr("src");
				$("#companyIcoUrl").css("background-image","url("+url+")");
				$("#companyIcoUrl").text('');
				$("#oneDiv").css("display","block");
				$("#twoDiv").css("display","none");
			}else{
				updateLogoType("word");
				$("#companyIcoUrl").addClass("text").removeClass("img");
				$("#companyIcoUrl").text($("#upName").val());
				$("#companyIcoUrl").css("background-image",'');
				$("#oneDiv").css("display","none");
				$("#twoDiv").css("display","block");
			}			
		}
	})
	
	});
	//初始化弹出层中的学科
	$.ajax({
		url: rootPath+"/sysConfigItem/getJsons",
		type: "post",
		dataType: "json",
		data: {"itemType":1},
		success: function(jsonData){
			$(".itemOne").find("div").html('');

			$.each(jsonData,function(i,data){
				$(".itemOne").find("div").append('<a href="javascript:;" value="'+data.id+'" class="btn btn-mini btn-default">'+data.itemName+'</a>');
			});
		}
	})
	//绑定事件
	$(document).on("click.btn","blockquote p a.btn-ico",function(){
		var input=$(this).prev();
		$.ajax({
			url: rootPath+"/company/currtCompany",
			type:"post",
			dataType: "json",
			success: function(jsonData){
				var domain=jsonData.domain;
				$(".popupwin").popup({
					init: true,
					show : true,
					modal: false,
					success: function(ele){
						var itemOne=$(".popupwin").find(".itemOne").find(".btn-success").attr("value");
						var itemTwo=$(".popupwin").find(".itemTwo").find(".btn-success").attr("value");
						var classes=$(".popupwin").find(".classes").find(".btn-success").attr("value");

						var url="";
						if(itemOne && itemTwo && !classes){
							url+=domain+"/sysConfigItem/"+itemOne+"/"+itemTwo;
						}else if(itemOne && !itemTwo){
							url+=domain+"/sysConfigItem/"+itemOne;
						}else if(classes){
							url+=domain+"/sysConfigItem/selectDetail/"+classes;
						}
						$(input).val(url);
					}
			})
			}
		})
		
	//学科
	$(".itemOne").on("click","a.btn",function(){
		$(this).siblings().removeClass("btn-success").addClass("btn-default");
		$(this).addClass("btn-success").removeClass("btn-default");
		queryItemSecond();
		 queryClassType();
	})
	queryItemSecond();
	//学科小类
	$(".itemTwo").on('click','a.btn',function(){
		$(this).siblings().removeClass("btn-success").addClass("btn-default");
		$(this).addClass("btn-success").removeClass("btn-default");
		  queryClassType();
	})
	//班型
	$(".classes").on('click','a.btn',function(){
		$(this).siblings().removeClass("btn-success").addClass("btn-default");
		$(this).addClass("btn-success").removeClass("btn-default");
	})

});
	
	function queryItemSecond(){
		 var itemOneId=0;
		$(".itemOne").find("a.btn").each(function(){
			var st=$(this).hasClass("btn-success");
			if(st){
				itemOneId=$(this).attr("value");
			}
		});
		$.ajax({
			url: rootPath+"/sysConfigItem/getJsons",
			type: "post",
			dataType: "json",
			data: {"itemType":2,"parentId":itemOneId},
			success: function(jsonData){
				$(".itemTwo").find("div").html('');
				$.each(jsonData,function(i,data){
					$(".itemTwo").find("div").append('<a href="javascript:;" value="'+data.id+'" class="btn btn-mini btn-default">'+data.itemName+'</a>');
				});
			}
		})
	}
	 function queryClassType(){
		var itemOneId=0,itemSecondId=0;
		itemOneId=$(".itemOne").find(".btn-success").attr("value");
		itemSecondId=$(".itemTwo").find(".btn-success").attr("value");
		console.log(itemOneId,itemSecondId);
		$.ajax({
			url: rootPath+"/classType/queryList",
			type: "post",
			dataType: "json",
			data: {"itemOneId":itemOneId,"itemSecondId":itemSecondId},
			success: function(jsonData){
				$(".classes").find("div").html('');
				$.each(jsonData,function(i,data){
					$(".classes").find("div").append('<a href="javascript:;" value="'+data.commodityId+'" class="btn btn-mini btn-default">'+data.name+'</a>');
				});
			}
		})
	}

//修改logo显示类型
function updateLogoType(type){
	 $.ajax({ 
		  type: "post", 
		  url: rootPath+"/company/updateLogoType", 
		  data:{"companyLogoType":type},
		  success: function(result){
			
		  }
	  });
}

function changen(){
	$("#spanName").css("display","none");
	$("#upName").css("display","block");
	$("#btnupdate").css("display","none");
	$("#btnsave").css("display","block");
}

//异步加载一级可用标题
function queryOneHeadTitleDatas(){
	$("#titleList").html('');
	 $.ajax({ 
		  type: "post", 
		  url: rootPath+"/sysPageHeadFoot/queryOneHeadTitle", 
		  success: function(result){
			    var html="";
				$.each(result,function(i,item){
	 				if(i<=5){
	 					 html+="<li><a href='javascript:;' class='out"+item.id+"'>"+item.name+"</a></li>";
	 				}else{
	 					if(i==6){
	 						 html+="<li><a href='javascript:;' style='font-size:25px;'>...</a></li>";
	 					}
	 				}
	 			});
	 			$("#titleList").append(html);
		  }
	  });
}

//异步加载数据
function queryDataTitle(){
	 $.ajax({ 
		  type: "post", 
		  url: rootPath+"/sysPageHeadFoot/showTitles", 
		  success: function(result){
			  $("#navbarconfigs").html(result);
			  $(".newtitle").css("display","none");
		  }
	  });
}

//打开添加链接
function addHeadContent(){
	 $(".newtitle").css("display","block");
	 $.scrollTo(".newtitle",500);
}

//修改标题名称
function changName(type,id){
	var name=null;
	var obj=null;
	if(type=='SYS_PAGE_HEAD_HOME'||type=='SYS_PAGE_HEAD_COURSE'||type=='SYS_PAGE_HEAD_NEWS'||type=='SYS_PAGE_HEAD_TIKU'||type=='SYS_PAGE_HEAD_OPENCLASS'
		||type=='SYS_PAGE_HEAD_TEACHER'||type=='SYS_PAGE_HEAD_QUESTION'){
		obj=$("#in"+id);
		name=$("#in"+id).val();
		$("#in"+id).attr("disabled",false);
		$("#in"+id).css({"border":"1px","background-color":"white"});
	}else if(type=='SYS_PAGE_HEAD_OTHER'){
		$("#childMenu"+id).css("display","block");
		return;
	}

	 $.ajax({ 
  		  type: "post", 
  		  url: rootPath+"/sysPageHeadFoot/updateTitle", 
  		  data: {"name":name, 
  			  	 "id" : id
  			  },
  		  success: function(result){
  			$(".out"+result.id).text(result.name);
  		  }
  	  });	
}

//改变标题状态
//function changStatus(id,obj){
//	//根据id查询当前状态，如果当前状态为已启用，这不做限制，如果当前状态为已禁用，则需判断当前可用状态的标题数
//	var count;
//	var shu=0;
//	 $.ajax({ 
//		  async: false,
// 		  url: rootPath+"/sysPageHeadFoot/"+id, 
// 		  success: function(result){
// 			  count=result.validFlag;
// 		  }
// 	  });
//	 if(count==0){
//		 $.ajax({ 
//	 		  type: "post", 
//	 		  async: false,
//	 		  url: rootPath+"/sysPageHeadFoot/queryIsUseCount", 
//	 		  success: function(c){
//	 			  if(c>=6){
//	 				shu++;
//	 			  }
//	 		  }
//	 	  });
//	 }
//	 if(shu>0){
//		 alert("标题最多可显示6个");
//		 return;
//	 }
//	var $this=$(obj);
//	var status=0;
//	if(obj.value=='已启用'){
//		status=0;
//	}else{
//		status=1
//	}
//	 $.ajax({ 
// 		  type: "post", 
// 		  url: rootPath+"/sysPageHeadFoot/updateStatus", 
// 		  data: { "id" : id, "validFlag" : status },
// 		  success: function(result){
// 			  if(result.validFlag==1){
// 				$this.attr("value","已启用");
// 			  }else{
// 				 $this.attr("value","已禁用");
// 			  }
// 			 queryOneHeadTitleDatas();
// 		  }
// 	  });	
//}

//添加导航链接
function addHeadTitle(){
	var openType;
	var titleName=$("#titleName").val();
	var titleUrl=$("#titleUrl").val();
	$("#hfList").find("input[type=radio]").each(function(){
		if($(this).is(":checked")){
			openType=$(this).val();
		}
	});
	if(titleUrl!=""){
		titleUrl="http://"+titleUrl;
		str = titleUrl.match(/http:\/\/.+/);
		if(str==null){
			alert("输入链接无效");
			return;
		}
	}
	if(titleName.length>6 || titleName <2 || titleName == ""){
		alert("标题长度为2到6位字符或汉子并且不能为空！");
		return;
	}
	var sequence;
	$("#navbarconfigs").find("li").each(function(i){
		sequence=i+1;
	});
	$("#titleList").html('');
	 $.ajax({ 
 		  type: "post", 
 		  url: rootPath+"/sysPageHeadFoot/addHeadTitle", 
 		  data: {"name":titleName, 
 			  	 "url" : titleUrl,
 			  	 "openType" : openType,
 			  	 "sequence" : sequence,
 			  	 "pageHeadType":'SYS_PAGE_HEAD_OTHER'
 			  },
 		  success: function(result){
 			var html="";
 			$.each(result,function(i,item){
 				if(i<=5){
 					 html+="<li><a href='javascript:;' class='out"+item.id+"'>"+item.name+"</a></li>";
 				}else{
 					if(i==6){
 						 html+="<li><a href='javascript:;'  style='font-size:25px;'>...</a></li>";
 					}
 				}
 			});
 			$("#titleList").append(html);
 			queryDataTitle();
 		  }
 	  });
}

//修改公司logo
function changCompanyIco(){
	var filename=$("#imgData").val();
	if(filename != "" && filename != null){
		$.ajaxFileUpload({
			url : rootPath+"/sysPageHeadFoot/updateCompanyIco;"+ window["sessionName"] + "=" + window["sessionId"],
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'imgData',
			dataType:'json',
			type : "POST",
			success : function(data) {
				console.log(data.url);
				if(data.url!="null"){
					$("#companyIcoUrl").css("background-image","url("+data.url+")");	
					$("#imgUrl").attr("src",data.url);	
					alert("上传成功!");
				}else{
					alert("上传失败!");
				}
			}
		});
	}else{
		
	}
}

//修改名称
function changCompanyName(){
	var companyName=$("#upName").val();
	if(companyName.length<=0){
		$.alert("公司名称不能为空!");
		return;
	}
	 $.ajax({ 
 		  type: "post", 
 		  url: rootPath+"/company/updateByNameShort", 
 		  data: {"companyNameShort":companyName},
 		  success: function(result){
 			  $("#spanName").text(result.companyNameShort);
 			  $("#upName").attr("value",result.companyNameShort);
 			  $("#btnupdate").css("display","block");
     		  $("#btnsave").css("display","none");
     		  $("#spanName").css("display","block");
     		  $("#upName").css("display","none");
     		  alert("保存成功");
     		  $("#companyIcoUrl").text(result.companyNameShort);
 		  }
 	  });
}

//显示添加二级标题
function showAdd(id){
	$("#chileTitleAdd"+id).css("display","block");
}

//取消添加
function Noshow(){
	 $(".newtitle").css("display","none");
}

function noShowAdd(num,id){
	if(num=='one'){
		$("#childMenu"+id).css("display","none");
	}
	if(num=='two'){
		$("#chileTitleAdd"+id).css("display","none");
	}
	if(num=='three'){
		$("#childMenuList"+id).css("display","none");
	}
}

//修改二级标题
function updateHeadTitle(types,id){
	var type,addTypes;
	var name=$("#tTitle"+id).val();
	var url=$("#tUrl"+id).val();
	$("#type"+id).find("input[type=radio]").each(function(){
		if($(this).is(":checked")){
			type=$(this).val();
		}
	});
//	var type=$("#type"+id).val();
	var addTitle=$("#addTitle"+id).val();
	var addUrl=$("#addUrl"+id).val();
	$("#addTypes"+id).find("input[type=radio]").each(function(){
		if($(this).is(":checked")){
			addTypes=$(this).val();
		}
	});
	if('update'==types){
		if(name==""){
			alert("标题不能为空");
			return;
		}
		if(name.length>6 || name.length <2){
			alert("标题为2到6位的字符或汉字");
			return;
		}
		if(url!=""){
			url="http://"+url;
			str = url.match(/http:\/\/.+/);
			if(str==null){
				alert("输入链接无效");
				return;
			}
		}else{
			url="";
			//type="self";
		}
		$.ajax({ 
	 		  type: "post", 
	 		  url: rootPath+"/sysPageHeadFoot/updateTitle",  
	 		  data: {"id" : id, "name" : name, "url" : url, "openType" : type,"parentId":null},
	 		  success: function(result){
	 			$(".out"+result.id).text(result.name);
	 			queryDataTitle();
	 		  }
	 	  });	
	}else{
		if(addTitle==""){
			alert("标题不能为空");
			return;
		}
		if(addUrl!=""){
			addUrl="http://"+addUrl;
			str = addUrl.match(/http:\/\/.+/);
			if(str==null){
				alert("输入链接无效");
				return;
			}
		}
		if(addTitle.length>6 || addTitle.length <2){
			alert("标题为2到6位的字符或汉字");
			return;
		}
		$.ajax({ 
	 		  type: "post", 
	 		  url: rootPath+"/sysPageHeadFoot/addHeadTitle", 
	 		  data: { "name" : addTitle, "url" : addUrl, "openType" : addTypes, "parentId" : id },
	 		  success: function(result){
	 			queryDataTitle();
	 		  }
	 	  });
	}
}

//显示二级标题列表
function changChild(id){
	$("#childMenuList"+id).css("display","block");
}

//删除二级标题
function deleteChild(id){
	$.confirm("您确定要删除吗?",function(b){
		if(b==true){
			$.ajax({ 
				  type: "post", 
				  url: rootPath+"/sysPageHeadFoot/deleteChildHeadTitle", 
				  data: { "id" : id},
				  success: function(result){
					queryDataTitle();
				  }
			  });
		}else{
			return;
		}
	});

}