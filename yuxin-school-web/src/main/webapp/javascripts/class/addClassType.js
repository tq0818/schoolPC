/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
(function($){
	var rules={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	            name: {
	                minlength: 2,
	                maxlength: 15,
	                required: true,
	                remote: {
                		url: rootPath+"/classType/checkClassTypeName",
                		type:"post",
                		dataType:"json",
                		data:{
                			name : function(){
                				return $("#classTypeName").val();
                			},
                		 error:function(arg,arg1,arg2){
                			 $(".loading-bg").hide();
                		 }
                		}
                	}
	            },
	            originalPrice:{
	            	number:true,
	            	digits:true,
	            	required:true
	            	
	            },
	            realPrice:{
	            	required:true,
	            	digits:true,
	            	number:true
	            },
	            baseNum:{
	            	required: true,
	            	digits:true,
	            	number:true
	            }
	        },
	        messages: {
	        	name:{
	        		remote:"该班型名称已存在"
	        	}
	        },
	        success: function (label) {
	        },
	        submitHandler: function (form) {
	            form.submit();
	        },
	        onkeyup: false
	    }
	  var rule1={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	            name: {
	                minlength: 2,
	                maxlength: 15,
	                required: true
	            },
	            originalPrice:{
	            	number:true,
	            	digits:true,
	            	required:true
	            	
	            },
	            realPrice:{
	            	required:true,
	            	digits:true,
	            	number:true
	            },
	            baseNum:{
	            	required: true,
	            	digits:true,
	            	number:true
	            }
	        },
	        messages: {
	        },
	        success: function (label) {
	        },
	        submitHandler: function (form) {
	            form.submit();
	        },
	        onkeyup: false
	    }
	var Form={
			init : function(){
				$.extend($.validator.messages, {
				    required: "必选字段",
					remote: "请修正该字段",
					email: "请输入正确格式的电子邮件",
					url: "请输入合法的网址",
					date: "请输入合法的日期",
					dateISO: "请输入合法的日期 (ISO).",
					number: "请输入合法的数字",
					digits: "只能输入整数",
					creditcard: "请输入合法的信用卡号",
					equalTo: "请再次输入相同的值",
					accept: "请输入拥有合法后缀名的字符串",
					maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
					minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
					rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
					range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
					max: jQuery.validator.format("请输入一个最大为{0} 的值"),
					min: jQuery.validator.format("请输入一个最小为{0} 的值"),
					isMobile : "不是有效的手机号"
				});
				$("#lableType").on('click','a.btn',function(){
					var _this=$(this),status= _this.hasClass('btn-success'),type=_this.attr("ids");
					if(type=="remoteFlag"){
					      $("#lableType").find("a").each(function(i){
					    	  var lable=$(this).attr("ids");
					    	  if(lable!="remoteFlag"){
					    		 $(this).removeClass('btn-success');  
					    	  }
					      });
					      if(!status){
								 _this.addClass('btn-success');
							}else{
								_this.removeClass('btn-success');
							}
					}else{
						 $("#lableType").find("a").each(function(i){
					    	  var lable=$(this).attr("ids");
					    	  if(lable=="remoteFlag"){
					    		 $(this).removeClass('btn-success');  
					    	  }
					      });
						if(!status){
							 _this.addClass('btn-success');
						}else{
							_this.removeClass('btn-success');
						}
					}
				});
				//售卖分校
//				$("#schoolLists").on('click','a.btn',function(){
//					var _this=$(this),status= _this.hasClass('btn-success');
//					if(!status){
//						 _this.addClass('btn-success');
//					}else{
//						_this.removeClass('btn-success');
//					}
//				});
				
				$("#tabsList").on('click','a.btn',function(){
					var _this=$(this),status= _this.hasClass('btn-success');
						_this.addClass('btn-success').siblings('a').removeClass('btn-success');
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
							$.alert("模块已存在");
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
							$.alert("模块已存在");
							return;
						}
					}else{
						$("#ulOne").append(newModule);
					}
				});
				var itemOneId="";
				$("#itemOneList").find("option").each(function(i){
					if($(this).is(':selected')){
						var cid=$(this).attr("value");
						itemOneId=cid;
					}
				});
				this.queryItemSecond(itemOneId);
			},
			queryItemSecond : function (id){
				if(id==null){
					$("#itemOneList").find("option").each(function(i){
						if($(this).is(':selected')){
							var cid=$(this).attr("value");
							id=cid;
						}
					});
				}
				$("#itemSecondList").html('');
				$.ajax({
					url : rootPath + "/exam/queryItemSecond",
					type : "post",
					data : {pid:id},
					dataType : "json",
					success : function(result) {
						if(result.length>0){
							$("#itemSecondList").css("display","block");
							$.each(result,function(i,item){
								if(item.id==$("#twoSecItemId").val()){
									$("#itemSecondList").append("<option selected='selected' value='"+item.id+"'>"+item.itemName+"</option>");
								}else{
									$("#itemSecondList").append("<option value='"+item.id+"'>"+item.itemName+"</option>");
								}
							});
						}else{
							$("#itemSecondList").css("display","none");
						}
						
					}
				});
			},
			addFormOne : function(mark){
				$(".loading-bg").show();
				var count=0;
				$("#mark").val(mark);
				var type=$("#type1").val();
//				var schools="";
//				$("#schoolLists").find("a").each(function(i){
//					if($(this).hasClass('btn-success')){
//						var cid=$(this).attr("ids");
//						schools+=cid+",";
//					}
//				});
//			    document.getElementById("schoolsId").value=schools;
			    $("#lableType").find("a").each(function(i){
			    	if($(this).hasClass('btn-success')){
			    		var lable=$(this).attr("ids");
			    		if(lable=="faceFlag"){
			    			$("#faceFlag").val(1);
			    			count++;
			    		}
			    		if(lable=="liveFlag"){
			    			$("#liveFlag").val(1);
			    			count++;
			    		}
			    		if(lable=="videoFlag"){
			    			$("#videoFlag").val(1);
			    			count++;
			    		}
			    		if(lable=="remoteFlag"){
			    			$("#remoteFlag").val(1);
			    			count++;
			    		}
			    	}else{
			    		var lable=$(this).attr("ids");
			    		if(lable=="faceFlag"){
			    			$("#faceFlag").val(0);
			    		}
			    		if(lable=="liveFlag"){
			    			$("#liveFlag").val(0);
			    		}
			    		if(lable=="videoFlag"){
			    			$("#videoFlag").val(0);
			    		}
			    		if(lable=="remoteFlag"){
			    			$("#remoteFlag").val(0);
			    		}
			    	}
			    });
			    if(count<=0){
			    	$("#errorM").html("所属标签不能为空!");
			    	$(".loading-bg").hide();
			    	return;
			    }else{
			    	$("#errorM").html('');
			    }
			    if(type=="update"){
					$("#addFormOne").validate(rule1);
			    	if($("#addFormOne").valid()){
			    		var itemOneId=$("#itemOneName").attr("marks");
				    	var itemSecondId=$("#itemSecondName").attr("marks");
				    	$("#oneId").val(itemOneId);
				    	$("#twoId").val(itemSecondId);
				    	$("#addFormOne").attr("action",rootPath+"/classType/updateClassTypeMessage").submit();
			    	}else{
			    		$(".loading-bg").hide();
			    	}
			    }else{
			    	$("#addFormOne").validate(rules);
			    	//$(".loading-bg").hide();
			    	if($("#addFormOne").valid()){
			    		$("#addFormOne").attr("action",rootPath+"/classType/addClassPackage").submit();
			    	}else{
			    		$(".loading-bg").hide();
			    	}
			    }
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
//									$.alert("模块已存在");
//								    return;
									b=false;
								}
