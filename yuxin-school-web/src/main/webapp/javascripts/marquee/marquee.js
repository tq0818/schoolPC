$(function(){
	$selectSubMenu('org_service');
		//查询是否已使用样式

	/*ColorPicker(
            document.getElementById('slide'),
            function(hex, hsv, rgb) {
            	$(".sebg").attr("data-col",hex.substring(1));
              look();
            });
	$(".picker-wrapper").remove();*/
	$.ajax({
		url:rootPath + "/company/selMarqueeLine",
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.msg == "success"){
				if(data.marquee != null && typeof(data.marquee.marqueeLineId) != "undefined"){
					$(".marg li").each(function(){
						if($(this).attr("data-id") == data.marquee.marqueeLineId){
							$(this).attr("class","useed");
							$(this).find(".jian").css("border-color","#87cefa");
							$(this).find("span").css("color","#87cefa");
							$(this).find(".fonts").css("color","#87cefa");
							$(this).find(".curr").remove();
							$(this).find(".jian").prepend("<div class='curr'>正在使用</div>");
							var srcc = $(this).find("img").attr("src");
							srcc = srcc.substring((srcc.lastIndexOf("/") + 1),(srcc.lastIndexOf(".") -1));
							$(this).find("img").attr("src",rootPath + "/images/marquee/" + srcc +"2.png");
						}
					});
					if(typeof(data.marquee.fontColor) != "undefined"){
						$(".sebg").attr("data-col",(data.marquee.fontColor).substring(2));
						$(".sebg").css("background-color","#" + (data.marquee.fontColor).substring(2));
					}
					if(typeof(data.marquee.fontSize) != "undefined"){
						$("#fontSize").val((data.marquee.fontSize));
					}
				}else{
					$(".marg li:first").attr("class","selected");
					$(".marg li:first").find(".jian").css("border-color","#87cefa");
					$(".marg li:first").find("span").css("color","#87cefa");
					$(".marg li:first").find(".fonts").css("color","#87cefa");
					$(".marg li:first").find(".curr").remove();
					$(".marg li:first").find(".jian").prepend("<div class='curr'>默认使用</div>");
					var srcc = $(".marg li:first").find("img").attr("src");
					srcc = srcc.substring((srcc.lastIndexOf("/") + 1),(srcc.lastIndexOf(".") -1));
					$(".marg li:first").find("img").attr("src",rootPath + "/images/marquee/" + srcc +"2.png");
				}
			}
			look();
		}
	});
	$('span').on('click','i.oc',function(){
         var status = 0,
             // 开关图标  03为左侧块 04为右侧块
             s = ['&#xe603;','&#xe604;'],
             // 开关文字
             st = [' 已启用',' 已禁用'],
             // 获得开关实体
             o = $(this),
             flag,
             // 获得当前状态
             staus = o.hasClass('open');
	         if(staus){
	        	 status = 0;
	         }else{
	        	 status = 1;
	         }
         
         //修改使用状态
         $.ajax({
        	 url:rootPath + "/company/marqueeState",
        	 type:"post",
        	 data:{"stauts":status},
        	 dataType:"json",
        	 success:function(data){
        		 if(data.msg == "success"){
        	         o
        	             [staus?'removeClass':'addClass']('open')
        	             [staus?'addClass':'removeClass']('close')
        	             .html(s[staus?1:0])
        	             .next('span.i').text(st[staus?1:0])
        	             [staus?'removeClass':'addClass']('open')
        	             [staus?'addClass':'removeClass']('close');
        	         if(staus){
        	        	 $(".option").slideUp(500);
        	         }else{
        	        	 $(".option").slideDown(500);
        	         }
        		 }else{
        			 $.msg(data.msg);
        		 }
        	 }
         });
	});
	
	//点击时样式
	$(".marg li").click(function(){
		var src = $(".selected").find("img").attr("src");
		if(typeof(src) != "undefined"){
			src = src.substring((src.lastIndexOf("/") + 1),(src.lastIndexOf(".") -1));
			$(".selected").find("img").attr("src",rootPath + "/images/marquee/" + src +"1.png");
		}
		$(".marg li.selected").find(".jian").css("border-color","#c6c6c6");
		$(".marg li.selected").find("span").css("color","#c6c6c6");
		$(".marg li.selected").find(".fonts").css("color","#c6c6c6");
		$(".marg li.selected").find(".curr").remove();
		$(".marg li.selected").removeClass("selected").addClass("noselected");
		if($(this).attr("class") == "useed"){
			return false;
		}
		
		$(this).removeClass("noselected").addClass("selected");
		$(this).find(".jian").css("border-color","#87cefa");
		$(this).find(".curr").remove();
		$(this).find(".jian").prepend("<div class='curr'>已选择</div>");
		$(this).find("span").css("color","#87cefa");
		$(this).find(".fonts").css("color","#87cefa");
		var srcc = $(this).find("img").attr("src");
		srcc = srcc.substring((srcc.lastIndexOf("/") + 1),(srcc.lastIndexOf(".") -1));
		$(this).find("img").attr("src",rootPath + "/images/marquee/" + srcc +"2.png");
	});
	
	//悬浮样式
	$(".marg li").hover(function(){
		if($(this).attr("class") != "selected" && $(this).attr("class") != "useed"){
			$(this).find(".jian").css("border-color","#87cefa");
			$(this).find("span").css("color","#87cefa");
			$(this).find(".fonts").css("color","#87cefa");
			var srcc = $(this).find("img").attr("src");
			srcc = srcc.substring((srcc.lastIndexOf("/") + 1),(srcc.lastIndexOf(".") -1));
			$(this).find("img").attr("src",rootPath + "/images/marquee/" + srcc +"2.png");
		}
	},function(){
		if($(this).attr("class") != "selected" && $(this).attr("class") != "useed"){
			$(this).find(".jian").css("border-color","#c6c6c6");
			$(this).find("span").css("color","#c6c6c6");
			$(this).find(".fonts").css("color","#c6c6c6");
			var srcc = $(this).find("img").attr("src");
			srcc = srcc.substring((srcc.lastIndexOf("/") + 1),(srcc.lastIndexOf(".") -1));
			$(this).find("img").attr("src",rootPath + "/images/marquee/" + srcc +"1.png");
		}
	});
	
	//循环
	$("input[name='count']").click(function(){
		if($("input[name='count']:checked").val() == 1){
			$("#marqueeCount").removeAttr("disabled");
		}else{
			$("#marqueeCount").attr("disabled","disabled");
		}
	});
	
	//字体颜色
	$(".colfont li").click(function(){
		var col = $(this).attr("data-col");
		$(".sebg").attr("data-col",col);
		look();
	});
	
	$("#fontSize").change(function(){
		look();
	});
	
	//保存设置
	$(".btn-save").click(function(){
		var content = $.trim($("#content").val());
		var cycleTime = -1;
		var singleCostTime = $("input[name='time']:checked").val();
		var fontSize = $("#fontSize").val();
		var fontColor = "0x" + ($(".sebg").attr("data-col"));
		var marqueeLineId = $("li.selected").attr("data-id");
		if(content == null || content == ""){
			$.msg("请填写跑马灯内容",1000);
			return false;
		}
		if($("input[name='count']:checked").val() == 1){
			cycleTime = $("#marqueeCount").val();
			if(cycleTime == null || cycleTime == ""){
				$.msg("请设定跑马灯循环次数",1000);
    			return false;
			}
			var reg = /^\d+$/;
			if(!reg.test(cycleTime)){
				$.msg("请输入正整数数字",1000);
				return false;
			}
		}
		if(cycleTime == 0 || cycleTime == 1){
			$.msg("马灯循环次数不能是0或1",1000);
			return false;
		}
		$.ajax({
			url : rootPath +"/company/saveMarquee",
			type:"post",
			data:{"content":content,"cycleTime":cycleTime,"singleCostTime":singleCostTime,"fontSize":fontSize,"fontColor":fontColor,"marqueeLineId":marqueeLineId},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				if(data.msg == "success"){
					$(".loading").hide();
					$.msg("设置完成",1000,function(){
			            $(".loading").show();
						location.reload();
					});
				}else{
					$.msg(data.msg,1000);
					$(".loading").hide();
		            $(".loading-bg").hide();
				}
			}
		});
	});
	
	//预览
	look();
});

function look(){
	var content = $.trim($("#content").val());
	var size = $("#fontSize").val();
	var color = $(".sebg").attr("data-col");
	if(content == null || content == ""){
		content = "盗版必究";
	}
	$(".looks").html("<span style='color:#" + color + ";font-size:" + size + "px'>" + content + "</span>");
}

function onlyNum() {
	var event = arguments.callee.caller.arguments[0] || window.event; 
	if (!(event.keyCode == 46) && !(event.keyCode == 8) && !(event.keyCode == 37) && !(event.keyCode == 39)){
		if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105))){

			if(window.event){
				event.returnValue = false;
			}else{
				event.preventDefault();
			}
		}
	}
}