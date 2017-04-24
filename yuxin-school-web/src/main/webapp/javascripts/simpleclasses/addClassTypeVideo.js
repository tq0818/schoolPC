$(document).ready(function(){
	//初始化一学科小类
	$(".tab1").find("#choose_itemOne").getSysItem();
	$(".tab1").find("#choose_itemOne").on("change",function(){
		$(".tab1").find("#choose_itemSecond").getSysItem($(this).val(),function(){
			
		});
	});
	$(".tab2").find("#choose_itemOne").getSysItem();
	$(".tab2").find("#choose_itemOne").on("change",function(){
		$(".tab2").find("#choose_itemSecond").getSysItem($(this).val(),function(){
			
		});
	});
	$(".tab2").find(".itemOne").getSysItem();
	$(".tab2").find(".itemOne").on("change",function(){
		$(".tab2").find(".itemSecond").getSysItem($(this).val(),function(){
		});
	});
	//初始化视频列表
	$(".tab1").find("#choose_itemOne").find("option[value='"+$("#itemOneId").val()+"']").attr("selected","selected");
	$(".tab1").find("#choose_itemSecond").getSysItem($("#choose_itemOne").val(),function(){
		$(".tab1").find("#choose_itemSecond").find("option[value='"+$("#itemSecondId").val()+"']").attr("selected","selected");
	});
	$(".tab2").find("#choose_itemOne").find("option[value='"+$("#itemOneId").val()+"']").attr("selected","selected");
	$(".tab2").find("#choose_itemSecond").getSysItem($(".tab2").find("#choose_itemOne").val(),function(){
		$(".tab2").find("#choose_itemSecond").find("option[value='"+$("#itemSecondId").val()+"']").attr("selected","selected");
	});
	
	searchVideos();
	//初始化日期框
	$(".date-picker").datetimepicker({
			format: "yyyy-mm-dd",
			minView:2,
			autoclose: true,
			language: "zh-CN"
		});
	
	//初始化标签库
	$.ajax({
		url: rootPath+"/videoTag/list",
		type: "post",
		dataType : "json",
		success: function(jsonData){
			var datas=[];
			$.each(jsonData,function(i,data){
				if(data){
					datas.push({id:data.id,text:(data.tagName?data.tagName:"")});
				}
			});
			$(".tag").each(function(){
				$(this).select2({
					multiple: true,
					data: datas,
					width: '150px',
					height:'32px'
				})
			})
		}
	})
		
      $(".w800").on("change.input.search","#tag,#tj",function(){
			searchVideos();
		});

		//单机选中视频，双击选择视频
		$(".w800").on("click.row.model",".term-list tr[has-data]",function(){
			$(".w800").find(".term-list tr").removeClass("active");
			$(this).addClass("active");
		})
		.on("click.btn.choose",":button",function(){
			searchVideos();
		})
		$(".w800").on("click.row.model",".term-list tr[has-data]",function(){
			var flag=$(this).attr("flag");
			var ccId=$(this).attr("videoCcId");
			//根据标记判断拼视频路径
			var url="";
			if($(this).attr("webVideoDomain")=="v.youku.com"){
				url='http://player.youku.com/player.php/sid/'+ $(this).attr("webVideoId") + '==/v.swf';
				$("#cke_FlashPreviewBox62").html("").append("<object width='100%' height='100%'>"
						 + "<param name='movie' value='http://player.youku.com/player.php/sid/" 
						 + $(this).attr("webVideoId") + "==/v.swf'></param>"
						 + "<param name='allowFullScreen' value='true'></param>"
						 + "<param name='allowscriptaccess' value='always'></param>"
						 + "<param name='wmode' value='opaque'></param>"
						 + "<embed src='http://player.youku.com/player.php/sid/" 
						 + $(this).attr("webVideoId") + "==/v.swf' type='application/x-shockwave-flash' allowscriptaccess='always' allowfullscreen='true' wmode='opaque' width='100%' height='100%'></embed>"
					     + "</object>");
			}else if($(this).attr("webVideoDomain")=="www.tudou.com"){
				url='http://www.tudou.com/'+ $(this).attr("webVideoId") + '/&vip=1';
				$("#cke_FlashPreviewBox62").html("").append("<object width='100%' height='100%'>"
						 + "<param name='movie' value='http://www.tudou.com/" 
						 + $(this).attr("webVideoId") + "/&vip=1'></param>"
						 + "<param name='allowFullScreen' value='true'></param>"
						 + "<param name='allowscriptaccess' value='always'></param>"
						 + "<param name='wmode' value='opaque'></param>"
						 + "<embed src='http://www.tudou.com/" 
						 + $(this).attr("webVideoId") + "/&vip=1' type='application/x-shockwave-flash' allowscriptaccess='always' allowfullscreen='true' wmode='opaque' width='100%' height='100%'></embed>"
						 + "</object>");
			}else{
				url='http://p.bokecc.com/flash/player.swf?vid='+ccId+'&siteid='+$(this).attr("ids")+'&amp;playerid=25CCD0665D668BCE&amp;playertype=1&amp;autoStart=true';
				$("#cke_FlashPreviewBox62").html("").append("<div style='width: 100%; height: 100%;' id='cc_video_"
						+ ccId
						+ "_2507439'><object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0' id=cc_"
						+ ccId
						+ " height='100%' width='100%''><param name='movie' value='http://p.bokecc.com/flash/player.swf?vid="
						+ ccId
						+ "&amp;siteid=" + $(this).attr("ids") + "&amp;playerid=25CCD0665D668BCE&amp;playertype=1&amp;autoStart=true'><param value='transparent' name='wmode'><param name='allowFullScreen' value='true'><param name='allowScriptAccess' value='always'><embed src='http://p.bokecc.com/flash/player.swf?vid="
						+ ccId
						+ "&amp;siteid=" + $(this).attr("ids") + "&amp;playerid=25CCD0665D668BCE&amp;playertype=1&amp;autoStart=true' name='cc_"
						+ ccId
						+ "' wmode='transparent' allowfullscreen='true' allowscriptaccess='always' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' height='100%' width='100%'></object></div>");
			}
			$("#cke_67_textInput").val(url);
			$('.w800').fadeOut(200,function(){
              $('.add-layer-bg').fadeOut(200);
          });
		});
		$(".t-c-l").find(".dis").eq(0).trigger("click.li.left");
		
		$(".videotabs").on('click','span',function(){
			$("div.layer-content").hide();
			$("div.tab"+($(this).index()+1)).show();
			$(this).siblings().removeClass("active");
			$(this).addClass("active");
			searchVideos();
		})

		$(".add-video-link").on("click",function(){
			$(".add-div").show();
			$(".default-div").hide();
		})
		$(".btn-add-url").on("click",function(){
			var url = $("#videourl").val();
			if(typeof(url) == "undefined" || url == null || url == ""){
				$.msg("请填入外部视频连接地址");
				return false;
			}
			//截取网址域
			if(url.indexOf("http://") >= 0){
				url = url.substring(7);
			}else if(url.indexOf("https://") >= 0){
				url = url.substring(8);
			}
			var httpTitle = url.substring(0,url.indexOf("/"));
			/*if(httpTitle == "open.163.com"){
				//截取163的
				if(url.lastIndexOf("/") >= 0){
					outUrl = (url.substring(url.lastIndexOf("/") + 1 , url.lastIndexOf(".")));
				}else{
					$('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return false;
				}
			}else */if(httpTitle == "v.youku.com"){
				storageType = "VIDEO_STORAGE_TYPE_YK";
				//截取优酷的
				if(url.indexOf("id_") >= 0){
					if(url.indexOf("=") >= 0){
						outUrl = (url.substring(url.indexOf("id_") + 3 , url.indexOf("=")));
					}else{
						outUrl = (url.substring(url.indexOf("id_") + 3 , url.lastIndexOf(".")));
					}
				}else{
					$.msg('您输入的地址不合法');
					return false;
				}
			}else if(httpTitle == "www.tudou.com"){
				storageType = "VIDEO_STORAGE_TYPE_TD";
                //截取土豆的
                if (url.indexOf("albumplay/") >= 0) {
                    outUrl = "a/" + (url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html")));
                } else if (url.indexOf("listplay/") >= 0) {
                	if(url.indexOf(".html") >= 0){
                        outUrl = "l/" + (url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html")));
                	}else{
                		outUrl = url.substring(url.indexOf("listplay/") + ("listplay/").length);
                		outUrl = "l/" + (outUrl.substring(url.indexOf("/") + 1, outUrl.lastIndexOf("/")));
                	}
                } else if (url.indexOf("view/") >= 0) {
                    outUrl = (url.substring(url.indexOf("view/") + ("view/").length));
                    if(outUrl.indexOf("/") >= 0){
                    	outUrl = "v/" + (outUrl.substring(0,outUrl.indexOf("/")));
                    }else{
                    	outUrl = "v/" + outUrl;
                    }
                } else {
                    $('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                        $(this).remove();
                    });
                    return false;
                }
			}else if(httpTitle == "qzonestyle.gtimg.cn"){
				storageType = "VIDEO_STORAGE_TYPE_QQ";
				var o1 = url.substring(url.indexOf("&file_id=")+9,url.indexOf("&app_id="));
				var o2 = url.substring(url.indexOf("&app_id=")+8,url.indexOf("&version="));
				outUrl = o1 + "," + o2;
			}else{
				$.msg('您输入的地址不合法');
				return false;
			}
			$("#videourl").data("outUrl",outUrl);
			videoType = httpTitle;
			$.ajax({
				url :rootPath + "/video/selVideoName" ,
				type:"post",
				data:{"url":$("#videourl").val(),"domain":httpTitle},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
					$(".addVideoTc").css("z-index",2);
	              $(".loading").show();
	              $(".loading-bg").show();
	         	},
				success:function(data){
					if(data.msg != "error"){
						$(".video-name").val(data.msg);
						$.msg('已添加外部链接');
					}
				}
			});
		});
		
		$(".tab2").on("click.btn.save",".btn-s",function(){
			var oneItemId = $(".add-div").find(".itemOne").val();
			var twoItemId = $(".add-div").find(".itemSecond").val();
			var tag=$(".add-div").find(".tag").val();
			var videoName = $(".add-div").find(".video-name").val();
			var url = $(".add-div").find("#videourl").val();
			var outUrl=$("#videourl").data("outUrl");
			/* alert(oneItemId + "\n" + twoItemId + "\n" + videoName + "\n" + url + "\n" + outUrl + "\n" + videoType); */
			if(oneItemId == null || twoItemId == null || oneItemId < 0 || twoItemId < 0){
				$.msg('请选择科目');
				return false;
			}
			if(url == "" || outUrl == "" || videoType == ""){
				$.msg('请添加外部链接地址');
				return false;
			}
			if(videoName == ""){
				$.msg('请添加视频名称');
				return false;
			}
//        	if(!$("#protocol").prop("checked")){
//        		$('您尚未同意上传协议');
//        		return false;
//        	}
        	if($(".hour").val() == 0 && $(".minute").val() == 0 && $(".second").val() == 0){
        		$('请填写视频时长');
        		return false;
        	}
        	//保存到video
        	var videoTime = $(".hour").val() + ":" + $(".minute").val() + ":" + $(".second").val();
        	$.ajax({
        		url:rootPath + "/video/saveVideoUrl",
        		type:"post",
        		data:{"videoName":videoName,"videoTime":videoTime,"videoStatus":"VIDEO_PROCESS_NOMAL","itemOneId":oneItemId,"itemSecondId":twoItemId,"videoTag":tag,"webVideoId":outUrl,"webVideoDomain":videoType,"storageType":storageType},
        		dataType:"json",
				beforeSend:function(XMLHttpRequest){
		             $.loading("")
		         },
        		success:function(data){
        			if(data.msg == "success"){
        				var domain = "";
        				/*if(videoType == "open.163.com"){
        					domain = "网易公开课";
        				}else */if(videoType == "v.youku.com"){
        					domain = "优酷视频";
        				}else if(videoType == "www.tudou.com"){
        					domain = "土豆视频";
        				}
//        				$(".v_url").prepend("<tr data-id='" + data.id + "'><td><div class='operate_w'>" + videoName + "</div></td><td>" + domain + "</td><td>正常</td><td><a class='btn btn-sm btn-del' href='javascript:;' data-id='" + data.id + "'>删除</a></td></tr>");
        				// 刷新视频列表
        				searchVideos();
        				$(".default-div").show();
        				$(".add-div").hide();
        				$(".add-div").find(".itemOne").val(-1);
        				$(".add-div").find(".itemSecond").val(-1);
        				$(".add-div").find(".tag").val("");
        				$(".add-div").find(".hour").val(0);
        				$(".add-div").find(".minute").val(0);
        				$(".add-div").find(".second").val(0);
        				$(".add-div").find(".video-url").val("");
        				$(".add-div").find(".video-name").val("");
        				outUrl = "";
        				videoType = "";
        			}else{
						$.msg("未保存");
        			}
        		},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		            $.loadover()
		        }
        	});
		}).on("click.btn.cancle",".btn-c",function(){
			$(".add-div").hide();
			$(".default-div").show();
		})

		
	});

