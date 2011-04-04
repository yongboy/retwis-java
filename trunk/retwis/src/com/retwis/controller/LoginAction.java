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
import com.retwis.util.MD5;

/**
 * 
 * @author yongboy
 * @date 2011-4-2
 * @version 1.0
 */
@WebServlet("/login")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IUserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/html/login.html").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = null;
		if (username == null || password == null
				|| (user = userService.checkLogin(username, password)) == null
				|| !MD5.checkMD5(password, user.getPass())) {
			request.setAttribute("username", username);
			request.setAttribute("loginError",
					"Incorrect username or password");

			doGet(request, response);
		} else {
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/");
		}
	}
}