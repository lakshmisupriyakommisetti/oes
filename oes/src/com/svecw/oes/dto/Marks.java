package com.svecw.oes.dto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.svecw.oes.dao.util.DAOUtility;
public class Marks {
	public  List<User> test(){
		PreparedStatement pstmt = null;
		String qstr1 = "select user_id,username from users u ,test_users tu where test_id=? and u.uid=tu.uid";
		List<User> users = new ArrayList<>();
		User temp;
		try {
			pstmt =  DAOUtility.getConnection().prepareStatement(qstr1);
			//pstmt.setString(1,.getTests());
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            temp = new User();
            temp.setUserId(rs.getString(1));
            temp.setName(rs.getString(2));
            users.add(temp);
		
       }
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return users;	
	}

	}

