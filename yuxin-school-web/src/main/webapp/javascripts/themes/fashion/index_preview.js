$.imageserver = 'http://images.cdds365.com/';
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
    	this.schoolId = undefined;
        //配置
        this.config = {
        		
        },
        this.sys_config_serive = [];
        this.htmlArr = [
            // 今日公开课
        '<section class="articleBox">'+
	    	'<div class="clear publicBox">'+
	               '<div class="fl publicBut ">'+
	                 '<div class="memberzhuan">'+
	                      '<span class="limit">公开课</span>'+
	                      '<button>更多</button>'+
	                 '</div>'+
	              '</div>'+
	              '<figure class="fr publicCon">'+
	                  '<div class="fl">'+
	                      '<img src="'+rootPath+'/images/heart3.jpg" alt="">'+
	                  '</div>'+
	                  '<div class="fr">'+
	                      '<figcaption></figcaption>'+
	                      '<p class="figText"></p>'+
	                      '<p class="figTime">'+
	                           '<img src="../images/zb.gif" alt=""><time> 正在直播 </time>'+
	                           '<span></span>'+
	                      '</p>'+
	                  '</div>'+
	              '</figure>'+
	              '<ol class="publicList">'+
	              '</ol>'+
	          '</div>'+
	      '</section>',
	      
            //推荐课程
            '<section class="articleBox">'+
                '<div>'+
                    '<h3 class="classTitle"> <span>推荐课程</span></h3>'+
                    '<ul class="clear classList">'+
                    '</ul>'+
                    '<div class="moreBut">'+
                        '<button>查看更多</button>'+
                    '</div>'+
                '</div>'+
            '</section>',
            
            // 课程
            '<section class="articleBox">'+
                '<div>'+
                    '<h3 class="classTitle"> <span>学科名称</span></h3>'+
                    '<ul class="clear classList">'+
                    '</ul>'+
                    '<div class="moreBut">'+
                        '<button>查看更多</button>'+
                    '</div>'+
                '</div>'+
            '</section>',
            
            // 广告
            '<section class="articleBox">'+
                '<aside class="advert">'+
                    '<img src="" alt="">'+
                '</aside>'+
            '</section>',
            
            //学员心声
            '<section class="articleBox">'+
                '<div class="dynamic">'+
                    '<div class="dynamicTit clear">'+
                        '<h4 class="fl">学员心声</h4>'+
                        '<span class="fr seeMore">查看更多</span>'+
                    '</div>'+
                    '<div class="dyheart clear">'+
                        '<ul class="heartshow clear">'+
                        '</ul>'+
                    '</div>'+
                '</div>'+
            '</section>',

            //推荐课程包
            '<section class="articleBox">'+
                '<div>'+
                    '<h3 class="classTitle"> <span>课程包</span></h3>'+
                    '<ul class="clear classList threeImg">'+
                    '</ul>'+
                    '<div class="moreBut">'+
                        '<button>查看更多</button>'+
                    '</div>'+
                '</div>'+
            '</section>',

            // 名师专区
            '<section class="articleBox">'+
                '<div>'+
                    '<h3 class="classTitle"><span>名师专区</span></h3>'+
                    '<ul class="famousArea clear">'+
                    '</ul>'+
                    '<div class="moreBut">'+
                        '<button>查看更多</button>'+
                    '</div>'+
                '</div>'+
            '</section>',

            //会员专区
            '<section class="articleBox">'+
                '<div class="clear publicBox">'+
                    '<div class="fl publicBut memberbut">'+
	                    '<div class="memberzhuan">'+
		                    '<span class="limit">会员专区</span>'+
		                    '<button>更多</button>'+
		               '</div>'+
                    '</div>'+
                    '<div class="fr swipergrade-container swiper-container">'+
                        '<ul class="swiper-wrapper">'+
                        '</ul>'+
                        '<div class="swiper-pagination swipergrade-pag"></div>'+
                    '</div>'+
                '</div>'+
            '</section>',

            // 学员动态
            '<section class="articleBox">'+
	            '<div class="dynamic">'+
		            '<div class="dynamicTit clear">'+
		                '<h4 class="fl">学员动态</h4>'+
		            '</div>'+
		            '<div class="swiper-container swipernamic-container">'+
	                    '<ul class="swiper-wrapper">'+
	                    	
		                '</ul>'+
		            '</div>'+
		        '</div>'+
            '</section>',

            // 问答社区
            '<section class="articleBox">'+
                '<div class="dynamic">'+
                    '<div class="dynamicTit clear">'+
                        '<h4 class="fl">问答社区</h4>'+
                        '<span class="fr seeMore">查看更多</span>'+
                    '</div>'+
                    '<div class="dynamicBox dynamicWen clear">'+
                        '<ul class="fl">'+
                        '</ul>'+
                        '<div class="cenLine cenLine2 fl"></div>'+
                        '<ul class="fr">'+
                        '</ul>'+
                    '</div>'+
                '</div>'+
            '</section>',

            // 新闻资讯
            '<section class="articleBox">'+
                '<div class="dynamic">'+
                    '<div class="dynamicTit clear">'+
                        '<h4 class="fl">新闻专区</h4>'+
                        '<span class="fr seeMore">查看更多</span>'+
                    '</div>'+
                    '<div class="newsArea clear">'+
                        '<div class="carousel fl">'+
                            '<div class="swiper-container swipernews-container">'+
                                '<ul class="swiper-wrapper">'+
                                '</ul>'+
                                '<div class="swipernew-pag swiper-pagination"></div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="newsintro fr">'+
                            '<ul>'+
                            '</ul>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
            '</section>',
        ];
    };
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
            	this.querySysCyclePic();
                this.event();
                this.main();
            },
            event: function() {

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
            /**
             * 查询配置
             * @return {[type]} [description]
             */
            main : function(){
                var _this = this,
                    _sysConfigServies,
                    _sysConfigServie,
                    _pageModels,
                    _pageModel,
                    _uuid,
                    _html = '';
                
                _pageModels = this.localStorage.getItem('scipts');
                for (var i = 0; i < _pageModels.length; i++) {
                    _pageModel = _pageModels[i];
                    if(!_this.choolId)
                    	_this.schoolId = _pageModel.schoolId;
                    if(_pageModel.status == 1){
                        _uuid = _this.uuid();
                        $('article').append(_this.htmlArr[_pageModel.moduleType]);
                        $('article').children().last().attr('id',_uuid).data(_pageModel);
                        _this.queryData(_uuid,_pageModel);
                    }
                }

            },
            sysServiceIsOpen : function(moduleType){
            	switch (moduleType) {
				case 0:
					return this.sys_config_serive.get('SERVICE_OPENCLASS')==1;
					break;
				case 4:
					return this.sys_config_serive.get('SERVICE_STUDENT_ASPIRATIONS')==1;
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
            /**
             * 轮播图
             */
            querySysCyclePic : function(){
            	var html = '';
            	this.ajax('/sysCyclePic/showPic2',{"picType" : 'homepage'},function(result){
            		$.each(result,function(i,item){
         				html += '<li class="swiper-slide" style="background-image: url('+item.picUrl+');" data-url="'+item.clickUrl+'">'+
		                        '</li>';
         			});
            		$('#banner').html(html);
            		setTimeout(function(){
            			var bannerSwiper = new Swiper('.swiperwell-container', {
                            pagination: '.selfPagination',
                            prevButton: '.prevBut',
                            loop: true,
                            autoplay: 5000,
                            autoplayDisableOnInteraction:false,
                            nextButton: '.nextBut',
                            paginationClickable: true,
                            paginationBulletRender: function(index, className) {
                                return '<span class="' + className + ' butto' + index + '">' + (index + 1) + '</span>';
                            }
                        });
    	           		$('.banner-container').on('mouseenter',function(){
    	           			bannerSwiper.stopAutoplay();
    	           		});
    	           		$('.banner-container').on('mouseleave',function(){
    	           			bannerSwiper.startAutoplay();
    	           		});
            		},2000);
            	});
            },
            /**
             * 查询数据
             * @param  {[type]} _uuid  [元素]
             * @param  {[type]} _scipt [配置]
             * @return {[type]}        [description]
             */
            queryData : function(_uuid,_scipt){
                var _this = this,
                	_html = '';
                // 今日公开课
                if(_scipt.moduleType == 0){
                	var con;
                	$('#'+_uuid).find('.memberzhuan span').html(_scipt.customName);
//                	$('#'+_uuid).find('.memberzhuan').on('click','button',function(){
//                		window.open(rootPath + '/liveOpenCourse/toOpenClass', '_blank');
//                	});
                	$('#'+_uuid).on('click','.publicList li',function(){
            			var _con = $(this).find('b').data();
            			_this.changeOpenClass(_uuid,_con);
            			if($(this).hasClass('on')){
            				$(this).find('img').attr('src',rootPath+'/images/zb1.gif');
            			}
            			$(this).addClass('active').siblings().removeClass('active');
            			$(this).siblings().each(function(){
            				if($(this).hasClass('on')){
            					$(this).find('img').attr('src',rootPath+'/images/zb.gif');
            				}
            			});
            		});
                	
                	this.ajax('/liveOpenCourse/findTodayAndAfter',{},function(result){
                		if(!result || !result.length){
                			$('#'+_uuid).remove();
                			return;
                		}
                		for(var i=0; i<result.length; i++){
                			if(i > 4)
                				break;
                			con = result[i];
                			con = _this.formatOpenClass(con);
                			
                			_html = '<li '+(con.playStatus == 1?'class="on"':'')+' data-id="'+con.id+'" data-id="'+con.id+'" data-live-types="live" data-url="'+con.studentUrlGh+'">'+
		                                '<b></b>'+
		                                (con.playStatus == 1?'<img src="'+rootPath+'/images/zb.gif" alt="">':'<time>'+con._begin+'</time><time>'+con._end+'</time>')+
		                                '<span>'+con.openCourseName+'</span>'+
		                            '</li>';
                			
                			$('#'+_uuid).find('.publicList').append(_html);
                			$('#'+_uuid).find('.publicList li').last().find('b').data(con);
                			if(i == 0){
//                				_this.changeOpenClass(_uuid,con);
//                				$('#'+_uuid).find('.publicList li').addClass('active');
                				$('#'+_uuid).find('.publicList li').last().trigger('click');
                			}
                			
                		}
                		$('#'+_uuid).on('click','.publicList li',function(){
                			var _con = $(this).find('b').data();
                			_this.changeOpenClass(_uuid,_con);
                			$(this).addClass('active').siblings().removeClass('active');
                		});
                		
                	});
                }
                //推荐课程
                if(_scipt.moduleType == 1){
                	var dataInfo = {},
                		comm;
                	
                	$('#'+_uuid).find('.classTitle span').html(_scipt.customName);
//                	$('#'+_uuid).find('.moreBut').on('click','button',function(){
//                		window.open(rootPath + '/sysConfigItem/showList', '_blank');
//                	});
                	
                	if('time' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'c.id desc';
                	if('hot' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'actualNum desc';
                	if('zh' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'CONVERT(c.name USING gbk) ASC';
                	dataInfo.cuslimit = _scipt.dataLimitNum;
                	
                	
                	this.ajax('/commodity/loadData',dataInfo,function(result){
                		if(!result || !result.length){
                			$('#'+_uuid).remove();
                			return;
                		}
                		
                		for(var i=0; i<result.length; i++){
                			comm = result[i];
                			
                			_html += '<li data-id="'+comm.id+'">'+
		                                '<h4 class="listImg">'+
				                            '<img src="'+(comm.coverUrl?$.imageserver + comm.coverUrl : rp+"/images/overview_demo.jpg")+'" alt="">'+
				                            '<span>'+
				                            '<i class="iconfont">&#xe618;</i>'+(comm.actualNum)+''+
				                        '</span>'+
				                        '</h4>'+
				                        '<p class="clear listCon">'+
				                            '<span class="fl">'+(comm.name)+'</span>'+
				                            (comm.isOver?'':'<small class="fr '+(!comm.realPrice?'freePrice':'')+'">'+(!comm.realPrice?'免费':'￥'+comm.realPrice)+'</small>')+
				                        '</p>'+
				                        '<hr class="hLine">'+
				                        (comm.memberFlag?'<strong class="vipImg"></strong>':'')+
					                    (comm.isOver?'<strong class="fillImg"></strong>':'')+
				                    '</li>';
                		}
                		$('#'+_uuid).find('.classList').html(_html);
//                		$('#'+_uuid).find('.classList').on('click','li',function(){
//                			var id = $(this).data('id');
//	            			if(id)
//	            				window.open(rootPath + '/sysConfigItem/selectDetail/' + id,'_blank');
//                		});
                	});
                }
                //课程
                if(_scipt.moduleType == 2){
                	var dataInfo = {},
	            		comm,itemOneIdArr,uuidArr=[];
                	
                	$('#'+_uuid).find('.classTitle span').html(_scipt.customName);
                	
                	dataInfo.itemOneIdStrs = _scipt.itemOneIdList;
                	if('time' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'c.id desc';
                	if('hot' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'actualNum desc';
                	if('zh' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'CONVERT(c.name USING gbk) ASC';
	            	dataInfo.cuslimit = _scipt.dataLimitNum;
	            	
	            	if(!dataInfo.itemOneIdStrs){
            			$('#'+_uuid).remove();
            			return;
            		}
	            	itemOneIdArr = dataInfo.itemOneIdStrs.split(',');
	            	for(var k = 0 ; k < itemOneIdArr.length ; k++){
	            		dataInfo.itemOneIdStrs = itemOneIdArr[k];
	            		
	            		if(k == 0){
	            			_this.queryRecommonClass(dataInfo,_uuid);
	            			uuidArr.push(_uuid);
	            		}else{
	            			var $uuid = _this.uuid();
	            			$('#' + uuidArr[k-1]).after(_this.htmlArr[_scipt.moduleType])
	            			$('#' + uuidArr[k-1]).next().attr('id',$uuid).data(_scipt);
	            			_this.queryRecommonClass(dataInfo,$uuid);
	            			uuidArr.push($uuid);
	            		}
	            	}
	            	
                }
                //广告
                if(_scipt.moduleType == 3){
                	
                	if(_scipt.widthSetting){
                		$('#'+_uuid).removeClass('articleBox');
                	}
                	if(_scipt.link){
                		$('#'+_uuid).find('img').css({'cursor':'pointer'});
                		$('#'+_uuid).on('click','img',function(){
                			window.open(_scipt.link,'_blank');
                		});
                	}else{
                		$('#'+_uuid).find('img').css({'cursor':'default'});
                	}
                	if(_scipt.picPath){
                		$('#'+_uuid).find('img').attr('src',_scipt.picPath);
                	}else{
                		$('#'+_uuid).remove();
                	}
                	
                }
                //学员心声
                if(_scipt.moduleType == 4){
                	var dataInfo = {},
                		stu;
                	$('#'+_uuid).find('.dynamicTit h4').html(_scipt.customName);
//                	$('#'+_uuid).on('click','.seeMore',function(){
//                		window.open(rootPath + '/studentStar/findByPage','_blank');
//                	});
                	
                	dataInfo.pageSize = _scipt.dataLimitNum;
                	this.ajax('/studentStar/getJson/1',dataInfo,function(result){
                		result = result.data;
                		
                		if(!result || !result.length){
                			$('#'+_uuid).remove();
                			return;
                		}
                		
                		for(var i=0; i < result.length; i++){
                			stu = result[i];
                			
                			_html += '<li class="fl">'+
		                                '<p class="stuimg" style="background-image:url('+(stu.userPic?$.imageserver + stu.userPic : rp+"/images/stu-no.png")+')"></p>'+
			                                '<h5>'+ stu.userName +'</h5>'+
			                                '<p class="situate">'+ stu.des +'</p>'+
			                            '</li>';
                		}
                		$('#'+_uuid).find('.heartshow').html(_html);
                	});
                }
                //推荐课程包
                if(_scipt.moduleType == 5){
                	var dataInfo = {},
                		classPackages,
                		classPackage;
                	$('#'+_uuid).find('.classTitle span').html(_scipt.customName);
//                	$('#'+_uuid).find('.moreBut').on('click','button',function(){
//                		window.open(rootPath + '/classPackage/findAll','_blank');
//                	});
                	
                	if('time' == _scipt.dataSortBy)
                		dataInfo.orderBy = 'b.create_time';
                	if('hot' == _scipt.dataSortBy)
                		dataInfo.orderBy = 'total_count';
	            	dataInfo.pageSize = _scipt.dataLimitNum;
	            	
	            	
	            	this.ajax('/classPackage/getJson',dataInfo,function(result){
	            		classPackages = result.pageFind.data;
	            		if(!classPackages || !classPackages.length){
	            			$('#'+_uuid).remove();
	            			return;
	            		}
	            		
	            		for(var i=0; i<classPackages.length; i++){
	            			classPackage = classPackages[i];
	            			
	            			_html += '<li data-id="'+ classPackage.id +'">'+
			                            '<h4 class="listImg">'+
					                        '<img src="'+(classPackage.cover?$.imageserver + classPackage.cover : rootPath + '/images/overview_demo.jpg')+'" alt="">'+
					                    '</h4>'+
					                    '<p class="clear listCon">'+
					                        '<span class="fl">'+classPackage.name+'</span>'+
					                        '<small class="fr '+(classPackage.realPrice==0.0?'freePrice':'')+'">'+(classPackage.realPrice==0.0?'免费':'￥'+classPackage.realPrice)+'</small>'+
					                    '</p>'+
					                    '<p class="clear stage">'+
					                        '<span class="fl">'+
					                            '阶段'+classPackage.stageCount+'<i>|</i>课程'+classPackage.classTypeCount+
					                        '</span>'+
					                        '<span class="fr">'+
					                            '<i class="iconfont">&#xe618;</i>'+(classPackage.payCount+classPackage.baseNum)+
					                        '</span>'+
					                    '</p>'+
					                '</li>';
	            		}
	            		$('#'+_uuid).find('.classList').html(_html);
//	            		$('#'+_uuid).find('.classList').on('click','li',function(){
//	            			var id = $(this).data('id');
//	            			if(id)
//	            				window.open(rootPath + '/classPackage/classPackageDetail/' + id,'_blank');
//	            		});
	            	});
                }
                //名师专区
                if(_scipt.moduleType == 6){
                	var dataInfo = {},
                		tea;
                	
                	$('#'+_uuid).find('.classTitle span').html(_scipt.customName);
//                	$('#'+_uuid).find('.moreBut').on('click','button',function(){
//                		window.open(rootPath + '/sysConfigTeacher/teachers' , '_blank');
//                	});
                	
                	if('zh' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'all';
                	if('hot' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'hot';
                	if('good' == _scipt.dataSortBy)
                		dataInfo.cusorder = 'good';
	            	
	            	
	            	this.ajax('/sysConfigTeacher/queryData',dataInfo,function(result){
	            		if(!result.data.length){
	            			$('#'+_uuid).remove();
	            			return;
	            		}
	            		for(var i=0; i<result.data.length; i++){
	            			tea = result.data[i];
	            			if(i > 5)
	            				break;
	            			
	            			tea = _this.formatTeaher(tea);
	            			
	            			_html += '<li class="fl" style="background-image:url('+ tea.headpicUrl +')" data-id="'+ tea.id +'">'+
			                            '<div class="clear compr">'+
					                        '<strong class="fl">'+ tea.name +'</strong>'+
					                        '<p class="fr star">'+
					                            _this.getStarHtml(5,tea.score)+
					                        '</p>'+
					                    '</div>'+
					                '</li>';
	            		}
	            		$('#'+_uuid).find('.famousArea').html(_html);
//	            		$('#'+_uuid).find('.famousArea').on('click','li',function(){
//	            			var id = $(this).data('id');
//	            			if(id)
//	            				window.open(rootPath + '/sysConfigTeacher/queryDetail/' + id, '_blank');
//	                	});
	            	});
                }
                //会员专区
                if(_scipt.moduleType == 7){
                	var dataInfo = {},
                		vipStages,
                		cmc,cic,
	            		vip;
                	
                	$('#'+_uuid).find('.limit').html(_scipt.customName);
//                	$('#'+_uuid).find('.memberzhuan').on('click','button',function(){
//                		window.open(rootPath + '/companyMemberConfig/toVip', '_blank');
//                	});
                	
	            	dataInfo.cusorder = _scipt.dataSortBy;
	            	
	            	this.ajax('/companyMemberConfig/getVipStage',dataInfo,function(result){
	            		cmc = result.cmc;
	            		cic = result.cic;
	            		vipStages = result.vipList;
	            		
	            		if(!vipStages.length){
	            			$('#'+_uuid).remove();
	            			return;
	            		}
	            		
	            		for(var i=0; i < vipStages.length; i++){
	            			vip = vipStages[i];
	            			
	            			_html += '<li class="fr memSwiper swiper-slide">'+
			                            '<div class="fl grade">'+
					                        '<div class="picContain clear">'+
					                            '<b class=""></b>'+
					                            '<span class="">'+ vip.vipName +'</span>'+
					                            '<b class=""></b>'+                             
					                        '</div>'+
					                    '</div>'+
					                    '<div class="fr member memberV">'+
					                        '<h3 class="memName"><i style="background-image:url('+ $.imageserver + vip.ico +')"></i>'+ vip.vipName +'</h3>'+
					                        '<p class="curName">'+
					                            vip.description +
					                        '</p>'+
					                        _this.getVipHtml(vip,cmc,cic)+
					                    '</div>'+
					                '</li>';
	            		}
	            		$('#'+_uuid).find('.swiper-wrapper').html(_html);
	            		var mySwipergrade = new Swiper('.swipergrade-container', {
	                	    loop: true,
	                	    speed:700,
	                	    // grabCursor : true,
	                	    pagination: '.swipergrade-pag',
	                	    paginationClickable: true
	                	}); 
	            	});
                }
                //学员动态
                if(_scipt.moduleType == 8){
                	var dataInfo = {},
                		log,
                		left='',right='';
                		dataInfo.cusorder = 'opera_time desc';
                		dataInfo.cuslimit = 24;
                		
                		$('#'+_uuid).find('.dynamicTit').find('h4').html(_scipt.customName);
                		
                	this.ajax('/sysLogStudentOperation/getJson',dataInfo,function(result){
                		var format,_num,_arr=[],_start,_end,_out,_a,_left='',_right='';
                		if(!result || !result.length){
                			$('#'+_uuid).remove();
                			return;
                		}
                			
                		
                		_num = Math.ceil(result.length/8);
                		for(var k = 0; k < _num ; k ++){
                			_start = k;
                			if(k == 0){
                				_end = result.length > 8 ? 8 : result.length;
                			}else {
                				_end = (k+1)*8;
                			}
                			_arr.push(result.slice(k*8,_end));
                		}
                		
                		for(var x = 0; x < _arr.length ; x ++){
                			_left='',_right='';
                			_a = _arr[x];
                			_out = '<li class="swiper-slide">'+
		                			    '<div class="dynamicBox clear">'+
				                	        '<ul class="fl"></ul>'+
				                	    '</div>'+
				                	'</li>';
                			
                			$('#'+_uuid).find('.swiper-wrapper').append(_out);
                			
                			for(var y = 0 ; y < _a.length ; y ++){
                				log = _a[y];
                				if(!log)
                					continue;
                    			format = _this.getOperation(log);
                    			
                				_html  =   '<li>'+
						                        '<div class="dynamicNext clear">'+
						                            '<p class="portrait fl" style="background-image: url('+ (log.headImg?log.headImg:rootPath + '/images/user/head_top.png') +');"></p>'+
						                            '<div class="studetail fl">'+
						                                '<h5>'+ log.userName +'</h5>'+
						                                '<p>'+
						                                    '<i>'+ format.action +'</i>'+
						                                    '<em '+(format.url?'data-url="'+format.url+'"':'')+'>'+ format.resource +'</em>'+
						                                '</p>'+
						                            '</div>'+
						                            '<b class="fr">'+ _this.getTime(log.operaTimemis) +'</b>'+
						                        '</div>'+
						                    '</li>';
                				if(y > 3){
                					_right += _html;
                				}else{
                					_left += _html;
                				}
                			}
                			
                			$('#'+_uuid).find('.swiper-slide').last().find('.fl').html(_left);
                			if(_right){
                				var _ulr = '<div class="cenLine cenLine1 fl"></div><ul class="fr">';
                				_ulr += _right;
                				_ulr + '</ul>';
                				$('#'+_uuid).find('.swiper-slide').last().find('.dynamicBox').append(_ulr);
                			}
                		}
                		
//                		$('#'+_uuid).on('click','.studetail em',function(){
//                			var _url = $(this).data('url');
//                			if(_url)
//                				window.open(rootPath + _url);
//                		});
                		setTimeout(function(){
                			var mySwipergrade = new Swiper('.swipernamic-container', {
                                autoplay: 3000,
                                autoplayDisableOnInteraction:false,
                                loop: true,
                            });
                    		$('.swipernamic-container').on('mouseenter',function(){
                    			mySwipergrade.stopAutoplay();
    	   	           		});
    	   	           		$('.swipernamic-container').on('mouseleave',function(){
    	   	           			 mySwipergrade.startAutoplay();
    	   	           		});
                		},2000);
                	});
                }

                //问答社区
                if(_scipt.moduleType == 9){
                	var dataInfo = {},
                		que,
                		left='',right='';
                	$('#'+_uuid).find('.dynamicTit h4').html(_scipt.customName);
//                	$('#'+_uuid).on('click','.seeMore',function(){
//                		window.open(rootPath + '/question/comQuestionIndex','_blank');
//                	});
                	
                	if('hot' == _scipt.dataSortBy)
                		dataInfo.essenceFlag = -1;
                	if('essence' == _scipt.dataSortBy)
                		dataInfo.essenceFlag = 1;
                	
                	this.ajax('/Question/getJson',dataInfo,function(result){
                		if(!result.questions || !result.questions.length){
                			$('#'+_uuid).remove();
	            			return;
                		}
                		
                		for(var i=0; i < result.questions.length; i++){
                			que = result.questions[i];
                			
                			if(i > 9)
                				break;
                			
                			_html = '<li data-id="'+ que.id +'">'+
                						'<div class="answerque clear">'+
				                            '<p class="fl courque">'+
				                                _this.getDj(que) +
				                                '<em>'+ que.questionTitle +'</em>'+
				                            '</p>'+
				                            '<p class="fr feedback">'+
				                                '<span>'+
				                                	'<i>'+ que.scanCount +'</i>回答'+
				                                '</span>'+
				                                '<span>'+
					                                '<i>'+ que.answerCount +'</i>浏览'+
					                            '</span>'+
				                            '</p>'+
				                        '</div>'+
				                    '</li>';
                			
                			if(i < 5){
                				left += _html;
                			}else{
                				right += _html;
                			}
                		}
                		$('#'+_uuid).find('.dynamicBox ul.fl').html(left);
                		$('#'+_uuid).find('.dynamicBox ul.fr').html(right);
//                		$('#'+_uuid).find('.dynamicBox').on('click','li',function(){
//	            			var id = $(this).data('id');
//	            			if(id)
//	            				window.open(rootPath + '/askQuestion/myQuestion/' + id, '_blank');
//	                	});
                	});
                }
                //新闻资讯
                if(_scipt.moduleType == 10){
                	var dataInfo = {},
                		news,left='',right='';
	            	$('#'+_uuid).find('.dynamicTit h4').html(_scipt.customName);
//	            	$('#'+_uuid).on('click','.seeMore',function(){
//                		window.open(rootPath + '/sysNews/list','_blank');
//                	});
	            	
	            	dataInfo.cusorder = 'sn.recommend_flag desc';
	            	dataInfo.cuslimit = 3;
	            	//三个推荐新闻
	            	this.ajax('/sysNews/getJosns',dataInfo,function(result){
	            		if(!result || !result.length){
	            			$('#'+_uuid).remove();
	            			return;
	            		}
	            		
	            		for(var i=0; i < result.length; i++){
	            			news = result[i];
	            			
	            			left += '<li class="swiper-slide" style="background-image: url('+$.imageserver + news.newsPic+');" data-id="'+ news.id +'">'+
			                            '<p class="pictintro">'+ news.newsTitle +'</p>'+
				                    '</li>';
	            		}
	            		$('#'+_uuid).find('.swiper-container ul').html(left);
//	            		$('#'+_uuid).find('.swiper-container ul').on('click','li',function(){
//	            			var id = $(this).data('id');
//	            			if(id)
//	            				window.open(rootPath + '/sysNews/findNewsById/' + id,'_blank');
//	            		});
	            		setTimeout(function(){
	            			var mySwipernews = new Swiper('.swipernews-container', {
		                	    autoplay: 3000,
		                	    loop: true,
		                	    speed: 1000,
		                	    // grabCursor : true,
		                	    pagination: '.swipernew-pag',
		                	    paginationClickable: true
		                	});
	            		},2000);
	            		
	            	});
	            	
	            	if('time' == _scipt.dataSortBy)
	            		dataInfo.cusorder = 'sn.publish_time desc';
	            	if('hot' == _scipt.dataSortBy)
	            		dataInfo.cusorder = 'sn.click_count desc';
	            	dataInfo.cuslimit = 4;
	            	this.ajax('/sysNews/getJosns',dataInfo,function(result){
	            		var arr;
	            		if(!result || !result.length)
	            			return;
	            		for(var i=0; i < result.length; i++){
	            			news = result[i];
	            			
	            			arr = news.publishTime.split('-');
	            			right += '<li class="clear" data-id="'+ news.id +'">'+
			                            '<div class="fl timeyear">'+
				                            '<em>'+ arr[1]+'-'+arr[2] +'</em>'+
				                            '<hr />'+
				                            '<i>'+ arr[0] +'</i>'+
				                        '</div>'+
				                        '<div class="fl along">'+
				                            '<p class="algood">'+
				                                '<span>'+ news.newsTitle +'</span>'+
				                                '<em>'+ news.newsTypeName +'</em>'+
				                            '</p>'+
				                            '<p class="alteach">'+ news.summary +'</p>'+
				                        '</div>'+
				                    '</li>';
	            		}
	            		$('#'+_uuid).find('.newsintro ul').html(right);
	            		
//	            		$('#'+_uuid).find('.newsintro ul').on('click','li',function(){
//	            			var id = $(this).data('id');
//	            			if(id)
//	            				window.open(rootPath + '/sysNews/findNewsById/' + id,'_blank');
//	            		});
	            	});
                }
            },
            formatOpenClass : function(con){
            	var today = dateToStr('yyyy-MM-dd',new Date()),
            		md = con.startOpenData.slice(con.startOpenData.indexOf('-')+1);
            	if(con.startOpenData == today){
            		con._begin = con.startTime;
            		con._end = con.endTime;
            	}else{
            		con._begin = md;
            		con._end = con.startTime;
            	}
//            	if(con.openCourseName && con.openCourseName.length > 10){
//            		con.openCourseName = con.openCourseName.substring(0,10)+'...';
//            	}
            	return con;
            },
            changeOpenClass : function(_uuid,con){
            	$('#'+_uuid).find('.publicCon .fl').find('img').attr('src',$.imageserver + con.cover);
            	$('#'+_uuid).find('figcaption').html(con.openCourseName);
				$('#'+_uuid).find('.figText').html(con.detailDesc);
				$('#'+_uuid).find('.figTime').html((con.playStatus == 1?'<img src="'+rootPath+'/images/zb.gif" alt=""><time> 正在直播 </time>':'<time>'+con.startTime + '-' + con.endTime+'</time>'));
				$('#'+_uuid).find('.figTime').append('<span>'+con.startOpenData+'</span>');
            },
            queryRecommonClass : function(dataInfo,_uuid){
            	var comm,_html = '';
            	
            	this.ajax('/commodity/loadData',dataInfo,function(result){
            		if(!result || !result.length){
            			$('#'+_uuid).remove();
            			return;
            		}
            		
            		for(var i=0; i<result.length; i++){
            			comm = result[i];
            			
            			_html += '<li data-id="'+comm.id+'">'+
	                                '<h4 class="listImg">'+
			                            '<img src="'+(comm.coverUrl?$.imageserver + comm.coverUrl : rp+"/images/overview_demo.jpg")+'" alt="">'+
			                            '<span>'+
			                            '<i class="iconfont">&#xe618;</i>'+(comm.actualNum)+''+
			                        '</span>'+
			                        '</h4>'+
			                        '<p class="clear listCon">'+
			                            '<span class="fl">'+(comm.name)+'</span>'+
			                            (comm.isOver?'':'<small class="fr '+(!comm.realPrice?'freePrice':'')+'">'+(!comm.realPrice?'免费':'￥'+comm.realPrice)+'</small>')+
			                        '</p>'+
			                        '<hr class="hLine">'+
			                        (comm.memberFlag?'<strong class="vipImg"></strong>':'')+
				                    (comm.isOver?'<strong class="fillImg"></strong>':'')+
			                    '</li>';
            		}
            		$('#'+_uuid).find('.classList').html(_html);
//            		$('#'+_uuid).find('.classList').on('click','li',function(){
//            			var id = $(this).data('id');
//            			if(id)
//            				window.open(rootPath + '/sysConfigItem/selectDetail/' + id,'_blank');
//            		});
            	});
            	
            	this.ajax('/sysConfigItem/getItems',{ids:dataInfo.itemOneIdStrs},function(result){
            		var item,itemHtml='';
            		
            		if(result && result.length > 0){
            			for(var i=0; i<result.length; i++){
            				item = result[i];
            				itemHtml += ('<span data-id="'+ item.id +'">'+ item.itemName +'</span>');
            				
            			}
            			$('#'+_uuid).find('.classTitle').html(itemHtml);
            		}else{
            			$('#'+_uuid).find('.classTitle').html('');
            		}
            		$('#'+_uuid).find('.moreBut').on('click','button',function(){
            			var itemOneId = $('#'+_uuid).find('.classTitle span').eq(0).data('id');
                		window.open(rootPath + '/sysConfigItem/showList' + (itemOneId?'?itemOneId='+itemOneId:''), '_blank');
                	});
            	});
            },
            getVipHtml : function(v,cmc,clc){
            	var _html = '',
            		c;
            	//通过购买成为会员 && 允许前台购买或累计消费晋升会员
            	if(cmc.becomeMember == 0 && v.openWay == 0 && v.cmld){
            		_html = '<ul class="clear">';
            		for(var i=0; i< v.cmld.length ; i++){
            			if(i > 3)
            				break;
            			c = v.cmld[i];
            			_html += '<li class="price fl">'+
		                			'<b>￥</b> <strong>'+c.price+'</strong>'+
		                			'<em>/'+c.name+'</em>'+
		                		 '</li>';
            		}
            		_html += '</ul>';
            	}
            	//通过购买成为会员 && 只允许机构后台开通
            	if(cmc.becomeMember == 0 && v.openWay == 1){
            		_html = '<ul class="clear">';
            		_html += '<li class="openTip fl">（请联系管理员开通会员）</li>';
            		_html += '</ul>';
            	}
            	//通过累计消费成为会员 
            	if(cmc.becomeMember == 1){
            		_html = '<ul class="clear">';
            		_html += '<li class="openTip fl">累计消费'+v.consumption+'元成为此会员'+'</li>';
            		_html += '</ul>';
            	}
            	if(v.openWay == 1){
            		
            	}
            	return _html;
            },
            getIntegalSet : function(cmc,clc){
            	var bili;
            	if(cmc.buyWithIntegral == 1 && clc){
            		bili = clc.exchangeScale;
            	}
            	return bili;
            },
            getOperation : function(log){
            	var data = {},
            		resource = '';
            	if(log.operation){
            		resource = log.operation.split('{dhead}')[1];
            		if(resource && resource.indexOf('{dfoot}') != -1){
            			resource = resource.replace('{dfoot}','');
            		}
            	}
            	data.resource = resource;
            	
            	//购买课程、上直播、录播、评论
            	if(!log.operationType || log.operationType == 1 || log.operationType == 3 || log.operationType == 6){
            		data.resource = data.resource.split('_')[0];
            		data.url = '/sysConfigItem/selectDetail/' + log.sourceId;
            	}
            	//上公开课
            	if(log.operationType == 2){
//            		data.resource = data.resource.split(' ')[0];
            	}
            	//提问和回答
            	if(log.operationType == 4 || log.operationType == 5){
            		data.url = '/askQuestion/myQuestion/' + log.sourceId;
            	}
            	//做题、考试、课后测验、课后作业
//            	if(data.operationType == 7 || data.operationType == 10 || data.operationType == 14 || data.operationType == 15){
//            		var _arr = data.resource.split(':')
//            		data.resource = _arr.length > 1 ? _arr[1] : _arr[0];
//            	}
            	//登录、注册、积分、会员
            	if(log.operationType == 2 || log.operationType == 8 || log.operationType == 9 || log.operationType == 11  ||log.operationType == 7 || log.operationType == 10 || log.operationType == 14 || log.operationType == 15){
            		data.resource = '';
            	}
            	if(log.operationType == 12){
            		data.url = '/companyMemberConfig/toVip';
            	}
            	//课程包
            	if(log.operationType == 13){
            		data.url = '/classPackage/commonty/' + log.sourceId;
            	}
            	
            	switch (log.operationType) {
				case 0:
					data.action = '购买课程';
					return data;
					break;
				case 1:
					data.action = '学习课程';
					return data;
					break;
				case 2:
					data.action = '参加公开课';
					return data;
					break;
					
				case 3:
					data.action = '学习课程';
					return data;
					break;
					
				case 4:
					data.action = '发布问题';
					return data;
					break;
				case 5:
					data.action = '回答问题';
					return data;
					break;
					
				case 6:
					data.action = '点评课程';
					return data;
					break;
					
				case 7:
					data.action = '题库答题';
					return data;
					break;
				case 8:
					data.action = '登录网校';
					return data;
					break;
				case 9:
					data.action = '注册账号';
					return data;
					break;
					
				case 10:
					data.action = '参加考试';
					return data;
					break;
					
				case 11:
					data.action = '购买积分';
					return data;
					break;
				case 12:
					data.action = '购买会员';
					return data;
					break;
					
				case 13:
					data.action = '购买课程包';
					return data;
					break;
				case 14:
					data.action = '完成课后测验';
					return data;
					break;
				case 15:
					data.action = '完成课后作业';
					return data;
					break;
				default:
					break;
				}
            },
            getTime : function(date){
            	var now = new Date(),
            		s = 1000,	//秒
            		m = 60*s,	//分
            		h = 60*m,	//小时
            		day = 24*h, //天
            		mon = 30*day,  //月
            		diff;	//日期毫秒差
            	
            	diff = now.getTime() - date;
            	
            	if(diff > mon){
            		return dateToStr('yyyy-MM-dd',date);
            	}
            	if(diff > day){
            		return parseInt(diff/day) + '天前';
            	}
            	if(diff > h){
            		return parseInt(diff/h) + '小时前';
            	}
            	if(diff > m){
            		return parseInt(diff/m) + '分钟前';
            	}
            	return parseInt(diff/s) + '秒前';
            },
            getStarHtml : function (max,star){
    			var i, j,
    				h1 = '',h2 = '';
    		
    			for(i = 0; i < star ; i++){
    				h1 += '<i class="iconfont">&#xe607;</i>';
    			}
    			for(j = 0; j < max-star ; j++){
    				h2 += '<i class="iconfont">&#xe606;</i>';
    			}
    			return h1 + h2 + '<em>'+ star +'分</em>';
    		},
    		formatTeaher : function (item){
    			if(!item.headpicUrl) item.headpicUrl = rp + '/images/teacher.png';
    			if(item.name && item.name.length>6) item.name = item.name.substring(0,6)+'...';
    			if(item.resume){
    				item.resume.length > 28 ? item.resume = item.resume.substring(0,28) + '...' : item.resume = item.resume;
    			}else{
    				item.resume = '暂无简介';
    			}
    			if(!item.score) item.score = 0;
    			return item;
    		},
    		getDj : function(que){
    			var _html = '<i class="iconfont nofinish"></i>';
    			if(que.topFlag){
    				_html = '<i class="ding"></i>';
    				return _html;
    			}
    			if(que.topFlag){
    				_html = '<i class="jing"></i>';
    				return _html;
    			}
    			return _html;
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
