package com.acciona.tweetsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acciona.tweetsmanager.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long>{

}
