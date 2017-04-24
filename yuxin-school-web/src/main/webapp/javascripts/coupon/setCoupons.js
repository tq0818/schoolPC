$(document).ready(function(){
	$selectSubMenu('sales');
	$selectSubMenus("coupon_manage");
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
	});
	//根据优惠对象计算发放数量
	$(".user-chose-position > div").on("click", function () {
		var type=$(this).attr("ids");
        if("COUPON_OBJECT_STUDENT"==type){
        	$(".give-out-nume").show();
        	$.ajax({
        		url: rootPath+"/usersFront/queryNumByType",
        		type: "post",
        		dataType: "json",
        		data: {"vipFlag":1},
        		success: function(num){
        			$("#tiketsNums").val(num);
        		}
        	});
        }else if("COUPON_OBJECT_MEMBER"==type){
        	$(".give-out-nume").show();
        	var memberLevel="";
        	$(".member_lists_chose1").find("input[type=checkbox]:checked").each(function(){
        		memberLevel+=$(this).val()+",";
        	});
        	if(memberLevel!=""){
        		memberLevel=memberLevel.substring(0,memberLevel.length-1);
        		$.ajax({
            		url: rootPath+"/usersFront/queryNumByType",
            		type: "post",
            		dataType: "json",
            		data: {"memberLevel":memberLevel},
            		success: function(num){
            			$("#tiketsNums").val(num);
            		}
            	});
        	}
        }else if("COUPON_OBJECT_ALL"==type){
        	$(".give-out-nume").show();
        	$.ajax({
        		url: rootPath+"/usersFront/queryNumByType",
        		type: "post",
        		dataType: "json",
        		success: function(num){
        			$("#tiketsNums").val(num);
        		}
        	});
        }else{
        	$(".give-out-nume").show();
        	$("#tiketsNums").val(0);
        }
    });
	//保存
	$(".save_info").on('click',function(){
		var type=$(this).attr("mark");
		addConponPatch(type);
	});
	$(".save_info2").on('click',function(){
		var type=$(this).attr("mark");
		addLineConponPatch(type);
	});
	//点击优惠范围
	$(document).on('click', '.comm_useRanges input[type=radio]',function(){
		var type=$(this).val();
		if(type==1){
			$(".item_list_choose").find(".coupon-set").removeClass("none");
			$(".item_course_choose").find(".coupon-set").addClass("none");
			$(".item_list_choose2").find(".coupon-set").removeClass("none");
			$(".item_course_choose2").find(".coupon-set").addClass("none");
		}else if(type==2){
			$(".item_course_choose").find(".coupon-set").removeClass("none");
			$(".item_list_choose").find(".coupon-set").addClass("none");
			$(".item_course_choose2").find(".coupon-set").removeClass("none");
			$(".item_list_choose2").find(".coupon-set").addClass("none");
		}else{
			$(".item_list_choose").find(".coupon-set").addClass("none");
			$(".item_course_choose").find(".coupon-set").addClass("none");
			$(".item_list_choose2").find(".coupon-set").addClass("none");
			$(".item_course_choose2").find(".coupon-set").addClass("none");
		}
	});
	//返回
	$(".cancle_ing").on("click",function(){
		window.location.href=rootPath+"/companyCouponsConfig/showCouponsList";
	});
	//文本输入框
	$("#message_Info").on("keyup",function(){
		//var tolen=140;
		var len=$(this).val().length;
		//var num=(tolen-len);
		$("#writeNum").text(len>=140?140:len);
	});
	$("#writeNum").text($("#message_Info").val().length);
	//点击导出excel文件按钮
    $(".export_coupons_data").on("click",function(){
    	 if ($(".coupons_libs_List").find("tr").eq(1).find("td").length <= 1) {
             $.msg("没有数据可以导出");
         } else {
        	 var exportExcel_PatchId=$("#coupsPatchId").val();
        	 window.location.href=rootPath+ "/companyCouponsLib/exportCouponsLib/"+exportExcel_PatchId;
         }
    });
    //显示发送信息
    $("#sms_info").on('click',function(){
    	if($(this).is(":checked")){
    		$(".message_showMsg").show();
    	}else{
    		$(".message_showMsg").hide();
    	}
    });
    $("#website_info").on('click',function(){
    	if($(this).is(":checked")){
    		$("#show_zhanneixin").show();
    	}else{
    		$("#show_zhanneixin").hide();
    	}
    });
	fillData();
});

