// 分校首页，创建分校弹窗
$(function () {

    //点击创建分校弹窗弹框
    $('.createBranch').click(function () {
        $('.popupContainer').show();
        $('.popupOpacity').show();
    });
    //点击确定关闭弹窗
    $('.submitSchoolSure').click(function () {
        $('.popupContainer').hide();
        $('.popupOpacity').hide();
    });
    //点击取消关闭弹窗
    $('.submitSchoolCancel').click(function () {
        $('.popupContainer').hide();
        $('.popupOpacity').hide();
    });


    //    点击向上向下箭头变换并排序
    $('.sorting').click(function(){
        if($(this).hasClass('unsort')){
            $(this).removeClass('unsort');
            $(this).addClass('iconUp');
            $(this).html('&#xe68d;');

        }else if($(this).hasClass('iconUp')){
            $(this).removeClass('iconUp');
            $(this).addClass('iconDown');
            $(this).html('&#xe68e;');
        }else {
            $(this).removeClass('iconDown');
            $(this).addClass('unsort');
            $(this).html('&#xe612;');
        }
    });


});
