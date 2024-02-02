package com.tinkudhaba.backend.SERVLET;

import java.io.IOException;
import java.util.List;
import com.tinkudhaba.backend.DAO.UserDAO;
import com.tinkudhaba.backend.DTO.UserDTO;
import com.tinkudhaba.backend.SERVICE.UserService;
import com.tinkudhaba.backend.UTIL.DBUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public UserServlet() {
		System.out.println("UserServlet: Object Created..");
		DBUtil dbUtil = new DBUtil();
		UserDAO userDAO = new UserDAO(dbUtil);
		userService = new UserService(userDAO);
	}

//	public void init(ServletConfig config) throws ServletException {
//		System.out.println("UserServlet: init..");
//	}

//	protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("UserServlet: service..");
//		String task = request.getParameter("task");
//
//		if (task.equalsIgnoreCase("findByMobile")) {
//			findByMobile(request, response);
//		} else if (task.equalsIgnoreCase("findByAll")) {
//			findAll(request, response);
//		} else if (task.equalsIgnoreCase("deleteById")) {
//			deleteById(request, response);
//		} else if (task.equalsIgnoreCase("findById")) {
//			findById(request, response);
//		} else if (task.equalsIgnoreCase("login")) {
//			findForLogin(request, response);
//		} else if (task.equalsIgnoreCase("signup")) {
//			signUp(request, response);
//		} else if (task.equalsIgnoreCase("updateById")) {
////			updateById(request, response);
//		} else {
//			System.out.println("No task found.");
//		}
//	}
	// ---------------------- OR ----------------------

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: doGet..");
		String task = request.getParameter("task");
		if (task.equalsIgnoreCase("findByMobile")) {
			findByMobile(request, response);
		} else if (task.equalsIgnoreCase("findByAll")) {
			findAll(request, response);
		} else if (task.equalsIgnoreCase("findAllbyAdmin")) {
			findAllbyAdmin(request, response);
		} else if (task.equalsIgnoreCase("deleteById")) {
			deleteById(request, response);
		} else if (task.equalsIgnoreCase("findById")) {
			findById(request, response);
		} else {
			System.out.println("No task found.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet: doPost..");

		String task = request.getParameter("task");

		if (task.equalsIgnoreCase("login")) {
			findForLogin(request, response);
		} else if (task.equalsIgnoreCase("signup")) {
			signUp(request, response);
		} else if (task.equalsIgnoreCase("updateById")) {
			updateById(request, response);
		} else {
			System.out.println("No task found.");
		}
	}

	public void destroy() {
		System.out.println("UserServlet: Destroy..");
	}

	private void findForLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet.findForLogin");

		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			UserDTO userDTO = userService.findByLogin(username, password);
			if (userDTO != null) {
//				System.out.println("User account found successfully.");
//				System.out.println(userDTO.getFirstName());
//				request.setAttribute("loginUserDTO", userDTO);

				// Admin-Logic
				if (userDTO.getEmail().equals("admin@test.com") || userDTO.getContact().equals("9024152797")) {
					System.out.println("Admin LoggedIn - UserServlet.findForLogin");
					System.out.println(userDTO.getFirstName());
					request.setAttribute("loginUserDTO", userDTO);
					RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
					rd.forward(request, response);

				} else {
					System.out.println("User account found successfully.");
					System.out.println(userDTO.getFirstName());
					request.setAttribute("loginUserDTO", userDTO);
					RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
					rd.forward(request, response);
				}
			} else {
				System.out.println("Failed to find user account.");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Incorrect username or password.");
				request.setAttribute("redirectUrl", "login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to login due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "login.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserServlet.signUp()");

		try {
			UserDTO userDTO = new UserDTO();

			userDTO.setFirstName(request.getParameter("firstName"));
			userDTO.setLastName(request.getParameter("lastName"));
			userDTO.setContact(request.getParameter("contact"));
			userDTO.setEmail(request.getParameter("email"));
			userDTO.setPassword(request.getParameter("password"));
//			userDTO.setCityId(request.getParameter("cityId"));
//			userDTO.setRoleId(request.getParameter("roleId"));
			userDTO.setCityId(1);
			userDTO.setRoleId(1);
			String dob = request.getParameter("dob");
			userDTO.setDob(dob);

			int count = userService.save(userDTO);
			if (count > 0) {
				System.out.println("User account created successfully.");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "User account created successfully.");
				request.setAttribute("redirectUrl", "login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			} else {
				System.out.println("Failed to create user account.");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to create user account.");
				request.setAttribute("redirectUrl", "signup.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to create user account due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "signup.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void findByMobile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet.findByMobile()");

		try {

			String mobile = request.getParameter("contact");
			UserDTO userDTO = userService.findByMobile(mobile);
			if (userDTO != null) {
				System.out.println("User account found successfully.");
				request.setAttribute("userDTO", userDTO);
//				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
//				rd.forward(request, response);

				if (userDTO.getContact().equals("9024152797")) {
					System.out.println("Admin LoggedIn - UserServlet.findForLogin");
					System.out.println(userDTO.getFirstName());
//					request.setAttribute("loginUserDTO", userDTO);
					RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
					rd.forward(request, response);

				} else {
					System.out.println("User account found successfully.");
					System.out.println(userDTO.getFirstName());
//					request.setAttribute("loginUserDTO", userDTO);
					RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
					rd.forward(request, response);
				}

			} else {
				System.out.println("Failed to find user account.");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to find user account.");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to find user account due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "signup.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	private void findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet.findById()");

		try {

			Integer userId = Integer.parseInt(request.getParameter("userId"));
			UserDTO userDTO = userService.findById(userId);
			if (userDTO != null) {
				System.out.println("User account found successfully.");
				request.setAttribute("userDTOForEdit", userDTO);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);

			} else {
				System.out.println("Failed to find user account.");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to find user account.");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to find user account due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "signup.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

//	private void findAll(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("MainApp.findAll()");
//
//		try {
//			List<UserDTO> userDTOs = userService.findAll();
//			if (!userDTOs.isEmpty()) {
//				System.out.println("User account found successfully.");
//				System.out.println(userDTOs.size());
//
//				userDTOs.stream().forEach(u -> {
//					System.out.println(u.getFirstName());
//				});
//
//			} else {
//				System.out.println("Failed to find user account.");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	private void findAllbyAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet.findAllbyAdmin()");
		try {
			List<UserDTO> userDTOs = userService.findAllbyAdmin();
			if (!userDTOs.isEmpty()) {
				System.out.println("User account found successfully.");
				request.setAttribute("userDTOs", userDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.forward(request, response);

			} else {
				System.out.println("Failed to find user account.");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to find user account.");
				request.setAttribute("redirectUrl", "admin.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to find user account due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "signup.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet.findAll()");

		try {
			List<UserDTO> userDTOs = userService.findAll();
			if (!userDTOs.isEmpty()) {
				System.out.println("User account found successfully.");
				request.setAttribute("userDTOs", userDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);

			} else {
				System.out.println("Failed to find user account.");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to find user account.");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to find user account due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "signup.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

	private void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet.deleteById()");

		try {

			Integer userId = Integer.parseInt(request.getParameter("userId"));
			Integer count = userService.deleteById(userId);
			if (count > 0) {
				System.out.println("User account deleted successfully.");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "User account deleted successfully.");
				// request.setAttribute("redirectUrl", "home.jsp");
				request.setAttribute("redirectUrl", "admin.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			} else {
				System.out.println("Failed to delete user account.");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to delete user account.");
				// request.setAttribute("redirectUrl", "home.jsp");
				request.setAttribute("redirectUrl", "admin.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to delete user account due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "signup.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	public void updateById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserServlet.Update()");

		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(Integer.parseInt(request.getParameter("userId")));

			userDTO.setFirstName(request.getParameter("firstName"));
			userDTO.setLastName(request.getParameter("lastName"));
			userDTO.setEmail(request.getParameter("mobileNumber"));
			userDTO.setContact(request.getParameter("email"));
			userDTO.setCityId(1);
			userDTO.setRoleId(1);
			String dob = request.getParameter("dob");
			userDTO.setDob(dob);

			int count = userService.update(userDTO);
			if (count > 0) {
				System.out.println("User account updated successfully.");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "User account updated successfully.");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			} else {
				System.out.println("Failed to update user account.");
				request.setAttribute("status", "Error");
				request.setAttribute("message", "Failed to update user account.");
				request.setAttribute("redirectUrl", "home.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("status", "Error");
			request.setAttribute("message", "Failed to update user account due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "home.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

}
