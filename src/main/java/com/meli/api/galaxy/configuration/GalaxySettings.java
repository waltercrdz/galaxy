package com.meli.api.galaxy.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.meli.api.galaxy.model.Planet;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "galaxy")
public class GalaxySettings {
	
	private List<Planet> planets = Lists.newLinkedList();

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
}
