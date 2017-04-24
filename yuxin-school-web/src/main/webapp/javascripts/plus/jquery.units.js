/*
*   title: 常用插件;
*   description: 常用的小插件;
*   date: 2015-04-10;
*   author: Piaoyis;
*/

;(
    function (factory){
        if (typeof define === "function" && define.amd) {
            // 如果define已被定义，课程单元化代码
            define( ["jquery"], factory );
        }else if ( typeof define === "function" && define.cmd ) {
            define(function(require){
                var $ = require('jquery');
                factory($);
            })
        }else {
            // 如果define没有被定义，正常执行jQuery
            factory(jQuery);
        }
    }(function($){
        $.units = {
            loading : function(opts){
                var 
                    // 默认参数
                    config = {
                        // 默认内容
                        content : '正在加载内容...',
                        // 是否自动消失
                        autohide : true,
                        // 隐藏时间
                        timeout : 2000,
                        // 消失的速度
                        speed : 200
                    },
                    // 合并参数
                    opt = $.extend({},config,opts),
                    // loading 框架
                    html = '<div class="loading lp-units-loading">'
                            +'<p><i></i>'+ opt.content +'</p>'
                            +'</div>'
                            +'<div class="loading-bg lp-units-loading-bg"></div>';

                // 将内容显示到页面
                opt.autohide && ( opt.timeout > 0 )? 
                    $(html)
                        .appendTo('body')
                        .delay(opt.timeout)
                        .fadeOut(opt.speed,function(){
                            $(this).remove();
                        }):
                    $(html)
                        .appendTo('body');

                // 销毁
                $.units.loading.destroy = function(){
                    $('.lp-units-loading,.lp-units-loading-bg').remove();
                }
            },
            // 获得地址栏参数
            getQuery : function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]); return null;
            }
        };
        
        $.fn.tips = function(opts){
            var 
                // 配置信息
                config = {
                    title : '',
                    target: 'mouseover',
                    time : 2000
                },
                // 合并参数
                opt = $.extend({},config,opts),
                // 当前对象
                _this = $(this);

                return _this.each(function(){
                    var $this = $(this),
                        x = $this.offset().left,
                        y = $this.offset().top;

                        $this.on('mouseover',function(){
                            console.log(x,y);
                        });
                });
        }
    })
);