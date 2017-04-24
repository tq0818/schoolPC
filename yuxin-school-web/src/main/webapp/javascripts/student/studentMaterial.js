$(function(){
	$(document).on("click",".icon-btn",function(e){
		 var ele=e.target;
		 var state=$("#dbk").is(':checked');
		 if(state){
			 if($(ele).is("em")){
			 		var num=parseInt($(ele).find("i").html());
			     	$(ele).find("i").html(num+1);
			 	}else if($(ele).is("i")){
			 		var num=parseInt($(ele).html());
			     	num=num>0?num-1:0;
			     	$(ele).html(num);
			     	return false;
			 	}	 
		 }      	
	});
	$("#mobile").on("keypress",function(e){
		if(e.keyCode==13){
			$("#searchBtn").trigger("click");
			return false;
		}
	})
});

//添加代报考
function saveStuMaterial(){ 
	var paymaster={};
	var materialInfo=new Array(), stumaster=new Array();
	var studentId=$("#studentId").val();
	var materialMark=$("#matrailMark").val();
	var materialFee=$("#materialFee").val();
	var paymaterId=$("#paymaterId").val();
	var payMethod="";
	var m=$("input[name=payFeeMethod]").is(':checked');
	if(m){
		payMethod=$("input[name=payFeeMethod]").attr("value");
	}
	var state=$("#dbk").is(':checked');
	var dbk=0;
	if(state){
		dbk=1;
	}
	var state1=$("#zlqq").is(':checked');
	var zlqq=0;
	if(state1){
		zlqq=1;
	}
	stumaster.push({id:paymaterId,isAgent:dbk,isAgentMaterialComplete:zlqq,agentRemark:materialMark,examAgentFee:materialFee,paymentTypeCode:payMethod});
	var arg=["EM_INCH1","EM_INCH2","EM_IDCARD","EM_EDUCATION","EM_DEGREE","EM_PROVE","EM_OTHER"];
	$("#material").find("em").each(function(i){
		var $material=$(this).find("i");
		var $unmaterial=$("#unmaterial").find("em").eq(i).find("i");
		materialInfo.push({stuId:studentId,payMasterId:paymaterId,materialTypeCode:arg[i],id:$material.attr("ids"),materialName:$material.attr("txt"),materialNumYet:$material.html(),materialNumNot:$unmaterial.html()});
	});
	paymaster.material=JSON.stringify(materialInfo);
	paymaster.stuMaster=JSON.stringify(stumaster);
	$.ajax({
		url:rootPath+"/studentAgentMaterial/addStuMaterial",
		type:"post",
		data:paymaster,
		dataType:"json",
		success:function(data){
			if(data=="success"){
				alert("添加代报考成功！");
				//window.location.href=rootPath+"/studentAgentMaterial/stuMaterial";
			}else{
				alert("添加代报考失败!");
			}
		},
		
	})
}
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