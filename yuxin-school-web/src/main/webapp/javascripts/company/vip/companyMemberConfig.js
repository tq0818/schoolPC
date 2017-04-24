/**
 * author zhang.zx 代报考 页面js封装
 */
(function($) {

	var Form = {};

	Form.init = function() {
		window.uploadFile=this.uploadFile;
		var  remind_content=$(".remind_content").val();
		if(remind_content!=''){
			$(".words").html(remind_content.length);
		}
		var  $this=this;
		$(".upload_btn,.trans").on('click',function(){
			$("#imgDatas").trigger('click');
		});	
		$("#imgDatas").on('change',function(){
			$this.uploadFile();
   		});
		//会员专区开关控制
		$(".memberareaswitch").on('click',function(){
			//如果有open这个class
			var  hasOpen=$(this).hasClass("open"),id=$(".memberPage").data("id");
			var  url=rootPath+"/companyMemberConfig/memberAreaSwitch";
			//封装数据
			var data={};
			data.memberPage=hasOpen?0:1;
			data.id=id;
			$.ajax({
				url:url,
				data:data,
				type:"post",
				success:function(jsonData){
					if(hasOpen){
						$(".memberareaswitch").removeClass("open").addClass("close").html("&#xe604;");
						$(".memberareaswitch").parents(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
						$(".picarea").hide();
						$(".pic").hide();
					}else{
						$(".picarea").show();
						$(".pic").show();
						 $(".memberareaswitch").removeClass("close").addClass("open").html("&#xe603;");
				         $(".memberareaswitch").parents(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
					}
				}
			});
		});
		$(".remindSwitch").on('click',function(){
			//如果有open这个class
			var  hasOpen=$(this).hasClass("open"),id=$(".memberPage").data("id");
			var  url=rootPath+"/companyMemberConfig/memberRemindSwitch";
			//封装数据
			var data={};
			data.remind=hasOpen?0:1;
			data.id=id;
			$.ajax({
				url:url,
				data:data,
				type:"post",
				success:function(jsonData){
					if(hasOpen){
						//开关
						$(".remindSwitch").removeClass("open").addClass("close").html("&#xe604;");
						$(".remindSwitch").parents(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
						//内容
						$(".beforeday").hide();
						$(".remindContent").hide();
						$(".tips").hide();
					}else{
						//内容
						$(".beforeday").show();
						$(".remindContent").show();
						$(".tips").show();
						//开关
						 $(".remindSwitch").removeClass("close").addClass("open").html("&#xe603;");
				         $(".remindSwitch").parents(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
					}
				}
			});
		
		});
		
		$(".classDiscountSwitch").on('click',function(){
			var  hasOpen=$(this).hasClass("open"),id=$(".memberPage").data("id");
			var  url=rootPath+"/companyMemberConfig/classDiscountSwitch";
			var data={};
			data.id=id;
			data.classDiscount=hasOpen?0:1;
			$.ajax({
				url:url,
				data:data,
				type:"post",
				success:function(jsonData){
					if(hasOpen){
						$(".classDiscountSwitch").removeClass("open").addClass("close").html("&#xe604;");
						$(".classDiscountSwitch").parents(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
					}else{
						 $(".classDiscountSwitch").removeClass("close").addClass("open").html("&#xe603;");
				         $(".classDiscountSwitch").parents(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
					}
				}
			});
		});
		/**
		 * 允许使用积分购买开关
		 * buyWithIntegral
		 */
		$(".buyWithIntegralSwitch").on('click',function(){
			var  hasOpen=$(this).hasClass("open"),id=$(".memberPage").data("id");
			var  url=rootPath+"/companyMemberConfig/buyWithIntegralSwitch";
			var data={};
			data.id=id;
			data.buyWithIntegral=hasOpen?0:1;
			$.ajax({
				url:url,
				data:data,
				type:"post",
				success:function(jsonData){
					if(hasOpen){
						$(".buyWithIntegralSwitch").removeClass("open").addClass("close").html("&#xe604;");
						$(".buyWithIntegralSwitch").parents(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
					}else{
						$(".buyWithIntegralSwitch").removeClass("close").addClass("open").html("&#xe603;");
						$(".buyWithIntegralSwitch").parents(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
					}
				}
			});
		});
		/**
		 * 只允许会员购买会员课程
		 * buyFlag
		 */
		$(".buyFlag").on('click',function(){
			var  hasOpen=$(this).hasClass("open"),id=$(".memberPage").data("id");
			var  url=rootPath+"/companyMemberConfig/buyFlag";
			var data={};
			data.id=id;
			data.buyFlag=hasOpen?1:0;
			$.ajax({
				url:url,
				data:data,
				type:"post",
				success:function(jsonData){
					if(hasOpen){
						$(".buyFlag").removeClass("open").addClass("close").html("&#xe604;");
						$(".buyFlag").parents(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
					}else{
						$(".buyFlag").removeClass("close").addClass("open").html("&#xe603;");
						$(".buyFlag").parents(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
					}
					$.msg("修改成功!");
				}
			});
		});
		//切换成为会员
		$(".upgradeMethod").change(function(){
			var $this = $(this),id = $this.attr("id");
			var  input0=$("input[name=upgradeMethod]:eq(0)");
			var  input1=$("input[name=upgradeMethod]:eq(1)");
			$.confirm("切换后当前网校中所有会员优惠将失效，并且需要重新设置会员等级，是否确认此操作？",function(a){
				if(a){
					//判断当前是否有设置会员等级
					var  url=rootPath+"/companyMemberConfig/checkMemberLevelExist";
					var data={};
					$.ajax({
						url:url,
						data:data,
						type:'post',
						success:function(jsonData){
							//如果存在
							if(jsonData){
								$.msg("请先删除会员等级后再切换！");
								if(id == 'buy'){
									$(".memberreminddiv,.integralBuyDiv,.classDiscountRadio").hide();
									$this.attr('checked',false);
									input1.prop('checked',true);
								}else{
									$(".memberreminddiv,.integralBuyDiv,.classDiscountRadio").show();
									$this.attr('checked',false);
									input0.prop('checked',true);
								}
								return;
							}else{
								if(id == 'buy'){
									$(".memberreminddiv,.integralBuyDiv,.classDiscountRadio").show();
								}else{
									$(".memberreminddiv,.integralBuyDiv,.classDiscountRadio").hide();
								}
							}
						}
					});
				}else{
					if(id == 'buy'){
						$(".memberreminddiv,.integralBuyDiv").hide();
						$this.attr('checked',false);
						input1.prop('checked',true);
					}else{
						$this.attr('checked',false);
						input0.prop('checked',true);
						$(".memberreminddiv,.integralBuyDiv").show();
					}
				}	
			});
		});
		$(".remind_content").keyup(function(){
			var  remind_content=$(".remind_content").val();
			if(remind_content.length>120){
				$(".words").html(remind_content.length);
				$(".words").css("color","red");
				$.msg("提醒内容输入过长！");
				return;
			}else{
				$(".words").css("color","");
				$(".words").html(remind_content.length);
			}
		});
		
		
		// 监听保存按钮，点击进行保存
		$(".save").on(
				'click',
				function() {
					var remind_before_day,remind_content,memberPageBanner,imgBanner,upgrade_type,becomeMember;
					becomeMember= $( 'input[name="upgradeMethod"]:checked ').val();
					if($(".remindSwitch").hasClass("open")){
						// 前几天提醒
						 remind_before_day = $('.expireDay').val();
						// 提醒内容
						 remind_content = $('.remind_content').val();
						 if(remind_before_day==''){
							 $.msg("请输入会员过期日前内容！");
							 return;
						 }else{
							 if(remind_before_day<0||remind_before_day==0){
								 $.msg("会员过期日前必须大于0！");
								 return;
							 }
						 }
						 if(remind_content==''){
							 $.msg("请输入提醒内容！");
							 return;
						 }else{
							 if(remind_content.length>120){
								 $.msg("提醒内容输入过长！");
								 return;
							 }
						 }
					}else{
						 remind_before_day = $('.expireDay').val();
						 remind_content = $('.remind_content').val();
					}
					
					
						// 会员课程个性化折扣按0原价购买1补差价购买
						 upgrade_type = $( '#upgrade_type input[name="sale"]:checked ').val();
						 if($(".memberareaswitch").hasClass("open")){
							// 宣传图，如果是添加，img标签（imgBanner）下无内容提示，如果是修改，img标签（imgBanner）下无内容提示
							//如果是正在上传的过程中，show-pic-area和imgBanner都没有内容，提示等待上传。
							memberPageBanner=$(".show-pic-area").data("picPath");
							  imgBanner=$(".imgBanner").attr("src");
							if(memberPageBanner==""&&imgBanner==''){
								$.msg("请上传或等待上传专区宣传图!");
								return;
							}
						 }else{
							 imgBanner=$(".imgBanner").attr("src");
						 }
					
					var datas = {
						id:$(".memberPage").data("id"),
						memberPageBanner:memberPageBanner,
						becomeMember:becomeMember,
						remindContent : remind_content,
						remindBeforeDay : remind_before_day,
						upgradeType : upgrade_type
					}
					$.ajax({
						url : rootPath + "/companyMemberConfig/addOrUpdate",
						async:false,
						type : "post",
						data : datas,
						async : false,
						dataType : "json",
						success : function(result) {
								$.msg("设置会员成功！");
						}
					});
				});
		//监听取消按钮
		$(".cancel").on(
				'click',
				function() { 
					window.location.href = rootPath
					+ "/company/companyService";
				});
	}
	Form.uploadFile=function(){
		$(".imgBanner").attr("src","");
		$(".show-pic-area").data("picPath", "");
		$.ajaxFileUpload({
			url : rootPath + "/companyMemberConfig/upload",
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'imgDatas',
			dataType : 'json',
			type : "POST",
			success : function(data) {
				$(".imgBanner").attr("src",data.picOriginalUrl);
				$(".show-pic-area").data("picPath",data.realPath);
			},
			error : function(resp, msg, err) {
				console.log(resp);
			},
			fileName : 'imgDatas'
		});
	}
	

	$(document).ready(function() {
		Form.init();
	})
})(jQuery)

