<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<title>main</title>
</head>
<body>
  <div class="container">
    <br><br>
    <s:form cssClass="text-center" action="tree_view">
      <s:submit type="button" value="DVD一覧" cssClass="btn btn-primary" />
    </s:form>



  </div>
</body>
</html>
