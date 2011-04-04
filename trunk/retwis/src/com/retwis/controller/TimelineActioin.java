package com.retwis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.retwis.service.IStatusService;
import com.retwis.service.StatusServiceImpl;

@WebServlet("/timeline")
public class TimelineActioin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IStatusService statusService = new StatusServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("posts", statusService.timeline(1));

		request.getRequestDispatcher("/WEB-INF/html/timeline.html").forward(
				request, response);
	}
}