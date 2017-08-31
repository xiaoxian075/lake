




var _bootOpt;

function bootInit(bootOpt) {
	_bootOpt = bootOpt;
	_bootOpt["hidden_params"] = {};
	
	var text = "";
	text += "<div id='_mytb' style='text-align:center;'></div>";
	text += "<div style='text-align:center;'><ul id='_useroption'></ul></div>";
	_bootOpt.tb().html(text);
	
	_bootOpt.hidden_params = _bootOpt.params(_bootOpt.hidden_params);
	_bootInit();
}

function bootRefresh() {
	_bootInit();
}

function bootSearch() {
	_bootOpt.hidden_params["pageNum"] = 1;
	_bootOpt.hidden_params = _bootOpt.params(_bootOpt.hidden_params);
	_bootInit();
}

function _bootInit() {
	//_bootOpt.hidden_params = _bootOpt.params(_bootOpt.hidden_params);
	$.ajax({
		async:true,
		type:"post",
		url:_bootOpt.url,
		dataType:"json",
		data:_bootOpt.hidden_params,
		cache:false,
		success:function(data) {
			if (data.code!=0) {
				alert(data.desc);
				return;
			}
			data = data.info;
			_cbData(data);

			var pageNum = data.pageNum; //当前页数
			var pages = data.pages; //总页数
			if (pages==0) {
				$('#_useroption').html("");
				return;
			}

			var options = {
					bootstrapMajorVersion: 3, //版本
					currentPage: pageNum, //当前页数
					totalPages: pages, //总页数
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
					onPageChanged: function (event, oldPage, newPage) {
						_bootOpt.hidden_params["pageNum"] = newPage;
						_bootOpt.hidden_params = _bootOpt.params(_bootOpt.hidden_params);
						$.ajax({
							async:true,
							url:_bootOpt.url,
							type:"post",
							dataType:"json",
							data:_bootOpt.hidden_params,
							cache:false,
							success: function (data) {
								if (data.code!=0) {
									alert(data.desc);
									return;
								}
								_cbData(data.info);
							}
						});
					}
			};
	        $('#_useroption').bootstrapPaginator(options);
		}
	});
}

function _cbData(data) {
	var list = data.listT;
	
	var tbody="";
	tbody += "<table class='table table-striped table-bordered table-hover'>";
	tbody +=	"<tr>";
	for (var k=0; k<_bootOpt.columns.length; k++) {
		var _mystyle = "text-align:center";
		if (_bootOpt.columns[k].style==null) {
			_mystyle = _bootOpt.columns[k].style;
		}
		tbody += "<th style='"+ _mystyle+"'>"+_bootOpt.columns[k].name+"</th>";
	}
	tbody += 	"</tr>";
	

	for (var i = 0; i <list.length; i++) {
		var trs = "";
		trs += 	"<tr>";
		for (var k=0; k<_bootOpt.columns.length; k++) {
			var _mystyle = "text-align:center";
			if (_bootOpt.columns[k].style==null) {
				_mystyle = _bootOpt.columns[k].style;
			}
			tbody += "<td style='"+_mystyle+"'>"+_bootOpt.columns[k].render(list[i])+"</td>";
		}
	    trs += 	"</tr>";
	    
		tbody += trs;
	};
	
	tbody += "</table>"
		
	tbody += "<div style=\"text-align:center;\">";
	tbody += 	"共&nbsp;<label id='_pages'><font size='3' color='red'>"+data.pages+"</font></label>&nbsp;页";
	tbody += 	"&nbsp;&nbsp;跳转至<input id='_pageNum' type='text' value=1 onchange='_skip();' style='text-align:center;width:40px;height:20px' maxlength='8' size='14'>页";
	tbody += 	"&nbsp;&nbsp;每页<select id='_pageSize' onchange='_changePage();' style='width:50px;height:20px'><option value=5>5</option><option value=10>10</option><option value=20>20</option><option value=50>50</option><option value=100>100</option></select>条";
	tbody += 	"&nbsp;&nbsp;共&nbsp;<font size='3' color='red'>"+data.total+"</font>&nbsp;条";
	tbody += "</div>"
		
	$("#_mytb").html(tbody);
	
	_bootOpt.hidden_params["pageNum"] = data.pageNum;
	
	$('#_pageNum').val(data.pageNum);
	if (data.total==0)
		$('#_pageNum').val(0);
	$("#_pageSize").find("option[value="+data.pageSize+"]").attr("selected",true);
}

function _changePage() {
	var totalpage = $("#_pages").text();
	totalpage = parseInt(totalpage);
	if (totalpage<=0) {
		return;
	}
	
	var pageSize = $('#_pageSize option:selected').val();
	_bootOpt.hidden_params["pageNum"] = 1;
	_bootOpt.hidden_params["pageSize"] = pageSize;
	
	//_bootOpt.hidden_params = _bootOpt.params(_bootOpt.hidden_params);
	_bootInit();
}

function _skip() {
	var num = $("#_pageNum").val();
	var totalpage = $("#_pages").text();
	
	num = parseInt(num);
	totalpage = parseInt(totalpage);
	
	if (totalpage<=0) {
		$('#_pageNum').val(0);
		return;
	}
	
    if(isNaN(num)){
    	num = _bootOpt.hidden_params["pageNum"];
    	num = parseInt(num);
    	$('#_pageNum').val(num);
    } else {
		
		if (num<=0 || num>totalpage) {
			num = _bootOpt.hidden_params["pageNum"];
			num = parseInt(num);
		}
		$('#_pageNum').val(num);
    }
    
	$('#_useroption').bootstrapPaginator("show",num);
}