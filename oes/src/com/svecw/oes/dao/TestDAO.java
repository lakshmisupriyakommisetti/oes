package com.svecw.oes.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.svecw.oes.dao.util.DAOUtility;
import com.svecw.oes.dto.*;
import com.svecw.oes.util.JSONUtility;

public class TestDAO {
	public int add(Test test) {
		PreparedStatement ps = null;
		int n = 0;
		final String query = "insert into test(test_name,start_time,end_time,duration) values(?,?,?,?)";

		try {
			ps = DAOUtility.getConnection().prepareStatement(query);
			ps.setString(1, test.getName());
			ps.setDate(2, new java.sql.Date(test.getStartDate().getTime()));
			ps.setDate(3, new java.sql.Date(test.getEndDate().getTime()));
			ps.setDate(4, new java.sql.Date(test.getDuration().getTime()));
			if( ps.executeUpdate()>0){
				List<Test> testsList = getTests();
				int size = testsList.size();
				return testsList.get(size-1).getTestId();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (null != ps)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;

	}

	public List<Test> getTests() {

		PreparedStatement pstmt = null;
		List<Test> tests = new ArrayList<>();
		Test temp;
		try {
			String qstr1 = "select * from test";
			pstmt = DAOUtility.getConnection().prepareStatement(qstr1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				temp = new Test();
				temp.setTestId(rs.getInt(1));
				temp.setName(rs.getString(2));
				temp.setStartDate(rs.getDate(3));
				temp.setEndDate(rs.getDate(4));
				tests.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != pstmt)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return tests;
	}
}
