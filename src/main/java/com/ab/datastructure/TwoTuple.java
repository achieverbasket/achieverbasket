package com.ab.datastructure;

public class TwoTuple<X, Y> {
	private final X x;
	private final Y y;

	public TwoTuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X getX() {
		return x;
	}

	public Y getY() {
		return y;
	}
}
