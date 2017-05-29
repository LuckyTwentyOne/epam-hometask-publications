package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.entity.Subscription;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;
import ua.kh.butov.subpub.util.SessionUtils;

@WebServlet("/my-subscriptions")
public class MySubscriptionsController extends AbstractController {
	private static final long serialVersionUID = -1263933306603914221L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
		List<Subscription> subscriptions = getSubscriptionService().listMySubscriptions(currentAccount, 1,
				Constants.MAX_SUBSCRIPTIONS_PER_HTML_PAGE);
		req.setAttribute("subscriptions", subscriptions);
		int totalCount = getSubscriptionService().countMySubscriptions(currentAccount);
		req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_SUBSCRIPTIONS_PER_HTML_PAGE));
		RoutingUtils.forwardToPage("my-subscriptions.jsp", req, resp);
	}
}
