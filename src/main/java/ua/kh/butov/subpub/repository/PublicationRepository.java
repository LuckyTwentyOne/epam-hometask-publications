package ua.kh.butov.subpub.repository;

import java.util.List;

import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.form.SearchForm;

public interface PublicationRepository {
	
	List<Publication> listAllPublications(int page, int limit);
	
	int countAllPublications();
	
	List<Publication> listPublicationsByCategory(String categoryUrl, int page, int limit);
	
	int countPublicationsByCategory(String categoryUrl);

	List<Publication> listPublicationsBySearchForm(SearchForm searchForm, int page, int limit);

	int countPublicationsBySearchForm(SearchForm searchForm);

	Publication findById(int idPublication);
	
	int delete(int idPublication);
	
	void updatePublication(Publication publication);
	
	Publication addPublication(Publication publication);

}
