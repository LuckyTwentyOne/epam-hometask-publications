package ua.kh.butov.subpub.repository;

import java.util.List;

import ua.kh.butov.subpub.entity.Category;

public interface CategoryRepository {
	
	List<Category> listAllCategories();
	
	void updatePublicationCount(int publicationCount, int idCategory);

}
