package be.ucll.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.model.Publication;
import be.ucll.service.PublicationService;

@RestController
@RequestMapping("/publications")
public class PublicationRestController {
    private PublicationService publicationService;

    public PublicationRestController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public List<Publication> getPublicationsByTitleAndType(
        @RequestParam(value="title", required=false) String title,
        @RequestParam(value="type", required=false) String type
    )
        {
        // System.out.println(publicationService.findPublicationsByTitleAndType(title, type));
        return publicationService.findPublicationsByTitleAndType(title, type);
    } 

    @GetMapping("/stock/{availableCopies}")
    public List<Publication> getPublicationByAvailableCopies(@PathVariable int availableCopies){
        // System.out.println(publicationService.getPublicationByAvailableCopies(availableCopies));
        return publicationService.getPublicationByAvailableCopies(availableCopies);
    }
}
