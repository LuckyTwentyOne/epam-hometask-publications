package ua.kh.butov.subpub.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.kh.butov.subpub.entity.Account;
import ua.kh.butov.subpub.entity.Category;
import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.entity.Subscription;

public final class ResultSetHandlerFactory {

	public final static ResultSetHandler<Publication> PUBLICATION_RESULT_SET_HANDLER = new ResultSetHandler<Publication>() {
		@Override
		public Publication handle(ResultSet rs) throws SQLException {
			Publication p = new Publication();
			p.setCategory(rs.getString("category"));
			p.setDescription(rs.getString("description"));
			p.setConditions(rs.getString("conditions"));
			p.setId(rs.getInt("id"));
			p.setImageLink(rs.getString("image_link"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getBigDecimal("price"));
			return p;
		}
	};
	
	public final static ResultSetHandler<Category> CATEGORY_RESULT_SET_HANDLER = new ResultSetHandler<Category>() {
		@Override
		public Category handle(ResultSet rs) throws SQLException {
			Category c = new Category();
			c.setId(rs.getInt("id"));
			c.setUrl(rs.getString("url"));
			c.setName(rs.getString("name"));
			c.setPublicationCount(rs.getInt("publication_count"));
			return c;
		}
	};
	
	public final static ResultSetHandler<Account> ACCOUNT_RESULT_SET_HANDLER = new ResultSetHandler<Account>() {
		@Override
		public Account handle(ResultSet rs) throws SQLException {
			Account a = new Account();
			a.setId(rs.getInt("id"));
			a.setEmail(rs.getString("email"));
			a.setFistName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			a.setPassword(rs.getString("password"));
			if(rs.getInt("id_role")==0){
				a.setRole("admin");
			}else{
				a.setRole("reader");
			}
			a.setMoney(rs.getBigDecimal("money"));
			return a;
		}
	};
	
	public final static ResultSetHandler<Subscription> SUBSCRIPTION_RESULT_SET_HANDLER = new ResultSetHandler<Subscription>() {
		@Override
		public Subscription handle(ResultSet rs) throws SQLException {
			Subscription s = new Subscription();
			s.setId(rs.getLong("id"));
			s.setIdAccount(rs.getInt("id_account"));
			s.setCreated(rs.getTimestamp("created"));
			s.setExpirationDate(rs.getTimestamp("expiration_date"));
			s.setIdPublication(rs.getInt("id_publication"));
			return s;
		}
	};
	
	public final static ResultSetHandler<Integer> getCountResultSetHandler() {
		return new ResultSetHandler<Integer>() {
			@Override
			public Integer handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getInt(1);
				} else {
					return 0;
				}
			}
		};
	}

	public final static <T> ResultSetHandler<T> getSingleResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
		return new ResultSetHandler<T>() {
			@Override
			public T handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return oneRowResultSetHandler.handle(rs);
				} else {
					return null;
				}
			}
		};
	}

	public final static <T> ResultSetHandler<List<T>> getListResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
		return new ResultSetHandler<List<T>>() {
			@Override
			public List<T> handle(ResultSet rs) throws SQLException {
				List<T> list = new ArrayList<>();
				while (rs.next()) {
					list.add(oneRowResultSetHandler.handle(rs));
				}
				return list;
			}
		};
	}

	private ResultSetHandlerFactory() {
	}
}
