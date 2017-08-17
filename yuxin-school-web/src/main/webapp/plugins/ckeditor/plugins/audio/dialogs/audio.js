CKEDITOR.dialog.add( 'audio', function ( editor ){;

	function commitValue( videoNode, extraStyles ){
		var value = this.getValue();
		videoNode.setAttribute( this.id, value);
		if ( !value )
			return;
	}
	function commitSrc( videoNode, extraStyles, videos ){
		var match = this.id.match(/(\w+)(\d)/),
			id = match[1],
			number = parseInt(match[2], 10);

		var video = videos[number] || (videos[number]={});
		video[id] = this.getValue();
	}

	function loadValue( videoNode ){;
		if ( videoNode ){
			if(this.id == 'loop'){
				if(!videoNode.getAttribute( this.id )){
					this.setValue('');
				}else{
					this.setValue( videoNode.getAttribute( this.id ) );
				}
			}else if(this.id=='autoplay'){
				if(!videoNode.getAttribute( this.id )){
					this.setValue('');
				}else{
					this.setValue( videoNode.getAttribute( this.id ) );
				}
			}else{
				this.setValue( videoNode.getAttribute( this.id ) );
			}
		}
	}
	function loadSrc( videoNode, videos ){;
		var match = this.id.match(/(\w+)(\d)/),
			id = match[1],
			number = parseInt(match[2], 10);

		var video = videos[number];
		if (!video)
			return;
		this.setValue( video[ id ] );
	};
	return {
		title : '音频属性',
		minWidth : 400,
		minHeight : 200,

		onShow : function(){
			// Clear previously saved elements.
			this.fakeImage = this.videoNode = null;
			// To get dimensions of poster image
			this.previewImage = editor.document.createElement( 'img' );

			var fakeImage = this.getSelectedElement();
			if ( fakeImage && fakeImage.data( 'cke-real-element-type' ) && fakeImage.data( 'cke-real-element-type' ) == 'audio' ){
				this.fakeImage = fakeImage;

				var videoNode = editor.restoreRealElement( fakeImage ),
					videos = [],
					sourceList = videoNode.getElementsByTag( 'source', '' );
				if (sourceList.count()==0)
					sourceList = videoNode.getElementsByTag( 'source', 'cke' );

				for ( var i = 0, length = sourceList.count() ; i < length ; i++ ){
					var item = sourceList.getItem( i );
					videos.push( {src : item.getAttribute( 'src' ), type: item.getAttribute( 'type' )} );
				}
				this.videoNode = videoNode;
				this.setupContent( videoNode, videos );
			}else
				this.setupContent( null, [] );
		},
		onOk : function(){
			var videoNode = null;
			if ( !this.fakeImage ){
				videoNode = CKEDITOR.dom.element.createFromHtml( '<cke:audio></cke:audio>', editor.document );
				videoNode.setAttributes(
					{
						controls : 'controls'
					} );
			}else{
				videoNode = this.videoNode;
			}

			var extraStyles = {}, videos = [];
			this.commitContent( videoNode, extraStyles, videos );
			
			if(videoNode){
				if(!videoNode.getAttribute('loop')){//属性值为空删除此标签
							videoNode.removeAttribute('loop');
				}
				if(!videoNode.getAttribute('autoplay')){
					videoNode.removeAttribute('autoplay');
				}
			}
			var innerHtml = '', links = '',
				link =  ''||'<a href="%src%">下载音频</a> ' ,
				fallbackTemplate =  ''||'您的浏览器不支持此播放插件.<br>请点击下载: %links%' ;
			for(var i = 0; i < videos.length; i++){
				var video = videos[i];
				if ( !video || !video.src )
					continue;
				//innerHtml +='<cke:source src="'+video.src+ '" type="'+video.type + '"/>';
				innerHtml += '<cke:source src="' +  video.src+ '" type="' + video.type + '" />';
				
				links += link.replace('%src%', video.src).replace('%type%', video.type);
			}
			//videoNode.setHtml( innerHtml );
			;
			videoNode.setHtml( innerHtml + fallbackTemplate.replace( '%links%', links ) );
			// Refresh the fake image.
			var newFakeImage = editor.createFakeElement( videoNode, 'cke_audio', 'audio', false );
			newFakeImage.setStyles( extraStyles );
			if ( this.fakeImage ){
				newFakeImage.replace( this.fakeImage );
				editor.getSelection().selectElement( newFakeImage );
			}else{
				// Insert it in a div
				var div = new CKEDITOR.dom.element( 'DIV', editor.document );
				editor.insertElement( div );
				div.append( newFakeImage );
			}
		},
		contents: [
			{
				id : 'info',
				elements: [
					/*{
						type : 'hbox',
						widths: [ '', '100px'],
						children : [
							{
								type: 'file',
								id: 'upload'
							},
							{
								type : 'fileButton',
								id : 'browse',
								hidden : 'true',
								filebrowser: "info:src0",
					      "for": ["info", "upload"],
								label : "上传文件到服务器"
							}
						]
					},*/
					{
						type : 'hbox',
						widths: [ '', '100px', '75px'],
						children : [
							{
								type : 'text',
								id : 'src0',
								label : '音频地址',
								validate : CKEDITOR.dialog.validate.notEmpty( '音频地址不能为空' ),
								commit : commitSrc,
								setup : loadSrc
							},
							{
								type : 'button',
								id : 'browse',
								hidden : 'true',
								style: "display:inline-block!important;margin-top:14px;",
								label: "音频库",
								filebrowser :
								{
									action : 'Browse',
									target: 'info:src0',
									url: editor.config.filebrowserAudioBrowseUrl
								},
								onClick: function(){
									$(".showAudio").show();
								}
							}]
					},
					{
						type : 'hbox',
						widths: [ '30%','30%','30%'],
						children : [
							{
								id : 'loop',
								label : '循环播放',
								type : 'select',
								'default':'',
								items :
								[
									['是', 'loop'],
									['否', '']
								],
								commit : commitValue,
								setup : loadValue
							},
							{
								id : 'autoplay',
								label : '自动播放',
								type : 'select',
								'default':'',
								items :
								[
									['是', 'autoplay'],
									['否', '']
								],
								commit : commitValue,
								setup : loadValue
							},
							{
								id : 'type0',
								label : '音频类型',
								type : 'select',
								'default':'audio/mpeg',
								items :
								[
									['MP3', 'audio/mpeg'],
									['OGG', 'audio/ogg'],
									['WAV', 'audio/wav']
								],
								commit : commitSrc,
								setup : loadSrc
							}]
					}
				]
			}
		]
	};
});