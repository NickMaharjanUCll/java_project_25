package be.ucll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ucll.model.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    List<Publication> findAllByTitleIgnoreCaseContaining(String title);

    List<Publication> findAllByTypeIgnoreCase(String type);

    List<Publication> findAllByTitleIgnoreCaseContainingAndTypeIgnoreCase(String title, String type);

    List<Publication> findAllByAvailableCopiesGreaterThanEqual(int availableCopies);

}

// Q&A First story always contains logic that needs to be understood by both team members. Should we both do it independently, or how should we divide the work, so that we both benifit from the first story. Pair programming.