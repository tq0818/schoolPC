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
			$selectMenu("company_member_vip");
			$this.options.student=JSON.parse($("#student").val());
			if($this.options.student &&  $this.options.student.id){
				//保存学员
				$this.options.index=1;
				$(".body").css("left","-100%");
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
				if($("#stuMobiles").val()!=""){
				if(!/^09\d{8}|1[3,4,5,7,8]\d{9}$/.test(_this.find(":text").val())){
					$this.showTip(_this,"不是有效的手机号");
					_this.find(":text").val("");
					return false;
				}
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
			var data=$("#step"+($this.options.index+1)).data("data");
			var validate=eval('this.check'+$this.options.index);
			var checkResult=validate.apply($this);
			if(!checkResult.flag){
				$.msg(checkResult.message);
				return false;
			}
			//保存表单
			var save=eval('this.save'+$this.options.index);
			var successBack=function(){
				//翻到下一步
				if($this.options.index==$this.options.minIndex){
					$this.options.index+=2;
					$this.slide($this.options.index-2,$this.options.index);
				}else{
					$this.options.index+=1;
					$this.slide($this.options.index-1,$this.options.index);
				}
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
			//resizeWindow($("div.step"+index).height());
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
					//resizeWindow($("div.step"+target).height());
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
					//resizeWindow($("div.step"+target).height());
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
			var mobile;
			$.validator.addMethod("isMobile",function(value,element,params){
				if(/^09\d{8}|1[3,4,5,7,8]\d{9}$/.test(value)){
					return true;
				}else{
					return false;
				}
			});
			$("#stuMobiles").on('blur',function(){
				
				if($this.options.student.mobile){
					$this.options.student="";
					$("#student").val(JSON.stringify($this.options.student));
					Page.prototype.clean('mobile');
				}
				var mob=$(this).val();
				if(mob && mob != ""){
					$.ajax({
						url : rootPath+"/student/searchByMobile2/"+mob,
						type: "post",
						dataType :"json",
						success: function(jsonData){
							if(jsonData && jsonData.id){
								$("#stuMobiles").next().remove();
								$this.options.student=jsonData;
								$this.filldata0();
								$("#saveStuInfo").attr("mark","saveExists");
								$this.init1(function(){
									$this.init2();
								})
							}
							if(jsonData.sex){
								if(jsonData.sex=="MALE"){
									$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" checked="checked"> 男 <input type="radio" class="sex" name="sex" value="FEMALE"> 女')
								}else if (jsonData.sex=="FEMALE"){
									$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" > 男 <input type="radio" class="sex" name="sex" value="FEMALE" checked="checked"> 女' )
								}else{
									$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" > 男 <input type="radio" class="sex" name="sex" value="FEMALE" > 女' )
								}
							}else{
								$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" > 男 <input type="radio" class="sex" name="sex" value="FEMALE" > 女' )
							}
							
							if(jsonData.identityTypeCode){
								$(".identityTypeCode").each(function(){
		                            $(this).find("option").each(function(){
	                                    if($(this).val() == jsonData.identityTypeCode){
	                                    	$(".identityTypeCode").val($(this).val());
	                                    }
		                            });
								});
							}else{
								$(".identityTypeCode").val("");
							}
							if(jsonData.educationCode){
								$(".educationCode").each(function(){
									$(this).find("option").each(function(){
										if($(this).val() == jsonData.educationCode){
											$(".educationCode").val($(this).val());
										}
									});
								});
							}else{
								$(".educationCode").val("");
							}
							
							$("#studentG1").find("option").each(function(){
		                     	$(this).removeAttr("selected");
		                     })
		                     $("#studentG1").find("option[value='"+(jsonData.groupOneId?jsonData.groupOneId:'')+"']").attr("selected",true); 
		                     $("#studentG1").html($("#studentG1").html());
		                     selectGroup_2(null,'',(jsonData.groupOneId?jsonData.groupOneId:''));
		                     $("#studentG2").find("option").each(function(){
		                     	$(this).removeAttr("selected");
		                     })
		                     $("#studentG2").find("option[value='"+(jsonData.groupTwoId?jsonData.groupTwoId:'')+"']").attr("selected",true); 
		                     $("#studentG2").html($("#studentG2").html());
						}
					})
				}
			})
			$("#username").on('blur',function(){
				if($this.options.student.username){
					$this.options.student="";
					$("#student").val(JSON.stringify($this.options.student));
					Page.prototype.clean('username');
				}
				var username=$(this).val();
				$("#username_xs").html(username);
				if(username && username != ""){
					$.ajax({
						url : rootPath+"/student/searchByUsername1/"+username,
						type: "post",
						dataType :"json",
						success: function(jsonData){
							if(jsonData && jsonData.id){
								$("#username").next().remove();
								$this.options.student=jsonData;
								$this.filldata0();
								$("#saveStuInfo").attr("mark","saveExists");
								$this.init1(function(){
									$this.init2();
								})
							}
							$("#username").val(jsonData.username);
							if(jsonData.sex){
								if(jsonData.sex=="MALE"){
									$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" checked="checked"> 男 <input type="radio" class="sex" name="sex" value="FEMALE"> 女')
								}else if (jsonData.sex=="FEMALE"){
									$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" > 男 <input type="radio" class="sex" name="sex" value="FEMALE" checked="checked"> 女' )
								}else{
									$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" > 男 <input type="radio" class="sex" name="sex" value="FEMALE" > 女' )
								}
							}else{
								$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" > 男 <input type="radio" class="sex" name="sex" value="FEMALE" > 女' )
							}
							
							if(jsonData.identityTypeCode){
								$(".identityTypeCode").each(function(){
		                            $(this).find("option").each(function(){
	                                    if($(this).val() == jsonData.identityTypeCode){
	                                    	$(".identityTypeCode").val($(this).val());
	                                    }
		                            });
								});
							}else{
								$(".identityTypeCode").val("");
							}
							if(jsonData.educationCode){
								$(".educationCode").each(function(){
									$(this).find("option").each(function(){
										if($(this).val() == jsonData.educationCode){
											$(".educationCode").val($(this).val());
										}
									});
								});
							}else{
								$(".educationCode").val("");
							}
							
							$("#studentG1").find("option").each(function(){
		                     	$(this).removeAttr("selected");
		                     })
		                     $("#studentG1").find("option[value='"+(jsonData.groupOneId?jsonData.groupOneId:'')+"']").attr("selected",true); 
		                     $("#studentG1").html($("#studentG1").html());
		                     selectGroup_2(null,'',(jsonData.groupOneId?jsonData.groupOneId:''));
		                     $("#studentG2").find("option").each(function(){
		                     	$(this).removeAttr("selected");
		                     })
		                     $("#studentG2").find("option[value='"+(jsonData.groupTwoId?jsonData.groupTwoId:'')+"']").attr("selected",true); 
		                     $("#studentG2").html($("#studentG2").html());
						}
					})
				}
			})
			var mobile,username;
			if(document.getElementById("mobile")){
				if($("#_mobile").val()){
					mobile=$("#_mobile").val();
				}else{
					mobile=$("#stuMobiles").val();
				}
			}
			if(document.getElementById("username")){
				username=$("#username").val();
			}
			if(mobile && mobile != ""){
				$.ajax({
					url : rootPath+"/student/searchByMobile2/"+mobile,
					type: "post",
					dataType :"json",
					success: function(jsonData){
						//去掉空值
						$.each(jsonData,function(k,v){
							if(!v){
								delete jsonData[k]
							}
						})
						$this.options.student=jsonData;
					}
				})
			}
			if(username && username != ""){
				$.ajax({
					url : rootPath+"/student/searchByUsername/"+username,
					type: "post",
					dataType :"json",
					success: function(jsonData){
						//去掉空值
						$.each(jsonData,function(k,v){
							if(!v){
								delete jsonData[k]
							}
						})
						$this.options.student=jsonData;
					}
				})
			}
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
	                    username: {
	                    	minlength: 4,
	                    	required: true,
	                    	remote:{
	                    		url: rootPath+"/student/checkByUsername",
	                    		type:"post",
	                    		dataType:"json",
	                    		data:{
	                    			username: function(){
	                    				return $(".username").val();
	                    			}
	                    		}
	                    	}
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
	                    	minlength: 15
	                    },
	                    email: {
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
	                	},
	                	username:{
	                		required: "用户名不能为空",
	                		remote: "该用户名已经被使用"
	                	}
	                },
	                success: function (label) {
	                },
	                submitHandler: function (form) {
	                    form.submit();
	                },
	                onkeyup: false
	            }
			var rules1={
	                errorElement: 'span', 
	                errorClass: 'note', 
	                focusInvalid: false, 
	                ignore: "",
	                rules: {
	                    name: {
	                        minlength: 2,
	                        required: true
	                    },
	                    username: {
	                    	remote:{
	                    		url: rootPath+"/student/checkByUsername",
	                    		type:"post",
	                    		dataType:"json",
	                    		data:{
	                    			username: function(){
	                    				return $(".username").val();
	                    			}
	                    		}
	                    	}
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
	                    	minlength: 15
	                    },
	                    email: {
	                    },
	                    mobile:{
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
	                	},
	                	username:{
	                		remote: "该用户名已经被使用"
	                	}
	                },
	                success: function (label) {
	                },
	                submitHandler: function (form) {
	                    form.submit();
	                },
	                onkeyup: false
	            }
			var rules2={
	                errorElement: 'span', 
	                errorClass: 'note', 
	                focusInvalid: false, 
	                ignore: "",
	                rules: {
	                    name: {
	                        minlength: 2,
	                        required: true
	                    },
	                    username: {
	                    	minlength: 4,
	                    	required: true,
	                    	remote:{
	                    		url: rootPath+"/student/checkByUsername",
	                    		type:"post",
	                    		dataType:"json",
	                    		data:{
	                    			username: function(){
	                    				return $(".username").val();
	                    			}
	                    		}
	                    	}
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
	                    	minlength: 15
	                    },
	                    email: {
	                    },
	                    mobile:{
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
	                		remote: "该手机号已经被使用"
	                	},
	                	username:{
	                		required: "用户名不能为空",
	                		remote: "该用户名已经被使用"
	                	}
	                },
	                success: function (label) {
	                },
	                submitHandler: function (form) {
	                    form.submit();
	                },
	                onkeyup: false
	            }
			var rules3={
	                errorElement: 'span', 
	                errorClass: 'note', 
	                focusInvalid: false, 
	                ignore: "",
	                rules: {
	                    name: {
	                        minlength: 2,
	                        required: true
	                    },
	                    username: {
	                    	remote:{
	                    		url: rootPath+"/student/checkByUsername",
	                    		type:"post",
	                    		dataType:"json",
	                    		data:{
	                    			username: function(){
	                    				return $(".username").val();
	                    			}
	                    		}
	                    	}
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
	                    	minlength: 15
	                    },
	                    email: {
	                    },
	                    mobile:{
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
	                		remote: "该手机号已经被使用"
	                	},
	                	username:{
	                		remote: "该用户名已经被使用"
	                	}
	                },
	                success: function (label) {
	                },
	                submitHandler: function (form) {
	                    form.submit();
	                },
	                onkeyup: false
	            }
			var mobileFlagOk=$("#mobileFlag").val()==1?true:false;
			var usernameFlagOk=$("#usernameFlag").val()==1?true:false;
			if(mobileFlagOk && usernameFlagOk){
				$("#studentForm").validate(rules);
			}else if(mobileFlagOk && !usernameFlagOk){
				$("#studentForm").validate(rules1);
			}else if(!mobileFlagOk && usernameFlagOk){
				$("#studentForm").validate(rules2);
			}else{
				$("#studentForm").validate(rules3);
			}
			
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
					//$("div.step0").find(".identityId").rules("add","required");
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
			$("div.step0").on("blur","[name='mobile']",function(){
				$.ajax({
            		url: rootPath+"/student/check",
            		type:"post",
            		dataType:"json",
            		data:{
            			mobile: $(".mobile").val()
            		},
            		success: function(data){
            			if(!data){
            				//$.msg("学员已经存在,直接去报名",function(){
            					$.ajax({
            						url : rootPath+"/student/searchByMobile2/"+$("#stuMobiles").val(),
            						type: "post",
            						dataType :"json",
            						success: function(jsonData){
            							//去掉空值
            							$.each(jsonData,function(k,v){
            								if(!v){
            									delete jsonData[k]
            								}
            							})
            							$this.options.student=jsonData;
            							$this.init1();
            							//$this.gotoStep(1);
            						}
            					})
            				//})
            			}
            		}
            	})
			})
			$("div.step0").on("blur","[name='username']",function(){
				$.ajax({
            		url: rootPath+"/student/checkByUsername",
            		type:"post",
            		dataType:"json",
            		data:{
            			mobile: $(".username").val()
            		},
            		success: function(data){
            			if(!data){
            				//$.msg("学员已经存在,直接去报名",function(){
            					$.ajax({
            						url : rootPath+"/student/searchByUsername/"+$("#username").val(),
            						type: "post",
            						dataType :"json",
            						success: function(jsonData){
            							//去掉空值
            							$.each(jsonData,function(k,v){
            								if(!v){
            									delete jsonData[k]
            								}
            							})
            							$this.options.student=jsonData;
            							$this.init1();
            							//$this.gotoStep(1);
            						}
            					})
            				//})
            			}
            		}
            	})
			})
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

			//初始化下拉框
			//$(".itemOne").getSysItem();
			//是否代报考
			$(".isAgent").off("click").on("click",function(){
				if($(this).hasClass("open")){
					$(this).removeClass("open").addClass("close").html('&#xe641;').next().html("是");
					$(".materialsInfo").slideDown(300,function(){
						//resizeWindow($("div.step2").height()+50);
					});
					$(".list-infos").find(".long").eq(1).slideDown(300,function(){
						//resizeWindow($("div.step2").height()+50);
					});
					
				}else{
					$(this).removeClass("close").addClass("open").html('&#xe642;').next().html("否");
					$(".materialsInfo").slideUp(300,function(){
						//resizeWindow($("div.step2").height()+50);
					});
					$(".list-infos").find(".long").eq(1).slideUp(300,function(){
						//resizeWindow($("div.step2").height()+50);
					});
				}
				
			})
			$(".year").find("option[value='"+(new Date()).getFullYear()+"']").attr("selected","selected");
			$(".itemOne").off("change").on("change",function(){
				$(".itemSecond").html('').getSysItem($(this).val(),function(){
//					$(".itemSecond").find("option[value='"+$this.options.payMaster.itemSecondId+"']").attr("selected","selected");
//					$(".itemSecond").trigger("change");
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
			//加载课程类型
			$("select.classType").on('change',function(){
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
			    			//	$this.countFee();
			    			}
			    		},
			    		error: function(){
			    			
			    		},
			    		complete:function(){
			    			
			    		}
			    	})
			});
			
		
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
							    			//	$this.countFee();
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
		        	if(!isNaN($(this).val()) && parseInt($(this).val())){
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
		        		//resizeWindow($("div.step2").height());
		        	}else{
		        		$(this).val("");
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
     			oldVal=$.formatMoney($(this).val());
     			oldNeedPay=$.formatMoney($(".needPay1").text());
     			$this.countFee();
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
		//	$this.countFee();
		},
		msgNew : function(text,delay,callback){
			var html='<div class="c-fa" style="margin-left:-260px;">'+ text +'</div>';
			var args=arguments;
			if(args.length>2){
				if(delay&& isNaN(delay)){
					delay=parseInt(delay);
				}else{
					delay=2000;
				}
			}else if(args.length==2){
				if(args[1] && !isNaN(args[1])){
					delay=parseInt(delay);
				}else{
					callback=args[1];
					delay=2000;
				}
			}else{
				delay=2000;
			}
			
			$(html).appendTo('body').fadeIn(100).delay(delay).fadeOut(200,function(){
				if(callback){
					callback();
				}
				$(this).remove();
			});
		},
		//存学员
		save0: function(successBack,errorBack){
			//检查用户是否已经是会员
			var $this=this;
			if($this.options.student.id){
				//修改
				var data=$("#studentForm").getParams()+"&id="+$this.options.student.id;//+"&mobile="+$(".mobile").val();
			}else{
				//新增
				var data=$("#studentForm").getParams(); //+"&mobile="+$(".mobile").val();;
			}
			if(document.getElementById("studentG1")){
				data+="&groupOneId="+$("#studentG1").val();
			}
			if(document.getElementById("studentG2")){
				data+="&groupTwoId="+$("#studentG2").val();
			}
			mobile=$("#stuMobiles").val();
			username=$("#username").val();
			if($("#mobileFlag").val()==1 &&  mobile == "" ){
				$.msg("请先填写手机号！");
				return false;
			}	
			if($("#usernameFlag").val()==1 &&  username == "" ){
				$.msg("请先填写用户名！");
				return false;
			}
			if($("#mobileFlag").val()!=1 && $("#usernameFlag").val()!=1 && mobile == "" &&  username == "" ){
				$.msg("手机号，用户名至少填写一个！");
				return false;
			}
			if(username){
				var myusername= /^[a-zA-Z]+[a-zA-Z0-9_]\w{2,14}$/; 
				if(!myusername.test(username)){
					Page.prototype.msgNew("用户名必须以字母开头，并且只是以字母、数字、下划线的4~16个字符组成！");
					return false;
				}
			}
				$.ajax({
					url: rootPath+"/companyMemberConfig/checkVip",
					data: {"mobile":mobile,"username":username},
					type: "post",
					dataType: "text",
					async: false,
					success : function(isVip){
						if(isVip=="true"){
							$.msg("此学员已经是会员！");
						}else{
							if($("#sName").val()=="" ||$("#sName").val()==null){
								$.msg("请先填写姓名！");
								return;
							}
							$.ajax({
								url: rootPath+"/companyMemberConfig/saveStudent",
								data: data,
								type: "post",
								dataType: "json",
								async: false,
								success : function(student){
									if(student){
										$this.options.student=student;
										$("#student").val(JSON.stringify(student));
										$(".G1Name").html($("#studentG1").find("option:selected").text()!="一级分组"?$("#studentG1").find("option:selected").text():"");
					                    $(".G2Name").html($("#studentG2").find("option:selected").text()!="二级分组"?$("#studentG2").find("option:selected").text():"");
										$this.init1();
										//初始化界面
										$this.init2();
										$("select.classType").trigger("change");
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
							//查询会员等级list
							$.ajax({
								url: rootPath+"/companyMemberConfig/selectVips",
								type: "post",
								dataType: "json",
								success : function(vips){
									$("#selectVips").empty(); 
									$("#selectVips").append("<option value=''>请选择</option>");
									$.each(vips,function(i,item){  
										$("#selectVips").append("<option value='"+item.id+"' detailopenway='"+item.openWay+"'>"+item.name+"</option>");
								    });
									$("#selectVipDetail").empty(); 
									$("#selectVipDetail").append("<option value=''>请选择</option>");
								}
							})
						}
						
					},
					error : function(){
						$.msg("会员检查失败");
					}
				})
				
				$("#username_xs").html($("#username").val());
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
			var stuMobile,username;
			if(document.getElementById("mobile")){
				stuMobile=$(".mobile").val();
			}
			if(document.getElementById("username")){
				username=$(".username").val();
			}
			memberId=$(".selectVips").val();
			memberLevel=$(".selectVips").find("option:selected").text();
			buyLength=$(".selectVipDetail").val();
			buyName=$(".selectVipDetail").find("option:selected").text();
			pos=$("#pos").val();
			cash=$("#cash").val();
			cheque=$("#cheque").val();
			remit=$("#remit").val();
			needPay=$.MoneyToNum($("#needPay1").val());
			detailId=$('#selectVipDetail option:selected').attr("detailId");
			if(pos<0 || pos=="" || pos==null ){
				pos=-1;
			}else{
				pos=$.MoneyToNum($("#pos").val());
			}
			if(cash<0 || cash=="" || cash==null){
				cash=-1;
			}else{
				cash=$.MoneyToNum($("#cash").val());
			}
			if(cheque<0 || cheque=="" || cheque==null){
				cheque=-1;
			}else{
				cheque=	$.MoneyToNum($("#cheque").val());
			}
			if(remit<0 || remit=="" || remit==null){
				remit=-1;
			}else{
				remit=$.MoneyToNum($("#remit").val());
			}
			if($("#saveOk").val()==1){
				$("#saveOk").val(0);
				$.ajax({
					url: rootPath+"/companyMemberConfig/insertCompanyMember",
					data: {
						"username":username,
						"stuMobile":stuMobile,
						"memberId":memberId,
						"memberLevel":memberLevel,
						"buyLength":buyLength,
						"buyName":buyName,
						"pos":pos,
						"cash":cash,
						"cheque":cheque,
						"remit":remit,
						"needPay":needPay,
						"detailId":detailId,
						"detailOpenWay":$("#detailOpenWay").val(),
						"groupOneId":$("#studengG1").val(),
						"groupTwoId":$("#studengG2").val()
					},
					type: "post",
					dataType: "text",
					async: false,
					beforeSend:function(){
						$(".step2").find(".nextstep").off("click.page");
					},
					success : function(isSuccess){
						if(isSuccess=="true"){
		    				$.msg("添加会员成功!",3000,function(){
		    					location.href=rootPath+"/companyMemberConfig/companyMemberVip";
		    				})
		    			}else{
		    				$.msg("添加会员失败");
		    				$("#saveOk").val(1);
		    			}
	//					if(successBack){
	//						successBack();
	//					}
					},
					error : function(){
						$.msg("添加会员失败");
	//					if(errorBack){
	//						errorBack();
	//					}
						$("#saveOk").val(1);
					},
					complete: function(){
						$(".step2").find(".nextstep").on(function(){
							$this.nextStep.apply($this);
						});
					}
				})
			}
		//	$this.countFee();
		},
		
		check0: function(){
			var msg={};
			
			var mark=$("#saveStuInfo").attr("mark");
			if(mark && "saveExists"==mark){
				msg.flag=true;
			}else{
				msg.flag=$("#studentForm").valid();
				if(!$("#studentForm").valid()){
					msg.message="填写不合格，请注意红色部分";
				}
			}
			var stuMobiles=$("#stuMobiles").val();
			if(stuMobiles==""){
			}else{
				if(/^09\d{8}|1[3,4,5,7,8]\d{9}$/.test($("#stuMobiles").val())){
					
				}else{
					msg.flag=false;
					msg.message="手机号格式不正确!";
				}
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
			if(!$(".selectVips").val()){
				msg.flag=false;
				msg.message="未选择会员等级";
				return msg;
			}
			if($("#becomeMember").val()==0){
			
				if($("#detailOpenWay").val()==0){
					if(!$(".selectVipDetail").val()){
						msg.flag=false;
						msg.message="未选择会员有效期";
						return msg;
					}
					if(!$("#pos").val()&&!$("#cash").val()&&!$("#cheque").val()&&!$("#remit").val()){
						msg.flag=false;
						msg.message="本期缴费不能为空";
						return msg;
					}
					needPay=$.MoneyToNum($("#needPay1").val());
					hasPay=0;
					$(".money").each(function(){
		 				hasPay+=$.MoneyToNum($(this).val());
		 			});
					if(needPay-hasPay!=0){
						msg.flag=false;
						msg.message="缴费数额不正确";
						$("#pos").val("");
						$("#cash").val("");
						$("#cheque").val("");
						$("#remit").val("");
						hasPay=0;
						$(".money").each(function(){
			 				hasPay+=$.MoneyToNum($(this).val());
			 			});
						$(".needPay1").html($.formatMoney(needPay-hasPay));
						return msg;
					}
				}else if($("#detailOpenWay").val()==1){
					
				}
			}else if($("becomeMember").val()==1){
				
			}
			return msg;
		},
		//计算费用
		countFee:function(){
			var hasPay=0,trainingFee=0,agentFee=0,totalFee=0;
			totalFee=$.MoneyToNum($("#needPay1").val());
			$(".money").each(function(){
 				hasPay+=$.MoneyToNum($(this).val());
 			});
			hasPay=hasPay.toFixed(2);
		//	trainingFee=$.MoneyToNum($(".trainingFee").html());//培训费
		//	agentFee=$.MoneyToNum($(".agentFee").val());//待报考费用
		//	totalFee=trainingFee+agentFee;//总费用
			needFee=totalFee-hasPay;//待缴费
			$(".totalAmount").html($.formatMoney(totalFee));
 			$(".needPay1").html($.formatMoney(needFee));
		},
		showTip: function(ele,txt){
			ele.find(".tip").html(txt);
		},
		clean: function(type){
			if(type=="mobile"){
				$("#username").val('');
			}else if(type=="username"){
				$("#stuMobiles").val('');
			}
			$("#sName").val('');
			$(".identityId").val('');
			$(".birthday").val('');
			$(".age").val('');
			$(".hkAddress").val('');
			$(".homePhone").val('');
			$(".officePhone").val('');
			$(".emergencyContact").val('');
			$(".emergencyPhone").val('');
			$(".email").val('');
			$(".qq").val('');
			$(".weixinId").val('');
			$("#sexSelect").html('<input type="radio" class="sex" name="sex" value="MALE" checked="checked"> 男 <input type="radio" class="sex" name="sex" value="FEMALE" > 女' );
			$(".identityTypeCode").find("option").each(function(){
             	$(this).removeAttr("selected");
            })
            $(".educationCode").find("option").each(function(){
            	$(this).removeAttr("selected");
            })
            $("#studentG1").find("option").each(function(){
            	$(this).removeAttr("selected");
            })
            $("#studentG2").find("option").each(function(){
            	$(this).removeAttr("selected");
            })
            selectGroup1('');
            $("#identityTypeCode").find("option").each(function(){
            	$(this).removeAttr("selected");
            })
			
		}
	};
	$(document).ready(function(){
		var page=new Page();
		page.init();
		selectGroup1('');
	})
})(jQuery)
	function selectVipDetail(){
		$(".needPay1").text($.formatMoney(0));
		$("#needPay1").val($.formatMoney(0));
		detailOpenWay=$(".selectVips").find("option:selected").attr("detailopenway");
		$("#detailOpenWay").val(detailOpenWay);
		
		$.ajax({
			url: rootPath+"/companyMemberConfig/selectVipDetail",
			data:"id="+$('#selectVips option:selected').val(),
			type: "post",
			dataType: "json",
			success : function(vips){
				$("#selectVipDetail").empty(); 
				if($("#becomeMember").val()==0){
					if(detailOpenWay=="1"){
						$("#selectVipDetail").append("<option value='-1'>无限期</option>");
						if(typeof($("#pos").attr("disabled"))=="undefined"){
							$("#pos").attr("disabled","disabled");
						}
						if(typeof($("#cash").attr("disabled"))=="undefined"){
							$("#cash").attr("disabled","disabled");
						}
						if(typeof($("#cheque").attr("disabled"))=="undefined"){
							$("#cheque").attr("disabled","disabled");
						}
						if(typeof($("#remit").attr("disabled"))=="undefined"){
							$("#remit").attr("disabled","disabled");
						}
						$("#pos").val("");
						$("#pos").attr("placeholder","此会员不用缴费");
						$("#cash").val("");
						$("#cash").attr("placeholder","此会员不用缴费");
						$("#cheque").val("");
						$("#cheque").attr("placeholder","此会员不用缴费");
						$("#remit").val("");
						$("#remit").attr("placeholder","此会员不用缴费");
					}else if(detailOpenWay=="0"){
						$("#selectVipDetail").append("<option value=''>请选择</option>");
						$.each(vips,function(i,item){  
							$("#selectVipDetail").append("<option value='"+item.length+"' detailId='"+item.id+"'>"+item.name+"</option>");
					    });
						if(typeof($("#pos").attr("disabled"))!="undefined"){
							$("#pos").removeAttr("disabled");
						}
						if(typeof($("#cash").attr("disabled"))!="undefined"){
							$("#cash").removeAttr("disabled");
						}
						if(typeof($("#cheque").attr("disabled"))!="undefined"){
							$("#cheque").removeAttr("disabled");
						}
						if(typeof($("#remit").attr("disabled"))!="undefined"){
							$("#remit").removeAttr("disabled");
						}
						$("#pos").val("");
						$("#cash").val("");
						$("#cheque").val("");
						$("#remit").val("");
						if($("#becomeMember").val()!=1){
							$("#pos").attr("placeholder","输入金额");
							$("#cash").attr("placeholder","输入金额");
							$("#cheque").attr("placeholder","输入金额");
							$("#remit").attr("placeholder","输入金额");
						}
					}
				}else if($("#becomeMember").val()==1){
					$("#selectVipDetail").append("<option value='-1'>无限期</option>");
					$(".needPay1").text($.formatMoney(0));
					$("#needPay1").val($.formatMoney(0));
					$("#pos").attr("readOnly","true");
					$("#cash").attr("readOnly","true");
					$("#cheque").attr("readOnly","true");
					$("#remit").attr("readOnly","true");
					$("#pos").val("");
					$("#pos").attr("placeholder","此会员不用缴费");
					$("#cash").val("");
					$("#cash").attr("placeholder","此会员不用缴费");
					$("#cheque").val("");
					$("#cheque").attr("placeholder","此会员不用缴费");
					$("#remit").val("");
					$("#remit").attr("placeholder","此会员不用缴费");
				}
			}
		})
	}
	function selectVipPrice(){
		if($("#detailOpenWay").val()==0){
			$.ajax({
				url: rootPath+"/companyMemberConfig/selectvipPrice",
				data:"id="+$('#selectVipDetail option:selected').attr("detailId"),
				type: "post",
				success : function(vips){
					$(".needPay1").text($.formatMoney(vips.price));
					$("#needPay1").val($.formatMoney(vips.price));
				}
			})
			$("#pos").val("");
			$("#cash").val("");
			$("#cheque").val("");
			$("#remit").val("");
		}else if($("#detailOpenWay").val()==1){
			$(".needPay1").text($.formatMoney(0));
			$("#needPay1").val($.formatMoney(0));
			$("#pos").val("");
			$("#cash").val("");
			$("#cheque").val("");
			$("#remit").val("");
		}
	}
	function selectGroup1(type){
		$("#studentG1"+type).html('');
		$("#studentG2"+type).html('');
		 $.ajax({
	    	url: rootPath+"/studentGroup/selectStudentGroup1",
	    	type: "post",
	    	dataType: "json",
	    	async:false,
	    	success: function(jsonData){
	    		$("#studentG1"+type).append('<option value="" selected="selected">一级分组</option>');
	    		$.each(jsonData,function(i, g){
	    			$("#studentG1"+type).append('<option value="' + g.id + '">'
					+ g.groupName + '</option>');
	    		})
	    		$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
	    	}
	     })
	}
	function selectGroup2(a,type){
		$("#studentG2"+type).html('');
		if($("#studentG1"+type).val()==""){
			$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
			return false;
		}
		$.ajax({
	    	url: rootPath+"/studentGroup/selectStudentGroup2/"+$("#studentG1"+type).val(),
	    	type: "post",
	    	dataType: "json",
	    	async:false,
	    	success: function(jsonData){
	    		$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
	    		$.each(jsonData,function(i, g){
	    			$("#studentG2"+type).append('<option value="' + g.id + '">'
					+ g.groupName + '</option>');
	    		})
	    	}
	     })
	}

	function selectGroup_2(a,type,val){
		$("#studentG2"+type).html('');
		if(val==""){
			$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
			return false;
		}
		$.ajax({
	    	url: rootPath+"/studentGroup/selectStudentGroup2/"+val,
	    	type: "post",
	    	dataType: "json",
	    	async:false,
	    	success: function(jsonData){
	    		$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
	    		$.each(jsonData,function(i, g){
	    			$("#studentG2"+type).append('<option value="' + g.id + '">'
					+ g.groupName + '</option>');
	    		})
	    	}
	     })
	}