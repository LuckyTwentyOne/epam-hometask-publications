package ua.kh.butov.subpub.servlets.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.servlets.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;


@WebServlet("/subscription")
public class SubscriptionController extends AbstractController{
	private static final long serialVersionUID = -1263933306603914221L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoutingUtils.forwardToPage("subscription.jsp", req, resp);
	}
}
