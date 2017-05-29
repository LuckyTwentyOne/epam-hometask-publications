package ua.kh.butov.subpub.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.util.SessionUtils;

/**
 * Filter determines which URLs are accessible for each role in system.
 * 
 * @author V. Butov
 *
 */
@WebFilter(filterName = "AccessFilter")
public class AccessFilter extends AbstractFilter {

	@Override
	public final void doFilter(final HttpServletRequest req, final HttpServletResponse resp, final FilterChain chain)
			throws IOException, ServletException {
		CurrentAccount account = SessionUtils.getCurrentAccount(req);
		String currentUrl = req.getRequestURI();
		if (account.getRole().equals("admin") && notForAdminURL(currentUrl)) {
			resp.sendRedirect("/subpub/publications");
		} else if (account.getRole().equals("reader") && notForReaderURL(currentUrl)) {
			resp.sendRedirect("/subpub/publications");
		} else {
			chain.doFilter(req, resp);
		}
	}

	/**
	 * Determines forbiden URLs for admin.
	 * 
	 * @param currentUrl
	 * @return true if current URL is forbiden
	 */
	private boolean notForAdminURL(String currentUrl) {
		if (currentUrl.startsWith("/subpub/subscription")) {
			return true;
		}
		if (currentUrl.startsWith("/subpub/my-subscriptions")) {
			return true;
		}
		if (currentUrl.startsWith("/subpub/my-balance")) {
			return true;
		}
		return false;
	}

	/**
	 * Determines forbiden URLs for reader.
	 * 
	 * @param currentUrl
	 * @return true if current URL is forbiden
	 */
	private boolean notForReaderURL(String currentUrl) {
		if (currentUrl.startsWith("/subpub/admin/")) {
			return true;
		}
		return false;
	}
}