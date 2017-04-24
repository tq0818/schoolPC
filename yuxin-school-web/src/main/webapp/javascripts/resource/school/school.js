$(function(){
	$selectSubMenu('resource_school');

		$(".clear li").each(function(){
			var senate = $(this).find(".h:eq(1)").find("span:eq(1)");
			var project = $(this).find(".h:eq(2)").find("span:eq(1)");
			var campus = $(this).find(".h:eq(3)").find("span:eq(1)");
			var teacher = $(this).find(".h:eq(4)").find("span:eq(1)");
			var classroom = $(this).find(".h:eq(5)").find("span:eq(1)");
			$.ajax({
				url:rootPath + "/sysConfigSchool/ajaxSel",
				type:"post",
				data:{"schoolId":$(this).find("input").val()},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					senate.text(data.senate);
					project.text(data.project);
					campus.text(data.campus);
					teacher.text(data.teacher);
					classroom.text(data.classroom);
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		});
		
		$("#openFace").click(function(){
			location.href = rootPath + "/companyMemberService/showFace";
		});
	});