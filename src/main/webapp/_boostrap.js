


//var _option = [
//	{
//		"name":"ID",
//		"render":function (data) {
//			return data.id;
//		}
//	},
//	{
//		"name":"用户名",
//		"render":function (data) {
//			return data.userName;
//		}
//	},
//	{
//		"name":"登入名",
//		"render":function (data) {
//			return data.loginName;
//		}
//	},
//	{
//		"name":"昵称",
//		"render":function (data) {
//			return data.nickName;
//		}
//	},
//	{
//		"name":"姓名",
//		"render":function (data) {
//			return data.identityName;
//		}
//	},
//	{
//		"name":"身份证",
//		"render":function (data) {
//			return data.identityID;
//		}
//	}
//];
//
//$(function(){
//	
//	ptinit($('#mydb'), "test.do", {}, _option);
//	
//});



function ptinit(textid, url, params, option) {
	var text = "";
	text += "<div id=\"mytb\"></div>";
	text += "<div style=\"text-align:center;\"><ul id=\"useroption\"></ul></div>";
	textid.html(text);
	
	params["page"] = 1;
	_init(url, params, option, _postAjax, _cbData, _cbBoot);
}


function _init(url, params, option, postAjax, cbData, cbBoot) {
	$.ajax({
		async: true,
		url: url,
		type: "post",
		dataType : "json",
		data: params,
		cache: false,
		success: function (data) {
			var list = data.list;
			cbData(option,list);
			
			var currentPage = data.CurrentPage; //当前页数
			var pageCount = data.pageCount; //总页数
			cbBoot(currentPage, pageCount, url, params, option, cbData, postAjax);
		}
    });
	

}

function _cbBoot(currentPage, pageCount, url, params, option, cbData, postAjax) {
	
	

	var options = {
			bootstrapMajorVersion: 3, //版本
			currentPage: currentPage, //当前页数
			totalPages: pageCount, //总页数
			numberOfPages: 3,
			shouldShowPage:true,//是否显示该按钮
			itemTexts: function (type, page, current) {
				switch (type) {
					case "first":
						return "首页";
					case "prev":
						return "上一页";
					case "next":
						return "下一页";
					case "last": 
						return "末页";
					case "page": 
						return page;
					
				}
			},
			onPageChanged:function (event, oldPage, newPage) {
				params["page"] = newPage;
				postAjax(url, params, option, cbData);
			}
	};
	$('#useroption').bootstrapPaginator(options);
}

function _change() {
	var num = $('#myPageIndex').val();
	$('#useroption').bootstrapPaginator("show",num);
}

function _setChange(num) {
	$('#myPageIndex').val(num);
}


function _cbData(option,data) {
	var tbody="";
	tbody += "<table class=\"table table-striped table-bordered table-hover\">";
	tbody +=	"<tr>";
	for (var k=0; k<option.length; k++) {
		tbody += "<th>"+option[k].name+"</th>";
	}
	tbody += 	"</tr>";
	

	for (var i = 0; i <data.length; i++) {
		var trs = "";
		trs += 	"<tr>";
		for (var k=0; k<option.length; k++) {
			tbody += "<td>"+option[k].render(data[i])+"</td>";
		}
	    trs += 	"</tr>";
	    
		tbody += trs;
	};
	
	tbody += "</table>"
		
	tbody += "<div style=\"text-align:center;\">跳转至<input id=\"myPageIndex\" type=\"text\" value=1 onchange=\"_change();\" style=\"text-align:center;width:40px\"></div>"
		
	$("#mytb").html(tbody);
}
function _postAjax(url, params, option, cbData) {
	$.ajax({
		async: true,
		url: url,
		type: "post",
		dataType : "json",
		data: params,
		cache: false,
		success: function (data) {
			var list = data.list;
			cbData(option, list);
			_setChange(params["page"]);
		}
    });
}