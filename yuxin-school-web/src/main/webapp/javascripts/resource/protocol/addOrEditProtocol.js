/**
 * author zhang.zx
 * 用户权限
 * 页面js封装
 */
(function($){
	
	var searchRelation=function(id,type,page){
		var $this = this
		if(type == '1'){
			$('#coursePackageDetail').show();
			var data = {};
			data.id = id;
			data.type = type;
			data.page = page?page:1;
			$('.coursePackageList').find('tr:gt(0)').remove();
			$('.cid').val(id);
			$('.ctype').val(type);
			$.ajax({
				url:rootPath+"/coursePotocolUserRelation/queryCourseOrPackageRelation",
				type:"post",
				data:data,
				dataType:'json',
				success:function(jsonData){
					if(!jsonData||jsonData.data.length==0){
						$('.coursePackageList').append('<tr><td colspan="4">暂无数据</td></tr>');
						return;
					}
					$.each(jsonData.data,function(i,item){
						$('.coursePackageList').append('<tr>'+
								'<td width="25%">'+(item.classPackageName)+'</td>'+
								'<td width="25%">'+(item.createTime)+'</td>'+
								'<td width="25%">'+(item.createUserName)+'</td>'+
								'<td width="25%">'+(item.releaseCount?item.releaseCount:0)+'</td>'+
								'<tr/>');
					})
					if (jsonData.rowCount > 2) {
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
                                    searchRelation(id,type,pageNo);
                                }
                            });
                    } else {
                        $(".pagination2").html('');
                    }
				}
			})
		}else{
			$('#courseDetail').show();
			var data = {};
			data.id = id;
			data.type = type;
			data.page = page?page:1;
			$('.cid').val(id);
			$('.ctype').val(type);
			$('.courseList').find('tr:gt(0)').remove();
			$.ajax({
				url:rootPath+"/coursePotocolUserRelation/queryCourseOrPackageRelation",
				type:"post",
				data:data,
				dataType:'json',
				success:function(jsonData){
					if(!jsonData||jsonData.data.length==0){
						$('.courseList').append('<tr><td colspan="4">暂无数据</td></tr>');
						return;
					}
					$.each(jsonData.data,function(i,item){
						$('.courseList').append('<tr>'+
								'<td width="25%">'+(item.classTypeName)+'</td>'+
								'<td width="25%">'+(item.createTime)+'</td>'+
								'<td width="25%">'+(item.createUserName)+'</td>'+
								'<td width="25%">'+(item.releaseCount?item.releaseCount:0)+'</td>'+
								'<tr/>');
					})
					if (jsonData.rowCount > 2) {
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
                                    searchRelation(id,type,pageNo);
                                }
                            });
                    } else {
                        $(".pagination1").html('');
                    }
				}
			})
		}
	}
	
	var searchHistory=function(id,type,page){

		if(type == '1'){
			var data = {};
			data.id = id;
			data.type = type;
			data.page = page?page:1;
			$('.coursePackageList').find('tr:gt(0)').remove();
			$.ajax({
				url:rootPath+"/coursePotocolUserRelation/queryHistoryCourseOrPackageRelation",
				type:"post",
				data:data,
				dataType:'json',
				success:function(jsonData){
					if(!jsonData||jsonData.data.length==0){
						$('.coursePackageList').append('<tr><td colspan="4">暂无数据</td></tr>');
						return;
					}
					$.each(jsonData.data,function(i,item){
						$('.coursePackageList').append('<tr>'+
								'<td width="25%">'+(item.classPackageName?item.classPackageName:"")+'</td>'+
								'<td width="25%">'+(item.createTime?item.createTime:"")+'</td>'+
								'<td width="25%">'+(item.createUserName?item.createUserName:"")+'</td>'+
								'<td width="25%">'+(item.releaseCount?item.releaseCount:0)+'</td>'+
								'<tr/>');
					})
					if (jsonData.rowCount > 10) {
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
                                    searchHistory(id,type,pageNo);
                                }
                            });
                    } else {
                        $(".pagination2").html('');
                    }
				}
			})
		}else{
			var data = {};
			data.id = id;
			data.type = type;
			data.page = page?page:1;
			$('.courseList').find('tr:gt(0)').remove();
			$.ajax({
				url:rootPath+"/coursePotocolUserRelation/queryHistoryCourseOrPackageRelation",
				type:"post",
				data:data,
				dataType:'json',
				success:function(jsonData){
					if(!jsonData||jsonData.data.length==0){
						$('.courseList').append('<tr><td colspan="4">暂无数据</td></tr>');
						return;
					}
					$.each(jsonData.data,function(i,item){
						$('.courseList').append('<tr>'+
								'<td width="25%">'+(item.classTypeName?item.classTypeName:"")+'</td>'+
								'<td width="25%">'+(item.createTime?item.createTime:"")+'</td>'+
								'<td width="25%">'+(item.createUserName?item.createUserName:"")+'</td>'+
								'<td width="25%">'+(item.releaseCount?item.releaseCount:0)+'</td>'+
								'<tr/>');
					})
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
                                    searchHistory(id,type,pageNo);
                                }
                            });
                    } else {
                        $(".pagination1").html('');
                    }
				}
			})
		}
	}
	$('select[name="type"]').on('change',function(){
		if($(this).val()==1){
			$('select[name="useTime"]').find('option').eq(1).hide();
		}else if($(this).val()==0){
			$('select[name="useTime"]').find('option').eq(1).show();
		}
	})
	
	$('select[name="useTime"]').on('change',function(){
		if($(this).val()==1){
			$('select[name="type"]').find('option').eq(1).hide();
		}else if($(this).val()==0){
			$('select[name="type"]').find('option').eq(1).show();
		}
	})
	if($('select[name="type"]').val()==1){
		$('select[name="useTime"]').find('option').eq(1).hide();
	}else{
		$('select[name="useTime"]').find('option').eq(1).show();
	}
	$('.saveProtocol').on('click',function(){
			CKupdate();
			var flag = true;
			var name = $("#name").val();
			if(!name){
				$.msg('协议名称不能为空');
				$("#name").focus();
				flag = false;
				return;
			}
			/*if(name){
				$.ajax({
					url:rootPath+"/courseProtocolConfig/checkNameExsit",
					async:false,
					type:"post",
					data:{"name":name,"id":id},
					success:function(jsonData){
						if(jsonData&&jsonData=="fail"){
							$.msg("协议名称不能重复");
							$('input[name="name"]').val("");
							$('input[name="name"]').focus();
							flag = false;
							return ;
						}
					}
				})
			}*/
			var title = $('#title').val();
			if(!title){
				$.msg('协议标题不能为空');
				$('#title').focus();
				flag = false;
				return;
			}
			var content = $('#q-content').val();
			if(!content){
				$.msg('协议内容不能为空');
				$('#q-content').focus();
				flag = false;
				return;
			}
			var type=$('select[name="type"]').val();
			var useTime =$('select[name="useTime"]').val();
			var id = $('.hiddenProtocolId').val();
			var data = {};
			data.id = id;
			data.name = name;
			data.title = title;
			data.content = content;
			data.content = encodeURI(data.content);
			data.type = type;
			data.useTime = useTime;
			if(flag){
				$.ajax({
					url:rootPath+"/courseProtocolConfig/saveOrUpdateProtocol",
					type:"post",
					data:data,
					dataType:'json',
					success:function(msg){
						if(msg=="success"){
							$.msg('保存成功');
							setTimeout(function(){
								window.location.href=rootPath+"/courseProtocolConfig/toProtocolList";
								//searchProtocol();
							},1000);
						}
					}
				})
			}
		})
		
		
		
		$('#loadData').on('click','.editProtocol',function(){
			var id = $(this).attr('marks');
			$.ajax({
				url:rootPath+"/coursePotocolUserRelation/checkPrototolIsRelease",
				type:"post",
				data:{"potocolId":id},
				success:function(jsonData){
					if(jsonData&&jsonData=="success"){
						$.alert('由于此课程协议已被发放 无法进行编辑修改操作');
						return;
					}
					else{
						window.location.href=rootPath+"/courseProtocolConfig/addProtocol"+"?protocolId="+id;
					}
				}
			})
		})
		
		
		$('#loadData').on('mouseleave.link','.spe',function(){
			$(this).find('.sss').hide();
		})
		
		$('#loadData').on('mouseenter.link','.moreOperation',function(){
			$(this).parent('.spe').find('.sss').eq(0).show();
		})
		
		
		$('#loadData').on('mouseenter.link','.changeStatus',function(){
			if($(this).text()=='停用'){
				$(this).css('color','red');
			}
		})
		
		$('#loadData').on('mouseleave.link','.changeStatus',function(){
			if($(this).text()=='停用'){
				$(this).css('color','#07bbee');
			}
		})
		$('#loadData').on('click','.protocolDetail',function(){
			var protocolId = $(this).attr('protocolId');
			window.location.href=rootPath+"/courseProtocolConfig/toProtocolDetail?protocolId="+protocolId;
		})
		
		$('#loadData').on('click','.protocolCourse',function(){
			$('.loading-bg').css('display','block');
			$('#currentCourse').find('a').addClass('btn-success');
			$('#historyCourse').find('a').removeClass('btn-success');
			$('#currentPackage').find('a').addClass('btn-success');
			$('#historyPackage').find('a').removeClass('btn-success');
			var id = $(this).attr('protocolId');
			var type = $(this).attr('type');
			searchRelation(id,type,'');
		})
		
		$('.confirm_close').on('click',function(){
			$('.loading-bg').hide();
			$('#coursePackageDetail').hide();
			$('#courseDetail').hide();
		})
		
		$('#currentCourse').on('click',function(){
			var id = $('.cid').val();
			var type = $('.ctype').val();
			searchRelation(id,type,'');
		})
		
		$('#historyCourse').on('click',function(){
			var id = $('.cid').val();
			var type = $('.ctype').val();
			searchHistory(id,type,'');
		})
		
		$('#pback').on('click',function(){
			window.location.href=rootPath+"/courseProtocolConfig/toProtocolList";
		})
		$('#currentPackage').on('click',function(){
			var id = $('.cid').val();
			var type = $('.ctype').val();
			searchRelation(id,type,'');
		})
		
		$('#historyPackage').on('click',function(){
			var id = $('.cid').val();
			var type = $('.ctype').val();
			searchHistory(id,type,'');
		})
		
		
		$('input[name="name"]').on("change",function(){
			$.ajax({
				url:rootPath+"/courseProtocolConfig/checkNameExsit",
				async:false,
				type:"post",
				data:{"name":$(this).val(),"id":$('.hiddenProtocolId').val()},
				success:function(jsonData){
					if(jsonData&&jsonData=="fail"){
						$.msg("协议名称不能重复");
						$('input[name="name"]').val("");
						$('input[name="name"]').focus();
						return ;
					}
				}
			})
		})
		
		
})(jQuery)

function CKupdate() {
	for (instance in CKEDITOR.instances) {
		CKEDITOR.instances[instance].updateElement();
	}
}