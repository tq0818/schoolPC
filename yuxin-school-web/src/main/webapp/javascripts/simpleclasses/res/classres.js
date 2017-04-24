
var docurl;
var docname;
var docsize;
$(function(){
	docChange();
	$(".oneItem:first").attr("class","btn btn-sm btn-success oneItem");
	selTwoItem();
	$(".class-resource").attr("style","z-index:99");
	$(".oneItem").click(function(){
		$(this).attr("class","btn btn-sm btn-success oneItem");
		$(this).prevAll().attr("class","btn btn-sm btn-default oneItem");
		$(this).nextAll().attr("class","btn btn-sm btn-default oneItem");
		selTwoItem();
	});

	$(".resource").click(function(){
		$(this).attr("class","btn btn-sm btn-success resource");
		$(this).prevAll().attr("class","btn btn-sm btn-default resource");
		$(this).nextAll().attr("class","btn btn-sm btn-default resource");
		selClassResource(1);
	});
	
	$("#class").change(function(){
		selClassResource(1);
	});

	$("#docres").click(function(event){
		event.preventDefault();
        $('#doctype').trigger('click');
	});
	
	$(".btn-upload").click(function(){
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
		if(docurl == null || docurl == ""){
			$('<div class="c-fa">请先上传文档</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
			return false;
		}
		var oneItem = $(".btn-success.oneItem").attr("data-id");
		var twoItem = $(".btn-success.oneItem").attr("data-id");
		var classid = $("#class").val();
		var resource = $("#classresource").val();
		var classTypeName = $.trim($("#classname").find("option:selected").text());
		if(classTypeName == null || classTypeName== ''){
			classTypeName = $('#classtypeName').val();
		}
		var resName = $.trim($("#classresource").find("option:selected").text());
		$.ajax({
			url : rootPath + "/classTypeResource/save",
			type:"post",
			data:{"itemOneId":oneItem,
				"itemSecondId":twoItem,
				"classTypeId":classid,
				"resourceType":resource,
				"classTypeName":classTypeName,
				"fileId":docurl},
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
		            selClassResource(1);
				}else{
		            $(".loading").hide();
					$('<div class="c-fa">' + data.msg + '</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
				}
			}
		});
	});
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
				 if(index == 0){
					 $("#two").find(".s-list").append("<a class='btn btn-sm btn-success twoItem' href='javascript:;' data-id='" + item.id +"'>" + item.itemName +"</a>");
				 }else{
					 $("#two").find(".s-list").append("<a class='btn btn-sm btn-default twoItem' href='javascript:;' data-id='" + item.id +"'>" + item.itemName +"</a>");
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
			 $.each(data.types,function(index,item){
				 $("#class").append("<option value='" + item.id + "'>" + item.name + "</option>");
				 $("#classname").append("<option value='" + item.id + "'>" + item.name + "</option>");
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
	 var twoItem = $(".btn-success.oneItem").attr("data-id");
	 var classid = $("#class").val();
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
	Qiniu.uploader({
	    runtimes: 'html5,flash,html4',
	    browse_button: 'doctype',
	    uptoken_url: '/classTypeResource/getuptoken',
	    get_new_uptoken: true, 
	    unique_names: false,
	    save_key: false,
	    domain: 'http://resource.yunduoketang.com',
	    container: 'class-resource',
	    max_file_size: '20mb',
	    multi_selection:false,
	    filters:{
			mime_types:[
			            {title:"docs files",extensions:"doc,pdf,xls,ppt,docx,pptx,xlsx,zip,rar"}]},
	    flash_swf_url: 'path/of/plupload/Moxie.swf',
	    max_retries: 3,
	    chunk_size: '4mb', 
	    auto_start: true, 
	    init: {
	        'FilesAdded': function(up, files) {
	            plupload.each(files, function(file) {
	                // 文件添加进队列后，处理相关的事情
	            	$("#dochint").html("<span style='color:red;' >正在上传</span>");
		        	if(file.name.length > 120){
		        		$("#dochint").html("<span style='color:red;'>文件名太长,建议三十个字以内</span>");
		        		up.removeFile(file);
		        		return false;
		        	}
		        	$.ajax({
		        		url:rootPath + "/classTypeResource/selSumNum",
		        		type:"post",
		        		dataType:"json",
		        		async:false,
		        		success:function(data){
		        			if(data.msg != "success"){
		        				$("#dochint").html("<span style='color:red;'>"+data.msg+"</span>");
		        				$.msg();
		        				up.removeFile(file);
		        				return false;
		        			}
		        		}
		        	});
	            });
	        },
	        'BeforeUpload': function(up, file) {
	               // 每个文件上传前，处理相关的事情
	        },
	        'UploadProgress': function(up, file) {
	               // 每个文件上传时，处理相关的事情
	        	$("#dochint").html("<span style='color:red;' >正在上传 " + file.percent + "%</span>");
	        },
	        'FileUploaded': function(up, file, info) {
	               var res = $.parseJSON(info);
	               var sourceLink = res.key;
	               var size = file.size;
	               var name = file.name;
	               var suffix = name.substring(name.lastIndexOf(".") + 1);
	               console.log(sourceLink+","+size+","+name);
	               if(name.length > 120){
	            	   $("#dochint").html("<span style='color:red;'>文件名太长</span>");
	            	   return false;
	               }
	               $.ajax({
	            	   url : "/classTypeResource/docupload",
	            	   type:"post",
	            	   data:{
	            		   'delFlag': 0,
	                       'fileName': name,
	                       'fileName':name,
	                       'fileType':suffix,
	                       'sourcePath':sourceLink,
	                       'sourceSize':size,
	                       'fileCategory':"docs",
	                       'uploadType':0},
	                   dataType:"json",
	                   success:function(data){
	                       if (data.msg == 'success') {
	                    	   $("#dochint").html("<span >上传完成</span>");
	                    		docurl = data.fileId;
	                       } else {
	                    	   $("#dochint").html("<span style='color:red;'>" + data.msg + "</span>");
	                       }
	                   }
	               });
	        },
	        'Error': function(up, err, errTip) {
	               //上传出错时，处理相关的事情
	        	/*var $this = $("#"+file.id);
	        	$this.find("td:eq(2)").html("<span style='color:red;'>" + errTip + "</span>");*/
	        	/*$this.removeAttr("id");*/
	        	 $("#dochint").html("<span style='color:red;'>" + errTip + "</span>");
	        	console.log(errTip);
	        },
	        'UploadComplete': function() {
	               //队列文件处理完毕后，处理相关的事情
	        },
	        'Key': function(up, file) {
	        	var name = file.name;
	        	var suffix = name.substring(parseInt(name.lastIndexOf(".") + 1));
	            var key = "";
	            key = $("#companyId").val() + "/docs" +
	            	"/" + guid() + "." + suffix;
	            return key;
	        }
	    }
	});
}

function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}