//	//检索视频
//	function searchVideos(page){
//		var $this=this;
//		var search={};
//		//获取当前视频类型
//		$(".videotabs").find("span").each(function(){
//			if($(this).hasClass("active")){
//				search.flag=$(this).attr("ids");
//			}
//		});
//		
//		if($("#choose_itemOne").val()){
//			search.itemOneId=$("#choose_itemOne").val();
//		}
//		if($("#choose_itemSecond").val()){
//			search.itemSecondId=$("#choose_itemSecond").val();
//		}
//		
//		if($("#start_date").val()){
//			search.beginTime=$("#start_date").val();
//		}
//		if($("#end_date").val()){
//			search.endTime=$("#end_date").val();
//		}
//		if($("#tag").val()){
//			var data=$("#tag").select2("data");
//			$.each(data,function(i,d){
//				if(i==0){
//					search.videoTag=d.text?d.text:"";
//				}else{
//					search.videoTag+=","+d.text?d.text:"";
//				}
//				
//			})
//		}
//		if($("#tj").val()){
//			search.videoName=$("#tj").val();
//		}
//		search.page=page?page:1;
//
//		$("#data_table").find('tr').remove();
//		$(".videoPagination").html('');
//		$.ajax({
//			url: rootPath+"/video/searchVideos",
//			data: search,
//			type:"post",
//			dataType:"json",
//			success: function(jsonData){
//				if(jsonData.data && jsonData.data.length>0){
//					$.each(jsonData.data,function(i,data){
//						$("#data_table").find("tbody")
//						.append('<tr id="'+data.id+'" videoCcId="'+data.videoCcId+'" webVideoDomain="'+data.webVideoDomain+'" ids="'+data.ccuserId+'" flag="'+data.storageType+'" has-data="true" webVideoId="'+data.webVideoId+'" videoStatus="'+data.videoStatus+'">'+
//			                    '<td style="width:30%;">'+data.videoName+'</td>'+
//			                    '<td style="width:10%;">'+data.vodeoSize+'M</td>'+
//			                    '<td style="width:30%;">'+(data.videoTag?data.videoTag:"")+'</td>'+
//			                    '<td style="width:10%;">'+data.creatorName+'</td>'+
//			                    '<td style="width:20%;">'+(data.createTime?data.createTime:"")+'</td>'+
//			                '</tr>');
//					})
//					$(".videoPagination").pagination(jsonData.rowCount, {
//				    	 next_text : "下一页",
//				    	 prev_text : "上一页",
//				    	 current_page : jsonData.pageNo-1,
//				    	 link_to : "javascript:void(0)",
//				    	 num_display_entries : 8,
//				    	 items_per_page : jsonData.pageSize,
//				    	 num_edge_entries : 1,
//				    	 callback:function(page,jq){
//					    	 var pageNo = page + 1;
//					    	 searchVideos(pageNo);
//				    	 }
//				   });
//				}else{
//					$("#data_table").find("tbody").append('<tr><td>没有查到视频资源&nbsp;&nbsp;<a href="'+rootPath+'/video/toVideo" target="_blank" style="color:blue;text-decoration:underline;">现在去上传视频</a></td></tr>');
//				}
//				
//			},
//			error: function(resp,err,msg){
//				console.log(resp);
//				alert("检索异常");
//			}
//			
//		});
//		
//	}
//	

	function  searchVideos(page){
		var $this=this;
		var search={};
		var $tab=$("div.tab1");
		//获取当前视频类型
		$(".videotabs").find("span").each(function(){
			if($(this).hasClass("active")){
				search.flag=$(this).attr("ids");
				$tab=$("div.tab"+($(this).index()+1));
			}
		});
		
		if($tab.find("#choose_itemOne").val()){
			search.itemOneId=$tab.find("#choose_itemOne").val();
		}
		if($tab.find("#choose_itemSecond").val()){
			search.itemSecondId=$tab.find("#choose_itemSecond").val();
		}
		
		if($tab.find("#start_date").val()){
			search.beginTime=$tab.find("#start_date").val();
		}
		if($tab.find("#end_date").val()){
			search.endTime=$tab.find("#end_date").val();
		}
		if($tab.find("#tag").val()){
			var data=$tab.find("#tag").select2("data");
			$.each(data,function(i,d){
				if(i==0){
					search.videoTag=d.text?d.text:"";
				}else{
					search.videoTag+=","+d.text?d.text:"";
				}
				
			})
		}
		if($tab.find("#tj").val()){
			search.videoName=$tab.find("#tj").val();
		}
		search.page=page?page:1;

		$tab.find("#data_table").find('tr').remove();
		$tab.find(".videopagination").html('');
		$.ajax({
			url: rootPath+"/video/searchVideos",
			data: search,
			type:"post",
			dataType:"json",
			success: function(jsonData){
				if(jsonData.data && jsonData.data.length>0){
					$.each(jsonData.data,function(i,data){
						$tab.find("#data_table").find("tbody")
						.append('<tr id="'+data.id+'" videoCcId="'+data.videoCcId+'" webVideoDomain="'+data.webVideoDomain+'" ids="'+data.ccuserId+'" flag="'+data.storageType+'" has-data="true" webVideoId="'+data.webVideoId+'" videoStatus="'+data.videoStatus+'">'+
			                    '<td style="width:30%;">'+(data.videoName?data.videoName:"")+'</td>'+
			                    '<td style="width:10%;">'+(data.vodeoSize?data.vodeoSize:0)+'M</td>'+
			                    '<td style="width:30%;">'+(data.videoTag?data.videoTag:"")+'</td>'+
			                    '<td style="width:10%;">'+data.creatorName+'</td>'+
			                    '<td style="width:20%;">'+(data.createTime?data.createTime:"")+'</td>'+
			                '</tr>');
					})
					$tab.find(".videopagination").pagination(jsonData.rowCount, {
				    	 next_text : "下一页",
				    	 prev_text : "上一页",
				    	 current_page : jsonData.pageNo-1,
				    	 link_to : "javascript:void(0)",
				    	 num_display_entries : 8,
				    	 items_per_page : jsonData.pageSize,
				    	 num_edge_entries : 1,
				    	 callback:function(page,jq){
					    	 var pageNo = page + 1;
					    	 searchVideos(pageNo);
				    	 }
				   });
				}else{
					$tab.find("#data_table").find("tbody").append('<tr><td>没有查到视频资源&nbsp;&nbsp;<a href="'+rootPath+'/video/toVideo" target="_blank" style="color:blue;text-decoration:underline;">现在去上传视频</a></td></tr>');
				}
				
			},
			error: function(resp,err,msg){
				console.log(resp);
				alert("检索异常");
			}
			
		});
		
	}