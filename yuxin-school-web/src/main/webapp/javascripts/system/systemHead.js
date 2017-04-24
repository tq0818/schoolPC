 var addnew = '<div class="section">'
        + '<div class="block l-menu">'
        + '<div class="title-wrap save">'
        + '<ul class="row clear">'
        + '<li class="with20"><i class="iconfont nav-name-icon"> &#xe630;</i><span class="title-info">自定义标题</span></li>'
        + '<li class="with50 link-address"></li>'
        + '<li class="with10"></li>'
        + '<li class="with20">'

        + '</li>'
        + '</ul>'
        + ' <div class="mask"></div>'
        + '<div class="field">'
        + '<div class="content">'
        + '<p><span class="name">显示名称：</span><input type="text" value="" id="addOneTitleName" class="title-content"></p>'
        + '<p><span class="name">链接地址：</span><!-- <em>http://</em> -->'
        + '<span>'
        + '<input type="text" class="address" id="titleUrl" value="http://" placeholder=""><a href="javascript:;" class="bt btn-ico">...</a>'
        + '</span>   示例:http://yunduoketang.com/course'
        + '</p>'
        + '<p id="hfList"><span class="name">链接类型：</span><input type="radio" name="openMethod" value="self">本页打开<input type="radio" name="openMethod" checked="checked" value="blank">弹出新窗口</p>'
        + '<p><input type="button" onclick="addHeadTitle(this)"  value="保存" class="btn btn-primary"><input type="button" onclick="noUseing(this);"  value="取消" class="btn btn-primary"></p>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<div class="block s-menu"></div>'
        + '</div>';
