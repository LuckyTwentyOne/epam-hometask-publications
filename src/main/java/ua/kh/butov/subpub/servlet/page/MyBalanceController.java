package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.model.CurrentAccount;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;
import ua.kh.butov.subpub.util.SessionUtils;

@WebServlet("/my-balance")
public class MyBalanceController extends AbstractController {
	private static final long serialVersionUID = -1263933306603914221L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoutingUtils.forwardToPage("my-balance.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
		try {
			getAccountService().addMoneyToAccountByVaucher(currentAccount, Long.valueOf(req.getParameter("code")));
			SessionUtils.setCurrentAccount(req, currentAccount);
			RoutingUtils.redirect("/subpub/my-balance", req, resp);
		} catch (ValidationException e) {
			req.setAttribute(Constants.UNSUCCESS_MESSAGE, e.getMessage());
			RoutingUtils.forwardToPage("my-balance.jsp", req, resp);
		} catch (NumberFormatException e) {
			req.setAttribute(Constants.UNSUCCESS_MESSAGE, "You should fill 'code' correctly");
			RoutingUtils.forwardToPage("my-balance.jsp", req, resp);
		}
	}
}
