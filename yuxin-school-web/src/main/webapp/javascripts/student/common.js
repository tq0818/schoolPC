/**
 * 公用的方法和对象
 */
(function($){
	var Page={
		init:function(){
			$.extend($.validator.messages, {
			    required: "必选字段",
				remote: "请修正该字段",
				email: "请输入正确格式的电子邮件",
				url: "请输入合法的网址",
				date: "请输入合法的日期",
				dateISO: "请输入合法的日期 (ISO).",
				number: "请输入合法的数字",
				digits: "只能输入整数",
				creditcard: "请输入合法的信用卡号",
				equalTo: "请再次输入相同的值",
				accept: "请输入拥有合法后缀名的字符串",
				maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
				minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
				rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
				range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
				max: jQuery.validator.format("请输入一个最大为{0} 的值"),
				min: jQuery.validator.format("请输入一个最小为{0} 的值")
			});
			$(".date-picker").datepicker({format: 'yyyy-mm-dd',autoclose: true,language:"zh_CN"});
		},
		validate:function(){
		
		},
		defaults:{
			
		},
		pagination: function(data,func){
			if(!func){
				alert("没有可执行的分页查询!");
				return;
			}
			$(".pagination").children().remove();
			$(".pagination").pagination(data.rowCount, {
	    		next_text : "下一页",
	    		prev_text : "上一页",
	    		current_page : data.pageNo-1,
	    		link_to : "javascript:void(0)",
	    		num_display_entries : 8,
	    		items_per_page : data.pageSize,
	    		num_edge_entries : 1,
	    		callback:function(page,jq){
	    			var pageNo = page + 1;
	    			func(pageNo);
	    		}
	    	});
		},
		lastPageUrl: ""
		
	}
	var Layout= {
		/**
		 * 加载中动画
		 */
		loading:function(options){
			if(options){
				if(typeof options === 'string'){
					options={element:$(options)};
				}else if(options instanceof jQuery){
					options={element:options};
				}else{
					options=$.extend({},options,{element:$('.page-content')});
				}
				
			}else if(!options){
				options={element:$(".page-content")};
			}
			var defaults={
					element:$(".page-content"),
					width:options.element.width(),
					height:options.element.height(),
					left:options.element.offset().left+parseInt($(options.element).css("padding-left").replace("px","")),
					top:options.element.offset().top+parseInt($(options.element).css("padding-top").replace("px","")),
					minHeight:100,
					minWidth:200,
					minTop: 10,
					minLeft: 10,
					mbOpacity:0 //白色蒙板透明度
			};
			var settings=$.extend({},defaults,options);
			var mb=$("<div id='ld-mb' class='ld-mb'></div>");
			var animate=$("<div id='ld-animate' class='ld-animate'><div class='loading'></div></div>");
			settings.element.append(mb).append(animate);
			var width=settings.width>settings.minWidth?settings.width:settings.minWidth;
			var height=settings.height>settings.minHeight?settings.height:settings.minHeight;
			var top=settings.top>settings.minTop?settings.top:settings.minTop;
			var left=settings.left>settings.minLeft?settings.left:settings.minLeft;
			mb.width(width).height(height).css({"top":top,"left":left,"filter":"alpha(opacity="+settings.mbOpacity*100+")",
				"-moz-opacity":settings.mbOpacity,"-khtml-opacity": settings.mbOpacity,"opacity": settings.mbOpacity}).show();
			animate.css({"top":(height/2-50)>0?top+(height/2-50):top,"left":(width/2-100)>0?left+(width/2-100):left}).fadeIn(300);
		},
		/**
		 * 取消loading
		 */
		loadover:function(){
			if($("#ld-mb") && $("#ld-animate")){
				$("#ld-mb").hide();
				$("#ld-animate").fadeOut(500,function(){
					$(".ld-animate").each(function(){
						$(this).remove();
					});
					$(".ld-mb").each(function(){
						$(this).remove();
					});
				});
			}
		}
	}
	var Util={
			/**
			 * json序列化为字符串
			 * @param json
			 * @returns
			 */
			serialize: function(json,tag){
				var serializeStr="";
				$.each(json,function(key,value){
					if(value){
						if(tag){
							serializeStr+="&"+tag+"."+key+"="+value;
						}else{
							serializeStr+="&"+key+"="+value;
						}
						
					}
				})
				return serializeStr.substring(1,serializeStr.length);
			},
			changeNum: function(i){
				i=i+1;
				var arg=["一", "二", "三", "四", "五", "六", "七", "八", "九","十"];
				var str="";
				if(i/10>1){
					var n=Math.floor(i/10-1)?Math.floor(i/10-1):"";
					if(n>0){
						str=arg[n]+arg[9];
					}else{
						str=arg[9];
					}
					
					if(i%10>0){
						str+=arg[i%10-1]
					}
				}else if(i>10){
					str=(arg[Math.floor(i/10-1)]?arg[i/10-1]:"")+arg[9];
				}else{
					str=arg[i-1];
				}
				return str;
			},
			getFormatDate: function (today){
			    var day = today?today:new Date();
			    var Year = 0;
			    var Month = 0;
			    var Day = 0;
			    var CurrentDate = "";
			    Year= day.getFullYear();//支持IE和火狐浏览器.
			    Month= day.getMonth()+1;
			    Day = day.getDate();
			    CurrentDate += Year;
			    if (Month >= 10 ){
			     CurrentDate += "-"+Month;
			    }
			    else{
			     CurrentDate += "-0" + Month;
			    }
			    if (Day >= 10 ){
			     CurrentDate += "-"+Day ;
			    }
			    else{
			     CurrentDate += "-0" + Day ;
			    }
			    return CurrentDate;
			 }
	}
	window.Page=Page;
	window.Layout=Layout;
	window.Util=Util;
})(jQuery)

