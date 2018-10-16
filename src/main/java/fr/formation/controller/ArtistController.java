package fr.formation.controller;

import fr.formation.model.Artist;
import fr.formation.service.ArtistService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Secured("ROLE_ARTIST")
    @GetMapping("/{artistId}")
    public Artist findOne(@PathVariable String artistId) {
        return artistService.findOne(Long.parseLong(artistId));
    }

    @Secured("ROLE_ARTIST")
    @GetMapping()
    public List<Artist> findAll() {
        return artistService.findAll();
    }

    @PostMapping()
    public void signup(@RequestBody Artist artist) {
        artistService.save(artist);
    }

    @Secured("ROLE_ARTIST")
    @PutMapping("/{artistId}")
    public void update(@PathVariable Long artistId, @RequestBody Artist artist) {

        //SecurityContextHolder.getContext().getAuthentication().getPrincipal()

        artistService.update(artistId, artist);
    }

    @Secured("ROLE_ARTIST")
    @DeleteMapping("/{artistId}")
    public void delete(@PathVariable String artistId) {
        artistService.deleteById(Long.parseLong(artistId));
    }

    @Secured("ROLE_USER")
    @GetMapping("/department/{deptCode}")
    public List<Artist> findAllByDeptCode(@PathVariable String deptCode) {
        return artistService.findAllByDeptCode(deptCode);
    }
}
