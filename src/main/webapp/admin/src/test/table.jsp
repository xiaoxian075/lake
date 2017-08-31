<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="../../com/plugin/bootstrap.min.css">
	<script type="text/javascript" src="../../js/jquery.js"></script>
	<script type="text/javascript" src="../../com/plugin/bootstrap.min.js"></script>
	<script type="text/javascript" src="../../com/plugin/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="../../com/js/_common.js"></script>
	<script type="text/javascript" src="../../com/js/_bootstrap.js"></script>
	<title>分页</title>
	
	<script type="text/javascript">
		var options = {
				tb:function () {
					return $('#mytb');
				},
				url:"../../../account/selectlist.do",
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
						name:"ç»å¥å",
						render:function (data) {
							return data.loginName;
						}
					},
					{
						style:"width:100px;text-align:center",
						name:"æµç§°",
						render:function (data) {
							return data.nickName;
						}
					},
					{
						style:"width:100px;text-align:center",
						name:"å§å",
						render:function (data) {
							return data.identityName;
						}
					},
					{
						style:"width:200px;text-align:center",
						name:"èº«ä»½è¯",
						render:function (data) {
							return data.identityID;
						}
					},
					{
						style:"width:150px;text-align:center",
						name:"æ¶é´",
						render:function (data) {
							return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
						}
					},
					{
						style:"width:100px;text-align:center",
						name:"æä½",
						render:function (data) {
							var str = "";
							str += "<a href='#' style='color:#0000E3' onclick='edit("+data.id+");'>ä¿®æ¹</a>";
							str += "&nbsp;&nbsp;<a href='#' style='color:#FF0000' onclick='del("+data.id+");'>å é¤</a>";
							//str += "<input type=\"button\" value=\"ä¿®æ¹\" style=\"background:#00FFFF;color:#000000\">";
							//str += "&nbsp;&nbsp;<input type=\"button\" value=\"å é¤\" style=\"background:#FF9797;color:#FFFFFF\">";
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
		ç¨æ·åï¼<input id="userName" type="text" value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		èº«ä»½è¯ï¼<input id="identifyID" type="text" value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="æ¥è¯¢" onclick="mySearch()">&nbsp;&nbsp;
		<input type="button" value="æ¸ç©º" onclick="myClear()">
		<button type="button" onclick="myAdd();" style="float:right" ><i class="icon-search"></i>+æ°å¢</button>
	</div>
	<div id="mytb" style="margin:5px 30px 0px 30px;text-align:center"></div>
<!--    	<div id="mytb" style="text-align:center;"><table id="userTable" class="table table-striped table-bordered table-hover"></table></div>
	<div style="text-align:center;"><ul id="useroption"></ul></div>  -->

</body>
</html>
	