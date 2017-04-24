/*
*   title: 购买会员;
*   description: 购买会员用到的js;
*   date: 2015-04-16;
*   author: Piaoyis;
*/

// define(['jquery','plus/jquery.units.js'],function(require){
//     var $ = require('jquery');
//     require('plus/jquery.units.js');
    // Document Ready
    $(function(){
        var 
            // 获得学科小类ID
            id = $.units.getQuery('id'),
            // 获得加亮内容
            part = '.'+$.units.getQuery('part'),
            // 要操作的元素
            o = $('li.m-title');

        // 处理ID
        id = id < 2? '2': id;

        o.find('span.m-' + id).append('<span class="tips">您目前享受</span>');
        $(part).addClass('light');
    });
// });