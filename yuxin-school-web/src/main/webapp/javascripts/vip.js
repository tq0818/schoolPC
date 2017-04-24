/**
 * Created by zhuo on 2016/5/19.
 */
(function($){
    $(function(){
        $(".c-main").on("click.a",".show-student",function(){
        	id=$(this).find("input:first").val();
        	 $.ajax({
                 url: rootPath + "/companyMemberConfig/queryUsers",
                 data: {"userId":id},
                 type: 'post',
                 beforeSend: function (XMLHttpRequest) {
                     $(".loading").show();
                     $(".loading-bg").show();
                 },
                 success: function (student) {
                	 if(student.identityTypeCode!=null){
                		 if(student.identityTypeCode=="ID_IDCARD"){
                			 $("#identityTypeCode").val("身份证");
                		 }
                		 if(student.identityTypeCode=="ID_PASSPORT"){
                			 $("#identityTypeCode").val("护照");
                		 }
                		 if(student.identityTypeCode=="ID_HMP"){
                			 $("#identityTypeCode").val("港澳通行证");
                		 }
                		 if(student.identityTypeCode=="ID_TCP"){
                			 $("#identityTypeCode").val("台胞证");
                		 }
                		 if(student.identityTypeCode=="ID_OFFICERS"){
                			 $("#identityTypeCode").val("军官证");
                		 }
                		 if(student.identityTypeCode=="ID_SERGEANTS"){
                			 $("#identityTypeCode").val("士官证");
                		 }
                		 if(student.identityTypeCode=="ID_UNCONFM_ID"){
                			 $("#identityTypeCode").val("未确定证件");
                		 }
                	 }
                    $("#name").val(student.name?student.name:"");
                    $("#sex").val(student.sex?(student.sex == "MALE"?"男":"女"):"");
                    $("#mobile").val(student.mobile?student.mobile:"");
                    $("#age").val(student.age?student.age:"");
                    $("#identityId").val(student.identityId?student.identityId:"");
                    $("#mobile1").val(student.mobile?student.mobile:"");
                    $("#homePhone").val(student.homePhone?student.homePhone:"");
                    $("#officePhone").val(student.officePhone?student.officePhone:"");
                    $("#emergencyContact").val(student.emergencyContact?student.emergencyContact:"");
                    $("#emergencyPhone").val(student.emergencyPhone?student.emergencyPhone:"");
                    $("#email").val(student.email?student.email:"");
                    $("#weixinId").val(student.weixinId?student.weixinId:"");
                
                 },
                 complete: function (XMLHttpRequest, textStatus) {
                     $(".loading").hide();
                     $(".loading-bg").hide();
                 }
             });
            $(".add-page-pop").fadeIn(100);
            $("body").addClass("popup-open");
        });
        $(".page-pop-title em").on("click", function () {
            $(".add-page-pop").fadeOut(200);
            $("body").removeClass("popup-open");
        });

        $(".edt").on("click.b",function(){
            $(".add-page-pop").fadeIn(100);
        });
        $(".priced").on("change keyup keydown keypress",function(){
            var _val = $(this).val();
            $(".pric").val(_val);
        })

    })
})(jQuery);