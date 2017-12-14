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
			index : 0,
			maxIndex: 3,
			minIndex: 0,
			student :{},
			payMaster:{},
			slaves: [],
			materials:[],
			stages: [],
			saveFlag:[],
			isInit:false //是否已经有模块号数据，有则需要展示出来
			
		}
		$.extend(this.options,settings);
	}
	Page.prototype={
		
		init : function(){
			var $this=this;
			$selectMenu("course_class_type");
			$this.options.student=JSON.parse($("#student").val());
			if($this.options.student &&  $this.options.student.id){
				//保存学员
				//$this.options.index=1;
				$this.options.index=2;
				$(".body").css("left","0%");
				//$(".body").css("left","-100%");
			}else{
				$this.options.index=0;
				$(".body").css("left","0%");

			}
			//初始化界面
			$this.doinit(function(){
//				$this.init1(function(){
//					$this.init2(function(){
//						$this.init3();
//					})
//				})
			})
			
			//绑定下一步，上一步按钮事件
			$(document).on("click.page",".nextstep",function(){
				var checkResult=$this.check();
				if(!checkResult.flag){
					$.msg(checkResult.message);
					return false;
				}
				$this.save();
			});	

			//绑定取消按钮
			$(document).on("click.page",".cancle",function(){
				history.back();
			});	
			//绑定编辑学员按钮
			$("#edit_base").on("click.page",function(){
				var $vals=$(".c-content").find("em") || $(".c-content");
				$this.gotoStep(0);
			});
			//绑定保存学员按钮
			$(".saveStudent").on("click.page",function(){
				$this.nextStep.apply($this);
			});
			//绑定顶部1234按钮
			$("[class='mainbackground nopadding']").on("click.page",".active",function(){
				var index=$("[class='mainbackground nopadding']").find("ul li").index(this);
				$this.gotoStep(index);
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
			//绑定搜索功能
			$(".search").on("click.btn",":button",function(){
				var _this=$(this).parents(".search");
				if(!/^09\d{8}|1[3,4,5,7,8]\d{9}$/.test(_this.find(":text").val())){
					$this.showTip("不是有效的手机号");
					_this.find(":text").val("");
					return false;
				}
				$.ajax({
					url : rootPath + "/student/searchByMobile/"+ _this.find(":text").val(),
					type : "post",
					dataType : "json",
					success : function(jsonData) {
						if (jsonData && jsonData.id) {
							var tempForm=document.createElement("form");
							$.each(jsonData,function(key,value){
								if(value && value!="null"){
									$(tempForm).append('<input type="hidden" id="'+key+'" name="'+key+'" value="'+value+'"/>');
								}
								$(tempForm).attr("action",rootPath + "/student/showSignUp").attr("method","post").submit();
							});
						}else if(jsonData && jsonData.message){
							$this.showTip(_this,jsonData.message);
						} else {
							$this.showTip(_this,"你查询的学员手机号不存在，是否&nbsp;&nbsp;<a id='showDiolog' href='javascript:void(0);' style='color:blue;font-style:italic;'>新增学员并报名</a>");
							_this.find("#showDiolog").off("click").on("click", function(){
								var tempForm=document.createElement("form");
								$(tempForm).append('<input type="hidden" id="mobile" name="mobile" value="'+$("#mobile").val()+'"/>');
								$(tempForm).attr("action",rootPath + "/student/showSignUp").attr("method","post").submit();
							});
						}
					},
					error : function(resp,msg,err) {
						$this.showTip(_this,"你查询的学员手机号不存在，是否&nbsp;&nbsp;<a id='showDiolog' href='javascript:void(0);' style='color:blue;font-style:italic;'>新增学员并报名</a>");
						_this.find("#showDiolog").off("click").on("click", function(){
							var tempForm=document.createElement("form");
							$(tempForm).append('<input type="hidden" id="mobile" name="mobile" value="'+$("#mobile").val()+'"/>');
							$(tempForm).attr("action",rootPath + "/student/showSignUp").attr("method","post").submit();
						});
					}
				})
			})
			
			$("select.classType").select2({width:'260px'}).on('change',function(){
				//根据班型查模块
				$(".totalClassHour").html('');
				
				$this.options.payMaster.trainingFee=$(this).find("option:selected").attr("realPrice");
				$this.options.payMaster.totalAmount=$(this).find("option:selected").attr("realPrice");
				$(".trainingFee").html($this.options.payMaster.trainingFee);
				if(!$("select.classType").val()){
					return false;
				}
				$.ajax({
			    		url: rootPath+"/classType/detail/"+$("select.classType").val(),
			    		type:"post",
			    		dataType:"json",
			    		success:function(jsonData){
			    			$(".resouceType").html('');
			    			if(jsonData){
//			    				Form.transeSchool(jsonData.schoolsId,function(txt){
//			    					$("#val_schoolsId").html(txt);
//			    				});
			    				if(jsonData.faceFlag==1 || jsonData.remoteFlag==1){
			    					$(".list-infos .long").eq(0).show();
			    				}else{
			    					$(".list-infos .long").eq(0).hide();
			    				}
			    				$(".totalClassHour").html(jsonData.totalClass?jsonData.totalClass:0);
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
//			    				$("#val_train_price").val(jsonData.originalPrice).trigger("change");
			    				$this.options.modules=jsonData.modules;
			    				$this.options.videos=jsonData.videos;
			    				$this.options.remotes=jsonData.remotes;
			    				if(!$this.options.isInit){
			    					//清空slaves记录
				    				$this.options.slaves=[];
				    				for(var i=0;i<$this.options.modules.length;i++){
				    					var slave={};
				    					slave.stuId=$this.options.student.id;
				    					slave.resourceType=$this.options.modules[i].teachMethod;
				    					slave.resourceId=$this.options.modules[i].id;
				    					slave.moduleId=$this.options.modules[i].id;
				    					$this.options.slaves.push(slave);
				    				}
				    				//远程教育
				    				for(var i=0;i<$this.options.remotes.length;i++){
				    					var remote={};
				    					remote.stuId=$this.options.student.id;
				    					remote.resourceType='TEACH_METHOD_REMOTE';
				    					remote.resourceId=$this.options.remotes[i].id;
				    					$this.options.slaves.push(remote);
				    				}
			    				}
			    				$this.options.isInit=false;
			    				$(".modules").html('');
			    				$this.getModules();
			    				$this.getRemotes();
//			    				$this.getVideos();
			    				$this.countFee();
			    			}
			    		},
			    		error: function(){
			    			
			    		},
			    		complete:function(){
			    			
			    		}
			    	})
			});
			$("select.classType").trigger("change");
		},
		doinit: function(callback){
			var $this=this;
			$this.options.slaves=[];
			//查询数据
			$this.queryData({
				url : rootPath+"/studentPayMaster/queryUnpay",
				data : $this.options.student
			},function(data){
				data=data[0];
				if(data && data.id){
					$this.gotoStep(2);
					$this.options.index=2;
					$("#step"+$this.options.index).data("data",data);
					$this.options.payMaster=data;
					
					$this.queryData({
						url : rootPath+"/studentAgentMaterial/queryMaterialsByStu",
						data : {
							stuId : $this.options.student.id,
							payMasterId : $this.options.payMaster.id
						}
					},function(data){
						if(data[0] && data[0].id){
							$this.options.materials=data;
							$this.filldata2(data);
						}
					})
					
					$this.queryData({
						url: rootPath+"/studentPaySlave/findByPayMaster/"+$this.options.payMaster.id
						},
						function(data){
							$this.options.slaves=data;
							$this.options.isInit=true;
						}
					)
					
					//初始化下拉框
//					$(".itemOne").getSysItem(function(){
//						$(".itemOne").find("option[value='"+$this.options.payMaster.itemOneId+"']").attr("selected","selected");
//						$(".itemOne").trigger("change");
//					});
//					
				}else{
					//初始化下拉框
					//$(".itemOne").getSysItem();
				}
				//初始化校区
				$.ajax({
					url : rootPath + "/sysConfigCampus/getCampusesJson",
					type: "post",
					dataType: "json",
					success: function(jsonData){
						$(".schools").html('').append('<a href="javascript:;" class="btn btn-sm btn-success school" value="">全部</a>');
						$.each(jsonData,function(i){
							var data=jsonData[i];
							$(".schools").append('&nbsp;&nbsp;<a href="javascript:;" class="btn btn-sm btn-default school" value="'+data.id+'">'+data.campusName+'</a>');
						})
						if($this.options.payMaster.campusId){
							$(".schools").find("a[value='"+$this.options.payMaster.campusId+"']").removeClass("btn-default").addClass("btn-success");
						}
					}
				});
			});
			//是否代报考
			$(".isAgent").off("click").on("click",function(){
				if($(this).hasClass("open")){
					$(this).removeClass("open").addClass("close").html('&#xe641;').next().html("是");
					$(".materialsInfo").slideDown(300,function(){
						resizeWindow($("div.step2").height());
					});
					$(".list-infos").find(".long").eq(1).slideDown(300,function(){
						resizeWindow($("div.step2").height());
					});
				}else{
					$(this).removeClass("close").addClass("open").html('&#xe642;').next().html("否");
					$(".materialsInfo").slideUp(300,function(){
						resizeWindow($("div.step2").height());
					});
					$(".list-infos").find(".long").eq(1).slideUp(300,function(){
						resizeWindow($("div.step2").height());
					});
					resizeWindow($("div.step2").height());
				}
				
			})
			$(".year").find("option[value='"+(new Date()).getFullYear()+"']").attr("selected","selected");
			$(".itemOne").off("change").on("change",function(){
				$(".itemSecond").html('').getSysItem($(this).val(),function(){
					$(".itemSecond").find("option[value='"+$this.options.payMaster.itemSecondId+"']").attr("selected","selected");
					$(".itemSecond").trigger("change");
				});
				//初始化考期
				$this.queryData("/sysConfigTerm/dict?itemOneId="+$(this).val(),function(data){
					if(data.examtermName){
						var year=data.examtermName.substring(0,4);
						var month=data.examtermName.substring(5);
						$(".year").find("option[value='"+year+"']").attr("selected","selected");
						$(".month").find("options[value='"+month+"']").attr("selected","selected");
					}
					
				})
			})
			$(".itemSecond").off("change").on("change",function(){
				$("select.classType").html(''); 
				//加载班型
				$.ajax({
					url: rootPath+"/classType/findList?itemOneId="+$(".itemOne").val()+"&itemSecondId="+$(".itemSecond").val(),
					type: 'post',
					datatype: 'json',
					success: function(jsonData){
						if(jsonData && jsonData.length>0){
							$("select.classType").html('').append('<option value="">请选择</option>');
							for(var i=0;i<jsonData.length;i++){
								$("select.classType").append('<option value="'+jsonData[i]["id"]+'" realPrice="'+jsonData[i]["realPrice"]+'">'+jsonData[i]["name"]+'</option>');
								$("select.classType").find('option:last').data('options',jsonData[i]);
							}
							$("select.classType").find("option[value='"+$this.options.payMaster.classTypeId+"']").attr("selected","selected");
							$("select.classType").select2({width:'260px'}).on('change',function(){
								//根据班型查模块
								$(".totalClassHour").html('');
								
								$this.options.payMaster.trainingFee=$(this).find("option:selected").attr("realPrice");
								$this.options.payMaster.totalAmount=$(this).find("option:selected").attr("realPrice");
								$(".trainingFee").html($this.options.payMaster.trainingFee);
								if(!$("select.classType").val()){
									return false;
								}
								$.ajax({
							    		url: rootPath+"/classType/detail/"+$("select.classType").val(),
							    		type:"post",
							    		dataType:"json",
							    		success:function(jsonData){
							    			$(".resouceType").html('');
							    			if(jsonData){
//							    				Form.transeSchool(jsonData.schoolsId,function(txt){
//							    					$("#val_schoolsId").html(txt);
//							    				});
							    				if(jsonData.faceFlag==1 || jsonData.remoteFlag==1){
							    					$(".list-infos .long").eq(0).show();
							    				}else{
							    					$(".list-infos .long").eq(0).hide();
							    				}
							    				$(".totalClassHour").html(jsonData.totalClass?jsonData.totalClass:0);
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
//							    				$("#val_train_price").val(jsonData.originalPrice).trigger("change");
							    				$this.options.modules=jsonData.modules;
							    				$this.options.videos=jsonData.videos;
							    				$this.options.remotes=jsonData.remotes;
							    				if(!$this.options.isInit){
							    					//清空slaves记录
								    				$this.options.slaves=[];
								    				for(var i=0;i<$this.options.modules.length;i++){
								    					var slave={};
								    					slave.stuId=$this.options.student.id;
								    					slave.resourceType=$this.options.modules[i].teachMethod;
								    					slave.resourceId=$this.options.modules[i].id;
								    					slave.moduleId=$this.options.modules[i].id;
								    					$this.options.slaves.push(slave);
								    				}
								    				//远程教育
								    				for(var i=0;i<$this.options.remotes.length;i++){
								    					var remote={};
								    					remote.stuId=$this.options.student.id;
								    					remote.resourceType='TEACH_METHOD_REMOTE';
								    					remote.resourceId=$this.options.remotes[i].id;
								    					$this.options.slaves.push(remote);
								    				}
							    				}
							    				$this.options.isInit=false;
							    				$(".modules").html('');
							    				$this.getModules();
							    				$this.getRemotes();
//							    				$this.getVideos();
							    				$this.countFee();
							    			}
							    		},
							    		error: function(){
							    			
							    		},
							    		complete:function(){
							    			
							    		}
							    	})
							});
							$("select.classType").trigger("change");
						}
					},
					error: function(){
					}
						
				});
			})

			$(".schools").off("click.page").on("click.page",".school",function(){
				$(".schools").find(".school").removeClass("btn-success").addClass("btn-default");
				$(this).removeClass("btn-default").addClass("btn-success");
			})
			//绑定校区按钮点击事件
			$(".classInfo").off("click.page").on("click.page",".school",function(e){
				$(".modules").find(".public").find(".block").removeClass("active");
				var currtCmapus=$(this).attr("value");
				$(".modules").children(".public").each(function(i){
					var num=0;
					var ele;
					$(this).find(".block").each(function(n){
						if($(this).attr("campusId")==currtCmapus){
							num+=1;
							ele=$(this);
						}
					})
					if(num==1){
						ele.addClass("active");
					}
				})
			});
			$(".classInfo").off("change.page").on("change.page",".year,.month",function(e){
				$(".modules").find(".public").find(".block").removeClass("active");
				var currtTerm=$(this).val();
				$(".modules").children(".public").each(function(i){
					var num=0;
					var ele;
					$(this).find(".block").each(function(n){
						if($(this).attr("termId")==currtTerm){
							num+=1;
							ele=$(this);
						}
					})
					if(num==1){
						ele.addClass("active");
						for(var i=0;i<$this.options.slaves.length;i++){
							if($this.options.slaves[i].moduleId==ele.parents(".public").attr("value")){
								$this.options.slaves[i].resourceId=ele.find("b").attr("value");
							}
						}
					}
				})
			});
			//绑定班号点击事件
			$(".modules").off("click.page").on("click.page",".public .block",function(){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
				}else{
					$(this).siblings(".block").removeClass("active");
					$(this).addClass("active");
					for(var i=0;i<$this.options.slaves.length;i++){
						if($this.options.slaves[i].moduleId==$(this).parents(".public").attr("value")){
							$this.options.slaves[i].resourceId=$(this).find("b").attr("value");
						}
					}
				}
				
			});
			
			$("div.step2").off("click.checkbox").on("click.checkbox","#dbk",function(){
				if($(this).hasClass("active")){
					$(".agentFeeInput").show();
				}else{
					$(".agentFeeInput").hide();
				}
			})
			$(".materials").css("cursor","pointer");
			//绑定资料按钮事件
			$(".materials").off("click.btn.signUp").on("click.btn.signUp","em,i",function(e){
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
			
			//$(".trainingFee").html($this.options.payMaster.trainingFee);
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
		        		resizeWindow($("div.step3").height());
		        	}
		        });
			 $("#pay_info").on("focus.page",".date-picker",function(){
     			$(this).datetimepicker({
     				format: "yyyy-mm-dd",
     				minView:2,
     				autoclose: true
     			})
     		});
     		$(".payInfo").on("keyup.page",".money",function(e){
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
     				$(".stageInfo").show();
     			}else{
     				$(".stageNum").hide();
     				$(".stageInfo").hide();
     			}
     		})
     		$this.countFee();
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
			//查缴费
			if($this.options.stages.length>0){
				$("#val_total_stage").val($this.options.stages.length).trigger("keyup");
				$("#pos").val($this.options.stages[0].posReal);
				$("#cash").val($this.options.stages[0].cashReal);
				$("#cheque").val($this.options.stages[0].checkReal);
				$("#remit").val($this.options.stages[0].remitReal);
				for(var n=1;n<$this.options.stages.length;n++){
					$("#stage_date_"+n).val($this.options.stages[n].stageDate);
					$("#stage_yen_"+n).val($this.options.stages[n].stageFee);
				}
			}
			$this.countFee();
		},
		//存订单
		save: function(successBack,errorBack){
			var $this=this,payMaster={},slave={},stageInfo=new Array();
			payMaster.itemOneId=$(".itemOne").val();
			payMaster.itemOneName=$(".itemOne").find("option:selected").text();
			payMaster.itemSecondId=$(".itemSecond").val();
			payMaster.itemSecondName=$(".itemSecond").find("option:selected").text();
			payMaster.commodityId=$("select.classType").val();
			payMaster.commodityType="COMMODITY_CLASS";
			payMaster.classTypeName=$("select.classType").find("option:selected").text();
			payMaster.schoolId=$(".schools").find("btn-success").attr("value");
			payMaster.examTermId=$(".term").val();
			payMaster.examTermName=$(".term").find("option:selected").text();
			payMaster.stuId=$this.options.student.id;
			payMaster.slaves=JSON.stringify($this.options.slaves);
			payMaster.classTypeHour=$(".totalClassHour").html();
			payMaster.campusId=$(".schools").find("a.btn-success").attr("value");
			
			var materialInfo=new Array();
			
			$("#materials").find("em").each(function(i){
				var $material=$(this).find("i");
				var arg=["EM_INCH1","EM_INCH2","EM_IDCARD","EM_EDUCATION","EM_DEGREE","EM_PROVE","EM_OTHER"];
				materialInfo.push({stuId:$this.options.student.id,payMasterId:$this.options.payMaster.id,materialTypeCode:arg[i],id:$material.attr("value"),materialName:$material.attr("txt"),materialNumYet:$material.html()});
			});
			
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

			var cpay=$.MoneyToNum($("#pos").val())+$.MoneyToNum($("#cash").val())+$.MoneyToNum($("#cheque").val())+$.MoneyToNum($("#remit").val());
	    	stageInfo.push({"stuId":$this.options.student.id,"posReal":$.MoneyToNum($("#pos").val()),"cashReal":$.MoneyToNum($("#cash").val()),"checkReal":$.MoneyToNum($("#cheque").val()),"remitReal":$.MoneyToNum($("#remit").val()),"stageStatus":"1","stageFee":cpay});
	    	if($("input[name=payType]:checked").val()=="STAGE"){
	    		for(var i=1;i<=$("#val_total_stage").val()-1;i++){
    				stageInfo.push({"stuId":$this.options.student.id,"stageDate":$("#stage_date_"+i).val(),"stageFee":$.MoneyToNum($("#stage_yen_"+i).val()),"stageStatus":"0"});
	    		}
	    	}
	    	payMaster.stage=JSON.stringify(stageInfo);
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
	    	payMaster.totalAmount=$.MoneyToNum($(".totalAmount").html());
	    	
			$this.options.payMaster=$.extend({},$this.options.payMaster,payMaster);

			
			//没有报过该班型，直接开始报名
			if($(".step2").find(".nextstep").hasClass("disabled")){
				return;
			}
			$.ajax({
				url: rootPath+"/studentPayMaster/full/savePayMasterMany",
				data: $this.options.payMaster,
				type: "post",
				dataType: "json",
				async: false,
				beforeSend:function(){
					$(".step2").find(".nextstep").addClass("disabled");
				},
				success : function(data){
					if(data.status == "success"){
						$this.options.payMaster=payMaster;
	//					$this.init3();
						$.msg("报名已完成!",3000,function(){
	    					location.href=rootPath+"/otherSchool/studentList/"+$("#stuClassTypeId").val()+"/"+$("#lable").val();
	    				})
						if(successBack){
							successBack();
						}
					}else if(data.status == "error"){
						$.alert(data.result);
					}
				},
				error : function(){
					$.msg("订单保存失败");
					$(".step2").find(".nextstep").removeClass("disabled");
					if(errorBack){
						errorBack();
					}
				}
			})
		
	
			
			
			$this.countFee();
		},
		check: function(){
			var msg={};
			msg.flag=true;
			if(!$("select.classType").val() || $("select.classType").val()=='null' ){
				//没有选班型
				msg.flag=false;
				msg.message="没有选班型";
				return msg;
			}

			if($(".modules").find("p").length<=0){
				msg.flag=false;
				msg.message="没有模块";
			}
			$(".modules").find(".public").each(function(){
				if($(this).find(".right .c-content").children("em").length>0){
					//没有安排课程
					msg.flag=false;
					msg.message="请选择可招生的班号";
					return false;
				}
				if($(this).find(".right").find(".block.active").length<=0){
					//没有选班号
					msg.flag=false;
					msg.message="没有选班号";
					return false;
				}
			});
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
			if(!$("#pos").val()&&!$("#cash").val()&&!$("#cheque").val()&&!$("#remit").val()){
				msg.flag=false;
				msg.message="本期缴费不能为空";
				return msg;
			}
			if($("input[name=payType]:checked").val()=="STAGE"){
	    		for(var i=1;i<=$("#val_total_stage").val()-1;i++){
	    			if($("#stage_date_"+i).val() && $("#stage_yen_"+i).val()){
	    				
	    			}else{
	    				msg.flag=false;
	    				msg.message="分期信息不完整哦~";
	    				return msg;
	    			}
	    		}
	    	}
			return msg;
		},
		getModules : function(callback){
			var $this=this;
			for(var i=0;i<$this.options.modules.length;i++){
				var module=$this.options.modules[i];
				var slave=$this.options.slaves[i];
				if(module.teachMethod=="TEACH_METHOD_VIDEO"){
					$(".modules").append('<p class="long" value="'+module.id+'">'+
							'<span class="c-title">课程单元</span> <span class="c-content">'+module.name+'</span>'+
							'<span class="c-title">课程单元类型</span> <span class="c-content">视频</span>'+
							'<span class="c-title">总课时</span> <span class="c-content">'+(module.totalClassHour?module.totalClassHour:0)+'</span><span class="right clear"></span></p>');
				}else if(module.teachMethod=="TEACH_METHOD_REMOTE"){
				}else{
					$(".modules").append('<p class="public clear" value="'+module.id+'" teachMethod="'+module.teachMethod+'">'+
							'<span class="left clear"><span class="c-title">课程单元</span>'+
							'<span class="c-content">'+module.name+'</span>'+
							'<span class="c-title">课程单元类型</span><span class="c-content teachMethod"></span></span><span class="right clear"><span class="class-number">可选班号</span></span></p>');
					var newModule=$(".modules").find("p[value='"+module.id+"']");
					newModule.data("settings",module);
					$this.queryModuleNo(newModule);
				}
				
			}
			$(".modules").children("p").each(function(i){
				var settings=$(this).data("settings");
				var $this=$(this);
				$.transeDict($(this).attr("teachMethod"),function(text){
					$this.find(".teachMethod").html(text);
				});
			})
			resizeWindow($("div.step2").height());
			if(callback){
				callback();
			}
		},
		getRemotes : function(){
			var $this=this;
			for(var i=0;i<$this.options.remotes.length;i++){
				var remote=$this.options.remotes[i];
				$(".modules").append('<p class="long" value="'+remote.id+'">'+
						'<span class="c-title">远程教育</span> <span class="c-content">'+remote.name+'</span></p>');
			}
		},
		queryModuleNo: function(module,callback){
			var $this=this;
			module.find('.right').html('<span class="class-number">可选班号</span>');
			var url=rootPath+"/classModuleNo/queryList";
			var data={moduleId:module.attr("value"),classTypeId:$("select.classType").val()};
			$.ajax({
				url: url,
				data: data,
				type: "post",
				dataType:"json",
				success: function(jsonData){
					if(jsonData && jsonData.length>0){
						$.each(jsonData,function(i){
							var moduleNo=jsonData[i];
							var selected=false;
							$.each($this.options.slaves,function(j){
								if(parseInt(module.attr("value"))==$this.options.slaves[j].moduleId && parseInt($this.options.slaves[j].resourceId)==moduleNo.id){
									selected=true;
								}
							});
							var html='';
							if(selected){
								html='<span class="block active" termId="'+moduleNo.examTerm+'" campusId="'+moduleNo.campusId+'">';
							}else{
								html='<span class="block" termId="'+moduleNo.examTerm+'" campusId="'+moduleNo.campusId+'">';
							}
							
							if(module.attr("teachmethod")=="TEACH_METHOD_FACE"){
								html+='<span class="c-content"><b value="'+moduleNo.id+'">'+moduleNo.campusName+"&nbsp;&nbsp;"+moduleNo.name+'</b></span></span>';
							}else{
								html+='<span class="c-content"><b value="'+moduleNo.id+'">'+moduleNo.name+'</b></span></span>';
							}
							module.find('.right').append(html);
						})
					}else{
							module.find('.right').append('<span class="block">'+
								'<span class="c-content"><em>此课程单元还没有绑定任何可售班号，暂时不能招生，请督促安排</em></span></span>');
					}
					
					if(callback){
						callback(jsonData);
					}
					resizeWindow($("div.step2").height());
				}
			})
		},
		//计算费用
		countFee:function(){
			var hasPay=0,trainingFee=0,agentFee=0,totalFee=0;
			$(".money").each(function(){
 				hasPay+=$.MoneyToNum($(this).val());
 			});
			trainingFee=$.MoneyToNum($(".trainingFee").html());//培训费
			agentFee=$.MoneyToNum($(".agentFee").val());//待报考费用
			totalFee=trainingFee+agentFee;//总费用
			needFee=totalFee-hasPay;//待缴费
			$(".totalAmount").html($.formatMoney(totalFee));
 			$(".needPay").html($.formatMoney(needFee));
		},
		showTip: function(ele,txt){
			ele.find(".tip").html(txt);
		}
	};
	$(document).ready(function(){
		var page=new Page();
		page.init();
	})
	

})(jQuery)
