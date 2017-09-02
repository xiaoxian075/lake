
//var base = window.location.host;
var base = getRootPath_dc();
function getRootPath_dc() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	if (webName == "") {     
		return window.location.protocol + '//' + window.location.host;   
	} else {
		return window.location.protocol + '//' + window.location.host + '/' + webName;   
	} 
} 


var _hytf_arrnet = {
		"login":"login/login.do",
		"account_selectlist":"account/selectlist.do",
		"area_selectlist":"area/selectlist.do"
};

function hytf_geturl(id) {
	return base+'/'+_hytf_arrnet[id];
}

