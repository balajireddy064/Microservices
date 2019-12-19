package com.ayusha.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayusha.tickets.entity.TicketHistryEntity;

public interface TicketHistryRepositry extends JpaRepository<TicketHistryEntity, Integer> {

}
