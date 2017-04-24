$(function(){
  			var student = $("#student").val();
  	  		$selectSubMenu('student_online_order');
  	  		var classTypeId=$("#classType").val();
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
  			$(".term").getTerm($("#itemOne").val(),function(){});
  			$(".schools").on("click",".school",function(){
  				//alert(123);
				if($(this).hasClass("btn-success")){
					$(".schools").find(".school").removeClass("btn-success").addClass("btn-default");
					$(this).addClass("btn-default");
				}else{
					$(".schools").find(".school").removeClass("btn-success").addClass("btn-default");
					$(this).removeClass("btn-default").addClass("btn-success");
				}
				$(".modules").find(".public").find(".block").removeClass("active");
				var currtCmapus=$(this).attr("value");
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
			$(".term").change(function(){
				var currtTerm=$(this).val();
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
						for(var i=0;i<$this.options.slaves.length;i++){
							if($this.options.slaves[i].moduleId==ele.parents(".public").attr("value")){
								$this.options.slaves[i].resourceId=ele.find("b").attr("value");
							}
						}
					}
				})
			})
			search();
  		})
  		function search(){
  			var itemOneId=$("#itemOne").val();
  			var itemSecondId=$("#itemSecond").val();
  			var examTerm=$(".term").val();
  			var campusId=$(".schools").find(".btn-success").attr("value");
  			var classTypeId=$("#classType").val();
  			var data={};
  			if(itemOneId){
  				data.itemOneId=itemOneId;
  			}
  			if(itemSecondId){
  				data.itemSecondId=itemSecondId;
  			}
  			if(examTerm && examTerm!="null"){
  				data.examTerm=examTerm;
  			}
  			if(campusId && campusId!="null"){
  				data.campusId=campusId;
  			}
  			data.classTypeId=classTypeId;
  			$.ajax({
  				url:rootPath+"/studentPayMaster/toSearch",
  				type:"post",
  				data:data,
  				success:function(html){
  					$(".modules").html(html);
  				}
  			})
  			
  		}
  		function save(){
  			var check=true;
  			$(".modules").find(".public").each(function(){
				if($(this).find(".right .c-content").children("em").length>0){
					alert("没有安排课程");
					check=false;
					return;
				} 
				if(($(this).find(".active").length<=0)&&($(this).find(".right .c-content").children("em").length<=0)){
					alert("没有选班号");
					check=false;
					return;
				} 
  			})
  			if(!check){
  				return;
  			}
			var payMasterId=$("#payMasterId").val();
			var stuId=$("#student").val();
			var campusId=$(".schools").find(".btn-success").attr("value");
			var examTerm=$(".term").val();
			var termName=$(".term").find("option:selected").html();
			
			var data={};
			data.payMasterId=payMasterId;
			data.stuId=stuId;
  			if(examTerm){
  				data.examTerm=examTerm;
  			}
  			if(campusId){
  				data.campusId=campusId;
  			}
  			if(termName){
  				data.termName=termName;
  			}
  			
			var slaves=new Array();
			$(".modules").find(".public .active").each(function(i){
				var slave={};
				slave.moduleId=$(this).attr("moduleId");
				slave.resourceType=$(this).attr("teachmethod");
				slave.resourceId=$(this).attr("id");
				slave.campusId=$(this).attr("campus");
				slaves.push(slave);
			})
			
			$(".modules").find(".long").each(function(i){
				var slave={};
				slave.moduleId=$(this).attr("moduleId");
				slave.resourceType=$(this).attr("teachmethod");
				slave.resourceId=$(this).attr("moduleId");
				slave.campusId=$(this).attr("campus");
				slaves.push(slave);
			})
			data.slaves=JSON.stringify(slaves);
			$.ajax({
				url:rootPath+"/studentPayMaster/arrangeCourse",
				type:"post",
				data:data,
				success:function(data){
					alert("安排课程成功!",function(){
						history.go(-1);
					});
				}
			})
			
			
		}