/*
*   title: 系统设置;
*   description: 主页设置用到的js;
*   date: 2015-04-09;
*   author: Piaoyis;
*/

// define(['jquery','plus/jquery.units.js'],function(require){
//     var $ = require('jquery');
    $(function(){
        // 点击开通
        $('#open-service')
            .on('click','.btn-switch',function(){
                $('.layer-bg,.layer').fadeIn(200);
            });

        // 关闭弹层
        $('.layer')
            .on('click','i.close',function(){
                $('.layer-bg,.layer').fadeOut(200);
            })
            // 如果单击的是A
            .on('click','a',function(){
                var url = $(this).attr('href') || '/';

                $('.layer-bg,.layer').fadeOut(200);
                location.href = url;
                return false;
            })
    });
// });