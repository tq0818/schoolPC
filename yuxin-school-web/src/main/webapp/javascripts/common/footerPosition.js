
// 使用方法：$.footerPosition({cur:'.footer',pre:'.article'}); cur：为底部元素的id或者class，pre：是底部前一个上一个块的ID或者class
$.extend({
    footerPosition:function (options){
        var ele=options.cur,pre=options.pre;
        // 为了解决第一次ajax回调时页面高度低于可视区高度，而影响第二次计算的现象
        $(pre).height('auto');
        var currentHeight=$(ele).offset().top+$(ele).height(),
            clienHeight=$(window).outerHeight(),
            preHeight=$(pre).height();
        if(currentHeight<clienHeight){
            $(pre).height(preHeight+clienHeight-currentHeight);
        }else{
            $(pre).height('auto');
        }
    }
});
