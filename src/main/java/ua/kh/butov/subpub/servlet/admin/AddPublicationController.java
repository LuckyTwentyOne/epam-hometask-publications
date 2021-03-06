package ua.kh.butov.subpub.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.exception.ValidationException;
import ua.kh.butov.subpub.form.AddEditPublicationForm;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;


@WebServlet(urlPatterns = { "/admin/addPublication" })
public class AddPublicationController extends AbstractController {
	private static final long serialVersionUID = 8945354368309814120L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoutingUtils.forwardToPage("add-edit-publication.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AddEditPublicationForm form = createForm(req, AddEditPublicationForm.class);
		try {
			form.validate(getI18nService());
			getPublicationService().createNewPublication(form);
			req.getSession().setAttribute(Constants.SUCCESS_MESSAGE, "Publication "+form.getName()+" has been created");
			RoutingUtils.redirect("/subpub/publications", req, resp);
		} catch (ValidationException e) {
			req.setAttribute(Constants.UNSUCCESS_MESSAGE, e.getMessage());
			RoutingUtils.forwardToPage("add-edit-publication.jsp", req, resp);
		}
	}
}
