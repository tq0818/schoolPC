;(function($){
	var convertToJson=function(src){
		var  str=src.substr(src.indexOf("["));
		str=str.replace("[","").replace("]","");
		var strs=str.split(",");
		var result={};
		for(var i=0;i<strs.length;i++){
			var key=strs[i].split("=")[0];
			var value=strs[i].split("=")[1];
			if(value && value!="null"){
				result[$.trim(key)]=value;
			}
		}
		return result;
	}
	var resizeWindow=function(height,speed){
		var $this=this;
		speed=speed?speed:300;
		if(speed==0){
			$(".contentWindow").css("height",height+50);
		}else{
			$(".contentWindow").animate({height:height+50},speed);
		}
	}
	var changeNum= function(i){
		i=i+1;
		var arg=["一", "二", "三", "四", "五", "六", "七", "八", "九","十"];
		var str="";
		if(i/10>1){
			var n=Math.floor(i/10-1)?Math.floor(i/10-1):"";
			if(n>0){
				str=arg[n]+arg[9];
			}else{
				str=arg[9];
			}
			
			if(i%10>0){
				str+=arg[i%10-1]
			}
		}else if(i>10){
			str=(arg[Math.floor(i/10-1)]?arg[i/10-1]:"")+arg[9];
		}else{
			str=arg[i-1];
		}
		return str;
	}
	
	var Page=function(settings){
		this.options = {
			
			index : 1,
			maxIndex: 1,
			minIndex: 0,
			student :{},
			payMaster:{},
			slaves: [],
			materials:[],
			stages: [],
			saveFlag:[],
			isInit:false
			
		}
		$.extend(this.options,settings);
	}
	Page.prototype={
		
		init : function(){
			var $this=this;
			$this.options.payMaster.id=$("#payMasterId").val();
			$this.options.student=JSON.parse($("#student").val());
			$(".body").css("left","0%");
			//初始化界面
			$this.doinit();
			//初始化下拉框
			$(".itemOne").getSysItem(function(){
				$(".itemOne").find("option[value='"+$this.options.payMaster.itemOneId+"']").attr("selected","selected");
				$(".itemOne").trigger("change");
			});
			//绑定下一步，上一步按钮事件
			$(document).on("click.page",".nextstep",function(){
				var checkResult=$this.check();
				if(!checkResult.flag){
					$.msg(checkResult.message);
					return false;
				}
				$this.save.apply($this);
			});	
			//绑定取消按钮
			$(document).on("click.page",".cancle",function(){
				history.back();
			});	
			$("#edit_base").on("click.page",function(){
				var $vals=$(".c-content").find("em") || $(".c-content");
			});
			//绑定顶部1234按钮
			$("[class='mainbackground nopadding']").on("click.page",".active",function(){
				var index=$(this).index();
			})
			//绑定日期控件
			$("body").on("focus.page",".date-picker",function(){
     			$(this).datetimepicker({
     				format: "yyyy-mm-dd",
     				minView:2,
     				autoclose: true,
     				language: "zh-CN"
     			})
     		});
			
		},
		doinit: function(callback){
			var $this=this;
			$this.options.slaves=[];
			//查询数据
			$this.queryData({
				url : rootPath+"/studentPayMaster/"+$("#payMasterId").val()
			},function(data){
				if(data && data.id){
					$this.options.index=1;
					$this.options.payMaster=data;
					$this.options.payMaster.oMasterId=data.id;
					$this.options.payMaster.classTypeId=data.commodityId;
					$this.filldata(data);
					if(callback){
						callback();
					}
					$this.queryData({
						url: rootPath+"/studentPaySlave/findByPayMaster/"+data.id
						},
						function(data){
							$this.options.slaves=data;
							$this.options.isInit=true;
							$this.queryData({
								url : rootPath+"/studentAgentMaterial/queryMaterialsByStu",
								data : {
									stuId : $this.options.student.id,
									payMasterId : $this.options.payMaster.id
								}
							},function(data){
								if(data[0] && data[0].id){
									$this.options.materials=data;
								}
							})
							$(".mystep").on("click.checkbox","#dbk",function(){
								if($(this).hasClass("active")){
									$(".agentFeeInput").show();
								}else{
									$(".agentFeeInput").hide();
								}
							})
							$(".materials").css("cursor","pointer");
							//绑定资料按钮事件
							$(".materials").on("click.btn.signUp","em,i",function(e){
								 var ele=e.target;
								 var state=$("#dbk").hasClass("active");
								 if(state){
									 if($(ele).is("em")){
									 		var _num=parseInt($(ele).find("i").html());
									     	$(ele).find("i").html(_num+1);
									 	}else if($(ele).is("i")){
									 		var _num=parseInt($(ele).html());
									     	_num=_num>0?_num-1:0;
									     	$(ele).html(_num);
									     	return false;
									 	}	 
								 }      	
							});
							
						}
					)
					//初始化下拉框
					$(".itemOne").getSysItem(function(){
						$(".itemOne").find("option[value='"+$this.options.payMaster.itemOneId+"']").attr("selected","selected");
						$(".itemOne").trigger("change");
					});
					
				}else{
					//初始化下拉框
					$(".itemOne").getSysItem();
				}
			});

			$(".itemOne").off("change").on("change",function(){
				$(".itemSecond").html('').getSysItem($(this).val(),function(){
					$(".itemSecond").find("option[value='"+$this.options.payMaster.itemSecondId+"']").attr("selected","selected");
					$(".itemSecond").trigger("change");
				});
				//初始化考期
				$this.queryData({url:rootPath+"/sysConfigTerm/dict?itemOneId="+$(this).val()},function(data){
//					if(data.examtermName){
//						var year=data.examtermName.substring(0,4);
//						var month=data.examtermName.substring(5);
//						$(".year").find("option[value='"+year+"']").attr("selected","selected");
//						$(".month").find("options[value='"+month+"']").attr("selected","selected");
//					}
					
				})
			})
			$(".itemSecond").off("change").on("change",function(){
				$(".classType").html(''); 
				//加载班型
				$.ajax({
					url: rootPath+"/classType/findList?itemOneId="+$(".itemOne").val()+"&itemSecondId="+$(".itemSecond").val(),
					type: 'post',
					datatype: 'json',
					success: function(jsonData){
						if(jsonData && jsonData.length>0){
							$(".classType").html('').append('<option value="">请选择</option>');
							for(var i=0;i<jsonData.length;i++){
								$(".classType").append('<option value="'+jsonData[i]["id"]+'" realPrice="'+jsonData[i]["realPrice"]+'">'+jsonData[i]["name"]+'</option>');
							}
							$(".classType").find("option[value='"+$this.options.payMaster.classTypeId+"']").attr("selected","selected");
						}
					},
					error: function(){
					}
						
				});
			})
			
			$(".classType").off("change.page").on("change.page",function(){
				//根据班型查模块
				$(".totalClassHour").html('');
				$(".resouceType").html('');
				$this.options.payMaster.trainingFee=$(this).find("option:selected").attr("realPrice");
				$this.options.payMaster.totalAmount=$(this).find("option:selected").attr("realPrice");
				if(!$(".classType").val()){
					return false;
				}
				$.ajax({
			    		url: rootPath+"/classType/detail/"+$(".classType").val(),
			    		type:"post",
			    		dataType:"json",
			    		success:function(jsonData){
			    			if(jsonData){
			    				if(jsonData.faceFlag==1 || jsonData.remoteFlag==1){
			    					$(".list-infos .long").eq(0).show();
			    				}
			    				$(".totalClassHour").html(jsonData.totalClass);
			    				if(jsonData.faceFlag==1){
			    					$(".resouceType").append("面授");
			    				}
			    				if(jsonData.liveFlag==1){
			    					if($(".resouceType").html()){
			    						$(".resouceType").append("+直播");
			    					}else{
			    						$(".resouceType").append("直播");
			    					}
			    					
			    				}
			    				if(jsonData.videoFlag==1){
			    					if($(".resouceType").html()){
			    						$(".resouceType").append("+录播");
			    					}else{
			    						$(".resouceType").append("录播");
			    					}
			    				}
			    				if(jsonData.remoteFlag==1){
			    					if($(".resouceType").html()){
			    						$(".resouceType").append("+远程");
			    					}else{
			    						$(".resouceType").append("远程");
			    					}
			    					
			    				}

			    			}
			    		},
			    		error: function(){
			    			
			    		},
			    		complete:function(){
			    			
			    		}
			    	})
			    	$this.countFee();
			})
			//是否代报考
			$(".isAgent").off("click").on("click",function(){
				if($(this).hasClass("open")){
					$(this).removeClass("open").addClass("close").html('&#xe641;').next().html("是");
					$(".materialsInfo").slideDown(300);
					$(".agentFee").parents("p").show();
					$(".list-infos").find(".long").eq(1).slideDown(300,function(){
					});
				}else{
					$(this).removeClass("close").addClass("open").html('&#xe642;').next().html("否");
					$(".materialsInfo").slideUp(300);
					$(".agentFee").parents("p").hide();
					$(".list-infos").find(".long").eq(1).slideUp(300,function(){
					});
				}
				
			})
			
			//绑定费用计算事件
			$(".agentFee").on("keyup.page",function(e){
				$this.countFee();
			}).on("change.page",function(e){
				if(!$.isMoney($(this).val())){
					$(this).val($.formatMoney($(this).val()));
				}
			})
			 $("#val_total_stage").on("keyup",function(){
		        	if(!isNaN($(this).val())){
		        		$(".stageInfo").find(".stage_val").remove();
		        		for(var i=1;i<$(this).val();i++){
		        			var newStage= '<div class="pay-list clear stage_val">'+
		    		        	'<div class="pay-list-left"><p>第'+changeNum(i)+'期</p></div>'+
		    		        	'<div class="pay-list-right">'+
		    		        	'<p class="c">'+
		    		        	'<span class="p-title">日期：</span> <span id="date_picker_'+i+'" class="p-content ">'+
		    		        	'<input class="date-picker" id="stage_date_'+i+'" type="text"></span> <span class="p-title">金额：</span>'+
		    		        	'<span class="p-content">￥ <input type="text" class="money" id="stage_yen_'+i+'" placeholder="输入缴费金额"></span></p></div></div>';
		        			$(".stageInfo").append(newStage);
		        		}
		        	}
		        });
			 $("#pay_info").on("focus.page",".date-picker",function(){
     			$(this).datetimepicker({
     				format: "yyyy-mm-dd",
     				minView:2,
     				autoclose: true
     			})
     		});
			 $(".used").on("keyup.page",function(){
				 if($.MoneyToNum($(this).val())>$.MoneyToNum($(".opayed").html())){
					 $.msg("不能超过原订单已缴金额",function(){
						 $(".used").focus().val("");
					 });
					 return;
				 }
				$(".last").html($.formatMoney($.MoneyToNum($(".opayed").html())-$(this).val()));
				$this.countFee();
			 });
     		$(".payInfo").on("keyup.page",".money",function(e){
     			$this.countFee();
     		}).on("change.page",".money",function(e){
     			if(!$.isMoney($(this).val())){
     				$(this).val($.formatMoney($(this).val()));
     				$(document).off('.money');
     			}
     		});
     		$(".payBack").on("keyup.page",".money",function(e){
     			$this.countFee();
     		}).on("change.page",".money",function(e){
     			if(!$.isMoney($(this).val())){
     				$(this).val($.formatMoney($(this).val()));
     				$(document).off('.money');
     			}
     		});
     		$(".pay-order").on("change",":radio",function(){
     			if($(this).val()=="STAGE"){
     				$(".stageNum").show();
     				$("#val_total_stage").val("");
     			}else{
     				$(".stageNum").hide();
     				$(".stageInfo").find(".stage_val").remove();
     			}
     		})
			
		},
		//查订单，填充表单
		queryData : function(options,callback){
			var defaults={
				url:'',
				type: 'post',
				dataType:'json'
			};
			options=$.extend({},defaults,options);
			$.ajax({
				url: options.url,
				data: options.data,
				type: options.type,
				dataType : options.dataType,
				success : function(jsonData){
					if(callback){
						callback(jsonData);
					}
				},
				error : function(resp,msg,err){
					if(callback){
						callback(err);
					}
				}
			})
		},
		//查订单
		filldata: function(callback){
			var $this=this;
			$(".trainingFee").html($this.options.payMaster.trainingFee);
			if($this.options.payMaster.isAgent && $this.options.payMaster.isAgent==1){
				$("#dbk").addClass("active").html("&#xe60a;");
				$(".agentFeeInput").show();
				$.each($this.options.materials,function(i){
					$("#materials").find(".m-c-c-c").find("i").each(function(j){
						if($(this).attr("code")==$this.options.materials[i].materialTypeCode){
							$(this).html($this.options.materials[i].materialNumYet);
						}
					})
					$("#unmaterials").find(".m-c-c-c").find("i").each(function(j){
						if($(this).attr("code")==$this.options.materials[i].materialTypeCode){
							$(this).html($this.options.materials[i].materialNumNot);
						}
					})
				})
			}else{
				$(".agentFeeInput").hide();
			}
			if($this.options.payMaster.isAgentMaterialComplete && $this.options.payMaster.isAgentMaterialComplete==1){
				$("#zlqq").addClass("active").html("&#xe60a;");
			}
			if($this.options.payMaster.examAgentFee){
				$(".agentFee").val($this.options.payMaster.examAgentFee);
			}
			if($this.options.payMaster.paymentTypeCode=="PAY_TYPE_ALL"){
				$(".pay-order").find(":radio[value='ALL']").attr("checked","checked");
			}
			if($this.options.payMaster.paymentTypeCode=="PAY_TYPE_STAGE"){
				$(".pay-order").find(":radio[value='STAGE']").attr("checked","checked");
			}
			$.ajax({
				url: rootPath+"/studentPayMaster/countPay/"+$("#payMasterId").val(),
				type : "post",
				dataType : "json",
				success: function(jsonData){
					$this.options.payMaster.opayed=jsonData.opayed;
					$(".opayed").html($.formatMoney(jsonData.opayed));//原订单已缴
					//各项合计
					$(".opos").html($.formatMoney(jsonData.opos));
					$(".ocash").html($.formatMoney(jsonData.ocash));
					$(".ocheck").html($.formatMoney(jsonData.ocheck));
					$(".orimit").html($.formatMoney(jsonData.orimit));
					
					$this.countFee();
				}
			})
		},
		checkHasCommodity:function(commodityId,stuId){
			$.ajax({
				url: rootPath+"/studentPayMaster/checkHasCommodity",
				data:{"commodityId":commodityId,"stuId":stuId},
				type: "post",
				dataType: "text",
				async: false,
				success : function(jsonData){
					if(jsonData=="success"){
						$("#checkIsOk").val("true");
					}
				},
				error : function(){
				}
			})
		},
		//存订单
		save: function(successBack,errorBack){
			$("#checkIsOk").val("false");
			var $this=this,payMaster={},slave={},materialInfo=new Array();
			payMaster.itemOneId=$(".itemOne").val();
			payMaster.itemOneName=$(".itemOne").find("option:selected").text();
			payMaster.itemSecondId=$(".itemSecond").val();
			payMaster.itemSecondName=$(".itemSecond").find("option:selected").text();
			payMaster.commodityId=$("select.classType").val();
			payMaster.commodityType="COMMODITY_CLASS";
			payMaster.classTypeName=$(".classType").find("option:selected").text();
			payMaster.schoolId=$(".schools").find("btn-success").attr("value");
			payMaster.examTermId=$(".term").val();
			payMaster.examTermName=$(".term").find("option:selected").text();
			payMaster.stuId=$this.options.student.id;
			payMaster.slaves=JSON.stringify($this.options.slaves);
			payMaster.classTypeHour=$(".totalClassHour").html();
			if($(".schools").find("a.btn-success").attr("value")){
				payMaster.campusId=$(".schools").find("a.btn-success").attr("value");
			}
			payMaster.omasterId=$("#payMasterId").val();

			$("#materials").find("em").each(function(i){
				var $material=$(this).find("i");
				var arg=["EM_INCH1","EM_INCH2","EM_IDCARD","EM_EDUCATION","EM_DEGREE","EM_PROVE","EM_OTHER"];
				materialInfo.push({stuId:$this.options.student.id,payMasterId:$this.options.payMaster.id,materialTypeCode:arg[i],id:$material.attr("value"),materialName:$material.attr("txt"),materialNumYet:$material.html()});
			});
			var stageInfo=new Array(),refund={};
			var cpay=$.MoneyToNum($("#pos").val())+$.MoneyToNum($("#cash").val())+$.MoneyToNum($("#cheque").val())+$.MoneyToNum($("#remit").val());
	    	if($(".payInfo").css("display")=="block"){
	    		stageInfo.push({"stuId":$this.options.student.id,"posReal":$.MoneyToNum($("#pos").val()),"cashReal":$.MoneyToNum($("#cash").val()),"checkReal":$.MoneyToNum($("#cheque").val()),"remitReal":$.MoneyToNum($("#remit").val()),"stageStatus":"1","stageFee":cpay});
	    		if($("input[name=payType]:checked").val()=="STAGE"){
	    			for(var i=1;i<=$("#val_total_stage").val()-1;i++){
		    			stageInfo.push({"stuId":$this.options.student.id,"stageDate":$("#stage_date_"+i).val(),"stageFee":$.MoneyToNum($("#stage_yen_"+i).val()),"stageStatus":"0"});
		    		}
	    		}
	    	}
	    	if($(".payBack").css("display")=="block"){
	    		refund.posRefund=$.MoneyToNum($(".bpos").val());
	    		refund.cashRefund=$.MoneyToNum($(".bcash").val());
	    		refund.checkRefund=$.MoneyToNum($(".bcheck").val());
	    		refund.remitRefund=$.MoneyToNum($(".bremit").val());
	    		refund.refundFee=refund.posRefund+ refund.cashRefund+ refund.checkRefund + refund.remitRefund;
	    	}
	    	if(refund){
	    		payMaster.refund=JSON.stringify(refund);
	    	}
	    	if(stageInfo.length){
	    		payMaster.stage=JSON.stringify(stageInfo);
	    	}
	    	if($(".pay-order").find(":radio:checked").val()=="ALL"){
	    		payMaster.payStatusCode='ORDER_PAID';
	    		payMaster.paymentTypeCode="PAY_TYPE_ALL";
	    	}
	    	if($(".pay-order").find(":radio:checked").val()=="STAGE"){
	    		payMaster.payStatusCode='ORDER_PART_PAY';
	    		payMaster.paymentTypeCode="PAY_TYPE_STAGE";
	    	}
	    	payMaster.examAgentFee=$.MoneyToNum($(".agentFee").val());
	    	payMaster.student=JSON.stringify($this.options.student);
	    	payMaster.totalAmount=$.MoneyToNum($(".totalAmount").html());//订单总价
	    	payMaster.usedFee=$.MoneyToNum($(".used").val());
	    	
	    	$this.options.payMaster=$.extend({},$this.options.payMaster,payMaster);
			
			var paymaterId,dbk,zlqq,materialMark,materialFee;
			paymasterId=this.options.payMaster.id;
			dbk=$("#dbk").hasClass("active")?1:0;
			zlqq=$("#zlqq").hasClass("active")?1:0;
			materialMark=$("#materialMark").val();
			payMaster.isAgent=dbk;
			payMaster.isAgentMaterialComplete=zlqq;
			payMaster.agentRemark=materialMark;
			if(dbk){
				payMaster.materials=JSON.stringify(materialInfo);
			}else{
				delete payMaster.materials;
			}
			$this.options.payMaster=$.extend({},$this.options.payMaster,payMaster);
			$this.countFee();
			if(successBack){
				successBack();
			}
	    	//去掉空值
	    	$.each($this.options.payMaster,function(key,value){
	    		if(value===null || value==='' || value==='null'){
	    			delete $this.options.payMaster[key];
	    		}
	    	})
	    	Page.prototype.checkHasCommodity(payMaster.commodityId,payMaster.stuId);
	    	if($("#checkIsOk").val()=="true"){
		    	$.ajax({
					url: rootPath+"/studentPayMaster/changeClass",
					data: $this.options.payMaster,
					type: "post",
					dataType: "json",
					async: false,
					success : function(payMaster){
						$this.options.payMaster=payMaster;
						if(successBack){
							successBack();
						}
						$.msg("转班完成",2000,function(){
							var form="<form action='"+rootPath+"/student/toTransaction' method='post'><input type='hidden' id='mobile' name='mobile' value='"+$this.options.student.mobile+"'/></form>";
							$(form).appendTo('body').submit();
						});
					},
					error : function(){
						$.msg("转班失败");
						if(errorBack){
							errorBack();
						}
					}
				})
	    	}else{
	    		$.msg("已在此班级报过名！");
	    	}
		},
		check: function(){
			var msg={};
			msg.flag=true;
			if(!$(".classType").val() || $(".classType").val()=='null'){
				//没有选班型
				msg.flag=false;
				msg.message="没有选班型";
				return msg;
			}

			if($("#dbk").attr("active") && !$(".agentFee").val()){
				msg.flag=false;
				msg.message="您选择了代报考，代报考费用不能为空";
				return msg;
			}
			if($.MoneyToNum($(".needPay").html())!=0){
				msg.flag=false;
				msg.message="待缴费不等于已缴费";
				return msg;
			}
			if($(".payInfo").css("display")=="block" && !$("#pos").val()&&!$("#cash").val()&&!$("#cheque").val()&&!$("#remit").val()){
				msg.flag=false;
				msg.message="本期缴费不能为空";
				return msg;
			}
			if($(".payBack").css("display")=="block" && $.MoneyToNum($(".needBack").html())!=0){
				msg.flag=false;
				msg.message="退费不等于零";
				return msg;
			}
			return msg;
		},
		//计算费用
		countFee:function(){
			var oMaster=convertToJson($("#payMaster").val());
			var hasPay=0,payBack=0,trainingFee=0,agentFee=0,totalFee=0,opayed=0,used=0;
			if($(".payInfo").css("display")=="block"){
				$(".payInfo").find(".money").each(function(){
	 				hasPay+=$.MoneyToNum($(this).val());
	 			});
			}
			if($(".payBack").css("display")=="block"){
				$(".payBack").find(".money").each(function(){
					payBack+=$.MoneyToNum($(this).val());
	 			});
			}
			trainingFee=$.MoneyToNum(this.options.payMaster.trainingFee);//培训费
			agentFee=$.MoneyToNum($(".agentFee").val());//待报考费用
			opayed=$.MoneyToNum(this.options.payMaster.opayed);
			used=$.MoneyToNum($(".used").val());
			totalFee=trainingFee+agentFee;//总费用
			needFee=totalFee+used-opayed-hasPay+payBack;//待缴费
//			$(".opayed").html(opayed);
			$(".trainingFee").html($.formatMoney(trainingFee));
			$(".ototalAmount").html($.formatMoney(oMaster.totalAmount));//原订单总价
			$(".totalAmount").html($.formatMoney(totalFee));//新订单总价
 			$(".needPay").html($.formatMoney(needFee));//欠缴
 			$(".last").html($.formatMoney(opayed-used));//剩余

 			if(totalFee+used-opayed<0){
 				$(".payBack").show();
 				$(".payInfo").hide();
 				$(".needBack").html(-needFee);
 				$(".needPay").html(0);//欠缴
 			}else if(totalFee+used-opayed >0){
 				$(".payBack").hide();
 				$(".payInfo").show();
 				$(".needBack").html(0);
 			}else{
 				$(".needBack").html(0);//需退
 				$(".needPay").html(0);//欠缴
 			}
		}
	};
	$(document).ready(function(){
		var page=new Page();
		page.init();
	})
	

})(jQuery)
