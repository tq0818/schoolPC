/**
 * 	充值卡开关
 *  @author Ds
 */
;( function ( win, doc, $, undefined ) {
    
    var Plugin = ( function () {

        function Plugin( settings ) { var defaults = { dataId: 'dataId' }; this.options = $.extend( defaults, settings || {} ); this.event(); }

        $.extend( Plugin.prototype, {

            ajax: function ( settings ) { var defaults = { url: '', data: {}, type: 'post', dataType: 'json', async: true, success: function () {}, error: function () {}, beforeSend: function () { $(".loading").show(); $(".loading-bg").show(); }, complete: function () {  $(".loading").hide(); $(".loading-bg").hide(); }, lazy: false }; var options = $.extend( defaults, settings || {} ); $.ajax({ url: encodeURI( rootPath + options.url ), data: options.data, type: options.type, dataType: options.dataType, async: options.async, beforeSend: function ( XMLHttpRequest ) { if( options.lazy && options.beforeSend ) { options.beforeSend(); } }, success: options.success || function () {}, error: options.error || function () {}, complete: function ( XMLHttpRequest, textStatus ) { if( options.lazy && options.complete ) { options.complete(); } } }); },

            setData: function ( name, value ) { if( !doc.getElementById( this.options.dataId ) ){  $( 'body' ).append( '<input type="hidden" id=' + this.options.dataId + '>' ); } $( '#' + this.options.dataId ).attr( 'data-' + name, value ); return this; },

            getData: function ( name ) { return $( '#' + this.options.dataId ).attr( 'data-' + name ); },

            event: function () { $(doc).on('click', '.url-btn', function(){ if(!$(this).data('url')){ return false; } if($(this).data('target') == '_blank'){  window.open($(this).data('url'));  }else{ window.location.href = $(this).data('url');  } }) }

        });

        return Plugin;

    })();

    var Module = ( function ( _$ ) {

        function Module( settings ) {
            var defaults = {};
            this.options = $.extend( defaults, settings || {} );
            this.init();
        }

        $.extend( Module.prototype, {

            init: function () {
                var self = this;
                $(doc)
                .on('click','.screen-right-title em.normal',function(){
                	var _this = $(this);
                	if(_this.hasClass("open")){
                		self.updateOpenFlag(0,function(){
                			_this.removeClass("open").addClass("close").html("&#xe604;");
                			_this.parent().find(".i").removeClass("open").addClass("close").text("已禁用");
                		})
					}else if(_this.hasClass("close")){
						self.updateOpenFlag(1,function(){
							_this.removeClass("close").addClass("open").html("&#xe603;");
							_this.parent().find(".i").removeClass("close").addClass("open").text("已启用");
                		})
					}
                })
            },

            event: function () {
                var self = this;
            },
            
            updateOpenFlag: function(status,callback){
            	var self = this,
            		data = {};
            	data.status = status;
            	_$.ajax({url: '/companyRechargePatch/updateOpenFlag', data: data, success: function(jsonData)
            		{
            			switch(jsonData.result){
            			case 'success':
            				$.msg('修改成功！');
            				if(callback) callback();
            				break;
            			case 'error':
            			default:
            				$.msg(jsonData.msg?jsonData.msg:'');
            				break;
            			}
            		}
            	})
            }
      
        });

        return Module;

    })( new Plugin() );
   
    $( function () {
        return new Module();
    });

})( window, document, jQuery );