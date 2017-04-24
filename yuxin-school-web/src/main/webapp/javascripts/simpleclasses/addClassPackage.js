/**
 * author zhang.zx
 * 班型打包
 * 页面js封装
 */
(function($){
	
	var Form={
			init : function(){
				$selectMenu("course_class_type");
				//查询公司不存在服务
				$.ajax({
					url : rootPath + "/companyServiceStatic/companyNoServices",
					type : "post",
					async:false,
					dataType : 'json',
					success : function(jsonData) {
						var count=jsonData.length;
						if(count<=1){
							$.each(jsonData,function(i,item){
								if(item.groupCode=='SYS_CONFIG_SERVICE_LIVE'){
									$("#tabsList").find("a.btn").eq(0).removeClass("btn-success").addClass("none");
									$("#tabsList").find("a.btn").eq(1).addClass("btn-success none");
								}else{
									$("#tabsList").find("a.btn").eq(0).addClass("btn-success none");
									$("#tabsList").find("a.btn").eq(1).removeClass("btn-success").addClass("none");
								}
							});
						}
					}
				});
				
				$("#tabsList").on('click','a.btn',function(){
					var _this=$(this),status= _this.hasClass('btn-success');
						_this.addClass('btn-success').siblings('a').removeClass('btn-success');
						if(_this.hasClass("btn-success")){
							if(_this.attr("ids")=="TEACH_METHOD_LIVE"){
								$(".marks").attr("title","增加直播课程单元");
							}else{
								$(".marks").attr("title","增加面授课程单元");
							}
							$('[title]').miniTip({
								  className: 'green',
								  offset: 30,
								  showAnimateProperties: {'top': '-=10'},
								  hideAnimateProperties: {'top': '+=10'},
								  position: 'bottom',
								  onLoad: function(element, minitip) {
								    $(element).animate({'opacity': 0.35}, '350');
								  },
								  onHide: function(element, minitip) {
								    $(element).animate({'opacity': 1}, '250');
								  }
								});
						}
						
						
				});
				
				$("ul").on('click','li',function(){
					var status=$(this).hasClass('active');
					if(!status){
						$(this).addClass('active');
					}else{
						$(this).removeClass('active');
					}
				});
				
				$("ul").on('dblclick','li',function(){
					var status=$(this).hasClass('active');
					if(!status){
						$(this).addClass('active');
					}else{
						$(this).removeClass('active');
					}
				});
				//双击向右
				$("#ulOne").on('dblclick','li',function(){
					var b=true;
					var cid=$(this).attr("ids");
					var newModule=$("#li"+cid);
					var b=$("#type2").attr("value");
					if(b=="update"){
						$("#ulTwo").find("li").each(function(i){
							var a=$(this).attr("ids");
							if(cid==a){
							    b=false;
							}
						});
						if(b){
							$("#ulTwo").append(newModule);
						}else{
							$.alert("课程单元已存在");
							return;
						}
					}else{
						$("#ulTwo").append(newModule);
					}
				});
				//双击向左
				$("#ulTwo").on('dblclick','li',function(){
					var b=true;
					var cid=$(this).attr("ids");
					var newModule=$("#li"+cid);
					var b=$("#type2").attr("value");
					if(b=="update"){
						$("#ulOne").find("li").each(function(i){
							var a=$(this).attr("ids");
							if(cid==a){
							    b=false;
							}
						});
						if(b){
							$("#ulOne").append(newModule);
						}else{
							$(this).remove();
							return;
						}
					}else{
						$("#ulOne").append(newModule);
					}
				});
				//查询二级项目
				this.queryItemSecond($("#itemOneIds").val());
				//改变二级项目时查询模块信息
				$("#itemSecsList").on('change',function(){
					var ids=$(this).find("option:selected").attr("value");
					Form.queryModuleByClassType(ids);
				})
				//查询模块信息
				this.queryModuleByClassType();
				console.log($("#itemOneIds").val());
				//查询课程单元中的二级项目
				//this.queryItemSecondtwo($("#itemOneIds").val());
				$(".box-select").on('click','.firstLi',function(){
					var itemSec=$("#itemSecsList option:selected").html();
					$("#twoItemsCon").html(itemSec);
					$("#tabsList").find("a.btn").each(function(){
			 			var $this=$(this);
			 			var sta=$this.hasClass("btn-success");
			 			if(sta){
			 				var mark=$this.attr("ids");
			 				if(mark=="TEACH_METHOD_FACE"){
				 				$("#tits").html("新增面授课程单元");
				 				$("#tMs").html("面授");
				 			}else{
				 				$("#tits").html("新增直播课程单元");
				 				$("#tMs").html("直播");
				 			}
			 			}
			 		})
					$("#chooseDiv").css("display", "block");
					$("#stopDiv").css("display", "block");
					$("#mouName").val('');
					$("#totalClassHour").val('');
					$("#moduleDesc").val('');
				});
				
	    		$("#giveUp").on('click',function(){
	    			$("#chooseDiv").css("display", "none");
					$("#stopDiv").css("display", "none");
	    		});
	    		
	    		//验证模块名称
	    		$("#mouName").on('keyup',function(){
	    			$.ajax({
						url : rootPath + "/classModule/checkName",
						type : "post",
						data : {"name":$(this).val()},
						dataType : "json",
						success : function(b) {
							if(!b){
								$(".note1").html('').css("display","block").html("课程单元名称已被使用!");
							}else{
								$(".note1").html('').css("display","none").html("请输入课程单元名称");
							}
						}
					});
	    		})
	    		
	    	  //更新+的宽度
	    		$('.firstLi').width("width",$(".ulOne").width());
			},
			queryItemSecond : function (id){
				var iTwoId=$("#iTwoId").val();
				$.ajax({
					url : rootPath + "/exam/queryItemSecond",
					type : "post",
					data : {pid:id},
					dataType : "json",
					success : function(result) {
						var html="";
						$.each(result,function(i,item){
							if(item.id==iTwoId){
								html+="<option value='"+item.id+"' selected='selected'>"+item.itemName+"</option>"
							}else{
								html+="<option value='"+item.id+"'>"+item.itemName+"</option>";
							}
						});
						$("#itemSecsList").append(html);
					}
				});
			},
			queryModuleByClassType : function(ids){
				var itemOneId=$("#itemOneIds").val();
				var itemSecondId,tMethod;
				var itemSec=$("#itemSecsList option:selected").attr("value");
				if(ids){
					itemSecondId=ids;
				}else{
					if(itemSec){
						itemSecondId=itemSec;
					}else{
						itemSecondId=$("#iTwoId").val();
					}
				}
				$("#tabsList").find("a.btn").each(function(i){
					if($(this).hasClass("btn-success")){
						tMethod=$(this).attr("ids");
					}
				});
				$("#ulOne").html('');
				$.ajax({
					url : rootPath + "/simpleClasses/queryModuleByCon",
					type : "post",
					data : {"itemOneId":itemOneId,"itemSecondId":itemSecondId,"teachMethod":tMethod},
					dataType : "json",
					success : function(result) {
						var html="";
						$.each(result,function(i,mou){
							html+="<li id='li"+mou.id+"' ids='"+mou.id+"' types='"+mou.teachMethod+"'>" +
							"<span class='p1'>"+mou.name+"</span>"+
							"<span class='p2'>"+(mou.teachMethod=='TEACH_METHOD_FACE'?'面授':'直播')+"</span>"+
							"<span class='p3'>"+(mou.totalClassHour?mou.totalClassHour:0)+"课时</span>"+
							"</li>";
						});
						$("#ulOne").append(html);
					}
				});
			},
			moveRight : function(){
				var b=true;
				$("#ulOne").find("li").each(function(i){
					if($(this).hasClass('active')){
						var cid=$(this).attr("ids");
						var newModule=$("#li"+cid);
						var b=$("#type2").attr("value");
						if(b=="update"){
							$("#ulTwo").find("li").each(function(i){
								var a=$(this).attr("ids");
								if(cid==a){
									b=false;
								}
							});
							if(b){
								$("#ulTwo").append(newModule);
							}else{
								$.alert("课程单元已存在");
								return;
							}
						}else{
							$("#ulTwo").append(newModule);
						}
					}
				});
			},
			moveLeft : function(){
				$("#ulTwo").find("li").each(function(i){
					if($(this).hasClass('active')){
						var cid=$(this).attr("ids");
						var newModule1=$("#li"+cid);
						$("#ulOne").append(newModule1);
					}
				});
			},
//			queryItemSecondtwo : function (id){
//				$.ajax({
//					url : rootPath + "/exam/queryItemSecond",
//					type : "post",
//					data : {pid:id},
//					dataType : "json",
//					success : function(result) {
//						var html="";
//						$.each(result,function(i,item){
//							if(i==0){
//								html+="<option value='"+item.id+"' selected='selected'>"+item.itemName+"</option>"
//							}else{
//								html+="<option value='"+item.id+"'>"+item.itemName+"</option>";
//							}
//						});
//						$("#twoItems").append(html);
//					}
//				});
//			},
			addModules:function(){
				$("#mNames").val($("#mouName").val());
				var itemSecondId=$("#itemSecsList option:selected").val();
				$("#ejItemId").val(itemSecondId);
				$("#tabsList").find("a.btn").each(function(i){
					if($(this).hasClass("btn-success")){
						$("#methodTeach").val($(this).attr("ids"));
					}
				});
				$("#totalHours").val($("#totalClassHour").val());
				$("#moduleMark").val($("#moduleDesc").val());
				if($("#mNames").val()==""){
					$(".note1").css("display","block");
					return;
				}else{
					$.ajax({
						url : rootPath + "/classModule/checkName",
						type : "post",
						data : {"name":$("#mouName").val()},
						dataType : "json",
						success : function(b) {
							if(!b){
								$(".note1").html('').css("display","block").html("课程单元名称已被使用!");
								return;
							}else{
								$(".note1").html('').css("display","none").html("请输入课程单元名称");
							}
						}
					});
					$(".note1").css("display","none");
				}
				if($("#totalHours").val()==""){
					$(".note2").html('').html("请输入总课时").css("display","block");
					return;
				}else{
					$(".note2").css("display","none");
				}
				if(isNaN($("#totalHours").val())||$("#totalHours").val()<0||$("#totalHours").val()>999){
					$(".note2").html('').html("请正确输入数字").css("display","block");
					return;
				}else{
					$(".note2").css("display","none");
				}
				$.ajax({
					url : rootPath + "/classModule/checkName",
					type : "post",
					data : {"name":$("#mouName").val()},
					dataType : "json",
					success : function(b) {
						if(b){
							$.ajax({
								url : rootPath + "/classModule/saveModules",
								type : "post",
								data : $("#editFroms").serialize(),
								dataType : "json",
								success : function(result) {
									$("#chooseDiv").css("display", "none");
									$("#stopDiv").css("display", "none");
									window.Form.queryModuleByClassType();
								}
							});
						}else{
							$(".note1").html('').css("display","block").html("课程单元名称已被使用!");
						}
					}
				});
				
			},
			ClassTypeOnsal : function(mark){
				$("#markType").val(mark);
				var type=$("#type2").val();
				var count1=0;
				var count2=0;
				var count3=0;
				var modules="";
				$("#ulTwo").find("li").each(function(i){
					var mds=$(this).attr("ids");
					modules+=mds+",";
					console.log($(this).attr("types"));
					if($(this).attr("types")=="TEACH_METHOD_FACE"){
						count1++;
					}
					if($(this).attr("types")=="TEACH_METHOD_LIVE"){
						count2++;
					}
					if($(this).attr("types")=="TEACH_METHOD_VIDEO"){
						count3++;
					}
				});
				if(count1>0){
					$("#faceId").val(1);
				}else{
					$("#faceId").val(0);
				}
				if(count2>0){
					$("#liveId").val(1);
				}else{
					$("#liveId").val(0);
				}
				if(count3>0){
					$("#videoId").val(1);
				}else{
					$("#videoId").val(0);
				}
				document.getElementById("moduleId").value=modules;
				if(modules.length<=0){
					$.alert("课程单元不能为空！");
					return;
				}
				if(type=="update"){
					$("#subMessageForm").attr("action",rootPath+"/simpleClasses/updatePackage").submit();
				}else{
					$("#subMessageForm").attr("action",rootPath+"/simpleClasses/classTypeOnsal").submit();
				}
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)