//线上
function addConponPatch(type){
	CKupdate();
	var data={};
	data.type=type;
	data.id=$("#coupsPatchId").val();
	data.issueWay=$(".coupon_types").find("span.active").attr("ids");
	var name=$("#onLine_couponseName").val();
	if(name=="" || name.length<=0){
		$.msg("请输入优惠名称");
		return;
	}
	var way=$("#onLine_conTypeslist").find("input[type=radio]:checked").val();
	data.name=name;
	if(way && way!=""){
		data.useWay=way;
	}else{
		$.msg("请选择优惠方式");
		return;
	}
	//优惠方式
	var onLine_insCashNum=$("#onLine_insCashNum").val();
	var onLine_overNum=$("#onLine_overNum").val();
	var onLine_overCutNum=$("#onLine_overCutNum").val();
	var onLine_discountNum=$("#onLine_discountNum").val();
	if(way==0){
		if(onLine_insCashNum==""){
			$.msg("请输入抵现金额");
			return;
		}
		if(onLine_insCashNum<=0){
			$.msg("请输入大于0的金额");
			return;
		}
		data.insCashNum=onLine_insCashNum;
	}else if(way==1){
		if(onLine_overNum=="" || onLine_overCutNum==""){
			$.msg("请输入满减金额");
			return;
		}
		if(onLine_overNum<=0 || onLine_overCutNum<=0){
			$.msg("请输入大于0的金额");
			return;
		}
		data.overNum=onLine_overNum;
		data.overCutNum=onLine_overCutNum?onLine_overCutNum:0;
	}else if(way==2){
		if(onLine_discountNum==""){
			$.msg("请输入折扣");
			return;
		}
		data.discountNum=onLine_discountNum;
	}
	var obj=$("#onLine_choseObject").attr("value");
	if(obj && obj!=""){
		data.forCrowd=obj;
	}else{
		$.msg("请选择优惠对象");
		return;
	}
	//优惠对象
	if(obj=="COUPON_OBJECT_MEMBER"){
		var mem_list="";
		$(".member_lists_chose1").find("input[type=checkbox]:checked").each(function(){
			mem_list+=$(this).val()+",";
		});
		if(mem_list!=""){
			data.memberList=mem_list;
		}else{
			$.msg("请选择会员级别");
			return;
		}
	}else if(obj=="COUPON_OBJECT_SOMEONE"){
		var uId_list="";
		$("#group").find("span").each(function(){
			 uId_list+=$(this).attr("ids")+",";
		});
		if(uId_list!="" && uId_list.length>0){
			data.userList=uId_list;
		}else{
			$.msg("您还没有选择发放用户");
			return;
		}
	}
	var num=$("#tiketsNums").val();
	if(num>1000){
		$.msg("一次最多只能生成1000个优惠码");
		return;
	}
	data.totalNum=num;
	var flag=true;
	var proCode=$("#onLine_promoCodePrefix").val();
	$.ajax({
		url: rootPath+"/companyCouponsPatch/checkIsExist",
		type: "post",
		dataType: "json",
		async:false,
		data:{"promoCodePrefix":proCode},
		success: function(result){
			if(result && result=="exist"){
				flag=false;
			}
		}
	});
	var re=new RegExp("^[A-Za-z]+$");
	 if(!re.test(proCode)){
    	 $.msg("优惠码前缀只能是英文字母A-Z，限4个字母，不区分大小写");
    	 return;
    }
	if(!$("#coupsPatchId").val()){
		if(!flag){
			$.msg("优惠码前缀已存在");
			return;
		}
	}
	data.promoCodePrefix=proCode;
	data.description=$("#onLine_desciprtion").val();
	//时间设置
	var start=$("#onLine_startTime").val();
	var end=$("#onLine_endTime").val();
	var today=dateTostring("yyyy-MM-dd",new Date());
	if(start=="" || end ==""){
		$.msg("请选择使用期限");
		return;
	}
	if(start<today){
		$.msg("开始时间不早于当天");
		return;
	}
	if(end<start){
		$.msg("结束时间不能早于或等于开始时间");
		return;
	}
	data.timeLimitFrom=start;
	data.timeLimitTo=end;
	
	data.commodityType = $('.product-type').find('input[name=product-type]:checked').val();
	if(!data.commodityType){
		$.msg("请选择产品类型");
		return;
	}
	
	//使用范围
	var rang=$("#onLine_userRanges").find("input[type=radio]:checked").val();
	data.useRange=rang;
	if(!data.useRange){
		$.msg("请选择优惠范围");
		return;
	}
	if(rang==1){
		switch(data.commodityType){
		case "0":
			data.rangeItemOne=$(".item_list_choose").find(".itemOne").find("option:selected").val();
			data.rangeItemSecond=$(".item_list_choose").find(".itemTwo").find("option:selected").val();
			if(!data.rangeItemOne || !data.rangeItemSecond){
				$.msg("请正确指定课程范围");
				return;
			}
			break;
		case "1":
			data.rangeItemPackageCategory = parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne").val()) || '';
    		if(data.rangeItemPackageCategory){
    			data.rangeItemPackageCategory = prefixInteger(data.rangeItemPackageCategory);
    		}else{
    			$.msg("请正确指定课程包范围");
				return;
    		}
			break;
		}
	}else if(rang==2){
		switch(data.commodityType){
		case "0":
			data.rangeItemOne=$(".item_course_choose").find(".itemOne").find("option:selected").val();
			data.rangeItemSecond=$(".item_course_choose").find(".itemTwo").find("option:selected").val();
			var courseId=$(".item_course_choose").find(".classes_course").find("option:selected").val();
			if(courseId && courseId!=""){
				data.rangeItemCourse=courseId;
			}else{
				$.msg("您还没有指定具体课程");
				return;
			}
			if(!data.rangeItemOne || !data.rangeItemSecond){
				$.msg("请正确指定课程范围");
				return;
			}
			break;
		case "1":
			data.rangeItemPackageCategory = parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne").val()) || '';
			data.rangeItemCourse = $('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackages_course").find("option:selected").val();
			
			if(data.rangeItemCourse && data.rangeItemCourse!=""){
				data.rangeItemCourse=data.rangeItemCourse;
			}else{
				$.msg("您还没有指定具体课程包");
				return;
			}
    		if(data.rangeItemPackageCategory){
    			data.rangeItemPackageCategory = prefixInteger(data.rangeItemPackageCategory);
    		}else{
    			$.msg("请正确指定课程包范围");
				return;
    		}
			break;
		}
	}
	//发送信息类型
	var msg=$("#sms_info").is(":checked");
	var web_msg=$("#website_info").is(":checked");
	if(msg && web_msg){
		data.noticWay=2;
		if($("#message_Info").val()==""){
			$.msg("请输入短信内容");
			return;
		}
		if($("#editor01").val()==""){
			$.msg("请输入站内信内容");
			return;
		}
		data.noticSmsContents=$("#message_Info").val();
		data.noticMsgContents=encodeURI($("#editor01").val());
	}else if(msg && !web_msg){
		data.noticWay=1;
		if($("#message_Info").val()==""){
			$.msg("请输入短信内容");
			return;
		}
		data.noticSmsContents=$("#message_Info").val();
	}else if(web_msg && !msg){
		data.noticWay=0;
		if($("#editor01").val()==""){
			$.msg("请输入站内信内容");
			return;
		}
		data.noticMsgContents=encodeURI($("#editor01").val());
	}
	if(type=="saveAndSend"){
		$.confirm("您创建的优惠码将以优惠券的形式，发放到<i>“优惠对象”</i> 的个人中心里，您确定发放吗？",function(b){
			if(b){
				if($(".save_info").hasClass("disabled")){
					return;
				}
				$.ajax({
					url: rootPath+"/companyCouponsPatch/addCouponsPatch",
					type: "post",
					dataType: "json",
					data: data,
					beforeSend:function(XMLHttpRequest){
						$(".save_info").addClass("disabled");
						$(".loading.send").show();
 	                    $(".loading-bg").show();
			        },
					success: function(jsonData){
						if(jsonData.flag=="success"){
							$.msg("操作成功",function(){
								window.location.href=rootPath+"/companyCouponsConfig/showCouponsList";
							});	
						}else{
							$.msg(jsonData.flag);
							$(".save_info").removeClass("disabled");
						}
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".save_info").removeClass("disabled");
						$(".loading.send").hide();
 	                    $(".loading-bg").hide();
			        }
				});
			}
		});
	}else{
		if($(".save_info").hasClass("disabled")){
			return;
		}
		$.ajax({
			url: rootPath+"/companyCouponsPatch/addCouponsPatch",
			type: "post",
			dataType: "json",
			data: data,
			beforeSend:function(XMLHttpRequest){
				$(".save_info").addClass("disabled");
	        },
			success: function(jsonData){
				if(jsonData.flag=="success"){
					$.msg("操作成功",function(){
						window.location.href=rootPath+"/companyCouponsConfig/showCouponsList";
					});	
				}else{
					$.msg(jsonData.flag);
					$(".save_info").removeClass("disabled");
				}
			},
			complete:function(XMLHttpRequest,textStatus){
				$(".save_info").removeClass("disabled");
	        }
		});
	}
}

