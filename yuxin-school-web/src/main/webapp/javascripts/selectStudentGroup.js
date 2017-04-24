function selectGroup1(type){
	$("#studentG1"+type).html('');
	 $.ajax({
    	url: rootPath+"/studentGroup/selectStudentGroup1",
    	type: "post",
    	dataType: "json",
    	async:false,
    	success: function(jsonData){
    		$("#studentG1"+type).append('<option value="" selected="selected">一级分组</option>');
    		$.each(jsonData,function(i, g){
    			$("#studentG1"+type).append('<option value="' + g.id + '">'
				+ g.groupName + '</option>');
    		})
    		$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
    	}
     })
}
function selectGroup2(a,type){
	$("#studentG2"+type).html('');
	if($("#studentG1"+type).val()==""){
		$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
		return false;
	}
	$.ajax({
    	url: rootPath+"/studentGroup/selectStudentGroup2/"+$("#studentG1"+type).val(),
    	type: "post",
    	dataType: "json",
    	async:false,
    	success: function(jsonData){
    		$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
    		$.each(jsonData,function(i, g){
    			$("#studentG2"+type).append('<option value="' + g.id + '">'
				+ g.groupName + '</option>');
    		})
    	}
     })
}

function selectGroup_2(a,type,val){
	$("#studentG2"+type).html('');
	if(val==""){
		$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
		return false;
	}
	$.ajax({
    	url: rootPath+"/studentGroup/selectStudentGroup2/"+val,
    	type: "post",
    	dataType: "json",
    	async:false,
    	success: function(jsonData){
    		$("#studentG2"+type).append('<option value="" selected="selected">二级分组</option>');
    		$.each(jsonData,function(i, g){
    			$("#studentG2"+type).append('<option value="' + g.id + '">'
				+ g.groupName + '</option>');
    		})
    	}
     })
}