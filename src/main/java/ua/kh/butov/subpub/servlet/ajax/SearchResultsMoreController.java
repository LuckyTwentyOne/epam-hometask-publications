package ua.kh.butov.subpub.servlet.ajax;

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


@WebServlet("/ajax/html/more/search")
public class SearchResultsMoreController extends AbstractController {
	private static final long serialVersionUID = -2651974520717714088L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchForm searchForm = createSearchForm(req);
		List<Publication> publications = getPublicationService().listPublicationsBySearchForm(searchForm, getPage(req), Constants.MAX_PUBLICATIONS_PER_HTML_PAGE);
		req.setAttribute("publications", publications);
		RoutingUtils.forwardToFragment("publication-list.jsp", req, resp);
	}
}
