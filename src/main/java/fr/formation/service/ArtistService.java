package fr.formation.service;

import fr.formation.model.Artist;
import fr.formation.model.UserRole;
import fr.formation.repository.ArtistRepository;
import fr.formation.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private UserRoleRepository userRoleRepository;
    private ArtistRepository artistRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, UserRoleRepository userRoleRepository, JdbcTemplate jdbcTemplate) {
        this.artistRepository = artistRepository;
        this.userRoleRepository = userRoleRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Artist artist) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(artist.getPassword());
        artist.setPassword(encryptedPassword);
        artist = artistRepository.save(artist);

        UserRole artistRole = new UserRole();
        artistRole.setRole("ARTIST");
        artistRole.setUserId(artist.getId());
        userRoleRepository.save(artistRole);

        UserRole userRole = new UserRole();
        userRole.setRole("USER");
        userRole.setUserId(artist.getId());
        userRoleRepository.save(userRole);
    }

    public Artist findOne(Long userId) {
        Optional<Artist> optionalArtist = artistRepository.findById(userId);
        return optionalArtist.isPresent() ? optionalArtist.get() : null;
    }

    public List<Artist> findAll() {
        return this.artistRepository.findAll();
    }

    public void update(Long id, Artist artist) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        optionalArtist.ifPresent(a -> {
            a = artist;
            this.save(a);
        });
    }

    public void deleteById(Long id) {
        this.artistRepository.deleteById(id);
    }

    public List<Artist> findAllByDeptCode(String deptCode) {
        List<Artist> artists = jdbcTemplate.query(
                "SELECT DISTINCT u.* FROM user as u, department_list_by_artist as da, departement as d, villes_france_free as v where u.id = da.artist_id AND d.departement_id = da.allowed_department AND d.departement_code = ?",
                new Object[] {deptCode},
                new BeanPropertyRowMapper<>(Artist.class)
        );
        artists.forEach(artist -> artist.setPassword(""));
        return artists;
    }
}
