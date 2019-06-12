package com.jee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jee.model.Event;

@Repository("eventRepository")
public interface EventRepository extends CrudRepository<Event, Integer> {
}