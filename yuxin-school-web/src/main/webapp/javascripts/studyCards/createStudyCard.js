/**
 * 	创建学习卡
 *  @author Ds
 */
;( function ( win, doc, $, undefined ) {
    
    var Plugin = ( function () {

        function Plugin( settings ) { var defaults = { dataId: 'dataId' }; this.options = $.extend( defaults, settings || {} ); this.event();}

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
                self.queryProxyOrgs();
                self.placeholderCompatible();
            },

            event: function () {
                var self = this;
                //菜单选中
                $selectSubMenu('studycard_head');
                //页面初始化
                $("body").addClass("spebody-confirm");
                
                $(doc).on('click','.studyCard-choose',function(){
                	var type = $("input[type=radio]:checked").attr("data-type");
                	self.confirm(type);
                	_$.setData("idsArrays",self.getAllIdsArrays());
                	switch(type){
                		case "class":
                			self.queryClass();
                			break;
                		case "classPackage":
                			self.queryClassPackage();
                			break;
                	}
                }).on('click','.course-box .detailCon li', function() {
                	var count = self.getIdsArraysCount();
                	if(count>50){
                		$.msg("已达到选择上线！");
                		return false;
                	}
                    if (!$(this).hasClass('active')) {
                        $(this).find('.choicep').show();
                        $(this).addClass('active');
                        $(this).addClass('tanColor');
                    } else {
                        $(this).find('.choicep').hide();
                        $(this).removeClass('active');
                        $(this).removeClass('tanColor');
                    }
                    self.addCommdityIds(_$.getData('idsArrays'),$(this).attr('data-id'));
                }).on('change','.type-class-itemOneId', function(){
                	self.chooseClassItem();
                }).on('click','.type-class-search', function(){
                	self.queryClass();
                }).on('change','.type-classPackage-itemFirstId', function(){
                	self.chooseClassPackageItem(1);
                }).on('change','.type-classPackage-itemSecondId', function(){
                	self.chooseClassPackageItem();
                }).on('click','.type-classPackage-search', function(){
                	self.queryClassPackage();
                }).on('click','.addProxyOrg', function(){
                	self.confirm("proxyOrg");
                }).on('click','.proxySelect', function(){
                	var	type = $(this).attr('data-type');
                	switch(type){
                		case "allsel":
                			$('input[name=nosel]').attr("checked", false);
                			if(!$(this).is(':checked')){
                				$('.proxyOrgs').find('input[type=checkbox]').each(function(){
                					$(this).attr("checked", false);
	                			})
                			}else{
	                			$('.proxyOrgs').find('input[type=checkbox]').each(function(){
	                				if(!$(this).is(':checked')) 
	                					$(this).click();
	                			})
                			}
                			break;
                		case "nosel":
                			$('input[name=allsel]').attr("checked", false);
                			if(!$(this).is(':checked')) return true;
                			$('.proxyOrgs').find('input[type=checkbox]').each(function(){
                				$(this).attr("checked", false);
                			})
                			break;
                	}
                }).on('click','.studyCard-alltime', function(){
                	$("#start").val('');
                	$("#end").val('');
                }).on('click','input[name=proxy-org]', function(){
                	$('.nosel').attr("checked", false);
                }).on('click','.studyCard-save', function(){
                	self.save();
                }).on('change','#studyCard_totalNum',function(){
                	if(!isNaN(parseInt($(this).val())) && /^[1-9]\d*$/.test($(this).val())){
                		var num = parseInt($(this).val());
                		if(num>1000){
                			$(this).val(1000);
                		}
                	}else{
                		$(this).val(1);
                	};
                }).on('change','#studyCard_price', function(){
                	if(!$.isMoney($(this).val())){
                		$(this).val($.formatMoney($(this).val()));
                	}
                }).on('change','input[type=radio]', function(){
                	var type = $("input[type=radio]:checked").attr("data-type");
                	switch(type){
	                	case "class":
	                		$(".choose-type-commodity").html("课程");
	            			break;
	            		case "classPackage":
	            			$(".choose-type-commodity").html("课程包");
	            			break;
                	}
                	_$.setData("AllidsArrays",new Array());
                	$(".classNum").html(self.getAllIdsArraysCount());
                });
                
                $('.date-picker').datetimepicker({
            	    format: "yyyy-mm-dd",
  	                minView: 2,
  	                autoclose: true,
  	                startDate: new Date(),
  	                language: "zh-CN"
                }).on('changeDate', function(ev) {
                    $(this).datetimepicker('hide');
                    $(".studyCard-alltime").attr("checked", false);
                });
            },
            
            confirm: function ( type ) {
            	var self = this;
            	switch( type ){
            		case "class":
            			$.confirm({
            	            title: '选择课程',
            	            text: ' <div class="tanSub ">' +
            	                '     <div class="proxySearch subSearch">' +
            	                '         <select name="" id="itemOneId" class="type-class-itemOneId">' +
            	                '         </select>' +
            	                '         <select name="" id="itemTwoId">' +
            	                '         </select>' +
            	                '         <input type="text" name="" id="class-name" class="proxyAgen proxyAgent" placeholder="课程名称">' +
            	                '         <input class="searchbut searchbut1 type-class-search" type="button" value="搜索">' +
            	                '     </div>' +
            	                '     <div class="course-box">' +
            	                '         <ul class="clear detailCon classs">' +
            	                '         </ul>' +
            	                '         <div class="pages pages-find pages-agent">' +
            	                '             <ul class="pagination">' +
            	                '             </ul>' +
            	                '         </div>' +
            	                '     </div>' +
            	                ' </div>',
            	            callback: function(b) {
            	            	if(b){
            	            		var newIds = self.getIdsArrays();
            	            		_$.setData("AllidsArrays",newIds);
            	            		_$.setData("idsArrays",new Array());
            	            	}else{
            	            		_$.setData("idsArrays",new Array())
            	            	}
            	            	$(".classNum").html(self.getAllIdsArraysCount());
            	            },
            	            save: '确定'
            	        });
            			self.queryClassItem(1);
                        self.chooseClassItem();
            			break;
            		case "classPackage":
            			$.confirm({
            	            title: '选择课程包',
            	            text: ' <div class="tanSub">' +
            	                '     <div class="proxySearch subSearch">' +
            	                '         <select name="" id="itemFirstId" class="type-classPackage-itemFirstId">' +
            	                '         </select>' +
            	                '         <select name="" id="itemSecondId" class="type-classPackage-itemSecondId">' +
            	                '         </select>' +
            	                '         <select name="" id="itemThirdId">' +
            	                '         </select>' +
            	                '         <input type="text" name="" id="classPackage-name" class="proxyAgen proxyAgent" placeholder="这里输入课程包名称">' +
            	                '         <input class="searchbut searchbut1 type-classPackage-search" type="button" value="搜索">' +
            	                '     </div>' +
            	                '     <div class="course-box">' +
            	                '         <ul class="clear detailCon classPackages">' +
            	                '         </ul>' +
            	                '         <div class="pages pages-find pages-agent">' +
            	                '             <ul class="pagination">' +
            	                '             </ul>' +
            	                '         </div>' +
            	                '     </div>' +
            	                ' </div>',
        	                callback: function(b) {
            	            	if(b){
            	            		var newIds = self.getIdsArrays();
            	            		_$.setData("AllidsArrays",newIds);
            	            		_$.setData("idsArrays",new Array());
            	            	}else{
            	            		_$.setData("idsArrays",new Array())
            	            	}
            	            	$(".classNum").html(self.getAllIdsArraysCount());
            	            },
            	            save: '确定'
            	        });
            			self.chooseClassPackageItem();
            			break;
            		case "proxyOrg":
            			$.confirm({
            	            title: '添加代理机构',
            	            text: '  <div class="tanproxy">' +
            	                '      <ul class="addproxy clear">' +
            	                '          <li class=" fl">' +
            	                '              <h4 class="fl"><i>*</i>代理机构名称 :</h4>' +
            	                '              <div class="fl">' +
            	                '                  <input type="text" placeholder="代理机构名称" id="orgName" maxlength="15">' +
            	                '              </div>' +
            	                '          </li>' +
            	                '          <li class=" fl">' +
            	                '              <h4 class="fl"><i>*</i>联系人 :</h4>' +
            	                '              <div class="fl">' +
            	                '                  <input type="text" placeholder="联系人" id="orgUsername" maxlength="100">' +
            	                '              </div>' +
            	                '          </li>' +
            	                '          <li class="fl">' +
            	                '              <h4 class="fl"><i>*</i>手机号码 :</h4>' +
            	                '              <div class="fl">' +
            	                '                  <input type="text" placeholder="手机号码" id="orgMobile" maxlength="20">' +
            	                '              </div>' +
            	                '          </li>' +
            	                '          <li class="fl">' +
            	                '              <h4 class="fl"><i>*</i>代理商前缀 :</h4>' +
            	                '              <div class="fl">' +
            	                '                  <input type="text" placeholder="代理商前缀" id="orgPefix" maxlength="4">' +
            	                '              </div>' +
            	                '          </li>' +
            	                '          <li class="fl lastli">' +
            	                '              <h4 class="fl">备注 :</h4>' +
            	                '              <div class="fl">' +
            	                '                  <textarea name="" class="tanAddress detailAddress" id="orgRemark" maxlength="255"></textarea>' +
            	                '              </div>' +
            	                '          </li>' +
            	                '      </ul>' +
            	                '  </div>',
            	            callback: function(b) {
            	            	if(b){
            	            	var data = {};
            	            	data.name = $("#orgName").val();
            	            	data.contracter = $("#orgUsername").val();
            	            	data.contractNumber = $("#orgMobile").val();
            	            	data.prefix = $("#orgPefix").val();
            	            	data.remark = $("#orgRemark").val();
            	            	data.address = '';
            	            	
            	            	if(!data.name){
            	            		$.msg("请输入代理机构名称！");
              	                    return false;
            	            	}
            	            	if(data.name.length>15){
            	            		$.msg("代理机构名称不能超过15个字符！");
              	                    return false;
            	            	}
            	            	
            	            	if(!data.contracter){
            	            		$.msg("请输入联系人！");
              	                    return false;
            	            	}
            	            	
            	            	var myMobile = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            	                if(!data.contractNumber){
            	                    $.msg("请输入手机号！");
            	                    return false;
            	                }
            	                if(isNaN(data.contractNumber) || !myMobile.test(data.contractNumber)){
            	                	$.msg('请输入有效的手机号码！'); 
            	                    return false;
            	                }
            	                
            	                var prefixreg = new RegExp("^[A-Za-z]+$");
            	            	if(!data.prefix){
            	            		$.msg("请输入代理商前缀！");
            	            		return false;
            	            	}
            	            	if(!prefixreg.test(data.prefix)){
            	            		$.msg('请输入正确的代理商前缀！'); 
            	                    return false;
            	            	}
            	            	
            	            	if(data.remark && data.remark.length>255){
            	            		$.msg('备注不能超过255个字符！'); 
            	                    return false;
            	            	}
            	            	
            	            	if($('.Confirm_Ok').hasClass('lock')) return;
            					$('.Confirm_Ok').addClass('lock');
            	            	
            	            	_$.ajax({url: "/companyStudyCard/addOrUpdateProxy", data: data, success:function(jsonData)
            	            		{
            	            			switch(jsonData.result){
            	            				case "success":
            	            					$.msg("保存成功");
            	            					$('.Confirm_Cancle').click();
            	            					self.queryProxyOrgs();
            	            					break;
            	            				case "error":
        	            					default:
        	            						$.msg("失败！" + (jsonData.msg?jsonData.msg:''));
        	            						$('.Confirm_Ok').removeClass('lock');
        	            						break;
            	            			}
            	            		}
            	            	})
            	            	}
            	            },
            	            save: '保存',
            	            fadeOutBefor: 'no'
            	        });
            			break;
            	}
            },
            
            queryClass: function( page ){
            	var self = this, data = {};
            	
            	data.page = page || 1;
            	data.pageSize = 6;
            	
            	data.itemOneId = $("#itemOneId").val();
            	data.itemSecondId = $("#itemSecondId").val();
            	data.name = $("#class-name").val();
            	
            	_$.ajax({url: "/companyStudyCard/queryClassTypes", data: data, success: function(jsonData)
            		{
            			switch(jsonData.result){
            				case "success":
            					$(".classs").html('');
		        				var html = "";
		        				if (jsonData.classTypes.data.length == 0) {
		        					html = '<div style="font-size: 14px;'+
			        					   'color: #333;'+
			        					   'padding-top: 50px;">没有发现您要找的内容</div>';
		                        }
		        				var idsArrays = self.getIdsArrays();
		        				$.each(jsonData.classTypes.data,function (i, card) {
		        					 var isChoosed = false; 
		        					 if(!($.inArray(card.id.toString(), idsArrays)<0)) isChoosed = true;
		        					 var classType = "";
		        					 if(card.liveFlag == 1) classType += "<em>直播</em>";
		        					 if(card.videoFlag == 1) classType += "<em>录播</em>";
		        					 if(card.faceFlag == 1) classType += "<em>面授</em>";
		        					 if(card.liveFlag == 0 && card.videoFlag == 0 && card.faceFlag == 0) classType += "<em>其他</em>";
		                        	 html += '             <li data-id="'+card.id+'" '+(isChoosed?'class="active tanColor"':'')+'>' +
			            	                '                 <div class="aparcel clear">' +
			            	                '                     <div class="showpic fl" '+(card.cover?'style="background-image:url('+jsonData.commodityPicUrl+card.cover+')"':"")+'">' +
			            	                '                         <p class="choicep" '+(isChoosed?'style="display: block;"':'')+'>' +
			            	                '                             已选择 <i class="iconfont">&#xe616;</i>' +
			            	                '                         </p>' +
			            	                '                     </div>' +
			            	                '                     <div class="wordint fr">' +
			            	                '                         <p class="wordming">' +
			            	                '                             '+card.name+'' +
			            	                '                         </p>' +
			            	                '                         <div class="pricebox">' + classType +
			            	                '                             <i class="fr">'+(card.realPrice == 0?'免费':'￥'+card.realPrice)+'</i>' +
			            	                '                         </div>' +
			            	                '                     </div>' +
			            	                '                 </div>' +
			            	                '             </li>' ;
		                        });
		                        $(".classs").append(html);
		                        if (jsonData.classTypes.rowCount > 6) {
		                             $(".pagination").pagination(jsonData.classTypes.rowCount,
		                                 {
		                                     next_text: "下一页",
		                                     prev_text: "上一页",
		                                     current_page: jsonData.classTypes.pageNo - 1,
		                                     link_to: "javascript:void(0)",
		                                     num_display_entries: 8,
		                                     items_per_page: jsonData.classTypes.pageSize,
		                                     num_edge_entries: 1,
		                                     callback: function (page, jq) {
		                                         self.queryClass(page + 1);
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
        	
        	queryClassItem: function( type, itemOneId ){
        		var self = this, data = {};
        		
        		data.itemType = type;
        		if(itemOneId) data.parentId = itemOneId;
        		
        		_$.ajax({url: '/sysConfigItem/getJsons', data: data, success: function(jsonData)
        			{
        				switch(type){
        					case 1:
        						$("#itemOneId").html('').append('<option selected="selected" value="0">学科</option>');
        						$.each(jsonData,function(i,data){
        							if(i=0){
        								$("#itemOneId").append('<option value="'+data.id+'">'+data.itemName+'</option>');
        							}else{
        								$("#itemOneId").append('<option value="'+data.id+'">'+data.itemName+'</option>');
        							}
        						});
        						$("#itemTwoId").html('').append('<option selected="selected" value="0">学科小类</option>');
        						break;
        					case 2:
    							$("#itemTwoId").html('').append('<option selected="selected" value="0">学科小类</option>');
        						$.each(jsonData,function(i,data){
        							$("#itemTwoId").append('<option value="'+data.id+'">'+data.itemName+'</option>');
        						});
        						break;
        				}
        			}
        		})
        		
        	},
        	
        	chooseClassItem: function(){
        		var self = this;
        		var itemOneId = $("#itemOneId").val();
        		self.queryClassItem(2,itemOneId);
        	},
        	
        	queryClassPackage: function( page ){
        		var self = this, data = {};
        		
        		data.page = page || 1;
        		data.pageSize = 6;
        		
        		data.categoryCode = parseInt($("#itemThirdId").val()) || parseInt($("#itemSecondId").val()) || parseInt($("#itemFirstId").val()) || '';
        		if(data.categoryCode){
        			data.categoryCode = self.prefixInteger(data.categoryCode);
        		}
        		data.name = $("#classPackage-name").val();
        		
        		_$.ajax({url: '/companyStudyCard/queryClassPackage', data: data, success: function(jsonData)
        			{
        				switch(jsonData.result){
        					case "success":
        						$(".classPackages").html('');
		        				var html = "";
		        				if (jsonData.classPackages.data.length == 0) {
		        					html = '<div style="font-size: 14px;'+
		        					   'color: #333;'+
		        					   'padding-top: 50px;">没有发现您要找的内容</div>';
		                        }
		        				var idsArrays = self.getIdsArrays();
		        				$.each(jsonData.classPackages.data,function (i, card) {
		        					 var isChoosed = false; 
		        					 if(!($.inArray(card.id.toString(), idsArrays)<0)) isChoosed = true;
		                        	 html += '             <li data-id="'+card.id+'" '+(isChoosed?'class="active tanColor"':'')+'>' +
			            	                '                 <div class="aparcel clear">' +
			            	                '                     <div class="showpic fl" '+(card.cover?'style="background-image:url('+jsonData.commodityPicUrl+card.cover+')"':"")+' >' +
			            	                '                         <p class="choicep" '+(isChoosed?'style="display: block;"':'')+'>' +
			            	                '                             已选择 <i class="iconfont">&#xe616;</i>' +
			            	                '                         </p>' +
			            	                '                     </div>' +
			            	                '                     <div class="wordint fr">' +
			            	                '                         <p class="wordming">' +
			            	                '                             '+ card.name +'' +
			            	                '                         </p>' +
			            	                '                         <div class="pricebox priceStage">' +
			            	                '              <span class="stage">阶段</span><b class="numb">'+ card.stageCount +'</b><span class="stav">|</span><span class="stage">课程</span><b class="numb">'+ card.classTypeCount +'</b>' +        
			            	                '                             <i class="fr rmb">'+(card.realPrice == 0?'免费':'￥'+card.realPrice)+'</i>' +
			            	                '                         </div>' +
			            	                '                     </div>' +
			            	                '                 </div>' +
			            	                '             </li>' ;
		                        });
		                        $(".classPackages").append(html);
		                        if (jsonData.classPackages.rowCount > 6) {
		                             $(".pagination").pagination(jsonData.classPackages.rowCount,
		                                 {
		                                     next_text: "下一页",
		                                     prev_text: "上一页",
		                                     current_page: jsonData.classPackages.pageNo - 1,
		                                     link_to: "javascript:void(0)",
		                                     num_display_entries: 8,
		                                     items_per_page: jsonData.classPackages.pageSize,
		                                     num_edge_entries: 1,
		                                     callback: function (page, jq) {
		                                         self.queryClassPackage(page + 1);
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
        	
        	queryClassPackageItem: function( leavl, code){
        		var self = this, data = {};
        		
        		data.leavl = leavl;
        		data.code = code;
        		data.status = 1;
        		
        		_$.ajax({url: '/classPackageCategory/queryCategoryList', data: data, success:function(jsonData)
        			{
        				switch(leavl){
		    				case "first":
        						$("#itemFirstId").html('').append('<option selected="selected" value="0">一级分类</option>');
        						$.each(jsonData,function(i,data){
        							if(i=0){
        								$("#itemFirstId").append('<option value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}else{
        								$("#itemFirstId").append('<option value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}
        						});
        						$("#itemSecondId").html('').append('<option selected="selected" value="0">二级分类</option>');
        						$("#itemThirdId").html('').append('<option selected="selected" value="0">三级分类</option>');
		    					break;
		    				case "second":
        						$("#itemSecondId").html('').append('<option selected="selected" value="0">二级分类</option>');
        						$.each(jsonData,function(i,data){
        							if(i=0){
        								$("#itemSecondId").append('<option value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}else{
        								$("#itemSecondId").append('<option value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        							}
        						});
        						$("#itemThirdId").html('').append('<option selected="selected" value="0">三级分类</option>');
		    					break;
		    				case "third":
    							$("#itemThirdId").html('').append('<option selected="selected" value="0">三级分类</option>');
        						$.each(jsonData,function(i,data){
        							$("#itemThirdId").append('<option value="'+data.code+'" ids="'+data.id+'">'+data.name+'</option>');
        						});
		    					break;
        				}
        			}
        		})
        	},
        	
        	chooseClassPackageItem: function(type){
        		var self = this;
        		var itemFirstId = $("#itemFirstId").val();
        		if(type != 1) var itemSecondId = $("#itemSecondId").val();
        		var type = (itemSecondId&&itemSecondId!=0)?"third":((itemFirstId&&itemFirstId!=0)?"second":"first");
        		self.queryClassPackageItem( type,itemSecondId||itemFirstId);
        	},
            
        	queryProxyOrgs: function(){
        		var self = this;
        		_$.ajax({url: "/companyStudyCard/queryProxyOrgs", success: function(jsonData)
        			{	
	        			$('.hasProxy').show();
						$(".proxyOrgs").show();
        				switch(jsonData.result){
	        				case "success":
	        					$(".proxyOrgs").html('');
	        					var html = "";
	        					if(jsonData.proxyOrgs.length == 0){
	        						$('.hasProxy').hide();
	        						$(".proxyOrgs").hide();
	        					}
		        				$.each(jsonData.proxyOrgs,function (i, proxy) {
		                        	 html += '<dd>'+
                                         	 '<input type="checkbox" name="proxy-org" value="'+proxy.id+'"/><span>'+proxy.name+'</span>'+
                                         	 '</dd>' ;
		                        });
		                        $(".proxyOrgs").append(html);
	        					break;
	        				case "error":
        					default:
        						break;
        				}
        			}
        		})
        	},
        	
        	addCommdityIds: function(idsArray,id){
        		var self = this;
        		var ids;
        		if(idsArray){
        			ids = idsArray.split(',');
        		}else{
        			ids = new Array();
        		}
        		var isOk=false;
        		
        		for(var i=0;i<ids.length;i++){
        			if(id==ids[i]){
        				ids.splice(i--,1);
        				isOk=true;
        				break;
        			}
        		}
        		if(!isOk){
        			ids.push(id);
        		}
        		_$.setData("idsArrays",ids);
        	},
        	
        	getIdsArrays: function(){
        		var self = this, idsArrays;
        		idsArrays = _$.getData('idsArrays');
        		if(idsArrays){
        			idsArrays = idsArrays.split(',');
        		}else{
        			idsArrays = new Array();
        		}
        		return idsArrays;
        	},
        	
        	getIdsArraysCount: function(){
        		var self = this, idsArrays;
        		idsArrays = _$.getData('idsArrays');
        		if(idsArrays){
        			return idsArrays.split(',').length;
        		}else{
        			return 0;
        		}
        	},
        	
        	getAllIdsArrays: function(){
        		var self = this, AllidsArrays;
        		AllidsArrays = _$.getData('AllidsArrays');
        		if(AllidsArrays){
        			AllidsArrays = AllidsArrays.split(',');
        		}else{
        			AllidsArrays = new Array();
        		}
        		return AllidsArrays;
        	},
        	
        	getAllIdsArraysCount: function(){
        		var self = this, AllidsArrays;
        		AllidsArrays = _$.getData('AllidsArrays');
        		if(AllidsArrays){
        			return AllidsArrays.split(',').length;
        		}else{
        			return 0;
        		}
        	},
        	
        	save: function(){
        		var self = this, data = {};
        		
        		data.name = $("#studyCard_name").val(); 
        		if(!data.name){
        			$.msg("请填写学习卡名称！");
					return false;
        		}
        		if(data.name.length>15){
        			$.msg("学习卡名称不能超过15个字符！");
					return false;
        		}
        		
        		data.courseType = $("input[type=radio]:checked").attr("data-type") == 'class'?0:1; 
        		if(parseInt($(".classNum").html()) <= 0){
        			$.msg('请选择'+(data.courseType == 0?"课程":"课程包")+'');
					return false;
        		}
        		data.courseList = self.getAllIdsArrays().toString();
        		
        		data.totalNum = $("#studyCard_totalNum").val(); 
        		if(!data.totalNum){
        			$.msg("请填写学习卡数量！");
					return false;
        		}
        		
        		
        		data.startDate = $("#start").val(); 
        		data.endDate = $("#end").val(); 
        		
        		if($(".studyCard-alltime").is(':checked')){
        			data.startDate = '';
        			data.endDate = '';
        		}else{
        			if(!data.startDate || !data.endDate){
        				$.msg("请填写使用期限！");
    					return false;
        			}
        			if(data.endDate<data.startDate){
        				$.msg("使用期限范围不正确！");
    					return false;
        			}
        		}
        		
        		var count = 0;
        		$("input[name=proxy-org]:checked").each(function(){
        			if(count == 0){
        				data.proxyOrgId = $(this).val();
        			} else{
        				data.proxyOrgId += "," + $(this).val();
        			}
        			count++;
        		})
        		
        		data.prefix = $("#studyCard_prefix").val();
        		if(!data.prefix){
        			$.msg("请填写学习码前缀！");
					return false;
        		}
        		if(!new RegExp("^[A-Za-z]+$").test(data.prefix)){
        			$.msg("学习码前缀只能是英文字母A-Z，限4个字母，不区分大小写！");
					return false;
        		}
        		
        		data.price = $("#studyCard_price").val();
        		if(data.price){
        			data.price = $.MoneyToNum($("#studyCard_price").val()); 
        		}
        		
        		data.description = $("#studyCard_description").val(); 
        		
        		if(data.description && data.description.length>400){
        			$.msg("学习卡说明不能超过400个字符！");
					return false;
        		}
        		
        		_$.ajax({url: "/companyStudyCard/createStudyCards", data: data, success:function(jsonData)
        			{
        				switch(jsonData.result){
	        				case "success":
	        					$.msg("创建成功！");
	        					win.location.href = rootPath + "/companyStudyCard/gotoStudyCardsManage";
	        					break;
	        				case "error":
	    					default:
	    						$.msg((jsonData.msg?jsonData.msg:''));
        						break;
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