package com.ab.vo;

public class City {
	private  long id;
	private  String name;
	private  State state;
	public City(){
		
	}
	public City(long id, String name, State state) {
		this.id = id;
		this.name = name;
		this.state = state;
	}
	public City(long id, String name) {
		this.id = id;
		this.name = name;
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
	public void setName(String name) {
		this.name = name;
	}
}
