package ua.kh.butov.subpub.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.kh.butov.subpub.service.NotificationService;

public class AsyncEmailNotificationService implements NotificationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncEmailNotificationService.class);
	private final ExecutorService executorService;

	private String smtpHost;
	private String smtpPort;
	private String smtpUsername;
	private String smtpPassword;
	private String fromEmail;
	private String tryCount;

	public AsyncEmailNotificationService(ServiceManager serviceManager) {
		executorService = Executors.newCachedThreadPool();
		this.smtpHost = serviceManager.getApplicationProperty("email.smtp.server");
		this.smtpPort = serviceManager.getApplicationProperty("email.smtp.port");
		this.smtpUsername = serviceManager.getApplicationProperty("email.smtp.username");
		this.smtpPassword = serviceManager.getApplicationProperty("email.smtp.password");
		this.fromEmail = serviceManager.getApplicationProperty("email.smtp.fromEmail");
		this.tryCount = serviceManager.getApplicationProperty("email.smtp.tryCount");
	}

	@Override
	public void sendNotificationMessage(String notificationAddress, String content) {
		executorService.submit(new EmailItem(notificationAddress, "New subscription", content, Integer.parseInt(tryCount)));
	}

	@Override
	public void close() {
		executorService.shutdown();
	}

	private class EmailItem implements Runnable {
		private final String emailAddress;
		private final String subject;
		private final String content;
		private int tryCount;

		private EmailItem(String emailAddress, String subject, String content, int tryCount) {
			this.emailAddress = emailAddress;
			this.subject = subject;
			this.content = content;
			this.tryCount = tryCount;
		}

		private boolean isValidTryCount() {
			return tryCount > 0;
		}

		public void run() {
			try {
				SimpleEmail email = new SimpleEmail();
				email.setCharset("utf-8");
				email.setHostName(smtpHost);
				email.setSSLOnConnect(true);
				email.setSslSmtpPort(smtpPort);
				email.setAuthenticator(new DefaultAuthenticator(smtpUsername, smtpPassword));
				email.setFrom(fromEmail);
				email.setSubject(subject);
				email.setMsg(content);
				email.addTo(emailAddress);
				email.send();
			} catch (EmailException e) {
				LOGGER.error("Can't send email: " + e.getMessage(), e);
				tryCount--;
				if (isValidTryCount()) {
					LOGGER.info("Resend email: {}", this.toString());
					executorService.submit(this);
				} else {
					LOGGER.error("Email was not sent: limit of try count");
				}
			} catch (Exception e) {
				LOGGER.error("Error during send email: " + e.getMessage(), e);
			}
		}

	}
}
