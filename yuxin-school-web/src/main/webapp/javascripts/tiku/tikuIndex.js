
(function($) {
	var subjectId;// 初始化科目id
	var subjectNameArray="";// 新增科目名称集合，已逗号分隔
	var Forms = {
		init : function() {
			Forms.loadTikuAjax();
			Forms.loadBtnInfo();
			$("#jia").hide();
			Forms.imgclick();
			Forms.changeIcon();
			
		},
		changeIcon : function(){
			//换一批图标
       		$(".btn-nexticon").click(function(){
       			$(".itemiconone").html("");
       			$("#bigimage").remove();
       			$("#jia").show();
       			Forms.selIcon((parseInt($("#iconpage").val()) + 1),parseInt($("#pagecount").val()));
       		});
		},
		delTiku : function(cvId){
			if(cvId){
				$.ajax({
					url : rootPath+"/tikuCategory/delById",
					type : "post",
					data : {"id":cvId},
					success : function(data){
						if(data == 'success'){
							Forms.loadTikuAjax();
						}
					}
				});
			}else{
				console.log("cvId no found");
				return false;
			}
		},
		editTiku : function(id,subjectNameArray){
			var tikuName = $("#tikuName").val();
			var tikuDesc = $("#tikuDesc").val();
			var itemOneId = $("#itemOneId").val();
			var itemSecondId = $("#itemSecondId").val();
			var iconUrl = $("#picUrl").val();
			var iconId = $("#picId").val();
			var tikuNameH = $("#tikuNameH").val();
			var param = "";
			if(id){
				param+="&id="+id;
			}
			if(tikuName){
				param+="&tikuName="+tikuName;
			}
			if(tikuDesc){
				param+="&tikuDesc="+tikuDesc;
			}
			if(itemOneId){
				param+="&itemOneId="+itemOneId;
			}
			if(itemSecondId){
				param+="&itemSecondId="+itemSecondId;
			}
			param+="&subjectNameArray="+subjectNameArray;
			if(iconUrl){
				param+="&iconUrl="+iconUrl;
			}
			if(iconId){
				param+="&iconBackUrl="+iconId;
			}
			$.ajax({
				url : rootPath+"/tikuCategory/checkTikuName",
				type : "post",
				data : {"tikuName":tikuName},
				success : function(data){
					if(tikuNameH == tikuName){
						data = true;
					}
					if(data){
						$.ajax({
							url : rootPath+"/tikuCategory/addTiku",
							type : "post",
							data :param,
							beforeSend:function(XMLHttpRequest){
					            $(".loading").show();
					            $(".loading-bg").show();
					        },
							success : function(data){
								if(data == 'success'){
									$(".edit-tiku").fadeOut(200,function(){
										$(".add-teacher-room-bg").fadeOut(200);
									});
									$("#subInfo").html("");
									$("#tikuDesc").val("");
									$("#tikuName").val("");
									$("#itemOneId option:first").prop("selected", 'selected');
									$("#itemSecondId option:first").prop("selected", 'selected');
									$("#bigimage").remove();
									subjectNameArray = "";
									
									Forms.loadTikuAjax();
								}
							},
					        complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					        }
						});
					}else{
						$('<div class="c-fa">' + "题库名称不允许重复" + '</div>')
						.appendTo('body').fadeIn(100).delay(
								1000).fadeOut(200, function() {
							$(this).remove();
						});
						return;
					}
				}
				});
		},
		addTiku : function(subjectNameArray){
			var tikuName = $("#tikuName").val();
			var tikuDesc = $("#tikuDesc").val();
			var itemOneId = $("#itemOneId").val();
			var itemSecondId = $("#itemSecondId").val();
			var iconUrl = $("#picUrl").val();
			var iconId = $("#picId").val();
			$.ajax({
				url : rootPath+"/tikuCategory/checkTikuName",
				type : "post",
				data : {"tikuName":tikuName},
				success : function(data){
					if(data){
						$.ajax({
							url : rootPath+"/tikuCategory/addTiku",
							type : "post",
							data : {"tikuName":tikuName,"tikuDesc":tikuDesc,"itemOneId":itemOneId,"itemSecondId":itemSecondId,"subjectNameArray":subjectNameArray,"iconUrl":iconUrl,"delFlag":"0","iconBackUrl":iconId},
							beforeSend:function(XMLHttpRequest){
					            $(".loading").show();
					            $(".loading-bg").show();
					        },
							success : function(data){
								if(data == 'success'){
									
									$(".edit-tiku").fadeOut(200,function(){
										$(".add-teacher-room-bg").fadeOut(200);
									});
									$("#subInfo").html("");
									$("#tikuDesc").val("");
									$("#tikuName").val("");
									$("#itemOneId option:first").prop("selected", 'selected');
									$("#itemSecondId option:first").prop("selected", 'selected');
									$("#bigimage").remove();
									subjectNameArray = "";
									
									Forms.loadTikuAjax();
								}
							},
					        complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					        }
						});
					}else{
						$('<div class="c-fa">' + "题库名称不允许重复" + '</div>')
						.appendTo('body').fadeIn(100).delay(
								1000).fadeOut(200, function() {
							$(this).remove();
						});
						return;
					}
				}
				});
		},
		queryItemSecond : function (id){
			if(id==null){
				$("#itemOneId").find("option").each(function(i){
					if($(this).is(':selected')){
						var cid=$(this).attr("value");
						id=cid;
					}
				});
			}
			$("#itemSecondId").html('');
			$.ajax({
				url : rootPath + "/tikuCategory/queryItemSecond",
				type : "post",
				data : {pid:id},
				dataType : "json",
				success : function(result) {
					if(result.length>0){
						$.each(result,function(i,item){
							if(item.id==$("#twoSecItemId").val()){
								$("#itemSecondId").append("<option selected='selected' value='"+item.id+"'>"+item.itemName+"</option>");
							}else{
								$("#itemSecondId").append("<option value='"+item.id+"'>"+item.itemName+"</option>");
							}
						});
					}
				}
			});
		},
		loadTikuAjax : function(){
			$.ajax({
				url : rootPath+"/tikuCategory/loadTikuAjax",
				type : "post",
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success : function(data){
					$("#info").html("");
					$("#info").html(data);
					//管理
					$(".glTiku").click(function(){
						var cvid = $(this).attr("cvid");
						$.ajax({
							url : rootPath+"/tikuSet/isExSetNew",
							type : "post",
							data : {"cvId":cvid},
							beforeSend:function(XMLHttpRequest){
					            $(".loading").show();
					            $(".loading-bg").show();
					        },
							success : function(data){
								console.log(data);
								if(data == 'fail'){
									location.href=rootPath+"/tikuSet/toSet";
								}else if(data == 'topic'){
									var objform = document.createElement("form");
									document.body.appendChild(objform);
									objform.action = rootPath + "/question/show";
									objform.method = "post";
									
									var cateId = document.createElement("input");
									cateId.type = "hidden";
									objform.appendChild(cateId);
									cateId.value = cvid;
									cateId.name = "categoryId";
									
									objform.submit();
								}else if(data == 'paper'){
									var objform = document.createElement("form");
									document.body.appendChild(objform);
									objform.action = rootPath + "/tikuPaper/toTikuPaper/"+cvid;
									objform.method = "post";

									objform.submit();
								}else if(data == 'exampoint'){
									var objform = document.createElement("form");
									document.body.appendChild(objform);
									objform.action = rootPath + "/tikuChapter/toChapter/" + cvid;
									objform.method = "post";

									objform.submit();
								}else if(data == 'subject'){
									var objform = document.createElement("form");
									document.body.appendChild(objform);
									objform.action = rootPath + "/tikuSet/toTikuSubManage/" + cvid;
									objform.method = "post";

									objform.submit();
								}
							},
					        complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					        }
						});
					});
					//鼠标悬浮显示操作按钮
					$(".block").hover(function(){
						$(this).find(".manageGL").show();
					},function(){
						$(this).find(".manageGL").hide();
					});
					//禁用
					$(".jinYong").click(function(){
						var tikuId = $(this).attr("tikuId");
						Forms.isDisabled(tikuId,"1");
					});
					
					//启用
					$(".qiYong").click(function(){
						var tikuId = $(this).attr("tikuId");
						Forms.isDisabled(tikuId,"0");
					});
					
					// 添加
					$("#addTiku").click(function(){
						$("#isTikuId").val("");
						Forms.queryItemSecond();
						$(".add-teacher-room-bg").fadeIn(200,function(){
							$(".edit-tiku").fadeIn(200);
						});
					});
					//删除
					$(".delInfo").click(function(){
						var cvId = $(this).attr("tikuId");
						$.ajax({
							url : rootPath+"/tikuCategory/isExTop",
							type : "post",
							data : {"id":cvId},
							success : function(data){
								if(data){
									$.msg("该题库下存在试题信息暂不能删除");
									// $.confirm("该题库下存在试题信息,是否删除？",function(result){
									// 	if(result){
									// 		Forms.delTiku(cvId);
									// 	}
									// })
								}else{
									$.confirm("是否删除该题库？",function(result){
										if(result){
											Forms.delTiku(cvId);
										}
									})
								}
							}
						});
						
					});
					// 编辑
					$(".editInfo").click(function(){
						var tikuId = $(this).attr("tikuId");
						$("#isTikuId").val(tikuId);
						$.ajax({
							url : rootPath+"/tikuCategory/editTikuInfo",
							type : "post",
							data : {"tikuId":tikuId},
							beforeSend:function(XMLHttpRequest){
					            $(".loading").show();
					            $(".loading-bg").show();
					        },
							success : function(data){
								$(".tikuEdit").html("");
								$(".tikuEdit").html(data);
								$("#jia").hide();
								Forms.imgclick();
								Forms.changeIcon();
								$(".add-teacher-room-bg").fadeIn(200,function(){
									$(".edit-tiku").fadeIn(200,function(){
										Forms.loadBtnInfo();
									});
								});
								
							},
					        complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					        }
						});
					});
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		},
		loadBtnInfo : function(){
			// 初始化学科小类
			var itemOneId = $("#itemOneId").val();
			Forms.queryItemSecond(itemOneId);
			
			// 隐藏弹层
			$(".btn-cancel").click(function(){
				$(".edit-tiku").fadeOut(200,function(){
					$(".add-teacher-room-bg").fadeOut(200);
				});
				$("#subInfo").html("");
				$("#tikuDesc").val("");
				$("#tikuName").val("");
				$("#itemOneId option:first").prop("selected", 'selected');
				$("#itemSecondId option:first").prop("selected", 'selected');
				$("#bigimage").remove();
				subjectNameArray = "";
			});
			// 添加题库
			$("#saveTikuBtn").click(function(){
				subjectNameArray = "";
				var isEx = $("#isTikuId").val();
				var name = $("#tikuName").val();
				var picUrl = $("#picUrl").val();
				
				if(name){
					if(!picUrl){
						$('<div class="c-fa">' + "请选择题库的图标" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function() {
							$(this).remove();
						});
						return;
					}
					
					var adds = $(".addSubName");
					if(adds.length > 0){
						//已有科目列表
						var containsItem = adds.parent().children("[class!='addSubName']");
						var contains = new Array();
						$.each(containsItem,function(i,e){
							contains.push($(e).html().replace(/(^\s*)|(\s*$)/g, ""));
						});
						
						$.each(adds,function(i,item){
							var value = $(item).html().replace(/(^\s*)|(\s*$)/g, "");
							if(contains.length > 0 && 
									contains.includes(value)){
								alert("课程名有重复，已自动合并");
								return false;
							}
							//把自己也加入到已存在列表中
							contains.push(value);
							if(i==0){
								subjectNameArray+=value;
							}else{
								subjectNameArray+=","+value;
							}
						});
					}
					
					if(isEx){
						Forms.editTiku(isEx,subjectNameArray);
					}else{
						if(!subjectNameArray){
							$('<div class="c-fa">' + "至少添加一个科目" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function() {
								$(this).remove();
							});
							return;
						}
						Forms.addTiku(subjectNameArray);
					}
				}else{
					$('<div class="c-fa">' + "请输入题库的名称" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function() {
						$(this).remove();
					});
					return;
				}
				
				
			});
			
			$(".addCBtn").find("a").click(function() {
				$(".addCBtn").hide();
				$(".addCConter").show();
			});
			$(".addCConter").find("a").click(
					function() {
						var thisHtml = $(this).html();
						if (thisHtml == "保存") {
							var CName = $("#addSubName").val();
							if (CName) {
								$("#subInfo").append('<li class="addSubName" style="height:16px;padding: 5px 10px;font-size: 14px;">'+CName+'</li>');
								$("#addSubName").val("");
								$(".addCConter").hide();
								$(".addCBtn").show();
								$("#bigimage").remove();
								$("#picUrl").val("");
							} else {
								$('<div class="c-fa">' + "请输入科目的名称" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function() {
										$(this).remove();
									});
								return;
							}
						}
						if (thisHtml == "取消") {

							$("#addSubName").val("");
							$(".addCConter").hide();
							$(".addCBtn").show();
						}
					})
		},
		selIcon : function (page,pagecount){
			//获得页码
			if((page) > pagecount){
				page = 1;
			}
			//获得已有标签id
			$.ajax({
				url : rootPath + "/sysConfigItem/selIconList",
				type:"post",
				data:{"page":page,"pageSize":8},
				dataType:"json",
				success:function(data){
					$("#jia").hide();
					$.each(data.msg.iconList,function(index,item){
						if(index <= 3){
							$(".itemiconone:eq(0)").append("<span class='item-icons' style='width:60px;height:30px;'><img src='http://"+ $("#imgurl").val() + "/" + item.iconBackUrl + "' width='20px' picId='" + item.id + "' title='" + item.iconName + "' style='padding:5px;'/></span>");
						}else{
							$(".itemiconone:eq(1)").append("<span class='item-icons' style='width:60px;height:30px;'><img src='http://"+ $("#imgurl").val() + "/" + item.iconBackUrl + "' width='20px' picId='" + item.id + "' title='" + item.iconName + "' style='padding:5px;'/></span>");
						}
					});
					Forms.imgclick();
					$("#iconpage").val(data.msg.nowpage);
					$("#pagecount").val(data.msg.pagecount);
				}
			});
		},
		imgclick : function (){
			$(".item-icons").click(function(){
				$("#bigimage").remove();
				$(this).attr("class","item-icons active");
				$(this).prevAll().attr("class","item-icons");
				$(this).nextAll().attr("class","item-icons");
				var pic = $(this).find("img").attr("src");
				var picId = $(this).find("img").attr("picId");
				console.log(picId);
       			if(typeof(pic) != "undefined"){
           			pic = pic.substring(pic.indexOf("icons"));
       			}else{
       				pic = null;
       			}
       			
       			$("#picUrl").val(pic);
       			$("#picId").val(picId);
				Forms.getAbsPoint($(this)[0]);
			});
		},
		getAbsPoint : function (obj){   
			   var   x,y; 
			   x=obj.offsetLeft;
			   y=obj.offsetTop;
			   /*margin-top:-40px;margin-left:-365px*/
			   $(".itemiconone:eq(0)").after("<p id='bigimage' style='position:absolute;Z-index:1000;margin-top:15px;margin-left:20px'><i class='iconfont' style='color:green;font-size:20px'>&#xe611;</i></p>");
			   $("#bigimage").css({top:(y ),left:(x )}).fadeIn("fast");
		},
		isDisabled : function(tId,delFlag){
			console.log(tId+"======"+delFlag);
			$.ajax({
				url : rootPath + "/tikuCategory/update",
				type : "post",
				data : {
					"id" : tId,
					"delFlag": delFlag
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if(data == 'success'){
						Forms.loadTikuAjax();
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		}
	}
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)