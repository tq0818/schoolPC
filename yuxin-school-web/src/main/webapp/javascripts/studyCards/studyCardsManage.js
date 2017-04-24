/**
 * 	学习卡管理
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
                _$.event();
                self.event();
                self.search();
            },

            event: function () {
                var self = this;
                //菜单选中
                $selectSubMenu('studycard_head');
                
                $(doc).on('click', 'studyCard-button', function(){
                	if(!$(this).data('url')){
                		return false;
                	}
                    if($(this).data('target') == '_blank'){
                        win.open($(this).data('url'));  
                    }else{
                    	win.location.href = $(this).data('url');
                    }
                    
                }).on('click','.studyCard-search',function(){
                	self.search();
                }).on('click','.studyCard-create',function(){
                	win.location.href = rootPath + '/companyStudyCard/createStudyCards';
                }).on('click','.studyCard-export',function(){
                	if (_$.getData("tablesize") <= 0) {
                        $.msg("没有数据可以导出");
                    } else {
                        $(".studyCardFrom").attr("action", rootPath + "/companyStudyCard/exportStudyCards").submit();
                    }
                }).on('click','.studyCard-create',function(){
                	win.location.href = rootPath + '/companyStudyCard/gotoCreateStudyCard';
                }).on('click','.studyCard-more',function(){
                	if(_$.getData("studycard-active") == 1) return false;
                	_$.setData("studycard-active",1);
                	var cardId = $(this).parents('tr').attr('data-id');
                	self.createLibs(cardId,function(){
                		win.location.href = rootPath + '/companyStudyCard/gotoStudyCardLibDetails?cardId=' + cardId;
                	})
                }).on('click','.studyCard-libsExport',function(){
                	if(_$.getData("studycard-active") == 1) return false;
                	_$.setData("studycard-active",1);
                	var cardId = $(this).parents('tr').attr('data-id');
                	self.createLibs(cardId,function(){
                		 $("#cardId").val(cardId);
                         $(".studyCardLibsFrom").attr("action", rootPath + "/companyStudyCard/exportStudyCardLibs").submit();
                         $("#cardId").val('');
                	})
                })
                
                //日期
                $('.data-picker').datetimepicker({
                	format: "yyyy-mm-dd",
  	                minView: 2,
  	                autoclose: true,
  	                language: "zh-CN"
        	    }).on('changeDate', function(ev) {
        	        $(this).datetimepicker('hide');
        	    });
                
                self.placeholderCompatible();
            },
            
            search: function( page ){
            	var self = this, data = {};
            	data.page = page || 1;
            	data.pageSize = 10;
            	
            	data.startDate = $("#startDate").val();
            	data.endDate = $("#endDate").val();
            	
            	if(data.endDate < data.startDate){
            		$.msg("时间范围不正确");
            		return false;
            	}
            	
            	data.name = $("#name").val();
            	data.proxyName = $("#proxyName").val();
            	
            	_$.ajax({url: '/companyStudyCard/queryStudyCards', data: data, lazy:true, beforeSend: function () { $(".loading.data").show(); $(".loading-bg").show(); }, complete: function () {  $(".loading.data").hide(); $(".loading-bg").hide(); }, success: function(jsonData)
            		{
            			switch(jsonData.result){
            				case "success":
            					 $(".studyCard-list").find("table").find("tr:gt(0)").remove();
		        				 var html = "";
		        				 _$.setData("tablesize",jsonData.studyCards.data.length);
		        				 if (jsonData.studyCards.data.length == 0) {
		                             $(".studyCard-list").find("table").append('<tr><td colspan="8">没有查找到数据</td></tr>');
		                         }
		                         $.each(jsonData.studyCards.data,function (i, card) {
		                        	 html += '<tr class="tab-dtr" data-id="'+card.id+'">' +
		                        	 		 '<td>'+(card.name?(card.proxyOrgId?(card.name + '_' + card.proxyOrgName):card.name):'')+'</td>' +
		                        	 		 '<td>'+(card.totalNum?card.totalNum:'')+'</td>' +
		                        	 		 '<td>'+(card.usedNum)+'</td>' +
		                        	 		 '<td>'+(card.useDateBegin?card.useDateBegin + ' 至 ' + card.useDateEnd:'永久有效')+'</td>' +
		                        	 		 '<td>'+(card.price?card.price:(card.price == 0?card.price:''))+'</td>' +
		                        	 		 '<td>'+(card.createTime?card.createTime:'')+'</td>' +
		                        	 		 '<td>'+(card.realName?card.realName:(card.username?card.username:''))+'</td>' +
		                        	 		 '<td><span class=" Withdraw"><em class="studyCard-more">详情</em>  |  <em class="studyCard-libsExport">导出 </em></span></td>' +
		                        	 		 '<tr>';
		                         });
		                         $(".studyCard-list").find("table").append(html);
		                         if (jsonData.studyCards.rowCount > 10) {
		                             $(".pagination").pagination(jsonData.studyCards.rowCount,
		                                 {
		                                     next_text: "下一页",
		                                     prev_text: "上一页",
		                                     current_page: jsonData.studyCards.pageNo - 1,
		                                     link_to: "javascript:void(0)",
		                                     num_display_entries: 8,
		                                     items_per_page: jsonData.studyCards.pageSize,
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
            					 $.msg(jsonData.msg);
            					 break;
            			}
            		}
            	})
            },
            
            createLibs: function(cardId, callback){
            	var self = this, data = {};
            	if(!cardId){
            		$.msg("数据异常，请刷新页面重试！")
            	}
            	data.cardId = cardId;
            	_$.ajax({url: "/companyStudyCard/createStudyCardLibs",data: data, lazy: true, beforeSend: function () { $(".loading.create").show(); $(".loading-bg").show(); }, complete: function () {  $(".loading.create").hide(); $(".loading-bg").hide(); }, success: function(jsonData) 
            		{	
                		_$.setData("studycard-active",0);
						switch(jsonData.result){
							case "success":
								if(callback)
									callback();
								break;
							case "error":
							default:
								$.msg(jsonData.msg);
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