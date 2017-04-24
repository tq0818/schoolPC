/*
*   title: 导航设置;
*   description: 系统设置导航设置;
*   date: 2015-04-13;
*   author: Piaoyis;
*/

// define(['jquery','plus/jquery.units.js'],function(require){
//     var $ = require('jquery');
   $(function(){
        // 导航设置相关
        // 上传logo
        $('#c-up')
            .on('click',function(){
                $(this).next('input.up').click();
            });

        // 热门推荐
        // 热门推荐配置按钮
        $('#navbarconfig')
            .on('click','.btn-config',function(){
                var 
                    // 获取按钮
                    _this = $(this),
                    // 获得按钮状态是否为禁用
                    status = !(_this.hasClass('disabled')),
                    // 获得配置信息
                    o = _this.next('.hot-commond');


                // 如果按钮没有激活则不响应点击事件
                if ( status ){
                    o.slideToggle(200);
                }

            })
            .on('click','.btn-switch',function(){
                var 
                    // 获得点击对象
                    _this = $(this),
                    // 该按钮是否为禁用
                    status = _this.hasClass('btn-primary'),
                    // 状态文字
                    stext = ['已禁用','已启用']
                    // 获得状态文字样式
                    st = _this.prevAll('span.pro-name').hasClass('open');

                _this
                    [status?'removeClass':'addClass']('btn-primary')
                    [status?'addClass':'removeClass']('btn-default')
                    .val(stext[status?'1':'0'])
                    .prevAll('span.pro-name')
                    [st?'removeClass':'addClass']('open')
                    [st?'addClass':'removeClass']('close');

            })
            // 保存按钮激活
            .on('keyup','.input-control',function(e){
                var 
                    // 获得按键
                    code = e.keyCode,
                    // 当前input对象
                    _this = $(this),
                    // 保存按钮
                    btn = _this.next('input.btn'),
                    // 状态文字
                    st = ['修改名称','保存修改'];

                    if ( code !== 13 ){
                        btn.addClass('btn-primary').val(st[1]);
                    }else {
                        // 提交
                        btn.removeClass('btn-primary').val(st[0]);
                    }
            })
            // 点击修改
            .on('click','.btn-save',function(){
                var 
                    // 获得当前按钮
                    _this = $(this),
                    // 当前按钮是否激活
                    status = _this.hasClass('btn-primary');

                    if ( status ){
                        _this.removeClass('btn-primary').val('修改名称');
                    }

            })
            // 禁用按钮
            .on('click','.btn-disbale',function(){
                var 
                    // 状态文字
                    st = ['已禁用','已启用'],
                    // 按钮对象
                    _this = $(this),
                    // 按钮状态 是否已经被禁用
                    status = _this.hasClass('disabled');


                _this
                    [status?'removeClass':'addClass']('disabled')
                    .val(st[status?1:0]);

            });
   }) 
// });