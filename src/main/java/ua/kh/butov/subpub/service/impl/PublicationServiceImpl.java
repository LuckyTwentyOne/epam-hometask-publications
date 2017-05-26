package ua.kh.butov.subpub.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ua.kh.butov.subpub.entity.Category;
import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.exception.InternalServerErrorException;
import ua.kh.butov.subpub.form.SearchForm;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.jdbc.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.ResultSetHandlerFactory;
import ua.kh.butov.subpub.service.PublicationService;

public class PublicationServiceImpl implements PublicationService {

	private static final ResultSetHandler<List<Publication>> publicationsResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.PUBLICATION_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Publication> publicationResultSetHandler = ResultSetHandlerFactory
			.getSingleResultSetHandler(ResultSetHandlerFactory.PUBLICATION_RESULT_SET_HANDLER);
	private static final ResultSetHandler<List<Category>> categoryResultSetHandler = ResultSetHandlerFactory
			.getListResultSetHandler(ResultSetHandlerFactory.CATEGORY_RESULT_SET_HANDLER);
	private static final ResultSetHandler<Integer> countResultSetHandler = ResultSetHandlerFactory
			.getCountResultSetHandler();

	private final DataSource dataSource;

	public PublicationServiceImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Publication> listAllPublications(int page, int limit) {
		try (Connection c = dataSource.getConnection()) {
			int offset = (page - 1) * limit;
			return JDBCUtils.select(c,
					"select p.*, c.name as category from publication p, category c"
							+ " where c.id = p.id_category limit ? offset ?",
					publicationsResultSetHandler, limit, offset);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Publication> listPublicationsByCategory(String categoryUrl, int page, int limit) {
		try (Connection c = dataSource.getConnection()) {
			int offset = (page - 1) * limit;
			return JDBCUtils.select(c,
					"select p.*, c.name as category from publication p, category c"
							+ " where c.id = p.id_category and c.url=? limit ? offset ?",
					publicationsResultSetHandler, categoryUrl, limit, offset);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Category> listAllCategories() {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select * from category order by name", categoryResultSetHandler);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public int countAllPublications() {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select count(*) from publication", countResultSetHandler);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public int countPublicationsByCategory(String categoryUrl) {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c, "select count(p.*) from publication p, category c "
					+ "where c.id=p.id_category and c.url=?", countResultSetHandler,
					categoryUrl);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Publication> listPublicationsBySearchForm(SearchForm searchForm, int page, int limit) {
		try (Connection c = dataSource.getConnection()) {
			int offset = (page - 1) * limit;
			return JDBCUtils.select(c,
					"select p.*, c.name as category from publication p, category c"
							+ " where (p.name ilike ? or p.description ilike ?) and c.id = p.id_category order by p."+searchForm.getSortedBy()+" limit ? offset ?",
					publicationsResultSetHandler, "%"+searchForm.getQuery()+"%", "%"+searchForm.getQuery()+"%", limit, offset);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}

	@Override
	public int countPublicationsBySearchForm(SearchForm searchForm) {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c,
					"select count(*) from publication p, category c"
							+ " where (p.name ilike ? or p.description ilike ?) and c.id = p.id_category",
					countResultSetHandler, "%"+searchForm.getQuery()+"%", "%"+searchForm.getQuery()+"%");
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}
	
	@Override
	public Publication findById(int idPublication) {
		try (Connection c = dataSource.getConnection()) {
			return JDBCUtils.select(c,
					"select p.*, c.name as category from publication p, category c where c.id = p.id_category and p.id = ?",
					publicationResultSetHandler, idPublication);
		} catch (SQLException e) {
			throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
		}
	}
}
