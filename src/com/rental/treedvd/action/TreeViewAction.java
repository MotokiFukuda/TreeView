package com.rental.treedvd.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rental.treedvd.dao.LevelDvdDAO;
import com.rental.treedvd.dto.LevelDvdDTO;

public class TreeViewAction extends ActionSupport {

	/**
	 * シリアルID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * アイテムリスト
	 */
	private ArrayList<LevelDvdDTO> itemList = new ArrayList<LevelDvdDTO>();
	private ArrayList<LevelDvdDTO> selectedList = new ArrayList<LevelDvdDTO>();
	private ArrayList<String> delImgList = new ArrayList<String>();
	private ArrayList<LevelDvdDTO> pathList = new ArrayList<LevelDvdDTO>();
	private int id = 1;
	private int pid;
	private String item;
	private String editButton = "";
	private int level;
	private String strPrice;
	private int price;
	private String imgPath = null;
	/**
	 * 選択されたアイテムのID
	 */
	private int selectedId;
	private String oyaItem;
	/**
	 * selectedListのサイズ
	 */
	private int listSize;
	private int vid;
	private String mes = null;

	// ファイルアップロード用
	private File file;
	private String contentType;
	private String filename;


	public String execute() throws Exception {

		System.out.println("--------execute()始め--------");
		System.out.println("id = " + id);
		System.out.println("pid = " + pid);
		System.out.println("item = " + item);
		System.out.println("oyaItem = " + oyaItem);
		System.out.println("level = " + level);
		System.out.println("selectedId = " + selectedId);
		System.out.println();

		// ツリービュー用のリスト作成
		LevelDvdDAO dao = new LevelDvdDAO();
		if (!dao.selectItem(1)) {
			return ERROR;
		}
		setItemList(dao.getItemList());

		// テーブル用のリスト」作成
		LevelDvdDAO dao2 = new LevelDvdDAO();
		if (!dao2.selectItem(id)) {
			return ERROR;
		}
		setSelectedList(dao2.getSelectedList());

		listSize = selectedList.size();

		/*
		 * 表の一番上に親を表示するため (テーブル内で更新や削除が行われても ツリービューで選択されたitemの値を保持しておくため
		 */
		for (LevelDvdDTO dto : itemList) {
			if (dto.getId() == id) {
				item = dto.getItem();
				pid = dto.getPid();
			}
		}
		for (LevelDvdDTO dto : itemList) {
			if (dto.getId() == pid) {
				oyaItem = dto.getItem();
			}
		}

		selectedId = id;

		System.out.println("--------execute()終わり--------");
		System.out.println("id = " + id);
		System.out.println("pid = " + pid);
		System.out.println("item = " + item);
		System.out.println("oyaItem = " + oyaItem);
		System.out.println("level = " + level);
		System.out.println("selectedId =" + selectedId);
		System.out.println("--------終わり--------");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		return SUCCESS;
	}

	public String edit() throws IOException {

		System.out.println("--------edit()始め--------");
		System.out.println("編集するアイテムのid = " + id);
		System.out.println("編集するアイテムのpid = " + pid);
		System.err.println("編集されたアイテムの名前は " + item);
		System.out.println("level = " + level);
		System.out.println("editButton = " + editButton);
		System.out.println();

		// editButtonの値によって操作を変える
		if (editButton.equals("削除")) {

			LevelDvdDAO dao = new LevelDvdDAO();
			if (!dao.selectDelImgpath(id)) {
				return ERROR;
			}
			delImgList = dao.getDelImgList();

			for (String s : delImgList) {
				if (s != null) {
					 //File delFile = new
					 //File("C:/pleiades/tomcat/6.0/webapps/treedvd/imges", s);
					File delFile = new File(
							"/pleiades/workspace/treedvd/WebContent/imges", s);
					if (!delFile.exists()) {
						System.out.println(s + " はimgesフォルダに存在しません");
					} else {
						FileUtils.forceDelete(delFile);
					}
				}
			}
			LevelDvdDAO dao2 = new LevelDvdDAO();
			dao2.deleteItem(id);

			// 今表示しているテーブルを選択しなおすため
			id = pid;

		} else if (editButton.equals("更新")) {

			System.out.println("-----if(更新)内-----");
			System.out.println("filename = " + filename);
			System.out.println("contentType = " + contentType);
			System.out.println();

			if (filename != null) {

				// 今のイメージを消す
				 //File delFile = new
				 //File("C:/pleiades/tomcat/6.0/webapps/treedvd/imges",
				 //imgPath);
				File delFile = new File(
						"C:/pleiades/workspace/treedvd/WebContent/imges",
						imgPath);
				if (!delFile.exists()) {
					System.out.println(imgPath + " はimgesフォルダに存在しません");
				} else {
					FileUtils.forceDelete(delFile);
				}

				long now = System.currentTimeMillis();
				imgPath = now + filename;
				 //File myFile = new
				 //File("C:/pleiades/tomcat/6.0/webapps/treedvd/imges",
				 //imgPath);
				File myFile = new File(
						"/pleiades/workspace/treedvd/WebContent/imges", imgPath);
				FileUtils.copyFile(file, myFile);
			}
			LevelDvdDAO dao = new LevelDvdDAO();
			dao.updateItem(id, item, price, imgPath);
			id = pid;
		} else {
			return ERROR;
		}

		return SUCCESS;
	}

