package fr.formation.controller;

import fr.formation.model.Artist;
import fr.formation.service.ArtistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
        String[] depList = {"74","38","69"};
        a.setAllowedDepartment(new ArrayList<String>(Arrays.asList(depList)));

        System.out.println(a);
        this.artistService.save(a);

    }
}
