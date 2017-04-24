/**
 * 在jQuery加载完成后执行
 * auther:大葱
 */
(function($){
	var Model = {
			html:['<div class="give-points-alert" data-type="registNum">'+
			   		    '<div class="title-box">'+
					        '<div class="tit-font">'+
					            '<span class="t">注册赠送积分</span>'+
					        '</div>'+
					        '<div class="cont-box">'+
					            '<span>注册赠送积分</span>'+
					            '<input type="text"/>积分'+
					        '</div>'+
					        '<div class="btns">'+
					            '<button class="btn-sm btn-primary">保存</button>'+
					            '<button class="btn-sm btn-default">取消</button>'+
					        '</div>'+
					    '</div>'+
					'</div>',
					'<div class="give-points-alert give-points-alert1 " data-type="loginNum">'+
					    '<div class="title-box">'+
					        '<div class="tit-font">'+
					            '<span class="t">登录赠送积分</span>'+
					        '</div>'+
					        '<div class="cont-box">'+
					            '<span class="set-with">登录赠送积分</span>'+
					            '<input type="text"/>积分'+
					        '</div>'+
					        '<div class="cont-box">'+
					            '<span class="set-with">连续登录额外奖励</span>'+
					            '<input type="text"/>积分'+
					        '</div>'+
					        '<div class="btns">'+
					            '<button class="btn-sm btn-primary">保存</button>'+
					            '<button class="btn-sm btn-default">取消</button>'+
					        '</div>'+
					   '</div>'+
					'</div>',
					'<div class="give-points-alert give-points-alert2"  data-type="consumeNum">'+
					    '<div class="title-box">'+
					        '<div class="tit-font">'+
					            '<span class="t">单次消费赠送积分</span>'+
					        '</div>'+
					        '<div class="cont-box">'+
					            '<span>单笔消费</span>'+
					            '<input type="text" class="floatNum"/>元'+
					            '<span>赠送</span>'+
					            '<input type="text"/>积分'+
					            '<p>例：满100元送10积分，如学员单次消费200元则送20积分</p>'+
					        '</div>'+
					        '<div class="btns">'+
					            '<button class="btn-sm btn-primary">保存</button>'+
					            '<button class="btn-sm btn-default">取消</button>'+
					        '</div>'+
					    '</div>'+
					'</div>',
					'<div class="give-points-alert"  data-type="inviteNum">'+
			   		    '<div class="title-box">'+
					        '<div class="tit-font">'+
					            '<span class="t">邀请码赠送积分</span>'+
					        '</div>'+
					        '<div class="cont-box">'+
					            '<span>邀请码赠送积分</span>'+
					            '<input type="text"/>积分'+
					        '</div>'+
					        '<div class="btns">'+
					            '<button class="btn-sm btn-primary">保存</button>'+
					            '<button class="btn-sm btn-default">取消</button>'+
					        '</div>'+
					    '</div>'+
					'</div>',
					'<div class="give-points-alert"  data-type="commentsNum">'+
		   		    '<div class="title-box">'+
				        '<div class="tit-font">'+
				            '<span class="t">课程评论赠送积分</span>'+
				        '</div>'+
				        '<div class="cont-box">'+
				            '<span>课程评论成功赠送积分</span>'+
				            '<input type="text"/>积分'+
				        '</div>'+
				        '<div class="btns">'+
				            '<button class="btn-sm btn-primary">保存</button>'+
				            '<button class="btn-sm btn-default">取消</button>'+
				        '</div>'+
				    '</div>'+
				'</div>',
				'<div class="give-points-alert give-points-alert1 "  data-type="questionNum">'+
				    '<div class="title-box">'+
				        '<div class="tit-font">'+
				            '<span class="t">提问赠送积分</span>'+
				        '</div>'+
				        '<div class="cont-box">'+
				            '<span class="set-with">提问赠送</span>'+
				            '<input type="text"/>积分'+
				        '</div>'+
				        '<div class="cont-box">'+
				            '<span class="set-with">每日最多赠送</span>'+
				            '<input type="text"/>积分'+
				        '</div>'+
				        '<div class="btns">'+
				            '<button class="btn-sm btn-primary">保存</button>'+
				            '<button class="btn-sm btn-default">取消</button>'+
				        '</div>'+
				   '</div>'+
				'</div>',
				'<div class="give-points-alert give-points-alert1"  data-type="answerNum">'+
				    '<div class="title-box">'+
				        '<div class="tit-font">'+
				            '<span class="t">回答问题赠送积分</span>'+
				        '</div>'+
				        '<div class="cont-box">'+
				            '<span class="set-with">回答赠送</span>'+
				            '<input type="text"/>积分'+
				        '</div>'+
				        '<div class="cont-box">'+
				            '<span class="set-with">每日最多赠送</span>'+
				            '<input type="text"/>积分'+
				        '</div>'+
				        '<div class="btns">'+
				            '<button class="btn-sm btn-primary">保存</button>'+
				            '<button class="btn-sm btn-default">取消</button>'+
				        '</div>'+
				   '</div>'+
				'</div>'
				],
			getRegist : function(){
				return  $('table tr').eq(0).find('em').eq(1).html();
			},
			setRegist : function(registNum){
				$('table tr').eq(0).find('em').eq(1).html(registNum);
			},
			getLoginNum : function(){
				var obj = {};
				obj.loginNum = $('table tr').eq(1).find('em').eq(1).html();
				obj.continueLoginNum = $('table tr').eq(2).find('em').eq(1).html();
				return obj;
			},
			setLoginNum : function(loginNum,continueLoginNum){
				$('table tr').eq(1).find('em').eq(1).html(loginNum);
				$('table tr').eq(2).find('em').eq(1).html(continueLoginNum);
			},
			getConsumeNum : function(){
				var obj = {};
				obj.consumeNum= $('table tr').eq(3).find('em').eq(1).html();
				obj.costMoreThen = $('table tr').eq(3).find('em').eq(2).html();
				return obj;
			},
			setConsumeNum : function(consumeNum,costMoreThen){
				$('table tr').eq(3).find('em').eq(1).html(consumeNum);
				$('table tr').eq(3).find('em').eq(2).html(costMoreThen);
			},
			getInviteNum : function(){
				return $('table tr').eq(4).find('em').eq(1).html();
			},
			setInviteNum : function(InviteNum){
				$('table tr').eq(4).find('em').eq(1).html(InviteNum);
			},
			getCommentsNum : function(){
				return $('table tr').eq(5).find('em').eq(1).html();
			},
			setCommentsNum : function(CommentsNum){
				$('table tr').eq(5).find('em').eq(1).html(CommentsNum);
			},
			getQuestionNum : function(){
				var obj = {};
				obj.questionNum = $('table tr').eq(6).find('em').eq(1).html();
				obj.quesMaxNum = $('table tr').eq(6).find('em').eq(2).html();
				return obj;
			},
			setQuestionNum : function(questionNum,quesMaxNum){
				$('table tr').eq(6).find('em').eq(1).html(questionNum);
				$('table tr').eq(6).find('em').eq(2).html(quesMaxNum);
			},
			getAnswerNum : function(){
				var obj = {};
				obj.answerNum = $('table tr').eq(7).find('em').eq(1).html();
				obj.anseMaxNum = $('table tr').eq(7).find('em').eq(2).html();
				return obj;
			},
			setAnswerNum : function(answerNum,anseMaxNum){
				$('table tr').eq(7).find('em').eq(1).html(answerNum);
				$('table tr').eq(7).find('em').eq(2).html(anseMaxNum);
			}
	
		};
		
	   	Model.ajax = function(url,dataInfo,func){
	   		 $.ajax({ 
	   	 		  type: "post", 
	   	 		  url : rootPath+url, 
	   	 		  data: dataInfo,
	   	 		  success: func,
	   	 		  error:function(){
	   	 			  $.msg('操作失败');
	   	 		  }
	   	 	  });
	   	};
	   	Model.init = function(){
	   		this.event();
	   	}
	   	/**
	   	 * 重置或者初始化数据
	   	 */
	   	Model.reSetData = function(){
	   		var cic = {};
	   		cic.buyIntegralSwitch = $(".buyIntegralSwitch").hasClass('open')?buyIntegralSwitch=1:buyIntegralSwitch=0;
    		cic.getIntegralSwitch = $(".getIntegralSwitch").hasClass('open')?getIntegralSwitch=1:getIntegralSwitch=0;
    		cic.registNum = 10;
    		cic.loginNum = 10;
    		cic.continueLoginNum = 10;
    		cic.consumeNum = 10;
    		cic.costMoreThen = 100;
    		cic.inviteNum = 10;
    		cic.commentsNum = 10;
    		cic.questionNum = 5;
    		cic.quesMaxNum = 5;
    		cic.answerNum = 5;
    		cic.anseMaxNum = 5;
    		return cic;
	   	}
	   	Model.getData = function(){
	   		var cic = {},
	   			loginObj = this.getLoginNum(),
	   			consObj = this.getConsumeNum(),
	   			questionObj = this.getQuestionNum(),
	   			answerObj = this.getAnswerNum();
	   		cic.buyIntegralSwitch = $(".buyIntegralSwitch").hasClass('open')?buyIntegralSwitch=1:buyIntegralSwitch=0;
    		cic.getIntegralSwitch = $(".getIntegralSwitch").hasClass('open')?getIntegralSwitch=1:getIntegralSwitch=0;
    		cic.registNum = this.getRegist();
    		cic.loginNum = loginObj.loginNum;
    		cic.continueLoginNum = loginObj.continueLoginNum;
    		cic.consumeNum = consObj.consumeNum;
    		cic.costMoreThen = consObj.costMoreThen;
    		cic.inviteNum = this.getInviteNum();
    		cic.commentsNum = this.getCommentsNum();
    		cic.questionNum = questionObj.questionNum;
    		cic.quesMaxNum = questionObj.quesMaxNum;
    		cic.answerNum = answerObj.answerNum;
    		cic.anseMaxNum = answerObj.anseMaxNum;
    		return cic;
	   	}
	   	//整数验证
	   	Model.checkNum = function(dom){
	   		var num = dom.val(),intNum;
	   		if(!num){
				$.msg("积分不能为空");
			return false;
			}
			intNum = parseInt(num);
			if(isNaN(intNum)){
				$.msg("请填写有效的数字");
				return false;
			}
			dom.val(Math.abs(intNum));
			return true;
	   	}
	   	//小数验证
	   	Model.checkNumFloat = function(dom){
	   		var num = dom.val(),floatNum;
	   		if(!num){
				$.msg("消费不能为空");
			return false;
			}
	   		floatNum = parseFloat(num);
			if(isNaN(floatNum)){
				$.msg("请填写有效的数字");
				return false;
			}
			floatNum = floatNum.toFixed(2);
			dom.val(Math.abs(floatNum));
			if(floatNum<=0){
				$.msg("消费金额必须大于0");
				return false;
			}
			return true;
	   	}
	   	//a<b
	   	Model.checkEveryDayMoreThen = function(a,b){
	   		a = parseInt(a);
	   		b = parseInt(b);
	   		if(a>b){
	   			$.msg('赠送积分不能大于每日获取积分上限');
	   			return false;
	   		}
	   		if(b%a != 0){
	   			$.msg('每日获取积分上限必须是赠送积分的倍数');
	   			return false;
	   		}
	   		return true;
	   	}
	   	Model.event = function(){
	   		var $this = this;
	   		//选中的checkbox 的字体颜色
	        $.each($(".check-wrap input:checkbox[checked]"),function(i,obj){
	        	var inputIndex = $('table input:checkbox').index($(this));
	        	$(this).parents("tr").css("color","#333");
	        	if(inputIndex==1)
	        		$(this).parents("tr").next().css("color","#333");
	        	
	        });
	       
	        //功能开启或者关闭
	        $(".l-btn-status").on('click',function(){
	        	var open=$(this).hasClass("open"),
	        		isBuyIntegralSwitch = $(this).hasClass('buyIntegralSwitch'),
	        		cic,obj,
	        		static_icom=[{icon:"&#xe603",word:"已开启"},{icon:"&#xe604;",word:"已禁用"}],
	        		_this = this;
	        	cic={id:$('table').data('id')};
	        	if(open){
	        		isBuyIntegralSwitch?cic.buyIntegralSwitch=0 : cic.getIntegralSwitch=0;
	        	}else{
	        		if(!isBuyIntegralSwitch){
	        			obj = $this.getData();
	        			for(var i in obj){
	        				cic[i] = obj[i];
	        			}
	        		}
	        		isBuyIntegralSwitch?cic.buyIntegralSwitch=1 : cic.getIntegralSwitch=1;
	        	}
	        	Model.ajax("/companyIntegralConfig/saveOrUpdate",cic,function(result){
        			if(open){
        				$(_this).removeClass("open").addClass("close");
    	                $(_this).find("i").html(static_icom[1].icon);
    	                $(_this).find("em").html(static_icom[1].word);
    	                if(!isBuyIntegralSwitch){
    	                	$(".check-wrap").hide();
    	                	$('.renew').hide();
    	                }
    	                	
    	        	}else{
    	        		$(_this).removeClass("close").addClass("open");
    	                $(_this).find("i").html(static_icom[0].icon);
    	                $(_this).find("em").html(static_icom[0].word);
    	                if(!isBuyIntegralSwitch){
    	                	$(".check-wrap").show();
    	                	$('.renew').show();
    	                }
    	        	}
        			if(!$('table').data('id'))
        				$('table').data('id',result.id);
        		});
	        	
	        });
	        //input勾选事件
	        $("table").on('click','input:checkbox',function(){
	        	var check=$(this).is(':checked'),
	        		type = $(this).data('type'),
	        		dataInfo = {},flag,_this = this,initData,inputIndex;
	        	
	        	check ? flag=1 : flag=0;
	        	if(type=='registFlag')
	        		dataInfo.registFlag = flag;
	        	if(type=='loginFlag')
	        		dataInfo.loginFlag = flag;
	        	if(type=='consumeFlag')
	        		dataInfo.consumeFlag = flag;
	        	if(type=='inviteFlag')
	        		dataInfo.inviteFlag = flag;
	        	if(type=='commentsFlag')
	        		dataInfo.commentsFlag = flag;
	        	if(type=='questionFlag')
	        		dataInfo.questionFlag = flag;
	        	if(type=='answerFlag')
	        		dataInfo.answerFlag = flag;
	        	if($('table').data('id')){
	        		dataInfo.id=$('table').data('id')
	        	}else{
	        		initData = Model.reSetData();
	        		for(var i in initData){
	        			dataInfo[i] = initData.i;
	        		}
	        	}
	        	inputIndex = $('table input:checkbox').index($(this));
	        	Model.ajax("/companyIntegralConfig/saveOrUpdate", dataInfo, function(result){
	        		if(!result)
	        			return;
	        		if(check){
	        			$(_this).parents("tr").css("color","#333");
	        			if(inputIndex==1){
	        				$(_this).parents("tr").next().css("color","#333");
	        			}
		            }else{
		                $(_this).parents("tr").css("color","#ccc");
		                if(inputIndex==1){
	        				$(_this).parents("tr").next().css("color","#ccc");
	        			}
		            }	
	        	});
	        });
	        //重置
	        $('.renew').on('click',function(){
	        	var cic;
	        	$.confirm("您确定要恢复重置吗？",function(ruslt){
	        		if(!ruslt)
	        			return;
	        		cic = Model.reSetData();
		        	Model.ajax("/companyIntegralConfig/saveOrUpdate",cic,function(result){
		        		$this.setRegist(result.registNum);
		        		$this.setLoginNum(result.loginNum,result.continueLoginNum);
		        		$this.setConsumeNum(result.consumeNum,result.costMoreThen);
		        		$this.setInviteNum(result.inviteNum);
		        		$this.setCommentsNum(result.commentsNum);
		        		$this.setQuestionNum(result.questionNum,result.quesMaxNum);
		        		$this.setAnswerNum(result.answerNum,result.anseMaxNum);
		        	});
	        	})
	        });
	        
	        //编辑框弹出
	        $(".check-wrap").on("click","button",function(){
	        	var btnIndex = $(".check-wrap button").index($(this)),html;
	        	html = Model.html[btnIndex];
	        	$('body').append(html);
	        	$(html).data('index',btnIndex);
	            $(".mask-bg").removeClass("none");
	            $(".give-points-alert").data('index',btnIndex);
	            switch (btnIndex) {
				case 0:
					$(".give-points-alert").find('input').val($this.getRegist());
					break;
				case 1:
					var obj = $this.getLoginNum();
					$(".give-points-alert").find('input').eq(0).val(obj.loginNum);
					$(".give-points-alert").find('input').eq(1).val(obj.continueLoginNum);
					break;
				case 2:
					var obj = $this.getConsumeNum();
					$(".give-points-alert").find('input').eq(0).val(obj.costMoreThen);
					$(".give-points-alert").find('input').eq(1).val(obj.consumeNum);
					break;
				case 3:
					$(".give-points-alert").find('input').val($this.getInviteNum());
					break;
				case 4:
					$(".give-points-alert").find('input').val($this.getCommentsNum());
					break;
				case 5:
					var obj = $this.getQuestionNum();
					$(".give-points-alert").find('input').eq(0).val(obj.questionNum);
					$(".give-points-alert").find('input').eq(1).val(obj.quesMaxNum);
					break;
				case 6:
					var obj = $this.getAnswerNum();
					$(".give-points-alert").find('input').eq(0).val(obj.answerNum);
					$(".give-points-alert").find('input').eq(1).val(obj.anseMaxNum);
					break;
				}
	        });
	        
	        //编辑框中的输入框绑定验证数字事件
	        $('body').on('blur','.give-points-alert input',function(){
	        	if($(this).hasClass('floatNum')){
	        		$this.checkNumFloat($(this));
	        	}else{
	        		$this.checkNum($(this));
	        	}
	        });
	        
	        //编辑框保存按钮事件
	        $('body').on('click','.give-points-alert .btn-primary',function(){
	        	var cic = {},btnIndex = $(this).parents(".give-points-alert").data('index'),_this = this,$input1,$input2;
	        	switch (btnIndex) {
				case 0:
					$input1 = $(".give-points-alert").find('input');
					if(!$this.checkNum($input1))
						return;
					cic.registNum = $input1.val();
					break;
				case 1:
					$input1 = $(".give-points-alert").find('input').eq(0);
					if(!$this.checkNum($input1))
						return;
					$input2 = $(".give-points-alert").find('input').eq(1);
					if(!$this.checkNum($input2))
						return;
					cic.loginNum = $input1.val();
					cic.continueLoginNum = $input2.val();
					break;
				case 2:
					$input1 = $(".give-points-alert").find('input').eq(0);
					if(!$this.checkNumFloat($input1))
						return;
					$input2 = $(".give-points-alert").find('input').eq(1);
					if(!$this.checkNum($input2))
						return;
					cic.costMoreThen = $input1.val();
					cic.consumeNum = $input2.val();
					break;
				case 3:
					$input1 = $(".give-points-alert").find('input');
					if(!$this.checkNum($input1))
						return;
					cic.inviteNum = $input1.val();
					break;
				case 4:
					$input1 = $(".give-points-alert").find('input');
					if(!$this.checkNum($input1))
						return;
					cic.commentsNum = $input1.val();
					break;
				case 5:
					$input1 = $(".give-points-alert").find('input').eq(0);
					if(!$this.checkNum($input1))
						return;
					$input2 = $(".give-points-alert").find('input').eq(1);
					if(!$this.checkNum($input2))
						return;
					cic.questionNum = $input1.val();
					cic.quesMaxNum = $input2.val();
					if(!$this.checkEveryDayMoreThen(cic.questionNum,cic.quesMaxNum))
						return;
					break;
				case 6:
					$input1 = $(".give-points-alert").find('input').eq(0);
					if(!$this.checkNum($input1))
						return;
					$input2 = $(".give-points-alert").find('input').eq(1);
					if(!$this.checkNum($input2))
						return;
					cic.answerNum = $input1.val();
					cic.anseMaxNum = $input2.val();
					if(!$this.checkEveryDayMoreThen(cic.answerNum,cic.anseMaxNum))
						return;
					break;
				}
	        	
	        	cic.id = $('table').data('id');
	        	Model.ajax("/companyIntegralConfig/saveOrUpdate",cic,function(result){
	        		if(!result)
	        			return;
	        		switch (btnIndex) {
					case 0:
						$this.setRegist(result.registNum);
						break;
					case 1:
						$this.setLoginNum(result.loginNum,result.continueLoginNum);
						break;
					case 2:
						$this.setConsumeNum(result.consumeNum,result.costMoreThen);
						break;
					case 3:
						$this.setInviteNum(result.inviteNum);
						break;
					case 4:
						$this.setCommentsNum(result.commentsNum);
						break;
					case 5:
						$this.setQuestionNum(result.questionNum,result.quesMaxNum);
						break;
					case 6:
						$this.setAnswerNum(result.answerNum,result.anseMaxNum);
						break;
					}
	        		$(_this).parents('.give-points-alert').remove();
		            $(".mask-bg").addClass("none");
	        		if(!$('table').data('id'))
        				$('table').data('id',result.id);
	        	});
	        });
	        
	        //隐藏编辑
	        $("body").on("click",".btn-default",function(){
	        	$(this).parents('.give-points-alert').remove();
	            $(".mask-bg").addClass("none");
	        });
	        
	        
	   	}
	   	
	   	
	   	
	   	
	   	   	
	/**
	 * 页面加载完成后执行
	 */
	$(function(){
		Model.init();
	});
})(jQuery);