package fr.formation.service;

import fr.formation.controller.EventDto;
import fr.formation.model.Event;
import fr.formation.model.User;
import fr.formation.repository.EventRepository;
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

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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

    // TODO : [WIP] user can join an event
//    public void addGuest(Long eventId, User user) {
//        Event eventToUpdate = eventRepository.getOne(eventId);
//        List<User> guests = new ArrayList<>(eventToUpdate.getGuests());
//        guests.add(user);
//        eventToUpdate.setGuests(guests);
//        eventRepository.save(eventToUpdate);
//    }
}
