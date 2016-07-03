package com.meli.api.galaxy.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.meli.api.galaxy.model.Galaxy;
import com.meli.api.galaxy.model.Planet;

@Configuration
@ConfigurationProperties(locations = "classpath:application.yml", prefix = "galaxy")
public class GalaxyConfiguration {
	
	private List<Planet> planets = Lists.newLinkedList();
	
	@Bean
	public Galaxy galaxy() {
		this.planets.stream().forEach(planet -> {
			planet.setAngle(0);
			planet.setXpos(planet.getDistanceFromSun().doubleValue());
			planet.setYpos(0D);
		});
		
		Galaxy galaxy = new Galaxy.Builder().planets(planets).build();
		return galaxy;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
}
