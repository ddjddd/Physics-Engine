package com.engine.simulation;

public final class Config {
	public static int DIMENTION = 2;				//2D
	public static int CONSIDERATION = 3;			//3:가속도까지 생각
	public static double GRAVITY = 9.81;			//중력가속도
	public static double RESTITUTION_COEFF = 0.5;	//반발계수(e)
	public static double FRICTION_COEFF = 0.9;		//바닥면마찰계수
	public static double delT = 0.1;				//시간간격(delta t)
	public static double STOP_POSITION = 1d;		//최소 정지 조건(position)
	public static double STOP_VELOCITY = 1d;		//최소 정지 조건(velocity)
}