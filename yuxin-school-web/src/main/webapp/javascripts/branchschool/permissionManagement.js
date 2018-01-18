/**
 * author zhang.zx
 * 用户权限
 * 页面js封装
 */
(function($){
	var Form={
			condition:undefined,//用户名或者手机号或者姓名
			
			init : function(){
				var $this = this;
//				$(".footer").addClass("footer-fixed");
				//分校点击事件
				$("#schoolListP").on('click','a.btn',function(){
					var _this=$(this),
						status= _this.hasClass('btn-success');
					
					$this.condition = undefined;
//					if(status) return;
					
					_this.addClass('btn-success').siblings('a').removeClass('btn-success');
					$this.queryUserRolesList(1,_this.attr('mark'));
				});
				//搜索
				$('#search_condition').on('click',function(){
					var condition = $.trim($('#condition').val());
					var roleUid = $.trim($('#roleUid').val());
					var status = $.trim($('#status').val());
					if(!condition){
						$this.condition = undefined;
					}else{
						$this.condition = condition;
					}
					if(!roleUid){
						$this.roleUid = undefined;
					}else{
						$this.roleUid = roleUid;
					}
					if(!status){
						$this.status = undefined;
					}else{
						$this.status = status;
					}
					$this.queryUserRolesList(1,'');
				});
				$(document).keydown(function(event) {
                    if (event.keyCode == 13) {
                    	var condition = $.trim($('#condition').val());
                    	if(!condition){
    						$this.condition = undefined;
    					}else{
    						$this.condition = condition;
    					}
    					$this.queryUserRolesList(1,'');
                        return false;
                    }
                });
				
				
				var schoolId="";
				$("#schoolListP").find("a").each(function(i){
					if($(this).hasClass("btn-success")){
						schoolId=$(this).attr("mark");
					}
				})
				this.queryUserRolesList(1,schoolId);
			},
			queryUserRolesList : function(page,schoolId){
				var _this = this;
				if(schoolId==""){
					$("#schoolListP").find("a").each(function(i){
						if($(this).hasClass("btn-success")){
							schoolId=$(this).attr("mark");
						}
					});
				}
				var data = {};
				data.page = page?page:1;
				data.schoolId = schoolId;
				if(_this.condition)
					data.condition = _this.condition;
				if(_this.roleUid)
					data.roleUid = _this.roleUid;
				if(_this.status)
					data.status = _this.status;
				$.ajax({
					url : rootPath + "/permissionManger/queryUserRolesByCompanyId?companyId="+$('#companyId').val(),
					type : "post",
					data : data,
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$(".user-list").html(result);
                        $("td.status").each(function(i){
                            if($(this).text()=="禁用"){
                                $(this).css("color","red");
                            }
                        });
						var pmark=$("#peopleMark").val();
						$(".btnsList:eq(0)").find("a.btn").each(function(){
							var st=$(this).parent().siblings("td.rNameSta").find("input").val();
							if(st){
								if(pmark!="admin"){
									$(this).addClass("none");
								}else{
									$(this).removeClass("none");
								}
							}
						});
						//$("table tr:eq(1)").find("td").find("a.btn").eq(1).css("background-color","gray");
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				            // $.footerPosition({ cur: '.footer', pre: '.mainbackground' });
				     }
				});
			},
			changUserStatus : function(id){
				/*var kg=0;
				$.ajax({
					url : rootPath + "/authRole/queryRoleFlag/"+id+"/"+$('#companyId').val(),
					type : "post",
					async:false,
					success : function(result) {
						if(result){
                        kg++;
                   		 }
               		 }
					});
				if(kg>0){
					$.msg("该用户不能被禁用");
					return;
				}*/
				var status=$("#com"+id).attr("marks");
				if(status=="0"){
					status=1;
				}else{
					status="0";
				}
				$.ajax({
					url : rootPath + "/authPrivilege/changeStatus",
					type : "post",
					data : {"id":id,"status":status,"companyId":$("#companyId").val()},
					success : function(result) {
						if(result.status==1){
							$("#status"+id).text("启用").css("color","black");
							$("#com"+id).attr("marks",1).text("禁用");
						}else{
							$("#status"+id).text("禁用").css("color","red");
							$("#com"+id).attr("marks",0).text("启用");
						}
						$.msg("操作成功！");
					}
				});
			},
			editUser : function(type,id){
				$("#schoolListP").find("a.btn").each(function(i){
					if($(this).hasClass("btn-success")){
						$("#schoolIds").val($(this).attr("mark"));
					}
				});
				$("#type").val(type);
				if(type=="save"){
					$("#myForm").attr("action",rootPath+"/permissionManger/addUser").submit();
				}else{
					$("#uIds").attr("value",id);
					$("#myForm").attr("action",rootPath+"/permissionManger/addUser").submit();
				}
			},
			syncOrgUser : function(){
				$.ajax({
					url : rootPath + "/tool/createUsersByOrg",
                    dataType:'json',
                    beforeSend:function(XMLHttpRequest){
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
					success : function(result) {
						if(result){
							$.msg(result);
						}else{
							$.msg("成功！");
						}
					},complete:function(XMLHttpRequest,textStatus){
                        $(".loading").hide();
                        $(".loading-bg").hide();
                        $.footerPosition({ cur: '.footer', pre: '.mainbackground' });
                    }
				});
			},
			deleteUser : function(id){
				/*var kg=0;
				$.ajax({
					url : rootPath + "/authRole/queryRoleFlag/"+id+"/"+$('#companyId').val(),
					type : "post",
					async:false,
					success : function(result) {
						if(!result){
							kg++;
						}
					}
				});
				if(kg>0){
					$.msg("该用户不能删除");
					return;
				}*/
				$.confirm("您确定要删除吗?",function(b){
					if(b){
						var companyId=$("#companyId").val();
						$.ajax({
							url : rootPath + "/authPrivilege/deleteById",
							type : "post",
							data : {"id":id,"companyId":companyId},
							dataType : 'json',
							success : function(result) {
								if(result=="success"){
									$(".table-center").find(".deletetr"+id).remove();
									$.msg("删除成功");
									return;
								}else{
									$.msg("删除失败");
								}
							}
						});
					}
				})
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)