(function($){
    $(function(){
        //    线上线下发放切换
        $(".coupon-title-tab").on("click.a",".tab-itm",function(){
            var type=$(this).attr("mark");
            $(".coupon-title-tab .tab-itm").removeClass("active");
            $(this).addClass("active");
            $(".coupon-out-type").hide();
            $("."+type).show();
        });
        //    优惠方式的点击
        $(".coupon-type").on("click.b","span input",function(){
            var t=$(this).attr("mark");
            $(this).parents(".coupon-type").find(".coupon-type-cont p").hide();
            $(this).parents(".coupon-type").find(".coupon-type-cont,p."+t).show();
        });
        //    使用时间的调用
        $(".date-picker").datetimepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            minView: 2,
            startDate: new Date(),
            autoclose: true
        });
        //    优惠对象为会员
        $(".user-chose-position div").on("click",function(){
            $(".member-p").hide()
            if($(this).attr("mark")=="member"){
                $(".member-p").show()
            }else if($(this).attr("mark")=="point-user"){
                $(".layer-tips-bg").show();
                $(".add-alert-content.point-user").show();
            }else{
                $(".add-layer-bg,.member-p,.add-alert-content.point-user").hide()
            }
        });
    //    编辑器调用
        var defaultcont= '<p>亲爱的【username】学员，您好：</p>'+
        '                            <p>&nbsp;&nbsp;&nbsp;&nbsp;为了感谢您一直以来对我们学校的支持，现特赠送您一张优惠券，已发放到您的个人中心，请注意查收！'+
        '                            <p>&nbsp;&nbsp;&nbsp;&nbsp;优惠信息如下：</p>'+
        '                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【couponinfo】</p>'+
        '                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可用于购买：【coursename】</p>'+
        '                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;使用期限：【startdate】到【enddate】 </p>'+
        '                            <p>&nbsp;&nbsp;&nbsp;&nbsp;使用方法：您在购买【coursename】时，在支付页面选择您可使用的优惠券，即可享受相应优惠。</p>';
        window.onload = function () {
        	var conten=decodeURI($("#noticContents").val());
        	var editor=CKEDITOR.replace('editor01');
     		editor.config.width=600;
     		editor.config.height=300;
         	editor.config.toolbar=[
         			['Source','Bold','Italic','Underline','Strike'],
         			['Image'],
         			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
         			['Link','Unlink'],
         			['Code']
         		];
         	if(conten && conten!=""){
         		editor.setData(conten);
         	}else{
         		editor.setData(defaultcont);
         	}
//         	 CKEDITOR.replace('editor01',{
//                 height : 300,
//                 width : 600
//         }).setData(defaultcont);
        };
    //  点击指定用户中的关闭按钮与取消保存按钮
        $(".s_box").on("click",".s_right.iconfont,.conserve button",function(){
            $(".add-alert-content,.layer-tips-bg").hide();
            var num=$("#choseStuNum").text();
            $("#tiketsNums").val(num);
        });

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
        //阻止事件冒泡

        function stopEvent(){ //阻止冒泡事件
            //取消事件冒泡
            var e=arguments.callee.caller.arguments[0]||event; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容
            if (e && e.stopPropagation) {
                // this code is for Mozilla and Opera
                e.stopPropagation();
            } else if (window.event) {
                // this code is for IE
                window.event.cancelBubble = true;
            }
        }
        
    //    模拟select
        $(".user-choses-selected,.select-sanjiao").on("click", function () {
            var click=$(this).attr("click");
            if(click=="true"){
                $(".user-chose-position").hide();
                $(".select-sanjiao b").removeClass("active");
                $(".user-choses-selected,.select-sanjiao").attr("click","false");
                stopEvent();
            }else{
                $(".user-chose-position").show();
                $(".select-sanjiao b").addClass("active");
                $(".user-choses-selected,.select-sanjiao").attr("click","true");
                stopEvent();
            }

        });

        $(".user-chose-position > div").on("click", function () {
            $(".user-choses-selected").html($(this).html()).attr("value",$(this).attr("ids"));
            $(".user-chose-position").hide();
            $(".select-sanjiao b").removeClass("active");
            $(".user-choses-selected,.select-sanjiao").attr("click","false");
        });
        
        $(document).on("click",function(){
            $(".user-chose-position").hide();
            $(".select-sanjiao b").removeClass("active");
            $(".user-choses-selected,.select-sanjiao").attr("click","false");
        })

    })
})(jQuery)