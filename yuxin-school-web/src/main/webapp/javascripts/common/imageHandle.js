/* 两种使用方式
第一种：
$.imageHandle({ url:'http://oano6er3n.bkt.clouddn.com/giraffe.jpg',data:{width:200, height:300, limit:true}});
第二种：
$.imageHandle({url:'http://oano6er3n.bkt.clouddn.com/giraffe.jpg', width:200, height:300, limit:true});
注解： url:图片链接  width（0-1000）/height（0-1000）:要设得图片宽/高度（如果宽高只给一个，则另一个会自适应。两个同时给会把图片设置成指定的宽高，这样图片可能会变形。如果设了宽高就不需要设置limit。）limit:true 设置图片压缩
*/
jQuery.extend({
    imageHandle:function (opations) {
        var url='';
        if(!opations.url){return};
        url=opations.url;
        opations.data=opations.data || {};
        opations.data.width=opations.data.width || opations.width || '';
        opations.data.height=opations.data.height || opations.height || '';
        opations.data.limit=opations.data.limit || opations.limit || false;
        if((opations.data.width || opations.data.height) && !opations.data.limit){
            // 宽度设给定，高度自适应
            if(opations.data.width) {
                url=opations.url + '?'+'imageMogr2/thumbnail/'+opations.data.width+'x';
            }
            // 高度给定，宽度自适应
            if(opations.data.height) {
                url=opations.url + '?'+'imageMogr2/thumbnail/x'+opations.data.height;
            }
            // 宽高给定 图片有可能变形
            if(opations.data.width && opations.data.height){
                url=opations.url + '?'+'imageMogr2/thumbnail/'+opations.data.width+'x'+opations.data.height+'!';
            }
        }
        if(opations.data.limit){
            url=opations.url + '?imageslim';
        }
        return url;
    }
});










