$(document).on("click.btn.signUp",".icon-btn,.badge-info",function(e){
	 var ele=e.target;
 	if($(ele).is("a")){
 		var num=parseInt($(ele).find("span").html());
     	$(ele).find("span").html(num+1);
 	}else if($(ele).is("span")){
 		var num=parseInt($(ele).html());
     	num=num>0?num-1:0;
     	$(ele).html(num);
     	return false;
 	}	        	
});