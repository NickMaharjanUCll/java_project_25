package be.ucll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.ucll.model.Publication;
import be.ucll.repository.PublicationRepository;

@Service
public class PublicationService {
    private PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> findPublicationsByTitleAndType(String title, String type) {
        if (title == null && type == null) {
            return publicationRepository.findAll();
        } else if (title == null) {
            return publicationRepository.findAllByTypeIgnoreCase(type);
        } else if (type == null) {
            return publicationRepository.findAllByTitleIgnoreCaseContaining(title);
        }
        return publicationRepository.findAllByTitleIgnoreCaseContainingAndTypeIgnoreCase(title, type);
    }

    public List<Publication> getPublicationByAvailableCopies(int availableCopies){
        List<Publication> publicationByAvailableCopies = publicationRepository.findAllByAvailableCopiesGreaterThanEqual(availableCopies);
     
        return publicationByAvailableCopies;
    }
    
}
