package com.engine.thing;

import java.awt.Shape;

import com.engine.simulation.Calculator;
import com.engine.simulation.Config;
import com.engine.simulation.Vec2d;
import com.engine.wall.Wall;

public abstract class Thing {
	
	final protected static int TYPE_NONE = 0;
	final protected static int TYPE_CIRCLE = 1;
	final protected static int TYPE_SQUARE = 2;
	final protected static int TYPE_RECTANGLE = 3;

	private final String name;
	protected int type;
	protected Vec2d pos, vel, acc;
	protected float mass;
	protected float theta;
	protected float angular;
//	private boolean isStop;
		
	public Thing(String name, Vec2d pos, Vec2d vel, Vec2d acc, float mass, float angular) {
		type = TYPE_NONE;
		this.name = name;
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
		this.mass = mass;
		this.theta = 0;
		this.angular = angular;
	}
	
	//getter
	public Vec2d pos() { return this.pos; }
	public float posX() { return this.pos.getX(); }
	public float posY() { return this.pos.getY(); }
	public Vec2d vel() { return this.vel; }
	public float velX() { return this.vel.getX(); }
	public float velY() { return vel.getY(); }
	public Vec2d acc() { return this.acc; }
	public float accX() { return this.acc.getX(); }
	public float accY() {return this.acc.getY(); }
	public float mass() { return this.mass; }
	public float theta() { return this.theta; }
//	public boolean isStop() { return this.isStop; }
	 
	public String getName() { return this.name; }
	
	//setter
	public void setPos(Vec2d newPos) { this.pos = newPos; }
	public void setPosX(float x) { this.pos.setX(x); }
	public void setPosY(float y) { this.pos.setY(y); }
	public void setVel(Vec2d newVel) {this.vel = newVel; }
	public void setVelX(float x) { this.vel.setX(x); }
	public void setVelY(float y) {this.vel.setY(y); }
	public void setAcc(Vec2d newAcc) { this.acc = newAcc; }
	public void setAccX(float x) {this.acc.setX(x); }
	public void setAccY(float y) {this.acc.setY(y); }
	public void setMass(float mass) {this.mass = mass; }
	
	//charLength
	abstract protected float charLength(Thing other);
	abstract protected float charLength(Wall wall);
	//Shape
	abstract public Shape fillShape();
	
	public void bounce(Wall wall) {
		if(!wall.isToward(pos, vel))
			return;
		float charLength = charLength(wall);
		float dist = wall.distance(pos);
		if(dist <= charLength) {
			wall.bounce(pos, vel, charLength);
		}
	}
	
	// Calculate vectors after collision
	public void collide(Thing other) {
		float distance = Calculator.distance(this, other);
		float charLenOne = charLength(other);
		float charLenOther = other.charLength(this);
		boolean isCollision = false;
		if(distance <= charLenOne)
			isCollision = true;
		else {
			if(distance <= charLenOther)
				isCollision = true;
			else
				isCollision = distance < charLenOne + charLenOther;
		}
		if(!isCollision)
			return;
		inelastic_collision(this.mass, other.mass, this.vel, other.vel);
		seperate(this, other, distance, charLenOne, charLenOther);
	}
	
	
	final float COEF = Config.RESTITUTION_COEFF_THING + 1f;	//(1 + e)
	
	// Calculate inelastic collision
	private void inelastic_collision(float m1, float m2, Vec2d v1, Vec2d v2) {
		float totalMass = m1 + m2;
		float vi1, vi2, vf1, vf2;
		for(int i=0; i<Config.DIMENSION; i++) {
			vi1 = v1.getComponent(i);
			vi2 = v2.getComponent(i);
			vf1 = 	((m1 - m2 * Config.RESTITUTION_COEFF_THING) *  vi1 + (m2 * COEF) 	* vi2) / totalMass;
			vf2 = 	((m1 * COEF) * vi1 + (m2 - m1 * Config.RESTITUTION_COEFF_THING) * vi2) / totalMass;
			v1.setComponent(i, vf1);
			v2.setComponent(i, vf2);
		}
	}
	
	
	private void seperate(Thing t1, Thing t2, float distance, float charLength1, float charLength2) {
		float push = (charLength1 + charLength2 - distance);
		if(push<0)
			return;
		Vec2d pushVec = t1.pos.sub(t2.pos);
		Vec2d sep1 = pushVec.unit(); sep1.multiply(push);
		Vec2d sep2 = pushVec.unit(); sep2.multiply(-push);
		t1.pos.add(sep1);
		t2.pos.add(sep2);
	}
	
	public float calc_theta() { 
		float ret_theta = this.theta;
		
		ret_theta += this.angular / 60;
		
		this.theta = ret_theta;
		
		return ret_theta;
	}
	
}