package ua.kh.butov.subpub.repository.impl;

import java.util.List;

import ua.kh.butov.subpub.entity.Category;
import ua.kh.butov.subpub.factory.JDBCConnectionUtils;
import ua.kh.butov.subpub.handler.DefaultListResultSetHandler;
import ua.kh.butov.subpub.handler.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.repository.CategoryRepository;

public class CategoryRepositoryImpl implements CategoryRepository {

	private final ResultSetHandler<List<Category>> categoriesResultSetHandler = new DefaultListResultSetHandler<>(
			Category.class);

	@Override
	public List<Category> listAllCategories() {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(), "select * from category order by name",
				categoriesResultSetHandler);
	}

	@Override
	public void updatePublicationCount(int publicationCount, int idCategory) {
		JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(),
				"update category set publication_count=? where id=?", publicationCount, idCategory);

	}

}
