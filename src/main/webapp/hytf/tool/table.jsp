<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="../../plugin/bootstrap.min.css">
	<script type="text/javascript" src="../../plugin/jquery.min.js"></script>
	<script type="text/javascript" src="../../plugin/bootstrap.min.js"></script>
	<script type="text/javascript" src="../../plugin/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="../js/_common.js"></script>
	<script type="text/javascript" src="../js/_bootstrap.js"></script>
	<title>分页</title>
	
	<script type="text/javascript">
		var options = {
				tb:function () {
					return $('#mytb');
				},
				url:"../../account/selectlist.do",
 				params:function (param) {
 					param["userName"] = $("#userName").val();
 					param["identifyID"] = $("#identifyID").val();
					return param;
				},
				columns : [
					{
						style:"width:20px;text-align:center",
						name:"<input id='imybox' class='mybox' type='checkbox' onclick='batchPitch(this);' />",
						render:function (data) {
							return "<input class='mybox' type='checkbox' value='"+data.id+"' onclick='pitch(this);'/>";
						}
						
					},
					{
						style:"width:40px;text-align:center",
						name:"ID",
						render:function (data) {
							return data.id;
						}
					},
					{
						style:"width:100px;text-align:center",
						name:"用户名",
						render:function (data) {
							return data.userName;
						}
					},
					{
						style:"width:100px;text-align:center",
						name:"登入名",
						render:function (data) {
							return data.loginName;
						}
					},
					{
						style:"width:100px;text-align:center",
						name:"昵称",
						render:function (data) {
							return data.nickName;
						}
					},
					{
						style:"width:100px;text-align:center",
						name:"姓名",
						render:function (data) {
							return data.identityName;
						}
					},
					{
						style:"width:200px;text-align:center",
						name:"身份证",
						render:function (data) {
							return data.identityID;
						}
					},
					{
						style:"width:150px;text-align:center",
						name:"时间",
						render:function (data) {
							return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
						}
					},
					{
						style:"width:100px;text-align:center",
						name:"操作",
						render:function (data) {
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
		<button type="button" onclick="myAdd();" style="float:right" ><i class="icon-search"></i>+新增</button>
	</div>
	<div id="mytb" style="margin:5px 30px 0px 30px;text-align:center"></div>
<!--    	<div id="mytb" style="text-align:center;"><table id="userTable" class="table table-striped table-bordered table-hover"></table></div>
	<div style="text-align:center;"><ul id="useroption"></ul></div>  -->

</body>
</html>
	