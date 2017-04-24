/**
 * author zhang.zx
 * zhang.zx
 * 页面js封装
 */
(function($){
	var rules={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	        	tuserName: {
	                required: true,
	                maxlength:12,
	                remote: {
                		url: rootPath+"/authPrivilege/checkUserName",
                		type:"post",
                		dataType:"json",
                		data:{
                			userName : function(){
                				return $("#tuserName").val();
                			}
                		}
                	}
	            },
	            password:{
	            	 required: true,
	            	 minlength:6
	            },
	            confirmpwd:{
	            	 required: true,
	            	 minlength:6,
	            	 equalTo:"#password"
	            },
	            realName:{
	            	 required: true
	            },
	            tMobile:{
	            	required:true,
	            	maxlength:11,
	            	minlength:11,
	            	digits:true,
	            	remote: {
                		url: rootPath+"/sysConfigTeacher/checkMobiles",
                		type:"post",
                		dataType:"json",
                		data:{
                			mobile : function(){
                				return $("#tMobile").val();
                			}
                		}
                	}
	            }
	        },
	        messages: {
	        	tuserName: {
	        		required:"用户名不能为空",
	        		maxlength:"用户名长度必须小于12个字符",
	        		remote:"<i></i>"
	        	},
	        	realName:{
	        		required:"姓名不能为空"
	        	},
	        	 password:{
	            	 required: "密码不能为空"
	            },
	        	confirmpwd: {
	        		required:"密码不能为空",
					equalTo:"密码输入不一致"
				},
				tMobile:{
		            	required:"手机号不能为空",
		            	maxlength:"请输入正确手机号",
		            	minlength:"请输入正确手机号",
		            	digits:"请输入正确手机号",
		            	remote:"手机号已存在"
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
				//打开添加教师
				$(".box-select").on('click',function(){
	 				$(".teacherpopuwin").popup("show");
	 				Form.clearData();
	 				$(".m-btn-red").removeClass("disabled");
	 			});
				$("#addTeachersForm").validate(rules);
				
				//打开视频选择框
//				$(".t-c-r").on("click.btn.right",".chooseVideo",function(){
//					 $('.add-layer-bg').fadeIn(200,function(){
//		               $('.w800').fadeIn(200);
//		             })
//				});
			},
			addTeacher : function(){
				if($("#addTeachersForm").valid()){
					var tuserName,password,realName,tMobile;
					tuserName=$("#tuserName").val();
					password=$("#password").val();
					realName=$("#realName").val();
					tMobile=$("#tMobile").val();
					if($(".m-btn-red").hasClass("disabled")){
						return;
					}
					$(".m-btn-red").addClass("disabled");
					$.ajax({
						type : 'post',
						url : rootPath+"/sysConfigTeacher/addTeacher",
						data : {"tname":tuserName,"password":password,"realName":realName,"tMobile":tMobile},
						dataType : 'json',
						success: function(jsonData){
							$("#teacherList").append('<option value="'+jsonData.id+'">'+jsonData.name+'</option>');
							$(".teacherpopuwin").popup("hide");
						}
					});
				}else{
					console.log("校验不通过");
				}
			},
			clearData : function(){
				$("#tuserName").val('');
				$("#password").val('');
				$("#confirmpwd").val('');
				$("#realName").val('');
				$("#tMobile").val('');
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)