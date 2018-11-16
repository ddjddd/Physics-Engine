package com.engine.simulation;

public class Vec2d {
	protected double x;
	protected double y;
	
	public Vec2d() {
		x = 0.0;
		y = 0.0;
	}
	
	public Vec2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2d sum(Vec2d v1) {
		Vec2d v2 = new Vec2d(this.x + v1.x, this.y + v1.y);
		return v2;
	}
	
	public Vec2d sub(Vec2d v1) {
		Vec2d v2 = new Vec2d(this.x - v1.x, this.y - v1.y);
		return v2;
	}
	
	public void print() {
		System.out.printf("%f %f\n",x, y);
	}
}
