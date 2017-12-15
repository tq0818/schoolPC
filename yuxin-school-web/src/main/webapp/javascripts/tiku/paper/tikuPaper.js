
(function($) {
	var tikuId;//题库类别id
	var subId;//科目id
	var paperType;//试卷类型
	var paperStatus;//试卷状态
	var paperName;//试卷名称
	var Forms = {
		init : function() {
			tikuId = $("#tikuId").val();//题库类别Id
			$("#subList").find("a").each(function(i){
				if($(this).hasClass('btn-success')){
					var id=$(this).attr("subId");
					subId=id;
					$("#subId").val(subId);
				}
			})
			/*.click(function(){
				subId = $(this).attr("subId");
				$(this).addClass("btn-success").siblings(".subject").removeClass("btn-success");
				$("#subId").val(subId);
				Forms.loadAjaxInfo(1,subId, paperType, paperStatus, paperName, tikuId);
			});*/
			
			//点击更换
		    $("#subList").on("click","a",function(){
		    	$(this).addClass("btn-success").siblings(".subject").removeClass("btn-success");
				subId = $(this).attr("subId");
				$("#subId").val(subId);
				Forms.loadAjaxInfo(1,subId, paperType, paperStatus, paperName, tikuId);
			});
			
			$(".icon-edit").click(function(){
		    	var subName=$(".btn-success.subject").text();
		    	var subId=$(".btn-success.subject").attr("subId");
		    	$("#subName").val(subName);
		    	$(".btn-save").attr("data-id",subId);
		    	$(".editorContent").show();
		    	$(".editorbtn").hide();
		    });
		    
		    $(".icon-del").click(function(){
		    	var subId=$(".btn-success.subject").attr("subId");
		    	Forms.delSubject(subId);
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
		    		Forms.updateSubject(subId,newSubName);
		    	}else{
		    		Forms.addSubject(newSubName);
		    	}
		    });
		    
		    $(".btn-cancle").click(function(){
		    	$("#subName").val("");
		    	$(".btn-save").attr("data-id","");
		    	$(".editorContent").hide();
		    	$(".editorbtn").show();
		    });
			
			$(".paperType").click(function(){
				$(this).addClass("btn-success").siblings(".paperType").removeClass("btn-success");
				paperType = $(this).attr("pt");
				$("#paperType").val(paperType);
				Forms.loadAjaxInfo(1,subId, paperType, paperStatus, paperName, tikuId);
			});
			
			$(".paperStatus").click(function(){
				$(this).addClass("btn-success").siblings(".paperStatus").removeClass("btn-success");
				paperStatus = $(this).attr("ps");
				$("#paperStatus").val(paperStatus);
				Forms.loadAjaxInfo(1,subId, paperType, paperStatus, paperName, tikuId);
			});
			
			//添加新试卷
			$("#newPaper").click(function(){
				if(subId){
					location.href=rootPath+"/tikuPaper/toPaperInfo/"+tikuId+"/"+subId+"/0/add/papers";
				}else{
					$('<div class="c-fa">' + "请先添加科目" + '</div>')
					.appendTo('body').fadeIn(100).delay(
							1000).fadeOut(200, function() {
						$(this).remove();
					});
					return;
				}
				
			});
			
			//进入试卷首页的时候，需要判断当前cookie中是否有值，如果有值则进入相应条件的页面
			var paperPageNo = $.cookie("paperPageNo");
			if(paperPageNo != null && paperPageNo > 0){
				Forms.loadAjaxInfo(paperPageNo,$.cookie("paperSubject"), $.cookie("paperType"), $.cookie("paperStatus"), null,  $.cookie("paperTiku"));
			}else{
				Forms.loadAjaxInfo(1,subId, null, null, null, tikuId);
			}
			
		},
		loadAjaxInfo : function(pageNo,subId,paperType,paperStatus,paperName,tikuId){
			var param = "";
			if(pageNo !=null && pageNo != ""){
				param+="&page="+pageNo;
			}
			if(subId !=null && subId != ""){
				param+="&tkuSubjectId="+subId;
			}
			if(paperType !=null && paperType != ""){
				param+="&paperType="+paperType;
			}
			if(paperStatus !=null && paperStatus != ""){
				param+="&paperStatus="+paperStatus;
			}
			if(paperName !=null && paperName != ""){
				param+="&paperName="+paperName;
			}
			if(tikuId !=null && tikuId != ""){
				param+="&tikuCategoryId="+tikuId;
			}
			$.ajax({
				url : rootPath+"/tikuPaper/loadAjaxInfo",
				type : "post",
				data : param,
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success : function(data){
					$("#info").html("");
					$("#info").html(data);
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		},
		editPaper : function(obj){
			var paperId = $(obj).attr("paperId");
			var btn = $(obj).attr("btn");
			
			//将试卷的条件添加到cookie中
			$.cookie("paperPageNo",null,{ path: "/"});
			$.cookie("paperPageNo",$(".pagination .active a").html(),{ path: "/"});
			//科目
			$.cookie("paperSubject", null, { path: "/"});
			$.cookie("paperSubject", $("#subList .btn-success").attr("subid"), { path: "/"});
			//类型
			$.cookie("paperType", null, { path: "/"});
			$.cookie("paperType", $(".paperType.btn-success").attr("pt"), { path: "/"});
			//状态
			$.cookie("paperStatus", null, { path: "/"});
			$.cookie("paperStatus", $(".paperStatus.btn-success").attr("ps"), { path: "/"});
			//题库
			$.cookie("paperTiku", null, { path: "/"});
			$.cookie("paperTiku", $("#tikuId").val(), { path: "/"});
			
			if(paperId && btn == "audite"){
				location.href=rootPath+"/tikuPaper/toPageInfoAudit?tikuId="+tikuId+"&subjectId="+subId+"&paperId="+paperId+"&btn="+btn+"&exam=paper";
			}else if(paperId){
				location.href=rootPath+"/tikuPaper/toPaperInfo/"+tikuId+"/"+subId+"/"+paperId+"/"+btn+"/paper";
			}else{
			}
			
		},
		delPaper : function(obj){
			var paperId = $(obj).attr("paperId");
			if(paperId){
				$.confirm("是否删除该试卷？",function(result){
					if(result){
						$.ajax({
							url : rootPath+"/tikuPaper/delPaper",
							type : "post",
							data : {"id":paperId},
							success : function(data){
								if(data == "success"){
									Forms.loadAjaxInfo(1,subId, paperType, paperStatus, paperName, tikuId);
								}else{
									alert("error");
									return;
								}
							}
						});
					}
				})
				
			}else{
				alert("主键不存在");
				return;
			}
		},
		searchByName : function(){
			var name = $("#pName").val();
			Forms.loadAjaxInfo(1,subId, paperType, paperStatus, name, tikuId);
		},
		statistics : function(obj){
			location.href=rootPath+"/tikuExamUserRelation/paperStatisticsIndex?id="+$(obj).attr("paperId")+"&tikuCategoryId="+$("#tikuId").val();
		},
		
		addSubject : function(subjectName){
	        $.ajax({
	            url: rootPath + "/tikuSubject/checkSubName",
	            type: "post",
	            data: { "subjectName": subjectName, "categoryId": $("#tikuId").val()},
	            success: function (data) {
	                if (data) {
	                	$.ajax({
	                        url: rootPath + "/tikuSubject/add1",
	                        type: "post",
	                        data: {"subjectName": subjectName,"categoryId": $("#tikuId").val(), "delFlag": 0  },
	                        beforeSend: function (XMLHttpRequest) {
	                                $(".loading").show();
	                                $(".loading-bg").show();
	                            },
	                            success: function (data) {
	                                if (data.result == "success") {
	                                   var a="<a href=\"javascript:;\" class=\"btn btn-mini btn-default subject\" subId=\""+data.subId+"\">"+subjectName+"</a>";
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
		},
		updateSubject:function(subId, newSubName){
			var $containsItem = $('.subList').children('a.subject');
	        var $contains = new Array();
	        $.each($containsItem, function (i, e) {
	        	if(subId!=$(this).attr("subId")){
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
	                    	$(".subject[subId='" +subId+ "']").text(newSubName);
	                    	$(".btn-cancle").click();
	                    }
	                },
	                complete: function (XMLHttpRequest, textStatus) {
	                    $(".loading").hide();
	                    $(".loading-bg").hide();
	                }
	            });
	        }
		},
		delSubject:function(subId){
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
		    	                            	$(".subject[subId='" +subId+ "']").remove();
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
		                            	$(".subject[subId='" +subId+ "']").remove();
		                            	 $(".subject:eq(0)").click();
		                            }
		                        }
		                    });
		                }
		            }
		        });
	    	}
		}
		
	}
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)