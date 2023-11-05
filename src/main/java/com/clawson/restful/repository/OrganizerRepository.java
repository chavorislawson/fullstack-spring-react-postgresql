package com.clawson.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clawson.restful.model.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long>{
    
}
