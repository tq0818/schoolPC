/*
*   title: 滑块公共部分;
*   description: 购买时候出现的滑块使用到的js;
*   date: 2015-04-16;
*   author: Piaoyis;
*/

// define(['jquery','plus/jquery.units.js','plus/jquery-ui.js','./plus/miniTip.js'],function(require){
//     var $ = require('jquery');
    
//     require('plus/jquery.units.js');
//     require('plus/jquery-ui.js');
//     require('plus/miniTip.js');
    // Document Ready
    $(function(){
        // tips
        $('i.ask').miniTip();
        // 滑块
        $('.slider').slider({
            range: "min",
            min : 0,
            max : 5, 
            value: 0,
            step : 1,
            change : function(){
            },
            slide: function(event, ui){
                var 
                    o = $(event.target).find('.slider-list em'),
                    index = ui.value - 1;

                if ( index !== -1 ){
                    o.eq(index).addClass('light').nextAll().removeClass('light');
                }else{
                    o.removeClass('light');
                }
            }
        });

        // 新版进度条
        $('.progress').slider({
            range: "min",
            min : 0,
            max : 100, 
            value: 0,
            step : 1,
            change : function(){
            },
            slide: function(event, ui){
                var 
                    o = $(event.target).find('.pro-number-cale em'),
                    index = ui.value - 1;

                if ( index !== -1 ){
                    o.eq(index).addClass('light').nextAll().removeClass('light');
                }else{
                    o.removeClass('light');
                }

                $(event.target).find('.pro-num-input').val(ui.value);
            }
        });

        $( "#minbeds" ).change(function() {
            slider.slider( "value", this.selectedIndex + 1 );
        });
    });
// });