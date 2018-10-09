package fr.formation.controller;

import fr.formation.model.Artist;
import fr.formation.service.ArtistService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private ArtistService artistService ;
    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }
    @Secured("ROLE_ARTIST")
    @GetMapping("/{artistId}")
    public Artist findOne(@PathVariable String userId){
        return artistService.findOne(Long.parseLong(userId));
    }
    @Secured("ROLE_ARTIST")
    @GetMapping()
    public List<Artist> findAll(){
        return artistService.findAll();
    }

    @PostMapping()
    public void signup(@RequestBody Artist artist, @RequestParam String... roles) {

        artistService.save(artist, roles);
    }
    @Secured("ROLE_ARTIST")
    @PutMapping("/{artistId}")
    public void update(@PathVariable Long artistId, @RequestBody Artist artist){
        artistService.update(artistId, artist);
    }
    @Secured("ROLE_ARTIST")
    @DeleteMapping("/{artistId}")
    public void delete(@PathVariable String userId){
        artistService.deleteById(Long.parseLong(userId));
    }

    @Secured("ROLE_USER")
    @GetMapping("/department/{deptId}")
    public List<Artist> findAllByDeptId(@PathVariable Integer deptId){
        return artistService.findAllByDeptId(deptId);
    }
}
