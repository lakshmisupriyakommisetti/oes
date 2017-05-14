package com.svecw.oes.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.svecw.oes.dao.QuestionDAO;
import com.svecw.oes.dao.ScoreDAO;
import com.svecw.oes.dao.TestDAO;
import com.svecw.oes.dto.Question;
import com.svecw.oes.dto.Score;
import com.svecw.oes.dto.Test;
import com.svecw.oes.util.OptionUtility;

/**
 * Servlet implementation class demo
 */
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<String, String> answerMap = new HashMap<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("take_test")) {

			int testID = Integer.parseInt(request.getParameter("test_id"));
			List<Question> questions = new QuestionDAO().getQuestions(testID);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("questionsList", questions);
			for (int i = 1; i <= questions.size(); i++) {
				answerMap.put(Integer.toString(i), "0000");
			}
			httpSession.setAttribute("current_qno", 1);
			httpSession.setAttribute("map", answerMap);
			response.sendRedirect("NewFile.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			DiskFileItemFactory dis = new DiskFileItemFactory();
			ServletFileUpload sfd = new ServletFileUpload(dis);

			try {
				List<FileItem> list = sfd.parseRequest(request);
				Test test = new Test();

				for (FileItem fileItem : list) {
					if (!fileItem.isFormField()) {
						String imagePath = "/home/user/Desktop/WISE/" + getServletContext().getContextPath()
								+ "/WebContent/csvs/" + fileItem.getName();
						System.out.println(fileItem.getName());
						fileItem.write(new File(imagePath));
					} else {
						if (fileItem.getFieldName().equals("name")) {
							test.setName(fileItem.getString());
						} else if (fileItem.getFieldName().equals("startDate")) {
							SimpleDateFormat format = new SimpleDateFormat();
							test.setStartDate(format.parse(fileItem.getString()));
						} else if (fileItem.getFieldName().equals("endDate")) {
							SimpleDateFormat format = new SimpleDateFormat();
							test.setEndDate(format.parse(fileItem.getString()));

						} else if (fileItem.getFieldName().equals("duration")) {
							SimpleDateFormat format = new SimpleDateFormat();
							test.setDuration(format.parse(fileItem.getString()));

						}

					}

				}
				int testId = new TestDAO().add(test);

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			HttpSession httpSession = request.getSession();
			int val = (int) httpSession.getAttribute("current_qno");
			List<Question> questions = (List<Question>) httpSession.getAttribute("questionsList");
			Map<String, String> answers = (Map<String, String>) httpSession.getAttribute("map");
			String str2 = "";
			StringBuilder str = new StringBuilder("0000");
			int[] arr;
			String[] checked = request.getParameterValues("c" + Integer.toString(val));
			if (checked != null) {
				for (int j = 0; j < checked.length; j++) {
					str.setCharAt(Integer.parseInt(checked[j]), '1');
				}
				str2 = str.toString();

			}

			String str1 = "";
			String checked1 = request.getParameter("r" + Integer.toString(val));
			if (checked1 != null) {
				for (int j = 0; j <= 3; j++) {
					if (Integer.toString(j).equals(checked1)) {
						str1 += "1";
					} else {
						str1 += "0";
					}
				}
				str2 = str1;
			}
			if (str2.equals("")) {
				str2 = "0000";
			}

			OptionUtility ou = new OptionUtility();

			if (checked1 != null || checked != null) {
				int dec = ou.binaryToDecimal(str2);
				questions.get(val - 1).setSelectedAnswer(dec);
				httpSession.setAttribute("questionsList", questions);
				answers.put(Integer.toString(val), str2);
				httpSession.setAttribute("map", answers);
			}

			String value1 = request.getParameter("link");

			if ("<-prev".equals(value1)) {
				httpSession.setAttribute("current_qno", val - 1);
				response.sendRedirect("NewFile.jsp");
			} else if ("next->".equals(value1)) {
				httpSession.setAttribute("current_qno", val + 1);
				response.sendRedirect("NewFile.jsp");
			} else if ("Finish".equals(value1)) {

				ScoreDAO obj = new ScoreDAO();
				String userId = (String) httpSession.getAttribute("userId");
				int testId = (int) httpSession.getAttribute("test_id");
				for (Question question : questions) {
					Score score = new Score(userId, testId, question.getQuestionId(), question.getSelectedAnswer());
					obj.insertOptedAnswer(score);
				}

				response.sendRedirect("finish.jsp");

			} else {
				httpSession.setAttribute("current_qno", Integer.parseInt(value1));
				response.sendRedirect("NewFile.jsp");
			}
		}

	}

}
