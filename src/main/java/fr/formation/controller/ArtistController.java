package fr.formation.controller;

import fr.formation.model.Artist;
import fr.formation.service.ArtistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private ArtistService artistService ;
    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @GetMapping()
    public  void create(){

        Artist a = new Artist();

        a.setLogin("travis");
        a.setPassword("daaa");
        a.setEmail("daaa@gmail.com");
        a.setCity(0);
        a.setArtistName("daaaBand");
        a.setShortDescription("we rock");

        System.out.println(a);
        this.artistService.save(a);

    }
}
