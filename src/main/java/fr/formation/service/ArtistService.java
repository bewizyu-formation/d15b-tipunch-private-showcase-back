package fr.formation.service;

import fr.formation.model.Artist;
import fr.formation.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private ArtistRepository artistRepository ;

    @Autowired
    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public void save(Artist artist){
        this.artistRepository.save(artist);
    }
    public Optional<Artist> findOne(Long id){
        return this.artistRepository.findById(id);
    }
    public List<Artist> findAll(){
        return this.artistRepository.findAll();
    }
    public void update(Artist artist){
        Optional<Artist> optionalArtist = this.findOne(artist.getId());
        optionalArtist.ifPresent(a -> {
            a = artist;
            this.save(a);
        } );
    }
    public void deleteById(Long id){
        this.artistRepository.deleteById(id);
    }

}
