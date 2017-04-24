//获得6个值
var oneItemId ;
var twoItemId ;
var termId ;
var type ;
var campusId ;
var status ;
var selNo;
	$(function(){
		$selectSubMenu('course_module_no');
		$("#two").hide();
		$("#two").find(".s-list").html("");
		$("#term").hide();
		$("#term").find(".s-list").html("");
		$("#campus").hide();
		$("#campus").find(".s-list").html("");
		selDetail(1);
		//点 学科 加载学科小类 考期
		$(".oneItem").each(function(){
			$(this).click(function(){
				$(this).attr("class","btn btn-sm btn-default oneItem active");
				$(this).prevAll().attr("class","btn btn-sm btn-default oneItem");
				$(this).nextAll().attr("class","btn btn-sm btn-default oneItem");
				$("#two").hide();
				$("#two").find(".s-list").html("");
				$("#term").hide();
				$("#term").find(".s-list").html("");
				$("#selNo").val("");
				selDetail(1);
				oneItemId = $.trim($(this).attr("data-id"));
				if(oneItemId != ""){
					$.ajax({
						url:rootPath + "/sysConfigItem/twoProByClass",
						type:"post",
						data:{"oneItemId":oneItemId},
						dataType:"json",
						success:function(data){
							if(data.twoItem != null){
								$("#two").show();
								$("#two").find(".s-list").html("");
								$.each(data.twoItem,function(index,item){
									$("#two").find(".s-list").append("<a class='btn btn-sm btn-default twoItem' href='javascript:;' data-id='" + item.id + "'>" + item.itemName+ "</a>");
								});
								$(".twoItem").each(function(){
									$(this).click(function(){
										$(this).attr("class","btn btn-sm btn-default twoItem active");
										$(this).prevAll().attr("class","btn btn-sm btn-default twoItem");
										$(this).nextAll().attr("class","btn btn-sm btn-default twoItem");
										$(".termByOne").attr("class","btn btn-sm btn-default termByOne");
										$("#selNo").val("");
										selDetail(1);
									});
								});
							}
						}
					});
					//加载考期
					$.ajax({
						url:rootPath + "/sysConfigTerm/selTermByOneItemId",
						type:"post",
						data:{"oneItemId":oneItemId},
						dataType:"json",
						success:function(data){
							if(data.term != null){
								$("#term").show();
								$("#term").find(".s-list").html("");
								$.each(data.term,function(index,item){
									$("#term").find(".s-list").append("<a class='btn btn-sm btn-default termByOne' href='javascript:;' data-id='" + item.id + "'>" + item.termName + "</a>");
								});
								$(".termByOne").each(function(){
									$(this).click(function(){
										$(this).attr("class","btn btn-sm btn-default termByOne active");
										$(this).prevAll().attr("class","btn btn-sm btn-default termByOne");
										$(this).nextAll().attr("class","btn btn-sm btn-default termByOne");
										$(".twoItem").attr("class","btn btn-sm btn-default twoItem");
										$("#selNo").val("");
										selDetail(1);
									});
								});
							}
						}
					});
				}
			});
		});
		
		//点击类型  查询 校区
		$(".classType").each(function(){
			$(this).click(function(){
				$(this).attr("class","btn btn-sm btn-default classType active");
				$(this).prevAll().attr("class","btn btn-sm btn-default classType");
				$(this).nextAll().attr("class","btn btn-sm btn-default classType");
				$("#campus").hide();
				$("#campus").find(".s-list").html("");
				$("#selNo").val("");
				selDetail(1);
				type = $.trim($(this).attr("data-type"));
				if(type == "TEACH_METHOD_FACE"){
					$("#campus").show();
					$("#campus").find(".s-list").html("");
					//加载校区
					$.ajax({
						url:rootPath + "/sysConfigCampus/schoolCampus",
						type:"post",
						dataType:"json",
						success:function(data){
							if(data.campus != null){
								$("#campus").show();
								$("#campus").find(".s-list").html("");
								$.each(data.campus,function(index,item){
									$("#campus").find(".s-list").append("<a class='btn btn-sm btn-default campusByFace' href='javascript:;' data-id='" + item.id + "'>" + item.campusName + "</a>");
								});
								$(".campusByFace").each(function(){
									$(this).click(function(){
										$(this).attr("class","btn btn-sm btn-default campusByFace active");
										$(this).prevAll().attr("class","btn btn-sm btn-default campusByFace");
										$(this).nextAll().attr("class","btn btn-sm btn-default campusByFace");
										$("#selNo").val("");
										selDetail(1);
									});
								});
							}
						}
					});
				}
			});
		});
		
		$(".status").each(function(){
			$(this).click(function(){
				$(this).attr("class","btn btn-mini btn-success status");
				$(this).prevAll().attr("class","btn btn-mini btn-default status");
				$(this).nextAll().attr("class","btn btn-mini btn-default status");
				$("#selNo").val("");
				selDetail(1);
			});
		});
		
		$(".btn-sel-no").click(function(){
			selDetail(1);
		});
		
	});
	
	function addClassModuleNo(){
		var oneItem = $("#one").find("a.active");
		var oneitemHtml = $(oneItem).text();
		if(oneitemHtml == '全部'){
			window.location.href = rootPath+"/classModuleNo/add";
			return false;
		}
		var oneItemId = $(oneItem).attr("data-id");
		
		var twoItem = $("#two").find("a.active");
		var twoItemId = 0;
		
		if(typeof($(twoItem).html()) != "undefined"){
			twoItemId = $(twoItem).attr("data-id");
		}
		
		var termObj = $("#term").find("a.active"); 
		var termId = 0;
		if(typeof($(termObj).html()) != "undefined"){
			termId = $(termObj).attr("data-id");
		}
		
		window.location.href = rootPath+"/classModuleNo/addByCondition/"+oneItemId+"/"+twoItemId+"/"+termId;
		
	}
	//加载详细信息
	function selDetail(pageNo){
		oneItemId = $.trim($(".oneItem.active").attr("data-id"));
		twoItemId = $.trim($(".twoItem.active").attr("data-id"));
		termId = 	$.trim($(".termByOne.active").attr("data-id"));
		type = 		$.trim($(".classType.active").attr("data-type"));
		campusId = 	$.trim($(".campusByFace.active").attr("data-id"));
		status = 	$.trim($(".btn-success.status").attr("data-type"));
		selNo =		$.trim($("#selNo").val());
		pageSize = 	$.trim($("#pageSize").val());
		
		$.ajax({
			url : rootPath + "/classModuleLesson/detail",
			type:"post",
			data:{"page":pageNo,"oneItemId":oneItemId,"twoItemId":twoItemId,"termId":termId,"type":type,"campusId":campusId,"status":status,"selNo":selNo,"pageSize":pageSize},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				$(".op-list").html("");
				$(".op-list").html(data);
			},
			complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}