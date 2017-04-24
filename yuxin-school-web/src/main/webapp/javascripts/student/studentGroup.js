;(function ($) {
	
	Page = {
		init: function(){
			$selectSubMenu('org_service');
			$selectSubMenus('student_group');
			Page.btnInit();
		},
		btnInit: function(){
			var status=$("#groupStatus").val();
			if(status==1){
				$(".code-cont").show();
			}
			//添加 学员
	        $(".screen-right").on("click", ".add-first-btn", function () {
	        	$(".add-yes").removeClass("clicked");
	        	$("#group_type_name").html("添加学员一级分组");
	        	$("#new_group_name").val("");
	            $(".add-grade-pop-box").fadeIn(300);
	        });
	        $(".screen-right").on("click", ".add-second-btn", function () {
	        	$(".add-yes").removeClass("clicked");
	        	$("#group_type_name").html("添加学员二级分组");
	        	$("#new_group_name").val("");
	        	$("#group_pid").val($(this).attr("ids"));
	        	$(".add-grade-pop-box").fadeIn(300);
	        });
	        $(".add-cancel").on("click", function () {
	            $(".add-grade-pop-box").fadeOut(300);
	        });
	        $(".add-yes").on("click",function(){
	        	if(!$(this).hasClass("clicked")){
	        		$(this).addClass("clicked");
		        	var newGroupName=$("#new_group_name").val();
		        	if($("#group_type_name").html()=="添加学员一级分组"){
		        		Page.addStudentGroup(newGroupName);
		        	}else if($("#group_type_name").html()=="添加学员二级分组"){
		        		Page.addStudentGroup(newGroupName,$("#group_pid").val());
		        	}else if($("#group_type_name").html()=="修改名称"){
		        		Page.editStudentGroup(newGroupName,$("#group_xg").val());
		        	}
	        	}
	        })
	        $("#new_group_name").on('keydown',function(e){
	        	 if(e.keyCode == 13){
	        		 if(!$(".add-yes").hasClass("clicked")){
	 	        		$(".add-yes").addClass("clicked");
	 		        	var newGroupName=$("#new_group_name").val();
	 		        	if($("#group_type_name").html()=="添加学员一级分组"){
	 		        		Page.addStudentGroup(newGroupName);
	 		        	}else if($("#group_type_name").html()=="添加学员二级分组"){
	 		        		Page.addStudentGroup(newGroupName,$("#group_pid").val());
	 		        	}else if($("#group_type_name").html()=="修改名称"){
	 		        		Page.editStudentGroup(newGroupName,$("#group_xg").val());
	 		        	}
	 	        	}
	        	 }
	        })
	        $(".screen-right").on("click",".edit-classify",function(){
	        	$(".add-yes").removeClass("clicked");
	        	$("#group_type_name").html("修改名称");
	        	$("#group_xg").val($(this).attr("ids"));
	        	$("#new_group_name").val($(this).attr("groupName"));
	        	$("#new_group_name").attr("oldName",$(this).attr("groupName"));
	        	if($(this).attr("pId")){
	        		$("#group_xgpid").val($(this).attr("pId"));
	        	}else{
	        		$("#group_xgpid").val("");
	        	}
	        	$(".add-grade-pop-box").fadeIn(300);
	        })
	       $(".screen-right").on("click",".deleteCategory",function(){
	    	   $this=$(this);
	        	$.confirm("确定要删除吗？",function(b){
					if(b){
						$.ajax({
							url: rootPath+"/studentGroup/delStudentGroup/"+$this.attr("ids"),
							type:"post",
							success:function(jsonData){
								jsonData=eval('('+jsonData+')');
								if(jsonData.msg=="success"){
									$.msg("操作成功！");
								}
								Page.selectStudentGroup1();
							},
							error:function(){
								$.msg("操作失败！");
							}
						})
					}
	        	})
	        })
	        //列表展开隐藏
	        $(document).on('click.grade-pop-btn',".fir-grade .grade-pop-btn",function(){
	    	if($(this).hasClass("hide")){
				 $(this).html("&#xe6a2;");
				 $(this).parents(".grade-box").find(".open-box").slideDown(200);
				 $(this).removeClass("hide");
				 $("#showLable").val("show");
	    	}else{
	    		$(this).html("&#xe6a1;");
	   		    $(this).parents(".grade-box").find(".open-box").slideUp(200);
	   		    $(this).addClass("hide");
	   		    $("#showLable").val("hide");
	    	}
	        });
	        //已开启,隐藏
	        $(".qnormal").click(function () {
	            $(this).toggleClass("open").toggleClass("close");
	            $(this).siblings(".qi").toggleClass("open").toggleClass("close");
	            if ($(this).siblings(".qi").hasClass("open")) {
	                $(this).siblings(".qi").html("已启用");
	                $(".toggle").show();
	            } else {
	                $(this).siblings(".qi").html("已禁用");
	                $(".toggle").hide();
	            }
	        });
	        $(".q-open").click(function () {
	            $("#q-cont").toggle();
	        });
	        $(".q-open2").click(function () {
	            $(".q-hide").toggle();
	        });
	        $(".use-time em").click(function () {
	            $(this).addClass("all").siblings("em").removeClass("all");
	        });
	        $(".screen-right-cont em.normal").off().on("click", function () {
	            if ($(this).hasClass("open")) {
	                $(this).removeClass("open").addClass("close").html("&#xe604;");
	                $(this).parents(".screen-right-title").find(".i").removeClass("open").addClass("close").text("已禁用");
	                $("#groupStatus").val(0);
	                Page.setStatus();
	            } else if ($(this).hasClass("close")) {
	                $(this).removeClass("close").addClass("open").html("&#xe603;");
	                $(this).parents(".screen-right-title").find(".i").removeClass("close").addClass("open").text("已启用");
	                $("#groupStatus").val(1);
	                Page.setStatus();
	            };
	        });
	        Page.selectStudentGroup1();
		},
		setStatus: function(){
			$.ajax({
				url: rootPath+"/studentGroup/setGroupStatus",
				data:{"status":$("#groupStatus").val()},
				type:"post",
				success:function(jsonData){
					jsonData=eval('('+jsonData+')');
					if(jsonData.msg=="success"){
						$.msg("操作成功！");
					}
				},
				error:function(){
					$.msg("操作失败！");
					window.location.href=rootPath+"/studentGroup/setStudentGroup";
				}

			})
		},
		selectStudentGroup1:function(){
			$("#studentGroupLists").html('');
			$.ajax({
            	url: rootPath+"/studentGroup/selectStudentGroup1",
            	type: "post",
            	dataType: "json",
            	success: function(jsonData){
            		$.each(jsonData,function(i, g){
            			$("#studentGroupLists").append('<div class="grade-box" id="group_'+g.id+'" ids="'+g.id+'" > '+
						'	<div class="fir-grade"> '+
						'	<span class="grade-pop-btn iconfont">&#xe6a2;</span> '+
						'	<span class="grade-name">'+g.groupName+'</span> '+
						'	<em class="grade-right-btn iconfont add-second-btn" ids="'+g.id+'"  ></em> '+
						'	<em class="grade-right-btn iconfont deleteCategory" ids="'+g.id+'"  ></em> '+
						'	<em class="grade-right-btn iconfont edit-classify" ids="'+g.id+'" groupName="'+g.groupName+'"></em> '+
						'	</div> <div class="open-box" id="two_'+g.id+'"> </div> '+
						'	</div> '+
						'	');
						
            			
            			Page.selectStudentGroup2(g.id);
            		})
            	}
             })
		},
		selectStudentGroup2:function(pId){
			$.ajax({
            	url: rootPath+"/studentGroup/selectStudentGroup2/"+pId,
            	type: "post",
            	dataType: "json",
            	success: function(jsonData){
            		$.each(jsonData,function(i, g){
            			$("#two_"+pId).append('	<div class="sec-grade q-grade" id="group_'+g.id+'"> '+
							'	<span class="lower-btn"></span><span class="grade-name">'+g.groupName+'</span> '+
							'	<em class="grade-right-btn iconfont deleteCategory" ids="'+g.id+'" ></em> '+
							'	<em class="grade-right-btn iconfont edit-classify" ids="'+g.id+'" groupName="'+g.groupName+'" pId="'+pId+'"></em> '+
							'	</div>');
            		})
            	}
             })
		},
		addStudentGroup: function(name,pId){
			var data={};
			if(name){
				data.groupName=name;
			}else{
				$(".add-yes").removeClass("clicked");
				$.msg("分组名不能为空！");
				return false;
			}
			if(pId){
				data.parentId=pId;
			}
			Page.checkStudentGroup(pId);
			if($("#isok").val()!="true"){
				$(".add-yes").removeClass("clicked");
				$.msg("分组名已存在！");
				return false;
			}
			$.ajax({
            	url: rootPath+"/studentGroup/addStudentGroup",
            	data: data,
            	type: "post",
            	dataType: "json",
            	success: function(jsonData){
            		jsonData=eval('('+jsonData+')');
            		if(jsonData.msg=="success"){
						$.msg("添加成功！");
					}else{
						$.msg("修改失败！");
						return false;
					}
            		$(".add-cancel").click();
            		Page.selectStudentGroup1();
            	},
            	error:function(){
            		$.msg("添加失败！");
            	}
             })
		},
		editStudentGroup: function(name,id){
			var data={};
			if(name){
				data.groupName=name;
			}else{
				$(".add-yes").removeClass("clicked");
				$.msg("分组名不能为空！");
				return false;
			}
			data.id=id;
			Page.checkStudentGroup($("#group_xgpid").val());
			if($("#isok").val()!="true"){
				$(".add-yes").removeClass("clicked");
				$.msg("分组名已存在！");
				return false;
			}
			$.ajax({
            	url: rootPath+"/studentGroup/editStudentGroup",
            	data: data,
            	type: "post",
            	dataType: "json",
            	success: function(jsonData){
            		jsonData=eval('('+jsonData+')');
            		if(jsonData.msg=="success"){
						$.msg("修改成功！");
					}else{
						$.msg("修改失败！");
						return false;
					}
            		$(".add-cancel").click();
            		Page.selectStudentGroup1();
            	},
            	error:function(){
            		$.msg("修改失败！");
            	}
             })
             $("#new_group_name").removeAttr("oldName");
		},
		checkStudentGroup:function(pId){
			var data={};
			data.groupName=$("#new_group_name").val();
			if(pId){
				data.parentId=pId;
			}
			if(data.groupName==$("#new_group_name").attr("oldName")){
				$("#isok").val("true");
				return false;
			}
			$.ajax({
            	url: rootPath+"/studentGroup/checkStudentGroup",
            	data: data,
            	type: "post",
            	dataType: "text",
            	async: false,
            	success: function(jsonData){
            		if(jsonData=="false"){
            			$("#isok").val("false");
            		}else if(jsonData=="true"){
            			$("#isok").val("true");
            		}
            	},
            	error:function(){
            		$("#isok").val("false");
            	}
             })
		}
	}
	
	
    $(function () {
    	Page.init();
    })
})(jQuery);
