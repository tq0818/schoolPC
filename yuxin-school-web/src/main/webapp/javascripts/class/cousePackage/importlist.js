(function($){

	var student={
		init: function(){
			var $this=this;
			//初始化数据
			$this.search();
			//报名
			$(".signStudents").on('click',function(){
				var mark=$(this).attr("mark");
				var umbile=$("#mobileSet").val();
            	var uName=$("#userNameSet").val();
				if("all"==mark){
					var list=new Array;
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
					var packageId=$("#packageId").val();
					var lable=$("#lableTypes").val();
					var form =document.createElement("form");
					document.body.appendChild(form);
					form.action = rootPath + '/classPackage/insertStudents';
	                form.method = "post";
	                form.target = "_self";
	                var fileName1 = document.createElement("input");
	                fileName1.type = 'hidden';
	                fileName1.value = packageId;
	                fileName1.name = 'packageId';
	                var fileName2 = document.createElement("input");
	                fileName2.type = 'hidden';
	                fileName2.value = lable;
	                fileName2.name = 'lable';
					var fileName3=document.createElement("input");
					fileName3.type='hidden';
					fileName3.value=list;
					fileName3.name='list';
					form.appendChild(fileName1);
	                form.appendChild(fileName2);
	                form.appendChild(fileName3);
					form.submit();
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
					if(list.length==0){
						$.msg("暂时没有新学员可以报名");
						return false;
					}
					
					var classtypeId=$("#stuClassTypeId").val();
					var lable=$("#lableTypes").val();
					var form =document.createElement("form");
					document.body.appendChild(form);
					form.action = rootPath + '/classPackage/insertStudents';
	                form.method = "post";
	                form.target = "_self";
	                var fileName1 = document.createElement("input");
	                fileName1.type = 'hidden';
	                fileName1.value = packageId;
	                fileName1.name = 'packageId';
	                var fileName2 = document.createElement("input");
	                fileName2.type = 'hidden';
	                fileName2.value = lable;
	                fileName2.name = 'lable';
					var fileName3=document.createElement("input");
					fileName3.type='hidden';
					fileName3.value=list;
					fileName3.name='list';
					form.appendChild(fileName1);
	                form.appendChild(fileName2);
	                form.appendChild(fileName3);
					form.submit();
				}
				
				$(".upload-layer").hide();
				$(".add-layer-bg").hide();
			});
			//批量报名
			$(".signUpMany").on('click', function(){
				$(".upload-layer").show();
				$(".add-layer-bg").show();
			});
			//关闭弹框
			$(".close_choose").on('click',function(){
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
			data.startTime=dateTostring("yyyy-MM-dd HH:mm",new Date());
			data.stusMobile=$("#stuMobiles").val();
			data.page=page?page:1;
		
			$.each(data,function(key,value){
				if(!value){
					delete data[key];
				}
			})
			$(".user-list").find("table").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/excelImportStudents/queryStudentsList",///student/queryStudentsList2
				data: data,
				type: 'post',
				success: function(jsonData){
					if(jsonData.length==0){
						$(".user-list").find("table").append('<tr><td colspan="7">没有查找到数据</td></tr>');
					}
					$.each(jsonData,function(i,stu){
						$(".user-list").find("table").append('<tr>'+
								'<td style="display:none;"><input type="checkbox" class="signUpMany" uname="'+(stu.username?stu.username:"")+'" '+(stu.updateTime?'':'checked="checked"')+' value="'+(stu.mobile?stu.mobile:"")+'"></td>'+
								'<td>'+(stu.mobile?stu.mobile:"")+'</td>'+
								'<td>'+(stu.name?stu.name:"")+'</td>'+
								'<td>'+(stu.identityId?stu.identityId:"")+'</td>'+
								'<td>'+(stu.createTime?stu.createTime:"")+'</td>'+
								'<td>'+ (stu.userId? '已开通': '未开通')+'</td>'+
								'<td class="ustatus">'+(stu.status == 1 ? '启用': '禁用')+'</td>'+
								'<td>'+(stu.paymaterCount>0?'已报名':'未报名')+'</td>'+
								'</tr>');
					});
				}
			});
		}
	}
	
	$(document).ready(function(){
		student.init();
	})
})(jQuery)