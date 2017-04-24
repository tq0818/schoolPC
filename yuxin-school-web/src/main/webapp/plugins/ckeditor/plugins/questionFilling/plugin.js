﻿'use strict';
( function() {
	CKEDITOR.plugins.add( 'questionFilling', {
		requires: 'widget,dialog',
		lang: 'zh-cn', // %REMOVE_LINE_CORE%
		icons: 'questionFilling', // %REMOVE_LINE_CORE%
		hidpi: true, // %REMOVE_LINE_CORE%

		onLoad: function() {
			// Register styles for questionFilling widget frame.
			CKEDITOR.addCss( '.cke_questionFilling{background-color:#eee}' );
		},

		init: function( editor ) {

			var lang = editor.lang.questionFilling;

			CKEDITOR.dialog.add( 'questionFilling', this.path + 'dialogs/questionFilling.js' );

			editor.widgets.add( 'questionFilling', {
				dialog: 'questionFilling',
				pathName: lang.pathName,
				template: '<span class="cke_questionFilling">[[]]</span>',

				downcast: function() {
					return new CKEDITOR.htmlParser.text( '[[' + this.data.name + ']]' );
				},

				init: function() {
					this.setData( 'name', this.element.getText().slice( 2, -2 ) );
				},

				data: function( data ) {
					this.element.setText( '[[' + this.data.name + ']]' );
				}
			} );

			editor.ui.addButton && editor.ui.addButton( 'questionFilling', {
				label: '添加填空答案',
				command: 'questionFilling',
				toolbar: 'insert,5',
				//icon: 'questionFilling'
				icon: this.path + 'images/blank.png'
			} );
		},

		afterInit: function( editor ) {
			var questionFillingReplaceRegex = /\[\[([^\[\]])+\]\]/g;

			editor.dataProcessor.dataFilter.addRules( {
				text: function( text, node ) {
					var dtd = node.parent && CKEDITOR.dtd[ node.parent.name ];

					// Skip the case when questionFilling is in elements like <title> or <textarea>
					// but upcast questionFilling in custom elements (no DTD).
					if ( dtd && !dtd.span )
						return;

					return text.replace( questionFillingReplaceRegex, function( match ) {
						// Creating widget code.
						var widgetWrapper = null,
							innerElement = new CKEDITOR.htmlParser.element( 'span', {
								'class': 'cke_questionFilling'
							} );

						// Adds questionFilling identifier as innertext.
						innerElement.add( new CKEDITOR.htmlParser.text( match ) );
						widgetWrapper = editor.widgets.wrapElement( innerElement, 'questionFilling' );

						// Return outerhtml of widget wrapper so it will be placed
						// as replacement.
						return widgetWrapper.getOuterHtml();
					} );
				}
			} );
		}
	} );

} )();
