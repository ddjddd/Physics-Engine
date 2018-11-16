package com.engine.simulation;

public class Circle extends Thing {
	public Circle(String name, Vec2d pos, Vec2d vel, Vec2d acc, double mass, double rad) {
		super(name, pos, vel, acc, mass, rad);
		super.type = TYPE_CIRCLE;
	}
}