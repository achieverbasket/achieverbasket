package com.ab.vo;

public class State {
	private  long id;
	private  String name;
	private  Country country;
	
	public State(){
		
	}
	
	public State(long id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}
	
	public State(long id, String name) {
		this.id = id;
		this.name = name;
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
