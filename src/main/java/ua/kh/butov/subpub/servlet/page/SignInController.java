package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.form.LoginForm;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;
import ua.kh.butov.subpub.util.SessionUtils;

@WebServlet("/sign-in")
public class SignInController extends AbstractController {
	private static final long serialVersionUID = 8121768999153148108L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (SessionUtils.isCurrentAccountCreated(req)) {
			RoutingUtils.redirect("/subpub/my-subscriptions", req, resp);
		} else {
			RoutingUtils.forwardToPage("sign-in.jsp", req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (SessionUtils.isCurrentAccountCreated(req)) {
			RoutingUtils.redirect("/subpub/my-subscriptions", req, resp);
		} else {
			LoginForm form = createForm(req, LoginForm.class);
			try {
				form.validate(getI18nService());
				CurrentAccount currentAccount = getAccountService().login(form);
				SessionUtils.setCurrentAccount(req, currentAccount);
				RoutingUtils.redirect("/subpub/my-subscriptions", req, resp);
			} catch (ValidationException e) {
				req.setAttribute("form", form);
				req.setAttribute(Constants.UNSUCCESS_MESSAGE, getI18nService().getMessage(e.getMessage(), SessionUtils.getSessionLocale(req)));
				RoutingUtils.forwardToPage("sign-in.jsp", req, resp);
			}
		}
	}
}
