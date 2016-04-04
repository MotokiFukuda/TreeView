package com.rental.treedvd.dto;

public class LevelDvdDTO {

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
	 * 階層
	 */
	private int level;

	private int price;

	private String imgPath;

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
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level セットする level
	 */
	public void setLevel(int level) {
		this.level = level;
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

}
