<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="design/js/bootstrap.js"></script>
<link href="design/css/bootstrap.css" rel="stylesheet">
<title>tree</title>
</head>
<body>
<br>
<div class="container">
	<s:iterator value="viewList">
		<s:property value="item" />
		<br>
	</s:iterator>

</div>
</body>
</html>