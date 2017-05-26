package ua.kh.butov.subpub.servlet.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.form.SearchForm;
import ua.kh.butov.subpub.servlet.AbstractController;
import ua.kh.butov.subpub.util.RoutingUtils;

@WebServlet("/search")
public class SearchController extends AbstractController {
	private static final long serialVersionUID = 1015660808630879774L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchForm searchForm = createSearchForm(req);
		if (searchForm.isEmpty()) {
			RoutingUtils.redirect("/subpub/publications", req, resp);
		} else {
			List<Publication> publications = getPublicationService().listPublicationsBySearchForm(searchForm, 1,
					Constants.MAX_PUBLICATIONS_PER_HTML_PAGE);
			req.setAttribute("publications", publications);
			int totalCount = getPublicationService().countPublicationsBySearchForm(searchForm);
			req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_PUBLICATIONS_PER_HTML_PAGE));
			req.setAttribute("publicationCount", totalCount);
			req.setAttribute("searchForm", searchForm);
			RoutingUtils.forwardToPage("search-result.jsp", req, resp);
		}
	}
}
