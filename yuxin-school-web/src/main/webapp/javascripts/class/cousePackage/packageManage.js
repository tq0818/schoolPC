(function($){
	
	var getCname=function(){
		return $("#class_PackageName").val()?$("#class_PackageName").val():"课程包";
	}
	var getLname=function(){
		return $("#clss_StageName").val()?$("#clss_StageName").val():"阶段";
	}
	
	var Page={
			init : function(){
				//查询自定义的名称
				$.ajax({
					url : rootPath+"/classPackage/customClassPackage",
					type: "post",
					dataType: "json",
					success: function(data){
						$("body").append('<input type="hidden" id="class_PackageName" value="'+(data.classPackageName?data.classPackageName:"")+'"/>');
						$("body").append('<input type="hidden" id="clss_StageName" value="'+(data.classStageName?data.classStageName:"")+'"/>');
						//加载数据
						Page.queryClassPackageCourse();
					}
				});
				//初始化弹出层中的学科
				$.ajax({
					url: rootPath+"/sysConfigItem/getJsons",
					type: "post",
					dataType: "json",
					data: {"itemType":1},
					success: function(jsonData){
						$("#itemOne").html('');
						$.each(jsonData,function(i,data){
							if(i=0){
								$("#itemOne").append('<option selected="selected" value="'+data.id+'">'+data.itemName+'</option>');
							}else{
								$("#itemOne").append('<option value="'+data.id+'">'+data.itemName+'</option>');
							}
						});
						Page.queryItemSecond();
					}
				});
				
				$(".prices").bind("keyup",function(event){
	    			//先把非数字的都替换掉，除了数字和. 
	    			$(this).val($(this).val().replace(/[^\d.]/g,""));
	    	        //必须保证第一个为数字而不是. 
	    	        $(this).val($(this).val().replace(/^\./g,"0."));
	    	        //保证只有出现一个.而没有多个. 
	    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	    	        //保证.只出现一次，而不能出现两次以上
	    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
	    		})
	    		$(".prices").bind("blur",function(event){
	    			//先把非数字的都替换掉，除了数字和. 
	    			$(this).val($(this).val().replace(/[^\d.]/g,""));
	    			var value=$(this).val();
	     	        var is=false;
	     	        for (var i = 0; i < value.length; i++) {
	     	            var  item =  value.charAt(i);
	     	           	if("."==item){
	     	           		is=true;
	     	           	}
	     	        }
	     	        if(value!=null||value!=""){
	     	        	if(!is){
	     	        		$(this).val($(this).val()+".00");	
	     	        	}
	     	        } 
	    	        //必须保证第一个为数字而不是. 
	    	        $(this).val($(this).val().replace(/^\./g,"0."));
	    	        //保证只有出现一个.而没有多个. 
	    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	    	        //保证.只出现一次，而不能出现两次以上
	    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
	    	        //保留小数点后两位
	    	        $(this).val($(this).val().substring(0,$(this).val().indexOf(".")+3));
	    		})
				
				//点击查询课程
				$("#searchCourse").on('click',function(){
					Page.searchClassType();
				});
				//初始化课程
				//Page.searchClassType();
				//点击显示添加阶段弹框
				 $(".add-grade-btn").on("click",function(){
					 Page.clearData();
					 $("#updateFlag").val("");
					 $("#addMarktitle").text("添加阶段");
					 $.ajax({
							url: rootPath+"/classPackage/searchCourse",
							type: "post",
							dataType: "json",
							data: {"id":$("#classPackageId").val()},
							success: function(jsonData){
								if(jsonData.length>0){
									$("#updateFlag").val("up");
									$(".add-grade-pop").fadeIn(200);
									//$.msg("移除全部课程后才能添加阶段");
								}else{
									$(".add-grade-pop").fadeIn(200);
								}
							}
					  });	
			     });
				//显示添加课程弹框
				$(".choice-class-btn").on("click",function(){
					$("#chooseCourseIds").html("");
					Page.searchClassType();
					var id=$("#sortable").find(".par:last").attr("ids");
					if(id){
						$(".add_stage_course").attr("stageId",id);
					}
		            $(".choice-class-pop").fadeIn(200);
		        });
				//点击添加阶段
				$(".manageStage").on('click',function(){
					var $this=$(this);
					if($this.hasClass("disabled")){
						return;
					}
					$this.addClass("disabled");
					var title=$("#stage_title").val();
					var desc=$("#stage_description").val();
					var status=$(".tit-font").find("em.normal").hasClass("close");
					var stage_price=$("#stage_price").val();
					if(!status){
						$("#publishStatus").val("ALLOW_SALE");
					}else{
						$("#publishStatus").val("NOT_ALLOW_SALE");
					}
					var sort=$("#sortable").find(".ui-state-parent").length+1;
					$("#stage_sort").val(sort);
					if(title=="" || title.length<=0){
						$.msg("阶段名称不能为空");
						$this.removeClass("disabled");
						return;
					}
					if(title.length>16){
						$.msg("阶段名称最多不能超过16个字符");
						$this.removeClass("disabled");
						return;
					}
					if(desc.length>120){
						$.msg("阶段描述最多不能超过120个字符");
						$this.removeClass("disabled");
						return;
					}
					if(stage_price!=""){
						if(stage_price>999999){
							$.msg("阶段价格最大不能超过999999");
							$this.removeClass("disabled");
							return;
						}
					}
					$.ajax({
						url: rootPath+"/classPackageStage/checkName",
						type: "post",
						dataType: "json",
						data: {"title":title,"id":$("#stage_id").val(),"packageId":$("#classPackageId").val()},
						success: function(flag){
							if(flag){
								$.ajax({
									url: rootPath+"/classPackageStage/addStage",
									type: "post",
									dataType: "json",
									data: $("#stageForm").serialize(),
									success: function(jsonData){
										if("success"==jsonData){
											Page.queryClassPackageCourse();
											$(".add-grade-pop").fadeOut(200);
											$.msg("操作成功");
											$this.removeClass("disabled");
										}else{
											$.msg(jsonData);
											$this.removeClass("disabled");
										}
									}
								});
							}else{
								$.msg("该阶段名称已存在");
								$this.removeClass("disabled");
							}
						}
					});
				});
				//操作
				//添加课程
				$(document).on('click.addcourse','.par .addcourse',function(){
					$("#chooseCourseIds").html("");
					Page.searchClassType();
					var id=$(this).parents(".par").attr("ids");
					$(".add_stage_course").attr("stageId",id);
					$(".choice-class-pop").fadeIn(200);
				})//编辑阶段
				.on('click.editstage','.par .editstage',function(){
					Page.clearData();
					var id=$(this).parents(".par").attr("ids");
					$("#addMarktitle").text("编辑阶段");
					$.ajax({
						url: rootPath+"/classPackageStage/"+id,
						type: "get",
						dataType: "json",
						success: function(jsonData){
							$("#stage_id").val(jsonData.id);
							$("#stage_title").val(jsonData.title);
							$("#stage_price").val(jsonData.realPrice);
							$("#stage_description").val(jsonData.description?jsonData.description:"");
							if(jsonData.publishStatus && jsonData.publishStatus=="NOT_ALLOW_SALE"){
								$(".tit-font").find("em.normal").addClass("close").removeClass("open").html("&#xe604;");
								$(".tit-font").find(".i").removeClass("open").addClass("close").text("不允许");
								$(".setstagePrice").css("display","none");
							}else{
								$(".tit-font").find("em.normal").addClass("open").removeClass("close").html("&#xe603;");
								$(".tit-font").find(".i").removeClass("close").addClass("open").text("允许");
								$(".setstagePrice").css("display","block");
							}
							$(".add-grade-pop").fadeIn(200);
						}
					});
				})//删除阶段
				.on('click.delstage','.par .delstage',function(){
					var id=$(this).parents(".par").attr("ids");
					var con=$(".stageCourse_list"+id).html();
					if(con && con!=""){
						$.msg("阶段下还存在课程，移除课程后删除阶段");
						return;
					}
					$.confirm("您确认要删除此阶段吗?",function(b){
						if(b){
							$.ajax({
								url: rootPath+"/classPackageStage/delStage",
								type: "post",
								dataType: "json",
								data: {"stageId":id},
								success: function(jsonData){
									Page.queryClassPackageCourse();
								}
							});
						}
					});
				})//为选择课程添加点击事件
				.on('click.pop-course','.pop-course',function(){
					if($(this).hasClass("active")){
						return;
					}
					var ids=$(this).attr("ids");
					if($(this).hasClass("chooseds")){
						$("#chooseCourseIds").find(".icId"+ids).remove();
						var html='';
						$(this).removeClass("chooseds").find(".courselable").removeClass("chooseds").html('');
					}else{
						$("#chooseCourseIds").append('<span class="listIds icId'+ids+'" ids='+ids+'></span>');
						var html='<span>已选择<i class="iconfont">&#xe68f;</i></span>';
						$(this).addClass("chooseds").find(".courselable").addClass("chooseds").html(html);
					}
							
				})//选择课程
				.on('click.add_stage_course','.add_stage_course',function(){
					var $this=$(this);
					if($this.hasClass("disabled")){
						return;
					}
					$this.addClass("disabled");
					var courses=new Array();
					var stageId=$(this).attr("stageId");
					var classPackageId=$("#classPackageId").val();
					if(stageId){
						var sortId=$(".stageCourse_list"+stageId).find(".course-manage").length;
						$("#chooseCourseIds").find(".listIds").each(function(i){
							var cId=$(this).attr("ids");
							if(cId){
								var data={};
								var classTypeId=$(this).attr("ids");
								data.classTypeId=classTypeId;
								data.classPackageId=classPackageId;
								data.classPackageStageId=stageId;
								if(sortId){
									sortId=sortId+1;
								}else{
									sortId=1;
								}
								data.sort=sortId;
								courses.push(data);
							}
						});
						if(courses.length<=0){
							$.msg("请至少选择一个课程");
							$this.removeClass("disabled");
							return;
						}
						$.ajax({
							url: rootPath+"/classPackageRelation/addCourse",
							type: "post",
							dataType: "json",
							data: {"classPackageId":classPackageId,"list":JSON.stringify(courses)},
							success: function(jsonData){
								Page.queryClassPackageCourse();
								$(".add_stage_course").removeAttr("stageId");
								$(".choice-class-pop").fadeOut(200);
								$this.removeClass("disabled");
							}
						});
					}else{
						var sortId=$("#sortable").find(".signle_course-manage").length;
						$("#chooseCourseIds").find(".listIds").each(function(i){
							var cId=$(this).attr("ids");
							if(cId){
								var data={};
								var classTypeId=$(this).attr("ids");
								data.classTypeId=classTypeId;
								data.classPackageId=classPackageId;
								data.classPackageStageId=stageId;
								if(sortId){
									sortId=sortId+1;
								}else{
									sortId=1;
								}
								data.sort=sortId;
								courses.push(data);
							}
						});	
						$.ajax({
							url: rootPath+"/classPackageRelation/addCourse",
							type: "post",
							dataType: "json",
							data: {"classPackageId":classPackageId,"list":JSON.stringify(courses)},
							success: function(jsonData){
								Page.queryClassPackageCourse();
								$(".add_stage_course").removeAttr("stageId");
								$(".choice-class-pop").fadeOut(200);
								$this.removeClass("disabled");
							}
						});
					}
				})//移除课程
				.on('click.delCourse','.course-manage .delCourse',function(){
					var relationId=$(this).attr("ids");
					$.confirm("移除后无法恢复，您确认要移除课程数据？",function(b){
						if(b){
							$.ajax({
								url: rootPath+"/classPackageRelation/removeCourse",
								type: "post",
								dataType: "json",
								data: {"id":relationId},
								success: function(jsonData){
									if("success"==jsonData){
										$.msg("操作成功");
										Page.queryClassPackageCourse();
									}
								}
							});
						}
					});
					
				});
				//点击保存
				$(".save_baseInfo").on('click',function(){
					var mark=$(this).attr("mark");
					if("continue"==mark){
						window.location.href=rootPath+"/classPackage/setDetail/"+$("#classPackageId").val()+"/edit";
					}else{
						$.msg("保存信息成功");
					}
				})
			},
			queryClassPackageCourse : function(){
				$("#sortable").html("");
				var num1=0,num2=0;
				var price1=0,price2=0;
				var classPackageId=$("#classPackageId").val();
				if(classPackageId){
					//加载独立于阶段外的课
					$.ajax({
						url: rootPath+"/classPackage/searchCourse",
						type: "post",
						dataType: "json",
						data: {"id":classPackageId},
						beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				        },
						success: function(jsonData){
							if(jsonData.length>0){
								num1=jsonData.length;
								var courseHtml="";
								$.each(jsonData,function(i,data){
									price1+=(data.realPrice?data.realPrice:0);
									 var typeflag="";
									  if(data.liveFlag==1&&data.videoFlag==0&&data.faceFlag==0){
										  typeflag='<span class="play-status blue-bg">直播</span>';
									  }else if(data.videoFlag==1&&data.faceFlag==0&&data.liveFlag==0){
										  typeflag='<span class="play-status yellow-bg">录播</span>';
									  }else if(data.faceFlag==1&&data.liveFlag==0&&data.videoFlag==0){
										  typeflag='<span class="play-status blue-bg">面授</span>';
									  }else if(data.faceFlag==0&&data.liveFlag==0&&data.videoFlag==0){
										  typeflag='<span class="play-status blue-bg">其他</span>';
									  }else{
										  typeflag='<span class="play-status red-bg">混合</span>';
									  }
									  var ht="";
									  if(data.validityDay && data.validityDay>0){
										  ht='| <i>有效期'+(data.validityDay?data.validityDay:0)+'天</i>';
									  }
									 courseHtml+='<div class="ui-state-default course-manage signle_course-manage" cid='+data.id+' ids='+data.relationId+'>'+
		                               '<img src="'+(data.cover?data.cover:(rootPath+'/images/overview_demo.jpg'))+'" alt="" width="19%" height="100px"/>'+
		                               '<div class="course-manage-cont">'+
		                                 '<div class="iconfont" title="可拖拽">&#xe630;</div>'+
		                                 '<div class="course-manage-title" style="line-height: 30px">'+
		                                     '<span>'+data.name+'</span>'+
		                                     '<em>'+(data.realPrice?($.formatMoney(data.realPrice)):"0.00")+'元</em>'+
		                                     '<i>'+(data.isSale==0?'未上架':'已上架')+'</i>'+
		                                 '</div>'+
		                                 '<div class="course-manage-bottom">'+
		                                   typeflag+
		                                     '<span class="remove-btn delCourse" cid='+data.id+' ids='+data.relationId+'>移除课程</span>'+
		                                 '<span class="number-box">'+
		                                     '<i>'+(data.actualNum?data.actualNum:0)+'学员</i> | <i>'+(data.totalClass?data.totalClass:0)+'课时</i> '+ht+''+
		                                 '</span>'+
		                                 '</div>'+
		                             '</div>'+
		                         '</div>';
								});
								$("#sortable").append(courseHtml);
								
								$( "#sortable" ).sortable({
						            placeholder: "ui-state-highlight",
						            update:function(event,ui){
										var sortMap=new Array();
										$("#sortable").find(".signle_course-manage").each(function(i){
											var course={};
											course.id=$(this).attr("ids");
											course.sort=$(this).index()+1;
											sortMap.push(course);
										})
										$.ajax({
											url: rootPath+"/classPackageRelation/sortCourse",
											data: "list="+JSON.stringify(sortMap),
											type: "post",
											dataType: "json",
											success:function(jsonData){
												
											}
										 });
									},
									delay: 200
						      }).disableSelection();
								
								$("#sortable").find(".course-manage").each(function(){
									if(!$(this).hasClass("ui-sortable-handle")){
										$(this).addClass("ui-sortable-handle");
									}
								});
								 //总课程数
								$.ajax({
									url : rootPath+"/classPackage/getCourseNum",
									type : "post",
									data : {"id":$("#classPackageId").val()},
									dataType : "json",
									success : function(result) {
										$("#totalCourseNum").text(result);
										$("#totalCourseNums").text(result?result:0);
									}
								});
								//课程包原价
								$("#totalCoursePrice").text($.formatMoney(price1+price2));
								$("#titldOrgingPrice").text($.formatMoney(price1+price2));
								//更新课程包信息
								var data={};
								data.id=$("#classPackageId").val();
								data.protocolId = $('#protocolId').val();
								data.originalPrice=$.MoneyToNum(price1+price2);
								if(data.originalPrice && data.originalPrice!=""){
									Page.updateClassPackageInfo(data);
								}
							}else{
								$("#totalCoursePrice").text($.formatMoney(0));
								$("#titldOrgingPrice").text($.formatMoney(0));
								var data={};
								data.id=$("#classPackageId").val();
								data.protocolId = $('#protocolId').val();
								data.originalPrice=0.00;
								Page.updateClassPackageInfo(data);
							}
						},
						 complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					     }
				    });	
					//加载阶段
					$.ajax({
						url: rootPath+"/classPackageStage/queryClassPackageStages",
						type: "post",
						dataType: "json",
						data: {"classPackageId":classPackageId},
						beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				        },
						success: function(jsonData){
						  var html='<div class="ui-state-disabled"></div>';
						  $.each(jsonData,function(i,data){
							 html+='<div class="ui-state-parent par" ids='+data.id+'>'+
			                            '<div class="ui-state-default">'+
			                                '<span class="iconfont" title="可拖拽">&#xe630;</span>'+
			                                '<span style="padding-right: 20px;max-width: 120px;overflow: hidden">第'+$.changeNum(i+1)+getLname()+'</span>'+
			                                '<span style="padding-right: 20px;max-width: 300px;overflow: hidden">'+data.title+'</span>'+
			                                '<span style="padding-right: 10px;max-width: 120px;overflow: hidden;color: #666;font-size:12px">共<s class="stagecourse'+data.id+'">0</s>课程</span>'+
			                                '<span style="padding-right: 10px;max-width: 120px;overflow: hidden;font-size: 12px;color: #FE5151;">'+(data.publishStatus=='NOT_ALLOW_SALE'?"":(data.realPrice?($.formatMoney(data.realPrice)):"0.00")+'元')+'</span>'+
			                                '<em class="iconfont open-btn">&#xe681;</em>'+
			                                '<em class="iconfont delstage">&#xe626;</em>'+
			                                '<em class="iconfont editstage">&#xe628;</em>'+
			                                '<em class="iconfont addcourse">&#xe61c;</em>'+
		                             '</div>'+
		                             '<div class="open-box">'+
		                             	'<div class="'+(data.description?'sec-title':"")+'">'+(data.description?('<b>[阶段描述]</b>'+data.description):"")+'</div>'+
		                             	'<div class="sortable-sec droptrue son stageCourse_list'+data.id+'" style="min-height:5px;" stageId='+data.id+'></div>'+
		                             '</div>'+
		                       '</div>';
						  });
						  $("#sortable").append(html);
						  //加载阶段下的课程
						  $("#sortable").find(".sortable-sec").each(function(i){
							  var stagePrice=0;
							  var stageId=$(this).attr("stageId");
							  $.ajax({
									url: rootPath+"/classPackage/searchCourse",
									type: "post",
									dataType: "json",
									data: {"id":classPackageId,"stageId":stageId},
									success: function(jsonData){
										if(jsonData.length>0){
											num2=jsonData.length;
											$(".stagecourse"+stageId).text(jsonData.length);
										}
										var courseHtml="";
										$.each(jsonData,function(i,data){
											price2+=(data.realPrice?data.realPrice:0);
											stagePrice+=(data.realPrice?data.realPrice:0);
											 var typeflag="";
											  if(data.liveFlag==1&&data.videoFlag==0&&data.faceFlag==0){
												  typeflag='<span class="play-status blue-bg">直播</span>';
											  }else if(data.videoFlag==1&&data.faceFlag==0&&data.liveFlag==0){
												  typeflag='<span class="play-status yellow-bg">录播</span>';
											  }else if(data.faceFlag==1&&data.liveFlag==0&&data.videoFlag==0){
												  typeflag='<span class="play-status blue-bg">面授</span>';
											  }else if(data.faceFlag==0&&data.liveFlag==0&&data.videoFlag==0){
												  typeflag='<span class="play-status blue-bg">其他</span>';
											  }else{
												  typeflag='<span class="play-status red-bg">混合</span>';
											  }
											  var ht="";
											  if(data.validityDay && data.validityDay>0){
												  ht='| <i>有效期'+(data.validityDay?data.validityDay:0)+'天</i>';
											  }
											 courseHtml+='<div class="ui-state-default course-manage" cid='+data.id+' ids='+data.relationId+'>'+
				                               '<img src="'+(data.cover?data.cover:(rootPath+'/images/overview_demo.jpg'))+'" alt="" width="19%" height="100px"/>'+
				                               '<div class="course-manage-cont">'+
				                                 '<div class="iconfont" title="可拖拽">&#xe630;</div>'+
				                                 '<div class="course-manage-title" style="line-height: 30px">'+
				                                     '<span>'+data.name+'</span>'+
				                                     '<em>'+(data.realPrice?($.formatMoney(data.realPrice)):"0.00")+'元</em>'+
				                                     '<i>'+(data.isSale==0?'未上架':'已上架')+'</i>'+
				                                 '</div>'+
				                                 '<div class="course-manage-bottom">'+
				                                   typeflag+
				                                     '<span class="remove-btn delCourse" cid='+data.id+' ids='+data.relationId+'>移除课程</span>'+
				                                 '<span class="number-box">'+
				                                     '<i>'+(data.actualNum?data.actualNum:0)+'学员</i> | <i>'+(data.totalClass?data.totalClass:0)+'课时</i> '+ht+''+
				                                 '</span>'+
				                                 '</div>'+
				                             '</div>'+
				                         '</div>';
										});
										$("#sortable").find(".stageCourse_list"+stageId).append(courseHtml);
										$("#sortable").find(".course-manage").each(function(){
											if(!$(this).hasClass("ui-sortable-handle")){
												$(this).addClass("ui-sortable-handle");
											}
										});
										 $( "#sortable" ).sortable({
									            placeholder: "ui-state-highlight",
									            update:function(event,ui){
													var sortMap=new Array();
													$("#sortable").find(".ui-state-parent").each(function(i){
														var stage={};
														stage.id=$(this).attr("ids");
														stage.sort=$(this).index()+1;
														sortMap.push(stage);
													})
													$.ajax({
														url: rootPath+"/classPackageStage/orderStage",
														data: "list="+JSON.stringify(sortMap),
														type: "post",
														dataType: "json",
														success:function(jsonData){
															Page.queryClassPackageCourse();
														}
													 });
												},
												delay: 200,
												start: function(event,ui){
													$(".ui-state-parent").find(".open-btn").html("&#xe67f;").addClass("hide");
													$(".ui-state-parent").find(".open-box").slideUp(200);
												}
									      }).disableSelection();

									        $( "div.droptrue" ).sortable({
									        	placeholder: "ui-state-highlight",
									        	update:function(event,ui){
									        	   var stageId=$(event.target).parents(".par").attr("ids");
									        	   var sortMap=new Array();
												   $(".droptrue").find(".course-manage").each(function(i){
														var course={};
														course.id=$(this).attr("ids");
														course.sort=$(this).index()+1;
														sortMap.push(course);
													})
													$.ajax({
														url: rootPath+"/classPackageRelation/sortCourse",
														data: "list="+JSON.stringify(sortMap),
														type: "post",
														dataType: "json",
														success:function(jsonData){
															
														}
													 });
												},
												receive:function(event,ui){
													var data={};
													data.classPackageStageId=$(event.target).parents(".par").attr("ids");
													data.id=ui.item.attr("ids");
													data.classTypeId=ui.item.attr("cid");
													$.ajax({
														url: rootPath+"/classPackageRelation/mvcourse_toStage",
														data:data,
														type: "post",
														dataType: "json",
														success: function(json){
															
														}
													})
												},
												delay: 200,
									            connectWith: ".son"
									        }).disableSelection();
									        //阶段总价
									        var stagTotalPrice=$.MoneyToNum(stagePrice);
									        //总课程数
											$.ajax({
												url : rootPath+"/classPackage/getCourseNum",
												type : "post",
												data : {"id":$("#classPackageId").val()},
												dataType : "json",
												success : function(result) {
													$("#totalCourseNum").text(result);
													$("#totalCourseNums").text(result?result:0);
												}
											});
											//课程包原价
											$("#totalCoursePrice").text($.formatMoney(price1+price2));
											$("#titldOrgingPrice").text($.formatMoney(price1+price2));
											//更新课程包信息
											var data={},data1={};
											data.id=$("#classPackageId").val();
											data.protocolId = $('#protocolId').val();
											data.originalPrice=$.MoneyToNum(price1+price2);
											if(data.originalPrice && data.originalPrice!=""){
												Page.updateClassPackageInfo(data);
											}
											//更新课程阶段信息
											data1.id=stageId;
											data1.originalPrice=stagTotalPrice;
											if(data1.originalPrice && data1.originalPrice!=""){
												Page.updateStageInfo(data1);
											}else{
												data1.originalPrice="0.00";
												Page.updateStageInfo(data1);
											}
									}
							  });		
						  });
						 
						},
						 complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					     }
					});
				}
			},
			queryItemSecond : function(){
				var itemOneId=0;
				var oneValue=$("#itemOne").val();
				if(oneValue){
					itemOneId=oneValue;
				}
				$.ajax({
					url: rootPath+"/sysConfigItem/getJsons",
					type: "post",
					dataType: "json",
					data: {"itemType":2,"parentId":itemOneId},
					success: function(jsonData){
						$("#itemTwo").html('');
						$.each(jsonData,function(i,data){
							$("#itemTwo").append('<option value="'+data.id+'">'+data.itemName+'</option>');
						});
					}
				})
			},
			searchClassType : function(page){
				$("#classLists").html("");
				if(page){
					page=page;
				}else{
					page=0;
				}
				var itemOneId=0,itemSecondId=0;
				itemOneId=$("#itemOne").val();
				itemSecondId=$("#itemTwo").val();
				var searchName=$("#searchName").val();
				$.ajax({
					url: rootPath+"/classType/queryClassDataList",
					type: "post",
					dataType: "json",
					data: {"page":page,"itemOneId":itemOneId,"itemSecondId":itemSecondId,"searchName":searchName,"classPackageId":$("#classPackageId").val()},
					success: function(jsonData){
						var html="";
						$.each(jsonData.data,function(i,data){
							  var typeflag="";
							  if(data.liveFlag==1&&data.videoFlag==0&&data.faceFlag==0){
								  typeflag='<span class="play-status blue-bg">直播</span>';
							  }else if(data.videoFlag==1&&data.faceFlag==0&&data.liveFlag==0){
								  typeflag='<span class="play-status yellow-bg">录播</span>';
							  }else if(data.faceFlag==1&&data.liveFlag==0&&data.videoFlag==0){
								  typeflag='<span class="play-status blue-bg">面授</span>';
							  }else if(data.faceFlag==0&&data.liveFlag==0&&data.videoFlag==0){
								  typeflag='<span class="play-status blue-bg">其他</span>';
							  }else{
								  typeflag='<span class="play-status red-bg">混合</span>';
							  }
							  var markhtml="";
							  if(data.classPackageCourseId==1){
								  markhtml='<span>已选择<i class="iconfont">&#xe68f;</i></span>';
							  }
							  var ht="";
							  if(data.validityDay && data.validityDay>0){
								  ht='| <i>有效期'+(data.validityDay?data.validityDay:0)+'天</i>';
							  }
							  html+='<div class="pop-course '+(data.classPackageCourseId==1?'chooseds':'')+' '+(data.classPackageCourseId==1?'active':'')+'" ids='+data.id+'>'+
			                        '<img src="'+(data.cover?data.cover:(rootPath+'/images/overview_demo.jpg'))+'" alt="" width="19%" height="100px"/>'+
			                        '<div class="course-manage-cont">'+
			                        	'<div class="top-right-tag courselable '+(data.classPackageCourseId==1?'chooseds':'')+'">'+
				                        	markhtml+
				                        '</div>'+
			                        '<div class="course-manage-title" style="line-height: 30px">'+
					                    '<span>'+data.name+'</span>'+
					                    '<em>'+(data.realPrice?($.formatMoney(data.realPrice)):"0.00")+'元</em>'+
					                '</div>'+
					                '<div class="course-manage-bottom">'+
					                  typeflag+
					                    '<span class="number-box">'+
					                    '<i>'+(data.actualNum?data.actualNum:0)+'学员</i> | <i><span class="t_courseNum'+data.id+'">'+(data.totalClass?data.totalClass:0)+'</span>课时</i> '+ht+''+ 
					                    '</span>'+
				                    '</div>'+ 
		                    '</div>'+
		                    (data.classPackageCourseId==1?'<div class="mask"></div>':'')+
		                 '</div>';
						});
						$("#classLists").append(html);
						if(jsonData.rowCount>3){
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
							    	 Page.searchClassType(pageNo);
						    	 }
						   });
						}else{
							$(".pagination").html('');
						}
						$("#classLists").find(".pop-course").each(function(i){
							var $this=$(this);
							var id=$(this).attr("ids");
							$.ajax({
								url : rootPath+"/classPackage/getLessonNum",
								type : "post",
								data : {"classTypeId":id},
								dataType : "json",
								success : function(result) {
									$this.find(".t_courseNum"+id).text(result);
								}
							});
						});
						if(jsonData.data.length==0){
							$("#classLists").html('<div class="no-content-prompt">没有你要查找的课程，您可以去<a href="'+rootPath+'/classPackage/addClassType" target="_blank">创建课程</a></div>');
							$(".bottomButtom").css("display","none");
						}else{
							$(".bottomButtom").css("display","block");
						}
					}
				})
			},
			updateClassPackageInfo : function(data){
				$.ajax({
					url : rootPath+"/classPackage/updateClassPackageInfo",
					type : "post",
					data : data,
					dataType : "json",
					success : function(result) {
						
					}
				});
			},
			updateStageInfo : function(data){
				$.ajax({
					url : rootPath+"/classPackageStage/upStagePrice",
					type : "post",
					data : data,
					dataType : "json",
					success : function(result) {
						
					}
				});
			},
			clearData : function(){
				$("#stage_id").val("");
				$("#stage_title").val("");
				$("#stage_price").val("");
				$("#stage_description").val("");
				$(".tit-font").find("em.normal").addClass("close").removeClass("open").html("&#xe604;");
				$(".tit-font").find(".i").removeClass("open").addClass("close").text("不允许");
				$(".setstagePrice").css("display","none");
			}
		}
	$(document).ready(function(){
		Page.init();
	})
	window.Page=Page;
})(jQuery)