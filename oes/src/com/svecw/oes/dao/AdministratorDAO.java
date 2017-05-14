
package com.svecw.oes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.svecw.oes.dao.util.DAOUtility;
import com.svecw.oes.dto.Administrator;

public class AdministratorDAO {
	public boolean insert(Administrator administrator) {
		PreparedStatement pstmt = null;
		try {
			String qstr1 = "insert into administrators (admin_name,password,phonenumber,email_id) values (?,?,?,?)";
			pstmt = DAOUtility.getConnection().prepareStatement(qstr1);
			pstmt.setString(1, administrator.getName());
			pstmt.setString(2, administrator.getPassword());
			pstmt.setString(3, administrator.getPhoneNumber());
			pstmt.setString(4, administrator.getEmailId());
			if (pstmt.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Administrator getAdmin(int adminId) {
		PreparedStatement pstmt = null;
		Administrator administrator = null;
		ResultSet rs = null;
		String qstr1 = "select * from administrators where admin_id=?";

		try {
			pstmt = DAOUtility.getConnection().prepareStatement(qstr1);
			pstmt.setInt(1, adminId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println(adminId);
				administrator.setname(rs.getString("admin_name"));
				administrator.setPassword(rs.getString("password"));
				administrator.setPhoneNumber("phoneNumber");
				administrator.setEmailId(rs.getString("email_id"));
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		finally {
			try {
				if (pstmt != null) {

					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return administrator;
	}

}
