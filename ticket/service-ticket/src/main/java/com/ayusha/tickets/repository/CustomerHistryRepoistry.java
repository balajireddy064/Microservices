package com.ayusha.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayusha.tickets.entity.CustomerHistryEntity;

public interface CustomerHistryRepoistry extends JpaRepository<CustomerHistryEntity, Integer> {
	

}
