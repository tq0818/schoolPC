$.extend({
    myQRcode:function(options){
        options.name=options.name || 'yuxin';
        var that=options.id;
        var titleId = $(options.elem).prevAll(".v-title").first().attr("chapterId");
        var title_id = $(options.elem).prevAll(".v-title").first().attr("chapter_id");
		var courseId = $(options.elem).attr("courseId");
		var course_id = $(options.elem).attr("course_id");
		var courseName = $("#courseName").val();
		options.url = "http://"+$("#domain").val()+"/course_mobile/queryDetail/"+titleId+"?lecId="+courseId;
        var qrcode = new QRCode(that+'', {
            text:options.url,
            width: 256,
            height: 256,
            colorDark : '#000000',
            colorLight : '#ffffff',
            correctLevel : QRCode.CorrectLevel.H
        });
        qrcode.clear();
        qrcode.makeCode(options.url);
		$('#'+that).find('img').remove();
		
        $('#'+that).find(options.ele).click(function(){
            var canvas =$('#'+that).find('canvas').get(0);
            var image = canvas.toDataURL("image/png");
//            var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");
            var saveFile = function(data, filename){
                var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
                save_link.href = data;
                save_link.download = filename;

                var event = document.createEvent('MouseEvents');
                event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
                save_link.dispatchEvent(event);
                options.end && options.end();
				
            };
    		var cname = title_id+"."+course_id;
            var filename = cname+"-"+ courseName + '.png';
            saveFile(image,filename);
        });
    }

});


