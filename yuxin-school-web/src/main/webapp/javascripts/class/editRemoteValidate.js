(function($) {
	
	//alert(schoolName);
	var formRules = {
		errorElement : 'p',
		errorClass : 'tips',
		focusInvalid : false,
		ignore : "",
		rules : {
			name : {
				required : true
				
			},
			major : {
				required : true,
				remote : {
					url : rootPath + "/classModule/checkMajor",
					type : "post",
					dataType : "json",
					data : {
						major : function(){
							return $("#major").val().trim();
						},
						schoolName: function(){
							return $("#schoolName").val().trim();
						}
							
						
					}
				}
				
			}
			
		},
		messages : {
			name : {
				required : "<i class='icons'></i>请输入合作院校",
				
			},
			major : {
				required : "<i class='icons'></i>请输入专业",
				remote : "<i class='icons'></i>该专业已经存在"
				
				
			}

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
		//alert($("#editForm").attr("action"));
		$("#editForm").validate(formRules);
		
	});
})(jQuery)

