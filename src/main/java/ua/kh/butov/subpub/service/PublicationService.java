package ua.kh.butov.subpub.service;

import java.util.List;

import ua.kh.butov.subpub.entity.Category;
import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.form.AddEditPublicationForm;
import ua.kh.butov.subpub.form.SearchForm;

public interface PublicationService {
	
	List<Publication> listAllPublications(int page, int limit);
	
	int countAllPublications();
	
	List<Publication> listPublicationsByCategory(String categoryUrl, int page, int limit);
	
	int countPublicationsByCategory(String categoryUrl);
	
	List<Category> listAllCategories();
	
	List<Publication> listPublicationsBySearchForm(SearchForm searchForm, int page, int limit);
	
	int countPublicationsBySearchForm(SearchForm searchForm);
	
	Publication findById(int idPublication);
	
	void deletePublication(int idPublication);
	
	void updatePublication(AddEditPublicationForm form);
	
	Publication createNewPublication(AddEditPublicationForm form);
	
	void countPublicationsPerCategory();

}
