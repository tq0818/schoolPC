(function($) {
	var hardbingdata = {
		search : function(page) {
			var $this = this;
			$(".user-list").find("table").find("tr:gt(0)").remove();
			var $this = this;
			var data = {};
			data.page = page ? page : 1;
			data.pageSize = $("#selectCounts").val() || 10;
			$
					.ajax({
						url : rootPath + "/companyHardbindData/queryList",
						data : data,
						type : 'post',
						beforeSend : function(XMLHttpRequest) {
							$(".loading").show();
							$(".loading-bg").show();
						},
						success : function(jsonData) {

							if (jsonData.data.length == 0) {
								$(".user-list")
										.find("table")
										.append(
												'<tr><td colspan="10">没有查找到数据</td></tr>');
							}
							$
									.each(
											jsonData.data,
											function(i, stu) {
												$(".user-list")
														.find("table")
														.append(
																'<tr >'
																		+ '<td>'
																		+ (stu.ip ? stu.ip
																				: "")
																		+ '</td>'
																		+ '<td>'
																		+ (stu.macAddress ? stu.macAddress
																				: "")
																		+ '</td>'
																		+ '<td>'
																		+ (stu.hdSerialnumber ? stu.hdSerialnumber
																				: "")
																		+ '<td>'
																		+ (stu.cpuSerialnumber ? stu.cpuSerialnumber
																				: "")
																		+ '</td>'
																		+ '</td>'
																		
																		+ '<td>'
																		+ (stu.domain ? stu.domain
																				: "")
																		+ '</td>'
																		
																		
																		+ '<td class="slink">'
																		+ '<a class="showSignUp" id="'
																		+ (stu.id ? stu.id
																				: "")
																		+ '"  href="javascript:void(0);">删除</a>|'
																		+ '<a class="updateStudentMsg" sid="'
																		+ (stu.id ? stu.id
																				: "")
																		+ '" href="javascript:void(0);">更新</a>'
																		+ '</td>'
																		+ '</tr>');
											});
							$("#pageNo").remove();
							$(".user-list").after(
									'<input type="hidden" id="pageNo" value="'
											+ jsonData.pageNo + '"/>');
							if (jsonData.rowCount > $("#selectCounts").val()) {
								$(".pagination").pagination(jsonData.rowCount,
										{
											next_text : "下一页",
											prev_text : "上一页",
											current_page : jsonData.pageNo - 1,
											link_to : "javascript:void(0)",
											num_display_entries : 8,
											items_per_page : jsonData.pageSize,
											num_edge_entries : 1,
											callback : function(page, jq) {
												var pageNo = page + 1;
												$this.search(pageNo);
											}
										});

							} else {
								$(".pagination").html('');
								// $("#selectCount").css("margin-bottom","").css("margin-bottom","-30px");
							}

						},
						complete : function(XMLHttpRequest, textStatus) {
							$(".loading").hide();
							$(".loading-bg").hide();
						}
					});
		}

	}

	var update = {

		queryStudent : function() {
			$(".user-list").on("click", ".updateStudentMsg", function() {
				update.clearData();
				var id = $(this).attr("sid");
				$.ajax({
					type : 'post',
					url : rootPath + "/companyHardbindData/findById",
					data : {
						"id" : id
					},
					dataType : 'json',
					success : function(jsonData) {
						$('#uId').val(id);
						$('#ip').val(jsonData.ip);
						$('#macAddress').val(jsonData.macAddress);
						$('#hdSerialnumber').val(jsonData.hdSerialnumber);
						$('#cpuSerialnumber').val(jsonData.cpuSerialnumber);
						$('#domain').val(jsonData.domain);
					}
				});
				$(".updateStudentPopup1").show();
				$(".updateStudentPopup").popup("show");
				$(".updateStudentPopup").css("top", "2%");
				$(".colsekuang").hide();

			});
		},
		updateStudent : function() {
			var data = {};
			data.id = $('#uId').val();
			data.ip = $("#ip").val();
			data.macAddress = $("#macAddress").val();
			data.hdSerialnumber = $("#hdSerialnumber").val();
			data.cpuSerialnumber = $("#cpuSerialnumber").val();
			data.domain = $("#domain").val();

			$.ajax({
				type : 'post',
				url : rootPath + "/companyHardbindData/updateItem",
				data : data,
				dataType : 'json',
				success : function(jsonData) {
					if (jsonData == "success") {
						$.msg("修改成功");
						var pageNo = $("#pageNo").val();
						hardbingdata.search(pageNo);
						$(".updateStudentPopup").popup("hide");
						$(".updateStudentPopup1").hide();
						update.clearData();
					}
				}
			});

		},
		clearData : function() {
			$("#updateStudentForm")[0].reset();
		}

	}

	$(document).ready(function() {

		hardbingdata.search();
		update.queryStudent();
		window.update = update;
		window.hardbingdata = hardbingdata;

		$(".addStudent").on('click', function() {
			$(".addStudentPopup1").show();
			$(".addStudentPopup").popup("show");
			$(".addStudentPopup").css("top", "2%");
			$(".colsekuang").hide();
			$('input[name="sip"]').val('');
			$('input[name="smacAddress"]').val('');
			$('input[name="shdSerialnumber"]').val('');
			$('input[name="scpuSerialnumber"]').val('');
			$('input[name="sdomain"]').val('');
			
		});
		$(".canclekuang").on('click', function() {
			$(".addStudentPopup1").hide();
			$(".updateStudentPopup1").hide();
		})

		 $(".user-list").on("click", ".showSignUp", function () {
			 
			 var data = {};
				data.id = $(this).attr('id');
				$.ajax({
					type : 'post',
					url : rootPath + "/companyHardbindData/deleteItem",
					data : data,
					dataType : 'json',
					success : function(jsonData) {
						if (jsonData == "success") {
							$.msg("删除成功");
							var pageNo = $("#pageNo").val();
							hardbingdata.search(pageNo);

						}
					}
				});
		 })
		
	});

})(jQuery)
