(function($) {
	var Form = function() {
	}
	Form.prototype = {
		init : function() {
			$.ajax({
				type : "post",
				url : rootPath + "/sysPageHeadFoot/showHeadChoose",
				success : function(result) {
					if(result.length==1){
						$(".select-place").addClass("none");
					}else{
						window.Form.queryDefaultSchool(function(school) {
							try{
								if(isIndex){
									$(".select-place").attr("value", school.id).html(
											school.schoolName+'<i></i>');
								}else{
									$(".select-place").attr("value", school.id).html(
											school.schoolName).css("cursor","default");
								}
							}catch(e){
								$(".select-place").attr("value", school.id).html(
										school.schoolName).css("cursor","default");
							}
						});
					}
				}
			})
			this.querySchoolTitle();
			this.querySchoolIco();
			this.showListDiv();
		},
		
		showListDiv : function(){
			$(".top-menu").on("mouseenter.menu","li.second",function(){
				var _this=$(this);
				var menu_id=$(this).attr("id");
				var mark=$(this).attr("mark");
				if(menu_id=="008"){
					$(".sectitle008").find("#mark_"+menu_id).slideDown(200);
				}else{
					var html="";
					if(mark=="child"){
						html='<ul id="mark_'+$(this).attr("id")+'" class="sedmenu customDiv"></ul>';
					}else{
						html='<ul id="mark_'+$(this).attr("id")+'" class="secmenu customDiv"></ul>';
					}
				$(html).appendTo(".sectitle"+menu_id);
				//清空数据
				$(".customDiv"+menu_id).html('');
				$.ajax({
					type : "post",
					url : rootPath + "/sysPageHeadFoot/showSpecial",
					data : "parentId="+ menu_id,
					success : function(result) {
						if(result.length<=0){
							return;
						}
						var $div=$(".sectitle"+menu_id).find("#mark_"+menu_id);
							$.each(result,function(i,item){
								if(item.openType=="blank"){
									$div.append('<li><a href="'+item.url+'" target="_blank">'+item.name+'</a></li>');
								}else{
									$div.append('<li><a href="'+item.url+'">'+item.name+'</a></li>');
								}
							});
							$div.slideDown(200);
					}
				});
				//TODO 加数据
				$(".sectitle"+menu_id).find(".customDiv").append();
				$(this).find(".sedmenu").css("top",$(this).index()*36);
			 }
			}).on('mouseleave.menu',"li.second",function(){
				var _this=$(this);
				var menu_id=$(this).attr("id");
				if(menu_id=="008"){
					$(".sectitle008").find("#mark_"+menu_id).slideUp(200);
				}else{
					$(".sectitle"+menu_id).find(".customDiv").slideUp(200,function(){
						$(this).remove();
					});
				}
			});
		} ,
		queryDefaultSchool : function(callback) {
			var school = eval('('+getCookie('school')+')');
			if (school) {
				if (callback) {
					callback(school);
				}
			} else {
				$.ajax({
					url : rootPath + "/sysPageHeadFoot/queryDefaultSchool",
					type : "post",
					dataType : "json",
					success : function(school) {
						setCookie('school', JSON.stringify(school));
						setCookie('schoolId',school.id);
						setCookie('schoolName',school.schoolName);
						try{
							if(isIndex){
								$(".select-place").attr("value", school.id).html(
										school.schoolName+'<i></i>');
							}else{
								$(".select-place").attr("value", school.id).html(
										school.schoolName).css("cursor","default");
							}
						}catch(e){
							$(".select-place").attr("value", school.id).html(
									school.schoolName).css("cursor","default");
						}
						//window.Form.querySchoolTitle();
					},
					error : function() {
						
					}
				});
				//showSchoolsList();
			}
		},
		querySchoolIco : function() {
			$.ajax({
				type : "post",
				url : rootPath + "/sysPageHeadFoot/queryIco",
				success : function(result) {
					if(result && result.companyLogoType=="picture" ){
						$(".navbar-brand").addClass("default").removeClass("text");
						$(".navbar-brand").css("background-image","url(" + result.companyLogo + ")");
					}else if(result && result.companyLogoType=="word"){
						$(".navbar-brand").addClass("text").removeClass("default");
						$(".navbar-brand").text(result.companyNameShort==null?'':result.companyNameShort);
					}
					
				}
			});
		},
		closeCss : function(){
			$(".specail_place").css("display","none");
			$(".school_place").css("display","none");
		},
		querySchoolTitle : function() {
			$(".nav-left").html('');
			$.ajax({
				type : "post",
				url : rootPath + "/sysPageHeadFoot/queryHeadContent",
				success : function(result) {
					$.each(result,function(i, item) {
						if(i<=4){
							if(item.pageHeadType=='SYS_PAGE_HEAD_HOME'){
								$(".nav-left").append("<li code='SYS_PAGE_HEAD_HOME'><a href='"+rootPath+"/' id=ts>"+ item.name + "</a></li>");
							}else if(item.pageHeadType=='SYS_PAGE_HEAD_COURSE'){
								$(".nav-left").append("<li code='SYS_PAGE_HEAD_COURSE'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
							}else if(item.pageHeadType=='SYS_PAGE_HEAD_NEWS'){
								$(".nav-left").append("<li code='SYS_PAGE_HEAD_NEWS'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
							}else if(item.pageHeadType=='SYS_PAGE_HEAD_TIKU'){
								$(".nav-left").append("<li code='SYS_PAGE_HEAD_TIKU'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
							}else if(item.pageHeadType=='SYS_PAGE_HEAD_OPENCLASS'){
								$(".nav-left").append("<li code='SYS_PAGE_HEAD_OPENCLASS'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
							}else if(item.pageHeadType=='SYS_PAGE_HEAD_TEACHER'){
								$(".nav-left").append("<li code='SYS_PAGE_HEAD_TEACHER'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
							}else if(item.pageHeadType=='SYS_PAGE_HEAD_OTHER'){
									if(item.count<=0){
										if(item.openType=='blank'){
											 $(".nav-left").append("<li><a href='"+item.url+"' target='_blank'>"+item.name+"</a></li>");
										}else{
											 $(".nav-left").append("<li><a href='"+item.url+"'>"+item.name+"</a></li>");
										}
									}else{
										 $(".nav-left").append("<li id='"+item.id+"' class='second sectitle"+item.id+"'><a href=javascript:;>" + item.name+ "</a></li>");
									}
							}
						}else if(i>=6){
							if(i==6){
								 $(".nav-left").append('<li id="008" class="second sectitle008" style="width:60px;"><a href="javascript:void(0);" style="font-size:15px;"><span style="border-radius:4px;width:4px;height:4px;display: inline-block;background:#fff;margin:0 3px;"></span><span style="border-radius:4px;width:4px;height:4px;display: inline-block;background:#fff;margin:0 3px;"></span><span style="border-radius:4px;width:4px;height:4px;display: inline-block;background:#fff;margin:0 3px;"></span></a></li>');
								 var html='<ul id="mark_008" class="secmenu customDiv008"></ul>';
								 $(html).appendTo(".sectitle008");
							}
								var $div=$(".sectitle008").find("#mark_008");
								if(item.count<=0){
									if(item.openType=='blank'&&item.pageHeadType=='SYS_PAGE_HEAD_OTHER'){
										$div.append('<li><a href="'+item.url+'" target="_blank">'+item.name+'</a></li>');
									}else if(item.openType=='self'&&item.pageHeadType=='SYS_PAGE_HEAD_OTHER'){
										$div.append('<li><a href="'+item.url+'">'+item.name+'</a></li>');
									}else if(item.pageHeadType=='SYS_PAGE_HEAD_NEWS'){
										$div.append("<li code='SYS_PAGE_HEAD_NEWS'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
									}else if(item.pageHeadType=='SYS_PAGE_HEAD_TIKU'){
										$div.append("<li code='SYS_PAGE_HEAD_TIKU'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
									}else if(item.pageHeadType=='SYS_PAGE_HEAD_OPENCLASS'){
										$div.append("<li code='SYS_PAGE_HEAD_OPENCLASS'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
									}else if(item.pageHeadType=='SYS_PAGE_HEAD_TEACHER'){
										$div.append("<li code='SYS_PAGE_HEAD_TEACHER'><a href='"+rootPath+"/"+item.url+ "' id=ts>"+ item.name + "</a></li>");
									}
								}else{
									$div.append("<li id='"+item.id+"' mark='child' class='second sectitle"+item.id+"'><a href=javascript:;>" + item.name+ "</a></li>");
								}
						}
					});
					if(window.loadover){
						window.loadover();
					}
				}
			});
		}
	}
	var getCookie = function(c_name) {
		var cookies = document.cookie.split(";");
		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var key = $.trim(cookie.split("=")[0]);
			var value = cookie.split("=")[1];
			if (key == c_name) {
				return decodeURI(value);
				break;
			}
		}
		return null;
	}
	
	var setCookie = function(c_name, value, expiredays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + expiredays ? "" : expiredays);
		document.cookie = c_name + "=" + encodeURI(value)
				+ (!expiredays ? "" : ";expires=" + exdate.toGMTString());
	}
	
	window.Form=new Form();
	$(document).ready(function(){
		window.Form.init();
	});
})(jQuery)