//线下
function addLineConponPatch(type){
	var data={};
	data.type=type;
	data.id=$("#coupsPatchId").val();
	data.issueWay=$(".coupon_types").find("span.active").attr("ids");
	var name=$("#offLine_couponseName").val();
	if(name=="" || name.length<=0){
		$.msg("请输入优惠名称");
		return;
	}
	var way=$("#offLine_conTypeslist").find("input[type=radio]:checked").val();
	data.name=name;
	var num=$("#offLine_tiketsNums").val();
	if(num==""){
		$.msg("请输入发放数量");
		return;
	}
    var re=new RegExp("^[0-9]*[1-9][0-9]*$"); 
    if(num.match(re)==null){ 
    	$.msg("只能输入正整数"); 
        return;
    }
	if(num<=0 || num>1000){
		$.msg("发放数量输入范围不正确");
		return;
	}
	data.totalNum=num;
	if(way && way!=""){
		data.useWay=way;
	}else{
		$.msg("请选择优惠方式");
		return;
	}
	//优惠方式
	var onLine_insCashNum=$("#offLine_insCashNum").val();
	var onLine_overNum=$("#offLine_overNum").val();
	var onLine_overCutNum=$("#offLine_overCutNum").val();
	var onLine_discountNum=$("#offLine_discountNum").val();
	if(way==0){
		if(onLine_insCashNum==""){
			$.msg("请输入抵现金额");
			return;
		}
		if(onLine_insCashNum<=0){
			$.msg("请输入大于0的金额");
			return;
		}
		data.insCashNum=onLine_insCashNum;
	}else if(way==1){
		if(onLine_overNum=="" || onLine_overCutNum==""){
			$.msg("请输入满减金额");
			return;
		}
		if(onLine_overNum<=0 || onLine_overCutNum<=0){
			$.msg("请输入大于0的金额");
			return;
		}
		data.overNum=onLine_overNum;
		data.overCutNum=onLine_overCutNum?onLine_overCutNum:0;
	}else if(way==2){
		data.discountNum=onLine_discountNum;
	}
	var flag=true;
	var proCode=$("#offLine_promoCodePrefix").val();
	$.ajax({
		url: rootPath+"/companyCouponsPatch/checkIsExist",
		type: "post",
		dataType: "json",
		async:false,
		data:{"promoCodePrefix":proCode},
		success: function(result){
			if(result && result=="exist"){
				flag=false;
			}
		}
	});
	var re=new RegExp("^[A-Za-z]+$");
	 if(!re.test(proCode)){
   	 $.msg("优惠码前缀只能是英文字母A-Z，限4个字母，不区分大小写");
   	 return;
   }
	if(!$("#coupsPatchId").val()){
		if(!flag){
			$.msg("优惠码前缀已存在");
			return;
		}
	}
	
	data.promoCodePrefix=proCode;
	data.description=$("#offLine_description").val();
	//时间设置
	var start=$("#offLine_startTime").val();
	var end=$("#offLine_endTime").val();
	var today=dateTostring("yyyy-MM-dd",new Date());
	if(start=="" || end ==""){
		$.msg("请选择使用期限");
		return;
	}
	if(start<today){
		$.msg("开始时间不早于当天");
		return;
	}
	if(end<start){
		$.msg("结束时间不能早于或等于开始时间");
		return;
	}
	data.timeLimitFrom=start;
	data.timeLimitTo=end;
	
	data.commodityType = $('.product-type1').find('input[name=product-type1]:checked').val();
	if(!data.commodityType){
		$.msg("请选择产品类型");
		return;
	}
	//使用范围
	var rang=$("#offLine_userRanges").find("input[type=radio]:checked").val();
	data.useRange=rang;
	if(!data.useRange){
		$.msg("请选择优惠范围");
		return;
	}
	if(rang==1){
		switch(data.commodityType){
		case "0":
			data.rangeItemOne=$(".item_list_choose2").find(".itemOne").find("option:selected").val();
			data.rangeItemSecond=$(".item_list_choose2").find(".itemTwo").find("option:selected").val();
			if(!data.rangeItemOne || !data.rangeItemSecond){
				$.msg("请正确指定课程范围");
				return;
			}
			break;
		case "1":
			data.rangeItemPackageCategory = parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne").val()) || '';
    		if(data.rangeItemPackageCategory){
    			data.rangeItemPackageCategory = prefixInteger(data.rangeItemPackageCategory);
    		}else{
    			$.msg("请正确指定课程包范围");
				return;
    		}
			break;
		}
	}else if(rang==2){
		switch(data.commodityType){
		case "0":
			data.rangeItemOne=$(".item_course_choose2").find(".itemOne").find("option:selected").val();
			data.rangeItemSecond=$(".item_course_choose2").find(".itemTwo").find("option:selected").val();
			var courseId=$(".item_course_choose2").find(".classes_course").find("option:selected").val();
			if(courseId && courseId!=""){
				data.rangeItemCourse=courseId;
			}else{
				$.msg("您还没有指定具体课程");
				return;
			}
			if(!data.rangeItemOne || !data.rangeItemSecond){
				$.msg("请正确指定课程范围");
				return;
			}
			break;
		case "1":
			data.rangeItemPackageCategory = parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne").val()) || '';
			data.rangeItemCourse = $('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackages_course").find("option:selected").val();;
			
			if(data.rangeItemCourse && data.rangeItemCourse!=""){
				data.rangeItemCourse=data.rangeItemCourse;
			}else{
				$.msg("您还没有指定具体课程包");
				return;
			}
    		if(data.rangeItemPackageCategory){
    			data.rangeItemPackageCategory = prefixInteger(data.rangeItemPackageCategory);
    		}else{
    			$.msg("请正确指定课程包范围");
				return;
    		}
			break;
		}
	}
	if($(".save_info2").hasClass("disabled")){
		return;
	}
	$.ajax({
		url: rootPath+"/companyCouponsPatch/addCouponsPatch",
		type: "post",
		dataType: "json",
		data: data,
		beforeSend:function(XMLHttpRequest){
			$(".save_info2").addClass("disabled");
        },
		success: function(jsonData){
			if(jsonData.flag=="success"){
				$("#coupsPatchId").val(jsonData.patchId);
				if(type=="save"){
					$.msg("操作成功");
					window.location.href=rootPath+"/companyCouponsConfig/showCouponsList";
				}else{
					$.msg("操作成功",function(){
						queryCouponsLibsList(1,$("#coupsPatchId").val());
					});
					$(".save_info2").removeClass("disabled");
				}	
			}else{
				$.msg(jsonData.flag);
				$(".save_info2").removeClass("disabled");
			}
		},
		complete:function(XMLHttpRequest,textStatus){
			//$(".save_info2").removeClass("disabled");
        }
	});
}

function fillData(){
	//使用方式
	var way=$("#useTypeIN").val();
	if(way){
		if(way==0){
			$("#onLine_conTypeslist").find("input[type=radio]").eq(0).trigger('click');
			$("#offLine_conTypeslist").find("input[type=radio]").eq(0).trigger('click');
		}else if(way==1){
			$("#onLine_conTypeslist").find("input[type=radio]").eq(1).trigger('click');
			$("#offLine_conTypeslist").find("input[type=radio]").eq(1).trigger('click');
		}else if(way==2){
			$("#onLine_conTypeslist").find("input[type=radio]").eq(2).trigger('click');
			$("#offLine_conTypeslist").find("input[type=radio]").eq(2).trigger('click');
		}
	}else{
		$("#onLine_conTypeslist").find("input[type=radio]").eq(0).trigger('click');
		$("#offLine_conTypeslist").find("input[type=radio]").eq(0).trigger('click');
	}
	//使用范围
	var range=$("#use_Rang_IN").val();
	if(range){
		if(range==1){
			$("#onLine_userRanges").find("input[type=radio]").eq(1).trigger('click');
			$("#offLine_userRanges").find("input[type=radio]").eq(1).trigger('click');
			var code = $('#id_rangeItemPackageCategory').val();
  			if(code){
  				$("#onLine_userRanges").find("input[type=radio]").eq(1).click()
  			}
		}else if(range==2){
			$("#onLine_userRanges").find("input[type=radio]").eq(2).trigger('click');
			$("#offLine_userRanges").find("input[type=radio]").eq(2).trigger('click');
			var code = $('#id_rangeItemPackageCategory').val();
			if(code){
				$("#onLine_userRanges").find("input[type=radio]").eq(2).click()
			}
		}
	}
	//优惠对象
	var obj=$("#id_forwardObject").val();
	if(obj && obj=="COUPON_OBJECT_STUDENT" && $("#add_type").val()==0){
		$(".give-out-nume").hide();
	}
	var msg_way=$("#id_noticeWay").val();
	if(msg_way){
		if(msg_way==1){
			$(".message_showMsg").show();
		}else if(msg_way==0){
			$("#show_zhanneixin").show();
		}else if(msg_way==2){
			$(".message_showMsg").show();
			$("#show_zhanneixin").show();
		}
	}
}

//查询优惠批次下的优惠码
function queryCouponsLibsList(page_libs,id){
	var forCrowd="";
	var useWay="";
	var useRange="";
	$.ajax({
		url: rootPath+"/companyCouponsPatch/queryCouponsPatchById",
		type: "post",
		data:{"id":id},
		dataType: "json",
		success: function(value){
			//优惠对象
			if(value.forCrowd=="COUPON_OBJECT_ALL"){
				forCrowd="所有用户";
			}
			if(value.forCrowd=="COUPON_OBJECT_MEMBER"){
				forCrowd="会员";
			}
			if(value.forCrowd=="COUPON_OBJECT_SOMEONE"){
				forCrowd="指定用户";
			}
			if(value.forCrowd=="COUPON_OBJECT_STUDENT"){
				forCrowd="购买过课程的学员";
			}
			//优惠方式
			if(value.useWay==0){
				useWay="抵现"+value.insCashNum;
			}
			if(value.useWay==1){
				useWay="满"+value.overNum+"减"+value.overCutNum;
			}
			if(value.useWay==2){
				useWay="打"+value.discountNum+"折";
			}
			//优惠范围
			switch(value.commodityType){
			case 1:
				if(value.useRange==0){
					useRange="全部课程包";
				}else{
					useRange=value.rangeItemPackageCategory;
				}
				break;
			default:
				if(value.useRange==0){
					useRange="全部课程";
				}
				if(value.useRange==1){
					useRange=value.rangeItemOneName+(value.rangeItemSecondName?"-"+value.rangeItemSecondName:"");
				}
				if(value.useRange==2){
					useRange=value.rangeItemOneName+"-"+value.rangeItemSecondName+"-"+value.rangeItemCourseName;
				}
				break;
			}
			$(".coupons_libs_List").find("tr:gt(0)").remove();
    		$.ajax({
    			url: rootPath+"/companyCouponsLib/queryLibsListByPatchId",
    			type: "post",
    			data:{"page":page_libs?page_libs:1,"id":id},
    			dataType: "json",
    			success: function(jsonData){
    				if(jsonData.data.length==0){
						$(".coupons_libs_List").append('<tr><td colspan="3">暂无数据</td></tr>');
					}
    				
    				$.each(jsonData.data, function (index, value) {
    					$(".coupons_libs_List").append('<tr>'+
    							'<td>'+(value.code?value.code:"")+'</td>'+
    							'<td>'+useRange+'</td>'+
    							'<td>'+('从 '+value.timeLimitFrom+' 到 '+value.timeLimitTo)+'</td>'+
    							'</tr>');
    	            });
    			
    				if(jsonData.rowCount>10){
    					$(".pagination_libs").pagination(jsonData.rowCount, {
    						 next_text: "下一页",
    			             prev_text: "上一页",
    			             current_page: jsonData.pageNo - 1,
    			             link_to: "javascript:void(0)",
    			             num_display_entries: 8,
    			             items_per_page: jsonData.pageSize,
    			             num_edge_entries: 1,
    			             callback: function (page_libs, jq) {
    			                 var pageNo_libs = page_libs + 1;
    			                 queryCouponsLibsList(pageNo_libs,id);
    			             }
    				   });
    					
    				}else{
    					$(".pagination_libs").html('');
    				}
    				
    			}
    		})
		}
	})
	 $(".layer-tips-bg").show();
     $(".add-alert-content.lead-out-code").show()
}

//处理CKEDITOR的值
 function CKupdate() {
    for (instance in CKEDITOR.instances){
      CKEDITOR.instances[instance].updateElement();
    }
 }	


function prefixInteger(num) {
	var length = num.toString().length;
	if(length<=3){
		length = 3;
	}else if(3<length && length<=6){
		length = 6;
	}else if(6<length && length<=9){
		length = 9;
	}else{
		return num;
	}
	return (Array(length).join('0') + num).slice(-length);
}

function resolveCode(code){
	switch(code.length){
	case 3:
		return code;
	case 6:
		return code.substring(0, 3) + "-" + code.substring(0, 6);
	case 9:
		return code.substring(0, 3) + "-" + code.substring(0, 6) + "-" + code.substring(0, 9);
	}
}