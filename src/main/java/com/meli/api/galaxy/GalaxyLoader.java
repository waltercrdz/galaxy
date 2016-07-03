package com.meli.api.galaxy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.meli.api.galaxy.model.Galaxy;

public class GalaxyLoader implements InitializingBean {

	private Galaxy galaxy;
	
	@Autowired
	public GalaxyLoader(Galaxy galaxy) {
		this.galaxy = galaxy;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Printing galaxy...");
		System.out.println(this.galaxy.toString());
	}

	public Galaxy getGalaxy() {
		return galaxy;
	}

	public void setGalaxy(Galaxy galaxy) {
		this.galaxy = galaxy;
	}
}
