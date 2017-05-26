package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.model.SocialAccount;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;
import ua.kh.butov.subpub.util.SessionUtils;

@WebServlet("/from-social")
public class FromSocialController extends AbstractController {
	private static final long serialVersionUID = -8146770694377066438L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		if (code != null) {
			SocialAccount socialAccount = getSocialService().getSocialAccount(code);
			CurrentAccount currentAccount = getAccountService().authentificate(socialAccount);
			SessionUtils.setCurrentAccount(req, currentAccount);
			redirectToSuccessPage(req, resp);
		} else {
			LOGGER.warn("Parameter code not found");
			RoutingUtils.redirect("/subpub/publications", req, resp);
		}
	}
	
	protected void redirectToSuccessPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			RoutingUtils.redirect("/subpub/my-subscriptions", req, resp);
	}
}
