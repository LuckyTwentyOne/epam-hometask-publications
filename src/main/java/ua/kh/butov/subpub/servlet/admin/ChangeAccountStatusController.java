package ua.kh.butov.subpub.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;


@WebServlet("/admin/active-status")
public class ChangeAccountStatusController extends AbstractController{
	private static final long serialVersionUID = 2700921951378677398L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getAccountService().changeAccountStatus(Integer.valueOf(req.getParameter("idAccount")));
		RoutingUtils.redirect("/subpub/admin/accounts", req, resp);
	}
}