//								else{
//									$("#ulTwo").append(newModule);
//								}
							});
							if(b){
								$("#ulTwo").append(newModule);
							}else{
								$.alert("模块已存在");
								return;
							}
						}else{
							$("#ulTwo").append(newModule);
						}
						//$("#ulTwo").append(newModule);
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
			changeCss : function(type){
				var len=$("#cmdule").val();
				if(len>0){
					if(type=='live'){
						$("#ulOne").find("li").each(function(i){
							var t=$(this).attr("type");
							if(t=="live"){
								$(this).css("display","block");
							}else{
								$(this).css("display","none");
							}
						});
					}
					if(type=='face'){
						$("#ulOne").find("li").each(function(i){
							var t=$(this).attr("type");
							if(t=="face"){
								$(this).css("display","block");
							}else{
								$(this).css("display","none");
							}
						});
					}
					if(type=='video'){
						$("#ulOne").find("li").each(function(i){
							var t=$(this).attr("type");
							if(t=="video"){
								$(this).css("display","block");
							}else{
								$(this).css("display","none");
							}
						});
					}
				}
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
					$.alert("添加模块不能为空！");
					return;
				}
				if(type=="update"){
					$("#subMessageForm").attr("action",rootPath+"/classType/updatePackage").submit();
				}else{
					$("#subMessageForm").attr("action",rootPath+"/classType/classTypeOnsal").submit();
				}
			}
			
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)