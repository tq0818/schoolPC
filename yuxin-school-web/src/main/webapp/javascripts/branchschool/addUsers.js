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
                		url: rootPath+"/authPrivilege/checkUserName",
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
				//$(".footer").addClass("footer-fixed");
				var type=$("#type").val();
				if(type=="update"){
					$("#pcOne").css("display","none");
					var userId=$("#uId").val();
					//选中当前用户角色
					$.ajax({
						url : rootPath + "/permissionManger/queryRolesById/"+userId,
						type : "post",
						dataType : "json",
						success : function(result) {
							$.each(result,function(i,role){
								$(".people-list").find("a").each(function(i){
									var id=$(this).attr("ids");
									if(id==role.roleUid){
										$(this).addClass("btn-success");
									}
									var statu=$(this).hasClass("btn-success");
									if(statu){
										var cid=$(this).attr("ids");
										window.Form.addCatgory(cid);
									}
								});
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
						
						var cid=_this.attr("ids");
						if(cid==6){
							$("#contactTeacher").css("display","none");
						}
						if($(".people-list").find("a.btn-success").length){
							$.ajax({
								url : rootPath + "/permissionManger/Category/"+cid,
								type : "post",
								dataType : "json",
								success : function(result) {
									$.each(result,function(i,tree){
										if(tree && tree.id){
											$(".clear li").find("p.c").each(function(i){
												var mark=$(this).attr("marks");
												if(tree.id==mark){
													//$(this).children("i").html("&#xe609;");
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
						var cid=$(this).attr("ids");
						if(cid==6){
							type=cid;
						}
					}
				});
				$("#tList").html('');
				$.ajax({
					url : rootPath + "/sysConfigTeacher/queryTeacherDetail",
					type : "post",
					dataType : "json",
					data:{"teacherName":teacherName,"teacherType":type},
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
			editUserMsg : function(){
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
						if(t==6){
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
					$(".loading-bg").hide();
					return;
				}
				if(count>1){
					$.msg("一个账号只能绑定一个老师");
					$(".loading-bg").hide();
					return;
				}
				$("#teachersId").val(teachersId);
				$("#tsId").val(tId);
				//代理机构
				var proxyOrgId;
				if($('#org-manage').hasClass('btn-success')){
					proxyOrgId = $('.org-list').find('a.btn-success').attr('ids');
					if(!proxyOrgId){
						$.msg("选择了代理机构角色，必须选择机构选项！");
						$(".loading-bg").hide();
						return false;
					}
					$('#proxyOrgId').val(proxyOrgId);
				}
				if(type=="save"){
					var chong=0;
					var mob=$("#mobile").val();
					if(mob!=""){
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
						$.msg("手机号已存在");
						$(".loading-bg").hide();
						return;
					}
					if($("#saveUserForm").valid()){
						$("#saveUserForm").attr("action",rootPath+"/permissionManger/saveUser").submit();
					}else{
						$(".loading-bg").hide();
						return;
					}
				}else{
						var name=$("#nameMark").val();
						$("#usernames").val(name);
						var pwd=$("#confirmPassword").val();
						if(pwd!=""){
							if($("#saveUserForm").valid()){
								 $("#saveUserForm").attr("action",rootPath+"/permissionManger/updateUser").submit();
							}else{
								$(".loading-bg").hide();
								return;
							}
						}else{
							 $("#saveUserForm").attr("action",rootPath+"/permissionManger/updateUser").submit();
						}
						$(".loading-bg").hide();
				}
				
			},
			addCatgory : function(cid){
				$.ajax({
					url : rootPath + "/permissionManger/Category/"+cid,
					type : "post",
					dataType : "json",
					success : function(result) {
						$.each(result,function(i,tree){
							if(tree && tree.id){
								$(".clear li").find("p.c").each(function(i){
									var mark=$(this).attr("marks");
									if(tree.id==mark){
										//$(this).children("i").html("&#xe60a;");
										$(this).children("i").html("&#xe60a;").prev().html("&#xe60a;");
										$(this).parent().css("display","block");
									}
								});
							}
						});
						
					}
				});
				if(cid==6){
					$("#contactTeacher").css("display","block");
				}
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)