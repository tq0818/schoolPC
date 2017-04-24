/**
 * @Caption: 公用工具类，包含商品获取工具、首页div拼装工具. 需要增加或修改必须添加注释，说明原因及功能
 * @Auther: chopin  
 * @Date: 2015-3-23
 */
function progress(obj,val){
	var width = obj.width();
	obj.css('position','relative');
	obj.html('<div class="proGres"><div class="proBack"> <span class="proValue"><small>'+val+'</small>%</span></div></div>');
	obj.find('.proGres').width(width);
	obj.find('.proBack').width(val/100*width);
}