package fr.formation.service;

import fr.formation.model.Artist;
import fr.formation.model.UserRole;
import fr.formation.repository.ArtistRepository;
import fr.formation.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private UserRoleRepository userRoleRepository;
    private ArtistRepository artistRepository ;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, UserRoleRepository userRoleRepository){
        this.artistRepository = artistRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public void save(Artist artist, String... roles) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(artist.getPassword());
        artist.setPassword(encryptedPassword);
        artist = artistRepository.save(artist);

        for (String role : roles) {

            UserRole userRole = new UserRole();
            userRole.setRole(role.toUpperCase());
            userRole.setUserId(artist.getId());

            userRoleRepository.save(userRole);
        }

    }
    public Artist findOne(Long userId){
        Optional<Artist> optionalArtist = artistRepository.findById(userId);
        return optionalArtist.isPresent() ? optionalArtist.get() : null;
    }

    public List<Artist> findAll(){
        return this.artistRepository.findAll();
    }
    public void update(Long id, Artist artist){
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        optionalArtist.ifPresent(a -> {
            a = artist;
            this.save(a);
        } );
    }
    public void deleteById(Long id){
        this.artistRepository.deleteById(id);
    }

    public List<Artist> findAllByDeptId(Integer deptId){
        return this.artistRepository.findArtistsByAllowedDepartmentLike(deptId);
    }
}
