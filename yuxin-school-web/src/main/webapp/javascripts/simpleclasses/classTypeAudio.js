$(document).ready(function(){
	$(".add-layer").delegate(".close","click",function () {
		$(".add-layer").hide();
	});
	searchAudios();
/*	$(".showAudio .videotabs").on('click','span',function(){
		$(".showAudio div.layer-content").hide();
		$(".showAudio div.tab"+($(this).index()+1)).show();
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
		searchAudios();
	});*/
		//选择视频
	$(".showAudio .videotabs").on("click.btn.choose",":button",function(){
			searchAudios();
		});
		$(".showAudio").on("click.row.model",".term-list tr[has-data]",function(){
			$(this).addClass("active").siblings().removeClass("active");
			var videoData = $(this).data();
	/*		var flag=$(this).attr("flag");
			var ccId=$(this).attr("videoCcId");*/
			//根据标记判断拼音频路径
			var url='';
			// if($(this).attr("webVideoDomain")=="v.youku.com"){
			// 	url='http://player.youku.com/player.php/sid/'+ $(this).attr("webVideoId") + '==/v.swf';
			// }else if($(this).attr("webVideoDomain")=="www.tudou.com"){
			// 	url='http://www.tudou.com/'+ $(this).attr("webVideoId") + '/&vip=1';
			// }else{
			if(!videoData.imgrootPath && !videoData.filePath){
				return false;
			}
				url = videoData.imgrootPath + videoData.filePath;
				//url='http://p.bokecc.com/flash/player.swf?vid='+ccId+'&siteid='+$(this).attr("ids")+'&amp;playerid=25CCD0665D668BCE&amp;playertype=1&amp;autoStart=true';
		/*	}*/
			$("input.cke_dialog_ui_input_text:visible").val(url);
			$('.showAudio').fadeOut(200,function(){
              $('.add-layer-bg').fadeOut(200);
          });
		});


		
	});

	function  searchAudios(page){
		var $this=this;
		var search={};
		var $tab=$(".showAudio .layer-content.tab1");
		//获取当前视频类型
	/*	$(".showAudio .videotabs").find("span").each(function(){
			if($(this).hasClass("active")){
				search.flag=$(this).attr("ids");
				$tab=$(".showAudio .tab"+($(this).index()+1));
			}
		});*/
		
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
			url: rootPath+"/video/searchAudio",
			data: search,
			type:"post",
			async: false,
			dataType:"json",
			success: function(jsonData){
				var rootPaths = jsonData.imageServeUrl,
					jData = jsonData.pageFinder.data;

				if(jData && jData.length>0){
					$.each(jData,function(i,data){
						var $tr =$('<tr id="'+data.id+'" has-data="true">"'+
							'<td style="width:30%;">'+(data.videoName?data.videoName:"")+'</td>'+
							'<td style="width:10%;">'+(data.vodeoSize?data.vodeoSize:0)+'M</td>'+
							'<td style="width:30%;">'+(data.videoTag?data.videoTag:"")+'</td>'+
							'<td style="width:10%;">'+data.creatorName+'</td>'+
							'<td style="width:20%;">'+(data.createTime?data.createTime:"")+'</td>'+
							'</tr>');
						//把地址配置到tr 的data()中。
						$tr.data($.extend(data,{"imgrootPath":rootPaths}));
						$tab.find("#data_table").find("tbody").append($tr);
					});
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
					    	 searchAudios(pageNo);
				    	 }
				   });
				}else{
					$tab.find("#data_table").find("tbody").append('<tr><td>没有查到视频资源&nbsp;&nbsp;<a href="'+rootPath+'/video/toVideo" target="_blank" style="color:blue;text-decoration:underline;">现在去上传音频</a></td></tr>');
				}
				
			},
			error: function(resp,err,msg){
				console.log(resp);
				alert("检索异常");
			}
			
		});
		
	}