package com.ab.vo;

public class State {
	private final long id;
	private final String name;
	private final Country country;

	public State(long id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", country=" + country + "]";
	}
}
