package com.engine.simulation;

import java.awt.geom.Point2D;

import com.engine.simulation.Vec2d;

public class Thing {
	
	final protected static int TYPE_NONE = 0;
	final protected static int TYPE_CIRCLE = 1;
	final protected static int TYPE_SQUARE = 2;
	final protected static int TYPE_RECTANGLE = 3;

	private final String name;
	protected int type;
	private Vec2d pos, vel, acc;
	private double mass, rad;
		
	public Thing(String name, Vec2d pos, Vec2d vel, Vec2d acc, double m) {
		type = TYPE_NONE;
		this.name = name;
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
		this.mass = m;
		
		this.rad = 10.0;
	}
	
	public Vec2d pos() { return this.pos; }
	public double posX() { return this.pos.x; }
	public double posY() { return this.pos.y; }
	public Vec2d vel() { return this.vel; }
	public double velX() { return this.vel.x; }
	public double velY() { return vel.y; }
	
	public double getRadius() { return this.rad; }
	
	public void setPos(Vec2d newPos) { this.pos = newPos; }
	public void setVel(Vec2d newVel) {this.vel = newVel; }
		
}