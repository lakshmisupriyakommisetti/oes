package com.svecw.oes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import com.svecw.oes.dao.*;
import com.svecw.oes.dto.Administrator;
import com.svecw.oes.dto.Enrollment;
import com.svecw.oes.dto.Test;
import com.svecw.oes.dto.User;
import com.svecw.oes.exception.oesException;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		UserDAO userDAO = new UserDAO();
		TestDAO testDAO = new TestDAO();
		List<User> users = new ArrayList<>();
		List<Test> tests = new ArrayList<>();
		String role = "enroll";
		HttpSession session = request.getSession();
		users = userDAO.getUsers();
		tests = testDAO.getTests();
		try {
			request.getRequestDispatcher("enrollUsers.jsp").forward(request, response);
			response.sendRedirect("enrollUsers.jsp");
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		session.setAttribute("tests", tests);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String action = request.getParameter("action");

		if (action.equals("admin_registration")) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String phoneNo = request.getParameter("mobileNo");
			String emailId = request.getParameter("emailId");
			Administrator administrator = new Administrator(name, password, phoneNo, emailId);
			AdministratorDAO administratorDAO = new AdministratorDAO();

			if (administratorDAO.insert(administrator)) {
				request.setAttribute("successMessage", "sucessfully registered");

				request.getRequestDispatcher("addAdministrator.jsp").forward(request, response);

			} else {
				request.setAttribute("errorMessage", "unsucessful");
				request.getRequestDispatcher("addAdministrator.jsp").forward(request, response);

			}

		} else if (action.equals("user_registration")) {
			String userId = request.getParameter("userId");
			String uname = request.getParameter("name");
			String upassword = request.getParameter("password");
			String uphoneNo = request.getParameter("mobileNo");
			String uemailId = request.getParameter("emailId");
			User user = new User(userId, uname, upassword, uphoneNo, uemailId);
			UserDAO userDAO = new UserDAO();
			if (userDAO.insert(user)) {
				request.setAttribute("successMessage", "successfull");
				request.getRequestDispatcher("addUser.jsp").forward(request, response);

			} else {
				request.setAttribute("errorMessage", "Unsuccessful");
					request.getRequestDispatcher("addUser.jsp").forward(request, response);

			}

		} else if (action.equals("user_login")) {
			String userID = request.getParameter("userId");
			String password = request.getParameter("password");
			User user = null;
			UserDAO userDAO = new UserDAO();
			user = userDAO.getUser(userID);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					request.setAttribute("successMessage", "Welcome" + user.getName());
						request.getRequestDispatcher("userLogin.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage", "invalid userid or password");
						request.getRequestDispatcher("userLogin.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage", "invalid userid or password");
					request.getRequestDispatcher("userLogin.jsp").forward(request, response);

			}

		} else if (action.equals("admin_login")) {
			int adminId = Integer.parseInt(request.getParameter("adminId"));

			String password = request.getParameter("password");
			Administrator administrator = new Administrator();
			AdministratorDAO administratorDAO = new AdministratorDAO();
			administrator = administratorDAO.getAdmin(adminId);

			if (administrator != null && administrator.getPassword().equals(password)) {
				request.setAttribute("successMessage", "Welcome" + administrator.getName());
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", adminId + "invalid adminid or password");
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
			}
		}

		else if (action.equals("enroll")) {
			/*String[] users = request.getParameterValues("users");
			EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
			if (users != null && users.length != 0) {
				Enrollment[] enrollment = new Enrollment[users.length];
				int test = Integer.parseInt(request.getParameter("test"));
				for (int i = 0; i < users.length; i++) {
					enrollment[i] = new Enrollment(users[i], test);
					if (enrollmentDAO.insert(enrollment[i])) {
						request.setAttribute("successMessage", "enrolled");
						try {
							request.getRequestDispatcher("enrollUsers.jsp").forward(request, response);
						} catch (ServletException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else {

						request.setAttribute("errorMessage", "not enrolled");
						try {
							request.getRequestDispatcher("enrollUsers.jsp").forward(request, response);
						} catch (ServletException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}*/
		} else if (action.equals("logout")) {
		/*	System.out.println("controller");
			if (request.getParameter("logOut").equals("logOut")) {
				request.setAttribute("successMessage", "u r successfully logged out");
				try {
					request.getRequestDispatcher("userLogin.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
*/		}

	}
}
