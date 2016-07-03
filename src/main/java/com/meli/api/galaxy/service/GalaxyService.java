package com.meli.api.galaxy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.api.galaxy.model.Galaxy;

@Service
public class GalaxyService {

	private static final Logger LOG = LoggerFactory.getLogger(GalaxyService.class);
	
	private Galaxy galaxy;
	
	@Autowired
	public GalaxyService(Galaxy galaxy) {
		this.galaxy = galaxy;
	}

	public Galaxy getByDay(Integer day) {
		galaxy.setDay(day);
		galaxy.getPlanets().stream().forEach(planet -> planet.move(day));
		LOG.info(galaxy.toString());
		return galaxy;
	}
}