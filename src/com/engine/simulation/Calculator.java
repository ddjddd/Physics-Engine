package com.engine.simulation;

import com.engine.thing.Thing;

public abstract class Calculator {

	/*
	 * @return distance between two things positions
	 */
	public static float distance(Thing t1, Thing t2) {
		return (float)Math.sqrt((double)distance_sqr(t1, t2));
	}
	public static float distance_sqr(Thing t1, Thing t2) {
		return t1.pos().sub(t2.pos()).sqr();
	}
	
	public static float abs(float x) {
		return x>=0 ? x : -x;
	}
	
}
