package ua.kh.butov.subpub.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.kh.butov.subpub.exception.FrameworkSystemException;
import ua.kh.butov.subpub.handler.ResultSetHandler;

public final class JDBCUtils {

	public static <T> T select(Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object... parameters) {
		try (PreparedStatement ps = c.prepareStatement(sql)) {
			populatePreparedStatement(ps, parameters);
			ResultSet rs = ps.executeQuery();
			return resultSetHandler.handle(rs);
		} catch (SQLException e) {
			throw new FrameworkSystemException("Can't execute query: " + e.getMessage(), e);
		}
	}

	public static <T> T insert(Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object... parameters) {
		try (PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			populatePreparedStatement(ps, parameters);
			int result = ps.executeUpdate();
			if (result != 1) {
				throw new SQLException("Can't insert row to database. Result=" + result);
			}
			ResultSet rs = ps.getGeneratedKeys();
			return resultSetHandler.handle(rs);
		} catch (SQLException e) {
			throw new FrameworkSystemException("Can't execute query: " + e.getMessage(), e);
		}
	}

	public static int update(Connection c, String sql, Object... parameters) {
		try (PreparedStatement ps = c.prepareStatement(sql)) {
			populatePreparedStatement(ps, parameters);
			int result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			throw new FrameworkSystemException("Can't execute query: " + e.getMessage(), e);
		}
	}

	private static void populatePreparedStatement(PreparedStatement ps, Object... parameters) throws SQLException {
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
		}
	}

	private JDBCUtils() {
	}
}
