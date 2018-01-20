/**
 * author zhang.zx
 * 用户权限
 * 页面js封装
 */
(function($){
	var rules={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	        	username: {
	                required: true,
	                maxlength:12,
	                remote: {
                		url: rootPath+"/authPrivilege/checkUserNameOrMobile?curcompanyId="+$('#companyId').val(),
                		type:"post",
                		dataType:"json",
                		data:{
                			userName : function(){
                				return $("#userName").val();
                			}
                		}
                	}
	            },
	            password:{
	            	 minlength:6
	            },
	            confirmPassword:{
	            	 minlength:6,
	            	 equalTo:"#password"
	            },
	            realName:{
	            	 required: true
	            },
	            email:{
	            	required:true,
	            	email:true
	            },
	            mobile:{
	            	required:true,
	            	maxlength:11,
	            	minlength:11,
	            	digits:true
	            }
	        },
	        messages: {
	        	username: {
	        		required:"登录账号不能为空",
	        		maxlength:"用户名长度必须小于12个字符",
	        		remote:"<i></i>"
	        	},
	        	realName:{
	        		required:"姓名不能为空"
	        	},
	        	email: {
	        		required:"邮箱不能为空",
	        		email:"请输入正确格式的电子邮件"
	        	},
				confirmPassword: {
					equalTo:"密码输入不一致"
				},
				 mobile:{
		            	required:"手机号不能为空",
		            	maxlength:"请输入正确手机号",
		            	minlength:"请输入正确手机号",
		            	digits:"请输入正确手机号"
		            }
	        },
	        success: function (label) {
	        
	        },
	        submitHandler: function (form) {
	            form.submit();
	        },
	        onkeyup: false
	}
	var Form={
			init : function(){
				$.extend($.validator.messages, {
				    required: "必选字段",
					remote: "请修正该字段",
					email: "请输入正确格式的电子邮件",
					url: "请输入合法的网址",
					date: "请输入合法的日期",
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
				var type=$("#type").val();
				if("save"==type){
					$('#userName').val('');
				}
				if(type=="update"){
					$("#pcOne").css("display","none");
					var userId=$("#uId").val();
					var data={};
					data.companyId=$("#companyId").val();
					//选中当前用户角色
					$.ajax({
						url : rootPath + "/authRole/queryRolesById/"+userId,
						type : "post",
						dataType : "json",
						data:data,
						success : function(result) {
							$.each(result,function(i,role){
								$(".people-list").find("a").each(function(i){
									var id=$(this).attr("ids");
									if(id==role.roleUid){
										$(this).addClass("btn-success");
									}
								});
							});
							$(".people-list").find("a").each(function(i){
								var statu=$(this).hasClass("btn-success");
								if(statu){
									var cid=$(this).attr("ids");
									window.Form.addCatgory(cid);
								}
							});
							//代理机构管理员初始化
							if($('#org-manage').hasClass('btn-success')){
								$("#org-manage-list").show();
								var proxyOrgId = $("#proxyOrgId").val();
								$('.org-list').find('a').each(function(){
									var _this = $(this);
									if(_this.attr('ids') == proxyOrgId){
										_this.click();
									}
								})
							}
						}
					});
					
				}
				//代理机构管理员 -- start
				var userorg_roleopenflag = $.getFunctionSet('USERORG_ROLEOPENFLAG');
				if(userorg_roleopenflag == 1){
					$('#org-manage').show();
				}
				$(document).on('click','#org-manage',function(){
					if($(this).hasClass('btn-success')){
						$("#org-manage-list").show();
					}else{
						$("#org-manage-list").hide();
					}
				})
				$(".org-list").on('click','a.btn',function(){
					var _this=$(this),
						status= _this.hasClass('btn-success');
					if(status){
						_this.removeClass('btn-success');
					}else{
						_this.addClass('btn-success');
					}
					_this.siblings().each(function(){
            			$(this).removeClass('btn-success');
                	})
				})
				//代理机构管理员 -- end
				//选中角色添加菜单
				$(".people-list").on('click','a.btn',function(){
					if($("#userType_status").val()=="USER_TYPE_ORG" && $(this).attr("ids")==1){
						return;
					}
					var _this=$(this),status= _this.hasClass('btn-success');
					if(!status){
						 _this.addClass('btn-success');
						var cid=_this.attr("ids");
						window.Form.addCatgory(cid);
					}else{
						_this.removeClass('btn-success');
						$(".pri-list").find("li").hide().find(".iconfont").html('&#xe609;');
						
						var cid=$(this).attr("ids");
						if('直播老师'== $.trim($(this).html())){
							$("#contactTeacher").css("display","none");
						}
						if($(".people-list").find("a.btn-success").length){
							$.ajax({
								url : rootPath + "/authRolePrivilege/Category/"+cid,
								type : "post",
								data:data,
								dataType : "json",
								success : function(result) {
									$.each(result,function(i,tree){
										if(tree && tree.id){
											$(".clear li").find("p.c").each(function(i){
												var mark=$(this).attr("marks");
												if(tree.id==mark){
													$(this).children("i").html("&#xe609;").prev().html("&#xe609;");
													$(this).parent().css("display","none");
												}
											});
										}
									});
									//点击取消时判断重复菜单
									$(".people-list").find("a").each(function(i){
										var status=$(this).hasClass("btn-success");
										if(status){
											var cid=$(this).attr("ids");
											window.Form.addCatgory(cid);
										}
									});
								}
							});
						}
						
					}
				});
				//过滤角色roleUid=99
				$(".people-list").find("a.btn").each(function(i){
					var _Id=$(this).attr("ids");
					if(_Id==99||_Id==97||_Id==98||_Id==8||_Id==96||_Id==51 ||_Id==149){
						$(this).addClass("none");
					}
				});
				$("#saveUserForm").validate(rules);
			},
			searchTeacher : function(){
				var type=0;
				var teacherName=$("#teacherName").val();
				if(teacherName==""){
					alert("请输入搜索条件!");
					return;
				}
				$(".people-list").find("a").each(function(i){
					var status=$(this).hasClass("btn-success");
					if(status){
						if('直播老师'== $.trim($(this).html())){
							type=cid;
						}
					}
				});
				$("#tList").html('');
				$.ajax({
					url : rootPath + "/sysConfigTeacher/queryTeacherDetail",
					type : "post",
					dataType : "json",
					data:{"teacherName":teacherName,"teacherType":type,"ccompanyId":$('#companyId').val()},
					success : function(result) {
						if(result.length<=0){
							alert("没有找到相应的记录!");
							return;
						}
						var html="";
						$.each(result,function(i,item){
							if(item.sex=="MALE"){
								html+="<a href='javascript:;' style='width:300px;'><input type='checkbox' name='tlist' value='"+item.id+"'/><span>"+item.teacherName+" 男 "+item.mobile+"</span></a>";
							}else{
								html+="<a href='javascript:;' style='width:300px;'><input type='checkbox' name='tlist' value='"+item.id+"'/><span>"+item.teacherName+" 女 "+item.mobile+"</span></a>";
							}
						});
						$("#tList").append(html);
					}
				});
			},
			editUserMsg : function(evt){
				if(!$("#saveUserForm").valid()){
					return;
				}
				$(".loading-bg").show();
				var b=true;
				var count=0;
				var type=$("#type").val();
				//得到选中角色
				var rolesId="";
				$(".people-list").find("a").each(function(i){
					var sta=$(this).hasClass("btn-success");
					if(sta){
						rolesId+=$(this).attr("ids")+",";
						var t=$(this).attr("ids");
						if('直播老师'== $.trim($(this).html())){
							$(".teacher-list").find("a input[type=checkbox]").each(function(){
								var _status=$(this).is(":checked");
								if(_status){
									count+=1;
								}
							});
							if(count<=0){
								b=false;
							}
						}
					}
				});
				$("#rolsesId").val(rolesId);
				//得到选中教师
				var teachersId="";
				var tId="";
				var count=0;
				$(".teacher-list").find("a input[name=tlist]").each(function(i){
					var status=$(this).is(":checked");
					if(status){
						count++;
						teachersId+=$(this).attr("value")+",";
					}else{
						tId+=$(this).attr("value")+",";
					}
				});
				if(!b){
					$.msg("请添加教师");
					evt.preventDefault();
					$(".loading-bg").hide();
					return false;
				}
				if(count>1){
					$.msg("一个账号只能绑定一个老师");
					evt.preventDefault();
					$(".loading-bg").hide();
					return false;
				}
				$("#teachersId").val(teachersId);
				$("#tsId").val(tId);
				//代理机构
				var proxyOrgId;
				if($('#org-manage').hasClass('btn-success')){
					proxyOrgId = $('.org-list').find('a.btn-success').attr('ids');
					if(!proxyOrgId){
						$.msg("选择了代理机构角色，必须选择机构选项！");
						evt.preventDefault();
						$(".loading-bg").hide();
						return false;
					}
					$('#proxyOrgId').val(proxyOrgId);
				}
				if(type=="save"){
					var chong=0;
					var mob=$("#mobile").val();
					var mob1=$("#mobile1").val();
					if(mob!=mob1){
						//验证手机号
						$.ajax({
							url : rootPath+"/register/checkMobile",
							type : "post",
							dataType : "json",
							async:false, 
							data:{mobile : mob},
							success : function(result) {
								if(!result){
									chong++;
								}
							}
						});
					}
					if(chong>0){
						$('#mobile-error').text("手机号已存在");
						evt.preventDefault();
						$(".loading-bg").hide();
						return false;
					}
					if($("#saveUserForm").valid()){
						$("#saveUserForm").attr("action",rootPath+"/permissionManger/saveUser");
					}else{
						evt.preventDefault();
						$(".loading-bg").hide();
						return false;
					}
				}else{
						var name=$("#nameMark").val();
						$("#usernames").val(name);
						var chong=0;
						var mob=$("#mobile").val();
						var mob1=$("#mobile1").val();
						if(mob!=mob1){
                            //验证手机号
                            $.ajax({
                                url : rootPath+"/register/checkMobile",
                                type : "post",
                                dataType : "json",
                                async:false,
                                data:{mobile : mob},
                                success : function(result) {
                                    if(!result){
                                        chong++;
                                    }
                                }
                            });
						}
						if(chong>0){
							$('#mobile-error').text("手机号已存在");
							evt.preventDefault();
                            $(".loading-bg").hide();
                            return false;
						}
						var pwd=$("#confirmPassword").val();
						if(pwd!=""){
							if($("#saveUserForm").valid()){
								 $("#saveUserForm").attr("action",rootPath+"/permissionManger/updateUser");
							}else{
								evt.preventDefault();
								$(".loading-bg").hide();
								return false;
							}
						}else{
							 $("#saveUserForm").attr("action",rootPath+"/permissionManger/updateUser");
						}
						$(".loading-bg").hide();
				}
			},
			getSchool:function(){
				var areaCode=$('#schoolAaraCode').val();
				var areaInfoVos=sessionStorage.getItem("schoolData");
				var optionSchoolStr="";
				$.each(JSON.parse(areaInfoVos),function(i,data){
					if(data.areaCode==areaCode){
						$.each(data.schoolInfos,function(i,dt){
							optionSchoolStr+='<option value="'+dt.schoolCode+'" >'+dt.schoolName+'</option>';
						});
					}
				});
				$('#schoolCode').empty();
				$('#schoolCode').append(optionSchoolStr);
			},
			deleteElement:function(obj){
				$(obj).parent().remove();
			},
			getClassInfo:function(obj){
				var gradeName=$(obj).val();
				var gradeInfos=sessionStorage.getItem("gradeInfo");
				var optionSchoolStr="";
				$.each(JSON.parse(gradeInfos),function(i,data){
					if(data.gradeName==gradeName){
						$.each(data.classInfos,function(i,dt){
							optionSchoolStr+='<option value="'+dt.className+'" >'+dt.className+'班</option>';
						});
					}
				});
				$(obj).next().empty();
				$(obj).next().append(optionSchoolStr);
			},
			addClassInfo:function(){
				var gradeInfos=sessionStorage.getItem("gradeInfo");
				var gradeInfos1=JSON.parse(gradeInfos);
				$(".addEdit").parent().remove();
				var optionStr='<p class="c"><select name="subJectGradeCode" onChange="Form.getClassInfo(this);">';
				//循环年级
				$.each(JSON.parse(gradeInfos),function(i,dt){
					optionStr+='<option value="'+dt.gradeName+'">'+dt.gradeName+'级</option>';
				});
				//循环班级
				optionStr+='</select><select name="subjectClassCode">';
				$.each(gradeInfos1[0].classInfos,function(i,dat){
					optionStr+='<option value="'+dat.className+'" >'+dat.className+'班</option>';
				});
				optionStr+='</select><a href="##" class="editDelete" onClick="Form.deleteElement(this);">删除</a></p>';
				optionStr+='<p class="c"><button class="btn btn-default addEdit" onClick="Form.addClassInfo(this)">+</button></p></li>';
				$('.headmasterToB').append(optionStr);
			},
			addCatgory : function(cid){
				var data={};
				data.roleId=cid;
				data.userId=$("#uId").val();
				data.companyId=$('#companyId').val();
				$.ajax({
					url : rootPath +"/authRolePrivilege/getCategorysInfo",
					type : "post",
					dataType : "json",
					data:data,
					success : function(result) {
						$.each(result.privilegeVos,function(i,tree){
							if(tree && tree.id){
								$(".clear li").find("p.c").each(function(i){
									var mark=$(this).attr("marks");
									if(tree.id==mark){
										$(this).children("i").html("&#xe60a;").prev().html("&#xe60a;");
										$(this).parent().css("display","block");
									}
								});
							}
						});
						//获取所选角色
						var roleName=result.roleName;
						//获得区、校
						if("区县负责人"==roleName){
							$('.areamasterToB').attr("style","display:block");
							var areaInfoVos=result.areaInfoVos;
							//填充区
							var htmlContent='<p class="c-title" marks="3"><span>区县负责人</span></p><p class="c"><span>选择区域：</span><br/>'+
											'<select name="earaCode" id="earaCode">';
							var optionStr="";
							$.each(areaInfoVos,function(i,data){
								if(data.selected){
									optionStr+='<option value="'+data.areaCode+'" selected ="selected">'+data.areaName+'</option>';
								}else{
									optionStr+='<option value="'+data.areaCode+'">'+data.areaName+'</option>';
								}
							});
							htmlContent+=optionStr+'</select></p>';
							$('.areamasterToB').html(htmlContent);
							return;
						}
						if("学校负责人"==roleName){
							$('.schoolmasterToB').attr("style","display:block");
							//填充学校
							var areaInfoVos=result.areaInfoVos;
							var str = JSON.stringify(areaInfoVos); 
							sessionStorage.setItem("schoolData",str);
							var htmlContent='<p class="c-title" marks="3"><span>学校负责人</span></p>'+
											'<p class="c" ><span>选择学校：</span><br/><select name="schoolAaraCode" id="schoolAaraCode" onChange="Form.getSchool()">';
							var optionStr="";
							var selectCount=0;
							//填充区
							$.each(areaInfoVos,function(i,data){
								if(data.selected){
									optionStr+='<option value="'+data.areaCode+'" selected ="selected">'+data.areaName+'</option>';
									selectCount=i;
								}else{
									optionStr+='<option value="'+data.areaCode+'">'+data.areaName+'</option>';
								}
							});
							htmlContent+=optionStr+'</select><select name="schoolCode" id="schoolCode" style="width:200px;">';
							//填充学校
							var optionSchoolStr="";
							$.each(areaInfoVos[selectCount].schoolInfos,function(i,data){
								if(data.selected){
									optionSchoolStr+='<option value="'+data.schoolCode+'" selected ="selected">'+data.schoolName+'</option>';
								}else{
									optionSchoolStr+='<option value="'+data.schoolCode+'" >'+data.schoolName+'</option>';
								}
							});
							htmlContent+=optionSchoolStr+'</select></p>';
							$('.schoolmasterToB').append(htmlContent);
							return;
						}
						if("班主任"==roleName){
							$('.classTeacherToB').attr("style","display:block");
							//获取年级、班级
							var gradeInfoVos=result.gradeInfoVos;
							//填充年级、班级
							//填充学校
							var gradeInfo = JSON.stringify(gradeInfoVos); 
							sessionStorage.setItem("gradeInfo",gradeInfo);
							var classInfoStr='<p class="c-title"><span>班主任</span></p><p class="c" ><span>班级：</span><br/><select name="gradeCode" id="gradeCode" onChange="Form.getClassInfo(this);">';
							var optionStr="";
							var selectCount=0;
							//填充区
							$.each(gradeInfoVos,function(i,data){
								if(data.selected){
									optionStr+='<option value="'+data.gradeName+'" selected ="selected">'+data.gradeName+'级</option>';
									selectCount=i;
								}else{
									optionStr+='<option value="'+data.gradeName+'">'+data.gradeName+'级</option>';
								}
							});
							classInfoStr+=optionStr+'</select><select name="classCode" id="classCode">';
							//填充学校
							var optionSchoolStr="";
							$.each(gradeInfoVos[selectCount].classInfos,function(i,data){
								if(data.selected){
									optionSchoolStr+='<option value="'+data.classId+'" selected ="selected">'+data.className+'班</option>';
								}else{
									optionSchoolStr+='<option value="'+data.classId+'" >'+data.className+'班</option>';
								}
							});
							classInfoStr+=optionSchoolStr+'</select></p>';
							$('.classTeacherToB').html(classInfoStr);
							return;
						}
						if("任课老师"==roleName){
							$('.headmasterToB').attr("style","display:block");
							//获取年级、班级
							var gradeInfoVos=result.gradeInfoVos;
							var gradeInfo = JSON.stringify(gradeInfoVos); 
							sessionStorage.setItem("gradeInfo",gradeInfo);
							var optionStr='<p class="c-title" marks="3"><span>任课老师</span></p><p class="c" ><span>科目：</span>'+
										'<select name="subjectCode" id="subjectCode">';
							//获取科目
							var subjectInfoVos=result.subjectInfoVos;
							
							//填充科目
							$.each(subjectInfoVos,function(i,data){
								if(data.selected){
									optionStr+='<option value="'+data.subjectCode+'" selected ="selected">'+data.subjectName+'</option>';
								}else{
									optionStr+='<option value="'+data.subjectCode+'">'+data.subjectName+'</option>';
								}
							});
							optionStr+='</select></p><p class="c"><span>班级：</span><br/>';
							//填充年级班级
							var eduSubjectClassTeacherInfos=result.eduSubjectClassTeacherInfos;
							if(eduSubjectClassTeacherInfos!=''){
								$.each(eduSubjectClassTeacherInfos,function(i,data){
									var gradeName=data.gradeName;
									var classNo=data.eduClass;
									optionStr+='<p class="c"><select name="subJectGradeCode" onChange="Form.getClassInfo(this);">';
									//循环年级
									var selectCount=0;
									$.each(gradeInfoVos,function(i,dt){
										if(gradeName==dt.gradeName){
											optionStr+='<option value="'+dt.gradeName+'" selected ="selected">'+dt.gradeName+'级</option>';
											selectCount=i;
										}else{
											optionStr+='<option value="'+dt.gradeName+'">'+dt.gradeName+'级</option>';
										}
									});
									
									//循环班级
									optionStr+='</select><select name="subjectClassCode">';
									$.each(gradeInfoVos[selectCount].classInfos,function(i,dat){
										if(dat.className==classNo){
											optionStr+='<option value="'+dat.className+'" selected ="selected">'+dat.className+'班</option>';
										}else{
											optionStr+='<option value="'+dat.className+'" >'+dat.className+'班</option>';
										}
									});
									optionStr+='</select><a href="##" class="editDelete" onClick="Form.deleteElement(this);">删除</a></p>';
								});
							}else{
								optionStr+='<p class="c"><select name="subJectGradeCode" onChange="Form.getClassInfo(this);">';
								//循环年级
								var selectCount=0;
								$.each(gradeInfoVos,function(i,dt){
									optionStr+='<option value="'+dt.gradeName+'">'+dt.gradeName+'级</option>';
								});
								//循环班级
								optionStr+='</select><select name="subjectClassCode">';
								$.each(gradeInfoVos[selectCount].classInfos,function(i,dat){
									optionStr+='<option value="'+dat.className+'" >'+dat.className+'班</option>';
								});
								optionStr+='</select><a href="##" class="editDelete" onClick="Form.deleteElement(this);">删除</a></p>';
							}
							optionStr+='<p class="c"><button class="btn btn-default addEdit" onClick="Form.addClassInfo(this)">+</button></p>';
							$('.headmasterToB').html(optionStr);
						}
					}
				});
				if('直播老师'== $.trim($("a[ids='"+cid+"']").html())){
					$("#contactTeacher").css("display","block");
				}
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)