//重写new Array()------------------
Array.prototype.containsKey  = function(_key) {
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
Array.prototype.removeByKey  = function(_key) {
        for (var i = 0; i < this.length; i++) {
            if (this[i].key === _key) {
                this.splice(i, 1);
                return true;
            }
        }

        return false;
};
Array.prototype.put = function(_key, _value) {
//        if(this.containsKey(_key)){
//            this.removeByKey(_key);
        	
//        }
		var flag = true;
		for (var i = 0; i < this.length; i++) {
	        var val = this[i];
	        if (val.key === _key) {
	        	val.value = _value;
	            flag = false;
	            break;
	        }
	    }
		if(flag){
			this.push( {
	            key : _key,
	            value : _value
	        });
		}
};
Array.prototype.keySet = function(){
	var keys = [];
	for(var i=0;i<this.length;i++){
		if(this[i]&&this[i].key){
			keys.push(this[i].key);
		}
	}
	return keys;
};
Array.prototype.valueSet = function(){
	var values = [];
	for(var i=0;i<this.length;i++){
		if(this[i]){
			values.push(this[i].value);
		}
	}
	return values;
};
Array.prototype.containsObj = function(obj){
	for(var i=0;i<this.length;i++){
		if(typeof obj==="boolean"||typeof obj==="number"||typeof obj==="string"||typeof obj==="undefined"){
			if(obj===this[i]){
				return true;
			}
			continue;
		}else if(typeof obj==="object"){
			if(obj.id&&this[i].id){
				return obj.id===this[i].id;
			}else{
				return obj===this[i];
			}
		}else if(typeof obj==="function"){
			return obj===this[i];
		}
	}
	return false;
};
(function($){
    function Model(){
    	this.productMessage 	= $('.productMessage'),			//产品公告
    	this.companyStatistics	= $('.companyStatistics'),		//机构数据统计
    	this.liveCourse			= $('.liveCourse'),				//直播排课
    	this.stuAndOrderPic		= $('.stuAndOrder'),			//学员注册、订单趋势图
    	this.costAndTransfer	= $('.costAndTransfer'),		//费用、确认转账
    	this.comment			= $('.comment'),				//课程评论
    	this.question			= $('.question'),				//未作答问题
    	this.revovery			= $('.revovery');				//教师动态回复
    	this.initStuOption  	= function (dataArr,countArr) {
    		var myChartStu = echarts.init(document.getElementById('stu-qushi')),
	    		option = {
	    			 	tooltip : {
	    			        trigger: 'axis'
	    			    },
	    			    xAxis : [
	    			        {
	    			            type : 'category',
	    			            boundaryGap : false,
	    			            data : dataArr
	    			        }
	    			    ],
	    			    yAxis : [
	    			        {
	    			            type : 'value'
	    			        }
	    			    ],	
	    			    calculable : true,
	    			    series : [
	    			        {
	    			            name:'学员数量',
	    			            type:'line',
	    			            stack: '总量',
	    			            label: {
	    			                normal: {
	    			                    show: true,
	    			                    position: 'top'
	    			                }
	    			            },
	    			            areaStyle: {normal: {}},
	    			            data:countArr
	    			        }
	    			    ]
	    			};
    		myChartStu.setOption(option);
    	},
    	this.initOrderOption = function(key,value){
    		var myChartDan = echarts.init(document.getElementById('dingdan-qushi')),
    			option = {
				    tooltip: {
				        trigger: 'axis',
				        position: function (pt) {
				            return [pt[0], '10%'];
				        }
				    },
				    legend: {
				        top: 'bottom',
				        data:['意向']
				    },
				   
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: key
				    },
				    yAxis: {
				        type: 'value',
				        boundaryGap: [0, '100%']
				    },
				   
				    series: [
				        {
				            name:'订单',
				            type:'line',
				            smooth:true,
				            symbol: 'none',
				            sampling: 'average',
				            itemStyle: {
				                normal: {
				                    color: 'rgb(255, 70, 131)'
				                }
				            },
				            areaStyle: {
				                normal: {
				                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
				                        offset: 0,
				                        color: 'rgb(255, 158, 68)'
				                    }, {
				                        offset: 1,
				                        color: 'rgb(255, 70, 131)'
				                    }])
				                }
				            },
				            data: value
				        }
				    ]
				};
    		myChartDan.setOption(option);
    	}
    }
    Model.prototype = {
    		init : function(){
    			this.queryProductMessage();
    			if(this.companyStatistics.length){
    				this.queryCompany();
    				this.queryServiceMessage();
    			}
    			if(this.liveCourse.length) this.queryLiveCourse();
    			if(this.stuAndOrderPic.length){
    				this.queryStu();
    				this.queryOrder();
    			}
    			if(this.costAndTransfer.length){
    				this.queryCostDetail();
    				this.queryTransfer();
    			}
    			if(this.comment.length) this.queryComment();
    			if(this.question.length) this.queryQuestion();
    			if(this.revovery.length) this.queryRevovery();
    			this.event();
    		},
    		ajaxLoad : function(url,dataInfo,func){
    	  		 $.ajax({ 
    	  	 		  type: "post", 
    	  	 		  url : rootPath+url, 
    	  	 		  data: dataInfo,
    	  			  beforeSend:function(XMLHttpRequest){
    	  	            $(".loading").show();
    	  	            $(".loading-bg").show();
    	  	          },
    	  	 		  success: func,
    	  	 		  complete:function(XMLHttpRequest,textStatus){
    	  				  $(".loading").hide();
    	  		          $(".loading-bg").hide();
    	  		      }
    	  	 	  });
    	  	},
    	  	ajax : function(){
    			var arg = Array.prototype.slice.call(arguments),
    				success = arg.length>2?arg[2]:function(){},
    				error = arg.length>3?arg[3]:function(){};
    			$.ajax({ 
    		 		  type: "post",
    		 		  url : rootPath+arg[0], 
    		 		  data: arg[1],
    		 		  dataType: "json",
    		 		  success: success,
    		 		  error : error
    		 	  });
    		},
    		/**
    		 * 产品公告
    		 */
    		queryProductMessage : function(){
    			var _this = this,
    				html  = '';
    			this.ajax('/queryMessage',{limit:3},function(jsonData){
    				$.each(jsonData,function(i,companyOrgMessag){
    					companyOrgMessag = _this.formatePM(companyOrgMessag);
    					html += '<em class="gonggao-h3" data-id="'+ companyOrgMessag.id +'" style="cursor:pointer;">'+
    								'<span class="blue-date" >['+ companyOrgMessag.sendTime +']</span>'+
	    			               '<span class="blue-intro" >'+ companyOrgMessag.title +'</span>'+
	    			            '</em>';
    				});
    				_this.productMessage.find('.laba').after(html);
    			});
    		},
    		/**
    		 * 服务公告
    		 */
    		queryServiceMessage : function(){
    			var _this = this,
					html  = '';
    			this.ajax('/queryMessage',{limit:6},function(jsonData){
    				$.each(jsonData,function(i,obj){
    					html += '<li class="item-mesg-list" data-id="'+ obj.id +'">'+
			                        '<span class="fl">'+ (obj.title.length>19?obj.title.substring(0,19):obj.title) +'</span>'+
			                        '<span class="fr">'+ dateToStr('yyyy-MM-dd',new Date(obj.sendTime)) +'</span>'+
			                    '</li>';
    				});
    				_this.companyStatistics.find('.item-mesg-cont').append(html);
    			});
    		},
    		/**
    		 * 机构数据统计
    		 */
    		queryCompany : function(){
    			var _this = this,
    				company,cms,css,clc,clcn,service,count,
    				$companyLi = this.companyStatistics.find('.onlineSchool'),
    				$liveLi	   = this.companyStatistics.find('.liveService'),
    				$videoLi   = this.companyStatistics.find('.videoService'),
    				$messageLi = this.companyStatistics.find('.messageService'),
    				$emailLi   = this.companyStatistics.find('.emailService');
    			
    			this.ajax('/queryCompanyService',{},function(jsonData){
    				company = jsonData.company;
    				cms		= jsonData.cms;
    				css		= jsonData.css;
    				clc		= jsonData.clc;
    				clcn	= jsonData.clcn;
    				service	= jsonData.serivce;
    				//直播
    				if(!css.liveConcurrent) css.liveConcurrent = 0;
    				if(!css.onlineStudent) css.onlineStudent = 0;
    				if(!css.faceStudent) css.faceStudent = 0;
    				//视频
    				if(!cms.videoFlow) cms.videoFlow = 0;
    				if(!cms.giveVideoFlow) cms.giveVideoFlow = 0;
    				if(!css.videoFlow) css.videoFlow = 0;
    				if(!cms.videoStorage) cms.videoStorage = 0;
    				if(!cms.giveVideoStorage) cms.giveVideoStorage = 0;
    				if(!css.videoStorage) css.videoStorgae = 0;
    				if(!cms.videoEndDate) cms.videoEndDate = cms.giveVideoStorageDate;
    				//短信数量
    				if(!cms.messageTotal) cms.messageTotal = 0;
    				if(!cms.giveMessage) cms.giveMessage = 0;
    				if(!css.messageSend) css.messageSeng = 0;
    				//邮件数量
    				if(!cms.emailTotal) cms.emailTotal = 0;
    				if(!cms.giveEmail) cms.giveEmail = 0;
    				if(!css.emailSend) css.emailSend = 0;
    				
    				//service.serviceVersion == 'ONLINE_COUNT' ? count=css.onlineStudent : count=css.faceStudent;
    				
    				if($companyLi.length){
    					//$companyLi.find('p').eq(1).children().last().html("du");
        				$companyLi.find('p').eq(2).children().last().html(count+'/'+cms.faceStudentAll);
        				$companyLi.find('p').eq(3).children().last().html(dateToStr('yyyy-MM-dd',new Date(company.memberEndDate)));
    				}
    				
    				if($liveLi.length){
    					$liveLi.find('p').eq(1).children().last().html(!clc?0:clc.concurrentMax?clc.concurrentMax:0);
        				$liveLi.find('p').eq(2).children().last().html(css.liveConcurrent?css.liveConcurrent:0);
        				$liveLi.find('p').eq(3).children().last().html(!clcn?0:clcn.concurrentMax?clcn.concurrentMax:0);
    				}
    				
    				if($videoLi.length){
    					$videoLi.find('p').eq(1).children().last().html(css.videoFlow+'/'+(cms.videoFlow + cms.giveVideoFlow)+'G').parent().attr('title',css.videoFlow+'/'+(cms.videoFlow + cms.giveVideoFlow)+'G');
        				$videoLi.find('p').eq(2).children().last().html(css.videoStorage+'/'+(cms.videoStorage + cms.giveVideoStorage)+'G').parent().attr('title',css.videoStorage+'/'+(cms.videoStorage + cms.giveVideoStorage)+'G');
        				$videoLi.find('p').eq(3).children().last().html(dateToStr('yyyy-MM-dd',new Date(cms.videoEndDate)));
    				}
    				
    				if($messageLi.length){
    					$messageLi.find('p').eq(1).children().last().html(cms.messageTotal + cms.giveMessage);
        				$messageLi.find('p').eq(2).children().last().html(css.messageSend);
        				$messageLi.find('p').eq(3).children().last().html(cms.messageTotal + cms.giveMessage - css.messageSend);
    				}
    				
    				if($emailLi.length){
    					$emailLi.find('p').eq(1).children().last().html(cms.emailTotal + cms.giveEmail);
        				$emailLi.find('p').eq(2).children().last().html(css.emailSend);
        				$emailLi.find('p').eq(3).children().last().html(cms.emailTotal + cms.giveEmail - css.emailSend);
    				}
    				
    			});
    		},
    		/**
    		 * 直播课
    		 */
    		queryLiveCourse : function(){
    			var _this = this,
    				html  = '',
    				now   = new Date().getTime(),
    				living= false,					//直播中
    				liveTeacher,paikeTeacher,
    				obj;
    			this.ajax('/classModule/selIndexLiveClass',{},function(jsonData){
    				if(!jsonData.module || !jsonData.module.length){
    					html = '<p class="none-text" style="display:block;">近期无直播课</p>';
    					_this.liveCourse.append(html);
    					return;
    				}
    				liveTeacher  = jsonData.liveTeacher;
    				paikeTeacher = jsonData.paikeTeacher;
    				obj = jsonData.module[0];
    				obj._liveDate = dateToStr('yyyy-MM-dd',new Date(obj.liveDate));
    				obj._liveTime = dateToStr('HH:mm',new Date(obj.liveDate));
    				if(!obj.enrollYetStudents) obj.enrollYetStudents = 0;
    				obj.classType==1 ? obj._classType='直播课' : obj._classType='公开课';
    				living = (now - obj.liveDate)>0 && (now - obj.liveEnd)<0;
    				
    				html = '<div class="zhibo-left">'+
		    		            '<span class="zhibo-text">直播时间</span>'+
		    		            '<span class="zhibo-date">'+ obj._liveDate +'</span>'+
		    		            '<span class="zhibo-time">'+ obj._liveTime+'</span>'+
		    		        '</div>'+
		    		        '<div class="zhibo-type">'+
		    		             '<span class="zhibo-intro"><em>班号：</em>'+ obj.name+'</span>'+
		    		             '<span class="zhibo-intro"><em>学员数量：</em>'+ obj.enrollYetStudents +'</span>'+
		    		             '<span class="zhibo-intro"><em>授课老师：</em>'+ obj.teacherName +'</span>'+
		    		             '<span class="zhibo-intro"><em>课次：</em>'+ obj.lessonName +'</span>'+
		    		             '<span class="zhibo-intro"><em>时长：</em>'+ obj.timeLong +'</span>'+
		    		             '<span class="zhibo-intro"><em>授课类型：</em>'+ obj._classType +'</span>'+
		    		        '</div>'+
		    		        '<div class="zhibo-right" style="width: auto;">'+
		    		        	((liveTeacher && !living) ? '<div class="zhibo-btn btn-left fl"><span class="zhibo-look" data-id="'+ obj.id +'">查看</span><span class="zhibo-change" data-id="'+ obj.id +'">修改</span></div>' : '')+
		    		        	(paikeTeacher ? '<div class="zhibo-btn btn-right fl open sel-resource" style="margin-right:20px;"  data-id="'+ obj.id +'" data-classType="'+ obj.classType +'">管理资料</div>' : '')+
		    		            '<div class="zhibo-btn btn-right fr open come-in-room" data-type="upclass" data-id="'+ obj.id +'" data-classType="'+ obj.classType +'">开始上课</div>'+
		    		        '</div>';
    				_this.liveCourse.append(html);
    				
    				if(!liveTeacher && !paikeTeacher) $('zhibo-right .fl').hide();
    				if(obj.isday == 2) $('zhibo-rigth .btn-right').addClass('L-active');
    			});
    		},
    		/**
    		 * 学员注册统计
    		 */
    		queryStu : function(){
    			var _this = this;
    			//获取过去7天日期
    			var myDate = new Date(); 
				myDate.setDate(myDate.getDate() - 7);
				var dateArray = []; 
				var dateTemp; 
				for (var i = 0; i < 7; i++) {
				    dateTemp = dateToStr('MM-dd',myDate);
				    dateArray.put(dateTemp,0);
				    myDate.setDate(myDate.getDate() + 1);
				}
    			this.ajax('/query/studentChartBySeven',{},function(jsonData){
    				for(var i=0;i<jsonData.length;i++){
    					dateArray.put(jsonData[i].registTime.substring(jsonData[i].registTime.indexOf('-')+1),jsonData[i].num);
    				}
    				_this.initStuOption(dateArray.keySet(),dateArray.valueSet());
    			});
    		},
    		/**
    		 * 订单趋势图
    		 */
    		queryOrder : function(){
    			var _this = this;
    			//获取过去7天日期
    			var myDate = new Date(); 
				myDate.setDate(myDate.getDate() - 7);
				var dateArray = []; 
				var dateTemp; 
				for (var i = 0; i < 7; i++) {
				    dateTemp = dateToStr('yyyy/MM/dd',myDate);
				    dateArray.put(dateTemp,0);
				    myDate.setDate(myDate.getDate() + 1);
				}
    			this.ajax('/studentPayMaster/queryChartBySeven',{},function(jsonData){
    				for(var i=0;i<jsonData.length;i++){
    					dateArray.put(jsonData[i].createTime.replace(/-/g,'/'),jsonData[i].num);
    				}
    			    _this.initOrderOption(dateArray.keySet(),dateArray.valueSet());
    			});
    		},
    		/**
    		 * 费用详情
    		 */
    		queryCostDetail : function(){
    			var _this = this,
					html  = '';
				this.ajax('/query/querylsFeeLast5',{},function(jsonData){
					if(!jsonData || !jsonData.data.length){
						html = '<p class="none-text" style="display:block;">暂无数据</p>';
						_this.costAndTransfer.find('.cost .biaoge-bottom').append(html);
						return;
					}
					$.each(jsonData.data,function(i,obj){
						obj = _this.formateCo(obj);
						html += '<tr>'+
		                            '<td>'+ obj.lname +'</td>'+
		                            '<td>'+ obj.classTypeName +'</td>'+
		                            '<td>'+ obj.payStatusCode +'</td>'+
		                            '<td>'+ obj.cou +'</td>'+
		                        '</tr>';
					});
					_this.costAndTransfer.find('.cost tbody').append(html);
				});
    		},
    		/**
    		 * 确认转账
    		 */
    		queryTransfer : function(){
    			var _this = this,
					html  = '';
				this.ajax('/payOrder/selOrderLast5',{},function(jsonData){
					if(!jsonData || !jsonData.data.length){
						html = '<p class="none-text" style="display:block;">暂无未确认的转账</p>';
						_this.costAndTransfer.find('.transfer .biaoge-bottom').append(html);
						return;
					}
					$.each(jsonData.data,function(i,obj){
						obj = _this.formateTr(obj);
						html += '<tr>'+
		                            '<td>'+ obj.orderNum +'</td>'+
		                            '<td>'+ obj.commodityName +'</td>'+
		                            '<td>'+ obj.payPrice +'</td>'+
		                            '<td style="color: red;">'+ obj.payStatus +'</td>'+
		                        '</tr>';
					});
					_this.costAndTransfer.find('.transfer tbody').append(html);
				});
    		},
    		/**
    		 * 课程评论
    		 */
    		queryComment : function(){
    			var _this = this,
					html  = '';
    			this.ajax('/classModule/manageCommentJsonLast5',{},function(jsonData){
    				if(!jsonData || !jsonData.data.length){
    					html = '<p class="none-text" style="display:block;">无最新课程评论</p>';
    					_this.comment.find('.biaoge-bottom').append(html);
    					return;
    				}
    				$.each(jsonData.data,function(i,obj){
    					obj = _this.formateCM(obj);
    					html += '<tr data-id="'+ obj.id +'" data-tid="'+obj.teacherId+'">'+
		                            '<td>'+ obj.comment +'</td>'+
		                            '<td>'+ obj.createTime +'</td>'+
		                            '<td>'+ obj.classTypeName +'</td>'+
		                            '<td>'+ obj.teacherName +'</td>'+
		                            '<td>'+ obj.score +'</td>'+
		                        '</tr>';
    				});
    				_this.comment.find('tbody').append(html);
    			});
    		},
    		/**
    		 * 最新未作答的问题
    		 */
    		queryQuestion : function(){
    			var _this = this,
					html  = '';
    			this.ajax('/Question/questionAjaxLast5',{},function(jsonData){
    				if(!jsonData || !jsonData.length){
    					html = '<p class="none-text" style="display:block;">无最新作答问题</p>';
    					_this.question.find('.biaoge-bottom').append(html);
    					return;
    				}
    				$.each(jsonData,function(i,obj){
    					obj = _this.formateQe(obj);
    					html += '<tr data-id="'+ obj.id +'">'+
		                            '<td>'+ obj.questionDesc +'</td>'+
		                            '<td>'+ obj.createTime +'</td>'+
		                            '<td>'+ obj.userName +'</td>'+
		                        '</tr>';
    				});
    				_this.question.find('tbody').append(html);
    			});
    		},
    		/**
    		 * 教师动态回复
    		 */
    		queryRevovery : function(){
    			var _this = this,
					html  = '';
				this.ajax('/classModule/manageDynamicsReplayJsonLast5',{},function(jsonData){
					if(!jsonData || !jsonData.length){
						html = '<p class="none-text" style="display:block;">无教师动态回复</p>';
						_this.revovery.find('.biaoge-bottom').append(html);
						return;
					}
					$.each(jsonData,function(i,obj){
						obj = _this.formateRe(obj);
						html += '<tr>'+
		                            '<td>'+ obj.content +'</td>'+
		                            '<td>'+ obj.tContent +'</td>'+
		                            '<td>'+ obj.testDate +'</td>'+
		                            '<td>'+ obj.userName +'</td>'+
		                        '</tr>';
					});
					_this.revovery.find('tbody').append(html);
				});
    		},
    		formateCo : function(stage){
    			stage.lname = stage.stuName || stage.mobile || stage.username;
    			if(stage.lname && stage.lname.length>6) stage.lname = stage.lname.substring(0,7);
    			if(stage.classTypeName && stage.classTypeName.length>10) stage.classTypeName = stage.classTypeName.substring(0,11);
    			stage.applyChannelCode=='CHANNEL_OFFLINE' ? stage.payStatusCode='线下报名' : (stage.applyChannelCode=='CHANNEL_ONLINE'?stage.payStatusCode='线上报名':(stage.applyChannelCode=='CHANNEL_STUDYCARD'?stage.payStatusCode='学习卡兑换':stage.payStatusCode=''));
    			var cou=0;
				if(stage.checkReal && stage.checkReal!=-1){
					cou+=stage.checkReal;
				}
				if(stage.posReal && stage.posReal!=-1){
					cou+=stage.posReal;
				}
				if(stage.cashReal && stage.cashReal!=-1){
					cou+=stage.cashReal;
				}
				if(stage.remitReal && stage.remitReal!=-1){
					cou+=stage.remitReal;
				}
				if(stage.checkReal==-1 || stage.remitReal==-1 || stage.cashReal==-1 || stage.posReal==-1){
					if(stage.stageFee){
						cou+=stage.stageFee;
					}
				}
				stage.cou = cou;
				return stage;
    		},
    		formateTr : function(payOrder){
    			if(payOrder.commodityName && payOrder.commodityName.length>10) payOrder.commodityName = payOrder.commodityName.substring(0,11);
    			if(payOrder.payStatus == 'PAY_NON') payOrder.payStatus = '未支付';
    			if(payOrder.payStatus == 'PAY_SUCCESS') payOrder.payStatus = '支付成功';
    			if(payOrder.payStatus == 'PAY_FAIL') payOrder.payStatus = '支付失败';
    			if(payOrder.payStatus == 'PAY_REMIT') payOrder.payStatus = '汇款中';
    			if(payOrder.payStatus == 'PAY_REMIT_CONFIRM') payOrder.payStatus = '已确认汇款';
    			return payOrder;
    		},
    		formatePM : function(companyOrgMessag){
    			companyOrgMessag.content = decodeURI(companyOrgMessag.content).replace(/<.*?>/g,"").replace(/&nbsp;/ig,'').replace(/<\/?.+?>/g,"").replace(/[\r\n]/g, "");
    			companyOrgMessag.sendTime = dateToStr('MM-dd',new Date(companyOrgMessag.sendTime));
    			if(companyOrgMessag.title && companyOrgMessag.title.length>20){
    				companyOrgMessag.title = companyOrgMessag.title.substring(0,20);
    			}
    			companyOrgMessag.titleAndContent = companyOrgMessag.title + '，' + companyOrgMessag.content;
    			if(companyOrgMessag.titleAndContent.length>30) companyOrgMessag.titleAndContent = companyOrgMessag.titleAndContent.substring(0,31)+'...';
    			return companyOrgMessag;
    		},
    		formateCM : function(teacherCommentVo){
    			if(teacherCommentVo.comment.length > 30) teacherCommentVo.comment =  this.delHtmlTag(teacherCommentVo.comment).substring(0,27)+'...';
    			if(teacherCommentVo.classTypeName && teacherCommentVo.classTypeName.length>10) teacherCommentVo.classTypeName = teacherCommentVo.classTypeName.substring(0,11);
    			if(!teacherCommentVo.classTypeName) teacherCommentVo.classTypeName = '';
    			if(teacherCommentVo.teacherName && teacherCommentVo.teacherName>10) teacherCommentVo.teacherName = teacherCommentVo.teacherName.substring(0,11);
    			if(teacherCommentVo.createTime) teacherCommentVo.createTime = dateToStr('yyyy-MM-dd',new Date(teacherCommentVo.createTime));
    			return teacherCommentVo;
    		},
    		formateQe : function(question){
    			question.questionDesc = question.questionDesc.replace(/<.*?>/g,"");
    			if(question.questionDesc && question.questionDesc.length>30) question.questionDesc = question.questionDesc.substring(0,31);
//    			if(question.userName && question.userName.length>6) question.userName = question.userName.substring(0,7);
    			return question;
    		},
    		formateRe : function(obj){
    			if(obj.content && obj.content.length>30) obj.content = obj.tContent.substring(0,31);
    			if(obj.tContent && obj.tContent.length>20) obj.tContent = obj.tContent.substring(0,20);
    			if(obj.testDate) obj.testDate = dateToStr('yyyy-MM-dd HH:mm:ss',new Date(obj.testDate));
    			if(!obj.userName){
    				obj.userName = '匿名用户';
    			}
    			if(obj.teacherReply && obj.teacherReply.length>4) obj.teacherReply = obj.teacherReply.substring(0,5);
    			return obj;
    		},
    		getUrlParam : function (paraName) {
    			var url = document.location.toString();
    			var arrObj = url.split("?");
    			if (arrObj.length > 1) {
	    			var arrPara = arrObj[1].split("&");
	    			var arr;
	    			for (var i = 0; i < arrPara.length; i++) {
		    			arr = arrPara[i].split("=");
		    			if (arr != null && arr[0] == paraName) {
		    				return arr[1];
		    			}
	    			}
	    			return "";
    			}else {
    				return "";
    			}
    		},
    		delHtmlTag : function (str){
    			return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
    		},
    		event : function(){
    			var _this = this;
    			
    			//产品服务
    			$('.productMessage').on('click','em',function(){
    				var id = $(this).data('id');
    				//查看消息
    				$.ajax({
						url:rootPath+"/company/updateFlag",
						data: {"id":id},
						type:'post',
						success: function(result){
							location.href=rootPath+"/company/detail/"+id;
						}
					});
    			});
    			
    			//消息服务
    			$('.companyStatistics').on('click','.item-mesg-list',function(){
    				var id = $(this).data('id');
    				//查看消息
    				$.ajax({
						url:rootPath+"/company/updateFlag",
						data: {"id":id},
						type:'post',
						success: function(result){
							location.href=rootPath+"/company/detail/"+id;
						}
					});
    			});
    			
    			//资源服务
    			$('.toService').on('click','.list-title,.item-back',function(){
    				var url = $(this).parents('li').data('url');
    				if(url)
    					window.location.href = rootPath + url;
    			});
    			
    			//课程评论点击
    			$('.comment tbody').on('click','tr',function(){
    				var id = $(this).data('id'),
    					tid= $(this).data('tid');
    				if(id)
    				window.location.href = rootPath + '/classModule/comment?i=' + id + '&ti=' + tid;
    			});
    			
    			//未作答问题点击
    			$('.question tbody').on('click','tr',function(){
    				var id = $(this).data('id');
    				if(id)
    				window.location.href = rootPath + '/Question/questionIndex?i=' + id;
    			});
    			
    			//查看和修改
    			$('.liveCourse').on('click','.zhibo-look,.zhibo-change',function(){
    				var type = $(this).hasClass('zhibo-look')?'s':'m',
    					id = $(this).data('id');
    				window.location.href = rootPath + '/classModule/show?t=' + type + '&id=' + id;
    			});
    			
    			//进直播
    			$('.liveCourse').on('click','.come-in-room',function(){
    				var curr = $(this);
    				if($(this).hasClass('L-active')) return;//不是今天的直播不可以进入直播教室
    				
    				if($(this).attr("data-type") == ""){
						return false;
					}else{
						var w = window.open();
						//获得p参数
						$.ajax({
							url:rootPath +"/classModule/checkOut",
							type:"post",
							dataType:"json",
							beforeSend:function(XMLHttpRequest){
					              $(".loading").show();
					              $(".loading-bg").show();
					         },
							success:function(data){
								$(".loading").hide();
					            $(".loading-bg").hide();
					            if(data.result == "success"){
									$.ajax({
										url:rootPath + "/classModule/getParam",
										type:"post",
										data:{"lessonId":curr.attr("data-id"),"types":curr.attr("data-type"),"classType":curr.attr("data-classType")},
										async: true,
										success:function(data){
											if(data.url == "timeno"){
												w.close();
								            	$('<div class="c-fa">'+ "正在进行直播课程的录制课程转码，请半个小时后再进入！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
													$(this).remove();});
											}else if(data.url == "timeno30"){
												w.close();
								            	$('<div class="c-fa">'+ "该课程正在建立手机端支持，30分钟后才能进入！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
													$(this).remove();});
											}else if(data.url == "" || data.url == null || data.url == "undefined"){
												w.close();
								            	$('<div class="c-fa">'+ "未上课或未录制，无法回看！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
													$(this).remove();});
											}else if(data.url == "error"){
												w.close();
								            	$('<div class="c-fa">'+ "回放地址还未生成！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
													$(this).remove();});
											}else{
												var pathurls = encrypt(data.url);
												w.location = rootPath 
													+ "/classModule/liveroom?url=" 
													+ pathurls + "&types=" + data.types 
													+ "&isr=" + data.isr + "&auth=" 
													+ data.auth + "&liveClassType=" 
													+ data.liveClassType + "&isres=" 
													+ data.isres + "&custom=" + data.curr;
											}
										},
										complete:function(XMLHttpRequest,textStatus){
											$(".loading").hide();
								            $(".loading-bg").hide();
								        }
									});
					            }else if(data.result == "outTime"){
									w.close();
					            	$('<div class="c-fa">'+ "直播服务已经过期！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
										$(this).remove();});
									$(".loading").hide();
						            $(".loading-bg").hide();
					            }else if(data.result == "service"){
									w.close();
					            	$('<div class="c-fa">'+ "公司服务已到期，暂时无法上课！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
										$(this).remove();});
									$(".loading").hide();
						            $(".loading-bg").hide();
					            }
							}
						});
					}
    			});
    			//查看资料
    			$('.liveCourse').on('click','.sel-resource',function(){
    				window.location.href = rootPath + '/classModule/show';
    			});
    		}
    };
    /**
     * 页面加载完成后执行
     */
    $(function(){
    	var model = new Model();
    	model.init();
    });
})(jQuery)