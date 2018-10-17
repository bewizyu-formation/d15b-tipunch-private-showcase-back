package fr.formation.service;

import fr.formation.model.Event;
import fr.formation.model.User;
import fr.formation.repository.EventRepository;
import fr.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {
    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findOne(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.isPresent() ? optionalEvent.get() : null;
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public void deleteById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public void addGuest(Long eventId, User user) {
        Event eventToUpdate = eventRepository.getOne(eventId);
        List<User> guestListToUpdate = new ArrayList<>(eventToUpdate.getGuests());
        List<Event> eventsAttendedByUserToUpdate = new ArrayList<>(user.getEventsAttended());

        guestListToUpdate.add(user);
        eventToUpdate.setGuests(guestListToUpdate);
        eventsAttendedByUserToUpdate.add(eventToUpdate);
        user.setEventsAttended(eventsAttendedByUserToUpdate);

        userRepository.save(user);
        eventRepository.save(eventToUpdate);
    }
}
