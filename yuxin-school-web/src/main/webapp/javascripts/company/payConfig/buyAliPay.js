$(function(){
	//更换图片验证码
	$("#captcha").click(function(){
		$("#captcha").attr("src", rootPath+"/captcha?" + new Date());
	});
	//上传支付宝二维码
	$("#zfbImg").click(function(){
		$(this).prev("#imgDataZfb").click();
	});
	
	//上传微信二维码
	$("#wxImg").click(function(){
		$(this).prev("#imgDataWx").click();
	});
	
	$("#save").click(function(){
		var types = "";//原有支付类型的集合
		var saveType = $("#saveType").val();
		var payType = $("#payType").val();
		var isHave = true;
		if(payType){
			types = payType.split(',');
			for(var i=0;i<types.length;i++){
				if(types[i] == saveType){
					isHave = false;
				}
			}
			if(isHave){
				payType+=","+saveType;
			}
		}else{
			payType+=saveType;
		}
		types = payType.split(',');
		payType = "";
		for(var i=0;i<types.length;i++){
			if(saveType =='PAY_TYPE_ZFB'){
				if(types[i] !='PAY_TYPE_GRDB'){
					if(payType){
						payType+=","+types[i];
					}else{
						payType+=types[i];
					}
				}
			}
			else if(saveType =='PAY_TYPE_GRDB'){
				if(types[i] !='PAY_TYPE_ZFB'){
					if(payType){
						payType+=","+types[i];
					}else{
						payType+=types[i];
					}
				}
			}
			else if(saveType =='PAY_TYPE_SXY'){
				if(types[i] !='PAY_TYPE_ZFBWY'){
					if(payType){
						payType+=","+types[i];
					}else{
						payType+=types[i];
					}
				}
			}
			else if(saveType =='PAY_TYPE_ZFBWY'){
				if(types[i] !='PAY_TYPE_SXY'){
					if(payType){
						payType+=","+types[i];
					}else{
						payType+=types[i];
					}
				}
			}else{
				if(payType){
					payType+=","+types[i];
				}else{
					payType+=types[i];
				}
			}
		}
		console.log(payType);
		var mobileCode = $.trim($("#mobileCode").val());
		var captcha = $.trim($("#picCode").val());
		
		//参数部分
		var zfbAccount = $.trim($("#zfbAccount").val());
		var zfbPartnerId = $.trim($("#zfbPartnerId").val());
		var zfbKey = $.trim($("#zfbKey").val());
		
		var bankCompanyName = $.trim($("#bankCompanyName").val());
		var bankAccountNumber = $.trim($("#bankAccountNumber").val());
		var bankAccountName = $.trim($("#bankAccountName").val());
		
		var personName = $.trim($("#personName").val());
		var personZfbAccount = $.trim($("#personZfbAccount").val());
		var personZfbUrl = $("#personZfbUrl").val();
		
		var personWxAccount = $.trim($("#personWxAccount").val());
		var personWxUrl = $("#personWxUrl").val();
		
		var appId = $.trim($("#appId").val());
		var appSecret = $.trim($("#appSecret").val());
		var paySaleId = $.trim($("#paySaleId").val());
		var paySaleKey = $.trim($("#paySaleKey").val());
		
		var sxyCode = $.trim($("#sxyCode").val());
		var sxyKey = $.trim($("#sxyKey").val());
		
		var companyId = $("#companyId").val();
		//end
		var param = "";
		var isTrue = true;
		param+="&payType="+payType;
		if(saveType == 'PAY_TYPE_WX_GZH'){
			if(!appId){
				$('<div class="c-fa">'+ "请输入AppID" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				if(appId.length!=18){
					$('<div class="c-fa">'+ "AppID长度为18位" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&gzhWxAppID="+appId;
					isTrue = true && isTrue;
				}
			}
			if(!appSecret){
				$('<div class="c-fa">'+ "请输入AppSecret" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				if(appSecret.length!=32){
					$('<div class="c-fa">'+ "AppSecret长度为32位" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&gzhWxAppSecret="+appSecret;
					isTrue = true && isTrue;
				}
			}
			if(!paySaleId){
				$('<div class="c-fa">'+ "请输入微信支付商户号" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				if(paySaleId.length!=10){
					$('<div class="c-fa">'+ "微信支付商户号长度为10位" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&gzhWxMchID="+paySaleId;
					isTrue = true && isTrue;
				}
			}
			if(!paySaleKey){
				$('<div class="c-fa">'+ "请输入微信支付商户号KEY" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				if(paySaleKey.length!=32){
					$('<div class="c-fa">'+ "微信支付商户号KEY长度为32位" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&gzhWxKey="+paySaleKey;
					isTrue = true && isTrue;
				}
			}
		}
		if(saveType == 'PAY_TYPE_ZFB' || saveType == 'PAY_TYPE_GRDB'){
			if(!zfbAccount){
				$('<div class="c-fa">'+ "请输入支付宝账号" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&zfbAccount="+zfbAccount;
				isTrue = true && isTrue;
			}
			if(!zfbPartnerId){
				$('<div class="c-fa">'+ "请输入合作者身份PID" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&zfbPartnerId="+zfbPartnerId;
				isTrue = true && isTrue;
			}
			if(!zfbKey){
				$('<div class="c-fa">'+ "请输入安全校验码KEY" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&zfbKey="+zfbKey;
				isTrue = true && isTrue;
			}
		}
		if(saveType == 'PAY_TYPE_ZFB_ZZ'){
			if(!personName){
				$('<div class="c-fa">'+ "请输入账户姓名" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&personName="+personName;
				isTrue = true && isTrue;
			}
			if(!personZfbAccount){
				if(!personZfbUrl){
					$('<div class="c-fa">'+ "请上传支付宝二维码或填写支付宝账号" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&personZfbUrl="+personZfbUrl;
					isTrue = true && isTrue;
				}
			}else{
				param+="&personZfbAccount="+personZfbAccount;
				isTrue = true && isTrue;
				if(!personZfbUrl){
					
				}else{
					param+="&personZfbUrl="+personZfbUrl;
				}
			}
			
		}
		if(saveType == 'PAY_TYPE_REMIT'){
			if(!bankCompanyName){
				$('<div class="c-fa">'+ "请输入开户名称" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&bankCompanyName="+bankCompanyName;
				isTrue = true && isTrue;
			}
			if(!bankAccountNumber){
				$('<div class="c-fa">'+ "请输入银行账号" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&bankAccountNumber="+bankAccountNumber;
				isTrue = true && isTrue;
			}
			if(!bankAccountName){
				$('<div class="c-fa">'+ "请输入开户银行" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&bankAccountName="+bankAccountName;
				isTrue = true && isTrue;
			}
		}
		if(saveType == 'PAY_TYPE_WX_PERSON'){
			if(!personWxAccount){
				$('<div class="c-fa">'+ "请输入微信用户名" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&personWxAccount="+personWxAccount;
				isTrue = true && isTrue;
			}
			if(!personWxUrl){
				$('<div class="c-fa">'+ "请上传微信二维码" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&personWxUrl="+personWxUrl;
				isTrue = true && isTrue;
			}
		}
		if(saveType == 'PAY_TYPE_SXY'){
			if(!sxyCode){
				$('<div class="c-fa">'+ "请输入首信易支付商户号" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&sxyCode="+sxyCode;
				isTrue = true && isTrue;
			}
			if(!sxyKey){
				$('<div class="c-fa">'+ "请输入首信易支付商户号KEY" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				isTrue = false && isTrue;
				return;
			}else{
				param+="&sxyKey="+sxyKey;
				isTrue = true && isTrue;
			}
		}
		if(isTrue){
			var payComId = $("#payConfigCompanyId").val();
			param+="&companyId="+companyId;
			console.log(param);
			if(payComId){
				if(!captcha){
					$('<div class="c-fa">'+ "请输入图形验证码" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return;
				}
				if(!mobileCode){
					$('<div class="c-fa">'+ "请输入手机验证码" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return;
				}
				updateInfo(mobileCode,param);
			}else{
				saveInfo(param);
			}
			
		}else{
			return;
		}
	});
	
	//获取手机验证码
	$("#getCode").click(function(){
		var phoneNum = $("#mobile").val();
		var captcha = $.trim($("#picCode").val());
		if(!captcha){
			$('<div class="c-fa">'+ "请输入图形验证码 "+'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			return;
		}
		$.ajax({
			url : rootPath + "/payConfig/sendMobileCode",
			type : "post",
			data : {"phoneNum":phoneNum,"captcha":captcha},
			success : function(data){
				var json =  eval('(' + data + ')'); 
				$('<div class="c-fa">'+ json.message +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				return;
			}
		});
	});
})
function updateInfo(mobileCode,param){
	$.ajax({
		url : rootPath+"/users/checkMobileCode",
		type : "post",
		data : {"smsCode":mobileCode},
		beforeSend : function(XMLHttpRequest) {
			$(".loading").show();
			$(".loading-bg").show();
		},
		success : function(data){
			if(data == "smsCodeError"){
				$('<div class="c-fa">'+ "请输入正确的手机验证码" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			}
			if(data == "success"){
				$.ajax({
					url : rootPath+"/payConfig/saveInfo",
					type : "post",
					data : param,
					success : function(data){
						if(data=="success"){
							$('<div class="c-fa">'+ "保存成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
								location.href=rootPath+"/payConfig/showPayChoice";
							});
						}
					}
				});
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			$(".loading").hide();
			$(".loading-bg").hide();
		}
	});
}

function saveInfo(param){
	$.ajax({
		url : rootPath+"/payConfig/saveInfo",
		type : "post",
		data : param,
		beforeSend : function(XMLHttpRequest) {
			$(".loading").show();
			$(".loading-bg").show();
		},
		success : function(data){
			if(data=="success"){
				$('<div class="c-fa">'+ "保存成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
					location.href=rootPath+"/payConfig/showPayChoice";
				});
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			$(".loading").hide();
			$(".loading-bg").hide();
		}
	});
}

function savePic(fileElementId){
	$.ajaxFileUpload({
		url : rootPath+"/payConfig/savePic",
		secureuri : false,// 安全协议
		async : false,
		fileElementId : fileElementId,
		dataType:'json',
		data : {"imgData":fileElementId},
		type : "POST",
		beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
        },
		success : function(data) {
			$('<div class="c-fa">'+ "上传成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
				var imgUrl = $("#imgUrl").val();
				var payComId = $("#payConfigCompanyId").val();
				if(fileElementId == 'imgDataZfb'){
					$("#personZfbUrl").val(data.url);
					$("#zfbImg").attr("src","http://"+imgUrl+"/"+data.url);
				}
				if(fileElementId == 'imgDataWx'){
					$("#personWxUrl").val(data.url);
					$("#wxImg").attr("src","http://"+imgUrl+"/"+data.url);
				}
			});
		},
        complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	});
}
