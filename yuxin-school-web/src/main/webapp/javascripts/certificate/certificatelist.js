;(function($){
    var Page = function(){}
    Page.prototype = {
    	init : function(){
    		$this = this;
    		$this.searchCertificate();
    		$this.clickFunction();
    		
    	},
    	searchCertificate:function(page){
    		$this = this;
    		var search = {};
    		var name = $('.state-right').find('input[type="text"]').val();
    		search.name = name;
    		var type = $('.state-left').find('span').siblings('.onAc');
    		
    		if( type && type.html()=='启用'){
    			search.status = 1;
    		}
    		else if(type && type.html()=='禁用'){
    			search.status = 0;
    		}
    		else
    			search.status = null;
    		search.page = page?page:1;
    		$('.state-list').html('');
    		$('.set-system').find(".pagination").html('');
    		$('.hiddenPageNo').val(page);
    		$.ajax({
    			url:rootPath+"/certificateConfig/certificateList",
    			type:"post",
    			data:search,
    			dataType:"json",
    			success:function(jsonData){
    				if(jsonData.data && jsonData.data.length>0){
    					$.each(jsonData.data,function(i,data){
    						$('.state-list').append('<li class="clear"><input type="hidden" class="hid" value="'+data.id+'">'+
                            '<div class="list-state">'+
                                '<p class="listTil">证书名称：<b>'+data.name+'</b></p>'+
                                '<p class="listTim">创建日期：<b>'+data.createTime+'</b></p>'+
                                (data.status==0 ? '<p class="listSta ">证书状态：<b class="prev">禁用</b></p>' : '<p class="listSta ">证书状态：<b>启用</b></p>')+
                                '<p class="listNum">发放数量：<b>'+data.releaseNum+'</b></p>'+
                            '</div>'+
                            '<div class="state-btn">'+
                                '<span>编辑</span>'+
                                (data.status==0?'<span>启用</span>':'<span class="stae-prev">禁用</span>')+
                                '<span class="listDel">删除</span>'+
                                '<span class="last">发放</span>'+
                            '</div>'+
                        '</li>');
						})
						$('.set-system').find(".pagination").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1;
						    	 $this.searchCertificate(pageNo);
					    	 }
					   });
						
    				}else{
    					$('.state-list').append('没有查到证书');
    				}
    			}
    		})
    	},
    	realeseCertificate:function(page,cerId){
    		var $this = this;
    		var search = {};
    		search.cerId = cerId;
    		search.courseId = $('#relatedCourses option:selected').val();
    		search.studyStatus = $('#studyStatus option:selected').val();
    		search.releaseStatus = $('#releaseStatus option:selected').val();
    		search.stuName = $('#stuName').val();
    		search.mobile = $('#mobile').val();
    		search.username = $('#username').val();
    		search.startTime = $('.from').val();
    		search.endTime = $('.to').val();
    		search.page=page?page:1;
    		if ($(".to").val() != "") {
                if ($(".to").val() < $(".from").val()) {
                    $.msg("时间范围不正确");
                    return;
                }

            }
            $.each(search, function (key, value) {
                if (!value) {
                    delete search[key];
                }
            })
            
            $("#userTable").find("tr:gt(0)").remove();
            $(".checkboxAll").prop("checked", false);
            $.ajax({
            	url:rootPath+"/certificateCourseRelation/releaseList",
            	type:"post",
            	data:search,
            	dataType:"json",
            	beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
            	success:function(jsonData){
            		if (jsonData.data.length == 0) {
                        $("#userTable")
                            .append(
                            '<tr><td colspan="9">没有查找到数据</td></tr>');
                    }
            		else if(jsonData.data.length>0){
            			$('#userTable').find('tbody').find('tr:gt(0)').html('');
            			$.each(jsonData.data,function(i,data){
            				$('#userTable').find('tbody').append(
            					'<tr><td>'+(data.isRelease?'<input type="checkbox"  courseId="'+data.courseId+'" userId="'+data.userId+'" class="signUpMany">':'<input type="checkbox" courseId="'+data.courseId+'" userId="'+data.userId+'">')+'<input type="hidden" class="hiddenCerId" value="'+data.cerId+'">'+'<input type="hidden" class="hiddenCourseId" value="'+data.courseId+'">'+'<input type="hidden" class="hiddenUserId" value="'+data.userId
            					+'"></td>'+	
            					'<td> '+(data.courseName?data.courseName:"")+'</td>'+
            					'<td>'+(data.stuName?data.stuName:"")+'</td>'+
            					'<td>'+(data.mobile?data.mobile:"")+'</td>'+
            					'<td>'+(data.username?data.username:"")+'</td>'+
            					'<td>'+(data.totalLectureNum - data.passLectureNum ==0 ? '已学完':'未学完')+'</td>'+
            					'<td>'+(data.userNum > 0? '已发放':'未发放')+'</td>'+
            					'<td> '+(data.receiveTime?data.receiveTime:"")+'</td>'+
            					'<td>'+(data.userNum > 0?'<a href="javascript:;" class="viewCertificate">查看</a>':(data.totalLectureNum - data.passLectureNum ==0 ? '<a href="javascript:;" class="releaseCertificate">发放</a>': '<a  disabled="disabled" class="userState">发放</a>'))+'</td>'+
            					'</tr>');
            			});
            		}
            		if (jsonData.rowCount > 10) {
                        $(".pagination1").pagination(jsonData.rowCount,
                            {
                                next_text: "下一页",
                                prev_text: "上一页",
                                current_page: jsonData.pageNo - 1,
                                link_to: "javascript:void(0)",
                                num_display_entries: 8,
                                items_per_page: jsonData.pageSize,
                                num_edge_entries: 1,
                                callback: function (page, jq) {
                                    var pageNo = page + 1;
                                    $this.realeseCertificate(pageNo,cerId);
                                }
                            });
                    } else {
                        $(".pagination1").html('');
                    }
            		
            	},
            	complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            })
    		
    	},
    	
    	viewCertificate:function(page){
    		var $this = this;
    		var $this = this;
    		var search = {};
    		search.cerId = $('select[name="cerId"] option:selected').val();
    		search.status = $('#viewCertificateStatus option:selected').val();
    		search.releaseStatus = $('#viewReleaseStatus option:selected').val();
    		search.stuName = $('#stuName1').val();
    		search.mobile = $('#mobile1').val();
    		search.username = $('#userName1').val();
    		search.startTime = $('.from1').val();
    		search.endTime = $('.to1').val();
    		search.page=page?page:1;
    		$('.hiddenViewCertificatePage').val(page);
    		if ($(".to").val() != "") {
                if ($(".to").val() < $(".from").val()) {
                    $.msg("时间范围不正确");
                    return;
                }

            }
            $.each(search, function (key, value) {
                if (!value) {
                    delete search[key];
                }
            })
            $("#viewCertificate").find("tr:gt(0)").remove();
            $('select[name="cerId"]').find("option:gt(0)").remove();
            $(".pagination2").html('');
            $.ajax({
            	url:rootPath+"/certificateUserRelation/viewCertificateList",
            	type:"post",
            	data:search,
            	dataType:"json",
            	beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
            	success:function(jsonData){
            		if (jsonData.data.length == 0) {
                        $("#viewCertificate")
                            .append(
                            '<tr><td colspan="7">没有查找到数据</td></tr>');
                    }
            		else if(jsonData.data.length>0){
            			$.each(jsonData.data,function(i,data){
            				$('#viewCertificate').append(
            					'<tr><td>'+(data.cerName?data.cerName:'')+'<input type="hidden" value="'+data.cerId+'">'+
            					'</td>'+	
            					'<td> '+(data.status == 1?"启用":"禁用")+'</td>'+
            					'<td>'+(data.stuName?data.stuName:"")+'</td>'+
            					'<td>'+(data.mobile?data.mobile:"")+'</td>'+
            					'<td>'+(data.username?data.username:"")+'</td>'+
            					'<td>'+(data.releaseStatus > 0? '已发放':'未发放')+'</td>'+
            					'<td> '+(data.receiveTime?data.receiveTime:"")+'</td>'+
            					'</tr>');
            			});
            		}
                        $(".pagination2").pagination(jsonData.rowCount,
                            {
                                next_text: "下一页",
                                prev_text: "上一页",
                                current_page: jsonData.pageNo - 1,
                                link_to: "javascript:void(0)",
                                num_display_entries: 8,
                                items_per_page: jsonData.pageSize,
                                num_edge_entries: 1,
                                callback: function (page, jq) {
                                    var pageNo = page + 1;
                                    $this.viewCertificate(pageNo);
                                }
                            });
            	},
            	complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            })
    		
            $.ajax({
            	url:rootPath+"/certificateUserRelation/releaseCount",
            	type:"post",
            	data:search,
            	dataType:"json",
            	success:function(jsonData){
            		$('#releaseCount').text(jsonData);
            	}
            })
            
            $.ajax({
    		    		   url:rootPath+"/certificateConfig/queryAllCertificate",
    		    		   type:"post",
    		    		   dataType:"json",
    		    		   data:{},
    		    		   success:function(jsonData){
    		    			   $.each(jsonData,function(i,data){
    		    				   $('select[name="cerId"]').append('<option value="'+data.id+'">'+data.name+'</option>');
    		    			   })
    		    		   }
    		    	   })
    		    	   
    	},
    	clickFunction:function(){
    		$this = this;
    		$('.state-left').on('click','button',function(){
    			$this.searchCertificate();
    		})
    		$('.stateSearch').on('click',function(){
    			$this.searchCertificate();
    		})
    		$('.state-list').on('click','span',function(){
    	        var index=$(this).index();
    	        switch(index){
    	            //编辑按钮
    	            case 0:
    	                $('.layer-tips-bg').css('display','block');
    	                $('#L-allowAdmissionsTc').css('display','block');
    	                var cid = $(this).parent().siblings().eq(0).val();
    	                $('.stateName').val($(this).parent().siblings('.list-state').find('.listTil').find('b').text());
    	                $('.addhiddenCerId').val(cid);
    	                var search = {};
    	                search.cerId = cid;
    	                $.ajax({
    	                	url:rootPath+"/certificateCourseRelation/queryClassTypesBycourseId",
    	                	type:"post",
    	                	data:search,
    	                	dataType:"json",
    	                	success:function(jsonData){
    	                		if(jsonData && jsonData.length>0){
    	                			$('.optgroup').find('.ckeckOption').html('');
    	                			$.each(jsonData,function(i,data){
    	                				$('.optgroup').find('.ckeckOption').append('<li><input type="hidden" class="hiddenRelationId" value="'+data.relationId+'">'+
    	                		                '<select name="choose_itemOne" class="itemOne" id="choose_itemOne'+i+'">'+
    	                		                '<option value="'+data.itemOneId+'">'+data.itemOneName+'</option>'+
    	                		            '</select>'+
    	                		            '<select name="choose_itemSecond" class="itemTwo" id="choose_itemSecond">'+
    	                		                '<option value="'+data.itemSecondId+'">'+data.itemSecondName+'</option>'+
    	                		            '</select>'+
    	                		            '<select name="choose_itemThird" class="itemThree" id="choose_itemThird">'+
    	                		            '<option value="'+data.id+'">'+data.name+'</option>'+
    	                		             '</select>'+  
    	                		            '<i class="iconfont iSubtract">&#xe6dc;</i>'+
    	                		            '</li>'
    	                		         );
    	                				$("#choose_itemOne"+i).getSysItem(function(){
    	                					$("#choose_itemOne"+i).find("option[value='"+data.itemOneId+"']").attr("selected","selected");
    	                					$("#choose_itemOne"+i).trigger('change');
    	                				});
    	                				
    	    						})
    	                		}
    	                	}
    	                })
    	                break;

    	            //启用，禁用按钮
    	            case 1:
    	                if($(this).hasClass('stae-prev')){
    	                    $(this).html('启用').removeClass('stae-prev');
    	                    var cid = $(this).parent().siblings().eq(0).val();
    	                    var item = {};
    	                    item.id = cid;
    	                    item.status = 0;
    	                    $.ajax({
    	                    	url:rootPath+"/certificateConfig/saveOrUpdateCertificateConfig",
    	                    	type:"post",
    	                    	data:item,
    	                    	dataType:"json",
    	                    	success:function(data){
        	                    	$this.searchCertificate($('.hiddenPageNo').val());
    	                    	}
    	                    })
    	                }else{
    	                    $(this).html('禁用').addClass('stae-prev');
    	                    var cid = $(this).parent().siblings().eq(0).val();
    	                    var item = {};
    	                    item.id = cid;
    	                    item.status = 1;
    	                    $.ajax({
    	                    	url:rootPath+"/certificateConfig/saveOrUpdateCertificateConfig",
    	                    	type:"post",
    	                    	data:item,
    	                    	dataType:"json",
    	                    	success:function(data){
    	                    		$this.searchCertificate($('.hiddenPageNo').val());
    	                    	}
    	                    })
    	                }
    	                break;

    	            //删除按钮
    	            case 2:
    	            	var userCount = $(this).parent().siblings('.list-state').find('.listNum').find('b').text();
    	                if(userCount != '0'){
    	                    //$('.L-allowAdmissionsTc').eq(4).css('display','block');
    	                	//$('.layer-tips-bg').css('display','block');
    	                	$.alert('您要删除的证书已经有发放记录，无法删除');
    	                }else{
    	                    //$('.L-allowAdmissionsTc').eq(3).css('display','block');
    	                	//$('.layer-tips-bg').css('display','block');
    	                	var cid = $(this).parent().siblings().eq(0).val();
    	                	$.confirm('确定要删除该证书吗？',function(b){
    	                		if(b){

        	                    	var item = {};
            	                    item.id = cid;
            	                    item.delFlag = 1;
            	                    $.ajax({
            	                    	url:rootPath+"/certificateConfig/saveOrUpdateCertificateConfig",
            	                    	type:"post",
            	                    	data:item,
            	                    	dataType:"json",
            	                    	success:function(data){
            	                    		$.msg('删除成功');
            	                    		$this.searchCertificate();
            	                    		//$('.L-allowAdmissionsTc').eq(3).css('display','none');
            	    	                    $('.layer-tips-bg').css('display','none');
            	                    	}
            	                    })
        	                    
    	                		}
    	                	})
    	                    //s$('.layer-tips-bg').css('display','block');
    	                   /* var cid = $(this).parent().siblings().eq(0).val();
    	                    $('#confirmCancel').on('click',function(){
    	                    	var item = {};
        	                    item.id = cid;
        	                    item.delFlag = 1;
        	                    $.ajax({
        	                    	url:rootPath+"/certificateConfig/saveOrUpdateCertificateConfig",
        	                    	type:"post",
        	                    	data:item,
        	                    	dataType:"json",
        	                    	success:function(data){
        	                    		$.msg('删除成功');
        	                    		$this.searchCertificate();
        	                    		$('.L-allowAdmissionsTc').eq(3).css('display','none');
        	    	                    $('.layer-tips-bg').css('display','none');
        	                    	}
        	                    })
    	                    })*/
    	                    
    	                    
    	                }
    	                break;

    	            //发放按钮
    	            case 3:
    	            	$tt = $(this);
    	                $('.L-right-side').each(function(index,ele){
    	                    if(index>=2){
    	                        $(ele).css('display','block');
    	                        var cerId = $tt.parent().siblings().eq(0).val();
    	                        $('.hiddenReleaseSearch').val(cerId);
    	                        if(cerId){
    	                        	$.ajax({
    	                        		url:rootPath+"/certificateCourseRelation/toReleasePage",
    	                        		type:"post",
    	                        		data:{"cerId":cerId},
    	                        		dataType:"json",
    	                        		success:function(data){
    	                        			if(data&&data.modelMap.courseList.length>0){
    	                        				$.each(data.modelMap.courseList,function(i,dd){
    	                        					$('.reCourses').append('<option value="'+dd.courseId+'">'+dd.courseName+'</option>');
    	                        				});
    	                        				$('#certificateName').text(data.modelMap.certificateName);
    	                        				$('.releasePageHiddenCerId').val(data.modelMap.cerId);
    	                        			}
    	                        		}
    	                        	})
    	                        	$("#userTable").find("tr:gt(0)").remove();
    	                        	$this.realeseCertificate('',cerId);
    	                        }
    	                    }else{
    	                        $(ele).css('display','none');
    	                    }
    	                });
    	                $(this).prev().attr('data-existence','existence');
    	                break;
    	        }
    	    });
    		
    		$('.couponCreate').eq(0).click(function(){
    	        $('.layer-tips-bg').css('display','block');
    	        $('#L-allowAdmissionsTc').css('display','block');
    	        $('#errormsg').text('');
    	        $('.addhiddenCerId').val(null);
    	        $('.stateName').val('');
    	        $('.ckeckOption').html('');
    	    });
    		
    		 $('#abind').on('click',function(){
    		    	$('.ckeckOption').append('<li>'+
    		                '<select name="choose_itemOne" >'+
    		                '<option value="-1">学科</option>'+
    		            '</select>'+
    		            '<select name="choose_itemSecond" >'+
    		                '<option value="-1">学科小类</option>'+
    		            '</select>'+
    		            '<select name="choose_itemThird" >'+
    		            '<option value="-1">课程名称</option>'+
    		             '</select>'+  
    		            '<i class="iconfont iSubtract">&#xe6dc;</i>'+
    		            '</li>'
    		         );
    		         
    		         $(".optgroup").find('select[name="choose_itemOne"]').last().getSysItem();
    		         $(".optgroup").find('select[name="choose_itemOne"]').last().find('option:eq(0)').remove();
    		         $(".optgroup").find('select[name="choose_itemOne"]').last().find('option:eq(0)').before('<option value="-1">学科</option>');
    		         $(".optgroup").find('select[name="choose_itemOne"]').last().find('option[value="-1"]').attr('selected','selected');
    		    });
    		 
    		 	$(".optgroup").on("change",'select[name="choose_itemOne"]',function(){
    		 		if($(this).siblings('.hiddenRelationId')){
						var secondId = $(this).next('select[name="choose_itemSecond"]').find('option').eq(0).val();
					}
					$(this).next('select[name="choose_itemSecond"]').eq(0).getSysItem($(this).val(),function(){
					});
					if(secondId){
						$(this).next('select[name="choose_itemSecond"]').eq(0).find('option[value='+secondId+']').attr('selected','selected');
					}
					$(this).next('select[name="choose_itemSecond"]').eq(0).find('option:eq(0)').remove();
					$(this).next('select[name="choose_itemSecond"]').eq(0).trigger('change');
				});
				$(".optgroup").on("change",'select[name="choose_itemSecond"]',function(){
					if($(this).siblings('.hiddenRelationId')){
						var thirdId = $(this).next('select[name="choose_itemThird"]').find('option').eq(0).val();
					}
					var $th = $(this);
					var search = {};
					search.itemSecondId = $(this).val();
					search.itemOneId = $(this).prev('select[name="choose_itemOne"]').eq(0).val();
					
					$.ajax({
						url:rootPath+"/simpleClasses/queryClassTypeByOneAndTwoItem",
						type:"post",
						dataType:"json",
						data:search,
						success:function(jsonData){
							if(jsonData&&jsonData.length>0){
								$th.siblings('select[name="choose_itemThird"]').eq(0).html('');
								$.each(jsonData,function(i,data){
									$th.next('select[name="choose_itemThird"]').eq(0).
		    						append('<option value="'+data.id+'">'+data.name+'</option>');
								})
								if(thirdId){
									$th.siblings('select[name="choose_itemThird"]').eq(0).find('option[value='+thirdId+']').attr('selected','selected');
								}
							}else{
								$th.siblings('select[name="choose_itemThird"]').eq(0).html('<option value=""></option>')
							}
						}
					})
				});
    		    $(".optgroup").on('click','.iSubtract',function(){
							$(this).parent().remove();
							/*var item = {};
							var cid = $('.addhiddenCerId').val();
							if(cid)
								item.cerId = cid;
							item.id = $(this).siblings('.hiddenRelationId').eq(0).val();
							var rid = $(this).siblings('select[name="choose_itemThird"]').eq(0).val();
							item.courseId = rid;
							$('#saveConfig').on('click',function(){
								if(item.id&&cid && rid && rid != -1){
									$.ajax({
										url:rootPath+"/certificateCourseRelation/deleteByCerIdAndCourseId",
										type:"post",
										data:item,
										dataType:"json",
										success:function(data){
										}
									})
								}
							})*/
							$(this).parent().css('display','none');
    		    })
    		    
    		    $('.stateName').on('change',function(){
    		    	$that = $(this);
    		    	if($(this).val() != ''){
    		    		var name = $(this).val();
    		    		var search = {};
    		    		search.name = name;
    		    		$.ajax({
    		    			url:rootPath+"/certificateConfig/checkNameIsExist",
    		    			type:"post",
    		    			data:search,
    		    			dataType:"json",
    		    			success:function(data){
    		    				if(data&&data=="true"){
    		    					$('#errormsg').text('证书名称已存在');
    		    					$that.focus();
    		    					return ;
    		    				}else{
    		    					$('#errormsg').text('');
    		    				}
    		    			}
    		    		})
    		    	}
    		    })
    		    $('#saveConfig').on('click',function(){
    		    	var arr = [];
    		    	var item = {};
    		    	var itemId = $('.addhiddenCerId').val();
    		    	if(itemId)
    		    	item.id = itemId;
    		    	if($('#errormsg').text()!= ''){
    		    		$.msg('证书名称已存在，请重新输入');
    		    		$('.stateName').val('').focus();
    		    		return ;
    		    	}
    		    	if($('.stateName').val() == ''){
    		    		$.msg('证书名称不能为空');
    		    		$('.stateName').focus();
    		    		return;
    		    	}
    		    	if($('.stateName').val().length > 20){
    		    		$.msg('证书名字过长（限输入20个字符）');
    		    		$('.stateName').val('');
    		    		$('.stateName').focus();
    		    		return;
    		    	}
    		    	item.name = $('.stateName').val();
    		    	var courses = $('.ckeckOption').find('li');
    		    	if(courses == null || courses.length ==0){
    		    		$.msg('请绑定课程！');
    		    		return;
    		    	}else{
    		    		for(var x=0;x<courses.length;x++){
    		    			if(courses[x]){
    		    				var children=courses[x].childNodes;
    		    				if(children.length == 5){
    		    					var itemOne = $(children[1]).find('option:selected').val();
    		    					var itemSecond = $(children[2]).find('option:selected').val();
    		    					var itemThird = $(children[3]).find('option:selected').val();
    		    				}else if(children.length == 4){
    		    					var itemOne = $(children[0]).find('option:selected').val();
    		    					var itemSecond = $(children[1]).find('option:selected').val();
    		    					var itemThird = $(children[2]).find('option:selected').val();
    		    				}
    		    				if(itemOne == undefined || itemOne == "" || itemOne == -1 || itemSecond == undefined || itemSecond == "" || itemSecond == -1 || itemThird == '' || itemThird == -1 || itemThird == undefined){
    		    					$.msg('请选择完整的课程（包含学科和学科小类和课程名称）');
    		    					return ;
    		    				}else{
    		    					if(arr.length >0){
    		    						for(var k=0;k<arr.length;k++){
    		    							if(arr[k]==itemThird){
    		    								$.msg('课程不能重复');
    		    								return;
    		    							}
    		    						}
    		    					}
    		    					arr.push(itemThird);
    		    				}
    		    			}
    		    		}
    		    	}
    		    	$.ajax({
	    				url:rootPath+"/certificateConfig/saveOrUpdateCertificateConfig",
	    				type:"post",
	    				data:item,
	    				dataType:"json",
	    				success:function(jsonData){
	    						if(jsonData && jsonData.id && arr && arr.length >0){
	    							for(var j=0;j<arr.length;j++){
	    								var relation = {};
	    								relation.cerId = jsonData.id;
	    								relation.courseId = arr[j];
	    								$.ajax({
	    									url:rootPath+"/certificateCourseRelation/saveOrUpdateCertificateCourseRelation",
	    									type:"post",
	    									data:relation,
	    									dataType:"json",
	    									success:function(data){
	    									}
	    								})
	    							}
	    						}
	    						$.msg('保存成功');
	    						$this.searchCertificate();
	    						$('.layer-tips-bg').css('display','none');
	    		    	        $('#L-allowAdmissionsTc').css('display','none');
	    				}
	    			})
    		    })
    		    
    		    $('#releaseSearch').on('click',function(){
    		    	var cid = $('.hiddenReleaseSearch').val();
    		    	$("#userTable").find("tr:gt(0)").remove();
    		    	$this.realeseCertificate('', cid);
    		    })
    		    
    		    $('#userTable').on('click','.viewCertificate',function(){
    		    	var userId = $(this).parent().siblings().eq(0).find('.hiddenUserId').val();
    		    	var classId = $(this).parent().siblings().eq(0).find('.hiddenCourseId').val();
    		    	var cerId = $('.releasePageHiddenCerId').val();
    		    	/*$.ajax({
    		    		url:rootPath+"/certificateConfig/queryCertificate",
    		    		type:"post",
    		    		data:{"userId":userId,"classId":classId,"cerId":cerId},
    		    		dataType:"json",
    		    		success:function(data){
    		    			
    		    		}
    		    	})*/
    		    	CerPage.queryCertificate(userId,cerId,classId);
    		    	$('.layer-tips-bg').css('display','block');
    		    	$('.certificate-alert').css('display','block');
    		    })
    		    
    		    $("#userTable").on('click','.releaseCertificate',function(){
    		    	var item={};
    		    	var cid = $('.releasePageHiddenCerId').val();
    		    	item.courseId = $(this).parent().siblings().eq(0).find('.hiddenCourseId').val();
    		    	item.userId = $(this).parent().siblings().eq(0).find('.hiddenUserId').val();
    		    	item.cerId = $('.releasePageHiddenCerId').val();
    		    	$('.certificate').eq(0).css('display','block');
    		    	$('.layer-tips-bg').css('display','block');
    		    	$('#confrimRelease').on('click',function(){
    		    		$.ajax({
    		    			url:rootPath+"/certificateUserRelation/releaseCertificate",
    		    			type:"post",
    		    			data:item,
    		    			dataType:"json",
    		    			success:function(data){
    		    				$('.layer-tips-bg').css('display','none');
    		    				$('.certificate').css('display','none');
    		    				if(data!="success"){
    		    					$.msg('发放失败');
    		    					return;
    		    				}
    		    				$this.realeseCertificate('', $('.releasePageHiddenCerId').val());
    		    			}
    		    		})
    		    	})
    		    })
    		    
    		    $('#cancelRelease1').on('click',function(){
    		    	$('.layer-tips-bg').css('display','none');
    		    	$(this).parents('.L-allowAdmissionsTc').css('display','none');
    		    	
    		    })
    		    
    		    
    		    $('#cancelRelease').on('click',function(){
    		    	$('.layer-tips-bg').css('display','none');
    		    	$(this).parents('.L-allowAdmissionsTc').css('display','none');
    		    	
    		    })
    		    
    		    $(".checkboxAll").on('change', function () {
                    if ($(this).prop("checked")) {
                        $("#userTable").find(".signUpMany").prop("checked", true);
                    } else {
                        $("#userTable").find(".signUpMany").prop("checked", false);
                    }
                });
    		    
    		    $('#batchRelease').on('click',function(){
    		    	var flag = true;
    		    	var cid = $('.releasePageHiddenCerId').val();
    		    	var checks = $('#userTable').find('input:checkbox:checked');
    		    	if(checks.length == 0){
    		    		$.msg('请先选择要发放的学员');
    		    		flag = false;
    		    		return;
    		    	}else if(checks.length == 1&& $(checks[0]).hasClass('checkboxAll')){
    		    		$.msg('请选择要发放的学员');
    		    		flag = false;
    		    		return;
    		    	}else if(checks.length > 1&& $(checks[0]).hasClass('checkboxAll')){
    		    		checks.splice(0,1);
    		    	}
    		    	checks.each(function(i){
    		    		if(!$(checks[i]).hasClass('signUpMany')){
    		    			$.msg('请选择满足条件的学员(已学完并且证书没有发放的)');
    		    			flag = false;
    		    			return;
    		    		}
    		    	})
    		    	if(flag){
    		    		var arr = [];
        		    	checks.each(function(i){
        		    		var item = {};
        		    		item.courseId = $(checks[i]).attr('courseId');
        		    		item.userId = $(checks[i]).attr('userId');
        		    		item.cerId = $('.hiddenReleaseSearch').val();
        		    		arr.push(item);
        		    	})
        		    	$('.certificate').eq(1).css('display','block');
        		    	$('.layer-tips-bg').css('display','block');
        		    	$('#batchRelaseCer').on('click',function(){
        		    		$(arr).each(function(i){
        		    			$.ajax({
        		    				url:rootPath+"/certificateUserRelation/releaseCertificate",
        		    				type:"post",
        		    				data:arr[i],
        		    				dataType:"json",
        		    				success:function(data){
        		    					if(data="success"&&i==arr.length-1){
        		    						$('.certificate').eq(1).css('display','none');
        		    						$('.layer-tips-bg').css('display','none');
        		    						$.msg('批量发放成功');
        		    						$this.realeseCertificate('',cid);
        		    					}
        		    				}
        		    			})
        		    		})
        		    	})
    		    	}
    		    	
    		    })
    		    
    		    $('#screen-list li').click(function(index){
    		        $('#screen-list li').removeClass('active');
    		       $(this).addClass('active');
    		       $('.L-right-side').css('display','none');
    		       $('.L-right-side').eq($(this).index()).css('display','block');
    		       if($(this).index()==1){
    		    	   
    		    	   $this.viewCertificate("");
    		       }
    		       
    		   });
    		   
    		    $('#viewCertificateSearch').on('click',function(){
    		    	$this.viewCertificate("");
    		    })
    		    
    		    $('#exportExcel').on('click',function(){
    		    $("#viewCertificateForm").attr("action",
                             rootPath + "/certificateUserRelation/exportExcel")
                             .submit();
    		    })
    		    
    		    $('#cancelBatchRelaseCer').on('click',function(){
    		    	$('.certificate').eq(1).css('display','none');
    		    	$('.layer-tips-bg').css('display','none');
    		    })
    		    
    		    $("#screen-list").find('li').eq(0).click(function(){
    		    	$this.searchCertificate('');
    		    })
    		    
    		    $('#cancelBatchDel').on('click',function(){
    		    	$('#batchdelalert').css('display','none');
    		    	$('.layer-tips-bg').css('display','none');
    		    })
    		    /*$("#screen-list").find('li').eq(1).click(function(){
    		    	$this.viewCertificate('');
    		    })*/
    	}
    }
    $(document).ready(function(){
		var page=new Page();
		page.init();
	})
})(jQuery)