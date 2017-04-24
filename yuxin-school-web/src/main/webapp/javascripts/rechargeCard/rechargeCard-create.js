/**
 * 	创建充值卡
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
                self.queryProxyOrgs();
            },

            event: function () {
                var self = this;
                $(doc)
                //价格
                .on('change','#rechargeCard-price',function(){
                	if(!$.isMoney($(this).val())){
                		$(this).val($.formatMoney($(this).val()));
                	}
                })
                //数量
                .on('change','#rechargeCard-num',function(){
                	if(!isNaN(parseInt($(this).val())) && /^[1-9]\d*$/.test($(this).val())){
                		var num = parseInt($(this).val());
                		if(num>100){
                			$(this).val(100);
                		}
                	}else{
                		$(this).val(1);
                	};
                })
                //保存
                .on('click','.manage-btn-success.save',function(){
                	self.save();
                })
                .on('click','.manage-btn-success.export',function(){
                	self.save('export');
                })
                .on('click','.manage-btn-cancel',function(){
                	win.location.href = rootPath +  '/companyRechargePatch/gotoRechargeCardManagePage'
                })
                $('.date-picker').datetimepicker({
            	    format: "yyyy-mm-dd",
  	                minView: 2,
  	                autoclose: true,
  	                startDate: new Date(),
  	                language: "zh-CN"
                })
            },
            
            queryProxyOrgs: function(){
            	var self = this;
            	_$.ajax({url: "/companyStudyCard/queryProxyOrgs", success: function(jsonData)
            		{
            			switch(jsonData.result){
            			case 'success': 
            				$('#rechargeCard-proxyOrgs').html('').append('<option value="">请选择</option>');
            				$.each(jsonData.proxyOrgs,function (i, org) {
            					$('#rechargeCard-proxyOrgs').append('<option value="'+org.id+'">'+org.name+'</option>')
            				})
            				if($("#rechargeCard-proxyOrgs").parents("div.relative-coupon").find("div").length<=0){
            					$("#rechargeCard-proxyOrgs").select2();
            				}else{
            					$("#rechargeCard-proxyOrgs").parents("div.relative-coupon").find("div").remove();
            					$("#rechargeCard-proxyOrgs").select2();
            				}
            				break;
            			case 'error':
        				default:
        					$.msg('机构代理查询失败，请刷新页面重试！')
        					break;
            			}
            		}
            	})
            },
            
            save: function(type){
            	var self = this,
            		data = {};
            	
            	data.totalNum = $('#rechargeCard-num').val();
            	data.cashAmount = $.MoneyToNum($('#rechargeCard-price').val());
            	data.startDate = $('#rechargeCard-from').val();
            	data.endDate = $('#rechargeCard-to').val();
            	data.promoCodePrefix = $('#rechargeCard-prefix').val();
            	data.orgId = $('#rechargeCard-proxyOrgs').val();
            	
            	//检查数据
            	var isok = self.check(data);
            	if(!isok) return false;
            	
            	data.issueWay = 1;
            	
            	
            	_$.ajax({url: '/companyRechargePatch/createRechargeCard', data: data, async:false, success: function(jsonData)
            		{
            			switch(jsonData.result){
            			case 'success':
            				$.msg('创建成功！');
            				if(type == 'export'){
        						  var iframe = document.createElement('iframe');
        						  iframe.setAttribute('src',rootPath + '/companyRechargeLib/exportRechargeLibs?patchId='+jsonData.patchId);
        						  $('body').append(iframe);
            				}
            				win.location.href = rootPath + '/companyRechargePatch/gotoRechargeCardManagePage';
            				break;
            			case 'error':
            			default:
            				$(jsonData.msg?jsonData.msg:'');
            				break;
            			}
            		}
            	})
            	
            },
            
            check: function(data){
            	//金额
            	if(!data.cashAmount){
            		$.msg('请输入金额,并且金额不能为0！');
            		return false;
            	}
            	//数量	
            	if(!data.totalNum){
            		$.msg('请输入数量！');
            		return false;
            	}
            	//日期
            	if(!data.startDate || !data.endDate){
    				$.msg("请填写使用期限！");
					return false;
    			}
    			if(data.endDate<data.startDate){
    				$.msg("使用期限范围不正确！");
					return false;
    			}
    			//前缀
    			if(!data.promoCodePrefix){
        			$.msg("请填写充值卡前缀！");
					return false;
        		}
        		if(!new RegExp("^[A-Za-z]+$").test(data.promoCodePrefix)){
        			$.msg("充值卡前缀只能是英文字母A-Z，限4个字母，不区分大小写！");
					return false;
        		}
        		return true;
            }
      
        });

        return Module;

    })( new Plugin() );
   
    $( function () {
        return new Module();
    });

})( window, document, jQuery );