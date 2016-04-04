<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>商品管理画面</title>
<link rel="stylesheet" type="text/css" href="css/store.css">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<link rel="stylesheet" type="text/css" href="jquery.treeview.css">
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquery.treeview.js"></script>
<script type="text/javascript" src="js/demo.js"></script>
<style>
/*表の階層構造ごとに色を付ける*/
.level1 {
  background-color: orange;
}

.level2 {
  background-color: gold;
}

.level3 {
  background-color: yellow;
}

.level4 {
  background-color: khaki;
}

.add {
  background-color: plum;
}
</style>

</head>
<body>
  <div class="container">
    <!-- ヘッダ -->
    <div class="row">
      <div class="navbar navbar-default navbar-fixed-top">
        <div class="navbar-header">
          <a href="index.jsp" class="navbar-brand">Home</a>
        </div>
        <ul class="nav navbar-nav">
          <li class="active"><s:a action="item">商品管理</s:a></li>
          <li><s:a action="store">店舗管理</s:a></li>
          <li><s:a action="search">商品検索</s:a></li>
        </ul>
      </div>
    </div>

    <!-- ジャンボトロン -->
    <div class="jumbotron" style="margin-top: 60px">
      <h1 class="text-center text-muted">商品管理画面</h1>
    </div>

    <!-- メインコンテンツ -->
    <div class="row">

      <!-- ツリービュー -->
      <div class="col-md-3">
        <h2>ツリービュー</h2>


        <!-- ツリービュー[第一版]
<s:iterator value="itemListForTreeView">
<s:if test="%{level==1}">
<ul id="browser" class="treeview-famfamfam">
<li><span><s:a href="select-item?itemId=%{itemId}&level=1"><s:property value="itemName"/></s:a></span>
  <s:iterator value="itemListForTreeView">
  <s:if test="%{level==2}">
  <ul>
  <li><span>
    [カテゴリ]<s:a href="select-item?itemId=%{itemId}&level=2"><s:property value="itemName"/></s:a>
    <s:set name="commodity" value="%{itemId}" scope="session"/></span>
    <s:iterator value="itemListForTreeView">
    <s:if test="%{pid==#session.commodity}">
    <ul>
    <li class="closed"><span>
      [商品名]<s:a href="select-item?itemId=%{itemId}&level=3"><s:property value="itemName"/></s:a>
      <s:set name="material" value="%{itemId}" scope="session"/></span>
      <s:iterator value="itemListForTreeView">
      <s:if test="%{pid==#session.material}">
      <ul>
      <li><span>[原材料]<s:property value="itemName"/></span></li>
      </ul>
      </s:if>
      </s:iterator>
    </li>
    </ul>
    </s:if>
    </s:iterator>
  </li>
  </ul>
  </s:if>
  </s:iterator>
</li>
</ul>
</s:if>
</s:iterator>
<%//ツリービュー表示のために使ったsessionをクリア
      session.removeAttribute("catogory");
      session.removeAttribute("commodity");
      session.removeAttribute("material");%>
