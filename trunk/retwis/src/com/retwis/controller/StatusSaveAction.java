package com.retwis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.retwis.User;
import com.retwis.service.IStatusService;
import com.retwis.service.StatusServiceImpl;

/**
 * 
 * @author yongboy
 * @date 2011-4-4
 * @version 1.0
 */
@WebServlet("/post")
public class StatusSaveAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IStatusService statusService = new StatusServiceImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");

		String postingError = null;
		if (content == null || content.length() == 0) {
			postingError = "You didn't enter anything.";
		} else if (content.length() > 140) {
			postingError = "Keep it to 140 characters please!";
		}

		if (postingError != null) {
			request.setAttribute("postingError", postingError);
			request.getRequestDispatcher("home").forward(request, response);

			return;
		}

		User user = (User) request.getSession().getAttribute("user");
		String ip = request.getRemoteAddr();

		statusService.save(user.getId(), content, ip);

		response.sendRedirect("home");
	}
}