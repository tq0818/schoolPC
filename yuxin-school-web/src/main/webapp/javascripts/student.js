/*
*   title: 学员课程单元公共JS;
*   description: 学员课程单元;
*   date: 2015-04-14;
*   author: Piaoyis;
*/

// define(['jquery','plus/jquery.units.js','plus/miniTip.js'],function(require){
//     var $ = require('jquery');
    
//     require('plus/miniTip.js');

    $(function(){
        $('.s-list')
            .on('click','a.btn',function(){
                var 
                    // 当前对象
                    _this = $(this),
                    // 是否被选中
                    active = _this.hasClass('active');

                if ( !active ) {
                    _this.addClass('active').siblings('a').removeClass('active');
                }
            })

        // 复选框
        $('.m-tools')
            .on('click','i.iconfont',function(){
                var
                    // 文字 A为选中 9为置空
                    txt = ['&#xe60a;','&#xe609;'],
                    // 是否为选中
                    active = $(this).hasClass('active');

                    $(this)
                        [active?'removeClass':'addClass']('active')
                        .html(txt[active?1:0]);

            })

        // 更多
        $('span.more')
            .on('click','a.m',function(){
                var 
                    // 获得当前元素
                    _this = $(this),
                    // 状态文字
                    txt = ['更多','收起'],
                    // 获得当前状态 true 为显示更多
                    active = _this.text() == txt[0],
                    // 获得父元素
                    parent = $(this).parents('.mark-more');

                if ( active ) {
                    _this.text(txt[1]);
                    parent.find('.none').fadeIn(200);
                    $('#more-tel').fadeOut(0);
                } else {
                    _this.text(txt[0]);
                    $('#more-tel').fadeIn(0);
                    parent.find('.none').fadeOut(0);
                }
            })

        // 人数
        $('.btn-view,.btn-failure')
            .on('click',function(){
                $('.add-classes-bg').fadeIn(200,function(){
                    $('.add-classes').fadeIn(200);
                });
            });
        // 弹层
        $('.add-classes')
            .on('click','i.close,.btn-cancel',function(){
                $('.add-classes').fadeOut(200,function(){
                    $('.add-classes-bg').fadeOut(200);
                });
            });
//        $(".money").bind("keyup",function(event){
//			//先把非数字的都替换掉，除了数字和. 
//			$(this).val($(this).val().replace(/[^\d.]/g,""));
//	        //必须保证第一个为数字而不是. 
//	        $(this).val($(this).val().replace(/^\./g,"0."));
//	        //保证只有出现一个.而没有多个. 
//	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
//	        //保证.只出现一次，而不能出现两次以上
//	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
//		})
		$(document).on('blur.money','input.money',function(event){
			//先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d.]/g,""));
			var value=$(this).val();
 	        var is=false;
 	        for (var i = 0; i < value.length; i++) {
 	            var  item =  value.charAt(i);
 	           	if("."==item){
 	           		is=true;
 	           	}
 	        }
 	        if(value!=null||value!=""){
 	        	if(!is){
 	        		$(this).val($(this).val()+".00");	
 	        	}
 	        } 
	        //必须保证第一个为数字而不是. 
	        $(this).val($(this).val().replace(/^\./g,"0."));
	        //保证只有出现一个.而没有多个. 
	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	        //保证.只出现一次，而不能出现两次以上
	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
	        //保留小数点后两位
	        $(this).val($(this).val().substring(0,$(this).val().indexOf(".")+3));
		});
    });
// });