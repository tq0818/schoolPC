(function ($) {
    var Page={
    	init : function(){
    		$selectMenu("sales");
    		$selectSubMenus("coupon_manage");
    		var page=$("#page").val();
    		Page.queryCouponsPatchList(page);
    		Page.eventInit();
    		Page.styleInit();
    	},
    	styleInit : function(){
    		  // 查看优惠码的margin-top值
            $(".checkCouponsCode").css("margin-top","").css("margin-top", "-" + $(".checkCouponsCode").height() / 2 +"px");
            // 优惠码详情的margin-top值
      //      $(".couponCodeDetail").css("margin-top","").css("margin-top", "-" + $(".couponCodeDetail").height() / 2 + "px");
//    		$(".checkCouponsCode").css("margin-top","").css("margin-top","-220px");
//    		$(".couponCodeDetail").css("margin-top","").css("margin-top","-300px");
    	},
    	selectInit : function(){//箭头
    		$("#usetimeOrder").val("-");
    		$("#statusOrder").val("-");
			$(".iconfont_status").removeClass("chang_one");
			$(".iconfont_status").removeClass("chang_one");
    	},
    	eventInit : function(){
    		/*$.confirm=function(msg,callback){
    			var tips='<div class="layer-tips confirm layerTips" style="display: none;">'+
    		        '<div class="layer-tips-title">请确认 <i class="close iconfont confirm_close"></i></div>'+
    		        '<div class="layer-tips-content">'+msg+'</div>'+
    		        '<div class="layer-tips-btns">'+
    		        '    <a href="javascript:;" class="btn btn-mini btn-success confirm_ok">确定</a>'+
    		        '   <a href="javascript:;" class="btn btn-mini btn-default confirm_cancle">取消</a>'+
    		        '</div>'+
    		    '</div>'+
    		    '<div class="layer-tips-bg confirm" style="display: none;"></div>';
    			$(document).find(".layerTips").remove();
    			$(document).find(".layer-tips-bg").remove();
    			$(document).find("body").append(tips);
    			$('.layer-tips-bg').fadeIn(200,function(){
    	            $('.layer-tips.confirm').fadeIn(200);
    	        })
    	        //确定
    			$(document).off("click.ok.confirm").on("click.ok.confirm",".confirm_ok",function(){
    				$(this).parents('.layer-tips').fadeOut(200,function(){
    	                $('.layer-tips-bg.confirm').fadeOut(200);
    	            })
    				if(callback){
    					callback(true);
    				}
    			})
    			.on("click.close.confirm",".confirm_close",function(){
    				$(this).parents('.layer-tips').fadeOut(200,function(){
    	                $('.layer-tips-bg.confirm').fadeOut(200);
    	            })
    			});
    			//取消
    			$(document).off("click.cancle.confirm").on("click.cancle.confirm",".confirm_cancle",function(){
    				$(this).parents('.layer-tips').fadeOut(200,function(){
    	                $('.layer-tips-bg.confirm').fadeOut(200);
    	            })
    	            if(callback){
    					callback(false);
    				}
    			});
    		}*/
    		//鼠标移入更多
            $(".coupon-page").on("mouseenter.a", ".a-more,.a-more-cont", function () {
                $(this).parent(".operate-cont").find(".a-more").addClass("active");
                $(this).parent(".operate-cont").find(".iconfont,.a-more-cont").stop().show();
            }).on("mouseleave.b", ".a-more,.a-more-cont", function () {
                $(this).parent(".operate-cont").find(".a-more").removeClass("active");
                $(this).parent(".operate-cont").find(".iconfont,.a-more-cont").stop().hide();
            });
            // 日期插件的调用
            $(".date-picker").datetimepicker({
                language: 'zh-CN',
                format: "yyyy-mm-dd",
                minView: 2,
                autoclose: true
            });
            // 点击发放,删除,查看,详情
            $(".user-list").on("click", ".give-out", function () {
            	if($("#dclick").val()==0){
                var id=$(this).attr("patchid");
                $.confirm("您创建的优惠码将以优惠券的形式，发放到<i>“优惠对象”</i> 的个人中心里，您确定发放吗？",function(b){
                	if($("#dclick").val()==0){
	                	$("#dclick").val(1);
	                	if(b==true){
	            			 $.ajax({ 
	            				  type: "post", 
	            				  url: rootPath+"/companyCouponsPatch/sendCouponsPatch", 
	            				  data: {"id" : id},
	            				  beforeSend: function (XMLHttpRequest) {
	        	                    $(".loading.send").show();
	        	                    $(".loading-bg").show();
        	                	  },
	            				  success: function(result){
	            					//sendError:发送失败 userEmpty：用户列表为空 objError：指定范围为空 success：成功 sendMsgError：发送信息失败  sendSmsError ：发送短信失败
	            					  if(result=="success"){
	            						  $.msg("发放成功！");
	            						  var page=$("#page").val();
	            						  Page.queryCouponsPatchList(page);
	            					  }else if(result=="sendError"){
	            						  $.msg("发送失败！");
	            					  }else if(result=="userEmpty"){
	            						  $.msg("发送用户查询不到！");
	            					  }else if(result=="objError"){
	            						  $.msg("指定的发送范围不正确或查询不到！");
	            					  }else if(result=="sendError"){
	            						  $.msg("发送失败！");
	            					  }else if(result=="sendMsgAndSmsError"){
	            						  $.msg("发送成功，站内信以及短信通知失败！");
	            					  }else if(result=="sendMsgError"){
	            						  $.msg("发送成功，站内信通知失败！");
	            					  }else if(result=="sendSmsError"){
	            						  $.msg("发送成功，短信通知失败！");
	            					  }else if(result=="msgCountNotEnough"){
	            						  $.msg("发送失败，短信剩余数量不足！");
	            					  }else if(result=="createError"){
	            						  $.msg("发送失败,创建优惠码失败！");
	            					  }
	            					  if(result != "success"){
	            						  $("#dclick").val(0);
	            					  }
	            				  },
	            				  error:function(){
	            					  $("#dclick").val(0);
	            				  },
	        	                  complete: function (XMLHttpRequest, textStatus) {
	        	                      $(".loading").hide();
	        	                      $(".loading-bg").hide();
	        	                  }
	            				
	            			  });
	            		}else{
	            			$("#dclick").val(0);
	            			return false; 
	            		}
                	}
            		 $(".layer-tips-bg").hide();
            	});
            	}
            }).on("click", ".a-delete", function () {
            	var id=$(this).attr("patchid");
            	var page=$("#page").val();
            	$.confirm("您真的要删除该优惠吗？",function(b){
            		if(b==true){
            			 $.ajax({ 
            				  type: "post", 
            				  url: rootPath+"/companyCouponsPatch/delCouponsPatch", 
            				  data: {"id" : id},
            				  async: false,
            				  success: function(result){
            					  if(result){
            						  Page.queryCouponsPatchList(page);
            					  }else{
            						  $.msg("删除失败！");
            					  }
            				  }
            			  });
            		}else{
            			return false; 
            		}
            	});
                
            }).on("click",".a-check",function(){
            	//var page_libs=$("#page_libs").val();
            	var id=$(this).attr("patchid");
            	$("#page_libs").val(1);
            	Page.queryCouponsLibsList('1',id);
            	Page.styleInit();
            	$(".layer-tips-bg,.check-coupon-code").show();
            }).on("click",".a-detail",function(){
             	$(".couponCodeDetail").css("margin-top","").css("margin-top","-285px");
            	var page_libs_order=$("#page_libs_order").val();
            	var id=$(this).attr("patchid");
            	$("#order_patchid").val(id);
            	Page.queryCouponsPatchById(id);
            	$("#page_libs_order").val(1);
            	Page.queryLibsListForOrderUsed('1',id);
            	Page.styleInit(); 
            	Page.selectInit();
                $(".layer-tips-bg,.coupon-code-detail").show(); 
                $('html').css('overflow-y','hidden');
                $('body').css('overflow-y','hidden');
            }).on("click","a.a-excel",function(){
            	 $(".layer-tips-bg").show();
            	//var page_libs=$("#page_libs").val();
            	var id=$(this).attr("patchid");
            	$("#page_libs").val(1);
            	Page.queryCouponsLibsList('1',id);
            	$("#exportExcel_PatchId").val(id);
            	$(".exportExcel").show();
            });
            $(".closeExport").on("click",function(){
            	$(".exportExcel").hide();
            	 $(".layer-tips-bg").hide();
            })
//            $(".operate-cont").on("click","a.a-check,a.give-out,a.a-detail,.a-derive",function(){
//                $(".layer-tips-bg").show();
//            });
            // 查看优惠码的margin-top值
            $(".check-coupon-code").css("margin-top", '-' + $(".check-coupon-code").height() / 2 + 'px');
            // 优惠码详情的margin-top值
            $(".coupon-code-detail").css("margin-top", '-' + $(".coupon-code-detail").height() / 2 + 'px');
            // 点击关闭按钮
            $(".layer-tips-title").on("click",".confirm_close",function(){
            	 $('html').css('overflow-y','');
                 $('body').css('overflow-y','');
            	Page.selectInit();
                $(".layer-tips,.layer-tips-bg").hide()
            });
            //点击导出excel文件按钮
            $(".s_box").on("click",".right a",function(){
            	 if ($(".coupons_libs_List").find("tr").eq(1).find("td").length <= 1) {
                     $.msg("没有数据可以导出");
                 } else {
                	 var exportExcel_PatchId=$("#exportExcel_PatchId").val();
                	 window.location.href=rootPath+ "/companyCouponsLib/exportCouponsLib/"+exportExcel_PatchId;
                 }
               // $(".add-alert-content,.layer-tips-bg").hide()
            });
            //  点击指定用户中的关闭按钮与取消保存按钮
           /* $(".s_box").on("click",".s_right.iconfont,.conserve button,.right a",function(){
                $(".add-alert-content,.layer-tips-bg").hide()
            });*/
            $(".couponCreate").on("click",function(){
            	window.location.href=rootPath+"/companyCouponsPatch/manage/0";
            })
            $(".btn-searh").on("click",function(){
        		Page.queryCouponsPatchList(1);
            })
            $(".couponsManage").on("click",function(){
            	window.location.href=rootPath+"/companyCouponsConfig/showCouponsList";
            })
//            $(".statusOrder_Up").on("click",function(){
//            	$("#statusOrder").val("up");
//            //	$("#usetimeOrder").val("-");
//            	var id=$("#order_patchid").val();
//            	Page.queryLibsListForOrderUsed('1',id);
//            })
//            $(".statusOrder_Down").on("click",function(){
//            	$("#statusOrder").val("down");
//            //	$("#usetimeOrder").val("-");
//            	var id=$("#order_patchid").val();
//            	Page.queryLibsListForOrderUsed('1',id);
//            })
//            $(".usetimeOrder_Up").on("click",function(){
//            	$("#usetimeOrder").val("up");
//            //	$("#statusOrder").val("-");
//            	var id=$("#order_patchid").val();
//            	Page.queryLibsListForOrderUsed('1',id);
//            })
//            $(".usetimeOrder_Down").on("click",function(){
//            	$("#usetimeOrder").val("down");
//            //	$("#statusOrder").val("-");
//            	var id=$("#order_patchid").val();
//            	Page.queryLibsListForOrderUsed('1',id);
//            })
            $(".status_th").click(function(){
                if($(this).find(".iconfont_s").eq(1).hasClass("chang_one")){
                	$(this).find(".iconfont_s").eq(0).addClass("chang_one").siblings().removeClass("chang_one");
                	$("#statusOrder").val("up");
                  	var id=$("#order_patchid").val();
                  	Page.queryLibsListForOrderUsed('1',id);
                }else{
                	$(this).find(".iconfont_s").eq(1).addClass("chang_one").siblings().removeClass("chang_one");
                	$("#statusOrder").val("down");
                  	var id=$("#order_patchid").val();
                  	Page.queryLibsListForOrderUsed('1',id);
                }
            });
            $(".usetime_th").click(function(){
	            if($(this).find(".iconfont_u").eq(1).hasClass("chang_one")){
	            	$(this).find(".iconfont_u").eq(0).addClass("chang_one").siblings().removeClass("chang_one");
	            	$("#usetimeOrder").val("up");
	              	var id=$("#order_patchid").val();
	              	Page.queryLibsListForOrderUsed('1',id);
	            }else{
	            	$(this).find(".iconfont_u").eq(1).addClass("chang_one").siblings().removeClass("chang_one");
	            	$("#usetimeOrder").val("down");
	              	var id=$("#order_patchid").val();
	              	Page.queryLibsListForOrderUsed('1',id);
	            }
            });

            
    	},
    	queryCouponsPatchList : function(page){
    	
    		var data={};
    		data.page=page?page:1;
    		data.startStatus=$("#startStatus option:selected").val();
    		data.startDate=$("#startDate").val();
    		data.endDate=$("#endDate").val();
    		data.name=$("#patchName").val();
    		data.status=$("#sendStatus option:selected").val();
    		$.each(data,function(key,value){
				if(!value){
					delete data[key];
				}
			})
    		$(".couponsList").find("tr:gt(0)").remove();
    		$.ajax({
    			url: rootPath+"/companyCouponsPatch/queryCompanyCouponsPatchList",
    			type: "post",
    			data:data,
    			dataType: "json",
    			beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
    			success: function(jsonData){
    				if(jsonData.data.length==0){
    					if(page>=2){
     						$("#page").val(page-1);
     						Page.queryCouponsPatchList(page-1);
     					}else if(page=1){
     						$(".couponsList").append('<tr><td colspan="10">暂无数据</td></tr>');
     					}else{
     						$("#page").val(1);
     						Page.queryCouponsPatchList(1);
     					}
					}
    				$.each(jsonData.data, function (index, value) {
    					//优惠对象
    					var forCrowd="";
    					if(value.forCrowd=="COUPON_OBJECT_ALL"){
    						forCrowd="所有用户";
    					}
    					if(value.forCrowd=="COUPON_OBJECT_MEMBER"){
    						forCrowd="会员";
    					}
    					if(value.forCrowd=="COUPON_OBJECT_SOMEONE"){
    						forCrowd="指定用户";
    					}
    					if(value.forCrowd=="COUPON_OBJECT_STUDENT"){
    						forCrowd="购买过课程的学员";
    					}
    					//优惠方式
    					var useWay="";
    					if(value.useWay==0){
    						useWay="抵现"+value.insCashNum+"元";
    					}
    					if(value.useWay==1){
    						useWay="满"+value.overNum+"元减"+value.overCutNum+"元";
    					}
    					if(value.useWay==2){
    						useWay="打"+(value.discountNum||"")+"折";
    					}
    					//优惠范围
    					var useRange="";
    					switch(value.commodityType){
    					case 1:
    						if(value.useRange==0){
	    						useRange="全部课程包";
	    					}else{
	    						useRange=value.rangeItemPackageCategory;
	    					}
    						break;
						default:
	    					if(value.useRange==0){
	    						useRange="全部课程";
	    					}
	    					if(value.useRange==1){
	    						useRange=value.rangeItemOneName+(value.rangeItemSecondName?"-"+value.rangeItemSecondName:"");
	    					}
	    					if(value.useRange==2){
	    						useRange=value.rangeItemOneName+"-"+value.rangeItemSecondName+"-"+value.rangeItemCourseName;
	    					}
	    					break;
    					}
    					//发放状态
    					var status='';
    					if(value.status==0){
    						status='<td>未发放</td>'
    					}
    					if(value.status==1){
    						status='<td class="active">已发放</td>'
    					}
						if(value.status==2){
							status='<td class="active">已过期</td>'
						}
						//发放方式
						var issueWay="";
						if(value.issueWay==0){
							issueWay="线上发放";
						}
						if(value.issueWay==1){
							issueWay="线下发放";
						}
						//操作
						var handle='';
						if(value.status==0){
							if(value.issueWay==0){
								handle=' <div class="operate-cont"> '+
								'    <a href="'+rootPath+'/companyCouponsPatch/manage/'+value.id+'" patchid="'+value.id+'">编辑</a> '+
								       '     <em>|</em> '+
								       '     <a href="javascript:;"  class="give-out" patchid="'+value.id+'">发放</a> '+
								       '     <em>|</em> '+
								       '         <a href="javascript:;" class="a-delete" patchid="'+value.id+'">删除</a> '+
								      // '     <a href="javascript:;" class="a-more">更多<i  class="iconfont">&#xe623;</i></a> '+
								    //   '     <span class="a-more-cont"> '+
								    //   '         <a href="javascript:;" class="a-delete" patchid="'+value.id+'">删除</a> '+
								   //    '         <a  href="javascript:;" class="a-check" patchid="'+value.id+'">查看</a> '+
								     //  '     </span> '+
								       ' </div>'	; 
							}else{
								handle=' <div class="operate-cont"> '+
								'  <a href="'+rootPath+'/companyCouponsPatch/manage/'+value.id+'" patchid="'+value.id+'">编辑</a> '+
										'    <em>|</em> '+
										'     <a href="javascript:;" class="a-delete" patchid="'+value.id+'">删除</a> '+
										'     <em>|</em> '+
										'    <a href="javascript:;" class="a-excel" patchid="'+value.id+'">导出</a> '+
										'  </div>';
							}
						}else{
							//已发放，已过期
							if(value.issueWay==0){
								handle=' <div class="operate-cont"> '+
						           ' <a href="javascript:;" class="a-detail" patchid="'+value.id+'">详情</a> '+
						           ' </div>';
							}else{
								handle=' <div class="operate-cont"> '+
						           ' <a href="javascript:;" class="a-detail" patchid="'+value.id+'">详情</a> '+
						           '     <em>|</em> '+
						           '    <a href="javascript:;" class="a-excel" patchid="'+value.id+'">导出</a> '+
						           ' </div>';
							}
						}
						
    					$(".couponsList").append('<tr>'+'<input type="hidden" id="dclick" value="0">'+
						'<td title="'+value.name+'">'+(value.name?value.name.length>5?(value.name.substring(0,5)+"..."):value.name:"")+'</td>'+
						'<td>'+(value.forCrowd?forCrowd:"")+'</td>'+
						'<td>'+useWay+'</td>'+
						'<td>'+(value.commodityType == 1?'课程包':"课程")+'</td>'+
						'<td>'+useRange+'</td>'+
						'<td>'+(value.totalNum?value.totalNum:"")+'</td>'+
						'<td>'+(value.timeLimitFrom?("从 "+value.timeLimitFrom+"到 "+value.timeLimitTo):"")+'</td>'+
						status+
						'<td>'+issueWay+'</td>'+
						'<td>'+handle+'</td>'+
						'</tr>');
    	            });
    			
    				if(jsonData.rowCount>10){
    					$(".pagination").pagination(jsonData.rowCount, {
    						 next_text: "下一页",
    			             prev_text: "上一页",
    			             current_page: jsonData.pageNo - 1,
    			             link_to: "javascript:void(0)",
    			             num_display_entries: 8,
    			             items_per_page: jsonData.pageSize,
    			             num_edge_entries: 1,
    			             callback: function (page, jq) {
    			                 var pageNo = page + 1;
    			                 $("#page").val(pageNo);
    			                 Page.queryCouponsPatchList(pageNo);
    			             }
    				   });
    					
    				}else{
    					$(".pagination").html('');
    				}
    			},
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
    		})
    	},
    	queryCouponsPatchById : function(id){
    		$.ajax({
    			url: rootPath+"/companyCouponsPatch/queryCouponsPatchById",
    			type: "post",
    			data:{"id":id},
    			dataType: "json",
    			async: false,
    			success: function(value){
    				//优惠对象
					var forCrowd="";
					if(value.forCrowd=="COUPON_OBJECT_ALL"){
						forCrowd="所有用户";
					}
					if(value.forCrowd=="COUPON_OBJECT_MEMBER"){
						forCrowd="会员 ( ";
						if(value.memberList){
							var memberIds=value.memberList.split(",");
							for(var i=0;i<memberIds.length;i++) {
								$.ajax({
					    			url: rootPath+"/companyMemberConfig/queryMemberLevelById",
					    			type: "post",
					    			data:{"id":memberIds[i]},
					    			dataType: "json",
					    			async: false,
					    			success: function(member){
					    				forCrowd+=(i==0?"":"，")+member.name;
					    			}
								});
							}	
						}
						forCrowd+=" )";
					}
					if(value.forCrowd=="COUPON_OBJECT_SOMEONE"){
						forCrowd="指定用户";
					}
					if(value.forCrowd=="COUPON_OBJECT_STUDENT"){
						forCrowd="购买过课程的学员";
					}
					//优惠方式
					var useWay="";
					if(value.useWay==0){
						useWay="抵现"+value.insCashNum+"元";
					}
					if(value.useWay==1){
						useWay="满"+value.overNum+"元减"+value.overCutNum+"元";
					}
					if(value.useWay==2){
						useWay="打"+(value.discountNum||"")+"折";
					}
					//优惠范围
					var useRange="";
					switch(value.commodityType){
					case 1:
						if(value.useRange==0){
    						useRange="全部课程包";
    					}else{
    						useRange=value.rangeItemPackageCategory;
    					}
						break;
					default:
						if(value.useRange==0){
							useRange="全部课程";
						}
						if(value.useRange==1){
							useRange=value.rangeItemOneName+((value.rangeItemSecondName!=null && value.rangeItemSecondName !='')?"-"+value.rangeItemSecondName:"");
						}
						if(value.useRange==2){
							useRange=value.rangeItemOneName+"-"+value.rangeItemSecondName+"-"+value.rangeItemCourseName;
						}
						break;
					}
    				$("#patch_name").html(value.name);
    				$("#patch_useWay").html(useWay);
    				$("#patch_totalNum").html(value.totalNum);
    				$("#patch_forCrowd").html(forCrowd);
    				$("#patch_time").html((value.timeLimitFrom?("从 "+value.timeLimitFrom+"到 "+value.timeLimitTo):""));
    				$("#patch_useRange").html(useRange);
    				$("#patch_description").html(value.description);
    			}
    		})
    	},
    	queryCouponsLibsList : function(page_libs,id){
    		var forCrowd="";
    		var useWay="";
    		var useRange="";
    		$.ajax({
    			url: rootPath+"/companyCouponsPatch/queryCouponsPatchById",
    			type: "post",
    			data:{"id":id},
    			dataType: "json",
    			async: false,
    			success: function(value){
    				//优惠对象
					if(value.forCrowd=="COUPON_OBJECT_ALL"){
						forCrowd="所有用户";
					}
					if(value.forCrowd=="COUPON_OBJECT_MEMBER"){
						forCrowd="会员 ( ";
						if(value.memberList){
							var memberIds=value.memberList.split(",");
							for(var i=0;i<memberIds.length;i++) {
								$.ajax({
					    			url: rootPath+"/companyMemberConfig/queryMemberLevelById",
					    			type: "post",
					    			data:{"id":memberIds[i]},
					    			dataType: "json",
					    			async: false,
					    			success: function(member){
					    				forCrowd+=(i==0?"":"，")+member.name;
					    			}
								});
							}	
						}
						forCrowd+=" )";
					}
					if(value.forCrowd=="COUPON_OBJECT_SOMEONE"){
						forCrowd="指定用户";
					}
					if(value.forCrowd=="COUPON_OBJECT_STUDENT"){
						forCrowd="购买过课程的学员";
					}
					//优惠方式
					if(value.useWay==0){
						useWay="抵现"+value.insCashNum+"元";
					}
					if(value.useWay==1){
						useWay="满"+value.overNum+"元减"+value.overCutNum+"元";
					}
					if(value.useWay==2){
						useWay="打"+(value.discountNum||"")+"折";
					}
					//优惠范围
					switch(value.commodityType){
					case 1:
						if(value.useRange==0){
    						useRange="全部课程包";
    					}else{
    						useRange=value.rangeItemPackageCategory;
    					}
						break;
					default:
						if(value.useRange==0){
							useRange="全部课程";
						}
						if(value.useRange==1){
							useRange=value.rangeItemOneName+((value.rangeItemSecondName!=null && value.rangeItemSecondName !='')?"-"+value.rangeItemSecondName:"");
						}
						if(value.useRange==2){
							useRange=value.rangeItemOneName+"-"+value.rangeItemSecondName+"-"+value.rangeItemCourseName;
						}
						break;
					}
					$(".coupons_libs_List").find("tr:gt(0)").remove();
		    		$.ajax({
		    			url: rootPath+"/companyCouponsLib/queryLibsListByPatchId",
		    			type: "post",
		    			data:{"page":page_libs?page_libs:1,"id":id},
		    			dataType: "json",
		    			async: false,
		    			success: function(jsonData){
		    				if(jsonData.data.length==0){
								$(".coupons_libs_List").append('<tr><td colspan="4">暂无数据</td></tr>');
							}
		    				
		    				$.each(jsonData.data, function (index, value) {
		    					$(".coupons_libs_List").append('<tr>'+
		    							'<td>'+(value.code?value.code:"")+'</td>'+
		    							'<td>'+useRange+'</td>'+
		    						//	'<td>'+forCrowd+'</td>'+
		    							'<td>'+(value.timeLimitFrom?('从 '+value.timeLimitFrom+' 到 '+value.timeLimitTo):"")+'</td>'+
		    							'</tr>');
		    	            });
		    			
		    				if(jsonData.rowCount>10){
		    					$(".pagination_libs").pagination(jsonData.rowCount, {
		    						 next_text: "下一页",
		    			             prev_text: "上一页",
		    			             current_page: jsonData.pageNo - 1,
		    			             link_to: "javascript:void(0)",
		    			             num_display_entries: 8,
		    			             items_per_page: jsonData.pageSize,
		    			             num_edge_entries: 1,
		    			             callback: function (page_libs, jq) {
		    			                 var pageNo_libs = page_libs + 1;
		    			                 $("#page_libs").val(pageNo_libs);
		    			                 Page.queryCouponsLibsList(pageNo_libs,id);
		    			             }
		    				   });
		    					
		    				}else{
		    					$(".pagination_libs").html('');
		    				}
		    			}
		    		})
    			}
    		})
    		
    	},
    	queryLibsListForOrderUsed : function(page_libs_order,id){
//    		$.ajax({
//    			url: rootPath+"/companyCouponsPatch/queryCouponsPatchById",
//    			type: "post",
//    			data:{"id":id},
//    			dataType: "json",
//    			async: false,
//    			success: function(value){
//    				//优惠对象
//					var forCrowd="";
//					if(value.forCrowd=="COUPON_OBJECT_ALL"){
//						forCrowd="所有用户";
//					}
//					if(value.forCrowd=="COUPON_OBJECT_MEMBER"){
//						forCrowd="会员 ( ";
//						if(value.memberList){
//							var memberIds=value.memberList.split(",");
//							for(var i=0;i<memberIds.length;i++) {
//								$.ajax({
//					    			url: rootPath+"/companyMemberConfig/queryMemberLevelById",
//					    			type: "post",
//					    			data:{"id":memberIds[i]},
//					    			dataType: "json",
//					    			async: false,
//					    			success: function(member){
//					    				forCrowd+=(i==0?"":"，")+member.name;
//					    			}
//								});
//							}	
//						}
//						forCrowd+=" )";
//					}
//					if(value.forCrowd=="COUPON_OBJECT_SOMEONE"){
//						forCrowd="指定用户";
//					}
//					if(value.forCrowd=="COUPON_OBJECT_STUDENT"){
//						forCrowd="购买过课程的学员";
//					}
//					//优惠范围
//					var useRange="";
//					if(value.useRange==0){
//						useRange="全部课程";
//					}
//					if(value.useRange==1){
//						useRange=value.rangeItemOneName+((value.rangeItemSecondName!=null && value.rangeItemSecondName !='')?"-"+value.rangeItemSecondName:"");
//					}
//					if(value.useRange==2){
//						useRange=value.rangeItemOneName+"-"+value.rangeItemSecondName+"-"+value.rangeItemCourseName;
//					}
//					//优惠方式
//					var useWay="";
//					var useway=-1;
//					var usewayType=-1;
//					if(value.useWay==0){
//						useWay="抵现"+value.insCashNum;
//						useway=0;
//						usewayType=value.insCashNum;
//					}
//					if(value.useWay==1){
//						useWay="满"+value.overNum+"减"+value.overCutNum;
//						useway=1;
//						usewayType=value.overCutNum;
//					}
//					if(value.useWay==2){
//						useWay="打"+value.discountNum+"折";
//						useway=2;
//						usewayType=value.discountNum;
//					}
//					$("#patch_name").html(value.name);
//    				$("#patch_useWay").html(useWay);
//    				$("#patch_totalNum").html(value.totalNum);
//    				$("#patch_forCrowd").html(forCrowd);
//    				$("#patch_time").html("从 "+value.timeLimitFrom+"到 "+value.timeLimitTo);
//    				$("#patch_useRange").html(useRange);
//    				$("#patch_description").html(value.description);
//					
//    			}
//    		})
    		var statusOrder=$("#statusOrder").val();
			var usetimeOrder=$("#usetimeOrder").val();
			
		//	$(".pagination_libs_order").html('');
    		$.ajax({
    			url: rootPath+"/companyCouponsLib/queryLibsListForOrderUsed",
    			type: "post",
    			data:{"page":page_libs_order?page_libs_order:1,"id":id,"statusOrder":statusOrder,"usetimeOrder":usetimeOrder},
    			dataType: "json",
    			//async: false,
    			beforeSend: function (XMLHttpRequest) {
    				$(".loading").css("z-index","").css("z-index","9999");
                    $(".loading").show();
                    $(".loading-bg").show();
                },
    			success: function(jsonData){
    				$(".coupons_libs_order_List").find("tr:gt(0)").remove();
    				if(jsonData.data.length==0){
						$(".coupons_libs_order_List").append('<tr><td colspan="9">暂无数据</td></tr>');
					}
    				
    				$.each(jsonData.data, function (index, value) {
    					//使用情况
    					var isUsed='';
    				//	var originalPrice=0;
    					if(value.status==0){
    						isUsed='未使用';
    					}else{
    						isUsed='已使用';
    					}
//		    					if(useway==0){
//		    						originalPrice=$.MoneyToNum(value.payPrice)+usewayType;
//		    					}else if(useway==1){
//		    						originalPrice=$.MoneyToNum(value.payPrice)+usewayType;
//		    					}else if(useway==2){
//		    						originalPrice=Math.round(value.payPrice/(usewayType*0.1));
//		    					}
    					$(".coupons_libs_order_List").append('<tr>'+
    							'<td>'+(value.code?value.code:"")+'</td>'+
    							'<td>'+isUsed+'</td>'+
    							'<td>'+(value.userMobile?value.userMobile:(value.userName?value.userName:""))+'</td>'+
    							'<td>'+(value.commodityName?value.commodityName:"")+'</td>'+
    							'<td>'+$("#patch_useWay").html()+'</td>'+
    							'<td>'+(value.originalPrice?$.formatMoney(value.originalPrice):"")+'</td>'+
    							'<td>'+(value.payPrice?$.formatMoney(value.payPrice):"")+'</td>'+
    							'<td>'+(value.sendDate?value.sendDate:"")+'</td>'+
    							'<td>'+(value.useTime?value.useTime:"")+'</td>'+
    							'</tr>');
    	            });
    			
    				if(jsonData.rowCount>5){
    					$(".pagination_libs_order").pagination(jsonData.rowCount, {
    						 next_text: "下一页",
    			             prev_text: "上一页",
    			             current_page: jsonData.pageNo - 1,
    			             link_to: "javascript:void(0)",
    			             num_display_entries: 8,
    			             items_per_page: jsonData.pageSize,
    			             num_edge_entries: 1,
    			             callback: function (page_libs_order, jq) {
    			                 var pageNo_libs_order = page_libs_order + 1;
    			                 $("#page_libs_order").val(pageNo_libs_order);
    			                 Page.queryLibsListForOrderUsed(pageNo_libs_order,id);
    			             }
    				   });
    					
    				}else{
    					$(".pagination_libs_order").html('');
    				}
    			},
    			complete: function (XMLHttpRequest, textStatus) {
    				$(".loading").css("z-index","").css("z-index","99");
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
    		})
    	}
    	
    }
    $(function () {
    	Page.init();
    })
})(jQuery)