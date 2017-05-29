package ua.kh.butov.subpub.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;


@WebServlet(urlPatterns = { "/admin/deletePublication" })
public class DeletePublicationController extends AbstractController {
	private static final long serialVersionUID = -3598712937305483742L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getPublicationService().deletePublication(Integer.valueOf(req.getParameter("id")));
		RoutingUtils.redirect("/subpub/publications", req, resp);
	}
}
