/**
 * author zhang.zx
 * 班型打包
 * 页面js封装
 */
(function($){
	var comStatus = 0;
	var iteStatus = 0;
	var claStatus = 0;
	var itemflag = false;
	var comflag = false;
	var Form={
			init : function(){
				
				comStatus = $("#comLS").val();
				if(!comStatus){
					comStatus = 0;
				}
				
				iteStatus = $("#itemLS").val();
				if(!iteStatus){
					iteStatus = 0;
				}
				
				claStatus = $("#claLS").val();
				if(!claStatus){
					claStatus = 0;
				}
				
				if(comStatus == 1){
					$(".kgCom").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open fs12">&nbsp;&nbsp;已启用</span>');
					Form.loadCom();
					$(".comContent").removeClass("none");
				}else{
					$(".kgCom").html('').append('<i class="iconfont close">&#xe604;</i><span class="i fs12">&nbsp;&nbsp;已禁用</span>');
				}
				
				if(iteStatus == 1){
					$(".kgItem").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open fs12">&nbsp;&nbsp;已启用</span>');
					Form.findOneItemByComId();
					$(".itemContent").removeClass("none");
				}else{
					$(".kgItem").html('').append('<i class="iconfont close">&#xe604;</i><span class="i fs12">&nbsp;&nbsp;已禁用</span>');
				}
				
				if(claStatus == 1){
					$(".kgCla").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open fs12">&nbsp;&nbsp;已启用</span>');
				}else{
					$(".kgCla").html('').append('<i class="iconfont close">&#xe604;</i><span class="i fs12">&nbsp;&nbsp;已禁用</span>');
				}
				
				
				$(".kg").on("click",".iconfont",function(){
					var $this = $(this);
					var level = $this.parent(".kg").attr("level");
					console.log(level);
					if($this.hasClass("open")){
						if(level == 'com'){
							comStatus = 0;
							Form.updateComFunSet("COURSE_VALIDITY_COMPANY_LEVEL", "0", null);
						$(".comContent").addClass("none");
						}
						if(level == 'item'){
							iteStatus = 0;
							Form.updateComFunSet("COURSE_VALIDITY_ITEM_LEVEL", "0", null);
							$(".itemContent").addClass("none");
							$("#subInfo").html('');
						}
						if(level == 'cla'){
							Form.updateComFunSet("COURSE_VALIDITY_COURSE_LEVEL", "0", null);
							claStatus = 0;
						}
						$this.parent(".kg").html('').append('<i class="iconfont close">&#xe604;</i><span class="i fs12">&nbsp;&nbsp;已禁用</span>');
					}
					if($this.hasClass("close")){
						if(level == 'com'){
							comStatus = 1;
							Form.updateComFunSet("COURSE_VALIDITY_COMPANY_LEVEL", "1", null);
							Form.loadCom();
							$(".comContent").removeClass("none");
						}
						if(level == 'item'){
							iteStatus = 1;
							Form.findOneItemByComId();
							$(".itemContent").removeClass("none");
							Form.updateComFunSet("COURSE_VALIDITY_ITEM_LEVEL", "1", null);
						}
						if(level == 'cla'){
							claStatus = 1;
							Form.updateComFunSet("COURSE_VALIDITY_COURSE_LEVEL", "1", null);
						}
						$this.parent(".kg").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open fs12">&nbsp;&nbsp;已启用</span>');
					}
				});
				
				$("#itemSave").click(function(){
					if(iteStatus == 1){
						itemflag = false;
						$(".itemObj").each(function(i,item){
							var itemId = $(item).attr("itemId");
							var validityDay = $(".itDate"+i+"").val();
							var videoWatchCount = $(".itVideo"+i+"").val();
							var liveWatchCount = $(".itLive"+i+"").val();
							if(!validityDay){
								validityDay = 0;
							}
							if(!videoWatchCount){
								videoWatchCount = 0;
							}
							if(!liveWatchCount){
								liveWatchCount = 0;
							}
							Form.updateByItemId(itemId, validityDay, videoWatchCount, liveWatchCount);
						});
					}else{
						$('<div class="c-fa">' + "请启动后再保存" + '</div>')
						.appendTo('body').fadeIn(100).delay(
								1000).fadeOut(200, function() {
							$(this).remove();
						});
						return;
					}
				});
			},
			updateByItemId : function(itemId,validityDay,videoWatchCount,liveWatchCount){
				$.ajax({
					type : 'post',
					url : rootPath+"/sysConfigItemCourse/updateByItemId",
					data : {"configItemId":itemId,"validityDay":validityDay,"videoWatchCount":videoWatchCount,"liveWatchCount":liveWatchCount},
					success: function(data){
						console.log(data);
						if(data == 'success'){
							if(!itemflag){
								$('<div class="c-fa">' + "保存成功" + '</div>')
								.appendTo('body').fadeIn(100).delay(
										1000).fadeOut(200, function() {
									$(this).remove();
								});
								itemflag = true;
							}
						}
					}
				});
			},
			loadSub : function(){
				$.ajax({
					url : rootPath + "/sysConfigItemCourse/loadAjax",
					type : "post",
					beforeSend : function(XMLHttpRequest) {
						$(".loading").show();
						$(".loading-bg").show();
					},
					success : function(data) {
						$("#subInfo").html('').html(data);
						//文本框只能输入数字
						$("input").keyup(function(){     
					        var tmptxt=$(this).val();     
					        $(this).val(tmptxt.replace(/\D/g,''));     
					    }).bind("paste",function(){     
					        var tmptxt=$(this).val();     
					        $(this).val(tmptxt.replace(/\D/g,''));     
					    }).css("ime-mode", "disabled"); 
					},
					complete : function(XMLHttpRequest, textStatus) {
						$(".loading").hide();
						$(".loading-bg").hide();
					}
				});
			},
			loadCom : function(){
				$.ajax({
					url : rootPath + "/sysConfigItemCourse/loadCom",
					type : "post",
					beforeSend : function(XMLHttpRequest) {
						$(".loading").show();
						$(".loading-bg").show();
					},
					success : function(data) {
						$(".comContent").html('').html(data);
						//文本框只能输入数字
						$("input").keyup(function(){     
					        var tmptxt=$(this).val();     
					        $(this).val(tmptxt.replace(/\D/g,''));     
					    }).bind("paste",function(){     
					        var tmptxt=$(this).val();     
					        $(this).val(tmptxt.replace(/\D/g,''));     
					    }).css("ime-mode", "disabled"); 
						
						
						$(".comSave").click(function(){
							var comDay = $("#comDay").val();
							var comVideo = $("#comVideo").val();
							var comLive = $("#comLive").val();
							if(!comDay){
								comDay = 0;
							}
							if(!comVideo){
								comVideo = 0;
							}
							if(!comLive){
								comLive = 0;
							}
							comflag = false;
							Form.updateComFunSetClo("COURSE_VALIDITY_DAY",null, comDay);
							Form.updateComFunSetClo("VIDEO_WATCH_TIMES",null, comVideo);
							Form.updateComFunSetClo("LIVE_WATCH_TIMES",null, comLive);
						});
					},
					complete : function(XMLHttpRequest, textStatus) {
						$(".loading").hide();
						$(".loading-bg").hide();
					}
				});
			},
			findOneItemByComId : function(){
				$.ajax({
					type : 'post',
					url : rootPath+"/sysConfigItemCourse/findOneItemByComId",
					beforeSend : function(XMLHttpRequest) {
						$(".loading").show();
						$(".loading-bg").show();
					},
					success: function(data){
						console.log("插入"+data)
						if(data == 'success'){
							Form.loadSub();
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						$(".loading").hide();
						$(".loading-bg").hide();
					}
				});
			},
			updateComFunSet : function(functionCode,status,column1){
				$.ajax({
					type : 'post',
					url : rootPath+"/sysConfigItemCourse/updateComFuncSet",
					data : {"functionCode":functionCode,"status":status,"column1":column1},
					success: function(data){
						
					}
				});
			},
			updateComFunSetClo : function(functionCode,status,column1){
				$.ajax({
					type : 'post',
					url : rootPath+"/sysConfigItemCourse/updateComFuncSet",
					data : {"functionCode":functionCode,"status":status,"column1":column1},
					success: function(data){
						if(data == 'success'){
							if(!comflag){
								$('<div class="c-fa">' + "保存成功" + '</div>')
								.appendTo('body').fadeIn(100).delay(
										1000).fadeOut(200, function() {
									$(this).remove();
								});
								comflag = true;
							}
							
						}
					}
				});
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)