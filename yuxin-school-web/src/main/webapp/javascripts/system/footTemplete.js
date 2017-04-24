var addnew = '<div class="section">'
        + '<div class="block l-menu">'
        + '<div class="title-wrap save">'
        + '<ul class="row clear">'
        + '<li class="with20"><i class="iconfont nav-name-icon"> &#xe630;</i><span class="title-info">新增底部链接</span></li>'
        + '<li class="with50 link-address"></li>'
        + '<li class="with10"></li>'
        + '<li class="with20">'
        + '</li>'
        + '</ul>'
        + ' <div class="mask"></div>'
        + '<div class="field">'
        + '<div class="content">'
        + '<p><span class="name">链接名称：</span><input type="text" maxlength="6" id="addOneFootName" class="title-content"></p>'
        + '<p id="footUrlType"><span class="name">&nbsp;&nbsp;&nbsp;&nbsp;方式：</span><input type="radio" name="linkMethod" checked="checked" value="LINK_CUSTOM" sort="1">自定义链接<input type="radio" name="linkMethod" value="LINK_DEFAULT" sort="2">默认链接-编辑文本<input type="radio" name="linkMethod" value="LINK_NONE" sort="3">无连接</p>'
        + '<p class="radio-tab1 radio-tab"><span class="name">链接地址：</span><!-- <em>http://</em> -->'
        + '<span>'
        + '<input type="text" id="footUrl" class="address" value="http://" placeholder=""><a href="javascript:;" class="btn-ico">...</a>'
        + '</span>   示例:http://yunduoketang.com/course'
        + '</p>'
        + '<p class="radio-tab2 radio-tab"><span class="name"></span>'+
          '<textarea class="ckeditor form-control" id="footWriteContents" rows="6"></textarea>'+
          '</p>'
        + '<p class="link-type" id="urlOpenType"><span class="name">链接类型：</span><input type="radio" name="openMethod">本页打开<input type="radio" name="openMethod" checked="checked">弹出新窗口</p>'
        + '<p><input type="button" onclick="addFootTitle(this);"  value="保存" class="btn btn-primary"><input type="button"  value="取消" class="btn btn-primary"></p>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<div class="block s-menu"></div>'
        + '</div>';

