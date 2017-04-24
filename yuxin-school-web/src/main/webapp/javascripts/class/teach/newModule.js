(function($){
	function Module(){
		this.settings.form="#moduleForm";
		this.settings.savbtn=".save";
		this.settings.init=[];
		this.settings.rules={
			errorElement : 'span',
			errorClass : 'note',
			focusInvalid : false,
			ignore : "",
			rules : {
				name : {
					required : true,
					remote : {
						url : rootPath + "/classModule/checkName",
						type : "post",
						dataType : "json",
						data : {
							name : function() {
								return $("#name").val();
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
	}
	Module.prototype={
		init : function(options){
			this.settings=$.extend({},this.settings,options);
			bind();
		},
		//保存表单
		save : function(){
			if(!$().valid()){
				return;
			}
			var form = $("#addForm").serialize();
			$.ajax({
				url:rootPath+"/classModule/saveModule",
				type:"post",
				data:form,
				success:function(data){
					$("#moduleId").val(data);
					//在录播模块不显示弹框
					$.msg("保存成功!",function(){$("#addForm")[0].submit();});
				},
				error:function(){
					$.msg("保存失败!");
					history.go(0);
				}
			})
		},
		//绑定事件
		bind : function(){
			var $this=this;
			//初始化数据
			if(options.init){
				for(var i=0;i<options.init.length;i++){
					var ele=options.init[i].key;
					var val=options.init[i].value;//只在select 里有用
					var txt=options.init[i].text;
					if($(ele).is("input")){
						$(ele).val(txt);
					}else if($(ele).is("select")){
						$(ele).append('<option value="'+val+'">'+txt+'</option>');
					}else{
						$(ele).html(txt);
					}
				}
			}
			//添加表单校验
			$(options.form).validate(options.rules);
			var moduleId = $("#moduleId").val();
			if (moduleId) {
				$(".item").attr("disabled", "disabled");
				$(".module").attr("style", "display:none");
				var moduleType = $("#moduleType").val();
				$("#" + moduleType).attr("selected", "selected");

				$("#method1").val("TEACH_METHOD_VIDEO");
				$("#method").val("录播");
				$(options.savbtn).html("下一步");
				$(".head").attr("style", "display:block");
				$(".moduleName").rules("remove", "remote");
				$(".moduleName").rules("add", {
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
				})
			}
			//绑定保存按钮点击事件
			$(options.savbtn).on('click',function(){
				$this.save(options);
			});
		}
	}
})(jQuery)