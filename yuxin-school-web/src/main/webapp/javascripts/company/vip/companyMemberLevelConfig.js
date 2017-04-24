/**
 * author zhang.zx 代报考 页面js封装
 */
(function($) {
	
	
	var Form = {};
	Form.init = function() {
		window.queryPrivatePic=this.queryPrivatePic;
		window.queryPublicPic=this.queryPublicPic;
		// 获取简介的数量
		var description = $("#description").val();
		if (description) {
			$(".words").html(description.length);
		}

		var $this = this;
		
		//选择不允许购买或累计消费晋升会员，只内部开通，成为会员方式隐藏
		$(".levelf").change(function(){
			var  val= $( 'input[name="level"]:checked ').val();
			if(val==1){
				$(".becomeMemberBuy").hide();
				$("#consumptionInput").hide();
				
			}else{
				$(".becomeMemberBuy").show();
				$("#consumptionInput").show();
			}
		});
		// 显示隐藏折扣
		$(".radioItem").change(
				function() {
					var quanxian = $(
							'#quanxian input[name="authorities"]:checked ')
							.val();
					if (quanxian == 0) {
						$("#discount").val(0.0);
					} else {
						$("#discount").val('');
					}

				});
		//简介内容输入动态显示字数
		$("#description").keyup(function() {
			var description = $("#description").val();
			if (description.length > 60) {
				$(".words").html(description.length);
				$(".words").css("color", "red");
				$.msg("简介内容输入过长！");
				return;
			} else {
				$(".words").css("color", "");
				$(".words").html(description.length);
			}
		});

		// 判断会员等级名称是否重复
		var nameRepeatFlag = false;
		$("#name")
				.blur(
						function() {
							var method = "add";
							var memberLevelIdHidden = $(".memberLevelIdDiv")
									.data('id');
							if (memberLevelIdHidden != "") {
								method = "update";
							}
							var name = $.trim($("#name").val());
							var datas = {};
							datas.name = name;
							datas.method = method;
							datas.id = memberLevelIdHidden;
							var url = rootPath
									+ "/companyMemberLevelConfig/checkMemberLevelName";
							$.ajax({
								url : url,
								type : "post",
								data : datas,
								success : function(jsonData) {
									if (jsonData) {
										$.msg("会员等级名称不能重复！");
										nameRepeatFlag = true;
										return;
									}
									nameRepeatFlag = false;
								}
							});
						});
		// 修改的时候，如果会员权限折扣为0，隐藏input输入框。
		var quanxian = $('#quanxian input[name="authorities"]:checked ').val();
		if (quanxian == 0||quanxian==undefined) {
			$("#discount").hide();
			$("#discountspan").hide();
		} else {
			$("#discount").show();
			$("#discountspan").show();
		}
		// 显示隐藏会员权限折扣
		$(".radioItem").change(
				function() {
					var quanxian = $(
							'#quanxian input[name="authorities"]:checked ')
							.val();
					if (quanxian == 0) {
						$("#discount").hide();
						$("#discountspan").hide();
					} else {
						$("#discount").show();
						$("#discountspan").show();
					}
				});
		// 跳转到运营-会员课程
		$(".memberClass").on('click', function() {
			window.location.href = rootPath + "/classType/memberClass";
		});

		// 监听保存按钮，点击进行添加
		$(".saveData")
				.on(
						'click',
						function() {
							var openWay;
							// 获取页面中的值
							var name = $.trim($("#name").val());
							if (name == '') {
								$.msg("请输入会员等级名称！");
								return;
							} else {
								if (name.length > 5) {
									$.msg("名称长度不能超过5！");
									return;
								}
							}
							// 判断会员等级名称是否重复,如果为false表示不重复，不return，如果为true，表示重复，return
							if (nameRepeatFlag) {
								$.msg("会员等级名称不能重复！");
								return;
							}
							var openWayVal = $(
									'#open_way input[name="level"]:checked')
									.val();
							if (openWayVal == null) {
								$.msg("请选择晋级方式!");
								return;
							} 

							// 会员权限
							var quanxian = $(
									'#quanxian input[name="authorities"]:checked ')
									.val();
							if (quanxian == null) {
								$.msg("请选择会员权限!");
								return;
							}
							// 如果权限选择免费
							if (quanxian == 0) {
								discount = 0;
							} else {
								// 如果是选中会员课程免费
								var discount = $("#discount").val();
								if (discount == '') {
									$.msg("请输入会员权限折扣！");
									return;
								} else {
									// 判断输入的会员权限折扣是否是数字
									if (!isNaN(discount)) {
										// 判断会员折扣的格式是否是在0~10之间,可包含一位小数点。
										var reg = /^(\d(\.\d)?|10)$/;
										if (reg.test(discount)) {
											discount = $("#discount").val();
										} else {
											$.msg("会员折扣在0~10之间,可包含一位小数点。");
											return;
										}
									} else {
										$.msg("会员权限折扣请输入数字！");
										return;
									}
								}
							}

							var consumption = null;
							//成为会员控制
							var  val= $( 'input[name="level"]:checked ').val();
							var becomeMember =$(".becomeMemberData").data("becomemember");
							var selectInput = $(".addInput"), array = [];
							if(val!=1){
							if (becomeMember == 0) {
								// 判断是否添加了会员等级详细信息
								if (selectInput.length > 0) {
									var flag = 0;
									var repeatFlag = false;
									var arrayLength = [];
									// 小数点前面是5位数字，小数点后面是2位！
									var reg = /^[0-9]{1,5}\.{0,1}[0-9]{0,2}$/;
									$.each(selectInput, function(i, obj) {
														var cmld = $(obj)
																.data();
														cmld.length = $(obj)
																.find(
																		"select option:selected")
																.val();
														arrayLength
																.push(cmld.length);
														cmld.price = $(this)
																.find('input')
																.val();
														cmld.unit = 'money';
														if (cmld.price == '') {
															flag = 2;
															return;
														}
														if (!reg.test(cmld.price)&& flag != 2) {
															flag = 1;
															return;
														}

														cmld.name = $(obj)
																.find(
																		"option:selected")
																.first().text();
														array.push(cmld);
													})
									if (flag == 2) {
										$.msg("请输入售卖价!");
										return;
									}
									if (flag == 1) {
										$.msg("售卖价格式不正确！小数点前面是5位数字，小数点后面是2位！");
										return;
									}

									if (isRepeat(arrayLength) && flag == 0) {
										$.msg("会员详情有效期长度不可以重复！");
										return;
									}
									array = JSON.stringify(array);
								} else {
									$.msg("请添加会员等级详情！");
									return;
								}

							} else {
								// 累积消费金额
								consumption = $("#consumption").val();
								if (consumption == '') {
									$.msg("请输入累积消费金额！");
									return;
								} else {
									if (isNaN(consumption)) {
										$.msg("累积消费金额请输入数字！");
										return;
									}
									if (consumption < 0 || consumption == 0) {
										$.msg("累计消费金额大于0！");
										return;
									} else {
										var reg = /^[1-9]\d*$/;
										if (!reg.test(consumption)) {
											$.msg("累计消费金额请输入正整数！");
											return;
										}
									}
								}
							}
							}

							var ico = $(".set-pic").data("picPath");
							var icoUrl = $(".icoUrl").attr("src");
							// 如果没有上传会员等级图标，进行提醒
							if ((ico == undefined || ico == "") && icoUrl == "") {
								$.msg("请等待或上传会员等级图标！");
								return;
							}else{
								ico=icoUrl;
							}
							// 会员简介
							var description = $("#description").val();
							if (description == '') {
								$.msg("请输入会员简介！");
								return;
							} else {
								if (description.length > 60) {
									$.msg("会员简介输入过长！")
									return;
								}

							}
							// 会员等级编号
							var id = $(".memberLevelIdDiv").data('id');
							var datas = {
								id : id,
								name : name,
								openWay : openWayVal,
								discount : discount,
								discountType:quanxian,
								description : description,
								becomeMember:becomeMember,
								ico : ico,
								consumption : consumption,
								"listCmld" : array
							}
							// 添加操作
							$
									.ajax({
										async : false,
										url : rootPath
												+ "/companyMemberLevelConfig/insertOrUpdateCompanyMemberLevelConfig",
										type : "post",
										data : datas,
										dataType : "json",
										success : function(result) {
											if (result == 1) {
												$.msg("修改成功！");
											}
											if (result == 2) {
												$.msg("添加成功！");
											}
											window.location.href = rootPath
													+ "/companyMemberLevelConfig/vipSet";
										}
									});
						});

		$(".cancel").on(
				'click',
				function() {
					window.location.href = rootPath
							+ "/companyMemberLevelConfig/vipSet";
				});

		// 添加会员等级按钮
		$(".add-vip-btn").on(
				'click',
				function() {
					window.location.href = rootPath
							+ "/companyMemberLevelConfig/addVipLevelUI";
				});

		// 设置图标
		// ////////////////////////////////////////////////////////////////////
		$(".set-pic").on("click.d", function() {
			$this=this;
			//淡入效果
//			$(".select-icon").fadeIn(100);
				$("#chooseDiv").css("display", "block");
				$(".pic-upload").css("display", "none");
				$("#stopDiv").css("display", "block");
			//查询公有云中的图片
			Form.queryPublicPic(1);
		});
		// 会员管理
		$(".c-main").on("click.a", ".show-student", function() {
			$(".add-page-pop").fadeIn(100);
			$("body").addClass("popup-open");
		});
		$(".page-pop-title em").on("click", function() {
			$(".add-page-pop").fadeOut(200);
			$("body").removeClass("popup-open");
		});
		// 课程添加会员
		$(".edt").on("click.b", function() {
			$(".add-page-pop").fadeIn(100);
		});
		$(".priced").on("change keyup keydown keypress", function() {
			var _val = $(this).val();
			$(".pric").val(_val);
		});

		// 添加课程
		$("span.r").on("click.c", function() {
			$(".choice-class-pop").fadeIn(100);
		});
		
		
		//点击关闭弹层框--
		// 弹层处理
	      $('.upload-layer') .on('click','i.close',function(){
	              $('.upload-layer').fadeOut(200,function(){
	                  $('.add-layer-bg').fadeOut(200);
	              });
	          })
	          // 取消
	          .on('click','.btn-cancel',function(){
	              $(this).parents('.pic-upload').fadeOut(200,function(){
	                 // alert('这个仅作示例，为了展示列表')
	              })
	          })
	          .on('click','li.add',function(){
	              $('.pic-upload').fadeIn(200,function(){
	                  //alert('仅作示例，具体根据实际情况自行修改！')
	              })
	          });
		// //////////////////////////////////////////////////////////////////
		//点击切换tab
//		$(".privatePics").on('click',function(){
//			$(".privatePics").addClass("active");
//			$(".publicPics").removeClass("active");
//			$(".icon-content").show();
//			$(".publicPicsContent").hide();
//		});
//		
//		$(".publicPics").on('click',function(){
//			$(".publicPics").addClass("active");
//			$(".privatePics").removeClass("active");
//			$(".icon-content").hide();
//			$(".publicPicsContent").show();
//			Form.queryPublicPic(1);
//		});
	      $(".types").on('click','a.btn',function(){
				var status=$(this).hasClass('btn-success');
				if(!status){
					$(this).addClass('btn-success').siblings('a').removeClass('btn-success');
				}
			});
		// 监听添加按钮，动态添加文本框
		$(".add-btn").on(
				'click',
				function() {
					// 将第一个选中
					$("#consumptionInput").hide();
					$("#consumptionInputContent").hide();
					$(".addInput").show();
					var row = $(".addButtonParent").find(".addInput");
					var html = ' <div class="right_cont_line addInput">'
							+ ' <select>' + '  <option value="1">1个月</option>'
							+ ' <option value="2">2个月</option>'
							+ ' <option value="3">3个月</option>'
							+ ' <option value="4">4个月</option>'
							+ ' <option value="5">5个月</option>'
							+ '<option value="6">6个月</option>'
							+ '<option value="7">7个月</option>'
							+ ' <option value="8">8个月</option>'
							+ ' <option value="9">9个月</option>'
							+ ' <option value="10">10个月</option>'
							+ ' <option value="11">11个月</option>'
							+ ' <option value="12">12个月</option>'
							+ ' <option value="24">24个月</option>'
							+ ' <option value="36">36个月</option>'
							+ ' <option value="-1">终身</option>' + '</select>'
							+ ' <em class="salename">售卖价&nbsp;</em>'
							+ '<input type="text" class="priceInput"/>'
							+ '元&nbsp;'
							+'<span class="bay-way-delete" onclick="deleteSelect(this,null,null)">删除</span>'
							+ ' </div>';
					if (row && row.length > 0) {
						row.last().after(html);
					} else {
						$(".addButtonParent").find(".addSelect").after(html);
					}
				});
	}

	//分页查询私有云pic_tag!=null
	Form.queryPrivatePic=function(page){
//		$("#ulList li").not(":first").remove();
		var url=rootPath+"/companyMemberLevelConfig/queryPrivatePic";
		var data={};
		$.ajax({
			url:url,
			type:"post",
			data : {"page" : page},
			success:function(jsonData){
				$("#tlist").html(jsonData);
			}
		});
	}
	
	
	//分页查询公有云pic_tag==null
	Form.queryPublicPic=function(page){
		var url=rootPath+"/companyMemberLevelConfig/queryPublicPic";
		var data={};
		$.ajax({
			url:url,
			type:"post",
			data : {"page" : page},
			success:function(jsonData){
				$("#tlist").html(jsonData);
			}
		});
	}
	
	$(document).ready(function() {
		Form.init();
	})
})(jQuery)
/**
 * 
 * @param obj
 */
