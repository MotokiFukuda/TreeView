package com.rental.treedvd.dto;

public class DvdDTO {

	/**
	 * id
	 */
	private int id;

	/**
	 * 親要素のid
	 */
	private int pid;
	/**
	 * アイテム
	 */
	private String item;

	/**
	 * イメージパス
	 */
	private String imgPath;

	/**
	 * 金額
	 */
	private int price;

	/**
	 *ノードが木の葉であるか否か
	 */
	private int isLeaf;


	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid セットする pid
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @return item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item セットする item
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return isLeaf
	 */
	public int getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf セットする isLeaf
	 */
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath セットする imgPath
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}
