package ua.kh.butov.subpub.service.impl;

import java.math.BigDecimal;
import java.util.List;

import ua.kh.butov.subpub.annotation.jdbc.Transactional;
import ua.kh.butov.subpub.entity.Category;
import ua.kh.butov.subpub.entity.Publication;
import ua.kh.butov.subpub.form.AddEditPublicationForm;
import ua.kh.butov.subpub.form.SearchForm;
import ua.kh.butov.subpub.repository.CategoryRepository;
import ua.kh.butov.subpub.repository.PublicationRepository;
import ua.kh.butov.subpub.service.PublicationService;

@Transactional
public class PublicationServiceImpl implements PublicationService {

	private final PublicationRepository publicationRepository;
	private final CategoryRepository categoryRepository;

	public PublicationServiceImpl(ServiceManager serviceManager) {
		publicationRepository = serviceManager.publicationRepository;
		categoryRepository = serviceManager.categoryRepository;
	}

	@Override
	public List<Publication> listAllPublications(int page, int limit) {
		return publicationRepository.listAllPublications(page, limit);
	}

	@Override
	public List<Publication> listPublicationsByCategory(String categoryUrl, int page, int limit) {
		return publicationRepository.listPublicationsByCategory(categoryUrl, page, limit);
	}

	@Override
	public List<Category> listAllCategories() {
		return categoryRepository.listAllCategories();
	}

	@Override
	public int countAllPublications() {
		return publicationRepository.countAllPublications();
	}

	@Override
	public int countPublicationsByCategory(String categoryUrl) {
		return publicationRepository.countPublicationsByCategory(categoryUrl);
	}

	@Override
	public List<Publication> listPublicationsBySearchForm(SearchForm searchForm, int page, int limit) {
		return publicationRepository.listPublicationsBySearchForm(searchForm, page, limit);
	}

	@Override
	public int countPublicationsBySearchForm(SearchForm searchForm) {
		return publicationRepository.countPublicationsBySearchForm(searchForm);
	}

	@Override
	public Publication findById(int idPublication) {
		return publicationRepository.findById(idPublication);
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePublication(int idPublication) {
		publicationRepository.delete(idPublication);
	}

	@Override
	@Transactional(readOnly = false)
	public void updatePublication(AddEditPublicationForm form) {
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(form.getPrice()));
		Publication publication = new Publication(form.getName(), form.getDescription(), form.getConditions(), price,
				form.getCategory());
		publication.setId(form.getId());
		publicationRepository.updatePublication(publication);

	}

	@Override
	@Transactional(readOnly = false)
	public Publication createNewPublication(AddEditPublicationForm form) {
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(form.getPrice()));
		Publication result = publicationRepository.addPublication(new Publication(form.getName(), form.getDescription(),
				form.getConditions(), price, form.getCategory()));
		countPublicationsPerCategory();
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public void countPublicationsPerCategory() {
		List<Category> listCategories = categoryRepository.listAllCategories();
		for(Category category: listCategories){
			int countPublications = publicationRepository.countPublicationsByCategory(category.getUrl());
			categoryRepository.updatePublicationCount(countPublications, category.getId());
		}
	}
}
