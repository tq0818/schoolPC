function onlyNum() {
	var event = arguments.callee.caller.arguments[0] || window.event; 
	if (!(event.keyCode == 46) && !(event.keyCode == 8)
			&& !(event.keyCode == 37) && !(event.keyCode == 39))
		if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)))
			if(window.event){
				event.returnValue = false;
			}else{
				event.preventDefault();
			}
}