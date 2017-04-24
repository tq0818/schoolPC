/*
Ajax 三级省市联动
http://code.ciaoca.cn/
日期：2012-7-18

settings 参数说明
-----
url:省市数据josn文件路径
prov:默认省份
city:默认城市
dist:默认地区（县）
nodata:无数据状态
required:必选项
------------------------------ */
(function($){
		$.fn.cityselect=function(settings){
			if(this.length<1){return;};
	
			// 默认值
			settings=$.extend({
				url:rootPath + "/javascripts/company/city.min.js",
				prov:null,
				city:null,
				dist:null,
				nodata:null,
				required:true
			},settings);
	
			var box_obj=this;
			var prov_obj=box_obj.find("#prov");
			var city_obj=box_obj.find("#city");
			var dist_obj=box_obj.find("#dist");
			var prov_val=settings.prov;
			var city_val=settings.city;
			var dist_val=settings.dist;
			var select_prehtml=(settings.required) ? "" : "<option value=''>请选择</option>";
			var city_json;
	
			var init=function(){
				// 遍历赋值省份下拉列表
				temp_html=select_prehtml;
				var hRegion = $("#HRegion").val();
				$.each(city_json.citylist,function(i,prov){
					if(hRegion == prov.p){
						temp_html+="<option selected='selected' value='"+prov.p+"'>"+prov.p+"</option>";
					}else{
						temp_html+="<option value='"+prov.p+"'>"+prov.p+"</option>";
					}
					
				});
				prov_obj.html(temp_html);
			};
	
			// 设置省市json数据
			if(typeof(settings.url)=="string"){
				$.getJSON(settings.url,function(json){
					city_json=json;
					init();
				});
			}else{
				city_json=settings.url;
				init();
			};
		};
	})(jQuery);