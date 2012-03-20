package org.eyequery;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

public @Model
class Query {
	private String query = "select e from Entity e";

	@PostConstruct
	public void initialize() {
		System.out
				.println(this.getClass().getSimpleName() + " was constructed");
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
