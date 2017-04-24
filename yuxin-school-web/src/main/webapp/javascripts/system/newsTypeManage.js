(function($){
	$(function(){
		//分校
		$("#schoolListP").on('click','a.btn',function(){
			var _this=$(this),status= _this.hasClass('btn-success');
			if(!status){
				 _this.addClass('btn-success').siblings('a').removeClass('btn-success');
			}
			$("#schoolId").val(_this.attr("mark"));
			queryNewsType();
		});
		//显示添加div
		$("#addNewsType").on("click",function(){
			$("#addName").val("");
			$(".addDiv").popup("show");
		});
		//添加btn click
		$("#addBtn").on("click",function(){
			var schoolId=$("#schoolId").val();
			var name=$("#addName").val();
			if(name.length==0){
				$.msg("类型不能为空");
				return;
			}
			if(name.length>6){
				$.msg("类型最大为6个字");
				return;
			}
			$.ajax({
				type : "post",
				url : rootPath+"/sysNewsType/insertSysNewsType",
				data : {name : name,
						schoolId : schoolId},
				success : function(data){
					if(data.flag=="success"){
						$.msg("添加成功");
						$(".addDiv").popup("hide");
						queryNewsType();
					}
					else{
						$.msg("新闻类型已存在");
					}
				}
			});
		});
		//显示修改div
		$(".table").on("click","#editType",function(){
			$(".editDiv").popup("show");
			$("#editName").attr("data-id",$(this).parents("tr").attr("id"));
			$("#editName").val($(this).parents("tr").find(".name").text());
		});
		//修改btn click
		$("#editBtn").on("click",function(){
			var name=$("#editName").val();
			if(name.length==0){
				$.msg("类型不能为空");
				return;
			}
			if(name.length>6){
				$.msg("类型最大为6个字");
				return;
			}
			$.ajax({
				type : "post",
				url : rootPath+"/sysNewsType/updateSysNewsType",
				data : {id : $("#editName").attr("data-id"),
						name : $("#editName").val(),
						schoolId : $("#schoolId").val()},
				success : function(data){
					if(data.flag=="success"){
						$.msg("修改成功");
						$(".editDiv").popup("hide");
						queryNewsType();
					}
					else{
						$.msg("新闻类型名已存在");
					}
				}
			});
		});
		//失效 生效 click
		$(".table").on("click","#typeStatus",function(){
			var id=$(this).parents("tr").attr("id");
			var delFlag=$(this).attr("data-status");
			$.confirm("该类型下新闻会同时被修改状态,您确定要修改吗？",function(a){
				if(a){
					$.ajax({
						type : "post",
						url : rootPath+"/sysNewsType/deleteSysNewsType",
						data : {id : id,
								delFlag : delFlag},
						success : function(data){
							if(data.flag=="success"){
								$.msg("修改成功");
								queryNewsType();
							}
							else{
								$.msg("发生异常");
							}
						}
					});
				}else{
					return;
				}
			});
		});
		queryNewsType();
	});
function queryNewsType(){
	var schoolId=$("#schoolId").val();
	$.ajax({ 
		  type : "post", 
		  url : rootPath+"/sysNewsType/selectSysNewsType", 
		  data : {schoolId : schoolId},
		  beforeSend : function(XMLHttpRequest){
	          $(".loading").show();
	          $(".loading-bg").show();
	      },
		  success : function(data){
			  var  html='<col width="20%">'+
			            '<col width="20%">'+
			            '<col width="20%">'+
			            '<col width="20%">'+
			            '<col width="20%">'+
			            '<tr>'+
			                '<th>标题名</th>'+
			                '<th>状态</th>'+
			                '<th>创建时间</th>'+
			                '<th>创建人</th>'+
			                '<th>操作</th>'+
			            '</tr>';
			  if(data.length>0){
				  $.each(data,function(index,a){
					  html+='<tr id='+a.id+'>'+
					  		'<td class="name">'+a.name+'</td>'+
					  		'<td>'+(a.delFlag=='0'?'启用':'禁用')+'</td>'+
					  		'<td>'+a.createTime+'</td>'+
					  		'<td>'+(a.realName?a.realName:a.userName)+'</td>'+
					  		'<td>'+
					  		'<a href="javascript:void(0);" data-status="'+a.delFlag+'" id="typeStatus" class="btn btn-mini btn-primary">'+(a.delFlag=='0'?'禁用':'启用')+'</a>&nbsp;'+
				            '<a href="javascript:void(0);" id="editType" class="btn btn-mini btn-primary">修改</a>'+
					  		'</td>'+
					  		'</tr>';
				  });
			  }else{
				  html+='<tr><td colspan="5">目前还没有创建新闻类型</td></tr>';
			  }
			  $(".table").html(html);
		  },
		  complete:function(XMLHttpRequest,textStatus){
			  $(".loading").hide();
	          $(".loading-bg").hide();
	      }
	  });
}	
})(jQuery)