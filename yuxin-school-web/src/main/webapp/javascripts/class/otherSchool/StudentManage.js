(function($){

	var student={
		init: function(){
			var $this=this;
			$selectMenu("course_class_type");
			$("#signMoney").bind("keyup",function(event){
    			//先把非数字的都替换掉，除了数字和. 
    			$(this).val($(this).val().replace(/[^\d.]/g,""));
    	        //必须保证第一个为数字而不是. 
    	        $(this).val($(this).val().replace(/^\./g,"0."));
    	        //保证只有出现一个.而没有多个. 
    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
    	        //保证.只出现一次，而不能出现两次以上
    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
    		})
    		$("#signMoney").bind("blur",function(event){
    			//先把非数字的都替换掉，除了数字和. 
    			$(this).val($(this).val().replace(/[^\d.]/g,""));
    			var value=$(this).val();
     	        var is=false;
     	        for (var i = 0; i < value.length; i++) {
     	            var  item =  value.charAt(i);
     	           	if("."==item){
     	           		is=true;
     	           	}
     	        }
     	        if(value!=null||value!=""){
     	        	if(!is){
     	        		$(this).val($(this).val()+".00");	
     	        	}
     	        } 
    	        //必须保证第一个为数字而不是. 
    	        $(this).val($(this).val().replace(/^\./g,"0."));
    	        //保证只有出现一个.而没有多个. 
    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
    	        //保证.只出现一次，而不能出现两次以上
    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
    	        //保留小数点后两位
    	        $(this).val($(this).val().substring(0,$(this).val().indexOf(".")+3));
    		})
			//校验数据
			$(".validateData").on('click',function(){
				var name=$("#imgData").val();
				if(name==""){
					$.msg("请选择文件");
					return;
				}
				$(".loading.check").show();
	            $(".loading-bg").show();
				$.ajaxFileUpload({
					url : rootPath+"/manageStudent/validateData",
					secureuri : false,// 安全协议
					async : false,
					fileElementId : 'imgData',
					type : "POST",
					//dataType : "json",
//					beforeSend:function(XMLHttpRequest){
//			            $(".loading").show();
//			            $(".loading-bg").show();
//			        },
					success : function(jsonData) {
						jsonData=JSON.parse(jsonData);
						var html='<span style="font-size:14px;">校验结果:</span><br/>';
						if(jsonData.teletmperror){
							html+='<span style="color:red;font-size:14px;">'+jsonData.teletmperror+'</span>';
							$(".insertData").addClass("none");
							$(".chooseFile").removeClass("none");
						}else{
							if(jsonData.error && jsonData.error.length>0){
								$.each(jsonData.error,function(i,data){
									html+='<span style="font-size:14px;">'+(i+1)+"、"+data+'</span><br/>';
								});
								$(".chooseFile").removeClass("none");
								$(".newinsert").removeClass("none");
								$(".allupdate").removeClass("none");
								$(".chooseInsert").addClass("none");
							}else{
								html+='<span style="color:red;font-size:14px;">无错误信息!</span>';
								$(".chooseFile").addClass("none");
								$(".newinsert").addClass("none");
								$(".allupdate").addClass("none");
								$(".chooseInsert").removeClass("none");
							}
							$("#markUrl").val(jsonData.exclePath);
						}
						$("#returnInfo").html(html);
						var mobilelists="";
						if(jsonData.stusMobile && jsonData.stusMobile.length>0){
							$.each(jsonData.stusMobile,function(i,item){
								mobilelists+="'"+item+"'"+",";
							});
						}
						$("#stuMobiles").val(mobilelists);
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        },
					error : function(resp,msg,err){
						console.log(resp);
					}
				});
				$("#fileNames").val("");
			})
			//导入学员信息
			$(".insertData").on('click',function(){
				var $this=$(this);
				if($this.hasClass("btn-default")){
					return;
				}
				$this.removeClass("btn-primary").addClass("btn-default");
				var n=$("#markUrl").val();
				var mark=$(this).attr("mark");
				if(n==""){
					$.msg("请选择文件并校验数据");
					$this.addClass("btn-primary").removeClass("btn-default");
					return;
				}
				var fileUrl=$("#markUrl").val();
				var classtypeId=$("#stuClassTypeId").val();
				var lable=$("#lableTypes").val();
				$.ajax({
					url : rootPath+"/student/insertStudents",
					data: {"filePath":$("#markUrl").val(),"mark":mark,"groupOneId":$("#studentG1").val(),"groupTwoId":$("#studentG2").val()},
					type: 'post',
					dataType :'json',
					beforeSend:function(){
						$(".loading.insert").show();
			            $(".loading-bg").show();
					},
					success: function(jsonData){
						$("#markUrl").val("");
						if(jsonData=="error"){
							$.msg("导入信息有误！");
							$this.addClass("btn-primary").removeClass("btn-default");
							$("#returnInfo").html("");
							$(".newinsert").addClass("none");
							$(".allupdate").addClass("none");
							return;
						}
						//成功后删除临时文件夹
						$.msg("导入信息完毕",function(){
							$.ajax({
								url : rootPath+"/student/deleteFile",
								type: 'post',
								success:function(data){
									$("#DataForm")[0].submit();
									$this.addClass("btn-primary").removeClass("btn-default");
								}
							});
						});
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			})
			//重新导入信息
			$(".chooseFile").on('click',function(){
				$("#imgData").trigger('click');
			})
		}
	}
	
	$(document).ready(function(){
		student.init();
		selectGroup1('');
	})
})(jQuery)