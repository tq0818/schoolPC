;(function($){
    var CerPage={
		queryCertificate : function(userId,cerId,classId){
			var xhr = new XMLHttpRequest();    
            xhr.open("post", rootPath + '/certificateConfig/queryCertificate?userId='+userId+'&cerId='+cerId+'&classId='+classId, true);
            xhr.responseType = "blob";
            xhr.onload = function() {
                if (this.status == 200) {
                    var blob = this.response;
        　　　　　	　　	$(".cerbanner").css({"background-image":"url("+window.URL.createObjectURL(blob)+")","background-size":"cover"});
                }else{
                	$.alert("加载证书失败,请刷新后再试！");
                }
            } 
            xhr.send();
		}
    }
    window.CerPage=CerPage;
})(jQuery)
