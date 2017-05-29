package ua.kh.butov.subpub.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import ua.kh.butov.subpub.exception.AbstractApplicationException;
import ua.kh.butov.subpub.exception.AccessDeniedException;
import ua.kh.butov.subpub.exception.InternalServerErrorException;
import ua.kh.butov.subpub.exception.ResourceNotFoundException;
import ua.kh.butov.subpub.util.RoutingUtils;
import ua.kh.butov.subpub.util.UrlUtils;

@WebFilter(filterName = "ErrorHandlerFilter")
public class ErrorHandlerFilter extends AbstractFilter {
	private static final String INTERNAL_ERROR = "Internal error";

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(req, new ThrowExceptionInsteadOnSendErrorResponse(resp));
		} catch (Throwable th) {
			String requestUrl = req.getRequestURI();
			LOGGER.error("Request " + requestUrl + " failed: " + th.getMessage(), th);
			handleException(requestUrl, th, req, resp);
		}
	}

	private int getStatusCode(Throwable th) {
		if (th instanceof AbstractApplicationException) {
			return (((AbstractApplicationException) th).getCode());
		} else {
			return (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private void handleException(String requestUrl, Throwable th, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int statusCode = getStatusCode(th);
		resp.setStatus(statusCode);
		if (UrlUtils.isAjaxHtmlUrl(requestUrl)) {
			RoutingUtils.sendHTMLFragment(INTERNAL_ERROR, req, resp);
		} else {
			req.setAttribute("statusCode", statusCode);
			RoutingUtils.forwardToPage("error.jsp", req, resp);
		}
	}

	private static class ThrowExceptionInsteadOnSendErrorResponse extends HttpServletResponseWrapper {
		public ThrowExceptionInsteadOnSendErrorResponse(HttpServletResponse response) {
			super(response);
		}

		@Override
		public void sendError(int sc) throws IOException {
			sendError(sc, INTERNAL_ERROR);
		}

		@Override
		public void sendError(int sc, String msg) throws IOException {
			switch (sc) {
			case 403: {
				throw new AccessDeniedException(msg);
			}
			case 404: {
				throw new ResourceNotFoundException(msg);
			}
			default:
				throw new InternalServerErrorException(msg);
			}
		}
	}
}
