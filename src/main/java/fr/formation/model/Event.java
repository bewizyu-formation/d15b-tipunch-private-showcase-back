package fr.formation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.formation.utils.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String address;

    @NotNull
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dateTime;

    @ManyToOne
    @NotNull
    @JsonIgnore
    private Artist artist;

    @ManyToOne
    @NotNull
    @JsonIgnore
    private User organizer;

    @ManyToMany(mappedBy = "eventsAttended")
    @JsonIgnore
    private List<User> guests;

    public Event() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public List<User> getGuests() {
        return guests;
    }

    public void setGuests(List<User> guests) {
        this.guests = guests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(address, event.address) &&
                Objects.equals(dateTime, event.dateTime) &&
                Objects.equals(artist, event.artist) &&
                Objects.equals(organizer, event.organizer) &&
                Objects.equals(guests, event.guests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, dateTime, artist, organizer, guests);
    }
}
