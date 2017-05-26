package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.form.RegistrationForm;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;


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
	}

}
