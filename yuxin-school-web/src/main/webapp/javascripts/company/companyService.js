$(function(){/*
	if($("#status").val() != 3){
		$("#noOpen").show();
		$("#opened").hide();
		$("#hint").text("未开通服务");
	}else{*/
		/*$("#hint").text("已开通服务");*/
	//}
	
	$(".isok a").each(function(){
		$(this).click(function(){
			var thisHtml = $(this).html();
			var thisClass = $(this).attr("class");/*
			if($("#status").val() == 1 && $("#caId").val() == ""){
				$(".layer").find(".alert-msg").html("");
				$(".layer").find(".alert-msg").html("机构认证后才能开通相关服务，请先去<a href='javascript:;''>申请机构认证</a>");
				$(".layer").find(".text-center").html("");
				$(".layer").find(".text-center").html("<a href='" + rootPath + "/companyAuthority/show' class='btn btn-primary'>马上认证</a>");
			}else if($("#status").val() == 2 && $("#caId").val() != ""){
				$(".layer").find(".alert-msg").html("");
				$(".layer").find(".alert-msg").html("您的认证信息已提交，请耐心等待审核......");
				$(".layer").find(".text-center").html("");
				$(".layer").find(".text-center").html("<a href='javascript:;' class='btn btn-primary'>确定</a>");
			}else if($("#status").val() == 4 && $("#caId").val() != ""){
				$(".layer").find(".alert-msg").html("");
				$(".layer").find(".alert-msg").html("您的信息已认证失败，请重新<a href='javascript:;''>申请机构认证</a>");
				$(".layer").find(".text-center").html("");
				$(".layer").find(".text-center").html("<a href='" + rootPath + "/companyAuthority/show' class='btn btn-primary'>重新认证</a>");
			}else */if(thisHtml == "设置支付信息"){
				location.href = rootPath + "/payConfig/showPayChoice";
			}else{
				if($(this).attr("disabled") == "disabled"){
					return false;
				}
				var typeId = $(this).attr("data-type");
				var objform = document.createElement("form");
				document.body.appendChild(objform);
				objform.action = rootPath + "/" + $(this).attr("data-url");
				objform.method = "post";
				
				var types = document.createElement("input");
				types.type = "hidden";
				objform.appendChild(types);
				types.value = typeId;
				types.name = "types";
				
				objform.submit();
			}
		});
	});
	
	$(".btn-open-ser").click(function(){
		var types = $(this).attr("data-type");
		if(types=="SERVICE_APP"){
			$.confirm({
				title:'开通服务',
				text:'<font color="grey">此服务为收费服务，请联系客户经理进行开通</font>',
				saveOk:false,
				cancelOk:true,
				callback:function(){}
			});
			return;
		}
		$.ajax({
			url:rootPath + "/company/selauth",
			type:"post",
			data:{"groupCode":types,"actions":"open"},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
//				$('.loading,.layer-bg').fadeIn(200);
	         },
			success:function(data){
				if(data.msg=="no_auth"){
					$('<div class="c-fa">'+ '请联系数校开通此服务！' +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
				}else if(data.msg == "auth"){
					$('<div class="c-fa">'+ '您没有操作权限！' +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
				}else if(data.msg == "error"){
					$('<div class="c-fa">'+ '出现了一点点小意外！' +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
				} else if (data.msg == 'notopen'){
					$.confirm({
						title:'开通服务',
						text:'<font color="grey">此服务为收费服务，请联系客户经理进行开通</font>',
						saveOk:false,
						cancelOk:true,
						callback:function(){}
					});
				}else if(data.msg == 'notAuth'){
					$.ajax({
						url : rootPath + "/company/openService",
						type:"post",
						data:{"groupCode":types},
						dataType:"json",
						beforeSend:function(XMLHttpRequest){
							$('.loading,.layer-bg').fadeIn(200);
				         },
						success:function(data){
							if(data.msg == "success"){
								$('.layer').fadeOut(200);
								$('.loading,.layer-bg').fadeIn(200);
								location.reload();
							}else{
								$('.layer-bg,.layer').fadeOut(200);
								$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
							}
						}
					});
				}else{
					var text = '';
					text += "<span style='color:red'>开通此服务，还将一并开通以下服务</span><br>";
					$.each(data.msg,function(index,item){
						text += '<font color="black">'+item + "</font><br>";
					});
					text += '<font color="grey">确定要开通此服务吗？</font>';
					$.confirm({
						title:'开通服务',
						text:text,
						saveOk:true,
						cancelOk:true,
						save:'确定',
						callback:function(flag){
							if(flag){
								$.ajax({
									url : rootPath + "/company/openService",
									type:"post",
									data:{"groupCode":types},
									dataType:"json",
									beforeSend:function(XMLHttpRequest){
										$('.loading,.layer-bg').fadeIn(200);
							         },
									success:function(data){
										if(data.msg == "success"){
											$('.layer').fadeOut(200);
											$('.loading,.layer-bg').fadeIn(200);
											location.reload();
										}else{
											$('.layer-bg,.layer').fadeOut(200);
											$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
										}
									}
								});
							}
						}
					});
				}
			},
			complete:function(XMLHttpRequest,textStatus){
//				$('.loading').fadeOut(200);
	        }
		});
	});
	
	$(".btn-colse-ser").click(function(){
		var types = $(this).attr("data-type");
		$.ajax({
			url:rootPath + "/company/selauth",
			type:"post",
			data:{"groupCode":types,"actions":"close"},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
//				$('.loading,.layer-bg').fadeIn(200);
	         },
			success:function(data){
				if(data.msg == "auth"){
					$('<div class="c-fa">'+ '您没有操作权限！' +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();$('.layer-bg').fadeOut(200);});
				}else if(data.msg == "error"){
					$('<div class="c-fa">'+ '开通时出现错误异常！' +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();$('.layer-bg').fadeOut(200);});
				}else if(data.msg == 'notAuth'){
					$.ajax({
						url : rootPath + "/company/colseService",
						type:"post",
						data:{"groupCode":types},
						dataType:"json",
						beforeSend:function(XMLHttpRequest){
							$('.loading,.layer-bg').fadeIn(200);
				         },
						success:function(data){
							if(data.msg == "success"){
								$('.layer').fadeOut(200);
								$('.loading,.layer-bg').fadeIn(200);
								location.reload();
							}else{
								$('.layer-bg,.layer').fadeOut(200);
								$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
							}
						}
					});
				}else{
					var text = '';
					text += "<span style='color:red'>关闭此服务，还将一并关闭以下服务</span><br>";
					$.each(data.msg,function(index,item){
						text += '<font color="black">'+item + "</font><br>";
					});
					text += '<font color="grey">确定要关闭此服务吗？</font>';
					$.confirm({
						title:'关闭服务',
						text:text,
						saveOk:true,
						cancelOk:true,
						save:'确定',
						callback:function(flag){
							if(flag){
								$.ajax({
									url : rootPath + "/company/colseService",
									type:"post",
									data:{"groupCode":types},
									dataType:"json",
									beforeSend:function(XMLHttpRequest){
										$('.loading,.layer-bg').fadeIn(200);
							         },
									success:function(data){
										if(data.msg == "success"){
											$('.layer').fadeOut(200);
											$('.loading,.layer-bg').fadeIn(200);
											location.reload();
										}else{
											$('.layer-bg,.layer').fadeOut(200);
											$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
										}
									}
								});
							}
						}
					});
				}
			},
			complete:function(XMLHttpRequest,textStatus){
//				$('.loading').fadeOut(200);
	        }
		});
	});
	
	//从公共utils.js取来进行单独修改
	$.confirm=function(msg,callback){
		var reminder,information,tips,sava,fadeOutBefor,saveOk,cancelOk;
		// 如果传入的不是对象
		if(typeof msg!='object'){
			reminder='提示';
			information=msg;
			fadeOutBefor='yes';
			sava='确定';
			saveOk = true;
			cancelOk = true;
		}else{
			reminder=msg.title || '提示';
			callback=msg.callback;
			fadeOutBefor=msg.fadeOutBefor || 'yes';
			information=msg.text;
			sava=msg.save || '保存';
			saveOk = msg.saveOk;
			cancelOk = msg.cancelOk;
		}
		tips='<div class="layerTips Confirm" style="display: none;">'+
			'<div class="layerTipsTitle">'+reminder+'<i class="Close iconfont Confirm_Close"></i></div>'+
			'<div class="layerTipsContent">'+information+'</div>'+
			'<div class="layerTipsBtns">'+
			(cancelOk?'<a href="javascript:;" class="btn btn-mini btn-default Confirm_Cancle">取消</a> ':'')+
			(saveOk?'<a href="javascript:;" class="btn btn-mini btn-success Confirm_Ok">'+sava+'</a>':'')+
			'</div>'+
			'</div>'+
			'<div class="layerTipsBg Confirm" style="display: none;"></div>';
		$(document).find(".layerTips").remove();
		$(document).find(".layerTipsBg").remove();
		$(document).find("body").append(tips);

		// 判断传入参数是否元素
		if(!(/<.*>.*<\/(\w+){3,8}/.test(information))){
			$('.layerTips .layerTipsContent').width('200px');
		}else{
			// 如果输入的是元素控制元素居中
			$('.layerTips.Confirm').css(
				{
					'margin-left':-$('.layerTips.Confirm').width()/2,
					'margin-top':-$('.layerTips.Confirm').height()/2
				});
		}

		// 背景样式及背景出现
		$('.layerTipsBg').fadeIn(200);
		$('.layerTips.Confirm').fadeIn(200);

		//确定
		$(document).off("click.ok.Confirm").on("click.ok.Confirm",".Confirm_Ok",function(){
			if(fadeOutBefor == 'yes'){
				$(this).parents('.layerTips').fadeOut(200);
				$('.layerTipsBg.Confirm').fadeOut(200);
			}
			if(callback){
				callback(true);
			}
		})
			.on("click.Close.Confirm",".Confirm_Close",function(){
				$(this).parents('.layerTips').fadeOut(200);
				$('.layerTipsBg.Confirm').fadeOut(200);
			});
		//取消
		$(document).off("click.cancle.Confirm").on("click.cancle.Confirm",".Confirm_Cancle",function(){
			$(this).parents('.layerTips').fadeOut(200);
			$('.layerTipsBg.Confirm').fadeOut(200);
			if(callback){
				callback(false);
			}
		});
	};
});