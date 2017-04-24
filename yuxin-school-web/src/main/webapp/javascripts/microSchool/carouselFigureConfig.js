/**
 *  微网校轮播图  
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
                $( '#' + this.options.dataId ).data( name, value );
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
            },
            
            event: function(){
            	var self = this;
            	
            	$(doc).on('click', '.addbtn', function(){
            		$(".add-focus").show();
            	}).on('click', '.btn-cancel-add', function(){
            		$(".add-focus").hide();
            		
            	})
            }
        });

        return Module;

    })( new Plugin() );
   
    $( function () {
        return new Module();
    });

})( window, document, jQuery );