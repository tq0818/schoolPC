/**
 * 	学习卡详情
 *  @author Ds
 */
;( function ( win, doc, $, undefined ) {
    
    var Plugin = ( function () {

        function Plugin( settings ) { var defaults = { dataId: 'dataId' }; this.options = $.extend( defaults, settings || {} ); }

        $.extend( Plugin.prototype, {

            ajax: function ( settings ) { var defaults = { url: '', data: {}, type: 'post', dataType: 'json', async: true, success: function () {}, error: function () {}, beforeSend: function () { $(".loading").show(); $(".loading-bg").show(); }, complete: function () {  $(".loading").hide(); $(".loading-bg").hide(); }, lazy: false }; var options = $.extend( defaults, settings || {} ); $.ajax({ url: encodeURI( rootPath + options.url ), data: options.data, type: options.type, dataType: options.dataType, async: options.async, beforeSend: function ( XMLHttpRequest ) { if( options.lazy && options.beforeSend ) { options.beforeSend(); } }, success: options.success || function () {}, error: options.error || function () {}, complete: function ( XMLHttpRequest, textStatus ) { if( options.lazy && options.complete ) { options.complete(); } } }); },

            setData: function ( name, value ) { if( !doc.getElementById( this.options.dataId ) ){  $( 'body' ).append( '<input type="hidden" id=' + this.options.dataId + '>' ); } $( '#' + this.options.dataId ).attr( 'data-' + name, value ); return this; },

            getData: function ( name ) { return $( '#' + this.options.dataId ).attr( 'data-' + name ); },

            event: function () { $(doc).on('click', '.url-button', function(){ if(!$(this).data('url')){ return false; } if($(this).data('target') == '_blank'){  window.open($(this).data('url'));  }else{ window.location.href = $(this).data('url');  } }) }

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
                self.event();
                self.search();
            },

            event: function () {
                var self = this;
                if($("#errorMsg").val()){
                	$.msg($("#errorMsg").val());
                }
                //菜单选中
                $selectSubMenu('studycard_head');
                
                $(doc).on('click','.libs-search',function(){
                	self.search();
                })
                self.placeholderCompatible();
            },
            
            search: function(page){
            	var self = this, data = {};
            	
            	data.page = page || 1;
            	data.pageSize = 10;
            	
            	data.cardId = $("#cardId").val(); 
            	data.username = $("#username").val();
            	data.mobile = $("#mobile").val();
            	data.name = $("#name").val();
            	data.code = $("#code").val();
            	
            	_$.ajax({url: "/companyStudyCard/queryStudyCardLibs", data: data, lazy: true, success:function(jsonData)
            		{
            			switch(jsonData.result){
            				case "success":
            					$(".studyCardlibs-list").find("table").find("tr:gt(0)").remove();
		        				 var html = "";
		        				 if (jsonData.studyCardLibs.data.length == 0) {
		                             $(".studyCardlibs-list").find("table").append('<tr><td colspan="6">没有查找到数据</td></tr>');
		                         }
		                         $.each(jsonData.studyCardLibs.data,function (i, card) {
		                        	 html += '<tr class="tab-dtr" >' +
		                        	 		 '<td>'+(card.code?card.code:'')+'</td>' +
		                        	 		 '<td>'+(card.status?(card.status == '1'?'已使用':(card.stuId?'使用失败':'未使用')):(card.stuId?'使用失败':'未使用'))+'</td>' +
		                        	 		 '<td>'+(card.username?card.username:'')+'</td>' +
		                        	 		 '<td>'+(card.mobile?card.mobile:'')+'</td>' +
		                        	 		 '<td>'+(card.name?card.name:'')+'</td>' +
		                        	 		 '<td>'+(card.useTime?card.useTime:'')+'</td>' +
		                        	 		 '<tr>';
		                         });
		                         $(".studyCardlibs-list").find("table").append(html);
		                         if (jsonData.studyCardLibs.rowCount > 10) {
		                             $(".pagination").pagination(jsonData.studyCardLibs.rowCount,
		                                 {
		                                     next_text: "下一页",
		                                     prev_text: "上一页",
		                                     current_page: jsonData.studyCardLibs.pageNo - 1,
		                                     link_to: "javascript:void(0)",
		                                     num_display_entries: 8,
		                                     items_per_page: jsonData.studyCardLibs.pageSize,
		                                     num_edge_entries: 1,
		                                     callback: function (page, jq) {
		                                         self.search(page + 1);
		                                     }
		                                 });
		                         } else {
		                             $(".pagination").html('');
		                         }
            					break;
            				case "error":
            				default:
            					break;
            			}
            		}
            	})
            	
            },
            
            placeholderCompatible: function(){
            	//判断浏览器是否支持placeholder属性
        	    supportPlaceholder='placeholder'in document.createElement('input'),
        	    placeholder=function(input){
            	    var text = input.attr('placeholder'),
            	    defaultValue = input.defaultValue;
            	    if(!defaultValue){
            	      input.val(text).addClass("phcolor");
            	    }
            	    input.focus(function(){
            	      if(input.val() == text){
            	        $(this).val("");
            	      }
            	    });
            	    input.blur(function(){
            	      if(input.val() == ""){
            	        $(this).val(text).addClass("phcolor");
            	      }
            	    });
            	    //输入的字符不为灰色
            	    input.keydown(function(){
            	      	$(this).removeClass("phcolor");
            	    });
        	    };
        	    //当浏览器不支持placeholder属性时，调用placeholder函数
        	    if(!supportPlaceholder){
	        	    $('input').each(function(){
	        	      text = $(this).attr("placeholder");
	        	      if($(this).attr("type") == "text"){
	        	        placeholder($(this));
	        	      }
	        	    });
        	     }
            }
            	 
        });

        return Module;

    })( new Plugin() );
   
    $( function () {
        return new Module();
    });

})( window, document, jQuery );