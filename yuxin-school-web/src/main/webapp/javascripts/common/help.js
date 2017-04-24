;(function($){
	var $ajax=function(options){
		var url=rootPath+"/help";
		options.url=url+options.url;
		var defaults={
			url: url+"#",
			type: "post",
			dataType:"json",
			success: function(){},
			error: function(resp,msg,err){
				console.log(resp);
			}
		}
		var settings=$.extend({},defaults,options);
		$.ajax(settings);
	}
	var GetUrlRelativePath = function() {
		var url = document.location.toString();
		var arrUrl = url.split("//");

		var start = arrUrl[1].indexOf("/");
		var relUrl = arrUrl[1].substring(start);// stop省略，截取从start开始到结尾的所有字符

		if (relUrl.indexOf("?") != -1) {
			relUrl = relUrl.split("?")[0];
		}
		return relUrl;
	}
	var checkForm=function(){
		var content=$(".help-feed-div").find("textarea").val();
		var result=true;
		if(!content){
			$(".help-feed-div").find("textarea").next().html("内容不能为空哦，骚年~");
			result=false;
		}
		if(content.length<20){
			$(".help-feed-div").find("textarea").next().html("内容不能少于20个字哦，骚年~");
			result=false;
		}
		return result;
	}
	
	var openHelp=function(){
		$(".help-feed-mb").fadeIn(200,function(){
			$(".help-feed-div").fadeIn(200);
		});
	}
	var closeHelp=function(){
		$(".help-feed-div").fadeOut(300,function(){
			$(".help-feed-mb").fadeOut(300);
		});
	}
	
	$(function(){
		$('body').find(".help-div").remove();
		var currtPath=GetUrlRelativePath();
		$.ajax({
			url: rootPath+"/help/find",
			data: {
				url: currtPath
			},
			type: "post",
			dataType:"json",
			success: function(help){
				var $help='<div class="help-div"><div class="wrap"><div class="help-bar">';
				if(help.helpUrl){
					$help+='<div class="bar doc" link="'+help.helpUrl+'"><i class="bar-ico iconfont">&#xe60f;</i><em class="bar-text">文档帮助</em></div>';
				}
				if(help.videoUrl){
					$help+='<div class="bar video"  link="'+help.videoUrl+'"><i class="bar-ico iconfont">&#xe627;</i><em class="bar-text">视频帮助</em></div>';
				}

				$help+='<div class="bar feed"><i class="bar-ico iconfont">&#xe628;</i><em class="bar-text">我要反馈</em></div>'+
//				'<div class="bar"><i></i><em>返回顶部</em></div>'+
				'</div></div></div>';
				$help+='<div class="help-feed-div none"><div class="pjax-container">'+
					'<div class="others clearfix">'+
					'<h1>意见反馈</h1><i class="close iconfont">&#xe610;</i>'+
					'<div class="feedback-wrap">'+
					'<div class="field-wrap">'+
					' <div>'+
					'      <textarea name="info" id="info" class="phd error-field" cols="30" rows="10" placeholder="骚年，哪有不爽，来喷吧！"></textarea>'+
					'       <p class="rlf-tip-wrap rlf-tip-error"></p>'+
					'    </div>'+
					'     <div>'+
					'          <input type="text" name="contact" id="contact" class="phd" placeholder="请留下真实联系方式（手机、邮箱、QQ），方便我们答疑解惑！">'+ 
					'           <p class="rlf-tip-wrap"></p>'+
					'        </div>'+
					'         <div class="btn-wrap">'+
					'              <button hidefocus="true" id="submit" class="r">提交</button>'+
					'               <p id="feedback-error" class="rlf-tip-wrap rlf-tip-error" style="display: none;"></p>'+
					'            </div>'+ 
					'        </div>'+
					'    </div>'+
					'</div>'+
					'</div></div><div class="help-feed-mb none"></div>';
				$('body').append($help);
				$(".help-feed-div").on("click.btn.close",".close",function(){
					closeHelp();
				})
				$(".feedback-wrap").on("blur.field.feed","textarea",function(){
					checkForm();
				})
				.on("click.btn.feed","#submit",function(){
					if(!checkForm()){
						return;
					}
					$.ajax({
						url: rootPath+"/help/feedBack",
						data: {
							content: $(".help-feed-div").find("textarea").val(),
							constract: $(".help-feed-div").find(":text").val()
						},
						type: "post",
						dataType: "json",
						success: function(jsonData){
							$.msg("您的反馈我们已经收到，非常感谢！");
							closeHelp();
						}
					})
				})
			},
			error : function(resp,msg,err){
				console.log(msg,err)
			}
		})

		$(document).on('click.link.help','.bar',function(e){
			if($(this).hasClass("toTop")){
				$(window).scrollTo(0);
			}else if($(this).hasClass("feed")){
				openHelp();
				
			}else{
				window.open($(this).attr("link"),"_blank");
			}
			
		})
		$(window).scroll(function(){
			$(".help-div").css("top",$(".help-div").css("top")+$(window).scrollTop());
		})
	});
})(jQuery)