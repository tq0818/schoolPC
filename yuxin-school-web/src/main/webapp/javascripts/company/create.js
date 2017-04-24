$(function(){
	var res = null;
	var id = $("#schoolId").val();
	setSchoolAddress(id);
	
	$(".manage-btn-cancel.manage-button").click(function(){
        $(".loading").show();
        $(".loading-bg").show();
		window.location.reload();
	});
	
	$(".manage-btn-success.manage-button").click(function(){
		var id = $("#schoolId").val();
		var schoolName = $("#schoolname").val();
		var county = $("#dist").val();
		var porv = $("#prov").val();
		var city = $("#city").val();
		/*var category = $("#category").val();*/
		var schoolDesc = $("#schoolDesc").val();
		var picurl = $("#commdotityPic").attr("src");
		var indexDomain = $("#domain").val();
		var overview = "";
		var suffix = $("#schoolsuffix").val();
		if(typeof(schoolName) == "undefined" || schoolName == null
				|| schoolName == ""){
			$.msg("请填写分校名称",1000);
			return false;
		}
		
		if(typeof(county) == "undefined"){
			county = "";
			porv = "";
			city = "";
		}
		
		if(typeof(suffix) == "undefined"){
			suffix = "";
		}
		
		if(typeof(indexDomain) == "undefined"){
			indexDomain = "";
		}
		
		if(typeof(picurl) != "undefined" && picurl != null && 
				picurl != "" && picurl.indexOf("course/") >= 0){
			overview = picurl.substring(picurl.indexOf("course/"));
		}
		
		if(typeof(schoolDesc) == "undefined" || schoolDesc == null
				|| schoolDesc == ""){
			$.msg("请填写分校简介",1000);
			return false;
		}
		var url = "";
		
		if(typeof(id) == "undefined" || id == null || id == ""){
			id = null;
			url = rootPath + "/sysConfigSchool/add";
		}else{
			url = rootPath + "/sysConfigSchool/update";
		}
		if(res != null){
			res.abort();
		}
		res = $.ajax({
			url : url,
			type:"post",
			data:{"id":id,
				"schoolName":schoolName,
				"county":county,
				/*"category":category,*/
				"schoolDesc":schoolDesc,
				"overview":overview,
				"indexDomain":indexDomain,
				"city":city,
				"porv":porv,
				"suffix":suffix},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
				$('.loading,.layer-bg').fadeIn(200);
	         },
			success:function(data){
				if(data.msg == "success"){
					$.msg("分校保存完成",1000,function(){
				        $(".loading").show();
				        $(".loading-bg").show();
						window.location.reload();
					});
				}else{
					$.msg(data.msg);
				}
			}
		});
		
	});

    $(".change-img").click(function(){
		$(".upload-layer").fadeIn(200,function(){
			$(".add-layer-bg").fadeIn(200);
		});
	});
	
	$(".close").click(function(){
		$(".upload-layer").fadeOut(200,function(){
			$(".add-layer-bg").fadeOut(200);
		});
		$(".pic-upload").hide();
	});
	
	$(".pic").on("change","#target", function() {
		var theImage = new Image();
		theImage.src = $(this).attr("src");
		 if (theImage.complete) {
		 	sourceHeight = theImage.height;
			sourceWidth = theImage.width;
			$.init(sourceWidth, sourceHeight);
	    } else {
	    	theImage.onload = function () {
	        	sourceHeight = theImage.height;
			sourceWidth = theImage.width;
			$.init(sourceWidth, sourceHeight);
	        };
	    }
	}); 
});

function setSchoolAddress(id){

	var prov = $("#province").val();
	var city = $("#citys").val();
	var dist = $("#county").val();
	var cate = $("#cate").val();
	if(prov == null || prov == ""){
		//设置下拉
		$("#pcdAddress").cityselect({
			url : rootPath + "/sysConfigDivision/getCitys",
		    prov:"北京", //省份 
		    city:"北京市",     //市
		    dist:"东城区",
		    nodata:"none" //当子集无数据时，隐藏select
		});
	}else{
		//设置下拉
		$("#pcdAddress").cityselect({
			url : rootPath + "/sysConfigDivision/getCitys",
		    prov:prov, //省份 
		    city:city,     //市
		    dist:dist, //街道
		    nodata:"none" //当子集无数据时，隐藏select
		});
	}
	if(cate != null && cate != ""){
		$("#category").val(cate);
	}
}
//上传截取后的图片
function classTypePic() {
	$.ajax({
		url : rootPath + "/liveOpenCourse/saveCutPic",
		data : {
			path : $("#target").attr("src"),
			x : $("#x").val(),
			y : $("#y").val(),
			w : $("#w").val(),
			h : $("#h").val(),
			itemOneid : $("#itemOneid").val()
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			var imgpath = $("#imgpath").val();
			var quan = "http://"+imgpath+"/"+data;
			chooseOnePic(quan,data);
		}
	});
	$("#chooseDiv").css("display", "none");
	$("#stopDiv").css("display", "none");
	return;
}

//选择图片
function savePic(){
		$.ajaxFileUpload({
		url : rootPath+"/simpleClasses/savePic;"+ window["sessionName"] + "=" + window["sessionId"],
		secureuri : false,// 安全协议
		async : false,
		fileElementId : 'imgData',
		dataType:'json',
		type : "POST",
		success : function(data) {
		  $("#sourcePic").attr("src",data.url);
		  $("#target").parent().html('<img id="target" src="'+data.url+'" style="width:516px;height:282px;"/>');
	      $("#target").trigger("change");
	      $(".p1 img").attr("src",data.url);
	      $(".p2 img").attr("src",data.url);
	      $(".p3 img").attr("src",data.url);
		},
		error:function(arg1,arg2,arg3){
			//console.log(arg1);
		},
		loadingEle: '#target',
		fileName: 'imgData'
	});
}

function suffixChange(){
	var s = $("#schoolsuffix").val();
	s = s.replace(/[\W]/g,'');
	$("#schoolsuffix").val(s);
	if(s == ""){
		$(".suffix").html("");
	}else{
		$(".suffix").html("schools/" + s);
	}
}