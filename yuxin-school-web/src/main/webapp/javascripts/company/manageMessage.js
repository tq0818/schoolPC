$(document).ready(function(){
	$(".footer").addClass("footer-fixed");
	//类型
	$("#typeList").on('click','a.btn',function(){
		var _this=$(this),status= _this.hasClass('btn-primary');
		if(!status){
			 _this.addClass('btn-primary').siblings('a').removeClass('btn-primary');
		}
	});
	queryPageByKeys(1);
	
	//添加消息
	$("#addMsg").on('click',function(){
		location.href=rootPath+"/company/editMessage/add";
	});
});

//异步加载模块列表
function queryPageByKeys(page){
	var type;
	$("#typeList").find("a").each(function(i){
		if($(this).hasClass("btn-primary")){
			type=$(this).attr("marks");
		}
	});
	  $.ajax({ 
		  type: "post", 
		  url: rootPath+"/company/queryMessageList", 
		  data: {"page":page,"messageType":type},
		  beforeSend:function(XMLHttpRequest){
	          $(".loading").show();
	          $(".loading-bg").show();
	      },
		  success: function(result){
			  $("#newsManagerList").html(result);
		  },
		  complete:function(XMLHttpRequest,textStatus){
			  $(".loading").hide();
	          $(".loading-bg").hide();
	      }
	  });
}

//编辑消息
function editMsg(id){
	location.href=rootPath+"/company/editMessage/update?id="+id;
}

//发送消息
function sendMsg(id){
	$.ajax({ 
		  type: "post", 
		  url: rootPath+"/company/sendMsg",
		  data:{"id":id},
		  success: function(result){
			$.msg("发送成功");
			$("#msgSta"+id).text("已发送");
			$("#sendbtn"+id).remove();
		  }
	  });
}

//删除消息
function deleteMsg(id){
	$.confirm("您确定要删除吗?",function(b){
		if(b){
			$.ajax({ 
				  type: "get", 
				  url: rootPath+"/company/delteMsg/"+id,
				  success: function(result){
					 if(result=="success"){
						 $("#tr"+id).remove();
					 }
				  }
			  });
		}else{
			return;
		}
	});
}

