/**
 * Created by zhuo on 2016/3/2.
 */
(function($){
    $(function(){

        if($('input:radio[name="testchoose"]:checked').val() == 1){
            $(".test-in-padd").fadeIn(200);
        }else{
            $(".test-in-padd").fadeOut(200);
        }
        
        $("input:checkbox").on("click",function(){
        	if($(this).val() == 1){
        		$(this).val(0);
        		$(this).attr("checked","checked");
        	}else{
        		$(this).val(1);
        		$(this).removeAttr("checked");
        	}
        	var val = $(this).val();
        	status(val,$(this).attr("data-code"));
        });
        
        $("input:radio[name='testchoose']").on("click",function(){
            var _val = $('input:radio[name="testchoose"]:checked').val();
            if(_val == 1){
                $(".test-in-padd").fadeIn(200);
            }else if(_val == 0){
                $(".test-in-padd").fadeOut(200);
                $(".notpaper").removeAttr("checked");
                $(".notpaper").val(1);
            }

        	status(_val,$(this).attr("data-code"));
        });
    });
    
    function status(val,code){
    	$.ajax({
    		url : rootPath + "/tikuConfig/upStatus",
    		type:"post",
    		data:{"val":val,"code":code},
    		dataType:"json",
			beforeSend : function(XMLHttpRequest) {
				$(".loading").show();
				$(".loading-bg").show();
			},
    		success:function(data){
    			if(data.msg == "success"){
    				$.msg("应用设置成功！");
    			}else{
    				$.msg(data.msg);
    			}
    		},
			complete : function(XMLHttpRequest, textStatus) {
				$(".loading").hide();
				$(".loading-bg").hide();
			}
    	});
    }
})(jQuery);
