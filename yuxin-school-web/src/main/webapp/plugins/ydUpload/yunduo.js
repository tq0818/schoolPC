/*global plupload ,mOxie*/
/*global ActiveXObject */
/*exported Qiniu */
/*exported QiniuJsSDK */

function YunduoJsSDK() {
	this.uploader = function(o){
        var upload = new plupload.Uploader(o);
        upload.setOption({
			url: '/video/uploadQiniuVideo'
		});
        upload.bind('Init',function(up,params){});
        upload.init();
        upload.bind('FilesAdded',function(up,files){
        	plupload.each(files, function(i, file) {
                up.start();
            });
        	up.refresh();
        });
        upload.bind('BeforeUpload',function(up,file){
        	
        });
        upload.bind('FileUploaded', function(up, file) {  
        	
        }); 
        upload.bind('UploadProgress', function(up, file) {  
            
        }); 
        upload.bind('Error', function(up, err) { 
        	
        });
	};
}

var Yunduo = new YunduoJsSDK();
