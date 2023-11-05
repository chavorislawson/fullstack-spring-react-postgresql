package com.clawson.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clawson.restful.model.Event;
public interface EventRepository extends JpaRepository<Event, Long>{
    
}
