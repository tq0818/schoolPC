/*
*   title: 系统必填项设置;
*   description: 系统必填项设置;
*   date: 2015-04-13;
*   author: Piaoyis;
*/
// define(['jquery','plus/jquery.units.js'],function(require){
//     var $ = require('jquery');
    $(function(){
        // 必选项信息设置
        $('.list')
            .on('click','a.btn',function(){
                var _this = $(this);

                    // 切换选填/必填
                    _this.hasClass('btn-primary')?
                        _this
                            .removeClass('btn-primary').addClass('btn-gray').text('取消必填')
                            .prev('span').find('sup').addClass('m').text('必填'):
                        _this
                            .removeClass('btn-gray').addClass('btn-primary').text('设为必填')
                            .prev('span').find('sup').removeClass('m').text('选填');
        
            });

        // 必填项快速选择
        $('.quickselect')
            .on('click','a',function(){
                var type = $(this).attr('class')||'all',
                    o = $(this).parents('.sm-heading').next('.list').find('ul');

                switch (type){
                    case 'all':
                    o.find('a.btn-primary').click();
                    break;

                    case 'select':
                    o.find('a.btn-gray').click();
                    break;

                    case 'reversal':
                    console.log('x')
                    o.find('a.btn').click();
                    break;
                }
            });

    });
// });