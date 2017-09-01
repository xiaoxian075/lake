
function ajaxPost(url,params,cbOK) {
	$.ajax({
		async:true,
		url:url,
		type:"post",
		dataType:"json",
		data:params,
		cache:false,
		success: function (data) {
			if (data.code!=0) {
				swal(data.desc);
				return;
			}
			cbOK(data.info);
		}
	});
}

function ajaxPost2(url,params,cbOK,cbErr) {
	$.ajax({
		async:true,
		url:url,
		type:"post",
		dataType:"json",
		data:params,
		cache:false,
		success: function (data) {
			if (data.code!=0) {
				//swal(data.desc);
				cbErr(data);
				return;
			}
			cbOK(data.info);
		}
	});
}

Date.prototype.format = function(format){
	    var o = {
	        "M+" : this.getMonth()+1, //month
	        "d+" : this.getDate(), //day
	        "h+" : this.getHours(), //hour
	        "m+" : this.getMinutes(), //minute
	        "s+" : this.getSeconds(), //second
	        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
	        "S" : this.getMilliseconds() //millisecond
	    }

	    if(/(y+)/.test(format)) {
	        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	    }

	    for(var k in o) {
	        if(new RegExp("("+ k +")").test(format)) {
	            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
	        }
	    }
	    return format;
	}