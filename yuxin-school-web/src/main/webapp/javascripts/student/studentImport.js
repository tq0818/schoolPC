/**
 *  学员导入
 *  @author Ds
 */
;( function ( win, doc, $ ) {
    
    var Module, Plugin;

    Plugin = ( function () {

        function Plugin( settings ) {
            var defaults = {
                dataId: 'dataId'
            };
            this.options = $.extend( defaults, settings || {} );
        }

        $.extend( Plugin.prototype, {

            ajax: function ( settings ) {
                var defaults = {
                    url: '',
                    data: {},
                    type: 'post',
                    dataType: 'json',
                    async: true,
                    success: function () {},
                    error: function () {},
                    beforeSend: function () { $(".loading").show(); $(".loading-bg").show(); },
                    complete: function () {  $(".loading").hide(); $(".loading-bg").hide(); },
                    lazy: false
                };
                var options = $.extend( defaults, settings || {} );
                $.ajax({
                    url: encodeURI( rootPath + options.url ),
                    data: options.data,
                    type: options.type,
                    dataType: options.dataType,
                    async: options.async,
                    beforeSend: function ( XMLHttpRequest ) {
                        if( options.lazy && options.beforeSend ) { options.beforeSend(); }                
                    },
                    success: options.success || function () {},
                    error: options.error || function () {},
                    complete: function ( XMLHttpRequest, textStatus ) {
                        if( options.lazy && options.complete ) { options.complete(); }
                    }
                });
            },

            setData: function ( name, value ) {
                if( !doc.getElementById( this.options.dataId ) ){
                    $( 'body' ).append( '<input type="hidden" id=' + this.options.dataId + '>' );
                }
                $( '#' + this.options.dataId ).data( name, value );
                return this;
            },

            getData: function ( name ) {
                return $( '#' + this.options.dataId ).data( name );
            }

        });

        return Plugin;

    })();

    Module = ( function ( _$ ) {

        function Module( settings ) {
            var defaults = {};
            this.options = $.extend( defaults, settings || {} );
            this.init();
        }

        $.extend( Module.prototype, {

            init: function () {
                var self = this;

                $( doc ).on( 'click', '.student-validate', function () {
                	var urls;
                	var keche = $("#keche").val();
                	var xuesheng = $("#xuesheng").val();
                	if(keche==2){
                		urls="/excelImportStudents/studentsValidateNo";
                	}
                	if(xuesheng==1){
                		urls="/excelImportStudents/studentsValidate";
                	}
                    var name = $("#imgData").val();
                    if( !name ) {
                        $.msg( "请选择文件" );
                        return false;
                    }
                    $(".loading.check").show();
    	            $(".loading-bg").show();
                    $.ajaxFileUpload({
                        url : rootPath + urls,
                        secureuri : false,  // 安全协议
                        async : false,
                        fileElementId : 'imgData',
                        type : "POST",
                        dataType : "json",
                        success : function ( jsonData ) {
                        	console.log(jsonData);
                        	var html = "";
                        	if(jsonData.result == "exception"){
                        		html+='<span style="color:red;font-size:14px;">'+jsonData.msg+'</span>';
    							$(".insertData").addClass("none");
    							$(".chooseFile").removeClass("none");
                        	}else if(jsonData.result == "error"){
                        		html+='<span style="font-size:14px;">校验结果:</span><br/>';
	                        	if(jsonData.errorMsg2In.length>0){
	                        		html+='<span style="font-size:14px;">Excel内部校验:</span><br/>';
			                    	$.each(jsonData.errorMsg2In,function(i,data){
										html+='<span style="font-size:14px;">'+(i+1)+"、"+data+'</span><br/>';
									});
	                        	}
	                        	if(jsonData.errorMsg2Out.length>0){
	                        		html+='<span style="font-size:14px;">与网校学员校验:</span><br/>';
			                    	$.each(jsonData.errorMsg2Out,function(i,data){
										html+='<span style="font-size:14px;">'+(i+1)+"、"+data+'</span><br/>';
									});
	                        	}
	                        	$(".chooseFile").removeClass("none");
	                        	if(jsonData.students4Insert.length>0){
	                        		$(".newinsert").removeClass("none");
	                        	}
	                        	if(jsonData.students4Update.length>0){
	                        		$(".allupdate").removeClass("none");
	                        	}
								$(".chooseInsert").addClass("none");
								$("#markUrl").val(JSON.stringify(jsonData.excelPath));
								_$.setData("students4Update",JSON.stringify(jsonData.students4Update));
								_$.setData("students4Insert",JSON.stringify(jsonData.students4Insert));
                        	}else{
                        		html+='<span style="color:red;font-size:14px;">无错误信息!</span>';
								$(".chooseFile").addClass("none");
								$(".newinsert").addClass("none");
								$(".allupdate").addClass("none");
								$(".chooseInsert").removeClass("none");
								$("#markUrl").val(JSON.stringify(jsonData.excelPath));
								_$.setData("students4Insert",JSON.stringify(jsonData.students4Insert));
                        	}
                        	$("#returnInfo").html(html);
                        },
                        complete: function ( XMLHttpRequest, textStatus ) { $(".loading").hide(); $(".loading-bg").hide(); }
                    });
                    $("#fileNames").val("");
                })
                .on( 'click', '.student-import', function () {
                	
    				if($(this).hasClass("btn-default")){
    					return;
    				}
    				$(this).removeClass("btn-primary").addClass("btn-default");
    				var n = $("#markUrl").val();
    				var mark=$(this).attr("mark");
    				if( n == "" ){
    					$.msg("请选择文件并校验数据");
    					$(this).addClass("btn-primary").removeClass("btn-default");
    					return;
    				}
    				_$.ajax({ url: '/excelImportStudents/insertMoreStudents', 
    						  data: {"data":mark=="insert"?_$.getData("students4Insert"):_$.getData("students4Update"),"groupOneId":$("#studentG1").val(),"groupTwoId":$("#studentG2").val()}, 
    						  beforeSend: function () { $(".loading.insert").show(); $(".loading-bg").show(); }, 
    						  lazy: true, 
    						  success: function ( jsonData ) {
			    					$("#markUrl").val("");
									if(jsonData.result == "error"){
										$.msg("导入信息有误！");
										$this.addClass("btn-primary").removeClass("btn-default");
										$("#returnInfo").html("");
										$(".newinsert").addClass("none");
										$(".allupdate").addClass("none");
										return;
									}
									$("#stuMobiles").val(jsonData.studentIds);
									//成功后删除临时文件夹
									$.msg("导入信息完毕",function(){
										$.ajax({
											url : rootPath+"/student/deleteFile",
											type: 'post',
											success:function(data){
												$("#DataForm")[0].submit();
												$this.addClass("btn-primary").removeClass("btn-default");
											}
										});
									});
	    					  }
    				});
    				

                })
            }

        });

        return Module;

    })( new Plugin() );
   
    $( function () {
        return new Module();
    });

})( window, document, jQuery );