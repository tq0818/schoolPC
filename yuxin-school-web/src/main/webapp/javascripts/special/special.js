/* autor:wqj ;
*date:2017-08-14;
* 专题js
*/
$(document).ready(function(){
    teacher_choose();
});
function teacher_choose(argument) {
    $(".teacher-list,.choose-item").delegate("li","click",function(){
        $(this).toggleClass("active");
    });
    $(".choose-teacher").delegate(".btn-next","click",function(){
        var item = $(".teacher-list li.active");

        $(".choose-item").append(item.removeClass("active"));
        var teacherIds = "";
        item.each(function(i,v){
        	teacherIds = teacherIds + $(v).attr("data-id")+",";
          
        });
        if($.trim(teacherIds) !=""){
        	teacherIds = teacherIds.substring(0,teacherIds.length-1);
        }
        findCourse(teacherIds);
    });
    $(".choose-teacher").delegate(".btn-prev","click",function(){
        var cancelItem = $(".choose-item li.active");
        $(".teacher-list").append(cancelItem.removeClass("active"));
        clearCourse(cancelItem);
        /*$(cancelItem).each(function(i,v){
            var dId = $(v).attr("data-id");
            $(".choose-teacher").find("[data-id="+dId+"]").removeClass("active");
        });*/

    });


}