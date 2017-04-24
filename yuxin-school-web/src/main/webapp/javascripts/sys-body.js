/*
*   title: 系统设置;
*   description: 主页设置用到的js;
*   date: 2015-04-09;
*   author: Piaoyis;
*/
    
// define(['jquery','plus/jquery.units.js','plus/jquery.sortable'],function(require){
//     var $ = require('jquery');
//     require('plus/jquery.units.js');
//     require('plus/jquery.sortable');

    $(function(){
        // 排序关闭
        $('#rank-title')
            .on('click',function(){
                $('.rank-mark').fadeOut(200);
            })
        // 主页设置
        $('.select-themes')
            .on('mouseenter','[data-index]',function(){
                var 
                    // 当前对象
                    _this = $(this),
                    // 获得额外数据
                    data = _this.attr('data-index'),
                    text = _this.find('h5').text(),
                    html = '<div class="lp-tips"><img src="/images/set-index/' + data +'.jpg"></div>',
                    o = $(html).appendTo(this),
                    m = '<div class="b"><li>'+ text +'</li></div>',
                    oh = o.innerHeight()>140? o.innerHeight() : 140,
                    ow = o.innerWidth(),
                    ox = -100,
                    oy = -(oh+20);

                // 将遮罩插入
                $(m).appendTo(this);

                _this.find('.b').fadeIn(200).addClass('xx');
                $('#rank-list,.b').sortable({ connectWith: '.b' }).bind('sortupdate',function(){
                    console.log('this is a log');
                    $('.rank-mark').delay(500).fadeOut(200)
                });
                o.css({'left':ox,'top':oy,'visibility':'visible'});

            })
            .on('mousedown','[data-index]',function(){
                // $('.rank-mark').fadeIn(200);
                
            })
            .on('mousemove','[data-index]',function(){

            })
            // 鼠标离开
            .on('mouseleave','[data-index]',function(){
                $(this).find('.lp-tips,.b').remove();
            })
            .on('dblclick','[data-index]',function(){
                var html = '<div>这是新增的DIV内容</div>';

                $.get('/demo.txt',function(data){
                    alert('插入需要的HTML，页面跟首页上的元素有区别，多了一个M的图层，详情见示例内容');
                    $(data).prependTo('#set-body');                
                });
            });

        // 课程单元的配置信息
        $('#set-body')
            // 排序按钮
            .on('click','.btn-rank',function(){
                $('.rank-mark').fadeIn(200);
                $('#rank-list').sortable().bind('sortupdate',function(){
                    console.log('this is a log');
                });
            })
            // 单击配置按钮
            .on('click','.btn-config',function(){
                var 
                    // 获得父元素
                    parent = $(this).parents('.warootPath');
            })
            // 单击移除
            .on('click','.btn-remove',function(){
                var
                    // 获得父元素
                    parent = $(this).parents('.wrap');

                parent.fadeOut(400,function(){
                    $(this).remove();
                });
            })
            // 鼠标悬浮
            .on('mouseenter','.wrap',function(){
                var mark = $(this).find('.m');

                mark.fadeIn(200);
            })
            // 鼠标移除
            .on('mouseleave','.wrap',function(){
                var mark = $(this).find('.m');

                mark.fadeOut(200);
            })

        // 区域设置信息
        $('.box-config')
            .on('click','span.switch',function(){
                var 
                    // 图标 03为滑块在左
                    si = ['&#xe603;','&#xe604;'],
                    // 显示文字
                    st = ['显示','隐藏'],
                    // 当前对象
                    _this = $(this),
                    // 是否为激活状态
                    s = _this.hasClass('open');

                _this
                    [s?'removeClass':'addClass']('open')
                    [s?'addClass':'removeClass']('close')
                    .find('i.iconfont')
                    .html(si[s?1:0])
                    .next('em')
                    .text(st[s?1:0]);
            })
            // 科目选择
            .on('click','a.btn-pro',function(){
                var 
                    // 当前对象
                    o = $(this),
                    // 是否激活
                    s = $(this).hasClass('btn-primary');

                if ( !s ){
                    o
                        .addClass('btn-primary').removeClass('btn-default')
                        .siblings('a.btn-pro').removeClass('btn-primary').addClass('btn-default');
                }
                /*
                o
                    [s?'removeClass':'addClass']('btn-primary')
                    [s?'addClass':'removeClass']('btn-default');
                */
            })
            // 上传图片
            .on('click','.btn-up',function(){
                $(this).next('input[type="file"]').click();
            })
            // 单选框
            .on('click','span.checkbox',function(){
                var 
                    // 按钮选中状态 0b为选中 0c为取消
                    st = ['&#xe60b;','&#xe60c;'],
                    // 当前对象
                    _this = $(this),
                    // 是否激活
                    s  = _this.hasClass('open');

                _this
                    [s?'removeClass':'addClass']('open')
                    [s?'addClass':'removeClass']('close')
                    .find('i.iconfont')
                    .html(st[s?1:0])
                    .end().siblings('.checkbox')['removeClass']('open').find('i.iconfont').html(st[1]);

                

            })
            // 推荐优先
            .on('click','span.radio',function(){
                var 
                    // 按钮选中状态 0a为选中 09为取消
                    st = ['&#xe60a;','&#xe609;'],
                    // 当前对象
                    _this = $(this),
                    // 是否激活
                    s  = _this.hasClass('open');

                _this
                    [s?'removeClass':'addClass']('open')
                    [s?'addClass':'removeClass']('close')
                    .find('i.iconfont')
                    .html(st[s?1:0])
                    .end().siblings('.checkbox')['removeClass']('open').find('i.iconfont').html(st[1]);
            });

    });
// });