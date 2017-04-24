(function($){
	
	function getDays(strDateStart,strDateEnd){
		var result=new Array();
		var strSeparator = "-"; //日期分隔符
		var mDays=[1,3,5,7,8,10,12];
		var oDate1;
		var oDate2;
		var iDays;
		oDate1= strDateStart.split(strSeparator);
		oDate2= strDateEnd.split(strSeparator);
		var strDateS = new Date(oDate1[0] + "-" + oDate1[1] + "-" + oDate1[2]);
		var strDateE = new Date(oDate2[0] + "-" + oDate2[1] + "-" + oDate2[2]);
		iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数 
		var currtDay=oDate1[2]-1;
		var currtMonth=oDate1[1];
		var currtYear=oDate1[0];
		for(var i=0;i<=iDays;i++){
			var day;
			if(oDate1[0]!=oDate2[0]){
				//不是同一年
				if(oDate1[1]!=oDate2[1]){
					//不是同一个月
					if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))!=-1 && currtDay<31){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))==-1 && currtDay<30){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<=12){
						currtDay=1;
						currtMonth= parseInt(currtMonth)<10?"0"+(parseInt(currtMonth)+1):parseInt(currtMonth)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else{
						currtDay=1;
						currtMonth=1;
						currtYear=parseInt(currtYear)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}
				}else{
					//是同一个月
					currtDay=parseInt(currtDay)+1;
					day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
				}
			}else{
				//是同一年
				if(oDate1[1]!=oDate2[1]){
					//不是同一个月
					if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))!=-1 && currtDay<31){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))==-1 && currtDay<30){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<12){
						currtDay=1;
						currtMonth= parseInt(currtMonth)<9?"0"+(parseInt(currtMonth)+1):parseInt(currtMonth)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else{
						currtDay=1;
						currtMonth=1;
						currtYear=parseInt(currtYear)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}
				}else{
					//是同一个月
					currtDay=parseInt(currtDay)+1;
					day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
				}
			}
			
			result.push(day);
		}
		return result;
	}
	
	var Form={
		init: function(){
			$selectSubMenu('financial');
			$selectSubMenus('operate_fee');
			var $this=this;
			// 初始化分校
			if($("#isAdmin").val() && $("#isAdmin").val()=="true"){
				$.ajax({
					url: rootPath+"/sysConfigSchool/getSchoolJson",
					type: "post",
					dataType: "json",
					success: function(jsonData){
						$(".classes-type").find(".c:first").find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success">全部</a>');
						$.each(jsonData,function(i,data){
							$(".classes-type").find(".c:first").find(".t-content").append('<a href="javascript:;" value="'+data.id+'" class="btn btn-mini btn-default">'+data.schoolName+'</a>');
						});
					}
				})
			}else if($("#isSubAdmin").val() && $("#isSubAdmin").val()=="true"){
				$(".classes-type").find(".c:first").find(".t-content").html('').append('<a href="javascript:;" id="'+$("#schoolId").val()+'" class="btn btn-mini btn-success">'+$("#schoolName").val()+'</a>');
				//$(".sc-type").find("a").eq(0).trigger("click.btn.schools");
			}
			//初始化一级项目
			$.ajax({
				url: rootPath+"/sysConfigItem/getJsons",
				type: "post",
				dataType: "json",
				data: {"itemType":1},
				success: function(jsonData){
					$(".classes-type").find(".c:eq(1)").find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success" value="">全部</a>');
					$.each(jsonData,function(i,data){
						$(".classes-type").find(".c:eq(1)").find(".t-content").append('<a href="javascript:;" value="'+data.id+'" class="btn btn-mini btn-default">'+data.itemName+'</a>');
					});
				}
			})
			
			$(".classes-type").find(".c").eq(2).find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success">全部</a>');
			$(".classes-type").find(".c").eq(2).find(".t-content").append('<a href="javascript:;" value="CHANNEL_ONLINE" class="btn btn-mini btn-default">线上报名</a>');
			$(".classes-type").find(".c").eq(2).find(".t-content").append('<a href="javascript:;" value="CHANNEL_OFFLINE" class="btn btn-mini btn-default">线下报名</a>');
			if($("#stydycardservice").val() == 1){
				$(".classes-type").find(".c").eq(2).find(".t-content").append('<a href="javascript:;" value="CHANNEL_STUDYCARD" class="btn btn-mini btn-default">学习卡兑换</a>');
			}
			//分校列表
			$("#schoolList").on("click","a.btn",function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
			});
			
			//一级项目
			$("#oneList").on("click","a.btn",function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
			})
			
			//日期列表
			$("#dateList").on("click","a.btn",function(){
				var mark=$(this).attr("mark");
				var status=$(this).hasClass("btn-success");
				if(!status){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
					if(mark=="nos"){
						$(".daterangs").removeClass("none");
					}else{
						$(".daterangs").addClass("none");
					}
				}
			})
			
			//订单
			$("#orderList").on('click','a.btn',function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
			})
			//绑定事件
			$this.bindEvent();
			//收索
			$("#searchContent").on("click",function(){
				$this.search();
			})
		},
		search: function(page){
			var $this=this;
			var mark="";
			var data={};
			$("#schoolList").find("a.btn").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					data.schoolId=$(this).attr("value");
				}
			});
			$("#oneList").find("a.btn").each(function(){
				var st1=$(this).hasClass("btn-success");
				if(st1){
					data.itemOneId=$(this).attr("value");
				}
			});
			data.applyChannelCode=$(".classes-type").find(".c").eq(2).find(".btn-success").attr("value");
			data.page=page?page:1;
			//日期列表
			$("#dateList").find("a").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					mark="other";
					if($(this).attr("mark")=="nos"){
						mark="nos";
						data.startDate=$(".from").val();
						data.endDate=$(".to").val();
					}else{
						data.timeMark=$(this).attr("mark");
					}
				}
			});
			if(mark==""){
				$.msg("请指定日期范围");
				return;
			}else if(mark=="nos"){
				if(!$(".from").val() || !$(".to").val()){
					$.msg("请指定日期范围");
					return false;
				}
				if ($(".to").val() != "") {
	                if ($(".to").val() < $(".from").val()) {
	                    $.msg("时间范围不正确");
	                    return;
	                }

	            }
				if(getDays($(".from").val(),$(".to").val()).length>90){
					$.msg("日期范围不能超过三个月");
					return;
				}
			}
			$.each(data,function(key,value){
				if(!value){
					delete data[key];
				}
			})
			console.log(data);
			$.ajax({
				url:rootPath+"/query/querylsTotal",
				data:data,
				success: function(result){
					var checkFee=$.formatMoney(result.checkFee);
					var remitFee=$.formatMoney(result.remitFee);
					var cashFee=$.formatMoney(result.cashFee);
					var posFee=$.formatMoney(result.posFee);
					var totalMoney=$.formatMoney(result.totalMoney);
					if(data.applyChannelCode == "CHANNEL_STUDYCARD") {
						$(".lsts").find("a").eq(2).find("b").html($.formatMoney(0));
						$(".lsts").find("a").eq(3).find("b").html($.formatMoney(0));
						$(".lsts").find("a").eq(4).find("b").html($.formatMoney(0));
						$(".lsts").find("a").eq(5).find("b").html($.formatMoney(0));
						$(".lsts").find("a").eq(6).find("b").html($.formatMoney(0));
					}else{
						$(".lsts").find("a").eq(2).find("b").html(checkFee);
						$(".lsts").find("a").eq(3).find("b").html(remitFee);
						$(".lsts").find("a").eq(4).find("b").html(cashFee);
						$(".lsts").find("a").eq(5).find("b").html(posFee);
						$(".lsts").find("a").eq(6).find("b").html(totalMoney);
					}
				}
			});
			//查询线上，线下统计金额
			$.ajax({
				url : rootPath+"/query/queryonoffFee",
				data: data,
				success: function(jsonData){
					var onlineMon=0;
					var cutlinMon=0;
					var st="";
					$.each(jsonData,function(i){
						onlineMon=jsonData[i].onCountFee;
						cutlinMon=jsonData[i].offCountFee;
					});
					$("#orderList").find("a.btn").each(function(i){
						var _this=$(this);
						if(_this.hasClass('btn-success')){
							st=_this.attr("value");
						}
					});
					if(st=="CHANNEL_ONLINE"){
						cutlinMon=0;
					}else if(st=="CHANNEL_OFFLINE"){
						onlineMon=0;
					}
					if(data.applyChannelCode == "CHANNEL_STUDYCARD") {
						$(".lsts").find("a").eq(0).find("b").html($.formatMoney(0));
						$(".lsts").find("a").eq(1).find("b").html($.formatMoney(0));
					}else{
						$(".lsts").find("a").eq(0).find("b").html($.formatMoney(onlineMon));
						$(".lsts").find("a").eq(1).find("b").html($.formatMoney(cutlinMon));
					}
				}
			})
			
			$(".lsOne").find("table").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/query/querylsFee",
				data: data,
				success: function(jsonData){
					if(jsonData.data.length==0){
						if($(".lsOne").find("table").find("tr.existsss").length<=0){
							$(".lsOne").find("table").append('<tr class="existsss"><td colspan="9">没有查找到数据</td></tr>');
						}
					}
					$.each(jsonData.data,function(i,user){
						//user.totalAmount
						var cou=0;
						var payMethod="";
						if(user.checkReal && user.checkReal!=-1){
							cou+=user.checkReal;
							payMethod+="支票 ";
						}
						if(user.posReal && user.posReal!=-1){
							cou+=user.posReal;
							payMethod+="POS ";
						}
						if(user.cashReal && user.cashReal!=-1){
							cou+=user.cashReal;
							payMethod+="现金 ";
						}
						if(user.remitReal && user.remitReal!=-1){
							cou+=user.remitReal;
							payMethod+="转账";
						}
						if(user.checkReal==-1 || user.remitReal==-1 || user.cashReal==-1 || user.posReal==-1){
							if(user.stageFee){
								cou+=user.stageFee;
							}
						}
						if(user.payMethod && user.payMethod!=-1){
							if(user.payMethod == 'PAY_TYPE_ZFB'){
								payMethod="支付宝";
							}else if(user.payMethod == 'PAY_TYPE_REMIT'){
								payMethod="银行汇款";
							}else if(user.payMethod == 'PAY_TYPE_ZFB_ZZ'){
								payMethod="支付宝转账";
							}else if(user.payMethod == 'PAY_TYPE_WX_PERSON'){
								payMethod="微信个人收款";
							}else if(user.payMethod == 'PAY_TYPE_WX_GZH'){
								payMethod="微信公众号";
							}
						}
						$(".lsOne").find("table").append('<tr>'+
								'<td>'+(user.schoolName?user.schoolName:"")+'</td>'+
								'<td>'+(user.stuName?user.stuName:"")+'</td>'+
								'<td>'+(user.applyTime?user.applyTime:"")+'</td>'+
								'<td>'+(user.itemOneName?user.itemOneName:"")+'</td>'+
								'<td>'+(user.classTypeName?user.classTypeName:"")+'</td>'+
								'<td>'+(user.payDate?user.payDate:"")+'</td>'+
								'<td>'+(user.applyChannelCode=="CHANNEL_ONLINE"?"线上报名":(user.applyChannelCode=="CHANNEL_OFFLINE"?"线下报名":(user.applyChannelCode=="CHANNEL_STUDYCARD"?"学习卡兑换":"")))+'</td>'+
								'<td>'+(payMethod)+'</td>'+
								'<td>'+($.formatMoney(cou))+'</td>'+
								'</tr>');
					});
					if(jsonData.rowCount>12){
						$(".ls").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1;
						    	 $this.search(pageNo);
					    	 }
					   });
					}else{
						$(".ls").html('');
					}
					
				}
			});
			
			$.ajax({
				url:rootPath+"/query/querythTotal",
				data:data,
				success: function(result){
					var checkFee=$.formatMoney(result.checkFee);
					var remitFee=$.formatMoney(result.remitFee);
					var cashFee=$.formatMoney(result.cashFee);
					var posFee=$.formatMoney(result.posFee);
					var totalMoney=$.formatMoney(result.totalMoney);
					$(".thOne").find("a").eq(0).find("b").html(checkFee);
					$(".thOne").find("a").eq(1).find("b").html(remitFee);
					$(".thOne").find("a").eq(2).find("b").html(cashFee);
					$(".thOne").find("a").eq(3).find("b").html(posFee);
					$(".thOne").find("a").eq(4).find("b").html(totalMoney);
				}
			});
			$(".thList").find("table").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/query/queryrefund",
				data: data,
				success: function(jsonData){
					if(jsonData.data.length==0){
						if($(".thList").find("table").find("tr.exixths").length<=0){
							$(".thList").find("table").append('<tr class="exixths"><td colspan="9">没有查找到数据</td></tr>');
						}
					}
//					$.each(jsonData.data,function(i,u){
//						var cou=0;
//						var pm="";
//						if(u.checkReal){
//							cou+=u.checkReal;
//							pm+="支票 ";
//						}
//						if(u.posReal){
//							cou+=u.posReal;
//							pm+="POS ";
//						}
//						if(u.cashReal){
//							cou+=u.cashReal;
//							pm+="现金 ";
//						}
//						if(u.remitReal){
//							cou+=u.remitReal;
//							pm+="转账 ";
//						}
//					});
					$.each(jsonData.data,function(i,stu){
						var totalMoney=0;
						var cou=0;
						var pm="";
						if(stu.checkRefund){
							cou+=stu.checkRefund;
							pm+="支票 ";
						}
						if(stu.posRefund){
							cou+=stu.posRefund;
							pm+="POS ";
						}
						if(stu.cashRefund){
							cou+=stu.cashRefund;
							pm+="现金 ";
						}
						if(stu.remitRefund){
							cou+=stu.remitRefund;
							pm+="转账 ";
						}
						//totalMoney=stu.posRefund+stu.cashRefund+stu.checkRefund+stu.remitRefund;
						$(".thList").find("table").append('<tr>'+
								'<td>'+(stu.schoolName?stu.schoolName:"")+'</td>'+
								'<td>'+(stu.stuName?stu.stuName:"")+'</td>'+
								'<td>'+(stu.refundDate?stu.refundDate:"")+'</td>'+
								'<td>'+(stu.itemOneName?stu.itemOneName:"")+'</td>'+
								'<td>'+(stu.itemSecondName?stu.itemSecondName:"")+'</td>'+
								'<td>'+(stu.classTypeName?stu.classTypeName:"")+'</td>'+
								'<td>'+(stu.applyChannelCode=="CHANNEL_ONLINE"?"线上报名":(stu.applyChannelCode=="CHANNEL_OFFLINE"?"线下报名":(stu.applyChannelCode=="CHANNEL_STUDYCARD"?"学习卡兑换":"")))+'</td>'+
								'<td>'+(pm)+'</td>'+
								'<td>'+($.formatMoney(cou))+'</td>'+
								'</tr>');
					});
					if(jsonData.rowCount>12){
						$(".th").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1;
						    	 $this.search(pageNo);
					    	 }
					   });
					}else{
						$(".th").html('');
					}
					
				}
			});
		},
		bindEvent : function(){
			var $this=this;
			$(".date-picker").datetimepicker({
				format: "yyyy-mm-dd",
 				minView:2,
 				autoclose: true,
 				language: "zh-CN"
			});
//			$(".date-picker").on("change",function(){
//				var from,to;
//				from=$(".from").val();
//				to=$(".to").val();
////				if(from && to){
////					$this.settings.xAxis.data.push();
////				}
//			})
			// 明细/趋势图
			$(".query").on("click.btn.panel",".title a",function(){
				if($(this).hasClass("btn-daoc")){
					var mark="";
					var data={};
					var types = "";
					$("#schoolList").find("a.btn").each(function(){
						var st=$(this).hasClass("btn-success");
						if(st){
							data.schoolId=$(this).attr("value");
						}
					});
					$("#oneList").find("a.btn").each(function(){
						var st1=$(this).hasClass("btn-success");
						if(st1){
							data.itemOneId=$(this).attr("value");
						}
					});
					data.applyChannelCode=$(".classes-type").find(".c").eq(2).find(".btn-success").attr("value");
					data.page=1;
					//日期列表
					$("#dateList").find("a").each(function(){
						var st=$(this).hasClass("btn-success");
						if(st){
							mark="other";
							if($(this).attr("mark")=="nos"){
								mark="nos";
								data.startDate=$(".from").val();
								data.endDate=$(".to").val();
							}else{
								data.timeMark=$(this).attr("mark");
							}
						}
					});
					if(mark==""){
						$.msg("请指定日期范围");
						return;
					}else if(mark=="nos"){
						if(!$(".from").val() || !$(".to").val()){
							$.msg("请指定日期范围");
							return false;
						}
						if ($(".to").val() != "") {
			                if ($(".to").val() < $(".from").val()) {
			                    $.msg("时间范围不正确");
			                    return;
			                }

			            }
						if(getDays($(".from").val(),$(".to").val()).length>90){
							$.msg("日期范围不能超过三个月");
							return;
						}
					}
					var url = "";
					if($(".btn-detail").hasClass("btn-success")){
						types = "lius";
						url = rootPath + "/query/exprotLius";
					}
					if($(".btn-chart").hasClass("btn-success")){
						types = "tuif";
						url = rootPath + "/query/exprotTuif";
					}
					
					if(url == ""){
						$.msg("请选择流水或退费");
						return false;
					}
					
					var param = "?types=" + types;
					$.each(data,function(key,value){
						if(!value){
							delete data[key];
						}else{
							param += "&" + key + "=" + value;
						}
					});
					
					console.log(param);
					window.location.href = url + param;
				}else{
					if($(this).hasClass("btn-detail")){
						$(".lsList").css("display","block");
						$(".thList").css("display","none");
					}
					if($(this).hasClass("btn-chart")){
						$(".lsList").css("display","none");
						$(".thList").css("display","block");
					}
					$(this).siblings().removeClass("btn-success").addClass("btn-default");
					$(this).addClass("btn-success").removeClass("btn-default");
				}
			});
		},
		initTables: function(){
			
		}
	}
	
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)