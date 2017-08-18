$(document).ready(function(){
	$(".footer").addClass("footer-fixed");
	//分校
	$("#schoolListP").on('click','a.btn',function(){
		var _this=$(this),status= _this.hasClass('btn-success');
		if(!status){
			 _this.addClass('btn-success').siblings('a').removeClass('btn-success');
		}
		queryNewsType('',function(){
			queryPageByKeys(1,'');
		});
	});
	//类型
	$("#typeList").on('click','a.btn',function(){
		var _this=$(this),status= _this.hasClass('btn-primary');
		if(!status){
			 _this.addClass('btn-primary').siblings('a').removeClass('btn-primary');
		}
		queryPageByKeys(1,'','');
	});
	//状态
	$("#chooseStatus").on('click','a.btn',function(){
		var _this=$(this),status= _this.hasClass('btn-primary');
		if(!status){
			 _this.addClass('btn-primary').siblings('a').removeClass('btn-primary');
		}
	});
	//发布时间
	$("#publicTime").on('click','a.btn',function(){
		var _this=$(this),status= _this.hasClass('btn-primary');
		if(!status){
			 _this.addClass('btn-primary').siblings('a').removeClass('btn-primary');
		}
	});
	var schoolId="";
	$("#schoolListP").find("a").each(function(i){
		if($(this).hasClass("btn-success")){
			schoolId=$(this).attr("mark");
		}
	})
	queryPageByKeys(1,schoolId);
	queryNewsType(schoolId);
});
//
function queryNewsType(schoolId,callback){
	if(schoolId==""){
		$("#schoolListP").find("a").each(function(i){
			if($(this).hasClass("btn-success")){
				schoolId=$(this).attr("mark");
			}
		})
	}
	$.ajax({
		  type: "post", 
		  url: rootPath+"/sysNewsType/selectSysNewsType2", 
		  data: {schoolId :schoolId},
		  success: function(data){
			  var html='<a href="javascript:void(0);" marks="" class="btn btn-mini btn-default btn-primary">全部</a>';
			  $.each(data,function(index,a){
				  html+='&nbsp;<a href="javascript:void(0);" marks="'+a.id+'" class="btn btn-mini btn-default">'+a.name+'</a>';
			  });
			  $("#typeList").html(html);
			  if(callback){
				  callback();
			  }
		  }
	});
	
}
//异步加载模块列表
function queryPageByKeys(page,schoolId,type,status,day){
	if(schoolId==""){
		$("#schoolListP").find("a").each(function(i){
			if($(this).hasClass("btn-success")){
				schoolId=$(this).attr("mark");
			}
		})
	}
	if(type==""){
		$("#typeList").find("a").each(function(i){
			if($(this).hasClass("btn-primary")){
				type=$(this).attr("marks");
			}
		});
	}
	if(status==""){
		$("#chooseStatus").find("a").each(function(){
			if($(this).hasClass("btn-primary")){
				status=$(this).attr("marks");
			}
		});
	}
  $.ajax({ 
	  type: "post", 
	  url: rootPath+"/sysConfigNews/showNewsList", 
	  data: {"page":page,"schoolId":schoolId,"newsType":type,"newsStatus":status,"day":day},
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

//编辑新闻公告
function editNews(type,id){
	//选择分校
	var mark;
	$("#schoolListP").find("a.btn").each(function(){
		var status=$(this).hasClass("btn-success");
		if(status){
			mark=$(this).attr("mark");
		}
	});
	if(mark==""){
		$.msg("请选择分校");
		return;
	}
	$("#sId").val(mark);
	$("#type").val(type);
	$("#newsId").val(id);
	$("#myForm").attr("action",rootPath+"/sysConfigNews/queryNewsById").submit();
}

/**
 * 改变新闻发布状态
 * @param newsId
 * @param newsStatus
 */
function changNewsStatus(newsId) {
	var status=$("#com"+newsId).attr("marks");
	if(status==0){
		status=1;
	}else{
		status="0";
	}
	$.ajax({
		url : rootPath + "/sysConfigNews/changNewsStatus",
		type : "post",
		data : {"id":newsId,"newsStatus":status},
		success : function(news) {
			if(news.newsStatus=="1"){
				$("#td"+newsId).text("已发布");
				$("#com"+newsId).text("下线").attr("marks",1);
			}else{
				$("#td"+newsId).text("未发布");
				$("#com"+newsId).text("发布").attr("marks",0);
			}
			$.msg("操作成功!");
		}
	});
}

//根据新闻名称收索
function searh(page){
	var schoolId="";
	if(schoolId==""){
		$("#schoolListP").find("a").each(function(i){
			if($(this).hasClass("btn-success")){
				schoolId=$(this).attr("mark");
			}
		})
	}
	var title=$("#newTitle").val();
	$.ajax({ 
		  type: "post", 
		  url: rootPath+"/sysConfigNews/showNewsList", 
		  data: {"page":page,"newsTitle":title,"schoolId":schoolId},
		  success: function(result){
			  $("#newsManagerList").html(result);
		  }
	  });
}

//删除新闻公告
function deleteNew(id){
	$.confirm("您确定要删除吗?",function(b){
		if(b){
			$.ajax({ 
				  type: "get", 
				  url: rootPath+"/sysNews/del/"+id,
				  success: function(result){
					 if(result=="success"){
						 $("#tr"+id).remove();
					 }
				  }
			  });
			queryPageByKeys();
		}else{
			return;
		}
	});
}
