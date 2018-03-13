$(document).ready(function() {

	var myDate = new Date();
	masterFindClassStu(0, myDate.getFullYear());
	$('#classLstMast').click(function() {
		masterFindClassStu(0, myDate.getFullYear());
	});

})

function masterFindClassStu(page, year) {
	$("#paginationStuList").html('');
	var eduYear2;
	var eduClass2;
	var eduStep2;
	var role = $("#role").val();
	if (role == 2) {
		// 班主任
		var eduYearM = $("#eduYearM").val();
		var eduClassM = $("#eduClassM").val();
		var eduStepM = $("#eduStepM").val();
		eduYear2 = eduYearM;
		eduClass2 = eduClassM;
		eduStep2 = eduStepM;
	} else {
		// 校级分校管理员
		console.log($('#eduYear2').val(), $('#eduClass2').val(), $('#eduStep2')
				.val());
		eduYear2 = $('#eduYear2').val() || year;
		eduClass2 = $('#eduClass2').val();
		eduStep2 = $('#eduStep2').val();
	}

	$.ajax({
		url : rootPath + "/query/learningDetails/queryStudentsList",
		data : {
			"page" : page,
			"pageSize" : $("#selectCounts").val() || 10,
			"eduSchool" : $("#eduSchool").val(),
			"eduStep" : eduStep2,
			"eduYear" : eduYear2,
			"eduClass" : eduClass2,
			"liveFlag" : $('#liveFlag').val(),
			"subject" : $('#subject').val()
		},
		type : 'post',
		beforeSend : function(XMLHttpRequest) {
			$(".loading").show();
			$(".loading-bg").show();
		},
		success : function(jsonData) {

			var json = jsonData;
			// console.log(jsonData);
			if (jsonData.data == null) {
				$('.studentContent').hide();
				$('.studentNo').show();
				$('#paginationStuList').hide();
				$('.tipsWord').hide();

				return;
			}

			jsonData = jsonData.data;
			if (jsonData.pageFinder.data.length == 0) {

				$('.studentContent').hide();
				$('.studentNo').show();
				$('#paginationStuList').hide();

			} else {
				$('.studentContent').show();
				$('.studentNo').hide();
				$('#paginationStuList').show();
			}
			if (jsonData.classList.length == 0) {
				// 无课程数据时，显示默认提示
				$('.tableSecond').hide();
				$('.leftIcon').hide();
				$('.rightIcon').hide();
				$('.classNo').css('display', 'inline-block');

			} else {
				$('.tableSecond').show();
				$('.leftIcon').show();
				$('.rightIcon').show();
				$('.classNo').css('display', 'none');
			}

			var eduStep = $('#eduStep2').val();
			//console.log(eduStep);
			if (eduStep2 == 'STEP_01') {
				eduStep = '小';
			} else if (eduStep2 == 'STEP_02') {
				eduStep = '初';
			} else {
				eduStep = '高';
			}

			var stuHtml = "";
			$.each(jsonData.pageFinder.data, function(i, stu) {
				var role = $("#role").val();
				if(role == 2){
                    stuHtml += "<tr>";
                    stuHtml += '<td>' + (stu.info.name ? stu.info.name : "") + '</td>';
                   /* stuHtml += '<td>' + eduStep + stu.info.eduYear + "年" + stu.info.eduClass + "班" + '</td>';*/
                    stuHtml += '<td>' + (stu.info.countClass != null ? stu.info.countClass : "0") + '</td>';
                    stuHtml += '<td>' + (stu.info.studyTime != null ? stu.info.studyTime : "0") + '</td>';
                    stuHtml += "</tr>";
				}else{
                    stuHtml += "<tr>";
                    stuHtml += '<td>' + (stu.info.name ? stu.info.name : "") + '</td>';
                    stuHtml += '<td>' + eduStep + stu.info.eduYear + "年" + stu.info.eduClass + "班" + '</td>';
                    stuHtml += '<td>' + (stu.info.countClass != null ? stu.info.countClass : "0") + '</td>';
                    stuHtml += '<td>' + (stu.info.studyTime != null ? stu.info.studyTime : "0") + '</td>';
                    stuHtml += "</tr>";
				}
			})
			$("#stuListTbody").html(stuHtml);
			// 课程导出用户
			$(".exportExcleCourse").on(
					'click',
					function() {
						// console.log($("#stuListTbody").find('tr'))
						if (!$("#stuListTbody").find('tr').length > 0) {

							$.msg("没有数据可以导出");
						}
						// if
						// ($("#tableClassList").find("tr").eq(1).find("td").length
						// <= 1) {
						//
						// }
						else {
							if (role == 2) {
								$("#eduStepL").val(eduStep2);
								$("#eduYearL").val(eduYear2);
								$("#eduClassL").val(eduClass2);
							} else {
								$("#eduStepL").val($('#eduStep2').val());
								$("#eduYearL").val($('#eduYear2').val());
								$("#eduClassL").val($('#eduClass2').val());
							}
							$("#flagL").val($('#liveFlag').val());
							$("#subjectL").val($('#subject').val());
							$("#eduSchoolL").val($('#eduSchool').val());

                                $(".loading").show();
                                $(".loading-bg").show();

							$("#searchClassList").attr("action",
									rootPath + "/query/exportExcleCourse")
									.submit();
                            setTimeout(function(){
                                $(".loading").hide();
                                $(".loading-bg").hide();
								},500
							);

						}
					})

			// 组装课程head
			var headHtml = "";
			headArr = new Array();
			// 初始化长度小于5，隐藏左侧icon
			if (jsonData.classList.length < 6) {
				$('.leftIcon').hide();
				$('.rightIcon').hide();
			}

			$.each(jsonData.classList, function(i, clas) {
				if (i % 5 == 0 && i > 0) {
					headArr.push(headHtml);
					headHtml = "";
				}
				headHtml += "<th title=" + '"'
						+ (clas.lesson_name ? clas.lesson_name : "") + '"'
						+ ">" + (clas.lesson_name ? clas.lesson_name : "")
						+ "</th>";
			})
			if (headHtml != '') {
				headArr.push(headHtml);
			}
			$("#className").html(headArr[0]);
			nowClass = 0;

			if (jsonData.classList.length != 0) {
				var classHtml = "";
				bodyArr = new Array();
				for ( var i in headArr) {
					bodyArr.push("");
				}
				$.each(jsonData.pageFinder.data, function(i, cla) {
					var tempHtml = "";
					$.each(cla.list,
							function(i, study) {
								if (i % 5 == 0 && i > 0) {
									bodyArr[parseInt(i / 5) - 1] += "<tr>" + tempHtml + "</tr>";
									tempHtml = "";
								}
								tempHtml += '<td>' + (study == 0 ? '✘' : '√') + '</td>';
							})
					if (tempHtml != '') {
						bodyArr[headArr.length - 1] += "<tr>" + tempHtml
								+ "</tr>";
					}

				})

				$("#classListTbody").html(bodyArr[0]);
			}

			// 分页
			$("#paginationStuList").pagination(json.count, {
				next_text : "下一页",
				prev_text : "上一页",
				current_page : json.page,
				link_to : "javascript:void(0)",
				num_display_entries : 8,
				items_per_page : json.size,
				num_edge_entries : 1,
				callback : function(page, jq) {
					var pageNo = page + 1;
					masterFindClassStu(pageNo);
				}
			});

			// $('.studentContent').show();
			// $('.studentNo').hide();
			// $('#paginationStuList').show();
			// $('.tipsWord').show();

			$("#leftIconBtn").click(function() {
				if (nowClass == 0) {

					return;
				}
				// 隐藏左侧icon
				if (nowClass == 1 || nowClass == 0) {
					$(".leftIcon").hide();
				} else {
					$(".leftIcon").show();
				}
				$(".rightIcon").show();

				nowClass--;
				$("#className").html(headArr[nowClass]);
				$("#classListTbody").html(bodyArr[nowClass]);

			})

			$("#rightIconBtn").click(function() {
				if (nowClass == headArr.length - 1) {
					return;
				}
				// 隐藏右侧icon

				if (nowClass == headArr.length - 2) {
					$(".rightIcon").hide();
				}
				$(".leftIcon").show();

				nowClass++;
				$("#className").html(headArr[nowClass]);
				$("#classListTbody").html(bodyArr[nowClass]);

			})

		},
		complete : function(XMLHttpRequest, textStatus) {
			$(".loading").hide();
			$(".loading-bg").hide();

			// 根据列表的高度设置切换按钮的高度
			var tableHeight = $('#stuListTbody').height() + 'px';
			$('.changeIcon').css('height', tableHeight).css('line-height',
					tableHeight).css('margin-top', '77px');
			$('.leftIcon').hide();
			// $('.classNo').css('height',($('.tableFirst').height()-2)+'px');
		}
	});
}