package ua.kh.butov.subpub.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.kh.butov.subpub.Constants;
import ua.kh.butov.subpub.service.impl.ServiceManager;

/**
 * Context listener.
 * 
 * @author V Butov
 * 
 */
@WebListener
public class SubpubApplicationListener implements ServletContextListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubpubApplicationListener.class);
	
	private ServiceManager serviceManager;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			serviceManager = ServiceManager.getInstance(sce.getServletContext());
			sce.getServletContext().setAttribute(Constants.CATEGORY_LIST, serviceManager.getPublicationService().listAllCategories());
		} catch (RuntimeException e) {
			LOGGER.error("Web application 'subpub' init failed: " + e.getMessage(), e);
			throw e;
		}
		LOGGER.info("Web application 'subpub' initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		serviceManager.close();
		LOGGER.info("Web application 'subpub' destroyed");
	}
}
