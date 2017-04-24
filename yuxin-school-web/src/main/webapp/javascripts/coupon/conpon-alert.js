/**
 * Created by na on 2016/6/16 0016.
 */
$(document).ready(function(){
    //点击choice
    $(".toggle").click(function(){
            if($(".choice").is(":visible")){
//上去
                $(".choice").slideUp(500);
                $(".toggle i").removeClass("tanslatX");
				$(".toggle i").addClass("tanslateY");
            }else{
//下来
                $(".choice").slideDown(500);
                $(".toggle i").removeClass("tanslateY");
                $(".toggle i").addClass("tanslatX");
            }
    });
//hover
    $("#group i").hover(function(){
        $(this).html("&#xe6bd;");
    },function(){
        $(this).html("&#xe6be;");
    }
    );
});
