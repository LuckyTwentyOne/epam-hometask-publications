package ua.kh.butov.subpub.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.kh.butov.subpub.service.AccountService;
import ua.kh.butov.subpub.service.I18nService;
import ua.kh.butov.subpub.service.PublicationService;
import ua.kh.butov.subpub.service.SocialService;
import ua.kh.butov.subpub.service.SubscriptionService;

public class ServiceManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);
	public static ServiceManager getInstance(ServletContext context) {
		ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
		if (instance == null) {
			instance = new ServiceManager(context);
			context.setAttribute("SERVICE_MANAGER", instance);
		}
		return instance;
	}
	
	public PublicationService getPublicationService() {
		return publicationService;
	}

	public SubscriptionService getSubscriptionService() {
		return subscriptionService;
	}
	
	public I18nService getI18nService() {
		return i18nService;
	}
	
	public SocialService getSocialService() {
		return socialService;
	}
	
	public AccountService getAccountService() {
		return accountService;
	}

	private final Properties applicationProperties = new Properties();
	private final BasicDataSource dataSource;
	private final PublicationService publicationService;
	private final SubscriptionService subscriptionService;
	private final AccountService accountService;
	private final I18nService i18nService;
	private final SocialService socialService;
	private ServiceManager(ServletContext context) {
		loadApplicationProperties();
		dataSource = createDataSource();
		i18nService = new I18nServiceImpl();
		publicationService = new PublicationServiceImpl(dataSource);
		subscriptionService = new SubscriptionServiceImpl(dataSource, i18nService);
		accountService = new AccountServiceImpl(dataSource);
		socialService = new FacebookSocialService(this);
	}
	
	public String getApplicationProperty(String key) {
		String value = applicationProperties.getProperty(key);
		if (value.startsWith("${sysEnv.")) {
			String keyVal = value.replace("${sysEnv.", "").replace("}", "");
			value = System.getProperty(keyVal);
		}
		return value;
	}

	public void close() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			LOGGER.error("Close datasource failed: " + e.getMessage(), e);
		}
	}

	private void loadApplicationProperties() {
		try (InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
			applicationProperties.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private BasicDataSource createDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDefaultAutoCommit(false);
		dataSource.setRollbackOnReturn(true);
		dataSource.setDriverClassName(getApplicationProperty("db.driver"));
		dataSource.setUrl(getApplicationProperty("db.url"));
		dataSource.setUsername(getApplicationProperty("db.username"));
		dataSource.setPassword(getApplicationProperty("db.password"));
		dataSource.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
		dataSource.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
		return dataSource;
	}
}
