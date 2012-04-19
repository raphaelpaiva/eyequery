package org.eyequery.testEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fruit {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;

	public Fruit() {}
	
	public Fruit(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Fruit[ id=" + id + ", name=" + name + " ]";
	}
}
