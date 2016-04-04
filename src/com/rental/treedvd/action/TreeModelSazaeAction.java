package com.rental.treedvd.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.rental.treedvd.dao.DvdDAO;
import com.rental.treedvd.dto.DvdDTO;
public class TreeModelSazaeAction extends ActionSupport {

	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * アイテムリスト
	 */
	private static ArrayList<DvdDTO> itemList = new ArrayList<DvdDTO>();

	/**
	 *ツリー構造にしたリスト
	 */
	private static ArrayList<DvdDTO> viewList = new ArrayList<DvdDTO>();

	/**
	 * ツリーを作成する。
	 */
	public String execute() throws Exception {

		DvdDAO dao = new DvdDAO();
		if (!dao.selectItem()) {
			System.out.println("取れてナイッスヨ！");
		}
		itemList = dao.getItemList();
		getChildren(0);

//		if(viewList.size()!=0){
//			viewList.get(0).setItem("<ul id='sitemap' class='treeview'>"+viewList.get(0).getItem().substring(4));
//		}
		for(DvdDTO dto : viewList)
			System.out.println("アイテム名  " + dto.getItem());

		return SUCCESS;
	}


	/**
	 * 子のリストを作るメソッド
	 *
	 * @param leader
	 * @param parentId
	 */
	public static void getChildren(int parentId) {
		ArrayList<DvdDTO> treeList = new ArrayList<DvdDTO>();
		for (DvdDTO dto : itemList) {
			if (dto.getPid() == parentId) {
				treeList.add(dto);
			}
		}
		int count = 0;
		for (DvdDTO dto : treeList) {
			count++;
			if (count < treeList.size()) {
				dto.setItem("<ul><li>" + dto.getItem() + "</li>");
				viewList.add(dto);
				getChildren(dto.getId());
			} else {
				dto.setItem("<li>" + dto.getItem() + "</li></ul>");
				viewList.add(dto);
				getChildren(dto.getId());
			}
		}
	}

	/**
	 * @return itemList
	 */
	public ArrayList<DvdDTO> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList
	 *            セットする itemList
	 */
	public void setItemList(ArrayList<DvdDTO> itemList) {
		TreeModelSazaeAction.itemList = itemList;
	}


	/**
	 * @return viewList
	 */
	public ArrayList<DvdDTO> getViewList() {
		return viewList;
	}

}
