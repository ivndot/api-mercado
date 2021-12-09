package io.ivndot.routes;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ivndot.beans.ProbaBean;
import io.ivndot.beans.ProbaResponseBean;
import io.ivndot.dao.ProbaDAO;
import io.ivndot.exception.DAOInitializationException;
import io.ivndot.util.ResponseUtil;

@WebServlet("/proba")
public class Probabilities extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get parameters
		String table = req.getParameter("table");
		String field1 = req.getParameter("field1");
		String valueField1 = req.getParameter("valueField1");
		String field2 = req.getParameter("field2");
		String valueField2 = req.getParameter("valueField2");

		// probabilities
		String conjunta = null;
		String bayesiana = null;
		String condicional = null;

		// bean
		ProbaResponseBean probaResponse = null;

		// dao
		ProbaDAO probaDAO = null;

		if ((table != "" && table != null) && (field1 != "" && field1 != null) && (field2 != "" && field2 != null)
				&& (valueField1 != "" && valueField1 != null) && (valueField2 != "" && valueField2 != null)) {
			// OK: all fields have content

			// create view with table, field1 and field2
			try {
				// create proba dao object
				probaDAO = new ProbaDAO();

				// execute query to create view
				probaDAO.createView(table, field1, field2);

				// compute `conjunta` probability
				conjunta = probaDAO.probaConjunta(field1, valueField1, field2, valueField2);
				// compute `bayesiana` probability
				bayesiana = probaDAO.probaBayesiana(field1, valueField1, field2, valueField2);
				// compute `condicional` probability
				condicional = probaDAO.probaCondicional(field1, valueField1, field2, valueField2);

				// OK: all were ok
				probaResponse = new ProbaResponseBean(100, "ok",
						"Se creo la vista y se obtuvieron las probabilidades correctamente",
						new ProbaBean(conjunta, bayesiana, condicional));

			} catch (SQLException | DAOInitializationException | ClassNotFoundException e) {
				// ERROR: there was an error creating the view or in the computation of
				// probabilities
				probaResponse = new ProbaResponseBean(200, "error",
						"Hubo un error en la creacion de la vista o en el calculo de las probabilidades",
						new ProbaBean(conjunta, bayesiana, condicional));
				e.printStackTrace();

			} finally {
				probaDAO.closeConnection();
			}

		} else {
			// ERROR: there are empty fields
			probaResponse = new ProbaResponseBean(201, "error", "Faltan campos por llenar",
					new ProbaBean(conjunta, bayesiana, condicional));
		}

		// send JSON response to the client
		ResponseUtil.sendJSONResponse(resp, probaResponse, "GET");

	}

}
