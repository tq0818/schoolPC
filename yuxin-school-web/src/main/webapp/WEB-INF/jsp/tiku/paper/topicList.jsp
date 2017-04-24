<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<input type="hidden" value="${categoryId }" id="categoryId"/>
<input type="hidden" value="${subId }" id="subId">
    <div class="main-content nospace">
		<div class="marketing">
			<c:if test="${topicType != 'TOPIC_TYPE_CASE' }">
				<p class="c">
					<span class="t-title">章</span> 
					<span class="t-content">
						<select id="chapter" class="select"></select>
					</span>
					<span class="t-title">节</span> 
					<span class="t-content">
						<select id="section" class="select"></select>
					</span>
				</p>
			</c:if>
			<p class="c">
				<span class="t-title">题目</span> 
				<span class="t-content">
					<input type="text" id="findTopicName" class="inputClass" style="border:#5980FF 1px solid;"/>
				</span>
				<span class="btn-section">
	        		<a href="javascript:;" class="btn btn-primary  btn-seach">查询</a>
	        	</span>
			</p>
		</div>
    
    <div class="place-list" style="height: 330px;overflow-y: auto;">
        
    </div>
    <div class="pages">
        <ul class="paginations">
	
		</ul>
    </div>
    <div class="btns text-center">
        <p class="text-center">
            <a href="javascript:;" class="btn btn-primary btn-ok" data-type=${topicType }>确定</a>
            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
        </p>
    </div>
	</div>
	
	<style>
    	.t-title{
		    line-height: 55px;
		    margin: 0px 5px -5px 15px;
		    width: 35px;
		    display: inline-block;
    	}
    	.select{
    		width: 260px;
    		display: inline-block;
    		border:#5980FF 1px solid;
    	}
    	.inputClass{
    		width:283px;
    	}
    	.btn-section{
    		margin-left:18px;
    	}
		.add-classes .place-list .table-hover tr:hover td{
		  	background: #f5f5f5;
		}
    </style>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/question/chapterSectionPoint.js"></script>
<script type="text/javascript">
	$(function(){
		$(".btn-ok").attr("disabled","disabled");
		$(".btn-seach").click(function(){
			selTopicExist(1);
		});

		$(".btn-ok").click(function(){
			if($(".btn-ok").attr("disabled") == "disabled"){
				return false;
			}
			var $obj = $(this);
			var ids = "";
			for(var i = 0; i < $(".addTopic:checked").length ; i++){
				if((i + 1 ) == $(".addTopic:checked").length){
					ids += $(".addTopic:checked:eq(" + i + ")").attr("data-id");
				}else{
					ids += $(".addTopic:checked:eq(" + i + ")").attr("data-id") + ",";
				}
			}
			//绑定到试题试卷关系表
			$.ajax({
				url : rootPath + "/tikuPaper/addTopicPaper",
				type:"post",
				data:{"ids":ids,"paperId":$("#paperId").val(), "topicType":$($obj).attr("data-type")},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					if(data.msg == "success"){
						selTopic();
					}else{
						$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(500).fadeOut(200,function(){
	        				$(this).remove();
						});
					}
				},
    	        complete:function(XMLHttpRequest,textStatus){
    				$(".loading").hide();
    	            $(".loading-bg").hide();
    	        }
			});
		});
	});
	function selTopicExist(pageNo){
		$.ajax({
			url : rootPath + "/question/selTopicExist",
			type:"post",
			data:{"paperId":$("#paperId").val(),"categoryId":$("#categoryId").val(),"subId":$("#subId").val(),"chapterId":$("#chapter").val(),"sectionId":$("#section").val(),"topicName":$("#findTopicName").val(),"page":pageNo,"pageSize":10,"topicType":$(".btn-primary.types").attr("data-type")},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	        },
			success:function(data){
				$(".place-list").html(data);
			},
	        complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}
</script>