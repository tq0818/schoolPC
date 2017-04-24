var d=dateToStr("yyyy-MM-dd",new Date());
function countAge(e){
	var date=$(e).val();
	if(date){
		var a=date.substring(0,4);
		var b=d.substring(0,4);
		var c=(b-a)>0?(b-a+1):1;
		$("#stu_Age").val(c);
	}
}