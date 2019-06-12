package com.jee.service;

import java.util.List;

import com.jee.model.Event;

public interface EventService {
	public List<Event> GetAllEvents();
	public void remove(Integer id);
	public void add(Event event);
}