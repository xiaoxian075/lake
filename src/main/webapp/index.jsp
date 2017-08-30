<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="./plugin/bootstrap.min.css">
	<script type="text/javascript" src="./plugin/jquery.min.js"></script>
	<script type="text/javascript" src="./plugin/bootstrap.min.js"></script>
	<script type="text/javascript" src="./plugin/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="./_common.js"></script>
	<script type="text/javascript" src="./_bootstrap.js"></script>
	<title>分页</title>
	
	<script type="text/javascript">
		var options = {
				tb:function () {
					return $('#mytb');
				},
				url:"account/selectlist.do",
 				params:function (param) {
 					param["userName"] = $("#userName").val();
 					param["identifyID"] = $("#identifyID").val();
					return param;
				},
				columns : [
					{
						"width":"40px",
						"name":"ID",
						"render":function (data) {
							return data.id;
						}
					},
					{
						"width":"100px",
						"name":"用户名",
						"render":function (data) {
							return data.userName;
						}
					},
					{
						"width":"100px",
						"name":"登入名",
						"render":function (data) {
							return data.loginName;
						}
					},
					{
						"width":"100px",
						"name":"昵称",
						"render":function (data) {
							return data.nickName;
						}
					},
					{
						"width":"100px",
						"name":"姓名",
						"render":function (data) {
							return data.identityName;
						}
					},
					{
						"width":"200px", 
						"name":"身份证",
						"render":function (data) {
							return data.identityID;
						}
					},
					{
						"width":"150px",
						"name":"时间",
						"render":function (data) {
							return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
						}
					},
					{
						"width":"100px",
						"name":"操作",
						"render":function (data) {
							var str = "";
							str += "<a href='#' style='color:#0000E3' onclick='edit("+data.id+");'>修改</a>";
							str += "&nbsp;&nbsp;<a href='#' style='color:#FF0000' onclick='del("+data.id+");'>删除</a>";
							//str += "<input type=\"button\" value=\"修改\" style=\"background:#00FFFF;color:#000000\">";
							//str += "&nbsp;&nbsp;<input type=\"button\" value=\"删除\" style=\"background:#FF9797;color:#FFFFFF\">";
							return str;
						}
					}
				]
		};

		$(function(){
			bootInit(options);
		});
		
		function mySearch() {
 			bootSearch();
		}
		function myClear() {
			$("#userName").val("");
			$("#identifyID").val("");
			bootSearch();
		}
		function edit(id) {
			alert("edit "+id);
			bootRefresh();
		}
		function del(id) {
			alert("del "+id);
		}
	</script>
</head>
<body>
	<div id="mysearch" style="margin:20px 30px 5px 30px;text-align:left">
		用户名：<input id="userName" type="text" value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		身份证：<input id="identifyID" type="text" value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="查询" onclick="mySearch()">&nbsp;&nbsp;
		<input type="button" value="清空" onclick="myClear()">
	</div>
	<div id="mytb" style="margin:5px 30px 0px 30px;text-align:center"></div>
<!--    	<div id="mytb" style="text-align:center;"><table id="userTable" class="table table-striped table-bordered table-hover"></table></div>
	<div style="text-align:center;"><ul id="useroption"></ul></div>  -->

</body>
</html>
	