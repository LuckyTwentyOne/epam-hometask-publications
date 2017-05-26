package ua.kh.butov.subpub.servlet.page;

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


@WebServlet("/publications/*")
public class PublicationsByCategoryController extends AbstractController {
	private static final long serialVersionUID = 1015660808630879774L;
	private static final int SUBSTRING_INDEX = "/subpub/publications".length();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
		List<Publication> publications = getPublicationService().listPublicationsByCategory(categoryUrl, 1, Constants.MAX_PUBLICATIONS_PER_HTML_PAGE);
		req.setAttribute("publications", publications);
		req.setAttribute("selectedCategoryUrl", categoryUrl);
		int totalCount = getPublicationService().countPublicationsByCategory(categoryUrl);
		req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_PUBLICATIONS_PER_HTML_PAGE));
		RoutingUtils.forwardToPage("publications.jsp", req, resp);
	}
}
