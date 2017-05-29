package ua.kh.butov.subpub.factory;

import java.sql.Connection;

import ua.kh.butov.subpub.exception.FrameworkSystemException;

public final class JDBCConnectionUtils {
	private JDBCConnectionUtils() {
	}

	private static final ThreadLocal<Connection> connections = new ThreadLocal<Connection>();

	public static Connection getCurrentConnection() {
		Connection c = connections.get();
		if (c == null) {
			throw new FrameworkSystemException(
					"Connection not found for current thread. Does your business service have @Transactional annotation?");
		}
		return c;
	}

	public static void setCurrentConnection(Connection c) {
		connections.set(c);
	}

	public static void removeCurrentConnection() {
		connections.remove();
	}
}