package com.engine.thing;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import com.engine.simulation.Vec2d;
import com.engine.wall.Wall;

public class Rectangle extends Thing {
	protected float width, height;
	protected float tangent;
	
	
	/*
	 * @param properties = {mass, width, height}
	 */
	public Rectangle(String name, Vec2d pos, Vec2d vel, Vec2d acc, float mass, float width, float height, float angular) {
		super(name, pos, vel, acc, mass, angular);
		super.type = TYPE_RECTANGLE;
		this.width = width;
		this.height = height;
		this.tangent = height / width;
		shape = new Rectangle2D.Float(pos.getX() - (width/2), pos.getY() - (height/2), width, height);
	}
	
	
	@Override
	protected float charLength(Thing other) {
		double slope = (this.posY() - other.posY()) / (this.posX() - other.posX());
		double slope2 = Math.pow(slope, 2);	//slope ^ 2
		slope = Math.abs(slope);
		if(slope<=tangent)
			return (float)((width / 2) * Math.sqrt(1 + slope2));
		else
			return (float)((height / 2) * Math.sqrt(1 + 1 / slope2));
	}
	
	
	@Override
	protected float charLength(Wall wall) {
		switch(wall.getType()) {
		case Wall.TYPE_VERTICAL :
			return width/2;
		case Wall.TYPE_HORIZONTAL :
			return height/2;
		}
		System.out.println("Rectangle.charLength) not defined wall");
		return 0;
	}

	protected Rectangle2D.Float shape;
	@Override
	public Shape fillShape() {
		shape.x = pos.getX() - width/2;
		shape.y = pos.getY() - height/2;
		return shape;
	}
}