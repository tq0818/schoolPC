/*
*   title: 系统设置;
*   description: 系统课程单元用到的样式;
*   date: 2015-04-09;
*   author: Piaoyis;
*/

// define(['jquery','plus/jquery.units.js'],function(require){
//     var $ = require('jquery');
    // Document Ready
    $(function(){
        // 小类切换的loading效果
        $('.selectsub')
            .on('click','a',function(){
                var 
                    _this = $(this),
                    text = _this.text(),
                    url = _this.attr('href'),
                    content = '正在加载'+ text+'内容...'

                // 如果按钮处在激活状态则不响应动作
                if ( !(_this.hasClass('btn-success')) ){
                    _this
                        .removeClass('btn-default')
                        .addClass('btn-success')
                        .siblings('a')
                        .removeClass('btn-success')
                        .addClass('btn-default');
                    

                    if ( url !== 'javascript:;' ) {
                        // 加载loading
                        $.units.loading({content:content,autohide:false});
                        
                        // 模拟请求，一秒钟之后再进行验证 
                        setTimeout(function(){
                            $.get(url,function(){
                                // 注销loading
                                $.units.loading.destroy();
                                // 执行最终动作
                                location.href = url;
                            });
                        },1000);
                    }
                    
                    // 阻止默认动作
                    return false;
                }            
            });
        
        // 权限管理
//        $('.pri-list')
//            .on('click','p.c',function(){
//                var status = ['&#xe609;','&#xe60a;'],
//                    _this = $(this).find('i.iconfont');
//
//                !_this.hasClass('active')?
//                    _this.html(status[1]).addClass('active'):
//                    _this.html(status[0]).removeClass('active');
//                    
//                return false;
//            })
//            .on('click','p.c-title',function(){
//                var status = ['&#xe609;','&#xe60a;'],
//                    _this = $(this).find('i.iconfont');
//
//                !_this.hasClass('active')?
//                    _this.html(status[1]).addClass('active').parent().nextAll('p.c').find('i.iconfont').html(status[1]).addClass('active'):
//                    _this.html(status[0]).removeClass('active').parent().nextAll('p.c').find('i.iconfont').html(status[0]).removeClass('active');
//
//                return false;
//            });

        // 教师权限管理
        $('.teacher-list')
            .on('click','a.btn',function(){
                var status = ['&#xe60c;','&#xe60b;'],
                    _this = $(this).find('i.iconfont');

                    !_this.hasClass('active')?
                    _this.html(status[1]).addClass('active'):
                    _this.html(status[0]).removeClass('active');

                return false;
            });
    });
// });