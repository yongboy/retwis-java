package com.retwis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.retwis.User;
import com.retwis.service.IStatusService;
import com.retwis.service.IUserService;
import com.retwis.service.StatusServiceImpl;
import com.retwis.service.UserServiceImpl;

/**
 * 
 * @author yongboy
 * @date 2011-4-4
 * @version 1.0
 */
@WebServlet("/mentions")
public class MentionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IStatusService statusService = new StatusServiceImpl();
	private IUserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userName = getUserName(request);
		System.out.println("userName : " + userName);

		request.setAttribute("posts", statusService.mentions(userName, 1));
		request.setAttribute("userName", userName);

		User currentUser = (User) request.getSession().getAttribute("user");
		User targetUser = userService.loadByName(userName);

		if (!targetUser.equals(currentUser)) {
			request.setAttribute("targetUser", targetUser);
			request.setAttribute("following", userService.checkFlollowing(currentUser.getId(), targetUser.getId()));
		}

		request.getRequestDispatcher("/WEB-INF/html/memtions.html").forward(
				request, response);
	}

	private String getUserName(HttpServletRequest request) {
		String path = request.getRequestURI();
		System.out.println("path : " + path);
		String context = request.getContextPath();
		System.out.println("context : " + context);
		String servlet = request.getServletPath();
		System.out.println("servlet : " + servlet);

		int from = path.indexOf(context);
		int end = path.indexOf(context);

		return path.substring(from + context.length(), end);
	}
}