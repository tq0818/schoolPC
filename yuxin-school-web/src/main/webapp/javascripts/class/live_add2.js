(function($) {
	var formRules = {
		errorElement : 'span',
		errorClass : 'note',
		focusInvalid : false,
		ignore : "",
		rules : {
			name : {
				required : true,
				remote : {
					url : rootPath + "/classModule/checkName2",
					type : "post",
					dataType : "json",
					data : {
						name : function() {
							return $("#name").val();
						},
						id : function() {
							return $("#moduleId").val();
						}
					}
				}
			},
			totalClassHour : {
				required : true,
				
			}
			
		},
		messages : {
			name : {
				required : "<i class='icons'></i>请输入模块名称",
				remote : "<i class='icons'></i>这个模块名称已被使用!"
			},
			totalClassHour : {
				required : "<i class='icons'></i>请输入总课时",
				//remote : "<i class='icons'></i>这个邮箱还没有注册!"
			},

		},
		invalidHandler : function(event, validator) {
		},

		highlight : function(element) {
		},

		unhighlight : function(element) {
		},

		success : function(label) {
		},

		submitHandler : function() {
		},
		onkeyup : false
	}
	
	
	
	$(document).ready(function() {
		 $selectMenu("course_module");
		$("#addForm1").validate(formRules);
	});
})(jQuery)

