package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.entity.Account;
import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.form.RegistrationForm;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;
import ua.kh.butov.subpub.util.SessionUtils;

@WebServlet(urlPatterns = { "/registration" })
public class RegistrationController extends AbstractController {
	private static final long serialVersionUID = -4154924682725019694L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoutingUtils.forwardToPage("registration.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RegistrationForm form = createForm(req, RegistrationForm.class);
		try {
			form.validate(getI18nService());
			CurrentAccount currentAccount = getAccountService().registrateAccount(
					new Account(form.getFirstName(), form.getLastName(), form.getEmail(), form.getPassword()));
			SessionUtils.setCurrentAccount(req, currentAccount);
			req.getSession().setAttribute(Constants.SUCCESS_MESSAGE, getI18nService().getMessage("registration.success.message", SessionUtils.getSessionLocale(req)));
			RoutingUtils.redirect("/subpub/publications", req, resp);
		} catch (ValidationException e) {
			req.setAttribute("form", form);
			req.setAttribute(Constants.UNSUCCESS_MESSAGE, getI18nService().getMessage(e.getMessage(), SessionUtils.getSessionLocale(req)));
			RoutingUtils.forwardToPage("registration.jsp", req, resp);
		}
	}

}
