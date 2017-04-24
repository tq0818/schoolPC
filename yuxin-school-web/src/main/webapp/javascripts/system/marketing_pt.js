$(function(){
	$(".temp-boxs").on('click',".temp",function(){
		var $this = $(this),dataInfo=$(this).find(".tool_style").data(),chooseImg = $(this).find(".tool_style").prev();
//		if(!$(this).find(".choosed").is(':hidden')){
//			return ;
//		}
		$.ajax({ 
  		  type: "post", 
  		  url: rootPath+"/sysConfigPage/configPage",
  		  data: dataInfo,
  		  dataType : 'json',
  		  success: function(data){
  			$.msg("保存成功");
  			$this.css("border","1px solid #09f").find(".tool-choosed").show();
  			$this.parent(".temp-boxs").siblings().find(".temp").css("border","1px solid #ddd").find(".tool-choosed").hide();
  		  },
  		  error:function(){
  			$.msg("保存失败");
  		  }
	  	});
	});
});
