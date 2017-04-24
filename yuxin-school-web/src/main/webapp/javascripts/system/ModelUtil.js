/**
 * @Caption: 公用工具类，包含商品获取工具、首页div拼装工具. 需要增加或修改必须添加注释，说明原因及功能
 * @Auther: chopin  
 * @Date: 2015-3-23
 */
(function($){
	$.imageserver="http://image.yunduoketang.com/";
	var modelUtil={
		//step1:解析个人配置
		/**
		 * @param bodys 个人配置
		 * @param divs  模板
		 * @param configs 数据配置
		 */
		resolve : function(bodys,divs,configs){
			bodys=eval(bodys);
			divs=eval(divs);
			configs=eval(configs);
			var htmls=new Array();
			for(var i=0;i<bodys.length;i++){
				var div,config;
				if(bodys[i]){
					for(j in divs){
						if(divs[j] && divs[j].id==bodys[i].modelId){
							div=divs[j].htmlElement;
						}
					}
					for(var k=0;k<configs.length;k++){
						if(configs[k] && configs[k].id==bodys[i].configId && configs[k].type==bodys[i].type){
							config=configs[k];
						}
					}
				}
				config.type=bodys[i].type;
				htmls.push(this.fixDiv(i,div,config));
			}
			return htmls;
		},
		//step2:组装div
		fixDiv : function(index,content,config){
			var div=document.createElement("div");
			if(content && config){
				$(div).append(content);
				$(div).find("._title").find("i").after(config.title);
				if(config.showItemSecond){
//					$(div).find("title").find(".class-menu").data("#itemSecond#","");
				}else{
					$(div).find("title").find(".class-menu").remove();
				}
				/*编号*/
				$(div).find("._body").find("._img").each(function(i){$(this).attr("id","img_"+index+"_"+i)});
				$(div).find("._body").find("._caption").each(function(i){$(this).attr("id","caption_"+index+"_"+i)});
				$(div).find("._body").find("._price").each(function(i){$(this).attr("id","price"+index+"_"+i)});
				$(div).find("._body").find("._people").each(function(i){$(this).attr("id","<i></i>people_"+index+"_"+i)});
				$(div).find("._body").find("._overview").each(function(i){$(this).attr("id","overview_"+index+"_"+i)});
//				$(div).find("._body").find("._caption").each(function(i){$(this).attr("id","caption_"+index+"_"+i)});
				
				$(div).children("div").data("config",config);
				return $(div).html();
			}else{
				return "";
			}
		},
		//step3:填充数据
		fillData : function(index,div,config){
			if(!div || !config){
				console.log("没有内容");
				return;
			}
			var params=config;
			if(config.sortBy=="time"){
				params.cusorder="update_time desc";
			}
			if(config.sortBy=="price"){
				params.cusorder="real_price asc ";
			}
			if(config.sortBy=="num"){
				params.cusorder="actualNum desc";
			}
			if(config.showItemSecond==1 || config.showItemSecond=="1"){
				params.showItemSecond=1;
			}
			//课程推荐标记
			if(config.recommendFlag=="1"){
				if(params.cusorder){
					params.cusorder="recommend_flag desc,update_time desc,"+params.cusorder;
				}else{
					params.cusorder="recommend_flag desc";
				}
			}
			//新闻推荐标记
			if(config.type == 'INDEX_NEWS'){
				if(config.recommentFlag==1){
					params.cusorder="recommend_flag desc";
				}else{
					params.cusorder="";
				}
			}
			
			if(config.teachType=="live"){
				params.liveFlag="1"
			}
			if(config.teachType=="video"){
				params.videoFlag="1"
			}
			if(config.teachType=="face"){
				params.faceFlag="1"
			}
			params.cuslimit=" 0,"+div.find("._item").length;
			
			$.each(params,function(k,v){
				if(v == null || v=="" || v=="null"){
					delete params[k];
				}
			})
			//标题
			$(div).find(".homeListTitle").find("h5").html(config.title);
			if(config.type=="INDEX_ITEM"){
				if(div.find(".homeListTitle").find('ul').length>0 && config.itemOneId){
					div.find(".homeListTitle").find('ul').html('');
					$.ajax({
						url: rootPath+"/sysConfigItem/findItemSecond/"+config.itemOneId+"/"+$("#schoolId").val(),
						type: "post",
						dataType: "json",
						success: function(jsonData){
							$.each(jsonData,function(i){
//							    if(i==0){
//							    	div.find(".class-menu").append('<a href="javascript:void(0);" class="_menutab active" value="'+jsonData[i].id+'">'+jsonData[i].itemName+'</a>');
//							    }else if(i<5){
//									div.find(".class-menu").append('<a href="javascript:void(0);" class="_menutab" value="'+jsonData[i].id+'">'+jsonData[i].itemName+'</a>');
//								}
								if(i==0){
									div.find(".homeListTitle").find('ul').append('<li  class="active _menutab"  value="'+jsonData[i].id+'"><a href="javascript:;">'+jsonData[i].itemName+'</a></li>');
							    }else if(i<5){
									div.find(".homeListTitle").find('ul').append('<li  class="_menutab"  value="'+jsonData[i].id+'"><a href="javascript:;">'+jsonData[i].itemName+'</a></li>');
								}
							});
							div.off("click.menu.classes");
							div.on("click.menu.classes","._menutab",function(){
								$(this).parent().find("._menutab").removeClass("active");
								$(this).addClass("active");
								params.itemSecondId=$(this).attr("value");
								window.ModelUtil.query($(this).parents(".wrap"),params,"INDEX_ITEM");
							});
//							注销这行代码的原因是：在后台设置3-8图的模块时，只配置了一级学科，并没有配置学科小类，所以这里在查找的时候是查找所有学科下面的课程
							params.itemSecondId=jsonData[0]?jsonData[0].id:"";
							window.ModelUtil.query(div,params,"INDEX_ITEM");
						},
						error: function(){
							
						}
					})
					
				}else{
					window.ModelUtil.query(div,params,"INDEX_ITEM");
				}
			}
			if(config.type=="INDEX_CLASSTYPE"){
				if(config.classTypeOne){
					params.classTypeOne=config.classTypeOne;
				}else{
					params.classTypeOne="";
				}
				if(config.classTypeTwo){
					params.classTypeTwo=config.classTypeTwo;
				}else{
					params.classTypeTwo="";
				}
				if(config.classTypeThree){
					params.classTypeThree=config.classTypeThree;
				}else{
					params.classTypeThree="";
				}
				this.query(div,params,"INDEX_CLASSTYPE");
				
			}
			if(config.type=="INDEX_CUSTOM"){
				this.query(div,params,"INDEX_CUSTOM");
			}
			if(config.type=="INDEX_NEWS"){
				if(config.newsId){
					params.id=config.newsId;
				}else{
					params.id=null;
				}
				this.query(div,params,"INDEX_NEWS");
			}
			
		},
		query: function(div,params,type){
			this.clean(div);
			if(type=="INDEX_CLASSTYPE"){
				$.ajax({
					url: rootPath+"/commodity/loadData2",
					data: params,
					type: "post",
					dataType : "json",
					async : false,
					success: function(jsonData){
						div.find("._title").html('<i></i>'+(params.title?params.title:""));
						div.find("._item").each(function(i){
							var data=jsonData[i];
							if(data){
//								data.coverUrl = data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg";
//								$(this).find("._img").attr("src",data.coverUrl).on("mouseover",function(){$(this).css("cursor","pointer");});
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
								if(data.memberFlag){
									$(this).find('.vipImg').removeClass('none');
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
			if(type=="INDEX_NEWS"){
				params.schoolId=$("#schoolId").val();
				$.ajax({
					url: rootPath+"/sysNews/loadData",
					data: params,
					type: "post",
					dataType : "json",
					async : false,
					success: function(jsonData){
//						if(div.find("._title").find("i")[0]){
//							div.find("._title").html('<i></i>'+params.title);
//						}else{
//							div.find("._title").html(params.title);
//						}
//						if(div.find(".list").length>0){
//							div.find("._item").each(function(i){
//								var data=jsonData[i];
//								if(data){
//									$(this).html('<i></i><a href="javascript:;" value="'+data.id+'">'+data.summary+'</a></li>');
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
				div.find("._title").html(params.title);
				div.find("._item").each(function(i){
					$(this).find("._img").attr("src",$.imageserver+params.picture).on("mouseover",function(){$(this).css("cursor","pointer");});
					$(this).find("._overview").html(params.description);
					$(this).find("._more").attr("href",params.link);
				})
			}
			if(type=="INDEX_ITEM"){
				params.schoolId=$("#schoolId").val(); 
				$.ajax({
					url: rootPath+"/commodity/loadData",
					data: params,
					type: "post",
					dataType : "json",
					async : false,
					success: function(jsonData){
						var div_num=div.find("._item").length;//几图
						div.find("._title").html('<i></i>'+params.title);
						if(params.showItemSecond){
						}else{
							div.find(".homeListTitle").find('ul').html('');
//							div.find(".title").find(".class-menu").find("a").remove();
						}
						div.find("._item").each(function(i){
							var data=jsonData[i];
							if(data){
//								data.coverUrl = data.coverUrl?$.imageserver+data.coverUrl:rootPath+"/images/overview_demo.jpg";
//								$(this).find("._img").attr("src",data.coverUrl).on("mouseover",function(){$(this).css("cursor","pointer");});
//								$(this).find("._price").html("¥"+data.realPrice);
//								$(this).find("._people").html("<i></i>"+data.actualNum);
//								$(this).find("._caption").html(data.name);
//								$(this).find("._overview").html(data.overview);
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
//								$(this).on('click','img,.t4Con h4',function(){
//									ModelUtil.openLink(rootPath+"/sysConfigItem/selectDetail/"+data.id);
//								});

							}else{
								$(this).hide();
							}
						});
					},
					error : function(){
						
					}
				});
			}
		},
		clean : function(div){
			div.find("._img").attr("src","").parent().html(div.find("._img")[0]);
			div.find("._price").html("");
			div.find("._people").html("");
			div.find("._caption").html("");
			div.find("._overview").html("");
			div.find("._time").remove("");
		},
		openLink : function(link){
			window.open(link);
		}
	};
	$.fn.extend({
        randomImg:function(option){
            var option = option || {},
                num    = parseFloat(option.num)>4 ? 4 : option.num,
                str    = '',
                div    = $('<div></div');
//                    cur=option.current || false,
//                    time=cur ? num : 4,//这里控制是否按照当前显示current：true时按照当前显示，否则默认显示4个
//                    user = user || 0;//这里控制显示除4以外剩下的在线人数
            if($(this).next().length){
                $(this).next().html('+'+num)
            }
            for(var i=0;i<num;i++){
                str = $('<strong>').css({
                    'background-position-x':-((Math.ceil(Math.random()*15)-1)*37+5),
                    'background-position-y':-((Math.ceil(Math.random()*3)-1)*36+5)
                });
                div.append(str);
            }
            $(this).html(div.html());
        }
    });
	window.ModelUtil=modelUtil;
})(jQuery)