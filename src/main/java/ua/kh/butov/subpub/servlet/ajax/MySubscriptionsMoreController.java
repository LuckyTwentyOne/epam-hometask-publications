package ua.kh.butov.subpub.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.entity.Subscription;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;
import ua.kh.butov.subpub.util.SessionUtils;

@WebServlet("/ajax/html/more/subscriptions")
public class MySubscriptionsMoreController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Subscription> subscriptions = getSubscriptionService().listMySubscriptions(SessionUtils.getCurrentAccount(req), getPage(req),
				Constants.MAX_SUBSCRIPTIONS_PER_HTML_PAGE);
		req.setAttribute("subscriptions", subscriptions);
		RoutingUtils.forwardToFragment("subscription-list.jsp", req, resp);
	}
}
