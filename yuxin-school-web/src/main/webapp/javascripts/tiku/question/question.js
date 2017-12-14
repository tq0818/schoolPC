var pageSize;
var subId;
var status;
var types;
var categoryId;
var name;
	$(function(){
		$selectMenu('tiku_header');
		$(".tiHeader .navspace li>a:eq(0)").addClass("active");
		$(".subject[data-id='" + $("#subId").val() + "']").attr("class","btn btn-sm btn-success subject");
		var page = $("#pageNO").val();
		
	    $(".icon-edit").click(function(){
	    	var subName=$(".btn-success.subject").text();
	    	var subId=$(".btn-success.subject").attr("data-id");
	    	$("#subName").val(subName);
	    	$(".btn-save").attr("data-id",subId);
	    	$(".editorContent").show();
	    	$(".editorbtn").hide();
	    });
	    
	    $(".icon-del").click(function(){
	    	var subId=$(".btn-success.subject").attr("data-id");
	    	delSubject(subId);
	    });
	    
	    $(".btn-add").click(function(){
	    	$("#subName").val("");
	    	$(".btn-save").attr("data-id","");
	    	$(".editorContent").show();
	    	$(".editorbtn").hide();
	    });
	    
	    $(".btn-save").click(function(){
	    	var subId=$(this).attr("data-id");
	    	var newSubName=$.trim($("#subName").val());
	    	if(!newSubName){
	    		alert("请输入科目名称");
	    		return false;
	    	}else if(subId){
	    		updateSubject(subId,newSubName);
	    	}else{
	    		addSubject(newSubName);
	    	}
	    });
	    
	    $(".btn-cancle").click(function(){
	    	$("#subName").val("");
	    	$(".btn-save").attr("data-id","");
	    	$(".editorContent").hide();
	    	$(".editorbtn").show();
	    });
	    
		//点击更换
	    $(".subList").on("click","a",function(){
			$(this).attr("class","btn btn-sm btn-success subject");
			$(this).prevAll(".subject").attr("class","btn btn-mini btn-default subject");
			$(this).nextAll(".subject").attr("class","btn btn-mini btn-default subject");
			$("#topicName").val("");
			selTopic(1);
		});
		$(".questionStatus").click(function(){
			$(this).attr("class","btn btn-mini btn-success questionStatus");
			$(this).prevAll().attr("class","btn btn-mini btn-default questionStatus");
			$(this).nextAll().attr("class","btn btn-mini btn-default questionStatus");
			$("#topicName").val("");
			selTopic(1);
		});
		
		types = $("#types").val();
		if(types != ""){
			$(".types[data-type='" + types + "']").attr("class","btn btn-mini btn-primary types");
			$(".types[data-type='" + types + "']").prevAll().attr("class","btn btn-mini btn-default types");
			$(".types[data-type='" + types + "']").nextAll().attr("class","btn btn-mini btn-default types");
		}
		status = $("#status").val();
		if(status != ""){
			$(".questionStatus[data-status='" + status + "']").attr("class","btn btn-mini btn-success questionStatus");
			$(".questionStatus[data-status='" + status + "']").prevAll().attr("class","btn btn-mini btn-default questionStatus");
			$(".questionStatus[data-status='" + status + "']").nextAll().attr("class","btn btn-mini btn-default questionStatus");
		}
		$(".types").click(function(){
			$(this).attr("class","btn btn-mini btn-primary types");
			$(this).prevAll().attr("class","btn btn-mini btn-default types");
			$(this).nextAll().attr("class","btn btn-mini btn-default types");
			$("#topicName").val("");
			selTopic(1);
		});
		$(".btn-sel-no").click(function(){
			selTopic(1);
		});
		if(page != null && page > 0){
			selTopic(page);
		}else{
			selTopic(1);
		}
	});
	
	function addSubject(subjectName){
        $.ajax({
            url: rootPath + "/tikuSubject/checkSubName",
            type: "post",
            data: {
                "subjectName": subjectName,
                "categoryId": $("#categoryId").val()
            },
            success: function (data) {
                if (data) {
                	$.ajax({
                        url: rootPath + "/tikuSubject/add1",
                        type: "post",
                        data: {"subjectName": subjectName,"categoryId": $("#categoryId").val(), "delFlag": 0  },
                        beforeSend: function (XMLHttpRequest) {
                                $(".loading").show();
                                $(".loading-bg").show();
                            },
                            success: function (data) {
                                if (data.result == "success") {
                                   var a=" <a class=\"btn btn-mini btn-default subject\" href=\"javascript:;\" data-id=\""+data.subId+"\">"+subjectName+"</a>";
                                   $('.subList').append(a);
                                   $(".btn-cancle").click();
                                }
                            },
                            complete: function (XMLHttpRequest, textStatus) {
                                $(".loading").hide();
                                $(".loading-bg").hide();
                            }
                    });
                } else {
                    alert("科目名称不允许重复");
                    return;
                }
            }
        });
	}
	
	function updateSubject(subId, newSubName){
		var $containsItem = $('.subList').children('a.subject');
        var $contains = new Array();
        $.each($containsItem, function (i, e) {
        	if(subId!=$(this).attr("data-id")){
        		$contains.push($(this).text().replace(/(^\s*)|(\s*$)/g, ""));
        	}
        });
        if ($contains.includes(newSubName)) {
            alert("科目已存在");
            return false;
        }else{
        	$.ajax({
                url: rootPath + "/tikuSubject/update",
                type: "post",
                data: {"id": subId,"subjectName": newSubName},
                beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                },
                success: function (data) {
                    if (data == "success") {
                    	$(".subject[data-id='" +subId+ "']").text(newSubName);
                    	$(".btn-cancle").click();
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
        }
	}
	function delSubject(subId){
		if($('.subList').children('a.subject').length <= 1){
    		alert("最后要一个科目不允许删除");
    		return false;
    	}else{
	        $.ajax({
	            url: rootPath + "/tikuSubject/checkSubjectIsHasChapter",
	            type: "post",
	            data: { "subId": subId },
	            success: function (data) {
	                if (data) {
	                	$.confirm("该科目下存在试题信息是否继续删除？", function (result) {
	                        if (result) {
	                            $.ajax({
	                                url: rootPath + "/tikuSubject/delSubById",
	                                type: "post",
	                                data: { "id": subId  },
	                                beforeSend: function (XMLHttpRequest) {
	                                    $(".loading").show();
	                                    $(".loading-bg").show();
	                                },
	                                success: function (data) {
	                                    if (data == "success") {
	    	                            	$(".subject[data-id='" +subId+ "']").remove();
	                                        $(".subject:eq(0)").click();
	                                    }
	                                },
	                                complete: function (XMLHttpRequest, textStatus) {
	                                    $(".loading").hide();
	                                    $(".loading-bg").hide();
	                                }
	                            });
	                        }
	                    });
	                } else {
	                    $.ajax({
	                        url: rootPath + "/tikuSubject/delSubById",
	                        type: "post",
	                        data: {"id": subId},
	                        success: function (data) {
	                            if (data == "success") {
	                            	$(".subject[data-id='" +subId+ "']").remove();
	                            	 $(".subject:eq(0)").click();
	                            }
	                        }
	                    });
	                }
	            }
	        });
    	}
	}
	
	//分页查询
	function selTopic(pageNo){
		pageSize = $("#pageSize").val();
		subId = $(".btn-success.subject").attr("data-id");
		status = $(".btn-success.questionStatus").attr("data-status");
		types = $(".btn-primary.types").attr("data-type");
		categoryId = $("#categoryId").val();
		name = $("#topicName").val();
		
		$.ajax({
			url:rootPath + "/question/selTopic",
			type:"post",
			data:{"page":pageNo,"pageSize":pageSize,"tikuSubjectId":subId,"tikuCategoryId":categoryId,"status":status,"topicType":types,"topicName":name},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	        },
			success:function(data){
				$(".table-list").html(data);
			},
	        complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}