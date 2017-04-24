/**
 * author wang.zx
 * 初始化页面一学科小类
 */
(function($){
	var itemList={
			init : function(){
		         $("#itemOneId").getSysItem(function(){
		        	 $("#itemOneId").bind("change",function(){
		        		 $("#itemSecondId").html('').getSysItem($(this).val(),function(){
		        			 $("#itemSecondId").removeAttr("disabled");
		        		 });
		        	 });
		         }); 
			},
		};

	var itemList2={
			init : function(){
		         $(".addResourceTc #itemOneId").getSysItem(function(){
		        	 $(".addResourceTc #itemOneId").bind("change",function(){
		        		 $(".addResourceTc #itemSecondId").html('').getSysItem($(this).val(),function(){
		        			 $(".addResourceTc #itemSecondId").removeAttr("disabled");
		        		 });
		        	 });
		         }); 
			},
		};
	$(document).ready(function(){
		itemList.init();
		itemList2.init();
	});
	window.itemList=itemList;
})(jQuery);