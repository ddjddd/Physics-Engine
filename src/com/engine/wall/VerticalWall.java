package com.engine.wall;

import java.awt.Shape;
import java.awt.geom.Line2D;

import com.engine.simulation.Calculator;
import com.engine.simulation.Config;
import com.engine.simulation.Vec2d;

public class VerticalWall extends Wall {
	protected float x1;
	protected VerticalWall(String name) { super(name); }
	public VerticalWall(String name, float x1) {
		super(name);
		this.type = TYPE_VERTICAL;
		this.x1 = x1;
		shape = new Line2D.Float(x1, 0, x1, Config.DP_BOTTOM);
	}

	@Override
	public boolean isToward(Vec2d pos, Vec2d vel) {
		if(pos.getX()>=x1 && vel.getX()<0d)
			return true;
		if(pos.getX()<=x1 && vel.getX()>0d)
			return true;
		return false;
	}
	
	@Override
	public float distance(Vec2d pos) {
		return Calculator.abs(pos.getX() - x1);
	}

	@Override
	public void bounce(Vec2d pos, Vec2d vel, float charLength) {
		if(pos.getX()>=x1)
			pos.setX(x1 + charLength);
		else
			pos.setX(x1 - charLength);
		vel.setX(vel.getX() * (-Config.RESTITUTION_COEFF_WALL));
		vel.setY(vel.getY() * Config.FRICTION_COEFF);
	}

	protected Line2D.Float shape;
	@Override
	public Shape drawShape() {
		return shape;
	}
}

class DependentOfX extends VerticalWall {
	protected String function;
	protected DependentOfX(String name) { super(name); }
	public DependentOfX(String name, String function) {
		super(name, 0f);
		this.type = TYPE_DEPENDENT_OF_X;
		this.function = function;
	}
}