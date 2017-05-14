package com.svecw.oes.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.svecw.oes.dao.util.DAOUtility;
import com.svecw.oes.util.JSONUtility;
import com.svecw.oes.dto.Question;

public class QuestionDAO {
	public boolean add(Question question) {
		PreparedStatement ps = null;
		final String query = "insert into question values(?,?,?,?,?,?,?,?)";
		JSONUtility obj = new JSONUtility();
		int n = 0;
		try {
			ps = DAOUtility.getConnection().prepareStatement(query);
			ps.setInt(1, question.getQuestionId());
			ps.setString(2, obj.convertJavatoJSON(question.getDescription()));
			ps.setString(3, obj.convertJavaListtoJSON(question.getOptions()));
			ps.setInt(4, question.getDifficultyId());
			ps.setInt(5, question.getSubjectId());
			ps.setInt(6, question.getChapterId());
			ps.setInt(7, question.getAnswer());
			ps.setString(8, question.getTitle());
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (n > 0)
			return true;
		else
			return false;

	}
	
	public List<Question> getQuestions(int testID) {
		return null;
	}

}
