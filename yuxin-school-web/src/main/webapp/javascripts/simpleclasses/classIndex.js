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
                var itemOneCode="";
				$("#itemOneCodeList").find("a").each(function(){
					var st=$(this).hasClass("btn-success");
					if(st){
							itemOneId=$(this).attr("ids");
                            itemOneCode =$(this).attr("data-code");
						}
				});
                $("#itemFourthCodeList").off().delegate("a","click",function(){
                    $(this).toggleClass("btn-success");
                });
				//this.queryItemSecond(itemOneCode,itemOneId);
				
				//判断是否有上架学科(学科)
	    		$("#itemOneCodeList").find("a").each(function(){
    				if($(this).attr("data-code")==$("#one").val()){
    					$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
    				}
    			});
                $("#itemSecondCodeList").find("a").each(function(){
                    if($(this).attr("data-code")==$("#tow").val()){
                        $(this).addClass("btn-success").siblings('a').removeClass('btn-success');
                    }
                });
                $("#itemThirdCodeList").find("a").each(function(){
                    if($(this).attr("data-code")==$("#three").val()){
                        $(this).addClass("btn-success").siblings('a').removeClass('btn-success');
                    }
                });
                $("#itemFouthCodeList").find("a").each(function(){
                    if($(this).attr("data-code")==$("#four").val()){
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
                this.queryAllCommdityByItemNew(1);
	    		//判断是否显示三级标签
	    		// $.ajax({
					// url : rootPath + "/serviceGroup/lableSetting",
					// type : "post",
					// success : function(result) {
					// 	if(result==""||result.status==0){
					// 		$(".labeSets").addClass("none");
					// 	}else{
					// 		$(".labeSets").removeClass("none");
					// 	}
					// }
                // });
                // $.ajax({
					// url : rootPath + "/serviceGroup/lableSeondSetting",
					// type : "post",
					// success : function(result) {
					// 	if(result==""||result.status==0){
					// 		$(".labeSecondeSets").addClass("none");
					// 	}else{
					// 		$(".labeSecondeSets").removeClass("none");
					// 	}
					// }
                // });
			},
			queryItemSecond : function (code,id){
				if(id==null){
                    $("#itemOneCodeList").find("a").each(function(){
                        var st=$(this).hasClass("btn-success");
                        if(st){
                            id=$(this).attr("data-id");
                            code=$(this).attr("value");
                        }
                    });
                }
                $("#itemSecondCodeList").html('');
                $("#itemSecondCodeList").append("<a href='javascript:Form.queryAllCommdityByItem(1,"+code+");' value='all' class='btn btn-mini btn-default btn-success'>全部</a>");
                $.ajax({
                    url : rootPath + "/itemTree/queryItemSecond",
                    type : "post",
                    data : {pid:id},
                    dataType : "json",
                	success : function(result) {
                        $.each(result,function(i,item){
                                $("#itemSecondCodeList").append("<a href='javascript:Form.queryAllCommdityByItem(1,"+code+","+item.itemCode+");' data-code='"+item.itemCode+"' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.itemName+"</a>");
                        });
						//判断是否有上架学科(学科)
						$("#itemSecondCodeList").find("a").each(function(){
		    				if($(this).attr("data-code")==$("#two").val()){
		    					$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
		    				}
		    			});
                       // this.queryItemThird();
						// window.Form.queryAllCommdityByItemNew(1,code);

						// window.Form.querylablesList(id);
					}
				});
			},
        // queryItemThird : function (code,id){
        //     $("#itemThirdCodeList").html('');
        //     $.ajax({
        //         url : rootPath + "/itemTree/queryItemSecond",
        //         type : "post",
        //         data : {pid:id},
        //         dataType : "json",
        //         success : function(result) {
        //             $("#itemThirdCodeList").append("<a href='javascript:Form.queryAllCommdityByItem(1,"+code+");' class='btn btn-mini btn-default btn-success'>全部</a>");
        //             $.each(result,function(i,item){
        //                 $("#itemThirdCodeList").append("<a href='javascript:Form.queryAllCommdityByItem(1,"+code+","+item.itemCode+");' data-code='"+item.itemCode+"' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.itemName+"</a>");
        //             });
        //             //判断是否有上架学科(学科)
        //             $("#itemThirdCodeList").find("a").each(function(){
        //                 if($(this).attr("data-code")==$("#two").val()){
        //                     $(this).addClass("btn-success").siblings('a').removeClass('btn-success');
        //                 }
        //             });
        //             window.Form.queryAllCommdityByItemNew(1,code);
        //
        //             // window.Form.querylablesList(id);
        //         }
        //     });
        // },
// 			querylablesList : function(itemOneId,itemSecondId,change){
// 				if(itemOneId==null){
// 					$("#itemOneList").find("a.btn").each(function(){
// 						if($(this).hasClass("btn-success")){
// 							itemOneId=$(this).attr("ids");
// 						}
// 					});
// 				}
// 				if(itemSecondId==null){
// 					$("#itemSecondList").find("a.btn").each(function(){
// 						if($(this).hasClass("btn-success")){
// 							itemSecondId=$(this).attr("ids");
// 						}
// 					});
// 				}
// 				$("#labelLists").html('<a href="javascript:Form.queryAllCommdityByItemNew(1,null,null);" class="btn btn-mini btn-default btn-success">全部</a>');
// 				$("#labelSecondLists").html('<a href="javascript:Form.queryAllCommdityByItemNew(1,null,null);" class="btn btn-mini btn-default btn-success">全部</a>');
// 				if(!itemSecondId){
// 					return;
// 				}
//
// 				$.ajax({
// 					url : rootPath + "/sysConfigItemTag/queryTags",
// 					type : "post",
// 					dataType : "json",
// 					data:{"itemOneId":itemOneId,"itemSecondId":itemSecondId,"level":1},
// 					success : function(result) {
// 						$("#labelLists").html('');
// 						$("#labelLists").append('<a href="javascript:Form.queryAllCommdityByItemNew(1,null,null);" class="btn btn-mini btn-default btn-success">全部</a>');
// 						$.each(result,function(i,item){
// 							$("#labelLists").append("<a href='javascript:Form.queryAllCommdityByItemNew(1,null,null,null,"+item.id+");' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.tagName+"</a>");
// 						});
// //						if(change){
// //
// //						}else{
// //							window.Form.queryAllCommdityByItemNew(1,itemOneId);
// //						}
// 					}
// 				});
//
// 				$.ajax({
// 					url : rootPath + "/sysConfigItemTag/queryTags",
// 					type : "post",
// 					dataType : "json",
// 					data:{"itemOneId":itemOneId,"itemSecondId":itemSecondId,"level":2},
// 					success : function(result) {
// 						$("#labelSecondLists").html('');
// 						$("#labelSecondLists").append('<a href="javascript:Form.queryAllCommdityByItemNew(1,null,null);" class="btn btn-mini btn-default btn-success">全部</a>');
// 						$.each(result,function(i,item){
// 							$("#labelSecondLists").append("<a href='javascript:Form.queryAllCommdityByItemNew(1,null,null,null,null,"+item.id+");' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.tagName+"</a>");
// 						});
// 						if(change){
//
// 						}else{
// 							window.Form.queryAllCommdityByItemNew(1,itemOneId);
// 						}
// 					}
// 				});
// 			},
        getietmList: function (dom){
            var itemCode = [];
            if(dom.children()){
                var checkitem = dom.children(".btn-success")
                $.each(checkitem,function(i,v){
                    itemCode.push($(v).attr("data-code"));
                });
            }
            return itemCode.join(",");
        },
        setietmList:function (list){
            $.each(list,function(i,v){
                $("a[data-code="+v+"]",dom).addClass("btn-success");
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
				}else{
					
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

				var faceFlag = 0;
				var liveFlag = 0;
				var videoFlag = 0;
				var remoteFlag = 0;
				$("#flagList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						var cid=$(this).attr("ids");
						switch(cid){
							case "IS_LIVE":liveFlag = 1;break;
							case "IS_VIDEO":videoFlag = 1;break;
							case "IS_FACE":faceFlag = 1;break;
							case "IS_REMOTE":remoteFlag = 1;break;
							default:break;
						}
					}
				});
//				window.Form.querylablesList(id);
				$.ajax({
					url : rootPath + "/simpleClasses/showAllclassType",
					type : "post",
					data : {"page" : page,"itemOneId" : id,"itemSecondId" : itemSecondId,"publishStatus" : status,"itemTag":lab,"itemTag2":labSec,
						"faceFlag":faceFlag,"liveFlag":liveFlag,"videoFlag":videoFlag,"remoteFlag":remoteFlag},
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
			queryCommdityByFlag : function(page,id,itemSecondId,status,lab,labTwo){
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
				}else{

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
				var faceFlag = 0;
				var liveFlag = 0;
				var videoFlag = 0;
				var remoteFlag = 0;
				$("#flagList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						var cid=$(this).attr("ids");
						switch(cid){
							case "IS_LIVE":liveFlag = 1;break;
							case "IS_VIDEO":videoFlag = 1;break;
							case "IS_FACE":faceFlag = 1;break;
							case "IS_REMOTE":remoteFlag = 1;break;
							default:break;
						}
					}
				});
	//				window.Form.querylablesList(id);
				$.ajax({
					url : rootPath + "/simpleClasses/showAllclassType",
					type : "post",
					data : {"page" : page,"itemOneId" : id,"itemSecondId" : itemSecondId,"publishStatus" : status,"itemTag":lab,"itemTag2":labSec,
						"faceFlag":faceFlag,"liveFlag":liveFlag,"videoFlag":videoFlag,"remoteFlag":remoteFlag},
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
			queryAllCommdityByItemNew : function(page){
				var labSec="";
                var datas = {"page":page};
				var itemOneCode,itemSecondCode,itemThirdCode,itemFourthCode,status;
					$("#itemOneCodeList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
                            itemOneCode=$(this).attr("data-code");
						}
					});
					if(itemOneCode!='all'){
                        datas.itemOneCode=itemOneCode;
					}
					$("#itemSecondCodeList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
                            itemSecondCode=$(this).attr("data-code");
						}
					});
                if(itemSecondCode!='all'){
                    datas.itemSecondCode=itemSecondCode;
                }
                $("#itemThirdCodeList").find("a").each(function(i){
                    if($(this).hasClass('btn-success')){
                        itemThirdCode=$(this).attr("data-code");
                    }
                });
                if(itemThirdCode!='all'){
                    datas.itemThirdCode=itemThirdCode;
                }
                itemFourthCode = this.getietmList($("#itemFourthCodeList"));
                if(itemFourthCode.length>0){
                    datas.itemFourthCode=itemFourthCode;
                }
				if(status==null){
					$("#statusList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							status=cid;
						}
					});
				}
                if(status!='all'){
                    datas.publishStatus=status;
                }

                // if(lab==null){
				// 	$("#labelLists").find("a").each(function(i){
				// 		if($(this).hasClass('btn-success')){
				// 			var cid=$(this).attr("ids");
				// 			lab=cid;
				// 		}
				// 	});
				// }else{
				//
				// }
				// if(labTwo && labTwo!=null){
				// 	labSec=labTwo;
				// }else{
				// 	$("#labelSecondLists").find("a").each(function(i){
				// 		if($(this).hasClass('btn-success')){
				// 			var cid=$(this).attr("ids");
				// 			labSec=cid;
				// 		}
				// 	});
				// }
				var faceFlag = 0;
				var liveFlag = 0;
				var videoFlag = 0;
				var remoteFlag = 0;
				var flag;
				$("#flagList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						flag=$(this).attr("ids");
						switch(flag){
							case "IS_LIVE":liveFlag = 1;break;
							case "IS_VIDEO":videoFlag = 1;break;
							case "IS_FACE":faceFlag = 1;break;
							case "IS_REMOTE":remoteFlag = 1;break;
							default:break;
						}
					}
				});
                    if(flag!='all'){
                        datas.liveFlag = liveFlag;
                        datas.videoFlag = videoFlag;
                        datas.faceFlag = faceFlag;
                        datas.remoteFlag = remoteFlag;
                }

				$.ajax({
					url : rootPath + "/simpleClasses/showAllclassType",
					type : "post",
					data:datas,
					// data : {"page" : page,"itemOneId" : id,"itemSecondId" : itemSecondId,"publishStatus" : status,"itemTag":lab,"itemTag2":labSec,
					// 	"faceFlag":faceFlag,"liveFlag":liveFlag,"videoFlag":videoFlag,"remoteFlag":remoteFlag},
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
					url : rootPath + "/simpleClasses/showAllclassType",
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
							url : rootPath + "/simpleClasses/StopSale",
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
								Form.queryAllCommdityByItem(1);
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
						location.href= rootPath + "/simpleClasses/deleteClassType/"+id;
					}else{
						return;
					}
				})
			},
			classTypeOnsale : function(id){
				$.ajax({
					url : rootPath + "/simpleClasses/classTypeonSale",
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
						Form.queryAllCommdityByItem(1);
					}
				});
			},
			showClassTypeDetail : function(id,typeCode){
				var itemOneId="";
				// $("#itemOneList").find("a").each(function(i){
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
				$("#myForm").attr("action",rootPath+"/simpleClasses/showClassTypeDetail").submit();
			},
			editClassType : function(id){
				var itemOneId="";
				var lab="";
				$("#itemOneList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						itemOneId=$(this).attr("ids");
					}
				});
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
				$("#myForm").attr("action",rootPath+"/editSimpleCourse/editClassTypeMessage").submit();
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
					url : rootPath + "/simpleClasses/changClassTypeCollect",
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
				$("#itemOneList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						itemOneId=$(this).attr("ids");
						$("#oneId").val($(this).attr("ids"));
					}
				});
				$("#itemSecondList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						$("#twoId").val($(this).attr("ids"));
					}
				});
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
				$("#myForm").attr("action",rootPath+"/simpleClasses/addClassType").submit();
			}
			
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)