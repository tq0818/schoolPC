<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div class="w h">
    	<span class="class_number_name">学科：</span>
    	<input type="hidden" value="${classType.itemOneId }" name="itemOneId">
        <span class="class_number_sp" id="itemOneId">${itemOneName}</span>
    </div>
    <div class="w h">
    	<span class="class_number_name">学科小类：</span>
    	<input type="hidden" value="${classType.itemSecondId }" name="itemSecondId" id="itemSecondId">
        <span class="class_number_sp" >${itemTwoName}</span>
    </div>
