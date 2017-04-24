(function($){
    $(document).ready(function(){
         var allWidth=$(".q-mainbackground").width();
        if(window.screen.width<=1600&&window.screen.width>=1280){
            perWidth=parseInt((allWidth-40)/3-40);
        $(".q-ucont ul li").css("width",perWidth)
        }
        if(window.screen.width==1920){
             perWidth=parseInt((allWidth-60)/4-40);
        $(".q-ucont ul li").css("width",perWidth)
        }
    });
  
})(jQuery)