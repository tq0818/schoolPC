var payTypes="";
$(function() {
	start();
	//更换图片验证码
	$("#captcha").click(function(){
		$("#captcha").attr("src", rootPath+"/captcha?" + new Date());
	});
	
	$(".howPay").change(function(){
		var payNo = $(".howPay option:selected").val();
		if(payNo == 1){
			 var $tempForm = $('<form method="post" target="_blank" action="'+rootPath+'/payConfig/howPay"></form>');  
			    $("body").append($tempForm);  
			    $tempForm.submit();  
			    $tempForm.remove(); 
		}
		if(payNo == 2){
			 var $tempForm = $('<form method="post" target="_blank" action="'+rootPath+'/payConfig/howPayTwo"></form>');  
			    $("body").append($tempForm);  
			    $tempForm.submit();  
			    $tempForm.remove(); 
		}
	});
	
	//支付类型选择
	$(".seTypeArea").on("click","i",function(){
		var op = $(this).prev(".payType").attr("payType");
		var tx = $(this).prev(".payType").val();
		var bool = true;
		$("select[id='seType'] option").each(function(){
			var opv = $(this).val();
			if(opv == op){
				bool = false;
				return false;
			}else{
				nool = true;
			}
		});
		if(bool){
			$("#seType").append('<option value="'+op+'">'+tx+'</option>');
		}
		
		$(this).prev(".payType").remove();
		$(this).remove();
		$("#saveType").val("");
		if(op == 'PAY_TYPE_ZFB' || op == 'PAY_TYPE_GRDB'){
			$("#zfbRel").hide();
		}
		if(op == 'PAY_TYPE_ZFB_ZZ'){
			$("#zfbPer").hide();
		}
		if(op == 'PAY_TYPE_WX_PERSON'){
			$("#perWx").hide();
		}
		if(op == 'PAY_TYPE_REMIT'){
			$("#bank").hide();
		}
		if(op == 'PAY_TYPE_WX_GZH'){
			$("#wxgzh").hide();
		}
		var list = payTypes.split(',');
		list.splice($.inArray(op,list),1);
		payTypes = list.join(',');
	});
	$(".seTypeArea").on("click",".payType",function(){
		var payType = $(this).attr("payType");
		$("#saveType").val(payType);
		if(payType == 'PAY_TYPE_ZFB' || payType == 'PAY_TYPE_GRDB'){
			$("#zfbRel").show();
			$("#zfbPer").hide();
			$("#perWx").hide();
			$("#bank").hide();
			$("#wxgzh").hide();
		}
		if(payType == 'PAY_TYPE_ZFB_ZZ'){
			$("#zfbPer").show();
			$("#zfbRel").hide();
			$("#perWx").hide();
			$("#bank").hide();
			$("#wxgzh").hide();
		}
		if(payType == 'PAY_TYPE_WX_PERSON'){
			$("#zfbPer").hide();
			$("#zfbRel").hide();
			$("#perWx").show();
			$("#bank").hide();
			$("#wxgzh").hide();
		}
		if(payType == 'PAY_TYPE_REMIT'){
			$("#zfbPer").hide();
			$("#zfbRel").hide();
			$("#perWx").hide();
			$("#bank").show();
			$("#wxgzh").hide();
		}
		if(payType == 'PAY_TYPE_WX_GZH'){
			$("#zfbPer").hide();
			$("#zfbRel").hide();
			$("#perWx").hide();
			$("#bank").hide();
			$("#wxgzh").show();
		}
		$(this).addClass("btn-success").siblings(".payType").removeClass("btn-success");
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
	//上传支付宝二维码
	$("#upZfb").click(function(){
		$(this).next("#imgDataZfb").click();
	});
	
	$("#zfbImg").click(function(){
		$(this).prev("#imgDataZfb").click();
	});
	//上传微信二维码
	$("#upWx").click(function(){
		$(this).next("#imgDataWx").click();
	});
	
	$("#wxImg").click(function(){
		$(this).prev("#imgDataWx").click();
	});
	
	$("#save").click(function(){
		var saveType = $("#saveType").val();
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
		if(!saveType){
			$('<div class="c-fa">'+ "请选择要编辑的类型信息" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			return;
		}
		//end
		var param = "";
		param+="&payType="+payTypes;
		var list = payTypes.split(',');
		var isTrue = true;
		$.each(list,function(i,item){
			if(item == 'PAY_TYPE_WX_GZH'){
				if(!appId){
					$('<div class="c-fa">'+ "请输入AppID" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&gzhWxAppID="+appId;
					isTrue = true && isTrue;
				}
				if(!appSecret){
					$('<div class="c-fa">'+ "请输入AppSecret" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&gzhWxAppSecret="+appSecret;
					isTrue = true && isTrue;
				}
				if(!paySaleId){
					$('<div class="c-fa">'+ "请输入微信支付商户号" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&gzhWxMchID="+paySaleId;
					isTrue = true && isTrue;
				}
				if(!paySaleKey){
					$('<div class="c-fa">'+ "请输入微信支付商户号KEY" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					isTrue = false && isTrue;
					return;
				}else{
					param+="&gzhWxKey="+paySaleKey;
					isTrue = true && isTrue;
				}
			}
			if(item == 'PAY_TYPE_ZFB' || item == 'PAY_TYPE_GRDB'){
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
			if(item == 'PAY_TYPE_ZFB_ZZ'){
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
			if(item == 'PAY_TYPE_REMIT'){
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
			if(item == 'PAY_TYPE_WX_PERSON'){
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
			if(item == 'PAY_TYPE_SXY'){
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
		});
		if(isTrue){
			var payComId = $("#payConfigCompanyId").val();
			param+="&companyId="+companyId;
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
});


function start(){
	var payType = $("#payType").val();
	payTypes = payType;
	var no = payType.indexOf(",");
	no = parseInt(no);
	if(no>0){
		var list = payType.split(',');
		for(var i=0;i<list.length;i++){
			if(list[i] == 'PAY_TYPE_ZFB'){
				$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_ZFB" value="企业即时到账"><i class="iconfont">&#xe610</i>');
			}
			if(list[i] == 'PAY_TYPE_GRDB'){
				$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_GRDB" value="个人担保交易"><i class="iconfont">&#xe610</i>');	
			}
			if(list[i] == 'PAY_TYPE_ZFB_ZZ'){
				$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_ZFB_ZZ" value="个人支付宝"><i class="iconfont">&#xe610</i>');	
			}
			if(list[i] == 'PAY_TYPE_WX_PERSON'){
				$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_WX_PERSON" value="个人微信"><i class="iconfont">&#xe610</i>');	
			}
			if(list[i] == 'PAY_TYPE_REMIT'){
				$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_REMIT" value="银行汇款"><i class="iconfont">&#xe610</i>');	
			}
			if(list[i] == 'PAY_TYPE_WX_GZH'){
				$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_WX_GZH" value="微信公众号"><i class="iconfont">&#xe610</i>');	
			}
			
			if(i==0){
				$(".payType").each(function(){
					var tr = $(this).attr("payType");
					if(list[i]==tr){
						$(this).addClass("btn-success").siblings(".payType").removeClass("btn-success");
						if(list[i] == 'PAY_TYPE_ZFB'){
							$("#zfbRel").show();
							$("#zfbPer").hide();
							$("#perWx").hide();
							$("#wxgzh").hide();
							$("#bank").hide();
						}
						if(list[i] == 'PAY_TYPE_GRDB'){
							$("#zfbRel").show();
							$("#zfbPer").hide();
							$("#perWx").hide();
							$("#bank").hide();
							$("#wxgzh").hide();
						}
						if(list[i] == 'PAY_TYPE_ZFB_ZZ'){
							$("#zfbPer").show();
							$("#zfbRel").hide();
							$("#perWx").hide();
							$("#bank").hide();
							$("#wxgzh").hide();
						}
						if(list[i] == 'PAY_TYPE_WX_PERSON'){
							$("#zfbPer").hide();
							$("#zfbRel").hide();
							$("#perWx").show();
							$("#bank").hide();
							$("#wxgzh").hide();
						}
						if(list[i] == 'PAY_TYPE_REMIT'){
							$("#zfbPer").hide();
							$("#zfbRel").hide();
							$("#perWx").hide();
							$("#bank").show();
							$("#wxgzh").hide();
						}
						if(list[i] == 'PAY_TYPE_WX_GZH'){
							$("#zfbPer").hide();
							$("#zfbRel").hide();
							$("#perWx").hide();
							$("#bank").hide();
							$("#wxgzh").show();
						}
						$("#saveType").val(list[i]);
					}
				});
			}
		}
	}else{
		if(payType == 'PAY_TYPE_ZFB'){
			$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_ZFB" value="企业即时到账"><i class="iconfont">&#xe610</i>');
		}
		if(payType == 'PAY_TYPE_GRDB'){
			$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_GRDB" value="个人担保交易"><i class="iconfont">&#xe610</i>');	
		}
		if(payType == 'PAY_TYPE_ZFB_ZZ'){
			$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_ZFB_ZZ" value="个人支付宝"><i class="iconfont">&#xe610</i>');	
		}
		if(payType == 'PAY_TYPE_WX_PERSON'){
			$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_WX_PERSON" value="个人微信"><i class="iconfont">&#xe610</i>');	
		}
		if(payType == 'PAY_TYPE_REMIT'){
			$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_REMIT" value="银行汇款"><i class="iconfont">&#xe610</i>');	
		}
		if(payType == 'PAY_TYPE_WX_GZH'){
			$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-code payType" payType="PAY_TYPE_WX_GZH" value="微信公众号"><i class="iconfont">&#xe610</i>');	
		}
		
		$(".payType").each(function(){
			var tr = $(this).attr("payType");
			if(payType==tr){
				$(this).addClass("btn-success").siblings(".payType").removeClass("btn-success");
				if(payType == 'PAY_TYPE_ZFB'){
					$("#zfbRel").show();
					$("#zfbPer").hide();
					$("#perWx").hide();
					$("#bank").hide();
					$("#wxgzh").hide();
				}
				if(payType == 'PAY_TYPE_GRDB'){
					$("#zfbRel").show();
					$("#zfbPer").hide();
					$("#perWx").hide();
					$("#bank").hide();
					$("#wxgzh").hide();
				}
				if(payType == 'PAY_TYPE_ZFB_ZZ'){
					$("#zfbPer").show();
					$("#zfbRel").hide();
					$("#perWx").hide();
					$("#bank").hide();
					$("#wxgzh").hide();
				}
				if(payType == 'PAY_TYPE_WX_PERSON'){
					$("#zfbPer").hide();
					$("#zfbRel").hide();
					$("#perWx").show();
					$("#bank").hide();
					$("#wxgzh").hide();
				}
				if(payType == 'PAY_TYPE_REMIT'){
					$("#zfbPer").hide();
					$("#zfbRel").hide();
					$("#perWx").hide();
					$("#bank").show();
					$("#wxgzh").hide();
				}
				if(payType == 'PAY_TYPE_WX_GZH'){
					$("#zfbPer").hide();
					$("#zfbRel").hide();
					$("#perWx").hide();
					$("#bank").hide();
					$("#wxgzh").show();
				}
				$("#saveType").val(payType);
			}
		});
		
	}
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

function tChange(){
	var op = $("#seType option:selected").val();
	var tx = $("#seType option:selected").text();
	var bool = true;
	var msg="";
	$(".payType").each(function(){
		var tr = $(this).attr("payType");
		if((tr == 'PAY_TYPE_ZFB' && op == 'PAY_TYPE_GRDB')||(tr == 'PAY_TYPE_GRDB' && op == 'PAY_TYPE_ZFB')){
			bool = false;
			msg = "only";
			return false;
		}else{
			bool = true;
		}
		if((tr == 'PAY_TYPE_ZFB' && op == 'PAY_TYPE_ZFB')||(tr == 'PAY_TYPE_GRDB' && op == 'PAY_TYPE_GRDB')||(tr == 'PAY_TYPE_ZFB_ZZ' && op == 'PAY_TYPE_ZFB_ZZ')||(tr == 'PAY_TYPE_WX_PERSON' && op == 'PAY_TYPE_WX_PERSON')||(tr == 'PAY_TYPE_REMIT' && op == 'PAY_TYPE_REMIT')||(tr == 'PAY_TYPE_WX_GZH' && op == 'PAY_TYPE_WX_GZH')){
			bool = false;
			msg = "cf";
			return false;
		}else{
			bool = true;
		}
		
	});
	if(bool){
		$(".seTypeArea").append('<input type="button" class="btn btn-sm btn-default btn-success btn-code payType" payType="'+op+'" value="'+tx+'"><i class="iconfont">&#xe610</i>');
		$("#seType option:selected").remove();
		$(".payType").each(function(){
			var tr = $(this).attr("payType");
			if(tr==op){
				$(this).addClass("btn-success").siblings(".payType").removeClass("btn-success");
			}
		});
		if(op == 'PAY_TYPE_ZFB'){
			$("#zfbRel").show();
			$("#zfbPer").hide();
			$("#perWx").hide();
			$("#bank").hide();
			$("#wxgzh").hide();
		}
		if(op == 'PAY_TYPE_GRDB'){
			$("#zfbRel").show();
			$("#zfbPer").hide();
			$("#perWx").hide();
			$("#bank").hide();
			$("#wxgzh").hide();
		}
		if(op == 'PAY_TYPE_ZFB_ZZ'){
			$("#zfbPer").show();
			$("#zfbRel").hide();
			$("#perWx").hide();
			$("#bank").hide();
			$("#wxgzh").hide();
		}
		if(op == 'PAY_TYPE_WX_PERSON'){
			$("#zfbPer").hide();
			$("#zfbRel").hide();
			$("#perWx").show();
			$("#bank").hide();
			$("#wxgzh").hide();
		}
		if(op == 'PAY_TYPE_REMIT'){
			$("#zfbPer").hide();
			$("#zfbRel").hide();
			$("#perWx").hide();
			$("#bank").show();
			$("#wxgzh").hide();
		}
		if(op == 'PAY_TYPE_WX_GZH'){
			$("#zfbPer").hide();
			$("#zfbRel").hide();
			$("#perWx").hide();
			$("#bank").hide();
			$("#wxgzh").show();
		}
		$("#saveType").val(op);
		if(payTypes == ""){
			payTypes+=op;
		}else{
			payTypes+=","+op;
		}
	}else{
		if(msg == 'only'){
			$('<div class="c-fa">'+ "企业即时到账和个人担保交易只能选其一" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
		}
		$("#seType option:eq(0)").attr("selected","selected");
	}
}
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
								location.href=rootPath+"/payConfig/showPay";
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
					location.href=rootPath+"/payConfig/showPay";
				});
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			$(".loading").hide();
			$(".loading-bg").hide();
		}
	});
}
