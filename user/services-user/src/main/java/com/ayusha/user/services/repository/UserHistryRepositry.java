package com.ayusha.user.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayusha.user.services.entity.UserHistryEntity;

public interface UserHistryRepositry extends JpaRepository<UserHistryEntity, Integer> {

}
