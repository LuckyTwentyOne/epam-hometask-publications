package ua.kh.butov.subpub.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.kh.butov.subpub.exception.ApplicationException;
import ua.kh.butov.subpub.form.AbstractForm;
import ua.kh.butov.subpub.form.SearchForm;
import ua.kh.butov.subpub.service.AccountService;
import ua.kh.butov.subpub.service.I18nService;
import ua.kh.butov.subpub.service.PublicationService;
import ua.kh.butov.subpub.service.SocialService;
import ua.kh.butov.subpub.service.SubscriptionService;
import ua.kh.butov.subpub.service.impl.ServiceManager;
import ua.kh.butov.subpub.util.SessionUtils;

public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = 8233947516885878614L;

	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private ServiceManager serviceManager;

	@Override
	public final void init() throws ServletException {
		serviceManager = ServiceManager.getInstance(getServletContext());
		initServlet();
	}

	protected void initServlet() throws ServletException {
	}

	@Override
	public final void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public final PublicationService getPublicationService() {
		return serviceManager.getPublicationService();
	}

	public final SubscriptionService getSubscriptionService() {
		return serviceManager.getSubscriptionService();
	}
	
	public final SocialService getSocialService() {
		return serviceManager.getSocialService();
	}
	
	public final I18nService getI18nService() {
		return serviceManager.getI18nService();
	}
	
	public final AccountService getAccountService() {
		return serviceManager.getAccountService();
	}

	public final int getPageCount(int totalCount, int itemsPerPage) {
		int res = totalCount / itemsPerPage;
		if (res * itemsPerPage != totalCount) {
			res++;
		}
		return res;
	}

	public final int getPage(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			return 1;
		}
	}

	public final SearchForm createSearchForm(HttpServletRequest request) {
		String sortBy = request.getParameter("sortBy");
		SearchForm result; 
		if(StringUtils.isNotBlank(sortBy)) {
			result = new SearchForm(request.getParameter("query"),sortBy);
		}else{
			result = new SearchForm(request.getParameter("query"),"id");
		}
		return result;
	}
	
	public final <T extends AbstractForm> T createForm(HttpServletRequest req, Class<T> formClass) throws ServletException {
		try {
			T form = formClass.newInstance();
			Locale locale = null;
			if (SessionUtils.getSessionLocale(req) == null) {
				locale = Locale.getDefault();
			}else{
				locale = SessionUtils.getSessionLocale(req);
			}
			form.setLocale(locale);
			BeanUtils.populate(form, req.getParameterMap());
			return form;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new ApplicationException("Can't create form "+formClass+" for request: "+e.getMessage(), e);
		}
	}
}
