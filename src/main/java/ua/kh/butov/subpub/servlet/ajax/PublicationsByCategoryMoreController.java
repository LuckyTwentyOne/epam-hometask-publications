package ua.kh.butov.subpub.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;

@WebServlet("/ajax/html/more/publications/*")
public class PublicationsByCategoryMoreController extends AbstractController {
	private static final long serialVersionUID = -2651974520717714088L;
	private static final int SUBSTRING_INDEX = "/subpub/ajax/html/more/publications".length();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
		List<Publication> publications = getPublicationService().listPublicationsByCategory(categoryUrl, getPage(req), Constants.MAX_PUBLICATIONS_PER_HTML_PAGE);
		req.setAttribute("publications", publications);
		RoutingUtils.forwardToFragment("publication-list.jsp", req, resp);
	}
}
