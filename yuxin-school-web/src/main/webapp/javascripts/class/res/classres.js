var fileId;

$(function(){
	$selectMenu("course_class_type");
	$chooseMenu("classResource");
	
	$(".online-content").hide();
	$(".online-btn").hide();
	var oneId=$("#oneId").val();
	if(oneId!=""){
		$("#one").find("a.btn").each(function(){
			console.log($(this).attr("ids"));
			if($(this).attr("ids")==oneId){
				$(this).removeClass("btn-default").addClass("btn-success");
			}
		});
	}else{
		$(".oneItem:first").removeClass("btn-default").addClass("btn-success");
	}
	selTwoItem();
	$(".class-resource").attr("style","z-index:99");
	$(".oneItem").click(function(){
		$(".oneItem").removeClass("btn-success").addClass("btn-default");
		$(this).removeClass("btn-default").addClass("btn-success");
		selTwoItem();
	});

	$(".resource").click(function(){
		$(".resource").removeClass("btn-success").addClass("btn-default");
		$(this).removeClass("btn-default").addClass("btn-success");
		selClassResource(1);
	});
	
	$("#class").change(function(){
		selClassResource(1);
	});

	$("#docres").click(function(event){
		event.preventDefault();
        $('#doctype').trigger('click');
	});
	
	$(".btn-res-table").click(function(){
		$(".btn-res-table").removeClass("btn-success").addClass("btn-default");
		$(this).remove("btn-default").addClass("btn-success");
		
		docurl = "";
		
		huan();
	});
	
	$(".btn-upload").click(function(){
		var classid = $("#class").val();
		if(typeof(classid) == "undefined" || classid == null || classid == ""){
			$.msg("请先选择课程",1000);
			return false;
		}
		$(".class-resource").show();
		$(".loading-bg").show();
	});
	
	$(".btn-cancel").click(function(){
		$(".class-resource").hide();
		$(".loading-bg").hide();
		$("#doctype").val("");
		$("#dochint").html("");
	});
	
	
	$(".close").click(function(){
		$(".class-resource").hide();
		$(".loading-bg").hide();
		$("#doctype").val("");
		$("#dochint").html("");
	});
	
	$(".btn-ok").click(function(){
		if(fileId == null || fileId == ""){
			$.msg("请先上传文档",1000);
			return false;
		}
		/*var oneItem = $(".btn-success.oneItem").attr("data-id");
		var twoItem = $(".btn-success.twoItem").attr("data-id");*/
		var classid = $("#cId").val();
		if(typeof(classid) == "undefined" || classid == null || classid == ""){
			$.msg("请先选择课程",1000);
			return false;
		}
		var resource = $("#classresource").val();
		var resName = $.trim($("#classresource").find("option:selected").text());
		var classTypeName = $.trim($("#classname").val());
		if(classTypeName== null || classTypeName == ''){
			classTypeName = $('#classtypeName').val();
		}
		$.ajax({
			url : rootPath + "/classTypeResource/save",
			type:"post",
			data:{
				/*"itemOneId":oneItem,
				"itemSecondId":twoItem,*/
				"classTypeId":classid,
				"resourceType":resource,
				"classTypeName":classTypeName,
				"fileId":fileId,
				"resName":resName},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	         },
			success:function(data){
				if(data.msg == "success"){
					$(".class-resource").hide();
					$(".loading-bg").hide();
		            $(".loading").hide();
		    		$("#doctype").val("");
		    		$("#dochint").html("");
		    		fileId = "";
		            selClassResource(1);
				}else{
		            $(".loading").hide();
		            $.msg(data.msg,1000);
				}
			}
		});
	});/*
	$(".btn-ok-online").click(function(){
		if(fileId == null || docurl == ""){
			$.msg("请先上传文档",1000);
			return false;
		}
		var oneItem = $(".btn-success.oneItem").attr("data-id");
		var twoItem = $(".btn-success.twoItem").attr("data-id");
		var classid = $("#classnames").val();
		if(typeof(classid) == "undefined" || classid == null || classid == ""){
			$.msg("请先选择课程",1000);
			return false;
		}
		var resource = $("#classresources").val();
		var classTypeName = $.trim($("#classnames").find("option:selected").text());
		var resName = $.trim($("#classresources").find("option:selected").text());
		$.ajax({
			url : rootPath + "/classTypeResource/save",
			type:"post",
			data:{"itemOneId":oneItem,"itemSecondId":twoItem,"classTypeId":classid,"resourceType":resource,"classTypeName":classTypeName,"name":docname,"url":docurl,"size":docsize,"resName":resName},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	         },
			success:function(data){
				if(data.msg == "success"){
					$(".class-resource").hide();
					$(".loading-bg").hide();
		            $(".loading").hide();
		    		$("#pdf").val("");
		    		$("#pdfhint").html("");
		    		docurl = "";
		            selClassResource(1);
				}else{
		            $(".loading").hide();
		            $.msg(data.msg,1000);
				}
			}
		});
	});*/
});
function selTwoItem(){
	 var oneItem = $(".btn-success.oneItem").attr("data-id");
	 $.ajax({
		 url:rootPath + "/classModule/selTwoItem",
		 type:"post",
		 data:{"oneItem":oneItem},
		 dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
		 success:function(data){
			 $("#two").find(".s-list").html("");
			 $.each(data.two,function(index,item){
				 if($("#twoId").val()!=""){
					 if($("#twoId").val()==item.id){
						 $("#two").find(".s-list").append("<a class='btn btn-sm btn-success twoItem' href='javascript:;' data-id='" + item.id +"'>" + item.itemName +"</a>");
					 }else{
						 $("#two").find(".s-list").append("<a class='btn btn-sm btn-default twoItem' href='javascript:;' data-id='" + item.id +"'>" + item.itemName +"</a>");
					 }
				 }else{
					 if(index == 0){
						 $("#two").find(".s-list").append("<a class='btn btn-sm btn-success twoItem' href='javascript:;' data-id='" + item.id +"'>" + item.itemName +"</a>");
					 }else{
						 $("#two").find(".s-list").append("<a class='btn btn-sm btn-default twoItem' href='javascript:;' data-id='" + item.id +"'>" + item.itemName +"</a>");
					 }
				 }
				$(".twoItem").click(function(){
					$(this).attr("class","btn btn-sm btn-success twoItem");
					$(this).prevAll().attr("class","btn btn-sm btn-default twoItem");
					$(this).nextAll().attr("class","btn btn-sm btn-default twoItem");
					selClass();
				});
			 });
			 selClass();
		 }
	 });
}

