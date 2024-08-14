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
        // List<Publication> pr = new ArrayList<>();

        if (title == null && type == null) {
            // System.out.println(publicationRepository.allPublications());
            return publicationRepository.allPublications();
        } else if (title == null) {
            return publicationRepository.publicationsByType(type);
        } else if (type == null) {
            return publicationRepository.publicationsByTitle(title, publicationRepository.allPublications());
        }
        // List<Publication> aha = publicationRepository.publicationsByTitleAndType(title, type);
        // System.out.println("/n/n/n THHISSSS");
        // System.out.println(aha);
        return publicationRepository.publicationsByTitleAndType(title, type);
    }

    public List<Publication> getPublicationByAvailableCopies(int availableCopies){
        List<Publication> publicationByAvailableCopies = publicationRepository.publicationByAvailableCopies(availableCopies);
     
        return publicationByAvailableCopies;
    }
    
}
