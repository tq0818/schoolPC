//选择课程封面
function chooseOnePic (url,path){
	$("#chooseDiv").css("display","none");
	$("#stopDiv").css("display","none");
	$("#set-body").find(".div6").find(".img-perview.active").parents(".model").find("._img").attr("src", url);
	$("#set-body").find(".div6").find(".img-perview.active").attr({"src":url,"realPath":path}).removeClass("active").fadeIn(200);
}

(function($){
	var $ajax=function(config,success,error){
		var defaults={
				url: "",
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
				url: rootPath+options.url,
				data: options.data,
				type: options.type,
				dataType : options.dataType,
				success: options.success,
				error : options.error
			});
	}
	
	var addSysBody={
		init: function(){
			$('body').addClass('homeBox');
			this.bind();
			this.load();
		},
		load: function(){
			if(!$("#templateId").val()){
				return ;
			}
			$(".templatename").html($("#templateName").val());
			var $this=this;
			$ajax({
				url: "/sysBody/loadPage/"+$("#schoolId").val()+"/"+$("#templateId").val(),
				success: function(jsonData){
					$.each(jsonData,function(i,data){
						var init=eval('$this.init'+data.modelId);
						init.call($this,data);
					});
				}
			});
		},
		//单图课程
		init1: function(data){
			var $this=this;
			$("#set-body").append($divs[0].text);
			var $model=$("#set-body").find(".model:last");
			data.type=$divs[0].type;
			$model.data("model",data);
			$model.attr("id",data.id);
			$model.attr("configId",data.configId);
			$model.attr("modelId",data.modelId);
			$model.attr("sort",data.sort);
			//类型
			$model.attr("type",$divs[0].type);
			//初始化名称
			$model.find(".name").find(":text").val(data.classtype.title);
			//初始化学科
			$model.find(".itemOne").find("a").remove();
			
			data.classtype.type=data.type;
			try{
				ModelUtil.fillData($model.index(),$model,data.classtype);
			}catch(e){
				console.log(e)
			}
			$ajax({
				url: "/sysConfigItem/getJsons?itemType=1",
				success:function(itemOnes){
					$.each(itemOnes,function(index,value){
						$model.find("p.itemOne").append('<a href="javascript:;" id="'+value.id+'" class="btn btn-mini btn-default btn-pro">'+value.itemName+'</a>')
					});
					if(data.classtype.classTypeOne){
						//查询班型
						$ajax({
							url: "/classType/findClassType/"+data.classtype.classTypeOne,
							success: function(sysitem){
								//选中对应的学科
								if(sysitem.itemOneId){
									$model.find("p.itemOne").find("a[id='"+sysitem.itemOneId+"']").removeClass("btn-default").addClass("btn-primary");
								}
								//加载学科小类
								$model.find("p.itemSecond").find("a").remove();
								$ajax({
									url: "/sysConfigItem/getJsons?itemType=2&parentId="+sysitem.itemOneId,
									success:function(items){
										$.each(items,function(i,item){
											$model.find("p.itemSecond").append('<a href="javascript:;" id="'+item.id+'" class="btn btn-mini btn-default btn-pro">'+item.itemName+'</a>')
										});
										//选中对应的学科小类
										if(sysitem.itemSecondId){
											$model.find("p.itemSecond").find("a[id='"+sysitem.itemSecondId+"']").removeClass("btn-default").addClass("btn-primary");
										}
									},
									error: function(){
										console.log("加载学科小类失败");
									}
								});
								//查询学科小类下对应的班型
								$model.find("p.classType").find("a").remove();
								$ajax({
									url: "/classType/findByItem/"+sysitem.itemOneId+"/"+sysitem.itemSecondId,
									success:function(items){
										$.each(items,function(i,item){
											$model.find("p.classType").append('<a href="javascript:;" id="'+item.id+'" class="btn btn-mini btn-default btn-pro">'+item.name+'</a>')
										});
										//选中对应的学科小类
										if(sysitem.id){
											$model.find("p.classType").find("a[id='"+sysitem.id+"']").removeClass("btn-default").addClass("btn-primary");
										}
									},
									error: function(){
										console.log("加载学科小类失败");
									}
								})
							},
							error: function(resp,msg,err){
								console.log("加载课程失败:",resp);
							}
						});
						
					}
				}
			});
			
		},
		//双图课程
		init2: function(data){
			var $this=this;
			$("#set-body").append($divs[1].text);
			var $model=$("#set-body").find(".model:last");
			data.type=$divs[1].type;
			$model.data("model",data);
			$model.attr("id",data.id);
			$model.attr("configId",data.configId);
			$model.attr("modelId",data.modelId);
			$model.attr("sort",data.sort);
			//类型
			$model.attr("type",$divs[1].type);
			//初始化名称
			$model.find(".name").find(":text").val(data.classtype.title);
			data.classtype.type=data.type;
			try{
				ModelUtil.fillData($model.index(),$model,data.classtype);
			}catch(e){
				console.log(e)
			}
			
			$ajax({
				url: "/sysConfigItem/getJsons?itemType=1",
				success:function(itemOnes){
					$.each(itemOnes,function(index,value){
						$model.find("p.itemOne").append('<a href="javascript:;" id="'+value.id+'" class="btn btn-mini btn-default btn-pro">'+value.itemName+'</a>')
					});
					if(data.classtype.classTypeOne){
						//查询班型
						$ajax({
							url: "/classType/findClassType/"+data.classtype.classTypeOne,
							success: function(sysitem){
								//选中对应的学科
								if(sysitem.itemOneId){
									$model.find("p.itemOne").find("a[id='"+sysitem.itemOneId+"']").removeClass("btn-default").addClass("btn-primary");
								}
								//加载学科小类
								$model.find("p.itemSecond").find("a").remove();
								$ajax({
									url: "/sysConfigItem/getJsons?itemType=2&parentId="+sysitem.itemOneId,
									success:function(items){
										$.each(items,function(i,item){
											$model.find("p.itemSecond").append('<a href="javascript:;" id="'+item.id+'" class="btn btn-mini btn-default btn-pro">'+item.itemName+'</a>')
										});
										//选中对应的学科小类
										if(sysitem.itemSecondId){
											$model.find("p.itemSecond").find("a[id='"+sysitem.itemSecondId+"']").removeClass("btn-default").addClass("btn-primary");
										}
									},
									error: function(){
										console.log("加载学科小类失败");
									}
								});
								//查询学科小类下对应的班型
								$model.find("p.classType").find("a").remove();
								$ajax({
									url: "/classType/findByItem/"+sysitem.itemOneId+"/"+sysitem.itemSecondId,
									success:function(items){
										$.each(items,function(i,item){
											$model.find("p.classType").append('<a href="javascript:;" id="'+item.id+'" class="btn btn-mini btn-default btn-pro">'+item.name+'</a>')
										});
										//选中对应的学科小类
										$model.find("p.classType").find("a[id='"+data.classtype.classTypeOne+"']").removeClass("btn-default").addClass("btn-primary");
										$model.find("p.classType").find("a[id='"+data.classtype.classTypeTwo+"']").removeClass("btn-default").addClass("btn-primary");
									},
									error: function(){
										console.log("加载学科小类失败");
									}
								})
							},
							error: function(resp,msg,err){
								console.log("加载课程失败:",resp);
							}
						});
						
					}
				}
			});
		},
		//三图课程
		init3: function(data){
			$("#set-body").append($divs[2].text);
			var $model=$("#set-body").find(".model:last");
			data.type=$divs[2].type;
			$model.data("model",data);
			$model.attr("id",data.id);
			$model.attr("configId",data.configId);
			$model.attr("modelId",data.modelId);
			$model.attr("sort",data.sort);
			//类型
			$model.attr("type",$divs[2].type);
			//初始化名称
			$model.find(".name").find(":text").val(data.item.title);
			data.item.type=data.type;
			try{
				ModelUtil.fillData($model.index(),$model,data.item);
			}catch(e){
				console.log(e)
			}
			$ajax({
				url: "/sysConfigItem/getJsons?itemType=1",
				success:function(itemOnes){
					$.each(itemOnes,function(index,value){
						$model.find("p.itemOne").append('<a href="javascript:;" id="'+value.id+'" class="btn btn-mini btn-default btn-pro">'+value.itemName+'</a>')
					});
					//选中对应的学科
					if(data.item.itemOneId){
						$model.find("p.itemOne").find("a[id='"+data.item.itemOneId+"']").removeClass("btn-default").addClass("btn-primary");
					}
				}
			});
			if(data.item.sortBy){
				$model.find(".sortBy").find("."+data.item.sortBy).addClass("open").find("i").html('&#xe60b;');
			}
			if(data.item.recommendFlag){
				$model.find(".sortBy").find(".radio").addClass("open").find("i").html('&#xe60a');
			}else{
				$model.find(".sortBy").find(".radio").removeClass("open").find("i").html('&#xe609');;
			}
			if(data.item.showItemSecond){
				$model.find(".showItem").find(".switch").addClass("open").find("i").html('&#xe603;').next().html('显示');
			}else{
				$model.find(".showItem").find(".switch").removeClass("open").find("i").html('&#xe604;').next().html('隐藏');;
			}
			//初始化授课类型按钮
			if(data.item.teachType){
				$model.find(".teachType").find("a."+data.item.teachType).removeClass("btn-default").addClass("btn-primary");
			}
			
		},
		//四图课程
		init4: function(data){
			$("#set-body").append($divs[3].text);
			var $model=$("#set-body").find(".model:last");
			data.type=$divs[3].type;
			$model.data("model",data);
			$model.attr("id",data.id);
			$model.attr("configId",data.configId);
			$model.attr("modelId",data.modelId);
			$model.attr("sort",data.sort);
			//类型
			$model.attr("type",$divs[3].type);
			//初始化名称
			$model.find(".name").find(":text").val(data.item.title);
			data.item.type=data.type;
			try{
				ModelUtil.fillData($model.index(),$model,data.item);
			}catch(e){
				console.log(e)
			}
			$ajax({
				url: "/sysConfigItem/getJsons?itemType=1",
				success:function(itemOnes){
					$.each(itemOnes,function(index,value){
						$model.find("p.itemOne").append('<a href="javascript:;" id="'+value.id+'" class="btn btn-mini btn-default btn-pro">'+value.itemName+'</a>')
					});
					//选中对应的学科
					if(data.item.itemOneId){
						$model.find("p.itemOne").find("a[id='"+data.item.itemOneId+"']").removeClass("btn-default").addClass("btn-primary");
					}
				}
			});
			if(data.item.sortBy){
				$model.find(".sortBy").find("."+data.item.sortBy).addClass("open").find("i").html('&#xe60b;');
			}
			if(data.item.recommendFlag){
				$model.find(".sortBy").find(".radio").addClass("open").find("i").html('&#xe60a');
			}else{
				$model.find(".sortBy").find(".radio").removeClass("open").find("i").html('&#xe609');
			}
			if(data.item.showItemSecond){
				$model.find(".showItem").find(".switch").addClass("open").find("i").html('&#xe603;').next().html('显示');
			}else{
				$model.find(".showItem").find(".switch").removeClass("open").find("i").html('&#xe604;').next().html('隐藏');;
			}
			//初始化授课类型按钮
			if(data.item.teachType){
				$model.find(".teachType").find("a."+data.item.teachType).removeClass("btn-default").addClass("btn-primary");
			}
			
		},
		//五图课程
		init5: function(data){
			$("#set-body").append($divs[4].text);
			var $model=$("#set-body").find(".model:last");
			data.type=$divs[4].type;
			$model.data("model",data);
			$model.attr("id",data.id);
			$model.attr("configId",data.configId);
			$model.attr("modelId",data.modelId);
			$model.attr("sort",data.sort);
			//类型
			$model.attr("type",$divs[4].type);
			//初始化名称
			$model.find(".name").find(":text").val(data.item.title);
			data.item.type=data.type;
			try{
				ModelUtil.fillData($model.index(),$model,data.item);
			}catch(e){
				console.log(e)
			}
			$ajax({
				url: "/sysConfigItem/getJsons?itemType=1",
				success:function(itemOnes){
					$.each(itemOnes,function(index,value){
						$model.find("p.itemOne").append('<a href="javascript:;" id="'+value.id+'" class="btn btn-mini btn-default btn-pro">'+value.itemName+'</a>')
					});
					//选中对应的学科
					if(data.item.itemOneId){
						$model.find("p.itemOne").find("a[id='"+data.item.itemOneId+"']").removeClass("btn-default").addClass("btn-primary");
					}
				}
			});
			if(data.item.sortBy){
				$model.find(".sortBy").find("."+data.item.sortBy).addClass("open").find("i").html('&#xe60b;');
			}
			if(data.item.showItemSecond){
				$model.find(".showItem").find(".switch").addClass("open").find("i").html('&#xe603;').next().html('显示');
			}else{
				$model.find(".showItem").find(".switch").removeClass("open").find("i").html('&#xe604;').next().html('隐藏');;
			}
			if(data.item.recommendFlag){
				$model.find(".sortBy").find(".radio").addClass("open").find("i").html('&#xe60a');
			}else{
				$model.find(".sortBy").find(".radio").removeClass("open").find("i").html('&#xe609');;
			}
			//初始化授课类型按钮
			if(data.item.teachType){
				$model.find(".teachType").find("a."+data.item.teachType).removeClass("btn-default").addClass("btn-primary");
			}
		},
		//八图课程
		init7: function(data){
			$("#set-body").append($divs[6].text);
			var $model=$("#set-body").find(".model:last");
			data.type=$divs[6].type;
			$model.data("model",data);
			$model.attr("id",data.id);
			$model.attr("configId",data.configId);
			$model.attr("modelId",data.modelId);
			$model.attr("sort",data.sort);
			//类型
			$model.attr("type",$divs[6].type);
			//初始化名称
			$model.find(".name").find(":text").val(data.item.title);
			$ajax({
				url: "/sysConfigItem/getJsons?itemType=1",
				success:function(itemOnes){
					$.each(itemOnes,function(index,value){
						$model.find("p.itemOne").append('<a href="javascript:;" id="'+value.id+'" class="btn btn-mini btn-default btn-pro">'+value.itemName+'</a>')
					});
					//选中对应的学科
					if(data.item.itemOneId){
						$model.find("p.itemOne").find("a[id='"+data.item.itemOneId+"']").removeClass("btn-default").addClass("btn-primary");
					}
				}
			});
			data.item.type=data.type;
			try{
				ModelUtil.fillData($model.index(),$model,data.item);
			}catch(e){
				console.log(e)
			}
			if(data.item.sortBy){
				$model.find(".sortBy").find("."+data.item.sortBy).addClass("open").find("i").html('&#xe60b;');
			}
			if(data.item.recommendFlag){
				$model.find(".sortBy").find(".radio").addClass("open").find("i").html('&#xe60a');
			}else{
				$model.find(".sortBy").find(".radio").removeClass("open");
			}
			//初始化授课类型按钮
			if(data.item.teachType){
				$model.find(".teachType").find("a."+data.item.teachType).removeClass("btn-default").addClass("btn-primary");
			}
		},
		//新闻-列表
		init8: function(data){
			$("#set-body").append($divs[7].text);
			var $model=$("#set-body").find(".model:last");
			data.type=$divs[7].type;
			$model.data("model",data);
			$model.attr("id",data.id);
			$model.attr("configId",data.configId);
			$model.attr("modelId",data.modelId);
			$model.attr("sort",data.sort);
			//类型
			$model.attr("type",$divs[7].type);
			//初始化名称
			$model.find(".name").find(":text").val(data.news.title);
			data.news.type=data.type;
			//排序
			if(data.news.sortBy){
				$model.find(".sortBy").find("."+data.news.sortBy).addClass("open").find("i").html('&#xe60b;');
			}
			//推荐
			if(data.news.recommentFlag){
				$model.find(".sortBy").find(".radio").addClass("open").removeClass('close').find("i").html('&#xe60a');
			}else{
				$model.find(".sortBy").find(".radio").removeClass("open").addClass("close").find('i').html('&#xe609;');
			}
			try{
				ModelUtil.fillData($model.index(),$model,data.news);
			}catch(e){
				console.log(e);
			}
		},
		//自定义
		init6: function(data){
			$("#set-body").append($divs[5].text);
//			var $model=$("#set-body").find(".model:last");
//			data.type=$divs[5].type;
//			$model.data("model",data);
//			$model.attr("id",data.id);
//			$model.attr("configId",data.configId);
//			$model.attr("modelId",data.modelId);
//			$model.attr("sort",data.sort);
//			//类型
//			$model.attr("type",$divs[5].type);
//			//初始化名称
//			$model.find(".name").find(":text").val(data.custom.title);
//			data.custom.type=data.type;
//			ModelUtil.fillData($model.index(),$model,data.custom);
//			if(data.custom.description){
//				$model.find(".text").find("textarea").val(data.custom.description);
//			}
//			if(data.custom.picture){
//				$model.find(".pic").find("img").attr("src",$.imageserver+data.custom.picture).show();
//				$model.find(".pic").find(".btn-up").html("更换图片");
//			}
//			if(data.custom.link){
//				$model.find(".url").find(":text").val(data.custom.link);
//			}
//			$model.find(".box-config").find(".up-btn").attr("id","file_"+$("#set-body").find(".model[type='"+$divs[7].type+"']").length);
//			this.queryConditionPics('privatePic');
			
//			$("#set-body").append('<div class="model div6" modelId="6" id="'+data.id+' sort='+data.sort+' type='+data.type+'"><div class="wrap contents single gap"><img class="cusImg img-perview" style="width:100%" src="http://img5.imgtn.bdimg.com/it/u=3637699519,1020801152&fm=21&gp=0.jpg"/></div>'+
//					'<div class="box-config none"><div class="title">配置信息</div><div class="main-config">'+
//				    '<p class="cusImg"><span class="pro-name">上传图片</span><span class="btns" style="position:relative;"><input type="file" name="imgData" class="cusfile u-f" accept="image/*" id="img_'+data.id+'"/><a class="btn btn-default  btn-mini c-uf" href="javascript:;">上传图片</a></span></p>'+
//				    '<p class="url"><span class="pro-name">链接地址</span><span>http://<input type="text" value=""/></span></p>'+
//				    '<p class="btns"><span class="pro-name"></span><a href="javascript:;" class="btn btn-primary">保存配置</a></p>'+
//				'</div>'+
//				'</div>'+
//				'</div>');
			var $model=$("#set-body").find(".model:last");
			$model.attr({'id':data.id,'sort':data.sort,'type':data.type,'configId':data.configId});
			$model.find('.cusImg').find(':file').attr('id','img_'+data.id);
			if(data.custom.picture && data.custom.picture!='null'){
				$model.find('.img-perview').attr('src',data.custom.picture).attr('realPath',data.custom.link);
			}
			if(data.custom.link){
				$model.find('.url').find(':text').val(data.custom.link.substring(7));
			}
			$model.find('.tongping').attr('name','title' + data.id);
			$model.find(":radio[value='"+data.custom.tile+"']").prop("checked",true);
			if(data.custom.tile==1){
				$model.find(":radio[value='"+data.custom.tile+"']").parent().next().html("注：设置为通屏时，建议上传图片宽度为1600px-1900px");
			}else{
				$model.find(":radio[value='"+data.custom.tile+"']").parent().next().html("注：设置为非通屏时，建议上传图片宽度为1200px");
			}
			
		},
		add : function(id){
			var $this=this;
			var privatePage={};
			privatePage.modelId=id;
			privatePage.schoolId=$("#schoolId").val();
			privatePage.sort=$("#set-body").find(".wrap").length;
			privatePage.type=$divs[id-1].type;
			privatePage.templateId=$("#templateId").val();
			$ajax({
				url: "/sysBody/addPage",
				data: privatePage,
				success:function(jsonData){
					$("#set-body").append($divs[id-1].text);
					$("#set-body").find(".model:last").attr("configId",jsonData.configId).attr("type",$divs[id-1].type).attr("id",jsonData.id);
					
					if($divs[id-1].type=="INDEX_CUSTOM"){
						$("#set-body").find(".model:last").find(".u-f").attr("id","img_"+jsonData.id);
						$("#set-body").find(".model:last").find('.tongping').attr('name','tile'+jsonData.id);
					}
					$('body').scrollTo($("#set-body").find(".model:last"),500);
					var $model=$("#set-body").find(".box-config:last");
					$model.data("model",jsonData);
					$ajax({
						url: "/sysConfigItem/getJsons?itemType=1",
						success:function(itemOnes){
							$.each(itemOnes,function(index,value){
								$model.find("p.itemOne").append('<a href="javascript:;" id="'+value.id+'" class="btn btn-mini btn-default btn-pro">'+value.itemName+'</a>')
							});
						}
					});
					if(id==8){
						$this.loadData($model.parent());
					}
				}
			});
		},
		//保存配置
		save: function(ele){
			var $this=this;
			var page={},item={},classType={},news={},custom={};
			page.id=ele.attr("id");
			page.type=ele.attr("type");
			page.schoolId=$("#schoolId").val();
			page.templateId=$("#templateId").val();
			page.sort=ele.index()+1;
			page.modelId=ele.attr("modelId");
			page.configId=ele.attr("configId");
			if(page.type=="INDEX_ITEM"){
				item.id=page.configId;
				item.title=ele.find(".name :text").val();
				
				item.itemOneId=ele.find(".itemOne a.btn-primary").attr("id");
				if(ele.find(".sortBy .time").hasClass("open")){
					item.sortBy='time';
					item.sort='desc';
				}else if(ele.find(".sortBy .price").hasClass("open")){
					item.sortBy='price';
					item.sort='asc';
				}else if(ele.find(".sortBy .num").hasClass("open")){
					item.sortBy='num';
					item.sort='desc';
				}else{
					item.sortBy=null;
					item.sort=null;
				}
				if(ele.find(".sortBy .recommend").hasClass("open")){
					item.recommendFlag=1;
				}else{
					item.recommendFlag=0;
				}
				if(ele.find(".showItem .switch").hasClass("open")){
					item.showItemSecond=1;
				}else{
					item.showItemSecond=0;
				}
				if(ele.find(".teachType").find("a.btn-primary").length){
					if(ele.find(".teachType").find("a.btn-primary").hasClass("live")){
						item.teachType="live";
					}
					if(ele.find(".teachType").find("a.btn-primary").hasClass("face")){
						item.teachType="face";
					}
					if(ele.find(".teachType").find("a.btn-primary").hasClass("video")){
						item.teachType="video";
					}
					if(ele.find(".teachType").find("a.btn-primary").hasClass("all")){
						item.teachType="all";
					}
				}
				page.items=JSON.stringify(item);
				if(!item.title){
					$.msg("标题不能空着哦~亲");
					return;
				}
			}
			if(page.type=="INDEX_CLASSTYPE"){
				classType.id=page.configId;
				classType.title=ele.find(".name :text").val();
				classType.classTypeOne=ele.find(".classType").find("a.btn-primary").eq(0).attr("id");
				classType.classTypeTwo=ele.find(".classType").find("a.btn-primary").eq(1).attr("id");
				classType.classTypeThree=ele.find(".classType").find("a.btn-primary").eq(2).attr("id");
				page.classType=JSON.stringify(classType);
				if(!classType.title){
					$.msg("标题不能空着哦~亲");
					return;
				}
				if(ele.hasClass("div2").length && ele.find(".classType").find("a.btn-primary").length<2){
					$.msg("需要选择两个课程");
					return;
				}
			}
			if(page.type=="INDEX_NEWS"){
				news.id=page.configId;
				news.title=ele.find(".name :text").val();
//				news.picture=ele.find(".");
				news.sortBy='time';
				news.sort='desc';
				if(ele.find(".sortBy .radio").hasClass("open")){
					news.recommentFlag=1;
				}else{
					news.recommentFlag=0;
				}
				page.news=JSON.stringify(news);
				if(!news.title){
					$.msg("标题不能空着哦~亲");
					return;
				}
			}
			if(page.type=="INDEX_CUSTOM"){
//				custom.id=page.configId;
//				custom.title=ele.find(".name :text").val();
//				custom.picture=ele.find(".img-perview").attr("realpath");
//				custom.description=ele.find("textarea").val();
//				custom.link=ele.find(".url").find(":text").val();
//				console.log(custom);
//				page.custom=JSON.stringify(custom);
//				if(!custom.title){
//					$.msg("标题不能空着哦~亲");
//					return;
//				}
				custom.id=page.configId;
				custom.picture=ele.find(".img-perview").attr("src");
				custom.link='http://'+ele.find(".url").find(":text").val();
				custom.tile=ele.find(":radio:checked").val();
				//改成数据库配置的字符串
//				if(custom.link){
//					page.divcode='<div class="wrap" style="cursor:pointer;margin-top:-1px;'+(custom.tile==1?"width:100%":"width:1200px;margin-bottom:20px;")+'" onclick="javascript:window.open(\''+custom.link+'\')"><img style="width:100%" src="'+custom.picture+'"/></div>';
//				}else{
//					page.divcode='<div class="wrap" style="'+(custom.tile==1?"width:100%;":"width:1200px;margin-bottom:20px;")+'margin-top:-1px;"><img style="width:100%" src="'+custom.picture+'"/></div>';
//				}
				
				page.custom=JSON.stringify(custom);
			}
			ele.data("model",page);
			$ajax({
				url: "/sysBody/editPage",
				data: page,
				success: function(jsonData){
					ele.find(".box-config").slideUp(300);
					$.msg("保存成功");
				}
			})
		},
		remove: function(ele){
			var $this=this;
			if(ele.attr("id")){
				$ajax({
					url: "/sysBody/delPage/"+ele.attr("id"),
					success: function(jsonData){
						ele.fadeOut(300,function(){
							$(this).remove();
						})
					}
				})
			}else{
				ele.fadeOut(300,function(){
					$(this).remove();
				})
			}
			
		},
		sort: function(ele,type){
			var $this=this;
			var models=new Array();
			var target;
			if(type=='next'){
				if(ele.index()<=$('#set-body').find(".model").length){
					models.push({'id':ele.attr("id"),'sort':ele.index()+1});
					models.push({'id':ele.next().attr("id"),'sort':ele.next().index()-1});	
				}else{
					return false;
				}
			}
			if(type=='prev'){
				if(ele.index()-1>=0){
					models.push({'id':ele.attr("id"),'sort':ele.index()-1});
					models.push({'id':ele.prev().attr("id"),'sort':ele.prev().index()+1});	
				}else{
					return false;
				}
			}
			if(type=='first'){
				models.push({'id':ele.attr("id"),"sort":0})
				for(var i=0;i<ele.index();i++){
					models.push({'id':$("#set-body").find(".model").eq(i).attr("id"),"sort":i+1});
				}
			}
			if(type=='last'){
				models.push({'id':ele.attr("id"),"sort":$("#set-body").find(".model").length-1});
				for(var i=ele.index()+1;i<$("#set-body").find(".model").length;i++){
					models.push({'id':$("#set-body").find(".model").eq(i).attr("id"),'sort':i-1});
				}
			}
			$ajax({
				url: "/sysBody/updatePage",
				data: {
					models : JSON.stringify(models)
				},
				success: function(jsonData){
					if(type=='next'){
						var html=ele.clone();
						html.attr("sort",html.index()+1);
						if(ele.next() && ele.next()[0]){
							ele.fadeOut(300,function(){
								html.addClass("none");
								ele.next().after(html);
								ele.remove();
								html.fadeIn(1000);
								$('body').scrollTo(html,500);
							});
						}
					}
					if(type=='prev'){
						var html=ele.clone();
						html.attr("sort",html.index()-1);
						if(ele.prev() && ele.prev()[0]){
							ele.fadeOut(300,function(){
								html.addClass("none");
								ele.prev().before(html);
								ele.remove();
								html.fadeIn(1000);
								$('body').scrollTo(html,500);
							});
						}
					}
					if(type=='first'){
						var html=ele.clone();
						html.attr("sort","0");
						ele.fadeOut(300,function(){
							ele.remove();
							html.addClass("none");
							$("#set-body").find(".model:first").before(html);
							html.fadeIn(1000);
							$('body').scrollTo(html,500);
						});
					}
					if(type=='last'){
						var html=ele.clone();
						html.attr("sort",$("#set-body").find(".model").length);
						ele.fadeOut(300,function(){
							ele.remove();
							html.addClass("none");
							$("#set-body").find(".model:last").after(html);
							html.fadeIn(1000);
							$('body').scrollTo(html,500);
						});
					}
					
				}
			})
		},
		bind : function() {
			var $this = this;
			var height = $('.sys-i').length?$('.sys-i').offset().top : 0;
			//模板名称滚动事件
			 $(window).on("scroll",function(){
		            var h = $(window).scrollTop();
		            if ( h > height){
		                $('.sys-i').addClass('fixed');
		            }else {
		                $('.sys-i').removeClass('fixed');
		            }
		        })
			// 绑定学科点击事件
			$("#set-body")
					.on("click.btn.item",".main-config .itemOne a",function() {
								$(this).parent().find("a").removeClass(
										"btn-primary").addClass("btn-default");
								$(this).removeClass("btn-default").addClass(
										"btn-primary");
								var ele = $(this).parents(".main-config").find(
										".itemSecond");
								ele.find("a").remove();
								if (ele && ele[0]) {
									ele.find(".second").remove();
									$ajax({
										url : "/sysConfigItem/getJsons?itemType=2&parentId="
												+ $(this).attr("id"),
										success : function(jsonData) {
											var items = '<span class="second">';
											$.each(jsonData,function(i, data) {
																items += '<a href="javascript:;" id="'
																		+ data.id
																		+ '" class="btn btn-mini btn-default btn-pro">'
																		+ data.itemName
																		+ '</a>';
															});
											items += '</span>';
											ele.append(items);
										}
									})
								}
								$this.loadData($(this).parents(".model"));
							})
					// 绑定学科小类点击事件
					.on("click.btn.item",".main-config .itemSecond a",function() {
						$(this).parent().find("a").removeClass("btn-primary").addClass("btn-default");
						$(this).removeClass("btn-default").addClass("btn-primary");
						var $model = $(this).parents(".main-config");
						var itemOneId = $(this).parents(".main-config").find(".itemOne").find(".btn-primary").attr("id");
						var itemSecondId = $(this).parents(".main-config").find(".itemSecond").find(".btn-primary").attr("id");
						//查询学科小类下对应的班型
						$model.find("p.classType").find("a").remove();
						$ajax({
							url: "/classType/findByItem/"+itemOneId+"/"+itemSecondId,
							success:function(items){
								$.each(items,function(i,item){
									$model.find("p.classType").append('<a href="javascript:;" id="'+item.id+'" class="btn btn-mini btn-default btn-pro">'+item.name+'</a>')
								});
								//选中对应的学科小类
								$model.find("p.classType").find("a[id='"+data.classtype.classTypeOne+"']").removeClass("btn-default").addClass("btn-primary");
								$model.find("p.classType").find("a[id='"+data.classtype.classTypeTwo+"']").removeClass("btn-default").addClass("btn-primary");
							},
							error: function(){
								console.log("加载学科小类失败");
							}
						})

							})
					//绑定鼠标悬浮事件
					.on('mouseenter.div.wrap','.wrap',function(){
						var mb='<div class="m" style="display: none;">'+
					    '<!-- 配置蒙板 -->'+
					    '<div class="config">'+
					        '<a href="javascript:;" class="btn btn-default btn-config">配置内容</a>'+
					        '<a href="javascript:;" class="btn btn-default btn-remove">删除该内容</a>'+
					    '</div>'+
					    '<div class="direction">'+
					        '<span class="top"><i class="iconfont"></i></span>'+
					        '<span class="up"><i class="iconfont"></i></span>'+
					        '<span class="down"><i class="iconfont"></i></span>'+
					        '<span class="bottom"><i class="iconfont"></i></span>'+
					    '</div>'+
					'</div>';
						if($(this).find(".m").length>0){
							$(this).find(".m").fadeIn(300);
						}else{
							$(this).append(mb);
							$(this).find(".m").fadeIn(300);
						}
						
					})
					.on('mouseleave.div.wrap','.wrap',function(){
						$(this).find(".m").fadeOut(300);
					})
					// 绑定配置按钮点击事件
					.on("click.btn.mb", "a.btn-config", function() {
						$(this).parents(".model").find(".box-config").slideToggle();
					})
					// 自定义-图片上传按钮
					.on("click.btn.upload",".up-pic .btn-up",function(){
						$(this).prev().addClass("active");
						$(".add-layer-bg").fadeIn(200,function(){
							 $('.upload-layer').fadeIn(200);
						})
					})
					//绑定删除按钮点击事件
					.on("click.btn.mb","a.btn-remove",function(){
						$this.remove($(this).parents(".model"));
					})
					.on("click.btn.sort",".top",function(){
						$this.sort($(this).parents(".model"),'first');
					})
					.on("click.btn.sort",".up",function(){
						$this.sort($(this).parents(".model"),'prev');
					})
					.on("click.btn.sort",".down",function(){
						$this.sort($(this).parents(".model"),'next');
					})
					.on("click.btn.sort",".bottom",function(){
						$this.sort($(this).parents(".model"),'last');
					})
					// 绑定班型点击事件
					.on("click.btn.classtype",".classType a",function() {
						if($(this).hasClass("btn-primary")){
							$(this).removeClass("btn-primary").addClass("btn-default");
						}else{
							if($(this).parents(".model").attr("modelId")=="1"){
								//1图
								$(this).parent().find("a").removeClass("btn-primary").addClass("btn-default");
								$(this).removeClass("btn-default").addClass("btn-primary");
							}
							if($(this).parents(".model").attr("modelId")=="2"){
								//2图
								if($(this).parents(".classType").find(".btn-primary").length>=2){
									$.msg("最多选2个");
									return false;
								}else{
									$(this).addClass("btn-primary").removeClass("btn-default");
								}
							}
							
						}
						// 查数据
						$this.loadData($(this).parents(".model"));
					})
					//标题
					.on("keyup.text.title",".in-name",function(){
						var _this=$(this);
						_this.parents(".model").find(".wrap .homeListTitle").find('h5').html("<i></i>"+_this.val());
					})
					//概述
					.on("keyup.text.caption","textarea",function(){
						var _this=$(this);
						_this.parents(".model").find("._overview").html(_this.val());
					})
					//图片
					.on("change.file.input",":file",function(){
						$this.savePic($(this).parents(".model"));
					})
					//链接
					.on("change.text.url","second :text",function(){
						//什么也不做
					})
					// 绑定保存按钮事件
					.on("click.btn.save", ".btns a", function() {
						$this.save($(this).parents(".model"));
					})
					// 绑定是否显示学科小类
					.on('click.btn.switch','span.switch',function() {
								var
								// 图标 03为滑块在左
								si = [ '&#xe603;', '&#xe604;' ],
								// 显示文字
								st = [ '显示', '隐藏' ],
								// 当前对象
								_this = $(this),
								// 是否为激活状态
								s = _this.hasClass('open');

								_this[s ? 'removeClass' : 'addClass']('open')[s ? 'addClass'
										: 'removeClass']('close').find(
										'i.iconfont').html(si[s ? 1 : 0]).next(
										'em').text(st[s ? 1 : 0]);
								if($(this).hasClass("close")){
									$(this).parents(".model").find(".homeListTitle ul").find('li').remove();
								}else{
									$this.loadData($(this).parents(".model"));
								}
								
							})
					//绑定单选按钮-排序
					.on('click','span.checkbox',function() {
								var
								// 按钮选中状态 0b为选中 0c为取消
								st = [ '&#xe60b;', '&#xe60c;' ],
								// 当前对象
								_this = $(this),
								// 是否激活
								s = _this.hasClass('open');

								_this[s ? 'removeClass' : 'addClass']('open')[s ? 'addClass'
										: 'removeClass']('close').find(
										'i.iconfont').html(st[s ? 1 : 0]).end()
										.siblings('.checkbox')['removeClass'](
										'open').find('i.iconfont').html(st[1]);
								//查数据
								$this.loadData($(this).parents(".model"));
							})
					// 推荐优先
					.on('click','span.radio',function() {
								var
								// 按钮选中状态 0a为选中 09为取消
								st = [ '&#xe60a;', '&#xe609;' ],
								// 当前对象
								_this = $(this),
								// 是否激活
								s = _this.hasClass('open');

								_this[s ? 'removeClass' : 'addClass']('open')[s ? 'addClass'
										: 'removeClass']('close').find(
										'i.iconfont').html(st[s ? 1 : 0]).end()
										.siblings('.checkbox')['removeClass'](
										'open').find('i.iconfont').html(st[1]);
								//查数据
								$this.loadData($(this).parents(".model"));
							})
					//绑定授课类型		
					.on('click','.teachType a',function(){
						$(this).siblings().removeClass("btn-primary").addClass("btn-default");
						$(this).addClass("btn-primary").removeClass("btn-default");
						$this.loadData($(this).parents(".model"));
					})
					.on('click','.tongping:radio',function(){
						if($(this).val()==1){
							$(this).parent().next().html("注：设置为通屏时，建议上传图片宽度为1600px-1900px");
						}else{
							$(this).parent().next().html("注：设置为非通屏时，建议上传图片宽度为1200px");
						}
					})
			$(".publish").on("click",function(){
				$this.publish();
			});	
			$(".goback").on("click",function(){
				$this.goback();
			});	
			// 绑定色块事件
			$(".select-themes")
					.on('mouseenter','[data-index]',function() {
								var
								// 当前对象
								_this = $(this),
								// 获得额外数据
								data = _this.attr('data-index'), 
								m = '<div class="b"><li><a class="btn btn-default btn-pro">加入到模板</a></li></div>',
								html = '<div class="lp-tips"><img src="'+rootPath+'/images/set-index/' + data +'.jpg"></div>',
								o = $(html).appendTo(this),
			                    oh = o.innerHeight()>140? o.innerHeight() : 140,
			                    ow = o.innerWidth(),
			                    ox = -100,
			                    oy = -(oh+20);
								// 将遮罩插入
								$(m).appendTo(this);

								_this.find('.b').fadeIn(200).addClass('xx');
								o.css({'left':ox,'top':oy,'visibility':'visible'});
							})
					// 鼠标离开
					.on('mouseleave', '[data-index]', function() {
						$(this).find('.lp-tips,.b').remove();
					}).on('click.btn.theme','.b',function() {
						var id = $(this).parent().attr("data-index").substring(1);
						$this.add(id);
					});
			// 绑定云图片系列事件
			$(".upload-layer")
			//一级TAB 公有云/私有云 点击事件
			.on("click.tab1.a",".types a",function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
				$this.queryConditionPics($(this).attr("marks"));
			})
			//二级TAB 学科/tag 点击事件
			.on("click.tab2.a",".types-list a",function(){
				$(this).siblings().removeClass("active");
				$(this).addClass("active");
				if($(this).parents(".upload-layer").find(".types .btn-success").attr("marks")=="privatePic"){
					//私有云
					$this.queryPirvatePics(1,$(this).attr("id"));
				}else{
					//公有云
					$this.queryPublicPics(1,$(this).attr("id"));
				}
			})
			//添加图片 
			.on("click.btn.add","",function(){
				
			})
			//选择图片
			.on("click.btn.choose",".up-pic .btn-up",function(){
				
			})
			.on("mouseenter.page", "#ulList li", function() {
				$(this).find(".mark").fadeIn(300);
			}).on("mouseleave.page", "#ulList li", function() {
				$(this).find(".mark").fadeOut(300);
			})
			.on("click.close","i.close",function(){
						 $('.upload-layer').fadeOut(200,function(){
			                  $('.add-layer-bg').fadeOut(200);
			              });
					})
			.on('click.cancle','.btn-cancel',function(){
	              $(this).parents('.pic-upload').fadeOut(200)
	          })
			 .on('click.add','li.add',function(){
	              $('.pic-upload').fadeIn(200,function(){
	            	  
	              })
	          })
	          .on("click.btn.ok",".btn-ok",function(){
	        	  $(this).parents('.pic-upload').fadeOut(200,function(){
	        		  $this.classTypePic();
	        	  })
	          })
	          .on("change",".pic #target", function() {
				var theImage = new Image();
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
		},
		//实时查询数据
		loadData: function(div){
			(function($div){
				$div.find(".wrap").find("._img").attr("src","").parent().html($div.find("._img")[0]);
				$div.find(".wrap").find("._price").html("");
				$div.find(".wrap").find("._people").html("");
				$div.find(".wrap").find("._caption").html("");
				$div.find(".wrap").find("._overview").html("");
				$div.find(".wrap").find("._time").remove("");
			})(div)
			var type,params={};
			if(div.hasClass('div1')){
				type=_types[0];
				params.cuslimit='1';
			}
			if(div.hasClass('div2')){
				type=_types[1];
				params.cuslimit='2';
			}
			if(div.hasClass('div3')){
				type=_types[2];
				params.cuslimit='3';
			}
			if(div.hasClass('div4')){
				type=_types[3];
				params.cuslimit='4';
			}
			if(div.hasClass('div5')){
				type=_types[4];
				params.cuslimit='5';
			}
			if(div.hasClass('div6')){
				type=_types[5];
				params.cuslimit='1';
			}
			if(div.hasClass('div7')){
				type=_types[6];
				params.cuslimit='8';
			}
			if(div.hasClass('div8')){
				type=_types[7];
				params.cuslimit='30';
				if(div.find('.recommend').hasClass('open')){
					params.cusorder = 'recommend_flag desc';
				}
			}
			if(div.find(".name").find(":text")[0] && div.find(".name").find(":text").val()){
				params.title=div.find(".name").find(":text").val();
			}
			if(div.find(".itemOne")[0] && div.find(".itemOne").find(".btn-primary").attr("id")){
				params.itemOneId=div.find(".itemOne").find(".btn-primary").attr("id");
			}
			if(div.find(".itemSecond")[0] && div.find(".itemSecond").find("a.btn-primary").attr("id")){
				params.itemSecondId=div.find(".itemSecond").find(".btn-primary").attr("id");
			}
			if(div.find(".classType")[0] && div.find(".classType").find("a.btn-primary")[0] && div.find(".classType").find("a.btn-primary").attr("id")){
				params.classTypeOne=div.find(".classType").find(".btn-primary").eq(0).attr("id");
			}else{
				params.classTypeOne="";
			}
			if(div.find(".classType")[0] && div.find(".classType").find("a.btn-primary")[1] && div.find(".classType").find("a.btn-primary").attr("id")){
				params.classTypeTwo=div.find(".classType").find(".btn-primary").eq(1).attr("id");
			}else{
				params.classTypeTwo="";
			}
			
			if(div.find(".teachType").length && div.find(".teachType").find("a.btn-primary").length){
				if(div.find(".teachType").find("a.btn-primary").hasClass("live")){
					params.liveFlag="1"
				}
				if(div.find(".teachType").find("a.btn-primary").hasClass("video")){
					params.videoFlag="1"
				}
				if(div.find(".teachType").find("a.btn-primary").hasClass("face")){
					params.faceFlag="1"
				}
			}
			
			if(div.find(".showItem")[0]){
				if(div.find(".showItem").find("span.open").length){
					params.showItemSecond=1;
				}else{
					params.showItemSecond=0;
				}
			}
			if(div.find(".sortBy")[0]){
				if(div.find(".sortBy").find("span.num.open")[0]){
					params.cusorder="actualNum desc";
				}
				if(div.find(".sortBy").find("span.time.open")[0]){
					params.cusorder="update_time desc";
				}
				if(div.find(".sortBy").find("span.price.open")[0]){
					params.cusorder="real_price asc";
				}
				if(div.find(".recommend.open")[0]){
					if(params.cusorder){
						params.cusorder="recommend_flag desc,"+params.cusorder;
					}else{
						params.cusorder="recommend_flag desc";
					}
				}
			}
			if(div.find(".text")[0] && div.find(".text").find("textarea")[0]){
				params.description=div.find(".text").find("textarea").val();
			}
			if(div.find(".pic")[0]){
				params.picture=div.find(".pic").find("img").attr("src");
			}
			if(div.find(".url")[0]){
				params.url=div.find(".url").find(":text").val();
			}
			if(type=="INDEX_CLASSTYPE"){
				params.schoolId=$("#schoolId").val();
				$.ajax({
					url: rootPath+"/commodity/loadData2",
					data: params,
					type: "post",
					dataType : "json",
					async : false,
					success: function(jsonData){
						div.find("._item").each(function(i){
							var data=jsonData[i];
							if(data){
//								$(this).find("._img").attr("src",(data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg"))
//								$(this).find("._price").html("¥"+data.realPrice);
//								$(this).find("._people").html("<i></i>"+data.actualNum);
//								$(this).find("._caption").html(data.name);
//								$(this).find("._overview").html(data.overview);
								$(this).show();
								data.coverUrl = data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg";
								$(this).find("._img").attr("src",data.coverUrl).on("click",function(){ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id)}).on("mouseover",function(){$(this).css("cursor","pointer");});
								$(this).find(".homeMoreBtn").on("click",function(){ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id)});
								$(this).show();
								if(data.overview && data.overview.length>60){
									data.overview = data.overview.substring(0,60)+"...";
								}
								//单图课程
								if(div.find('._item').length == 1){
									$(this).find('.textTitle').html(data.name);
									if(!data.realPrice){
										$(this).find('.t1_Price').html('<span class="free">免费</span>');
									}else{
										$(this).find('.t1_Price').find('em').html('￥'+data.realPrice);
										$(this).find('.t1_Price').find('del').html('￥'+data.originalPrice);
									}
									$(this).find('.t1_Con').html(data.overview);
									if(!(data.baseNum + data.actualNum)){
										$(this).find('div.clear').find('h4').remove();
										$(this).find('div.clear').find('p').remove();
									}else{
										$(this).find('.userTitle1').html('').randomImg({num:(data.baseNum + data.actualNum)});
									}
								}
								//两图模板
								if(div.find('._item').length == 2){
									$(this).find('.picContent').find('h4').html(data.name);
									$(this).find('.picContent').find('.picDet').html(data.overview);
									if(!data.realPrice){
										$(this).find('.picContent').find('p.clear').find('span.fl').addClass('.deFree').html('免费');
									}else{
										$(this).find('.picContent').find('em').html('￥'+data.realPrice);
										$(this).find('.picContent').find('del').html('￥'+data.originalPrice);
									}
									if(!(data.baseNum + data.actualNum)){
										$(this).find('.picContent').find('p.clear').find('span.fr').remove();
									}else{
										$(this).find('.userTitle2').siblings('b').html('最新加入学习');
										$(this).find('.userTitle2').randomImg({num:3});
									}
									//更多链接
									div.find('.homeListTitle .homeMoreBtn').attr('href',rootPath + '/sysConfigItem/getitemTwo/' + data.itemOneId + '/' + data.itemSecondId);
								}

							}else{
								$(this).hide();
							}
						});
					},
					error : function(resp,err,msg){
						console.log(resp);
					}
				});
			}
			if(type=="INDEX_NEWS"){
				params.schoolId=$("#schoolId").val();
				$.ajax({
					url: rootPath+"/sysNews/loadData",
					data: params,
					type: "post",
					dataType : "json",
					async : false,
					success: function(jsonData){
//						if(div.find(".list").length>0){
//							div.find("._item").each(function(i){
//								var data=jsonData[i];
//								if(data){
//									$(this).html('<i></i><a href="'+rootPath+"/sysNews/queryNewsById/"+data.id+'" value="'+data.id+'">'+data.summary+'</a></li>');
//								}
//							})
//						}else{
//							div.find("._item").each(function(i){
//								var data=jsonData[i];
//								if(data){
//									$(this).find("._img").attr("src",$.imageserver+data.newsPic);
//									$(this).find("._overview").html(data.newsTitle);
//									$(this).find(".btn").attr("id",data.id).attr("href",rootPath+"/sysNews/queryNewsById/"+data.id);
//								}
//							});
//						}
						
						var news;
						div.find('._ulitem,._olitem').each(function(i){
							news = jsonData[i];
							if(news){
								if($(this).hasClass('_ulitem')){
									$(this).find('.borderCon img').attr('src',news.newsPic?$.imageserver+news.newsPic:rootPath+'/images/no-image.png');
									$(this).find('.inforList').find('h3').html(news.newsTitle.length>18?news.newsTitle.substring(0,18)+'...':news.newsTitle);
									$(this).find('.inforList').find('p').html(news.summary&&news.summary.length>40?news.summary.substring(0,40)+'...':news.summary);
									$(this).find('.inforList').find('time').html(news.updateTime);
								}
								if($(this).hasClass('_olitem')){
									$(this).find('h3').html(news.newsTitle.length>23?news.newsTitle.substring(0,23)+'...':news.newsTitle);
									$(this).find('p').eq(0).html(news.summary&&news.summary.length>40?news.summary.substring(0,40)+'...':news.summary);
									$(this).find('p.lastp').find('time').html(news.updateTime);
									$(this).find('p.lastp span').eq(0).find('small').html(news.clickCount?news.clickCount:0);
									$(this).find('p.lastp span').eq(1).find('small').html(news.newsTypeName);
								}
								$(this).show();
							}else{
								$(this).hide();
							}
						});
						
						
					},
					error : function(){
						
					}
				});
			}
			if(type=="INDEX_CUSTOM"){
				div.find("._body").find("._item").each(function(i){
					$(this).find("._img").attr("src",$.imageserver+params.picture);
					$(this).find("._overview").html(params.description);
					$(this).find("._more").attr("href",params.link);
					$(this).find(".img-perview").attr("src",$.imageserver+params.picture);
				})
			}
			if(type=="INDEX_ITEM"){
				params.schoolId=$("#schoolId").val();
//				$.ajax({
//					url: rootPath+"/commodity/loadData",
//					data: params,
//					type: "post",
//					dataType : "json",
//					async : false,
//					success: function(jsonData){
//						var div_num=div.find("._item").length;//几图
//						div.find("._item").each(function(i){
//							var data=jsonData[i];
//							if(data){
////								$(this).find("._img").attr("src",(data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg"));
////								$(this).find("._price").html("¥"+data.realPrice);
////								$(this).find("._people").html("<i></i>"+data.actualNum);
////								$(this).find("._caption").html(data.name);
////								$(this).find("._overview").html(data.overview);
//								$(this).show();
//								data.coverUrl = data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg";
//								$(this).find("._img").attr("src",data.coverUrl).on("click",function(){ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id)}).on("mouseover",function(){$(this).css("cursor","pointer");});
//								$(this).find("._more").on("click",function(){ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id)});
//								if(div_num == 3){
//									data.name = data.name.length>20?data.name.substring(0,20)+"...":data.name;
//									if(data.overview && data.overview.length>60){
//										data.overview = data.overview.substring(0,60)+"...";
//									}
//									$(this).find('.pic').find('img').attr('src',data.coverUrl);
//									$(this).find('.infos').find('h2').find('a').html(data.name).attr('href',rootPath+"/sysConfigItem/selectDetail/"+data.id);
//									$(this).find('.infos').find('.t3C').html(data.overview);
//									$(this).find('._people').append('<i class="iconfont">&#xe6e6;</i>');
//									$(this).find('._people').append((data.baseNum+data.actualNum)+'人学习过');
//								}else{
//									if(div_num == 4 || div_num == 8){
//										data.name = data.name.length>30?data.name.substring(0,30)+"...":data.name;
//									}else{
//										data.name = data.name.length>14?data.name.substring(0,12)+"...":data.name;
//									}
//									$(this).find('.borderCon').find('img').attr('src',data.coverUrl);
//									$(this).find('.t4Con').find('h4').html(data.name);
//									$(this).find('._people').append('<i class="iconfont">&#xe6e6;</i>');
//									$(this).find('._people').append('<em class="ml10">'+(data.baseNum+data.actualNum)+'</em>');
//								}
//								if(!data.realPrice){
//									$(this).find('._price').html('免费');
//								}else{
//									$(this).find('._price').append('<em>￥'+data.realPrice+'</em>');
//									$(this).find('._price').append('<del>￥'+data.originalPrice+'</del>');
//								}
//
//							}else{
//								$(this).hide();
//							}
//						});
//					},
//					error : function(){
//						
//					}
//				});
				
				if(params.showItemSecond && params.itemOneId){
					div.find(".class-menu").find("a").remove();
					$ajax({
						url : "/sysConfigItem/getJsons?itemType=2&parentId="+params.itemOneId,
						success : function(itemTwo){
							var nn,html='';
							if(itemTwo && itemTwo.length){
								for(var x=0 ; x < itemTwo.length ; x++){
									if(x > 4)
										break;
									if(x==0)
										html += '<li  class="active _menutab"  value="'+itemTwo[x].id+'"><a href="javascript:;">'+itemTwo[x].itemName+'</a></li>';
									else 
										html += '<li  class="_menutab"  value="'+itemTwo[x].id+'"><a href="javascript:;">'+itemTwo[x].itemName+'</a></li>';
									div.find(".homeListTitle").find('ul').html(html);
								}
							}else{
								div.find(".homeListTitle").find('ul').html('');
							}
							params.itemSecondId=itemTwo[0]?itemTwo[0].id:"";
							$.ajax({
								url: rootPath+"/commodity/loadData",
								data: params,
								type: "post",
								dataType : "json",
								async : false,
								success: function(jsonData){
									var div_num=div.find("._item").length;//几图
									div.find("._item").each(function(i){
										var data=jsonData[i];
										if(data){
											$(this).show();
											data.coverUrl = data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg";
											$(this).find("._img").attr("src",data.coverUrl).on("click",function(){ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id)}).on("mouseover",function(){$(this).css("cursor","pointer");});
											$(this).find("._more").on("click",function(){ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id)});
											if(div_num == 3){
												data.name = data.name.length>20?data.name.substring(0,20)+"...":data.name;
												if(data.overview && data.overview.length>60){
													data.overview = data.overview.substring(0,60)+"...";
												}
												$(this).find('.pic').find('img').attr('src',data.coverUrl);
												$(this).find('.infos').find('h2').find('a').html(data.name).attr('href',rootPath+"/sysConfigItem/selectDetail/"+data.id);
												$(this).find('.infos').find('.t3C').html(data.overview);
												$(this).find('._people').append('<i class="iconfont">&#xe6e6;</i>');
												$(this).find('._people').append((data.baseNum+data.actualNum)+'人学习过');
											}else{
												if(div_num == 4 || div_num == 8){
													data.name = data.name.length>30?data.name.substring(0,30)+"...":data.name;
												}else{
													data.name = data.name.length>14?data.name.substring(0,12)+"...":data.name;
												}
												$(this).find('.borderCon').find('img').attr('src',data.coverUrl);
												$(this).find('.t4Con').find('h4').html(data.name);
												$(this).find('._people').append('<i class="iconfont">&#xe6e6;</i>');
												$(this).find('._people').append('<em class="ml10">'+(data.baseNum+data.actualNum)+'</em>');
											}
											if(!data.realPrice){
												$(this).find('._price').html('免费');
											}else{
												$(this).find('._price').append('<em>￥'+data.realPrice+'</em>');
												$(this).find('._price').append('<del>￥'+data.originalPrice+'</del>');
											}

										}else{
											$(this).hide();
										}
									});
								},
								error : function(){
									
								}
							});
							
						}
					})
				}else{
					div.find(".homeListTitle").find('ul').html('');
					$.ajax({
						url: rootPath+"/commodity/loadData",
						data: params,
						type: "post",
						dataType : "json",
						async : false,
						success: function(jsonData){
							var div_num=div.find("._item").length;//几图
							div.find("._item").each(function(i){
								var data=jsonData[i];
								if(data){
//									$(this).find("._img").attr("src",(data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg"));
//									$(this).find("._price").html("¥"+data.realPrice);
//									$(this).find("._people").html("<i></i>"+data.actualNum);
//									$(this).find("._caption").html(data.name);
//									$(this).find("._overview").html(data.overview);
									$(this).show();
									data.coverUrl = data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg";
									$(this).find("._img").attr("src",data.coverUrl).on("click",function(){ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id)}).on("mouseover",function(){$(this).css("cursor","pointer");});
									$(this).find("._more").on("click",function(){ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id)});
									if(div_num == 3){
										data.name = data.name.length>20?data.name.substring(0,20)+"...":data.name;
										if(data.overview && data.overview.length>60){
											data.overview = data.overview.substring(0,60)+"...";
										}
										$(this).find('.pic').find('img').attr('src',data.coverUrl);
										$(this).find('.infos').find('h2').find('a').html(data.name).attr('href',rootPath+"/sysConfigItem/selectDetail/"+data.id);
										$(this).find('.infos').find('.t3C').html(data.overview);
										$(this).find('._people').append('<i class="iconfont">&#xe6e6;</i>');
										$(this).find('._people').append((data.baseNum+data.actualNum)+'人学习过');
									}else{
										if(div_num == 4 || div_num == 8){
											data.name = data.name.length>30?data.name.substring(0,30)+"...":data.name;
										}else{
											data.name = data.name.length>14?data.name.substring(0,12)+"...":data.name;
										}
										$(this).find('.borderCon').find('img').attr('src',data.coverUrl);
										$(this).find('.t4Con').find('h4').html(data.name);
										$(this).find('._people').append('<i class="iconfont">&#xe6e6;</i>');
										$(this).find('._people').append('<em class="ml10">'+(data.baseNum+data.actualNum)+'</em>');
									}
									if(!data.realPrice){
										$(this).find('._price').html('免费');
									}else{
										$(this).find('._price').append('<em>￥'+data.realPrice+'</em>');
										$(this).find('._price').append('<del>￥'+data.originalPrice+'</del>');
									}

								}else{
									$(this).hide();
								}
							});
						},
						error : function(){
							
						}
					});
				}
			}
			
		},
		//发布
		publish : function(){
			$ajax({
				url : "/sysBody/publish",
				data : {
					schoolId : $("#schoolId").val(),
					id : $("#templateId").val()
				},
				success: function(){
					$.msg("新的配置已应用，快去首页看看吧~");
				}
			})
		},
		//返回
		goback: function(){
			location.href=rootPath+"/sysBody/show";
		},
	// tab  公有云/私有云  
	queryConditionPics : function(tab){
		var $this=this;
		//私有云
		if(tab=="privatePic"){
			$("#ulList li:first").show();
			$ajax({
				url : "/sysConfigItem/itemList",
				success : function(result) {
					$(".upload-layer").find(".types-list").find("a").not(":first").remove();
					$.each(result,function(i,item){
						$(".upload-layer").find(".types-list").append("<a href='javascript:;' id='"+item.id+"' class='btn btn-mini btn-link'>"+item.itemName+"</a>");
					});
					$this.queryPirvatePics(1);
				}
			});
		}else{
			//公有云
			$ajax({
				url : "/company/queryCondition",
				success : function(result) {
					$(".upload-layer").find(".types-list").find("a").not(":first").remove();
					$.each(result,function(i,item){
						$(".upload-layer").find(".types-list").append("<a href=javascript:; id='"+item.id+"' class='btn btn-mini btn-link'>"+item.picTag+"</a>");
					});
					$this.queryPublicPics(1);
				}
			});
			
		}
	},
	//查询私有云
	queryPirvatePics : function (page,itemOneId){
		$("#ulList li").not(":first").remove();
		var params={};
		if(page){
			params.page=page;
		}else{
			params.page=1;
		}
		if(itemOneId){
			params.itemOneId=itemOneId;	
		}
		$ajax({
			url : "/company/queryPics",
			data : params,
			dataType : "html",
			success : function(data) {
				$("#tlist").html(data);
			},
			error: function(resp,err,msg){
				console.log(resp);
			}
		});
	},
	//查询公司公有图片库
	queryPublicPics : function (page,pictag){
		var params={};
		if(page){
			params.page=page;
		}else{
			params.page=1;
		}
		if(pictag){
			params.picTag=pictag;	
		}
		$ajax({
			url : "/company/queryPirvatePics",
			type : "POST",
			data : params,
			dataType : "html",
			success : function(data) {
				$("#tlist").html(data);
			},
			error: function(resp,err,msg){
				console.log(resp);
			}
		});
	},
	//选择图片
	savePic : function ($model){
		$.loading();
			$.ajaxFileUpload({
			url : rootPath+"/sysBody/uploadPics;"+ window["sessionName"] + "=" + window["sessionId"],
			fileElementId : 'img_'+$model.attr("id"),
			dataType:'json',
			type: 'POST',
			success : function(data) {
				if(data.url){
					$model.find('.cusImg').attr('src',data.url);
					$model.find('.img-perview').attr('realPath',data.picPath);
				}
				$.loadover();
			},
			error:function(arg1,arg2,arg3){
				//console.log(arg1);
				$.loadover();
			}
		});
	},
	//上传截取后的图片
		classTypePic : function() {
			$.ajax({
				url : rootPath + "/sysBody/saveCutPic",
				data : {
					path : $("#target").attr("src"),
					x : $("#x").val(),
					y : $("#y").val(),
					w : $("#w").val(),
					h : $("#h").val()
				},
				type : "post",
				dataType : "json",
				success : function(data) {
					chooseOnePic(data.picOriginalUrl, data.realPath);
				}
			})
			$("#chooseDiv").css("display", "none");
			$("#stopDiv").css("display", "none");
			return;
		}
		
	}
	window.queryPicByItemOneId=addSysBody.queryPirvatePics;
	window.queryPublicPic=addSysBody.queryPublicPics;
	window.savePic=addSysBody.savePic;
	$.callback=addSysBody.add;
	$(document).ready(function(){
		addSysBody.init();
	});
})(jQuery)