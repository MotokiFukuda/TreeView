package com.rental.treedvd.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.rental.treedvd.dao.DvdDAO;
import com.rental.treedvd.dto.DvdDTO;
import com.rental.treedvd.util.TreeModel;

public class TreeModelStAction  extends ActionSupport {


	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * アイテムリスト
	 */
	private static ArrayList<DvdDTO> itemList = new ArrayList<DvdDTO>();

	private TreeModel el;

	/**
	 * ツリーを作成する。
	 */
	public String execute() throws Exception {

		DvdDAO dao = new DvdDAO();
		if (!dao.selectItem()) {
			System.out.println("取れてナイッスヨ！");
			;
		}
		itemList = dao.getItemList();
		for (DvdDTO dto : itemList) {
			TreeModel el= createTreeModel(String.valueOf(dto.getId()), dto.getItem());
			if(el.getNodeId().equals(String.valueOf(dto.getPid()))) {
				el.addChild(el);
			}
		}
		setRoot(el);
		return SUCCESS;
	}

	public TreeModel createTreeModel(String id, String item) {
		TreeModel tm = new TreeModel();
		tm.setNodeId(id);
		tm.setTitle(item);
		return tm;
	}

	/**
	 * @return itemList
	 */
	public static ArrayList<DvdDTO> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList セットする itemList
	 */
	public static void setItemList(ArrayList<DvdDTO> itemList) {
		TreeModelStAction.itemList = itemList;
	}
	private TreeModel root;

	/**
	 * @return the root
	 */
	public TreeModel getRoot() {
	return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(TreeModel root) {
	this.root = root;
	}
}
