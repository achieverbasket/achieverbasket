package com.ab.vo;

public class City {
	private final long id;
	private final String name;
	private final State state;
	
	public City(long id, String name, State state) {
		this.id = id;
		this.name = name;
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public State getState() {
		return state;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + state + "]";
	}
}
