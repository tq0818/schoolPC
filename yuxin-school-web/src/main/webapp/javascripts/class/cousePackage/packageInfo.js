/**
 * author zhang.zx
 */
(function($){
	var rules={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	        	categoryCode:{
	        		required: true
	        	},
	            name: {
	                minlength: 2,
	                maxlength: 20,
	                required: true,
	                remote: {
                		url: rootPath+"/classPackage/checkedClassPackageName",
                		type:"post",
                		dataType:"json",
                		data:{
                			name : function(){
                				return $("#packageName").val();
                			},
                		 error:function(arg,arg1,arg2){
                			 $(".loading-bg").hide();
                		 }
                		}
                	}
	            },
	            realPrice:{
	            	required:true,
	            	number:true,
	            	max:99999
	            },
	            baseNum:{
	            	required: true,
	            	digits:true,
	            	number:true,
	            	max:99999
	            }
	        },
	        messages: {
	        	name:{
	        		remote:"该课程包名称已存在"
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
	        	categoryCode:{
	        		required: true
	        	},
	            name: {
	                minlength: 2,
	                maxlength: 20,
	                required: true
	            },
	            realPrice:{
	            	required:true,
	            	number:true,
	            	max:99999
	            },
	            baseNum:{
	            	required: true,
	            	digits:true,
	            	number:true,
	            	max:99999
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
				$selectMenu("course_package");
				$chooseMenu("baseCode");
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
				
				$(".prices").bind("keyup",function(event){
	    			//先把非数字的都替换掉，除了数字和. 
	    			$(this).val($(this).val().replace(/[^\d.]/g,""));
	    	        //必须保证第一个为数字而不是. 
	    	        $(this).val($(this).val().replace(/^\./g,"0."));
	    	        //保证只有出现一个.而没有多个. 
	    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	    	        //保证.只出现一次，而不能出现两次以上
	    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
	    		})
	    		$(".prices").bind("blur",function(event){
	    			//先把非数字的都替换掉，除了数字和. 
	    			$(this).val($(this).val().replace(/[^\d.]/g,""));
	    			var value=$(this).val();
	     	        var is=false;
	     	        for (var i = 0; i < value.length; i++) {
	     	            var  item =  value.charAt(i);
	     	           	if("."==item){
	     	           		is=true;
	     	           	}
	     	        }
	     	        if(value!=null||value!=""){
	     	        	if(!is){
	     	        		$(this).val($(this).val()+".00");	
	     	        	}
	     	        } 
	    	        //必须保证第一个为数字而不是. 
	    	        $(this).val($(this).val().replace(/^\./g,"0."));
	    	        //保证只有出现一个.而没有多个. 
	    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	    	        //保证.只出现一次，而不能出现两次以上
	    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
	    	        //保留小数点后两位
	    	        $(this).val($(this).val().substring(0,$(this).val().indexOf(".")+3));
	    		})
	    		//课程设置框只能输入数字
				$(".settingcount").keyup(function(){     
			        var tmptxt=$(this).val();     
			        $(this).val(tmptxt.replace(/\D/g,''));     
			    }).bind("paste",function(){     
			        var tmptxt=$(this).val();     
			        $(this).val(tmptxt.replace(/\D/g,''));     
			    }).css("ime-mode", "disabled"); 
				//加载一级分类
				$("#firstTypeList").html("");
				$.ajax({
					url : rootPath + "/classPackageCategory/queryCategoryList",
					type : "post",
					dataType : "json",
					data:{"leavl":"first","status":1},
					success : function(result) {
						var code;
						var markId=$("#packageId").val();
						if(markId && markId!=""){
							var codes=$("#categoryCode").val();
							if(codes && codes.length==3){
								code=codes;
							}else if(codes && codes.length==6){
								code=codes.substring(0,3);
							}else if(codes && codes.length==9){
								code=codes.substring(0,3);
							}
						}else{
							code=$.cookie("onecode");
						}
						var html="";
						if(code){
							$.each(result,function(i,item){
								if(code==item.code){
									html+='<option value='+item.code+' selected="selected" ids='+item.id+'>'+item.name+'</option>';
								}else{
									html+='<option value='+item.code+' ids='+item.id+'>'+item.name+'</option>';
								}
								
							});
						}else{
							$.each(result,function(i,item){
								if(i==0){
									html+='<option value='+item.code+' selected="selected" ids='+item.id+'>'+item.name+'</option>';
								}else{
									html+='<option value='+item.code+' ids='+item.id+'>'+item.name+'</option>';
								}
							});
						}
						$("#firstTypeList").html(html);
						Form.queryItemSecond();
					}
				});
				//点击保存信息
				$(".save_baseInfo").on('click',function(){
					var code=$("#categoryCode").val();
			    	var firstTypeList=$("#firstTypeList").val();
			    	var secondeTypeList=$("#secondeTypeList").val();
			    	var thirdTypeList=$("#thirdTypeList").val();
			    	if(firstTypeList){
			    		code=firstTypeList;
			    	}
			    	if(secondeTypeList){
			    		code=secondeTypeList;
			    	}
			    	if(thirdTypeList){
			    		code=thirdTypeList;
			    	}
			    	$("#categoryCode").val(code);
					var lable=$("#lableType").val();
					var mark=$(this).attr("mark");
					 if(lable=="edit"){
						 $("#addFormOne").remove('rules');
						$("#addFormOne").validate(rule1);
				    	if($("#addFormOne").valid()){
				    		$.ajax({
				    			url: rootPath+"/classPackage/checkUpdateClassPackageName",
		                		type:"post",
		                		dataType:"json",
		                		data:{"name":$("#packageName").val(),"id":$("#packageId").val()},
		                		success:function(b){
		                			if(b){
		                				$.ajax({
		        							url : rootPath+"/classPackage/updateClassPackageInfo",
		        							type : "post",
		        							data : $("#addFormOne").serialize(),
		        							dataType : "json",
		        							beforeSend:function(XMLHttpRequest){
		        					            $(".loading-bg").show();
		        					        },
		        							success : function(result) {
		        								$.msg("保存信息成功!",function(){
		        									//保存并继续
			        								if("continue"==mark){
			        									window.location.href=rootPath+"/classPackage/manageClassPackage/"+result+"/edit";
			        								}else{
			        									window.location.href=rootPath+"/classPackage/manageBaseInfo/edit?id="+result;
			        								}
		        								});
		        								$(".loading-bg").hide();
		        							}
		        						});
		                			}else{
		                				$.msg("课程包名称已存在");
		                			}
		                		}
		                	})
				    	}else{
				    		$(".loading-bg").hide();
				    	}
				    }else{
				    	$("#addFormOne").validate(rules);
				    	if($("#addFormOne").valid()){
				    		$.ajax({
				    			url: rootPath+"/classPackage/checkedClassPackageName",
		                		type:"post",
		                		dataType:"json",
		                		data:{"name":$("#packageName").val()},
		                		success:function(b){
		                			if(b){
		                				$.ajax({
		        							url : rootPath+"/classPackage/saveBaseInfo",
		        							type : "post",
		        							data : $("#addFormOne").serialize(),
		        							dataType : "json",
		        							beforeSend:function(XMLHttpRequest){
		        					            $(".loading-bg").show();
		        					        },
		        							success : function(result) {
		        								$.msg("保存信息成功!",function(){
		        									//保存并继续
			        								if("continue"==mark){
			        									window.location.href=rootPath+"/classPackage/manageClassPackage/"+result+"/edit";
			        								}else{
			        									window.location.href=rootPath+"/classPackage/manageBaseInfo/edit?id="+result;
			        									$(".loading-bg").hide();
			        								}
		        								});
		        							}
		        						});
		                			}else{
		                				$.msg("课程包名称已存在");
		                			}
		                		}
				    		});
					    }else{
				    		$(".loading-bg").hide();
				    	}
			    	}
				});
				this.queryCourseProtocolConfig();
			},
			/**
			 * 查询课程协议
			 * @param callback 扩展以后如果再添加dom元素的时候可以执行该方法
			 */
			queryCourseProtocolConfig : function(callback){
				var pHtml = '',
					optionHtml = '<option value="0"></option>',
					selected = false,
					courseProtocols,
					courseProtocol,
					protocolId = $('.heading').data('protocol');
				$.ajax({
					url : rootPath + "/classType/getCtPotocolSet",
					type : "post",
					data : {type : 1,functionCode : 'CLASSPACKAGE_POTOCOL_SET'},
					success : function(result){
						if(!result || !result.open) return;
						courseProtocols = result.courseProtocolConfig;
						for(var i=0; i<courseProtocols.length; i++){
							courseProtocol = courseProtocols[i];
							selected = protocolId == courseProtocol.id;
							optionHtml += '<option value="'+courseProtocol.id+'" '+ (selected?'selected':'') +'>'+ courseProtocol.name +'</option>';
						}
						
						pHtml = '<p class="c">'+
				                    '<span class="c-title">课程包协议</span>'+
				                    '<span class="c-content">'+
				                        '<select id="courseProtocol" name="protocolId" style="width:189px;min-width: 180px;">'+ optionHtml +'</select>'+
				                    '</span>'+
				                '</p>';
						$('.isrecomment').before(pHtml);
						
						if(callback){
							callback();
						}
					}
				});
			},
			queryItemSecond : function (){
				var code=$("#firstTypeList").val();
				$("#secondeTypeList").html('');
				$.ajax({
					url : rootPath + "/classPackageCategory/queryCategoryList",
					type : "post",
					data : {"leavl":"second","code":code,"status":1},
					dataType : "json",
					success : function(result) {
						var code;
						var markId=$("#packageId").val();
						if(markId && markId!=""){
							var codes=$("#categoryCode").val();
							if(codes && codes.length==6){
								code=codes;
							}else if(codes && codes.length==9){
								code=codes.substring(0,6);
							}
						}else{
							code=$.cookie("twocode");
						}
						
						var html="<option value=''>请选择</option>";
						$.each(result,function(i,item){
							if(code && code==item.code){
								html+='<option value='+item.code+' selected="selected" ids='+item.id+'>'+item.name+'</option>';
							}else{
								html+='<option value='+item.code+' ids='+item.id+'>'+item.name+'</option>';
							}
						});
						$("#secondeTypeList").html(html);
						Form.querythirdList();
					}
				});
			},
			querythirdList : function(){
				var code;
				var firstCode=$("#firstTypeList").val();
				var secondCode=$("#secondeTypeList").val();
				if(firstCode){
					code=firstCode;
				}
				if(secondCode){
					code=secondCode;
				}
				$("#thirdTypeList").html('');
				$.ajax({
					url : rootPath + "/classPackageCategory/queryCategoryList",
					type : "post",
					dataType : "json",
					data : {"leavl":"third","code":code,"status":1},
					success : function(result) {
						var code;
						var markId=$("#packageId").val();
						if(markId && markId!=""){
							var codes=$("#categoryCode").val();
							if(codes.length==9){
								code=codes;
							}
						}else{
							code=$.cookie("threecode");
						}
						var html="<option value=''>请选择</option>";
						$.each(result,function(i,item){
							if(code && code==item.code){
								html+='<option value='+item.code+' selected="selected" ids='+item.id+'>'+item.name+'</option>';
							}else{
								html+='<option value='+item.code+' ids='+item.id+'>'+item.name+'</option>';
							}
						});
						$("#thirdTypeList").html(html);
					}
				});
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)