$(document).ready(function(){
	$selectMenu('org_service');
	$("body").on("click",':text',function(){
		$(this).focus();
	})
	queryDataTitle();
	queryOneHeadTitleDatas();
	//默认logo为图片显示
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
	
	 //点击新增
    $(".right-side").on("click", ".new-add-btn", function () {
        var click = $(this).attr("check");
        if (click == "true") {
            return false
        } else {
            $(this).attr("check", "true");
            $(this).before(addnew);
            $(this).prev(".section").find(".field").slideDown()
        }
        $(".title-wrap").each(
            function (index) {
                $(this).find(":radio").attr("name", "openMethod" + index);
            }
        )
    });
	
	 //点击编辑按钮
    $(".right-side").on("click", ".edit-icon", function () {
    	var parent = $(this).parents('.title-wrap');
    	var name = parent.data('name');
    	parent.find(".field").slideDown();
        if(name){
        	parent.find('.title-info').html(name);
        	parent.find('.titleName').val(name);
        }
        
    })

    //点击编辑中 保存取消按钮
    $(".right-side").on("click.c",".title-wrap .btn-primary", function () {
        var value=$(this).val();
        if(value=="取消"){
             $(".mar-lr5").attr("check", "false");
            $(this).parents(".title-wrap").find(".field").slideUp();
            var parent = $(this).parents('.title-wrap');
            var name = parent.data('name');
            if(name){
            	parent.find('.title-info').html(name);
            	parent.find('.titleName').val(name);
            	
            }
            	
        }
    });
    
    //点击上拉下拉菜单
    $(document).on('click.slide',".right-side .slide",function(){
    	 var $click = $(this).attr("chose");
         if ($click == "true") {
             $(".mar-lr5").attr("check", "false");
             $(this).parents(".section").find(".field").slideUp();
             $(this).parents(".l-menu").next(".s-menu").slideUp();
             $(this).html("&#xe67f;");
             $(this).attr("chose", "false");
             $(this).parents(".section").find(".save").remove();
         }else {
             $(this).parents(".l-menu").next(".s-menu").slideDown();
             $(this).html("&#xe681;");
             $(this).attr("chose", "true");
         }
    })//删除一级标题
    .on('click.deleteparents_icon','li i.deleteparents_icon',function(){
    	var id=$(this).attr("ids");
    	var len=$(this).parents("div.section").find("div.s-menu .title-wrap").length;
    	if(len>0){
    		$.msg("请先删除该导航栏下的二级导航栏");
    		return false;
    	}else{
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
    				return false;
    			}
    		});
    		return false;
    	}
    })//修改标题
    .on('click.save_headtitle','.right-side .save_headtitle',function(){
    	var $this=$(this);
    	var mark=$this.attr("mark");
    	var id=$this.attr("ids");
    	if("no"==mark){
    		var name=$this.parent().prev().find("input.titleName").val();
        	if(name.length>6 || name.length <2 || name == ""){
        		$.msg("标题长度为2到6位字符或汉字并且不能为空！");
        		return;
        	}
    		 $.ajax({ 
    	  		  type: "post", 
    	  		  url: rootPath+"/sysPageHeadFoot/updateTitle", 
    	  		  data: {"name":name,"id" : id},
    	  		  success: function(result){
    	  			  $(".in"+result.id).text(result.name);
    	  			  $(".out"+result.id).text(result.name);
    	  			  $this.parents('.title-wrap').data('name',result.name);
    	  			  $.msg("操作成功");
    	  			 $(".mar-lr5").attr("check", "false");
    	             $this.parents(".title-wrap").removeClass("save");
    	             $this.parents(".title-wrap").find(".field").slideUp();
    	  		  }
    	  	  });	
    	}else if("other"==mark){
    		var type;
    		var $p=$this.parents("div.father_content");
    		var name= $p.find("input.titleName").val();
    		var url= $p.find("input.weburl").val();
    		$p.find(".type_url").find("input[type=radio]").each(function(){
    			if($(this).is(":checked")){
    				type=$(this).val();
    			}
    		});
    		if(name.length>6 || name <2 || name == ""){
        		$.msg("标题长度为2到6位字符或汉字并且不能为空！");
        		return;
        	}
    		if(url!=""){
//    			if(url.indexOf("http://")!=-1){
//      				$.msg("输入链接无效");
//      				return;
//      			}
    			//url="/http:\/\/.+/;
    			var sp="^((https|http|ftp|rtsp|mms)?://)";
    			var str = url.match(sp);
    			if(str==null){
    				alert("输入链接无效");
    				return;
    			}
    		}else{
    			url="";
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
    	}
    })//显示添加二级标题
    .on("click.add-title", ".right-side .add-title", function () {
      var click = $(this).attr("check");
      if (click == "true") {
          return false;
      }else{
    	  $(this).next(".slide").attr("chose", "true").html("&#xe681;");
          $(this).attr("check","true");
          $(this).parents(".l-menu").next(".s-menu").show().append(addcontent);
          $(this).parents(".l-menu").next(".s-menu").find(".save .field").slideDown();
          
    	  var id=$(this).attr("ids");
          $(this).next(".slide").attr("chose", "true").html("&#xe681;");
          $(this).attr("check","true");
          var addcontent = '<div class="title-wrap save">'
              + ' <ul class="row clear">'
              + '<li class="with16"><span class="title-info">二级标题</span></li>'
              + '<li class="with55 link-address"></li>'
              + '<li class="with12"></li>'
              + '<li class="with17">'

              + '</li>'
              + '</ul>'
              + '<div class="mask"></div>'
              + '<div class="field">'
              + '<div class="content">'
              + '<p><span class="name">显示名称：</span><input type="text" id="addTitle'+id+'" value="" class="title-content"></p>'
              + '<p><span class="name">链接地址：</span><!-- <em>http://</em> -->'
              + '<span>'
              + '<input type="text" class="address" id="addUrl'+id+'" value="http://" placeholder=""><a href="javascript:;" class="bt btn-ico">...</a>'
              + '</span>   示例:http://yunduoketang.com/course'
              + '</p>'
              + '<p id="addTypes'+id+'"><span class="name">链接类型：</span><input type="radio" value="self" name="openMethods'+id+'">本页打开<input type="radio" value="blank" name="openMethods'+id+'" checked="checked">弹出新窗口</p>'
              + '<p><input type="button" value="保存" class="btn btn-primary save_childtitle" ids='+id+' mark="add"/><input type="button" onclick="noUseChild(this);"  value="取消" class="btn btn-primary"></p>'
              + '</div>'
              + '</div>'
              + '</div>';
          $(this).parents(".l-menu").next(".s-menu").show().append(addcontent);
          $(this).parents(".l-menu").next(".s-menu").find(".save .field").slideDown();
          $(".save_childtitle").on('click',function(){
    	    var id=$(this).attr("ids");
    	    var mark=$(this).attr("mark");
    	    if("add"==mark){
    	    	 var addTypes;
    	      		var addTitle=$("#addTitle"+id).val();
    	      		var addUrl=$("#addUrl"+id).val();
    	      		$("#addTypes"+id).find("input[type=radio]").each(function(){
    	      			if($(this).is(":checked")){
    	      				addTypes=$(this).val();
    	      			}
    	      		});
    	      		if(addTitle==""){
    	      			$.msg("标题不能为空");
    	      			return;
    	      		}
    	      		if(addUrl!=""){
//    	      			if(addUrl.indexOf("http://")!=-1){
//    	      				$.msg("输入链接无效");
//    	      				return;
//    	      			}
    	      			//addUrl="http://"+addUrl;
    	      			var sp="^((https|http|ftp|rtsp|mms)?://)";
    	      			var str = addUrl.match(sp);
    	      			if(str==null){
    	      				$.msg("输入链接无效");
    	      				return;
    	      			}
    	      		}
    	      		if(addTitle.length>6 || addTitle.length <2){
    	      			$.msg("标题为2到6位的字符或汉字");
    	      			return;
    	      		}
    	      		$.ajax({ 
    	      	 		  type: "post", 
    	      	 		  url: rootPath+"/sysPageHeadFoot/addHeadTitle", 
    	      	 		  data: { "name" : addTitle, "url" : addUrl, "openType" : addTypes, "parentId" : id,"validFlag" : 1 },
    	      	 		  success: function(result){
    	      	 			queryDataTitle();
    	      	 		  }
    	      	 	  });
    	    }
    	    return false;
          });
      }
  }); 
    var themes_color={
    	color:{"blue":"bluezhi","red":"redzhi"},
    	btn_click:function(){
    		
    	}
    		
    }
   $("#blue_btn").on("click",function(){
	   $.ajax({
		 type:"get",
		 url:rootPath+"/sysPageHeadFoot/updateThemes",
		 data:{"content":"bluezhi"},
		 success:function(result){
			 alert(result);
		 }
	   })
   })
     $("#red_btn").on("click",function(){
	   $.ajax({
		 type:"get",
		 url:rootPath+"/sysPageHeadFoot/updateThemes",
		 data:{"content":"red_btn"},
		 success:function(result){
			 alert(result);
		 }
	   })
   })

});

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
	 				if(i<=3){
	 					 html+="<li><a href='javascript:;' style='font-size:12px;margin:0px 2px;' class='out"+item.id+"'>"+item.name+"</a></li>";
	 				}else{
	 					if(i==4)
	 						html+="<li><a href='javascript:;' style='font-size:20px;margin:0px 2px;'>...</a></li>";
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
		  beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
	      },
		  success: function(result){
			  $("#navbarconfigs").html(result);
			  $(".newtitle").css("display","none");
		  },
		  complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
	     }
	  });
}

