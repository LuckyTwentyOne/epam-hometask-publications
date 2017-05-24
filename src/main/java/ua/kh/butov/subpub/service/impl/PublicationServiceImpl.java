package ua.kh.butov.subpub.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.exception.InternalServerErrorException;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.jdbc.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.ResultSetHandlerFactory;
import ua.kh.butov.subpub.service.PublicationService;

public class PublicationServiceImpl implements PublicationService {

	private static final ResultSetHandler<List<Publication>> publicationResultSetHandler = 
			ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PUBLICATION_RESULT_SET_HANDLER);
	
	private final DataSource dataSource;
	
	public PublicationServiceImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Publication> listAllPublications(int page, int limit) {
		try (Connection c = dataSource.getConnection()) {
			int offset = (page - 1) * limit;
			return JDBCUtils.select(c, "select p.*, c.name as category from publication p, category c"
					+ " where c.id = p.id_category limit ? offset ?", publicationResultSetHandler, limit, offset);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

}