$(document).ready(function(){
	//加载页尾数据
	loadData();
	isSelf();
	query_footShowContent();
	 //点击新增
    $(".right-side").on("click", ".new-add-btn", function () {
    	var mark=$("#showPageType").val();
    	var len=$("#navbarconfigs").find("div.section").length;
		if(mark=="sys_middle"){
			if(len>=8){
				$.msg("该模板最多添加8个底部链接");
				return;
			} 
		}else if(mark=="sys_simple"){
			if(len>=8){
				$.msg("该模板最多添加8个底部链接");
				return;
			} 
		}else if(mark=="sys_default"){
			if(len>=5){
				$.msg("该模板最多添加5个底部链接");
				return;
			}
		}
        var click = $(this).attr("check");
        if (click == "true") {
            return false
        } else {
            $(this).attr("check", "true");
            $(this).before(addnew);
            $(this).prev(".section").find(".field").slideDown()
        }
        editor1=CKEDITOR.replace('footWriteContents');
        editor1.config.width="800";
     	editor1.config.toolbar=[
     			['Source','Bold','Italic','Underline','Strike','-'],
     			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
     			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
     			['Link','Unlink','Anchor'],
     			['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
     			['Code'],    
     		    ['Styles','Format','Font','FontSize'],
     		    ['TextColor','BGColor'],
     		    ['Maximize', 'ShowBlocks','-','About']
     		];
     	editor1.config.baseFloatZIndex=10100;
     	editor1.config.toolbarGroups = [
 	                     			{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
 	                     			{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
 	                     			{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ] },
 	                     			{ name: 'forms' },
 	                     			'/',
 	                     			{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
 	                     			{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
 	                     			{ name: 'links' },
 	                     			{ name: 'insert' },
 	                     			'/',
 	                     			{ name: 'styles' },
 	                     			{ name: 'colors' },
 	                     			{ name: 'tools' },
 	                     			{ name: 'others' }
 	                     	    ];
     	editor1.config.removeButtons = 'Underline,Subscript,Superscript';

   		// Set the most common block elements.
   		editor1.config.format_tags = 'p;h1;h2;h3;pre';
   		
   		//set picture is null;
   		editor1.config.image_previewText=' ';

   		// Simplify the dialog windows.
   		editor1.config.removeDialogTabs = 'image:advanced;link:advanced';
   		
   		//图片上传
   	    editor1.config.filebrowserImageUploadUrl = rootPath + '/classType/editorUploadImg';
    });
    // 点击新增或添加中的取消
    $(".right-side").on("click.a", ".save .btn-primary:last", function () {
        $(".new-add-btn").attr("check", "false");
        $(this).parents(".title-wrap").slideUp();
    });
    //    点击编辑按钮
    $(".right-side").on("click", ".edit-icon", function () {
        $(this).parents(".title-wrap").find(".field").slideDown();
    });
    //点击编辑中 保存取消按钮
    $(".right-side").on("click.c",".title-wrap .btn-primary", function () {
        var value=$(this).val();
        if(value=="取消"){
            $(".mar-lr5").attr("check", "false");
            $(this).parents(".title-wrap").find(".field").slideUp();
            loadData();
        }
    });
    //    输入框内容与标题同步
    $(".right-side").on("keyup keydown focus", ".title-content", function () {
        var text = $(this).val();
        $(this).parents(".title-wrap").find(".title-info").html(text)
    })
    $(".right-side").on("blur", ".title-content", function () {
        var text = $(this).val();
        if (text == "") {
            $(this).parents(".title-wrap").find(".title-info").html("自定义标题")
        }
    })
    $(".right-side").on("keyup keydown focus", ".address", function () {
        var text = $(this).val();
        $(this).parents(".title-wrap").find(".link-address").html(text)
    })
    $(".right-side").on("blur", ".address", function () {
        var text = $(this).val();
        if (text == "") {
            $(this).parents(".title-wrap").find(".link-address").html("")
        }
    });
   //连接方式选择
    $(".set-system").on("click","input[type='radio']",function(){
        radioTab(this);
    });
    
    if($("#showPageType").val()=="sys_middle"){
    	//默认logo为图片显示
        $("#chooseLists").find("input[type=radio]").each(function(i){
      		if($(this).is(":checked")){
      			var name=$(this).attr("value");
      			if(name=="logo"){
    				updateLogoType("picture");
    				var url=$("#imgUrl").attr("src");
					var html='<img src="'+url+'" alt="" width="150" height="50"/>';
					$("#company_showPic").html(html);
    				$("#oneDiv").css("display","block");
    				$("#twoDiv").css("display","none");
    			}else{
    				updateLogoType("word");
    				var name=$("#spanName").html();
    				$("#company_showPic").html(name);
    				$("#oneDiv").css("display","none");
    				$("#twoDiv").css("display","block");
    			}		
      		}
      	});
        
        $("#chooseLists").on('click','input[type=radio]',function(){
    		var sta=$(this).is(":checked");
    		if(sta){
    			var name=$(this).attr("value");
    			if(name=="logo"){
    				updateLogoType("picture");
    				var url=$("#imgUrl").attr("src");
					var html='<img src="'+url+'" alt="" width="150" height="50"/>';
					$("#company_showPic").html(html);
    				$("#oneDiv").css("display","block");
    				$("#twoDiv").css("display","none");
    			}else{
    				updateLogoType("word");
    				var name=$("#spanName").html();
    				$("#company_showPic").html(name);
    				$("#oneDiv").css("display","none");
    				$("#twoDiv").css("display","block");
    			}			
    		}
    	});
      //腾讯相关
        $.ajax({ 
  		  type: "post", 
  		  url: rootPath+"/companyFootInfo/queryCompanyId",
  		  dataType : 'json',
  		  success: function(result){
  			 var html='';
  	 		 if(result){
  	 			 if(result.tencentWeiboFlag==1){
  	 				 html='<em class="iconfont normal open">&#xe603;</em>&nbsp;&nbsp;<span id="" class="i open">已启用</span>';
  	 				$("#tecent_marking").html(html);
  	 			 }else{
  	 				html='<em class="iconfont normal close">&#xe604;</em>&nbsp;&nbsp;<span id="" class="i close">已禁用</span>';
  	 				$("#tecent_marking").html(html);
  	 			 }
  	 			 if(result.sinaWeiboFlag==1){
  	 				 html='<em class="iconfont normal open">&#xe603;</em>&nbsp;&nbsp;<span id="" class="i open">已启用</span>';
  	 				 $("#xlFlag").html(html);
  	 			 }else{
  	 				html='<em class="iconfont normal close">&#xe604;</em>&nbsp;&nbsp;<span id="" class="i close">已禁用</span>';
  	 				$("#xlFlag").html(html); 
  	 			 }
  	 			 if(result.tencentWechatFlag==1){
  	 				 html='<em class="iconfont normal open">&#xe603;</em>&nbsp;&nbsp;<span id="" class="i open">已启用</span>';
  	 				 $("#wxFlag").html(html);
  	 			 }else{
  	 				html='<em class="iconfont normal close">&#xe604;</em>&nbsp;&nbsp;<span id="" class="i close">已禁用</span>';
  	 				$("#wxFlag").html(html); 
  	 			 }
  	 		 }else{
  	 			html='<em class="iconfont normal close">&#xe604;</em>&nbsp;&nbsp;<span id="" class="i close">已禁用</span>';
  	 			$("#tecent_marking").html(html);
  		 		$("#wxFlag").html(html);
  		 		$("#xlFlag").html(html);
  	 		 }
  	 		
  		  }
  	  });
      
        //点击修改营销链接
  	  $(".savebtn_mark").on('click',function(){
  		  var $this=$(this);
  		  var status=$this.parents(".other-link").find(".tit-font em.normal").hasClass("open");
  		  if(!status){
  			  return;
  		  }
  		  if($(this).text()=="编辑"){
  			 $(this).prev().removeAttr("disabled");
  			 $(this).css("display","none");
  			 $(this).next().css("display","inline-block").next().css("display","inline-block");
  		  }
  	  });
  	 //营销按钮取消
      $(".marking_cancle").on('click',function(){
    	    var $this=$(this);
  	      	var mark=$(this).attr("mark");
  	      	var value=$(this).attr("value");
  	      	if("sina_mark"==mark){
  	      		$("#sinaNos").val(value);
  	      	}else if("qq_mark"==mark){
  	      		$("#tecentMarking").val(value);
  	      	}
  	      	$this.css("display","none").prev().css("display","none").prev().css("display","inline-block").prev().attr("disabled","disabled");
      });

        
    }
     //页尾
        $(".modify-btn").on("click",function(){
            if(!($(this).hasClass("saves-btn"))){
                var _labelHtml = $(this).parents(".line-right").find("label").html();
                $(this).css({
                    "border": "1px solid #237fd5",
                    "background-color": "#237fd5",
                    "color": "#fff"
                }).html("保存").addClass("saves-btn");
                $(this).next().css("display","inline-block");
                $(this).parents(".line-right").find("label").css("display","none");
                $(this).parents(".line-right").find("input").css("display","inline-block").val(_labelHtml);
            }else if($(this).hasClass("saves-btn")){
                var _val = $(this).parents(".line-right").find("input").val();
                $(this).css({
                    "border": "1px solid #ccc",
                    "background-color": "#fff",
                    "color": "#999"
                }).html("修改");
                $(this).next().css("display","none");
                $(this).parents(".line-right").find("input").css("display","none");
                $(this).parents(".line-right").find("label").css("display","block").html(_val);
                $(this).removeClass("saves-btn");
            }
        });
        $(".cancel-btn").on("click",function(){
            $(this).css("display","none");
            $(this).prev().css({
                "border": "1px solid #ccc",
                "background-color": "#fff",
                "color": "#999"
            }).html("修改").removeClass("saves-btn");
            $(this).parents(".line-right").find("input").css("display","none");
            $(this).parents(".line-right").find("label").css("display","block");
        });
    
    //删除尾部链接
    $(document) .on('click.deleteparents_icon','li i.deleteparents_icon',function(){
    	var id=$(this).attr("ids");
    		$.confirm("您确定要删除吗?",function(b){
    			if(b){
    				$.ajax({ 
    					  type: "post", 
    					  url: rootPath+"/sysPageHeadFoot/deleteChildHeadTitle", 
    					  data: { "id" : id},
    					  success: function(result){
    						loadData();
    						query_footShowContent();
    					  }
    				  });
    			}else{
    				return false;
    			}
    		});
     })//编辑标题
      .on('click.save_headtitle','.right-side .save_headtitle',function(){
    	CKupdate();
    	var $this=$(this);
    	var id=$this.attr("ids");
    		var urlType,openType;
    		var $p=$this.parents("div.father_content");
    		var footName= $p.find("input.titleName").val();
    		var footUrl= $p.find("input.weburl").val();
    		var contents=encodeURI($p.find("textarea.editorContents").val());
    		$p.find(".links_url").find("input[type=radio]").each(function(){
    			if($(this).is(":checked")){
    				urlType=$(this).val();
    			}
    		});
    		$p.find(".type_url").find("input[type=radio]").each(function(){
    			if($(this).is(":checked")){
    				openType=$(this).val();
    			}
    		});
    		if(footName.length>6 || footName.length <2 || footName == ""){
        		$.msg("标题长度为2到6位字符或汉字并且不能为空！");
        		return;
        	}
    		if(urlType && urlType=="LINK_CUSTOM"){
    			if(footUrl && footUrl!=""){
        			var sp="^((https|http|ftp|rtsp|mms)?://)";
        			var str = footUrl.match(sp);
        			if(str==null){
        				$.msg("输入链接无效");
        				return;
        			}
        		}else{
        			footUrl="";
        		}
    		}else{
    			footUrl="";
    		}
    		if(footUrl && footUrl.length>100){
    			$.msg("链接地址长度最大为100个字符");
				return;
    		}
    		$.ajax({ 
	       		  type: "post", 
	       		  url: rootPath+"/sysPageHeadFoot/updateFoots", 
	       		  data: {
	       			  	"id": id,
	       			  	"name":footName, 
	       			  	 "url" : footUrl,
	       			  	 "openType" : openType,
	       			  	 "content" :contents,
	       			  	 "urlType" : urlType
	       			  },
	       		  success: function(result){
	       			 loadData();
	       			 query_footShowContent();
	       		  }
	       	});
    })//开关
     .on('click.normal','.tit-font em.normal',function(){
    	var status=$(this).hasClass("open");
    	if($(this).hasClass("open")&&(!($(this).hasClass("warning-btn")))){
            $(this).removeClass("open").addClass("close").html("&#xe604;");
            $(this).parents(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
        }else if($(this).hasClass("close")){
            $(this).removeClass("close").addClass("open").html("&#xe603;");
            $(this).parents(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
        };
        if(status){
          	 var data={};
          	 var type=$(this).parents(".tit-font").attr("mark");
          	 if(type=="weibo_flag"){
          		 data.tencentWeiboFlag=0;
          		 $("#txqq").css("display","none");
          	 }else if(type=="sina_flag"){
          		 data.sinaWeiboFlag=0;
          		 $("#txsina_Flag").css("display","none");
          	 }else if(type=="wx_flag"){
          		 data.tencentWechatFlag=0;
          		 $("#show_wxPic").css("display","none");
          	 }
          	 $.ajax({
        			type: "post",
        			url:  rootPath+"/companyFootInfo/editCompanyMarking",
        			dataType : "json",
        			data:data,
        			success: function(jsonData){
        				
        			}
        		});
           }else{
          	 var data={};
          	 var type=$(this).parents(".tit-font").attr("mark");
          	 if(type=="weibo_flag"){
          		 data.tencentWeiboFlag=1;
          		 $("#txqq").css("display","inline-block");
          	 }else if(type=="sina_flag"){
          		 data.sinaWeiboFlag=1;
          		$("#txsina_Flag").css("display","inline-block");
          	 }else if(type=="wx_flag"){
          		 data.tencentWechatFlag=1;
          		$("#show_wxPic").css("display","inline-block");
          	 }
          	 $.ajax({
        			type: "post",
        			url:  rootPath+"/companyFootInfo/editCompanyMarking",
        			dataType : "json",
        			data:data,
        			success: function(jsonData){
        				
        			}
        		});
           }
    });
    
    //点击修改公司信息配置按钮
    $(".updateCompanyInfo").on('click',function(){
  	  var $this=$(this);
  	//判断是否企业标准版
		var isSelfNet=true;
		$.ajax({
			url:rootPath+"/companyFootInfo/isEnterootPathriseVersion",
			async:false,
			type:"post",
			success:function(data){
				if(!data){
					$.alert("只有企业标准版及以上版本才可以修改版本信息");
					isSelfNet=false;
				}
			}
		})
		if(!isSelfNet){
			return;
		}
  	  if($this.attr("mark")=="update"){
  		  $(".show_company").hide();
      	  $(".hide_company").css("display","inline-block");
      	  $this.attr("mark","save").html("保存");
  	  }else{
      	  var companyName=$("#companyName").val();
    		  var companyOverview=$("#companyOverview").val();
    		  var copyright=$("#copyright").val();
    		  var icpNos=$("#icpNos").val();
    		 if(companyName==""){
				$.msg("公司名称不能为空");
				return;
			 }
    		 if(companyName.length>20){
 				$.msg("公司名称不能超过20个字符");
 				return;
 			 }
    		 if(copyright==""){
				$.msg("版权号不能为空");
				return;
			 }
    		 if(copyright.length>20){
 				$.msg("版权号不能超过20个字符");
 				return;
 			 }
    		 if(icpNos==""){
				$.msg("备案号不能为空");
				return;
			 }
    		 if(icpNos.length>50){
 				$.msg("备案号不能超过50个字符");
 				return;
 			 }
    		 $.ajax({ 
    			  type: "post", 
    			  url: rootPath+"/companyFootInfo/editCompanyIo", 
    			  data: {"companyName":companyName,
    				     "overview" : companyOverview,
    				  	 "copyright" : copyright,
    				  	 "icpNo" : icpNos
    				  },
    			  success: function(result){
    				  if(result=="error"){
    					  $.alert("只有企业标准版及以上版本才可以修改版本信息");
    					  return;
    				  }else{
    					$("#companyName").val(companyName);
    					$("#copyright").val(copyright);
    		      		$("#icpNos").val(icpNos);
	      		      	$("#companyName_1").text(companyName);
	  					$("#copyright_1").text(copyright);
	  		      		$("#icpNos_1").text(icpNos);
    				  }
    				  isSelf();
    			  }
    		  });
    		 $(".show_company").css("display","inline-block");
     	    $(".hide_company").hide();
     	    $this.attr("mark","update").html("修改");
  	  }
    });
   
});

//加载数据
function loadData(){
	var id=$("#configId").val();
	$.ajax({ 
		  type: "post", 
		  url: rootPath+"/sysPageHeadFoot/queryByConfigId/"+id,
		  dataType : 'html',
		  success: function(result){
	 		 $("#navbarconfigs").html(result);
		  }
	  });
}

//判断是否为自主域名
function isSelf(){
	$.ajax({
		url:rootPath+"/sysPageHeadFoot/isEnterootPathriseVersion",
		type:"post",
		success:function(data){
			if(data){
				$.ajax({
			        url:rootPath+"/companyFootInfo/queryCompanyId",
			        type:"post",
			        dataType : 'json',
			        success:function(result){
			        	var html="";
			        	if(result){
			        		html+="Copyright@<i id='i-copyright'>"+(result.copyright?result.copyright:"")+"</i> <i id='i-name'>"+(result.companyName?result.companyName:"")+"</i>　版权所有　    <i id='i-icp'>"+(result.icpNo?result.icpNo:"")+"</i>";
			        		$("#show_companyOveriew").html(result.overview?result.overview:"");
			        		if(result.tencentWechat){
			        			$("#show_wxPic").attr("src",result.tencentWechat?result.tencentWechat:"");
			        		}
			        		if(result.tencentWeiboFlag && result.tencentWeiboFlag==1){
			        			$("#txqq").css("display","inline-block");
			        		}else{
			        			$("#txqq").css("display","none");
			        		}
							if(result.sinaWeiboFlag && result.sinaWeiboFlag==1){
								$("#txsina_Flag").css("display","inline-block");		        			
							}else{
								$("#txsina_Flag").css("display","none");	
							}
							if(result.tencentWechatFlag && result.tencentWechatFlag==1){
								$("#show_wxPic").css("display","inline-block");
							}else{
								$("#show_wxPic").css("display","none");
							}
			        	}
			        	$("#copyrightList").html(html);
			        }
				});
			}else{
				$.ajax({
			        url:rootPath+"/companyFootInfo/queryCompanyId",
			        type:"post",
			        dataType : 'json',
			        success:function(result){
			        	var html="";
			        	if(result){
			        		$("#show_companyOveriew").html(result.overview?result.overview:"");
			        		$("#show_wxPic").attr("src",result.tencentWechat?result.tencentWechat:"");
			        		if(result.tencentWeiboFlag && result.tencentWeiboFlag==1){
			        			$("#txqq").css("display","inline-block");
			        		}else{
			        			$("#txqq").css("display","none");
			        		}
							if(result.sinaWeiboFlag && result.sinaWeiboFlag==1){
								$("#txsina_Flag").css("display","inline-block");		        			
							}else{
								$("#txsina_Flag").css("display","none");	
							}
							if(result.tencentWechatFlag && result.tencentWechatFlag==1){
								$("#show_wxPic").css("display","inline-block");
							}else{
								$("#show_wxPic").css("display","none");
							}
			        	}
			        }
				});
				var html="";
	        	html+="<i class='i-copyright'>Copyright@2014-2017</i> <i id='i-name'>云朵课堂</i>　<i class='i-copyright'>技术支持</i>　   <i id='i-icp'>京ICP备15011506号</i>";
	        	$("#copyrightList").html(html);
			}
		}
	})
}

//查询展示的内容
function query_footShowContent(){
	var id=$("#configId").val();
	var showPageType=$("#showPageType").val();
	$("#foot_showContents").html("");
	$.ajax({
        type:"post",
        url:rootPath+"/sysPageHeadFoot/queryFootShowContent/"+id,
        dataType:'json',
        success:function(result){
        	var html="";
        	if(showPageType=="sys_default"){
        		if(result.length<=0){
        			html=' <a href="#">关于我们</a><a href="#">联系我们</a><a href="#">隐私条款</a><a href="#">法律声明</a>';
        		}else{
        			$.each(result,function(i,item){
                		html+='<a href="#">'+item.name+'</a>';
                	});
        		}
        	}else if(showPageType=="sys_simple"){
        		if(result.length<=0){
        			html='<a href="#">关于我们</a>|<a href="#">加盟代理</a>|<a href="#">网站地图</a>|'+
        				 '<a href="#">合作伙伴</a>|<a href="#">免责声明</a>|<a href="#">招贤纳士</a>|'+
        				 '<a href="#">联系方式</a>';
        		}else{
        			$.each(result,function(i,item){
                		html+='<a href="#">'+item.name+'</a>|';
                	});
        			html=html.substring(0,html.length-1);
        		}
        	}else{
        		if(result.length<=0){
        			html='<a href="#">关于我们</a><a href="#">隐私条款</a><a href="#">产品声明</a><a href="#">法律声明</a>'+
        			'<a href="#">服务报价</a><a href="#">帮助中心</a><a href="#">成功案例</a><a href="#">联系我们</a>';
        		}else{
        			$.each(result,function(i,item){
                		html+='<a href="#">'+item.name+'</a>';
                	});
        		}
        	}
        	$("#foot_showContents").append(html);
        }
	});
}
function radioTab(obj){
        var  value =  $(obj).attr("sort");
        switch($(obj).val()){
            case "LINK_CUSTOM":
            case "LINK_DEFAULT":
                $(obj).parents(".content").find(".radio-tab"+value).siblings(".radio-tab").hide();
                $(obj).parents(".content").find(".radio-tab"+value).show();
                $(obj).parents(".content").find(".link-type").show();
                break;
            case "LINK_NONE":
                $(obj).parents(".content").find(".radio-tab").hide();
                $(obj).parents(".content").find(".link-type").hide();
                break;
            default:
                break;
        }
    }
//添加尾部链接
function addFootTitle(e){
	CKupdate();
	var $this=$(e);
	var urlType,openType;
	var id=$("#configId").val();
	var footName=$("#addOneFootName").val();
	var footUrl=$("#footUrl").val();
	var contents=encodeURI($("#footWriteContents").val());
	if($this.hasClass("disabled")){
		return;
	}
	$this.addClass("disabled");
	$("#footUrlType").find("input[name=linkMethod]").each(function(){
		if($(this).is(":checked")){
			urlType=$(this).val();
		}
	});
	$("#urlOpenType").find("input[name=openMethod]").each(function(){
		if($(this).is(":checked")){
			openType=$(this).val();
		}
	});
	if(urlType && urlType=="LINK_CUSTOM"){
		if(footUrl && footUrl!=""){
			//footUrl="http://"+footUrl;
			var sp="^((https|http|ftp|rtsp|mms)?://)";
			var str = footUrl.match(sp);
			if(str==null){
				$.msg("输入链接无效");
				$this.removeClass("disabled");
				return;
			}
		}
	}
	
	if(footName.length>6 || footName <2 || footName == ""){
		$.msg("标题长度为2到6位字符或汉字并且不能为空！");
		$this.removeClass("disabled");
		return;
	}
	if(footUrl && footUrl.length>100){
		$.msg("链接地址长度最大为100个字符");
		$this.removeClass("disabled");
		return;
	}
	var sequence=0;
	$("#navbarconfigs").find("div.section").each(function(i){
		sequence=i+1;
	});
	 $.ajax({ 
 		  type: "post", 
 		  url: rootPath+"/sysPageHeadFoot/saveFoots", 
 		  data: {"name":footName, 
 			  	 "url" : footUrl,
 			  	 "openType" : openType,
 			  	 "sequence" : sequence,
 			  	 "content" :contents,
 			  	 "configId" : id,
 			  	 "urlType" : urlType
 			  },
 		  success: function(result){
 			 loadData();
 			 query_footShowContent();
 		  }
 	  });
}
//处理CKEDITOR的值
 function CKupdate() {
    for (instance in CKEDITOR.instances){
      CKEDITOR.instances[instance].updateElement();
    }
 }	

//修改公司名称
function updateCompanyInfo(e){
	var $this=$(e);
	var mark=$this.attr("mark");
	var companyName=$("#companyName").val();
	var companyOverview=$("#companyOverview").val();
	var copyright=$("#copyright").val();
	var icpNos=$("#icpNos").val();
	if("companyName"==mark){
		if(companyName==""){
			$.msg("公司名称不能为空");
			return;
		}
	}else if("copyright"==mark){
		if(copyright==""){
			$.msg("版权号不能为空");
			return;
		}
	}else if("icpNos"==mark){
		if(icpNos==""){
			$.msg("备案号不能为空");
			return;
		}
	}
	 $.ajax({ 
		  type: "post", 
		  url: rootPath+"/companyFootInfo/editCompanyIo", 
		  data: {"companyName":companyName, 
			  	 "overview" : companyOverview,
			  	 "copyright" : copyright,
			  	 "icpNo" : icpNos
			  },
		  success: function(result){
			  if(result=="error"){
				  $.alert("只有企业标准版及以上版本才可以修改版本信息");
				  return;
			  }
			  isSelf();
		  }
	  });
}

//点击修改公司名称
function changen(){
	$("#spanName").css("display","none");
	$("#upName").css("display","block");
	$("#btnupdate").css("display","none");
	$("#btnsave").css("display","block");
}
//修改名称
function changCompanyName(){
	var companyName=$("#upName").val();
	if(companyName.length<=0){
		$.msg("公司名称不能为空!");
		return;
	}
	if(companyName.length<2 || companyName.length>8){
		$.msg("公司名称长度范围为2-8个字符");
		return;
	}
	 $.ajax({ 
 		  type: "post", 
 		  url: rootPath+"/companyFootInfo/editCompanyIo", 
 		  data: {
 			  "logoText":companyName
 			  },
 		  success: function(result){
 			 if(result=="error"){
				  $.alert("只有企业标准版及以上版本才可以修改版本信息");
				  return;
			  }else{
				  $.msg("保存成功");
			  }
 			 $("#company_showPic").html(companyName);
 			 $("#spanName").html(companyName);
 			  $("#btnupdate").css("display","block");
     		  $("#btnsave").css("display","none");
     		  $("#spanName").css("display","block");
     		  $("#upName").css("display","none");
 		  }
 	  });
}
//修改公司logo
function changCompanyIco(type){
	if("logo"==type){
		var filename=$("#imgData").val();
		if(filename != "" && filename != null){
			$.ajaxFileUpload({
				url : rootPath+"/sysPageHeadFoot/updateCompanyFootIco;"+ window["sessionName"] + "=" + window["sessionId"],
				secureuri : false,// 安全协议
				async : false,
				fileElementId : 'imgData',
				dataType:'json',
				data:{type:type},
				type : "POST",
				success : function(data) {
					if(data.url!="null"){	
						$("#imgUrl").attr("src",data.url);
						var html='<img src="'+data.url+'" alt="" width="150" height="50"/>';
						$("#company_showPic").html(html);
						$.msg("上传成功!");
					}else{
						$.msg("上传失败!");
					}
				}
			});
		}else{
			$.msg("上传失败!");
		}
	}else{
		var filename=$("#imgDatas").val();
		if(filename != "" && filename != null){
			$.ajaxFileUpload({
				url : rootPath+"/sysPageHeadFoot/updateCompanyFootIco;"+ window["sessionName"] + "=" + window["sessionId"],
				secureuri : false,// 安全协议
				async : false,
				fileElementId : 'imgDatas',
				dataType:'json',
				data:{type:type},
				type : "POST",
				success : function(data) {
					if(data.url!="null"){
						$("#wxPic_marking").val(data.url);
						if(data.url){
							$("#show_wxPic").attr("src",data.url);
						}
						$.msg("上传成功!");
						$("#imgDatas").prev().html("更换");
					}else{
						$.msg("上传失败!");
					}
				}
			});
		}else{
			$.msg("上传失败!");
		}
	}
	
}

//修改logo显示类型
function updateLogoType(type){
	 $.ajax({ 
		  type: "post", 
		  url: rootPath+"/companyFootInfo/editCompanyIo", 
		  data:{"logoType":type},
		  success: function(result){
			
		  }
	  });
}

//修改公司营销相关信息
function updateMarkingInfo(e){
	var $this=$(e);
	if($this.hasClass("disabled")){
		return;
	}
	var tencentWeibo,sinaWeibo,wxPic_marking, mark=$this.attr("mark");
	if("qq_mark"==mark){
		tencentWeibo=$("#tecentMarking").val();
		if(tencentWeibo!="" && tencentWeibo.length>100 ){
			$.msg("链接不能为空且长度不能超过100个字符");
			return;
		}
	}else if("sina_mark"==mark){
		sinaWeibo=$("#sinaNos").val();
		if(sinaWeibo!="" && sinaWeibo.length>100 ){
			$.msg("链接不能为空且长度不能超过100个字符");
			return;
		}
	}else if("wx_mark"==mark){
		wxPic_marking=$("#wxPic_marking").val();
		if(wxPic_marking!="" && wxPic_marking>100 ){
			$.msg("链接不能为空且长度不能超过100个字符");
			return;
		}
	}
	 $.ajax({ 
		  type: "post", 
		  url: rootPath+"/companyFootInfo/editCompanyMarking", 
		  data: {"tencentWeibo":tencentWeibo, 
			  	 "sinaWeibo" : sinaWeibo,
			  	 "tencentWechat" : wxPic_marking
			  },
		  success: function(result){
			  if(result=="success"){
				  $.msg("保存成功");
			  }
			  if("wx_mark"!=mark){
				  $this.css("display","none").prev().css("display","inline-block").prev().attr("disabled","disabled");
				  $this.next().css("display","none");
			  }
			  isSelf();
		  }
	  });
}