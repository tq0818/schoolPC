/*
*   title: 机构首页;
*   description: 机构首页用到的js;
*   date: 2015-04-14;
*   author: Piaoyis;
*/

// define(['jquery','plus/jquery.units.js','./plus/miniTip.js'],function(require){
//     var $ = require('jquery');

//     require('./plus/miniTip.js');
    
    $(function(){
        // 支付相关
        $('.pay-type-tab').on('click','a',function(){
            $(this).addClass('active').siblings('a').removeClass('active');
            $('.banks').eq($(this).index()).fadeIn(0).siblings('.banks').fadeOut(0);
        });

        $('.banks').on('click','a',function(){
            $(this).prev('input:radio').prop('checked','true');
        });

        // tips
        $('.ask').miniTip();
        $('.mainbackground')
            // 普通文本框响应键盘事件
            .on('keyup','.input-edit',function(e){
                var 
                    _this = $(this),
                    // 获得键盘代码
                    code = e.keyCode,
                    // 获得按钮
                    btn = _this.next('.btn'),
                    // 按钮是否被激活
                    active = btn.hasClass('bt-parimary'),
                    // 按钮文字
                    bt = ['修改','保存'];


                if ( code == 13 ) {
                    alert('按钮提交')
                    btn.click().removeClass('btn-primary').addClass('btn-default').val(bt[0]);
                }else {
                    btn.addClass('btn-primary').removeClass('btn-default').val(bt[1]);
                }

            })
            // 电话 邮件修改按钮
            .on('click','.btn-edit',function(){
                var 
                    // 获得按钮
                    _this = $(this),
                    // 获得按钮状态
                    active = _this.hasClass('btn-primary'),
                    // 获得输入
                    input = _this.prev('input'),
                    // 按钮文字
                    bt = ['修改','保存'];

                if ( active ) {
                    _this
                        .removeClass('btn-primary').addClass('btn-default').val(bt[0])                        
                        .prev('input').addClass('readonly').attr('readonly');
                }else {
                    _this
                        .addClass('btn-primary').removeClass('btn-default').val(bt[1])
                        .prev('input').removeClass('readonly').removeAttr('readonly');
                }
            })
    });
// });