function selClass(){
	var oneItem = $(".btn-success.oneItem").attr("data-id");
	var twoItem = $(".btn-success.twoItem").attr("data-id");
	 $.ajax({
		 url:rootPath + "/classModule/selClassType",
		 type:"post",
		 data:{"itemOneId":oneItem,"itemSecondId":twoItem},
		 dataType:"json",
  			beforeSend:function(XMLHttpRequest){
  	              $(".loading").show();
  	              $(".loading-bg").show();
  	         },
		 success:function(data){
			 $("#class").empty();
			 $("#classname").empty();
			 $("#classnames").empty();
			 $.each(data.types,function(index,item){
				 if($("#cId").val()==item.id){
					 $("#class").append("<option selected='selected' value='" + item.id + "'>" + item.name + "</option>");
					 $("#classname").append("<option selected='selected' value='" + item.id + "'>" + item.name + "</option>");
					 $("#classnames").append("<option selected='selected' value='" + item.id + "'>" + item.name + "</option>");
				 }else{
					 $("#class").append("<option value='" + item.id + "'>" + item.name + "</option>");
					 $("#classname").append("<option value='" + item.id + "'>" + item.name + "</option>");
					 $("#classnames").append("<option value='" + item.id + "'>" + item.name + "</option>");
				 }
			 });
			 $(".js-example-basic-single").select2();
 	         $(".loading").hide();
 	         $(".loading-bg").hide();
			 selClassResource(1);
		 }
	 });
}

function selClassResource(page){
	 var oneItem = $(".btn-success.oneItem").attr("data-id");
	 var twoItem = $(".btn-success.twoItem").attr("data-id");
	 var classid = $("#cId").val();
	 var resource = $(".btn-success.resource").attr("data-type");
	 $.ajax({
		 url : rootPath + "/classTypeResource/selResource",
		 type: "post",
		 data:{"itemOneId":oneItem,"itemSecondId":twoItem,"classTypeId":classid,"resourceType":resource,"page":page,"pageSize":10},
		 dataType:"html",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		 success:function(data){
			 $(".op-list").html(data);
		 },
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	 });
}
function docChange(){
	//改变 上传
	$("#dochint").html("<span style='color:red;' >正在上传 <img src='../../../images/jia.jpg' width='16px' heigth='16px'/></span>");
	$.ajaxFileUpload({
		url:rootPath + "/classTypeResource/docupload;"+ window["sessionName"] + "=" + window["sessionId"],
		type:"post",
		secureuri:false,
		fileElementId:"doctype",
		dataType: "json",
		success:function(data){
			if(data.msg == "formatNotRight"){
				$("#dochint").html("<span style='color:red;'>上传文件格式不正确</span>");
			}else if(data.msg == "sizeOutOf"){
				$("#dochint").html("<span style='color:red;'>文件不能大于20MB</span>");
			}else if(data.msg == "success"){
				fileId = data.fileId;
				$("#dochint").html("<span style='color:green;'>上传成功</span>");
			}else if(data.msg == "storage"){
				$("#dochint").html("<span style='color:red;'>资源存储空间不足</span>");
			}else if(data.msg == "nameTooLang"){
				$("#dochint").html("<span style='color:red;'>文件名太长</span>");
			}else{
				$("#dochint").html("<span style='color:red;'>" + data.msg + "</span>");
			}
		}
	});
}

/*function pdfChange(){
	//改变 上传
	$("#pdfhint").html("<span style='color:red;' >正在上传 <img src='../../../images/jia.jpg' width='16px' heigth='16px'/></span>");
    $.ajaxFileUpload({
    	url:rootPath + "/classTypeResource/uploadToSwf;"+ window["sessionName"] + "=" + window["sessionId"],
    	type:"post",
    	secureuri:false,
    	fileElementId:"pdf",
    	dataType: "json",
    	success:function(data){
    		if(data.msg == "success"){
    			docurl = data.url;
    			docname = data.name;
    			docsize = data.size;
    			$("#pdfhint").html("<span style='color:green;'>上传成功</span>");
    		}else{
    			$("#pdfhint").html("<span style='color:red;'>" + data.msg + "</span>");
    		}
    	}
    });
}*/

function huan(){
	if($(".btn-success.btn-res-table").hasClass("btn-putong")){
		$(".putong-content").show();
		$(".putong-btn").show();
		$(".online-content").hide();
		$(".online-btn").hide();
	}else{
		$(".putong-content").hide();
		$(".putong-btn").hide();
		$(".online-content").show();
		$(".online-btn").show();
	}
}

function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}