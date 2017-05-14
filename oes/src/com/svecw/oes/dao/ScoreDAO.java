package com.svecw.oes.dao;

import com.svecw.oes.dao.util.DAOUtility;
import com.svecw.oes.dto.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ScoreDAO {
	public void insertOptedAnswer(Score score) {
		final String str = "insert in to score values(?,?,?,?)";
		PreparedStatement ps = null;
		int rs;
		try {
			ps = DAOUtility.getConnection().prepareStatement(str);
			ps.setString(1, score.getUserId());
			ps.setInt(2, score.getTestId());
			ps.setInt(3, score.getQuestionId());
			ps.setInt(4, score.getOptionSelected());
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
