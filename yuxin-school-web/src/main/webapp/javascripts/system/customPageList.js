$(document).ready(function(){
	$selectSubMenus('system_custom_page');
	//添加
	$(".addCustom_page").on('click',function(){
		window.location.href=rootPath+"/companyConfigCustompage/managePage/0";
	});
	//点击收索
	$("#searchContent").on('click',function(){
		search();
	});
	loadCustomList(function(){
		search();
	});
	//删除
	$(document).on('click.delCustomPage','i.delCustomPage',function(){
		var id=$(this).attr("ids");
		$.confirm("您确定要删除此页面吗?",function(b){
			if(b){
				$.ajax({ 
					  type: "post", 
					  url: rootPath+"/companyConfigCustompage/delCustomPage/"+id,
					  dataType:'json',
					  success: function(jsonData){
						  $.msg("删除成功");
						  search();
					  }
				});
			}
		});
	})//编辑
	.on('click.editCustomPage','i.editCustomPage',function(){
		var id=$(this).attr("ids");
		window.location.href=rootPath+"/companyConfigCustompage/managePage/"+id;
	});
});

//查询页面类型
function loadCustomList(callback){
	$("#templete_choose").html("");
	$.ajax({ 
		  type: "post", 
		  url: rootPath+"/companyConfigCustompageTemplate/queryCustomTypeList",
		  dataType:'json',
		  success: function(jsonData){
			  var html='<option value="">全部</option>';
			  $.each(jsonData,function(i,item){
				  html+='<option value="'+item.id+'">'+item.title+'</option>';
			  });
			  $("#templete_choose").append(html);
			  if(callback){
				  callback();
			  }
		  }
	});
}

//查询模板组
function loadGroupList(){
	$("#group_choose").html("");
	var templateId=$("#templete_choose").val();
	$.ajax({ 
		  type: "post", 
		  url: rootPath+"/companyConfigCustompageGroup/queryGroupList",
		  dataType:'json',
		  data:{"templateId":templateId},
		  success: function(jsonData){
			  var html='<option value="">全部</option>';
			  $.each(jsonData,function(i,item){
				  html+='<option value="'+item.id+'">'+item.groupName+'</option>';
			  });
			  $("#group_choose").append(html);
		  }
	});
}
function search(page){
	var data={};
	var title=$("#custom_title").val();
	var templeteId=$("#templete_choose").val();
	var groupId=$("#group_choose").val();
	data.page=page?page:1;
	data.title=title;
	data.templateId=templeteId;
	if(groupId != 0){
		data.groupId=groupId;
	}
	$.each(data,function(key,value){
		if(!value){
			delete data[key];
		}
	})
	$(".tables").find(".tr:gt(0)").remove();
	$(".tables").find(".no-info").remove();
	$.ajax({ 
		  type: "post", 
		  url: rootPath+"/companyConfigCustompage/customList",
		  data:data,
		  dataType:'json',
		  success: function(jsonData){
			 if(jsonData.data.length<=0){
				 $(".tables").append('<div class="no-info">没有找到匹配的内容</div>');
			 }
			 $.each(jsonData.data,function(i,item){
			   $(".tables").append('<div class="tr">'+
			                 '<span class="coll-1">'+
			             '<em class="color-blue">'+(item.title?item.title:"")+'</em>'+
			             '<em>自定义域名：/custom/page/'+(item.url?item.url:"")+'/'+item.id+'</em>'+
			         '</span>'+
			         '<span class="coll-11">'+(item.groupName?item.groupName:"无")+'</span>'+
			         '<span class="coll-2">'+(item.updateTime)+'</span>'+
			         '<span class="coll-3">'+(item.creaters)+'</span>'+
			         '<span class="coll-4">'+
			             '<i class="iconfont editCustomPage" ids='+item.id+' title="编辑">&#xe628;</i><i class="iconfont delCustomPage" ids='+item.id+' title="删除">&#xe626;</i>'+
			         '</span>'+
			     '</div>');
			 });
			 if(jsonData.rowCount>12){
					$(".pagination").pagination(jsonData.rowCount, {
				    	 next_text : "下一页",
				    	 prev_text : "上一页",
				    	 current_page : jsonData.pageNo-1,
				    	 link_to : "javascript:void(0)",
				    	 num_display_entries : 8,
				    	 items_per_page : jsonData.pageSize,
				    	 num_edge_entries : 1,
				    	 callback:function(page,jq){
					    	 var pageNo = page + 1;
					    	 search(pageNo);
				    	 }
				   });
				}else{
					$(".pagination").html('');
				}
		  }
	  });	
}
