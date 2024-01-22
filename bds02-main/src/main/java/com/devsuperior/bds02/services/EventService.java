package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;



@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity(new City(dto.getCityId(), null));
		entity = eventRepository.save(entity);
		return new EventDTO(entity);
		
	}
	
	@Transactional
	public EventDTO update(EventDTO dto, Long id) {
		try {
		    @SuppressWarnings("deprecation")
			Event event = eventRepository.getOne(id);
		    // Event entity = new Event();
		    event.setName(dto.getName());
		    event.setDate(dto.getDate());
		    event.setUrl(dto.getUrl());
		    event.setCity(new City(dto.getCityId(), null));
		    
		    return new EventDTO(eventRepository.save(event));
		}
		catch(EntityNotFoundException e) {
			 throw new ResourceNotFoundException("Id not found " + id);
		}
		
	}
	

}
