/**
 * 	充值卡管理
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
                $selectSubMenu('sales');
                $selectSubMenus("rachargecard_manage");
                self.event();
                self.search();
            },

            event: function () {
                var self = this;
                $(doc)
                //搜索
                .on('click','.patchs-search',function(){
                	self.search();
                })
                //禁用
                .on('click','.a-prevent',function(){
                	var	patchId = $(this).parents('tr').attr('data-id');
                	self.del(patchId);
                })
                //详情
                .on('click','.a-detail',function(){
                	var	patchId = $(this).parents('tr').attr('data-id');
                	_$.setData('patchId',patchId);
                	$('.libsShow').show();
                	$('.layer-tips-bg').show();
                	self.queryLibs(patchId);
                })
                //导出
                .on('click','.a-export',function(){
                	var	patchId = $(this).parents('tr').attr('data-id');
                	$('#export-patchId').val(patchId);
                	$("#export-form").attr("action", rootPath + "/companyRechargeLib/exportRechargeLibs").submit();
                })
                .on('click','.libs-close',function(){
                	$('.libsShow').hide();
                	$('.layer-tips-bg').hide();
                	_$.setData('patchId','');
                })
                .on('click','.libs-search',function(){
                	self.queryLibs();
                })
                .on('click','.rechargeCard-timemark',function(){
                	var _this = $(this)
                	if(_this.hasClass('active')){
                		_this.removeClass('active');
            			$('.c-use-time').hide();
                	}else{
                		$(this).addClass('active');
                		if(_this.attr('data-mark') == 'nos'){
                			$('.c-use-time').show();
                		}else{
                			$('.c-use-time').hide();
                		}
                	}
                	_this.siblings().each(function(){
            			$(this).removeClass('active');
                	})
                })
                .on('click','.rechargeCard-create',function(){
                	win.location.href = rootPath + '/companyRechargePatch/gotoRechargeCardCreatePage';
                })
                $('.date-picker').datetimepicker({
            	    format: "yyyy-mm-dd",
  	                minView: 2,
  	                autoclose: true,
  	                language: "zh-CN"
                })
            },
            
            search: function(page){
            	var self = this,
            		data = {};
            	
            	data.page = page || 1;
            	data.pageSize = 10;
            	
            	data.timeMark = $('.rechargeCard-timemark.active').attr('data-mark');
            	data.startTime = $('#rechargeCard-startTime').val();
            	data.endTime = $('#rechargeCard-endTime').val();
            	if(data.timeMark != 'nos'){
            		data.startTime = '';
                	data.endTime = '';
            	}
            	if(data.endTime<data.startTime){
    				$.msg("创建日期范围不正确！");
					return false;
    			}
            	data.startDate = $('#rechargeCard-startDate').val();
            	data.endDate = $('#rechargeCard-endDate').val();
            	if(data.endTime<data.startTime){
    				$.msg("有效期范围不正确！");
					return false;
    			}
            	data.minPrice = $('#rechargeCard-minPrice').val();
            	data.maxPrice = $('#rechargeCard-maxPrice').val();
            	if(data.maxPrice<data.minPrice){
    				$.msg("金额范围不正确！");
					return false;
    			}
            	data.orgName = $('#rechargeCard-orgName').val();
            	
            	_$.ajax({url: '/companyRechargePatch/queryRechargePatch', data: data, success: function(jsonData)
            		{
            			switch(jsonData.result){
            			case 'success':
            				 $(".card-list").find("table").find("tr:gt(0)").remove();
	        				 var html = "";
	        				 if (jsonData.patchs.data.length == 0) {
	                             $(".card-list").find("table").append('<tr><td colspan="7">没有查找到数据</td></tr>');
	                         }
	        				 $('#price-num').html($.formatMoney(jsonData.price)+'元');
	                         $.each(jsonData.patchs.data,function (i, patch) {
	                        	 html += '<tr data-id="'+patch.id+'">'+
	                                 '<td>'+patch.createDate+'</td>'+
	                                 '<td>'+(patch.orgName?patch.orgName:'')+'</td>'+
	                                 '<td>'+$.formatMoney(patch.cashAmount)+'</td>'+
	                                 '<td>'+patch.totalNum+'</td>'+
	                                 '<td>'+patch.timeLimitFrom+'至'+patch.timeLimitTo+'</td>'+
	                                 '<td>'+patch.usedNum+'</td>'+
	                                 '<td>'+
	                                 '    <div class="operate-cont">'+(patch.status == 0?'':
	                                 '        <a href="javascript:;" class="a-prevent" >禁用</a>'+
	                                 '        <em>|</em>')+
	                                 '        <a href="javascript:;" class="a-detail">详情</a>'+
	                                 '        <em>|</em>'+
	                                 '        <a href="javascript:;" class="a-export">导出</a>'+
	                                 '    </div>'+
	                                 '</td>'+
                        	 		 '</tr>';
	                         });
	                         $(".card-list").find("table").append(html);
	                         if (jsonData.patchs.rowCount > 10) {
	                             $(".pagination").pagination(jsonData.patchs.rowCount,
	                                 {
	                                     next_text: "下一页",
	                                     prev_text: "上一页",
	                                     current_page: jsonData.patchs.pageNo - 1,
	                                     link_to: "javascript:void(0)",
	                                     num_display_entries: 8,
	                                     items_per_page: jsonData.patchs.pageSize,
	                                     num_edge_entries: 1,
	                                     callback: function (page, jq) {
	                                         self.search(page + 1);
	                                     }
	                                 });
	                         } else {
	                             $(".pagination").html('');
	                         }
            				break;
            			case 'error':
            			default:
            				break;
            			}
            		}
            	})
            },
            
            del: function(patchId){
            	var self = this,
            		data = {};
            	$.confirm('禁用将导致未进行充值的充值码失效。您确定要禁用该充值卡吗？',function(b){
        		if(!b) return false;
            	data.id = patchId;
            	_$.ajax({url: '/companyRechargePatch/delPatch', data: data, success: function(jsonData)
            		{
            			switch(jsonData.result){
            			case 'success':
            				$.msg('禁用成功！');
            				self.search();
            				break;
            			case 'error':
            			default:
            				$.msg(jsonData.msg?jsonData.msg:'');
            				break;
            			}
            		}
            	})
            	})
            },
            
            queryLibs: function(patchId,page){
            	var self = this,
            		data = {};
            	data.page = page || 1;
            	data.pageSize = 10;
            	
            	data.status = $('#libs-status').val();
            	data.usernameAndMobile = $('#libs-user').val();
            	
            	data.patchId= patchId;
            	if(!data.patchId) data.patchId=_$.getData('patchId');
            	_$.ajax({url: '/companyRechargeLib/queryLibs', data: data, success: function(jsonData)
            		{
            			switch(jsonData.result){
            			case 'success':
            				 $(".libs-list").find("table").find("tr:gt(0)").remove();
	        				 var html = "";
	        				 if (jsonData.libs.data.length == 0) {
	                             $(".libs-list").find("table").append('<tr><td colspan="4">没有查找到数据</td></tr>');
	                         }
	                         $.each(jsonData.libs.data,function (i, lib) {
	                        	 html += ' <tr>'+
	                        	 '  <td>'+lib.code+'</td>'+
	                        	 '     <td>'+(lib.status==1?'已充值':'未充值')+'</td>'+
	                        	 '    <td>'+(lib.username?lib.username:'')+(lib.mobile?'/'+lib.mobile:'')+'</td>'+
	                        	 '    <td>'+(lib.useTime?lib.useTime:'')+'</td>'+
	                        	 ' </tr>';
	                         });
	                         $('.libs-list').find("table").find('tbody').append(html);
	                         if (jsonData.libs.rowCount > 10) {
	                             $(".lib-pagination").pagination(jsonData.libs.rowCount,
	                                 {
	                                     next_text: "下一页",
	                                     prev_text: "上一页",
	                                     current_page: jsonData.libs.pageNo - 1,
	                                     link_to: "javascript:void(0)",
	                                     num_display_entries: 8,
	                                     items_per_page: jsonData.libs.pageSize,
	                                     num_edge_entries: 1,
	                                     callback: function (page, jq) {
	                                         self.queryLibs(patchId,page + 1);
	                                     }
	                                 });
	                         } else {
	                             $(".lib-pagination").html('');
	                         }
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