package ua.kh.butov.subpub.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.entity.Account;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;


@WebServlet("/admin/accounts")
public class AllAccountsController extends AbstractController{
	private static final long serialVersionUID = 2700921951378677398L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Account>accounts = getAccountService().listAllAccounts(1, Constants.MAX_ACCOUNTS_PER_HTML_PAGE);
		req.setAttribute("accounts", accounts);
		int totalCount = getAccountService().countAllAccounts();
		req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_PUBLICATIONS_PER_HTML_PAGE));
		RoutingUtils.forwardToPage("accounts.jsp", req, resp);
	}
}
