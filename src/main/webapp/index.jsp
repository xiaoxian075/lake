<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="./plugin/bootstrap.min.css">
	<script type="text/javascript" src="./plugin/jquery.min.js"></script>
	<script type="text/javascript" src="./plugin/bootstrap.min.js"></script>
	<script type="text/javascript" src="./plugin/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="./_common.js"></script>
	<script type="text/javascript" src="./_boostrap.js"></script>
	<title>分页</title>
	
	<script type="text/javascript">
		var _option = [
			{
				"name":"ID",
				"render":function (data) {
					return data.id;
				}
			},
			{
				"name":"用户名",
				"render":function (data) {
					return data.userName;
				}
			},
			{
				"name":"登入名",
				"render":function (data) {
					return data.loginName;
				}
			},
			{
				"name":"昵称",
				"render":function (data) {
					return data.nickName;
				}
			},
			{
				"name":"姓名",
				"render":function (data) {
					return data.identityName;
				}
			},
			{
				"name":"身份证",
				"render":function (data) {
					return data.identityID;
				}
			},
			{
				"name":"时间",
				"render":function (data) {
					//return data.createTime;
					return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
				}
			}
		];
		
	
		$(function(){
			ptinit($('#mydb'), "test.do", {}, _option);
			
		});
	</script>
</head>
<body>
	<div id="mydb"></div>
</body>
</html>
	