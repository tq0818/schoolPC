(function($) {;
	$(document).ready(function(){
		init();
	});
	
	function init(){
		var paperId = $("#paperId").val();
		loadDetailAjaxInfo(1,paperId);
		// 导出
        $("#exportExcle").on( 'click',  function () {
        	exportInfo(1, paperId, null, null,null,null,null);
       });
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
					$("#tableList").append('<tr><td colspan="5">没有查找到数据</td></tr>')
				}
				$.each(jsonData.data.data,function(idx,obj){
					console.log(obj);
					var item = $("<tr><td>"+getObject(obj,"name")+"</td><td>"+getObject(obj,"username")+"</td><td>"+getObject(obj,"mobile")+"</td><td>"+getObject(obj,"exercise_score")+"</td><td>"+timeFormatter(getObject(obj,"start_time"))+"</td></tr>");
					content.append(item);
					
					function timeFormatter(time){
						if(isEmpty(time)){
							return "";
						}else{
							return new Date(time).toLocaleDateString();
						}
					}
				});
				
				 if (jsonData.data.rowCount >6) {
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