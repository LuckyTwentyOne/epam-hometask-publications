package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;

@WebServlet(urlPatterns = "/switch")
public class SwitchLanguageController extends AbstractController {
	private static final long serialVersionUID = 886423434186015381L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Locale locale = switchLanguage(req);
		req.getSession().setAttribute(Constants.SESSION_LOCALE, locale);
		List<String> userHistoryUrl = (List<String>) req.getSession().getAttribute(Constants.ACCOUNT_ACTIONS_HISTORY);
		RoutingUtils.redirect(userHistoryUrl.get(userHistoryUrl.size()-1), req, resp);
	}
	
	private Locale switchLanguage(HttpServletRequest req){
		Locale locale = (Locale) req.getSession().getAttribute(Constants.SESSION_LOCALE);
		if (locale == null) {
			locale = req.getLocale();
		}
		if (!locale.getLanguage().equals("en")) {
			locale = new Locale("en");
		} else {
			locale = new Locale("ru");
		}
		return locale;
	}
}
