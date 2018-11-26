package com.engine.thing;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import com.engine.simulation.Vec2d;
import com.engine.wall.Wall;

public class Circle extends Thing {
	private float rad;
	
	public Circle(String name, Vec2d pos, Vec2d vel, Vec2d acc, float mass, float rad, float angular) {
		super(name, pos, vel, acc, mass, angular);
		super.type = TYPE_CIRCLE;
		this.rad = rad;
		shape = new Ellipse2D.Float(pos.getX() - rad, pos.getY() - rad, rad*2, rad*2);
	}

	@Override
	protected float charLength(Thing other) {
		return rad;
	}
	@Override
	protected float charLength(Wall wall) {
		return rad;
	}

	protected Ellipse2D.Float shape;
	@Override
	public Shape fillShape() {
		shape.x = pos.getX() - rad;
		shape.y = pos.getY() - rad;		
		return shape;
	}
}