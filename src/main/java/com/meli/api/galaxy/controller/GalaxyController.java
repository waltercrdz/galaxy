package com.meli.api.galaxy.controller;

import java.util.Optional;

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

		Optional<StatusDay> optionalResult = this.service.getWeatherByDay(day);
		
		if (!optionalResult.isPresent()) {
			return new ResponseEntity<StatusDay>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<StatusDay>(optionalResult.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "weather/drought-count", method = RequestMethod.GET)
	public ResponseEntity<Integer> getDroughtDays() {
		Integer droughtCount = this.service.getDroughtDays();
		return new ResponseEntity<Integer>(droughtCount, HttpStatus.OK);
	}
	
	@RequestMapping(value = "weather/rainy-count", method = RequestMethod.GET)
	public ResponseEntity<Integer> getRainyDays() {
		Integer rainyCount = this.service.getRainyDays();
		return new ResponseEntity<Integer>(rainyCount, HttpStatus.OK);
	}
	
	@RequestMapping(value = "weather/optimal-count", method = RequestMethod.GET)
	public ResponseEntity<Integer> getOptimalDays() {
		Integer optimalCount = this.service.getOptimalDays();
		return new ResponseEntity<Integer>(optimalCount, HttpStatus.OK);
	}
}