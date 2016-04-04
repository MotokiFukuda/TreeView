<%@page import="com.rental.treedvd.dto.LevelDvdDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="design/js/bootstrap.js"></script>
<link href="design/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript">
  google.load("jquery", "1.2.x");
</script>
<link rel="stylesheet" href="design/jquery.treeview.css" />
<script src="js/jquery.treeview.js" type="text/javascript"></script>

<title>tree</title>
</head>
<body>
  <div class="container">
    <br> <br> <br>
    <div class="row">

      <!-- ツリービュー表示 -->
      <div class="col-md-4">
        <a
          href="<s:url action="tree_view"><s:param name ="id" value="1"/></s:url>"
          style="margin-left: 25px; margin-bottom: 1em;">一覧表示 </a>

        <ul style="list-style: none; padding-left: 0;">
          <s:iterator value="itemList">
            <li style="text-indent:calc(<s:property value='level'/>em * 2);">
              <s:a action="tree_view?id=%{id}&item=%{item}&isLeaf=%{isLeaf}">
                <s:property value='item' />
              </s:a>
            </li>
          </s:iterator>
        </ul>
      </div>
      <!-- col-sm-6 -->

      <!-- テーブル表示 -->
      <div class="col-md-8">
        <table class="table table-condensed table-bordered text-center">

          <!-- 選択されたら表の一番上に親のアイテムを表示-->
          <s:if test="%{id!=1}">
            <tr class="text-center" style="background-color: #91E0CA;">
              <td colspan="4"><b><s:property value='item' /></b></td>
            </tr>
          </s:if>

          <s:if test="%{listLength==0}">
            <tr class="success">
              <td><b>ID</b></td>
              <td><b>リスト</b></td>
              <td colspan="2"></td>
            </tr>
          </s:if>
          <s:else>
            <tr class="success">
              <s:if test="%{isLeaf==1}">
                <td><b>ID</b></td>
                <td><b>リスト</b></td>
                <td><b>IMG</b></td>
                <td></td>
              </s:if>
              <s:else>
                <td><b>ID</b></td>
                <td><b>IMG</b></td>
                <td><b>商品</b></td>
                <td></td>
              </s:else>
            </tr>
          </s:else>
          <!-- 一覧表示 -->
          <s:if test="%{id==1}">
            <s:iterator value="itemList">
              <s:form action="edit?id=%{id}&pid=%{pid}">
                <tr>
                  <td><s:property value="id" /></td>
                  <td><s:textfield cssClass="form-control input-sm"
                      name="item" value='%{item}' /></td>
                  <td><s:property value="pid" /></td>
                  <td><s:submit cssClass="btn btn-sm"
                      style="margin-right:2em" value="更新" name="editButton" /> <s:submit
                      cssClass="btn btn-warning btn-sm" style="margin-left:2em"
                      value="削除" name="editButton" /></td>
                </tr>
              </s:form>
            </s:iterator>
          </s:if>

          <s:else>
            <s:iterator value="selectedList">
              <s:if test="%{pid==selectedId}">
                <s:form action="edit?id=%{id}&pid=%{pid}"
                  cssClass="form-horizontal" role="form">
                  <tr>
                    <td><s:property value="id" /></td>
                    <!-- 商品一覧のとき -->
                    <s:if test="%{isLeaf==1}">
                      <td><img src='./imges/<s:property value="imgPath" />'
                        class="img-rounded" width="60" height="65" /></td>
                    </s:if>
                    <!-- 商品一覧のとき -->
                    <s:if test="%{isLeaf==1}">
                      <td>
                        <div class="form-group">
                          <label class="control-label col-sm-3" for="text"
                            style="padding: 0px 5px; margin-top: 5px; text-align: right;">タイトル :</label>
                          <div class="col-sm-9" style="padding: 0px 5px;">
                            <s:textfield cssClass="form-control input-sm" name="item"
                              value='%{item}' />
                          </div>
                        </div> <br>
                        <div class="form-group">
                          <label class="control-label col-sm-3" for="text"
                            style="padding: 0px 5px; margin-top: 5px; text-align: right;">料金 :</label>
                          <div class="col-sm-6" style="padding: 0px 5px;">
                            <s:textfield cssClass="form-control input-sm" name="price"
                              value='%{price}' />
                          </div>
                          <label class="control-label col-sm-3" for="text"
                            style="padding: 0px 5px; margin-top: 5px; text-align: left;">円</label>
                        </div>

                      </td>
                      <td><s:submit cssClass="btn btn-sm"
                          style="margin-right:2em" value="更新" name="editButton" /> <s:submit
                          cssClass="btn btn-warning btn-sm" style="margin-left:2em"
                          value="削除" name="editButton" /></td>
                    </s:if>
                    <!-- カテゴリ一覧のとき -->
                    <s:else>
                      <td><s:textfield cssClass="form-control input-sm"
                          name="item" value='%{item}' /></td>
                      <td><s:submit cssClass="btn btn-sm"
                          style="margin-right:2em" value="更新" name="editButton" /> <s:submit
                          cssClass="btn btn-warning btn-sm" style="margin-left:2em"
                          value="削除" name="editButton" /></td>
                    </s:else>
                  </tr>
                </s:form>
              </s:if>
            </s:iterator>
          </s:else>

          <s:if test="%{id!=1}">
            <s:form action="add?id=%{id}&pid=%{pid}">
              <tr>
                <td>新規</td>
                <td><s:textfield cssClass="form-control input-sm"
                    name="item" value="" size="15" /></td>
                <td colspan="2"><s:submit
                    cssClass="btn btn-info pull-right" style="margin-right:1em"
                    value="追加" name="addButton" /></td>

                <s:if test="%{isLeaf==1}">
                  <td>新規</td>
                  <td></td>
                  <td><s:textfield cssClass="form-control input-sm"
                           name="item" value="" size="15" /></td>
                  <td colspan="2"><s:submit
                    cssClass="btn btn-info pull-right" style="margin-right:1em"
                    value="追加" name="addButton" /></td>
                </s:if>
              </tr>
            </s:form>
          </s:if>

        </table>

      </div>
      <!-- col-sm-8 -->

    </div>
    <!-- row -->
  </div>
  <!-- container -->
</body>
</html>
