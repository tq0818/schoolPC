/*
*   title: 资源课程单元;
*   description: 资源课程单元用到的js;
*   date: 2015-04-27;
*   author: Piaoyis;
*/

// define(function(require){
//     var $ = require('jquery');

    $(function(){

        // 取消添加
        $('.btn-cancel').on('click',function(){
            $(this).parents('.add-subs-layer').fadeOut(200,function(){
                $('.add-subs-layer-bg').fadeOut(200);
            });
        });
        
        // 添加学科小类btn-add-subs
        $('.btn-add-subs').on('click',function(){
            $('.add-subs-layer-bg').fadeIn(200,function(){
                $('.add-subs').fadeIn(200);
            });
        });

        // 编辑按钮
        $('.btn-edit,.btn-add-class,.btn-add-classroom')
            .on('click',function(){
                var _this = $(this),
                    parent = $('.add-teacher'),
                    bg = $('.add-teacher-room-bg');

                bg.fadeIn(200,function(){
                    parent.fadeIn(200);
                });
            });
        // 取消添加教室
        $('.btn-close')
            .on('click',function(){
                var _this = $(this),
                    parent = $('.add-teacher'),
                    bg = $('.add-teacher-room-bg');

                parent.fadeOut(200,function(){
                    bg.fadeOut(200);
                });
            });

        // 添加校区
        $('.btn-add-school')
            .on('click',function(){
                $('.add-subs-layer-bg').fadeIn(200,function(){
                    $('.add-school').fadeIn(200)
                });
            });

        // 停用校区
        $('.btn-stop-sc')
            .on('click',function(){
                $('.add-subs-layer-bg').fadeIn(200,function(){
                    $('.stop-subs').fadeIn(200)
                });
            });

        // 切换类别
        $('.tabs')
            .on('click','a.btn',function(){
                var 
                    _this = $(this),
                    // 获得索引
                    index = _this.index(),
                    // active
                    active = _this.hasClass('.btn-success'),
                    // 获得对象
                    obj = $('.sel-tabs .box-select');

                if (!active){
                    _this.addClass('btn-success').siblings('a').removeClass('btn-success').addClass('btn-default');
                }

                obj.eq(index).fadeIn(200).siblings('.box-select').fadeOut(200);

            });
    });
// });