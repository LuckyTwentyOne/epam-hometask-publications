package ua.kh.butov.subpub.handler;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BigDecimalResultSetHandler implements ResultSetHandler<BigDecimal> {
	@Override
	public BigDecimal handle(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return rs.getBigDecimal(1);
		} else {
			return BigDecimal.ZERO;
		}
	}
}
