package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Trip;
import utils.MyUtils;
import utils.Router;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.TripDAO;

/**
 * Servlet implementation class SearchListTripsServlet
 */
@WebServlet(urlPatterns = "/searchListTrips")
public class SearchListTripsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchListTripsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = MyUtils.getStoredConnection(request);
		TripDAO tripDAO = new TripDAO();
		HttpSession session = request.getSession();
		String userManager = (String) session.getAttribute("userManager");
		String searchContent = request.getParameter("searchContent");
		List<Trip> list = null;
		try {
			list = tripDAO.searchListTrips(conn, searchContent,userManager);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("listTrips", list);

		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(Router.MANAGER_TRIPMANAGEMENT);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
