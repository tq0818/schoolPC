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
			maxIndex: 2,
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
			//获取被选择的用户信息 student
			$this.options.student=JSON.parse($("#student").val());
			if($this.options.student &&  $this.options.student[0].id){
				//保存学员
				//$this.options.index=1;
				$this.options.index=2;
				$(".body").css("left","0%");
				//$(".body").css("left","-100%");
//				resizeWindow($("div.step1").height()+50);
			}else{
				$this.options.index=0;
				$(".body").css("left","0%");
//				resizeWindow($("div.step0").height()+50);
			}
			//初始化界面
			$this.init0(function(){
				$this.init1(function(){
					$this.init2()
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
							    				}
							    				$this.options.isInit=false;
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
			
			//绑定下一步，上一步按钮事件
			$(document).on("click.page",".nextstep",function(){
				$this.nextStep.apply($this);
			});	
			
			$(document).on("click.page",".prevstep",function(){
				$this.prevStep.apply($this);
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
			
		},
		//下一步
		nextStep: function(){
			var $this=this;
			if($this.options.index>$this.options.maxIndex){
				return false;
			}
			var data=$("#step"+($this.options.index)).data("data");
			var validate=eval('this.check'+$this.options.index);
			var checkResult=validate.apply($this);
			if(!checkResult.flag){
				$.msg(checkResult.message);
				return false;
			}
			//保存表单
			var save=eval('this.save'+$this.options.index);
			var successBack=function(){
//				//翻到下一步
//				if($this.options.index==$this.options.minIndex){
//					$this.options.index+=2;
//					$this.slide($this.options.index-2,$this.options.index);
//				}else{
//					$this.options.index+=1;
//					$this.slide($this.options.index-1,$this.options.index);
//				}
			}
			var errorBack=function(){
				//alert("保存失败");
			}
			save.call($this,successBack,errorBack);
			
			
		},
		//上一步
		prevStep: function(){
			var $this=this;
			if($this.options.index<=$this.options.minIndex){
				return false;
			}
			this.options.index-=1;
			$this.slide($this.options.index+1,$this.options.index);
		},
		gotoStep: function(index){
			var $this=this;
			if(index>$this.options.maxIndex || index<$this.options.minIndex){
				return false;
			}
			if(index==0){
				//$this.filldata0();
				$("div.step"+$this.options.index).parent().css("left","0%");
			}else{
				$("div.step"+$this.options.index).parent().css("left","-"+100*index+"%");
			}
			$(".u-wrap").find(".nopadding").find("div.steps").find("li").removeClass("active").removeClass("hover");
			$(".u-wrap").find(".nopadding").find("div.steps").find("li").each(function(i){
				if(i<$this.options.index){
					$(this).addClass("active");
				}
				if(i==$this.options.index){
					$(this).addClass("hover");
				}
			})
			$this.options.index=index;
			resizeWindow($("div.step"+index).height());
		},
		//当前在哪一步
		currtStep : function(){
			return this.options.index;
		},
		
		slide: function(src,target){
			var $this=this;
			var owidth=$("div.step"+src).width();
			if(target>$this.options.maxIndex){
				return;
			}
			if(src<target){
				//左移
				$("div.step"+src).parent().animate({left:"-"+(100*target)+"%"},300,function(){
					resizeWindow($("div.step"+target).height());
				})
				$(".u-wrap").find(".nopadding").find("div.steps").find("li").removeClass("active").removeClass("hover");
				$(".u-wrap").find(".nopadding").find("div.steps").find("li").each(function(i){
					if(i+1<$this.options.index){
						$(this).addClass("active");
					}
					if(i+1==$this.options.index){
						$(this).addClass("hover");
					}
				})
			}else{
				//右移
				$("div.step"+src).parent().animate({left:"-"+(100*target)+"%"},300,function(){
					resizeWindow($("div.step"+target).height());
				})
				$(".u-wrap").find(".nopadding").find("div.steps").find("li").removeClass("active").removeClass("hover");
				$(".u-wrap").find(".nopadding").find("div.steps").find("li").each(function(i){
					if(i+1<$this.options.index){
						$(this).addClass("active");
					}
					if(i+1==$this.options.index){
						$(this).addClass("hover");
					}
				})
			}
		},
		submit: function(callback){
			if(callback){
				callback();
			}
		},
		init0: function(callback){
			var $this=this;
			$.validator.addMethod("isMobile",function(value,element,params){
				if(/^09\d{8}|1[3,4,5,7,8]\d{9}$/.test(value)){
					return true;
				}else{
					return false;
				}
			});
//			$.ajax({
//				url : rootPath+"/student/searchByMobile2/"+$("#_mobile").val(),
//				type: "post",
//				dataType :"json",
//				success: function(jsonData){
//					$this.options.student=jsonData;
//				}
//			})
			$.extend($.validator.messages, {
			    required: "必选字段",
				remote: "请修正该字段",
				email: "请输入正确格式的电子邮件",
				url: "请输入合法的网址",
				dateNL: "请输入合法的日期",
				dateISO: "请输入合法的日期 (ISO).",
				number: "请输入合法的数字",
				digits: "只能输入整数",
				creditcard: "请输入合法的信用卡号",
				equalTo: "请再次输入相同的值",
				accept: "请输入拥有合法后缀名的字符串",
				maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
				minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
				rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
				range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
				max: jQuery.validator.format("请输入一个最大为{0} 的值"),
				min: jQuery.validator.format("请输入一个最小为{0} 的值"),
				isMobile : "不是有效的手机号"
			});
			var rules={
	                errorElement: 'span', 
	                errorClass: 'note', 
	                focusInvalid: false, 
	                ignore: "",
	                rules: {
	                    name: {
	                        minlength: 2,
	                        required: true
	                    },
	                    age:{
	                    	maxlength:2,
	                    	digits : true
	                    },
	                    birthday:{
	                    	date : true
	                    },
	                    identityTypeCode:{
	                    },
	                    identityId:{
	                    	minlength: 15,
	                    	remote: {
	                    		url: rootPath+"/student/check",
	                    		type:"post",
	                    		dataType:"json",
	                    		data:{
	                    		}
	                    	}
	                    },
	                    email: {
	                        email: true,
	                        remote: {
	                    		url: rootPath+"/student/check",
	                    		type:"post",
	                    		dataType:"json",
	                    		data:{
	                    			
	                    		}
	                    	}
	                    },
	                    mobile:{
	                    	required: true,
	                    	isMobile: true,
	                    	remote: {
	                    		url: rootPath+"/student/check",
	                    		type:"post",
	                    		dataType:"json",
	                    		data:{
	                    			mobile: function(){
	                    				return $(".mobile").val();
	                    			}
	                    		}
	                    	}
	                    }
	                },
	                messages: {
	                	identityId: {
	                		minlength:"不是有效的身份证",
	                		required: "证件号码不能为空",
	                		remote: "该身份证号已经被使用"
	                	},
	                	mobile: {
	                		required: "手机号不能为空",
	                		remote: "该手机号已经被使用"
	                	}
	                },
	                success: function (label) {
	                },
	                submitHandler: function (form) {
	                    form.submit();
	                },
	                onkeyup: false
	            }
			$("#studentForm").validate(rules);
			//证件类型切换事件  如果为身份证，做完整校验，如果是不确定证件，不做校验，其他的不做号码校验
			$("div.step0").find(".identityTypeCode").on("change.txt.page",function(e){
				if($(this).find("option:selected").val()=="ID_IDCARD"){
					$("div.step0").find(".identityId").rules("add",{
                    	minlength: 15,
                    	remote: {
                    		url: rootPath+"/student/check",
                    		type:"post",
                    		dataType:"json",
                    		data:{
                    		}
                    	}
                    });
				//自动计算出生日期
				$("div.step0").find(".identityId").off("change");
				$("div.step0").find(".identityId").on("change",function(){
					//获取输入身份证号码 
					var UUserCard = $(this).val().length==18?$(this).val():$(this).val().substring(0,6)+"19"; 
					var re=new RegExp("/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i");
					if(!UUserCard || re.test(UUserCard)){
						return;
					}
					//获取出生日期 
					var birthday=UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14); 
					console.log(birthday);
					$("div.step0").find(".birthday").val(birthday);
					$("div.step0").find(".sex").removeAttr("checked");
					//获取性别
					if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) { 
						$("div.step0").find(".sex[value='MALE']").attr("checked","checked"); 
					} else { 
						$("div.step0").find(".sex[value='FEMALE']").attr("checked","checked"); 
					} 
					//获取年龄 
					var myDate = new Date(); 
					var month = myDate.getMonth() + 1; 
					var day = myDate.getDate();
					var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1; 
					if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) { 
					age++; 
					} 
					$("div.step0").find(".age").val(age);
				});
				}else if($(this).find("option:selected").val()=="ID_UNCONFM_ID"){
					$("div.step0").find(".identityId").rules("remove");
					$("div.step0").find(".identityId").off("change");
				}else{
					$("div.step0").find(".identityId").rules("remove","minlength remote");
					$("div.step0").find(".identityId").rules("add","required");
					$("div.step0").find(".identityId").off("change");
				}
			});
			$("div.step0").find(".mobile").val($this.options.student.mobile);
			$("div.step0").find(".identityTypeCode").getSysDict("IDENTITY_TYPE",function(){$(".identityTypeCode").trigger("change.txt.page")},"bycode",$this.options.student.identityTypeCode);
			$("div.step0").find(".educationCode").getSysDict("EDUCATION",null,"bycode",$this.options.student.educationCode);
			$.each($this.options.student,function(key,value){
				if(value && value!='null'){
					if($("div.step0").find("."+key).is(":text")){
						$("div.step0").find("."+key).val(value);
					}
					if($("div.step0").find("."+key).is(":radio")){
						$("div.step0").find("."+key+"[value='"+value+"']").attr("checked","checked");
					}
					if($("div.step0").find("."+key).is("select")){
						$("div.step0").find("."+key).find("option[value='"+value+"']").attr("selected","selected");
					}
				}
			})
			if(this.options.student.id){
				$("div.step0").find(".saveStudent").show();
			}else{
				$("div.step0").find(".saveStudent").hide();
			}
			if(callback){
				callback();
			}
		},
		init1: function(callback){
			var $this=this;
			$.each($this.options.student,function(key,value){
				if(value && value!='null'){
					$("div.step1").find("."+key).html(value);
				}
			})
			$.transeDict($("div.step1").find(".identityTypeCode").html(),function(text){
				$("div.step1").find(".identityTypeCode").html(text);
			});
			$.transeDict($("div.step1").find(".educationCode").html(),function(text){
				$("div.step1").find(".educationCode").html(text);
			});
			$.transeDict($("div.step1").find(".sex").html(),function(text){
				$("div.step1").find(".sex").html(text);
			});
			if(callback){
				callback();
			}
		},
		init2: function(callback){
			var $this=this;
			$this.options.slaves=[];

			//是否代报考
			$(".isAgent").off("click").on("click",function(){
				if($(this).hasClass("open")){
					$(this).removeClass("open").addClass("close").html('&#xe641;').next().html("是");
					$(".materialsInfo").slideDown(300,function(){
						resizeWindow($("div.step2").height()+50);
					});
					$(".list-infos").find(".long").eq(1).slideDown(300,function(){
						resizeWindow($("div.step2").height()+50);
					});
					
				}else{
					$(this).removeClass("close").addClass("open").html('&#xe642;').next().html("否");
					$(".materialsInfo").slideUp(300,function(){
						resizeWindow($("div.step2").height()+50);
					});
					$(".list-infos").find(".long").eq(1).slideUp(300,function(){
						resizeWindow($("div.step2").height()+50);
					});
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
//								    				for(var i=0;i<$this.options.remotes.length;i++){
//								    					var remote={};
//								    					remote.stuId=$this.options.student.id;
//								    					remote.resourceType='TEACH_METHOD_REMOTE';
//								    					remote.resourceId=$this.options.remotes[i].id;
//								    					$this.options.slaves.push(remote);
//								    				}
							    				}
							    				$this.options.isInit=false;
//							    				$(".modules").html('');
//							    				$this.getModules(callback);
//							    				$this.getRemotes();
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

			//缴费
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
		        		resizeWindow($("div.step2").height());
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
		filldata0: function(callback){
			var data=$("divdiv.step0").data("data") || this.options.student;
			if(data){
				$.each(data,function(key,value){
					if($("div.step0").find("."+key).is("input")){
						$("div.step0").find("."+key).val(value);
					}
					if($("div.step0").find("."+key).is("select")){
						$("div.step0").find("."+key).find("opiton[value='"+value+"']").attr("selected","selected");
					}
					if($("div.step0").find("."+key).is("span")){
						$("div.step0").find("."+key).html(value);
					}
				});
				if(callback){
					callback(data);
				}
			}else{
				
			}
		},
		
		filldata1: function(){
			var data=$("div.step1").data("data") || this.options.student;
			if(data){
				$.each(data,function(key,value){
					$("div.step1").find("."+key).html(value);
				})
			}else{
				console.log("没有获取到学员信息");
			}
		},
		//查订单
		filldata2: function(callback){
			var $this=this;
			//代报考
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
			//缴费
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
		//存学员
		save0: function(successBack,errorBack){
			var $this=this;
			if($this.options.student.id){
				//修改
				var data=$("#studentForm").getParams()+"&id="+$this.options.student.id+"&mobile="+$(".mobile").val();
			}else{
				//新增
				var data=$("#studentForm").getParams()+"&mobile="+$(".mobile").val();;
			}
			$.ajax({
				url: rootPath+"/student/saveStudent",
				data: data,
				type: "post",
				dataType: "json",
				async: false,
				success : function(student){
					if(student){
						$this.options.student=student;
						$("#student").val(JSON.stringify(student));
						$this.init1();
						//初始化界面
						$this.init2();
						console.log("学员已保存");
						if(successBack){
							successBack();
						}
					}else{
						$.msg("学员保存失败");
						if(errorBack){
							errorBack();
						}
					}
					
				},
				error : function(){
					$.msg("学员保存失败");
					if(errorBack){
						errorBack();
					}
				}
			})
		},
		//什么都不做
		save1: function(successBack,errorBack){
			this.init2();
			if(successBack){
				successBack();
			}
		},
		//存订单
		save2: function(successBack,errorBack){
			var $this=this,payMaster={},slave={};
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
			
			var materialInfo=new Array(),stageInfo=new Array();
			
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
				url: rootPath+"/studentPayMaster/savePayMasterMany",
				data: $this.options.payMaster,
				type: "post",
				dataType: "json",
				async: false,
				beforeSend:function(){
					$(".step2").find(".nextstep").addClass("disabled");
				},
				success : function(data){
					if(data.status == "success"){
						$this.options.payMaster=data.payMaster;
						if($this.options.ns){
		    				$.alert("报名成功，同时为该学员生成了网校的账户，账户名称是学员的手机号，初始密码是手机号后六位，已通过短信形式通知学员",function(){
		    					location.href=rootPath+"/otherSchool/studentList/"+$("#stuClassTypeId").val()+"/"+$("#lable").val();
			    			})
		    			}else{
		    				$.msg("报名已完成!",3000,function(){
		    					location.href=rootPath+"/otherSchool/studentList/"+$("#stuClassTypeId").val()+"/"+$("#lable").val();
		    				})
		    			}
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
		
		check0: function(){
			var msg={};
			msg.flag=$("#studentForm").valid();
			if(!$("#studentForm").valid()){
				msg.message="填写不合格，请注意红色部分";
			}
			return msg;
		},
		check1: function(){
			var msg={};
			msg.flag=true;
			return msg;
		},
		check2: function(){
			var msg={};
			msg.flag=true;
			if(!$("select.classType").val() || $("select.classType").val()=='null' ){
				//没有选班型
				msg.flag=false;
				msg.message="没有选班型";
				return msg;
			}
//			if(!$(".term").find("option:selected").val() || $(".term").val()=='null'){
//				//没有选考期
//				msg.flag=false;
//				msg.message="没有选考期";
//				return msg;
//			}
//			if($(".modules").find("p").length<=0){
//				msg.flag=false;
//				msg.message="没有模块";
//			}
//			$(".modules").find(".public").each(function(){
//				if($(this).find(".right .c-content").children("em").length>0){
//					//没有安排课程
//					msg.flag=false;
//					msg.message="请选择可招生的班号";
//					return false;
//				}
//				if($(this).find(".right").find(".block.active").length<=0){
//					//没有选班号
//					msg.flag=false;
//					msg.message="没有选班号";
//					return false;
//				}
//			});
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
