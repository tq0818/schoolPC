$(function(){
	      $selectMenu("course_module");
				
				var teachMethod = $("#teachMethod").val();
				//alert(teachMethod);
				//alert(teachMethod);
				$("#"+teachMethod).addClass("btn-success");
				$("#method1").val($("#"+teachMethod).attr("fValue"));
				$("#method").val($("#"+teachMethod).html());
				$(".bt1").bind("click",function(){
					$(this).parent().find(".btn-success").removeClass("btn-success");
					$(this).addClass("btn-success");
					$("#teachMethod").val($(this).attr("fValue"));
					$("#editForm").attr("action",rootPath+"/classModule/toAddModule2");
					$("#editForm")[0].submit();
				})
				
				//$(".footer").addClass("footer-fixed");
				var moduleId=$("#moduleId").val();
				if(moduleId!=null&&moduleId!=''){
					$(".h5").html("编辑课程单元");
				}
				
				
				
				$("#one").bind("change",function(){
					$("#two").html("");
					var itemOneId = $(this).val();
					//alert(itemOneId);
					$.ajax({
						url:rootPath+"/sysConfigItem/getSecondItemByOne",
						type:"post",
						data:{
							"itemOneId":itemOneId
						},
						success:function(jsonData){
							
							for (var i = 0; i < jsonData.length; i++) {
								var str=' <option value="'+jsonData[i].id+'"> '+jsonData[i].itemName+'</option>'; 
								//alert(str);
								$("#two").append(str);
							}
						}
					})
				})
			})
			function toSave(){
				if(!$("#editForm").valid()){
					return;
				}
				var form = $("#editForm").serialize();
				$.ajax({
					url:rootPath+"/classModule/saveRemote",
					type:"post",
					data:form,
					success:function(data){
						$.msg("保存成功!");
						$("#editForm")[0].submit();
					}
				})

			}