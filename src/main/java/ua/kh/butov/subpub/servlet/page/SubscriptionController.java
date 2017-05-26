package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;
import ua.kh.butov.subpub.util.SessionUtils;


@WebServlet("/subscription")
public class SubscriptionController extends AbstractController{
	private static final long serialVersionUID = -1263933306603914221L;
	private static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CurrentAccount account = SessionUtils.getCurrentAccount(req);
		Publication requestedPublication = getPublicationService().findById(Integer.parseInt(req.getParameter("idPublication")));
		try {
			long idSubscription = getSubscriptionService().makeSubscription(requestedPublication, Integer.valueOf(req.getParameter("numberOfMonthes")), account);
			req.getSession().setAttribute(CURRENT_MESSAGE, "Subscription created successfully.");
			RoutingUtils.redirect("/subpub/subscription?id=" + idSubscription, req, resp);
		} catch (ValidationException e) {
			req.getSession().setAttribute(CURRENT_MESSAGE, e.getMessage());
			RoutingUtils.redirect("/subpub/publications", req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = (String) req.getSession().getAttribute(CURRENT_MESSAGE);
		req.getSession().removeAttribute(CURRENT_MESSAGE);
		req.setAttribute(CURRENT_MESSAGE, message);
		RoutingUtils.forwardToPage("subscription.jsp", req, resp);
	}
}
