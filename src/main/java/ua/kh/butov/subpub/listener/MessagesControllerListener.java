package ua.kh.butov.subpub.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import ua.kh.butov.subpub.Constants;

@WebListener
public class MessagesControllerListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req = ((HttpServletRequest)sre.getServletRequest());
		String successMessage = (String) req.getSession().getAttribute(Constants.SUCCESS_MESSAGE);
		req.getSession().removeAttribute(Constants.SUCCESS_MESSAGE);
		req.setAttribute(Constants.SUCCESS_MESSAGE, successMessage);
		String unSuccessMessage = (String) req.getSession().getAttribute(Constants.UNSUCCESS_MESSAGE);
		req.getSession().removeAttribute(Constants.UNSUCCESS_MESSAGE);
		req.setAttribute(Constants.UNSUCCESS_MESSAGE, unSuccessMessage);
		}
	
}
