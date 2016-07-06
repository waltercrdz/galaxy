package com.meli.api.galaxy.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.meli.api.galaxy.configuration.GalaxySettings;
import com.meli.api.galaxy.model.Coordinates;
import com.meli.api.galaxy.model.Galaxy;
import com.meli.api.galaxy.model.Planet;
import com.meli.api.galaxy.model.RainyDay;
import com.meli.api.galaxy.model.StatusDay;
import com.meli.api.galaxy.model.Weather;
import com.meli.api.galaxy.repository.GalaxyRepository;
import com.meli.api.galaxy.utils.PlanetUtils;

@Service
public class GalaxyService {

	private static final Logger LOG = LoggerFactory.getLogger(GalaxyService.class);
	
	private final static long DAYS_COUNT = 365 * 10;

	private GalaxyRepository repository;
	private GalaxySettings settings;
	
	@Autowired
	public GalaxyService(GalaxyRepository repository, GalaxySettings settings) {
		this.repository = repository;
		this.settings = settings;
	}
	
	public Galaxy generateGalaxy() {
		Map<String, Planet> map = Maps.newHashMap();
		this.settings.getPlanets().stream().forEach(planet -> {
			planet.setAngle(0);
			planet.setCoordinate(new Coordinates.Builder().xpos(planet.getDistanceFromSun().doubleValue()).ypos(0D).build());
			map.put(planet.getName(), planet);
		});
		
		Galaxy galaxy = new Galaxy.Builder().planets(map).droughtDays(0).rainyDays(0).optimalDays(0).days(Maps.newHashMap()).build();
		
		Integer counter = 1;
		for (; counter <= DAYS_COUNT; counter++) {
			this.predict(galaxy, counter);
		}
		
		return galaxy;
	}
	
	public boolean existsSomeGalaxy() {
		return this.repository.count() != 0;
	}
	
	public Galaxy save(Galaxy galaxy) {
		Preconditions.checkArgument(Objects.nonNull(galaxy));
		return this.repository.save(galaxy);
	}

	public Galaxy getByDay(Integer day) {
		Galaxy galaxy = findGalaxy();
		
		this.predict(galaxy, day);
		
		LOG.info(galaxy.toString());
		return galaxy;
	}

	private Galaxy findGalaxy() {
		List<Galaxy> resultSet = this.repository.findAll();
		return resultSet.get(0);
	}
	
	public Integer getDroughtDays() {
		Galaxy galaxy = this.findGalaxy();
		return galaxy.getDroughtDays();
	}
	
	public Integer getRainyDays() {
		Galaxy galaxy = this.findGalaxy();
		return galaxy.getRainyDays();
	}
	
	public Integer getOptimalDays() {
		Galaxy galaxy = this.findGalaxy();
		return galaxy.getOptimalDays();
	}
	
	public Optional<StatusDay> getWeatherByDay(Integer day) {
		Galaxy galaxy = findGalaxy();
		
		this.predict(galaxy, day);
		
		LOG.info(galaxy.toString());
		return Optional.ofNullable(galaxy.getDays().get(day));
	}
	
	public void predict(Galaxy galaxy, Integer day) {
		this.movePlanets(galaxy, day);
		this.updateWeather(galaxy, day);
	}

	private void movePlanets(Galaxy galaxy, Integer day) {
		galaxy.movePlanets(day);
	}

	private void updateWeather(Galaxy galaxy, Integer day) {
		Planet vulcano = galaxy.getPlanets().get("vulcano");
		Planet ferengi = galaxy.getPlanets().get("ferengi");
		Planet betasoide = galaxy.getPlanets().get("betasoide");
		
		if (this.isDroughtWeather(vulcano, ferengi, betasoide)) {
			galaxy.setDroughtDays(galaxy.getDroughtDays() + 1);
			galaxy.getDays().put(day, new StatusDay.Builder().day(day).weather(Weather.drought).build());
		} else if(this.isRainyWeather(vulcano, ferengi, betasoide)) {
			galaxy.setRainyDays(galaxy.getRainyDays() + 1);
			galaxy.getDays().put(day, new StatusDay.Builder().day(day).weather(Weather.rainy).build());
			
			RainyDay currentRainyDay = new RainyDay.Builder().day(day).perimeter(PlanetUtils.perimeter(vulcano.getCoordinate(), ferengi.getCoordinate(), betasoide.getCoordinate())).build();
			
			if (Objects.isNull(galaxy.getMaxRainyDay()) || galaxy.getMaxRainyDay().getPerimeter() < currentRainyDay.getPerimeter()) {
				galaxy.setMaxRainyDay(currentRainyDay);
			}
		} else if(this.isOptimalWeather(vulcano, ferengi, betasoide)) {
			galaxy.setOptimalDays(galaxy.getOptimalDays() + 1);
			galaxy.getDays().put(day, new StatusDay.Builder().day(day).weather(Weather.optimal).build());
		}
	}
	
	private boolean isDroughtWeather(Planet vulcano, Planet ferengi, Planet betasoide) {
		Integer vangle = vulcano.getAngle();
		Integer fangle = ferengi.getAngle();
		Integer bangle = betasoide.getAngle();
		
		return PlanetUtils.areAligned(vangle, fangle) && PlanetUtils.areAligned(fangle, bangle);
	}
	
	private boolean isRainyWeather(Planet vulcano, Planet ferengi, Planet betasoide) {
		Coordinates sunCoordinates = new Coordinates.Builder().xpos(0D).ypos(0D).build();
		return PlanetUtils.isPointInTriangle(vulcano.getCoordinate(), ferengi.getCoordinate(), betasoide.getCoordinate(), sunCoordinates);
	}
	
	private boolean isOptimalWeather(Planet vulcano, Planet ferengi, Planet betasoide) {
		return PlanetUtils.areAlignedButNotWithCenter(vulcano.getCoordinate(), ferengi.getCoordinate(), betasoide.getCoordinate());
	}
}