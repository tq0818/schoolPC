/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
(function($){
	var Form={
			init : function(){
				var $this=this;
				$selectMenu("course_class_type");
				//$(".footer").addClass("footer-fixed");
				$(".t-content").on('click','a.btn',function(){
					var _this=$(this),status= _this.hasClass('btn-success');
					if(!status){
						 _this.addClass('btn-success').siblings('a').removeClass('btn-success');
					}
				});
				var itemOneId="";
				$("#itemOneList").find("a").each(function(){
					var st=$(this).hasClass("btn-success");
					if(st){
						if($("#one").val()!=""){
							itemOneId=$("#one").val();
						}else{
							itemOneId=$(this).attr("ids");
						}
					}
				});
				this.queryItemSecond(itemOneId);

				//判断是否有上架学科(学科)
	    		$("#itemOneList").find("a").each(function(){
    				if($(this).attr("ids")==$("#one").val()){
    					$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
    				}
    			});
	    		$(".upload-layer").on("click","li",function(){
	    			if($(this).hasClass('b1')){
	    				$this.addClassType('live');
	    			}
	    			if($(this).hasClass('b2')){
	    				$this.addClassType('video');
	    			}
	    			if($(this).hasClass('b3')){
	    				$this.addClassType('face');
	    			}
	    			if($(this).hasClass('b4')){
	    				$this.addClassType('togther');
	    			}
	    			if($(this).hasClass('b5')){
	    				$this.addClassType('other');
	    			}
	    		})
	    		//判断是否显示三级标签
	    		$.ajax({
					url : rootPath + "/serviceGroup/lableSetting",
					type : "post",
					success : function(result) {
						if(result==""||result.status==0){
							$(".labeSets").addClass("none");
						}else{
							$(".labeSets").removeClass("none");
						}
					}
				});
	    		$.ajax({
					url : rootPath + "/serviceGroup/lableSeondSetting",
					type : "post",
					success : function(result) {
						if(result==""||result.status==0){
							$(".labeSecondeSets").addClass("none");
						}else{
							$(".labeSecondeSets").removeClass("none");
						}
					}
				});
			},
			queryItemSecond : function (id){
				$("#itemSecondList").html('');
				$.ajax({
					url : rootPath + "/exam/queryItemSecond",
					type : "post",
					data : {pid:id},
					dataType : "json",
					success : function(result) {
						$.each(result,function(i,item){
							if(i==0){
								$("#itemSecondList").append("<a href='javascript:Form.queryAllCommdityByItem(1,"+id+");' class='btn btn-mini btn-default btn-success'>全部</a>");
							}
							$("#itemSecondList").append("<a href='javascript:Form.queryAllCommdityByItem(1,"+id+","+item.id+");' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.itemName+"</a>");
						});
						//判断是否有上架学科(学科)
						$("#itemSecondList").find("a").each(function(){
		    				if($(this).attr("ids")==$("#two").val()){
		    					$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
		    				}
		    			});
						window.Form.querylablesList(id);
					}
				});
			},
			querylablesList : function(itemOneId,itemSecondId,change){
				if(itemOneId==null){
					$("#itemOneList").find("a.btn").each(function(){
						if($(this).hasClass("btn-success")){
							itemOneId=$(this).attr("ids");
						}
					});
				}
				if(itemSecondId==null){
					$("#itemSecondList").find("a.btn").each(function(){
						if($(this).hasClass("btn-success")){
							itemSecondId=$(this).attr("ids");
						}
					});
				}
				$("#labelLists").html('');
				$.ajax({
					url : rootPath + "/sysConfigItemTag/queryTags",
					type : "post",
					dataType : "json",
					data:{"itemOneId":itemOneId,"itemSecondId":itemSecondId,"level":1},
					success : function(result) {
						$("#labelLists").append('<a href="javascript:Form.queryAllCommdityByItemNew(1,null,null);" class="btn btn-mini btn-default btn-success">全部</a>');
						$.each(result,function(i,item){
							$("#labelLists").append("<a href='javascript:Form.queryAllCommdityByItemNew(1,null,null,null,"+item.id+");' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.tagName+"</a>");
						});
						if(change){
							
						}else{
							window.Form.queryAllCommdityByItemNew(1,itemOneId);
						}
					}
				});
				
				$("#labelSecondLists").html('');
				$.ajax({
					url : rootPath + "/sysConfigItemTag/queryTags",
					type : "post",
					dataType : "json",
					data:{"itemOneId":itemOneId,"itemSecondId":itemSecondId,"level":2},
					success : function(result) {
						$("#labelSecondLists").append('<a href="javascript:Form.queryAllCommdityByItemNew(1,null,null);" class="btn btn-mini btn-default btn-success">全部</a>');
						$.each(result,function(i,item){
							$("#labelSecondLists").append("<a href='javascript:Form.queryAllCommdityByItemNew(1,null,null,null,null,"+item.id+");' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.tagName+"</a>");
						});
						if(change){
							
						}else{
							window.Form.queryAllCommdityByItemNew(1,itemOneId);
						}
					}
				});
			},
			queryAllCommdityByItem : function(page,id,itemSecondId,status,lab,labTwo){
				var labSec="";
				if(id==null){
					$("#itemOneList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							id=cid;
						}
					});
				}
				if(itemSecondId==null){
					$("#itemSecondList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							itemSecondId=cid;
						}
					});
				}
				Form.querylablesList(null,null,"change");
				if(status==null){
					$("#statusList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							status=cid;
						}
					});
				}
				if(lab==null){
					$("#labelLists").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							lab=cid;
						}
					});
				}
				if(labTwo && labTwo!=null){
					labSec=labTwo;
				}else{
					$("#labelSecondLists").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							labSec=cid;
						}
					});
				}

				$.ajax({
					url : rootPath + "/classType/showAllclassType",
					type : "post",
					data : {"page" : page,"itemOneId" : id,"itemSecondId" : itemSecondId,"publishStatus" : status,"itemTag":lab,"itemTag2":labSec},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#commodityDetailList").html(result);
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			queryAllCommdityByItemNew : function(page,id,itemSecondId,status,lab,labTwo){
				var labSec="";
				if(id==null){
					$("#itemOneList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							id=cid;
						}
					});
				}
				if(itemSecondId==null){
					$("#itemSecondList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							itemSecondId=cid;
						}
					});
				}
				if(status==null){
					$("#statusList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							status=cid;
						}
					});
				}
				if(lab==null){
					$("#labelLists").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							lab=cid;
						}
					});
				}
				if(labTwo && labTwo!=null){
					labSec=labTwo;
				}else{
					$("#labelSecondLists").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							labSec=cid;
						}
					});
				}

				$.ajax({
					url : rootPath + "/classType/showAllclassType",
					type : "post",
					data : {"page" : page,"itemOneId" : id,"itemSecondId" : itemSecondId,"publishStatus" : status,"itemTag":lab,"itemTag2":labSec},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#commodityDetailList").html(result);
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			queryCommodityByName : function(page){
				var name=$("#classTypeName").val();
				$.ajax({
					url : rootPath + "/classType/showAllclassType",
					type : "post",
					data : {"page" : page,"name":name},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#commodityDetailList").html(result);
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			stopOnsale : function(id){
				$.confirm("您确定要下架此课程?下架后学员将无法再报名此课程。",function(a){
					if(a==true){
						$.ajax({
							url : rootPath + "/classType/StopSale",
							type : "post",
							data : {"id":id,"publishStatus":'CLASS_STOP_SALE'},
							success : function(result) {
								var st="";
								$("#statusList").find("a").each(function(i){
									if($(this).hasClass('btn-success')){
										var cid=$(this).attr("ids");
										st=cid;
									}
								});
								if(st!=""){
									//$("#commodityLi"+id).remove();
									$("#commodityLi"+id).find("i:first").text("停售").css({"background-color":"rgba(231,31,26,0.8)","color":"white"});;
									$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("上架").attr("href","javascript:Form.classTypeOnsale("+result.id+")");
								}else{
									$("#itemOneList").find("a").each(function(i){
										if(result.itemOneId==$(this).attr("ids")){
											$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
										}
									});
									$("#itemSecondList").find("a").each(function(i){
										if(result.itemOneId==$(this).attr("ids")){
											$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
										}
									});
									$("#commodityLi"+id).find("i:first").text("停售").css({"background-color":"rgba(231,31,26,0.8)","color":"white"});;
									$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("上架").attr("href","javascript:Form.classTypeOnsale("+result.id+")");
								}
								
								//alert(result);
							}
						});
					}else{
						return;
					}
				});
			},
			deleteClassType : function(id){
				$.confirm("您确定要删除此课程?",function(a){
					if(a==true){
						location.href= rootPath + "/classType/deleteClassType/"+id;
					}else{
						return;
					}
				})
			},
			classTypeOnsale : function(id){
				$.ajax({
					url : rootPath + "/classType/queryHasOnSaleNo/"+id,
					type : "post",
					data : {"id":id},
					success : function(data) {
						if(data=="success"){
							$.ajax({
								url : rootPath + "/classType/classTypeonSale",
								type : "post",
								data : {"id":id,"publishStatus":'CLASS_ON_SALE'},
								success : function(result) {
									var st="";
									$("#statusList").find("a").each(function(i){
										if($(this).hasClass('btn-success')){
											var cid=$(this).attr("ids");
											st=cid;
										}
									});
									if(st!=""){
										//$("#commodityLi"+id).remove();
										$("#commodityLi"+id).find("i:first").text("在售").css({"background-color":"rgba(194,235,235,.8)","color":"black"});
										$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("下架").attr("href","javascript:Form.stopOnsale("+result.id+")");
									}else{
										$("#itemOneList").find("a").each(function(i){
											if(result.itemOneId==$(this).attr("ids")){
												$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
											}
										});
										$("#itemSecondList").find("a").each(function(i){
											if(result.itemOneId==$(this).attr("ids")){
												$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
											}
										});
										$("#commodityLi"+id).find("i:first").text("在售").css({"background-color":"rgba(194,235,235,.8)","color":"black"});
										$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("下架").attr("href","javascript:Form.stopOnsale("+result.id+")");
									}
								}
							});
						}else{
							$.msg("此课程没有在售班号，暂时不能上架");
							return;
						}
					}
				});
			},
			showClassTypeDetail : function(id,typeCode){
				// var itemOneId="";
				// $("#itemOneCodeList").find("a").each(function(i){
				// 	if($(this).hasClass('btn-success')){
				// 		itemOneId=$(this).attr("ids");
				// 	}
				// });
				// if(itemOneId==""){
				// 	alert("请先设置学科!");
				// 	return;
				// }
				$("#classTypeId").val(id);
				$("#typeCode").val(typeCode);
				$("#myForm").attr("action",rootPath+"/classType/showClassTypeDetail").submit();
			},
			editClassType : function(id){
				var itemOneId="";
				var lab="";
				// $("#itemOneList").find("a").each(function(i){
				// 	if($(this).hasClass('btn-success')){
				// 		itemOneId=$(this).attr("ids");
				// 	}
				// });
				//得到课程所属标签
				$("#lab"+id).find("a").each(function(i){
					lab+=$(this).attr("mark")+",";
				});
				//console.log(lab);
				// if(itemOneId==""){
				// 	alert("请先设置学科!");
				// 	return;
				// }
				$("#classTypeId").val(id);
				$("#lab").val(lab);
				$("#myForm").attr("action",rootPath+"/editClass/editClassTypeMessage").submit();
			},
			show : function(){
				$("#divDisplay").css("display","block");
			},
			close : function(){
				$("#divDisplay").css("display","none");
			},
			showSave : function(id){
				$("#com"+id).css("display","block");
			},
			closeSave : function(id){
				$("#com"+id).css("display","none");
			},
			collectShop : function(id){
				var status=$("#com"+id).attr("marks");
				if(status=="0"){
					status=1;
				}else{
					status="0";
				}
				$.ajax({
					url : rootPath + "/classType/changClassTypeCollect",
					type : "post",
					data : {"id":id,"recommendFlag":status},
					success : function(result) {
						if(result.recommendFlag==1){
							$("#com"+id).text('取消推荐');
							$("#com"+id).attr("marks",1);
						}else{
							$("#com"+id).text('加入推荐');
							$("#com"+id).attr("marks",0);
						}
					}
				});
			},
			addClassType : function(mark){
				$("#lab").val(mark);
				var itemOneId="";
				// $("#itemOneList").find("a").each(function(i){
				// 	if($(this).hasClass('btn-success')){
				// 		itemOneId=$(this).attr("ids");
				// 		$("#oneId").val($(this).attr("ids"));
				// 	}
				// });
				// $("#itemSecondList").find("a").each(function(i){
				// 	if($(this).hasClass('btn-success')){
				// 		$("#twoId").val($(this).attr("ids"));
				// 	}
				// });
				// if(itemOneId==""){
				// 	alert("请先设置学科!");
				// 	return;
				// }
				var count=0;
				$("#itemSecondList").find("a.btn").each(function(i){
					if($(this).hasClass("btn-default")){
						count++;
					}
				});
				// if(count<=0){
				// 	alert("请先设置学科小类!");
				// 	return;
				// }
				$("#myForm").attr("action",rootPath+"/classType/addClassType").submit();
			}
			
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)