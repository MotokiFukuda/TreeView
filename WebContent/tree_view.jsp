<%@page import="com.rental.treedvd.dto.LevelDvdDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript">
  google.load("jquery", "1.2.x");
</script>
<link rel="stylesheet" href="design/jquery.treeview.css" />
<link rel="stylesheet" href="css/tree_view.css" >
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
          class="display-list">一覧表示 </a>

        <ul class="ul-style">
          <s:iterator value="itemList">
            <li style="text-indent:calc(<s:property value='level'/>em * 2);">
              <!-- 商品要素の色を変える --> <s:if test="%{level==4}">
                <s:a
                  action="tree_view?id=%{id}&item=%{item}&pid=%{pid}&level=%{level}"
                  cssClass="blue">
                  <s:property value='item' /><!--lv.<s:property value='level' /> pid.<s:property value='pid' />-->
                </s:a>
              </s:if> <s:else>
                <s:a
                  action="tree_view?id=%{id}&item=%{item}&pid=%{pid}&level=%{level}">
                  <s:property value='item' /><!--lv.<s:property value='level' /> pid.<s:property value='pid' />-->
                </s:a>
              </s:else>
            </li>
          </s:iterator>
        </ul>
      </div>
      <!-- col-sm-4 -->

      <!-- テーブル表示 -->
      <div class="col-md-8">
        <!-- 選択されたら表の一番上に親のアイテムを表示-->
        <table class="table table-condensed table-bordered text-center">
          <s:if test="%{id!=1}">
            <s:if test="%{id==2}"><!-- すべてのカテゴリのとき -->
              <tr class="text-center table-title">
                <td colspan="4"><s:property value='item' /></td>
              </tr>
            </s:if>
            <s:else>
              <tr class="text-center table-title">
                <td colspan="4"><s:property value='oyaItem' />&nbsp;&nbsp;&gt;&nbsp;&nbsp;<s:property
                    value='item' /></td>
              </tr>
            </s:else>
          </s:if>
        </table>

              <!-- 検索結果 -->

        <s:iterator value="pathList">
            <s:property value="item"/>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
            <br>
            <s:property value="imgpath"/>
            <s:property value="mes"/>
        </s:iterator>


        <!-- 一覧表示用のテーブル -->
        <s:if test="%{id==1}">
        <!-- 検索フォーム -->
        <s:form action="search" class="form-inline pull-right" role="form">
          <div class="form-group">
            <label>商品を検索 : </label>
            <s:textfield cssClass="form-control input-sm" name="item" value="" />
          </div>
          <s:submit cssClass="btn btn-default btn-sm" value="検索" />
        </s:form>
        <br><br>
          <table class="table table-condensed table-bordered text-center">
            <tr class="success">
              <td><b>ID</b></td>
              <td><b>商品一覧</b></td>
            </tr>
            <s:iterator value="itemList">
              <!-- 商品要素に色をつける -->
              <s:if test="%{level==4}">
                <tr class="productList-title">
                  <td><s:property value="id" /></td>
                  <td><s:property value="item" /></td>
                </tr>
              </s:if>
            </s:iterator>
          </table>
        </s:if>
        <s:else>
          <!-- カテゴリー要素があればカテゴリテーブル出力 -->
            <s:if test="%{level==1||level==2}">
            <s:if test="%{listSize!=0}">
              <table class="table table-condensed table-bordered text-center">
                <tr class="success">
                  <td><b>ID</b></td>
                  <td><b>カテゴリ</b></td>
                  <td></td>
                </tr>
                <s:iterator value="selectedList">
                  <s:if test="%{pid==selectedId}">
                    <s:form action="edit?id=%{id}&pid=%{pid}&level=%{level}">
                      <tr>
                        <td><s:property value="id" /></td>
                        <td><s:textfield cssClass="form-control input-sm"
                            name="item" value='%{item}' /></td>
                        <td><s:submit cssClass="btn btn-default btn-sm btn_update_c"
                            value="更新" name="editButton" />
                          <s:submit cssClass="btn btn-warning btn-sm btn_delete_c"
                            value="削除" name="editButton" /></td>
                      </tr>
                    </s:form>
                  </s:if>
                </s:iterator>
              </table>
            </s:if><!-- test="%{listSize!=0} -->

            <!-- 新規カテゴリ追加フォーム -->
            <s:form action="add?id=%{id}&pid=%{pid}&level=%{level}"
              cssClass="form-inline" role="form">
              <div class="form-group">
                <label for="text">新規カテゴリ : </label>
                <s:textfield cssClass="form-control" name="item" value=""
                  size="43" />
                <s:submit cssClass="btn btn-default" value="追加" />
              </div>
            </s:form>
          </s:if><!-- test="%{level==1||level==2}" -->
        </s:else>

        <!-- 3LVカテゴリクリック時、商品要素があれば商品テーブル出力-->
        <s:if test="%{level==3}">
        <s:if test="%{listSize!=0}">
          <table class="table table-condensed table-bordered text-center">
            <tr class="success">
              <td><b>ID</b></td>
              <td><b>IMG</b></td>
              <td><b>商品</b></td>
              <td></td>
            </tr>
            <s:iterator value="selectedList" status="st">
              <s:if test="%{pid==selectedId}">
                <s:form action="edit?id=%{id}&pid=%{pid}&level=3&imgPath=%{imgPath}"
                  cssClass="form-horizontal" role="form" method="POST" enctype="multipart/form-data">
                  <tr>
                    <td><s:property value="id" /></td>
                    <td><s:if test="%{imgPath==null}">
                        <img src='./imges/nothing.png' class="img-rounded" width="68"
                          height="95" />
                      </s:if> <s:else>
                        <img src='./imges/<s:property value="imgPath" />'
                          class="img-rounded" width="68" height="95" />
                      </s:else></td>
                    <td><div class="form-group">
                        <label class="control-label col-sm-3 form-label" for="text">タイトル : </label>
                        <div class="col-sm-9 form-input">
                          <s:textfield cssClass="form-control input-sm" name="item"
                            value='%{item}' />
                        </div>
                      </div> <br>
                      <div class="form-group">
                        <label class="control-label col-sm-3 form-label" for="text">
                                                    料金 :</label>
                        <div class="col-sm-6 form-input">
                          <s:textfield cssClass="form-control input-sm" name="strPrice"
                            value='%{price}' />
                        </div>
                        <label class="control-label col-sm-3 form-label-yen" for="text">円</label>
                      </div>

                      <s:if test="%{id==vid}">
                        <div class="err_msg">

                           <s:fielderror name="msg" cssClass="err"/>

                        </div>
                      </s:if>

                      <div class="form-group">
                        <label class="control-label col-sm-3 form-label file_change_label" for="text">画像変更 : </label>

                        <label class="btn btn-default btn-sm col-sm-4 change_file" for='<s:property value="#st.index"/>'>
                          <s:file  accept="image/*" name="upload" cssStyle="display:none;" id='%{#st.index}'/>
                                                                       ファイルを選択
                        </label>
                        <div class="col-sm-5"></div>

                      <!--
                        <div class="col-sm-9 file_left">
                          <s:file  accept="image/*" name="upload" cssClass="form_file" name="upload" id="upload%{#st.index}"/>
                        </div>
                      -->
                      </div>
                    </td>
                    <td><s:submit cssClass="btn btn-default btn-sm btn_update"
                        value="更新" name="editButton" /> <s:submit
                        cssClass="btn btn-warning btn-sm btn_delete"
                               value="削除" name="editButton" /></td>
                  </tr>
                </s:form>
              </s:if><!-- test="%{pid==selectedId}" -->
            </s:iterator>
          </table>

        </s:if><!-- test="%{listSize!=0}" -->

        <!-- 新規商品追加フォーム -->
        <s:form action="add?id=%{id}&pid=%{pid}&level=3" role="form" method="POST" enctype="multipart/form-data">
          <div class="row">
             <div class="col-sm-2 new-label"><b>新規タイトル ：</b></div>
             <div class="col-sm-4 new-input-item">
                <s:textfield cssClass="form-control input-sm" name="item" value=""/>
             </div>
             <div class="col-sm-6">
               <label class="btn btn-default btn-sm" for="new_photo">
                 <s:file  accept="image/*" name="upload" cssStyle="display:none;" id="new_photo" />
                                       ファイルを選択
               </label>
             </div>
          </div>
          <div class="row">
            <div class="col-sm-2 new-label"><b>新規料金 ：</b></div>
            <div class="col-sm-3 new-input-price">
              <s:textfield cssClass="form-control input-sm" name="strPrice" value="" />
            </div>
            <div class="col-sm-1 new-input-yen"><b>円</b></div>
            <div class="col-sm-6">
              <s:submit cssClass="btn btn-default btn-sm" value="追加" />
            </div>
          </div>
          <div class="err_msg_n">
            <s:if test="%{vid == 0}"><s:fielderror name="msg" cssClass="err" /></s:if>
          </div>
         </s:form>
      </s:if><!-- test="%{level==3} -->


      </div>
      <!-- col-sm-8 -->

    </div>
    <!-- row -->
  </div>
  <!-- container -->
<s:debug></s:debug>
<br><br><br><br><br><br>

  <script type="text/javascript">
     $('[name=upload]').change(function(e){
    	 e.target.nextSibling.nodeValue = e.target.files.length ? e.target.files[0].name : "ファイルを選択";
     });
  </script>
</body>
</html>
