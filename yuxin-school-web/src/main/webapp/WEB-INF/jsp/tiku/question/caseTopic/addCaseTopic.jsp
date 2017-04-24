<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.cke_wysiwyg_frame.cke_reset{
			width:100%;
		}
	</style>
	<script type="text/javascript">
		$(".add-topicname-ckeditor").remove();
	</script>
	<input type="hidden" value="${topicId }" id="topicId"/>
    <input type="hidden" value="${categoryId }" id="categoryId">
    <input type="hidden" value="${subId }" id="subId">
        
     <div class="qution-content">
	      <div class="qution-control">
	          <span class="qution-title">题目类型</span>
	          <span class="qution-input">
	              <label for=""><input type="radio" name="names" class="redioButton" data-type="TOPIC_TYPE_RADIO">单选题</label>
	              <label for=""><input type="radio" name="names" class="redioButton" data-type="TOPIC_TYPE_MULTIPLE">多选题</label>
	              <label for=""><input type="radio" name="names" class="redioButton" data-type="TOPIC_TYPE_UNDEFINED">不定项题</label>
	              <label for=""><input type="radio" name="names" class="redioButton" data-type="TOPIC_TYPE_TRUE_FALSE">判断题</label>
	              <label for=""><input type="radio" name="names" class="redioButton" data-type="TOPIC_TYPE_FILLING">填空题</label>
	              <label for=""><input type="radio" name="names" class="redioButton" data-type="TOPIC_TYPE_ANSWER">简答题</label>
	          </span>
	      </div>
	   	  <!-- 添加子题 -->
	      <div class="questionEdit">
	    	
	      </div>
    </div>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/question/caseTopic.js"></script>
</body>
</html>