$(document).ready(function(){
			$(document).on("mouseenter.page", "#ulList li", function() {
				$(this).find(".mark").fadeIn(300)
			}).on("mouseleave.page", "#ulList li", function() {
				$(this).find(".mark").fadeOut(300)
			});
		$(".types").on('click','a.btn',function(){
			var status=$(this).hasClass('btn-success');
			if(!status){
				$(this).addClass('btn-success').siblings('a').removeClass('btn-success');
			}
		});
		$(".types-list").on('click','a.btn',function(){
			var status=$(this).hasClass('active');
			if(!status){
				$(this).addClass('active').siblings('a').removeClass('active');
			}
		});
		loadImage();
		$(".btn-upload").on('click',function(){
			$("#chooseDiv").css("display", "block");
			$(".pic-upload").css("display", "none");
			$("#stopDiv").css("display", "block");
		});
//		$(".btn-upload").on('click',function(){
//			$("#ulList li:first").prev("<li class='add'><i class='iconfont'>&#xe61c;</i></li>");
//		});
		
      $('.btn-add-class')
          .on('click',function(){
              $('.add-layer-bg').fadeIn(200,function(){
                  $('.add-class-layer').fadeIn(200);
              })
          });

      // 弹层处理
      $('.upload-layer')
          .on('click','i.close',function(){
              $('.upload-layer').fadeOut(200,function(){
                  $('.add-layer-bg').fadeOut(200);
              });
          })
          // 取消
          .on('click','.btn-cancel',function(){
              $(this).parents('.pic-upload').fadeOut(200,function(){
                 // alert('这个仅作示例，为了展示列表')
            	  $('.upload-layer').css({'height':'481px'});
              })
          })
          .on('click','li.add',function(){
              $('.pic-upload').fadeIn(200,function(){
                  //alert('仅作示例，具体根据实际情况自行修改！')
            	  $('.upload-layer').css({'height':'540px'});
              })
          });
		
	});

	//加载公有云图片
	function loadImage(){
		$.ajax({
			url : rootPath+"/sysConfigItem/itemList",
			type : "POST",
			success : function(result) {
				$.each(result,function(i,item){
					$(".types-list").append("<a href='javascript:queryPicByItemOneId(1,"+item.id+");' class='btn btn-mini btn-link'>"+item.itemName+"</a>");
				});
				queryPicByItemOneId(1);
			}
		});
	}
	
	//条件查询公司图片库
	function queryConditionPics(tab){
		if(tab==""){
			$(".types").find("a").each(function(i){
				if($(this).hasClass('btn-success')){
					var t=$(this).attr("marks");
					tab=t;
				}
			});
		}
		if(tab=="privatePic"){
			$("#ulList li:first").show();
			$.ajax({
				url : rootPath+"/sysConfigItem/itemList",
				type : "POST",
				success : function(result) {
					$(".types-list").find("a").not(":first").remove();
					$.each(result,function(i,item){
						$(".types-list").append("<a href='javascript:queryPicByItemOneId(1,"+item.id+");' class='btn btn-mini btn-link'>"+item.itemName+"</a>");
					});
					queryPicByItemOneId(1);
				}
			});
		}else{
			//$(".types-list").find("a").not(":first").remove();
			//$("#ulList li:first").hide();
			//$("#ulList li").not(":first").remove();
			$.ajax({
				url : rootPath+"/company/queryCondition",
				type : "POST",
				success : function(result) {
					$(".types-list").find("a").not(":first").remove();
					$.each(result,function(i,item){
						$(".types-list").append("<a href=javascript:queryPublicPic(1,'"+item.picTag+"'); class='btn btn-mini btn-link'>"+item.picTag+"</a>");
					});
					queryPublicPic(1);
				}
			});
			
		}
	}
	
	//查询全部
	function queryAll(page,type){
		if(type==""){
			$(".types").find("a").each(function(i){
				var status=$(this).hasClass("btn-success");
				if(status){
					type=$(this).attr("marks");
				}
			});
		}
		if(type=="privatePic"){
			queryPicByItemOneId(1);
		}else{
			queryPublicPic(1);
		}
	}
	
	//根据学科查询课程课程封面
	function queryPicByItemOneId(page,itemOneId){
		$("#ulList li").not(":first").remove();
		$.ajax({
			url : rootPath+"/company/queryPics",
			type : "POST",
			data : {"page" : page,"itemOneId" : itemOneId},
			success : function(data) {
				$("#tlist").html(data);
//				if(page==1){
//					var html="<li class='add'><i class='iconfont'>&#xe61c;</i></li>";
//					$("#tlist").find("li:first").before(html);
//				}
			}
		});
	}
	
	//查询公司公有图片库
	function queryPublicPic(page,pictag){
		//$("#ulList li").not(":first").remove();
		$.ajax({
			url : rootPath+"/company/queryPirvatePics",
			type : "POST",
			data : {"page":page,"picTag" : pictag},
			success : function(data) {
				$("#tlist").html(data);
				//$("#ulList li:first").hide();
			}
		});
	}
	//选择课程封面
	function chooseOnePic(url,path){
		$("#chooseDiv").css("display","none");
		$("#stopDiv").css("display","none");
		$("#commdotityPic").attr({"src":url,"realPath":path});
	}
	
	//选择图片
	function savePic(){
			$.ajaxFileUpload({
			url : rootPath+"/simpleClasses/savePic;"+ window["sessionName"] + "=" + window["sessionId"],
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'imgData',
			dataType:'json',
			type : "POST",
			success : function(data) {
			  $("#sourcePic").attr("src",data.url);
			  $("#target").parent().html('<img id="target" src="'+data.url+'" style="width:516px;height:282px;"/>');
		      $("#target").trigger("change");
		      $(".p1 img").attr("src",data.url);
		      $(".p2 img").attr("src",data.url);
		      $(".p3 img").attr("src",data.url);
			},
			error:function(arg1,arg2,arg3){
				//console.log(arg1);
			},
			loadingEle: '#target',
			fileName: 'imgData'
		});
	}