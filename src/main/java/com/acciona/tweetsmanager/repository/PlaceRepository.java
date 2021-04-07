package com.acciona.tweetsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acciona.tweetsmanager.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
