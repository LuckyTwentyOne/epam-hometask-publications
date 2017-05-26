package ua.kh.butov.subpub.service;

import javax.xml.bind.ValidationException;

import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.model.CurrentAccount;

public interface SubscriptionService {
	
	long makeSubscription(Publication publication, int numberOfMonthes, CurrentAccount account) throws ValidationException;

}
