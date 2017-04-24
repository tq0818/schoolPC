/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
(function($){
	var Form={
			init : function(){
				$selectSubMenu('student_agent');
				$(".footer").addClass("footer-fixed"); 
				$("#itemOneList").on("click","a.btn",function(){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
				});
				$("#itemSecondList").on("click","a.btn",function(){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
				});
				$("#termList").on("click","a.btn",function(){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
				});
				$("#completeList").on("click","a.btn",function(){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
				});
				var itemOneId=$("#itemOneList").find("a").eq(0).attr("ids");
				this.queryItemContent(itemOneId);
				this.queryStudentList(1,itemOneId);
			},
			queryItemContent : function(id){
				$.ajax({
					url : rootPath + "/exam/queryItemSecond",
					type : "post",
					data : {pid:id},
					dataType : "json",
					success : function(result) {
						$("#itemSecondList").html('');
						$.each(result,function(i,item){
							if(i==0){
								$("#itemSecondList").append("<a href='javascript:Form.queryStudentList(1,"+id+");' class='btn btn-mini btn-default btn-success'>全部</a>");
							}
							$("#itemSecondList").append("<a href='javascript:Form.queryStudentList(1,"+id+","+item.id+");' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.itemName+"</a>");
						});
						window.Form.queryTermList(id);
					}
				});
			},
			queryItemSecond : function (id){
				$.ajax({
					url : rootPath + "/exam/queryItemSecond",
					type : "post",
					data : {pid:id},
					dataType : "json",
					success : function(result) {
						$("#itemSecondList").html('');
						$.each(result,function(i,item){
							if(i==0){
								$("#itemSecondList").append("<a href='javascript:Form.queryStudentList(1,"+id+");' class='btn btn-mini btn-default btn-success'>全部</a>");
							}
							$("#itemSecondList").append("<a href='javascript:Form.queryStudentList(1,"+id+","+item.id+");' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.itemName+"</a>");
						});
						window.Form.queryTermList(id);
						window.Form.queryStudentList(1,id,null,null,null);
					}
				});
			},
			queryTermList : function (id){
				$("#termList").html('');
				$.ajax({
					url : rootPath + "/exam/terms",
					type : "post",
					data : {"itemOneId" : id},
					dataType : "json",
					success : function(result) {
						$.each(result,function(i,term){
							if(i==0){
								$("#termList").append("<a href='javascript:Form.queryStudentList(1,"+id+",null,null);' class='btn btn-mini btn-default btn-success'>全部</a>");
							}
							if(term.termName){
								 $("#termList").append("<a href='javascript:Form.queryStudentList(1,"+id+",null,"+term.id+");' ids='"+term.id+"' class='btn btn-mini btn-default'>"+term.termName+"</a>");
							}
						});
					}
				});
			},
			queryStudentList : function(page,itemOneId,itemSecondId,examTermId,isComplete){
				if(itemOneId==""){
					itemOneId=$("#itemOneId").val();
				}
				if(itemSecondId==null){
					$("#itemSecondList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var twoid=$(this).attr("ids");
							itemSecondId=twoid;
						}
					});
				}
				if(examTermId==null){
					$("#termList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var tid=$(this).attr("ids");
							examTermId=tid;
						}
					});
				}
				if(isComplete==null){
					$("#completeList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							isComplete=cid;
						}
					});
				}
				$("#studentList").html('');
				$.ajax({
					url : rootPath + "/exam/ajaxStudentList",
					type : "post",
					data : {"page" : page,"itemOneId" : itemOneId,"itemSecondId" : itemSecondId,"examTermId" : examTermId,"isAgentMaterialComplete" : isComplete},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#studentList").html(result);
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			apply: function(){
				$("#tableList").find("tr:gt(0)").find("input[type=checkbox]:checked").each(function(i){
					var id=$(this).val();
		    		$.ajax({
		    			url:rootPath+'/exam/apply/'+id,
		    			data: '',
		    			type:'post',
		    			success: function(result){
		    				$("#completeStatus"+id).text("是");
		    				$.msg("操作成功！");
		    			},
		    			error: function(e){
		    				$.msg("操作失败！");
		    			}
		    		});
		    	});
		    },
		    queryStuListByMobile : function(page){
		    	$("#tip").attr("style","display:none");
		    	var mobile=$("#mobile").val();
		    	var itemOneId,itemSecondId,examTermId,isComplete;
		    	if(itemOneId=="" || itemOneId==null){
					itemOneId=$("#itemOneId").val();
				}
				if(itemSecondId==null){
					$("#itemSecondList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var twoid=$(this).attr("ids");
							itemSecondId=twoid;
						}
					});
				}
				if(examTermId==null){
					$("#termList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var tid=$(this).attr("ids");
							examTermId=tid;
						}
					});
				}
				if(isComplete==null){
					$("#completeList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							isComplete=cid;
						}
					});
				}
		    	$.ajax({
	    			url:rootPath+'/exam/ajaxStudentList',
	    			data: {"page":page,"mobile":mobile,"itemOneId" : itemOneId,"itemSecondId" : itemSecondId,"examTermId" : examTermId,"isAgentMaterialComplete" : isComplete},
	    			type:'post',
	    			success: function(result){
	    				$("#studentList").html(result);
	    			}
	    		});
		    },
		    exportExcel : function (){
		    	if($("#tableList").find("tr").eq(1).find("td").length<=1){
		    		alert("没有数据可以导出");
		    	}else{
		    		$("#searchForm").attr("action",rootPath+"/exam/exportExcels").submit();
		    	}
		    	return false;
		      }
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)