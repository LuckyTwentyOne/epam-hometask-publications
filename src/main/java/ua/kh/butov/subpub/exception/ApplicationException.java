package ua.kh.butov.subpub.exception;

import javax.servlet.http.HttpServletResponse;

public class ApplicationException extends AbstractApplicationException {
	private static final long serialVersionUID = -6348577267642737705L;

	public ApplicationException(String message) {
		super(message, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public ApplicationException(Throwable cause) {
		super(cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
}
