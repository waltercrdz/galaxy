package com.meli.api.galaxy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.meli.api.galaxy.model.Galaxy;

public interface GalaxyRepository extends MongoRepository<Galaxy, String> {

}
