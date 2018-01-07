(function($) {
	
	 var _sh1 = "&lt;script&gt;<br/>"+
     "	(function(i, s, o, g, r, a, m) {<br/>"+
     "		i[\'GoogleAnalyticsObject\'] = r;<br/>"+
     "		i[r] = i[r] ||<br/>"+
     "		function() { (i[r].q = i[r].q || []).push(arguments)<br/>"+
     "		},"+
     "				i[r].l = 1 * new Date();<br/>"+
     "		a = s.createElement(o),<br/>"+
     "				m = s.getElementsByTagName(o)[0];<br/>"+
     "		a.async = 1;<br/>"+
     "		a.src = g;<br/>"+
     "		m.parentNode.insertBefore(a, m)<br/>"+
     "	})(window, document, \'script\', \'//www.google-analytics.com/analytics.js\', \'ga\');<br/>"+
     "	ga(\'create\', \'UA-<span class=\"input-show\">71745163-1</span>\', \'auto\');<br/>"+
     "	ga(\'send\', \'pageview\');<br/>"+
     "&lt;/script&gt;";
     var _sh2 = "&lt;script&gt;<br/>"+
     "var _hmt = _hmt || [];<br/>"+
     "(function() <br/>"+
     "var hm = document.createElement(\"script\");<br/>"+
     "hm.src = \""+
     "//hm.baidu.com/hm.js?<br/>"+
     "<span class=\"input-show\">f6000fc073c6b683e3d957c89bf098cd</span>\";<br/>"+
     "var s = document.getElementsByTagName(\"script\")[0];<br/>"+
     "s.parentNode.insertBefore(hm, s);<br/>"+
     "})();<br/>"+
     "&lt;/script&gt;";
	 var _sh3 = "&lt;script type=\"text/javascript\" src=\"http://tajs.qq.com/stats?sId=<span class=\"input-show\">53614549</span>\" charset=\"UTF-8\"&gt;&lt;/script&gt;";
	 var _sh4 = "&lt;script type=\"text/javascript\"&gt;<br/>"+
     "	var cnzz_protocol = ((\"https:\" == document.location.protocol) ? \" https://\" : \" http://\");<br/>"+
     "	document.write(unescape(\"%3Cspan id=\'cnzz_stat_icon_<span class=\"input-show\">1257041520</span>\'%3E%3C/span%3E%3Cscript src=\'\" + cnzz_protocol + \"s11.cnzz.com/z_stat.php%3Fid%3D<span class=\"input-show\">1257041520</span>\' type=\'text/javascript\'%3E%3C/script%3E\"));<br/>"+
     "&lt;/script&gt;";
	
	var Forms = {
		init : function() {
		    var $this=this;
		    $this.loadData();
		    $this.loadSeoSet();
		    //上传图片
			$("#c-up").on('click',function(){
				$(this).prev().trigger("click");
				return false;
			});
			
			$("#pic-up").on('click',function(){
				$("#imgData").trigger("click");
				return false;
			})
			 //启用
			 $(".start").on("click",function(){
		        if($(this).parent(".pay-btn").hasClass("c")){
		            $(this).hide();
		            $(this).next().show();
		            $(this).parent(".pay-btn").removeClass("c").addClass("ccc");
		        }
		        var type=$(this).attr("type");
		        var status=1;
		        $.ajax({ 
		       		  type: "post", 
		       		  url: rootPath+"/sysConfigSeo/addSeo", 
		       		  dataType:'json',
		       		  data:{"type":type,"status":status},
		       		  success: function(jsonData){
		       			$this.loadSeoSet();
		       		  }
				 });
		        return false;
		    });
			//禁用
		    $(".ban-btn").on("click",function(){
		        if($(this).parents(".pay-btn").hasClass("ccc")){
		            $(this).parent(".ban-box").hide();
		            $(this).parents(".pay-btn").find(".start").show();
		            $(this).parents(".pay-btn").removeClass("ccc").addClass("c");
		        }
		        var type=$(this).attr("type");
		        var status=0;
		        $.ajax({ 
		       		  type: "post", 
		       		  url: rootPath+"/sysConfigSeo/addSeo", 
		       		  dataType:'json',
		       		  data:{"type":type,"status":status},
		       		  success: function(jsonData){
		       			$this.loadSeoSet();
		       		  }
				 });
		        return false;
		    });
		    //设置
		    $(".set-btn").on('click',function(){
		    	var type=$(this).attr("type");
		    	window.location.href=rootPath+'/sysConfigSeo/configSeo/'+type;
		    });
		    //百度统计代码开关
		    $(".tit-font em.normal").off().on("click",function(){
		    	var status=$(this).hasClass("open");
		        if($(this).hasClass("open")){
		            $(this).removeClass("open").addClass("close").html("&#xe604;");
		            $(this).parents(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
		        }else{
		            $(this).removeClass("close").addClass("open").html("&#xe603;");
		            $(this).parents(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
		        };
		        var type="SEO_SET_CODE";
		        if(status){
		        	$("#tg_Type").attr("disabled","disabled");
		        	$("#tg_Code").attr("disabled","disabled");
		        	$.ajax({
		     			type: "post",
		     			url:  rootPath+"/sysConfigSeo/addSeo",
		     			dataType : "json",
		     			 data:{"type":type,"status":0},
		     			success: function(jsonData){
		     				$this.loadSeoSet();
		     			}
		     		});
		        }else{
		        	$("#tg_Type").removeAttr("disabled");
		        	$("#tg_Code").removeAttr("disabled");
		        	$.ajax({
		     			type: "post",
		     			url:  rootPath+"/sysConfigSeo/addSeo",
		     			dataType : "json",
		     			 data:{"type":type,"status":1},
		     			success: function(jsonData){
		     				$this.loadSeoSet();
		     			}
		     		});
		        }
		    });
		    
		    //SEO设置
		    $(".upload-pic-box").on("mouseenter",function(){
		        $(this).find(".trans").fadeIn(50)
		    });
		    $(".upload-pic-box").on("mouseleave",function(){
		        $(this).find(".trans").fadeOut(50)
		    });

		    //网站统计分析代码部署
		    $(".code-select input").on("change keyup keydown keypress",function(){
		        var _val = $(this).val();
		        $(".chosed span.input-show").html(_val);
		        $(".codesive-btn").addClass("blue-btn");
		        $(".codecancel-btn").css("display","inline-block");
		    });
		    
		    $(".code-select select").change(function(){
		        $(this).siblings("input").val("");
		        $(".code-show").html("");
		        $(".codesive-btn").removeClass("blue-btn");
		        $(".codecancel-btn").css("display","none");
		        var _value = $(this).find("option:checked").attr("value");


		        if(_value == "google"){
		            $(".sh1").show().addClass("chosed").append("");
		            $(".sh2,.sh3,.sh4").hide().removeClass("chosed");
		            $(".input-tit").html("UA -");
		        }else if(_value == "baidu"){
		            $(".sh2").show().addClass("chosed").append("");
		            $(".sh1,.sh3,.sh4").hide().removeClass("chosed");
		            $(".input-tit").html("hm.src =");
		        }else if(_value == "tencent"){
		            $(".sh3").show().addClass("chosed").append("");
		            $(".sh1,.sh2,.sh4").hide().removeClass("chosed");
		            $(".input-tit").html("sId =");
		        }else if(_value == "cnzz"){
		            /*$(".sh4").show().addClass("chosed").append(_sh4);*/
                    $(".sh4").show().addClass("chosed").append("");
		            $(".sh1,.sh2,.sh3").hide().removeClass("chosed");
		            $(".input-tit").html("id =");
		        }

		    });
		    //点击返回
		    $(".codecancel-btn").on("click",function(){
		        $(".codesive-btn").removeClass("blue-btn");
		        $(this).css("display","none");
		        $this.loadSeoSet();
		        return false;
		    });
		    //保存统计代码
		    $(".code-select").on("click",'div.codesive-btn',function(){
		        if($(this).hasClass("blue-btn")){
		            $(this).removeClass("blue-btn");
		        }
		        var fathertype="SEO_SET_CODE";
		        var type=$("#tg_Type").find("option:selected").val();
		        var code=$("#tg_Code").val();
		        $.ajax({
	     			type: "post",
	     			url:  rootPath+"/sysConfigSeo/addSeo",
	     			dataType : "json",
	     			data:{"type":fathertype,"statType":type,"statKey":code},
	     			success: function(jsonData){
	     				
	     			}
	     		});
		        $(".codecancel-btn").css("display","none");
		        return false;
		    });
		    //返回
			 $(".hcancle").on('click',function(){
				 window.location.href=rootPath+"/company/companyService";
			 })
		},
		loadData : function(){
			 $.ajax({ 
	       		  type: "post", 
	       		  url: rootPath+"/sysConfigIco/queryIco", 
	       		  dataType:'json',
	       		  success: function(jsonData){
	       			 if(jsonData && jsonData.path && jsonData.path != ""){
	       				$("#imgObject").attr("src",jsonData.path);
						$("#c-up").text("更换");
						$("#pic-up").text("更换");
	       			 }else{
						$("#c-up").text("上传");
	       			 }
	       		  }
			 });
		},
		loadSeoSet : function(){
			 $.ajax({ 
	       		  type: "post", 
	       		  url: rootPath+"/sysConfigSeo/queryData", 
	       		  dataType:'json',
	       		  success: function(jsonData){
	       			if(jsonData.length>0){
	       				$.each(jsonData,function(i,item){
	       					if(item.type=="SEO_SET_INDEX" && item.status==1){
	       						$(".seo_lists").find(".index_seo").addClass("ccc").find(".start")
	       						.css("display","none").next().css("display","block");
	       					}else if(item.type=="SEO_SET_COURSE" && item.status==1){
	       						$(".seo_lists").find(".course_seo").addClass("ccc").find(".start")
	       						.css("display","none").next().css("display","block");
	       					}else if(item.type=="SEO_SET_OPENCLASS" && item.status==1){
	       						$(".seo_lists").find(".openclass_seo").addClass("ccc").find(".start")
	       						.css("display","none").next().css("display","block");
	       					}else if(item.type=="SEO_SET_NEWS" && item.status==1){
	       						$(".seo_lists").find(".news_seo").addClass("ccc").find(".start")
	       						.css("display","none").next().css("display","block");
	       					}else if(item.type=="SEO_SET_CODE" && item.status==1){
		       					$(".tit-font em.normal").removeClass("close").addClass("open").html("&#xe603;");
		     		            $(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
		     		            $("#tg_Type").removeAttr("disabled");
		     		            $("#tg_Code").removeAttr("disabled");
		     		            if(item.statType){
		     		            	if(item.statType=="baidu"){
		     		            		$("#tg_Type").find("option[value='baidu']").attr("selected","selected");
		     		            		$("#tg_Code").val(item.statKey?item.statKey:"");
		     		            		$(".sh2").show().addClass("chosed").append(_sh2);
		     				            $(".sh1,.sh3,.sh4").hide().removeClass("chosed");
		     				            $(".input-tit").html("hm.src =");
		     		    		        $(".chosed span.input-show").html(item.statKey?item.statKey:"");
			     		            }else if(item.statType=="google"){
			     		            	$("#tg_Type").find("option[value='google']").attr("selected","selected");
			     		            	$("#tg_Code").val(item.statKey?item.statKey:"");
			     		            	$(".sh1").show().addClass("chosed").append(_sh1);
			     			            $(".sh2,.sh3,.sh4").hide().removeClass("chosed");
			     			            $(".input-tit").html("UA -");
			     				        $(".chosed span.input-show").html(item.statKey?item.statKey:"");
			     		            }else if(item.statType=="cnzz"){
			     		            	$("#tg_Type").find("option[value='cnzz']").attr("selected","selected");
			     		            	$("#tg_Code").val(item.statKey?item.statKey:"");
			     		            	$(".sh4").show().addClass("chosed").append(_sh4);
			     			            $(".sh1,.sh2,.sh3").hide().removeClass("chosed");
			     			            $(".input-tit").html("id =");
			     				        $(".chosed span.input-show").html(item.statKey?item.statKey:"");
			     		            }else if(item.statType=="tencent"){
			     		            	$("#tg_Type").find("option[value='tencent']").attr("selected","selected");
			     		            	$("#tg_Code").val(item.statKey?item.statKey:"");
			     		            	$(".sh3").show().addClass("chosed").append(_sh3);
			     			            $(".sh1,.sh2,.sh4").hide().removeClass("chosed");
			     			            $(".input-tit").html("sId =");
			     				        $(".chosed span.input-show").html(item.statKey?item.statKey:"");
			     		            }
		     		            }else{
		     		            	$(".sh1").append(_sh1);
		     		            }
	       					}
	       				});
	       			}
	       		  }
			 });
		},
		addCompanyIco : function(){
			$.ajaxFileUpload({
				url : rootPath+"/sysConfigIco/uploadIco;"+ window["sessionName"] + "=" + window["sessionId"],
				secureuri : false,// 安全协议
				async : false,
				fileElementId : 'imgData',
				dataType:'json',
				type : "POST",
				success : function(data) {
					$("#imgObject").attr("src",data.url);
					$("#imgObject").attr("ids",data.picPath);
					$("#c-up").text("更换");
					$("#pic-up").text("更换");
				},
				error : function(resp,msg,err){
					console.log(resp);
				}
			});
		}
	}
	
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)