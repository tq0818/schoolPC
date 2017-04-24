$selectMenu("member_class");
	
	var page=$("#page").val();
	var page1=$("#page1").val();
	var idsArray=new Array();
	var idstr="";
	$.ajax({
		url: rootPath+"/classType/queryMemberLevelAndClassType",
		type: "post",
		data:{"page":page1,"memberId":$("#memberId").val()},
		dataType: "json",
		success: function(jsonData){
		
			$.each(jsonData.data, function (index, value) {
				var typeflag1="";
				  if(value.liveFlag==1&&value.videoFlag==0&&value.faceFlag==0){
					  typeflag1='<span class="play-status blue-bg">直播</span>';
				  }else if(value.videoFlag==1&&value.faceFlag==0&&value.liveFlag==0){
					  typeflag1='<span class="play-status yellow-bg">录播</span>';
				  }else if(value.faceFlag==1&&value.liveFlag==0&&value.videoFlag==0){
					  typeflag1='<span class="play-status blue-bg">面授</span>';
				  }else if(value.faceFlag==0&&value.liveFlag==0&&value.videoFlag==0){
					  typeflag1='<span class="play-status blue-bg">其他</span>';
				  }else{
					  typeflag1='<span class="play-status red-bg">混合</span>';
				  }
				  ownVipPrice='';
				  ownVipDiscount='';
				  if($("#discountType").val()==1){
					  ownVipPrice='        <em class="orange-b changePrice">自定义会员价 <input type="hidden" name="classTypeId2" value="'+value.classTypeId+'"/></em> ';
					  ownVipDiscount='         会员优惠：'+value.memberDiscount+'折';
				  }
				$(".memberClassList").append('<div class="pop-course"> '+
						 '       <img src="'+(value.cover?value.cover:(rootPath+'/images/overview_demo.jpg'))+'" alt="" width="19%" height="100px"/> '+
                        '      <div class="course-manage-cont"> '+
                        '       <div class="top-right-tag"></div> '+
                            '        <div class="course-manage-title" style="line-height: 30px"> '+
                            '         <span>'+value.name+'</span> '+
                                '        <em class="gray-b delCourse">移除课程 <input type="hidden" name="classTypeId" value="'+value.id+'"/></em> '+
                                ownVipPrice+
                                '        <em class="moved">原价 <s>'+$.formatMoney(value.originalPrice)+'</s> 元</em> '+
                                '      </div> '+
                            '       <div class="course-manage-bottom"> '+
                            typeflag1+
                                '           <span class="number-box vip-price"> '+
                                ownVipDiscount +'   会员价  <em>'+$.formatMoney((value.originalPrice*value.memberDiscount*0.1))+' </em> 元 '+
                                '        </span> '+
                            '       </div> '+
                            '   </div> '+
                   ' </div>'); 
				
            });
		
	
			if(jsonData.rowCount>3){
				$(".pagination1").pagination(jsonData.rowCount, {
					 next_text: "下一页",
		             prev_text: "上一页",
		             current_page: jsonData.pageNo - 1,
		             link_to: "javascript:void(0)",
		             num_display_entries: 8,
		             items_per_page: jsonData.pageSize,
		             num_edge_entries: 1,
		             callback: function (page1, jq) {
		                 var pageNo1 = page1 + 1;
		                 $("#page1").val(pageNo1);
		                 queryMemberLevelAndClassType(pageNo1);
		             }
			   });
				
			}else{
				$(".pagination1").html('');
			}
// 			if(jsonData.data.length==0){
// 					$(".memberClassList").append('<div class="no-content-prompt">此会员等级还没有添加课程</div>');
// 				}
		}
	})
	$.ajax({
		url: rootPath+"/sysConfigItem/getJsons",
		type: "post",
		dataType: "json",
		data: {"itemType":1},
		success: function(jsonData){
			$("#itemOne1").html('');
			$("#itemOne1").append('<option selected="selected" value="0">学科</option>');
			$.each(jsonData,function(i,data){
				if(i=0){
					$("#itemOne1").append('<option value="'+data.id+'">'+data.itemName+'</option>');
				}else{
					$("#itemOne1").append('<option value="'+data.id+'">'+data.itemName+'</option>');
				}
			});
			$("#itemTwo1").append('<option selected="selected" value="0">学科小类</option>');
		}
	});


	function queryItemSecond(){
		var itemOneId=0;
		var oneValue=$("#itemOne1").val();
		if(oneValue){
			itemOneId=oneValue;
		}
		$.ajax({
			url: rootPath+"/sysConfigItem/getJsons",
			type: "post",
			dataType: "json",
			data: {"itemType":2,"parentId":itemOneId},
			success: function(jsonData){
				$("#itemTwo1").html('');
				if(jsonData==""){
					$("#itemTwo1").append('<option selected="selected" value="0">学科小类</option>');
				}
				$.each(jsonData,function(i,data){
					$("#itemTwo1").append('<option value="'+data.id+'">'+data.itemName+'</option>');
				});
			}
		})
	}
	$("span.r").on("click",function(){
		$("#dclick").val(0);
	    $(".choice-class-pop").fadeIn(100);
	    idsArray=[];
	    searchClassType(page);
	})
	$("#freelook").on("click",function(){
    	pric=$.MoneyToNum($("#pric").val());
		$("#course_discount").val(0);
		$("#chaPrice").val($.formatMoney(pric));
		$("#youhuiPrice").val($.formatMoney(0.00));
		if(typeof($("#course_discount").attr("disabled"))=="undefined"){
			$("#course_discount").attr("disabled","disabled");
		}
		if(typeof($("#chaPrice").attr("disabled"))=="undefined"){
			$("#chaPrice").attr("disabled","disabled");
		}
		if(typeof($("#youhuiPrice").attr("disabled"))=="undefined"){
			$("#youhuiPrice").attr("disabled","disabled");
		}
	})
	$("#usediscount").on("click",function(){
    	pric=$.MoneyToNum($("#pric").val());
		$("#course_discount").val(10);
		$("#chaPrice").val($.formatMoney(0.00));
		$("#youhuiPrice").val($.formatMoney(pric));
		if(typeof($("#course_discount").attr("disabled"))!="undefined"){
			$("#course_discount").removeAttr("disabled");
		}
		if(typeof($("#chaPrice").attr("disabled"))!="undefined"){
			$("#chaPrice").removeAttr("disabled");
		}
		if(typeof($("#youhuiPrice").attr("disabled"))!="undefined"){
			$("#youhuiPrice").removeAttr("disabled");
		}
	})
	function searchClassType(page){
	   $(".list").html("");
	   if(page){
			page=page;
		}else{
			page=0;
		}
	 	ischoosed=' <span>已选择<i class="iconfont">&#xe68f;</i></span>';
	  	itemOneId=$("#itemOne1").val();
		itemSecondId=$("#itemTwo1").val();
		name=$("#searchName").val();
		$.ajax({
			url: rootPath+"/classType/queryClassTypeNotMember",
			type: "post",
			data:{"page":page,"itemOneId":itemOneId,"itemSecondId":itemSecondId,"name":name,"memberId":$("#memberId").val()},
			dataType: "json",
			beforeSend: function (XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
			success: function(jsonData){
				$.each(jsonData.data, function (index, value) {
					free=' <span class="free">已免费</span>';
					if(value.originalPrice==0){
						
					}else{
						free="";
					}

					var typeflag="";
					  if(value.liveFlag==1&&value.videoFlag==0&&value.faceFlag==0){
						  typeflag='<span class="play-status blue-bg">直播</span>';
					  }else if(value.videoFlag==1&&value.faceFlag==0&&value.liveFlag==0){
						  typeflag='<span class="play-status yellow-bg">录播</span>';
					  }else if(value.faceFlag==1&&value.liveFlag==0&&value.videoFlag==0){
						  typeflag='<span class="play-status blue-bg">面授</span>';
					  }else if(value.faceFlag==0&&value.liveFlag==0&&value.videoFlag==0){
						  typeflag='<span class="play-status blue-bg">其他</span>';
					  }else{
						  typeflag='<span class="play-status red-bg">混合</span>';
					  }
					if(isLive(value.id)){
			              $(".list").append(' <div class="pop-course classTypeNotMember disabled" style="border: 1px solid #ddd;"> '+
			            		  '<input id="classids" type="hidden" value='+value.id+ ' />  '+
			            		  '     <img src="'+(value.cover?value.cover:(rootPath+'/images/overview_demo.jpg'))+'" alt="" width="19%" height="100px"/> '+
			              '     <div class="course-manage-cont"> '+
			              '       <div class="top-right-tag"> '+
			                  free+'<input id="classid" type="hidden" value='+value.id+ ' /> <span class="choosedid">已选择<i class="iconfont">&#xe68f;</i></span> '+
			                      '       </div> '+
			                  '       <div class="course-manage-title" style="line-height: 30px"> '+
			                  '          <span>'+value.name+'</span> '+
			                      '          <em class="moved">原价 <s>'+$.formatMoney(value.originalPrice)+'</s> 元</em> '+
			                      '          </div> '+
			                      '       <div class="course-manage-bottom"> '+
			                  typeflag+
			                      '             <span class="number-box"> '+
			                      '           优惠价 '+$.formatMoney($("#discount").val()*0.1*value.originalPrice)+'  元 '+
			                          '           </span> '+
			                      '       </div> '+
			                  '    </div> '+
			         '  </div> ');
					}else{
						  $(".list").append(' <div class="pop-course classTypeNotMember" style="border: 1px solid #ddd;"> '+
			            		  '<input id="classids" type="hidden" value='+value.id+ ' />  '+
			            		  '     <img src="'+(value.cover?value.cover:(rootPath+'/images/overview_demo.jpg'))+'" alt="" width="19%" height="100px"/> '+
			              '     <div class="course-manage-cont"> '+
			              '       <div class="top-right-tag"> '+
			                  free+'<input id="classid" type="hidden" value='+value.id+ ' />  '+
			                      '       </div> '+
			                  '       <div class="course-manage-title" style="line-height: 30px"> '+
			                  '          <span>'+value.name+'</span> '+
			                      '          <em class="moved">原价 <s>'+$.formatMoney(value.originalPrice)+'</s> 元</em> '+
			                      '          </div> '+
			                      '       <div class="course-manage-bottom"> '+
			                  typeflag+
			                      '             <span class="number-box"> '+
			                      '           优惠价 '+$.formatMoney($("#discount").val()*0.1*value.originalPrice)+'  元 '+
			                          '           </span> '+
			                      '       </div> '+
			                  '    </div> '+
			         '  </div> ');
					}
	            });
			
		
			if(jsonData.rowCount>3){
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
		                 searchClassType(pageNo);
		             }
			   });
			}else{
				$(".pagination").html('');
			}
			if(jsonData.data.length==0){
				$(".list").html('<div class="no-content-prompt">没有你要查找的课程，您可以去<a href="'+rootPath+'/classPackage/addClassType" target="_blank">创建课程</a></div>');
				$(".bottomButtom").css("display","none");
			}else{
				$(".bottomButtom").css("display","block");
			}
			},
		   complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
		})
	}
	$(function(){
		$("#searchButton").click(function(){
			searchClassType(page);
		})
		
		
		  $(".print_chaPrice").on('keyup',function(){
			  chaPrice=$.MoneyToNum($("#chaPrice").val());
			  pric=$.MoneyToNum($("#pric").val());
			  if(pric==0){
				  $.msg("原始价格为0.00元,已不能减价了！");
				  $("#chaPrice").val($.formatMoney(0));
				  $("#youhuiPrice").val($.formatMoney(0));
				  $("#course_discount").val(10);
				  return false;
			  }
			  youhuiPrice=pric-chaPrice;
			  courseDiscount=(youhuiPrice/pric)*10
			  if(chaPrice>pric){
				  $.msg("减价不能超过原始价格！")
				  $("#usediscount").click();
				  return false;
			  }
			  $("#youhuiPrice").val($.formatMoney(youhuiPrice));
			  $("#course_discount").val(Math.round(courseDiscount*100)/100);
		     }).on('blur',function(){
		   	  chaPrice=$.MoneyToNum($("#chaPrice").val());
			  pric=$.MoneyToNum($("#pric").val());
			  if(pric==0){
				  $.msg("原始价格为0.00元,已不能减价了！");
				  $("#chaPrice").val($.formatMoney(0));
				  $("#youhuiPrice").val($.formatMoney(0));
				  $("#course_discount").val(10);
				  return false;
			  }
			  youhuiPrice=pric-chaPrice;
			  courseDiscount=(youhuiPrice/pric)*10
			  if(chaPrice>pric){
				  $.msg("减价不能超过原始价格！")
				  $("#usediscount").click();
				  return false;
			  }
			  $("#youhuiPrice").val($.formatMoney(youhuiPrice));
			  $("#course_discount").val(Math.round(courseDiscount*100)/100);
		     }).on('change',function(){
		   	  chaPrice=$.MoneyToNum($("#chaPrice").val());
			  pric=$.MoneyToNum($("#pric").val());
			  if(pric==0){
				  $.msg("原始价格为0.00元,已不能减价了！");
				  $("#chaPrice").val($.formatMoney(0));
				  $("#youhuiPrice").val($.formatMoney(0));
				  $("#course_discount").val(10);
				  return false;
			  }
			  youhuiPrice=pric-chaPrice;
			  courseDiscount=(youhuiPrice/pric)*10
			  if(chaPrice>pric){
				  $.msg("减价不能超过原始价格！")
				  $("#usediscount").click();
				  return false;
			  }
			  $("#youhuiPrice").val($.formatMoney(youhuiPrice));
			  $("#course_discount").val(Math.round(courseDiscount*100)/100);
		     });
		
		  $(".print_youhuiPrice").on('keyup',function(){
			  youhuiPrice=$.MoneyToNum($("#youhuiPrice").val());
			  pric=$.MoneyToNum($("#pric").val());
			  if(pric==0){
				  $.msg("原始价格为0.00元,已不能减价了！");
				  $("#chaPrice").val($.formatMoney(0));
				  $("#youhuiPrice").val($.formatMoney(0));
				  $("#course_discount").val(10);
				  return false;
			  }
			  chaPrice=pric-youhuiPrice;
			  courseDiscount=(youhuiPrice/pric)*10
			  if(youhuiPrice>pric){
				  $.msg("优惠价不能超过原始价格！")
				  $("#usediscount").click();
				  return false;
			  }
			  $("#chaPrice").val($.formatMoney(chaPrice));
			  $("#course_discount").val(Math.round(courseDiscount*100)/100);
		     }).on('blur',function(){
		    	 youhuiPrice=$.MoneyToNum($("#youhuiPrice").val());
				  pric=$.MoneyToNum($("#pric").val());
				  if(pric==0){
					  $.msg("原始价格为0.00元,已不能减价了！");
					  $("#chaPrice").val($.formatMoney(0));
					  $("#youhuiPrice").val($.formatMoney(0));
					  $("#course_discount").val(10);
					  return false;
				  }
				  chaPrice=pric-youhuiPrice;
				  courseDiscount=(youhuiPrice/pric)*10
				  if(youhuiPrice>pric){
					  $.msg("优惠价不能超过原始价格！")
					  $("#usediscount").click();
					  return false;
				  }
				  $("#chaPrice").val($.formatMoney(chaPrice));
				  $("#course_discount").val(Math.round(courseDiscount*100)/100);
		     }).on('change',function(){
		    	 youhuiPrice=$.MoneyToNum($("#youhuiPrice").val());
				  pric=$.MoneyToNum($("#pric").val());
				  if(pric==0){
					  $.msg("原始价格为0.00元,已不能减价了！");
					  $("#chaPrice").val($.formatMoney(0));
					  $("#youhuiPrice").val($.formatMoney(0));
					  $("#course_discount").val(10);
					  return false;
				  }
				  chaPrice=pric-youhuiPrice;
				  courseDiscount=(youhuiPrice/pric)*10
				  if(youhuiPrice>pric){
					  $.msg("优惠价不能超过原始价格！")
					  $("#usediscount").click();
					  return false;
				  }
				  $("#chaPrice").val($.formatMoney(chaPrice));
				  $("#course_discount").val(Math.round(courseDiscount*100)/100);
		     });
		
		  $("#course_discount").on('keyup',function(){
    		if ($("#course_discount").val()>10) {
	    		$.msg("折扣不能超过10");
	    		$("#course_discount").val(10);
    		 	pric=$.MoneyToNum($("#pric").val());
				course_discount=$("#course_discount").val();
				$("#chaPrice").val($.formatMoney(pric-pric*course_discount*0.1));
				$("#youhuiPrice").val($.formatMoney(pric*course_discount*0.1));
	    		return;
	    	}
	    	pric=$.MoneyToNum($("#pric").val());
			course_discount=$("#course_discount").val();
			$("#chaPrice").val($.formatMoney(pric-pric*course_discount*0.1));
			$("#youhuiPrice").val($.formatMoney(pric*course_discount*0.1));
	     }).on('blur',function(){
	    	if ($("#course_discount").val()>10) {
	    		$.msg("折扣不能超过10");
	    		$("#course_discount").val(10);
    		 	pric=$.MoneyToNum($("#pric").val());
				course_discount=$("#course_discount").val();
				$("#chaPrice").val($.formatMoney(pric-pric*course_discount*0.1));
				$("#youhuiPrice").val($.formatMoney(pric*course_discount*0.1));
	    		return;
	    	}
	    	pric=$.MoneyToNum($("#pric").val());
			course_discount=$("#course_discount").val();
			$("#chaPrice").val($.formatMoney(pric-pric*course_discount*0.1));
			$("#youhuiPrice").val($.formatMoney(pric*course_discount*0.1));

	     }).on('change',function(){
	    	if ($("#course_discount").val()>10) {
	    		$.msg("折扣不能超过10");
	    		$("#course_discount").val(10);
    		 	pric=$.MoneyToNum($("#pric").val());
				course_discount=$("#course_discount").val();
				$("#chaPrice").val($.formatMoney(pric-pric*course_discount*0.1));
				$("#youhuiPrice").val($.formatMoney(pric*course_discount*0.1));
	    		return;
	    	}
	    	pric=$.MoneyToNum($("#pric").val());
			course_discount=$("#course_discount").val();
			$("#chaPrice").val($.formatMoney(pric-pric*course_discount*0.1));
			$("#youhuiPrice").val($.formatMoney(pric*course_discount*0.1));

	     });
		
	})
	.on('click.classTypeNotMember','.classTypeNotMember',function(){
		$this=$(this);
		if($(this).hasClass("disabled")){
			$(this).removeClass("disabled");
			addClassType($this.find("input").val());
			$(this).find(".top-right-tag").find(".choosedid").remove();
			return;
		}
		$this.addClass("disabled");
		addClassType($this.find("input").val());
		$this.find(".top-right-tag").append('<span class="choosedid">已选择<i class="iconfont">&#xe68f;</i></span>');

	})
	.on('click.changePrice','.changePrice',function(){
//		$("#usediscount").click();
	
		
		

		$(".add-page-pop").fadeIn(100);
		classTypeid=$(this).find("input").val();
		$.ajax({
			url: rootPath+"/classType/queryMemberLevelAndClassTypeVoByClassId",
			type: "post",
			data: {"classTypeId":classTypeid,"memberId":$("#memberId").val()},
			success: function(jsonData){
				if(jsonData.discountType!=null){
					if(jsonData.discountType == 0){
						$("#freelook").click();
					}else{
						$("#usediscount").click();
					}
				}
				$("#pric").val($.formatMoney(jsonData.originalPrice));				
				$("#pric").append('<input type="hidden" value="'+classTypeid+'" />');
				$("#course_discount").val(jsonData.memberDiscount);
				$("#chaPrice").val($.formatMoney(jsonData.originalPrice-jsonData.originalPrice*jsonData.memberDiscount*0.1));
				$("#youhuiPrice").val($.formatMoney(jsonData.originalPrice*jsonData.memberDiscount*0.1));
				$("#discount_classTypeId").val(classTypeid);
			}
		})
		
	})
	.on('click.delCourse','.delCourse',function(){
		
		classId=$(this).find("input").val();
		$.confirm("您确认要将此课程移除会员课程列表？",function(a){
			if(a){
				id=$("#memberId").val();
				$.ajax({
					url: rootPath+"/classType/delClassTypeForMember",
					type: "post",
					dataType:"text",
					data: {"memberId":id,"id":classId},
					success: function(jsonData){
						if(jsonData=="true"){
							$.msg("移除课程成功");
							queryMemberLevelAndClassType($("#page1").val());
						}else{
							$.msg("移除课程失败");
						}
					
					}
				})
				
			}
		});
		
	})
	
	
	function checkClassTypeChoosed(){
		idstr="";
		for(var i=0;i<idsArray.length;i++){
			idstr+=idsArray[i]+",";
		}
	}
	function addClassType(id){
		
		islive=false;
		for(var i=0;i<idsArray.length;i++){
			if(id==idsArray[i]){
				idsArray[i]="";
				islive=true;
			}
		}
		if(!islive){
			idsArray.push(id);
		}
	}
	
	function isLive(id){
		for(var i=0;i<idsArray.length;i++){
			if(id==idsArray[i]){
				return true;
			}
		}
		return false;
	}
	
 	 $(".yes-btn").on("click",function(){
 		if($("#dclick").val()==0){
 		checkClassTypeChoosed();
 		if (idsArray.length<=0) {
			$.msg("请先选择课程!");
			return;
		}
 		$("#dclick").val(1);
 		$.ajax({
			url: rootPath+"/classType/saveClassTypeForMember",
			type: "post",
			datatype:"text",
			data: {"memberId":$("#memberId").val(),"ids":idstr.length>0?idstr.substring(0,idstr.length-1):""},
			success: function(jsonData){
				if(jsonData=='true'){
					 $.msg("添加课程成功!",function(){
				        	$(".choice-class-pop").fadeOut(200);
				        	queryMemberLevelAndClassType(0);
                     });
				}else{
					$.msg("添加课程失败!");
				}
			}
		});
 		}
     })
	
	function queryMemberLevelAndClassType(page_1){
 		$(".memberClassList").html("");
 	   if(page_1){
 			page1=page_1;
 		}else{
 			page1=0;
 		}
 		$.ajax({
 			url: rootPath+"/classType/queryMemberLevelAndClassType",
 			type: "post",
 			data:{"page":page1,"memberId":$("#memberId").val()},
 			dataType: "json",
		    beforeSend: function (XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
 			success: function(jsonData){
 				if(jsonData.data.length==0){
 					if(page1>=2){
 						$("#page1").val(page_1-1);
 						queryMemberLevelAndClassType(page_1-1);
 					}else{
 						$("#page1").val(1);
 						queryMemberLevelAndClassType(1);
 					}
 					return false;
 				}
 				$.each(jsonData.data, function (index, value) {
 					var typeflag1="";
				  if(value.liveFlag==1&&value.videoFlag==0&&value.faceFlag==0){
					  typeflag1='<span class="play-status blue-bg">直播</span>';
				  }else if(value.videoFlag==1&&value.faceFlag==0&&value.liveFlag==0){
					  typeflag1='<span class="play-status yellow-bg">录播</span>';
				  }else if(value.faceFlag==1&&value.liveFlag==0&&value.videoFlag==0){
					  typeflag1='<span class="play-status blue-bg">面授</span>';
				  }else if(value.faceFlag==0&&value.liveFlag==0&&value.videoFlag==0){
					  typeflag1='<span class="play-status blue-bg">其他</span>';
				  }else{
					  typeflag1='<span class="play-status red-bg">混合</span>';
				  }
				  ownVipPrice='';
				  ownVipDiscount='';
				  if($("#discountType").val()==1){
					  ownVipPrice=  '        <em class="orange-b changePrice">自定义会员价 <input type="hidden" name="classTypeId2" value="'+value.classTypeId+'"/></em> ';
					  ownVipDiscount= '         会员优惠：'+value.memberDiscount+'折';
				  }
 					$(".memberClassList").append('<div class="pop-course"> '+
 							 '       <img src="'+(value.cover?value.cover:(rootPath+'/images/overview_demo.jpg'))+'" alt="" width="19%" height="100px"/> '+
 	                        '      <div class="course-manage-cont"> '+
 	                        '       <div class="top-right-tag"></div> '+
 	                            '        <div class="course-manage-title" style="line-height: 30px"> '+
 	                            '         <span>'+value.name+'</span> '+
 	                                '        <em class="gray-b delCourse">移除课程 <input type="hidden" name="classTypeId" value="'+value.id+'"/></em> '+
 	                               ownVipPrice +
 	                                '        <em class="moved">原价 <s>'+$.formatMoney(value.originalPrice)+'</s> 元</em> '+
 	                                '      </div> '+
 	                            '       <div class="course-manage-bottom"> '+
 	                         	  typeflag1+
 	                                '           <span class="number-box vip-price"> '+
 	                               ownVipDiscount +'   会员价   <em>'+$.formatMoney((value.originalPrice*value.memberDiscount*0.1))+' </em> 元 '+
 	                                '        </span> '+
 	                            '       </div> '+
 	                            '   </div> '+
 	                   ' </div>'); 
 					
 	            });
 			
 		
 				if(jsonData.rowCount>3){
 					$(".pagination1").pagination(jsonData.rowCount, {
 						 next_text: "下一页",
 			             prev_text: "上一页",
 			             current_page: jsonData.pageNo - 1,
 			             link_to: "javascript:void(0)",
 			             num_display_entries: 8,
 			             items_per_page: jsonData.pageSize,
 			             num_edge_entries: 1,
 			             callback: function (page1, jq) {
 			                 var pageNo1 = page1 + 1;
 			                $("#page1").val(pageNo1);
 			                queryMemberLevelAndClassType(pageNo1);
 			             }
 				   });
 				
 				}else{
 					$(".pagination1").html('');
 				}
//  				if(jsonData.data.length==0){
// 					$(".memberClassList").append('<div class="no-content-prompt">此会员等级还没有添加课程</div>');
// 				}
 			},
		   complete: function (XMLHttpRequest, textStatus) {
                $(".loading").hide();
                $(".loading-bg").hide();
            }

 		})
 	 }
 	 $(".cancle_qx").on("click", function () {
         $(".add-page-pop").fadeOut(200);
     });
     $(".page-pop-title em").on("click", function () {
         $(".add-page-pop").fadeOut(200);
     });
     $(".print_prices").bind("keyup",function(event){
     	$(this).removeClass("printErorColor").removeClass("printYesColor").addClass("printColor");
     	//先把非数字的都替换掉，除了数字和. 
		$(this).val($(this).val().replace(/[^\d.]/g,""));
        //必须保证第一个为数字而不是. 
        $(this).val($(this).val().replace(/^\./g,"0."));
        //保证只有出现一个.而没有多个. 
        $(this).val($(this).val().replace(/\.{2,}/g,"."));
        //保证.只出现一次，而不能出现两次以上
        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
         if($(this).val()>10||$(this).val()<0){
         	$(this).removeClass("printColor").removeClass("printYesColor").addClass("printErorColor");
         }
 	}).on('blur',function(){
 		$(this).removeClass("printColor").removeClass("printErorColor").addClass("printYesColor");
 	});
   
     
      $(".save_member_set").on('click',function(){
    	 var d=$("#course_discount").val();
    	 classType_id=$(this).find("input").val();
    	 var id=$(this).attr("ids");
    	 if(d>10 || d<0){
    		 $.msg("折扣输入不正确，请输入0-10之间的数字");
    		 return;
    	 }
    	 $.ajax({
				url: rootPath+"/classType/changeDiscount",
				type: "post",
				dataType:"text",
				data: {"discount":$("#course_discount").val(),"classTypeId":classType_id,"memberId":$("#memberId").val()},
				success: function(jsonData){
					if(jsonData=="true"){
						$.msg("修改成功!");
						 queryMemberLevelAndClassType($("#page1").val());
					}else{
						$.msg("修改失败!");
						 queryMemberLevelAndClassType($("#page1").val());
					}
				}
			})

    	 $(".add-page-pop").fadeOut(200);
    	
     });
	$(".member_ClassType").on("click",function(){
		window.location.href=rootPath+"/classType/memberClass";
	})