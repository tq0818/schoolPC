/**
 * 升级会员相关js
 * 1.加载大于当前会员等级的所有等级名称
 * 2.监听等级名称，例如：选择初级会员，加载出初级会员的所有有效期
 * 3.计算升级缴费需要多少钱
 * 4.监听保存按钮，点击保存，向订单表中插入一条数据，延长会员的时间等。
 * 
 * 
 */
$(function(){
	var  upgrade={
			init:function(){
				$this=this;
				//加载大于或等于当前会员等级的所有等级名称
				$this.search();
				//监听事件
				$this.event();
			},
			event:function(){
				$(".payInfo").on("keyup.page",".money",function(e){
	     			var oldVal=$.formatMoney($(this).val());
	     			var oldNeedPay=$.formatMoney($(".needPay1").text());
	     			console.log('oldVal'+oldVal);
	     			console.log('oldNeedPay'+oldNeedPay);
	     			$this.countFee();
	     			console.log('$.formatMoney($(".needPay1").text())'+$.formatMoney($(".needPay1").text()));
	     			if($.formatMoney($(".needPay1").text())<0){
	     				$.msg("超过应缴费用，请重新输入！");
	     				$(".needPay1").html(oldNeedPay);
	     				$(this).val("");
	     			}
	     		}).on("change.page",".money",function(e){
	     			if(!$.isMoney($(this).val())){
	     				$(this).val($.formatMoney($(this).val()));
	     				$(document).off('.money');
	     			}
	     		});
				//退费
				$(".returnFee").on("keyup.page",".money",function(e){
	     			var oldVal=$.formatMoney($(this).val());
	     			var oldNeedPay=$.formatMoney($(".returnMoney").text());
	     			console.log('oldVal'+oldVal);
	     			console.log('oldNeedPay'+oldNeedPay);
	     			$this.countFee1();
	     			if($.formatMoney($(".returnMoney").text())<0){
	     				$.msg("超过应缴费用，请重新输入！");
	     				$(".returnMoney").html(oldNeedPay);
	     				$(this).val("");
	     			}
	     		}).on("change.page",".money",function(e){
	     			if(!$.isMoney($(this).val())){
	     				$(this).val($.formatMoney($(this).val()));
	     				$(document).off('.money');
	     			}
	     		});
				
				//取消跳转
				$(".cancle").on('click',function(){
					window.location.href=rootPath+"/companyMemberConfig/companyMemberVip";
				});
				//发送Ajax请求，获取会员等级对应的有效期
				$("#selectVips").change(function(){
					
					var  becomeMember=$(".becomeMemberData").data("becomemember");
					$("#selectVipDetail").empty();
					var  memberLevelId=$("#selectVips").val();
					var  usersFrontId=$("#usersFrontId").val();
					var  url=rootPath+"/companyMemberConfig/queryMemberLevelDetails/"+memberLevelId+"/"+usersFrontId;
					var  data={};
					$.ajax({
						url:url,
						data:data,
						type:"post",
						success:function(jsonData){
							$(".openWay").data("openway",jsonData.openWay);
							if(jsonData.openWay==0){
								if(becomeMember==0){
									$("#selectVipDetail").append("<option value='-2'>请选择</option>");
									$.each(jsonData.list,function(i,obj){
										console.log($(".becomeMemberData").data("becomemember")==0);
											$("#selectVipDetail").append("<option value="+obj.length+">"+obj.name+"</option>");
									})
								}else{
									$("#selectVipDetail").append("<option value='-2'>请选择</option>");
									$("#selectVipDetail").append("<option value='-1' >无限期</option>");
									$("#pos,#cash,#cheque,#remit").attr("disabled",true);
									$("#pos,#cash,#cheque,#remit").val("");
									$("#pos,#cash,#cheque,#remit").attr("placeholder","此会员不用缴费");
							}
							}else{
								$("#selectVipDetail").append("<option value='-2'>请选择</option>");
								$("#selectVipDetail").append("<option value='-1' >无限期</option>");
								$("#pos,#cash,#cheque,#remit").attr("disabled",true);
								$("#pos,#cash,#cheque,#remit").val("");
								$("#pos,#cash,#cheque,#remit").attr("placeholder","此会员不用缴费");
							}
							
						}
					});
					
					//发送Ajax请求，计算欠缴/剩余多少钱
					$("#selectVipDetail").change(function(){
						var  becomeMember=$(".becomeMemberData").data("becomemember");
						if(becomeMember==0){
						var  usersFrontId=$("#usersFrontId").val();
						var  memberId=$("#selectVips").val();
						var  memberBuyLength=$("#selectVipDetail").val();
						var  url=rootPath+"/companyMemberConfig/leaveAndOweCalc/"+usersFrontId+"/"+memberId+"/"+memberBuyLength;
						var  data={};
						$.ajax({
							url:url,
							data:data,
							type:"post",
							success:function(jsonData){
								//如果欠缴纳费用>0或者==0，显示欠缴，缴费
								if(jsonData.ownPrice>0||jsonData.ownPrice==0){
									$(".needPay1Title").show();
									$(".needPay1").show();
									$(".leaveMoneyTitle").show();
									$(".leaveMoney").show();
									$(".returnMoneyTitle").hide();
									$(".returnMoney").hide();
									$(".returnFee").hide();
									$(".payInfo").show();
									$(".needPay1").html(jsonData.ownPrice);
									$(".leaveMoney").html(jsonData.leaveMoney);
									$("#needPay1Hidden").val(jsonData.ownPrice);
									$("#originPriceHidden").val(jsonData.originPrice);
									//清空POS等输入的值
									$("#pos").val("");
									$("#cash").val("");
									$("#cheque").val("");
									$("#remit").val("");
								}else{
									$(".payInfo").hide();
									$(".returnFee").show();
									$(".needPay1Title").hide();
									$(".leaveMoneyTitle").hide();
									$(".needPay1").hide();
									$(".leaveMoney").hide();
									$(".returnMoneyTitle").show();
									$(".returnMoney").show();
									$("#needPay1Hidden").val(jsonData.ownPrice);
									$("#originPriceHidden").val(jsonData.originPrice);
									$(".returnMoney").html(-jsonData.ownPrice);
									//清空值
									$("#pos1").val("");
									$("#cash1").val("");
									$("#cheque1").val("");
									$("#remit1").val("");
								}
							}
						});
						}
					});
				});
				
				//点击保存，向数据库中添加数据
				$this=this;
				$(".save").on('click',function(){
					//查询需要交易的金额
					var  payMoney=$("#needPay1Hidden").val();
					//如果payMoney==''表示的是累积消费型会员升级,如果payMoney==0表示剩余=支付
					if(payMoney>0||payMoney==''||payMoney==0){
						//付款
						$this.payMent();
					}
				});
			},
			search:function(){
				var  usersFrontId=$("#usersFrontId").val();
				//发送请求，加载所有的等级名称
				var  url=rootPath+"/companyMemberConfig/queryMemberlevelNames/"+usersFrontId;
				var  data={
				};
				$.ajax({
					url:url,
					data:data,
					type:"post",
					success:function(jsonData){
						$.each(jsonData, function(i, obj) {
							$("#selectVips").append("<option value="+obj.id+">"+obj.name+"</option>");
						})
					}
				});
			},
			//计算费用
			countFee:function(){
				var hasPay=0,trainingFee=0,agentFee=0,totalFee=0;
				totalFee=$.MoneyToNum($("#needPay1Hidden").val());
				console.log(totalFee);
				$(".money").each(function(){
	 				hasPay+=$.MoneyToNum($(this).val());
	 			});
				needFee=totalFee-hasPay;//待缴费
				$(".totalAmount").html($.formatMoney(totalFee));
	 			$(".needPay1").html($.formatMoney(needFee));
			},
			//计算费用
			countFee1:function(){
				var hasPay=0,trainingFee=0,agentFee=0,totalFee=0;
				totalFee=$.MoneyToNum($("#needPay1Hidden").val());
				console.log(totalFee);
				$(".money").each(function(){
	 				hasPay+=$.MoneyToNum($(this).val());
	 			});
				needFee=-totalFee-hasPay;//待缴费
	 			$(".returnMoney").html($.formatMoney(needFee));
			},
			payMent:function(){
				//判断是成为会员方式
				var  becomeMember=$(".becomeMemberData").data("becomemember");
				var memberId=$("#selectVips").val();
				if(memberId==-1){
					$.msg("请选择会员等级!");
					return;
				}
				//会员是否允许内部开通
				var openWayVal=$(".openWay").data("openway");
				//会员有效期
				var memberBuyLength=$("#selectVipDetail").val();
				if(memberBuyLength==-2){
					$.msg("请选择会员有效期!");
					return;
				}
				//会员是否允许内部开通，如果是，设置购买时长
				if(openWayVal==1){
					memberBuyLength=-1;
				}
				
				
				//会员等级名称
				var  memberLevel=$("#selectVips").find("option:selected").text();
				var  payMethod;
				if(becomeMember==0&&openWayVal==0){
				if($.trim($("#pos").val())==""&&$.trim($("#cash").val())==""&&$.trim($("#cheque").val())==""&&$.trim($("#remit").val())==""){
					$.msg("请输入缴费金额！");
					return;
				}else{
		 			var sum=0;
					$(".money").each(function(){
						sum+=$.MoneyToNum($(this).val());
		 			});
					//判断支付金额是否等于欠缴费用
					if(sum>$("#needPay1Hidden").val()){
						$.msg("支付金额大于欠缴！");
						return;
					}
					if(sum<$("#needPay1Hidden").val()){
						$.msg("支付金额小于欠缴！");
						return;
					}
					//pos支付
					if($("#pos").val()!=""){
						payMethod="pos,";
					}
					//现金支付
					if($("#cash").val()!=""){
						payMethod+="cash,";
					}
					//支票
					if( $("#cheque").val()!=""){
						payMethod+="cheque,";
					}
					//转账
					if($("#remit").val()!=""){
						payMethod+="remit,";
					}
					}
				}
				//前台用户Id
				var usersFrontId=$("#usersFrontId").val();
				var  url=rootPath+"/companyMemberConfig/createOrder";
				//如果是累积成为会员，originPrice=payMoney==0
				var  originPrice,payMoney;
				if(becomeMember==0){
					  originPrice=$("#originPriceHidden").val();
					//需要支付的钱
					payMoney=$("#needPay1Hidden").val();
				}else{
					 originPrice=0;
					 payMoney=0;
				}
				//如果是内部开通允许，生成0元订单
				if(openWayVal==1){
					 originPrice=0;
					 payMoney=0;
				}
				
				var  data={};
				data.memberId=memberId;
				data.memberBuyLength=memberBuyLength;
				data.memberLevel=memberLevel;
				data.payMoney=payMoney;
				data.payMethod=payMethod;
				data.usersFrontId=usersFrontId;
				data.originPrice=originPrice;
				$.ajax({
					url:url,
					data:data,
					type:"post",
					success:function(jsonData){
						if(jsonData=="success"){
							$.msg("升级成功！");
							window.location.href=rootPath+"/companyMemberConfig/companyMemberVip";
						}
					}
				});
			}
	};
	window.upgrade=upgrade;
	
	$(document).ready(function() {
		upgrade.init();
	})
})