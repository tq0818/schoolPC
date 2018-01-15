(function($) {
    Array.prototype.containsKey = function(_key) {
        for (var i = 0; i < this.length; i++) {
            var val = this[i];
            if (val.key === _key) {
                return true;
            }
        }
        return false;
    };
    Array.prototype.get = function(_key) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] && this[i].key === _key) {
                return this[i].value;
            }
        }
        return null;
    };
    Array.prototype.removeByKey = function(_key) {
        for (var i = 0; i < this.length; i++) {
            if (this[i].key === _key) {
                this.splice(i, 1);
                return true;
            }
        }

        return false;
    };
    Array.prototype.put = function(_key, _value) {
        if (this.containsKey(_key)) {
            this.removeByKey(_key);
        }
        this.push({
                key: _key,
                value: _value
            });
    };
    Array.prototype.keySet = function() {
        var keys = [];
        for (var i = 0; i < this.length; i++) {
            if (this[i] && this[i].key) {
                keys.push(this[i].key);
            }
        }
        return keys;
    };
    Array.prototype.valueSet = function() {
        var values = [];
        for (var i = 0; i < this.length; i++) {
            if (this[i]) {
                values.push(this[i].value);
            }
        }
        return values;
    };
    Array.prototype.containsObj = function(obj) {
        for (var i = 0; i < this.length; i++) {
            if (typeof obj === "boolean" || typeof obj === "number" || typeof obj === "string" || typeof obj === "undefined") {
                if (obj === this[i]) {
                    return true;
                }
                continue;
            } else if (typeof obj === "object") {
                if (obj.id && this[i].id) {
                    return obj.id === this[i].id;
                } else {
                    return obj === this[i];
                }
            } else if (typeof obj === "function") {
                return obj === this[i];
            }
        }
        return false;
    };

    function Model() {
        //配置
    	this.baseEntiy = {'id':true,'moduleName':true,'customName':true,'status':true,'moduleType':true,'widthSetting':true,'link':true,'picPath':true,'itemOneIdList':true,'dataSortBy':true,'dataLimitNum':true,'companyId':true,'schoolId':true,'displaySeq':true};
        this.config = {

        };
        this.sys_config_serive = []; //机构已开通服务
        this.modelPage = ['公开课','推荐课程','课程','广告','学员心声','推荐课程包','名师专区','会员','学员动态','问答社区','新闻资讯'];
        this.deleteAdv = [];
    }
    Model.prototype = {
            ajaxLoad: function(url, dataInfo, func) {
                $.ajax({
                    type: "post",
                    url: rootPath + url,
                    data: dataInfo,
                    beforeSend: function(XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: func,
                    complete: function(XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                        $.footerPosition({ cur: '#footerContents', pre: '#classList' });
                    }
                });
            },
            ajax: function() {
                var arg = Array.prototype.slice.call(arguments),
                    success = arg.length > 2 ? arg[2] : function() {},
                    error = arg.length > 3 ? arg[3] : function() {};
                $.ajax({
                    type: "post",
                    url: rootPath + arg[0],
                    data: arg[1],
                    success: success,
                    error: error
                });
            },
            init: function() {
            	$selectSubMenu('org_service');
                this.initData();
                this.getItemOne();
                this.event();
            },
            initData: function() {
                var dataInfo = $('.schWorkT .heading').data(),
                    _this = this,
                    _sysConfigServies,
                    _sysConfigServie,
                    _pageModels,
                    _pageModel,
                    _html = '';
                this.ajax('/sysConfigIndexPageTemplate/getJosnData', dataInfo, function(result) {
                    //初始化机构已开通服务
                    _sysConfigServies = result.scses;
                    for (var k = _sysConfigServies.length - 1; k >= 0; k--) {
                        _sysConfigServie = _sysConfigServies[k];
                        _this.sys_config_serive.put(_sysConfigServie.groupCode,_sysConfigServie.delFlag);
                    }

                    _pageModels = result.scpts;

                    for (var i = 0; i < _pageModels.length; i++) {
                        _pageModel = _pageModels[i];
                        if(_this.sysServiceIsOpen(_pageModel.moduleType)){
                            _html = _this.getLeftHtml(_pageModel);
                            //jquery need before append html to body,after can bind data
                            $('.schList dl').append(_html);
                            $('.schList dd').last().data(_pageModel);
                        }
                    }
                    $('.schList dd').eq(0).find('.edit').trigger('click');
                    //jquery ui sortable api
                    $('.schList dl').sortable({
                    	//指定元素内的哪一个项目应是 sortable
                    	items:'>dd',
                    	//当排序停止时触发该事件
                    	deactivate:function(event, ui){
                    		$('.schList dd').each(function(i){
                    			var scipt = $(this).data();
                    			scipt.displaySeq = $(this).index()-1;
                    			$(this).data(scipt);
                    		});
                    	}
                    });
                });
            },
            sysServiceIsOpen : function(moduleType){
            	switch (moduleType) {
				case 0:
					return this.sys_config_serive.get('SERVICE_OPENCLASS')==1;
					break;
                case 5:
                    return this.sys_config_serive.get('SERVICE_CLASS_PACKAGE')==1;
                    break;
                case 6:
                    return this.sys_config_serive.get('SERVICE_TEACHER')==1;
                    break;
                case 7:
                    return this.sys_config_serive.get('SERVICE_MEMBER')==1;
                    break;
                case 9:
                    return this.sys_config_serive.get('SERVICE_QUESTION_ANSWER')==1;
                    break;
				default:
                    return true;
					break;
				}
            },
            getLeftHtml : function(scipt){
                var html =  '<dd>'+
                                '<span class="w32">'+
                                    '<a href="javascript:;" title="'+scipt.moduleName+'">'+scipt.moduleName+'</a>'+
                                '</span>'+
                                    '<span class="w33">'+
                                    '<a href="javascript:;" title="'+scipt.customName+'">'+(scipt.customName||scipt.moduleName)+'</a>'+
                                '</span>'+
                                '<span class="w9">'+
                                    '<i class="iconfont switch '+(scipt.status?'open':'')+'" title="'+(scipt.status?'禁用':'启用')+'">'+(scipt.status?'&#xe604;':'&#xe603;')+'</i>'+
                                '</span>'+
                                    '<span class="w17">'+
                                    '<p '+(scipt.moduleType == 3 ? 'class="advert"' : '')+'>'+
                                        '<i class="iconfont edit" title="编辑">&#xe628;</i>'+
                                        (scipt.moduleType == 3 ? '<i class="iconfont delet" title="删除">&#xe6e8;</i>' : '')+
                                    '</p>'+
                                '</span>'+
                                    '<span class="w9">'+
                                    '<i class="iconfont draw">&#xe630;</i>'+
                                '</span>'+
                            '</dd>';

                return html;
            },
            getItemOne : function(){
                var _this = this,
                    _html = '',
                    _itemOne ;
            	this.ajax('/sysConfigItem/getJsons',{itemType:'1'},function(result){
            		for (var i = 0; i < result.length; i++) {
                        _itemOne = result[i];
                        if(_itemOne.itemName && _itemOne.itemName.length > 14)
                            _itemOne.itemName = _itemOne.itemName.substring(0,13)+'...';
                        _html += '<label for=""><input type="checkbox" class="swCheck" data-id="'+_itemOne.id+'">'+_itemOne.itemName+'</label>';
                    }
                    $('.itemOneList').html(_html);
            	});
            },
            initRightHtml : function($div,scipt,_uuid){
                //今日公开课
                if(scipt.moduleType == 0){
                    $div.find('.swText').val(scipt.customName || scipt.moduleName);
                }
                //推荐课程
                if(scipt.moduleType == 1){
                    $div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                        if($(this).data('order') == scipt.dataSortBy){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                    $div.find('li').eq(2).find('p input').each(function(index, el) {
                        if($(this).data('limit') == scipt.dataLimitNum){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                //课程
                if(scipt.moduleType == 2){
                    var _itemOneIdList = [],map = [];
                    if(scipt.itemOneIdList)
                        _itemOneIdList = scipt.itemOneIdList.split(',');

                    for(var k = 0 ; k < _itemOneIdList.length ; k++){
                    	map.put(_itemOneIdList[k],_itemOneIdList[k]);
                    }
                    $div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(0).find('p.showCheck input').each(function(index, el) {
                    	if(map.get($(this).data('id')+'')){
                    		$(this).prop('checked',true);
                    	}else{
                    		$(this).prop('checked', false);
                    	}
                    });
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                        if($(this).data('order') == scipt.dataSortBy){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                    $div.find('li').eq(2).find('p input').each(function(index, el) {
                        if($(this).data('limit') == scipt.dataLimitNum){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                //广告
                if(scipt.moduleType == 3){
                	$div.find('li').eq(0).find('p input.swText').val(scipt.customName || scipt.moduleName);
                	$div.find('li').eq(1).find('p input.swText').val(scipt.link);
                	$div.find('li').eq(2).find('input.swCheck').each(function(index,el){
                		if($(this).data('width') == scipt.widthSetting){
                			$(this).prop('checked','checked');
                		}else{
                			$(this).prop('checked',false);
                		}
                	});
                	if(scipt.widthSetting){
                		$div.find('li').eq(3).find('.reMark').hide();
                	}else{
                		$div.find('li').eq(3).find('.reMark').show();
                	}
                	if(scipt.picPath){
                		$div.find('.prompt').addClass('complete').removeClass('red').removeClass('uploading').html('已上传');
                	}else{
                		$('.prompt').addClass('uploading').removeClass('red').removeClass('complete').html('未上传');
                	}
                }
                //学员心声
                if(scipt.moduleType == 4){
                    $div.find('.swText').val(scipt.customName || scipt.moduleName);
                }
                //推荐课程包
                if(scipt.moduleType == 5){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                        if($(this).data('order') == scipt.dataSortBy){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                    $div.find('li').eq(2).find('p input').each(function(index, el) {
                        if($(this).data('limit') == scipt.dataLimitNum){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                //名师专区
                if(scipt.moduleType == 6){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                }
                //会员专区
                if(scipt.moduleType == 7){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                        if($(this).data('order') == scipt.dataSortBy){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                //学员动态
                if(scipt.moduleType == 8){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                }
                //问答专区
                if(scipt.moduleType == 9){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                        if($(this).data('order') == scipt.dataSortBy){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                //新闻咨询
                if(scipt.moduleType == 10){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                        if($(this).data('order') == scipt.dataSortBy){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                //最近直播
                if(scipt.moduleType == 11){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                    	if($(this).data('limit') == scipt.dataLimitNum){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                
                //精品微课
                if(scipt.moduleType == 12){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                    	if($(this).data('limit') == scipt.dataLimitNum){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                
                //专题课堂
                if(scipt.moduleType == 13){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                    	if($(this).data('limit') == scipt.dataLimitNum){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                
                //名师推荐
                if(scipt.moduleType == 14){
                	$div.find('.swText').val(scipt.customName || scipt.moduleName);
                    $div.find('li').eq(1).find('p input').each(function(index, el) {
                    	if($(this).data('limit') == scipt.dataLimitNum){
                            $(this).addClass('active').siblings().removeClass('active');
                            return;
                        }
                    });
                }
                
                //绑定数据
                $div.find('ul').data(scipt).find('.swSaveBut').data('uuid',_uuid);
            },
            eidtScipt : function($div){
                var customName = $div.find('li').eq(0).find('.swText').val(),
                	_order,_limit,_link,_itemOneIdList = [];

                scipt = $div.find('ul').data();

                if(scipt.moduleType != 2 && !customName){
                    $.msg('自定义名称不能为空');
                    return false;
                }
                if(customName && customName.length > 6){
                	$.msg('自定义名称最多6个字');
                    return false;
                }
                scipt.customName = customName;
                //今日公开课
                if(scipt.moduleType == 0){

                }
                //推荐课程
                if(scipt.moduleType == 1){
                	_order = $div.find('li').eq(1).find('.active').data('order');
                    _limit = $div.find('li').eq(2).find('.active').data('limit');
                    if(_order)
                    	scipt.dataSortBy = _order;
                    if(_limit)
                        scipt.dataLimitNum = _limit;
                }
                //课程
                if(scipt.moduleType == 2){

                    $div.find('li').eq(0).find('p.showCheck input').each(function(index, el) {
                        if($(this).is(':checked'))
                            _itemOneIdList.push($(this).data('id'));
                    });
                    if(!_itemOneIdList.length){
                        $.msg('请选择展示学科');
                        return false;
                    }
                    scipt.itemOneIdList = _itemOneIdList.join(',');
                    _order = $div.find('li').eq(1).find('.active').data('order');
                    _limit = $div.find('li').eq(2).find('.active').data('limit');
                    if(_order)
                        scipt.dataSortBy = _order;
                    if(_limit)
                        scipt.dataLimitNum = _limit;
                }
                //广告
                if(scipt.moduleType == 3){
                    _link = $div.find('li').eq(1).find('.swText').val();
//                    if(!_link){
//                        $.msg('广告链接不能为空');
//                        return false;
//                    }
                    scipt.link = _link;
                    $div.find('li').eq(2).find('input.swCheck').each(function(index,el){
                        if($(this).is(':checked')){
                            scipt.widthSetting = $(this).data('width');
                        }
                    });
                }
                //学员心声
                if(scipt.moduleType == 4){

                }
                //推荐课程包
                if(scipt.moduleType == 5){
                    _order = $div.find('li').eq(1).find('.active').data('order');
                    _limit = $div.find('li').eq(2).find('.active').data('limit');
                    if(_order)
                        scipt.dataSortBy = _order;
                    if(_limit)
                        scipt.dataLimitNum = _limit;
                }
                //名师专区
                if(scipt.moduleType == 6){
                    _order = $div.find('li').eq(1).find('.active').data('order');
                    if(_order)
                        scipt.dataSortBy = _order;
                }
                //会员专区
                if(scipt.moduleType == 7){
                    _order = $div.find('li').eq(1).find('.active').data('order');
                    if(_order)
                        scipt.dataSortBy = _order;
                }
                //学员动态
                if(scipt.moduleType == 8){

                }
                //问答专区
                if(scipt.moduleType == 9){
                    _order = $div.find('li').eq(1).find('.active').data('order');
                    if(_order)
                        scipt.dataSortBy = _order;
                }
                //新闻咨询
                if(scipt.moduleType == 10){
                    _order = $div.find('li').eq(1).find('.active').data('order');
                    if(_order)
                        scipt.dataSortBy = _order;
                }
                //最近直播 
                if(scipt.moduleType == 11){
                	_limit = $div.find('li').eq(1).find('.active').data('limit');
                    if(_limit)
                        scipt.dataLimitNum = _limit;
                }
                
                //精品微课
                if(scipt.moduleType == 12){
                	_limit = $div.find('li').eq(1).find('.active').data('limit');
                    if(_limit)
                        scipt.dataLimitNum = _limit;
                }
                
                //专题课堂 
                if(scipt.moduleType == 13){
                	_limit = $div.find('li').eq(1).find('.active').data('limit');
                    if(_limit)
                        scipt.dataLimitNum = _limit;
                }
                //名师推荐
                if(scipt.moduleType == 14){
                	_limit = $div.find('li').eq(1).find('.active').data('limit');
                    if(_limit)
                        scipt.dataLimitNum = _limit;
                }
                return scipt;
            },
            beforeSave : function(){
            	var arr = [],
            		scipt,
            		_this = this;
            	
                $('.schList dd').each(function(index, el) {
                	scipt = $(this).data();
                	for(var i in scipt){
                		if(!_this.baseEntiy[i])
                			delete scipt[i];
                	}
                	if(!scipt.schoolId)
                		scipt.schoolId = $('.schWorkT .heading').data('schoolId');
                    arr.push(scipt);
                });
                //排序
                arr.sort(this.sortByDesc('status',this.sortByAsc('displaySeq',this.sortByDesc('id'))));
                for(var _n = 0; _n < arr.length ; _n++)
                	arr[_n].displaySeq = _n;
                
                console.log(arr);
                return arr;
            },
            uuid : function(){
            	var s = [];
                var hexDigits = "0123456789abcdef";
                for (var i = 0; i < 36; i++) {
                    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
                }
                s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
                s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
                s[8] = s[13] = s[18] = s[23] = "-";

                var uuid = s.join("");
                return uuid;
            },
            localStorage : {
                checkVersion : function(){
                    return window.localStorage;
                },
                getItem : function(key){
                    var val;
                    if(this.checkVersion()){
                    	try {
                    		val = JSON.parse(localStorage.getItem(key));
						} catch (e) {
							console.log('json转换失败');
						}
                    }
                    return val;
                },
                setItem : function(key,val){
                    if(this.checkVersion()){
                    	try {
                    		var str = JSON.stringify(val);
                    		localStorage.setItem(key,JSON.stringify(val));
						} catch (e) {
							console.log('本地存储失败');  
						}
                    }
                },
                removeItem : function(key){
                    if(this.checkVersion())
                        localstorage.removeItem(key);
                },
                clear : function(){
                    if(this.checkVersion())
                        localStorage.clear();
                }
            },
            sortByAsc : function(name, minor){
                return function(o, p) {
                    var a, b;
                    if (o && p && typeof o === 'object' && typeof p === 'object') {
                        a = o[name];
                        b = p[name];
                        if (a === b) {
                            return typeof minor === 'function' ? minor(o, p) : 0;
                        }
                        if (typeof a === typeof b) {
                            return a - b;
                        }
                        return typeof a < typeof b ? -1 : 1;
                    } else {
                        throw("error");
                    }
                }
            },
            sortByDesc : function(name, minor){
                return function(o, p) {
                    var a, b;
                    if (o && p && typeof o === 'object' && typeof p === 'object') {
                        a = o[name];
                        b = p[name];
                        if (a === b) {
                            return typeof minor === 'function' ? minor(o, p) : 0;
                        }
                        if (typeof a === typeof b) {
                            return b - a;
                        }
                        return typeof a > typeof b ? 1 : -1;
                    } else {
                        throw("error");
                    }
                }
            },
            event: function() {
            	var _scipt,
                    _this = this,
                    _uuid;
                $('.schList').on('click','dd .switch',function(){
                	_scipt = $(this).parents('dd').data();
                	if($(this).hasClass('open')){
                		_scipt.status = 0;
                		$(this).removeClass('open').html('&#xe603;');
                	}else{
                		_scipt.status = 1;
                		$(this).addClass('open').html('&#xe604;');
                	}
                }).on('click','.w17 .edit',function(){
                    _uuid = _this.uuid();
                	_scipt = $(this).parents('dd').addClass('active').attr('id',_uuid).data();
                    $(this).parents('dd').siblings().removeClass('active');
                	$('.eaditArea').show().find('.swConText').each(function(){
                		if($(this).data('id') == _scipt.moduleType){
                			$(this).show().siblings().hide();
                			_this.initRightHtml($(this),_scipt,_uuid);
                			return;
                		}
                	});
                }).on('click','.advert .delet',function(){
                	_scipt = $(this).parents('dd').data();
                	//记录删除的广告id
                	if(_scipt.id){
                		_this.deleteAdv.push(_scipt.id);
                	}
                    $(this).parents('dd').remove();
                    $('.schList dd').eq(0).find('.edit').trigger('click');
                });
                //添加广告
                $('.addAdverBut').on('click','button',function(){
                	var _scipt = {moduleName:'广告',customName:'广告',status:1,moduleType:3,widthSetting:0},
                		_index = $('.schList dd').length;
                	_scipt.displaySeq = _index;

                	$('.schList dl').append(_this.getLeftHtml(_scipt));
                	$('.schList dd').last().data(_scipt);
                });
                //编辑保存
                $('.swConText').on('click','.swSaveBut',function(){
                    _uuid = $(this).data('uuid');
                    _scipt = _this.eidtScipt($(this).parents('.swConText'));
                    console.log(_scipt);
                    if(_scipt){
                        $('#'+_uuid).data(_scipt);
                        $('#'+_uuid).find('.w33 a').html(_scipt.customName).attr('title', _scipt.customName);
                        $('#'+_uuid).find('.w9 .switch').attr('title', _scipt.status?'禁用':'启用');
                        $.msg('已保存此模块信息');
//                        $('.eaditArea').hide();
//                        $(this).parents('.swConText').hide();
                    }
                }).on('click','.swChioceBut',function(){
                    $(this).addClass('active').siblings().removeClass('active');
                }).on('change','#imgData',function(){
                	_this.upload($(this).parents('.swConText'));
                });

                //广告通屏设置
                $('.isWidth').on('click','.swCheck',function(){
                	$(this).parent().siblings().find('.swCheck').prop('checked', false);
                	if($(this).data('width')){
                		$(this).parents('li').next().find('.reMark').hide();
                	}else{
                		$(this).parents('li').next().find('.reMark').show();
                	}
                });



                // 预览
                $('.heading').on('click','.preview',function(){
                    var arr = _this.beforeSave();
                    if(!_this.localStorage.checkVersion()){
                    	$.msg('浏览器版本太低，请升级或更换浏览器后再尝试该操作');
                    	return;
                    }
                    _this.localStorage.clear();
                    _this.localStorage.setItem('scipts',arr);
                    window.open(rootPath + '/sysConfigIndexPageTemplate/preview' ,'_blank');
                }).on('click','.save',function(){
                	var arr = _this.beforeSave(),
                		data = {},
                		$this = $(this);
                	
                	data.scipts = JSON.stringify(arr);
                	if(_this.deleteAdv.length)
                		data.deleAdv = _this.deleteAdv.join(',');
                	
                	if($(this).hasClass('disable'))
                		return;
                	$(this).addClass('disable')
                	_this.ajax('/sysConfigIndexPageTemplate/updateSysConfigIndexPage',data,function(result){
                		$(this).removeClass('disable');
                		if(result.flag){
                			$.msg('此模板已发布生效');
                			
                			setTimeout(function(){
                				history.go(-1);
                			},2000);
                		}
                	});
                }).on('click','.reset',function(){
                	var dataInfo = $('.schWorkT .heading').data();
                	$.confirm({
						title:'重置',
						text:'重置模板后，所有在此模板上的操作内容都将会被删除，是否确认重置模板？',
						saveOk:true,
						cancelOk:true,
						save:'确定',
						callback:function(flag){
							if(flag){
								if($(this).hasClass('disable'))
				            		return;
				            	$(this).addClass('disable')
			                	_this.ajax('/sysConfigIndexPageTemplate/reset',dataInfo,function(result){
			                		if(result.flag){
			                			$.msg('重置成功');
			                			window.location.reload();
			                		}
			                	});
							}
						}
					});
                	
                }).on('click','.goback',function(){
                	window.history.go(-1);	
                });

            },
            upload : function ($div){
            	var _scipt;
            	_scipt = $div.find('ul').data();
            	var file = $("#imgData").val();
            	var fileType = file.substring(file.lastIndexOf(".")+1);
            	if(fileType!="png"&&fileType!="jpg"){ 
                    $.msg("上传文件格式错误"); 
                    return; 
                } 
            	
            	$('.prompt').addClass('uploading').removeClass('red').removeClass('complete').html('上传中');
            	$.ajaxFileUpload({
        			url : rootPath+"/sysBody/uploadPics;"+ window["sessionName"] + "=" + window["sessionId"],
        			fileElementId : 'imgData',
        			dataType:'json',
        			type: 'POST',
        			beforeSend:function(xhr){
        		        $('.prompt').addClass('uploading').removeClass('red').removeClass('complete').html('上传中');
        		    },
        			success : function(data) {
        				if(data.url){
        					_scipt.picPath = data.url;
        					$div.find('ul').data(_scipt);
        					$('.prompt').addClass('complete').removeClass('red').removeClass('uploading').html('已上传');
        				}
        			},
        			error:function(arg1,arg2,arg3){
        				//console.log(arg1);
//        				$.loadover();
        				$('.prompt').addClass('red').removeClass('complete').removeClass('uploading').html('！上传失败');
        			}
        		});
            }

        }
        /**
         * 页面加载完成后执行
         */
    $(function() {
        var model = new Model();
        model.init();
        window._model = model;
    });
})(jQuery);
