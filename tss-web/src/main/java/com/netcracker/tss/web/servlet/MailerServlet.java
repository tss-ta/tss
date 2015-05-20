package com.netcracker.tss.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.util.GlobalVariables;

@WebServlet(urlPatterns = "/mailer")
public class MailerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (GlobalVariables.MAILER_STATE.get()) {
            resp.sendRedirect("/admin?menu=dashboard&action=view");
        }

	}

}
