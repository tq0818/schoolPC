//根据手机号查询学员报考材料信息
function search1(){
		var mobile = document.getElementById("mobile").value;
		$("#ulList").html('');
		$.ajax({
			url:rootPath+"/studentAgentMaterial/search",
			type:"post",
			data:{
				"mobile":mobile
			},
			success:function(data){
				if(data.length<=0){
					$("#tip").attr("style","display:block");
				}else{
					$("#tip").attr("style","display:none");
					if(data.length>1){
						var html="";
						$.each(data,function(i,item){
							html+="<li><table class='table table-noline'><col width='20%'><col width='20%'><col width='20%'><col width='20%'><col width='20%'>";
							html+="<tr><td><span class='c-title'>姓名</span><span class='c-content'> "+item.stuName+"</span></td><td><span class='c-title'>科目</span><span class='c-content'> "+item.itemOneName+"</span></td>";
							html+="<td><span class='c-title'>学习课程</span><span class='c-content'> "+item.classTypeName+"</span></td>";
							if(item.isAgent=="1"){
								html+="<td><span class='c-title'>是否代报考</span><span class='c-content'> 是</span></td>";
							}else{
								html+="<td><span class='c-title'>是否代报考</span><span class='c-content'> 否</span></td>";
							}
							html+="<td rowspan='2'><a href='javascript:queryClassTypeDetail("+item.id+");' class='btn btn-primary btn-sm'>报考材料</a></td></tr>";
							html+="<tr><td><span class='c-title'>手机号</span><span class='c-content'>&nbsp;"+item.mobile+"</span></td><td><span class='c-title'>科目小类</span><span class='c-content'> "+item.itemSecondName+"</span></td><td colspan='2'>&nbsp;</td></tr>";
							html+="</table><li>";
						});
						$("#ulList").append(html);
					}else{
						console.log(data.length);
						$.each(data,function(i,item){
							$("#studentPayMasterId").val(item.id);
						});
						$("#toPayMessage")[0].submit();
					}
				}
			},
			
		});
}
function queryClassTypeDetail(id){
	$("#studentPayMasterId").val(id);
	$("#toPayMessage")[0].submit();
}
$(document).ready(function() {
	search1();
});
