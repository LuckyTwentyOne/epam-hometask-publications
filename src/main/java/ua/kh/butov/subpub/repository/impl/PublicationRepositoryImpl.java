package ua.kh.butov.subpub.repository.impl;

import java.util.List;

import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.factory.JDBCConnectionUtils;
import ua.kh.butov.subpub.form.SearchForm;
import ua.kh.butov.subpub.handler.DefaultListResultSetHandler;
import ua.kh.butov.subpub.handler.DefaultUniqueResultSetHandler;
import ua.kh.butov.subpub.handler.IntResultSetHandler;
import ua.kh.butov.subpub.handler.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.repository.PublicationRepository;

public class PublicationRepositoryImpl implements PublicationRepository {

	private final ResultSetHandler<List<Publication>> publicationsResultSetHandler = new DefaultListResultSetHandler<>(
			Publication.class);
	private final ResultSetHandler<Publication> publicationResultSetHandler = new DefaultUniqueResultSetHandler<>(
			Publication.class);
	private final ResultSetHandler<Integer> countResultSetHandler = new IntResultSetHandler();

	@Override
	public List<Publication> listAllPublications(int page, int limit) {
		int offset = (page - 1) * limit;
		return JDBCUtils
				.select(JDBCConnectionUtils.getCurrentConnection(),
						"select p.*, c.name as category from publication p, category c"
								+ " where c.id = p.id_category limit ? offset ?",
						publicationsResultSetHandler, limit, offset);
	}

	@Override
	public int countAllPublications() {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(), "select count(*) from publication",
				countResultSetHandler);
	}

	@Override
	public List<Publication> listPublicationsByCategory(String categoryUrl, int page, int limit) {
		int offset = (page - 1) * limit;
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select p.*, c.name as category from publication p, category c"
						+ " where c.id = p.id_category and c.url=? limit ? offset ?",
				publicationsResultSetHandler, categoryUrl, limit, offset);
	}

	@Override
	public int countPublicationsByCategory(String categoryUrl) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select count(p.*) from publication p, category c " + "where c.id=p.id_category and c.url=?",
				countResultSetHandler, categoryUrl);
	}

	@Override
	public List<Publication> listPublicationsBySearchForm(SearchForm searchForm, int page, int limit) {
		int offset = (page - 1) * limit;
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select p.*, c.name as category from publication p, category c"
						+ " where (p.name ilike ? or p.description ilike ?) and c.id = p.id_category order by p."
						+ searchForm.getSortedBy() + " limit ? offset ?",
				publicationsResultSetHandler, "%" + searchForm.getQuery() + "%", "%" + searchForm.getQuery() + "%",
				limit, offset);
	}

	@Override
	public int countPublicationsBySearchForm(SearchForm searchForm) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select count(*) from publication p, category c"
						+ " where (p.name ilike ? or p.description ilike ?) and c.id = p.id_category",
				countResultSetHandler, "%" + searchForm.getQuery() + "%", "%" + searchForm.getQuery() + "%");
	}

	@Override
	public Publication findById(int idPublication) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(),
				"select p.*, c.name as category from publication p, category c where c.id = p.id_category and p.id = ?",
				publicationResultSetHandler, idPublication);
	}

	@Override
	public int delete(int idPublication) {
		return JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(), "delete from publication where id=?",
				idPublication);
	}

	@Override
	public void updatePublication(Publication publication) {
		JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(),
				"update publication set name=?, description=?, conditions=?, price=?, id_category=? where id =?",
				publication.getName(), publication.getDescription(), publication.getConditions(),
				publication.getPrice(), publication.getIdCategory(), publication.getId());

	}

	@Override
	public Publication addPublication(Publication publication) {
		return JDBCUtils.insert(JDBCConnectionUtils.getCurrentConnection(),
				"insert into publication (id, name, description, conditions, price, id_category) values (nextval('publication_seq'),?,?,?,?,?)",
				publicationResultSetHandler, publication.getName(), publication.getDescription(),
				publication.getConditions(), publication.getPrice(), publication.getIdCategory());

	}

}
