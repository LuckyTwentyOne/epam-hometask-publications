package ua.kh.butov.subpub.service;

public interface NotificationService {
	void sendNotificationMessage(String notificationAddress, String content);
	
	void close();
}
