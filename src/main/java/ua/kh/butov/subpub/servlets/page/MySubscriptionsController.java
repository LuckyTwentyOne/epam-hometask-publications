package ua.kh.butov.subpub.servlets.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.servlets.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;


@WebServlet("/my-subscriptions")
public class MySubscriptionsController extends AbstractController{
	private static final long serialVersionUID = -1263933306603914221L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoutingUtils.forwardToPage("my-subscriptions.jsp", req, resp);
	}
}
