package ua.kh.butov.subpub.servlet;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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
			T form = createForm(formClass, req);
			Locale locale = null;
			if (SessionUtils.getSessionLocale(req) == null) {
				locale = Locale.getDefault();
			}else{
				locale = SessionUtils.getSessionLocale(req);
			}
			form.setLocale(locale);
			return form;
		} catch (UnsupportedEncodingException e) {
			throw new ApplicationException("Can't create form "+formClass+" for request: "+e.getMessage(), e);
		}
	}
	
	private <T> T createForm(Class<T> formClass, HttpServletRequest req) throws UnsupportedEncodingException {
		try {
			T form = formClass.newInstance();
			Field[] fields = formClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String value = new String(req.getParameter(field.getName()).getBytes("ISO-8859-1"),"UTF-8");
				if (value != null) {
					Object convertedValue = convert(field.getType(), value);
					field.set(form, convertedValue);
				}
			}
			return form;
		} catch (InstantiationException | IllegalAccessException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	private Object convert(Class<?> type, String value) {
		if (type == String.class) {
			return value;
		} else if (type == Integer.TYPE) {
			if(value.equals("")){
				return Integer.parseInt("0");
			}else{
				return Integer.parseInt(value);
			}
		} else if (type == Boolean.TYPE) {
			return value != null;
		} else if (type == Long.class) {
			return Long.parseLong(value);
		} else {
			throw new IllegalArgumentException("Can't convert to " + type);
		}
	}
}
