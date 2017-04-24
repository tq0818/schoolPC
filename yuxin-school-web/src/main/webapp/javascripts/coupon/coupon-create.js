/**
 *  创建优惠码(新增课程包类型)	
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
                self.event();
                self.queryItemOne();
            },

            event: function () {
                var self = this;
                $(doc).on('click', 'input[name=product-type],input[name=product-type1]', function(){
                	var tab_type = $('.tab-itm.active').attr('ids');
                	var product_type = $(this).attr('data-type');
                	switch(tab_type){
                	case "0"://线上
                		self.onlineChange(product_type);
                		break;
                	case "1"://线下
                		self.offlineChange(product_type);
                		break;
                	}
                })
                //学科
                .on('change', '.itemOne', function(){
             		$(this).siblings().removeAttr("selected").attr("selected","selected");
             		self.queryItemSecond($(this));
             		self.queryClassType($(this),$(this).val());
                })
             	//学科小类
                .on('change', '.itemTwo', function(){
            		$(this).siblings().removeAttr("selected").attr("selected","selected");
            		self.queryClassType($(this));
            	})
            	//班型
                .on('change', '.classes_course', function(){
                	$(this).siblings().removeAttr("selected").attr("selected","selected");
                })
                .on('click', '.class-choose', function(){
                	self.queryItemOne();
                })
                //课程包 一级
                .on('change','.classPackage-itemOne', function(){
                	self.queryClassPackageItem('second',$(this));
                })
                //课程包 二级
                .on('change','.classPackage-itemTwo', function(){
                	self.queryClassPackageItem('third',$(this));
                })
                //课程包 三级
                .on('change','.classPackage-itemThree', function(){
                	self.queryClassPackages()
                })
                .on('click','.class-package-choose', function(){
                	self.queryClassPackageItem('first');
                })
                var type=$("#add_type").val();
            	if(type && type==1){
            		$(".coupon_types").find("span.tab-itm").eq(1).trigger('click');
            		var product_type = $('#id_commodity_type').val();
            		if(product_type && type==1){
            			$('.product-type').find('input[data-type=1]').click();
            		}
            	}
            	var product_type = $('#id_commodity_type').val();
            	if(product_type && product_type==1){
            		$('.product-type').find('input[data-type=1]').click();
            	}
            	var code = $('#id_rangeItemPackageCategory').val();
            	if(code){
            		var	id = $("#id_rangeItemCourse").val();
            		if(id){
            			$("#onLine_userRanges").find('input[type=radio]').eq(1).click();
            			var codes = self.resolveCode(code).split('-');
            			for(var int=0; int<codes.length; int++){
            				if(int == 1){
            					$("#onLine_userRanges").find("input[type=radio]").eq(1).next().find('.classPackage-itemOne').val(codes[int])
            				}
            			}
            		}else{
            			
            		}
            	}
               self.classPackageCourseInit();
            },
            
            onlineChange: function(product_type){
            	var self = this;
            	switch(product_type){
            	case "0"://课程
            		//1.优惠范围
            		$('#onLine_userRanges').html('').append(self.getClassUseRangesHtml());
            		$('#onLine_userRanges').find('input[type=radio]:first').click()
            		$("#onLine_insCashNum_msg").html('当课程原价或优惠后的价格低于抵现金额时,抵现金额不能用.');
//            		self.queryItemOne();
            		break;
            	case "1"://课程包
            		//1.优惠范围
            		$('#onLine_userRanges').html('').append(self.getClassPackageUseRangesHtml());
            		$('#onLine_userRanges').find('input[type=radio]:first').click()
            		$("#onLine_insCashNum_msg").html('当课程包原价或优惠后的价格低于抵现金额时,抵现金额不能用.');
//            		self.chooseClassPackageItem();
            		break;
            	}
            },
            
            offlineChange: function(product_type){
            	var self = this;
            	switch(product_type){
            	case "0"://课程
            		//1.优惠范围
            		$('#offLine_userRanges').html('').append(self.getClassUseRangesHtml(1));
            		$('#offLine_userRanges').find('input[type=radio]:first').click()
            		$("#offLine_insCashNum_msg").html('当课程原价或优惠后的价格低于抵现金额时,抵现金额不能用.');
//            		self.queryItemOne();
            		break;
            	case "1"://课程包
            		//1.优惠范围
            		$('#offLine_userRanges').html('').append(self.getClassPackageUseRangesHtml(1));
            		$('#offLine_userRanges').find('input[type=radio]:first').click()
            		$("#offLine_insCashNum_msg").html('当课程包原价或优惠后的价格低于抵现金额时,抵现金额不能用.');
//            		self.chooseClassPackageItem();
            		break;
            	}
            },
            
            getClassUseRangesHtml: function(type){
            	return 	'	<p><input type="radio" checked="checked" value="0" name="coupon-name'+type+'"/>全部课程</p> '+
		        		'	<p class="item_list_choose"> '+
		        		'		<input class="class-choose" type="radio" name="coupon-name'+type+'" value="1"/>指定范围 '+
		        		'		<span class="coupon-set none"> <select class="itemOne"> </select> </span> '+
		        		'		<span class="coupon-set none"> <select class="itemTwo"> </select> </span> '+
		        		'	</p> '+
		        		'	<p class="item_course_choose"><input class="class-choose" type="radio" name="coupon-name'+type+'" value="2"/>指定课程 '+
		        		'		<span class="coupon-set none"> <select class="itemOne"> </select> </span> '+
		        		'		<span class="coupon-set none"> <select class="itemTwo"> </select> </span> '+
		        		'		<span class="coupon-set none"> <select class="classes_course"> </select> </span> '+
		        		'	</p>';
            },
            
            getClassPackageUseRangesHtml: function(type){
            	return 	'	<p><input  type="radio" checked="checked" value="0" name="coupon-name'+type+'"/>全部课程包</p> '+
		        		'	<p class="item_list_choose"> '+
		        		'		<input class="class-package-choose" type="radio" name="coupon-name'+type+'" value="1"/>指定范围 '+
		        		'		<span class="coupon-set none"> <select class="classPackage-itemOne"> </select> </span> '+
		        		'		<span class="coupon-set none"> <select class="classPackage-itemTwo"> </select> </span> '+
		        		'		<span class="coupon-set none"> <select class="classPackage-itemThree"> </select> </span> '+
		        		'	</p> '+
		        		'	<p class="item_course_choose"><input class="class-package-choose"  type="radio" name="coupon-name'+type+'" value="2"/>指定课程包 '+
		        		'		<span class="coupon-set none"> <select class="classPackage-itemOne"> </select> </span> '+
		        		'		<span class="coupon-set none"> <select class="classPackage-itemTwo"> </select> </span> '+
		        		'		<span class="coupon-set none"> <select class="classPackage-itemThree"> </select> </span> '+
		        		'		<span class="coupon-set none"> <select class="classPackages_course"> </select> </span> '+
		        		'	</p>';
            },
            
            queryItemOne: function(){
            	var self = this;
            	$(".itemOne").html('');
            	_$.ajax({url: "/sysConfigItem/getJsons", data: {"itemType":1}, success: function(jsonData)
            		{
	          			var item_oneId=$("#id_rangeItemOne").val();
	          			$.each(jsonData,function(i,data){
	          				if(item_oneId){
	          					if(item_oneId==data.id){
	          						$(".itemOne").append('<option selected="selected" value="'+data.id+'">'+data.itemName+'</option>');
	          					}else{
	          						$(".itemOne").append('<option value="'+data.id+'">'+data.itemName+'</option>');
	          					}
	          				}else{
	          					if(i==0){
	          						$(".itemOne").append('<option selected="selected" value="'+data.id+'">'+data.itemName+'</option>');
	          					}else{
	          						$(".itemOne").append('<option value="'+data.id+'">'+data.itemName+'</option>');
	          					}
	          				}
	          			});
	          			self.queryItemSecond();
	          			self.queryClassType();
            		}
            	})
            },
            
            queryItemSecond:function(e){
	         	  var self = this;
	         	  var itemOneId=0;
	         	  if(e){
	         		  itemOneId=$(e).find("option:selected").val();
	         	  }else{
	         		  itemOneId=$(".itemOne").find("option:selected").val();
	         	  }
        		  $(".itemTwo").html("");
        		  _$.ajax({url: "/sysConfigItem/getJsons", data: {"itemType":2,"parentId":itemOneId}, success: function(jsonData)
        			  {
	        			  var item_seondId=$("#id_rangeItemSecond").val();
      					  $(".itemTwo").append('<option value="">全部</option>');
	      				  $.each(jsonData,function(i,data){
		      					if(item_seondId && item_seondId==data.id){
		      						$(".itemTwo").append('<option selected="selected" value="'+data.id+'">'+data.itemName+'</option>');
		      					}else{
		      						$(".itemTwo").append('<option value="'+data.id+'">'+data.itemName+'</option>');
		      					}
	      				  });
        			  }
        		  })
            },
    	    queryClassType:function(e,id){
        		$(".classes_course").html("");
        		var itemOneId=0,itemSecondId=0;
        		if(e){
        			if(!id || id==""){
        				itemOneId=$(e).parent().prev().find("option:selected").val();
        	   	   		itemSecondId=$(e).find("option:selected").val();
        			}
        		}else{
        			itemOneId=$(".itemOne").find("option:selected").val();
        	   		itemSecondId=$(".itemTwo").find("option:selected").val();
        		}
        		if(id){
        			itemOneId=id;
        		}
        		_$.ajax({url: "/classType/queryList", data: {"itemOneId":itemOneId,"itemSecondId":itemSecondId}, success: function(jsonData)
        			{
	        			var item_courseId=$("#id_rangeItemCourse").val();
	    				$(".classes_course").append('<option value="">全部</option>');
	    				$.each(jsonData,function(i,data){
	    					if(item_courseId && item_courseId==data.commodityId){
	    						$(".classes_course").append('<option selected="selected" value="'+data.commodityId+'">'+data.name+'</option>');
	    					}else{
	    						$(".classes_course").append('<option value="'+data.commodityId+'">'+data.name+'</option>');
	    					}
	        			});
	    				if($(".classes_course").parents("span.coupon-set").find("div").length<=0){
        					$(".classes_course").select2();
        				}else{
        					$(".classes_course").parents("span.coupon-set").find("div").remove();
        					$(".classes_course").select2();
        				}
        			}
        		})
    	    },
    	    
    	    queryClassPackageItem: function( leavl, e){
        		var self = this, data = {};
        		
        		data.leavl = leavl;
        		if(e) {
        			data.code = e.find("option:selected").val() || -1;
        		}
        		data.status = 1;
        		
        		_$.ajax({url: '/classPackageCategory/queryCategoryList', data: data,async:false, success:function(jsonData)
        			{
        				switch(leavl){
		    				case "first":
		    					if($('#id_rangeItemPackageCategory').val()){
		    						var first = self.resolveCode($('#id_rangeItemPackageCategory').val()).split('-')[0];
		    					}
		    					$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne").html('').append('<option value="">请选择</option>');
        						$.each(jsonData,function(i,data){
        							if(first && first==data.code){
        								$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne").append('<option selected="selected" value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}else{
        								$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne").append('<option value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}
        						});
        						$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").html('').append('<option selected="selected" value="">全部</option>');
        						$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").html('').append('<option selected="selected" value="">全部</option>');
        						self.queryClassPackageItem('second',$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne"));
		    					break;
		    				case "second":
		    					if($('#id_rangeItemPackageCategory').val()){
		    						var second = self.resolveCode($('#id_rangeItemPackageCategory').val()).split('-')[1];
		    					}
		    					$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").html('').append('<option value="">全部</option>');
        						$.each(jsonData,function(i,data){
        							if(second && second==data.code){
        								$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").append('<option selected="selected"  value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}else{
        								$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").append('<option value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}
        						});
        						$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").html('').append('<option selected="selected" value="">全部</option>');
        						self.queryClassPackageItem('third',$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo"));
		    					break;
		    				case "third":
		    					if($('#id_rangeItemPackageCategory').val()){
		    						var third = self.resolveCode($('#id_rangeItemPackageCategory').val()).split('-')[2];
		    					}
		    					$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").html('').append('<option value="">全部</option>');
        						$.each(jsonData,function(i,data){
        							if(third && third==data.code){
        								$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").append('<option selected="selected" value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}else{
        								$('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").append('<option value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}
        						});
        						self.queryClassPackages();
		    					break;
        				}
        			}
        		})
        		self.queryClassPackages();
        	},
        	
        	queryClassPackages: function(code){
        		var self = this, data={};
        		data.categoryCode = parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemThree").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemTwo").val()) || parseInt($('.comm_useRanges input[type=radio]:checked').parents('p').find(".classPackage-itemOne").val()) || '';
        		if(data.categoryCode){
        			data.categoryCode = self.prefixInteger(data.categoryCode);
        		};
        		
        		_$.ajax({url: '/companyCouponsPatch/queryClassPackages', data: data,async:false,  success: function(jsonData)
        			{
	        			var item_courseId=$("#id_rangeItemCourse").val();
	    				$(".classPackages_course").html('').append('<option value="">全部</option>');
	    				$.each(jsonData,function(i,data){
	    					if(item_courseId && item_courseId==data.commodityId){
	    						$(".classPackages_course").append('<option selected="selected" value="'+data.commodityId+'">'+data.commodityName+'</option>');
	    					}else{
	    						$(".classPackages_course").append('<option value="'+data.commodityId+'">'+data.commodityName+'</option>');
	    					}
	        			});
	    				if($(".classPackages_course").parents("span.coupon-set").find("div").length<=0){
	    					$(".classPackages_course").select2();
	    				}else{
	    					$(".classPackages_course").parents("span.coupon-set").find("div").remove();
	    					$(".classPackages_course").select2();
	    				}
        			}
        		})
        		
        	},
        	
        	prefixInteger: function(num) {
        		var length = num.toString().length;
        		if(length<=3){
        			length = 3;
        		}else if(3<length && length<=6){
        			length = 6;
        		}else if(6<length && length<=9){
        			length = 9;
        		}else{
        			return num;
        		}
        		return (Array(length).join('0') + num).slice(-length);
    		},
    		
    		resolveCode: function(code) {
    			switch(code.length){
    			case 3:
    				return code;
    			case 6:
    				return code.substring(0, 3) + "-" + code.substring(0, 6);
    			case 9:
    				return code.substring(0, 3) + "-" + code.substring(0, 6) + "-" + code.substring(0, 9);
    			}
    		},
    		
    		classPackageCourseInit: function(){
    			var self = this;
    			var code = $('#id_rangeItemPackageCategory').val();
    			var name = $("#id_codes").val();
    			var names ='';
    			if(name){
    				names = name.split("-");
    			}
    			if(code){
    				var	id = $("#use_Rang_IN").val();
    				if(id==2){
	    				$('.class-package-choose:last').click();
						self.queryClassPackageItem('first');
						var codes = self.resolveCode(code).split('-');
						for(var int=0; int<codes.length; int++){
							if(int == 0){
								$(".class-package-choose:last").next().find('.classPackage-itemOne').val(codes[int]);
							}
							if(int == 1){
								$(".class-package-choose:last").parents('div').find('.classPackage-itemTwo').val(codes[int]);
							}
							if(int == 2){
								$(".class-package-choose:last").parents('div').find('.classPackage-itemThree').val(codes[int]);
							}
						}
//						$(".class-package-choose:last").parents('div').find('.classPackages_course').val(id);
    				}else if(id==1){
    					$('.class-package-choose:first').click();
    					self.queryClassPackageItem('first');
    					var codes = self.resolveCode(code).split('-');
    					for(var int=0; int<codes.length; int++){
    						if(int == 0){
    							$(".class-package-choose:first").next().find('.classPackage-itemOne').val(codes[int]);
    						}
    						if(int == 1){
    							$(".class-package-choose:first").parents('div').find('.classPackage-itemTwo').val(codes[int]);
    						}
    						if(int == 2){
    							$(".class-package-choose:first").parents('div').find('.classPackage-itemThree').val(codes[int]);
    						}
    					}
    				}
    			}
    		}
    		
            
        });

        return Module;

    })( new Plugin() );
   
    $( function () {
       var module = new Module();
       win.module = module;
    });

})( window, document, jQuery );