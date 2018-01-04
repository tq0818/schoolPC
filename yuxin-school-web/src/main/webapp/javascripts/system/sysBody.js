(function($){
	var links=["http://koubei.yunduoketang.com","http://laoheiedu.yunduoketang.com/","http://weilan.yunduoketang.com","","",""];
	var captions=["适合于多个学科的课程在首页集中展示","适合于首页有普通课程、推荐课程和新闻资讯","适合于首页侧重营销的情况","适合于内容较多时使用，突出重点课程、新闻","适合于直播和录播网校，分类清晰简单","清新简洁而不失大方，要fashion,选它就对了！",'适用于内容较多的网校，可选择性展示网校内所有的功能。']
	var sysBody={
		init: function(){
			var $this=this;
			// 加载分校
			$this.loadSchools();
			// 加载模板
//			$this.loadModules();
			// 鼠标滑过
//			$this.hover();
			// 预览
			$this.preview();
			// 配置
			$this.config();
			// 删除
			$this.del();
			// 使用模板
			$this.use();
			// 添加模板
			$this.add();
		},
		loadSchools: function(){
			var $this=this;
			$(".sc-type").html('');
			$(".title").on("click.btn.schools",".sc-type a",function(){
				$(".sc-type a").removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success");
				$this.loadModules($(this).attr("id"),$this.findUsedTheme);
			});
			if($("#isAdmin").val() && $("#isAdmin").val()=="true"){
				$this.$ajax({
					url: rootPath + "/sysConfigSchool/getSchoolJson",
					success: function(jsonData){
						$.each(jsonData,function(i){
							$(".sc-type").append('<a href="javascript:;" id="'+jsonData[i].id+'" class="btn btn-mini btn-default" style="display:none">'+jsonData[i].schoolName+'</a>&nbsp;&nbsp;');
						})
						if($("#schoolId").val()){
							$(".sc-type").find("a[id='"+$("#schoolId").val()+"']").trigger("click.btn.schools");
						}else{
							$(".sc-type").find("a").eq(0).trigger("click.btn.schools");
						}
					}
				});
			}else if($("#isSubAdmin").val() && $("#isSubAdmin").val()=="true"){
				$(".sc-type").append('<a href="javascript:;" id="'+$("#schoolId").val()+'" class="btn btn-mini btn-default">'+$("#schoolName").val()+'</a>&nbsp;&nbsp;');
				$(".sc-type").find("a").eq(0).trigger("click.btn.schools");
			}
			
		},
		loadModules: function(schoolId,func){
			var $this=this;
			var data={};
			if(schoolId){
				data.schoolId=schoolId;
			}else{
				return false;
			}
			$(".themes-list ul").find("li").remove();
			var n=y=0;
			$this.$ajax({
				url: rootPath+"/sysBody/load",
				data: data,
				success:function(jsonData){
					var sys_templete_count=0;
					var own = jsonData.own,
						sys = jsonData.system,
						data,n=y=0;
					//系统模板
					for(var x = 0; x < sys.length ; x++){
						data = sys[x];
						if(sys_templete_count<3 || sys_templete_count==6 || sys_templete_count==7){
							/*****这是系统模板******/
							if(data.templateName == "新电商模板"){
							var html='<li class="module sysmodule" id="'+data.id+'">'+
		                    '<div class="picture">'+
		                    '</div>'+
		                    '<div class="themes-title">'+
		                    '<h2 class="h6">模板名称：'+data.templateName+ (sys_templete_count == 6?'<i class="iconfont" style="color:#e22019;display: inline-block;height: 20px;font-size: 30px;vertical-align: middle;line-height: 22px;margin-left: 4px;">&#xe6eb;</i>':'')+'</h2><a href="javascript:;" target="_blank">查看示例</a>'+
		                    '</div>'+
		                    '<div class="themes-content">'+
		                    '</div>'+
		                    '<div class="themes-btns">'+
		                    '<a class="left '+ ((sys_templete_count != 6  && sys_templete_count != 7)?'use':'useIt')+'" href="javascript:;">应用</a>'+
		                    '<a class="right '+ ((sys_templete_count != 6 && sys_templete_count != 7)?'config':'fashion')+'" href="javascript:;" '+(sys_templete_count == 7 ?'newECFlag="newECFlag"':'')+'>设计模板</a></div>'+
		                    '</li>';
							$(html).appendTo($(".sysmodules").find(".themes-list ul"));
							$(".sysmodules").find(".themes-list ul").find("#"+data.id).find(".themes-content").html(captions[n]);
							$(".sysmodules").find(".themes-list ul").find("#"+data.id).find(".themes-title a").attr("href",rootPath + '/sysBody/indexExample/' + (++y));
							$(".sysmodules").find(".themes-list ul").find("#"+data.id).find(".picture").append('<img src="'+rootPath+'/images/perview_'+(++n)+'.png"/>');
							}
						}else{
							/*****这是系统模板******/
							if(data.templateName == "新电商模板"){
							var html='<li class="module sysmodule" id="'+data.id+'">'+
		                    '<div class="picture">'+
		                    '</div>'+
		                    '<div class="themes-title">'+
		                    '<h2 class="h6">模板名称：'+data.templateName+'</h2><a href="javascript:;" target="_blank">查看示例</a>'+
		                    '</div>'+
		                    '<div class="themes-content">'+
		                    '</div>'+
		                    '<div class="themes-btns">'+
		                    '<a class="left useIt" href="javascript:;">应用</a>'+
		                    '</div>'+
		                    '</li>';
							$(html).appendTo($(".sysmodules").find(".themes-list ul"));
							$(".sysmodules").find(".themes-list ul").find("#"+data.id).find(".themes-content").html(captions[n]);
							$(".sysmodules").find(".themes-list ul").find("#"+data.id).find(".themes-title a").attr("href",rootPath + '/sysBody/indexExample/' + (++y));
							$(".sysmodules").find(".themes-list ul").find("#"+data.id).find(".picture").append('<img src="'+rootPath+'/images/perview_'+(++n)+'.png"/>');
							}
						}
						
						sys_templete_count++;
					}
					//机构自己的模板
					for(var y=0; y < own.length; y++){
						data = own[y];
						var html='<li class="module mymodule" id="'+data.id+'" templateStatus="'+data.templateStatus+'">'+
	                    '<div class="picture">';
						if(data.templateStatus=="1"){
							html+='<div class="curr">当前使用</div>';
							 html+='</div>'+
			                    '<div class="themes-title">'+
			                    '<h2 class="h6">模板名称：'+data.templateName+'</h2><a href="javascript:;" class="preview">预览</a>'+
			                    '</div>'+
			                    '<div class="themes-btns">'+
			                    '<a class="right config" href="javascript:;">设计模板</a></div>'+
			                    '</li>';
						}else{
							 html+='</div>'+
			                    '<div class="themes-title">'+
			                    '<h2 class="h6">模板名称：'+data.templateName+'</h2><a href="javascript:;" class="preview">预览</a>'+
			                    '</div>'+
			                    '<div class="themes-btns">'+
			                    '<a class="left use" href="javascript:;">应用</a>'+
			                    '<a class="left del" href="javascript:;">删除</a>'+
			                    '<a class="right config" href="javascript:;">设计模板</a></div>'+
			                    '</li>';
						}
						$(html).appendTo($(".mymodules").find(".themes-list ul"));
//						$this.getPictures(data.id,'user',function(pics){
//							$(".mymodules").find(".themes-list ul").find("#"+data.id).find(".picture").append(pics);
//						});
						$this.getPictures(data.id,'user');
					}
					if(func){
						func();
					}
				},
				error: function(resp,msg,err){
					console.log(resp);
				}
			});
		},
		getPictures: function(id,type,callback){
//			var pics=["overview_0.png","overview_000.png","overview_1.png","overview_2.png","overview_3.png","overview_4.png","overview_5.png","overview_1.png","overview_4.png","overview_1.png","overview_00.png",];
			var pics=["overview_0.png","overview_000.png","b1.jpg","b2.jpg","b3.jpg","b4.jpg","b5.jpg","b6.jpg","b7.jpg","b8.jpg","overview_00.png"];
			if(!id){
				return;
			}
			this.$ajax({
				url: rootPath+"/companyIndexModelPrivatepage/findByTemplate/"+type+"/"+id+"/"+$(".sc-type").find("a.btn-success").attr("id"),
				success: function(list){
					var picture="<img src='"+rootPath+"/images/"+pics[0]+"' alt='banner'/><img src='"+rootPath+"/images/"+pics[1]+"' alt='banner' style='margin-top:-2px;'/>";
					for(var i=0;i<=3;i++){
						var data=list[i];
						if(data){
							picture+="<img src='"+rootPath+"/images/set-index/"+pics[data.modelId+1]+"' alt='"+data.modelId+"图'/>";
						}
					}
					picture+="<img src='"+rootPath+"/images/"+pics[10]+"' alt='footer'/>";
					$(".mymodules").find(".themes-list ul").find("#"+id).find(".picture").append(picture);
					if(callback){
						callback(picture)
					}
					
				}
			})
		},
		add: function(){
			$(".addModule").on("click.btn.add",function(){
				if($(".sc-type").find("a").length){
					$(document).find(".layer-tips").remove();
					$('body').append('<div class="layer-tips add_module_tip" style="display: none;">'+
						    '<div class="layer-tips-title">输入模板名称 <i class="close iconfont"></i></div>'+
						    '<div class="layer-tips-content">'+
						    '    <p class="text-center"><input type="text" placeholder="输入模板名称"></p>'+
						    '    <p class="text-center tips">模板名称不能超过10个汉字！</p>'+
						    '</div>'+
						    '<div class="layer-tips-btns">'+
						    '    <a href="javascript:;" class="btn btn-mini btn-success">确定</a>'+
						    '    <a href="javascript:;" class="btn btn-mini btn-default btn-cancel">取消</a>'+
						    '</div>'+
						'</div>'+
						'<div class="layer-tips-bg" style="display: none;"></div>');
					$('.layer-tips-bg').fadeIn(200,function(){
	                    $('.layer-tips').fadeIn(200);
	                })
				}else{
					$.msg("没有可用的分校");
				}
					
				
			})
			$(document).on("click.btn.add",".add_module_tip .btn-success",function(){
				var _this=$(this);
				var _name = _this.parents(".layer-tips").find(":text").val();
				if(_name.length > 10){
					$.msg('名称不能超过10个字');
					return;
				}
				$.ajax({
					url: rootPath+"/sysBody/check",
					type: "post",
					data: {
						schoolId: $(".sc-type").find("a.btn-success").attr("id"),
						templateName: _this.parents(".layer-tips").find(":text").val()
					},
					success: function(data){
						if(data){
							$(".sc-add_module_tip").find("tips").html('模板已存在，不能重复命名');
							return;
						}else{
							$.ajax({
								url: rootPath+"/sysBody/addModule",
								type: "post",
								data: {
									schoolId: $(".sc-type").find("a.btn-success").attr("id"),
									templateName: _this.parents(".layer-tips").find(":text").val()
								},
								success: function(data){
									if(data){
										var form='<form action="'+rootPath+'/sysBody/newModule" method="post">'+
											'<input type="hidden" id="id" name="id" value="'+data.id+'"/>'+
											'<input type="hidden" id="templateName" name="templateName" value="'+data.templateName+'"/>'+
											'<input type="hidden" id="schoolId" name="schoolId" value="'+data.schoolId+'"/>'+
											'<input type="submit" style="display:none" />'+
											'</form>';
										$(form).appendTo('body').submit();
									}
								},
								error: function(){
									
								}
							})
						}
					},
					error: function(){
						$.msg("没添加上，再试试~");
					}
				})
			}).on("click.btn.cancle",".layer-tips .btn-cancel,.layer-tips-title .close",function(){
				$('.layer-tips').fadeOut(200,function(){
                    $('.layer-tips-bg').fadeOut(200);
                })
			})
		},
		preview: function(){
			$(document).on("click.link.preview",".preview",function(){
				window.open("http://"+document.domain+(location.port=="80"?"":":"+location.port)+rootPath+"/sysBody/preview/"+$(this).parents(".mymodule").attr("id"));
			})
			
		},
		config : function(){
			var $this=this;
			$(document).on("click.btn.config",".config",function(){
				if($(this).parents(".module").hasClass("sysmodule")){
					$this.intoMine($(this),function(data){
						var form='<form action="'+rootPath+'/sysBody/editModule" method="post"><input type="hidden" id="id" name="id" value="'+data.id+'"/><input type="hidden" id="schoolId" name="schoolId" value="'+schoolId+'"/></form>';
						$(form).appendTo('body').submit();
					})
				}else{
					var form='<form action="'+rootPath+'/sysBody/editModule" method="post"><input type="hidden" id="id" name="id" value="'+$(this).parents("li.mymodule").attr("id")+'"/></form>';
					$(form).appendTo('body').submit();
				}

			}).on('click','.fashion',function(){
				var schoolId = $('.single .sc-type').find('.btn-success').attr('id');
				var ecFlag = $(this).attr("newECFlag");
				var form;
				if(ecFlag == "newECFlag"){
					form ='<form action="'+rootPath+'/sysConfigIndexPageTemplate/indexPageConfig?newECFlag=newECFlag" method="post"><input type="hidden" id="schoolId" name="schoolId" value="'+ schoolId +'"/></form>';
				}else{
					form ='<form action="'+rootPath+'/sysConfigIndexPageTemplate/indexPageConfig" method="post"><input type="hidden" id="schoolId" name="schoolId" value="'+ schoolId +'"/></form>';
				}
				
				$(form).appendTo('body').submit();
			});
		},
		del : function(){
			var $this=this;
			$(document).on("click.btn.del",".del",function(){
				var _this=$(this);
				$.confirm("确定删除？",function(e){
					if(e){
						$this.$ajax({
							url: rootPath+"/sysBody/delModule/"+_this.parents("li.mymodule").attr("id"),
							success:function(success){
								_this.parents("li.mymodule").animate({'height':'1px',top:'150px'},300,function(){
									_this.parents("li.mymodule").animate({'width':0,left:'100px'},100,function(){
										_this.parents("li.mymodule").remove();
									})
								})
							},
							error: function(){
								alert("哎呀呀，没有删除掉~");
							}
						})
						
					}
				})
			})
		},
		use: function(){
			var $this=this;
			$(document).on("click.btn.use",".use",function(){
				var _this=$(this);
				if(_this.parents(".module").hasClass("mymodule")){
					var id=_this.parents("li.mymodule").attr("id"),
					schoolId=$(".sc-type").find("a.btn-success").attr("id");
					if(!id || !schoolId){
						alert("世界上最悲剧的事莫过于你点了，却没有生效...");
						return false;
					}
					$this.$ajax({
						url: rootPath+"/sysBody/useTemplate",
						data:{
							id: id,
							schoolId: schoolId
						},
						success:function(){
							_this.find(".use").remove();
							_this.find(".curr").remove();
							_this.parents("li.mymodule").attr("class","use").find(".picture").append('<div class="curr">已启用</div>');
							_this.parents("li.mymodule").find(".themes-btns").find("a").eq(1).hide();
							$this.loadModules($(".sc-type").find("a.btn-success").attr("id"),$this.findUsedTheme);
							$.msg("已应用此模板，请检查已上传的轮播图是否适配此模板~");
						},
						error: function(resp,msg,err){
							console.log(resp);
							alert("世界上最悲剧的事莫过于你点了，却没有生效...");
							return false;
						}
					})
				}else{
					$this.$ajax({
						url: rootPath+"/sysBody/intoMine/"+_this.parents("li.sysmodule").attr("id")+"/"+$(".sc-type").find(".btn-success").attr("id"),
						success:function(json){
							var ele = _this.parents(".sysmodule").clone();
							ele.attr("id",json.id);
							ele.attr("templatestatus",json.templateStatus);
							ele.css("display","none");
							ele.removeClass("sysmodule").addClass("mymodule");
							ele.find(".mark .ctrl-btns").before('<p class="user-this"><a href="javascript:;" class="btn btn-mini btn-success use">使用此模板</a></p>');
							ele.find(".mark .ctrl-btns").append('<a href="javascript:;" class="btn btn-mini btn-success config">配置</a>');
							ele.find(".mark .ctrl-btns").append('<a href="javascript:;" class="btn btn-mini btn-success del">删除</a>');
							ele.find(".mark .ctrl-btns").find(".intoMine").remove();
							if($(".mymodules").find("li").length){
								$('body').scrollTo($(".mymodules").find("li:first"),300);
								$(".mymodules").find("li:first").before(ele);
							}else{
								$(".mymodules").find(".themes-list").find("ul").append(ele);
							}
							
							ele.fadeIn(2000);
							var id=ele.attr("id"),
							schoolId=$(".sc-type").find("a.btn-success").attr("id");
							if(!id || !schoolId){
								alert("世界上最悲剧的事莫过于你点了，却没有生效...");
								return false;
							}
							$this.$ajax({
								url: rootPath+"/sysBody/useTemplate",
								data:{
									id: id,
									schoolId: schoolId
								},
								success:function(){
									_this.find(".use").remove();
									_this.find(".curr").remove();
									_this.parents("li.mymodule").attr("class","use").find(".picture").append('<div class="curr">已启用</div>');
									_this.parents("li.mymodule").find(".themes-btns").find("a").eq(1).hide();
									$this.loadModules($(".sc-type").find("a.btn-success").attr("id"),$this.findUsedTheme);
									$.msg("已应用此模板，请检查已上传的轮播图是否适配此模板~");
								},
								error: function(resp,msg,err){
									console.log(resp);
									alert("世界上最悲剧的事莫过于你点了，却没有生效...");
									return false;
								}
							})
						},
						error: function(){
							alert("人生不如意十之八九，这次没成功，再试试");
						}
					})
				}
				
			})
			.on("click.btn.useIt",".useIt",function(){
				var themeId=$(this).parents(".module").attr("id"),
					schoolId=$(".sc-type").find("a.btn-success").attr("id");
				$this.$ajax({
					url: rootPath+"/sysBody/useTheme",
					data:{templeteId:themeId,schoolId:schoolId},
					success:function(jsonData){
						if(jsonData=="success"){
							$this.loadModules($(".sc-type").find("a.btn-success").attr("id"),$this.findUsedTheme);
							$.msg("已应用此模板，请检查已上传的轮播图是否适配此模板~");
						}
					},
					error: function(resp,msg,err){
						console.log(resp);
						alert("世界上最悲剧的事莫过于你点了，却没有生效...");
						return false;
					}
				})
			})
		},
		intoMine: function(ele,callback){
			var $this=this;
			$this.$ajax({
				url: rootPath+"/sysBody/intoMine/"+ele.parents("li.sysmodule").attr("id")+"/"+$(".sc-type").find(".btn-success").attr("id"),
				success:function(json){
					console.log(json);
					ele.attr("id",json.id);
					ele.attr("templatestatus",json.templateStatus);
					ele.css("display","none");
					ele.removeClass("sysmodule").addClass("mymodule");
					ele.find(".mark .ctrl-btns").before('<p class="user-this"><a href="javascript:;" class="btn btn-mini btn-success use">使用此模板</a></p>');
					ele.find(".mark .ctrl-btns").append('<a href="javascript:;" class="btn btn-mini btn-success config">配置</a>');
					ele.find(".mark .ctrl-btns").append('<a href="javascript:;" class="btn btn-mini btn-success del">删除</a>');
					ele.find(".mark .ctrl-btns").find(".intoMine").remove();
					if($(".mymodules").find("li").length){
						$('body').scrollTo($(".mymodules").find("li:first"),300);
						$(".mymodules").find("li:first").before(ele);
					}else{
						$(".mymodules").find(".themes-list").find("ul").append(ele);
					}
					if(callback){
						callback(json);
					}
					ele.fadeIn(2000);
				},
				error: function(){
					alert("人生不如意十之八九，这次没成功，再试试");
				}
			})
		},
		findUsedTheme:function(){
			var schoolId=$(".sc-type").find("a.btn-success").attr("id");
			$.ajax({
				url: rootPath+"/sysBody/usedTheme?schoolId="+schoolId,
				type:"post",
				dataType:"json",
				success:function(data){
					$(".sysmodules").find(".themes-list ul").find("#"+data.templateId).find(".picture").append('<div class="curr">当前使用</div>');
				}
			})
			
		},
		hover: function(){
			$(document).on("mouseover.div.mymodules",".mymodule",function(){
				var mark='<div class="mark">';
		               
				/*****公司模板******/
				if($(this).attr("templatestatus")=="1"){
					//使用中
					mark+= '<p class="ctrl-btns">';
					//mark+='<a href="javascript:;" class="btn btn-mini btn-success preview">预览</a>';
					mark+='<a href="javascript:;" class="btn btn-mini btn-success config">配置</a>';
					mark+='</p>'
				}else{
					//未使用
					mark+='<p class="user-this"><a href="javascript:;" class="btn btn-mini btn-success use">使用此模板</a></p>';
					mark+='<p class="ctrl-btns">';
					//mark+='<a href="javascript:;" class="btn btn-mini btn-success preview">预览</a>';
					mark+='<a href="javascript:;" class="btn btn-mini btn-success config">配置</a>';
					mark+='<a href="javascript:;" class="btn btn-mini btn-success del">删除</a>';
					mark+='</p>';
				}
				mark+='</div>';
				if($(this).find(".mark").length>0){
					$(this).find(".mark").show();
				}else{
					$(this).find(".picture").before(mark);
				}
				
			}).on("mouseout.div.mymodules",".mymodule",function(){
				$(this).find(".mark").hide();
			});
			$(document).on("mouseover.div.sysmodules",".sysmodule",function(){
				var mark='<div class="mark">'+
                '<p class="ctrl-btns">'+
				//'<a href="javascript:;" class="btn btn-mini btn-success preview">预览</a>'+
				'<a href="javascript:;" class="btn btn-mini btn-success intoMine">加入我的</a>'+
				'</p></div>';
				if($(this).find(".mark").length>0){
					$(this).find(".mark").show();
				}else{
					$(this).find(".picture").before(mark);
				}
			}).on("mouseout.div.sysmodules",".sysmodule",function(){
				$(this).find(".mark").hide();
			});
		},
		$ajax:function(config,success,error){
			var defaults={
				url:"",
				data:{},
				type:"post",
				dataType:"json",
				success: function(jsonData){
					if(success){
						success(jsonData);
					}
				},
				error: function(resp,msg,err){
					if(error){
						success(error);
					}
				}
			}
			var options=$.extend({},defaults,config);
			$.ajax({
				url: options.url,
				data: options.data,
				type: options.type,
				dataType : options.dataType,
				success: options.success,
				error : options.error
			})
		}
		
	}
	
	$(document).ready(function(){
		sysBody.init();
	})
	
})(jQuery)