package com.engine.simulation;

import java.awt.geom.Point2D;

import com.engine.simulation.Vec2d;

public abstract class Thing {
	
	final protected static int TYPE_NONE = 0;
	final protected static int TYPE_CIRCLE = 1;
	final protected static int TYPE_SQUARE = 2;
	final protected static int TYPE_RECTANGLE = 3;

	private final String name;
	protected int type;
	private Vec2d pos, vel, acc;
	private double mass, rad;
		
	public Thing(String name, Vec2d pos, Vec2d vel, Vec2d acc, double mass, double rad) {
		type = TYPE_NONE;
		this.name = name;
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
		this.mass = mass;
		this.rad = rad;
	}
	
	public Vec2d pos() { return this.pos; }
	public double posX() { return this.pos.x; }
	public double posY() { return this.pos.y; }
	public Vec2d vel() { return this.vel; }
	public double velX() { return this.vel.x; }
	public double velY() { return vel.y; }
	public Vec2d acc() { return this.acc; }
	public double accX() { return this.acc.x; }
	public double accY() {return this.acc.y; }
	
	public double getRad() { return this.rad; }
	public double getDim() { return this.rad*2; }
	
	public void setPos(Vec2d newPos) { this.pos = newPos; }
	public void setPosX(double x) { this.pos.x = x; }
	public void setPosY(double y) { this.pos.y = y; }
	public void setVel(Vec2d newVel) {this.vel = newVel; }
	public void setVelX(double x) { this.vel.x = x; }
	public void setVelY(double y) {this.vel.y = y; }
	public void setAcc(Vec2d newAcc) { this.acc = newAcc; }
	public void setAccX(double x) {this.acc.x = x; }
	public void setAccY(double y) {this.acc.y = y; }
}