$(function() {
		if($("#lableTypes").val()){
			$selectMenu("course_class_type");
		}else{
			$selectSubMenu('student_change');
		}
		selectType(4);
		var itemOneId = $("#itemOneId").val();
		var classTypeId = $("#classTypeId").val();
		var payMasterId = $("#payMasterId").val();

		//初始化校区
		$.ajax({
			url: rootPath+"/classType/"+classTypeId,
			type: "get",
			dataType: "json",
			success: function(jsonData){
				if(jsonData.faceFlag==1 || jsonData.remoteFlag==1){
					$(".list-infos .long").eq(0).show();
				}
				$.ajax({
					url : rootPath + "/sysConfigCampus/getCampusesJson",
					type: "post",
					dataType: "json",
					success: function(jsonData){
						$(".schools").html('')
						$.each(jsonData,function(i){
							var data=jsonData[i];
							$(".schools").append('&nbsp;&nbsp;<a href="javascript:;" class="btn btn-sm btn-default school" value="'+data.id+'">'+data.campusName+'</a>');
						})
					}
				});
			}
		})
		
			//初始化考期
			$.ajax({
				url:rootPath+"/sysConfigTerm/dict?itemOneId="+itemOneId,
				type:"post",
				dataType : "json",
				success:function(jsonData){
					if(jsonData.examtermName){
						$(".list-infos").find(".long").eq(0).show();
						var year=data.examtermName.substring(0,4);
						var month=data.examtermName.substring(5);
						$(".year").find("option[value='"+year+"']").attr("selected","selected");
						$(".month").find("options[value='"+month+"']").attr("selected","selected");
					}else{
						$(".list-infos").find(".long").eq(1).hide();
					}
				}
			})
			$(".schools").on("click.page",".school",function(){
				if($(this).hasClass("btn-success")){
					$(".schools").find(".school").removeClass("btn-success");
					$(this).addClass("btn-default");
					//初始化考期
					$(".term").html("<option value=''>请选择&nbsp;</option>");
					$("#campusId").val("");
				}else{
					$(".schools").find(".school").removeClass("btn-success").addClass("btn-default");
					$(this).removeClass("btn-default").addClass("btn-success");
					var a=$(this).attr("value");
					$("#campusId").val(a);
				}
				var currtCmapus=$(this).attr("value");
				$(".modules").find(".public").find(".block").removeClass("active");
				$(".modules").children(".public").each(function(i){
					var num=0;
					var ele;
					$(this).find(".block").each(function(n){
						if($(this).attr("campusId")==currtCmapus){
							num+=1;
							ele=$(this);
						}
					})
					if(num==1){
						ele.addClass("active");
					}
				})
			})
	
		$.ajax({
			url:rootPath+"/classModuleNo/getByPaySlave",
			type:"post",
			data:{
				"payMasterId":payMasterId,
				"classTypeId":classTypeId
			},
			success:function(jsonData){
				$(".modules").html(jsonData);
			}
		})
		$(".term").change(function(){
			var currtTerm=$(this).val();
			$(".modules").find(".public").find(".block").removeClass("active");
			$(".modules").children(".public").each(function(i){
				var num=0;
				var ele;
				$(this).find(".block").each(function(n){
					if($(this).attr("termId")==currtTerm){
						num+=1;
						ele=$(this);
					}
				})
				if(num==1){
					ele.addClass("active");
				}
			})
		})
		
		
	})
	function toSubmit(){
//	if($(".term").val()==''){
//		$.alert("请选考期!");
//		return;
//	}
	if(!$(".modules").find(".public").find(".block.active").length){
		$.alert("请选择班号",function(){
			return;
		})
	}
	if(!$(".modules").find(".public").length){
		$.alert("请先去安排课程",function(){
			return;
		})
	}
	if(!$(".modules").find(".public").find(".block.active").length){
		$.alert("请选择班号",function(){
			return;
		})
		return;
	}
		var payMasterId = $("#payMasterId").val();
		var moduleNo="";
		$(".main-content").find(".active").each(function(){
			var a = $(this).attr("value");
			if(moduleNo!=""){
				moduleNo=moduleNo+";"+a;
			}else{
				moduleNo=a;
			}
			
		})
		$.ajax({
			url:rootPath+"/classModuleNo/toChange",
			type:"post",
			data:{
				"payMasterId":payMasterId,
				"moduleNo":moduleNo
			},
			success:function(data){
				$.alert("更改成功!",function(){
					history.back();
				});
			}
		})
	}