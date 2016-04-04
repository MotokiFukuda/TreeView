package com.rental.treedvd.util;

import java.io.Serializable;
import java.util.Vector;

public class TreeModel implements Serializable {

	/**
	 *シリアルID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *ノードID
	 *JSPのtreeタグ：nodeIdProperty属性で指定
	 */
	private String nodeId;

	/**
	 *ノードの表示文字列
	 *JSPのtreeタグ:nodeTitleProperty属性で指定
	 */
	private String title;

	/**
	 *子要素をまとめる。配列の中身はTreeModelそのものが入る。
	 *JSPのtreeのダグ:childCollectionPropertyで指定
	 */
	private Vector<TreeModel> child = new Vector<TreeModel>();


	/**
	 * @return nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId セットする nodeId
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return child
	 */
	public Vector<TreeModel> getChild() {
		return child;
	}

	/**
	 * @param child セットする child
	 */
	public void setChild(Vector<TreeModel> child) {
		this.child = child;
	}

	public void addChild(TreeModel tm) {
		TreeModel child = new TreeModel();
		child.addChild(tm);
	}


}