function  deleteSelect(obj,id,detailId){
if(id!=null){
	$.confirm("确定要删除吗？",
			function(a) {
				if (a) {
					var url = rootPath
							+ "/companyMemberLevelConfig/deleteMemberDetail";
					var data = {};
					data.id = detailId;
					$.ajax({
						url : url,
						data : data,
						type : "post",
						beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				          },
						success : function() {
							$.msg("删除成功！");
							$(obj).parent().remove();
						},
						error : function() {
							$.msg("删除失败");
						},
						  complete:function(XMLHttpRequest,textStatus){
							  $(".loading").hide();
					          $(".loading-bg").hide();
					      }
					});
				} else {
					return;
				}
			});
}else{
	$(obj).parent().remove();
}

}
// 启用，禁用会员等级详情
function changeStatus(id, obj) {
	$
			.ajax({
				url : rootPath
						+ "/companyMemberLevelConfig/forbiddenMemberLevel/"
						+ id,
				type : "get",
				dataType : "json",
				 beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			          },
				success : function(result) {
					if (result == 0) {
						$.msg("禁用成功！");
						$(obj).html("启用");
					}
					if (result == 1) {
						$.msg("启用成功！");
						$(obj).html("禁用");
					}
				},
		 		  complete:function(XMLHttpRequest,textStatus){
					  $(".loading").hide();
			          $(".loading-bg").hide();
			      },
				error : function() {
					$.msg("禁用失败");
				}
			});
}

