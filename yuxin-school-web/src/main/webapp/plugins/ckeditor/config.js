/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	// Toolbar groups configuration.
	config.toolbarGroups = [
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'forms' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'links' },
		{ name: 'insert' },
		'/',
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'tools' },
		{ name: 'others' }
    ];
	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';
	
	//set picture is null;
	config.image_previewText=' ';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	
	var pathName = window.document.location.pathname;
    //获取带"/"的学科名，如：/uimcardprj 
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	//图片上传
    config.filebrowserImageUploadUrl = rootPath + '/classType/editorUploadImg';
    
    //扩展
    config.extraPlugins = 'questionFilling';
    
//	config.filebrowserImageUploadUrl = projectName + '/classType/editorUploadImg?type=Image';
//	config.filebrowserUploadUrl = projectName+'/classType/editorUploadImg?type=Image'; //固定路径
};