	public String add() throws IOException {

		if (item.equals("")) {
			item = "defualt";
		}

		System.out.println("--------add()始め--------");
		System.out.println("追加するタイトル = " + item);
		System.out.println("追加する料金 = " + price);
		System.out.println("追加するアイテムのid = " + id);
		System.out.println("追加するアイテムのpid = " + pid);
		System.out.println("追加するアイテムのimgPath = " + imgPath);
		System.out.println("level = " + level);
		System.out.println("filename = " + filename);
		System.out.println("contentType = " + contentType);

		System.out.println();

		long now = System.currentTimeMillis();

		// C:/pleiades/workspace/treedvd/WebContent/imges
		if (filename != null) {
			if (!filename.isEmpty()) {
				imgPath = now + filename;
				 //File myFile = new
				 //File("C:/pleiades/tomcat/6.0/webapps/treedvd/imges",
				 //imgPath);
				File myFile = new File(
						"/pleiades/workspace/treedvd/WebContent/imges", imgPath);
				FileUtils.copyFile(file, myFile);
			}
		}

		LevelDvdDAO dao = new LevelDvdDAO();
		dao.addItem(id, item, price, imgPath);

		return SUCCESS;
	}

/*
	public String searchItem() {

		LevelDvdDAO dao = new LevelDvdDAO();
		String s = dao.selectPathList(item);
		if(s.equals("在りました")) {
			pathList = dao.getPathList();
			imgPath = pathList.get(2).getImgPath();
			level = 4;
		} else {
			mes = s;
		}
		return SUCCESS;
	}
*/

	@Override
	public void validate() {

		String actionName = ActionContext.getContext().getName();

		if(actionName.equals("add") || actionName.equals("edit")){
			System.out.println("-----validate()----------");
			System.out.println("strPrice = " + strPrice);
			System.out.println("item = " + item);
			System.out.println();

			if(strPrice == null || strPrice.equals("")) {
				strPrice = "0";
			}
			if(isNumber(strPrice) && strPrice.length() < 10) {
				price = Integer.parseInt(strPrice);
			} else {
				addFieldError("msg","料金は9桁以下の半角数字で入力してください");
				if (editButton.equals("更新")) {
					vid = id;
					id = pid;
				} else if (editButton.equals("追加")) {
					vid = 0;
					id = pid;
				}
			}
		}
	}



	/**
	 * valが数字であるか判別するメソッド
	 * @param val
	 * @return boolean
	 */
	public boolean isNumber(String val) {
		String regex = "\\A[-]?[0-9]+\\z";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(val);
		return m.find();
	}

	// <s:file>からアップロードされたファイルを取得↓3
	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

	/**
	 * @return itemList
	 */
	public ArrayList<LevelDvdDTO> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList
	 *            セットする itemList
	 */
	public void setItemList(ArrayList<LevelDvdDTO> itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return selectedList
	 */
	public ArrayList<LevelDvdDTO> getSelectedList() {
		return selectedList;
	}

	/**
	 * @param selectedList
	 *            セットする selectedList
	 */
	public void setSelectedList(ArrayList<LevelDvdDTO> selectedList) {
		this.selectedList = selectedList;
	}

	/**
	 * @return delImgList
	 */
	public ArrayList<String> getDelImgList() {
		return delImgList;
	}

	/**
	 * @param delImgList
	 *            セットする delImgList
	 */
	public void setDelImgList(ArrayList<String> delImgList) {
		this.delImgList = delImgList;
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
	 * @param pid
	 *            セットする pid
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
	 * @param item
	 *            セットする item
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return editButton
	 */
	public String getEditButton() {
		return editButton;
	}

	/**
	 * @param editButton
	 *            セットする editButton
	 */
	public void setEditButton(String editButton) {
		this.editButton = editButton;
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

	/**
	 * @return selectedId
	 */
	public int getSelectedId() {
		return selectedId;
	}

	/**
	 * @param selectedId
	 *            セットする selectedId
	 */
	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}

	/**
	 * @return oyaItem
	 */
	public String getOyaItem() {
		return oyaItem;
	}

	/**
	 * @param oyaItem
	 *            セットする oyaItem
	 */
	public void setOyaItem(String oyaItem) {
		this.oyaItem = oyaItem;
	}

	/**
	 * @return listSize
	 */
	public int getListSize() {
		return listSize;
	}

	/**
	 * @param listSize
	 *            セットする listSize
	 */
	public void setListSize(int listSize) {
		this.listSize = listSize;
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
	 * @return strPrice
	 */
	public String getStrPrice() {
		return strPrice;
	}

	/**
	 * @param strPrice セットする strPrice
	 */
	public void setStrPrice(String strPrice) {
		this.strPrice = strPrice;
	}

	/**
	 * @return vid
	 */
	public int getVid() {
		return vid;
	}

	/**
	 * @param vid セットする vid
	 */
	public void setVid(int vid) {
		this.vid = vid;
	}

	/**
	 * @return pathList
	 */
	public ArrayList<LevelDvdDTO> getPathList() {
		return pathList;
	}

	/**
	 * @param pathList セットする pathList
	 */
	public void setPathList(ArrayList<LevelDvdDTO> pathList) {
		this.pathList = pathList;
	}

	/**
	 * @return mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes セットする mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}



}
