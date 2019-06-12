package com.jee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jee.model.Event;
import com.jee.repository.EventRepository;

import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService {
 
 @Autowired
 private EventRepository eventRepository;

@Override
public List<Event> GetAllEvents() {
	return (List<Event>) eventRepository.findAll();
} 

public void remove(Integer id) {
	  eventRepository.deleteById(id);
}

public void add(Event event) {
	eventRepository.save(event);
}
}