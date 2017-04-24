/**
 * author zhang.zx
 * 班型打包
 * 页面js封装
 */
(function($){
	
	var Form={
			init : function(){
				$selectMenu("course_class_type");
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
	    		//鼠标移到加号上的提示信息
	    		$(".marks").on("mouseover",function(){
	    			if($(this).attr("marks")=="face"){
	    				$(this).attr("title","增加面授课程单元");
	    			}else{
	    				$(this).attr("title","增加直播课程单元");
	    			}
	    		})
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
				var lab=$("#lableId").val();
				if(lab=="face"){
					tMethod="TEACH_METHOD_FACE";
				}else{
					tMethod="TEACH_METHOD_LIVE";
				}
				
				$("#ulOne").html('');
				$.ajax({
					url : rootPath + "/classType/queryModuleByCon",
					type : "post",
					data : {"itemOneId":itemOneId,"itemSecondId":itemSecondId,"teachMethod":tMethod},
					dataType : "json",
					success : function(result) {
						var html="";
						$.each(result,function(i,mou){
							html+="<li id='li"+mou.id+"' ids='"+mou.id+"' types='"+mou.teachMethod+"' title="+mou.name+">" +
							"<span class='p1'>"+(mou.name?(mou.name.length>10?mou.name.substring(0,10)+"...":mou.name):"")+"</span>"+
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
				var tMethod;
				$("#mNames").val($("#mouName").val());
				var itemSecondId=$("#itemSecsList option:selected").val();
				$("#ejItemId").val(itemSecondId);
				var lab=$("#lableId").val();
				if(lab=="face"){
					tMethod="TEACH_METHOD_FACE";
				}else{
					tMethod="TEACH_METHOD_LIVE";
				}
				$("#methodTeach").val(tMethod);
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
				if(isNaN($("#totalHours").val())||!(/^(\+|-)?\d+$/.test($("#totalHours").val()))||$("#totalHours").val()<0||$("#totalHours").val()>999){
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
								success : function(mou) {
									$("#chooseDiv").css("display", "none");
									$("#stopDiv").css("display", "none");
									var html="<li id='li"+mou.id+"' ids='"+mou.id+"' types='"+mou.teachMethod+"' title="+mou.name+">" +
									"<span class='p1'>"+(mou.name?(mou.name.length>10?mou.name.substring(0,10)+"...":mou.name):"")+"</span>"+
									"<span class='p2'>"+(mou.teachMethod=='TEACH_METHOD_FACE'?'面授':'直播')+"</span>"+
									"<span class='p3'>"+(mou.totalClassHour?mou.totalClassHour:0)+"课时</span>"+
									"</li>";
									$("#ulOne").prepend(html);
									//window.Form.queryModuleByClassType();
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
				var modules="";
				$("#ulTwo").find("li").each(function(i){
					var mds=$(this).attr("ids");
					modules+=mds+",";
				});
				document.getElementById("moduleId").value=modules;
				if(modules.length<=0){
					$.alert("课程单元不能为空！");
					return;
				}
				if(type=="update"){
					$.ajax({
						url : rootPath + "/editClass/updatePackage",
						type : "post",
						data : $("#subMessageForm").serialize(),
						dataType : "json",
						success : function(b) {
							$.msg("保存信息成功");
						}
					});
				}
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)