//打开添加链接
function addHeadContent(){
	 $(".newtitle").css("display","block");
	 $.scrollTo(".newtitle",500);
}

//添加导航链接
function addHeadTitle(e){
	var $this=$(e);
	if($this.hasClass("disabled")){
		return;
	}
	$this.addClass("disabled");
	var openType;
	var titleName=$("#addOneTitleName").val();
	var titleUrl=$("#titleUrl").val();
	$("#hfList").find("input[type=radio]").each(function(){
		if($(this).is(":checked")){
			openType=$(this).val();
		}
	});
	if(titleUrl!=""){
		//titleUrl="http://"+titleUrl;
		var sp="^((https|http|ftp|rtsp|mms)?://)";
		var str = titleUrl.match(sp);
		if(str==null){
			$.msg("输入链接无效");
			$this.removeClass("disabled");
			noUseing(e);
			return;
		}
	}
	if(titleName.length>6 || titleName <2 || titleName == ""){
		$.msg("标题长度为2到6位字符或汉字并且不能为空！");
		$this.removeClass("disabled");
		noUseing(e);
		return;
	}
	var sequence=2;
	$("#navbarconfigs").find("div.section").each(function(i){
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
 			  	 "pageHeadType":'SYS_PAGE_HEAD_OTHER',
 			  	 "validFlag":1
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
					$.msg("上传成功!");
				}else{
					$.msg("上传失败!");
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
		$.msg("公司名称不能为空!");
		return;
	}
	if(companyName.length<2){
		$.msg("公司名称长度范围为2-8个字符");
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
     		  $.msg("保存成功");
     		  $("#companyIcoUrl").text(result.companyNameShort);
 		  }
 	  });
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
	var addTitle=$("#addTitle"+id).val();
	var addUrl=$("#addUrl"+id).val();
	$("#addTypes"+id).find("input[type=radio]").each(function(){
		if($(this).is(":checked")){
			addTypes=$(this).val();
		}
	});
	if('update'==types){
		if(name==""){
			$.msg("标题不能为空");
			return;
		}
		if(name.length>6 || name.length <2){
			$.msg("标题为2到6位的字符或汉字");
			return;
		}
		if(url!=""){
//			if(url.indexOf("http://")!=-1){
//  				$.msg("输入链接无效");
//  				return;
//  			}
			//url="http://"+url;
			var sp="^((https|http|ftp|rtsp|mms)?://)";
			var str = url.match(sp);
			if(str==null){
				$.msg("输入链接无效");
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
			$.msg("标题不能为空");
			return;
		}
		if(addUrl!=""){
//			if(addUrl.indexOf("http://")!=-1){
//  				$.msg("输入链接无效");
//  				return;
//  			}
			//addUrl="http://"+addUrl;
			var sp="^((https|http|ftp|rtsp|mms)?://)";
			var str = addUrl.match(sp);
			if(str==null){
				$.msg("输入链接无效");
				return;
			}
		}
		if(addTitle.length>6 || addTitle.length <2){
			$.msg("标题为2到6位的字符或汉字");
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
//取消添加一级菜单
function noUseing(e){
	var $this=$(e);
	$(".right-side").find(".new-add-btn").attr("check","false");
	$this.parents("div.section").remove();
}

//取消添加二级级菜单
function noUseChild(e){
	var $this=$(e);
	$(".mar-lr5").attr("check", "false");
	$this.parents("div.title-wrap").remove();
}