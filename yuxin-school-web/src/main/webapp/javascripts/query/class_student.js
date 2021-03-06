$(document).ready(function() {
	var myDate = new Date();
	findClassStu(0, myDate.getFullYear());
	
	
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
	
	
})

var nowClass = 0;
var headArr = null;
var bodyArr = null;

function findClassStu(page, year) {
	$("#paginationStuList").html('');
	$.ajax({
		url : rootPath + "/query/learningDetails/queryStudentsList",
		data : {
			"page" : page,
			"pageSize" : $("#selectCounts").val() || 10,
			"eduSchool" : $("#eduSchool").val(),
			"eduStep" : $('#eduStep2').val(),
			"eduYear" : $('#eduYear2').val() || year,
			"eduClass" : $('#eduClass2').val(),
			"liveFlag" : $('#liveFlag').val(),
			"subject" : $('#subject').val()
		},
		type : 'post',
		beforeSend : function(XMLHttpRequest) {
			$(".loading").show();
			$(".loading-bg").show();
		},
		success : function(jsonData) {
			// console.log(jsonData);
			if (jsonData.data == null) {
				$('.studentContent').hide();
				$('.studentNo').show();
				$('#paginationStuList').hide();
				$('.tipsWord').hide();
				return;
			}
			var json = jsonData;
			jsonData = jsonData.data;

			if (jsonData.pageFinder.data.length == 0) {
				$('.studentContent').hide();
				$('.studentNo').show();
				$('#paginationStuList').hide();
				$('.tipsWord').hide();
			} else {
				$('.studentContent').show();
				$('.studentNo').hide();
				$('#paginationStuList').show();
				$('.tipsWord').show();
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
			if (eduStep == 'STEP_01') {
				eduStep = '小';
			} else if (eduStep == 'STEP_02') {
				eduStep = '初';
			} else {
				eduStep = '高';
			}

			var stuHtml = "";
			$.each(jsonData.pageFinder.data, function(i, stu) {
				stuHtml += "<tr>";
				stuHtml += '<td>' + (stu.info.name ? stu.info.name : "")
						+ '</td>';
				stuHtml += '<td>' + eduStep + stu.info.eduYear + "年"
						+ stu.info.eduClass + "班" + '</td>';
				stuHtml += '<td>'
						+ (stu.info.countClass != null ? stu.info.countClass
								: "0") + '</td>';
				stuHtml += '<td>'
						+ (stu.info.studyTime != null ? stu.info.studyTime
								: "0") + '</td>';
				stuHtml += "</tr>";
			})
			$("#stuListTbody").html(stuHtml);

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
									bodyArr[parseInt(i / 5) - 1] += "<tr>"
											+ tempHtml + "</tr>";
									tempHtml = "";
								}
								tempHtml += '<td>' + (study == 0 ? '✘' : '√')
										+ '</td>';
							})
					if (tempHtml != '') {
						bodyArr[headArr.length - 1] += "<tr>" + tempHtml
								+ "</tr>";
					}

				})

				$("#classListTbody").html(bodyArr[0]);

			}


			// 分页paginationStuList
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
					findClassStu(pageNo);
				}
			});

			// $('.studentContent').show();
			// $('.studentNo').hide();
			// $('#paginationStuList').show();
			// $('.tipsWord').show();

			/*$("#leftIconBtn").click(function() {
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

				console.log("page = "+nowClass)
				console.log(headArr[nowClass]);
				
			})*/

		},
		complete : function(XMLHttpRequest, textStatus) {
			$(".loading").hide();
			$(".loading-bg").hide();

			// 设置右侧icon的高度
			var tableHeight = $('#stuListTbody').height() + 'px';
			$('.changeIcon').css('height', tableHeight).css('line-height',
					tableHeight).css('margin-top', '77px');
			// $('.classNo').css('height',($('.tableFirst').height()-2)+'px');
			$('.leftIcon').hide();
		}
	});
}
