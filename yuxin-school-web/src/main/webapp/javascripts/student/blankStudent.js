(function($) {
	var crcMobileFlag   = parseInt($("#crc-mobileFlag").val()),
		crcUsernameFlag = parseInt($("#crc-usernameFlag").val()),
		$username = $('#username'),
		$mobile = $('#mobile');
	if(crcMobileFlag && !crcUsernameFlag){
		$username.prop('disabled',true);
	}
	if(!crcMobileFlag && crcUsernameFlag){
		$mobile.prop('disabled',true);
	}
	jQuery.validator.addMethod('isUsername',function(value,element){
		if(crcUsernameFlag && value){
			return this.optional(element) || /^[a-zA-Z]+[a-zA-Z0-9_]\w{2,14}$/ .test(value);
		}
		return true;
	});
	$.validator.addMethod("isMobile",function(value,element,params){
		if(crcMobileFlag && value){
			return this.optional(element) || /^09\d{8}|1[3,4,5,7,8]\d{9}$/.test(value);
		}
		return true;
	});
	
	$(".identityTypeCode").on("change",function(){
		if($(this).find("option:selected").val()=="ID_IDCARD"){
			$(".identityId").rules("add",{
            	minlength: 15,
            	required: true,
            	remote: {
            		url: rootPath+"/student/check",
            		type:"post",
            		dataType:"json",
            		data:{
            		}
            	}
            })
		}else{
			//alert(123);
			$(".identityId").rules("remove","minlength,remote");
		}
	})
	var formRules = {
		errorElement : 'p',
		errorClass : 'tips',
		focusInvalid : false,
		ignore : "",
		rules : {
			username : {
				required : false,
				isUsername : true,
				remote : {
					url : rootPath + "/student/checkFrontUserName",
					type : "post",
					dataType : "json",
					data : {
						username : function() {
							return $("#blankUsername").val();
						}
					}
				}
			},
			mobile : {
				required : false,
				isMobile : true,
				remote : {
					url : rootPath + "/student/checkUserFrontMobile",
					type : "post",
					dataType : "json",
					data : {
						mobile : function() {
							return $("#blankMobile").val();
						}
					}
				}
			},
			email : {
				email : true,
				remote : {
					url : rootPath + "/login/checkEmail/",
					type : "post",
					dataType : "json",
					data : {
						email : function() {
							return $("#email").val();
						}
					}
				}
			},
			age : {
				maxlength:2

			},
			name : {
				required : true,
				
			}
			
		},
		messages : {
			username : {
				isUsername : "<i class='icons'></i>请输入正确的用户名格式",
				remote : "<i class='icons'></i>用户名已注册"
			},
			mobile :{
				moblie : "<i class='icons'></i>请输入合法的手机号码格式",
				isMobile : "<i class='icons'></i>请输入正确的手机号",
				remote : "<i class='icons'></i>手机号已注册"
			},
			email : {
				email : "<i class='icons'></i>请输入正确的邮箱",
				remote : "<i class='icons'></i>这个邮箱还没有注册!"
			},
			age : {
				maxlength : "<i class='icons'></i>请输入合法年龄",
			},
			name : {
				required : "<i class='icons'></i>请输入姓名",
			},
			identityId : {
				required : "<i class='icons'></i>请输入证件号码",
				minlength : "<i class='icons'></i>身份证格式不正确",
				remote:"<i class='icons'></i>该身份证已注册",
			},
			


		},
		invalidHandler : function(event, validator) {
		},

		highlight : function(element) {
		},

		unhighlight : function(element) {
		},

		success : function(label) {
		},

		submitHandler : function() {
		},
		onkeyup : false
	}
	
	
	
	$(document).ready(function() {
		$("#newStu").validate(formRules);
		selectGroup1('');
	});
})(jQuery)

