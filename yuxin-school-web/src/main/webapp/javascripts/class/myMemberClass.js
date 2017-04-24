$selectMenu("member_class");
	$(document).ready(function(){
		 $.ajax({
             url: rootPath + "/classType/memberLevelsList",
             type: 'post',
             beforeSend: function (XMLHttpRequest) {
                 $(".loading").show();
                 $(".loading-bg").show();
             },
             success: function (data) {
            		$(".show-vip-class").empty();
// 	            	if (data.length == 0) {
// 	            		   $(".show-vip-class").append(" <div class='vip-class'> "+
// 	                               " <p>未添加会员等级</p> "+
// 	                               " <p></p> "+
// 	                               " </div>");
// 	                 }
            	   $.each(data,function(i,item){
                       $(".show-vip-class").append(" <div class='vip-class' onclick='searchMemberClass("+item.id+")'> "+
                              " <p>"+item.name+"</p> "+
                              "   <p>包含"+item.ctmd.length+"个课程</p> "+
                              " </div>");
                   });
             },
             complete: function (XMLHttpRequest, textStatus) {
                 $(".loading").hide();
                 $(".loading-bg").hide();
             }
            
         });
		
	})
	function searchMemberClass(search){
		window.location.href = rootPath + "/classType/searchMemberClass/" + search;
	}
	$(".member_ClassType").on("click",function(){
		window.location.href=rootPath+"/classType/memberClass";
	})