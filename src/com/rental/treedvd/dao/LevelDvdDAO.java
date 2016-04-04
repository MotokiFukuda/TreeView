package com.rental.treedvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.rental.treedvd.dto.LevelDvdDTO;
import com.rental.treedvd.util.DBConnector;

/**
 * 階層入りでソートされた アイテムリストを取得するためのクラス
 *
 * @author LocalAdmin
 */
public class LevelDvdDAO {

	/**
	 * アイテムリスト
	 */
	private ArrayList<LevelDvdDTO> itemList = new ArrayList<LevelDvdDTO>();
	private ArrayList<LevelDvdDTO> selectedList = new ArrayList<LevelDvdDTO>();
	private ArrayList<String> delImgList = new ArrayList<String>();
	private ArrayList<LevelDvdDTO> pathList = new ArrayList<LevelDvdDTO>();

	Connection con = DBConnector.getConnection();

	/**
	 * itemListとselectedListを取得するメソッド
	 *
	 * @param int OyaId
	 * @return boolean 処理の成否
	 */
	public boolean selectItem(int OyaId) {
		boolean res = false;

		try {

			String sql = "SELECT id, pid, item, level, price, imgpath FROM dvd "
					+ "START WITH pid = ? " + "CONNECT BY PRIOR id = pid";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, OyaId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LevelDvdDTO dto = new LevelDvdDTO();
				dto.setId(rs.getInt("id"));
				dto.setPid(rs.getInt("pid"));
				dto.setItem(rs.getString("item"));
				dto.setLevel(rs.getInt("level"));
				dto.setPrice(rs.getInt("price"));
				dto.setImgPath(rs.getString("imgpath"));

				itemList.add(dto);
				selectedList.add(dto);

			}
			res = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	/*
	public static void main(String[] args) {
		LevelDvdDAO dao = new LevelDvdDAO();
		System.out.println("test");
		dao.searchItem("レゲエ");
	}
	*/

	public void searchItem(String word) {
		System.out.println("検索文字は" + word);

		String sql = "SELECT item FROM dvd WHERE item LIKE ?";
		System.out.println(sql);
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, word);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("とれたやつ = " + rs.getString("item"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("取れてない");
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * imgPathリストの取得 (imgesフォルダ内の画像を削除するため)
	 *
	 * @param id
	 * @return 処理結果の成否
	 */
	public boolean selectDelImgpath(int id) {
		boolean res = false;

		try {

			String sql = "SELECT imgpath FROM dvd START WITH id = ? CONNECT BY PRIOR id = pid";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String st = rs.getString("imgpath");
				delImgList.add(st);
			}
			res = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * DBからアイテムを削除するメソッド
	 *
	 * @return boolean 処理の成否
	 * @param int id
	 */
	public boolean deleteItem(int id) {
		boolean res = false;

		try {
			String sql = "DELETE FROM dvd WHERE id IN (SELECT id FROM dvd START WITH id = ? CONNECT BY PRIOR id = pid)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			if (!(line == 0)) {
				res = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * アイテム名を変更「するメソッド
	 *
	 * @return boolean 処理の成否
	 * @param int id, String item
	 */
	public boolean updateItem(int id, String item, int price, String imgPath) {
		boolean res = false;

		try {
			String sql = "UPDATE dvd SET item = ?, price = ?, imgPath = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item);
			ps.setInt(2, price);
			ps.setString(3, imgPath);
			ps.setInt(4, id);
			int line = ps.executeUpdate();

			if (!(line == 0)) {
				res = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * アイテムを追加するメソッド
	 *
	 * @param id
	 *            item
	 * @return boolean 処理の成否
	 */
	public boolean addItem(int id, String item, int price, String imgpath) {
		boolean res = false;

		try {
			String sql = "INSERT INTO dvd (item, pid, price, imgpath) values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item);
			ps.setInt(2, id);
			ps.setInt(3, price);
			ps.setString(4, imgpath);
			int line = ps.executeUpdate();

			if (!(line == 0)) {
				res = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * 検索されたアイテムのカテゴリたちを取得するメソッド
	 *
	 * @param item
	 * @return boolean 処理の成否
	 */
	public boolean selectPathList(String item) {
		boolean res = false;
		try {
			String sql = "SELECT id, pid, item, level, price, imgpath FROM dvd WHERE id != 1 AND id != 2 START WITH item = ? CONNECT BY PRIOR pid = id";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LevelDvdDTO dto = new LevelDvdDTO();
				dto.setId(rs.getInt("id"));
				dto.setPid(rs.getInt("pid"));
				dto.setItem(rs.getString("item"));
				dto.setLevel(rs.getInt("level"));
				dto.setPrice(rs.getInt("price"));
				dto.setImgPath(rs.getString("imgpath"));
				pathList.add(dto);
			}
			Collections.reverse(pathList);
			for (LevelDvdDTO dto : pathList) {
				System.out.println(dto.);
			}
			System.out.println();
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * @return itemList
	 */
	public ArrayList<LevelDvdDTO> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList
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
	 * @return pathList
	 */
	public ArrayList<LevelDvdDTO> getPathList() {
		return pathList;
	}

	/**
	 * @param pathList
	 *            セットする pathList
	 */
	public void setPathList(ArrayList<LevelDvdDTO> pathList) {
		this.pathList = pathList;
	}
}
