// 忘记密码相关内容
(function($){
	var rules={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: true, 
	        ignore: "",
	        rules: {
	        	userName: {
	                required: true,
	                maxlength:16,
	                remote: {
                		url: rootPath+"/users/checkUserName",
                		type:"post",
                		dataType:"json",
                		data:{
                			userName : function(){
                				return $("#userName").val();
                			}
                		}
                	}
	            },
	            password:{
	            	 required: true,
	            	 minlength:6
	            },
	            confirmPassword:{
	            	 required: true,
	            	 minlength:6,
	            	 equalTo:"#password"
	            },
	            mobile:{
	            	required:true,
	            	maxlength:11,
	            	minlength:11,
	            	digits:true,
	            	remote: {
                		url: rootPath+"/users/checkMobileForFind",
                		type:"post",
                		dataType:"json",
                		data:{
                			mobile : function(){
                				return $("#mobile").val();
                			}
                		}
                 }
	            },
	            checkcode:{
	            	required: true,
	            	remote: {
                		url: rootPath+"/register/checkCode",
                		type:"post",
                		dataType:"json",
                		data:{
                			code : function(){
                				return $("#checkcode").val();
                			}
                		}
	            	}
	            },
	            sms : {
	            	required: true,
					remote: {
						url:rootPath+"/register/SMS/checkCode",
						type:"post",
						dataType: "json",
						data: {
							smsCode:function(){
								return $("#register_sms2").val();
							}
						}
					}
				},
				domain : {
					required: true,
					minlength:5,
					remote: {
						url:rootPath+"/users/checkDomain",
						type:"post",
						dataType: "json",
						data: {
							domain:function(){
								return $("#domain").val();
							}
						}
					}
				}
	        },
	        messages: {
	        	userName: {
	        		required:"用户名不能为空",
	        		maxlength:"用户名长度必须小于16",
	        		remote:"用户名不存在"
	        	},
	        	
	        	mobile: {
	        		required:"手机号不能为空",
	        		isMobile:true,
	        		maxlength:"请输入正确的手机号",
	        		minlength:"请输入正确的手机号",
	        		remote:"手机号不存在"
	        	},
	        	checkcode: {
	        		required:"验证码不能为空",
	        		remote:"验证码输入错误"
	        	},
	        	sms :{
	        		required:"请输入验证码",
					remote: "<i></i>短信验证码不正确"
				},
				domain: {
					required:"公司域名不能为空",
					minlength:"域名不能小于5个字",
					remote:"域名不存在"
				},
				confirmPassword: {
					equalTo:"密码输入不一致"
				}
	        },
	        success: function (label) {
	        	if(label.prev().attr("id")=="checkcode"){
	        		$("#btn-sms").removeClass("btn-gray").removeAttr("disabled");
				}
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
				$(".footer").addClass("footer-fixed");
				
				var userId;
				 
				//验证码
				$(".r-img").html('<img src="'+rootPath+'/captcha?refesh='+new Date()+'" height="40" width="100" alt="点击刷新验证码" onclick="Form.refreshCaptcha()">')
				$("#registerForm").validate(rules);
				$("#registerFormTwo").validate(rules);
			},
			refreshCaptcha: function(){
				$('.r-img').html('<img src="'+rootPath+'/captcha?refesh='+new Date()+'" height="40" width="100" alt="点击刷新验证码" onclick="Form.refreshCaptcha()">')
			},
			sendSmsCode : function(ele){
				var $this=$(ele);
				$.ajax({
					url : rootPath+"/users/isExixts",
					type : "post",
					data : {"username":$("#userName").val(),"domain":$("#domain").val(),"mobile":$("#mobile").val()},
					success : function(data){
						if(data){
							//校验通过
							$.ajax({
								url: rootPath+"/users/sendSMS/"+$("#mobile").val()+"/"+$("#checkcode").val(),
								type: "post",
								dataType: "json",
								async : false,
								success : function(jsonData){
									jsonData=eval('('+jsonData+')');
									if(jsonData && jsonData.flag=="success"){
										$(".msg-code").find("span.tips").html('').html(jsonData.message).show("1000",function(){
											setTimeout('$(".msg-code").find("span.tips").hide()',3000);
											
										});
										
									}else{
										$(".msg-code").find("span.tips").html('').html(jsonData.message).show("1000",function(){
											setTimeout('$(".msg-code").find("span.tips").hide()',3000);
										});
										return;
									}
									//短信发出后，禁用按钮，开始倒计时60s，之后解开
									$this.addClass("btn-gray").attr("disabled","disabled");
									$("#checkcode").attr("disabled","disabled");
									var s=60;
									var timer=setInterval(function(){
										if(!s || s<=0){
											$this.val("重新获取");
											$this.removeClass("btn-gray").removeAttr("disabled");
											$("#checkcode").removeAttr("disabled");
											clearInterval(timer);
										}else{
											s-=1;
											$this.val("("+s+"s)后重试");
										}
										
									},1000);
									$(".t").html("").hide();
								},
								error : function(resp,err,msg){
									console.log(resp);
									alert("呃~发生一点点意外~");
									return;
								}
							})
						}else{
							$(".t").html("您输入的域名，用户名，手机号不匹配").show();
							setTimeout('$(".t").html("");',3000);
							return;
						}
					}
				});
					
			},
			registerCompany : function (){
				
					 if($("#registerForm").valid()){
						 $.ajax({
								url : rootPath+"/users/isExixts",
								type : "post",
								data : {"username":$("#userName").val(),"domain":$("#domain").val(),"mobile":$("#mobile").val()},
								success : function(data){
									if(data){
										//校验通过
										$(".s1").removeClass("hover");
										$(".s1").addClass("active");
										$(".s2").addClass("hover");
										$("#registerStepOne").css("display","none");
										$("#registerStepTwo").css("display","block");
										//根据用户名查询出用户Id
										$.ajax({
											url : rootPath+"/users/getUserIdByName",
											type : "post",
											data : {"name":$("#userName").val()},
											success : function(data){
												userId = data;
											}
										});
									}else{
										$(".t").html("您输入的域名，用户名，手机号不匹配").show();
										return;
									}
								}
							});
										
									
						
					}else{
						
					}
				
			},
			registerCompanyContent : function(){
				if($("#registerFormTwo").valid()){
					$.ajax({
						url : rootPath+"/users/newPwd",
						type : "post",
						data : {"pwd":$("#password").val(),"userName":$("#userName").val(),"id":userId},
						success : function(data){
							
									if(data == 'success'){
										$(".s1").addClass("active");
										$(".s2").removeClass("hover");
										$(".s2").addClass("active");
										$(".s3").addClass("hover");
										$("#registerStepOne").css("display","none");
										$("#registerStepTwo").css("display","none");
										$("#registerStepThree").css("display","block");
									}
						}
					});
				}else{
					//校验不通过
					 
				}
			}
	}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)