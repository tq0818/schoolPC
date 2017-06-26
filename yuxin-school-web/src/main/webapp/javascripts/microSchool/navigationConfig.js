/**
 *  微网校导航配置   
 *  @author Ds
 */
;( function ( win, doc, $ ) {
    
    var Module, Plugin;

    Plugin = ( function () {

        function Plugin( settings ) {
            var defaults = {
                dataId: 'dataId'
            };
            this.options = $.extend( defaults, settings || {} );
        }

        $.extend( Plugin.prototype, {

            ajax: function ( settings ) {
                var defaults = {
                    url: '',
                    data: {},
                    type: 'post',
                    dataType: 'json',
                    async: true,
                    success: function () {},
                    error: function () {},
                    beforeSend: function () { $(".loading").show(); $(".loading-bg").show(); },
                    complete: function () {  $(".loading").hide(); $(".loading-bg").hide(); },
                    lazy: false
                };
                var options = $.extend( defaults, settings || {} );
                $.ajax({
                    url: encodeURI( rootPath + options.url ),
                    data: options.data,
                    type: options.type,
                    dataType: options.dataType,
                    async: options.async,
                    beforeSend: function ( XMLHttpRequest ) {
                        if( options.lazy && options.beforeSend ) { options.beforeSend(); }                
                    },
                    success: options.success || function () {},
                    error: options.error || function () {},
                    complete: function ( XMLHttpRequest, textStatus ) {
                        if( options.lazy && options.complete ) { options.complete(); }
                    }
                });
            },

            setData: function ( name, value ) {
                if( !doc.getElementById( this.options.dataId ) ){
                    $( 'body' ).append( '<input type="hidden" id=' + this.options.dataId + '>' );
                }
                $( '#' + this.options.dataId ).attr( "data-"+name, value );
                return this;
            },

            getData: function ( name ) {
                return $( '#' + this.options.dataId ).attr( 'data-' + name );
            }

        });

        return Plugin;

    })();

    Module = ( function ( _$ ) {

        function Module( settings ) {
            var defaults = {};
            this.options = $.extend( defaults, settings || {} );
            this.init();
        }

        $.extend( Module.prototype, {

            init: function () {
                var self = this;
                self.event();
                self.sortEvent();
                self.queryNav();
                
            },
            
            event: function(){
            	var self = this;
            	$(doc).on('click','.headStatus', function(){
            		if($(this).find('i').hasClass('close')){
            			var isOk = self.queryService($(this).parents('.section').attr('data-service'))
            			if(!isOk && $(this).parents('.section').attr('data-service') != "SERVICE_CLASS"){
            				$.msg("此服务未开启,不能启用");
            				return false;
            			}
            			$(this).html('<i class="iconfont normal open" style="">&#xe603;</i> <span class="open">已启用</span>');
            		}else{
            			$(this).html('<i class="iconfont normal close" style="color: #999999;">&#xe604;</i> <span class="close">已禁用</span>');
            		}
            		self.saveStatus($(this).attr('data-service'));
            	}).on('click','.edit-icon',function(){
            		_$.setData("service",$(this).attr("data-service"));
            		var HeadName = $("#"+$(this).attr("data-service")).find(".HeadName").html();
            		$(".updateName").val(HeadName);
            		$(".Confirm").show();
            		$(".layerTipsBg").show();
            	}).on('click','.Confirm_Cancle,.Confirm_Close',function(){
            		_$.setData("service","");
            		$(".updateName").val("");
            		$(".Confirm").hide();
            		$(".layerTipsBg").hide();
            	}).on('click','.Confirm_Ok',function(){
            		if(!$(".updateName").val().replace(/(^\s*)|(\s*$)/g, "")){
            			$.msg("名称不能为空！");
            			return false;
            		}
            		_$.setData("updatename",$(".updateName").val());
            		self.saveStatus(_$.getData("service"),function(){
            			$("#"+_$.getData("service")).find(".HeadName").html($(".updateName").val());
            			$(".updateName").val("");
            			$(".Confirm").hide();
            			$(".layerTipsBg").hide();
            		})
            	})
            },
            
            sortEvent: function(){
            	var self = this;
            	$('#navbarconfigs').sortable({
                    placeholder: "ui-state-highlight",
                    items: "div.section:not(.ui-state-disabled)",
                    update:function(event,ui){
                       var count = 1;
                       $("#navbarconfigs").find(".section").each(function(){
                    	   $(this).attr("data-sort",count);
                    	   count++;
                       })
                       $("#navbarconfigs").find(".section").each(function(){
                    	   self.saveStatus($(this).attr("data-service"));
                       })
                       
                    },
                    delay: 200
                }).disableSelection();
            },
            
            queryNav: function(){
            	var self = this;
            	_$.ajax({url: '/microSchool/queryWaphead',success:function(jsonData)
            		{
            			if(jsonData.result == "success"){
            				$.each(jsonData.headlist, function(i, item){ 
            					$("#"+item.url).find(".HeadName").html(item.name);
            					$("#"+item.url).find(".HeadNameInput").val(item.name);
            					$("#"+item.url).attr("data-sort",item.sequence);
            					if(item.validFlag == 0){
            						$("#"+item.url).find(".headStatus").html('<i class="iconfont normal close" style="color: #999999;">&#xe604;</i> <span class="close">已禁用</span>');
            					}
            				})
            				self.sort();
            			}
            		}
            	})
            },
            
            sort: function(){
            	var self = this;
            	var SERVICE_CLASS,SERVICE_CLASS_VIDEO,SERVICE_CLASS_PACKAGE,SERVICE_OPENCLASS,SERVICE_TEACHER,SERVICE_TIKU,SERVICE_QUESTION_ANSWER,SERVICE_MEMBER;
            	var SERVICE_CLASS_HTML = "";
				var SERVICE_CLASS_VIDEO_HTML = "";
            	var SERVICE_CLASS_PACKAGE_HTML = "";
            	var SERVICE_OPENCLASS_HTML = "";
            	var SERVICE_TEACHER_HTML = ""; 
            	var SERVICE_TIKU_HTML = "";
            	var SERVICE_QUESTION_ANSWER_HTML = "";
            	var SERVICE_MEMBER_HTML = "";
            	$("#navbarconfigs").find(".section").each(function(){
            		if($(this).attr("data-service") == "SERVICE_CLASS"){
            			SERVICE_CLASS = $(this).attr("data-sort");
            			SERVICE_CLASS_HTML = $(this).prop('outerHTML');
            			$(this).remove();
            		}
					if($(this).attr("data-service") == "SERVICE_CLASS_VIDEO"){
						SERVICE_CLASS_VIDEO = $(this).attr("data-sort");
						SERVICE_CLASS_VIDEO_HTML = $(this).prop('outerHTML');
						$(this).remove();
					}
            		if($(this).attr("data-service") == "SERVICE_CLASS_PACKAGE"){
            			SERVICE_CLASS_PACKAGE = $(this).attr("data-sort");
            			SERVICE_CLASS_PACKAGE_HTML = $(this).prop('outerHTML');
            			$(this).remove();
            		}
            		if($(this).attr("data-service") == "SERVICE_OPENCLASS"){
            			SERVICE_OPENCLASS = $(this).attr("data-sort");
            			SERVICE_OPENCLASS_HTML = $(this).prop('outerHTML');
            			$(this).remove();
            		}
            		if($(this).attr("data-service") == "SERVICE_TEACHER"){
            			SERVICE_TEACHER = $(this).attr("data-sort");
            			SERVICE_TEACHER_HTML = $(this).prop('outerHTML');
            			$(this).remove();
            		}
            		if($(this).attr("data-service") == "SERVICE_TIKU"){
            			SERVICE_TIKU = $(this).attr("data-sort");
            			SERVICE_TIKU_HTML = $(this).prop('outerHTML');
            			$(this).remove();
            		}
            		if($(this).attr("data-service") == "SERVICE_QUESTION_ANSWER"){
            			SERVICE_QUESTION_ANSWER = $(this).attr("data-sort");
            			SERVICE_QUESTION_ANSWER_HTML = $(this).prop('outerHTML');
            			$(this).remove();
            		}
            		if($(this).attr("data-service") == "SERVICE_MEMBER"){
            			SERVICE_MEMBER = $(this).attr("data-sort");
            			SERVICE_MEMBER_HTML = $(this).prop('outerHTML');
            			$(this).remove();
            		}
            	})
            	for (var int = 1; int <= 8; int++) {
            		switch(''+int){
            			case SERVICE_CLASS:
            				$("#navbarconfigs").append(SERVICE_CLASS_HTML);
            				break;
						case SERVICE_CLASS_VIDEO:
							$("#navbarconfigs").append(SERVICE_CLASS_VIDEO_HTML);
							break;
            			case SERVICE_CLASS_PACKAGE:
            				$("#navbarconfigs").append(SERVICE_CLASS_PACKAGE_HTML);
            				break;
            			case SERVICE_OPENCLASS:
            				$("#navbarconfigs").append(SERVICE_OPENCLASS_HTML);
            				break;
            			case SERVICE_TEACHER:
            				$("#navbarconfigs").append(SERVICE_TEACHER_HTML);
            				break;
            			case SERVICE_TIKU:
            				$("#navbarconfigs").append(SERVICE_TIKU_HTML);
            				break;
            			case SERVICE_QUESTION_ANSWER:
            				$("#navbarconfigs").append(SERVICE_QUESTION_ANSWER_HTML);
            				break;
            			case SERVICE_MEMBER:
            				$("#navbarconfigs").append(SERVICE_MEMBER_HTML);
            				break;
                	}
				}
            	
            },
            
            saveStatus: function(className,callback){
            	var data = {}
            	data.name = _$.getData("updatename") || $("#"+className).find(".HeadName").html();
            	data.url = className
            	data.validFlag = $("#"+className).find(".headStatus").find("i").hasClass("open")?1:0;
            	data.sequence = $("#"+className).attr("data-sort");
            	_$.ajax({ url:'/microSchool/saveWaphead', data: data, success:function(jsonData)
            		{
            			if(jsonData.result == "success"){
            				$.msg("修改成功");
            				
            				if(callback){
            					callback();
            				}
//            				win.location.reload()
            			}else{
            				win.location.reload()
            			}
            		}
            	})
            },
            
            queryService: function(service){
            	var isOk;
            	_$.ajax({url:"/microSchool/queryServiceStatus",async: false,data:{"service":service},success:function(data)
            		{
            			if(data){
            				isOk = data;
            			}else{
            				isOk = false;
            			}
            		}            		
            	})
            	return isOk;
            }
            
        });

        return Module;

    })( new Plugin() );
   
    $( function () {
        return new Module();
    });

})( window, document, jQuery );