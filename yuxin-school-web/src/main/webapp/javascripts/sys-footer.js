/*
*   title: 页脚设置;
*   description: 系统设置页尾配置页面;
*   date: 2015-04-13;
*   author: Piaoyis;
*/
// define(['jquery','plus/jquery.units.js'],function(require){
//     var $ = require('jquery');
    $(function(){
        // 相应配置的input的单击事件
        $('.c')
            .on('click','input[type="text"]',function(){
                var _this = $(this);

                _this
                    .removeClass('readonly')
                    .removeAttr('readonly')
                    .focus();
            })
            // 如果按键则激活保存按钮
            .on('keyup','input[type="text"]',function(e){
                var _this = $(this),
                    code = e.keyCode;

                // 如果没有被激活则不相应按键事件
                if ( !(_this.hasClass('readonly')) ){

                    // 相应回车事件
                    if ( code == '13' ){
                        // 检测按钮是否被激活
                        var btn = _this.next('.btn').hasClass('btn-primary');
                        // 如果保存按钮没有被激活则不进行保存
                        if ( btn ){
                            _this.next('.btn').click();
                        }else{
                            _this
                                .addClass('readonly')
                                .attr('readonly','readonly');
                        }

                    }else {

                        _this
                            .next('.btn')
                            .removeClass('btn-default')
                            .addClass('btn-primary')
                            .val('保存');    
                    }

                }

                
            })
            .on('click','input[type="button"]',function(){
                var _this = $(this),
                    id = _this.prev('input').attr('name'),
                    val = '';

                if ( _this.hasClass('btn-primary') ){
                    // 获取输入内容
                    val = 
                        _this
                        .val('修改')
                        .removeClass('btn-primary')
                        .addClass('btn-default')
                        .prev('input')
                        .addClass('readonly')
                        .attr('readonly','readonly').val();

                        // 提交保存
                        alert(id + ' 提交保存！内容为：' + val);
                }else{
                    _this
                        .val('保存')
                        .removeClass('btn-default')
                        .addClass('btn-primary')
                        .prev('input')
                        .removeClass('readonly')
                        .removeAttr('readonly');
                }
            })
            // 监控输入框实时变化
            .on('input propertychange','input[type="text"]',function(){
                var 
                    id = $(this).attr('name'),
                    val = $(this).val()||'';

                // 将结果实时显示在页面上，使用纯文本形式显示
                $('#i-'+id).text(val);

            });

        // 设置关于我们 tabs 切换
        $('#about-tabs')
            .on('click','a',function(){
                var o = $(this),
                    i = o.index();

                if( !(o.hasClass('active')) ){
                    o.addClass('active').siblings('a').removeClass('active');
                    alert('当前索引为: ' + i);
                }
            });
    })
// });