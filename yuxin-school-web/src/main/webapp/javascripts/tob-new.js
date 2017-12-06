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
    })
})
