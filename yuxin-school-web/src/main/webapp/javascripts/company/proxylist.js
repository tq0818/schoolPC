/**
 * 
 */

$(function(){
	
	var proxy={
		init:function(){
			var $this = this;
            $selectMenu("proxy");
            // 初始化日期框
            $(".date-picker").datetimepicker({
                format: "yyyy-mm-dd",
                minView: 2,
                autoclose: true,
                language: "zh-CN"
            });
            $this.searchProxy();
		},
		searchProxy:function(page){
			var $this = this;
			var data = {};
			data.status = $('#proxystatus').val();
			data.rateSort = $('#commissionRate').val();
			data.startTime = $(".from").val();
	        data.endTime = $(".to").val();
	        data.name = $('#name').val();
	        data.page = page ? page : 1;
	        if ($(".to").val() != "") {
	            if ($(".to").val() < $(".from").val()) {
	                $.msg("时间范围不正确");
	                return;
	            }

	        }
	        $.each(data, function (key, value) {
	            if (!value) {
	                delete data[key];
	            }
	        })
	        $('.table').find('tbody').html("");
	        var openFlag = $('#openFlag').val();
	        $.ajax({
	            url: rootPath + "/companyConfigProxyOrg/queryProxysList",
	            data: data,
	            type: 'post',
	            beforeSend: function (XMLHttpRequest) {
	                $(".loading").show();
	                $(".loading-bg").show();
	            },
	            success: function (jsonData) {
	               
	                if (jsonData.data.length == 0) {
	                	$('.table').find('tbody')
	                        .append(
	                        '<tr><td colspan="8">没有查找到数据</td></tr>');
	                }
	                $.each(jsonData.data,function (i, proxy) {
	                	if(openFlag == 'success'){
	                		$('.table').find('tbody')
                            .append('<tr>'+
                            '<td>'+
                                '<span>'+(proxy.name?proxy.name:"")+'</span>'+
                            '</td>'+
                            '<td>'+
                                '<span>'+(proxy.contracter?proxy.contracter:"")+'</span>'+
                            '</td>'+
                            '<td>'+
                                '<span>'+(proxy.contractNumber?proxy.contractNumber:"")+'</span>'+
                            '</td>'+
                            '<td><span>'+(proxy.createDate?proxy.createDate:"")+'</span></td>'+
                            '<td><span>'+(proxy.commissionRate?proxy.commissionRate+"%":"")+'</span></td>'+
                            '<td>'+(proxy.status?'<span>启用</span>':'<span class="prevent">禁用</span>')+'</td>'+
                            '<td>'+
                                '<span class="option" proxyId='+(proxy.id)+'>'+
                                    '<i class="openorclose">'+(proxy.status?"禁用":"启用")+'</i>'+
                                    '<em>|</em>'+
                                    '<i class="editproxy">编辑</i>'+
                                    '<em>|</em>'+
                                    '<i class="delproxy">删除</i>'+
                                    '<em>|</em>'+
                                    '<i class="proxyInviteCode">邀请码</i>'+
                               '</span>' +
                            '</td>'+
                        '</tr>'
                            );
	                	}else{
	                		$('.table').find('tbody')
                            .append('<tr>'+
                            '<td>'+
                                '<span>'+(proxy.name?proxy.name:"")+'</span>'+
                            '</td>'+
                            '<td>'+
                                '<span>'+(proxy.contracter?proxy.contracter:"")+'</span>'+
                            '</td>'+
                            '<td>'+
                                '<span>'+(proxy.contractNumber?proxy.contractNumber:"")+'</span>'+
                            '</td>'+
                            '<td><span>'+(proxy.createDate?proxy.createDate:"")+'</span></td>'+
                            '<td><span>'+(proxy.commissionRate?proxy.commissionRate+"%":"")+'</span></td>'+
                            '<td>'+(proxy.status?'<span>启用</span>':'<span class="prevent">禁用</span>')+'</td>'+
                            '<td>'+
                                '<span class="option" proxyId='+(proxy.id)+'>'+
                                    '<i class="openorclose">'+(proxy.status?"禁用":"启用")+'</i>'+
                                    '<em>|</em>'+
                                    '<i class="editproxy">编辑</i>'+
                                    '<em>|</em>'+
                                    '<i class="delproxy">删除</i>'+
                               '</span>' +
                            '</td>'+
                        '</tr>'
                            );
	                	}
	                    });
	                $("#pageNo").remove();
	                
	                $(".table").after('<input type="hidden" id="pageNo" value="'+jsonData.pageNo+'"/>');
	                
	                if (jsonData.rowCount > 10) {
	                    $(".pagination").pagination(jsonData.rowCount,
	                        {
	                            next_text: "下一页",
	                            prev_text: "上一页",
	                            current_page: jsonData.pageNo - 1,
	                            link_to: "javascript:void(0)",
	                            num_display_entries: 8,
	                            items_per_page: jsonData.pageSize,
	                            num_edge_entries: 1,
	                            callback: function (page, jq) {
	                                var pageNo = page + 1;
	                                $this.searchProxy(pageNo);
	                            }
	                        });
	                } else {
	                    $(".pagination").html('');
	                }
	            },
	            complete: function (XMLHttpRequest, textStatus) {
	                $(".loading").hide();
	                $(".loading-bg").hide();
	            }
	        });
		}
	}
	
	
	function convertCanvasToImage(canvas) {
		var image = new Image();
		image.src = canvas.toDataURL("image/png");
		return image;
	}
	
   var saveFile = function(data, filename){
	   if (!!window.ActiveXObject || "ActiveXObject" in window){
		   var blob = new Blob([data],{type:"image/png"});
		   //navigator.msSaveBlob(blob, filename);
		   //var FileSaver = require('file-saver');
		   saveAs(blob,filename);
		   return;
	   }else{
		   var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
		   save_link.href = data;
		   save_link.download = filename;
		   
		   var event = document.createEvent('MouseEvents');
		   event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
		   save_link.dispatchEvent(event);
	   }
   };
   
	
   function download(blob){
	   var a=document.createElement('a');
//	   var url=window.URL.createObjectURL(blob);
	   var url=blob;
	   var filename='二维码.png';
	   a.href=url;
	   a.download=filename;
	   a.click();
	   window.URL.revokeObjectURL(url);
	 }
   
	$(document).ready(function () {
		$selectMenu('resource_org');
        proxy.init();
        $('input[type="submit"]').on('click',function(){
        	proxy.searchProxy();
        	return false;
        })
        $('.table').on('click','.openorclose',function(){
        	var proxyId = $(this).parent().attr('proxyId');
        	var status;
        	if($(this).html()=="启用"){
        		$(this).html('禁用');
        		status = 1;
        	}else{
        		$(this).html('启用');
        		status = 0;
        	}
        	$.ajax({
        		url:rootPath+"/companyConfigProxyOrg/addOrUpdateProxy",
        		type:"POST",
        		data:{"id":proxyId,"status":status},
        		success:function(data){
        			if(data=="success"){
        				//$.msg('操作成功');
        				if($('#pageNo').val()&&$('#pageNo').val()!=1){
        					proxy.searchProxy($('#pageNo').val());
        				}else{
        					proxy.searchProxy();
        				}
        			}
        		}
        	})
        })
        
         $('.table').on('click','.delproxy',function(){
        	var proxyId = $(this).parent().attr('proxyId');
        	$.ajax({
        		url:rootPath+"/companyConfigProxyOrg/checkOrgHasInviteRecord",
        		type:"POST",
        		data:{"proxyId":proxyId},
        		success:function(jsondata){
        			if(jsondata&&jsondata=="fail"){
        				$.alert('您要删除的代理机构已经有邀请记录，无法删除！');
        				return false;
        			}else{
        				/*$.confirm('您确定要删除该代理机构吗？',funciton(b){});*/
        				$.confirm('您确定要删除该代理机构吗？',function(b){
        					if(b){
        						$.ajax({
        			        		url:rootPath+"/companyConfigProxyOrg/addOrUpdateProxy",
        			        		type:"POST",
        			        		data:{"id":proxyId,"delFlag":"1"},
        			        		success:function(data){
        			        			if(data=="success"){
        			        				if($('#pageNo').val()&&$('#pageNo').val()!=1){
        			        					proxy.searchProxy($('#pageNo').val());
        			        				}else{
        			        					proxy.searchProxy();
        			        				}
        			        			}
        			        		}
        			        	})
        					}
        				})
        			}
        		}
        	})
        	
        })
    
	
        $('.addproxy_').on('click',function(){
    	window.location.href=rootPath+"/companyConfigProxyOrg/toCompanyProxyAddOrUpdate";
        })
    
        
        $('.table').on('click','.editproxy',function(){
        	var proxyId = $(this).parent().attr('proxyId');
        	window.location.href=rootPath+"/companyConfigProxyOrg/toCompanyProxyAddOrUpdate?pid="+proxyId;
        })
    
         $('.table').on('click','.proxyInviteCode',function(){
        	var proxyId = $(this).parent().attr('proxyId');
        	$.ajax({
        		url:rootPath+"/companyConfigProxyOrg/getOrgInviteCode",
        		type:"POST",
        		data:{"proxyId":proxyId},
        		success:function(data){
        			if(data){
        				$('#qrcode').html('');
        				$(".invite_ewm_code div").qrcode({
        					text  : data,
        					size:150
        				});
        				var canvas=$("#qrcode").find("canvas")[0];
        				var img=convertCanvasToImage(canvas);
        				$.confirm({title:'代理机构邀请码',text:'<div class="alertErqod"><p><img src="'+img.src+'" alt=""></p></div>',save:"保存到本地",cancel:'hidden',savehidden:'savehidden'});
        				$('.Confirm_Ok').click(function(){
        					//document.execCommand('SaveAs',false);
        					//DownLoadReportIMG(img.src);
        					//$('.hiddenimg').attr('href',img.src).attr('download','代理机构二维码.png').trigger('click');
        					//img.src = img.src.replace('image/png','image/octet-stream;Content-Disposition:attachment;filename=code.png');
//        					 var blob = new Blob([img.src],{type:"image/octet-stream"});
//        					window.location.href=img.src;
        					saveFile(img.src,"二维码.png");
        					 //var dataURI = 'data:image/jpeg;base64,/9j/4AAQSkZJRg...'; //base64 字符串
        					 /*var mimeString =  img.src.split(',')[0].split(':')[1].split(';')[0]; // mime类型
        					 var byteString = atob(img.src.split(',')[1]); //base64 解码
        					 var arrayBuffer = new ArrayBuffer(byteString.length); //创建缓冲数组
        					 var intArray = new Uint8Array(arrayBuffer); //创建视图
        					 for (i = 0; i < byteString.length; i += 1) {
        					      intArray[i] = byteString.charCodeAt(i);
        					 }

        					 var blob = new Blob([intArray], { type:  'image/octet-stream '});*/
//        					download(img.src);
        					/*$.ajax({
        						url:rootPath+"/companyConfigProxyOrg/downloadProxyInviteCode",
        						type:"POST",
        						data:{"base64Code":img.src},
        						success:function(data){
        							
        						}
        					})*/
        				})
        			}
        			
        		}
        	})
        	//window.location.href=rootPath+"/companyConfigProxyOrg/toCompanyProxyAddOrUpdate?pid="+proxyId;
        })
	})
    
    
})
