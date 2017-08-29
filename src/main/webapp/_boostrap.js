var GOption;

function ptinit(option) {
	GOption = option;
	
	var text = "";
	text += "<div id=\"mytb\" style=\"text-align:center;\"></div>";
	text += "<div style=\"text-align:center;\"><ul id=\"useroption\"></ul></div>";
	option.tb().html(text);
	
	
	_init(option, _postAjax, _cbData, _cbBoot);
}


function _init(option, postAjax, cbData, cbBoot) {
	$.ajax({
		async: true,
		url: option.url,
		type: "post",
		dataType : "json",
		data: option.params,
		cache: false,
		success: function (data) {
			if (data.code!=0) {
				alert(data.desc);
				return;
			}
			data = data.info;
			cbData(option,data);
			_setChange(data.pageNum);
			_setSize(data.pageSize);
			
			var currentPage = data.pageNum; //当前页数
			var pageCount = data.pages; //总页数
			cbBoot(currentPage, pageCount, option, cbData, postAjax);
		}
    });
	

}

function _cbBoot(currentPage, pageCount, option, cbData, postAjax) {
	
	var options = {
			bootstrapMajorVersion: 3, //版本
			currentPage: currentPage, //当前页数
			totalPages: pageCount, //总页数
			numberOfPages: 5,
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
				option.params["pageNum"] = newPage;
				postAjax(option, cbData);
			}
	};
	$('#useroption').bootstrapPaginator(options);
}

function _change() {
	var num = $('#myPageIndex').val();
	var inum = parseInt(num);//如果变量val是字符类型的数则转换为int类型 如果不是则ival为NaN
    if(isNaN(inum)){
    	inum = GOption.params["pageNum"];
    	$('#myPageIndex').val(inum);
    } else {
		var totalpage = $("#_mytotalpage").text();
		totalpage = parseInt(totalpage);
		if (inum<=0 || inum>totalpage) {
			inum = GOption.params["pageNum"];
		}
		$('#myPageIndex').val(inum);
    }
		
	$('#useroption').bootstrapPaginator("show",inum);
}

function _setChange(num) {
	$('#myPageIndex').val(num);
}

function _setSize(num) {
	$("#_myselect").find("option[value='"+num+"']").attr("selected",true);
}

function myselectf() {
	var pageSize = $('#_myselect option:selected').val();
	
	var curPage = GOption.params["pageNum"];

	GOption.params["pageNum"] = 1;
	GOption.params["pageSize"] = pageSize;
	
	ptinit(GOption);
	
//	if (curPage==1) {
//		_postAjax(GOption, _cbData);
//	} else {
//		$('#useroption').bootstrapPaginator("show",1);
//		_postAjax(GOption, _cbData);
//	}
//	
}


function _cbData(option,data) {
	var list = data.listT;
	
	var tbody="";
	tbody += "<table class=\"table table-striped table-bordered table-hover\">";
	tbody +=	"<tr>";
	for (var k=0; k<option.columns.length; k++) {
		if (option.columns[k].width==null) {
			tbody += "<th style=\"text-align:center\">"+option.columns[k].name+"</th>";
		} else {
			tbody += "<th style=\"width:"+option.columns[k].width+";text-align:center\">"+option.columns[k].name+"</th>";
		}
	}
	tbody += 	"</tr>";
	

	for (var i = 0; i <list.length; i++) {
		var trs = "";
		trs += 	"<tr>";
		for (var k=0; k<option.columns.length; k++) {
			if (option.columns[k].width==null) {
				tbody += "<td style=\"text-align:center\">"+option.columns[k].render(list[i])+"</td>";
			} else {
				tbody += "<td style=\"width:"+option.columns[k].width+";text-align:center\">"+option.columns[k].render(list[i])+"</td>";
			}
		}
	    trs += 	"</tr>";
	    
		tbody += trs;
	};
	
	tbody += "</table>"
		
	tbody += "<div style=\"text-align:center;\">";
	tbody += 	"共&nbsp;<label id='_mytotalpage'><font size='3' color='red'>"+data.pages+"</font></label>&nbsp;页";
	tbody += 	"&nbsp;&nbsp;跳转至<input id=\"myPageIndex\" type=\"text\" value=1 onchange=\"_change();\" style=\"text-align:center;width:40px;height:20px\" onkeyup=\"this.value=this.value.replace(/\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\D/g,'')\" maxlength=\"8\" size=\"14\">页";
	tbody += 	"&nbsp;&nbsp;每页<select id='_myselect' onchange='myselectf();' style='width:50px;height:20px'><option value='5'>5</option><option value='10'>10</option><option value='20'>20</option><option value='50'>50</option></select>条";
	tbody += 	"&nbsp;&nbsp;共&nbsp;<font size='3' color='red'>"+data.total+"</font>&nbsp;条";
	tbody += "</div>"
		
	$("#mytb").html(tbody);
	
	option.params["pageNum"] = data.pageNum;
}

function _postAjax(option, cbData) {
	
	$.ajax({
		async: true,
		url: option.url,
		type: "post",
		dataType : "json",
		data: option.params,
		cache: false,
		success: function (data) {
			if (data.code!=0) {
				alert(data.desc);
				return;
			}
			data = data.info;
			cbData(option, data);
			_setChange(data.pageNum);
			_setSize(data.pageSize);
		}
    });
}