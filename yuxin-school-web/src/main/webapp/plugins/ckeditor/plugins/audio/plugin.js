( function() {
CKEDITOR.plugins.add( 'audio',{

	getPlaceholderCss : function(){
		return 'img.cke_audio' +
				'{' +
					'background-image: url(' + CKEDITOR.getUrl( this.path + 'images/audio.png' ) + ');' +
					'background-position: center center;' +
					'background-repeat: no-repeat;' +
					'background-color:gray;'+
					'border: 1px solid #a9a9a9;' +
					'width: 300px;' +
					'height: 30px;' +
				'}';
	},

	onLoad : function(){
		// v4
		if (CKEDITOR.addCss){
			//CKEDITOR.addCss("img.cke_audio{background-image: url(" + CKEDITOR.getUrl(this.path + "images/placeholder.png") + ");background-position: center center;background-repeat: no-repeat;border: 1px solid #a9a9a9;width: 80px;height: 80px;}")
			CKEDITOR.addCss( this.getPlaceholderCss() );
		}

	},

	init : function(editor){
		CKEDITOR.dialog.add( 'audio', this.path + 'dialogs/audio.js' );
		editor.addCommand( 'Audio', new CKEDITOR.dialogCommand( 'audio'));
		editor.ui.addButton( 'Audio',{
			label : '音频',
			command : 'Audio',
			icon : this.path + 'images/audio.png'
		});
		editor.on( 'doubleclick', function( evt ){
			var element = evt.data.element;
	
			if ( element.is( 'img' ) && element.data( 'cke-real-element-type' ) == 'audio' )
				evt.data.dialog = 'audio';
		});

	},
	afterInit: function( editor )
	{
		var dataProcessor = editor.dataProcessor,
			htmlFilter = dataProcessor && dataProcessor.htmlFilter,
			dataFilter = dataProcessor && dataProcessor.dataFilter;

		// dataFilter : conversion from html input to internal data
		dataFilter.addRules(
			{

			elements : {
				$ : function( realElement )
				{
						if ( realElement.name == 'audio' )
						{
							realElement.name = 'cke:audio';
							for( var i=0; i < realElement.children.length; i++)
							{
								if ( realElement.children[ i ].name == 'source' )
									realElement.children[ i ].name = 'cke:source'
							}

							var fakeElement = editor.createFakeParserElement( realElement, 'cke_audio', 'audio', false ),
								fakeStyle = fakeElement.attributes.style || '';
							return fakeElement;
						}
				}
			}

			}
		);

	}
});
})();