-->

        <!-- ツリービュー[第二版] -->
        <ul>
          <s:iterator value="itemListForTreeView">
            <!--
  前のデータのレベルより自分のデータのレベルが
  大きいか小さいか同じかでs:ifで分けたい
  ・前要素のlevelがこの要素のlevelより低いとき
  さらに深くしたいので<ul><li>を追加
   -->
            <li><s:a action="select-item?itemId=%{itemId}&level=%{level}">
                <s:property value="itemName" />
              </s:a></li>
          </s:iterator>
        </ul>

      </div>

      <!-- 一覧表 -->
      <div class="col-mod-9">
        <h2>
          <s:property value="itemName" />
          -要素一覧
        </h2>
        <table class="table" style="width: 70%">
          <thead>
            <tr>
              <th>ID</th>
              <th>画像</th>
              <th>アイテム名</th>
              <th>PID</th>
              <th>売価</th>
              <th>原価</th>
              <th>編集</th>
            </tr>
          </thead>
          <tbody>
            <s:iterator value="itemList" status="stat">
              <s:form action="edit-item">
                <s:hidden name="level" value="%{level}" />
                <s:hidden name="itemImagePath" value="%{itemImagePath}" />
                <s:hidden name="itemId" value="%{itemId}" />
                <tr class="level<s:property value='level'/>">
                  <td><s:property value="itemId" /></td>
                  <td><s:if test="%{level==3}">
                      <img src="images/upload/<s:property value='itemImagePath'/>"
                        width="50px" height="30px">
                    </s:if> <s:if test="%{level==2}">カテゴリ</s:if> <s:if test="%{level>=4}">原材料</s:if>
                  </td>
                  <td><span class="edit-off"><s:property
                        value="itemName" /></span> <span class="edit-on"><s:textfield
                        class="form-control" name="itemName" value="%{itemName}"
                        maxlength="20" /></span></td>
                  <td><s:property value="pid" />
                    <s:hidden name="pid" value="%{pid}" /></td>
                  <td>
                    <!-- 商品のときだけ売価が変更できる --> <s:if test="%{level==3}">
                      <span class="edit-off"><s:property value="itemPrice" /></span>
                      <span class="edit-on"><s:hidden name="editFlag"
                          value="price" />
                        <s:textfield class="form-control" name="itemPrice"
                          value="%{itemPrice}" maxlength="20" /></span>
                    </s:if> <s:else>
                      <s:property value="itemPrice" />
                    </s:else>
                  </td>
                  <td>
                    <!-- 原材料のときだけ原価が変更できる --> <s:if
                      test="%{level>=4 && #stat.count>1}">
                      <span class="edit-off"><s:property value="itemCost" /></span>
                      <span class="edit-on"><s:hidden name="itemCost"
                          value="%{itemCost}" />
                        <s:hidden name="editFlag" value="cost" /> <s:textfield
                          class="form-control" name="itemCostChange"
                          value="%{itemCost}" maxlength="20" /></span>
                    </s:if> <s:else>
                      <s:property value="itemCost" />
                    </s:else>
                  </td>
                  <td><s:if test="%{level==1}">
                      <input type="button" class="btn btn-default" value="編集不可"
                        disabled="disabled" />
                    </s:if> <s:else>
                      <s:submit class="btn btn-default" value="修正"
                        name="editButton" />
                      <s:submit class="btn btn-default" value="削除"
                        name="editButton" />
                    </s:else></td>
                </tr>
              </s:form>
            </s:iterator>
          </tbody>
          <!-- アイテム追加フォーム -->
          <tfoot>
            <s:form class="form-inline" action="add-item"
              enctype="multipart/form-data" method="post">
              <s:hidden name="level" value="%{level}" />
              <s:hidden name="pid" value="%{itemId}" />
              <s:hidden name="categoryId" value="%{categoryId}" />
              <tr class="add">
                <th colspan="7"><s:if test="%{level==2}">
                    <s:property value="itemName" />カテゴリを追加</s:if> <s:if
                    test="%{level==3}">カテゴリ『<s:property value="itemName" />』に商品を追加</s:if>
                  <s:if test="%{level==4}">『<s:property value="itemName" />』に原材料を追加</s:if></th>
              </tr>
              <tr class="add">
                <s:if test="%{level==2}">
                  <td colspan="6">カテゴリ名<s:textfield class="form-control"
                      name="itemName" value="" /></td>
                </s:if>
                <s:if test="%{level==3}">
                  <td colspan="2">商品名<s:textfield class="form-control"
                      name="itemName" value="" /></td>
                  <td>売価<s:textfield class="form-control" name="itemPrice"
                      value="" size="10" /></td>
                  <td colspan="3">画像<s:file class="form-control"
                      name="itemImage" label="Item Image" /></td>
                </s:if>
                <s:if test="%{level>=4}">
                  <s:hidden name="editFlag" value="%{itemList.size()}" />
                  <s:hidden name="itemCostChange"
                    value="%{itemList.get(0).getItemCost()}" />
                  <td colspan="3">原材料名<s:textfield class="form-control"
                      name="itemName" value="" /></td>
                  <td colspan="3">原価<s:textfield class="form-control"
                      name="itemCost" value="" /></td>
                </s:if>
                <td><s:submit class="btn btn-default" value="追加する" /></td>
              </tr>
            </s:form>
          </tfoot>
        </table>
      </div>

      <s:debug></s:debug>
    </div>
    <script type="text/javascript" src="js/store.js"></script>

  </div>
</body>
</html>
