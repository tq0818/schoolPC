var secs =60;
var t;//短信
var m;//邮箱
//倒计时发送短信验证码
function timer(){
	secs--; 
	$(".sendMobileCode").val("倒计时：[" + secs + "]");
	if(secs == 0){
		window.clearInterval(t);//清楚定时任务
		$(".sendMobileCode").bind("click",sendMobile);
		$(".sendMobileCode").removeAttr("readonly");
		secs = 60;
		$(".sendMobileCode").val("发送验证码");
	}
}
//倒计时发送邮箱验证码
function timerM(){
	secs--; 
	$(".sendMaileCode").val("倒计时：[" + secs + "]");
	if(secs == 0){
		window.clearInterval(m);//清楚定时任务
		$(".sendMaileCode").bind("click",sendMobile);
		$(".sendMaileCode").removeAttr("readonly");
		secs = 60;
		$(".sendMaileCode").val("发送验证码");
	}
}
$(function() {

	//修改邮箱
	$("#UEmail").click(function(){
		 $('.add-subs-layer-bg').fadeIn(200,function(){
             $('.replace-email').fadeIn(200);
         });
	});
	//修改手机号
	$("#UMobile").click(function(){
		 $('.add-subs-layer-bg').fadeIn(200,function(){
             $('.replace-mobile').fadeIn(200);
         });
	});
	
	
	//修改姓名
	$("#URealName").click(function(){
		
		if($("#URealName").val()=="修改"){
			$("#URealName").prev("[type=text]").attr("class","input-edit");
			$("#URealName").prev("[type=text]").removeAttr("readonly");
		}
		if($("#URealName").val()=="保存"){
			
			$("#URealName").prev("[type=text]").attr("class","readonly");
			$("#URealName").prev("[type=text]").attr("readonly","readonly");
			$("#URealName").attr("class","btn btn-sm btn-default");
			$("#URealName").val("修改");
			$.ajax({
				url : rootPath+"/users/updateAccountInfo",
				type : "post",
				data : {"companyId":$("#companyId").val(),"realName":$("[name=realName]").val()},
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success : function(data){
					$("#HRealName").val($("[name=realName]").val());
					if(data="success"){
						$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					}
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
			}
	});
	
	//修改紧急联系人姓名
	$("#UEmergencyContactName").click(function() {
		if($("#UEmergencyContactName").val()=="修改"){
			$("#UEmergencyContactName").prev("[type=text]").attr("class","input-edit");
			$("#UEmergencyContactName").prev("[type=text]").removeAttr("readonly");
		}
		if($("#UEmergencyContactName").val()=="保存"){
			$("#UEmergencyContactName").prev("[type=text]").attr("class","readonly");
			$("#UEmergencyContactName").prev("[type=text]").attr("readonly","readonly");
			$("#UEmergencyContactName").attr("class","btn btn-sm btn-default");
			$("#UEmergencyContactName").val("修改");
			$.ajax({
				url : rootPath+"/users/updateAccountInfo",
				type : "post",
				data : {"companyId":$("#companyId").val(),"emergencyContactName":$("[name=emergencyContactName]").val()},
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success : function(data){
					$("#HEmergencyContactName").val($("[name=emergencyContactName]").val());
					if(data="success"){
						$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					}
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		}
	});
	
	//修改紧急联系人电话
	$("#UEmergencyContactMobile").click(function() {
		if($("#UEmergencyContactMobile").val()=="修改"){
			$("#UEmergencyContactMobile").prev("[type=text]").attr("class","input-edit");
			$("#UEmergencyContactMobile").prev("[type=text]").removeAttr("readonly");
		}
		if($("#UEmergencyContactMobile").val()=="保存"){
			$("#UEmergencyContactMobile").prev("[type=text]").attr("class","readonly");
			$("#UEmergencyContactMobile").prev("[type=text]").attr("readonly","readonly");
			$("#UEmergencyContactMobile").attr("class","btn btn-sm btn-default");
			$("#UEmergencyContactMobile").val("修改");
			$.ajax({
				url : rootPath+"/users/updateAccountInfo",
				type : "post",
				data : {"companyId":$("#companyId").val(),"emergencyContactMobile":$("[name=emergencyContactMobile]").val()},
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success : function(data){
					$("#HEmergencyContactMobile").val($("[name=emergencyContactMobile]").val());
					if(data="success"){
						$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					}
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		}
	});
	//修改姓名
	$("#UQqNo").click(function() {
		if($("#UQqNo").val()=="修改"){
			$("#UQqNo").prev("[type=text]").attr("class","input-edit");
			$("#UQqNo").prev("[type=text]").removeAttr("readonly");
		}
		if($("#UQqNo").val()=="保存"){
			$("#UQqNo").prev("[type=text]").attr("class","readonly");
			$("#UQqNo").prev("[type=text]").attr("readonly","readonly");
			$("#UQqNo").attr("class","btn btn-sm btn-default");
			$("#UQqNo").val("修改");
			$.ajax({
				url : rootPath+"/users/updateAccountInfo",
				type : "post",
				data : {"companyId":$("#companyId").val(),"qqNo":$("[name=qqNo]").val()},
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success : function(data){
					$("#HQqNo").val($("[name=qqNo]").val());
					if(data="success"){
						$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					}
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		}
	});
	//更换图片验证码
	$("#captcha").click(function(){
		$("#captcha").attr("src", rootPath+"/captcha?" + new Date());
	});
	$("#maileCaptcha").click(function(){
		$("#maileCaptcha").attr("src", rootPath+"/captcha?" + new Date());
	});
	//点击返回隐藏
	 $('.btn-cancel').on('click',function(){
         $(this).parents('.add-subs-layer').fadeOut(200,function(){
             $('.add-subs-layer-bg').fadeOut(200);
         });
        $(".phoneNum").val("");
 		$(".mobileCode").val("");
 		$(".picCode").val("");
 		$(".newMaile").val("");
		$(".mailePicCode").val("");
		$(".maileCode").val("");
     });
	//发送短信验证
	$(".sendMobileCode").bind("click",sendMobile);
	
	//保存新手机号
	$(".saveNewMobile").click(function(){
		var phoneNum = $(".phoneNum").val();
		var mobileCode = $(".mobileCode").val();
		var captcha = $(".picCode").val();
		if(phoneNum!=""&&mobileCode!=""&&captcha!=""){
			$.ajax({
				url : rootPath+"/users/checkMobileCode",
				type : "post",
				data : {"smsCode":mobileCode},
				success : function(data){
					if(data == "smsCodeError"){
						$(".mobileErrorInfo").html("请输入正确的手机验证码");
					}
					if(data == "success"){
						$.ajax({
							url : rootPath+"/users/updateAccountInfo",
							type : "post",
							data : {"companyId":$("#companyId").val(),"mobile":phoneNum},
							success : function(data){
								if(data="success"){
									$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
									location.href=rootPath+"/users/accountInfo";
								}
							}
						});
					}
				}
			});
		}else{
			$(".mobileErrorInfo").html("信息不完全");
		}
		
	});
	
	//发送邮箱验证
	$(".sendMaileCode").bind("click",sendMail);
	

	//保存新邮箱
	$(".saveMail").click(function(){
		var newMaile = $(".newMaile").val();
		var maileCode = $(".maileCode").val();
		var captcha = $(".mailePicCode").val();
		if(newMaile!=""&&maileCode!=""&&captcha!=""){
			$.ajax({
				url : rootPath+"/users/checkMobileCode",
				type : "post",
				data : {"smsCode":maileCode},
				success : function(data){
					if(data == "smsCodeError"){
						$(".MailErrorInfo").html("验证码不正确");
					}
					if(data == "success"){
						$.ajax({
							url : rootPath+"/users/updateAccountInfo",
							type : "post",
							data : {"companyId":$("#companyId").val(),"email":newMaile},
							success : function(data){
								if(data="success"){
									$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
									location.href=rootPath+"/users/accountInfo";
								}
							}
						});
					}
				}
			});
		}else{
			$(".MailErrorInfo").html("信息不完全");
		}
		
	});
});
//发送邮箱验证码
function sendMail(){
	var newMaile = $(".newMaile").val();
	var captcha = $(".mailePicCode").val();
	var oldEmail = $("#HEmail").val();
	 var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(newMaile == oldEmail){
		$(".MailErrorInfo").html("邮箱未变更，发送失败");
	}else{
		if(myreg.test(newMaile)){
			$.ajax({
				url : rootPath+"/users/checkEmail",
				type : "post",
				data : {"email":newMaile},
				success : function(data){
					if(data){
						$.ajax({
							url : rootPath+"/users/sendMaileCode",
							type : "post",
							data : {"newMaile":newMaile,"captcha":captcha},
							success : function(data){
								var json =  eval('(' + data + ')'); 
								$(".MailErrorInfo").html(json.message);
								if(json.flag == "success"){
									$(".sendMaileCode").unbind("click");
									$(".sendMaileCode").attr("readonly","readonly");
									m = window.setInterval("timerM()",1000);//启用定时倒计
								}
								return;
							}
						});
					}else{
						$(".MailErrorInfo").html("该邮箱已被注册");
					}
				}
			});
			
		}else{
			$(".MailErrorInfo").html("邮箱格式不正确");
		}
		
	}
}
//发送短信验证码
function sendMobile(){
	
	var phoneNum = $(".phoneNum").val();
	var captcha = $(".picCode").val();
	var oldPhoneNum = $("#HMobile").val();
	if(!phoneNum){
		$(".mobileErrorInfo").html("请输入手机号");
		return false;
	}
	if(phoneNum == oldPhoneNum){
		$(".mobileErrorInfo").html("手机号未变更，发送失败");
	}else{
		$.ajax({
			url : rootPath+"/users/checkMobile",
			type : "post",
			data : {"mobile":phoneNum},
			success : function(data){
				if(data){
					$.ajax({
						url : rootPath+"/users/sendMobileCode",
						type : "post",
						data : {"phoneNum":phoneNum,"captcha":captcha},
						success : function(data){
							var json =  eval('(' + data + ')'); 
							$(".mobileErrorInfo").html(json.message);
							if(json.flag == "success"){
								$(".sendMobileCode").unbind("click");
								$(".sendMobileCode").attr("readonly","readonly");
								t = window.setInterval("timer()",1000);//启用定时倒计
							}
							return;
						}
					});
				}else{
					$(".mobileErrorInfo").html("该手机号已被注册");
				}
			}
		});
		
	}
		


}

