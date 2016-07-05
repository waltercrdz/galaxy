package com.meli.api.galaxy;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.meli.api.galaxy.configuration.GalaxySettings;
import com.meli.api.galaxy.model.Galaxy;
import com.meli.api.galaxy.model.Planet;
import com.meli.api.galaxy.service.GalaxyService;

@Component
public class GalaxyLoader implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(GalaxyLoader.class);

	private final static long DAYS_COUNT = 365 * 10;
	
	private GalaxyService service;
	private GalaxySettings settings;
	
	@Autowired
	public GalaxyLoader(GalaxyService repository, GalaxySettings settings) {
		this.service = repository;
		this.settings = settings;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("Checking if galaxy does exists...");
		
		if (!this.service.existsSomeGalaxy()) {
			LOG.info("Galaxy doesn't exists.");
			Galaxy galaxy = this.createGalaxy();
			Galaxy galaxySaved = this.service.save(galaxy);
			LOG.info("Galaxy created = " + galaxySaved);
		} else {
			LOG.info("Galaxy already exists!");
		}
	}
	
	public Galaxy createGalaxy() {
		Map<String, Planet> map = Maps.newHashMap();
		this.settings.getPlanets().stream().forEach(planet -> {
			planet.setAngle(0);
			planet.setXpos(planet.getDistanceFromSun().doubleValue());
			planet.setYpos(0D);
			map.put(planet.getName(), planet);
		});
		
		Galaxy galaxy = new Galaxy.Builder().planets(map).droughtDays(0).rainyDays(0).optimalDays(0).days(Maps.newHashMap()).build();
		
		Integer counter = 1;
		for (; counter <= DAYS_COUNT; counter++) {
			this.service.predict(galaxy, counter);
		}
		
		return galaxy;
	}
}
