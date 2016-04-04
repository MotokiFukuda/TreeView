package com.rental.treedvd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rental.treedvd.dto.DvdDTO;
import com.rental.treedvd.util.DBConnector;

/**
 * アイテムリストを取得するためのクラス
 *
 * @author LocalAdmin
 */
public class DvdDAO {

	/**
	 * アイテムリスト
	 */
	private ArrayList<DvdDTO> itemList = new ArrayList<DvdDTO>();

	/**
	 * DBからアイテムリストを取得するメソッド
	 * @return boolean 処理の成否
	 */
	public boolean selectItem() {
		boolean res = false;
		Connection con = null;
		try {
			con = DBConnector.getConnection();

			Statement st = con.createStatement();
			String sql = "SELECT * FROM dvd";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				DvdDTO dto = new DvdDTO();
				dto.setId(rs.getInt("id"));
				dto.setPid(rs.getInt("pid"));
				dto.setItem(rs.getString("item"));
				itemList.add(dto);
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
	 * @return itemList
	 */
	public ArrayList<DvdDTO> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList
	 */
	public void setItemList(ArrayList<DvdDTO> itemList) {
		this.itemList = itemList;
	}
}
