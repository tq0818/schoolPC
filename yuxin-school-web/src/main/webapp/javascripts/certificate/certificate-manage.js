$(function(){
   

//点击关闭按钮关闭当前弹出框
    $('.close').click(function(){
        $(this).parents('.L-allowAdmissionsTc').css('display','none');
        $('.layer-tips-bg').css('display','none');
    });

//点击保存或者取消按钮关闭当前弹出框
   $('.layer-tips-btns #cancelConfig').click(function(){
        $(this).parents('.L-allowAdmissionsTc').css('display','none');
        $('.layer-tips-bg ').css('display','none');
    });



//发放和查看
    $('.userTable td a').click(function(){
        if($(this).hasClass('releaseCertificate')){
            //发放证书提示
            $('.certificate').eq(0).css('display','block');
        }else{
            //查看证书提示
        }
        $('.layer-tips-bg').css('display','block');
    });

    //时间插件
    $('.date-picker').datetimepicker({
        language:  'zh-CN',
        format:'yyyy-mm-dd',
        weekStart: 1,
        todayHighlight:true,
        startView: 2,
        forceParse: 0,
        minView:3,
        showMeridian: 1
    }).on('changeDate', function (ev) {
        $(this).datetimepicker('hide');
    });


    $('.state-left button').click(function(){
        $('.state-left button').each(function(){
            if($(this).hasClass('onAc')){
                $(this).removeClass('onAc');
            }
        });

        $(this).addClass('onAc');
    });
});

