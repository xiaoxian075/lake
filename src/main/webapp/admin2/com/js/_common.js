var _VERSION = "yzyj-admin-1.0.0";
var _ENCRYPT = "none";

function hytf_ajaxPost(url,params,cbOK,cbErr) {
	var _params = {
			"version":_VERSION,
			"encrypt":_ENCRYPT,
			"random":0,
			"data":JSON.stringify(params)
	}
	$.ajax({
		async:true,
		url:url,
		type:"post",
		dataType:"json",
		data:_params,
		cache:false,
		success: function (data) {
			if (data.code!=0) {
				if (cbErr==undefined) {
					swal(data.desc);
				} else {
					cbErr(data);
				}
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