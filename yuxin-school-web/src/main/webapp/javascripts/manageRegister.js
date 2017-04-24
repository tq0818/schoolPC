(function($){
	var register;
	var rules={
			errorPlacement:function(error, element){
				if(error.text())
					$.msg($(error).text());
			},
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	        	username: {
	                required: true,
	                remote: {
                		url: rootPath+"/wap_manageRegiste/checkUserName",
                		type:"post",
                		dataType:"json",
                		data:{
                			userName : function(){
                				return $(".register-panel").find("#username").val();
                			}
                		}
                	}
	            },
	            password:{
	            	 required: true,
	            	 minlength:6
	            },
	            mobile:{
	            	required:true,
	            	maxlength:11,
	            	minlength:11,
	            	digits:true,
	            	remote: {
                		url: rootPath+"/wap_manageRegiste/checkMobile",
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
                		url: rootPath+"/wap_manageRegiste/checkImgCode",
                		type:"post",
                		dataType:"json",
                		data:{
                			code : function(){
                				return $("#checkcode").val();
                			}
                		}
	            	}
	            },
	            smscode:{
	            	required: true,
	            	remote: {
                		url: rootPath+"/wap_manageRegiste/SMS/checkCode",
                		type:"post",
                		dataType:"json",
                		data:{
                			smsCode : function(){
                				return $(".register-panel").find("#smscode").val();
                			}
                		}
	            	}
	            }
	        },
	        messages: {
	        	username: {
	        		required:"用户名不能为空",
	        		remote:"<i></i>"
	        	},
	        	password:{
	        		required:"密码不能为空",
	        		minlength:"密码不能少于6位"
	        	},
	        	email: {
	        		required:"邮箱不能为空",
	        		email:"请输入正确格式的电子邮件",
	        		remote:"该邮箱已被注册"
	        	},
	        	mobile: {
	        		required:"手机号不能为空",
	        		maxlength:"请输入正确的手机号",
	        		minlength:"请输入正确的手机号",
	        		remote:"该手机号已被注册"
	        	},
	        	checkcode: {
	        		required:"验证码不能为空",
	        		remote:"验证码输入错误"
	        	},
	        	smscode:{
					required:"短信验证码不能为空",
					remote: "短信验证码不正确"
				}
	        },
	        success: function (label) {
	       
	        },
	        submitHandler: function (form) {
	            form.submit();
	        },
	        invalidHandler: function (event, validator) { //display error alert on form submit   
            	
            },
	        onkeyup: false
	}
	var Form={
			init : function(){
				var $this=this;
				$.extend($.validator.messages, {
				    required: "不能为空",
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

				$this.bindEvent()
				
				register=$("#registerForm").validate(rules);
				//验证码
				$this.refreshCaptcha();
			},
			refreshCaptcha: function(){
				$('.codeimg').attr("src", rootPath+"/captcha?refesh="+Math.random());
			},
			sendSmsCode : function(ele){
				var $this=ele;
				
				if(!register.element("#username") || !register.element("#password") || !register.element("#mobile") || !register.element("#checkcode"))
					return;
				
				if($($this).hasClass("send")){
					return;
				}else{
					$($this).addClass("send");
				}
				$.ajax({
            		url: rootPath+"/wap_manageRegiste/checkMobile",
            		type:"post",
            		data:{
            			mobile :$(".register-panel").find("#mobile").val()
            		},
            		success:function(result){
            			if(result){
            				$.ajax({
        						url: rootPath+"/wap_manageRegiste/sendSMS/"+$("#mobile").val()+"/"+$("#checkcode").val(),
        						type: "post",
        						dataType: "json",
        						async : false,
        						success : function(jsonData){
        							jsonData=eval('('+jsonData+')');
        							if(jsonData && jsonData.flag !== "success"){
        								$.msg(jsonData.message);
        								return;
        							}
        							//短信发出后，禁用按钮，开始倒计时60s，之后解开
        							$this.attr("disabled","disabled");
        							var s=60;
        							var timer=setInterval(function(){
        								if(!s || s<=0){
        									$($this).removeClass("send");
        									$this.html("重新获取");
        									$this.removeClass("btn-gray").removeAttr("disabled");
        									clearInterval(timer);
        								}else{
        									s-=1;
        									$this.html("("+s+"s)后重试");
        								}
        								
        							},1000);
        						},
        						error : function(resp,err,msg){
        							$($this).removeClass("send");
        							$.msg("呃~发生一点点意外~");
        							return;
        						}
        					})
            			}
            		}
				});
					
			},
			registerCompany : function (ele){
				var value = $(".register-panel").find("#mobile").val(),
					$this = ele;
				if(!register.element("#username") || !register.element("#password") || !register.element("#mobile") || !register.element("#checkcode") || !register.element("#smscode"))
					return;
				/*if(!$("#registerForm").valid()){
					return;
				}*/
				if($($this).hasClass("send")){
					return;
				}else{
					$($this).addClass("send");
				}
				$.ajax({
					url: rootPath+"/wap_manageRegiste",
					data:{
						username: $("#username").val(),
						password:$("#password").val(),
						mobile: $("#mobile").val(),
						smscode:$("#smscode").val(),
						utm_source:$("#utm_source").val(),
						utm_medium:$("#utm_medium").val(),
						utm_term:$("#utm_term").val(),
						utm_content:$("#utm_content").val(),
						utm_campaign:$("#utm_campaign").val(),
						utm_url:$("#utm_url").val()
					},
					type:"post",
					dataType: "json",
					success:function(json){
						var data=eval('('+json+')');
						if(data.flag === "success"){
							var html='<form action="'+rootPath+'/wap_manageRegiste/registeSuccess?url='+document.referrer+' method="post"></form>';
							$(html).appendTo("body").submit();
						}else{
							$($this).removeClass("send");
							$.msg(data.message);
						}
					},
					error: function(){
						$($this).removeClass("send");
						$.msg("服务器异常");
					}
				})
			},
			bindEvent: function(){
				var $this=this;
				//发送短信
				$("#btn-sms").on("click",function(){
					$this.sendSmsCode($(this));
				});
				//注册
				$("#register_submit").on("click",function(){
					$this.registerCompany(this);
				});
				//后退
				$("#goBack").bind('click',function(){
					if(window.history){
						window.history.go(-1);
					}else{
						window.location = document.referrer;
					}
				});
			}
	}
	$(document).ready(function(){
		Form.init();
	})
	window.Form = Form;
})(jQuery)