(function() {
if (!/*@cc_on!@*/0) return;
// 在DOM下循环创建html5新增标签以便低版本浏览器支持
var e = "abbr,article,aside,audio,canvas,datalist,details,dialog,eventsource,figure,footer,header,hgroup,mark,menu,meter,nav,output,progress,section,time,video".split(',');
var i= e.length;
while (i--){ document.createElement(e[i]);} 
})();