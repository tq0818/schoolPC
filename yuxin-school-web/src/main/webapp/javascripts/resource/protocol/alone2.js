var res = null;
var editor = null;
var successCallBack=function(){
	window.location.reload();
};
(function ($,cicDescription) {var editor=CKEDITOR.replace('editor01');
editor.config.width="730px";
editor.config.height="100px";
editor.config.toolbar=[
       				[ 'mode', 'document', 'doctools' ], [ 'Source', '-', 'NewPage' ] ,
       				[ 'basicstyles', 'cleanup' ],
       				[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript' ] ,
       				[ 'list', 'indent', 'blocks', 'align', 'bidi' ],  
       				[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
       				[ 'Link', 'Unlink' ] ,
       				[  'Table' ],
       				[ 'Styles', 'Format', 'Font', 'FontSize' ],
       			    [ 'TextColor', 'BGColor' ],
       				[ 'Maximize'] ,
       				[ '-' ] ,
       				[ 'About' ]
       		];
       		editor.config.baseFloatZIndex = 10100;
       		var detailDesc = decodeURI(cicDescription).replace("\r\n","<br>&nbsp;&nbsp;");
       		editor.setData(detailDesc);
       		editor.config.customConfig ='config.js';})(jQuery,cicDescription);

function CKupdate() {
	for (instance in CKEDITOR.instances) {
		CKEDITOR.instances[instance].updateElement();
	}
}
