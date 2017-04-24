/**
 * author zhang.zx
 * 课程包
 */
(function($){
	var Form={
			init : function(){
				var $this=this;
				$selectMenu("course_package");
				//点击一级分类
				$("#firstTypeList").on('click','a.btn',function(){
					if(!$(this).hasClass('btn-success')){
						$(this).addClass('btn-success').siblings('a').removeClass('btn-success');
						$.cookie("onecode",$(this).attr("code"),{path:'/'});
					}
					Form.queryItemSecond();
				});
				//点击二级分类
				$("#secondeTypeList").on('click','a.btn',function(){
					if(!$(this).hasClass('btn-success')){
						$(this).addClass('btn-success').siblings('a').removeClass('btn-success');
						$.cookie("twocode",$(this).attr("code"),{path:'/'});
					}
					Form.querythirdList();
				});
				//点击三级级分类
				$("#thirdTypeList").on('click','a.btn',function(){
					if(!$(this).hasClass('btn-success')){
						$(this).addClass('btn-success').siblings('a').removeClass('btn-success');
						$.cookie("threecode",$(this).attr("code"),{path:'/'});
					}
					Form.queryAllCommdityByItem(1);
				});
				//加载一级分类
				$("#firstTypeList").html("");
				$.ajax({
					url : rootPath + "/classPackageCategory/queryCategoryList",
					type : "post",
					dataType : "json",
					data:{"leavl":"first","status":1},
					success : function(result) {
						var code=$.cookie("onecode");
						if(code){
							$.each(result,function(i,item){
								if(code==item.code){
									$("#firstTypeList").append("<a href='javascript:void(0);' code='"+item.code+"' ids='"+item.id+"' class='btn btn-mini btn-default btn-success'>"+item.name+"</a>");
								}else{
									$("#firstTypeList").append("<a href='javascript:void(0);' code='"+item.code+"' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.name+"</a>");
								}
							});
						}else{
							$.each(result,function(i,item){
								if(i==0){
									$("#firstTypeList").append("<a href='javascript:void(0);' code='"+item.code+"' ids='"+item.id+"' class='btn btn-mini btn-default btn-success'>"+item.name+"</a>");
								}else{
									$("#firstTypeList").append("<a href='javascript:void(0);' code='"+item.code+"' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.name+"</a>");
								}
								
							});
						}
						Form.queryItemSecond();
					}
				});
			},
			queryItemSecond : function (){
				var code;
				$("#firstTypeList").find("a.btn").each(function(){
					if($(this).hasClass("btn-success")){
						code=$(this).attr("code");
					}
				});
				$("#secondeTypeList").html('');
				$.ajax({
					url : rootPath + "/classPackageCategory/queryCategoryList",
					type : "post",
					data : {"leavl":"second","code":code,"status":1},
					dataType : "json",
					success : function(result) {
						var code=$.cookie("twocode");
						$("#secondeTypeList").append("<a href='javascript:void(0);' code='' class='btn btn-mini btn-default'>全部</a>");
						$.each(result,function(i,item){
							if(code && code==item.code){
								$("#secondeTypeList").append("<a href='javascript:void(0);' code='"+item.code+"' ids='"+item.id+"' class='btn btn-mini btn-default btn-success'>"+item.name+"</a>");
							}else{
								$("#secondeTypeList").append("<a href='javascript:void(0);' code='"+item.code+"' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.name+"</a>");
							}
							
						});
						Form.querythirdList();
					}
				});
			},
			querythirdList : function(){
				var firstCode,code;
				$("#firstTypeList").find("a.btn").each(function(){
					if($(this).hasClass("btn-success")){
						firstCode=$(this).attr("code");
					}
				});
				$("#secondeTypeList").find("a.btn").each(function(){
					if($(this).hasClass("btn-success")){
						code=$(this).attr("code");
					}
				});
				if(!code){
					code=firstCode;
				}
				$("#thirdTypeList").html('');
				$.ajax({
					url : rootPath + "/classPackageCategory/queryCategoryList",
					type : "post",
					dataType : "json",
					data : {"leavl":"third","code":code,"status":1},
					success : function(result) {
						var code=$.cookie("threecode");
						$("#thirdTypeList").append('<a href="javascript:void(0);" code="" class="btn btn-mini btn-default">全部</a>');
						$.each(result,function(i,item){
							if(code && code==item.code){
								$("#thirdTypeList").append("<a href='javascript:void(0);' code='"+item.code+"' ids='"+item.id+"' class='btn btn-mini btn-default btn-success'>"+item.name+"</a>");
							}else{
								$("#thirdTypeList").append("<a href='javascript:void(0);' code='"+item.code+"' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.name+"</a>");
							}
						});
						Form.queryAllCommdityByItem(1);
					}
				});
			},
			queryAllCommdityByItem : function(page,code){
				$("#firstTypeList").find("a.btn").each(function(){
					if($(this).hasClass("btn-success")){
						var firstCode=$(this).attr("code");
						if(firstCode){
							code=firstCode;
						}
					}
				});
				$("#secondeTypeList").find("a.btn").each(function(){
					if($(this).hasClass("btn-success")){
						var secondCode=$(this).attr("code");
						if(secondCode){
							code=secondCode;
						}
					}
				});
				$("#thirdTypeList").find("a.btn").each(function(){
					if($(this).hasClass("btn-success")){
						var thirdCode=$(this).attr("code");
						if(thirdCode){
							code=thirdCode;
						}
					}
				});
				if(code==""){
					code=$("#coursePackageCode").val();
				}
				if(!page || page == ""){
					page=1;
				}
				$.ajax({
					url : rootPath + "/classPackage/queryByList",
					type : "post",
					data : {"page" : page,"categoryCode" : code},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#packageLists").html(result);
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			queryCommodityByName : function(page){
				var name=$("#coursePackageName").val();
				$.ajax({
					url : rootPath + "/classPackage/queryByList",
					type : "post",
					data : {"page" : page,"name":name},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#packageLists").html(result);
						//$("#coursePackageName").val("");
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			stopOnsale : function(id){
				$.confirm("您确定要下架此课程包?下架后学员将无法再报名此课程包。",function(a){
					if(a==true){
						$.ajax({
							url : rootPath + "/classPackage/changClassPackageCollect",
							type : "post",
							data : {"id":id,"publishStatus":'CLASS_STOP_SALE'},
							success : function(result) {
								$("#commodityLi"+id).find("i:first").text("停售").css({"background-color":"rgba(231,31,26,0.8)","color":"white"});;
								$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("上架").attr("href","javascript:Form.classTypeOnsale("+result.id+")");
							}
						});
					}else{
						return;
					}
				});
			},
			deleteClassPackage : function(id){
				$.confirm("您确定要删除此课程包吗?",function(a){
					if(a==true){
						location.href=rootPath + "/classPackage/delClassPackage/"+id;
					}
				})
			},
			classTypeOnsale : function(id){
				$.ajax({
					url : rootPath+"/classPackage/getCourseNum",
					type : "post",
					data : {"id":id},
					dataType : "json",
					success : function(result) {
						if(result && result>0){
							$.ajax({
								url : rootPath + "/classPackage/changClassPackageCollect",
								type : "post",
								data : {"id":id,"publishStatus":'CLASS_ON_SALE'},
								success : function(result) {
									$("#commodityLi"+id).find("i:first").text("在售").css({"background-color":"rgba(194,235,235,.8)","color":"black"});
									$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("下架").attr("href","javascript:Form.stopOnsale("+result.id+")");
								}
							});
						}else{
							if(result && result==-1){
								$.msg("课程包中还有课程没有添加在售班号");
							}else{
								$.msg("课程包下添加课程后才可以发布");
							}
						}
					}
				});	
			},
			editClassType : function(id){
				var code;
				$("#firstTypeList").find("a.btn").each(function(){
					var firstCode=$(this).attr("code");
					if(firstCode){
						code=firstCode;
					}
				});
				if(code && code !=""){
					window.location.href=rootPath+"/classPackage/manageBaseInfo/edit?id="+id;
				}else{
					$.msg("请先添加课程包分类");
				}
			},
			show : function(){
				$("#divDisplay").css("display","block");
			},
			close : function(){
				$("#divDisplay").css("display","none");
			},
			showSave : function(id){
				$("#com"+id).css("display","block");
			},
			closeSave : function(id){
				$("#com"+id).css("display","none");
			},
			collectShop : function(id){
				var status=$("#com"+id).attr("marks");
				if(status=="0"){
					status=1;
				}else{
					status="0";
				}
				$.ajax({
					url : rootPath + "/classPackage/changClassPackageCollect",
					type : "post",
					data : {"id":id,"recommendFlag":status},
					success : function(result) {
						if(result.recommendFlag==1){
							$("#com"+id).text('取消推荐');
							$("#com"+id).attr("marks",1);
						}else{
							$("#com"+id).text('加入推荐');
							$("#com"+id).attr("marks",0);
						}
					}
				});
			}
			
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)