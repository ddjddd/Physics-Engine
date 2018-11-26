package com.engine.wall;

import java.awt.Shape;

import com.engine.simulation.Vec2d;

public abstract class Wall {
	public final static int TYPE_NONE = 0;
	public final static int TYPE_VERTICAL = 1;
	public final static int TYPE_HORIZONTAL = 2;
	public final static int TYPE_DEPENDENT_OF_X = 3;
	public final static int TYPE_DEPENDENT_OF_Y = 4;
	
	private String name;
	protected int type;
	protected Wall(String name) {
		this.name = name;
		this.type = TYPE_NONE;
	}
	public String getName() {
		return this.name;
	}
	public int getType() {
		return this.type;
	}
	//Shape
	abstract public Shape drawShape();
	
	
	public abstract boolean isToward(Vec2d pos, Vec2d vel);	//벽으로 향하고 있는지 반별
	public abstract float distance(Vec2d pos);
	public abstract void bounce(Vec2d pos, Vec2d vel, float charLength);
}