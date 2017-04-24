/**
 * author zhang.zx
 * 远程结费
 * 页面js封装
 */
(function($){
	var Form={
			init : function(){
				var $this=this;
				$selectSubMenu('netschool_remote');
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
				$("#pStatus").on("click","a.btn",function(){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
				});
				var itemOneId=$("#itemOneList").find("a").eq(0).attr("ids");
				$this.queryItemContent(itemOneId);
				$this.queryStuLongList(1);
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
								$("#itemSecondList").append("<a href='javascript:Form.queryStuLongList(1);' class='btn btn-mini btn-default'>全部</a>");
							}
							$("#itemSecondList").append("<a href='javascript:Form.queryStuLongList(1);' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.itemName+"</a>");
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
								$("#itemSecondList").append("<a href='javascript:Form.queryStuLongList(1);' class='btn btn-mini btn-default'>全部</a>");
							}
							$("#itemSecondList").append("<a href='javascript:Form.queryStuLongList(1);' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.itemName+"</a>");
						});
						window.Form.queryTermList(id);
						window.Form.queryStuLongList(1);
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
								$("#termList").append("<a href='javascript:Form.queryStuLongList(1);' class='btn btn-mini btn-default'>全部</a>");
							}
							 $("#termList").append("<a href='javascript:Form.queryStuLongList(1);' ids='"+term.id+"' class='btn btn-mini btn-default'>"+term.termName+"</a>");
						});
					}
				});
			},
			queryStuLongList : function(page){
				var itemOneId,itemSecondId,examTermId,payoffStatus;
				$("#itemOneList").find("a.btn").each(function(){
					var st1=$(this).hasClass("btn-success");
					if(st1){
						itemOneId=$(this).attr("ids");
					}
				});
				$("#itemSecondList").find("a.btn").each(function(i){
					if($(this).hasClass('btn-success')){
						itemSecondId=$(this).attr("ids");
					}
				});
				$("#termList").find("a.btn").each(function(i){
					if($(this).hasClass('btn-success')){
						examTermId=$(this).attr("ids");
					}
				});
				$("#pStatus").find("a.btn").each(function(i){
					if($(this).hasClass('btn-success')){
						payoffStatus=$(this).attr("ids");
					}
				});
				$("#remoteListMsg").html('');
				$.ajax({
					type : "post",
					url : rootPath + "/fee/queryLists",
					data : {"page" : page,"itemOneId" : itemOneId,"itemSecondId" : itemSecondId,"examTermId" : examTermId,"payoffStatus":payoffStatus},
					success : function(result) {
						$("#remoteListMsg").html(result);
					}
				});
			},
			searchByName:function(page){
				var remoteName=$("#remoteName").val();
				$("#remoteListMsg").html('');
				$.ajax({
					type : "post",
					url : rootPath + "/fee/queryLists",
					data : {"page" : page,"classTypeName" : remoteName},
					success : function(result) {
						$("#remoteListMsg").html(result);
					}
				});
			},
			queryDetailList:function(id){
				$("#lsOne").find("table").find("tr:gt(0)").remove();
				$.ajax({
					type : "post",
					url : rootPath + "/fee/queryFeeList",
					data : {"feeId" : id},
					success : function(jsonData) {
						$.each(jsonData,function(i,data){
							$("#lsOne").find("table").append('<tr>'+
								'<td>'+(data.classTypeName?data.classTypeName:"")+'</td>'+
								'<td>'+(data.examTermName?data.examTermName:"")+'</td>'+
								'<td>'+(data.payoffDate?data.payoffDate:"")+'</td>'+
								'<td>'+(data.payoffFee?data.payoffFee:"")+'</td>'+
								'<td>'+(data.userName?data.userName:"")+'</td>'+
								'</tr>');
						});
					}
				});
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)