function deleteMemberLevelDetail(detailId, obj) {
	$.confirm("<p>确定要删除吗？删除会导致以下结果：</p><p>升级时不计算剩余，按0计算。</p>",
			function(a) {
				if (a) {
					var url = rootPath
							+ "/companyMemberLevelConfig/deleteMemberDetail";
					var data = {};
					data.id = detailId;
					$.ajax({
						url : url,
						data : data,
						type : "post",
						beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				          },
						success : function() {
							$.msg("删除成功！");
							$(obj).parent().parent().remove();
						},
						error : function() {
							$.msg("删除失败");
						},
						  complete:function(XMLHttpRequest,textStatus){
							  $(".loading").hide();
					          $(".loading-bg").hide();
					      }
					});
				} else {
					return;
				}
			});
}
function deleteCompanyMemberConfigAndDetail(memberId, obj) {
	$
			.confirm(
					"<p>确定要删除吗？删除会导致以下结果:</p><p>1.该等级下会员将不再是会员。</p><p>2.该等级下的所有详情也会删除！</p>",
					function(a) {
						if (a) {
							var url = rootPath
									+ "/companyMemberLevelConfig/deleteCompanyMemberConfigAndDetail/"
									+ memberId;
							var data = {};
							$.ajax({
								url : url,
								data : data,
								type : "post",
								 beforeSend:function(XMLHttpRequest){
							            $(".loading").show();
							            $(".loading-bg").show();
							          },
								success : function() {
									$.msg("删除成功！");
									$(obj).parent().parent().parent().remove();
								},
								 error : function(){
						 			  $.msg("删除失败！");
						 		  },
								  complete:function(XMLHttpRequest,textStatus){
									  $(".loading").hide();
							          $(".loading-bg").hide();
							      }
							});
						}
					});
}

