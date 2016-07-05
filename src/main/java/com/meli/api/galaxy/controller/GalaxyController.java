package com.meli.api.galaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.api.galaxy.model.Galaxy;
import com.meli.api.galaxy.model.StatusDay;
import com.meli.api.galaxy.service.GalaxyService;

@RestController
@RequestMapping("galaxy")
public class GalaxyController {

	private final GalaxyService service;

	@Autowired
	public GalaxyController(GalaxyService service) {
		this.service = service;
	}

	@RequestMapping(value = "{day}", method = RequestMethod.GET)
	public ResponseEntity<Galaxy> getGalaxy(@PathVariable Integer day) {
		if (day < 0)
			return new ResponseEntity<Galaxy>(HttpStatus.BAD_REQUEST);

		Galaxy galaxy = this.service.getByDay(day);
		return new ResponseEntity<Galaxy>(galaxy, HttpStatus.OK);
	}

	@RequestMapping(value = "weather/{day}", method = RequestMethod.GET)
	public ResponseEntity<StatusDay> getWeatherByDay(@PathVariable Integer day) {
		if (day < 0)
			return new ResponseEntity<StatusDay>(HttpStatus.BAD_REQUEST);

		StatusDay weather = this.service.getWeatherByDay(day);
		return new ResponseEntity<StatusDay>(weather, HttpStatus.OK);
	}
}