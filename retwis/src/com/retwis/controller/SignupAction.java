package com.retwis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.retwis.User;
import com.retwis.service.IUserService;
import com.retwis.service.UserServiceImpl;

/**
 * 
 * @author y.nie
 * @date 2011-4-2
 * @version 1.0
 */
@WebServlet("/signup")
public class SignupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IUserService userService = new UserServiceImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String password_confirmation = request
				.getParameter("password_confirmation");

		String signupError = null;
		if (!username.matches("^\\w+")) {
			signupError = "Username must only contain letters, numbers and underscores.";
		} else if (userService.checkExistByName(username)) {
			signupError = "That username is taken.";
		} else if (username.length() < 4) {
			signupError = "Username must be at least 4 characters";
		} else if (password.length() < 6) {
			signupError = "Password must be at least 6 characters!";
		} else if (!password.equals(password_confirmation)) {
			signupError = "Passwords do not match!";
		}

		if (signupError != null) {
			request.setAttribute("signupError", signupError);

			request.getRequestDispatcher("login").forward(request, response);
			return;
		}

		User user = new User();
		user.setName(username);
		user.setPass(password);

		userService.save(user);

		request.getSession().setAttribute("user", user);

		response.sendRedirect(request.getContextPath() + "/");
	}
}