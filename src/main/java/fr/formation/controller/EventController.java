package fr.formation.controller;

import fr.formation.model.Event;
import fr.formation.model.User;
import fr.formation.service.ArtistService;
import fr.formation.service.EventService;
import fr.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {
    private EventService eventService;
    private UserService userService;
    private ArtistService artistService;

    @Autowired
    public EventController(EventService eventService, UserService userService, ArtistService artistService) {
        this.eventService = eventService;
        this.userService = userService;
        this.artistService = artistService;
    }

    /**
     * @param event : the event to turn into DTO object
     * @return : the DTO object to send data
     */
    private EventDto toDto(Event event) {
        List<Long> guestsIds = event.getGuests().stream().map(User::getId).collect(Collectors.toList());
        return new EventDto(event.getId(), event.getAddress(), event.getDateTime(), event.getArtist().getId(), event.getOrganizer().getId(), guestsIds);
    }

    /**
     * @param events : a collection of events to convert to DTO
     * @return : a collection of Event objects converted to EventDTO objects
     */
    private List<EventDto> toDtos(List<Event> events) {
        return events.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Secured("ROLE_USER")
    @GetMapping()
    public List<EventDto> findAll() {
        return toDtos(eventService.findAll());
    }

    @Secured("ROLE_USER")
    @GetMapping("/{eventId}")
    public Event findOne(@PathVariable String eventId) {
        return eventService.findOne(Long.parseLong(eventId));
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/{eventId}")
    public void deleteById(@PathVariable String eventId) {
        String loggedUserLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String organizerUsername = userService.findOne(this.findOne(eventId).getOrganizer().getId()).getLogin();

        // Checks if user making the delete request is the organizer of the event
        if (loggedUserLogin.equals(organizerUsername)) {
            eventService.deleteById(Long.parseLong(eventId));
        }
    }

    @Secured("ROLE_USER")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void save(@RequestBody EventDto eventDto) {
        String loggedUserLogin = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String organizerUsername = userService.findOne(eventDto.getOrganizerId()).getLogin();

        // Checks if user making the post request is the organizer of the event
        if (organizerUsername.equals(loggedUserLogin)) {
            Event event = new Event();
            event.setId(eventDto.getId());
            event.setOrganizer(userService.findOne(eventDto.getOrganizerId()));
            event.setArtist(artistService.findOne(eventDto.getArtistId()));
            event.setAddress(eventDto.getAddress());
            event.setDateTime(eventDto.getDateTime());

            eventService.save(event);
        }
    }

    // TODO : [WIP] user can join an event
//    @Secured("ROLE_USER")
//    @PostMapping(path = "/{eventId}/{userId}")
//    public void addGuest(@PathVariable String eventId, @PathVariable String userId) {
//        User user = userService.findOne(Long.parseLong(userId));
//
//        eventService.addGuest(Long.parseLong(eventId), user);
//    }

}
