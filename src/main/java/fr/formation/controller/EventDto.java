package fr.formation.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class EventDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("date_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @JsonProperty("artist_id")
    private Long artistId;

    @JsonProperty("organizer_id")
    private Long organizerId;

    @JsonProperty("guests")
    private List<Long> guests;

    public EventDto() {}

    public EventDto(Long id, String address, LocalDateTime dateTime, Long artistId, Long organizerId, List<Long> guests) {
        this.id = id;
        this.address = address;
        this.dateTime = dateTime;
        this.artistId = artistId;
        this.organizerId = organizerId;
        this.guests = guests;
    }

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

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public List<Long> getGuests() {
        return guests;
    }

    public void setGuests(List<Long> guests) {
        this.guests = guests;
    }
}
