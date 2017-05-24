package ua.kh.butov.subpub.service;

import java.util.List;

import ua.kh.butov.subpub.entity.Publication;

public interface PublicationService {
	
	List<Publication> listAllPublications(int page, int limit);

}
