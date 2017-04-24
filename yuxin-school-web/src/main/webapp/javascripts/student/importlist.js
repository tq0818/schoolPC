(function($){

	var student={
		init: function(){
			var $this=this;
			//初始化数据
			$this.search();
			//点击批量报名
			$(".signUpMany").on('click', function(){
				$(".upload-layer").show();
				$(".add-layer-bg").show();
			});
			//关闭弹框
			$(".close_choose").on('click',function(){
				$(".upload-layer").hide();
				$(".add-layer-bg").hide();
			});
			//报名
			$(".signStudents").on('click', function(){
				var mark=$(this).attr("mark");
				var umbile=$("#mobileSet").val();
            	var uName=$("#userNameSet").val();
				var list=new Array;
				if("all"==mark){
					$("#tableList").find(".signUpMany").each(function(){
						if(umbile==1){
	                		list.push($(this).val());
	                	}else{
	                		if(uName==1){
	                			list.push($(this).attr("uname"));
	                		}else{
	                			list.push($(this).val());
	                		}
	                	}
					});
					if(list.length==0){
						$.msg("请至少选择一个学员");
						return false;
					}
				}else if("part"==mark){
					var stuList=$("#stuMobiles").val();
					var list=new Array;
					$("#tableList").find(".signUpMany:checked").each(function(i){
						var $this=$(this);
						if(umbile==1){
							if(stuList && stuList.length>0){
								if(stuList.indexOf($this.val())<0){
									list.push($this.val());
								}
							}else{
								list.push($this.val());
							}
	                	}else{
	                		if(uName==1){
	                			if(stuList && stuList.length>0){
	    							if(stuList.indexOf($this.attr("uname"))<0){
	    								list.push($this.attr("uname"));
	    							}
	    						}else{
	    							list.push($this.attr("uname"));
	    						}
	                		}else{
	                			if(stuList && stuList.length>0){
	    							if(stuList.indexOf($this.val())<0){
	    								list.push($this.val());
	    							}
	    						}else{
	    							list.push($this.val());
	    						}
	                		}
	                	}
					});
				}
				
				if(list.length==0){
					$.msg("暂时没有新学员可以报名");
					return false;
				}
				var form =document.createElement("form");
				document.body.appendChild(form);
				if($("#isFull").val()==1){
					form.action=rootPath + '/student/full/showSignUpMany';
				}
				else{
					form.action=rootPath + '/student/showSignUpMany';
				}
				
				form.method="post";
				form.target="_self";
				var fileName=document.createElement("input");
				fileName.type='hidden';
				fileName.value=list;
				fileName.name='list';
				form.appendChild(fileName);
				form.submit();
				$(".upload-layer").hide();
				$(".add-layer-bg").hide();
			});
			//全选 取消全选
			$(".checkboxAll").on('change',function(){
				if ($(this).prop("checked")) {
					$("#tableList").find(".signUpMany").prop("checked",true);
				}else{
					$("#tableList").find(".signUpMany").prop("checked",false);
				}
			});
		},
		search: function(page){
			var $this=this;
			var data={};
			data.stusMobile=$("#stuMobiles").val();
			data.page=page?page:1;
		
			$.each(data,function(key,value){
				if(!value){
					delete data[key];
				}
			})
			$(".user-list").find("table").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/excelImportStudents/queryStudentsList",//student/queryStudentsList2
				data: data,
				type: 'post',
				success: function(jsonData){
					if(jsonData.length==0){
						$(".user-list").find("table").append('<tr><td colspan="7">没有查找到数据</td></tr>');
					}
					$.each(jsonData,function(i,stu){
						$(".user-list").find("table").append('<tr>'+
								'<td style="display:none;"><input type="checkbox" class="signUpMany" '+(stu.updateTime?'':'checked="checked"')+' uname="'+(stu.username?stu.username:"")+'" value="'+(stu.mobile?stu.mobile:"")+'"></td>'+
								'<td>'+(stu.mobile?stu.mobile:"")+'</td>'+
								'<td>'+(stu.name?stu.name:"")+'</td>'+
								'<td>'+(stu.identityId?stu.identityId:"")+'</td>'+
								'<td>'+(stu.createTime?stu.createTime:"")+'</td>'+
								'<td>'+ (stu.userId? '已开通': '未开通')+'</td>'+
								'<td class="ustatus">'+(stu.status == 1 ? '启用': '禁用')+'</td>'+
								'<td>'+(stu.paymaterCount>0?'已报名':'未报名')+'</td>'+
								'</tr>');
					});
//					if(jsonData.rowCount>10){
//						$(".paginations").pagination(jsonData.rowCount, {
//					    	 next_text : "下一页",
//					    	 prev_text : "上一页",
//					    	 current_page : jsonData.pageNo-1,
//					    	 link_to : "javascript:void(0)",
//					    	 num_display_entries : 8,
//					    	 items_per_page : jsonData.pageSize,
//					    	 num_edge_entries : 1,
//					    	 callback:function(page,jq){
//						    	 var pageNo = page + 1;
//						    	 $this.search(pageNo);
//					    	 }
//					   });
//					}else{
//						$(".paginations").html('');
//					}
				}
			});
		}
	}
	
	$(document).ready(function(){
		student.init();
	})
})(jQuery)