// 判断数组中是否有重复的数据
function isRepeat(arr) {
	var hash = {};
	for ( var i in arr) {
		if (hash[arr[i]]) {
			return true;
		}
		// 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
		hash[arr[i]] = true;
	}
	return false;
}

//点击单选框中的图标要执行的事件
function  setImgUrl(url){
	$(".icoUrl").attr("src",url);
	$(".set-pic").data("picPath",url);
	$('.upload-layer i.close').trigger("click");
}

$(".pic").on("change","#target", function() {
	var theImage = new Image();
	console.log($(this).attr("src"));
	theImage.src = $(this).attr("src");
	 if (theImage.complete) {
		 	sourceHeight = theImage.height;
			sourceWidth = theImage.width;
			$.init(sourceWidth, sourceHeight);
		    } else {
		    	theImage.onload = function () {
		        	sourceHeight = theImage.height;
				sourceWidth = theImage.width;
				$.init(sourceWidth, sourceHeight);
		        };
		    };
	
}); 


//条件查询公司图片库
function queryConditionPics(tab){
	if(!tab){
		$(".types").find("a").each(function(i){
			if($(this).hasClass('btn-success')){
				var t=$(this).attr("marks");
				tab=t;
			}
		});
	}
	if(tab=="privatePic"){
		$("#ulList li:first").show();
		queryPrivatePic(1);
	}else{
		 queryPublicPic(1);
		
	}
}


//选择图片
function savePic(){
		$.ajaxFileUpload({
		url : rootPath+"/companyMemberLevelConfig/upload;"+ window["sessionName"] + "=" + window["sessionId"],
		secureuri : false,// 安全协议
		async : false,
		fileElementId : 'imgData',
		dataType:'json',
		type : "POST",
		success : function() {
			$.msg("上传成功！");
			queryPrivatePic(1);
		},
		 error : function(){
			  $.msg("上传失败！");
		  },
		loadingEle: '#target',
		fileName: 'imgData'
	});
}
