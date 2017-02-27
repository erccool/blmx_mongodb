package net.tutorial.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tutorial.utilities.MongoService;

@WebServlet({ "home", "" })
public class MainController extends HttpServlet {
	RequestDispatcher dispatcher;
	MongoService ms = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("action");
		String id = req.getParameter("id");
		String viewName = "home";

		if (param != null && param.equals("new")) {
			viewName = "contact";
		} else if (param != null && param.equals("edit")) {
			
			viewName = "contact";
			ms = MongoService.getInstance();
			ms.connect();
			req.setAttribute("document", ms.findOne(id));
			ms.close();
			
		} else {

			ms = MongoService.getInstance();
			ms.connect();

			if (param != null && id != null && param.equals("delete")) {
				ms.delete(id);
			}

			req.setAttribute("contacts", ms.allDocuments());
			ms.close();
		}

		dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");

		Map<String, String> document = new HashMap<String, String>();
		MongoService ms = MongoService.getInstance();
		ms.connect();
		
		document.put("name", name);
		document.put("email", email);
		document.put("mobile", mobile);
		
		if (id == null) {
			ms.create(document);
		} else {
			ms.update(id, document);
		}
		
		ms.close();

		resp.sendRedirect("home");
	}

}
