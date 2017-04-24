(function($){

	var Form={
			init : function(){
				var $sh=this;
				 $sh.findRolesByCompanyId(0);
		        $('.pri-list')
		            .on('click','p.c',function(){
		                var status = ['&#xe609;','&#xe60a;'],
		                    _this = $(this).find('i.iconfont');

		                !_this.hasClass('active')?
		                    _this.html(status[1]).addClass('active'):
		                    _this.html(status[0]).removeClass('active');
		                return false;
		            })
		            .on('click','p.c-title',function(){
		                var status = ['&#xe609;','&#xe60a;'],
		                    _this = $(this).find('i.iconfont');

		                !_this.hasClass('active')?
		                    _this.html(status[1]).addClass('active').parent().nextAll('p.c').find('i.iconfont').html(status[1]).addClass('active'):
		                    _this.html(status[0]).removeClass('active').parent().nextAll('p.c').find('i.iconfont').html(status[0]).removeClass('active');

		                return false;
		            });

		        $(".people-list").on('click','em.del_authRole',function(){
		        	var $this=$(this);
		        	var id=$(this).attr("ids");
		        	if(id==""){
		        		$.msg("操作异常");
		        		return;
		        	}
		        	$.confirm("您确定要删除该角色吗?",function(b){
		        		if(b){
		        			$.ajax({
								url : rootPath + "/authRole/del_privleges/"+id,
								type : "post",
								dataType : "json",
								success : function(result) {
									if("success"==result){
										$this.parent().remove();
										$.msg("操作成功");
									}else{
										$.msg("操作失败");
									}
								}
							});
		        		}
		        	});
		        	return false;
		        });
		        
		        $(".people-list").on('click','a.btn',function(){
					var _this=$(this),status= _this.hasClass('btn-success');
					if(!status){
						 _this.addClass('btn-success');
						var cid=_this.attr("ids");
						$sh.addCatgory(cid);
					}else{
						_this.removeClass('btn-success');
						$(".pri-list").find("li").find(".iconfont").html('&#xe609;');
						
						var cid=_this.attr("ids");
						if($(".people-list").find("a.btn-success").length){
							$.ajax({
								url : rootPath + "/authRolePrivilege/Category/"+cid,
								type : "post",
								dataType : "json",
								success : function(result) {
									$.each(result,function(i,tree){
										if(tree && tree.id){
											$(".clear li").find("p.c").each(function(i){
												var mark=$(this).attr("marks");
												if(tree.id==mark){
													//$(this).children("i").html("&#xe609;");
													$(this).children("i").html("&#xe609;").prev().html("&#xe609;");
												}
											});
										}
									});
									//点击取消时判断重复菜单
									$(".people-list").find("a").each(function(i){
										var status=$(this).hasClass("btn-success");
										if(status){
											var cid=$(this).attr("ids");
											$sh.addCatgory(cid);
										}
									});
								}
							});
						}
						
					}
				});
		        //根据公司id查询角色
		        $("#companyId_mark").on('change',function(){
		        	var id=$("#companyId_mark").find("option:selected").val();
		        	$sh.findRolesByCompanyId(id);
		        });
		       
			},
			addRolePrivliage : function(e){
				var $this=$(e);
				if($this.hasClass("disabled")){
					return;
				}
				$this.addClass("disabled");
				var data={};
				var privlage="";
				var name=$.trim($("#roleName").val());
				var companyId=$("#companyId_mark").find("option:selected").val();
				var roleFlag=$("#manageRoleFlag").find("input[name=roleFlag]:checked").val();
				if(name.length<=0){
					$.msg("请输入角色名称");
					$this.removeClass("disabled");
					return;
				}
				if(name.length>10){
					$.msg("角色名称不能超过10个字符");
					$this.removeClass("disabled");
					return;
				}
				if(companyId==""||isNaN(companyId)){
					$.msg("请输入正确的公司ID");
					$this.removeClass("disabled");
					return;
				}
				$.ajax({
					url : rootPath + "/authRole/queryRoleName",
					type : "post",
					dataType : "json",
					data:{"name":name,"companyId":companyId},
					success : function(result) {
						if(result=="norepet"){
							$(".pri-list").find("p.c").each(function(i){
								var privilegeId=$(this).attr("marks");
								if($(this).find('i.iconfont').hasClass("active")){
									privlage+=privilegeId+",";
								}
							});
							if(privlage.length<=0){
								$.msg("请选择角色对应的权限菜单");
								$this.removeClass("disabled");
								return;
							}
							data.name=name;
							data.privlage=privlage;
							data.companyId=companyId;
							data.roleFlag=roleFlag;
							$.ajax({
								url : rootPath + "/authRole/addRoleandMenu",
								type : "post",
								dataType : "json",
								data:data,
								success : function(result) {
									if(result){
										$.msg("操作成功");
									}else{
										$.msg("操作失败");
									}
									window.location.href=rootPath+"/authRole/configRole";
								}
							});
						}else{
							$.msg("该角色名称已存在");
							$this.removeClass("disabled");
							return;
						}
					}
				});
			},
			giveUp:function(){
				window.location.href=rootPath+"/authRole/configRole";
			},
			findRolesByCompanyId : function(id){
				$(".people-list").html("");
				$.ajax({
					url : rootPath + "/authRole/getRolesByCompanyId/"+id,
					type : "post",
					dataType : "json",
					success : function(result) {
						var html="";
						$.each(result,function(i,item){
							html+='<a href="javascript:;" ids='+item.roleUid+' class="btn btn-mini btn-default">&nbsp;&nbsp;'+item.roleName+''+
			            		'<em class="iconfont del_authRole" ids='+item.id+' style="font-size: 12px;">&#xe610;</em></a>';
						});
						$(".people-list").html(html);
					}
				});
			},
			addCatgory : function(cid){
				$.ajax({
					url : rootPath + "/authRolePrivilege/Category/"+cid,
					type : "post",
					dataType : "json",
					success : function(result) {
						$.each(result,function(i,tree){
							if(tree && tree.id){
								$(".clear li").find("p.c").each(function(i){
									var mark=$(this).attr("marks");
									if(tree.id==mark){
										//$(this).children("i").html("&#xe60a;");
										$(this).children("i").html("&#xe60a;").prev().html("&#xe60a;");
									}
								});
							}
						});
						
					}
				});
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)