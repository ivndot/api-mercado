package io.ivndot.routes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import io.ivndot.util.ResponseUtil;

@WebServlet("/")
public class App extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		json.put("api_version", 1.0);

		// CORS configuration
		ResponseUtil.setAccessControlHeaders(resp, "GET");

		// send response
		resp.getWriter().println(json.toString());
	}

}
