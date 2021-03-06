(function($) {
	$(document).ready(function(){
		init();
        $(".tab-info").delegate(".tab-type","click",function () {
           var href = $(this).attr("href");
            $(this).addClass("active").siblings().removeClass("active");
            $(".content-show").children().hide();
            $(".tab-search").hide();
            $(href).show();
        });

		initStatsitcs();
	});
	
	function init(){
        $("#eduArea").change(function(){
            var area = $(this).find(":selected").attr("data-id");
            var schoolVal = $.trim($("#eduSchool").attr("data-id"));
            if(area==null || area==""){
                $("#eduSchool").html('<option value="">请选择所在学校</option>');
            }else{
                $.ajax({
                    url: rootPath + "/student/getSchoolList/"+area,
                    type: "post",
                    success: function (data) {
                        $("#eduSchool").html('<option value="">请选择所在学校</option>');
                        var options = '';
                        $.each(data,function(i,j){
                            if(schoolVal==j.itemValue){
                                options+='<option value="'+j.itemCode+'" selected="selected">'+j.itemValue+'</option>';
                            }else{
                                options+='<option value="'+j.itemCode+'">'+j.itemValue+'</option>';
                            }

                        });
                        $("#eduSchool").append(options);
                    }
                });
            }
        });
		var paperId = $("#paperId").val();
		loadDetailAjaxInfo(1,paperId);
		// 导出
        $("#exportExcle").on( 'click',  function () {
        	exportInfo(1, paperId, null, null,null,null,null);
       });

        // 收索
        $(".searchContents").on('click', function () {
            var paperId = $("#paperId").val();
            loadDetailAjaxInfo(1,paperId);
        });
	}

	function initStatsitcs(){
		var paperId = $("#paperId").val();
		$(".teacherContent2").find(".ques").each(function(){
			var topicId = $(this).attr("topicId");
			$.ajax({
				url: rootPath + "/tikuExamUserRelation/getExamAccuracy",
				data:{"paperId":paperId, "topicId":topicId},
				type:"post",
				success:function(result){
					if(result!=null && result!=""){
						$("#topic_"+topicId).append("<option>总计答题人数："+result.answerNum+"</option><option>正确人数："+result.answerAccuracyNum+"</option><option>正确率："+GetPercent(result.answerAccuracyNum, result.answerNum)+"</option>");
					}else{
                        $("#topic_"+topicId).append("<option>总计答题人数："+0+"</option><option>正确人数："+0+"</option><option>正确率："+GetPercent(result.answerAccuracyNum, result.answerNum)+"</option>");
					}
				},
				error:function(e){}
			});
		});
	}

	///计算两个整数的百分比值
	function GetPercent(num, total) {
		num = parseFloat(num);
		total = parseFloat(total);
		if (isNaN(num) || isNaN(total)) {
			return "-";
		}
		return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%");
	}

	function loadDetailAjaxInfo(pageNo,paperId,status,mobile,examId,start,end){
		var param = "";
		param+="&pageSize=10&page="+pageNo;
		if(examId){
			param+="&tikuExamId="+examId;
		}
		if(start){
			param+="&startTime="+start;
		}
		if(end){
			param+="&endTime="+end;
		}
		if(mobile){
			param+="&userMobile="+mobile;
			$("#paperName option:eq(0)").attr("selected","selected");
			$("#passStatus option:eq(0)").attr("selected","selected");
		}else{
			if(paperId && paperId != 0){
				param+="&exerciseId="+paperId;
			}
			if(status){
				if(status!=0){
					param+="&status="+status;
				}
			}
		}
        param+="&eduArea="+$("#eduArea").val();
        param+="&eduSchool="+$("#eduSchool").val();
        param+="&eduClass="+$("#eduClass").val();
        param+="&eduYear="+$("#eduYear").val();
		$("#tableList").find("tr:gt(0)").remove();
		$.ajax({
			url : rootPath + "/tikuExamUserRelation/getPaperRspdInfo",
			type : "post",
			data :param,
			beforeSend : function(XMLHttpRequest) {
				$(".loading").show();
				$(".loading-bg").show();
			},
			success : function(jsonData) {
				var content = $("#tableList");
				if(jsonData.data.data.length == 0){
					$("#tableList").append('<tr><td colspan="7">没有查找到数据</td></tr>')
				}
				$.each(jsonData.data.data,function(idx,obj){
					console.log(obj);
					var item = $("<tr><td>"+getObject(obj,"username")+"</td><td>"+getObject(obj,"name")+"</td><td>"+getObject(obj,"eduArea")+"</td><td>"+getObject(obj,"eduSchool")+"</td><td>"+getObject(obj,"eduStep")+getObject(obj,"eduYear")+"年"+getObject(obj,"eduClass")+"班</td><td>"+getObject(obj,"exercise_score")+"</td><td>"+timeFormatter(getObject(obj,"start_time"))+"</td></tr>");
					content.append(item);
					
					function timeFormatter(time){
						if(isEmpty(time)){
							return "";
						}else{
							return new Date(time).toLocaleString();
						}
					}
				});
				
				 if (jsonData.data.rowCount >10) {
                     $(".pagination").pagination(jsonData.data.rowCount,
                         {
                             next_text: "下一页",
                             prev_text: "上一页",
                             current_page: jsonData.data.pageNo - 1,
                             link_to: "javascript:void(0)",
                             num_display_entries: 8,
                             items_per_page: jsonData.data.pageSize,
                             num_edge_entries: 1,
                             callback: function (page, jq) {
                                 var pageNo = page + 1;
                                 loadDetailAjaxInfo(pageNo,paperId,status,mobile,examId,start,end);
                             }
                         });
				 }else{
                     $(".pagination").empty();
                 }
				//setPage(data.data);
			},
			complete : function(XMLHttpRequest, textStatus) {
				$(".loading").hide();
				$(".loading-bg").hide();
			}
		});
	}

	function exportInfo(pageNo,paperId,status,mobile,examId,start,end){
		$("#page").val(pageNo);
		if(examId){
			$("#tikuExamId").val(examId);
		}
		if(start){
			$("#startTime").val(start);
		}
		if(end){
			$("#endTime").val(end);
		}
		if(mobile){
			$("#userMobile").val(mobile);
		}else{
			if(paperId &&paperId !=0){
				$("#tikuPaperId").val(paperId);
			}
			if(status){
				if(status!=0){
					$("#status").val(status);
				}
			}
		}
		$("#exportForm").attr("action",rootPath + "/tikuExamUserRelation/exportExcle1").submit();
	}
	function setPage(pageInfo){
		$(".pagination").html("");
		$(".pagination").pagination(pageInfo.rowCount,{
			next_text : "下一页",
			prev_text : "上一页",
			current_page : (pageInfo.pageNo)-1,
			link_to : "javascript:void(0)",
			num_display_entries : 8,
			items_per_page : pageInfo.pageSize,
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				var tikuId = $("#tikuId").val();
				var subId = $("#subId").val();
				var paperType = $("#paperType").val();
				var paperStatus = $("#paperStatus").val();
				var paperName = $("#paperName").val();
				loadDetailAjaxInfo(pageNo, subId, paperType,paperStatus, paperName, tikuId);
			}
		});
	}
})(jQuery);


function getObject(obj, key) {
    if (!isEmpty(obj)) {
        var res = obj[key];
        if (!isEmpty(res)) {
            return res;
        } else {
            return "";
        }
    } else {
        return "";
    }
}

function isEmpty(obj) {
    if (obj == null || obj === "" || typeof (obj) == "undefined") {
        return true;
    }
    return false;
}