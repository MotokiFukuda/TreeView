<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html lang="ja">
<head>
<s:head theme="ajax" debug="true" />

<title>My page</title>

</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/common.css" type="text/css">
<link rel="stylesheet" href="/css/displaytag.css" type="text/css">

</head>
<body>
<s:debug></s:debug>
	<br />
<!--<s:i18n name="commons">
			<sx:tree id="tree" rootNode="%{root}" nodeIdProperty="nodeId"
				nodeTitleProperty="title" childCollectionProperty="child"
				 />
	</s:i18n>-->
	<s:iterator value="%{root}">

	</s:iterator>
</body>
</html>