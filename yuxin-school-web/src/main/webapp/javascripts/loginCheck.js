// 登录验证
(function($){
	var rules={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	        	username: {
	                required: true,
	                maxlength:12
	            },
	            password:{
	            	 required: true
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
				}
	        },
	        messages: {
	        	username: {
	        		required:"用户名不能为空",
	        		maxlength:"用户名长度必须小于12"
	        	},
	        	checkcode: {
	        		required:"验证码不能为空",
	        		remote:"验证码输入错误"
	        	}
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
					min: jQuery.validator.format("请输入一个最小为{0} 的值")
				});
				//验证码
				$(".codeimg").html('<img src="'+rootPath+'/captcha?refesh='+new Date()+'" height="40" width="100" alt="点击刷新验证码" onclick="Form.refreshCaptcha()">')
				$(".login-form").validate(rules);
				//this.handelRemeberMe();
				//this.login();
			},
			refreshCaptcha: function(){
				$('.codeimg').html('<img src="'+rootPath+'/captcha?refesh='+new Date()+'" height="40" width="100" alt="点击刷新验证码" onclick="Form.refreshCaptcha()">')
			},
			handelRemeberMe: function(){
				 $("#rememberMe").bind("click",function(){
					  if($(this).attr("checked")){
						  if($(":text[name=username]").val()){
							  $.cookie("username",$(":text[name=username]").val());
						  }else{
							  alert("请输入用户名!");
							  $(this).removeAttr("checked").parent().removeClass("checked");
							  return false;
						  }
					  }else{
						  $.cookie("username","",{ expires: -1 });
					  }
				  })
				  if($.cookie("username")){
					  $(":text[name=username]").val($.cookie("username"));
					  $("#rememberMe").attr("checked","checked").parent().addClass("checked");
				  }
			},
			login: function(){
				 $('.login-form input').keypress(function (e) {
			            if (e.which == 13) {
			                if ($('.login-form').validate().form()) {
			                    $('.login-form').submit();
			                }
			                return false;
			            }
			        });
			}
	}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)