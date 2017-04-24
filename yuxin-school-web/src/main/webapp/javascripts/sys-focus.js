/*
*   title: 幻灯片设置;
*   description: 设置系统幻灯片;
*   date: 2015-04-13;
*   author: Piaoyis;
*/

// define(['jquery','plus/jquery.units.js'],function(require){
//     var $ = require('jquery');
    $(function(){
        // 轮播图设置
        // 新增一个幻灯片
	   $('#addfocus')
       .on('click',function(){
           $('li.add-focus','.set-focus').slideDown(400);
       });

       // 取消添加
       $('.btn-cancel-add')
           .on('click',function(){
               $('li.add-focus','.set-focus').slideUp(400);
           });
        // 配置信息事件
        $('.set-focus')
            .on('click','i.iconfont',function(){
                var 
                    // 开关图标  03为左侧块 04为右侧块
                    s = ['&#xe603;','&#xe604;'],
                    // 开关文字
                    st = ['已启用','已禁用'],
                    // 获得开关实体
                    o = $(this),
                    // 获得当前状态
                    staus = o.hasClass('open');

                o
                    [staus?'removeClass':'addClass']('open')
                    [staus?'addClass':'removeClass']('close')
                    .html(s[staus?1:0])
                    .next('span.i').text(st[staus?1:0])
                    [staus?'removeClass':'addClass']('open')
                    [staus?'addClass':'removeClass']('close');

            })
            // 上传图片按钮
            .on('click','a.c-uf',function(){
                var 
                    _this = $(this),
                    // 获得真实的上传按钮
                    upbtn = _this.next('input');

                upbtn.click();
                // 阻止默认动作
                return false;
            })
            // 相应点击时间按钮变化
            .on('click','input[type="text"],a.c-uf,input[type="file"].u-f,i.iconfont,a.bt',function(){
                var _this = $(this).parents('.set-infos').find('a.btn-submit');

                // 单击之后按钮变可用
                // 如果已经是激活状态则不继续响应
                if ( !(_this.hasClass('.btn-primary')) ){
                    _this.addClass('btn-primary').removeClass('btn-gray');
                }
            });
    });
// });