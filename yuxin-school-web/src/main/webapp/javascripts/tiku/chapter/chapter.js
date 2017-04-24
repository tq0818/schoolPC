var chapterId;// 章id
var sectionId;//节id
(function($) {
	var subId;// 科目id
	var tikuId;// 题库类别Id
	var cOrder;// 章顺序
	var sOrder;// 节的顺序
	var Forms = {
		init : function() {

			// 拖动排序
			$("#chapterInfo").dragsort({
				dragSelector : "li",
				dragEnd : function() {

					$(".chOrder").each(function(i, item) {
						var sort = $(item).attr("data-itemidx");
						var chapterId = $(item).attr("chapterid");
						Forms.updateChapterOrder(chapterId, sort);
					});
				},
				dragBetween : false,
				placeHolderTemplate : "<li></li>"
			});
			
			$("#sectionInfo").dragsort({
				dragSelector : "li",
				dragEnd : function() {

					$(".secOrder").each(function(i, item) {
						var sort = $(item).attr("data-itemidx");
						var sectionId = $(item).attr("sectionId");
						Forms.updateSectionByOrder(sectionId, sort);
					});
				},
				dragBetween : false,
				placeHolderTemplate : "<li></li>"
			});

			tikuId = $("#tikuId").val();
			// 加载科目
			$("#subList").find("a").each(function(i) {
				if ($(this).hasClass('btn-success')) {
					var id = $(this).attr("subId");
					subId = id;
				}
			}).click(
					function() {
						$(this).addClass("btn-success").siblings(".subject")
								.removeClass("btn-success");
						;
						subId = $(this).attr("subId");
						Forms.loadChapter(subId);
						chapterId = null;
						sectionId = null;
						$("#sectionInfo").html("");
						$("#pointInfo").html("");
					});
			// 添加章
			$(".addCBtn").find("a").click(function() {
				if(subId){
					$(".addCBtn").hide();
					$(".addCConter").show();
				}else{
					$('<div class="c-fa">' + "请先添加科目" + '</div>')
					.appendTo('body').fadeIn(100).delay(
							1000).fadeOut(200, function() {
						$(this).remove();
					});
					return;
				}
				
			});
			$(".addCConter").find("a").click(
					function() {
						var thisHtml = $(this).html();
						if (thisHtml == "保存") {
							var CName = $("#addChapterName").val();
							if (CName) {
								Forms.addChapter(CName);
							} else {
								$('<div class="c-fa">' + "请输入章的名称" + '</div>')
										.appendTo('body').fadeIn(100).delay(
												1000).fadeOut(200, function() {
											$(this).remove();
										});
								return;
							}
						}
						if (thisHtml == "取消") {
							$("#addChapterName").val("");
							$(".addCConter").hide();
							$(".addCBtn").show();
						}
					})
			// 添加节
			$(".addSBtn").find("a").click(
					function() {
						if (chapterId) {
							$(".addSBtn").hide();
							$(".addSConter").show();
						} else {
							$('<div class="c-fa">' + "请选中一章" + '</div>')
									.appendTo('body').fadeIn(100).delay(1000)
									.fadeOut(200, function() {
										$(this).remove();
									});
							return;
						}

					});
			$(".addSConter").find("a").click(
					function() {
						var thisHtml = $(this).html();
						if (thisHtml == "保存") {
							var SName = $("#addSectionName").val();
							if (SName) {
								Forms.addSection(SName);
							} else {
								$('<div class="c-fa">' + "请输入节的名称" + '</div>')
										.appendTo('body').fadeIn(100).delay(
												1000).fadeOut(200, function() {
											$(this).remove();
										});
								return;
							}
						}
						if (thisHtml == "取消") {
							$("#addSectionName").val("");
							$(".addSConter").hide();
							$(".addSBtn").show();
						}
					})
			
			// 添加考点
			$(".addPBtn").find("a").click(
					function() {
						if (sectionId) {
							$(".addPBtn").hide();
							$(".addPConter").show();
						} else {
							$('<div class="c-fa">' + "请选中一节" + '</div>')
									.appendTo('body').fadeIn(100).delay(1000)
									.fadeOut(200, function() {
										$(this).remove();
									});
							return;
						}

					});
			$(".addPConter").find("a").click(
					function() {
						var thisHtml = $(this).html();
						if (thisHtml == "保存") {
							var PName = $("#addPointName").val();
							if (PName) {
								Forms.addExamPoint(PName);
							} else {
								$('<div class="c-fa">' + "请输入考点的名称" + '</div>')
										.appendTo('body').fadeIn(100).delay(
												1000).fadeOut(200, function() {
											$(this).remove();
										});
								return;
							}
						}
						if (thisHtml == "取消") {
							$("#addPointName").val("");
							$(".addPConter").hide();
							$(".addPBtn").show();
						}
					})
			this.loadChapter(subId);
		},
		loadChapter : function(subId) {
			$.ajax({
				url : rootPath + "/tikuChapter/loadChapter",
				type : "post",
				data : {
					"tikuSubjectId" : subId
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					$("#chapterInfo").html("");
					$("#chapterInfo").html(data);

					// 章操作
					$("#chapterInfo").find("li").hover(function(){
						$(this).addClass("liColor");
						if($(this).find(".edit").hasClass("none")){
							$(this).find(".chapterM").show();
						}
					},function(){
						$(this).removeClass("liColor");
						$(this).find(".chapterM").hide();
//						$(this).find(".edit").hide();
						$(this).find(".info").show();
					});
//					$("#chapterInfo").find("li").find(".info").find("a").click(
//							function() {
//								chapterId = $(this).parent(".info").parent(
//										".chOrder").attr("chapterId");
//								Forms.addClassCha(this);
//								Forms.loadSection(chapterId);
//							});
					$("#chapterInfo").find("li").find(".chapterM").find("a")
							.click(
									function() {
										var $this = $(this);
										var mana = $this.attr("ht");
										if (mana == "编辑") {
											$this.parent(".chapterM").hide();
											$this.parent(".chapterM").next(
													".edit").attr("style","display:inherit;margin-top: -44px;height:44px;");
											$this.parent(".chapterM").next(
											".edit").removeClass("none");
											$this.parent(".chapterM").prev(
													".info").addClass("none");
										}
									});
					$("#chapterInfo").find("li").find(".edit").find("a").click(
							function() {
								var $this = $(this);
								var mana = $this.html();
								if (mana == "保存") {
									var newChapterName = $this.prev(".newChapterName")
											.val();
									var chapterId = $this.attr("chapterAddId");
									Forms.updateChapter(chapterId,
											newChapterName);
									
								}
								if (mana == "取消") {
									$this.parent(".edit").hide();
									$this.parent(".edit").addClass("none");
									$this.parent(".edit").prev(".chapterM")
											.prev(".info").show();
									$this.parent(".edit").prev(".chapterM")
											.show();
								}
							});

				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});

			$("#addChapterName").val("");
			$(".addCConter").hide();
			$(".addCBtn").show();
			
			$("#addSectionName").val("");
			$(".addSConter").hide();
			$(".addSBtn").show();
			
			$("#addPointName").val("");
			$(".addPConter").hide();
			$(".addPBtn").show();

			cOrder = Forms.findMaxChapterOrder(subId);
		},
		findMaxChapterOrder : function(subId) {
			$.ajax({
				url : rootPath + "/tikuChapter/getMaxChapterOrder",
				type : "post",
				data : {
					"subId" : subId
				},
				success : function(data) {
					cOrder = data;
				}
			});
		},
		loadSection : function(chapterId) {
			$.ajax({
				url : rootPath + "/tikuSection/loadSection",
				type : "post",
				data : {
					"chapterId" : chapterId
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					$("#sectionInfo").html("");
					$("#sectionInfo").html(data);
					
					// 节操作
					$("#sectionInfo").find("li").hover(function(){
						$(this).addClass("liColor");
						if($(this).find(".edit").hasClass("none")){
							$(this).find(".sectionM").show();
						}
					},function(){
						$(this).removeClass("liColor");
						$(this).find(".sectionM").hide();
//						$(this).find(".edit").hide();
						$(this).find(".info").show();
					});
//					$("#sectionInfo").find("li").find(".info").find("a").click(
//							function() {
//								sectionId = $(this).parent(".info").parent(
//										".secOrder").attr("sectionId");
//								Forms.addClassSec(this);
//								Forms.loadExamPoint(sectionId);
//							});
					$("#sectionInfo").find("li").find(".sectionM").find("a")
							.click(
									function() {
										var $this = $(this);
										var mana = $this.attr("ht");
										if (mana == "编辑") {
											$this.parent(".sectionM").hide();
											$this.parent(".sectionM").next(
													".edit").attr("style","display:inherit;margin-top: -44px;height:44px;");
											$this.parent(".sectionM").next(
											".edit").removeClass("none");
											$this.parent(".sectionM").prev(
													".info").addClass("none");
										}
										if (mana == "删除") {

										}
									});
					$("#sectionInfo").find("li").find(".edit").find("a").click(
							function() {
								var $this = $(this);
								var mana = $this.html();
								if (mana == "保存") {
									var newSectionName = $this.prev(".newSectionName")
											.val();
									var sectionId = $this.attr("sectionAddId");
									Forms.updateSection(sectionId, newSectionName);

								}
								if (mana == "取消") {
									$this.parent(".edit").hide();
									$this.parent(".edit").addClass("none");
									$this.parent(".edit").prev(".sectionM")
											.prev(".info").show();
									$this.parent(".edit").prev(".sectionM")
											.show();
								}
							});

				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});

			$("#addSectionName").val("");
			$(".addSConter").hide();
			$(".addSBtn").show();
			
			$("#addPointName").val("");
			$(".addPConter").hide();
			$(".addPBtn").show();
			
			$("#pointInfo").html("");
			sectionId = null;
			
			Forms.findMaxSectionOrder(chapterId);
		},
		findMaxSectionOrder : function(chapterId) {
			$.ajax({
				url : rootPath + "/tikuSection/getMaxSectionOrder",
				type : "post",
				data : {
					"chapterId" : chapterId
				},
				success : function(data) {
					sOrder = data;
				}
			});
		},
		loadExamPoint : function(sectionId) {
			$.ajax({
				url : rootPath + "/tikuExampoint/loadExamPoint",
				type : "post",
				data : {
					"sectionId" : sectionId
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					$("#pointInfo").html("");
					$("#pointInfo").html(data);
					
					// 考点操作
//					$("#pointInfo").find("li").find(".info").find("a").click(
//							function() {
//								sectionId = $(this).parent(".info").parent(
//										".poOrder").attr("pointId");
//								Forms.loadExamPoint(sectionId);
//							});
					$("#pointInfo").find("li").hover(function(){
						$(this).addClass("liColor");
						if($(this).find(".edit").hasClass("none")){
							$(this).find(".pointM").show();
						}
					},function(){
						$(this).removeClass("liColor");
						$(this).find(".pointM").hide();
//						$(this).find(".edit").hide();
						$(this).find(".info").show();
					});
					$("#pointInfo").find("li").find(".pointM").find("a")
							.click(
									function() {
										var $this = $(this);
										var mana = $this.attr("ht");
										if (mana == "编辑") {
											$this.parent(".pointM").hide();
											$this.parent(".pointM").next(
													".edit").attr("style","display:inherit;margin-top: -44px;height:44px;");
											$this.parent(".pointM").next(
											".edit").removeClass("none");
											$this.parent(".pointM").prev(
													".info").addClass("none");
										}
										if (mana == "删除") {

										}
									});
					$("#pointInfo").find("li").find(".edit").find("a").click(
							function() {
								var $this = $(this);
								var mana = $this.html();
								if (mana == "保存") {
									var newPointName = $this.prev(".newPointName")
											.val();
									var pointId = $this.attr("pointId");
									Forms.updatePoint(pointId, newPointName);

								}
								if (mana == "取消") {
									$this.parent(".edit").hide();
									$this.parent(".edit").addClass("none");
									$this.parent(".edit").prev(".pointM")
											.prev(".info").show();
									$this.parent(".edit").prev(".pointM")
											.show();
								}
							});
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
			$("#addPointName").val("");
			$(".addPConter").hide();
			$(".addPBtn").show();
		},
		delChapter : function(obj) {
			var chId = $(obj).attr("chapterDelId");
			if (chId) {
				$.ajax({
					url : rootPath + "/tikuChapter/isExistsSection",
					type : "post",
					data : {
						"chapterId" : chId
					},
					beforeSend : function(XMLHttpRequest) {
						$(".loading").show();
						$(".loading-bg").show();
					},
					success : function(data) {
						if (!data) {
							$.confirm("是否删除该章？",function(result){
								if(result){
									$.ajax({
										url : rootPath
												+ "/tikuChapter/delChapter",
										type : "post",
										data : {
											"id" : chId
										},
										beforeSend : function(XMLHttpRequest) {
											$(".loading").show();
											$(".loading-bg").show();
										},
										success : function(data) {
											if (data == "success") {
												chapterId = null;
												sectionId = null;
												$("#sectionInfo").html("");
												$("#pointInfo").html("");
												Forms.loadChapter(subId);
											} else {
												alert("error");
												return;
											}
										},
										complete : function(XMLHttpRequest,
												textStatus) {
											$(".loading").hide();
											$(".loading-bg").hide();
										}
									});
								}
							})
						} else {
							$(
									'<div class="c-fa">' + "请先删除章下面存在的节和考点"
											+ '</div>').appendTo('body')
									.fadeIn(100).delay(1000).fadeOut(200,
											function() {
												$(this).remove();
											});
							return;
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						$(".loading").hide();
						$(".loading-bg").hide();
					}
				});

			} else {
				alert("主键不存在");
				return;
			}
		},
		delSection : function(obj) {
			var secId = $(obj).attr("secDelId");
			if (secId) {
				// 查询节下面是否存在考点
				$.ajax({
					url : rootPath + "/tikuSection/isExistsPoint",
					type : "post",
					data : {
						"secId" : secId
					},
					beforeSend : function(XMLHttpRequest) {
						$(".loading").show();
						$(".loading-bg").show();
					},
					success : function(data) {
						if (!data) {
							$.confirm("是否删除该节？",function(result){
								if(result){
									$.ajax({
										url : rootPath + "/tikuSection/delSection",
										type : "post",
										data : {
											"id" : secId
										},
										beforeSend : function(XMLHttpRequest) {
											$(".loading").show();
											$(".loading-bg").show();
										},
										success : function(data) {
											if (data == "success") {
												sectionId = null;
												$("#pointInfo").html("");
												Forms.loadSection(chapterId);
											} else {
												alert("error");
												return;
											}
										},
										complete : function(XMLHttpRequest, textStatus) {
											$(".loading").hide();
											$(".loading-bg").hide();
										}
									});
								}
							})
						} else {
							$(
									'<div class="c-fa">' + "请先删除节下面存在的考点"
											+ '</div>').appendTo('body')
									.fadeIn(100).delay(1000).fadeOut(200,
											function() {
												$(this).remove();
											});
							return;
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						$(".loading").hide();
						$(".loading-bg").hide();
					}
				});
					
			} else {
				alert("主键不存在");
				return;
			}
		},
		delExamPointByChecked : function(obj) {
			var pointId = $(obj).attr("pointId");
			// 查询考点下是否存在子考点,并删除
			if (pointId) {
				$.ajax({
					url : rootPath + "/tikuExampoint/isExixtsChildrenPoint",
					type : "post",
					data : {
						"pointId" : pointId
					},
					beforeSend : function(XMLHttpRequest) {
						$(".loading").show();
						$(".loading-bg").show();
					},
					success : function(data) {
						if(data){
							$.confirm("该考点下存在子考点，是否继续删除？",function(result){
								if(result){
									Forms.delExamPoint(pointId);
								}
							})
						}else{
							$.confirm("是否删除该考点？",function(result){
								if(result){
									Forms.delExamPoint(pointId);
								}
							})
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						$(".loading").hide();
						$(".loading-bg").hide();
					}
				});
			} else {
				alert("主键不存在");
				return;
			}
		},
		delExamPoint : function(pointId) {
			$.ajax({
				url : rootPath + "/tikuExampoint/delExamPoint",
				type : "post",
				data : {
					"pointId" : pointId
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if (data == "success") {
						Forms.loadExamPoint(sectionId);
					} else {
						alert("error");
						return;
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		addChapter : function(chapterName) {
			if (!cOrder) {
				cOrder = 0;
			}
			var newOrder = cOrder + 1;
			$.ajax({
				url : rootPath + "/tikuChapter/add",
				type : "post",
				data : {
					"chapterName" : chapterName,
					"tikuCategoryId" : tikuId,
					"tikuSubjectId" : subId,
					"chapterOrder" : newOrder
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if (data == "success") {
						chapterId = null;
						sectionId = null;
						$("#sectionInfo").html("");
						$("#pointInfo").html("");
						Forms.loadChapter(subId);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		addSection : function(sectionName) {
			if (!sOrder) {
				sOrder = 0;
			}
			var newOrder = sOrder + 1;
			$.ajax({
				url : rootPath + "/tikuSection/add",
				type : "post",
				data : {
					"sectionName" : sectionName,
					"tikuCategoryId" : tikuId,
					"tikuSubjectId" : subId,
					"chapterId" : chapterId,
					"sectionOrder" : newOrder
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if (data == "success") {
						sectionId = null;
						$("#pointInfo").html("");
						Forms.loadSection(chapterId);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		addExamPoint : function(pointName) {
			$.ajax({
				url : rootPath + "/tikuExampoint/add",
				type : "post",
				data : {
					"pointName" : pointName,
					"sectionId" : sectionId,
					"tikuSubjectId" : subId,
					"chapterId" : chapterId,
					"tikuCategoryId" : tikuId
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if (data == "success") {
						Forms.loadExamPoint(sectionId);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		updateChapter : function(id, chapterName) {
			var param = "";
			if (id != null && id != "") {
				param += "&id=" + id;
			}
			if (chapterName != null && chapterName != "") {
				param += "&chapterName=" + chapterName;
			}

			$.ajax({
				url : rootPath + "/tikuChapter/update",
				type : "post",
				data : param,
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if (data == "success") {
						$("#sectionInfo").html("");
						$("#pointInfo").html("");
						chapterId = null;
						sectionId = null;
						Forms.loadChapter(subId);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		updateChapterOrder : function(id, chapterOrder) {
			var param = "";
			if (id != null && id != "") {
				param += "&id=" + id;
			}
			if (chapterOrder != null && chapterOrder != "") {
				param += "&chapterOrder=" + chapterOrder;
			}

			$.ajax({
				url : rootPath + "/tikuChapter/update",
				type : "post",
				data : param,
				success : function(data) {

				}
			});
		},
		updateSection : function(id, sectionName) {
			var param = "";
			if (id != null && id != "") {
				param += "&id=" + id;
			}
			if (sectionName != null && sectionName != "") {
				param += "&sectionName=" + sectionName;
			}
//			if (secSort != null && secSort != "") {
//				param += "&sectionOrder=" + secSort;
//			}
			$.ajax({
				url : rootPath + "/tikuSection/update",
				type : "post",
				data : param,
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if (data == "success") {
						$("#pointInfo").html("");
						sectionId = null;
						Forms.loadSection(chapterId);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		updateSectionByOrder : function(id,sectionOrder){
			var param = "";
			if (id != null && id != "") {
				param += "&id=" + id;
			}
			if (sectionOrder != null && sectionOrder != "") {
				param += "&sectionOrder=" + sectionOrder;
			}
			$.ajax({
				url : rootPath + "/tikuSection/update",
				type : "post",
				data : param,
				success : function(data) {
				}
			});
		},
		updatePoint : function(id, pointName) {
			var param = "";
			if (id != null && id != "") {
				param += "&id=" + id;
			}
			if (pointName != null && pointName != "") {
				param += "&pointName=" + pointName;
			}

			$.ajax({
				url : rootPath + "/tikuExampoint/update",
				type : "post",
				data : param,
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if (data == "success") {
						Forms.loadExamPoint(sectionId);
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		addClassCha : function(obj) {
			$(".chOrder").each(function() {
				$(this).attr("style", "");
			});
			obj.attr("style","background-color: #D6D6D6;");
		},
		addClassSec : function(obj) {
			$(".secOrder").each(function() {
				$(this).attr("style", "");
			});
			obj.attr("style", "background-color: #D6D6D6;");
		}
	}
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)