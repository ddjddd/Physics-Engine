package com.engine.simulation;

public class Vec2d {

	private float x;
	private float y;
	
	public Vec2d() {
		x = 0.0f;
		y = 0.0f;
	}
	
	public Vec2d(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	//getter and setter
	public float getX() { return x; }
	public float getY() { return y; }
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	
	//본래의 벡터 값을 건드리지 않는 연산
	public Vec2d sum(Vec2d v1) {
		return new Vec2d(this.x + v1.x, this.y + v1.y);
	}
	public Vec2d sub(Vec2d v1) {
		return new Vec2d(this.x - v1.x, this.y - v1.y);
	}
	public float sqr() {
		return x*x + y*y;
	}
	public float norm() {
		return (float)Math.sqrt((double)sqr());
	}
	public Vec2d unit() {
		float norm = norm();
		return unit(norm);
	}
	public Vec2d unit(float norm) {
		return new Vec2d(x / norm, y / norm);
	}
	
	public void multiply(float k) {
		this.x *= k;
		this.y *= k;
	}
	public void add(Vec2d v1) {
		this.x += v1.x;
		this.y += v1.y;
	}
	
	//get & set each component
	public float getComponent(int itr) {
		switch(itr) {
		case 0 : return this.x;
		case 1 : return this.y;
//		case 2 : return this.z;
		default : return -1;
		}
	}
	public void setComponent(int itr, float val) {
		switch(itr) {
		case 0 : this.x = val; break;
		case 1 : this.y = val; break;
//		case 2 : return this.z;
		default : return;
		}
	}
	
	public void print() {
		System.out.printf("%f %f\n",x, y);
	}
}