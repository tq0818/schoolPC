(function($) {
	Date.prototype.Format = function(fmt) { // author: meizz
	  var o = {   
	    "M+" : this.getMonth()+1,                 // 月份
	    "d+" : this.getDate(),                    // 日
	    "h+" : this.getHours(),                   // 小时
	    "m+" : this.getMinutes(),                 // 分
	    "s+" : this.getSeconds(),                 // 秒
	    "q+" : Math.floor((this.getMonth()+3)/3), // 季度
	    "S"  : this.getMilliseconds()             // 毫秒
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}

		var  LiveClass={
				init:function(){
		            var $this = this;
		            // 初始化数据
		            $("#tableMemberDetails").find("table") .append( '<tr><td colspan="9">请输入查询条件。</td></tr>');
		            $this.search();
		            $this.event();
				},
				event:function(){
					var $this = this;
					$("#queryById").on("click", function() {
						// 查询
						 $this.search();
						
					});
					$("#searchs").on("click", function() {
						// 查询2
						$this.searchMemberDetail();
						
					});
					//切换tab页面监听
					$(".live-class-count").on("click",".title-tab span",function(){
				           $(".title-tab span").removeClass("active");
				           $(this).addClass("active");
				           var mark=$(this).attr("mark");
				           $(".count-content").hide();
				           $("."+mark).show();
				       })
					// 导出查询出来的数据
					$("#export").on( 'click',
									function() {
											 if ($("#lession_list").find("tr").eq(1).find("td").length <= 1) {
							                        $.msg("没有数据可以导出");
							                    } else {
							                    	 // 取得要提交的参数  
							                    	var   mobile= $.trim($("#mobile").val());
							                    	var   classLesson=$("#classLesson").val();
							                    	var   classLessonName=$("#classLesson  option:selected").text();
							                    	var   countClassPeoples=$(".peoples").html();
							                    	var  classTypeId=$(".classTypeId").val();
							                    	var watchType = $('#watchType').val();
							                        // 取得要提交页面的URL  
							                        var action = rootPath + "/companyLiveStaticDetail/exportStatistics";
							                        if($('.export-data'))
							                        	$('.export-data').remove();
							                        // 创建Form  
							                        var form = $('<form class="export-data" style="display:none;"></form>');  
							                        // 设置属性  
							                        form.attr('action', action);  
							                        form.attr('method', 'post');  
							                        // form的target属性决定form在哪个页面提交  
							                        // _self -> 当前页面 _blank -> 新页面  
							                        form.attr('target', '_self');  
							                        // 创建Input  
							                        var mobile_input = $('<input type="text" name="mobile" />');  
							                        var classLessionId_input = $('<input type="text" name="classLessionId" />');  
							                        var classTypeId_input = $('<input type="text" name="classTypeId" />');  
							                        var lessonName_input = $('<input type="text" name="lessonName" />');  
							                        var countClassPeoples_input = $('<input type="text" name="countClassPeoples" />');  
							                        mobile_input.attr('value', mobile);  
							                        classLessionId_input.attr('value', classLesson);  
							                        classTypeId_input.attr('value', classTypeId);  
							                        lessonName_input.attr('value', classLessonName);  
							                        countClassPeoples_input.attr('value', countClassPeoples);  
							                        // 附加到Form  
							                        form.append(mobile_input);  
							                        form.append(classLessionId_input);  
							                        form.append(classTypeId_input);  
							                        form.append(lessonName_input);  
							                        form.append(countClassPeoples_input);
							                        if(watchType == 0 || watchType == 1)
								                        form.append('<input type="text" name="watchType" value="'+watchType+'"/>');
							                        $('body').append(form);
							                        // 提交表单  
							                        form.submit();  
							                    }
									});
					$("#exportData").on(
							'click',
							function() {
								 if ($("#tableMemberDetails").find("tr").eq(1).find("td").length <= 1) {
				                        $.msg("没有数据可以导出");
				                    } else {
				                    	 // 取得要提交的参数  
				                    	var   mobile= $.trim($(".mobile_num").val());
				                    	var   nameDetail= $.trim($(".studentName").val());
				                    	var  classTypeId=$(".classTypeId").val();
				                        // 取得要提交页面的URL  
				                        var action = rootPath + "/companyLiveStaticDetail/exportCompanyLiveStaticDetailByStudentNamePhone";
				                        if($('.export-data'))
				                        	$('.export-data').remove();
				                        // 创建Form  
				                        var form = $('<form class="export-data" style="display:none;"></form>');  
				                        // 设置属性  
				                        form.attr('action', action);  
				                        form.attr('method', 'post');  
				                        // form的target属性决定form在哪个页面提交  
				                        // _self -> 当前页面 _blank -> 新页面  
				                        form.attr('target', '_self');  
				                        // 创建Input  
				                        var mobile_input = $('<input type="text" name="mobile" />');  
				                        var name_input = $('<input type="text" name="name" />');  
				                        var classTypeId_input = $('<input type="text" name="classTypeId" />');  
				                        mobile_input.attr('value', mobile);  
				                        name_input.attr('value', nameDetail);  
				                        classTypeId_input.attr('value', classTypeId);  
				                        // 附加到Form  
				                        form.append(mobile_input);  
				                        form.append(name_input);  
				                        form.append(classTypeId_input);  
				                        // 提交表单  
				                        form.submit();  
				                    }
							});
				},
				//搜索---参课人数查询
			    search: function (page) {
			    	 var $this = this;
			            var data = {};
			            data.mobile =  $.trim($("#mobile").val());
			            data.classLessionId = $("#classLesson").val();
			            data.classTypeId=$(".classTypeId").val();
			            data.watchType = $('#watchType').val();
			            data.page = page ? page : 1;
						$.ajax({
							url : rootPath+ "/companyLiveStaticDetail/queryall",
							data : data,
							type : 'post',
							beforeSend:function(XMLHttpRequest){
				  	            $(".loading").show();
				  	            $(".loading-bg").show();
				  	        },
							success : function(jsonData) {
								// 清空table中的数据
								$("#lession_list").find("table").find("tr:gt(0)").remove();
								$(".peoples").html(jsonData.rowCount);
								// 如果jsonData中么有值得话，输出没有找到数据
								 if (jsonData.data.length == 0) {
			                            $("#lession_list").find("table") .append( '<tr><td colspan="7">没有查找到数据</td></tr>');
			                        }
			                        $.each(jsonData.data,function (i, live) {
			                                $("#lession_list") .find("table") .append(
			                                    '<tr>'
		                                		+ '<td>' + (live.lessonName? live.lessonName : '') + '</td>'
			                                    + '<td>' +(live.watchType==0? '看直播' : live.watchType==1? '看回放' : '') + '</td>'
			                                    + '<td>' + (live.inTime?new Date(live.inTime).Format("yyyy-MM-dd hh:mm:ss"): '') + '</td>'
		                                		+ '<td>' + (live.mobile ? live.mobile : '') + '</td>'
			                                    + '<td>' + (live.userName ? live.userName : '') + '</td>'
			                                    + '<td>' + (live.name? live.name : '') + '</td>'
			                                    + '<td>' + (live.email? live.email : '') + '</td>'
			                                    + '</tr>');
			                            });
			                        if (jsonData.rowCount >jsonData.pageSize) {
			                            $(".pagination").pagination(jsonData.rowCount,
			                                {
			                                    next_text: "下一页",
			                                    prev_text: "上一页",
			                                    current_page: jsonData.pageNo - 1,
			                                    link_to: "javascript:void(0)",
			                                    num_display_entries: 8,
			                                    items_per_page: jsonData.pageSize,
			                                    num_edge_entries: 1,
			                                    callback: function (page, jq) {
			                                        var pageNo = page + 1;
			                                        $this.search(pageNo);
			                                    }
			                                });
			                        } else {
			                            $(".pagination").html('');
			                        }
			                    },
			                    complete: function (XMLHttpRequest, textStatus) {
			                        $(".loading").hide();
			                        $(".loading-bg").hide();
			                    }
			                });
			   },
				//搜索---学员上课详情
			    searchMemberDetail: function (page) {
			    	 var $this = this;
			            var data = {};
			            data.mobile = $.trim($("#mobileDetail").val());
			            data.name = $.trim($("#nameDetail").val());
			            if(data.name==''&&data.mobile==''){
			            	alert("请输入查询条件！");
			            	return;
			            }
			            
			            data.classTypeId=$(".classTypeId").val();
			            data.page = page ? page : 1;
						$.ajax({
							url : rootPath+ "/companyLiveStaticDetail/queryAllCompanyLiveStaticDetailByStudentNamePhone",
							data : data,
							type : 'post',
							beforeSend:function(XMLHttpRequest){
				  	            $(".loading").show();
				  	            $(".loading-bg").show();
				  	        },
							success : function(jsonData) {
								//赋值查询条件，防止导出出错
								$(".studentName").val( $.trim($("#nameDetail").val()));
								$(".mobile_num").val( $.trim($("#mobileDetail").val()));
								//学员购买的直播课次数量
								if(jsonData.sumCountClass) $("#stuBuyLessonCount").html(jsonData.sumCountClass);
								//学员学习过的课次数量
								if(jsonData.learnCount) $('.countClass').html(jsonData.learnCount);
								// 清空table中的数据
								$("#tableMemberDetails").find("table").find("tr:gt(0)").remove();
								
								// 如果jsonData中么有值得话，输出没有找到数据
								 if (jsonData.pageFinder.data.length == 0) {
			                            $("#tableMemberDetails").find("table") .append( '<tr><td colspan="8">没有查找到数据</td></tr>');
			                        }
			                        $.each(jsonData.pageFinder.data,function (i, live) {
			                                $("#tableMemberDetails") .find("table") .append(
			                                    '<tr>'
			                                    + '<td>'
			                                    + (live.lessonName ? live.lessonName
			                                        : '')
			                                    + '</td>'
			                                    + '<td>'
			                                    + (live.inTime?new Date(live.inTime).Format("yyyy-MM-dd hh:mm:ss"): '') + '</td>'
			                                    + '<td>'
			                                    + (live.mobile? live.mobile : '') + '</td>'
			                                    + '<td>'
			                                    + (live.userName? live.userName : '') + '</td>'
			                                    + '<td>'
			                                    + (live.nickName? live.nickName : '') + '</td>'
			                                    + '<td>'
			                                    + (live.name? live.name : '') + '</td>'
			                                    + '<td>'
			                                    + (live.email? live.email : '') + '</td>'
			                                    + (live.watchType==0? '看直播' : live.watchType==1? '看回放' : '') + '</td>'
			                                    + '</tr>');
			                            });
			                        if (jsonData.pageFinder.rowCount >jsonData.pageFinder.pageSize) {
			                            $(".paginationdetail").pagination(jsonData.pageFinder.rowCount,
			                                {
			                                    next_text: "下一页",
			                                    prev_text: "上一页",
			                                    current_page: jsonData.pageFinder.pageNo - 1,
			                                    link_to: "javascript:void(0)",
			                                    num_display_entries: 8,
			                                    items_per_page: jsonData.pageFinder.pageSize,
			                                    num_edge_entries: 1,
			                                    callback: function (page, jq) {
			                                        var pageNo = page + 1;
			                                        $this.searchMemberDetail(pageNo);
			                                    }
			                                });
			                        } else {
			                            $(".paginationdetail").html('');
			                        }
			                    },
			                    complete: function (XMLHttpRequest, textStatus) {
			                        $(".loading").hide();
			                        $(".loading-bg").hide();
			                    }
			                });
			   }
				
		}
		window.LiveClass = LiveClass;
	   $(document).ready(function () {
		   LiveClass.init();
	    })
	   
})(jQuery)