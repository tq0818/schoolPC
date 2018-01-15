
$(function(){
	$(".searchContents").click(function(){
		classQueryDetail(1,null);
	});
	
	 $('.btn-sort').click(function(){
	        if($(this).hasClass('unsort')){
	            $(this).removeClass('unsort');
	            $(this).addClass('iconUp');
	            // $(this).html('&#xe68d;');
	        }else if($(this).hasClass('iconUp')){
	            $(this).removeClass('iconUp');
	            $(this).addClass('iconDown');
	            // $(this).html('&#xe68e;');
	        }else {
	            $(this).removeClass('iconDown');
	            $(this).addClass('unsort');
	            // $(this).html('&#xe612;');
	        }
	      /*  classQueryDetail(1,1);*/
	    });
    $(".buyNumMax").on('click', function () {
        if($("#buyNumMax").val()==1){
            //2 asc 1 desc
            $("#buyNumMax").val("2")
            classQueryDetail(1,1);
        }else{
            $("#buyNumMax").val("1")
            classQueryDetail(1,2);
        }
    });
    $(".actelNum").on('click', function () {
        if($("#actelNum").val()==3){
            //2 asc 1 desc
            $("#actelNum").val("4")
            classQueryDetail(1,3);
        }else{
            $("#actelNum").val("3")
            classQueryDetail(1,4);
        }
    });
    classQueryDetail(1,null);
});

function classQueryDetail(pageNo,paixu1){
	var paixu=paixu1;
	var order_signup='';
	var order_buy='';
	if($("#order_signup").hasClass('iconUp')){
		order_signup='1';
	}else if($("#order_signup").hasClass('iconDown')){
		order_signup='0';
	}
	if($("#order_buy").hasClass('iconUp')){
		order_buy='1';
	}else if($("#order_buy").hasClass('iconDown')){
		order_buy='0';
	}
	$.ajax({
		url : rootPath + "/classQuery/classQueryDetail",
		type:"post",
		data:{"companyId":$("#companyId").val(),"name":$("#name").val(),"lable":$("#lable").val(),
			"order_signup":order_signup,
			"order_buy":order_buy,"page":pageNo,
			"pageSize":$.trim($("#pageSize").val()),
			"paixu":paixu},
		dataType:"html",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		success:function(data){
			$(".tbodyList").html(data);
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
		});
}