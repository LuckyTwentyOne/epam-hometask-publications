package ua.kh.butov.subpub.service;

import ua.kh.butov.subpub.entity.Subscription;

public interface NotificationContentBuilderService {
	String buildNewSubscriptionCreatedNotificationMessage(Subscription subscription);
}
