//计算截止日期
function dateCalculate(datetime,month){
	var date = new Date(datetime);
	var now = new Date();
	var validity = "";
	if((date.getTime() - (new Date(now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate()).getTime())) < 0){
		var oldDate = DateAdd(month)
		validity = (oldDate.getFullYear() + "-" + (oldDate.getMonth() + 1) + "-" + oldDate.getDate());
	}else{
		date.setMonth((date.getMonth() + month));
		if(date.getDate() < 28){
			date.setDate(0);
		}else{
			switch ((date.getMonth() + 1)) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				date.setDate(31);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				date.setDate(30);
				break;
			}
		}
		validity = (date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate());
	}
	return validity;
}

function DateAdd(month) {
	var date = new Date();
		date.setMonth((date.getMonth() + 1));
		date.setDate(0);
		date.setMonth(date.getMonth() + (month - 1));
		if(date.getDate() < 28){
			date.setDate(0);
		}else{
			switch ((date.getMonth() + 1)) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				date.setDate(31);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				date.setDate(30);
				break;
			}
		}
    return date;
}
	