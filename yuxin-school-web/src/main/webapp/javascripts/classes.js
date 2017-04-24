/*
*   title: 课程
*   description: 课程用到的JS
*   date: 2015-04-25
*/
//
//define(['jquery','plus/jquery.units.js','plus/jquery.sortable'],function(require){
//    var $ = require('jquery');
//    require('plus/jquery.sortable');
    // Document ready
    $(function(){
        // 列表拖动
//        $('.son').sortable({
//                items: ':not(.dis)'
//            });
        $('i.iconfont')
            .on('click',function(){
                $(this).parent('.add-layer').fadeOut(200,function(){
                    $('.add-layer-bg').fadeOut(200);
                });
            });

        $('.btn-add-layer')
            .on('click',function(){
                $('.add-layer-bg').fadeIn(200,function(){
                    $('.w800').fadeIn(200);
                })
            });
/* 特意删掉的，不要再覆盖回来了！！*/
//        $('.btn-add-class')
//            .on('click',function(){
//                $('.add-layer-bg').fadeIn(200,function(){
//                    $('.add-class-layer').fadeIn(200);
//                })
//            });
//
//        // 弹层处理
//        $('.upload-layer')
//            .on('click','i.close',function(){
//                $('.upload-layer').fadeOut(200,function(){
//                    $('.add-layer-bg').fadeOut(200);
//                });
//            })
//            // 取消
//            .on('click','.btn-cancel',function(){
//                $(this).parents('.pic-upload').fadeOut(200,function(){
//                    alert('这个仅作示例，为了展示列表')
//                })
//            })
//            .on('click','li.add',function(){
//                $('.pic-upload').fadeIn(200,function(){
//                    alert('仅作示例，具体根据实际情况自行修改！')
//                })
//            });

        // 弹层滚动条
        $('.term-list').scroll(function(){
            var _this = $(this),
                head = $('.table-head');

            if( head.length > 0 ) {
                head.css({'top': _this.scrollTop()})
            }
        })
        // 上传按钮
        $('.btn-upload')
            .on('click',function(){
                $('.add-layer-bg').fadeIn(200,function(){
                    $('.upload-layer').fadeIn(200);
                })
            })
//    });
}); 