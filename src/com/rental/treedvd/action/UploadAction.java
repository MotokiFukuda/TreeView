package com.rental.treedvd.action;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private File file;
	private String contentType;
	private String filename;
	private int id;
	private int pid;
	private int level;
	private int price;
	private String item;
	private String imgPath = "default";

	public void setUpload(File file) {
		this.setFile(file);
	}

	public void setUploadContentType(String contentType) {
		this.setContentType(contentType);
	}

	public void setUploadFileName(String filename) {
		this.setFilename(filename);
	}

	public String execute() throws Exception {

		System.out.println("----UploadActionのexecute()内-------");
		System.out.println("contenType = " + contentType);
		System.out.println("filename = " + filename);
		System.out.println("id = " + id);
		System.out.println("price = " + price);
		System.out.println("item = " + item);
		System.out.println("imgPath = " + imgPath);
		System.out.println("");

		long now = System.currentTimeMillis();

		if (filename != null) {
			imgPath = now + filename;
			File myFile = new File("C:/pleiades/workspace/treedvd/WebContent/imges", imgPath);
			FileUtils.copyFile(file, myFile);
		} else {
			return ERROR;
		}

		System.out.println("imgPath = " + imgPath);
		return SUCCESS;
	}

	/**
	 * @return file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            セットする file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            セットする contentType
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            セットする filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            セットする id
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
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            セットする level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param pid
	 *            セットする pid
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item
	 *            セットする item
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath
	 *            セットする imgPath
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
