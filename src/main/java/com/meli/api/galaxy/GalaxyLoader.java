package com.meli.api.galaxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meli.api.galaxy.model.Galaxy;
import com.meli.api.galaxy.service.GalaxyService;

@Component
public class GalaxyLoader implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(GalaxyLoader.class);
	
	private GalaxyService service;
	
	@Autowired
	public GalaxyLoader(GalaxyService repository) {
		this.service = repository;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("Checking if galaxy does exists...");
		
		if (!this.service.existsSomeGalaxy()) {
			LOG.info("Galaxy doesn't exists.");
			Galaxy galaxy = this.service.generateGalaxy();
			Galaxy galaxySaved = this.service.save(galaxy);
			LOG.info("Galaxy created = " + galaxySaved);
		} else {
			LOG.info("Galaxy already exists!");
		}
	}
}
