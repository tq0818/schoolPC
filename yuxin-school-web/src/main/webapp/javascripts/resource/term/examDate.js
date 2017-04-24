$(function(){
	$(".edit").hide();
	var $thisEdit;
	$(".btn-add-subs").click(function(){
		var $this = $(this);
		$thisEdit = $this.parent(".new-btn").parent(".r-title").next(".itemOneId").next(".r-subs").children(".edit");
		$thisEdit.show();
		addTime();
	});
	//点击取消隐藏
	$(".btnCancle").click(function(){
		$(this).parent(".edit").hide();
	});
	
	//点击删除考期
	$(".btnDel").click(function(){
		var termId = $(this).attr("termId");
		$.ajax({
			url : rootPath+"/sysConfigTerm/editTerm",
			type : "post",
			data : {"id":termId,"delFlag":1},
			success : function(data){
				if(data == "success"){
					$('<div class="c-fa">'+ "删除成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					location.href=rootPath+"/sysConfigTerm/toExamDate";
				}
			}
		});
	});
	
	$(".saveTerm").click(function(){
		var $this = $(this);
		var schoolName = $("#schoolName").val();
		var itemOneId = $this.parent(".edit").parent(".r-subs").prev(".itemOneId").val();
		var schoolId = $("#schoolId").val();
		var termName = $this.prev(".termMonth").prev(".termYear").val()+"-"+$this.prev(".termMonth").val();
		$.ajax({
			url : rootPath+"/sysConfigTerm/checkUnique",
			type : "post",
			data : {"itemOneId":itemOneId,"termName":termName},
			beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	        },
			success : function(data){
				if(data == "success"){
					$('<div class="c-fa">'+ "已存在相同考期" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return;
				}
				if(data == "fail"){
					$.ajax({
						url : rootPath+"/sysConfigTerm/addTerm",
						type : "post",
						data : {"itemOneId":itemOneId,"termName":termName,"schoolId":schoolId,"schoolName":schoolName},
						success : function(data){
							if(data == "success"){
								$('<div class="c-fa">'+ "保存成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
									location.href = rootPath+"/sysConfigTerm/toExamDate";
								});
								
							}
						}
					});
				}
			},
	        complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
		
	});
});

function addTime(){
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
	 $(".termYear").html("");
	 $(".termMonth").html("");
	 for(var i=0;i <=10;i++){ 
		 var newYear = year+i;
		 if(i==0){
			 $(".termYear").append("<option value=\""+newYear+"\"  selected=\"selected\">"+newYear+"</option>");
		 }else{
			 $(".termYear").append("<option value=\""+newYear+"\">"+newYear+"</option>");
		 }
		 
	 }
	 for(var i=1;i <=12;i++){ 
		 var newMonth = month+1;
		 var mon;
		 if(i<10){
			 mon = "0"+i;
		 }else{
			 mon = i;
		 }
		 if(i==newMonth){
			 $(".termMonth").append("<option value=\""+mon+"\" selected=\"selected\">"+mon+"</option>");
		 }else{
			 $(".termMonth").append("<option value=\""+mon+"\">"+mon+"</option>");
		 }
	 }
}

