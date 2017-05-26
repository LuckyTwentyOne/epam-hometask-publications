package ua.kh.butov.subpub.tag;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.service.impl.ServiceManager;

public class ResourseBundleReaderTag extends TagSupport {
	private static final long serialVersionUID = -4641583315147784200L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourseBundleReaderTag.class);

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			ServiceManager serviceManager = ServiceManager.getInstance(request.getServletContext());
			Locale sessionLocale = (Locale) request.getSession().getAttribute(Constants.SESSION_LOCALE);
			String value = serviceManager.getI18nService().getMessage(key, sessionLocale);
			out.print(value);
		} catch (IOException e) {
			LOGGER.error("Exception at the tag " + this.getClass(), e);
		}
		return SKIP_BODY;
	}
	
	
}
