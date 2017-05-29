package ua.kh.butov.subpub.service.impl;

import ua.kh.butov.subpub.entity.Subscription;
import ua.kh.butov.subpub.service.NotificationContentBuilderService;

public class NotificationContentBuilderServiceImpl implements NotificationContentBuilderService {

	private String host;
	
	public NotificationContentBuilderServiceImpl(ServiceManager serviceManager) {
		this.host = serviceManager.getApplicationProperty("app.host");
	}

	@Override
	public String buildNewSubscriptionCreatedNotificationMessage(Subscription subscription) {
		return host + "/subscription?id=" + subscription.getId();
	}
}
